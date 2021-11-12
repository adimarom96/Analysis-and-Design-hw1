public class Date {
    private String date;

    public Date(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return date;
    }
}

enum UserState {
    New,
    Active,
    Blocked,
    Banned
}
enum OrderStatus{
    New,
    Hold,
    Shipped,
    Delivered,
    Closed
}