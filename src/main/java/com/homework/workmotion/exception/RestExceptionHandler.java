package com.homework.workmotion.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.homework.workmotion.generated.dto.ErrorDetails;

@RestControllerAdvice
public class RestExceptionHandler {

	private static final Logger LOG = LoggerFactory.getLogger(RestExceptionHandler.class);

	@ExceptionHandler(value = { BadRequestException.class })
	private ResponseEntity<ErrorDetails> handleBadRequest(BadRequestException ex) {
		LOG.error(ex.getMessage(), ex);
		ErrorDetails errorDetails = new ErrorDetails().message(ex.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
	}

	@ExceptionHandler(value = { NotFoundException.class })
	private ResponseEntity<ErrorDetails> handleBadRequest(NotFoundException ex) {
		LOG.error(ex.getMessage(), ex);
		ErrorDetails errorDetails = new ErrorDetails().message(ex.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);
	}

	@ExceptionHandler(value = { Throwable.class })
	private ResponseEntity<ErrorDetails> handleInternalServerError(Throwable ex) {
		LOG.error(ex.getMessage(), ex);
		ErrorDetails errorDetails = new ErrorDetails().message("Unexpected Server Error Occured.");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);
	}
}
