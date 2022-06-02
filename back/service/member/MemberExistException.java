package back.service.member;

import java.io.Serializable;

public class MemberExistException extends Exception implements Serializable {

    public MemberExistException() {
        super("Member is already exist!");
    }

    public MemberExistException(String message) {
        super(message);
    }
}
