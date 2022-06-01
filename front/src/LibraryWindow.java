import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LibraryWindow extends JFrame {
    private JTextField userField;
    private JTextField passField;
    private JLabel messageLabel;

    LibraryWindow() {
        setSize(640, 480);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container c = getContentPane();
        c.add(mainPanel());
    }

    private JPanel mainPanel() {
        JPanel mainPanel = new JPanel();

        mainPanel.add(new JLabel("username"));
        userField = new JTextField(10);
        mainPanel.add(userField);

        mainPanel.add(new JLabel("password"));
        passField = new JTextField(10);
        mainPanel.add(passField);

        JButton submitButton = new JButton("Login");
        submitButton.addActionListener(new SubmitButtonListener());
        mainPanel.add(submitButton);

        messageLabel = new JLabel();
        mainPanel.add(messageLabel);

        return mainPanel;
    }

    private class SubmitButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            messageLabel.setForeground(Color.RED);
            messageLabel.setText(userField.getText() + " " + passField.getText());
        }
    }
}
