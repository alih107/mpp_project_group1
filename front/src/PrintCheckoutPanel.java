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
import java.util.List;

public class PrintCheckoutPanel extends JPanel {
    String[] COLUMNS = {"ISBN", "Title", "Book Copy ID", "Library member ID", "Checkout Date", "Due date"};
    public JTextField memberIDField = new JTextField(10);
    public final static JTable recordsTable = new JTable();
    public final static PrintCheckoutPanel INSTANCE = new PrintCheckoutPanel();
    private final ICheckoutController checkoutController = CheckoutController.getInstance();

    PrintCheckoutPanel() {
        this.setLayout(new BorderLayout());

        JPanel printPanel = new JPanel();
        printPanel.add(new JLabel("Member ID"));
        memberIDField.addKeyListener(new TextFieldListener());
        printPanel.add(memberIDField);
        JButton printButton = new JButton("Print");
        printButton.addActionListener(new PrintButtonListener());
        printPanel.add(printButton);
        this.add(printPanel, BorderLayout.NORTH);

        this.add(new JScrollPane(recordsTable), BorderLayout.CENTER);
        this.setVisible(false);
    }

    public static class TableModelGenerator {
        public static DefaultTableModel getEmptyModel() {
            return new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
        }

        public static DefaultTableModel getModel(Object[][] data, Object[] cols) {
            return new DefaultTableModel(data, cols) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
        }
    }

    private class PrintButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            performPrint();
        }
    }

    private class TextFieldListener implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode()==KeyEvent.VK_ENTER){
                performPrint();
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }

    public void performPrint() {
        String[][] data;
        List<CheckoutRecord> res;
        try {
            res = checkoutController.searchCheckouts(CheckoutSearchFilter.createByMemberFilter(memberIDField.getText()));
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
            data[i][4] = c.getCheckoutDate().toString();
            data[i][5] = c.getDueDate().toString();
        }
    }

    public void resetPanel() {
        recordsTable.setModel(TableModelGenerator.getEmptyModel());
        memberIDField.setText("");
        memberIDField.setEditable(true);
    }
}
