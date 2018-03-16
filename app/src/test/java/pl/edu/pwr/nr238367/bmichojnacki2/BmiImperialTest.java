package pl.edu.pwr.nr238367.bmichojnacki2;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BmiImperialTest {
    @Test
    public void forValidWeightAndHeightShouldReturnCorrectValue() {
        AbstractBmi bmi = new BmiImperial(110, 67);
        assertEquals(bmi.calculateBmi(), 17.2265537982, 0.001);

    }

    @Test(expected = IllegalArgumentException.class)
    public void forZeroWeightAndHeightShouldThrowException() {
        AbstractBmi bmi = new BmiImperial(0, 0);
        bmi.calculateBmi();

    }

    @Test(expected = IllegalArgumentException.class)
    public void forNegativeHeightShouldThrowException() {
        AbstractBmi bmi = new BmiImperial(-60, -5);
        bmi.calculateBmi();

    }
}
