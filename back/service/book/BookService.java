package back.service.book;

import back.repo.dataaccess.EntityNotFoundException;
import back.repo.domain.Book;
import back.repo.domain.CheckoutRecord;
import back.repo.domain.LibraryMember;
import back.service.BaseService;
import back.service.auth.AuthService;
import back.service.auth.IAuthService;

import java.time.LocalDate;
import java.util.HashMap;

public class BookService extends BaseService implements IBookService {

    private static final BookService INSTANCE = new BookService();
    private final IAuthService authService;

    public BookService() {
        authService = AuthService.getInstance();
    }

    @Override
    public void addNewBook() {

    }

    @Override
    public void addBookCopy(String isbn) throws EntityNotFoundException {
        Book book = findBook(isbn);
        book.addCopy();

        dataAccess.saveBook(book);
    }

    @Override
    public void checkout(String memberId, String isbn) throws EntityNotFoundException, BookNotAvailableException {
        LibraryMember member = authService.findById(memberId);
        Book book = findBook(isbn);

        if (book.getNextAvailableCopy() == null) {
            throw new BookNotAvailableException();
        }

        LocalDate now = LocalDate.now();
        LocalDate dueDate = LocalDate.now().plusDays(book.getMaxCheckoutLength());

        dataAccess.addNewCheckoutRecord(CheckoutRecord.createCheckoutRecord(member, book.getNextAvailableCopy(), now, dueDate));
    }

    @Override
    public Book findBook(String isbn) throws EntityNotFoundException {
        HashMap<String, Book> booksMap = dataAccess.readBooksMap();
        if (!booksMap.containsKey(isbn)) {
            throw new EntityNotFoundException(String.format("A book with this isbn: %s not found!", isbn));
        }
        return booksMap.get(isbn);
    }

    public static BookService getInstance() {
        return INSTANCE;
    }
}
