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
    static int orderid=0;

    public Order( Date orderd, Address ship_to, OrderStatus status, Account account) {
        orderid++;
        this.number = String.valueOf(orderid);
        this.shipped = null;
        this.orderd = orderd;
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
        this.orderd = orderd;
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

    public String getNumber() {
        return number;
    }

    public String getOrderId() {
        return this.number;
    }

    public void display() {
        System.out.println("order number: "+ this.number + "\n");
        System.out.println("order date: "+ this.orderd + "\n");
        System.out.println("shipping date: "+ this.shipped + "\n");
        System.out.println("ship to : "+ this.ship_to + "\n");
        System.out.println("status : "+ this.status + "\n");
        System.out.println("total : "+ this.total + "\n");
    }
}