import javax.swing.*;

public class AdministratorPanel extends JPanel {
    public static final AdministratorPanel INSTANCE = new AdministratorPanel();

    AdministratorPanel() {
        this.add(new JLabel("Administrator"));
        this.setVisible(false);
    }
}
