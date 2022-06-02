import front.src.LibraryFrame;

import javax.swing.SwingUtilities;

public class Start {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LibraryFrame lw = new LibraryFrame();
            lw.setVisible(true);
        });
    }
}
