package data;

public class MailAddress {
    private final String address;
    public MailAddress(String address) {
        this.address = address;
    }
    public String getAddress() {
        return address;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MailAddress mailAddress = (MailAddress) o;
        return address.equals(mailAddress.address);
    }
    @Override
    public int hashCode() {
        return address.hashCode();
    }
    @Override
    public String toString() {
        return "Address{" + "address='" + address + '\'' + '}';
    }
}
