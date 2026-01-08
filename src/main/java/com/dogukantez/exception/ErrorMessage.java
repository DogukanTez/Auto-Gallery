package com.dogukantez.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessage {
    private String ofStatic;
    private MessageType messageType;

    public String prepareErrormessage(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(messageType.getMessage());
        if(this.ofStatic !=null){
            //stringBuilder.append(" : "+ofStatic);
            stringBuilder.append(" : ");
            stringBuilder.append(ofStatic);
        }
        return stringBuilder.toString();
    }

}
