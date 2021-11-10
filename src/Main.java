public class Main {
    public static void main(String[] args) {
        Account a1 = new Account();
        Account a2 = new Account();
        Customer c1 = new Customer("id1", "phone1", "email1", new Address("address1"), a1);
        Customer c2 = new Customer("id2", "phone2", "email2", new Address("address2"), a2);
        User u1 = new User("id", "password", UserState.Active, c1);
        //System.out.println(c1.addUser(u1));


    }

    public boolean Add_user(String Login_id) {

        return false;
    }

    public boolean Remove_user(String Login_id) {
        return false;
    }

    public boolean Login_user(String login_id, String password) {
        return false;
    }

    public boolean Logout_user(String login_id) {
        return false;
    }

    public boolean Create_new_order(String address) {
        return false;
    }

    public boolean Add_product_to_order(String order_id, String login_id, String product_name) {
        return false;
    }
    public void Display_order(){
    }
    public boolean Link_product(String product_name,double price, int quantity){
        return false;
    }
    public boolean Add_product(String product_name,String supp_name){
        return false;
    }
    public boolean Delete_product(String product_name){
        return false;
    }
    public void Show_all_object(){

    }
    public void Show_object_id(String id){

    }

}
