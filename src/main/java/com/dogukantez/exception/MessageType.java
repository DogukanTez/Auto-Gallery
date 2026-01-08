package com.dogukantez.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public enum MessageType {
    NO_RECORD_EXIST("1004","No record found."),
    GENERAL_EXCEPTION("9999","Some error occurred.");

    private final String code;
    private final String message;

    MessageType(String code,String message){
        this.code=code;
        this.message=message;
    }

}
