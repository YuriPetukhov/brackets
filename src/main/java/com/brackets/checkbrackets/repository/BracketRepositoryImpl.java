package com.brackets.checkbrackets.repository;

import com.brackets.checkbrackets.exception.BracketAllreadyExistException;
import com.brackets.checkbrackets.exception.BracketNotFoundException;
import com.brackets.checkbrackets.model.Bracket;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class BracketRepositoryImpl implements BracketRepository {
    private final List<Bracket> brackets = new ArrayList<>();

    public BracketRepositoryImpl() {
        addBracket("(", ")");
        addBracket("[", "]");
        addBracket("{", "}");
    }
    @Override
    public Bracket addBracket(String opening, String closing) {
        for (Bracket bracket : brackets) {
            if (bracket.getOpening().equals(opening) && bracket.getClosing().equals(closing)) {
                throw new BracketAllreadyExistException("Такие скобки уже есть");
            }
        }
        Bracket newBracket = new Bracket(opening, closing);
        brackets.add(newBracket);
        return newBracket;
    }
    @Override
    public Bracket findBracket(String opening, String closing) {
        for (Bracket bracket : brackets) {
            if (bracket.getOpening().equals(opening) && bracket.getClosing().equals(closing)) {
                return bracket;
            }
        }
        throw new BracketNotFoundException("Скобки не найдены");
    }
    @Override
    public void removeBracket(String opening, String closing) {
        for (Bracket bracket : brackets) {
            if (bracket.getOpening().equals(opening) && bracket.getClosing().equals(closing)) {
                brackets.remove(bracket);
                return;
            }
        }
        throw new BracketNotFoundException("Скобки не найдены");
    }
    @Override
    public Bracket getBracketInfoByChar(char c) {
        for (Bracket bracket : brackets) {
            if (bracket.getOpening().equals(Character.toString(c))) {
                return bracket;
            }
            if (bracket.getClosing().equals(Character.toString(c))) {
                return bracket;
            }
        }
        return null;
    }
    @Override
    public List<Bracket> getAllBrackets() {
        return brackets;
    }

    @Override
    public Bracket updateBracket(String oldOpening, String oldClosing, String newOpening, String newClosing) {
        for (Bracket bracket : brackets) {
            if (bracket.getOpening().equals(oldOpening) && bracket.getClosing().equals(oldClosing)) {
                bracket.setOpening(newOpening);
                bracket.setClosing(newClosing);
                return bracket;
            }
        }
        throw new BracketNotFoundException("Скобки не найдены");
    }
}