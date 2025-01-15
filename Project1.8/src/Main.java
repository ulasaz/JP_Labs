import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Product> products = new ArrayList<>();

        var product1 = new Product(20, "Airpods Max");
        var product2 = new Product(25, "IPhone");
        var product3 = new Product(19, "Ipad");
        var product4 = new Product(11, "Macbook");
        var product5 = new Product(56, "Apple watch");
        var product6 = new Product(24, "Airpods");

        products.add(product1);
        products.add(product2);
        products.add(product3);
        products.add(product4);
        products.add(product5);
        products.add(product6);

        sortAndPrintByPrice(products);
        sortAndPrintByName(products);


    }
    private static void sortAndPrintByPrice(List<Product> products) {
        Collections.sort(products); // Использует compareTo из Product
        System.out.println("After Sorting by price: " + products);
    }

    private static void sortAndPrintByName(List<Product> products) {
        products.sort(new ProductComparator()); // Использует компаратор
        System.out.println("After Sorting by name: " + products);
    }
}