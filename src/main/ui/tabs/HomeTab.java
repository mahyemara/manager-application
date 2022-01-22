package ui.tabs;

import ui.PetOwnerGUI;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeTab extends Tab {

    private static final String INIT_GREETING = "\n";
    protected JsonWriter jsonWriter;
    JLabel greeting = new JLabel("", JLabel.CENTER);
    protected static final String JSON_STORE = "./data/workroom.json";

    //EFFECTS: constructs a home tab for console with buttons and a greeting
    public HomeTab(PetOwnerGUI controller) {
        super(controller);
        setLayout(new GridLayout(3, 1));
        jsonWriter = new JsonWriter(JSON_STORE);
        placeGreeting(INIT_GREETING);
        placeStatusButton2();
    }

    // EFFECTS: place LOAD, ADD, VIEW, SAVE buttons
    private void placeStatusButton2() {
        JPanel statusBlock = new JPanel();
        JButton statusButton = new JButton("Load");
        JButton statusButton2 = new JButton("View");
        JButton statusButton3 = new JButton("Save");
        JButton statusButton4 = new JButton("Add");
        format(statusBlock,statusButton,statusButton2,statusButton3,statusButton4);
        button(statusButton,"Load",LOAD_TAB);
        button(statusButton2,"View",VIEW_TAB);
        button(statusButton3,"Save",SAVE_TAB);
        button(statusButton4,"Add",ADD_TAB);
        this.add(statusBlock);
    }

    // EFFECTS: constructs a new JButton
    private void button(JButton statusButton,String pressed, int tabIndex) {
        statusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buttonPressed = e.getActionCommand();
                if (buttonPressed.equals(pressed)) {
                    getController().getTabbedPane().setSelectedIndex(tabIndex);
                }
            }
        });
    }

    // EFFECTS: modifies statusBlock to add buttons
    private JPanel format(JPanel statusBlock,JButton statusButton,JButton statusButton2,
                          JButton statusButton3, JButton statusButton4) {
        statusBlock.add(formatButtonRow(statusButton));
        statusBlock.add(formatButtonRow(statusButton2));
        statusBlock.add(formatButtonRow(statusButton3));
        statusBlock.add(formatButtonRow(statusButton4));
        return statusBlock;
    }

    // MODIFIES: this
    // EFFECTS: creates greeting at top of console
    private void placeGreeting(String greet) {
        greeting.setText(greet);
        greeting.setSize(WIDTH, 10);
        this.add(greeting);
    }
}