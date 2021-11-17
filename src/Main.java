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

    static User Remove_user(String login_id, LinkedList<User> users, HashMap<Integer, Object> allobj, LinkedList<Order> allOrders) {
        User u = find_user(login_id, users);
        if (u != null) {
            users.remove(u);
            Account accountremove = u.getCustomer().getAccount();
            Customer customerremove = u.getCustomer();
            ShoppingCart shoptoremove = u.getShoppingCart();
            for (Payment p : accountremove.getPayments()
            ) {
                allobj.remove(p.hashCode());
            }
            for (Order o : accountremove.getOrders()
            ) {
                allobj.remove(o.hashCode());
                allOrders.remove(o);
            }

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

    public static boolean Link_product(Product product, int price, int quantity, User logged_user, LinkedList<Product> all_products) {

        Account premium_account = logged_user.getCustomer().getAccount();
        LinkedList<Product> account_products = ((PremiumAccount) premium_account).getProducts();

        if (find_Product(product.getName(), account_products) == null) {
            product.setPrice(price);
            ((PremiumAccount) premium_account).addProcudt(product, quantity);
            return true;
        }

        return false;
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
        bamba.setPrice(9);
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
        accDana.addProcudt(bamba, 1);

        System.out.print("Welcome!");
        boolean flag = true;
        while (flag) {
            String menu = "\nChoose your next action:\n1.Add user\n2.Remove user\n3.login user\n4.Logout user\n5.Create new order\n6.Add product to order\n7.Display order\n8.Link product\n9.Add product\n10.Delete product\n11.Show all objects\n12.Show object Id\n13.Exit!";
            System.out.println(menu);
            Scanner scan = new Scanner(System.in);
            String number = scan.next();
            switch (number) {
                case "1"://add user
                    System.out.println("Enter login id");
                    scan = new Scanner(System.in);
                    login_id = scan.next();

                    System.out.println("Enter password");
                    scan = new Scanner(System.in);
                    password = scan.next();

                    System.out.println("Enter your address");
                    scan = new Scanner(System.in);
                    address = scan.next();

                    System.out.println("Enter your phone");
                    scan = new Scanner(System.in);
                    phone = scan.next();

                    System.out.println("Enter your email");
                    scan = new Scanner(System.in);
                    email = scan.next();

                    System.out.println("Enter your billing address");
                    scan = new Scanner(System.in);
                    billing_address = scan.next();

                    System.out.println("Do you want to be a Premium Account? (y/n)");
                    scan = new Scanner(System.in);
                    String ans = scan.next();
                    LocalDate localDate = LocalDate.now();
                    String current_date = localDate.toString();

                    if (ans.equals("y")) {
                        account = new PremiumAccount(login_id, billing_address, false, new Date(current_date), new Date("none"), 0, null, null);
                    } else if (ans.equals("n")) {
                        account = new Account(login_id, billing_address, false, new Date(current_date), new Date("none"), 0, null, null);
                    } else
                        break;
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
                    System.out.println(login_id + ", your user been created!");
                    break;  //optional

                case "2"://remove user
                    if (logged_user == null) {
                        System.out.println("You must logged in first!");
                        break;
                    }
                    System.out.println("Enter login id you want to remove");
                    scan = new Scanner(System.in);
                    login_id = scan.next();
                    User user2 = Remove_user(login_id, users, allObj, orders);

                    if (user2 != null) {
                        System.out.println(login_id + " been removed");
                        allObj.remove(user2.hashCode());
                        if (logged_user.getLogin_id().equals(login_id))
                            logged_user = null;
                    } else
                        System.out.println("no delete");
                    break;

                case "3":
                    if (logged_user != null) {
                        System.out.println("There is already logged in user, try again later.");
                        break;
                    }

                    System.out.println("Enter your login id:");
                    scan = new Scanner(System.in);
                    login_id = scan.next();
                    User want_login = find_user(login_id, users);
                    if (want_login != null) {
                        System.out.println("Enter your password:");
                        scan = new Scanner(System.in);
                        password = scan.next();
                        if (Login_user(login_id, password, users, logged_user)) {
                            System.out.println("You are now logged in!");
                            logged_user = want_login;
                            break;
                        } else
                            System.out.println("Wrong password");
                        break;
                    }
                    System.out.println("Wrong login id");
                    //login user
                    break;

                case "4"://Logout user
                    if (logged_user == null) {
                        System.out.println("You are not logged in!");
                        break;
                    }
                    System.out.println("Enter your login id:");
                    scan = new Scanner(System.in);
                    login_id = scan.next();
                    //User want_logout = find_user(login_id, users);
                    logged_user = Logout_user(login_id, users, logged_user);
                    if (logged_user == null) {
                        System.out.println("You are now logged out!");
                        break;
                    } else
                        System.out.println("You can't log out!");
                    break;

                case "5"://Create new order
                    if (logged_user == null) {
                        System.out.println("You are not logged in!");
                        break;
                    }
                    Account to_order = logged_user.getCustomer().getAccount();
                    System.out.println("Enter your address");
                    scan = new Scanner(System.in);
                    address = scan.next();

                    //Create new order
                    Order new_order = Create_new_order(address, to_order, orders);
                    allObj.put(new_order.hashCode(), new_order);
                    break;

                case "6": //Add product to order
                    if (logged_user == null) {
                        System.out.println("You are not logged in!");
                        break;
                    }
                    System.out.println("Enter the Order id");
                    scan = new Scanner(System.in);
                    String order_id = scan.next();
                    Order order_to_addproduct = find_order(order_id, orders);
                    if (order_to_addproduct == null) {
                        System.out.println("Could not found order number: " + order_id);
                        break;
                    }

                    System.out.println("Enter login id to buy from");
                    scan = new Scanner(System.in);
                    login_id = scan.next();

                    System.out.println("Enter the product name you want to buy");
                    scan = new Scanner(System.in);
                    product_name = scan.next();
                    product_to_order = find_Product(product_name, products);
                    if (product_to_order == null) {
                        System.out.println("One or more of your inputs are wrong");
                        break;
                    }

                    //String idToBuyFrom = "";
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
                                System.out.println("This product doesn't belong to this account");
                                break;
                            }
                            //idToBuyFrom = product_to_order.getPremiumAccount().getID();
                        }
                    }
                    if (f1) {
                        PremiumAccount premium = (PremiumAccount) u.getCustomer().getAccount();
                        if (!premium.has_enough(product_name, 1)) {
                            System.out.println("Product out of stock");
                            break;
                        }
                        premium.decressProd(product_name);
                        LineItem line_to_add = new LineItem(1, product_to_order.getPrice(), product_to_order, logged_user.getShoppingCart(), order_to_addproduct);
                        order_to_addproduct.addLineItem(line_to_add);
                        System.out.println("Product added to order!");
                        allObj.put(line_to_add.hashCode(), line_to_add);
                        localDate = LocalDate.now();
                        current_date = localDate.toString();
                        Date d = new Date(current_date);
                        DelayPayment pay = new DelayPayment(logged_user.getLogin_id(), d, order_to_addproduct.getTotal(), "", logged_user.getCustomer().getAccount(), order_to_addproduct, d);
                        allObj.put(pay.hashCode(), pay);
                    } else {
                        System.out.println("One or more of your inputs are wrong");
                    }
                    break;

                case "7"://Display order
                    if (logged_user == null) {
                        System.out.println("You are not logged in!");
                        break;
                    }
                    last_order = logged_user.getCustomer().getAccount().getLastOrder();
                    if (last_order != null) {
                        last_order.display();
                    } else {
                        System.out.println("No orders yet!");
                    }
                    break;

                case "8": //Link Product *Product_Name* *Price* *Quantity*
                    //Link product
                    if (logged_user == null) {
                        System.out.println("You are not logged in!");
                        break;
                    }
                    if (!logged_user.getCustomer().getAccount().isPremium()) {
                        System.out.println("You are not premium account!");
                        break;
                    }
                    System.out.println("Enter the product name");
                    scan = new Scanner(System.in);
                    product_name = scan.next();
                    productLink = find_Product(product_name, products);
                    if (productLink == null) {
                        System.out.println("could not found " + product_name);
                        break;
                    }
                    System.out.println("Enter the price");
                    scan = new Scanner(System.in);
                    int price = scan.nextInt();

                    System.out.println("Enter the quantity");
                    scan = new Scanner(System.in);
                    int quantity = scan.nextInt();
                    if (productLink.getPremiumAccount() == null) {
                        System.out.println("This product already linked to other premium account!");
                        break;
                    }
                    if (Link_product(productLink, price, quantity, logged_user, products))
                        System.out.println("Successfully link the product!");
                    else {
                        System.out.println("Could not link the product");
                        break;
                    }
                    break;

                case "9": //Add Product *Product_Name* *Supplier_Name*
                    System.out.println("Enter the product name");
                    scan = new Scanner(System.in);
                    product_name = scan.next();
                    product_to_order = find_Product(product_name, products);
                    if (product_to_order != null) {
                        System.out.println("Already exist " + product_name);
                        break;
                    }
                    System.out.println("Enter the supplier name");
                    scan = new Scanner(System.in);
                    supplier_name = scan.next();
                    supplier = find_Supplier(supplier_name, suppliers);
                    if (supplier == null) {
                        System.out.println("There is no supplier by this name");
                        break;
                    }
                    product_to_order = new Product(product_name, product_name, supplier);
                    allObj.put(product_to_order.hashCode(), product_to_order);
                    supplier.addProduct(product_to_order);
                    products.add(product_to_order);
                    System.out.println("New product added");
                    break;

                case "10": //Delete product
                    System.out.println("Enter the product name");
                    scan = new Scanner(System.in);
                    product_name = scan.next();
                    product_to_order = find_Product(product_name, products);
                    if (product_to_order == null) {
                        System.out.println("Could not found the product: " + product_name);
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
                    System.out.println("Product been deleted");
                    break;

                case "11": //Show all objects
                    Show_all_object(users, orders, products, suppliers);
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
            }
        }
    }
}
