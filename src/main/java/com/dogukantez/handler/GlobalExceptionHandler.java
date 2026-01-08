package com.dogukantez.handler;

import com.dogukantez.exception.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {BaseException.class})
    public ResponseEntity<ApiError<?>> handleBaseException(BaseException exception, WebRequest request){
        return ResponseEntity.badRequest().body(createApiError(exception.getMessage(),request));
    }

    private String getHostName(){
        try {
            return Inet4Address.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            //throw new RuntimeException(e);
            e.printStackTrace();
        }
        return "";
    }

    public <E> ApiError<E> createApiError(E message, WebRequest request){
        ApiError<E> apiError = new ApiError<>();
        apiError.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

        Exception<E> exception = new Exception<>();
        exception.setPath(request.getDescription(false));
        exception.setCreatedAt(new Date());
        exception.setMessage(message);
        exception.setHostName("");

        apiError.setException(exception);
        return apiError;
    }

}
