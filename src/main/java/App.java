import CustomerModel.Adress;
import CustomerModel.Client;
import Service.CustomerService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class App {

    static CustomerService customerService;

    public static void main(String[] args) throws ParseException {
        customerService = new CustomerService();
        customerService.findAll().stream().forEach(x -> System.out.println(x));
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");


//            Date date1 = format.parse("1986-06-02");
//            Adress adress = new Adress("Polska","Zielona Góra","95-020","Okrężna","12","2a");
//            Client client1 = new Client("86060214121","Paulina","Cieśla",date1,adress);
//
//            Set<Client> clients = new HashSet<>();
//           adress.setClients(clients);
////            customerService;
//
//            customerService.persist(client1,adress);
//

//        customerService.findAll().stream().forEach(x-> System.out.println(x));
        Client client = customerService.findById(129);
        client.setPesel("86060212116");
        client.setLastName("Cieśla-Wilk");
        client.getAdress().setStreet("Malinowa");
        customerService.update(client, client.getAdress());
//       customerService.findAll().stream().forEach(System.out::println);
    }


}
