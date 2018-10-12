import controller.MainController;
import model.Order;
import service.OrderFileReader;
import view.ConsoleOutput;

import java.util.ArrayList;

public class MainApp {
    private final static String PATH = "target/classes/";

    public static void main(String[] args) {
        ArrayList<Order> orders = prepareModel(args);
        ConsoleOutput output = new ConsoleOutput();

        MainController mainController = new MainController(orders,output);
        mainController.start(orders.size() > 0);
    }

    private static ArrayList<Order> prepareModel(String[] args) {
        System.out.println("Loading orders from files...");
        OrderFileReader reader = new OrderFileReader(args);
        return reader.read();
    }
}
