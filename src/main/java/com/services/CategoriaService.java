package com.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.models.Categoria;
import com.repositories.CategoriaRepository;
import com.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository _repository;

	public List<Categoria> findAll() {
		return _repository.findAll();
	}

	public Categoria findById(Integer id) {

		Optional<Categoria> obj = _repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não foi encontrado."));
	}
}
