import java.sql.SQLOutput;
import java.util.LinkedList;

public class Order {
    private String number;
    private Date shipped;
    private Date ordered;
    private Address ship_to;
    private float total;
    private OrderStatus status;
    private LinkedList<LineItem> lineItems;
    private Account account;
    private LinkedList<Payment> payments;
    static int orderid = 0;

    public Order(Date orderd, Address ship_to, OrderStatus status, Account account) {
        orderid++;
        this.number = String.valueOf(orderid);
        this.shipped = null;
        this.ordered = orderd;
        this.ship_to = ship_to;
        this.total = 0;
        this.status = status;
        this.account = account;
        this.lineItems = new LinkedList<LineItem>();
        this.payments = new LinkedList<Payment>();
        account.addOrder(this);
    }

    public Order(Date orderd, Address ship_to, OrderStatus status, LinkedList<LineItem> lineItems, Account account, LinkedList<Payment> payments) {
        orderid++;
        this.number = String.valueOf(orderid);
        this.shipped = null;
        this.ordered = orderd;
        this.ship_to = ship_to;
        this.total = 0;
        this.status = status;
        this.lineItems = lineItems;
        this.account = account;
        this.payments = payments;
        account.addOrder(this);
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
        this.total += lineItem.getPrice();
        return true;
    }

    public boolean removeLineItem(LineItem lineItem) {
        this.total -= lineItem.getPrice();
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

    public boolean removePayment(Payment payment) {
        return this.payments.remove(payment);
    }

    public String getNumber() {
        return number;
    }

    public String getOrderId() {
        return this.number;
    }

    public void display()  {
        System.out.println("Order number: " + this.number);
        System.out.println("Order date: " + this.ordered);
        if(this.shipped!=null)
            System.out.println("Shipping date: " + this.shipped);
        else
            System.out.println("Not shipped yet");
        System.out.println("Ship to: " + this.ship_to);
        System.out.println("Status: " + this.status);
        System.out.println("Total: " + this.total);
        System.out.println();
    }

    public void toPrint() {
        // todo - add to print the id system
        System.out.println("Order{" +
                "number='" + number + '\'' +
                ", shipped=" + shipped +
                ", ordered=" + ordered +
                ", ship_to=" + ship_to +
                ", total=" + total +
                ", status=" + status +
                '}');

        for (LineItem lineItem : lineItems) {
            lineItem.toPrint();
        }
        for (Payment payment : payments) {
            payment.toPrint();
        }
    }
}