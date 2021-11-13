import java.util.LinkedList;

public class Supplier {
    private String id;
    private String name;
    private LinkedList<Product> products;

    public Supplier(String id, String name) {
        this.id = id;
        this.name = name;
        this.products = new LinkedList<Product>();
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

    public LinkedList<Product> getProducts() {
        return products;
    }

    public void setProducts(LinkedList<Product> products) {
        this.products = products;
    }

    public boolean addProduct(Product product){
        if(product == null){
            return false;
        }
        for (Product p: this.products
             ) {
            if(p.getId().equals(product.getId())){
                return false;
            }
        }
        this.products.add(product);
        return true;
    }

    public void removeProduct(Product product){
        this.products.remove(product);
    }
    @Override
    public int hashCode() {
        return super.hashCode();
    }
    public void toPrint() {
        System.out.println( "Supplier{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", system id=" + hashCode() +

                '}');
    }

    @Override
    public String toString() {
        String productlist="";
        for (Product p:products
             ) {
            productlist += p.getName() + " ";

        }
        return "Supplier{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}'+" Products: " +productlist;
    }
}
