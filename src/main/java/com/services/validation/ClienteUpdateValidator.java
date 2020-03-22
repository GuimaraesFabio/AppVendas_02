package com.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.controllers.exceptions.FieldMessage;
import com.dtos.ClienteDto;
import com.models.Cliente;
import com.repositories.ClienteRepository;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDto> {

	@Autowired
	private ClienteRepository _clienteRepository;

	@Autowired
	private HttpServletRequest _request;

	@Override
	public void initialize(ClienteUpdate ann) {
	}

	@Override
	public boolean isValid(ClienteDto objDto, ConstraintValidatorContext context) {

		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) _request
				.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer auxId = Integer.parseInt(map.get("id"));

		Cliente aux = _clienteRepository.findByEmail(objDto.getEmail());

		List<FieldMessage> list = new ArrayList<>();

		if (aux != null && !aux.getId().equals(auxId)) {
			list.add(new FieldMessage("email", "Email ja cadastrado."));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
