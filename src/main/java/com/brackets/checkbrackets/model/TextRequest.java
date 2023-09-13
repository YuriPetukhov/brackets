package com.brackets.checkbrackets.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TextRequest {
    private String text;
    public TextRequest(String text) {
        this.text = text;
    }
}
