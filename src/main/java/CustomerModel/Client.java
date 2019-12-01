package CustomerModel;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_client")
    public int id;
    @Column(name = "pesel")
    private String pesel;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "dob")
    private Date dateOfBirth;
    @ManyToOne
    @JoinColumn(name = "address_id", nullable = false)
    private Adress address;
//    @Column(name = "address_id")
//    private int address_id;
    @Transient
    private List<Account> accountslist;

    public Client(String pesel, String firstName, String lastName, Date dateOfBirth, Adress address) {
        this.id = id;
        this.pesel = pesel;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.address=address;
//        this.address_id = address_id;

    }

    public Client() {
    }

//    public int getAddress_id() {
//        return address_id;
//    }

//    public void setAddress_id(int address_id) {
//        this.address_id = address_id;
//    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDateOfBirth(Date dob) {
        this.dateOfBirth = dob;
    }

    public void setAdress(Adress adress) {
        this.address = adress;
    }

    public void setAccountslist(List<Account> accountslist) {
        this.accountslist = accountslist;
    }

    public int getId() {

        return id;
    }

    public String getPesel() {
        return pesel;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public Adress getAdress() {
        return address;
    }

    public List<Account> getAccountslist() {
        return accountslist;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", pesel='" + pesel + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dob=" + dateOfBirth +
                ", adress=" + address +
                ", accountslist=" + accountslist +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;
        Client client = (Client) o;
        return id == client.id &&
                Objects.equals(pesel, client.pesel) &&
                Objects.equals(firstName, client.firstName) &&
                Objects.equals(lastName, client.lastName) &&
                Objects.equals(dateOfBirth, client.dateOfBirth) &&
                Objects.equals(address, client.address) &&
                Objects.equals(accountslist, client.accountslist);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pesel, firstName, lastName, dateOfBirth, address, accountslist);
    }


}
