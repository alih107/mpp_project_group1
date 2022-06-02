package back.controller.auth;

import back.repo.domain.Role;
import back.service.auth.AuthenticationException;

public interface IAuthController {

    void login(String id, String pass) throws AuthenticationException;

    void logout();

    void hasAccess(Role role);
}
