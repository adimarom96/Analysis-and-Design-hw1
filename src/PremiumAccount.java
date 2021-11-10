import java.util.LinkedList;

public class PremiumAccount extends Account {
    private LinkedList<Product> products;
    public PremiumAccount(String id, String billing_address, boolean is_closed, Date open, Date closed, int balance, Customer customer, ShoppingCart shoppingCart, LinkedList<Order> orders, LinkedList<Payment> payments,LinkedList<Product> products) {
        super(id, billing_address, is_closed, open, closed, balance, customer, shoppingCart, orders, payments);
        this.products=products;
    }
    public PremiumAccount(String id, String billing_address, boolean is_closed, Date open, Date closed, int balance, Customer customer, ShoppingCart shoppingCart, LinkedList<Order> orders, LinkedList<Payment> payments) {
        super(id, billing_address, is_closed, open, closed, balance, customer, shoppingCart, orders, payments);
    }

    public PremiumAccount(String id, String billing_address, boolean is_closed, Date open, Date closed, int balance, Customer customer, ShoppingCart shoppingCart,LinkedList<Product> products) {
        super(id, billing_address, is_closed, open, closed, balance, customer, shoppingCart);
        this.products=products;
    }

    public PremiumAccount(String id, String billing_address, boolean is_closed, Date open, Date closed, int balance, Customer customer, ShoppingCart shoppingCart) {
        super(id, billing_address, is_closed, open, closed, balance, customer, shoppingCart);
    }

    public LinkedList<Product> getProducts() {
        return products;
    }
}
