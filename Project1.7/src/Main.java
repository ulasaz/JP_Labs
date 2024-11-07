import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

        Set<Book> Books_Shop = new HashSet<>();

        var book1 = new Book(123456, "Do Androids Dream of Electric Sheep?", "Philip K. Dick");
        var book2 = new Book(125426, "Something Wicked This Way Comes", "Ray Bradbury");
        var book3 = new Book(142345, "A Story of Yesterday", "Sergio Cobo");
        var book4 = new Book(142345, "To Kill a Mockingbird", "by Harper Lee");

        Books_Shop.add(book1);
        Books_Shop.add(book2);
        Books_Shop.add(book3);
        Books_Shop.add(book4);


        for(Book item : Books_Shop) {
            System.out.println(item);
        }

    }
}