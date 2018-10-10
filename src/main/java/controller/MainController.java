package controller;

import model.Order;
import service.OrderValidator;
import service.ReportCreator;
import view.ConsoleOutput;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class MainController {
    private ArrayList<Order> orders;
    private ConsoleOutput output;
    private Scanner scanner;
    private ReportCreator creator;
    private OrderValidator validator;

    public MainController(ArrayList<Order> orders, ConsoleOutput consoleOutput) {
        this.orders = orders;
        this.output = consoleOutput;
        this.scanner = new Scanner(System.in);
        this.creator = new ReportCreator(orders);
        this.validator = new OrderValidator();
    }

    public void start(boolean ordersLoaded) {
        output.notifyIfOrdersWereLoaded(ordersLoaded);
        output.displayGreetings();
        while(mainLoop());
    }

    private boolean mainLoop() {
        output.menu();
        int userChoice = readMenuUserChoice();
        if (userChoice == 0) {
            return false;
        }
        if (userChoice % 2 == 0) {
            output.requestForClientId();
            output.showReport(creator.create(userChoice, readClientId()));
        } else {
            output.showReport(creator.create(userChoice));
        }
        output.checkIfSaveToFile();
        if (readYesNo()) {
            output.notifyIfSavedToFile(saveToFile());
        }
        output.checkIfLoopShouldBeRepeated();
        return readYesNo();
    }

    private int readMenuUserChoice() {
        while (true) {
            String input = scanner.nextLine();
            if (Pattern.matches("[0-8]", input)) {
                return Integer.parseInt(input);
            }
            output.notifyBadInput();
        }
    }

    private String readClientId() {
        while (true) {
            String clientId = scanner.nextLine();
            if (validator.validateClientId(clientId) != null) {
                for (Order order : orders) {
                    if (order.getClientId().equals(clientId)) {
                        return clientId;
                    }
                }
            }
            output.notifyBadInput();
        }
    }

    private boolean readYesNo() {
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("Y")) {
                return true;
            }
            if (input.equals("N")) {
                return false;
            }
            output.notifyBadInput();
        }
    }

    private String saveToFile() {
        // report saver
        return "random_name.csv";
    }
}
