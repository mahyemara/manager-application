package exceptions;

public class InvalidHealthInsuranceType extends Exception {

    public InvalidHealthInsuranceType() {
        System.out.print("Please enter either basic or premium or none for health insurance type!");
    }
}
