package view;

public class ConsoleOutput {
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
        System.out.println("MENU\nWhich report should be generated:\n");
        System.out.println("["+ i++ +"] Amount of orders in total");
        System.out.println("["+ i++ +"] Amount of orders related with specific ClientId");
        System.out.println("["+ i++ +"] Total value of orders");
        System.out.println("["+ i++ +"] Total value of orders related with specific ClientId");
        System.out.println("["+ i++ +"] List of all orders");
        System.out.println("["+ i++ +"] List of orders related with specific ClientId");
        System.out.println("["+ i++ +"] Average value of order");
        System.out.println("["+ i +"] Average value of order related with specific ClientId");
        System.out.println("[0] Exit\n");
        System.out.print("Choose: ");
    }

    public void showReport(String report) {
        System.out.println("\n" + report + "\n");
    }

    public void checkIfSaveToFile() {
        System.out.print("Save report to file? (Y - yes, N - no): ");
    }

    public void notifyIfSavedToFile(String file) {
        System.out.println(((file != null) ? "Report was successfully saved to file \"" + file + "\"!"
                : "Unfortunately, report could not been saved to file!") + "\n");
    }

    public void checkIfLoopShouldBeRepeated() {
        System.out.print("Repeat? (Y - yes, N - no): ");
    }

    public void notifyBadInput() {
        System.out.println("Your input was wrong. Please, try again.");
    }
}
