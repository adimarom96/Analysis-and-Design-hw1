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
        order.addPayment(this);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public void toPrint() {
        System.out.print("Payment{" +
                "id='" + id + '\'' +
                ", paid=" + paid +
                ", total=" + total +
                ", details='" + details +
                ", system id=" + hashCode());
        if (this instanceof ImmediatePayment)
            System.out.println(", PhoneConfirmation=" + ((ImmediatePayment) this).isPhoneConfirmation() + "}");
        else if (this instanceof DelayPayment)
            System.out.println(", PaymentDate=" + ((DelayPayment) this).getPaymentDate() + "}");
        else
            System.out.println();
    }
}
