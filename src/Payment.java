import javax.swing.*;

public class Payment {
    private String id;
    private Date paid;
    private float total;
    private String details;
    private Account account;
    private Order order;

    public Payment(String id, Date paid, float total, String details, Account account, Order order) {
        this.id = id;
        this.paid = paid;
        this.total = total;
        this.details = details;
        this.account = account;
        this.order = order;
        account.addPayment(this);
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

    public void toPrint() {
        // todo - add to print the id system
        System.out.println("Payment{" +
                "id='" + id + '\'' +
                ", paid=" + paid +
                ", total=" + total +
                ", details='" + details);
        if (this instanceof ImmediatePayment)
            System.out.println(", PhoneConfirmation=" + ((ImmediatePayment) this).isPhoneConfirmation() + "}");
        if (this instanceof DelayPayment)
            System.out.println(", PaymentDate=" + ((DelayPayment) this).getPaymentDate() + "}");
    }
}
