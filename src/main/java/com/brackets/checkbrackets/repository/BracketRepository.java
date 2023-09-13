package com.brackets.checkbrackets.repository;

import com.brackets.checkbrackets.model.Bracket;

import java.util.List;

public interface BracketRepository {
    Bracket addBracket(String opening, String closing);
    Bracket findBracket(String opening, String closing);
    void removeBracket(String opening, String closing);
    Bracket getBracketInfoByChar(char c);
    List<Bracket> getAllBrackets();
    Bracket updateBracket(String oldOpening, String oldClosing, String newOpening, String newClosing);
}
