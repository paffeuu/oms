package service.parser;

import exception.IncorrectLineException;
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
            try {
                if (values.length != 5) {
                    throw new IncorrectLineException(line);
                }
            } catch (IncorrectLineException ilex) {
                System.err.println("Incorrect line was found by parser: ");
                System.err.println(ilex.getMessage());
            }
            Order newOrder = buildOrder(values);
            if (newOrder != null) {
                orders.add(newOrder);
            }
        }
        return orders;
    }
}
