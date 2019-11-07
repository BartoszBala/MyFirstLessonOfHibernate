package CustomerModel;

public enum TypeOfOperation {
    DEPOSIT("depozyt"),LOAN("pozyczka"),OUTGOINGTRANSFER("przelew wychodzący"),INCOMINGTRANSFER(" przelew wychodzący");


    private final String name;

    TypeOfOperation(String name) {
        
        this.name=name;
    }
}
