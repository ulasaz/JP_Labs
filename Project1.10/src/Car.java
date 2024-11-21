public class Car {
    final private String label;
    final private String model;
    final private int year;

    public Car(String label, String model, int year){
        this.label = label;
        this.model = model;
        this.year = year;
    }

    public String getLabel() {
        return label;
    }

    public String getModel() {
        return model;
    }
    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return label + " " + model + " " + year;
    }
}
