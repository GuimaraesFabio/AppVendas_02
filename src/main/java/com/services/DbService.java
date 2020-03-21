package com.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.models.Categoria;
import com.repositories.CategoriaRepository;

@Service
public class DbService {

	@Autowired
	private CategoriaRepository _categoriaRepository;

	public void instanciateDataBase() {

		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");

		_categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
	}
}
