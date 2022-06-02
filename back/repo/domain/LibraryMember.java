package back.repo.domain;

import java.io.Serializable;
import java.util.List;

final public class LibraryMember extends Person implements Serializable {

    private String memberId;
    private List<Role> roles;

    public LibraryMember(String memberId, List<Role> roles, String lname, String tel, Address add, String fname) {
        super(fname, lname, tel, add);
        this.memberId = memberId;
        this.roles = roles;
    }

    public String getMemberId() {
        return memberId;
    }

    @Override
    public String toString() {
        return "Member Info: " + "ID: " + memberId + ", name: " + getFirstName() + " " + getLastName() +
               ", " + getTelephone() + " " + getAddress() + ", role: " + roles.toString();
    }

    private static final long serialVersionUID = -2226197306790714013L;
}
