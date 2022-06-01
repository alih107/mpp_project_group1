import javax.swing.*;

public class Start {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LibraryWindow lw = new LibraryWindow();
            lw.setVisible(true);
        });
    }
}
