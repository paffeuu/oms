package model;

public class Order {
    private String clientId;
    private long requestId;
    private String name;
    private int quantity;
    private double price;

    public Order() {}

    public Order(String clientId, long requestId, String name, int quantity, double price) {
        this.clientId = clientId;
        this.requestId = requestId;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public long getRequestId() {
        return requestId;
    }

    public void setRequestId(long requestId) {
        this.requestId = requestId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Order))
            return false;
        Order order = (Order) obj;
        if (!this.clientId.equals(order.clientId)) {
            return false;
        }
        if (this.requestId != order.requestId) {
            return false;
        }
        if (!this.name.equals(order.name)) {
            return false;
        }
        if (this.quantity != order.quantity) {
            return false;
        }
        if (this.price != order.price) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return clientId.hashCode() + (int)(requestId % (long)Integer.MAX_VALUE) + name.hashCode();
    }
}
