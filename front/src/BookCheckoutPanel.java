package front.src;

import back.controller.book.CheckoutController;
import back.controller.book.ICheckoutController;
import back.repo.dataaccess.EntityNotFoundException;
import back.service.auth.AuthenticationException;
import back.service.book.BookNotAvailableException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.nio.file.AccessDeniedException;

public class BookCheckoutPanel extends JPanel {
    public static JTextField memberIDField = new JTextField(10);
    public static JTextField isbnField = new JTextField(10);
    public static final BookCheckoutPanel INSTANCE = new BookCheckoutPanel();
    private final ICheckoutController checkoutController = CheckoutController.getInstance();

    BookCheckoutPanel() {
        this.setLayout(new BorderLayout());

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
            performCheckout();
        }
    }

    private class TextFieldListener implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode()==KeyEvent.VK_ENTER){
                performCheckout();
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }

    private void performCheckout() {
        String memberIDText = memberIDField.getText();
        String isbnText = isbnField.getText();
        if (memberIDText.isBlank()) {
            JOptionPane.showMessageDialog(null, "Member ID cannot be empty");
            return;
        }
        if (isbnText.isBlank()) {
            JOptionPane.showMessageDialog(null, "ISBN cannot be empty");
            return;
        }
        try {
            checkoutController.checkout(memberIDText, isbnText);
        } catch (EntityNotFoundException | BookNotAvailableException | AccessDeniedException | AuthenticationException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return;
        }
        JOptionPane.showMessageDialog(null, "Book is checked out");
    }
}
