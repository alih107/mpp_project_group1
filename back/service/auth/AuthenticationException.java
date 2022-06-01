package back.service.auth;

import java.io.Serializable;

public class AuthenticationException extends Exception implements Serializable {

    public AuthenticationException() {
        super();
    }

    public AuthenticationException(String msg) {
        super(msg);
    }

    public AuthenticationException(Throwable t) {
        super(t);
    }

    private static final long serialVersionUID = 8978723266036027364L;
}
