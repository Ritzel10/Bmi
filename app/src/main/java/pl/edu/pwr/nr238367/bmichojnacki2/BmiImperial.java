package pl.edu.pwr.nr238367.bmichojnacki2;

public class BmiImperial extends AbstractBmi {

    private static int CONVERSION_CONST = 703;
    private static double MAX_WEIGHT = 1500;
    private static double MAX_HEIGHT = 300;

    BmiImperial(double weight, double height) {
        super(weight, height);
    }

    BmiImperial() {
        super();
    }

    @Override
    protected double calculateBmi() {
        if (!dataAreValid()) {
            throw new IllegalArgumentException();
        }
        return (getWeight() / (getHeight() * getHeight())) * CONVERSION_CONST;
    }

    @Override
    protected double getMaxHeight() {
        return MAX_HEIGHT;
    }

    @Override
    protected double getMaxWeight() {
        return MAX_WEIGHT;
    }


}
