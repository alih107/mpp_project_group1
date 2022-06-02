package back.repo.dataaccess;

import back.repo.domain.Role;

import java.io.Serializable;
import java.util.List;

final public class User implements Serializable {

    private static final long serialVersionUID = 5147265048973262104L;

    private String id;
    private String password;
    private List<Role> authorizations;

    User(String id, String pass, List<Role> authorizations) {
        this.id = id;
        this.password = pass;
        this.authorizations = authorizations;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public List<Role> getAuthorizations() {
        return authorizations;
    }

    @Override
    public String toString() {
        return "[" + id + ":" + password + ", " + authorizations.toString() + "]";
    }
}
