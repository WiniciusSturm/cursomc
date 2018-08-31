package com.winiciussturm.cursomc.services;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.winiciussturm.cursomc.domain.Cidade;
import com.winiciussturm.cursomc.domain.Cliente;
import com.winiciussturm.cursomc.domain.Endereco;
import com.winiciussturm.cursomc.domain.enums.Perfil;
import com.winiciussturm.cursomc.domain.enums.TipoCliente;
import com.winiciussturm.cursomc.dto.ClienteDTO;
import com.winiciussturm.cursomc.dto.ClienteNewDTO;
import com.winiciussturm.cursomc.repositories.ClienteRepository;
import com.winiciussturm.cursomc.repositories.EnderecoRepository;
import com.winiciussturm.cursomc.security.UserSS;
import com.winiciussturm.cursomc.services.exceptions.AuthorizationException;
import com.winiciussturm.cursomc.services.exceptions.DataIntegrityException;
import com.winiciussturm.cursomc.services.exceptions.ObjectNotFoundException;

@Service // @ = anotação do Spring Boot
public class ClienteService 
{
	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired //Instancia automaticamente as dependências dentro de uma classe
	private ClienteRepository repo;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private S3Service s3Service;
	
	@Autowired
	private ImageService imageService;
	
	@Value("${img.prefix.client.profile}")
	private String prefix;
	
	public Cliente find(Integer id)
	{
		UserSS user = UserService.authenticated();
		if (user==null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getID()))
		{
			throw new AuthorizationException("Acesso negado");
		}
		
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException (
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}
	
	@Transactional
	public Cliente insert (Cliente obj)
	{
		obj.setId(null); //Apenas para garantir que o objeto a ser inserido será um novo objeto
		obj = repo.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
	}
	
	public Cliente update (Cliente obj)
	{
		Cliente newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	public void delete (Integer id)
	{
		find(id);
		try
		{
			repo.deleteById(id);
		}
		catch (DataIntegrityViolationException e)
		{
			throw new DataIntegrityException("Não é possível excluir pois há pedidos relacionados");
		}
	}
	

	public List<Cliente> findAll()
	{
		return repo.findAll();
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) //Page: classe do Spring Data, encapsula informações e operações de paginação
	{
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Cliente fromDTO(ClienteDTO objDTO)
	{
		return new Cliente(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(), null, null, null);
	}
	
	public Cliente fromDTO(ClienteNewDTO objDTO)
	{
		Cliente cli = new Cliente(null, objDTO.getNome(), objDTO.getEmail(), objDTO.getCpfOuCnpj(), TipoCliente.toEnum(objDTO.getTipo()), pe.encode(objDTO.getSenha()));
		Cidade cid = new Cidade(objDTO.getCidadeId(), null, null);
		Endereco end = new Endereco(null, objDTO.getLogradouro(), objDTO.getNumero(), objDTO.getComplemento(), objDTO.getBairro(), objDTO.getCep(), cli, cid);
		cli.getEnderecos().add(end);
		cli.getTelefones().add(objDTO.getTelefone1());
		if (objDTO.getTelefone2()!=null)
		{
			cli.getTelefones().add(objDTO.getTelefone2());
		}
		if (objDTO.getTelefone3()!=null)
		{
			cli.getTelefones().add(objDTO.getTelefone3());
		}
		return cli;
	}
	
	private void updateData(Cliente newObj, Cliente obj)
	{
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
	
	public URI uploadProfilePicture (MultipartFile multipartFile)
	{
		UserSS user = UserService.authenticated();
		if (user == null)
		{
			throw new AuthorizationException("Acesso negado");
		}
		BufferedImage jpgImage = imageService.getJpgImageFromFile(multipartFile);
		String fileName = prefix + user.getID() + ".jpg";
		return s3Service.uploadFile(fileName, imageService.getInputStream(jpgImage, "jpg"), "image");
	}
	
}
