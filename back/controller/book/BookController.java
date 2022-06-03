package back.controller.book;

import back.repo.dataaccess.EntityNotFoundException;
import back.repo.domain.Author;
import back.repo.domain.BorrowDaysType;
import back.service.auth.AuthenticationException;
import back.service.book.BookService;
import back.service.book.IBookService;

import java.util.List;

public class BookController implements IBookController {

    private static final BookController INSTANCE = new BookController();
    private final IBookService bookService;

    public BookController() {
        bookService = BookService.getInstance();
    }

    @Override
    public void addNewBook() {

    }

    @Override
    public void addCopy(String isbn) throws EntityNotFoundException {
        bookService.addBookCopy(isbn);
    }

    @Override
    public List<Author> getAuthors() {
        return bookService.getAuthors();
    }

    @Override
    public void createBook(String isbn, String title, BorrowDaysType borrowDaysType, List<Author> authors, int copies) throws AuthenticationException {
        bookService.createBook(isbn, title, borrowDaysType, authors, copies);
    }

    public static BookController getInstance() {
        return INSTANCE;
    }
}
