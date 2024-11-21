import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Car> cars = new ArrayList<>();


        cars.add(new Car("Chevrolet", "Malibu", 2024));
        cars.add(new Car("Mitsubishi", "ASX", 2021));
        cars.add(new Car("Mazda", "CX-80", 2020));
        cars.add(new Car("Audi", "R8", 2019));
        cars.add(new Car("Chevrolet", "Volt", 2024));
        cars.add(new Car("Mitsubishi", "C7", 2021));
        cars.add(new Car("Mazda", "CX-5", 2020));
        cars.add(new Car("Audi", "Q5", 2019));

        cars.sort(new CarComparator());

        System.out.println("Sorting by label and model:");
        for (Car vehicle : cars) {
            System.out.println(vehicle);
        }

        cars.sort(new CarComparatorYear());

        System.out.println("Sorting by year:");
        for (Car vehicle : cars) {
            System.out.println(vehicle);
        }
    }
}