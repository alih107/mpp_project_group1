package front.src;

import back.controller.member.IMemberController;
import back.controller.member.MemberController;
import back.repo.domain.Address;
import back.repo.domain.LibraryMember;
import back.repo.domain.Role;
import back.service.EntityExistException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

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
    public static JPasswordField passField = new JPasswordField(FIELD_LENGTH);
    public static JCheckBox librarianCheckBox = new JCheckBox("Librarian");
    public static JCheckBox adminCheckBox = new JCheckBox("Administrator");
    public static final AddMemberPanel INSTANCE = new AddMemberPanel();
    private final IMemberController memberController = MemberController.getInstance();

    AddMemberPanel() {
        this.setLayout(new GridLayout(16, 1, 10, 10));

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

        JPanel passPanel = new JPanel();
        passPanel.add(new JLabel("Password"));
        passPanel.add(passField);
        this.add(passPanel);

        JPanel rolesPanel = new JPanel();
        rolesPanel.add(librarianCheckBox);
        rolesPanel.add(adminCheckBox);
        this.add(rolesPanel);

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
            performSaveMember();
        }
    }

    private void performSaveMember() {
        StringBuilder sb = new StringBuilder();

        // fields validation
        String memberIDText = memberIDField.getText();
        if (memberIDText.isBlank()) {
            sb.append("Member ID cannot be empty\n");
        }

        String firstNameText = firstNameField.getText();
        if (firstNameText.isBlank()) {
            sb.append("First name cannot be empty\n");
        }

        String passText = passField.getText();
        if (passText.isBlank()) {
            sb.append("Password cannot be empty\n");
        }
        if (!sb.isEmpty()) {
            JOptionPane.showMessageDialog(null, sb.toString());
            return;
        }
        List<Role> roles = new ArrayList<>();
        if (librarianCheckBox.isSelected()) {
            roles.add(Role.LIBRARIAN);
        }
        if (adminCheckBox.isSelected()) {
            roles.add(Role.ADMIN);
        }
        LibraryMember lm = new LibraryMember(
                memberIDText, roles, lastNameField.getText(), telephoneField.getText(),
                new Address(streetField.getText(), cityField.getText(), stateField.getText(), zipField.getText()),
                firstNameText
        );

        try {
            memberController.createNewMember(lm, passText);
        } catch (EntityExistException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return;
        }

        JOptionPane.showMessageDialog(null, "Member saved!");
        memberIDField.setText("");
        firstNameField.setText("");
        lastNameField.setText("");
        telephoneField.setText("");
        streetField.setText("");
        cityField.setText("");
        stateField.setText("");
        zipField.setText("");
        passField.setText("");
        librarianCheckBox.setSelected(false);
        adminCheckBox.setSelected(false);
    }
}
