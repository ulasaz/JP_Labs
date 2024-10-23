public class Product  implements Comparable<Product>{
        private final int price;
        private final String name;

        Product(int price, String name){
            this.price = price;
            this.name = name;
        }
        public Integer getPrice(){
            return price;
        }
        public String toString(){
            return this.price + " " + this.name;
        }


    @Override
    public int compareTo(Product otherProduct) {
        return Integer.compare(getPrice(), otherProduct.getPrice());
    }
}

