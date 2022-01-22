package ui.console;

import model.Animal;
import model.ListOfAnimal;

import java.util.Scanner;

// REMOVES pet from list of pets
public class Remove {

    private ListOfAnimal listOfAnimal;
    private Add add;

    // MODIFIES: this
    // EFFECTS: initializes values
    public Remove(ListOfAnimal listOfAnimal) {
        this.listOfAnimal = listOfAnimal;
        add = new Add(listOfAnimal);
    }

    // EFFECTS: displays list of pets already in list and asks user whether to remove a pet or not
    public void removePet(ListOfAnimal listOfAnimal) {
        this.listOfAnimal = listOfAnimal;
        System.out.println("These are the pets currently in your list of pets: ");
        add.printListOfPets(listOfAnimal);
        System.out.println("Are you sure you still want to remove a pet?\n"
                + "Please type either YES or NO");
        isInputYesOrNo(add.nextInput());
    }

    // EFFECTS: matches user input with whether or not to remove a new pet
    private void isInputYesOrNo(String input) {
        if (input.equals("yes")) {
            yesRemovePet();
        } else if (input.equals("no")) {
            System.out.println("No pet has been removed");
        } else {
            System.out.println("Selection not valid...Please enter either YES or NO");
            isInputYesOrNo(add.nextInput());
        }
    }

    // EFFECTS: removes pet to existing list of pets
    private void yesRemovePet() {
        System.out.println("What is your pet's name?");
        String name = add.nextInput();
        System.out.println(listOfAnimal.removePet(name));
    }
}