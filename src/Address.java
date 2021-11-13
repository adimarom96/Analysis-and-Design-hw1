public class Address {
    private String address;

    public Address(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return address;
    }
    @Override
    public int hashCode() {
        return super.hashCode();
    }
    public String getAddressString() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
