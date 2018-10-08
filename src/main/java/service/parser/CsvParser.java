package service.parser;

import model.Order;

import java.util.ArrayList;

public class CsvParser extends Parser{
    public CsvParser(ArrayList<String> content) {
        super(content);
    }

    @Override
    public ArrayList<Order> parse() {
        ArrayList<Order> orders = new ArrayList<>();
        for (String line: content) {
            String[] values = line.split(",");
            Order newOrder = buildOrder(values);
            if (newOrder != null) {
                orders.add(newOrder);
            }
        }
        return orders;
    }
}
