public class Payment {
    private String id;
    //todo: add paid type Date
    private float total;
    private String details;
    private Account account;
    private Order order;

    public Payment(String id, float total, String details, Account account, Order order) {
        this.id = id;
        //todo: date!
        this.total = total;
        this.details = details;
        this.account = account;
        this.order = order;
    }

    public boolean addAcount(Account a){
        if(this.account!=null)
        {
            this.account = a;
            this.account.addPayment(this);
            return true;
        }
        return false;
    }

    public boolean addOrder(Order o){
        if(this.order!=null)
        {
            this.order = o;
            this.order.addPayment(this);
            return true;
        }
        return false;
    }

    public String getId() {
        return id;
    }

    public float getTotal() {
        return total;
    }

    public String getDetails() {
        return details;
    }

    public Account getAccount() {
        return account;
    }

    public Order getOrder() {
        return order;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public void setDetails(String details) {
        this.details = details;
    }
    // todo: set or replace of the order, account.
}
