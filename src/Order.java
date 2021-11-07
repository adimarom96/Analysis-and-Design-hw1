import java.util.LinkedList;

public class Order {
    private String number;
    private Date shipped;
    private Date orderd;
    private Address ship_to;
    private float total;
    private OrderStatus status;
    private LinkedList<LineItem> lineItems;
    private Account account;
    private LinkedList<Payment> payments;

    public Order(String number, Date shipped, Date orderd, Address ship_to, float total, OrderStatus status, Account account) {
        this.number = number;
        this.shipped = shipped;
        this.orderd = orderd;
        this.ship_to = ship_to;
        this.total = total;
        this.status = status;
        this.account = account;
        this.lineItems = new LinkedList<LineItem>();
        this.payments = new LinkedList<Payment>();
    }

    public Order(String number, Date shipped, Date orderd, Address ship_to, float total, OrderStatus status, LinkedList<LineItem> lineItems, Account account, LinkedList<Payment> payments) {
        this.number = number;
        this.shipped = shipped;
        this.orderd = orderd;
        this.ship_to = ship_to;
        this.total = total;
        this.status = status;
        this.lineItems = lineItems;
        this.account = account;
        this.payments = payments;
    }

    public boolean addLineItem(LineItem lineItem) {
        if (lineItem == null) {
            return false;
        }
        for (LineItem p : this.lineItems
        ) {
            if (p == lineItem) {
                return false;
            }
        }
        this.lineItems.add(lineItem);
        return true;
    }

    public boolean removeLineItem(LineItem lineItem) {
        return this.lineItems.remove(lineItem);
    }

    public boolean addPayment(Payment payment) {
        if (payment == null) {
            return false;
        }
        for (Payment p : this.payments
        ) {
            if (p == payment) {
                return false;
            }
        }
        this.payments.add(payment);
        return true;

    }
    public boolean removePayment(Payment payment)
    {
        return this.payments.remove(payment);
    }
}