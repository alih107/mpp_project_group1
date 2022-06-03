package back.controller.book;

import back.repo.dataaccess.EntityNotFoundException;
import back.repo.domain.CheckoutRecord;
import back.service.auth.AuthenticationException;
import back.service.book.BookNotAvailableException;
import back.service.book.CheckoutSearchFilter;

import java.nio.file.AccessDeniedException;
import java.util.List;

public interface ICheckoutController {

    void checkout(String memberId, String isbn) throws EntityNotFoundException, BookNotAvailableException, AccessDeniedException, AuthenticationException;

    List<CheckoutRecord> searchCheckouts(CheckoutSearchFilter filter) throws AuthenticationException, AccessDeniedException;

}
