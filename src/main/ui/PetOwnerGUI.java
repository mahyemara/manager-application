package ui;

import ui.tabs.*;
import model.ListOfAnimal;

import javax.swing.*;
import java.io.FileNotFoundException;

public class PetOwnerGUI extends JFrame {

    private JTabbedPane sidebar;
    protected ListOfAnimal listOfAnimal;

    public static final int WIDTH = 2000;
    public static final int HEIGHT = 1000;
    public static final int HOME_TAB_INDEX = 0;

    //MODIFIES: this
    //EFFECTS: creates SmartHomeUI, loads SmartHome appliances, displays sidebar and tabs
    public PetOwnerGUI() throws FileNotFoundException {
        super("Pet Owner App");
        listOfAnimal = new ListOfAnimal("My Pets");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        sidebar = new JTabbedPane();
        sidebar.setTabPlacement(JTabbedPane.LEFT);

        loadTabs();
        add(sidebar);

        setVisible(true);
    }

    public ListOfAnimal getListOfAnimal() {
        return listOfAnimal;
    }

    //MODIFIES: this
    //EFFECTS: adds home tab, settings tab and report tab to this UI
    private void loadTabs() {
        JPanel homeTab = new HomeTab(this);
        setGraphics(homeTab, "Home");

        JPanel saveTab = new SaveTab(this);
        setGraphics(saveTab, "Save");

        JPanel loadTab = new LoadTab(this);
        setGraphics(loadTab, "Load");

        JPanel viewTab = new ViewTab(this);
        setGraphics(viewTab, "View");

        JPanel addTab = new AddTab(this);
        setGraphics(addTab, "Add");
    }

    // MODIFIES: this
    // EFFECTS: setGraphics for JPanel
    public void setGraphics(JPanel tab, String string) {
        sidebar.add(tab, HOME_TAB_INDEX);
        sidebar.setTitleAt(HOME_TAB_INDEX, string);
    }

    // MODIFIES: this
    // EFFECTS: updates list of animals
    public void updateListOfAnimal(ListOfAnimal listOfAnimal) {
        this.listOfAnimal = listOfAnimal;
    }

    //EFFECTS: returns sidebar of this UI
    public JTabbedPane getTabbedPane() {
        return sidebar;
    }
}