package front.src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class AddBookCopyPanel extends JPanel {
    public static JTextField isbnField = new JTextField(10);
    public static final AddBookCopyPanel INSTANCE = new AddBookCopyPanel();

    AddBookCopyPanel() {
        setLayout(new BorderLayout());

        JPanel searchPanel = new JPanel();
        searchPanel.add(new JLabel("Book ISBN"));
        searchPanel.add(isbnField);
        isbnField.addKeyListener(new TextFieldListener());

        JButton addButton = new JButton("Add copy");
        addButton.addActionListener(new AddButtonListener());
        searchPanel.add(addButton);

        this.add(searchPanel, BorderLayout.NORTH);
        this.setVisible(true);
    }

    private class AddButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            performAddBookCopy();
        }
    }

    private class TextFieldListener implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode()==KeyEvent.VK_ENTER){
                performAddBookCopy();
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }

    private void performAddBookCopy() {
        // TODO: connect to back
        JOptionPane.showMessageDialog(null, "Book copy added!");
    }
}
