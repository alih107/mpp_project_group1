package back.service.book;

import back.repo.dataaccess.EntityNotFoundException;
import back.repo.domain.Book;

public interface IBookService {

    void addNewBook();

    void addBookCopy(String isbn) throws EntityNotFoundException;

    void checkout(String memberId, String isbn) throws EntityNotFoundException, BookNotAvailableException;

    Book findBook(String isbn) throws EntityNotFoundException;
}
