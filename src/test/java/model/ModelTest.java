package model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ModelTest {
    private Model m;


    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        m = new Model();
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        m = null;
    }

    @org.junit.jupiter.api.Test
    void generateRandomNumbers() {
        List<Integer> num = new ArrayList<>();
        num.add(1);
        num.add(2);
        num.add(3);
        num.add(4);
        List<Integer> result = new ArrayList<>();
        result = m.generateRandomNumbers(1,4,4);
        assertTrue(num.equals(result));
    }

    @org.junit.jupiter.api.Test
    void generateCorrentAnswerPlace() {
        assertEquals(1, m.generateCorrentAnswerPlace(1, 1));
    }
}