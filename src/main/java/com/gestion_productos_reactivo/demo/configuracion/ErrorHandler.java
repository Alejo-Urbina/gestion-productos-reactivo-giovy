package com.gestion_productos_reactivo.demo.configuracion;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gestion_productos_reactivo.demo.excepciones.GenericExcepcion;
import com.gestion_productos_reactivo.demo.excepciones.ProductoExcepcion;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {
    private final HttpServletRequest httpServletRequest;

    public ErrorHandler(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ApiErrorResponse> handle(Throwable ex) {
        return buildResponseError(HttpStatus.INTERNAL_SERVER_ERROR, ex);
    }

    private ResponseEntity<ApiErrorResponse> buildResponseError(HttpStatus httpStatus, Throwable ex) {

        final ApiErrorResponse apiErrorResponse = new ApiErrorResponse(ex.getMessage());
        return new ResponseEntity<>(apiErrorResponse, httpStatus);
    }

    @ExceptionHandler({
            ProductoExcepcion.class,
    })
    public ResponseEntity<ApiErrorResponse> handle(GenericExcepcion ex) {
        final ApiErrorResponse apiErrorResponse = new ApiErrorResponse(ex.getMensaje());
        return new ResponseEntity<>(apiErrorResponse, HttpStatus.BAD_REQUEST);
    }


    private static class ApiErrorResponse {

        @JsonProperty
        private final String mensaje;

        private ApiErrorResponse(String mensaje) {
            this.mensaje = mensaje;
        }
    }


}
