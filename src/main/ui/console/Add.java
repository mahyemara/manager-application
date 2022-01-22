package ui.console;

import exceptions.InvalidHealthInsuranceType;
import model.Animal;
import model.ListOfAnimal;

import java.util.LinkedList;
import java.util.Scanner;

// Add new Pet to ListOfAnimal
public class Add {

    private ListOfAnimal listOfAnimal;
    private Scanner input;
    private Animal newAnimal;

    // REQUIRES: nothing
    // MODIFIES: this
    // EFFECTS: initializes value
    public Add(ListOfAnimal listOfAnimal) {
        this.listOfAnimal = listOfAnimal;
        input = new Scanner(System.in);
        newAnimal = new Animal("");
    }

    // EFFECTS: displays list of pets already in list and asks user whether to add a new pet or not
    public void addNewPet(ListOfAnimal listOfAnimal) {
        this.listOfAnimal = listOfAnimal;
        System.out.println("These are the pets currently in your list of pets: ");
        printListOfPets(listOfAnimal);
        System.out.println("Are you sure you still want to add a new pet?\n"
                + "Please type either YES or NO");
        isInputYesOrNo(nextInput());
    }

    // EFFECTS: displays list of pets on screen
    public void printListOfPets(ListOfAnimal listOfAnimal) {
        if (listOfAnimal.isEmpty()) {
            System.out.println("none");
        } else {
            for (String name : listOfAnimal.getAnimalNamesInList()) {
                System.out.println(" " + name);
            }
        }
    }

    // EFFECTS: matches user input with whether or not to add a new pet
    private void isInputYesOrNo(String input) {
        if (input.equals("yes")) {
            yesAddPet();
        } else if (input.equals("no")) {
            System.out.println("No pet has been added");
        } else {
            System.out.println("Selection not valid...Please enter either YES or NO");
            isInputYesOrNo(nextInput());
        }
    }

    // MODIFIES: this
    // EFFECTS: adds new pet to existing list of pets
    private void yesAddPet() {
        newAnimal = addPetName();
        addPetType(newAnimal);
        addAge(newAnimal);
        try {
            addPetHealthInsurance(newAnimal);
        } catch (InvalidHealthInsuranceType invalidHealthInsuranceType) {
            invalidHealthInsuranceType.printStackTrace();
        }
        askForListOfVaccines(newAnimal);
        System.out.println("Your pet has been added successfully\n");
    }


    // EFFECTS: asks for and returns name for new pet
    public Animal addPetName() {
        System.out.println("What is your pet's name?");
        String input = nextInput();
        return newAnimal.petWithName(input);
    }

    // EFFECTS: asks for and returns name for new pet
    private Animal addPetType(Animal newAnimal) {
        System.out.println("What is your pet's type? (bird, dog, cat, etc.)");
        String input = nextInput();
        newAnimal.updateType(input);
        return newAnimal;
    }


    // EFFECTS: asks for and returns health insurance for new pet
    private void addPetHealthInsurance(Animal newAnimal) throws InvalidHealthInsuranceType {
        System.out.println("Does " + newAnimal.getName() + " have basic, premium, or no health insurance?");
        System.out.println("Please type either BASIC, PREMIUM, or NONE.");
        String input = nextInput();
        if (!(newAnimal.basicOrPremiumOrNone(input))) {
            addPetHealthInsurance(newAnimal);
        } else {
            newAnimal.updateHealthInsurance(input);
        }
    }

    // EFFECTS: asks for and returns age for new pet
    private void addAge(Animal newAnimal) {
        System.out.println("How old is " + newAnimal.getName());
        System.out.println("Please type a whole number.");
        String input = nextInput();
        if (!(isInteger(input))) {
            addAge(newAnimal);
        } else {
            int age = Integer.parseInt(input);
            newAnimal.updateAge(age);
        }
    }

    // EFFECTS: asks for list of vaccines new pet previously took
    private void askForListOfVaccines(Animal newAnimal) {
        System.out.println("Please list all the vaccines your animal has taken, each separate vaccine on a new line.\n"
                + "Once you are done, please type DONE on a separate line. \n"
                + "If your animal has never taken a vaccine, please just type DONE.");
        Animal newPetWithVaccines = addListOfVaccines(newAnimal);
        listOfAnimal.addAnimal(newPetWithVaccines);
    }

    // MODIFIES: this
    // EFFECTS: returns pet with list of vaccines new pet previously took
    private Animal addListOfVaccines(Animal newAnimal) {
        String nextInput = nextInput();
        LinkedList<String> listOfVaccines = newAnimal.getVaccinesTaken();
        if (nextInput.equals("done")) {
            System.out.println("Your animal's vaccine list has been updated."
                    + "It now contains all the vaccines your animal took.");
        } else if (!(isString(nextInput))) {
            System.out.println("Please enter a vaccine that consists only of letters");
            addListOfVaccines(newAnimal);
        } else {
            listOfVaccines.add(nextInput);
            addListOfVaccines(newAnimal);
        }
        return newAnimal;
    }

    // EFFECTS: prompts user to input something new
    public String nextInput() {
        String nextInput = this.input.next().toLowerCase();
        return nextInput;
    }

    // EFFECTS: return true if input is all Letters
    private boolean isString(String input) {
        return input.matches("[a-zA-Z_]+");
    }

    // EFFECTS: return true if input is all number
    private boolean isInteger(String input) {
        return input.matches("[0-9]+");
    }


}