import java.util.LinkedList;

public class ShoppingCart {
    private Date created;
    private User user;
    private LinkedList<LineItem> lineItems;
    private Account account;

    public void removelines(){
        for (LineItem l:lineItems
             ) {
            l.delete_lineitem_from_order();
            l.delete_lineitem_from_shopcart();
            l.delete_lineitem_from_product();

        }
    }
    public ShoppingCart(Date date, User user, Account account) {
        this.created = date;
        this.user = user;
        this.account = account;
        this.lineItems = new LinkedList<LineItem>();
    }

    public ShoppingCart(Date date, User user, LinkedList<LineItem> lineItems, Account account) {
        this.created = date;
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
        this.lineItems.remove(lineItem);
        return true;
    }

    /*public boolean addUser(User user) {
        return true;
    }*/
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public void toPrint() {
        System.out.println("ShoppingCart{" + "created=" + created + ", system id=" + hashCode() + '}' + ", Owner: " + this.user.getLogin_id());
    }

    public String getDate() {
        return created.toString();
    }

    @Override
    public String toString() {
        String all ="";
        all += ", User: " + user.getLogin_id() + ", Account: " + account.getID();
        for (LineItem l :lineItems
             ) {
            all += " lineitem: " + l.getProduct().getName() + "-  Price: " + l.getPrice()+ " Quantity: " + l.getQuantity();

        }
        return "ShoppingCart{" +
                "created=" + created +
                '}' + ", "+ all;
    }

    public LinkedList<LineItem> getLines() {
        return  this.lineItems;
    }
}
