package com.brackets.checkbrackets.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.brackets.checkbrackets.exception.BracketAllreadyExistException;
import com.brackets.checkbrackets.exception.BracketNotFoundException;
import com.brackets.checkbrackets.model.Bracket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class BracketRepositoryImplTest {

    private BracketRepositoryImpl bracketRepository;

    @BeforeEach
    void setUp() {
        bracketRepository = new BracketRepositoryImpl();
    }

    @Test
    void testAddBracket() {
        Bracket newBracket = bracketRepository.addBracket("<", ">");
        assertEquals("<", newBracket.getOpening());
        assertEquals(">", newBracket.getClosing());
    }

    @Test
    void testAddExistingBracket() {
        assertThrows(BracketAllreadyExistException.class, () -> bracketRepository.addBracket("(", ")"));
    }

    @Test
    void testFindBracket() {
        Bracket foundBracket = bracketRepository.findBracket("(", ")");
        assertNotNull(foundBracket);
        assertEquals("(", foundBracket.getOpening());
        assertEquals(")", foundBracket.getClosing());
    }

    @Test
    void testFindNonexistentBracket() {
        assertThrows(BracketNotFoundException.class, () -> bracketRepository.findBracket("<", ">"));
    }

    @Test
    void testRemoveBracket() {
        bracketRepository.removeBracket("[", "]");
        assertThrows(BracketNotFoundException.class, () -> bracketRepository.findBracket("[", "]"));
    }

    @Test
    void testRemoveNonexistentBracket() {
        assertThrows(BracketNotFoundException.class, () -> bracketRepository.removeBracket("<", ">"));
    }

    @Test
    void testGetBracketInfoByChar() {
        Bracket foundBracket = bracketRepository.getBracketInfoByChar('(');
        assertNotNull(foundBracket);
        assertEquals("(", foundBracket.getOpening());
        assertEquals(")", foundBracket.getClosing());
    }

    @Test
    void testGetBracketInfoByNonexistentChar() {
        assertNull(bracketRepository.getBracketInfoByChar('$'));
    }

    @Test
    void testGetAllBrackets() {
        List<Bracket> bracketList = bracketRepository.getAllBrackets();
        assertEquals(3, bracketList.size());
    }

    @Test
    void testUpdateBracket() {
        Bracket updatedBracket = bracketRepository.updateBracket("(", ")", "<", ">");
        assertNotNull(updatedBracket);
        assertEquals("<", updatedBracket.getOpening());
        assertEquals(">", updatedBracket.getClosing());
    }

    @Test
    void testUpdateNonexistentBracket() {
        assertThrows(BracketNotFoundException.class, () -> bracketRepository.updateBracket("<", ">", "[", "]"));
    }
}
