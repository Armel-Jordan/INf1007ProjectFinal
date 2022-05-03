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

    public Address() {}

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
        return "\n\t\t\t- Numero Municipal : " + numeroMunicipal +
                "\n\t\t\t- street : " + street +
                "\n\t\t\t- city : " + city +
                "\n\t\t\t- zip : " + zip;
    }
}
