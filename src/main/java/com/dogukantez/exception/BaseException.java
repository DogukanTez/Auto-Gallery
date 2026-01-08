package com.dogukantez.exception;

public class BaseException extends RuntimeException{
    public BaseException(ErrorMessage errorMessage){
        super(errorMessage.prepareErrormessage());
    }
}
