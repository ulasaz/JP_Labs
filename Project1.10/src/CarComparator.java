import java.util.Comparator;

public class CarComparator implements Comparator<Car> {
    public int compare(Car a, Car b){
        var labelCompare = a.getLabel().compareTo(b.getLabel());
        if (labelCompare != 0) {
            return labelCompare;
        }
        return a.getModel().compareTo(b.getModel());
    }
}
