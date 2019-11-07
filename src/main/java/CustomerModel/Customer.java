package CustomerModel;

import java.util.Date;
import java.util.List;

public class Customer {

    private static int counter;
    private  int id;
    private String pesel;
    private String firstName;
    private  String lastName;
    private Date dob;
    private Adress adress;
    private List<Account> accountslist;


}
