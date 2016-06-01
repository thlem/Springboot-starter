package fr.soc.api;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ApiExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(ApiExceptionHandler.class);
	
	@ExceptionHandler(MissingPathVariableException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public void missingPathVariableExceptionHandler(HttpServletRequest request, MissingPathVariableException exception){
		LOGGER.error("[API-EXCEPTION][{}] An argument is missing in the HttpServletRequest", request.getRequestURL());
		LOGGER.debug(exception.getMessage());
	}
}
