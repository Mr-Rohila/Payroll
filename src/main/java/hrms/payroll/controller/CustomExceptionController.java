package hrms.payroll.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import hrms.payroll.dto.GenericErrorResponse;

@RestControllerAdvice
public class CustomExceptionController {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public GenericErrorResponse methodArgumentNotValidException(MethodArgumentNotValidException exception) {
		Map<String, String> errors = new HashMap<>();
		exception.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return GenericErrorResponse.builder().error("Data Validation Error").status(HttpStatus.BAD_REQUEST.value())
				.data(errors).build();
	}

	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
	public GenericErrorResponse illegalArgumentException(IllegalArgumentException exception) {
		return GenericErrorResponse.builder().status(HttpStatus.NOT_ACCEPTABLE.value()).error(exception.getMessage())
				.build();
	}

}
