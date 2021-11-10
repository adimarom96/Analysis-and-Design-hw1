public class Customer {
    private String id;
    private String phone;
    private String email;
    private Address address;
    private Account account;
    private User user;

    public Customer(String id, String phone, String email, Address address, Account account, User user) {
        this.id = id;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.account = account;
        this.user = user;
    }

    public Customer(String id, String phone, String email, Address address, Account account) {
        this.id = id;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.account = account;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    /*public boolean addUser(User user) {
        if (this.user != null)
            return false;
        if (user != null) {
            this.user = user;
            //user.addCustomer(this);
            return true;
        }
        return false;
    }*/
}
