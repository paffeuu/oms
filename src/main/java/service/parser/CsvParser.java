package service.parser;

import model.Order;
import service.OrderValidator;

import java.util.ArrayList;

public class CsvParser implements Parser{
    private OrderValidator orderValidator;
    private ArrayList<String> content;

    public CsvParser(ArrayList<String> content) {
        orderValidator = new OrderValidator();
        this.content = content;
    }

    @Override
    public ArrayList<Order> parse() {
        ArrayList<Order> orders = new ArrayList<>();
        for (String line: content) {
            String[] values = line.split(",");
            Order newOrder = new Order();
            String clientId = orderValidator.validateClientId(values[0]);
            if (clientId != null) {
                newOrder.setClientId(clientId);
            } else {
                continue;
            }
            Long requestId = orderValidator.validateRequestId(values[1]);
            if (requestId != null) {
                newOrder.setRequestId(requestId);
            } else {
                continue;
            }
            String name = orderValidator.validateName(values[2]);
            if (name != null) {
                newOrder.setName(name);
            } else {
                continue;
            }
            Integer quantity = orderValidator.validateQuantity(values[3]);
            if (quantity != null) {
                newOrder.setQuantity(quantity);
            } else {
                continue;
            }
            Double price = orderValidator.validatePrice(values[4]);
            if (price != null) {
                newOrder.setPrice(price);
            } else {
                continue;
            }
            orders.add(newOrder);
        }
        return orders;
    }
}
