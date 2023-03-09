package main.java.com.healthycoderapp;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BMICalculatorTest {

    @BeforeAll
    static void beforeAll() {
        System.out.println("Before all unit test.");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("After all unit test.");
    }

    @ParameterizedTest(name = "weight={0}, height={1}")
    @CsvFileSource(resources = "/diet-recommended-input-data.csv", numLinesToSkip = 1)
    void should_ReturnTrue_When_DietRecommended(Double coderWeight, Double coderHeight) {
        //arrange
        double weight = coderWeight;
        double height = coderHeight;
        //act
        boolean recommended = BMICalculator.isDietRecommended(weight, height);
        //assert
        assertTrue(recommended);
    }

    @Test
    void should_ReturnFalse_When_DietRecommended() {
        //given
        double weight = 50.0;
        double height = 1.92;
        //when
        boolean recommended = BMICalculator.isDietRecommended(weight, height);
        //then
        assertFalse(recommended);
    }

    @Test
    void should_ThrowArithmeticException_When_HeightZero() {
        double weight = 50.0;
        double height = 0;
        Executable executable = () -> BMICalculator.isDietRecommended(weight, height);
        assertThrows(ArithmeticException.class, executable);
    }

    @Test
    void should_ReturnCoderWithWorstBMI_When_CoderListNotEmpty() {
        List<Coder> coders = new ArrayList<>();
        coders.add(new Coder(1.80, 60.0));
        coders.add(new Coder(1.82, 98.0));
        coders.add(new Coder(1.82, 64.7));
        Coder coderWorstBMI = BMICalculator.findCoderWithWorstBMI(coders);
        assertAll(
                () -> assertEquals(1.82, coderWorstBMI.getHeight()),
                () -> assertEquals(98, coderWorstBMI.getWeight())
        );
    }

    @Test
    void should_ReturnNullWorstBMI_When_CoderListEmpty() {
        List<Coder> coders = new ArrayList<>();
        Coder coderWorstBMI = BMICalculator.findCoderWithWorstBMI(coders);
        assertNull(coderWorstBMI);
    }

    @Test
    void should_ReturnCorrectBMIScoreArray_When_CoderListEmpty() {
        List<Coder> coders = new ArrayList<>();
        coders.add(new Coder(1.80, 60.0));
        coders.add(new Coder(1.82, 98.0));
        coders.add(new Coder(1.82, 64.7));
        double[] expected = {18.52, 29.59, 19.53};
        double[] bmiScores = BMICalculator.getBMIScores(coders);
        assertArrayEquals(expected, bmiScores);
    }
}