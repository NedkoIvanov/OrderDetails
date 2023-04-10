package org.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class ProductTest {

    @Test
    public void testGetProductPrice() {
        Product product = new Product("Test Product", 10.0, "5%", "none");


        assertEquals(52.5, product.getProductPrice(5), 0.0);


        product.setMarkup("10%");
        assertEquals(55.0, product.getProductPrice(5), 0.0);


        product.setMarkup("1.5 EUR/unit");
        assertEquals(57.5, product.getProductPrice(5), 0.0);


        //I set markup to 1.5 EUR/unit so unit cost == 11.5 , after 20% off unit cost == 9.2.Expected 46.0
        product.setPromotion("20% off");
        assertEquals(46.0, product.getProductPrice(5), 0.0);


        product.setPromotion("Buy 2, get 3rd free");
        assertEquals(23.0, product.getProductPrice(3), 0.0);
        assertEquals(34.5, product.getProductPrice(4), 0.0);
        assertEquals(46.0, product.getProductPrice(5), 0.0);
    }
}