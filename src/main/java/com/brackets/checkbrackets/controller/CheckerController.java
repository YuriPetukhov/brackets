package com.brackets.checkbrackets.controller;

import com.brackets.checkbrackets.service.Checker;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CheckerController {
    private final Checker checker;

    public CheckerController(Checker checker) {
        this.checker = checker;
    }
    @PostMapping("/checkBrackets")
    public boolean checkBrackets(@RequestBody String text){
        return checker.checkBrackets(text);
    }
}
