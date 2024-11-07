import java.util.Objects;

public class Address {
    final private int houseNumber;
    final private int postalCode;
    final private String street;
    final private String country;
    final private String city;
    public Address( String city,
                    String country,
                    String street,
                    int postalCode,
                    int houseNumber) {

        this.city = city;
        this.country = country;
        this.street = street;
        this.postalCode = postalCode;
        this.houseNumber = houseNumber;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address that = (Address) o;

        return city.equals(that.city) && street.equals(that.street) && postalCode == that.postalCode;
    }
    @Override
    public int hashCode() {
        return Objects.hash(country, city, street, postalCode, houseNumber);
    }
}
