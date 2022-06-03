package back.controller.book;

import back.repo.dataaccess.EntityNotFoundException;
import back.repo.domain.CheckoutRecord;
import back.service.auth.AuthenticationException;
import back.service.book.BookNotAvailableException;
import back.service.book.CheckoutSearchFilter;
import back.service.book.CheckoutService;
import back.service.book.ICheckoutService;

import java.nio.file.AccessDeniedException;
import java.util.List;

public class CheckoutController implements ICheckoutController {

    private static final CheckoutController INSTANCE = new CheckoutController();

    private final ICheckoutService checkoutService;

    public CheckoutController() {
        checkoutService = CheckoutService.getInstance();
    }

    @Override
    public void checkout(
            String memberId, String isbn
    ) throws EntityNotFoundException, BookNotAvailableException, AccessDeniedException, AuthenticationException {
        checkoutService.checkout(memberId, isbn);
    }

    @Override
    public List<CheckoutRecord> searchCheckouts(CheckoutSearchFilter filter) throws AuthenticationException, AccessDeniedException {
        return checkoutService.searchCheckouts(filter);
    }

    public static CheckoutController getInstance() {
        return INSTANCE;
    }
}
