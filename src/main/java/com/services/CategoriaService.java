package com.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.dtos.CategoriaDto;
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

	public Page<Categoria> findPage(Integer page, Integer linePerPage, String direction, String orderBy) {

		PageRequest pr = PageRequest.of(page, linePerPage, Direction.valueOf(direction), orderBy);
		return _repository.findAll(pr);
	}

	public Categoria insert(Categoria obj) {
		if (obj.getId() == null) {
			_repository.save(obj);
		}
		return obj;
	}

	public Categoria fromDto(CategoriaDto objDto) {
		return new Categoria(objDto.getId(), objDto.getNome());
	}
}
