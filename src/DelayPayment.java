public class DelayPayment extends Payment {
    private Date paymentDate;

    public DelayPayment(String id, float total, String details, Account account, Order order, Date paymentDay) {
        super(id, total, details, account, order);
        paymentDate = paymentDay;
    }
    //todo : add date

}
