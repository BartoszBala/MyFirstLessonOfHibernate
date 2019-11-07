package CustomerModel;

import java.util.Date;
import java.util.List;

public class Account {

    private String accountNumber;
    private TypeOfAccount typeOfAccount;
    private Date datOfestablish;
    private List<Operation> listOfAllOperation;

    public Account(String accountNumber, TypeOfAccount typeOfAccount, Date datOfestablish) {
        this.accountNumber = accountNumber;
        this.typeOfAccount = typeOfAccount;
        this.datOfestablish = datOfestablish;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber='" + accountNumber + '\'' +
                ", typeOfAccount=" + typeOfAccount +
                ", datOfestablish=" + datOfestablish +
                '}';
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setTypeOfAccount(TypeOfAccount typeOfAccount) {
        this.typeOfAccount = typeOfAccount;
    }

    public void setDatOfestablish(Date datOfestablish) {
        this.datOfestablish = datOfestablish;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public TypeOfAccount getTypeOfAccount() {
        return typeOfAccount;
    }

    public Date getDatOfestablish() {
        return datOfestablish;
    }
}
