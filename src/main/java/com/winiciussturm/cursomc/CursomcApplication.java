package com.winiciussturm.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.winiciussturm.cursomc.domain.Categoria;
import com.winiciussturm.cursomc.repositories.CategoriaRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner { //implements para instanciar as categorias

	@Autowired
	private CategoriaRepository categoriaRepository;//Dependência do repository para gravar as categorias instanciadas no banco de dados
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception 
	{
		
		Categoria cat1 = new Categoria (null, "Informática");// Nulo pq o banco de dados criará automaticamente o ID
		Categoria cat2 = new Categoria(null, "Escritório");
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));//Arrays.aslist cria uma lista com vários elementos a serem gravados no banco de dados
		
	}
}
