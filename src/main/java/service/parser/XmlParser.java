package service.parser;

import model.Order;

import java.util.ArrayList;

public class XmlParser implements Parser {
    private ArrayList<String> content;

    public XmlParser(ArrayList<String> content) {
        this.content = content;
    }

    @Override
    public ArrayList<Order> parse() {
        return null;
    }
}
