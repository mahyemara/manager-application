package ui.tabs;


import ui.PetOwnerGUI;
import model.Animal;
import model.ListOfAnimal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewTab extends Tab {
    private ListOfAnimal listOfAnimal;
    private JLabel greeting;
    private JTextArea textArea;

    private static final String INIT_GREETING = "\n Please press VIEW to view your pets' information";
    private static final String NEW_LINE = "\n";

    //EFFECTS: constructs a home tab for console with buttons and a greeting
    public ViewTab(PetOwnerGUI controller) {
        super(controller);
        setLayout(new GridLayout(3, 1));
        placeGreeting(INIT_GREETING);
        placeTextArea();
        place();
        placeStatusButton("Home",HOME_TAB);
    }

    private void placeTextArea() {
        // placing area for text
        textArea = new JTextArea(5, 5);
        textArea.setFont(new Font("Serif", Font.ITALIC, 16));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        this.add(textArea);

        // SCROLL PANE
        JScrollPane areaScrollPane = new JScrollPane(textArea);
        areaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        areaScrollPane.setPreferredSize(new Dimension(250, 250));
        this.add(areaScrollPane);
    }

    //EFFECTS: creates greeting at top of console
    private void placeGreeting(String greet) {
        greeting = new JLabel(greet, JLabel.CENTER);
        greeting.setSize(WIDTH, HEIGHT / 3);
        this.add(greeting);
    }

    //EFFECTS: creates Arrive and Leave buttons that change greeting message
    private void place() {
        JButton b1 = new JButton("View");
        JPanel buttonRow = formatButtonRow(b1);
        buttonRow.setSize(WIDTH, HEIGHT / 6);
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buttonPressed = e.getActionCommand();
                if (buttonPressed.equals("View")) {
                    textArea.setText(null); // clear textArea before each viewing
                    listOfAnimal = getController().getListOfAnimal();
                    viewAnimalListInformation();
                }
            }
        });
        this.add(buttonRow);
    }

    // MODIFIES: this
    // EFFECTS: displays animal list information iff listOfAnimal is not empty
    private void viewAnimalListInformation() {
        if (listOfAnimal.isEmpty()) {
            textArea.append("No pet is stored in your list." + NEW_LINE);
            textArea.append("Please either add a new pet to your list of pets  or load existing pets to begin"
                    + NEW_LINE);
        } else {
            viewAnimalInformation();
        }
    }

    // EFFECTS: displays animal name, age, type, health insurance, and list of vaccines on screen
    private void viewAnimalInformation() {
        for (Animal animal : listOfAnimal.getListOfAnimal()) {
            textArea.append(animal.getName() + "'s age: " + animal.getAge() + NEW_LINE);
            textArea.append(animal.getName() + "'s type: " + animal.getType() + NEW_LINE);
            displayHealthInsurance(animal);
            printListOfVaccines(animal);
            textArea.append("" + NEW_LINE);
        }
    }

    // EFFECTS: displays health insurance type on screen
    private void displayHealthInsurance(Animal animal) {
        if (animal.getHealthInsuranceType().equals("none")) {
            textArea.append(animal.getName() + " does not have health insurance." + NEW_LINE);
        } else {
            textArea.append(animal.getName() + "'s Health Insurance type is: "
                    + animal.getHealthInsuranceType() + NEW_LINE);
        }
    }

    // EFFECTS: displays list of vaccines to user on screen
    private void printListOfVaccines(Animal animal) {
        if (animal.getVaccinesTaken().isEmpty()) {
            textArea.append(animal.getName() + "'s list of vaccines previously taken: none " + NEW_LINE);
        } else {
            for (int i = 1; i <= animal.getVaccinesTaken().size(); i++) {
                textArea.append(animal.getName() + "'s vaccine number " + i + " is: "
                        + animal.getVaccinesTaken().get(i - 1) + NEW_LINE);
            }
        }
    }

}

