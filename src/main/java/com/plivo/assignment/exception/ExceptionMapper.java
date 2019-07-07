package com.plivo.assignment.exception;

import com.plivo.assignment.dto.*;

import org.springframework.http.*;
import org.springframework.validation.*;
import org.springframework.web.*;
import org.springframework.web.bind.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.*;

import java.util.*;

@ControllerAdvice
public class ExceptionMapper {

    @ExceptionHandler(ApplicationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse.AppResponse exception(ApplicationException ex) {

        ErrorResponse.AppResponse error = new ErrorResponse.AppResponse();
        error.setMessage("");
        error.setError(ex.getMessage());

        return error;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleException(MethodArgumentNotValidException ex) {

        List<FieldError> errors = ex.getBindingResult().getFieldErrors();

        List<ErrorResponse.AppResponse> errorDetails = new ArrayList<>();
        for (FieldError fieldError : errors) {
            ErrorResponse.AppResponse error = new ErrorResponse.AppResponse();
            error.setMessage("");
            error.setError(fieldError.getDefaultMessage());
            errorDetails.add(error);
        }

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrors(errorDetails);

        return errorResponse;
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResponse.AppResponse handleInternalError(RuntimeException ex) {

        ErrorResponse.AppResponse error = new ErrorResponse.AppResponse();
        error.setMessage("");
        error.setError("unknown failure");

        return error;
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ResponseBody
    public ErrorResponse.AppResponse handleMethodNotSupported(HttpRequestMethodNotSupportedException ex) {

        ErrorResponse.AppResponse error = new ErrorResponse.AppResponse();
        error.setMessage("");
        error.setError("Http Method Not supported");

        return error;
    }

}
