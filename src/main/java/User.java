import org.hibernate.annotations.Generated;



//@Id
//@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQUENCE1")
//@SequenceGenerator(name="SEQUENCE1", sequenceName="SEQUENCE1", allocationSize=1)
import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name="USERS1")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @SequenceGenerator(name="SEQUENCE1",sequenceName = "SEQUENCE1",allocationSize = 1)
    private  int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @Column(name="email")
    private String email;
    @Column(name="nick")
    private String nick;
    @Column(name="password_1")
    private String password;
    @Column(name="DATE_OF_REGISTER")
    private Date dateOfFirstRegister;
@Transient
    private  String repatedPassword;

    public User() {
    }



    public User(String email, String nick, String password, Date date) {
        this.email = email;
        this.nick = nick;
        this.password = password;
        this.dateOfFirstRegister = date;
    }



    public String getEmail() {
        return email;
    }

    public String getNick() {
        return nick;
    }

    public String getPassword() {
        return password;
    }

    public Date getDateOfFirstRegister() {
        return dateOfFirstRegister;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDateOfFirstRegister(Date dateOfFirstRegister) {
        this.dateOfFirstRegister = dateOfFirstRegister;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", nick='" + nick + '\'' +
                ", password='" + password + '\'' +
                ", repatedPassword='" +  + '\'' +
                ", dateOfFirstRegister=" + dateOfFirstRegister +
                '}';
    }
}
