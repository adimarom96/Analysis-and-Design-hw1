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

    public void addCustomer(Customer customer) {
        if (this.customer == null) {
            this.customer = customer;
        }
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

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public void toPrint() {
        System.out.println("User{" +
                "login_id='" + login_id + '\'' +
                ", password='" + password + '\'' +
                ", state=" + state +
                ", system id=" + hashCode() +

                '}');
        this.customer.toPrint();
    }

    @Override
    public String toString() {

        return "User{" +
                "login_id='" + login_id + '\'' +
                ", password='" + password + '\'' +
                ", state=" + state +
                '}' + " Customer: " + this.getCustomer().getId() + ", Shopping cart: " + shoppingCart.getDate();
    }
}