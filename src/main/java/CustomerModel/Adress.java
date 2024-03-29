package CustomerModel;
import javax.persistence.*;
import java.beans.IntrospectionException;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="client_address")
public class Adress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="address_id")
    private int id;
    @Column(name="country")
    private String country;
    @Column(name="city")
    private String city;
    @Column(name="postCode")
    private String postCode;
    @Column(name="street")
    private String street;
    @Column(name="number_Residence")
    private String numberOfResidence;
    @Column(name="number_Apartment")
    private String numberOfAppartment;
    @OneToMany
    @JoinColumn(name = "address_id")
    private Set<Client> clients;

    public Adress() {
    }

    public Adress(String country, String city, String postCode, String street, String numberOfResidence, String numberOfAppartment) {
        this.country = country;
        this.city = city;
        this.postCode = postCode;
        this.street = street;
        this.numberOfResidence = numberOfResidence;
        this.numberOfAppartment = numberOfAppartment;

        Set<Integer> secik = new HashSet<>();


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

    public Set<Client> getClients() {
        return clients;
    }

    public void setClients(Set<Client> clients) {
        this.clients = clients;
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
