package view;

import model.Order;

import java.util.ArrayList;

public class ConsoleOutput {
    private ArrayList<Order> orders;

    public ConsoleOutput(ArrayList<Order> orders) {
        this.orders = orders;
    }

    public void displayGreetings() {
        System.out.println("Welcome to Simple Order Management System developed by Paweł Kowański!\n");
        System.out.println("This application after launch reads all the files passed by program arguments and saves" +
                "them in memory as orders.");
        System.out.println("If you pass files with valid orders you will be able to receive some statistics" +
                "and reports covering given data.\n");
    }

    public void notifyIfOrdersWereLoaded(boolean loaded) {
        System.out.println(((loaded) ? "Orders were successfully loaded!" : "Unfortunately, orders could not been loaded!") + "\n");
    }

    public void menu() {
        int i = 1;
        System.out.println("MENU\nWhich raport should be generated:\n");
        System.out.println("["+ i++ +"] Amount of orders in total");
        System.out.println("["+ i++ +"] Amount of orders related with specific ClientId");
        System.out.println("["+ i++ +"] Total value of orders");
        System.out.println("["+ i++ +"] Total value of orders related with specific ClientId");
        System.out.println("["+ i++ +"] List of all orders");
        System.out.println("["+ i++ +"] List of orders related with specific ClientId");
        System.out.println("["+ i++ +"] Average value of order");
        System.out.println("["+ i +"] Average value of order related with specific ClientId");
        System.out.println("[0] Exit\n");
        System.out.println("Choose: ");

    }
}
