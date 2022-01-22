package ui.tabs;


import ui.PetOwnerGUI;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class SaveTab extends Tab {

    private JLabel greeting = new JLabel();
    protected static final String JSON_STORE = "./data/workroom.json";
    protected JsonWriter jsonWriter;

    //EFFECTS: constructs a home tab for console with buttons and a greeting
    public SaveTab(PetOwnerGUI controller) {
        super(controller);
        jsonWriter = new JsonWriter(JSON_STORE);
        setLayout(new GridLayout(3, 1));
        placeGreeting("save?");
        place();
        placeStatusButton("Home",HOME_TAB);

    }

    // EFFECTS: creates greeting at top of console
    private void placeGreeting(String greet) {
        greeting = new JLabel(greet, JLabel.CENTER);
        greeting.setSize(WIDTH, HEIGHT / 3);
        this.add(greeting);
    }

    // EFFECTS: place Save Button
    private void place() {
        JPanel statusBlock = new JPanel();
        JButton statusButton = new JButton("Save");
        statusBlock.add(formatButtonRow(statusButton));
        statusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buttonPressed = e.getActionCommand();
                if (buttonPressed.equals("Save")) {
                    saveListOfAnimals();
                }
            }
        });
        this.add(statusBlock);
    }

    // EFFECTS: saves the animal list to file
    public void saveListOfAnimals() {
        try {
            jsonWriter.open();
            jsonWriter.write(getController().getListOfAnimal());
            jsonWriter.close();
            greeting.setText("Saved! Please press HOME to be redirected to home menu");
        } catch (FileNotFoundException e) {
            greeting.setText("Unable to save");
        }
    }
}