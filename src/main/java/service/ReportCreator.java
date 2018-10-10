package service;

import dnl.utils.text.table.TextTable;
import model.Order;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

public class ReportCreator {
    private ArrayList<Order> orders;

    public ReportCreator(ArrayList<Order> orders) {
        this.orders = orders;
    }

    public String create(int choice) {
        switch (choice) {
            case 1:
                return createReportAmountOfOrdersInTotal();
            case 2:
                return createReportAmountOfOrdersRelatedWithSpecificClientId();
            case 3:
                return createReportTotalValueOfOrders();
            case 4:
                return createReportTotalValueOfOrdersRelatedWithSpecificClientId();
            case 5:
                return createReportListOfAllOrders();
            case 6:
                return createReportListOfAllOrdersRelatedWithSpecificClientId();
            case 7:
                return createReportAverageValueOfOrder();
            case 8:
                return createReportAverageValueOfOrderRelatedWithSpecificClientId();
        }
        return null;
    }

    private String createReportAmountOfOrdersInTotal() {
        return "Amount of orders in total is " + orders.size() + ".";
    }

    private String createReportAmountOfOrdersRelatedWithSpecificClientId() {
        return "2";
    }

    private String createReportTotalValueOfOrders() {
        double totalValue = 0;
        for (Order order : orders) {
            totalValue += (double) order.getQuantity() * order.getPrice();
        }
        return "Total value of orders is " + totalValue + ".";
    }

    private String createReportTotalValueOfOrdersRelatedWithSpecificClientId() {
        return "4";
    }

    private String createReportListOfAllOrders() {
        String[] cols = new String[] {"Client_Id","Request_id","Name","Quantity","Price"};
        Object[][] data = new Object[orders.size()][];
        for (int i = 0; i < data.length; i++) {
            data[i] = new Object[5];
            data[i][0] = orders.get(i).getClientId();
            data[i][1] = orders.get(i).getRequestId();
            data[i][2] = orders.get(i).getName();
            data[i][3] = orders.get(i).getQuantity();
            data[i][4] = orders.get(i).getPrice();
        }
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        TextTable table = new TextTable(cols, data);
        table.printTable(new PrintStream(outputStream), 0);
        return outputStream.toString();
    }

    private String createReportListOfAllOrdersRelatedWithSpecificClientId() {
        return "6";
    }

    private String createReportAverageValueOfOrder() {
        double sum = 0;
        for (Order order : orders) {
            sum += (double) order.getQuantity() * order.getPrice();
        }
        double average = sum / orders.size();
        return "Average value of order is " + average + ".";
    }

    private String createReportAverageValueOfOrderRelatedWithSpecificClientId() {
        return "8";
    }


}
