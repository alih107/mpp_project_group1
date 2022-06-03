package back.service;

import java.io.Serializable;

public class EntityExistException extends Exception implements Serializable {

    public EntityExistException() {
        super("Entity is already exist!");
    }

    public EntityExistException(String message) {
        super(message);
    }
}
