package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyPad extends JPanel implements KeyListener {
    private static final String CLR_STR = "CLR";
    private JButton[] keys;
    private ClickHandler keyHandler;

    /**
     * Constructor creates keypad and code display area.
     */
    public KeyPad() {
        keyHandler = new ClickHandler();
        setLayout(new BorderLayout());
        JPanel keyPanel = new JPanel();
        keyPanel.setLayout(new GridLayout(5,3));
        addButtons(keyPanel);
        add(keyPanel, BorderLayout.CENTER);
        Box hbox = Box.createHorizontalBox();
        hbox.add(Box.createHorizontalGlue());

        hbox.add(Box.createHorizontalGlue());
        add(hbox, BorderLayout.SOUTH);
    }


    /**
     * Adds buttons to button panel
     * @param p  the button panel
     */
    private void addButtons(JPanel p) {
        keys = new JButton[14];
        for (int i = 0; i < 7; i++) {
            keys[i] = new JButton(Integer.toString(i + 1));
            keys[i].addActionListener(keyHandler);
            p.add(keys[i]);
        }
        keys[8] = new JButton("c");
        keys[8].addActionListener(keyHandler);
        p.add(keys[8]);
        keys[9] = new JButton("l");
        keys[9].addActionListener(keyHandler);
        p.add(keys[9]);
        keys[10] = new JButton("q");
        keys[10].addActionListener(keyHandler);
        p.add(keys[10]);
        keys[11] = new JButton("r");
        keys[11].addActionListener(keyHandler);
        p.add(keys[11]);
        keys[12] = new JButton("s");
        keys[12].addActionListener(keyHandler);
        p.add(keys[12]);

    }


    /**
     * A listener for key events.
     */
    private class ClickHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton src = (JButton) e.getSource();

        }
    }

    @Override
    public void keyPressed(KeyEvent ke) {

    }

    @Override
    public void keyReleased(KeyEvent ke) {

    }

    @Override
    public void keyTyped(KeyEvent ke) {
        char key = ke.getKeyChar();

        if (key == '0') {
            keys[10].doClick();
        } else if (key > '0' && key <= '9') {
            keys[ke.getKeyChar() - '1'].doClick();
        }

    }
}

