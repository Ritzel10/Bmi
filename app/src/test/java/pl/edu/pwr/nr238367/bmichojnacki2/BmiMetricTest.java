package pl.edu.pwr.nr238367.bmichojnacki2;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BmiMetricTest {

    @Test
    public void forValidWeightAndHeightShouldReturnCorrectValue() {
        AbstractBmi bmi = new BmiMetric(60, 1.70);
        assertEquals(bmi.calculateBmi(), 20.76124567, 0.001);

    }

    @Test(expected = IllegalArgumentException.class)
    public void forZeroWeightAndHeightShouldThrowException() {
        AbstractBmi bmi = new BmiMetric(0, 0);
        bmi.calculateBmi();

    }

    @Test(expected = IllegalArgumentException.class)
    public void forNegativeHeightShouldThrowException() {
        AbstractBmi bmi = new BmiMetric(-60, -5);
        bmi.calculateBmi();

    }
}
