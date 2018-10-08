package service;

public class OrderValidator {
    private final static String POLISH_CHARS = "ĄąĆćĘęŁłŃńÓóŚśŹźŻż";

    public String validateClientId(String value) {
        if (value.length() < 6 && value.matches("[A-Za-z0-9" + POLISH_CHARS + "]+")) {
            return value;
        } else {
            return null;
        }
    }

    public Long validateRequestId(String value) {
        Long requestId;
        try {
            requestId = Long.parseLong(value);
        } catch (NumberFormatException nfex) {
            requestId = null;
        }
        return requestId;
    }

    public String validateName(String value) {
        if (value.length() < 255 && value.matches("[ A-Za-z0-9"+POLISH_CHARS+"]+")) {
            return value;
        } else {
            return null;
        }
    }

    public Integer validateQuantity(String value) {
        Integer quantity;
        try {
            quantity = Integer.parseInt(value);
        } catch (NumberFormatException nfex) {
            quantity = null;
        }
        return quantity;
    }

    public Double validatePrice(String value) {
        Double price;
        try {
            price = Double.parseDouble(value);
        } catch (NumberFormatException nfex) {
            price = null;
        }
        return price;
    }
}
