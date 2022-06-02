package front.src;

import javax.swing.*;
import java.awt.*;

public class LibrarianPanel extends JPanel {
    public static final CheckoutPanel checkoutPanel = CheckoutPanel.INSTANCE;
    public static final LibrarianPanel INSTANCE = new LibrarianPanel();

    LibrarianPanel() {
        this.setLayout(new BorderLayout());

        DefaultListModel<String> items = new DefaultListModel<>();
        items.addElement("Checkout a book");
        JList<String> menuList = new JList<>(items);
        menuList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        menuList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.add(menuList, BorderLayout.WEST);

        this.add(checkoutPanel, BorderLayout.CENTER);
        this.setVisible(false);
    }
}
