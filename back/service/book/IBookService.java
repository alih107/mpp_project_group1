package back.service.book;

import back.repo.dataaccess.EntityNotFoundException;
import back.repo.domain.Author;
import back.repo.domain.Book;
import back.repo.domain.BorrowDaysType;
import back.service.auth.AuthenticationException;

import java.nio.file.AccessDeniedException;
import java.util.List;

public interface IBookService {

    void addNewBook();

    void addBookCopy(String isbn) throws EntityNotFoundException;

    void checkout(String memberId, String isbn) throws EntityNotFoundException, BookNotAvailableException, AuthenticationException, AccessDeniedException;

    List<Author> getAuthors();

    void createBook(String isbn, String title, BorrowDaysType borrowDaysType, List<Author> authors, int copies);
}
