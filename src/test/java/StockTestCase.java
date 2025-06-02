import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StockTestCase {
    private Product product;
    private int units;

    @BeforeEach
    public void setUp(){
        units = (int)(Math.random()*101);
        product = new Product("Coca Cola", 1.2, units);
    }

    @Test
    public void testGetProductName(){
        assertEquals("Coca Cola", product.getName());
    }

    @Test
    public void testGetProductPrice(){
        assertEquals(1.2,product.getPrice());
    }

    @Test
    public void testGetUnits(){
        assertEquals(units, product.getUnits());
    }

    @Test
    public void testAddUnits(){
        product.addUnits(10);
        assertEquals(units+10, product.getUnits());
    }

    @Test
    public void testEditStock(){
        Product newProduct = new Product("Pipocas", 1.5, 10);
        product.editProduct("Pipocas",1.5, 10);
        assertEquals(newProduct.toString(),product.toString());
    }

}
