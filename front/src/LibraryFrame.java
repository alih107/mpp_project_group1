package front.src;

import javax.swing.*;
import java.awt.*;

public class LibraryFrame extends JFrame {
    public static final JPanel loginPanel = LoginPanel.INSTANCE;
    public static final JPanel authorizedPanel = AuthorizedPanel.INSTANCE;
    public static final LibraryFrame INSTANCE = new LibraryFrame();

    public LibraryFrame() {
        setTitle("Library Group 1");
        setSize(1024, 768);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container c = getContentPane();
        JPanel mainPanel = new JPanel(new CardLayout());
        mainPanel.add(loginPanel);
        mainPanel.add(authorizedPanel);
        c.add(mainPanel);
    }
}
