package com.dummy.shared.excetion;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.dummy.herlper.ErrorMessage;

@Provider
public class ConverterExceptionMapper implements ExceptionMapper<ConverterException> {
	 @Override
	    public Response toResponse(ConverterException exception) {
	        ErrorMessage errorMessage = new ErrorMessage(
	                exception.getMessage(),
	                ErrorMessages.INVALID_INPUT.name()
	        );
	        
	        return Response.status(Response.Status.BAD_REQUEST).
	                entity(errorMessage).
	                build();
	    }
}
