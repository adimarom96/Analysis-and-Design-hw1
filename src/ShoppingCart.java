import java.util.LinkedList;

public class ShoppingCart {
    private Date date;
    private User user;
    private LinkedList<LineItem> lineItems;
    private Account account;

    public ShoppingCart(Date date, User user, Account account) {
        this.date = date;
        this.user = user;
        this.account = account;
    }

    public ShoppingCart(Date date, User user, LinkedList<LineItem> lineItems, Account account) {
        this.date = date;
        this.user = user;
        this.lineItems = lineItems;
        this.account = account;
    }

    public boolean addLineItem(LineItem lineItem) { //maybe should get alinked list of line items
        if (lineItem != null) {
            this.lineItems.add(lineItem);
            return true;
        }
        return false;
    }

    public boolean removeLineItem(LineItem lineItem) {
        //todo
        return true;
    }

    public boolean addUser(User user) {
        return true;
        //todo!
    }
    //todo: created type date
}
