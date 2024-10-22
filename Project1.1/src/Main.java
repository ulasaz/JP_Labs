import java.util.HashSet;
import java.util.Scanner;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {

        HashSet<Integer> SetA = new HashSet();
        TreeSet<Integer> SetB = new TreeSet();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Podaj 5 liczb:");

        for (int i = 0; i < 5; i++) {
            int a = scanner.nextInt();
            SetA.add(a);
            SetB.add(a);
        }

        System.out.println("Iterowanie po zbiorze A");
        for(Integer item : SetA) {
            System.out.println(item);
        }
        System.out.println("Iterowanie po zbiorze B");
        for(Integer item : SetB) {
            System.out.println(item);
        }
    }
}