package ui;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Event;
import model.EventLog;
import model.city.City;
import model.city.Continent;
import model.destinationlist.DestinationList;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

// AdventusApp for tracking cities visited. UI Development based off of Java's ListDemo.java demo
public class AdventusAppUI extends JPanel implements ListSelectionListener, WindowListener {

    private JList list;
    private DefaultListModel listModel;

    private static final String JSON_STORE = "./data/destinationlist.json";
    private static final String addCityString = "Add";
    private static final String removeCityString = "Remove";
    private static final String saveString = "Save";
    private static final String loadString = "Load";
    private static final String[] continents = { "AFRICA", "ANTARCTICA", "ASIA", "EUROPE", "OCEANIA",
            "NORTH_AMERICA", "SOUTH_AMERICA" };
    private JButton addCityButton;
    private JButton removeCityButton;
    private JButton saveButton;
    private JButton loadButton;
    private JTextField cityName;
    private JTextField cityRating = new JTextField(10);
    private JComboBox cityContinent = new JComboBox<>(continents);
    private DestinationList destinationList;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // Constructor sets up button panel and field form.
    // EFFECTS: constructs and runs the AdventusUI (GUI version) application
    public AdventusAppUI() {
        super(new BorderLayout());

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        destinationList = new DestinationList("Kwan's destination list");

        listModel = new DefaultListModel();

        //Create the list and put it in a scroll pane.
        JScrollPane listScrollPane = getjScrollPane();

        AddCityListener addCityListener = getAddCityListener();

        removeCityButton = new JButton(removeCityString);
        removeCityButton.setActionCommand(removeCityString);
        removeCityButton.addActionListener(new RemoveCityListener());

        saveButton = new JButton(saveString);
        SaveListener saveListener = new SaveListener(saveButton);
        saveButton.setActionCommand(saveString);
        saveButton.addActionListener(saveListener);

        loadButton = new JButton(loadString);
        LoadListener loadListener = new LoadListener(loadButton);
        loadButton.setActionCommand(loadString);
        loadButton.addActionListener(loadListener);

        setCityListener(addCityListener);

        addButtonsPanel(listScrollPane);
    }

    // Helper method for the JScrollPane
    private JScrollPane getjScrollPane() {
        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.addListSelectionListener(this);
        list.setVisibleRowCount(5);
        JScrollPane listScrollPane = new JScrollPane(list);
        return listScrollPane;
    }

    // Helper method for setting the City listener
    private void setCityListener(AddCityListener addCityListener) {
        cityName = new JTextField(10);
        cityName.addActionListener(addCityListener);
        cityName.getDocument().addDocumentListener(addCityListener);

    }

    // Helper method of the Add City listener
    private AddCityListener getAddCityListener() {
        addCityButton = new JButton(addCityString);
        AddCityListener addCityListener = new AddCityListener(addCityButton);
        addCityButton.setActionCommand(addCityString);
        addCityButton.addActionListener(addCityListener);
        addCityButton.setEnabled(false);
        return addCityListener;
    }

    // Sets up the button panel
    // EFFECT: Adds the Jbuttons, JTextfields and JComboBox onto the GUI
    private void addButtonsPanel(JScrollPane listScrollPane) {
        JPanel buttonPane = new JPanel();
        JLabel labelCityName = new JLabel("Enter city name: ");
        JLabel labelCityRating = new JLabel("Enter city rating (1-5): ");
        JLabel labelCityContinent = new JLabel("Enter city's continent: ");
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(labelCityName);
        buttonPane.add(cityName);
        buttonPane.add(labelCityRating);
        buttonPane.add(cityRating);
        buttonPane.add(labelCityContinent);
        buttonPane.add(cityContinent);
        buttonPane.add(addCityButton);
        buttonPane.add(removeCityButton);
        buttonPane.add(saveButton);
        buttonPane.add(loadButton);
        buttonPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        add(listScrollPane, BorderLayout.CENTER);
        add(buttonPane, BorderLayout.PAGE_END);
    }

    // MODIFIES: listModel, destinationList
    // EFFECT: Helper method for removing a city in destinationList and the JList
    class RemoveCityListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int index = list.getSelectedIndex();
            listModel.remove(index);

            int size = listModel.getSize();

            if (size == 0) { //Empty city list, disable removing a city
                removeCityButton.setEnabled(false);

            } else { //Select an index.
                if (index == listModel.getSize()) {
                    //removed item in last position
                    index--;

                }

                // Removes the city in the model
                City cityObject = destinationList.getAllCities().get(index);
                String cityToRemove = cityObject.getName();
                destinationList.removeCity(cityToRemove);

                // Add an entry to the EventLog
                EventLog.getInstance().logEvent(new Event("REMOVED: City: " + cityObject.getName() + ", Rating: "
                        + cityObject.getRating() + " stars, Continent: "
                        + cityObject.getContinent()));

                // Highlights a new city in the UI
                list.setSelectedIndex(index);
                list.ensureIndexIsVisible(index);
            }
        }
    }

    // This listener is shared by the text field and the add city button.
    // MODIFIES: listModel, destinationList
    // EFFECT: Adds a City entry to JList, and updates the destinationList backend.
    class AddCityListener implements ActionListener, DocumentListener {
        private boolean alreadyEnabled = false;
        private JButton button;

        public AddCityListener(JButton button) {
            this.button = button;
        }

        //Required by ActionListener.
        public void actionPerformed(ActionEvent e) {
            Integer rating = Integer.parseInt(cityRating.getText());

            int index = list.getSelectedIndex(); //get selected index
            if (index == -1) { //no selection, so insert at beginning
                index = 0;
            } else {           //add after the selected item
                index++;
            }

            // This is where the city name, rating and continent gets added to the JList
            listModel.insertElementAt(cityName.getText() + ", " + cityRating.getText()
                    + ", " + cityContinent.getSelectedItem(), index);

            // Convert continent from dropdown menu to Continent class
            Continent continentObject = AdventusApp.convertContinentNum(cityContinent.getSelectedIndex() + 1);

            // Convert user's input to a city object, and add it to DestinationList
            City cityObject = new City(cityName.getText(), rating, continentObject);
            destinationList.addCity(cityObject);

            // Add an entry to the EventLog
            EventLog.getInstance().logEvent(new Event("ADDED: City: " + cityName.getText() + ", Rating: " + rating
                    + " stars, Continent: " + cityContinent.getSelectedItem()));

            //Reset the text field.
            cityName.requestFocusInWindow();
            cityName.setText("");
            cityRating.setText("");

            //Select the new item and make it visible.
            list.setSelectedIndex(index);
            list.ensureIndexIsVisible(index);
        }

        //Required by DocumentListener.
        public void insertUpdate(DocumentEvent e) {
            enableButton();
        }

        //Required by DocumentListener.
        public void removeUpdate(DocumentEvent e) {
            handleEmptyTextField(e);
        }

        //Required by DocumentListener.
        public void changedUpdate(DocumentEvent e) {
            if (!handleEmptyTextField(e)) {
                enableButton();
            }
        }

        // MODIFIES: setEnabled
        // EFFECTS: Flips the boolean value of setEnabled of a button
        private void enableButton() {
            if (!alreadyEnabled) {
                button.setEnabled(true);
            }
        }

        // REQUIRES: an existing DocumentEvent
        // MODIFIES: alreadyEnabled
        // EFFECTS: Returns true if length of e.getDocument() is zero or negative, false otherwise
        private boolean handleEmptyTextField(DocumentEvent e) {
            if (e.getDocument().getLength() <= 0) {
                button.setEnabled(false);
                alreadyEnabled = false;
                return true;
            }
            return false;
        }
    }

    // MODIFIES: THE .JSON file at JSON_STORE
    // EFFECTS: Writes the content for the destination list into JList
    class SaveListener implements ActionListener {
        private JButton button;

        public SaveListener(JButton button) {
            this.button = button;
        }

        public void actionPerformed(ActionEvent e) {
            try {
                jsonWriter.open();
                jsonWriter.write(destinationList);
                jsonWriter.close();
                System.out.println("Saved " + destinationList.getName() + " to " + JSON_STORE);
            } catch (FileNotFoundException exception) {
                System.out.println("Unable to write to file: " + JSON_STORE);
            }
        }
    }

    // MODIFIES: THE .JSON file at JSON_STORE
    // EFFECTS: Reads the TXT file and loads it into a JList
    class LoadListener implements ActionListener {
        private JButton button;

        public LoadListener(JButton button) {
            this.button = button;
        }

        // EFFECTS: Reads from the jSON file and displays it into JList
        public void actionPerformed(ActionEvent e) {
            try {
                destinationList = jsonReader.read();
                System.out.println("Loaded " + destinationList.getName() + " from " + JSON_STORE);
                int index = 0;
                List<City> savedList = destinationList.getAllCities();
                Collections.reverse(savedList);
                for (City c : savedList) {
                    listModel.insertElementAt(c.getName() + ", " + c.getRating() + ", " + c.getContinent(), index);
                }
            } catch (IOException exception) {
                System.out.println("Unable to read from file: " + JSON_STORE);
            }
        }
    }

    // EFFECTS: This method is required by ListSelectionListener.
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {

            if (list.getSelectedIndex() == -1) {
                //No selection, disable remove button.
                removeCityButton.setEnabled(false);

            } else {
                //Selection, enable the remove button.
                removeCityButton.setEnabled(true);
            }
        }
    }

    // REQUIRES: An image to be displayed at the splash screen
    // EFFECTS: Creates and shows the GUI
    private static void createAndShowGUI() {
        // Create and set up the window.
        JFrame frame = new JFrame("Adventus App");
        frame.addWindowListener(new AdventusAppUI());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create and set up the content pane.
        JComponent newContentPane = new AdventusAppUI();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        // Display the window.
        frame.pack();
        frame.setVisible(true);
        ImageIcon icon = new ImageIcon("./adventus-splash-screen.png");
        JOptionPane.showMessageDialog(null, "", "Welcome to", JOptionPane.INFORMATION_MESSAGE, icon);
    }

    // Required by WindowListener
    @Override
    public void windowOpened(WindowEvent e) {

    }

    // Required by WindowListener
    @Override
    public void windowClosing(WindowEvent e) {
        for (Event event : EventLog.getInstance()) {
            System.out.println(event.getDescription());
        }
    }

    // Required by WindowListener
    @Override
    public void windowClosed(WindowEvent e) {

    }

    // Required by WindowListener
    @Override
    public void windowIconified(WindowEvent e) {

    }

    // Required by WindowListener
    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    // Required by WindowListener
    @Override
    public void windowActivated(WindowEvent e) {

    }

    // Required by WindowListener
    @Override
    public void windowDeactivated(WindowEvent e) {

    }

    // EFFECTS: Starts the application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}

