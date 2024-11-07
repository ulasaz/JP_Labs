import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        TreeMap<Town, String> towns = new TreeMap<>(new TownComparator());

        towns.put(new Town(200000, "Wroclaw"), "Poland");
        towns.put(new Town(5300000, "Warsaw"), "Poland");
        towns.put(new Town(833000, "New York"), "USA");

        for (Town town : towns.keySet()) {
            System.out.println(town);
        }
    }
}
