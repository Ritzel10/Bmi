package pl.edu.pwr.nr238367.bmichojnacki2;


abstract class AbstractBmi {

    private double weight;
    private double height;

    AbstractBmi(double weight, double height) {
        this.weight = weight;
        this.height = height;
    }

    AbstractBmi() {
        weight = 0;
        height = 0;
    }


    protected abstract double calculateBmi();

    protected boolean dataAreValid() {
        return getHeight() > 0 && getWeight() > 0 && getHeight() < getMaxHeight() && getWeight() < getMaxWeight();
    }

    protected abstract double getMaxHeight();

    protected abstract double getMaxWeight();

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }
}
