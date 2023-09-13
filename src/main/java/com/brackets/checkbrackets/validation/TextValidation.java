package com.brackets.checkbrackets.validation;

import com.brackets.checkbrackets.exception.NoInputException;
import com.brackets.checkbrackets.model.TextRequest;

public class TextValidation {
    public static void checkTextNotNullOrEmpty(TextRequest textRequest) {
        String text = textRequest.getText();
        if (text == null || text.trim().isEmpty()) {
            throw new NoInputException("Текст не введен");
        }
    }
}
