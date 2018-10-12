package service.reports;

import dnl.utils.text.table.TextTable;
import model.Order;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

public class ReportCreator {
    private ArrayList<Order> orders;
    private String lastReport;
    private TextTable lastReportList;

    public ReportCreator(ArrayList<Order> orders) {
        this.orders = orders;
    }

    public String create(int choice) {
        return create(choice, null);
    }

    public String create(int choice, String clientId) {
        switch (choice) {
            case 1:
                return createReportAmountOfOrdersInTotal();
            case 2:
                return createReportAmountOfOrdersRelatedWithSpecificClientId(clientId);
            case 3:
                return createReportTotalValueOfOrders();
            case 4:
                return createReportTotalValueOfOrdersRelatedWithSpecificClientId(clientId);
            case 5:
                return createReportListOfAllOrders();
            case 6:
                return createReportListOfAllOrdersRelatedWithSpecificClientId(clientId);
            case 7:
                return createReportAverageValueOfOrder();
            case 8:
                return createReportAverageValueOfOrderRelatedWithSpecificClientId(clientId);
        }
        return null;
    }

    private String createReportAmountOfOrdersInTotal() {
        return "Amount of orders in total is " + orders.size() + ".";
    }

    private String createReportAmountOfOrdersRelatedWithSpecificClientId(String clientId) {
        int amount = 0;
        for (Order order : orders) {
            if (order.getClientId().equals(clientId)) {
                amount++;
            }
        }
        lastReport = "Amount of orders in total related with given ClientId is " + amount + ".";
        return lastReport;
    }

    private String createReportTotalValueOfOrders() {
        double totalValue = 0;
        for (Order order : orders) {
            totalValue += (double) order.getQuantity() * order.getPrice();
        }
        lastReport = "Total value of orders is " + totalValue + ".";
        return lastReport;
    }

    private String createReportTotalValueOfOrdersRelatedWithSpecificClientId(String clientId) {
        double totalValue = 0;
        for (Order order : orders) {
            if (order.getClientId().equals(clientId)) {
                totalValue += (double) order.getQuantity() * order.getPrice();
            }
        }
        lastReport = "Total value of orders related with given ClientId is " + totalValue + ".";
        return lastReport;
    }

    private String createReportListOfAllOrders() {
        return createReportListOfAllOrdersRelatedWithSpecificClientId(null);
    }

    private String createReportListOfAllOrdersRelatedWithSpecificClientId(String clientId) {
        ArrayList<Order> selectedOrders;
        if (clientId != null) {
            selectedOrders = new ArrayList<>();
            for (Order order : orders) {
                if (order.getClientId().equals(clientId)) {
                    selectedOrders.add(order);
                }
            }
        } else {
            selectedOrders = orders;
        }
        String[] cols = new String[] {"Client_Id","Request_id","Name","Quantity","Price"};
        Object[][] data = new Object[selectedOrders.size()][];
        for (int i = 0; i < data.length; i++) {
            data[i] = new Object[5];
            data[i][0] = selectedOrders.get(i).getClientId();
            data[i][1] = selectedOrders.get(i).getRequestId();
            data[i][2] = selectedOrders.get(i).getName();
            data[i][3] = selectedOrders.get(i).getQuantity();
            data[i][4] = selectedOrders.get(i).getPrice();
        }
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        TextTable table = new TextTable(cols, data);
        lastReportList = table;
        table.printTable(new PrintStream(outputStream), 0);
        return outputStream.toString();
    }

    private String createReportAverageValueOfOrder() {
        double sum = 0;
        for (Order order : orders) {
            sum += (double) order.getQuantity() * order.getPrice();
        }
        double average = sum / orders.size();
        lastReport = "Average value of order is " + average + ".";
        return lastReport;
    }

    private String createReportAverageValueOfOrderRelatedWithSpecificClientId(String clientId) {
        double sum = 0;
        double amount = 0;
        for (Order order : orders) {
            if (order.getClientId().equals(clientId)) {
                amount++;
                sum += (double) order.getQuantity() * order.getPrice();
            }
        }
        double average = sum / amount;
        lastReport = "Average value of order related with given ClientId is " + average + ".";
        return lastReport;
    }

    public String getLastReport() {
        return lastReport;
    }

    public TextTable getLastReportList() {
        return lastReportList;
    }
}
