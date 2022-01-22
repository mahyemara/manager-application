package ui;

import exceptions.InvalidHealthInsuranceType;
import model.ListOfAnimal;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.console.Add;
import ui.console.Remove;
import ui.console.Update;
import ui.console.View;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// NOTE: Some code in this class is either inspired by / copied from code present in the CPSC210 AccountNotRobust
//  & CPSC210 JsonReader project.

// Represents Pet Owner Application
public class PetOwnerConsole extends JPanel {
    protected static final String JSON_STORE = "./data/workroom.json";
    protected ListOfAnimal listOfAnimal;                                  // declares list of Animals
    private Scanner input;                                              // scans the input of user
    private Add add;                                                    // to be able to use ADD class
    private View view;                                                  // to be able to use VIEW class
    private Update update;                                              // to be able to use UPDATE class
    private Remove remove;                                              // to be able to use REMOVE class
    protected JsonWriter jsonWriter;
    protected JsonReader jsonReader;

    // EFFECTS: constructs Pet Owner App and runs application
    public PetOwnerConsole() throws FileNotFoundException, InvalidHealthInsuranceType {
        super();
        runApp();
    }

    // EFFECTS: runs application as long as user does not input QUIT
    private void runApp() throws FileNotFoundException, InvalidHealthInsuranceType {
        boolean keepGoing = true;
        String command;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("quit")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
    }

    // EFFECTS: initializes all variables
    private void init() throws FileNotFoundException {
        listOfAnimal = new ListOfAnimal("Your Pets:");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        input = new Scanner(System.in);
        update = new Update(listOfAnimal);
        add = new Add(listOfAnimal);
        view = new View(listOfAnimal);
        remove = new Remove(listOfAnimal);
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tLOAD   -> load your existing list of pets to file");
        System.out.println("\tVIEW   -> view a pet's information");
        System.out.println("\tUPDATE -> update a pet’s information");
        System.out.println("\tADD    -> add a new pet to your list of pets");
        System.out.println("\tREMOVE -> remove an existing pet");
        System.out.println("\tSAVE   -> save your list of pets to file");
        System.out.println("\tQUIT   -> quit");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) throws InvalidHealthInsuranceType {
        if (command.equals("save")) {
            saveListOfAnimals();
        } else if (command.equals("add")) {
            add.addNewPet(listOfAnimal);
        } else if (command.equals("view")) {
            view.viewAnimalListInformation(listOfAnimal);
        } else if (command.equals("update")) {
            update.checkWhichAnimalToUpdate(listOfAnimal);
        } else if (command.equals("remove")) {
            remove.removePet(listOfAnimal);
        } else if (command.equals("load")) {
            loadListOfAnimals();
        } else {
            System.out.println("Selection not valid...0");
            command = input.next().toLowerCase();
            processCommand(command);
        }
    }

    // EFFECTS: saves the animal list to file
    public void saveListOfAnimals() {
        try {
            jsonWriter.open();
            jsonWriter.write(listOfAnimal);
            jsonWriter.close();
            System.out.println("Saved " + listOfAnimal.getPetOwnerName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    public void loadListOfAnimals() throws InvalidHealthInsuranceType {
        try {
            listOfAnimal = jsonReader.read();
            System.out.println("Loaded " + listOfAnimal.getPetOwnerName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}



