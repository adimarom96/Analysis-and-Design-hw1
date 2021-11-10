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
        if (customer.getUser() == null)
            customer.setUser(this);
    }

    public User(String login_id, String password, UserState state, Customer customer, ShoppingCart shoppingCart) {
        this.login_id = login_id;
        this.password = password;
        this.state = state;
        this.customer = customer;
        this.shoppingCart = shoppingCart;
        if (customer.getUser() == null)
            customer.setUser(this);
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
        if (this.customer == null) {
            if (customer.getUser() == null) { // when adding customer make sure that the customer dont alredy have user
                this.customer = customer;
                return true;
            }
        }// todo replace??
        return false;
    }

    public String getLogin_id() {
        return login_id;
    }

    public String getPassword() {
        return password;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void remove_user(){
        //todo: remove shopping cart and more
    }
}
