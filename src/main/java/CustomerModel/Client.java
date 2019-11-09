package CustomerModel;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
@Entity
@Table(name="Client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_client")
    public  int id;
    @Column(name="pesel")
    private String pesel;
    @Column(name="first_name")
    private String firstName;
    @Column(name="last_name")
    private  String lastName;
    @Column(name="dob")
    private Date dob;
    @Embedded
    private Adress adress;
    @Transient
    private List<Account> accountslist;

    public Client(String pesel, String firstName, String lastName, Date dob, Adress adress) {
        this.id=id;
        this.pesel = pesel;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.adress=adress;

    }

    public Client() {
    }

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

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public void setAdress(Adress adress) {
        this.adress = adress;
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

    public Date getDob() {
        return dob;
    }

    public Adress getAdress() {
        return adress;
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
                ", dob=" + dob +
                ", adress=" + adress +
                ", accountslist=" + accountslist +
                '}';
    }
}
