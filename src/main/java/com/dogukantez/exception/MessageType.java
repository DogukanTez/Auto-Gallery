package com.dogukantez.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public enum MessageType {
    NO_RECORD_EXIST("1004","No record found."),
    TOKEN_IS_EXPIRED("1005","Token is expired"),
    USERNAME_NOT_FOUND("1006","User Not Found."),
    USERNAME_OR_PASSWORD_INCORRECT("1007","Invalid credentials."),
    REFRESH_TOKEN_NOT_FOUND("1008", "Refresh token not found."),
    REFRESH_TOKEN_IS_EXPIRED("1009","Refresh token is expired"),
    CURRENCY_RATES_IS_ERR_OCCURRED("1010","Currency service couldn't operated."),
    CUSTOMER_AMOUNT_IS_NOT_ENOUGH("1011","Customer amount is not enough for buy operation."),
    CAR_STATUS_ALREADY_SOLD("1012","This car is already sold."),
    GENERAL_EXCEPTION("9999","Some error occurred.");

    private final String code;
    private final String message;

    MessageType(String code,String message){
        this.code=code;
        this.message=message;
    }

}
