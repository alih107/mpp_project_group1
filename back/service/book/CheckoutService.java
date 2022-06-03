package back.service.book;

import back.repo.dataaccess.EntityNotFoundException;
import back.repo.domain.Book;
import back.repo.domain.BookCopy;
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
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class CheckoutService extends BaseService implements ICheckoutService {

    private static final CheckoutService INSTANCE = new CheckoutService();

    private final IBookService bookService;
    private final IMemberService memberService;
    private final IAuthService authService;

    public CheckoutService() {
        bookService = BookService.getInstance();
        memberService = MemberService.getInstance();
        authService = AuthService.getInstance();
    }

    @Override
    public void checkout(
            String memberId, String isbn
    ) throws EntityNotFoundException, BookNotAvailableException, AuthenticationException, AccessDeniedException {
        LibraryMember member = memberService.findById(memberId);

        if (!authService.hasAccess(member.getMemberId(), Role.LIBRARIAN)) {
            throw new AccessDeniedException(String.format("Member id: %s has no access to checkout", member.getMemberId()));
        }

        Book book = bookService.findBook(isbn);
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
    public List<CheckoutRecord> searchCheckouts(String memberId) throws AuthenticationException {

        if (!authService.hasAccess(Role.ADMIN)) {
            memberId = authService.getAuthorizedMemberId();
        }

        HashMap<UUID, CheckoutRecord> checkoutMap = dataAccess.readCheckoutRecordMap();

        if (checkoutMap.values().isEmpty()) {
            return Collections.emptyList();
        }

        String finalMemberId = memberId;
        return checkoutMap.values().stream()
                .filter(t -> t.getMember().getMemberId().equals(finalMemberId)).collect(Collectors.toList());
    }

    public static CheckoutService getInstance() {
        return INSTANCE;
    }
}
