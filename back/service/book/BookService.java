package back.service.book;

import back.repo.dataaccess.EntityNotFoundException;
import back.repo.dataaccess.TestData;
import back.repo.domain.Author;
import back.repo.domain.Book;
import back.repo.domain.BorrowDaysType;
import back.repo.domain.Role;
import back.service.BaseService;
import back.service.auth.AuthService;
import back.service.auth.AuthenticationException;
import back.service.auth.IAuthService;
import back.service.member.IMemberService;
import back.service.member.MemberService;

import java.util.HashMap;
import java.util.List;

public class BookService extends BaseService implements IBookService {

    private static final BookService INSTANCE = new BookService();
    private final IMemberService memberService;
    private final IAuthService authService;

    public BookService() {
        memberService = MemberService.getInstance();
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
    public Book findBook(String isbn) throws EntityNotFoundException {
        HashMap<String, Book> booksMap = dataAccess.readBooksMap();
        if (!booksMap.containsKey(isbn)) {
            throw new EntityNotFoundException(String.format("A book with this isbn: %s not found!", isbn));
        }
        return booksMap.get(isbn);
    }

    @Override
    public List<Author> getAuthors() {
        return new TestData().allAuthors;
    }

    @Override
    public void createBook(String isbn, String title, BorrowDaysType borrowDaysType, List<Author> authors, int copies) throws AuthenticationException {
        authService.hasAccess(Role.ADMIN);

        Book book = new Book(isbn, title, borrowDaysType, authors);
        for (int i = 0; i < copies - 1; i++) {
            book.addCopy();
        }

        dataAccess.saveBook(book);
    }

    public static BookService getInstance() {
        return INSTANCE;
    }
}
