import java.util.ArrayList;
import java.util.List;

public class Ticket {
    private int id;
    private Session session;
    private double price;
    private String ticketType;
    private List<Product> barProducts = new ArrayList<>();

    public Ticket(int id, Session session, double price, String ticketType) {
        this.id = id;
        this.session = session;
        this.price = price;
        this.ticketType = "Normal";
    }

    public void addBarProduct(Product product) {
        barProducts.add(product);
    }

    public List<Product> getBarProducts() {
        return barProducts;
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public int getId() {
        return id;
    }

    public Session getSession() {
        return session;
    }

    public double getPrice() {
        return price;
    }
}
