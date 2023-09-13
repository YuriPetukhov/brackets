package com.brackets.checkbrackets.service;

import com.brackets.checkbrackets.exception.BracketNotFoundException;
import com.brackets.checkbrackets.exception.NoInputException;
import com.brackets.checkbrackets.model.Bracket;
import com.brackets.checkbrackets.model.TextRequest;
import com.brackets.checkbrackets.repository.BracketRepository;
import com.brackets.checkbrackets.validation.TextValidation;
import org.springframework.stereotype.Service;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

@Service
public class BracketsServiceImpl implements BracketsService {
    private final BracketRepository repository;

    public BracketsServiceImpl(BracketRepository repository) {
        this.repository = repository;
    }

    @Override
    public Bracket addBracket(String opening, String closing) {
        return repository.addBracket(opening, closing);
    }

    @Override
    public Bracket findBracket(String opening, String closing) {
        return repository.findBracket(opening, closing);
    }

    @Override
    public void removeBracket(String opening, String closing) {
        repository.removeBracket(opening, closing);
    }

    @Override
    public boolean checkBrackets(TextRequest textRequest) {
        TextValidation.checkTextNotNullOrEmpty(textRequest);

        String text = textRequest.getText();
        Deque<Bracket> stack = new ArrayDeque<>();
        boolean hasBrackets = false;
        boolean hasTextBetweenBrackets = false;

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            Bracket bracket = repository.getBracketInfoByChar(c);

            if (bracket != null) {
                if (bracket.getOpening().equals(String.valueOf(c))) {
                    stack.push(bracket);
                    hasTextBetweenBrackets = false;
                    hasBrackets = true;
                } else {
                    if (stack.isEmpty() || !stack.peek().getOpening().equals(bracket.getOpening()) || !hasTextBetweenBrackets) {
                        return false;
                    }
                    Bracket topBracket = stack.pop();

                    if (!topBracket.getOpening().equals(bracket.getOpening())) {
                        return false;
                    }
                    hasTextBetweenBrackets = false;
                }
            } else if (!stack.isEmpty()) {
                hasTextBetweenBrackets = true;
            }
        }
        return hasBrackets && stack.isEmpty();
    }

    @Override
    public List<Bracket> getAllBrackets() {
        return repository.getAllBrackets();
    }

    @Override
    public Bracket updateBracket(String oldOpening, String oldClosing, String newOpening, String newClosing) {
        return repository.updateBracket(oldOpening, oldClosing, newOpening, newClosing);
    }
}
