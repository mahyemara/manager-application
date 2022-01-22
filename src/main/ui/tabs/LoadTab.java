package ui.tabs;


import exceptions.InvalidHealthInsuranceType;
import ui.PetOwnerGUI;
import model.ListOfAnimal;
import persistence.JsonReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class LoadTab extends Tab {

    private JLabel greeting = new JLabel();
    protected JsonReader jsonReader;
    protected PetOwnerGUI controller;
    protected static final String JSON_STORE = "./data/workroom.json";

    //EFFECTS: constructs a home tab for console with buttons and a greeting
    public LoadTab(PetOwnerGUI controller) {
        super(controller);
        this.controller = controller;
        jsonReader = new JsonReader(JSON_STORE);
        setLayout(new GridLayout(3, 1));
        placeGreeting("Please press LOAD to load your pets' information from file");
        place();
        placeStatusButton("Home", HOME_TAB);
    }

    //EFFECTS: creates greeting at top of console
    private void placeGreeting(String greet) {
        greeting = new JLabel(greet, JLabel.CENTER);
        greeting.setSize(WIDTH, HEIGHT / 3);
        this.add(greeting);
    }

    // EFFECTS: place load button
    public void place() {
        JPanel statusBlock = new JPanel();
        JButton statusButton = new JButton("Load");
        statusBlock.add(formatButtonRow(statusButton));
        statusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buttonPressed = e.getActionCommand();
                if (buttonPressed.equals("Load")) {
                    try {
                        controller.updateListOfAnimal(loadListOfAnimals());
                    } catch (InvalidHealthInsuranceType invalidHealthInsuranceType) {
                        invalidHealthInsuranceType.printStackTrace();
                    }
                }
            }
        });
        this.add(statusBlock);
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private ListOfAnimal loadListOfAnimals() throws InvalidHealthInsuranceType {
        ListOfAnimal listOfAnimal = null;
        try {
            listOfAnimal = jsonReader.read();
            greeting.setText("Loaded! Please press HOME to be redirected to home menu");
        } catch (IOException e) {
            greeting.setText("Unable to load");
        }
        return listOfAnimal;
    }
}