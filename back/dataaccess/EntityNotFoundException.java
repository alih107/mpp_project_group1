package back.dataaccess;

import java.io.Serializable;

public class EntityNotFoundException extends Exception implements Serializable {

    public EntityNotFoundException(String msg) {
        super(msg);
    }
}
