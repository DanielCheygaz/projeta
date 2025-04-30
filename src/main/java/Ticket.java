public class Ticket {
    private int id;
    private Session session;
    private double price;

    public Ticket(int id, Session session, double price) {
        this.id = id;
        this.session = session;
        this.price = price;
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
