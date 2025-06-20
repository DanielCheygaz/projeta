import java.util.ArrayList;
import java.util.List;

public class Sale {
    private static int nextId = 1;
    private int id;
    private List<SaleLine> saleLines;

    public Sale() {
        this.id = nextId++;
        this.saleLines = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void addLine(SaleLine line) {
        saleLines.add(line);
    }

    public void removeLine(SaleLine line) {
        saleLines.remove(line);
    }

    public List<SaleLine> getSaleLines() {
        return saleLines;
    }

    public double getTotalPrice() {
        return saleLines.stream().mapToDouble(SaleLine::getTotal).sum();
    }

    public boolean hasLines() {
        return !saleLines.isEmpty();
    }

    public int getTotalTickets() {
        return (int) saleLines.stream()
                .filter(line -> line.getTicket() != null)
                .count();
    }

    public List<Ticket> getTickets() {
        List<Ticket> tickets = new ArrayList<>();
        for (SaleLine line : saleLines) {
            if (line.getTicket() != null) {
                tickets.add(line.getTicket());
            }
        }
        return tickets;
    }

    public boolean containsProduct(Product product) {
        return saleLines.stream()
                .anyMatch(line -> {
                    Product p = line.getProduct();
                    return p != null && p.getName().equals(product.getName());
                });
    }

    public int getQuantityOfProduct(String productName) {
        return saleLines.stream()
                .filter(line -> line.getProduct() != null && line.getProduct().getName().equals(productName))
                .mapToInt(SaleLine::getQuantity)
                .sum();
    }
}
