import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

        Set<Product> Set_A = new HashSet<>();

        var product = new Product(100, 1);
        var product1 = new Product(101,2);
        var product2 = new Product(100, 1);

        Set_A.add(product);
        Set_A.add(product1);
        Set_A.add(product2);

        for(Product item : Set_A) {
            System.out.println(item);
        }
    }
}