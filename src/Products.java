import java.util.ArrayList;

public class Products {
    static ArrayList<Product> products = new ArrayList<>();

    public static ArrayList<Product> get() {
        return products;
    }

    public static void add(Product product) {
        products.add(product);
    }
    public static void remove(Product product) {
        products.remove(products.indexOf(product));
    }
    public static void set(ArrayList<Product> product) {
        products = product;
    }
}
