package service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderValidatorTest {
    private OrderValidator orderValidator = new OrderValidator();

    @Test
    void validateClientId() {
        assertNotNull(orderValidator.validateClientId("Źąć89X"));
        assertNotNull(orderValidator.validateClientId("234903"));
        assertNotNull(orderValidator.validateClientId("aaaaaa"));
        assertNotNull(orderValidator.validateClientId("ąś1"));
        assertNotNull(orderValidator.validateClientId("ą"));
        assertNotNull(orderValidator.validateClientId("5"));

        assertNull(orderValidator.validateClientId(null));
        assertNull(orderValidator.validateClientId(""));
        assertNull(orderValidator.validateClientId("$"));
        assertNull(orderValidator.validateClientId(" "));
        assertNull(orderValidator.validateClientId("34 sd2"));
        assertNull(orderValidator.validateClientId("1234567"));
        assertNull(orderValidator.validateClientId("ąś4242s"));
    }

    @Test
    void validateRequestId() {
        assertNotNull(orderValidator.validateRequestId("0"));
        assertNotNull(orderValidator.validateRequestId("1829381038"));
        assertNotNull(orderValidator.validateRequestId(String.valueOf(Long.MAX_VALUE)));
        assertNotNull(orderValidator.validateRequestId(String.valueOf(Long.MIN_VALUE)));

        assertNull(orderValidator.validateRequestId(null));
        assertNull(orderValidator.validateRequestId(String.valueOf(Long.MAX_VALUE) + "0"));
        assertNull(orderValidator.validateRequestId(String.valueOf(Long.MIN_VALUE) + "0"));
        assertNull(orderValidator.validateRequestId("aaaaaaaa"));
        assertNull(orderValidator.validateRequestId("123123a"));
        assertNull(orderValidator.validateRequestId("a324223"));
    }

    @Test
    void validateName() {
        String w255 = "12345678901235612345678trfd3456123456x7890 23456123456789012345lksjd5678901234561 345678901234561234567890123ccc1234567890123456123456     3456123456789012345   3456789012345612345678901234561234567890123456123456789012345612345678901234561234567890123xxx";
        assertEquals(255, w255.length());
        assertNotNull(orderValidator.validateName(w255));

        assertNull(orderValidator.validateName(null));
        String w256 = w255 + "c";
        assertEquals(256, w256.length());
        assertNull(orderValidator.validateName(w256));
        String w255dot = w255.replace('2', '.');
        assertNull(orderValidator.validateName(w255dot));
    }

    @Test
    void validateQuantity() {
        assertNotNull(orderValidator.validateQuantity("0"));
        assertNotNull(orderValidator.validateQuantity("1829381038"));
        assertNotNull(orderValidator.validateQuantity(String.valueOf(Integer.MAX_VALUE)));
        assertNotNull(orderValidator.validateQuantity(String.valueOf(Integer.MIN_VALUE)));

        assertNull(orderValidator.validateQuantity(null));
        assertNull(orderValidator.validateQuantity(String.valueOf(Integer.MAX_VALUE) + "0"));
        assertNull(orderValidator.validateQuantity(String.valueOf(Integer.MIN_VALUE) + "0"));
        assertNull(orderValidator.validateQuantity("aaaaaaaa"));
        assertNull(orderValidator.validateQuantity("123123a"));
        assertNull(orderValidator.validateQuantity("a324223"));
    }

    @Test
    void validatePrice() {
        assertNotNull(orderValidator.validatePrice("0.0"));
        assertNotNull(orderValidator.validatePrice("182938.23423"));
        assertNotNull(orderValidator.validatePrice(String.valueOf(Double.MAX_VALUE)));
        assertNotNull(orderValidator.validatePrice(String.valueOf(Double.MIN_VALUE)));

        assertNull(orderValidator.validatePrice(null));
        assertNull(orderValidator.validatePrice(String.valueOf(Double.MAX_VALUE).replaceFirst(".",".3")));
        assertNull(orderValidator.validatePrice(String.valueOf(Double.MIN_VALUE).replaceFirst(".",".3")));
        assertNull(orderValidator.validatePrice("aaaaaaaa"));
        assertNull(orderValidator.validatePrice("123123a"));
        assertNull(orderValidator.validatePrice("a324223"));
    }
}