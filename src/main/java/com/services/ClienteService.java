package com.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.models.Cliente;
import com.repositories.ClienteRepository;
import com.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository _repository;

	public List<Cliente> findAll() {
		return _repository.findAll();
	}

	public Cliente findById(Integer id) {

		Optional<Cliente> obj = _repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não foi encontrado."));
	}
}
