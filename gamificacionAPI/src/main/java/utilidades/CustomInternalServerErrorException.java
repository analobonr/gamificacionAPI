package utilidades;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class CustomInternalServerErrorException extends Exception{

	
	private static final long serialVersionUID = 1L;

	public CustomInternalServerErrorException (String message) {
	      super(message);
	  }
}
