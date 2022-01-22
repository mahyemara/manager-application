package ui.console;

import exceptions.InvalidHealthInsuranceType;
import model.Animal;
import model.ListOfAnimal;

import java.util.LinkedList;
import java.util.Scanner;

// updates an animal's information
public class Update {

    private ListOfAnimal listOfAnimal;
    private Scanner input;
    private Add add;

    // MODIFIES: this
    // EFFECTS: initializes value
    public Update(ListOfAnimal listOfAnimal) {
        this.listOfAnimal = listOfAnimal;
        input = new Scanner(System.in);
        add = new Add(listOfAnimal);
    }

    // EFFECTS: matches user input with which Animal in listOfAnimals to update
    public void checkWhichAnimalToUpdate(ListOfAnimal listOfAnimal) {
        this.listOfAnimal = listOfAnimal;
        System.out.println("Please enter the name of the animal you want to update its information.");
        String nextInput = add.nextInput();
        if (listOfAnimal.checkDuplicateName(nextInput)) {
            updateHealthOrVaccines(listOfAnimal.getAnimalInList(nextInput));
        } else {
            System.out.println("Did not find animal... please make sure you add animal to list of animals.\n");
        }
    }

    // REQUIRES: pet to be in ListOfAnimals
    // EFFECTS: updates pet's either health or vaccine info
    private void updateHealthOrVaccines(Animal pet) {
        System.out.println("Do you want to update " + pet.getName() + "'s Age, Health Insurance, or Vaccines Taken?");
        System.out.println("Please write either AGE or HEALTH or VACCINE.");
        String nextInput = add.nextInput();
        whatIsInputEqual(nextInput, pet);
    }

    // REQUIRES: pet to be in ListOfAnimals
    // EFFECTS: matches user input with whether to update animal's health or vaccine information
    private void whatIsInputEqual(String input, Animal pet) {
        if (input.equals("age")) {
            updateAge(pet);
        } else if (input.equals("health")) {
            updateHealthInfo(pet, listOfAnimal);
        } else if (input.equals("vaccine")) {
            updateVaccineInfo(pet.getVaccinesTaken());
        } else {
            System.out.println("Selection not valid...Please enter either HEALTH or VACCINE");
            String nextInput = add.nextInput();
            whatIsInputEqual(nextInput, pet);
        }
    }


    // REQUIRES: pet in listOfAnimal
    // MODIFIES: this
    // EFFECTS: updates pet's health info and adds updated pet to listOfAnimal
    private void updateAge(Animal pet) {
        System.out.println("Your animal is currently " + pet.getAge() + " years old.\n"
                + "What do you want to update age to?\n"
                + "Please type your answer in integer.\n");
        String input = add.nextInput();
        if (!(isInteger(input))) {
            updateAge(pet);
        } else {
            Integer newAge = Integer.parseInt(input);
            pet.updateAge(newAge);
            listOfAnimal.replacePet(pet);
            System.out.println("Your pet's age has been updated.\n");
        }

    }


    // REQUIRES: pet in listOfAnimal
    // MODIFIES: this
    // EFFECTS: updates pet's health info and adds updated pet to listOfAnimal
    private void updateHealthInfo(Animal pet, ListOfAnimal listOfAnimal) {
        System.out.println("You can update your insurance to either No health insurance, Basic health insurance, or "
                + "Premium health insurance.\n");
        displayInsuranceType(pet.getHealthInsuranceType());
        System.out.println("What do you want to update your health insurance to?");
        String input = add.nextInput();
        try {
            pet.updateHealthInsurance(input);
        } catch (InvalidHealthInsuranceType invalidHealthInsuranceType) {
            invalidHealthInsuranceType.printStackTrace();
        }
        listOfAnimal.replacePet(pet);
    }

    // EFFECTS: displays animal's insurance type
    private void displayInsuranceType(String healthInsuranceType) {
        if (healthInsuranceType.equals("none")) {
            System.out.println("Your animal currently has no health insurance.\n");
        } else {
            System.out.println("Your animal currently has " + healthInsuranceType + " health insurance.\n");
        }
    }

    // MODIFIES: this
    // EFFECTS: updates animal's vaccine info
    private void updateVaccineInfo(LinkedList<String> listOfVaccines) {
        System.out.println("The list of vaccines your animal previously took is: ");
        printlistofVaccines(listOfVaccines);
        System.out.println("Please list the vaccine your animal took: ");
        String input = this.input.next().toLowerCase(); //force entry into loop
        while (!(isString(input))) {
            System.out.println("The vaccine your animal took should only contain letters");
            input = this.input.next().toLowerCase();
        }
        String newListElement = input;
        listOfVaccines.add(newListElement);
        System.out.println("Your animal's vaccine list has been updated. It now contains the vaccine"
                + " your animal took.");
    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    private void printlistofVaccines(LinkedList<String> listOfVaccines) {
        if (listOfVaccines.isEmpty()) {
            System.out.println("none");
        } else {
            for (String s : listOfVaccines) {
                System.out.println(" " + s);
            }
        }
    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    private boolean isString(String input) {
        return input.matches("[a-zA-Z_]+");
    }

    private boolean isInteger(String input) {
        return input.matches("[0-9]+");
    }
}