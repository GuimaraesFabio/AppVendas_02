package com.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.models.Categoria;
import com.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository _repository;

	public List<Categoria> findAll() {
		return _repository.findAll();
	}
}
