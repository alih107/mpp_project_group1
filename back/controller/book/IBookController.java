package back.controller.book;

import back.repo.dataaccess.EntityNotFoundException;
import back.repo.domain.Author;
import back.repo.domain.BorrowDaysType;
import back.service.auth.AuthenticationException;

import java.util.List;

public interface IBookController {

    void addNewBook();

    void addCopy(String isbn) throws EntityNotFoundException;

    List<Author> getAuthors();

    void createBook(String isbn, String title, BorrowDaysType borrowDaysType, List<Author> authors, int copies) throws AuthenticationException;
}
