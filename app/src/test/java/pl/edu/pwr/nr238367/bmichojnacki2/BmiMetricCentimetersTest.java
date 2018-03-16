package pl.edu.pwr.nr238367.bmichojnacki2;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BmiMetricCentimetersTest {

    @Test
    public void forValidWeightAndHeightShouldReturnCorrectValue() {
        AbstractBmi bmi = new BmiMetricCentimeters(60, 170);
        assertEquals(bmi.calculateBmi(), 20.76124567, 0.001);

    }

    @Test(expected = IllegalArgumentException.class)
    public void forZeroWeightAndHeightShouldThrowException() {
        AbstractBmi bmi = new BmiMetricCentimeters(0, 0);
        bmi.calculateBmi();

    }

    @Test(expected = IllegalArgumentException.class)
    public void forNegativeHeightShouldThrowException() {
        AbstractBmi bmi = new BmiMetricCentimeters(-60, -5);
        bmi.calculateBmi();

    }
}
