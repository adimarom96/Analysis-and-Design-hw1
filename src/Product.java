import java.util.LinkedList;

public class Product {
    private String id;
    private String name;
    private Supplier supplier;
    private LinkedList<LineItem> lineItems;
    private PremiumAccount premiumAccount;

    public Product(String id, String name, Supplier supplier) {
        this.id = id;
        this.name = name;
        this.supplier = supplier;
        this.lineItems = new LinkedList<LineItem>();
    }

    public Product(String id, String name, Supplier supplier, PremiumAccount premiumAccount) {
        this.id = id;
        this.name = name;
        this.supplier = supplier;
        this.lineItems = new LinkedList<LineItem>();
        this.premiumAccount = premiumAccount;
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

    public void setName(String name) {
        this.name = name;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
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

    public boolean addPremiumAccount(PremiumAccount p) {
        if (this.premiumAccount == null) {
            this.premiumAccount = p;
            return true;
        }
        return false;
    }// todo: consider new func if want to replace the Premium account;

    public boolean addSupplier(Supplier supplier) {

        if (this.supplier == null) {
            if (supplier.addProduct(this)) {
                this.supplier=supplier;
                return true;
            }
        }// todo replace??
        return false;

    }

    public boolean removeLineItem(LineItem lineItem) {
        return this.lineItems.remove(lineItem);
    }

    public void remove() {
        for (LineItem l:lineItems
             ) {
            l.delete_lineitem_from_order();
            l.delete_lineitem_from_shopcart();

        }
    }

    public void toPrint() {
        System.out.println( "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}');
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
}
