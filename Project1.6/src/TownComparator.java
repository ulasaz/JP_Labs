import java.util.Comparator;

public class TownComparator implements Comparator<Town> {

    @Override
    public int compare(Town firstTown, Town secondTown) {
        return Integer.compare(firstTown.getNumber_of_people(), secondTown.getNumber_of_people());
    }

}