package front.src;

import back.controller.auth.AuthController;
import back.controller.auth.IAuthController;
import back.service.auth.AuthenticationException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class LoginPanel extends JPanel {
    public static final LoginPanel INSTANCE = new LoginPanel();
    private final JTextField userField = new JTextField(10);
    private final JPasswordField passField = new JPasswordField(10);
    private final JLabel messageLabel = new JLabel();
    private final JLabel warningLabel = new JLabel();
    private final IAuthController authController = AuthController.getInstance();
    public static final PrintCheckoutPanel printCheckoutPanel = PrintCheckoutPanel.INSTANCE;

    LoginPanel() {
        this.setLayout(new GridLayout(17, 1, 10, 10));

        this.add(new JPanel());
        this.add(new JPanel());
        this.add(new JPanel());
        this.add(new JPanel());
        this.add(new JPanel());
        this.add(new JPanel());
        this.add(new JPanel());

        JPanel usernamePanel = new JPanel();
        usernamePanel.add(new JLabel("username"));
        usernamePanel.add(userField);
        userField.addKeyListener(new TextFieldListener());
        this.add(usernamePanel);

        JPanel passPanel = new JPanel();
        passPanel.add(new JLabel("password"));
        passPanel.add(passField);
        passField.addKeyListener(new TextFieldListener());
        this.add(passPanel);

        JPanel buttonPanel = new JPanel();
        JButton submitButton = new JButton("Login");
        submitButton.addActionListener(new SubmitButtonListener());
        buttonPanel.add(submitButton);
        this.add(buttonPanel);


        JPanel messagePanel = new JPanel();

        messagePanel.add(messageLabel);
        this.add(messagePanel);

        JPanel warningPanel = new JPanel();
        warningPanel.add(warningLabel);
        warningLabel.setForeground(Color.MAGENTA);
        this.add(warningPanel);

        this.add(new JPanel());
        this.add(new JPanel());
        this.add(new JPanel());
        this.add(new JPanel());
        this.add(new JPanel());

        this.setVisible(true);
    }

    private class SubmitButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            performLogin();
        }

    }

    private class TextFieldListener implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode()==KeyEvent.VK_ENTER) {
                performLogin();
            } else if (e.getKeyCode()==KeyEvent.VK_CAPS_LOCK) {
                if (warningLabel.getText().isBlank()) {
                    warningLabel.setText("You turned on CAPS LOCK");
                } else {
                    warningLabel.setText("");
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }

    void performLogin() {
        try {
            authController.login(userField.getText(), String.valueOf(passField.getPassword()));
            messageLabel.setText("");
            userField.setText("");
            passField.setText("");
            printCheckoutPanel.resetPanel();
            LibraryFrame.loginPanel.setVisible(false);
            LibraryFrame.authorizedPanel.setVisible(true);
        } catch (AuthenticationException e) {
            if (messageLabel.getForeground() == Color.RED) {
                messageLabel.setForeground(Color.ORANGE);
            } else {
                messageLabel.setForeground(Color.RED);
            }
            messageLabel.setText(e.getMessage());
        }
    }
}
