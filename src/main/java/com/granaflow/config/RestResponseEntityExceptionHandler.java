package com.granaflow.config;

import java.util.Arrays;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.granaflow.config.erro.Erro;
import com.granaflow.config.erro.ErroResponse;
import com.granaflow.exception.DataInvalidaException;

import lombok.Generated;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Generated
@RestControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	private static final String ERROR_HANDER_MESSAGE = "[ExceptionHandler] Sistema com erro";

	private static final String MSG_ERROR_SERVER = "O servidor apresentou erro. Entre em contato com administrador do sistema.";

	@ExceptionHandler(Exception.class)
	protected ResponseEntity<Object> handleGeneralError(Exception ex, WebRequest request) {
		log.error(ERROR_HANDER_MESSAGE, ex);
		var erros = Arrays.asList(new Erro(HttpStatus.INTERNAL_SERVER_ERROR.value(), MSG_ERROR_SERVER));
		var bodyOfResponse = new ErroResponse(erros);
		return ResponseEntity.internalServerError().body(bodyOfResponse);
	}

	@ExceptionHandler(DataInvalidaException.class)
	protected ResponseEntity<Object> handleDataInvalidaException(DataInvalidaException ex, WebRequest request) {
		var erros = Arrays.asList(new Erro(HttpStatus.UNPROCESSABLE_ENTITY.value(), ex.getMessage()));
		var bodyOfResponse = new ErroResponse(erros);
		return ResponseEntity.unprocessableEntity().body(bodyOfResponse);
	}

}
