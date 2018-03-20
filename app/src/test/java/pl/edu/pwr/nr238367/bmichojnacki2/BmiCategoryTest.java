package pl.edu.pwr.nr238367.bmichojnacki2;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class BmiCategoryTest {
    @Test
    public void bmiValidUnderweightValue() throws Exception {
        assertEquals(BmiCategory.classifyBmiCategory(16.49), BmiCategory.UNDERWEIGHT);
    }
    @Test
    public void bmiValidCornerUnderweightValue() throws Exception{
        assertEquals(BmiCategory.classifyBmiCategory(18.49), BmiCategory.UNDERWEIGHT);
    }
    @Test
    public void bmiValidNormalValue() throws Exception {
        assertEquals(BmiCategory.classifyBmiCategory(18.50), BmiCategory.NORMAL);
    }
    @Test
    public void bmiValidCornerNormalValue() throws Exception{
        assertEquals(BmiCategory.classifyBmiCategory(24.99), BmiCategory.NORMAL);
    }
    @Test
    public void bmiValidOverweightValue() throws Exception {
        assertEquals(BmiCategory.classifyBmiCategory(25.00), BmiCategory.OVERWEIGHT);

    }
    @Test
    public void bmiValidCornerOverweightValue() throws Exception{
        assertEquals(BmiCategory.classifyBmiCategory(29.99), BmiCategory.OVERWEIGHT);
    }
    @Test
    public void bmiValidObeseValue() throws Exception {
        assertEquals(BmiCategory.classifyBmiCategory(30.00), BmiCategory.OBESE);
    }
    @Test
    public void bmiValidCornerObeseValue() throws Exception{
        assertEquals(BmiCategory.classifyBmiCategory(42.39), BmiCategory.OBESE);
    }

}
