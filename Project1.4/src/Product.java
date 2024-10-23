public class Product {
    private final int price;
    public int id;

    public Product(int price, int id) {
        this.id = id;
        this.price = price;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        var product = (Product) obj;
        return product.id == this.id && product.price == this.price;
    }

    @Override
    public int hashCode() {
       return 31 * id + price;
    }

    @Override
    public String toString() {
        return "Product with id: " + id + " and price: " + price;
    }
}
