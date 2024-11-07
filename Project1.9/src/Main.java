import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<Address, String> inhabitants = new HashMap<>();

        var stanAddress = new Address("Wroclaw", "Poland", "Dluga", 55053,51);
        var stanAddress2 = new Address("Wroclaw", "Poland", "Dluga", 55053,51);

        inhabitants.put(stanAddress, "Stan");
        inhabitants.put(new Address("Krakow", "Poland", "Wroblewskiego", 52023,22), "Vlad");
        inhabitants.put(new Address("Warszawa", "Poland", "Druga", 54553,43), "Ivan");
        inhabitants.put(new Address("Poznan", "Poland", "Piekna", 554423,12), "Katia");
        inhabitants.put(new Address("Wroclaw", "Poland", "Im.PWR", 51253,32), "Maria");

        var foundInhabitant = inhabitants.get(stanAddress2);

        System.out.println(foundInhabitant);

    }
}