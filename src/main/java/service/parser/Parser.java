package service.parser;

import model.Order;
import service.OrderValidator;

import java.util.ArrayList;

public abstract class Parser {
    public abstract ArrayList<Order> parse();
    private OrderValidator orderValidator;
    ArrayList<String> content;

    Parser(ArrayList<String> content) {
        orderValidator = new OrderValidator();
        this.content = content;
    }

    Order buildOrder(String[] values) {
        Order newOrder = new Order();
        String clientId = orderValidator.validateClientId(values[0]);
        if (clientId != null) {
            newOrder.setClientId(clientId);
        } else {
            return null;
        }
        Long requestId = orderValidator.validateRequestId(values[1]);
        if (requestId != null) {
            newOrder.setRequestId(requestId);
        } else {
            return null;
        }
        String name = orderValidator.validateName(values[2]);
        if (name != null) {
            newOrder.setName(name);
        } else {
            return null;
        }
        Integer quantity = orderValidator.validateQuantity(values[3]);
        if (quantity != null) {
            newOrder.setQuantity(quantity);
        } else {
            return null;
        }
        Double price = orderValidator.validatePrice(values[4]);
        if (price != null) {
            newOrder.setPrice(price);
        } else {
            return null;
        }
        return newOrder;
    }
}
