package ui.tabs;

import exceptions.InvalidHealthInsuranceType;
import ui.PetOwnerGUI;
import model.Animal;
import model.ListOfAnimal;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.Integer.parseInt;

public class AddTab extends Tab implements ListSelectionListener {

    private Animal newPet;
    private ListOfAnimal listOfAnimal;
    private JLabel greeting;
    private JTextField t1;
    private JTextField t2;
    private JTextField t3;
    private JTextField t4;
    private String[] vaccines;
    private JList<String> subList;

    private static final String INIT_GREETING =
            "Please enter your pet's name, age, and insurance type in the fields below, then press ADD.";

    // default constructor
    public AddTab(PetOwnerGUI controller) {
        super(controller);
        vaccines = new String[]{"influenza", "rabies", "herpes", "leukemia", "none"};
        subList = new JList<>(vaccines);
        newPet = new Animal("");
        greeting = new JLabel("", JLabel.CENTER);
        setBound();
        setLayout(new GridLayout(3, 1));
        place();

    }

    // MODIFIES: this
    // EFFECTS: set text bounds
    private void setBound() {
        t1 = new JTextField("Please add name here");
        t1.setBounds(50, 150, 200, 30);
        t2 = new JTextField("Please add age as a whole number");
        t2.setBounds(50, 150, 200, 30);
        t3 = new JTextField("Please add health insurance(basic premium or none)");
        t3.setBounds(50, 150, 200, 30);
        t4 = new JTextField("Please add type (bird, cat, etc)");
        t4.setBounds(50, 150, 200, 30);
    }

    // EFFECTS: place text on the application
    private void place() {
        placeGreeting(INIT_GREETING);
        placeAdd();
        placeStatusButton("Home", HOME_TAB);
    }

    //EFFECTS: creates greeting at top of console
    private void placeGreeting(String greet) {
        greeting.setText(greet);
        greeting.setSize(WIDTH, 10);
        this.add(greeting);
    }

    // MODIFIES: this
    // EFFECTS: place Add button
    private void placeAdd() {
        JPanel statusBlock = new JPanel();
        JButton statusButton = new JButton("Add");
        statusBlock = vaccines(statusBlock);
        statusBlock.add(statusButton);
        statusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buttonPressed = e.getActionCommand();
                if (buttonPressed.equals("Add")) {
                    try {
                        addWorks();
                    } catch (NumberFormatException numberFormatException) {
                        redo();
                        greeting.setText("FAILED TO ADD! please enter an integer for your pet's age");
                    } catch (InvalidHealthInsuranceType invalidHealthInsuranceType) {
                        redo();
                        greeting.setText("FAILED TO ADD! Please enter either BASIC or PREMIUM or NONE for your pet's"
                                + "health insurance.");
                    }
                }
            }
        });
        this.add(statusBlock);
    }

    // MODIFIES: this
    // EFFECTS: add new animal to list of animal
    private void addWorks() throws NumberFormatException,InvalidHealthInsuranceType {
        listOfAnimal = getController().getListOfAnimal();
        listOfAnimal.addAnimal(updateNewPet());
        getController().updateListOfAnimal(listOfAnimal);
        greeting.setText("Added! If you'd like to add another pet, please press HOME then ADD.");
    }

    // EFFECTS: modifies JPanel statusBlock
    private JPanel vaccines(JPanel statusBlock) {
        statusBlock.setLayout(new FlowLayout());
        statusBlock.add(t1);
        statusBlock.add(t2);
        statusBlock.add(t3);
        statusBlock.add(t4);
        JLabel subLabel = new JLabel("Vaccines:");
        statusBlock.add(subLabel);
        subList.setVisibleRowCount(8);
        subList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane scroller = new JScrollPane(subList);
        statusBlock.add(scroller);
        subList.addListSelectionListener(this);
        setVisible(true);
        return statusBlock;
    }

    //EFFECTS: constructs a status button that switches to the report tab on the console
    public void placeStatusButton(String specificTab, int index) {
        JPanel statusBlock = new JPanel();
        JButton statusButton = new JButton(specificTab);
        statusBlock.add(formatButtonRow(statusButton));
        statusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buttonPressed = e.getActionCommand();
                if (buttonPressed.equals(specificTab)) {
                    greeting.setText(INIT_GREETING);
                    redo();
                    getController().getTabbedPane().setSelectedIndex(index);
                }
            }
        });

        this.add(statusBlock);
    }

    // MODIFIES: this
    // EFFECTS: redos t1,t2,t3,t4 to original text
    private void redo() {
        t1.setText("Please add name here");
        t2.setText("Please add age as a whole number");
        t3.setText("Please add health insurance(basic premium or none)");
        t4.setText("Please add type (bird, cat, etc)");
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    public Animal updateNewPet() throws NumberFormatException, InvalidHealthInsuranceType {
        newPet = new Animal(t1.getText());
        int age = parseInt(t2.getText());
        newPet.updateAge(age);
        newPet.updateHealthInsurance(t3.getText()); // healthInsurance
        newPet.updateType(t4.getText());
        Object[] obj = subList.getSelectedValues();
        for (int i = 0; i < obj.length; i++) {
            newPet.updateVaccinesTaken((String) obj[i]);
        }
        return newPet;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        // already did method above
    }
}
