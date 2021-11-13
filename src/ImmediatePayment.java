public class ImmediatePayment extends Payment{
    private boolean phoneConfirmation;

    public ImmediatePayment(String id, Date paid, float total, String details, Account account, Order order, boolean phoneConfirmation) {
        super(id, paid, total, details, account, order);
        this.phoneConfirmation = phoneConfirmation;
    }
    @Override
    public int hashCode() {
        return super.hashCode();
    }
    public boolean isPhoneConfirmation() {
        return phoneConfirmation;
    }
}
