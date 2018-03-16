package pl.edu.pwr.nr238367.bmichojnacki2;


class BmiMetric extends AbstractBmi {

    private static double MAX_WEIGHT = 650;
    private static double MAX_HEIGHT = 3.00;

    BmiMetric(double weight, double height) {
        super(weight, height);
    }

    BmiMetric() {
        super();
    }

    @Override
    protected double calculateBmi() {
        if (!dataAreValid()) {
            throw new IllegalArgumentException();
        }
        return getWeight() / (getHeight() * getHeight());
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
