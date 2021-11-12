import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static User find_user(String user_id, LinkedList<User> users) {
        for (User u : users
        ) {
            if (u.getLogin_id().equals(user_id))
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

    static boolean Remove_user(String login_id, LinkedList<User> users) {

        User u = find_user(login_id, users);
        if (u != null) {
            users.remove(u);
            u.remove_user();
            return true;
        }
        return false;
    }

    public static boolean Login_user(String login_id, String password, LinkedList<User> users, User logged_user) {
        if (logged_user == null) {
            User u = find_user(login_id, users);
            if (u != null) {
                if (u.getPassword().equals(password)) {
                    logged_user = u;
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean Logout_user(String login_id, LinkedList<User> users, User logged_user) {
        if (logged_user.getLogin_id() == login_id) {
            logged_user = null;
            return true;
        }

        return false;
    }

    public static boolean Create_new_order(String address, Account account, LinkedList<Order> orders) {
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
        LinkedList<Order> user_orders = logged_user.getCustomer().getAccount().getOrders();
        Order order = user_orders.getLast();
        order.display();

    }

    public boolean Link_product(String product_name, double price, int quantity, User logged_user, LinkedList<Product> all_products) {
        if (logged_user == null)
            return false;
        Product product_to_add = find_Product(product_name, all_products);
        Account a = logged_user.getCustomer().getAccount();
        if (a instanceof PremiumAccount) {
            LinkedList<Product> account_products;
            account_products = ((PremiumAccount) a).getProducts();
            if (find_Product(product_name, account_products) == null) {
                account_products.add(product_to_add);
                return true;
            }
        }
        return false;
    }

    public boolean Add_product(String product_name, String supp_name, LinkedList<Product> all_products, LinkedList<Supplier> all_suppplier) {
        Supplier s = find_Supp(supp_name, all_suppplier);
        if (s == null)
            return false;
        Product new_pord = new Product("0", product_name, s);
        all_products.add(new_pord);
        return true;
    }

    public boolean Delete_product(String product_name, LinkedList<Product> all_products) {
        Product product_to_delete = find_Product(product_name, all_products);
        if (product_to_delete == null)
            return false;
        product_to_delete.remove();
        all_products.remove(product_to_delete);
        return true;
    }

    public static void Show_all_object(LinkedList<User> users) {
        for (User u:users
             ) {
            u.toPrint();

        }

    }

    public void Show_object_id(String id) {

    }

    public static void main(String[] args) {



        LinkedList<User> users = new LinkedList<User>();
        LinkedList<Order> orders = new LinkedList<Order>();
        LinkedList<Product> products= new LinkedList<Product>();
        LinkedList<Supplier> suppliers= new LinkedList<Supplier>();
        User logged_user = null;

        String login_id, password, address, phone, email, billing_address;
        Account account = null;
        Customer customer = null;
        ShoppingCart shoppingCart = null;
        User user = null;

        Supplier osem = new Supplier("Osem", "Osem");
        Supplier eastwest = new Supplier("EastWest", "EastWest");
        Product bamba = new Product("Bamaba", "Bamba", osem);
        Product ramen = new Product("Ramen", "Ramen", eastwest);

        LocalDate local = LocalDate.now();
        String currentD = local.toString();
        User dani = new User("Dani","Dani123",UserState.Active,null);
        Customer cDani = new Customer("Dani","0541234567","D@walla.com",new Address("Rager"),null,dani);
        Account accDani = new Account("Dani","Rager", false,new Date(currentD),new Date("none"),0,cDani,null);
        ShoppingCart SCDani = new ShoppingCart(new Date(currentD),dani,accDani);
        dani.addCustomer(cDani);
        cDani.setAccount(accDani);
        accDani.setShoppingCart(SCDani);
        users.add(dani);

        User dana = new User("Dana","Dana123",UserState.Active,null);
        Customer cDana = new Customer("Dana","0541234567","Da@walla.com",new Address("Rager1"),null,dana);
        PremiumAccount accDana = new PremiumAccount("Dana","Rager1", false,new Date(currentD),new Date("none"),0,cDana,null);
        ShoppingCart SCDana = new ShoppingCart(new Date(currentD),dana,accDana);
        dana.addCustomer(cDana);
        cDana.setAccount(accDana);
        accDana.setShoppingCart(SCDana);
        users.add(dana);

        accDana.addProcudt(bamba);

        //dana.toString();
        //dani.toString();

        System.out.println("Welcome");
        boolean flag = true;
        while (flag) {
            String menu = "Choose your next action:\n1.Add user\n2.Remove user\n3.login user\n4.Logout user\n5.Create new order\n6.Add product to order\n7.Display order\n8.Link product\n9.Add product\n10.Delete product\n11.Show all objects\n12.Show object Id\n13.Exit!";
            System.out.println(menu);
            Scanner scan = new Scanner(System.in);
            int number = scan.nextInt();
            switch (number) {
                case 1:
                    System.out.println("enter login id");
                    scan = new Scanner(System.in);
                    login_id = scan.next();

                    System.out.println("enter password");
                    scan = new Scanner(System.in);
                    password = scan.next();

                    System.out.println("enter your address");
                    scan = new Scanner(System.in);
                    address = scan.next();

                    System.out.println("enter your phone");
                    scan = new Scanner(System.in);
                    phone = scan.next();

                    System.out.println("enter your email");
                    scan = new Scanner(System.in);
                    email = scan.next();

                    System.out.println("enter your billing address");
                    scan = new Scanner(System.in);
                    billing_address = scan.next();

                    System.out.println("do you want to be preimum account? (y/n)");
                    scan = new Scanner(System.in);
                    String ans = scan.next();
                    LocalDate localDate = LocalDate.now();
                    String current_date = localDate.toString();

                    if (ans == "y") {
                        //account = new PremiumAccount();
                    } else {
                        account = new Account(login_id, billing_address, false, new Date(current_date), new Date("none"), 0, null, null);
                    }
                    //todo: take care of preimum accuont

                    customer = new Customer(login_id, phone, email, new Address(address), account, null);
                    user = new User(login_id, password, UserState.New, customer);
                    shoppingCart = new ShoppingCart(new Date(current_date), user, account);
                    user.setShoppingCart(shoppingCart);
                    account.setShoppingCart(shoppingCart);
                    account.setCustomer(customer);
                    customer.setUser(user);

                    users.add(user);
                    System.out.println(login_id + " your user been created!");
                    //add user
                    break;  //optional
                case 2:
                    System.out.println("enter login id");
                    scan = new Scanner(System.in);
                    login_id = scan.next();
                    if(Remove_user(login_id,users))
                        System.out.println(login_id + " been removed");
                    else
                        System.out.println("no delete");

                    //remove user
                    break;
                case 3:
                    if(logged_user != null) {
                        System.out.println("there is alredy logged in user, try again later.");
                        break;
                    }

                    System.out.println("enter your login id:");
                    scan = new Scanner(System.in);
                    login_id = scan.next();
                    User want_login =find_user(login_id,users);
                    if(want_login!=null)
                    {
                    System.out.println("enter your password:");
                    scan = new Scanner(System.in);
                    password = scan.next();
                    if(Login_user(login_id,password,users,logged_user)) {
                        System.out.println("you are now logged in!");
                        logged_user = want_login;
                        break;
                    }
                    else
                        System.out.println("worng password");
                        break;
                    }
                    System.out.println("worng login id");
                    //login user
                    break;
                case 4:
                    if(logged_user==null){
                        System.out.println("your are not logged in!");
                        break;
                    }
                    System.out.println("enter your login id:");
                    scan = new Scanner(System.in);
                    login_id = scan.next();
                    User want_logout =find_user(login_id,users);
                    Logout_user(login_id,users,logged_user);
                    System.out.println("you are now loggedout!");
                    //Logout user
                    break;
                case 5:
                    if(logged_user==null){
                        System.out.println("you must logged in first");
                        break;
                    }
                    Account to_order = logged_user.getCustomer().getAccount();
                    System.out.println("enter your address:");
                    scan = new Scanner(System.in);
                    address = scan.next();

                    Create_new_order(address,to_order,orders);
                    //Create new order
                    break;
                case 6:
                    if(logged_user==null){
                        System.out.println("your are not logged in!");
                        break;
                    }

                    
                    //Add product to order
                    break;
                case 7:
                    //Display order
                    break;
                case 8:
                    //Link product
                    break;
                case 9:
                    //Add product
                    break;
                case 10:
                    //Delete product
                    break;
                case 11:
                    Show_all_object(users);//todo: need to send all the rest of the obejcts
                    //Show all objects
                    break;
                case 12:
                    //Show object
                    break;
                case 13:
                    //Exit!
                    flag = false;
                    break;
            }
        }


    }



}
