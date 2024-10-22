public class Product {
    private int price;
    public int id;

    public Product(int price, int id) {
        this.id = id;
        this.price = price;
    }

    @Override
    public boolean equals(Object obj) {
        var product = (Product) obj;
        return product.id == this.id && product.price == this.price;
    }

    @Override
    public int hashCode() {
       return this.price *37;
    }

    @Override
    public String toString() {
        return "Product with id: " + this.id;
    }
}
