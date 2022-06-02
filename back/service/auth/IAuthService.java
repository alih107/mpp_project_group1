package back.service.auth;

import back.repo.dataaccess.EntityNotFoundException;
import back.repo.domain.LibraryMember;
import back.repo.domain.Role;

public interface IAuthService {

    void login(String id, String pass) throws AuthenticationException;

    void logout();

    boolean hasAccess(Role role);

    LibraryMember findById(String memberId) throws EntityNotFoundException;
}
