package fr.soc.api;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
 * <h1>The API Exception Handler</h1>
 * 
 * <p>
 * Allow to handle exceptions. Log exception details and redirect with an error status.
 * </p>
 * 
 * @author thomas.lemercier.pro@gmail.com
 *
 */
@ControllerAdvice
public class ApiExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(ApiExceptionHandler.class);

	/**
	 * Handle MissingPathVariableException
	 * 
	 * @param request
	 *            The current request details
	 * @param exception
	 *            Appears when parts of the url-path is missing
	 */
	@ExceptionHandler(MissingPathVariableException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ResponseEntity<String> missingPathVariableExceptionHandler(HttpServletRequest request,
			MissingPathVariableException exception) {

		LOGGER.error("[API-EXCEPTION][{}] An argument is missing in the HttpServletRequest", request.getRequestURL());
		LOGGER.error(exception.getMessage());

		return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}

	/**
	 * Handle MethodArgumentTypeMismatchException
	 * 
	 * @param request
	 *            The current request details
	 * @param exception
	 *            Appears when arguments of the url-path has wrong type
	 */
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	@ResponseBody
	public ResponseEntity<String> methodArgumentTypeMismatchExceptionHandler(HttpServletRequest request,
			MethodArgumentTypeMismatchException exception) {

		LOGGER.error("[API-EXCEPTION][{}] An argument has the wrong type in the HttpServletRequest",
				request.getRequestURL());
		LOGGER.error(exception.getMessage());

		return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}

	/**
	 * Handle EntityNotFoundException
	 * 
	 * @param request
	 *            The current request details
	 * @param exception
	 *            Appears when the entity we trying to retrieve doest not exist
	 * @return
	 */
	@ExceptionHandler(EntityNotFoundException.class)
	@ResponseBody
	public ResponseEntity<String> entityNotFoundExceptionHandler(HttpServletRequest request,
			EntityNotFoundException exception) {

		LOGGER.error("[API-EXCEPTION][{}] The Entity has not been found.", request.getRequestURL());
		LOGGER.error(exception.getMessage());

		return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
	}
}
