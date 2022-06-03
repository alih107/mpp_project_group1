package front.src;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

public class AdministratorPanel extends JPanel {
    public static AddMemberPanel addMemberPanel = AddMemberPanel.INSTANCE;
    public static AddBookCopyPanel addBookCopyPanel = AddBookCopyPanel.INSTANCE;
    public static AddBookPanel addBookPanel = AddBookPanel.INSTANCE;
    private final JPanel workingPanel;
    private final JList<String> menuList;
    public static final AdministratorPanel INSTANCE = new AdministratorPanel();

    AdministratorPanel() {
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));

        DefaultListModel<String> items = new DefaultListModel<>();
        items.addElement("Add a book copy");
        items.addElement("Add a book into collection");
        items.addElement("Add a member");
        menuList = new JList<>(items);
        menuList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        menuList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        menuList.addListSelectionListener(new MenuListListener());
        this.add(menuList, BorderLayout.WEST);

        workingPanel = new JPanel(new CardLayout());
        workingPanel.add(addMemberPanel);
        workingPanel.add(addBookCopyPanel);
        workingPanel.add(addBookPanel);
        workingPanel.setVisible(false);
        this.add(workingPanel, BorderLayout.CENTER);

        this.setVisible(false);
    }

    private class MenuListListener implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (!e.getValueIsAdjusting()) {
                workingPanel.setVisible(true);
                String menuChoice = menuList.getSelectedValue();
                switch (menuChoice) {
                    case "Add a book copy" -> {
                        addMemberPanel.setVisible(false);
                        addBookPanel.setVisible(false);
                        addBookCopyPanel.setVisible(true);
                    }
                    case "Add a member" -> {
                        addBookCopyPanel.setVisible(false);
                        addBookPanel.setVisible(false);
                        addMemberPanel.setVisible(true);
                        AddMemberPanel.memberIDField.requestFocus();
                    }
                    case "Add a book into collection" -> {
                        addBookCopyPanel.setVisible(false);
                        addMemberPanel.setVisible(false);
                        addBookPanel.setVisible(true);
                    }
                }
            }
        }
    }
}
