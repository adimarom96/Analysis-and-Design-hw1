import java.util.LinkedList;

public class Product {
    private String id;
    private String name;
    private Supplier supplier;
    private LinkedList<LineItem> lineItems;
    private PremiumAccount premiumAccount;
    private int price;

    public Product(String id, String name, Supplier supplier) {
        this.id = id;
        this.name = name;
        this.supplier = supplier;
        this.lineItems = new LinkedList<LineItem>();
        this.price = 0;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public boolean addLineItem(LineItem lineItem) {
        if (lineItem == null) {
            return false;
        }
        for (LineItem p : this.lineItems
        ) {
            if (p == lineItem) {
                return false;
            }
        }
        this.lineItems.add(lineItem);
        return true;
    }

    public void removeLineItem(LineItem lineItem) {
        this.lineItems.remove(lineItem);
    }

    public void remove() {
        for (LineItem l : lineItems
        ) {
            l.delete_lineitem_from_order();
            l.delete_lineitem_from_shopcart();
        }
    }

    public void setPremium(PremiumAccount premiumAccount) {
        this.premiumAccount = premiumAccount;
    }

    public LinkedList<LineItem> getLineItems() {
        return lineItems;
    }

    public PremiumAccount getPremiumAccount() {
        return premiumAccount;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public void toPrint() {
        System.out.println("Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price =" + price +
                ", system id=" + hashCode() +
                '}');
    }

    @Override
    public String toString() {
        String all = "";
        if (this.getPremiumAccount() != null)
            all += " Owner: " + this.getPremiumAccount().getID();
        for (LineItem l : lineItems
        ) {
            all += ", lineitem: " + l.getProduct().getName() + " - Price: " + l.getPrice() + " Quantity: " + l.getQuantity();
        }
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}' + ", Supplier: " + this.getSupplier().getName() + ", " + all;
    }
}