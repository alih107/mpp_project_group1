package front.src;

import back.controller.book.CheckoutController;
import back.controller.book.ICheckoutController;
import back.repo.domain.Book;
import back.repo.domain.BookCopy;
import back.repo.domain.CheckoutRecord;
import back.service.auth.AuthenticationException;
import back.service.book.CheckoutSearchFilter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.nio.file.AccessDeniedException;
import java.time.LocalDate;
import java.util.List;

public class OverdueRecordsPanel extends JPanel {
    String[] COLUMNS = {"ISBN", "Title", "Book Copy ID", "Library Member ID", "Due date", "Status"};
    public JTextField isbnField = new JTextField(10);
    private final JTable recordsTable;
    public final static OverdueRecordsPanel INSTANCE = new OverdueRecordsPanel();
    private final ICheckoutController checkoutController = CheckoutController.getInstance();

    OverdueRecordsPanel() {
        this.setLayout(new BorderLayout());

        JPanel printPanel = new JPanel();
        printPanel.add(new JLabel("Book ISBN"));
        printPanel.addKeyListener(new TextFieldListener());
        printPanel.add(isbnField);
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(new SearchButtonListener());
        printPanel.add(searchButton);
        this.add(printPanel, BorderLayout.NORTH);

        recordsTable = new JTable();
        this.add(new JScrollPane(recordsTable), BorderLayout.CENTER);
        this.setVisible(false);
    }

    private static class TableModelGenerator {
        public static DefaultTableModel getModel(Object[][] data, Object[] cols) {
            return new DefaultTableModel(data, cols) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
        }
    }

    private class SearchButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            performOverdueSearch();
        }
    }

    private class TextFieldListener implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode()==KeyEvent.VK_ENTER){
                performOverdueSearch();
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }

    public void performOverdueSearch() {
        String[][] data;
        List<CheckoutRecord> res;
        try {
            res = checkoutController.searchCheckouts(CheckoutSearchFilter.createByIsbn(isbnField.getText()));

        } catch (AccessDeniedException | AuthenticationException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return;
        }
        int resLen = res.size();
        data = new String[resLen][6];
        fillData(data, res, resLen);
        recordsTable.setModel(TableModelGenerator.getModel(data, COLUMNS));
    }

    private void fillData(String[][] data, List<CheckoutRecord> res, int resLen) {
        for (int i = 0; i < resLen; i++) {
            CheckoutRecord c = res.get(i);
            BookCopy bc = c.getBookCopy();
            Book b = bc.getBook();
            data[i][0] = b.getIsbn();
            data[i][1] = b.getTitle();
            data[i][2] = String.valueOf(bc.getCopyNum());
            data[i][3] = c.getMember().getMemberId();
            data[i][4] = c.getDueDate().toString();
            data[i][5] = "";
            if (LocalDate.now().isAfter(c.getDueDate()) && !bc.isAvailable()) {
                data[i][5] = "Overdue";
            }
        }
    }
}
