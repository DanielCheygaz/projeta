import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Ticket {
    private int id;
    private Session session;
    private double price;
    private String ticketType;
    private List<Product> barProducts = new ArrayList<>();
    private LocalDateTime timestamp;

    public Ticket(int id, Session session, double price, String ticketType) {
        this.id = id;
        this.session = session;
        this.price = price;
        this.ticketType = "normal";
        this.timestamp = LocalDateTime.now();
    }



    public void setSession(Session session) {
        this.session = session;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setBarProducts(List<Product> barProducts) {
        this.barProducts = barProducts;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
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
        double total = price;
        for (Product p : barProducts) {
            total += p.getPrice();
        }
        return total;
    }


}
