package back.controller.book;

import back.dataaccess.EntityNotFoundException;

public class BookController implements IBookController {

    private static final BookController INSTANCE = new BookController();
    @Override
    public void addNewBook() {

    }

    @Override
    public void addCopy(String isbn) throws EntityNotFoundException {

    }

    public static BookController getInstance() {
        return INSTANCE;
    }
}
