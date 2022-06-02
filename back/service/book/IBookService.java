package back.service.book;

import back.dataaccess.EntityNotFoundException;

public interface IBookService {

    void addNewBook();

    void addBookCopy(String isbn) throws EntityNotFoundException;
}
