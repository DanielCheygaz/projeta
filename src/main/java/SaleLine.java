public class SaleLine {
    private Ticket ticket;
    private Product product;
    private int quantity;

    // Para bilhetes
    public SaleLine(Ticket ticket) {
        this.ticket = ticket;
    }

    // Para produtos avulso
    public SaleLine(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotal() {
        if (ticket != null) return ticket.getPrice();
        if (product != null) return product.getPrice() * quantity;
        return 0;
    }

    // Método auxiliar para identificar o tipo de item
    public Object getItem() {
        if (ticket != null) return ticket;
        if (product != null) return product;
        return null;
    }
}
