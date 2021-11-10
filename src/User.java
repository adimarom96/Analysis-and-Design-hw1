public class User {
    private String login_id;
    private String password;
    private UserState state;
    private Customer customer;
    private ShoppingCart shoppingCart;

    public User(String login_id, String password, UserState state, Customer customer) {
        this.login_id = login_id;
        this.password = password;
        this.state = state;
        this.customer = customer;
    }

    public User(String login_id, String password, UserState state, Customer customer, ShoppingCart shoppingCart) {
        this.login_id = login_id;
        this.password = password;
        this.state = state;
        this.customer = customer;
        this.shoppingCart = shoppingCart;
    }

    public boolean addShoppingCart(ShoppingCart shopC) {
        if (this.shoppingCart == null) {
            if (shopC.addUser(this)) {
                this.shoppingCart = shopC;
                return true;
            }
        }
        return false;
    }
    public boolean addCustomer(Customer customer) {
        if (this.shoppingCart == null) {
            if (customer.addUser(this)) {
                this.customer = customer;
                return true;
            }
        }// todo replace??
        return false;
    }
}
