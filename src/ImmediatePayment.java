public class ImmediatePayment extends Payment{
    private boolean phoneConfirmation;

    public ImmediatePayment(String id, Date paid, float total, String details, Account account, Order order, boolean phoneConfirmation) {
        super(id, paid, total, details, account, order);
        this.phoneConfirmation = phoneConfirmation;
    }
}
