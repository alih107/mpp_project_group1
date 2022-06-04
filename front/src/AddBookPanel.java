package front.src;

import back.controller.book.BookController;
import back.controller.book.IBookController;
import back.repo.domain.Author;
import back.repo.domain.BorrowDaysType;
import back.service.EntityExistException;
import back.service.auth.AuthenticationException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class AddBookPanel extends JPanel {
    private static final int FIELD_LENGTH = 10;
    private static final JTextField isbnField = new JTextField(FIELD_LENGTH);
    private static final JTextField titleField = new JTextField(FIELD_LENGTH);
    private final JList<String> authorList;
    private final JComboBox<BorrowDaysType> borrowTypeBox;
    private static final JTextField numOfCopiesField = new JTextField(3);
    private final IBookController bookController = BookController.getInstance();
    private static List<Author> allAuthors;
    public static final AddBookPanel INSTANCE = new AddBookPanel();

    AddBookPanel() {
        this.setLayout(new GridLayout(8, 1, 10, 10));

        this.add(new JPanel());

        JPanel isbnPanel = new JPanel();
        isbnPanel.add(new JLabel("Book ISBN"));
        isbnPanel.add(isbnField);
        this.add(isbnPanel);

        JPanel titlePanel = new JPanel();
        titlePanel.add(new JLabel("Book title"));
        titlePanel.add(titleField);
        this.add(titlePanel);

        JPanel authorListPanel = new JPanel();
        DefaultListModel<String> items = new DefaultListModel<>();
        allAuthors = bookController.getAuthors();
        for (Author a : allAuthors) {
            items.addElement(a.getFirstName() + " ; " + a.getLastName() + " ; " + a.getTelephone());
        }
        authorList = new JList<>(items);
        authorList.setLayoutOrientation(JList.VERTICAL);
        authorList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        authorListPanel.add(new JScrollPane(authorList));
        this.add(authorListPanel);

        JPanel borrowTypePanel = new JPanel();
        BorrowDaysType[] blist = {BorrowDaysType.SEVEN, BorrowDaysType.TWENTY_ONE};
        borrowTypeBox = new JComboBox<>(blist);
        borrowTypePanel.add(new JLabel("Max checkout length"));
        borrowTypePanel.add(borrowTypeBox);
        this.add(borrowTypePanel);

        JPanel numOfCopiesPanel = new JPanel();
        numOfCopiesPanel.add(new JLabel("Number of copies"));
        numOfCopiesPanel.add(numOfCopiesField);
        this.add(numOfCopiesPanel);

        JPanel addPanel = new JPanel();
        JButton addButton = new JButton("Add book");
        addButton.addActionListener(new AddButtonListener());
        addPanel.add(addButton);
        this.add(addPanel);

        this.add(new JPanel());

        this.setVisible(true);
    }

    private class AddButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            performAddBook();
        }
    }

    private void performAddBook() {
        String isbnText = isbnField.getText();
        String titleText = titleField.getText();
        String numOfCopiesText = numOfCopiesField.getText();

        List<Author> selectedAuthors = new ArrayList<>();
        for (String s : authorList.getSelectedValuesList()) {
            String[] authorStrs = s.split(";");
            for (Author a : allAuthors) {
                if (authorStrs[0].trim().equals(a.getFirstName()) &&
                        authorStrs[1].trim().equals(a.getLastName()) &&
                        authorStrs[2].trim().equals(a.getTelephone())) {
                    selectedAuthors.add(a);
                    break;
                }

            }
        }

        BorrowDaysType bdt = (BorrowDaysType) borrowTypeBox.getSelectedItem();
        String errorString = validateFields(isbnText, titleText, selectedAuthors, numOfCopiesText);
        if (!errorString.isBlank()) {
            JOptionPane.showMessageDialog(null, errorString);
            return;
        }
        int numOfCopies = Integer.parseInt(numOfCopiesText);
        try {
            bookController.createBook(isbnText, titleText, bdt, selectedAuthors, numOfCopies);
        } catch (AuthenticationException | EntityExistException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return;
        }
        JOptionPane.showMessageDialog(null, "Book added");
        isbnField.setText("");
        titleField.setText("");
        numOfCopiesField.setText("");
        authorList.clearSelection();
    }

    private String validateFields(String isbnText, String titleText, List<Author> selectedAuthors, String numOfCopiesText) {
        StringBuilder sb = new StringBuilder();
        if (isbnText.isEmpty()) {
            sb.append("Book ISBN cannot empty!\n");
        }
        if (titleText.isEmpty()) {
            sb.append("Book title cannot empty!\n");
        }
        if (selectedAuthors.size() <= 0) {
            sb.append("You have to choose at least 1 author!\n");
        }

        int numOfCopies = 0;
        try {
            numOfCopies = Integer.parseInt(numOfCopiesText);
        } catch (NumberFormatException e) {
            sb.append("Number of copies should be a number!\n");
        }
        if (numOfCopies <= 0) {
            sb.append("Number of copies must be at least 1\n");
        }
        return sb.toString();
    }
}
