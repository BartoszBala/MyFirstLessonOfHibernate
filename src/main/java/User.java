import javax.persistence.*;
import java.util.Date;

public class User {
    @Id
    private  int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String email;
    private String nick;
    private String password;
    private  String repatedPassword;
    private Date dateOfFirstRegister;

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
                ", repatedPassword='" + repatedPassword + '\'' +
                ", dateOfFirstRegister=" + dateOfFirstRegister +
                '}';
    }
}
