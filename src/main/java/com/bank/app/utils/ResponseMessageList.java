package com.bank.app.utils;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ResponseMessageList {

    private String message;
    private List<String> stringList;

    public void setMessage(String message) {
        stringList.add(message);
    }
}
