package windows;

import javax.swing.*;
import java.awt.*;

public class LibraryFrame extends JFrame {
    public static final JPanel loginPanel = LoginPanel.INSTANCE;
    public static final JPanel systemPanel = SystemPanel.INSTANCE;

    public LibraryFrame() {

        setTitle("Library Group 1");
        setSize(640, 480);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container c = getContentPane();
        JPanel mainPanel = new JPanel(new CardLayout());
        mainPanel.add(loginPanel, "login");
        mainPanel.add(systemPanel, "system");
        c.add(mainPanel);
    }
}
