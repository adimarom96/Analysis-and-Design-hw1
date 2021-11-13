import java.util.LinkedList;

public class PremiumAccount extends Account {
    private LinkedList<Product> products;

    public PremiumAccount(String id, String billing_address, boolean is_closed, Date open, Date closed, int balance, Customer customer, ShoppingCart shoppingCart, LinkedList<Order> orders, LinkedList<Payment> payments, LinkedList<Product> products) {
        super(id, billing_address, is_closed, open, closed, balance, customer, shoppingCart, orders, payments);
        this.products = products;
    }

    public PremiumAccount(String id, String billing_address, boolean is_closed, Date open, Date closed, int balance, Customer customer, ShoppingCart shoppingCart, LinkedList<Order> orders, LinkedList<Payment> payments) {
        super(id, billing_address, is_closed, open, closed, balance, customer, shoppingCart, orders, payments);
        this.products = new LinkedList<Product>();
    }

    public PremiumAccount(String id, String billing_address, boolean is_closed, Date open, Date closed, int balance, Customer customer, ShoppingCart shoppingCart, LinkedList<Product> products) {
        super(id, billing_address, is_closed, open, closed, balance, customer, shoppingCart);
        this.products = products;
    }

    public PremiumAccount(String id, String billing_address, boolean is_closed, Date open, Date closed, int balance, Customer customer, ShoppingCart shoppingCart) {
        super(id, billing_address, is_closed, open, closed, balance, customer, shoppingCart);
        this.products = new LinkedList<Product>();
    }

    public LinkedList<Product> getProducts() {
        return products;
    }

    public void addProcudt(Product p) {
        this.products.add(p);
        p.setPremium(this);
    }

    public void removeProduct(Product p){
        if(p!=null)
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
        String productlist="";
        for (Product p:products
        ) {
            productlist += p.getName() + " ";

        }
        return  "PremiumAccount{" +
                "products=" + productlist +
                '}' + " "+ super.toString();
    }
}
