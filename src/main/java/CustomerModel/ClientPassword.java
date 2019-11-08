package CustomerModel;



import javax.persistence.*;

@Entity
@Table(name="client_password")
public class ClientPassword {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     public int id;
    @Column(name="password")
    public String password;
    @Column(name="FK_client_id")
    public int client_id;

    public ClientPassword(String password, int client_id) {
        this.password = password;
        this.client_id = client_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public int getClient_id() {
        return client_id;
    }

    @Override
    public String toString() {
        return "ClientPassword{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", client_id=" + client_id +
                '}';
    }
}
