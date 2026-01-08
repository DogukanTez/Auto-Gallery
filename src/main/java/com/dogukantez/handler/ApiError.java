package com.dogukantez.handler;

import com.dogukantez.exception.ErrorMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiError<E> {
    private int status;
    private String message;
    private Exception<E> exception;
}
