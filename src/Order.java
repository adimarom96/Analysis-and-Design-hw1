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

    public void removeLineItem(LineItem lineItem) {
        this.total -= lineItem.getPrice();
        this.lineItems.remove(lineItem);
    }

    public void addPayment(Payment payment) {
        if (payment == null) {
            return;
        }
        for (Payment p : this.payments
        ) {
            if (p == payment) {
                return;
            }
        }
        this.payments.add(payment);
    }

    public String getNumber() {
        return number;
    }

    public String getOrderId() {
        return this.number;
    }

    private int getProductsNum() {
        int s = 0;
        for (LineItem l : lineItems
        ) {
            s++;
        }
        return s;
    }

    public float getTotal() {
        return total;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public void display() {
        int s = 0;
        System.out.println("Order number: " + this.number);
        System.out.println("Order date: " + this.ordered);
        if (this.shipped != null)
            System.out.println("Shipping date: " + this.shipped);
        else
            System.out.println("Not shipped yet");
        System.out.println("Ship to: " + this.ship_to);
        System.out.println("Status: " + this.status);
        s = this.getProductsNum();
        if (s != 0)
            System.out.println("Number of Products: " + s);
        System.out.println("Total money: " + this.total);
    }

    @Override
    public String toString() {
        String payList = "";
        for (Payment pay : payments
        ) {
            payList += pay.getId() + " ";
        }
        String all = "";
        for (LineItem l : lineItems
        ) {
            all += "line item: " + l.getProduct().getName() + " - Price: " + l.getPrice() + " Quantity: " + l.getQuantity()+", ";

        }
        String ans="";
        if(this.shipped != null)
            ans = shipped.toString();
        else
            ans = "not yet";
        return "Order{" +
                "number='" + number + '\'' +
                ", shipped=" + ans +
                ", ordered=" + ordered +
                ", ship_to=" + ship_to +
                ", total=" + total +
                ", status=" + status +
                '}' + ", Account: " + this.account.getID() + ", Payments: " + payList + ", LineItems: " + all;
    }

    public void toPrint() {
        String ans="";
        if(this.shipped != null)
            ans = shipped.toString();
        else
            ans = "not yet";
        System.out.println("Order{" +
                "number='" + number + '\'' +
                ", shipped=" + ans +
                ", ordered=" + ordered +
                ", ship_to=" + ship_to +
                ", total=" + total +
                ", status=" + status +
                ", system id=" + hashCode() +
                '}');

        for (LineItem lineItem : lineItems) {
            lineItem.toPrint();
        }
        for (Payment payment : payments) {
            payment.toPrint();
        }
    }
}