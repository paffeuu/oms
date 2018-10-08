package service.parser;

import model.Order;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XmlParser extends Parser {
    private final static String[] TAGS = {"clientId", "requestId", "name", "quantity", "price"};

    public XmlParser(ArrayList<String> content) {
        super(content);
    }

    @Override
    public ArrayList<Order> parse() {
        ArrayList<Order> orders = new ArrayList<>();
        int i = 1;
        while (!content.get(++i).contains("requests>")) {
            String[] values = new String[TAGS.length];
            while (!content.get(i).contains("request>")) {
                for (int j = 0; j < TAGS.length; j++) {
                    Matcher matcher = Pattern.compile("<" + TAGS[j] + ">(.+?)</" + TAGS[j] + ">").matcher(content.get(i));
                    if (matcher.find()) {
                        values[j] = matcher.group(1);
                        i++;
                        break;
                    }
                }
            }
            Order newOrder = buildOrder(values);
            if (newOrder != null) {
                orders.add(newOrder);
            }
        }
        return orders;
    }
}
