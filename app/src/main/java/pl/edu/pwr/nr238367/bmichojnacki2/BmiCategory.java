package pl.edu.pwr.nr238367.bmichojnacki2;


public enum BmiCategory {
    UNDERWEIGHT(18.50), NORMAL(25), OVERWEIGHT(30), OBESE;

    private double cutoffPoint;

    BmiCategory() {

    }

    BmiCategory(double cutoffPoint) {
        this.cutoffPoint = cutoffPoint;
    }

    public double getCutoffPoint() {
        return cutoffPoint;
    }

    public static BmiCategory classifyBmiCategory(double bmi) {
        //bmi values less than cut-off point are a member of that category
        if (bmi < BmiCategory.UNDERWEIGHT.getCutoffPoint()) {
            return BmiCategory.UNDERWEIGHT;
        } else if (bmi < BmiCategory.NORMAL.getCutoffPoint()) {
            return BmiCategory.NORMAL;
        } else if (bmi < BmiCategory.OVERWEIGHT.getCutoffPoint()) {
            return BmiCategory.OVERWEIGHT;
        } else {
            return BmiCategory.OBESE;
        }
    }
}