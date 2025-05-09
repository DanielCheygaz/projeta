import java.util.LinkedList;

public class Bundle {
    private int id;
    private String name;
    private int numberTickets;
    private LinkedList<Product> products;
    private double price;

    public Bundle(int id, String name, int numberTickets, LinkedList<Product> products, double price) {
        this.id = id;
        this.name = name;
        this.numberTickets = numberTickets;
        this.products = products;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberTickets() {
        return numberTickets;
    }

    public void setNumberTickets(int numberTickets) {
        this.numberTickets = numberTickets;
    }

    public LinkedList<Product> getProducts() {
        return products;
    }

    public void setProducts(LinkedList<Product> products) {
        this.products = products;
    }
}
