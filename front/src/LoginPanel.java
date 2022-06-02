import javax.swing.*;
import javax.swing.border.EmptyBorder;
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

    LoginPanel() {
        this.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.setLayout(new GridLayout(6, 1, 10, 10));

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

        this.add(new JPanel());

        this.setVisible(true);
    }

    private class SubmitButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            performLogin();
            LibraryFrame.loginPanel.setVisible(false);
            LibraryFrame.systemPanel.setVisible(true);
        }

    }

    private class TextFieldListener implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode()==KeyEvent.VK_ENTER){
                performLogin();
                messageLabel.setText("Invalid username and/or password");
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }

    void performLogin() {
        messageLabel.setForeground(Color.RED);
        messageLabel.setText(userField.getText() + " " + String.valueOf(passField.getPassword()));
    }
}
