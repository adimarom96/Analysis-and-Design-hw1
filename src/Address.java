public class Address {
    private String address;

    public Address(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return address;
    }

    public String getAddressString() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
