package back.service.book;

import back.repo.dataaccess.EntityNotFoundException;
import back.repo.domain.CheckoutRecord;
import back.service.auth.AuthenticationException;

import java.nio.file.AccessDeniedException;
import java.util.List;

public interface ICheckoutService {

    void checkout(String memberId, String isbn) throws EntityNotFoundException, BookNotAvailableException, AuthenticationException, AccessDeniedException;

    List<CheckoutRecord> searchCheckouts(String memberId) throws AuthenticationException;
}
