import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddMemberPanel extends JPanel {
    private static final int FIELD_LENGTH = 20;
    public static JTextField memberIDField = new JTextField(FIELD_LENGTH);
    public static JTextField firstNameField = new JTextField(FIELD_LENGTH);
    public static JTextField lastNameField = new JTextField(FIELD_LENGTH);
    public static JTextField telephoneField = new JTextField(FIELD_LENGTH);
    public static JTextField streetField = new JTextField(FIELD_LENGTH);
    public static JTextField cityField = new JTextField(FIELD_LENGTH);
    public static JTextField stateField = new JTextField(FIELD_LENGTH);
    public static JTextField zipField = new JTextField(FIELD_LENGTH);
    public static final AddMemberPanel INSTANCE = new AddMemberPanel();

    AddMemberPanel() {
        this.setLayout(new GridLayout(15, 1, 10, 10));

        this.add(new JPanel());
        this.add(new JPanel());
        this.add(new JPanel());

        JPanel memberIDPanel = new JPanel();
        memberIDPanel.add(new JLabel("Member ID"));
        memberIDPanel.add(memberIDField);
        this.add(memberIDPanel);

        JPanel firstNamePanel = new JPanel();
        firstNamePanel.add(new JLabel("First name"));
        firstNamePanel.add(firstNameField);
        this.add(firstNamePanel);

        JPanel lastNamePanel = new JPanel();
        lastNamePanel.add(new JLabel("Last name"));
        lastNamePanel.add(lastNameField);
        this.add(lastNamePanel);

        JPanel telephonePanel = new JPanel();
        telephonePanel.add(new JLabel("Telephone"));
        telephonePanel.add(telephoneField);
        this.add(telephonePanel);

        JPanel streetPanel = new JPanel();
        streetPanel.add(new JLabel("Street"));
        streetPanel.add(streetField);
        this.add(streetPanel);

        JPanel cityPanel = new JPanel();
        cityPanel.add(new JLabel("City"));
        cityPanel.add(cityField);
        this.add(cityPanel);

        JPanel statePanel = new JPanel();
        statePanel.add(new JLabel("State"));
        statePanel.add(stateField);
        this.add(statePanel);

        JPanel zipPanel = new JPanel();
        zipPanel.add(new JLabel("Zip"));
        zipPanel.add(zipField);
        this.add(zipPanel);

        JPanel savePanel = new JPanel();
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new SaveButtonListener());
        savePanel.add(saveButton);
        this.add(savePanel);

        this.add(new JPanel());
        this.add(new JPanel());
        this.add(new JPanel());
    }

    private class SaveButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null, "Member saved!");
        }
    }
}
