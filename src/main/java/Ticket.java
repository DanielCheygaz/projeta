public class Ticket {
    private int id;
    private Session session;
    private double price;
    private String ticketType;

    public Ticket(int id, Session session, double price, String ticketType) {
        this.id = id;
        this.session = session;
        this.price = price;
        this.ticketType = "Normal";
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
