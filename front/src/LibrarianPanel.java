import javax.swing.*;

public class LibrarianPanel extends JPanel {
    public static final LibrarianPanel INSTANCE = new LibrarianPanel();

    LibrarianPanel() {
        this.add(new JLabel("Librarian"));
        this.setVisible(false);
    }
}
