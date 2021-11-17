import java.util.HashMap;
import java.util.LinkedList;

public class PremiumAccount extends Account {
    private LinkedList<Product> products;
    private HashMap<String, Integer> prod_quntity;

    public PremiumAccount(String id, String billing_address, boolean is_closed, Date open, Date closed, int balance, Customer customer, ShoppingCart shoppingCart, LinkedList<Order> orders, LinkedList<Payment> payments, LinkedList<Product> products) {
        super(id, billing_address, is_closed, open, closed, balance, customer, shoppingCart, orders, payments);
        this.products = products;
        this.prod_quntity = new HashMap<>();
    }

    public PremiumAccount(String id, String billing_address, boolean is_closed, Date open, Date closed, int balance, Customer customer, ShoppingCart shoppingCart, LinkedList<Order> orders, LinkedList<Payment> payments) {
        super(id, billing_address, is_closed, open, closed, balance, customer, shoppingCart, orders, payments);
        this.products = new LinkedList<Product>();
        this.prod_quntity = new HashMap<>();
    }

    public PremiumAccount(String id, String billing_address, boolean is_closed, Date open, Date closed, int balance, Customer customer, ShoppingCart shoppingCart, LinkedList<Product> products) {
        super(id, billing_address, is_closed, open, closed, balance, customer, shoppingCart);
        this.products = products;
        this.prod_quntity = new HashMap<>();
    }

    public PremiumAccount(String id, String billing_address, boolean is_closed, Date open, Date closed, int balance, Customer customer, ShoppingCart shoppingCart) {
        super(id, billing_address, is_closed, open, closed, balance, customer, shoppingCart);
        this.products = new LinkedList<Product>();
        this.prod_quntity = new HashMap<>();
    }

    public int getQuantity(String name) {
        return this.prod_quntity.get(name);
    }

    public LinkedList<Product> getProducts() {
        return products;
    }

    boolean has_enough(String proudct, int qunt) {
        return prod_quntity.get(proudct) >= qunt;
    }

    public void decressProd(String product_name) {
        prod_quntity.put(product_name, prod_quntity.get(product_name) - 1);
    }

    public void addProcudt(Product p, int quantity) {
        this.products.add(p);
        this.prod_quntity.put(p.getName(), quantity);
        p.setPremium(this);
    }

    public void removeProduct(Product p) {
        if (p != null)
            this.products.remove(p);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean isPremium() {
        return true;
    }

    @Override
    public String toString() {
        String productlist = ":\n";
        for (Product p : products
        ) {
            int quant = this.prod_quntity.get(p.getName());

            productlist += p.getName() + ":" + " quantity: " + quant + "\n";

        }
        return "PremiumAccount "+ super.toString() + "\nProducts" + productlist;
    }
}
