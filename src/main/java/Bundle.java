import java.util.LinkedList;

public class Bundle {
    private int id;
    private String name;
    private LinkedList<Product> products;

    public Bundle(int id, String name, LinkedList<Product> products) {
        this.id = id;
        this.name = name;
        this.products = products;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LinkedList<Product> getProducts() {
        return products;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProducts(LinkedList<Product> products) {
        this.products = products;
    }
}
