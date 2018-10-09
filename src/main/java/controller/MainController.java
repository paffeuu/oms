package controller;

import model.Order;
import view.ConsoleOutput;

import java.util.ArrayList;

public class MainController {
    private ArrayList<Order> orders;
    private ConsoleOutput output;

    public MainController(ArrayList<Order> orders, ConsoleOutput consoleOutput) {
        this.orders = orders;
        this.output = consoleOutput;
    }

    public void start(boolean ordersLoaded) {
        output.notifyIfOrdersWereLoaded(ordersLoaded);
        output.displayGreetings();
        while(mainLoop()) {}
    }

    private boolean mainLoop() {
        output.menu();
        return false;
    }
}
