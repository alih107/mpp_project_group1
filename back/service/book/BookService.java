package back.service.book;

import back.repo.dataaccess.EntityNotFoundException;
import back.repo.dataaccess.TestData;
import back.repo.domain.Author;
import back.repo.domain.Book;
import back.repo.domain.BookCopy;
import back.repo.domain.BorrowDaysType;
import back.repo.domain.CheckoutRecord;
import back.repo.domain.LibraryMember;
import back.repo.domain.Role;
import back.service.BaseService;
import back.service.auth.AuthService;
import back.service.auth.AuthenticationException;
import back.service.auth.IAuthService;
import back.service.member.IMemberService;
import back.service.member.MemberService;

import java.nio.file.AccessDeniedException;
import java.time.LocalDate;
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
    public void checkout(String memberId, String isbn) throws EntityNotFoundException, BookNotAvailableException, AuthenticationException,
            AccessDeniedException {
        LibraryMember member = memberService.findById(memberId);

        if (!authService.hasAccess(member.getMemberId(), Role.LIBRARIAN)) {
            throw new AccessDeniedException(String.format("Member id: %s has no access to checkout", member.getMemberId()));
        }

        Book book = findBook(isbn);
        BookCopy bookCopy = book.getNextAvailableCopy();

        if (bookCopy == null) {
            throw new BookNotAvailableException();
        }

        LocalDate now = LocalDate.now();
        LocalDate dueDate = LocalDate.now().plusDays(book.getMaxCheckoutLength());

        dataAccess.addNewCheckoutRecord(CheckoutRecord.createCheckoutRecord(member, bookCopy, now, dueDate));

        bookCopy.changeAvailability();
        dataAccess.saveBook(book);
    }

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
    public void createBook(String isbn, String title, BorrowDaysType borrowDaysType, List<Author> authors, int copies) {

    }

    public static BookService getInstance() {
        return INSTANCE;
    }
}
