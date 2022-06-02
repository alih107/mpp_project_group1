package back.service.book;

import java.io.Serializable;

public class BookNotAvailableException extends Exception implements Serializable {

    public BookNotAvailableException() {
        super("There are currently no copies of the book available.");
    }
}
