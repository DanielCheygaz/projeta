// Feito por Daniel Chagas
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StockTestCase {
    private Product product;
    private int units;

    @BeforeEach
    public void setUp(){
        product = new Product("Coca Cola", 1.2, 15);
    }

    @Test
    public void testConstructor(){
        assertEquals("Coca Cola", product.getName());
        assertEquals(1.2,product.getPrice());
        assertEquals(units, product.getUnits());
    }

    @Test
    public void testAddUnits(){
        product.addUnits(10);
        assertEquals(units+10, product.getUnits());
    }

    @Test
    public void testEditStock(){
        String name = "Pipocas";
        double price = 1.5;
        int units = 12;
        product.editProduct(name, price, units);
        assertEquals(name,product.getName());
        assertEquals(price,product.getPrice());
        assertEquals(units,product.getUnits());
    }

}
