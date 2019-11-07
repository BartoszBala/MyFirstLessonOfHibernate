package CustomerModel;

public class Adress {

    private String country;
    private String city;
    private String postCode;
    private String street;
    private String numberOfResidence;
    private String numberOfAppartment;


    public Adress(String country, String city, String postCode, String street, String numberOfResidence, String numberOfAppartment) {
        this.country = country;
        this.city = city;
        this.postCode = postCode;
        this.street = street;
        this.numberOfResidence = numberOfResidence;
        this.numberOfAppartment = numberOfAppartment;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getPostCode() {
        return postCode;
    }

    public String getStreet() {
        return street;
    }

    public String getNumberOfResidence() {
        return numberOfResidence;
    }

    public String getNumberOfAppartment() {
        return numberOfAppartment;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setNumberOfResidence(String numberOfResidence) {
        this.numberOfResidence = numberOfResidence;
    }

    public void setNumberOfAppartment(String numberOfAppartment) {
        this.numberOfAppartment = numberOfAppartment;
    }

    @Override
    public String toString() {
        return "Adress{" +
                "country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", postCode='" + postCode + '\'' +
                ", street='" + street + '\'' +
                ", numberOfResidence='" + numberOfResidence + '\'' +
                ", numberOfAppartment='" + numberOfAppartment + '\'' +
                '}';
    }
}
