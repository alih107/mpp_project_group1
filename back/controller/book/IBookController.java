package back.controller.book;

import back.repo.dataaccess.EntityNotFoundException;
import back.service.book.BookNotAvailableException;

public interface IBookController {

    void addNewBook();

    void addCopy(String isbn) throws EntityNotFoundException;

    void checkout(String memberId, String isbn) throws EntityNotFoundException, BookNotAvailableException;
}
