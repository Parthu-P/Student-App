package in.zkteco.util;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class StudentExceptionHandler {
	
	@ExceptionHandler(value=InvalidDataException.class)
	public ResponseEntity<ErrorInfo> userDefindExceptionHandler(InvalidDataException ex){
		String msg = ex.getMessage();
		ErrorInfo error=new ErrorInfo();
		error.setMessage(msg);
		error.setWhen(LocalDateTime.now());
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value=EntityNotFoundException.class)
	public ResponseEntity<String> handleException(EntityNotFoundException ex){
		return ResponseEntity
				.status(HttpStatus.NOT_FOUND)
				.body(ex.getMessage());
		
	}
}
