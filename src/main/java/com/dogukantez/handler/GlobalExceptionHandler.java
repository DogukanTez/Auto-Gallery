package com.dogukantez.handler;

import com.dogukantez.exception.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {BaseException.class})
    public ResponseEntity<ApiError<?>> handleBaseException(BaseException exception, WebRequest request){
        return ResponseEntity.badRequest().body(createApiError(exception.getMessage(),request));
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<ApiError<Map<String, List<String>>>> handleMethodNotValidException(MethodArgumentNotValidException ex, WebRequest request){
        Map<String, List<String>> map= new HashMap<>();
        for(ObjectError objErr : ex.getBindingResult().getAllErrors()){
            String fieldName= ((FieldError)objErr).getField();
            if (map.containsKey(fieldName)){
                map.put(fieldName,addValue(map.get(fieldName),objErr.getDefaultMessage()));
            }else {
                map.put(fieldName,addValue(new ArrayList<>(),objErr.getDefaultMessage()));
            }

        }
        return ResponseEntity.badRequest().body(createApiError(map,request));
    }

    private List<String> addValue(List<String> list, String newValue){
        list.add(newValue);
        return list;
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
