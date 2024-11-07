import java.util.Comparator;

public class CarComparatorYear implements Comparator<Car> {
    public int compare(Car a, Car b){
        return Integer.compare(a.getYear(), b.getYear());
    }
}

