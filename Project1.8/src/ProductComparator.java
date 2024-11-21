import java.util.Comparator;

public class ProductComparator implements Comparator<Product> {
    public int compare(Product a, Product b){
        return a.getName().compareTo(b.getName());
    }
}
