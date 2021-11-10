import java.time.LocalDate;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {

       /* Account a1 = new Account();
        Account a2 = new Account();
        Customer c1 = new Customer("id1", "phone1", "email1", new Address("address1"), a1);
        Customer c2 = new Customer("id2", "phone2", "email2", new Address("address2"), a2);
        User u1 = new User("id", "password", UserState.Active, c1);
        //System.out.println(c1.addUser(u1));
        */

        /*--------------------------------------------------------------------*/
        Supplier osem = new Supplier("Osem", "Osem");
        Supplier eastwest = new Supplier("EastWest", "EastWest");
        Product bamba = new Product("Bamaba", "Bamba", osem);
        Product ramen = new Product("Ramen", "Ramen", eastwest);
        //User dani = new User("Dani","Dani123",UserState.Active,c1);

        LinkedList<User> users;
        LinkedList<Order> orders;
        LinkedList<Product> products;
        LinkedList<Supplier> suppliers;
        User logged_user = null;


    }

    public User find_user(String user_id, LinkedList<User> users) {
        for (User u : users
        ) {
            if (u.getLogin_id() == user_id)
                return u;
        }
        return null;
    }

    public Order find_order(String order_id, LinkedList<Order> orders) {
        for (Order o : orders
        ) {
            if (o.getOrderId() == order_id)
                return o;
        }
        return null;
    }

    public Product find_Product(String product_name, LinkedList<Product> products) {
        for (Product p : products
        ) {
            if (p.getName() == product_name)
                return p;
        }
        return null;
    }

    public Supplier find_Supp(String supp_name, LinkedList<Supplier> suppliers) {
        for (Supplier s : suppliers
        ) {
            if (s.getName().equals(supp_name))
                return s;
        }
        return null;
    }



    public boolean Add_user(String login_id, LinkedList<User> users) {

        User u = find_user(login_id, users);
        if (u != null)
            return false;

        users.add(u);
        return true;

        //TODO: ADD USER, GET DATA FROM USER
            /*User new_u = new User()
            users.add(new User())*/

    }

    public boolean Remove_user(String login_id, LinkedList<User> users) {

        User u = find_user(login_id, users);
        if (u != null) {
            users.remove(u);
            u.remove_user();
            return true;
        }
        return false;
    }

    public boolean Login_user(String login_id, String password, LinkedList<User> users, User logged_user) {
        if (logged_user != null) {
            User u = find_user(login_id, users);
            if (u != null) {
                if (u.getPassword() == password) {
                    logged_user = u;
                    return true;
                }
            }
        }
        return false;
    }

    public boolean Logout_user(String login_id, LinkedList<User> users, User logged_user) {
        if (logged_user.getLogin_id() == login_id) {
            logged_user = null;
            return true;
        }

        return false;
    }

    public boolean Create_new_order(String address, Account account, LinkedList<Order> orders) {
        //DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd");
        LocalDate localDate = LocalDate.now();
        Order order = new Order(new Date(localDate.toString()), new Address(address), OrderStatus.New, account);
        orders.add(order);
        System.out.println(order.getNumber()); //todo: order printing
        return true;
    }

    public boolean Add_product_to_order(String order_id, String login_id, String product_name, LinkedList<Order> orders, LinkedList<Product> products) {
        Order o = find_order(order_id, orders);
        if (o == null)
            return false;
        Product p = find_Product(product_name, products);
        if (p == null)
            return false;
        //todo: fill the function
        //o.addLineItem();

        return true;
    }

    public void Display_order(User logged_user) {
        LinkedList<Order> user_orders= logged_user.getCustomer().getAccount().getOrders();
        Order order = user_orders.getLast();
        order.display();

    }

    public boolean Link_product(String product_name, double price, int quantity, User logged_user,LinkedList<Product> all_products) {
        if(logged_user==null)
            return false;
        Product product_to_add = find_Product(product_name,all_products);
        Account a = logged_user.getCustomer().getAccount();
        if(a instanceof PremiumAccount) {
            LinkedList<Product> account_products;
            account_products = ((PremiumAccount) a).getProducts();
            if(find_Product(product_name,account_products) == null){
                account_products.add(product_to_add);
                return true;
            }
        }
        return false;
    }

    public boolean Add_product(String product_name, String supp_name,LinkedList<Product> all_products,LinkedList<Supplier> all_suppplier) {
        Supplier s = find_Supp(supp_name,all_suppplier);
        if(s==null)
            return false;
        Product new_pord = new Product("0",product_name,s);
        all_products.add(new_pord);
        return true;
    }

    public boolean Delete_product(String product_name,LinkedList<Product> all_products) {
        Product product_to_delete = find_Product(product_name,all_products);
        if(product_to_delete==null)
            return false;
        product_to_delete.remove();
        all_products.remove(product_to_delete);
        return true;
    }

    public void Show_all_object() {

    }

    public void Show_object_id(String id) {

    }

}
