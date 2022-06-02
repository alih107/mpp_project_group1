package back.controller.book;

import back.dataaccess.EntityNotFoundException;

public interface IBookController {

    void addNewBook();

    void addCopy(String isbn) throws EntityNotFoundException;
}
