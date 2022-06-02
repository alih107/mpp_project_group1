package front.src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class CheckoutPanel extends JPanel {
    public static JTextField memberIDField = new JTextField(10);
    public static JTextField isbnField = new JTextField(10);
    public static final CheckoutPanel INSTANCE = new CheckoutPanel();

    CheckoutPanel() {
        setLayout(new BorderLayout());

        JPanel searchPanel = new JPanel();
        searchPanel.add(new JLabel("Member ID"));
        memberIDField.addKeyListener(new TextFieldListener());
        searchPanel.add(memberIDField);

        searchPanel.add(new JLabel("Book ISBN"));
        searchPanel.add(isbnField);
        isbnField.addKeyListener(new TextFieldListener());

        JButton checkoutButton = new JButton("Checkout");
        checkoutButton.addActionListener(new CheckoutButtonListener());
        searchPanel.add(checkoutButton);

        this.add(searchPanel, BorderLayout.NORTH);

        this.setVisible(true);
    }

    private class CheckoutButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            performSearch();
        }
    }

    private class TextFieldListener implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode()==KeyEvent.VK_ENTER){
                performSearch();
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }

    private void performSearch() {
        // TODO: connect to back
        JOptionPane.showMessageDialog(null, "Search is being performed...");
    }
}
