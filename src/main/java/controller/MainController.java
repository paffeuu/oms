package controller;

import model.Order;
import view.ConsoleOutput;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class MainController {
    private ArrayList<Order> orders;
    private ConsoleOutput output;
    private Scanner scanner;

    public MainController(ArrayList<Order> orders, ConsoleOutput consoleOutput) {
        this.orders = orders;
        this.output = consoleOutput;
        this.scanner = new Scanner(System.in);
    }

    public void start(boolean ordersLoaded) {
        output.notifyIfOrdersWereLoaded(ordersLoaded);
        output.displayGreetings();
        while(mainLoop()) {}
    }

    private boolean mainLoop() {
        output.menu();
        output.showReport(generateReport(readMenuUserChoice()));
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

    private String generateReport(int choice) {
        // report generator
        return "report\nreport\nreport";
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
