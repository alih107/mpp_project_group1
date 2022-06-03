package back.service.book;

import back.repo.dataaccess.EntityNotFoundException;
import back.repo.domain.Book;
import back.service.auth.AuthenticationException;

import java.nio.file.AccessDeniedException;

public interface IBookService {

    void addNewBook();

    void addBookCopy(String isbn) throws EntityNotFoundException;

    void checkout(String memberId, String isbn) throws EntityNotFoundException, BookNotAvailableException, AuthenticationException, AccessDeniedException;

    Book findBook(String isbn) throws EntityNotFoundException;
}
