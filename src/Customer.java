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

    public void setUser(User user) {
        this.user = user;
    }

    public Account getAccount() {
        return account;
    }

    public User getUser() {
        return user;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void toPrint() {
        System.out.println("Customer{" +
                "id='" + id + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", address=" + address +
                ", system id=" + hashCode() +
                '}');
        this.account.toPrint();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public String getId() {
        return this.id;
    }

    @Override
    public String toString() {
        String us = "";
        if (user != null)
            us = " User: " + this.getUser().getLogin_id();
        return "Customer{" +
                "id='" + id + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", address=" + address +
                '}' + ", Account: " + this.getAccount().getID() + us;
    }
}