public class DelayPayment extends Payment {
    private Date paymentDate;

    public DelayPayment(String id, Date paid, float total, String details, Account account, Order order, Date paymentDate) {
        super(id, paid, total, details, account, order);
        this.paymentDate = paymentDate;
    }
    //todo : add date

}
