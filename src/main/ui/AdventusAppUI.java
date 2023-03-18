package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;

// UI Development based off of the AlarmSystem codebase from CPSC 210
public class AdventusAppUI extends JFrame {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final String FILE_DESCRIPTOR = "...file";
    private static final String SCREEN_DESCRIPTOR = "...screen";
    private AdventusApp aa;
    private KeyPad kp;
    private JComboBox<String> printCombo;
    private JDesktopPane desktop;
    private JInternalFrame controlPanel;

    // Constructor sets up button panel, key pad and visual alarm status window.
    public AdventusAppUI() {


        desktop = new JDesktopPane();
        desktop.addMouseListener(new DesktopFocusAction());
        controlPanel = new JInternalFrame("Control Panel", false, false, false, false);
        controlPanel.setLayout(new BorderLayout());

        setContentPane(desktop);
        setTitle("Adventus Travel App Tracker");
        setSize(WIDTH, HEIGHT);

        addButtonPanel();
        addMenu();
        addKeyPad();

        controlPanel.pack();
        controlPanel.setVisible(true);
        desktop.add(controlPanel);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        centreOnScreen();
        setVisible(true);
    }

// Helper to add control buttons.

    private void addButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1));
//        buttonPanel.add(new JButton(new AddACity()));
//        buttonPanel.add(new JButton(new ViewAllCities()));
//        buttonPanel.add(new JButton(new ViewCityByRating()));

        buttonPanel.add(new JButton("Add a city"));
        buttonPanel.add(new JButton("View all cities"));
        buttonPanel.add(new JButton("View cities by rating"));

        controlPanel.add(buttonPanel, BorderLayout.WEST);
    }

// Adds menu bar.
    private void addMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu sensorMenu = new JMenu("Destination List");
        sensorMenu.setMnemonic('D');
//        addMenuItem(sensorMenu, new AddSensorAction(),
//                KeyStroke.getKeyStroke("control D"));
        menuBar.add(sensorMenu);

        setJMenuBar(menuBar);
    }

// Adds an item with given handler to the given menu

    private void addMenuItem(JMenu theMenu, AbstractAction action, KeyStroke accelerator) {
        JMenuItem menuItem = new JMenuItem(action);
        menuItem.setMnemonic(menuItem.getText().charAt(0));
        menuItem.setAccelerator(accelerator);
        theMenu.add(menuItem);
    }


 //  Helper to add keypad to main application window

    private void addKeyPad() {
        kp = new KeyPad();
        addKeyListener(kp);
        controlPanel.add(kp, BorderLayout.CENTER);
    }

//  Helper to centre main application window on desktop

    private void centreOnScreen() {
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        setLocation((width - getWidth()) / 2, (height - getHeight()) / 2);
    }

//    // Represents action to be taken when user wants to add a new code to the system.
//    private class AddACity extends AbstractAction {
//
//        AddACity() {
//            super("Add City");
//        }
//
//        @Override
//        public void actionPerformed(ActionEvent evt) {
//            AlarmCode alarmCode = new AlarmCode(kp.getCode());
//            kp.clearCode();
//            try {
//                ac.addCode(alarmCode);
//            } catch (NotValidCodeException e) {
//                JOptionPane.showMessageDialog(null, e.getMessage(), "System Error",
//                        JOptionPane.ERROR_MESSAGE);
//            }
//        }
//    }
//
//    // Represents action to be taken when user wants to add a new code to the system.
//    private class ViewAllCities extends AbstractAction {
//
//        ViewAllCities() {
//            super("View all cities");
//        }
//
//        @Override
//        public void actionPerformed(ActionEvent evt) {
//            AlarmCode alarmCode = new AlarmCode(kp.getCode());
//            kp.clearCode();
//            try {
//                ac.addCode(alarmCode);
//            } catch (NotValidCodeException e) {
//                JOptionPane.showMessageDialog(null, e.getMessage(), "System Error",
//                        JOptionPane.ERROR_MESSAGE);
//            }
//        }
//    }
//
//    // Represents action to be taken when user wants to add a new code to the system.
//    private class ViewCitiesByRating extends AbstractAction {
//
//        ViewCitiesByRating() {
//            super("View cities by rating");
//        }
//
//        @Override
//        public void actionPerformed(ActionEvent evt) {
//            AlarmCode alarmCode = new AlarmCode(kp.getCode());
//            kp.clearCode();
//            try {
//                ac.addCode(alarmCode);
//            } catch (NotValidCodeException e) {
//                JOptionPane.showMessageDialog(null, e.getMessage(), "System Error",
//                        JOptionPane.ERROR_MESSAGE);
//            }
//        }
//    }

    // Represents action to be taken when user clicks desktop to switch focus.
    private class DesktopFocusAction extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            AdventusAppUI.this.requestFocusInWindow();
        }
    }

    // Starts the application
    public static void main(String[] args) {
        new AdventusAppUI();

        try {
            new AdventusApp();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
