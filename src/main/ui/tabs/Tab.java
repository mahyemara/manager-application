package ui.tabs;

import ui.PetOwnerGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Tab extends JPanel {

    private PetOwnerGUI controller;

    protected static final int VIEW_TAB = 1;
    protected static final int LOAD_TAB = 2;
    protected static final int ADD_TAB = 0;
    protected static final int SAVE_TAB = 3;
    protected static final int HOME_TAB = 4;

    //REQUIRES: SmartHomeUI controller that holds this tab
    public Tab(PetOwnerGUI controller) {
        this.controller = controller;
    }

    //EFFECTS: creates and returns row with button included
    public JPanel formatButtonRow(JButton b) {
        JPanel p = new JPanel();
        p.setLayout(new FlowLayout());
        p.add(b);
        return p;
    }

    //EFFECTS: creates and returns row with button included
    public JPanel formatButtonRow(JTextField t) {
        JPanel p = new JPanel();
        p.setLayout(new FlowLayout());
        p.add(t);
        return p;
    }

    //EFFECTS: returns the SmartHomeUI controller for this tab
    public PetOwnerGUI getController() {
        return controller;
    }

    //EFFECTS: constructs a status button that switches to the report tab on the console
    public void placeStatusButton(String tab, int index) {
        JPanel statusBlock = new JPanel();
        JButton statusButton = new JButton(tab);
        statusBlock.add(formatButtonRow(statusButton));
        statusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buttonPressed = e.getActionCommand();
                if (buttonPressed.equals(tab)) {
                    getController().getTabbedPane().setSelectedIndex(index);
                }
            }
        });

        this.add(statusBlock);
    }
}
