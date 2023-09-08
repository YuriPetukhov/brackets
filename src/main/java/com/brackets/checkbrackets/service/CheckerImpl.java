package com.brackets.checkbrackets.service;

import org.springframework.stereotype.Service;

import java.util.ArrayDeque;
import java.util.Deque;
@Service
public class CheckerImpl implements Checker{
    @Override
    public boolean checkBrackets(String text) {
        Deque<Character> stack = new ArrayDeque<>();
        boolean hasTextBetweenBrackets = false;

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);

            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
                hasTextBetweenBrackets = false;
            } else if (c == ')' || c == '}' || c == ']') {
                if (stack.isEmpty() || !hasTextBetweenBrackets) {
                    return false;
                }

                char top = stack.pop();
                if ((c == ')' && top != '(') ||
                        (c == '}' && top != '{') ||
                        (c == ']' && top != '[')) {
                    return false;
                }

                hasTextBetweenBrackets = false;
            } else if (!stack.isEmpty()) {
                hasTextBetweenBrackets = true;
            }
        }
        return stack.isEmpty();
    }
}
