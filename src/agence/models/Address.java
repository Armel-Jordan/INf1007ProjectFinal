package agence.models;

public class Address {
    private String numeroMunicipal;
    private String street;
    private String city;
    private String zip;

    public Address(String numeroMunicipal, String street, String city, String zip) {
        this.numeroMunicipal = numeroMunicipal;
        this.street = street;
        this.city = city;
        this.zip = zip;
    }

    public String getNumeroMunicipal() {
        return numeroMunicipal;
    }

    public void setNumeroMunicipal(String numeroMunicipal) {
        this.numeroMunicipal = numeroMunicipal;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    @Override
    public String toString() {
        return "Address{" +
                "numeroMunicipal='" + numeroMunicipal + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", zip='" + zip + '\'' +
                '}';
    }
}
