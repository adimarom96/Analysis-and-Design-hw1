import java.time.LocalDate;
import java.util.HashMap;
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

    public static Order find_order(String order_id, LinkedList<Order> orders) {
        for (Order o : orders
        ) {
            if (o.getOrderId().equals(order_id))
                return o;
        }
        return null;
    }

    public static Product find_Product(String product_name, LinkedList<Product> products) {
        for (Product p : products
        ) {
            if (p.getName().equals(product_name))
                return p;
        }
        return null;
    }

    public static Supplier find_Supplier(String supplier_name, LinkedList<Supplier> sups) {
        for (Supplier p : sups
        ) {
            if (p.getName().equals(supplier_name))
                return p;
        }
        return null;
    }

    static User Remove_user(String login_id, LinkedList<User> users, HashMap<Integer, Object> allobj) {
        User u = find_user(login_id, users);
        if (u != null) {
            users.remove(u);
            Account accountremove = u.getCustomer().getAccount();
            Customer customerremove = u.getCustomer();
            ShoppingCart shoptoremove = u.getShoppingCart();

            for (LineItem l : shoptoremove.getLines() // remove the lines of the shopping cart from the hash
            ) {
                allobj.remove(l.hashCode());
            }
            shoptoremove.removelines();
            allobj.remove(shoptoremove.hashCode());
            allobj.remove(accountremove.hashCode());
            allobj.remove(customerremove.hashCode());

            return u;
        }
        return null;
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

    public static User Logout_user(String login_id, LinkedList<User> users, User logged_user) {
        if (logged_user.getLogin_id().equals(login_id)) {
            return null;
        }
        return logged_user;
    }

    public static Order Create_new_order(String address, Account account, LinkedList<Order> orders) {
        //DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd");
        LocalDate localDate = LocalDate.now();
        Order order = new Order(new Date(localDate.toString()), new Address(address), OrderStatus.New, account);

        orders.add(order);
        //System.out.println("Order number: " + order.getNumber());
        order.display();
        return order;
    }

    public static void Show_all_object(LinkedList<User> users, LinkedList<Order> orders, LinkedList<Product> products, LinkedList<Supplier> suppliers) {
        for (User u : users
        ) {
            u.toPrint();
        }

        for (Order u : orders
        ) {
            u.toPrint();
        }

        for (Product u : products
        ) {
            u.toPrint();
        }

        for (Supplier u : suppliers
        ) {
            u.toPrint();
        }
    }

    public static void main(String[] args) {

        HashMap<Integer, Object> allObj = new HashMap<Integer, Object>();
        LinkedList<User> users = new LinkedList<User>();
        LinkedList<Order> orders = new LinkedList<Order>();
        LinkedList<Product> products = new LinkedList<Product>();
        LinkedList<Supplier> suppliers = new LinkedList<Supplier>();
        User logged_user = null;

        String login_id, password, address, phone, email, billing_address, product_name, supplier_name;
        Account account = null;
        Customer customer = null;
        ShoppingCart shoppingCart = null;
        User user = null;
        Order last_order = null;
        Supplier supplier = null;
        Product product_to_order = null;
        Product productLink = null;

        Supplier osem = new Supplier("Osem", "Osem");
        Supplier eastwest = new Supplier("EastWest", "EastWest");
        Product bamba = new Product("Bamaba", "Bamba", osem);
        Product ramen = new Product("Ramen", "Ramen", eastwest);
        osem.addProduct(bamba);
        allObj.put(osem.hashCode(), osem);
        allObj.put(eastwest.hashCode(), eastwest);
        allObj.put(bamba.hashCode(), bamba);
        allObj.put(ramen.hashCode(), ramen);
        eastwest.addProduct(ramen);
        products.add(bamba);
        products.add(ramen);
        suppliers.add(osem);
        suppliers.add(eastwest);

        LocalDate local = LocalDate.now();
        String currentD = local.toString();
        User dani = new User("Dani", "Dani123", UserState.Active, null);
        Customer cDani = new Customer("Dani", "0541234567", "D@walla.com", new Address("Rager"), null, dani);
        Account accDani = new Account("Dani", "Rager", false, new Date(currentD), new Date("none"), 0, cDani, null);
        ShoppingCart SCDani = new ShoppingCart(new Date(currentD), dani, accDani);
        allObj.put(dani.hashCode(), dani);
        allObj.put(cDani.hashCode(), cDani);
        allObj.put(accDani.hashCode(), accDani);
        allObj.put(SCDani.hashCode(), SCDani);
        dani.addCustomer(cDani);
        cDani.setAccount(accDani);
        dani.setShoppingCart(SCDani);
        accDani.setShoppingCart(SCDani);
        users.add(dani);

        User dana = new User("Dana", "Dana123", UserState.Active, null);
        Customer cDana = new Customer("Dana", "0541234567", "Da@walla.com", new Address("Rager1"), null, dana);
        PremiumAccount accDana = new PremiumAccount("Dana", "Rager1", false, new Date(currentD), new Date("none"), 0, cDana, null);
        ShoppingCart SCDana = new ShoppingCart(new Date(currentD), dana, accDana);
        allObj.put(dana.hashCode(), dana);
        allObj.put(cDana.hashCode(), cDana);
        allObj.put(accDana.hashCode(), accDana);
        allObj.put(SCDana.hashCode(), SCDana);
        dana.addCustomer(cDana);
        cDana.setAccount(accDana);
        dana.setShoppingCart(SCDana);
        accDana.setShoppingCart(SCDana);
        users.add(dana);
        accDana.addProcudt(bamba);


        //dana.toString();
        //dani.toString();
        logged_user = dani;
        System.out.println("Welcome");
        boolean flag = true;
        while (flag) {
            String menu = "\nChoose your next action:\n1.Add user\n2.Remove user\n3.login user\n4.Logout user\n5.Create new order\n6.Add product to order\n7.Display order\n8.Link product\n9.Add product\n10.Delete product\n11.Show all objects\n12.Show object Id\n13.Exit!";
            System.out.println(menu);
            Scanner scan = new Scanner(System.in);
            String number = scan.next();
            switch (number) {
                case "1"://add user
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

                    System.out.println("do you want to be premium account? (y/n)");
                    scan = new Scanner(System.in);
                    String ans = scan.next();
                    LocalDate localDate = LocalDate.now();
                    String current_date = localDate.toString();

                    if (ans.equals("y")) {
                        account = new PremiumAccount(login_id, billing_address, false, new Date(current_date), new Date("none"), 0, null, null);
                    } else if (ans.equals("n")) {
                        account = new Account(login_id, billing_address, false, new Date(current_date), new Date("none"), 0, null, null);
                    } else
                        break;// todo: what if != y/n ???
                    customer = new Customer(login_id, phone, email, new Address(address), account, null);
                    user = new User(login_id, password, UserState.New, customer);
                    shoppingCart = new ShoppingCart(new Date(current_date), user, account);
                    user.setShoppingCart(shoppingCart);
                    account.setShoppingCart(shoppingCart);
                    account.setCustomer(customer);
                    customer.setUser(user);
                    allObj.put(account.hashCode(), account);
                    allObj.put(customer.hashCode(), customer);
                    allObj.put(user.hashCode(), user);
                    allObj.put(shoppingCart.hashCode(), shoppingCart);
                    users.add(user);
                    System.out.println(login_id + " your user been created!");
                    break;  //optional

                case "2"://remove user
                    if (logged_user == null) {
                        System.out.println("you must logged in first");
                        break;
                    }
                    // todo: can remove others?
                    System.out.println("enter login id you want to remove");
                    scan = new Scanner(System.in);
                    login_id = scan.next();
                    User user2 = Remove_user(login_id, users, allObj);
                    if (user2 != null) {
                        System.out.println(login_id + " been removed");
                        allObj.remove(user2.hashCode());
                        //todo: remove from hash: shoopingcart, account ..etc

                        if (logged_user.getLogin_id().equals(login_id))
                            logged_user = null;
                    } else
                        System.out.println("no delete");
                    break;

                case "3":
                    if (logged_user != null) {
                        System.out.println("there is already logged in user, try again later.");
                        break;
                    }

                    System.out.println("enter your login id:");
                    scan = new Scanner(System.in);
                    login_id = scan.next();
                    User want_login = find_user(login_id, users);
                    if (want_login != null) {
                        System.out.println("enter your password:");
                        scan = new Scanner(System.in);
                        password = scan.next();
                        if (Login_user(login_id, password, users, logged_user)) {
                            System.out.println("you are now logged in!");
                            logged_user = want_login;
                            break;
                        } else
                            System.out.println("wrong password");
                        break;
                    }
                    System.out.println("wrong login id");
                    //login user
                    break;

                case "4":
                    if (logged_user == null) {
                        System.out.println("your are not logged in!");
                        break;
                    }
                    System.out.println("enter your login id:");
                    scan = new Scanner(System.in);
                    login_id = scan.next();
                    User want_logout = find_user(login_id, users);
                    logged_user = Logout_user(login_id, users, logged_user);
                    if (logged_user == null) {
                        System.out.println("you are now logged out!");
                        break;
                    } else
                        System.out.println("you can't log out!");
                    //Logout user
                    break;

                case "5"://Create new order
                    if (logged_user == null) {
                        System.out.println("you are not logged in!");
                        break;
                    }
                    Account to_order = logged_user.getCustomer().getAccount();
                    System.out.println("enter your address:");
                    scan = new Scanner(System.in);
                    address = scan.next();

                    //Create new order
                    Order new_order = Create_new_order(address, to_order, orders);
                    allObj.put(new_order.hashCode(), new_order);
                    break;

                case "6": //Add product to order
                    if (logged_user == null) {
                        System.out.println("you are not logged in!");
                        break;
                    }

                    System.out.println("enter the product name you want to buy");
                    scan = new Scanner(System.in);
                    product_name = scan.next();
                    product_to_order = find_Product(product_name, products);
                    if (product_to_order == null) {
                        System.out.println("could not found " + product_name);
                        break;
                    }

                    System.out.println("enter login id to buy from");
                    //todo - the id is the id of the user the product belongs to !
                    scan = new Scanner(System.in);
                    login_id = scan.next();
//                    if (!logged_user.getLogin_id().equals(login_id)) {
//                        System.out.println("wrong login id");
//                        break;
//                    }
                    //todo: new ohad..
                    String idToBuyFrom = "";
                    boolean f1 = false;
                    User u = find_user(login_id, users);
                    LinkedList<Product> prods = null;
                    if (u != null) {
                        if (u.getCustomer().getAccount().isPremium()) {
                            prods = ((PremiumAccount) (u.getCustomer().getAccount())).getProducts();
                            for (Product p : prods
                            ) {
                                if (p == product_to_order) {
                                    f1 = true;
                                    break;
                                }
                            }
                            if (!f1) {
                                System.out.println("This product does not belong to this account");
                                break;
                            }
                            idToBuyFrom = product_to_order.getPremiumAccount().getID();
                        }
                    }
                    if (f1) {
                        if (!idToBuyFrom.equals(product_to_order.getPremiumAccount().getID())) {
                            System.out.println("wrong id");
                            break;
                        }


                        System.out.println("enter the Order id");
                        scan = new Scanner(System.in);
                        String order_id = scan.next();
                        Order order_to_addproduct = find_order(order_id, orders);

                        if (order_to_addproduct == null) {
                            System.out.println("could not found " + order_id);
                            break;
                        }

                        LineItem line_to_add = new LineItem(1, 1, product_to_order, logged_user.getShoppingCart(), order_to_addproduct);
                        order_to_addproduct.addLineItem(line_to_add);
                        System.out.println("product added to order!");
                        allObj.put(line_to_add.hashCode(), line_to_add);
                        localDate = LocalDate.now();
                        current_date = localDate.toString();
                        Date d = new Date(current_date);
                        DelayPayment pay = new DelayPayment(logged_user.getLogin_id(),d,order_to_addproduct.getTotal(),"",logged_user.getCustomer().getAccount(),order_to_addproduct,d);
                        allObj.put(pay.hashCode(), pay);
                        //System.out.println(line_to_add.hashCode());
                    /*if (Create_new_order(logged_user.getCustomer().getAddress().getAddressString(), logged_user.getCustomer().getAccount(), orders)) {
                        System.out.println("product added to order!");
                    }*/
                    } else {
                        System.out.println("can't make that action..");
                    }
                    break;

                case "7"://Display order
                    if (logged_user == null) {
                        System.out.println("your are not logged in!");
                        break;
                    }
                    last_order = logged_user.getCustomer().getAccount().getLastOrder();
                    if (last_order != null) {
                        last_order.display();
                    } else {
                        System.out.println("no orders yet!");
                    }
                    break;

                case "8": //Link Product *Product_Name* *Price* *Quantity*
                    //Link product
                    if (logged_user == null) {
                        System.out.println("your are not logged in!");
                        break;
                    }
                    if (!logged_user.getCustomer().getAccount().isPremium()) {
                        System.out.println("your are not premium account!");
                        break;
                    }
                    System.out.println("enter the product name ");
                    scan = new Scanner(System.in);
                    product_name = scan.next();
                    productLink = find_Product(product_name, products);
                    if (productLink == null) {
                        System.out.println("could not found " + product_name);
                        break;
                    }
                    if (productLink.getPremiumAccount() == null) {
                        ((PremiumAccount) logged_user.getCustomer().getAccount()).addProcudt(productLink);
                        System.out.println("Successfully link the product!");
                    } else
                        System.out.println("This product already belong to another premium account");
                    break;

                case "9": //Add Product *Product_Name* *Supplier_Name*
                    System.out.println("enter the product name ");
                    scan = new Scanner(System.in);
                    product_name = scan.next();
                    product_to_order = find_Product(product_name, products);
                    if (product_to_order != null) {
                        System.out.println("Already exist " + product_name);
                        break;
                    }
                    System.out.println("enter the supplier name ");
                    scan = new Scanner(System.in);
                    supplier_name = scan.next();
                    supplier = find_Supplier(supplier_name, suppliers);
                    if (supplier == null) {
                        System.out.println("No supplier exists");
                        break;
                    }
                    product_to_order = new Product(product_name, product_name, supplier);
                    allObj.put(product_to_order.hashCode(), product_to_order);
                    supplier.addProduct(product_to_order);
                    products.add(product_to_order);
                    System.out.println("new product added");
                    break;

                case "10":
                    //Delete product
                    System.out.println("enter the product name");
                    scan = new Scanner(System.in);
                    product_name = scan.next();
                    product_to_order = find_Product(product_name, products);
                    if (product_to_order == null) {
                        System.out.println("could not found " + product_name);
                        break;
                    }

                    if (product_to_order.getPremiumAccount() != null)
                        product_to_order.getPremiumAccount().removeProduct(product_to_order);
                    product_to_order.getSupplier().removeProduct(product_to_order);
                    LinkedList<LineItem> lineItems = product_to_order.getLineItems();
                    for (LineItem LI : lineItems
                    ) {
                        LI.delete_lineitem_from_order();
                        LI.delete_lineitem_from_shopcart();
                        allObj.remove(LI.hashCode());
                    }
                    products.remove(product_to_order);
                    allObj.remove(product_to_order.hashCode());
                    System.out.println("product been deleted");
                    break;

                case "11":
                    Show_all_object(users, orders, products, suppliers);//todo: need to send all the rest of the obejcts
                    //Show all objects
                    break;

                case "12"://ShowObjectId *id*
                    Object value = null;
                    boolean flag2 = false;
                    /* Show object details AND all the NAMES of his connections ! */
                    System.out.println("enter the object id you want to see");
                    scan = new Scanner(System.in);
                    int wantedID = scan.nextInt();
                    for (int hashcode : allObj.keySet()) {
                        if (hashcode == wantedID) {
                            value = allObj.get(hashcode);
                            System.out.println(hashcode + " " + value);
                            flag2 = true;
                            break;
                        }
                    }
                    if (!flag2)
                        System.out.println("No object with this id");
                    break;

                case "13":
                    //Exit!
                    flag = false;
                    break;

                default:
                    System.out.println("Please try again");
                    //System.out.println("???????????????");
                    //break;
            }
        }
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
        order.toPrint();
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

    public void Show_object_id(String id) {

    }
}
