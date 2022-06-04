package front.src;

import back.controller.auth.AuthController;
import back.controller.auth.IAuthController;
import back.repo.domain.Role;
import back.service.auth.AuthenticationException;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

public class LibrarianPanel extends JPanel {
    public static final BookCheckoutPanel bookCheckoutPanel = BookCheckoutPanel.INSTANCE;
    public static final PrintCheckoutPanel printCheckoutPanel = PrintCheckoutPanel.INSTANCE;
    private final JPanel workingPanel;
    private final JList<String> menuList;
    public static final LibrarianPanel INSTANCE = new LibrarianPanel();
    private final IAuthController authController = AuthController.getInstance();

    LibrarianPanel() {
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));

        DefaultListModel<String> items = new DefaultListModel<>();
        items.addElement("Checkout a book");
        items.addElement("Print checkout records");
        menuList = new JList<>(items);
        menuList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        menuList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        menuList.addListSelectionListener(new MenuListListener());
        this.add(menuList, BorderLayout.WEST);

        workingPanel = new JPanel(new CardLayout());
        workingPanel.add(bookCheckoutPanel);
        workingPanel.add(printCheckoutPanel);
        workingPanel.setVisible(false);
        this.add(workingPanel, BorderLayout.CENTER);
        this.setVisible(false);
    }

    private class MenuListListener implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (!e.getValueIsAdjusting()) {
                String menuChoice = menuList.getSelectedValue();
                if (menuChoice == null) {
                    return;
                }
                switch (menuChoice) {
                    case "Checkout a book" -> {
                        printCheckoutPanel.setVisible(false);
                        bookCheckoutPanel.setVisible(true);
                    }
                    case "Print checkout records" -> {
                        bookCheckoutPanel.setVisible(false);
                        try {
                            if (!authController.hasAccess(Role.ADMIN)) {
                                printCheckoutPanel.memberIDField.setEditable(false);
                                printCheckoutPanel.memberIDField.setText(authController.getAuthorizedMemberId());
                            } else {
                                printCheckoutPanel.memberIDField.setEditable(true);
                                printCheckoutPanel.memberIDField.setText("");
                                printCheckoutPanel.memberIDField.requestFocus();
                            }
                            printCheckoutPanel.performPrint();
                        } catch (AuthenticationException ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage());
                            return;
                        }
                        printCheckoutPanel.setVisible(true);
                    }
                }
                workingPanel.setVisible(true);
            }
        }
    }
}
