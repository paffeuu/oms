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
    }

    @Test
    void validateName() {
    }

    @Test
    void validateQuantity() {
    }

    @Test
    void validatePrice() {
    }
}