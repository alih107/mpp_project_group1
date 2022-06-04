import back.repo.dataaccess.DataAccess;
import back.repo.dataaccess.DataAccessFacade;
import front.src.LibraryFrame;

import javax.swing.SwingUtilities;

public class Start {
    public static void main(String[] args) {
        DataAccess da = new DataAccessFacade();
        System.out.println("========================================================================");
        System.out.println(da.readUserMap());
        System.out.println("========================================================================");
        System.out.println(da.readBooksMap());
        System.out.println("========================================================================");
        System.out.println(da.readMemberMap());
        SwingUtilities.invokeLater(() -> {
            LibraryFrame.INSTANCE.setVisible(true);
        });
    }
}
