package front.src;

import back.controller.book.BookController;
import back.controller.book.IBookController;
import back.repo.dataaccess.EntityNotFoundException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class AddBookPanel extends JPanel {
    public static JTextField isbnField = new JTextField(10);
    public static JTextField titleField = new JTextField(10);
    public static final AddBookPanel INSTANCE = new AddBookPanel();
    private final IBookController bookController = BookController.getInstance();

    AddBookPanel() {
        this.setLayout(new GridLayout(19, 1, 10, 10));

        this.add(new JPanel());
        this.add(new JPanel());
        this.add(new JPanel());

        JPanel isbnPanel = new JPanel();
        isbnPanel.add(new JLabel("Book ISBN"));
        isbnPanel.add(isbnField);
        this.add(isbnPanel);

        JPanel titlePanel = new JPanel();
        titlePanel.add(new JLabel("Book title"));
        titlePanel.add(titleField);
        this.add(titlePanel);

        JPanel addPanel = new JPanel();
        JButton addButton = new JButton("Add book");
        addButton.addActionListener(new AddButtonListener());
        addPanel.add(addButton);
        this.add(addPanel);

        this.add(new JPanel());
        this.add(new JPanel());
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
        if (isbnText.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Book ISBN cannot empty!");
            return;
        }
        if (titleText.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Book title cannot empty!");
            return;
        }
        try {
            bookController.addCopy(isbnText);
        } catch (EntityNotFoundException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return;
        }
        JOptionPane.showMessageDialog(null, "Book added");
        isbnField.setText("");
        titleField.setText("");
    }
}
