package com.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dtos.ClienteDto;
import com.dtos.ClienteNewDto;
import com.enums.TipoCliente;
import com.models.Cidade;
import com.models.Cliente;
import com.models.Endereco;
import com.repositories.CidadeRepository;
import com.repositories.ClienteRepository;
import com.repositories.EnderecoRepository;
import com.services.exceptions.DataIntegrityException;
import com.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository _repository;

	@Autowired
	private CidadeRepository _cidadeRepository;

	@Autowired
	private EnderecoRepository _enderecoRepository;

	public List<Cliente> findAll() {
		return _repository.findAll();
	}

	public Cliente findById(Integer id) {

		Optional<Cliente> obj = _repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não foi encontrado."));
	}

	public Page<Cliente> findPage(Integer page, Integer linePerPage, String direction, String orderBy) {

		PageRequest pr = PageRequest.of(page, linePerPage, Direction.valueOf(direction), orderBy);
		return _repository.findAll(pr);
	}

	@Transactional
	public Cliente insert(Cliente obj) {
		if (obj.getId() == null) {
			_repository.save(obj);
			_enderecoRepository.saveAll(obj.getEnderecos());
		}
		return obj;
	}

	public Cliente update(Cliente obj) {
		Cliente newObj = findById(obj.getId());
		updateData(newObj, obj);
		return _repository.save(newObj);
	}

	public void delete(Integer id) {

		findById(id);
		try {
			_repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException(
					"Erro ao excluir, existem relacionamento entre a categoria e demais entidades.");
		}

	}

	private void updateData(Cliente newObj, Cliente obj) {

		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}

	public Cliente fromDto(ClienteDto objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
	}

	public Cliente fromDto(ClienteNewDto objDto) {

		Cliente cli = new Cliente(null, objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(),
				TipoCliente.toEnum(objDto.getTipo()));
		Cidade cid = _cidadeRepository.getOne(objDto.getCidadeId());
		Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(),
				objDto.getBairro(), objDto.getCep(), cli, cid);

		cli.getTelefones().add(objDto.getTelefone1());
		cli.getEnderecos().add(end);

		if (objDto.getTelefone2() != null) {
			cli.getTelefones().add(objDto.getTelefone2());
		}
		if (objDto.getTelefone3() != null) {
			cli.getTelefones().add(objDto.getTelefone3());
		}

		return cli;
	}

}
