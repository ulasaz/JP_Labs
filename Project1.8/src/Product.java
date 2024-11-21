public class Product implements Comparable<Product>{
        private final int price;
        private final String name;

        Product(int price, String name){
            this.price = price;
            this.name = name;
        }
        public Integer getPrice(){
            return price;
        }
        public String getName(){
        return name;
    }
        public String toString(){
            return price + " " + name;
        }

    public int compareTo(Product otherProduct) {
            return Integer.compare(getPrice(), otherProduct.getPrice());
    }
}

