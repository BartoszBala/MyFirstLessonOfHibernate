package CustomerModel;

public enum TypeOfAccount {

    PLN("złoty"),EURO("euro"),USD("dolar"),GBP("funt");


    private final String currency;              //fixme nie wiem czy to jest ok

    TypeOfAccount(String currency) {

        this.currency=currency;
    }
}
