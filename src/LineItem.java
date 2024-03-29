public class LineItem {
    private int quantity;
    private int price;
    private Product product;
    private ShoppingCart shoppingCart;
    private Order order;

    public LineItem(int quantity, int price, Product product, ShoppingCart shoppingCart, Order order) {
        this.quantity = quantity;
        this.price = price;
        this.product = product;
        this.shoppingCart = shoppingCart;
        this.order = order;
        if (!this.product.addLineItem(this) | !this.shoppingCart.addLineItem(this) | !this.order.addLineItem(this))
            throw new RuntimeException("Can't create the wanted item");
    }

    public int getQuantity() {
        return quantity;
    }

    public int getPrice() {
        return price;
    }

    public Product getProduct() {
        return product;
    }

    public void delete_lineitem_from_order() {
        this.order.removeLineItem(this);
    }

    public void delete_lineitem_from_product() {
        this.product.removeLineItem(this);
    }

    public void delete_lineitem_from_shopcart() {
        this.shoppingCart.removeLineItem(this);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public void toPrint() {
        System.out.println("LineItem{" +
                "quantity=" + quantity +
                ", price=" + price +
                ", system id=" + hashCode() +
                '}');
    }

    @Override
    public String toString() {
        return ", Product: " + this.product.getName() + " LineItem{" +
                "quantity=" + quantity +
                ", price=" + price +
                '}' + ", ShoppingCart: " + this.shoppingCart.getDate() + ", Order: "
                + this.order.getNumber();
    }
}