import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SaleTestCase {
    private Sale sale;

    @BeforeEach
    public void setUp() {
        sale = new Sale();
    }

    @Test
    public void testAddTicket() {
        Ticket ticket = new Ticket(1, null, 10.0, "normal");
        SaleLine saleLine = new SaleLine(ticket);
        sale.addLine(saleLine);
        assertEquals(1, sale.getTickets().size());
        assertEquals(10.0, sale.getTotalPrice(), 0.01);
    }

    @Test
    public void testGetTotalPrice() {
        Product product1 = new Product("Coca Cola", 1.2, 15);
        SaleLine saleLine1 = new SaleLine(product1, 2);
        sale.addLine(saleLine1);

        Ticket ticket = new Ticket(1, null, 10.0, "normal");
        SaleLine saleLine2 = new SaleLine(ticket);
        sale.addLine(saleLine2);

        assertEquals(12.4, sale.getTotalPrice(), 0.01);
    }
}
