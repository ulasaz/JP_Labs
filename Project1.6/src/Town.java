public class Town {
    private final Integer number_of_people;
    private final String name_of_town;

    public Town(Integer number_of_people, String name_of_town){
        this.name_of_town = name_of_town;
        this.number_of_people = number_of_people;
    }

    public Integer getNumber_of_people() {
        return number_of_people;
    }

    @Override
    public String toString() {
        return "Name of town: " + name_of_town + " Number of people: " + number_of_people;
    }
}
