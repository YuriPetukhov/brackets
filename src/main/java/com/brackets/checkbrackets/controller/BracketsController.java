package com.brackets.checkbrackets.controller;

import com.brackets.checkbrackets.exception.BracketNotFoundException;
import com.brackets.checkbrackets.exception.NoInputException;
import com.brackets.checkbrackets.model.Bracket;
import com.brackets.checkbrackets.model.TextRequest;
import com.brackets.checkbrackets.service.BracketsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BracketsController {
    private final BracketsService service;

    public BracketsController(BracketsService service) {
        this.service = service;
    }

    @PostMapping("/checkBrackets")
    public ResponseEntity<Boolean> checkBrackets(@RequestBody TextRequest textRequest) {
        Boolean isCorrect = service.checkBrackets(textRequest);
        return ResponseEntity.ok(isCorrect);
    }
    @PostMapping("/brackets")
    public Bracket addBracket(@RequestBody Bracket bracket) {
        return service.addBracket(bracket.getOpening(), bracket.getClosing());
    }
    @DeleteMapping("/brackets")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void removeBracket(@RequestBody Bracket bracket) {
        service.removeBracket(bracket.getOpening(), bracket.getClosing());
    }
    @GetMapping("/brackets")
    public Bracket findBracket(
            @RequestParam("opening") String opening,
            @RequestParam("closing") String closing) {
        return service.findBracket(opening, closing);
    }
    @GetMapping("/brackets/all")
    public List<Bracket> getAllBrackets() {
        return service.getAllBrackets();
    }
    @PutMapping("/brackets")
    public Bracket updateBracket(
            @RequestParam("oldOpening") String oldOpening,
            @RequestParam("oldClosing") String oldClosing,
            @RequestParam("newOpening") String newOpening,
            @RequestParam("newClosing") String newClosing) {
        return service.updateBracket(oldOpening, oldClosing, newOpening, newClosing);
    }
}
