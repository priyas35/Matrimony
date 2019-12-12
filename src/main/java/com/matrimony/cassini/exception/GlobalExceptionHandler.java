package com.matrimony.cassini.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.matrimony.cassini.constants.Constant;
import com.matrimony.cassini.dto.ErrorDto;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorDto> userNotFoundException(){
		ErrorDto errorDto = new ErrorDto();
		errorDto.setMessage(Constant.USER_NOT_FOUND);
		errorDto.setStatusCode(HttpStatus.NOT_FOUND.value());
		return ResponseEntity.ok().body(errorDto);
	}
	
	@ExceptionHandler(RequestNotRaisedException.class)
	public ResponseEntity<ErrorDto> requestNotRaisedException(){
		ErrorDto errorDto = new ErrorDto();
		errorDto.setMessage(Constant.REQUEST_NOT_RAISED);
		errorDto.setStatusCode(HttpStatus.NOT_FOUND.value());
		return ResponseEntity.ok().body(errorDto);
	}


}
