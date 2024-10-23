import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Product> products = new ArrayList<>();

        var product1 = new Product(20, "Alex");
        var product2 = new Product(25, "Martha");
        var product3 = new Product(19, "Bob");

        products.add(product1);
        products.add(product2);
        products.add(product3);

        System.out.println("Before Sorting : " + products);
        Collections.sort(products);
        System.out.println("After Sorting : " + products);

    }
}