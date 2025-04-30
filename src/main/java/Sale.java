import java.util.LinkedList;

public class Sale {
    private LinkedList<Product> products;
    private LinkedList<Ticket> tickets;

    public Sale(LinkedList<Product> products, LinkedList<Ticket> tickets) {
        this.products = products;
        this.tickets = tickets;
    }
}
