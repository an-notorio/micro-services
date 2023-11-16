package com.example.student.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import jakarta.ws.rs.BadRequestException;
import java.util.NoSuchElementException;

import java.text.ParseException;
import java.util.Objects;

@Slf4j(topic = "GLOBAL_EXCEPTION_HANDLER")
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class GlobalExceptionHandler  {

    public static final String TRACE = "trace";

    @Value("${reflectoring.trace:false}")
    private boolean printStackTrace;




    @Nullable
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return buildErrorResponse(ex, ex.getMessage(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex, WebRequest request) {
        String errorMessage = "Error - malformed request syntax, size too large, invalid request message framing, or deceptive request routing";
        return buildErrorResponse(ex, errorMessage, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> handleInternalError(Exception ex, WebRequest request) {
        log.error("Internal server error occurred", ex);

        String errorMessage = "Error - unexpected condition was encountered and no more specific message is suitable";
        return buildErrorResponse(ex, errorMessage, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }


    //CONTROLLO QUANDO NON VIENE TROVATA NESSUNA RISORSA CON IL CODICE INSERITO
    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleNoSuchElementFoundException(NoSuchElementException itemNotFoundException, WebRequest request) {
        return buildErrorResponse(itemNotFoundException, HttpStatus.NOT_FOUND, request);
    }

    //INSERIMENTO DATI ERRATO
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleBadRequestException(BadRequestException itemBadRequestException, WebRequest request) {
        String errorMessage = "Error - malformed request syntax, size too large, invalid request message framing, or deceptive request routing";
        return buildErrorResponse(itemBadRequestException, errorMessage, HttpStatus.BAD_REQUEST, request);
    }

    //CONTROLLO QUANDO NESSUN VALORE VIENE TROVATO
    @ExceptionHandler(EmptyResultDataAccessException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleNoSuchElementFoundExceptions(EmptyResultDataAccessException itemNotFoundException, WebRequest request) {

        return buildErrorResponse(itemNotFoundException, HttpStatus.NOT_FOUND, request);
    }

    //CONRTOLLO DEL FORMATO DEI NUMERI
    @ExceptionHandler(NumberFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleNumberFormatException(NumberFormatException itemNumberFormatException, WebRequest request) {

        return buildErrorResponse(itemNumberFormatException, "BAD REQUEST", HttpStatus.BAD_REQUEST, request);
    }

    //QUANDO VENGONO INSERITI VALORI NON VALIDI
    @ExceptionHandler(InvalidDataAccessApiUsageException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleInvalidDataAccessApiUsageException(InvalidDataAccessApiUsageException itemInvalidDataAccessApiUsageException, WebRequest request) {

        return buildErrorResponse(itemInvalidDataAccessApiUsageException, "BAD REQUEST", HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(ParseException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleParseException(ParseException itemParseException, WebRequest request) {

        return buildErrorResponse(itemParseException, "BAD REQUEST", HttpStatus.BAD_REQUEST, request);
    }




    private ResponseEntity<Object> buildErrorResponse(Exception exception,
                                                      HttpStatus httpStatus,
                                                      WebRequest request) {
        return buildErrorResponse(exception, httpStatus, request);
    }

    private ResponseEntity<Object> buildErrorResponse(Exception exception,
                                                      String message,
                                                      HttpStatus httpStatus,
                                                      WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(httpStatus.value(), message);
        if (printStackTrace && isTraceOn(request)) {
            errorResponse.setStackTrace(ExceptionUtils.getStackTrace(exception));
        }
        return ResponseEntity.status(httpStatus).body(errorResponse);
    }

    private boolean isTraceOn(WebRequest request) {
        String[] value = request.getParameterValues(TRACE);
        return Objects.nonNull(value)
                && value.length > 0
                && value[0].contentEquals("true");
    }



}
