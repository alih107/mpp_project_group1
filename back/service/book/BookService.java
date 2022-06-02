package back.service.book;

import back.dataaccess.EntityNotFoundException;
import back.repo.domain.Book;
import back.service.BaseService;

import java.util.HashMap;

public class BookService extends BaseService implements IBookService {

    private static final BookService INSTANCE = new BookService();

    @Override
    public void addNewBook() {

    }

    @Override
    public void addBookCopy(String isbn) throws EntityNotFoundException {
        HashMap<String, Book> booksMap = dataAccess.readBooksMap();

        if (!booksMap.containsKey(isbn)) {
            throw new EntityNotFoundException(String.format("A book with this isbn: %s not found!", isbn));
        }

        Book book = booksMap.get(isbn);
        book.addCopy();

        dataAccess.saveBook(book);
    }

    public static BookService getInstance() {
        return INSTANCE;
    }
}
