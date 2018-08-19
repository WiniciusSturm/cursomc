package com.winiciussturm.cursomc.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.winiciussturm.cursomc.domain.ItemPedido;
import com.winiciussturm.cursomc.domain.PagamentoComBoleto;
import com.winiciussturm.cursomc.domain.Pedido;
import com.winiciussturm.cursomc.domain.enums.EstadoPagamento;
import com.winiciussturm.cursomc.repositories.ClienteRepository;
import com.winiciussturm.cursomc.repositories.ItemPedidoRepository;
import com.winiciussturm.cursomc.repositories.PagamentoRepository;
import com.winiciussturm.cursomc.repositories.PedidoRepository;
import com.winiciussturm.cursomc.repositories.ProdutoRepository;
import com.winiciussturm.cursomc.services.exceptions.ObjectNotFoundException;

@Service // @ = anotação do Spring Boot
public class PedidoService 
{
	@Autowired //Instancia automaticamente as dependências dentro de uma classe
	private PedidoRepository repo;
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private EmailService emailService;
	
	public Pedido find(Integer id)
	{
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException (
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}
	
	@Transactional
	public Pedido insert (Pedido obj)
	{
		obj.setId(null);
		obj.setInstante(new Date());
		obj.setCliente(clienteService.find(obj.getCliente().getId()));
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if (obj.getPagamento() instanceof PagamentoComBoleto)
		{
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento(); //Casting do obj.getPagamento()
			boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
		}
		obj = repo.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		for (ItemPedido ip : obj.getItens())
		{
			ip.setDesconto(0.0);
			ip.setProduto(produtoService.find(ip.getProduto().getId()));
			ip.setPreco(ip.getProduto().getPreco());
			ip.setPedido(obj);
		}
		itemPedidoRepository.saveAll(obj.getItens());
		emailService.sendOrderConfirmationEmail(obj);
		return obj;
	}
	
}
