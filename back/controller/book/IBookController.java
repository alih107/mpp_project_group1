package back.controller.book;

import back.repo.dataaccess.EntityNotFoundException;
import back.repo.domain.Author;
import back.repo.domain.BorrowDaysType;
import back.service.auth.AuthenticationException;
import back.service.book.BookNotAvailableException;

import java.nio.file.AccessDeniedException;
import java.util.List;

public interface IBookController {

    void addNewBook();

    void addCopy(String isbn) throws EntityNotFoundException;

    void checkout(String memberId, String isbn) throws EntityNotFoundException, BookNotAvailableException, AccessDeniedException, AuthenticationException;

    List<Author> getAuthors();

    void createBook(String isbn, String title, BorrowDaysType borrowDaysType, List<Author> authors, int copies);
}
