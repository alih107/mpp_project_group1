package back.controller.book;

import back.dataaccess.EntityNotFoundException;
import back.service.book.BookService;
import back.service.book.IBookService;

public class BookController implements IBookController {

    private static final BookController INSTANCE = new BookController();
    private final IBookService bookService = BookService.getInstance();

    @Override
    public void addNewBook() {

    }

    @Override
    public void addCopy(String isbn) throws EntityNotFoundException {
        bookService.addBookCopy(isbn);
    }

    public static BookController getInstance() {
        return INSTANCE;
    }
}
