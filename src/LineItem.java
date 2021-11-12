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

    public boolean delete() {
        return !(!this.product.removeLineItem(this) | !this.shoppingCart.removeLineItem(this) | !this.order.removeLineItem(this));
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void delete_lineitem_from_order() {
        this.order.removeLineItem(this);
    }

    public void delete_shopcart_from_order() {
        this.shoppingCart.removeLineItem(this);
    }

    public void toPrint() {
        // todo - add to print the id system
        System.out.println("LineItem{" +
                "quantity=" + quantity +
                ", price=" + price +
                '}');
    }
}
