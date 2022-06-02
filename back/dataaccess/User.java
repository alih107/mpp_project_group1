package back.dataaccess;

import back.repo.domain.Role;

import java.io.Serializable;

final public class User implements Serializable {

    private static final long serialVersionUID = 5147265048973262104L;

    private String id;
    private String password;
    private Role authorization;

    User(String id, String pass, Role auth) {
        this.id = id;
        this.password = pass;
        this.authorization = auth;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public Role getAuthorization() {
        return authorization;
    }

    @Override
    public String toString() {
        return "[" + id + ":" + password + ", " + authorization.toString() + "]";
    }
}
