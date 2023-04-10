package org.example;


import org.junit.Test;
import static org.junit.Assert.*;



    public class OrderTest {

        @Test
        public void testAdditionalClientDiscount() {
            assertEquals(0.0, Order.additionalClientDiscount(1, 5000.0),0.0);
            assertEquals(0.0, Order.additionalClientDiscount(1, 8000.0),0.0);
            assertEquals(0.01 * 15000.0, Order.additionalClientDiscount(2, 15000.0),0.0);
            assertEquals(0.0, Order.additionalClientDiscount(4, 5000.0),0.0);
            assertEquals(0.07 * 35000.0, Order.additionalClientDiscount(5, 35000.0),0.0);
        }

        @Test
        public void testBasicClientDiscount() {
            assertEquals(0.05 * 0.64, Order.basicClientDiscount(1, 0.64),0.0);
            assertEquals(0.04 * 0.48, Order.basicClientDiscount(2, 0.48),0.0);
            assertEquals(0.03 * 0.22, Order.basicClientDiscount(3, 0.22),0.0);
            assertEquals(0.02 * 0.79, Order.basicClientDiscount(4, 0.79),0.0);
            assertEquals(0.0, Order.basicClientDiscount(5, 1.25),0.0);
        }



}
