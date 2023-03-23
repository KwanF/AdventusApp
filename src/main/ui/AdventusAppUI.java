package ui;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Vector;

// AdventusApp for tracking cities visited. UI Development based off of Java's ListDemo.java demo
public class AdventusAppUI extends JPanel implements ListSelectionListener {

    private JList list;
    private DefaultListModel listModel;
    private Boolean[] annotations = null;

    private static final String JSON_STORE = "./data/destinationlist.json";
    private static final String addCityString = "Add";
    private static final String removeCityString = "Remove";
    private static final String saveString = "Save";
    private static final String loadString = "Load";
    private static final String highlightString = "Highlight";
    private JButton addCityButton;
    private JButton removeCityButton;
    private JButton saveButton;
    private JButton loadButton;
    private JButton highlightButton;
    private JTextField cityName;
    private JScrollPane listScrollPane;

    // Constructor sets up button panel and field form.
    public AdventusAppUI() {
        super(new BorderLayout());

        listModel = new DefaultListModel();
        listModel.addElement("Vancouver");

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
        String name = listModel.getElementAt(
                list.getSelectedIndex()).toString();
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

    // EFFECT: Adds the buttons onto the GUI
    private void addButtonsPanel(JScrollPane listScrollPane) {
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane,
                BoxLayout.LINE_AXIS));
        buttonPane.add(removeCityButton);
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(cityName);
        buttonPane.add(addCityButton);
        buttonPane.add(saveButton);
        buttonPane.add(loadButton);
        buttonPane.add(highlightButton);
        buttonPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        add(listScrollPane, BorderLayout.CENTER);
        add(buttonPane, BorderLayout.PAGE_END);
    }

    // EFFECT: Helper method for removing a city
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

                list.setSelectedIndex(index);
                list.ensureIndexIsVisible(index);
            }
        }
    }

    // This listener is shared by the text field and the add city button.
    class AddCityListener implements ActionListener, DocumentListener {
        private boolean alreadyEnabled = false;
        private JButton button;

        public AddCityListener(JButton button) {
            this.button = button;
        }

        //Required by ActionListener.
        public void actionPerformed(ActionEvent e) {
            String name = cityName.getText();

            //User didn't type in a unique name...
            if (name.equals("") || alreadyInList(name)) {
                Toolkit.getDefaultToolkit().beep();
                cityName.requestFocusInWindow();
                cityName.selectAll();
                return;
            }

            int index = list.getSelectedIndex(); //get selected index
            if (index == -1) { //no selection, so insert at beginning
                index = 0;
            } else {           //add after the selected item
                index++;
            }

            listModel.insertElementAt(cityName.getText(), index);
            //If we just wanted to add to the end, we'd do this:
            //listModel.addElement(employeeName.getText());

            //Reset the text field.
            cityName.requestFocusInWindow();
            cityName.setText("");

            //Select the new item and make it visible.
            list.setSelectedIndex(index);
            list.ensureIndexIsVisible(index);
        }


        //This method tests for string equality. You could certainly
        //get more sophisticated about the algorithm.  For example,
        //you might want to ignore white space and capitalization.
        protected boolean alreadyInList(String name) {
            return listModel.contains(name);
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

    // MODIFIES: destinationlist.txt
    // EFFECTS: Writes the content of the JList into a TXT file
    class SaveListener implements ActionListener {
        private JButton button;

        public SaveListener(JButton button) {
            this.button = button;
        }

        public void actionPerformed(ActionEvent e) {
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter("destinationlist.txt"));
                for (int i = 0; i < list.getModel().getSize(); i++) {
                    bw.write((String) list.getModel().getElementAt(i));
                    bw.newLine();
                }
                bw.close();
            } catch (IOException exception) {
                System.out.println("\nIO error");
            }
        }
    }

    // EFFECTS: Reads the TXT file and loads it into a JList
    class LoadListener implements ActionListener {
        private JButton button;

        public LoadListener(JButton button) {
            this.button = button;
        }

        // Code referenced from java2s.com tutorial for loading TXT file into a JList
        public void actionPerformed(ActionEvent e) {
            File sourceFile = new File("./destinationlist.txt");
            FileReader fr = null;
            fr = getFileReader(sourceFile);
            BufferedReader br = new BufferedReader(fr);
            Vector<String> lines = new Vector<String>();
            String line;
            int index = 0;
            listModel.removeAllElements();
            addToJList(br, lines, index);
            JOptionPane.showMessageDialog(null, new JScrollPane(new JList(lines)));
            try {
                fr.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }

        private void addToJList(BufferedReader br, Vector<String> lines, int index) {
            String line;
            while (true) {
                try {
                    if (!((line = br.readLine()) != null)) {
                        break;
                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                lines.add(line);
                listModel.insertElementAt(line, index);
                index++;
            }
        }

        private FileReader getFileReader(File sourceFile) {
            FileReader fr;
            try {
                fr = new FileReader(sourceFile);
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            return fr;
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

    // EFFECTS: Creates and shows the GUI
    private static void createAndShowGUI() {
        // Create and set up the window.
        JFrame frame = new JFrame("Adventus App");
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


    // EFFECTS: Starts the application
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}

