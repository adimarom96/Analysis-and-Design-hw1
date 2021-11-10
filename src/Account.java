import java.util.LinkedList;

public class Account {

    private String id;
    private String billing_address;
    private boolean is_closed;
    private Date open;
    private Date closed;
    private int balance;
    private Customer customer;
    private ShoppingCart shoppingCart;
    private LinkedList<Order> orders;
    private LinkedList<Payment> payments;

    //todo: קשר אחד לאחד עם קסטומר ועם עגלת קניות
    public Account(String id, String billing_address, boolean is_closed, Date open, Date closed, int balance, Customer customer, ShoppingCart shoppingCart, LinkedList<Order> orders, LinkedList<Payment> payments) {
        this.id = id;
        this.billing_address = billing_address;
        this.is_closed = is_closed;
        this.open = open;
        this.closed = closed;
        this.balance = balance;
        this.customer = customer;
        this.shoppingCart = shoppingCart;
        this.orders = orders;
        this.payments = payments;
    }

    public Account(String id, String billing_address, boolean is_closed, Date open, Date closed, int balance, Customer customer, ShoppingCart shoppingCart) {
        this.id = id;
        this.billing_address = billing_address;
        this.is_closed = is_closed;
        this.open = open;
        this.closed = closed;
        this.balance = balance;
        this.customer = customer;
        this.shoppingCart = shoppingCart;
    }


    public void addPayment(Payment payment) {
        payments.add(payment);
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public LinkedList<Order> getOrders() {
        return orders;
    }
}
