package pl.edu.pwr.nr238367.bmichojnacki2;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class BmiCategoryTest {
    @Test
    public void bmiCategory_isCorrect() throws Exception {
        assertEquals(BmiCategory.classifyBmiCategory(16.49), BmiCategory.UNDERWEIGHT);
        assertEquals(BmiCategory.classifyBmiCategory(18.49), BmiCategory.UNDERWEIGHT);
        assertEquals(BmiCategory.classifyBmiCategory(18.50), BmiCategory.NORMAL);
        assertEquals(BmiCategory.classifyBmiCategory(24.99), BmiCategory.NORMAL);
        assertEquals(BmiCategory.classifyBmiCategory(25.00), BmiCategory.OVERWEIGHT);
        assertEquals(BmiCategory.classifyBmiCategory(29.99), BmiCategory.OVERWEIGHT);
        assertEquals(BmiCategory.classifyBmiCategory(30.00), BmiCategory.OBESE);
        assertEquals(BmiCategory.classifyBmiCategory(42.39), BmiCategory.OBESE);
    }
}
