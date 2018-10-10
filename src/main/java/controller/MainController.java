package controller;

import model.Order;
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

    public MainController(ArrayList<Order> orders, ConsoleOutput consoleOutput) {
        this.orders = orders;
        this.output = consoleOutput;
        this.scanner = new Scanner(System.in);
        this.creator = new ReportCreator(orders);
    }

    public void start(boolean ordersLoaded) {
        output.notifyIfOrdersWereLoaded(ordersLoaded);
        output.displayGreetings();
        while(mainLoop()) {}
    }

    private boolean mainLoop() {
        output.menu();
        int userChoice = readMenuUserChoice();
        if (userChoice == 0) {
            return false;
        }
        output.showReport(creator.create(userChoice));
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
