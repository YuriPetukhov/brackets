package com.brackets.checkbrackets.service;

import static org.junit.jupiter.api.Assertions.*;

import com.brackets.checkbrackets.exception.NoInputException;
import com.brackets.checkbrackets.model.Bracket;
import com.brackets.checkbrackets.model.TextRequest;
import com.brackets.checkbrackets.repository.BracketRepository;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class BracketsServiceImplTest {

    @Mock
    private BracketRepository repository;
    @InjectMocks
    private BracketsServiceImpl bracketsService;
    private static final Faker FAKER = new Faker();

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        if (closeable != null) {
            closeable.close();
        }
    }

    @ParameterizedTest
    @CsvSource({
            "open=\"(\",close=\")\"",
            "open=\"[\",close=\"]\"",
            "open=\"{\",close=\"}\"",
            "open=\"<\",close=\">\"",
    })
    void addBracket(String open, String close) {
        Bracket expected = new Bracket(open, close);

        String randomOpen = String.valueOf(FAKER.lorem().character());
        String randomClose = String.valueOf(FAKER.lorem().character());

        when(repository.addBracket(randomOpen, randomClose)).thenReturn(expected);

        Bracket actual = bracketsService.addBracket(randomOpen, randomClose);

        Assertions.assertEquals(expected, actual);
    }

    @ParameterizedTest
    @CsvSource({
            "\"(\",\")\"",
            "\"[\",\"]\"",
            "\"{\",\"}\"",
            "\"<\",\">\"",
    })
    void findBracket(String open, String close) {
        Bracket expected = new Bracket(open, close);
        when(repository.findBracket(open, close)).thenReturn(expected);

        Bracket actual = bracketsService.findBracket(open, close);

        assertEquals(expected, actual);
    }
    @Test
    void removeBracket() {
        bracketsService.removeBracket("(", ")");
        verify(repository, times(1)).removeBracket("(", ")");
    }

    @Test
    void checkBracketsValid() {
        when(repository.getBracketInfoByChar(anyChar())).thenAnswer(invocation -> {
            char c = invocation.getArgument(0);
            if (c == '(') return new Bracket("(", ")");
            else if (c == ')') return new Bracket("(", ")");
            else return null;
        });

        assertTrue(bracketsService.checkBrackets(new TextRequest("abc(de)fg")));
    }

    @Test
    void checkBracketsInvalid() {
        when(repository.getBracketInfoByChar(anyChar())).thenAnswer(invocation -> {
            char c = invocation.getArgument(0);
            if (c == '(') return new Bracket("(", ")");
            else if (c == ')') return new Bracket("(", ")");
            else return null;
        });

        assertFalse(bracketsService.checkBrackets(new TextRequest("abc(de))fg")));
    }

    @Test
    void checkBracketsNoInput() {
        assertThrows(NoInputException.class, () -> bracketsService.checkBrackets(new TextRequest(null)));
    }

    @Test
    void getAllBrackets() {
        List<Bracket> expected = Arrays.asList(new Bracket("(", ")"), new Bracket("<", ">"));
        when(repository.getAllBrackets()).thenReturn(expected);

        List<Bracket> actual = bracketsService.getAllBrackets();

        assertEquals(expected, actual);
    }

    @Test
    void updateBracket() {
        Bracket expected = new Bracket("{", "}");
        when(repository.updateBracket("(", ")", "{", "}")).thenReturn(expected);

        Bracket actual = bracketsService.updateBracket("(", ")", "{", "}");

        assertEquals(expected, actual);
    }
}
