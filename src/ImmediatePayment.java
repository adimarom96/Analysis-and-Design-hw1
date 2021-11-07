public class ImmediatePayment extends Payment{
    private boolean phoneConfirmation;
    public ImmediatePayment(String id, float total, String details, Account account, Order order,boolean phoneConfirmation) {
        super(id, total, details, account, order);
        this.phoneConfirmation = phoneConfirmation;
    }
}
