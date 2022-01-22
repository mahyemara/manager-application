package ui.console;

import model.Animal;
import model.ListOfAnimal;

// VIEW All information about all of owner's pets
public class View {

    private ListOfAnimal listOfAnimal;

    public View(ListOfAnimal listOfAnimal) {
        this.listOfAnimal = listOfAnimal;
    }

    // REQUIRES: nothing
    // MODIFIES: this
    // EFFECTS: displays animal list information iff listOfAnimal is not empty
    public void viewAnimalListInformation(ListOfAnimal listOfAnimal) {
        this.listOfAnimal = listOfAnimal;
        if (listOfAnimal.isEmpty()) {
            System.out.println("No pet is stored in your list. "
                    + "Please type ADD to add a new pet to your list of pets to begin.");
        } else {
            viewAnimalInformation();
        }
    }

    // REQUIRES: nothing
    // MODIFIES: nothing
    // EFFECTS: displays animal name, age, type, health insurance, and list of vaccines on screen
    public void viewAnimalInformation() {
        for (Animal animal : listOfAnimal.getListOfAnimal()) {
            System.out.println(animal.getName() + "'s age: " + animal.getAge());
            System.out.println(animal.getName() + "'s type: " + animal.getType());
            displayHealthInsurance(animal);
            printListOfVaccines(animal);
        }
    }

    // REQUIRES: nothing
    // MODIFIES: nothing
    // EFFECTS: displays health insurance type on screen
    public void displayHealthInsurance(Animal animal) {
        if (animal.getHealthInsuranceType().equals("none")) {
            System.out.println(animal.getName() + " does not have health insurance.");
        } else {
            System.out.println(animal.getName() + "'s Health Insurance type is: "
                    + animal.getHealthInsuranceType());
        }
    }

    // REQUIRES: nothing
    // MODIFIES: nothing
    // EFFECTS: displays list of vaccines to user on screen
    private void printListOfVaccines(Animal animal) {
        System.out.println(animal.getName() + "'s list of vaccines previously taken: ");
        if (animal.getVaccinesTaken().isEmpty()) {
            System.out.println("none\n");
        } else {
            for (String s : animal.getVaccinesTaken()) {
                System.out.println(" " + s);
            }
            System.out.println();
        }
    }
}