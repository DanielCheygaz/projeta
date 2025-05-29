import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StockTestCase {
    private Product product;
    private Stock stock;
    private int units;

    @BeforeEach
    public void setUp(){
        product = new Product("Coca Cola", 1.2);
        units = (int)(Math.random()*101);
        stock = new Stock(product, units);
    }

    @Test
    public void testGetProductName(){
        assertEquals("Coca Cola", stock.getProduct().getName());
    }

    @Test
    public void testGetProductPrice(){
        assertEquals(1.2,stock.getProduct().getPrice());
    }

    @Test
    public void testGetUnits(){
        assertEquals(units, stock.getUnits());
    }

    @Test
    public void testAddUnits(){
        stock.addUnits(10);
        assertEquals(units+10, stock.getUnits());
    }

    @Test
    public void testEditStock(){
        Stock newStock = new Stock(new Product("Pipocas", 1.5), 10);
        stock.editStock("Pipocas",10,1.5);
        assertEquals(newStock.toString(),stock.toString());
    }

}
