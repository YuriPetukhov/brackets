package com.brackets.checkbrackets.service;

import com.brackets.checkbrackets.model.Bracket;
import com.brackets.checkbrackets.model.TextRequest;

import java.util.List;

public interface BracketsService {
    Bracket addBracket(String opening, String closing);
    Bracket findBracket(String opening, String closing);
    void removeBracket(String opening, String closing);
    boolean checkBrackets(TextRequest textRequest);
    List<Bracket> getAllBrackets();
    Bracket updateBracket(String oldOpening, String oldClosing, String newOpening, String newClosing);
}
