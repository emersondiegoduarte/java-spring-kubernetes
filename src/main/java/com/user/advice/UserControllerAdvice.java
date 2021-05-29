package com.user.advice;

import java.util.Date;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import dto.ErrorDTO;
import exceptions.UserNotFoundException;

@ControllerAdvice(basePackages = "com.user.controller")
public class UserControllerAdvice {
	
	@ResponseBody
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(UserNotFoundException.class)
	public ErrorDTO handleUserNotFound(UserNotFoundException userNotFoundException) {
		ErrorDTO errorDTO =	new	ErrorDTO();
		errorDTO.setStatus(HttpStatus.NOT_FOUND.value());
		errorDTO.setMessage("Usuário não encontrado.");
		errorDTO.setTimestamp(new	Date());
		return	errorDTO;
	}
	
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ConstraintViolationException.class)
	public ErrorDTO handleUserConstraintViolationException(ConstraintViolationException constraintViolationException) {
		ErrorDTO errorDTO =	new	ErrorDTO();
		errorDTO.setStatus(HttpStatus.BAD_REQUEST.value());
		StringBuilder sb =	new	StringBuilder("Valor inválido para o(s) campo(s):");	
		sb.append(constraintViolationException.getConstraintName());
		errorDTO.setMessage(sb.toString());
		errorDTO.setTimestamp(new	Date());
		return	errorDTO;
	}

}
