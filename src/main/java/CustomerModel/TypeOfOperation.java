package CustomerModel;

public enum TypeOfOperation {
    DEPOSIT("depozyt"),LOAN("pozyczka"),OUTGOINGTRANSFER("przelew wychodzący"),INCOMINGTRANSFER(" przelew przychodzący");


    private final String name;

    TypeOfOperation(String name) {
        
        this.name=name;
    }
}
