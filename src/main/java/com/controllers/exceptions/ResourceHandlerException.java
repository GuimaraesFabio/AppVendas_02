package com.controllers.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.services.exceptions.DataIntegrityException;
import com.services.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ResourceHandlerException {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNoFound(ObjectNotFoundException e, HttpServletRequest request) {

		String error = "Objeto não encontrado.";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(),
				request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidatorError> validatorError(MethodArgumentNotValidException e,
			HttpServletRequest request) {

		String error = "Erro de validação.";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ValidatorError err = new ValidatorError(Instant.now(), status.value(), error, error, request.getRequestURI());

		for (FieldError x : e.getBindingResult().getFieldErrors()) {

			err.addErrors(x.getField(), x.getDefaultMessage());
		}

		return ResponseEntity.status(status).body(err);
	}

	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<StandardError> dataIntegrityException(DataIntegrityException e, HttpServletRequest request) {

		String error = "Erro de integridade referencial.";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(),
				request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
}
