package pl.edu.pwr.nr238367.bmichojnacki2;


class BmiMetricCentimeters extends AbstractBmi {

    private static double MAX_WEIGHT = 650;
    private static double MAX_HEIGHT = 300;

    BmiMetricCentimeters(double weight, double height) {
        super(weight, height);
    }

    BmiMetricCentimeters() {
        super();
    }

    @Override
    protected double calculateBmi() {
        if (!dataAreValid()) {
            throw new IllegalArgumentException();
        }
        double height = getHeight() / 100;
        return getWeight() / (height * height);
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
