package back.controller.book;

import back.repo.dataaccess.EntityNotFoundException;
import back.service.book.BookNotAvailableException;
import back.service.book.BookService;
import back.service.book.IBookService;

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
    public void checkout(String memberId, String isbn) throws EntityNotFoundException, BookNotAvailableException {
        bookService.checkout(memberId, isbn);
    }

    public static BookController getInstance() {
        return INSTANCE;
    }
}
