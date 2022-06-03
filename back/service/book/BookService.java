package back.service.book;

import back.repo.dataaccess.EntityNotFoundException;
import back.repo.domain.Book;
import back.repo.domain.BookCopy;
import back.repo.domain.CheckoutRecord;
import back.repo.domain.LibraryMember;
import back.service.BaseService;
import back.service.member.IMemberService;
import back.service.member.MemberService;

import java.time.LocalDate;
import java.util.HashMap;

public class BookService extends BaseService implements IBookService {

    private static final BookService INSTANCE = new BookService();
    private final IMemberService memberService;

    public BookService() {
        memberService = MemberService.getInstance();
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
        LibraryMember member = memberService.findById(memberId);
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
