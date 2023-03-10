package main.java.com.healthycoderapp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ActivityCalculatorTest {
    @Test
    void should_ReturnBad_When_AvgBelow20() {
        int weeklyCardioMin = 40;
        int weeklyCardioSessions = 1;
        String actual = ActivityCalculator.rateActivityLevel(weeklyCardioMin, weeklyCardioSessions);
        assertEquals("bad", actual);
    }
    @Test
    void should_ReturnAverage_When_AvgBetween20And40() {
        int weeklyCardioMin = 40;
        int weeklyCardioSessions = 3;
        String actual = ActivityCalculator.rateActivityLevel(weeklyCardioMin, weeklyCardioSessions);
        assertEquals("average", actual);
    }
    @Test
    void should_ReturnGood_When_AvgAbove40() {
        int weeklyCardioMin = 40;
        int weeklyCardioSessions = 7;
        String actual = ActivityCalculator.rateActivityLevel(weeklyCardioMin, weeklyCardioSessions);
        assertEquals("good", actual);
    }
    @Test
    void should_ThrowException_When_InputBelowZero() {
        int weeklyCardioMin = -40;
        int weeklyCardioSessions = 7;
        Executable executable = () -> ActivityCalculator.rateActivityLevel(weeklyCardioMin, weeklyCardioSessions);
        assertThrows(RuntimeException.class, executable);
    }
}