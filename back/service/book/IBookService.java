package back.service.book;

import back.repo.dataaccess.EntityNotFoundException;
import back.repo.domain.Author;
import back.repo.domain.Book;
import back.repo.domain.BorrowDaysType;
import back.service.EntityExistException;
import back.service.auth.AuthenticationException;

import java.util.List;

public interface IBookService {


    void addBookCopy(String isbn) throws EntityNotFoundException;

    List<Author> getAuthors();

    void createBook(String isbn, String title, BorrowDaysType borrowDaysType, List<Author> authors, int copies) throws AuthenticationException, EntityExistException;

    Book findBook(String isbn) throws EntityNotFoundException;
}
