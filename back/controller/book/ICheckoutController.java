package back.controller.book;

import back.repo.dataaccess.EntityNotFoundException;
import back.repo.domain.CheckoutRecord;
import back.service.auth.AuthenticationException;
import back.service.book.BookNotAvailableException;

import java.nio.file.AccessDeniedException;
import java.util.List;

public interface ICheckoutController {

    void checkout(String memberId, String isbn) throws EntityNotFoundException, BookNotAvailableException, AccessDeniedException, AuthenticationException;

    List<CheckoutRecord> searchCheckouts(String memberId) throws AuthenticationException;

}
