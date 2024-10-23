import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<People> people = new ArrayList<>();

        var people1 = new People(20, "Alex");
        var people2 = new People(25, "Martha");
        var people3 = new People(19, "Bob");

        people.add(people1);
        people.add(people2);
        people.add(people3);

        PeopleAgeComparator peopleComparator = new PeopleAgeComparator();
        people.sort(peopleComparator);

        for (People person : people) {
            System.out.println(person);
        }

    }
}