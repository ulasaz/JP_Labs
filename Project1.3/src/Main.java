import java.util.HashMap;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        // Inicjalizacja map: TreeMap i HashMap
        TreeMap<Integer, String> mapA = new TreeMap<>();
        HashMap<Integer, String> mapB = new HashMap<>();

        // Dodanie kilku elementów do obu map
        mapA.put(3, "Value 3");
        mapA.put(1, "Value 1");
        mapA.put(4, "Value 4");
        mapA.put(5, "Value 5");
        mapA.put(2, "Value 2");

        mapB.put(3, "Value 3");
        mapB.put(1, "Value 1");
        mapB.put(4, "Value 4");
        mapB.put(5, "Value 5");
        mapB.put(2, "Value 2");

        // Iterowanie i wyświetlanie wartości z TreeMap
        System.out.println("Iterowanie po wartościach TreeMap (klucze posortowane):");
        for (Integer key : mapA.keySet()) {
            System.out.println("Key: " + key + ", Value: " + mapA.get(key));
        }

        // Iterowanie i wyświetlanie wartości z HashMap
        System.out.println("\nIterowanie po wartościach HashMap (kolejność losowa):");
        for (Integer key : mapB.keySet()) {
            System.out.println("Key: " + key + ", Value: " + mapB.get(key));
        }
    }
}
