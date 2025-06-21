//João Amado - 2231032

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TicketTestCase {
    private static Ticket ticket;

    @BeforeEach
    public void setUp() {
       ticket = new Ticket(1, null, 10.0, "normal");
    }

    @Test
    public void testConstructor() {
        assertEquals(1, ticket.getId());
        assertEquals(10.0, ticket.getPrice());
        assertEquals("normal", ticket.getTicketType());
        assertEquals(null, ticket.getSession());
    }

    @Test
    public void testChangeTicketType() {
        Ticket ticket = new Ticket(2, null, 5.0, "normal");
        ticket.setTicketType("estudante");

        assertEquals("estudante", ticket.getTicketType());
    }
}
