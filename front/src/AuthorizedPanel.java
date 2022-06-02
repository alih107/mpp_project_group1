package front.src;

import back.controller.auth.AuthController;
import back.controller.auth.IAuthController;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AuthorizedPanel extends JPanel {
    public static final LibrarianPanel librarianPanel = LibrarianPanel.INSTANCE;
    public static final AdministratorPanel administratorPanel = AdministratorPanel.INSTANCE;
    public static final AuthorizedPanel INSTANCE = new AuthorizedPanel();
    public static JPanel workingPanel;
    private static JList<String> menuList;
    private final IAuthController authController = AuthController.getInstance();

    AuthorizedPanel() {
        this.setLayout(new BorderLayout());

        DefaultListModel<String> items = new DefaultListModel<>();
        items.addElement("Librarian");
        items.addElement("Administrator");
        menuList = new JList<>(items);
        menuList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        menuList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        menuList.addListSelectionListener(new MenuListListener());
        this.add(menuList, BorderLayout.WEST);

        workingPanel = new JPanel(new CardLayout());
        workingPanel.add(administratorPanel);
        workingPanel.add(librarianPanel);
        workingPanel.setVisible(false);
        this.add(workingPanel, BorderLayout.CENTER);

        JPanel logoutPanel = new JPanel();
        logoutPanel.setLayout(new GridLayout(1, 6, 10, 10));
        logoutPanel.add(new JPanel());
        logoutPanel.add(new JPanel());
        logoutPanel.add(new JPanel());
        logoutPanel.add(new JPanel());
        logoutPanel.add(new JPanel());

        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(new LogoutButtonListener());
        logoutPanel.add(logoutButton);
        this.add(logoutPanel, BorderLayout.NORTH);
        this.setVisible(false);
    }

    private class LogoutButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            authController.logout();
            LibraryFrame.loginPanel.setVisible(true);
            LibraryFrame.systemPanel.setVisible(false);
        }
    }

    private static class MenuListListener implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (!e.getValueIsAdjusting()) {
                workingPanel.setVisible(true);
                String menuChoice = menuList.getSelectedValue();
                switch (menuChoice) {
                    case "Librarian" -> {
                        administratorPanel.setVisible(false);
                        librarianPanel.setVisible(true);
                        CheckoutPanel.memberIDField.requestFocus();
                    }
                    case "Administrator" -> {
                        administratorPanel.setVisible(true);
                        librarianPanel.setVisible(false);
                    }
                }
            }
        }
    }
}
