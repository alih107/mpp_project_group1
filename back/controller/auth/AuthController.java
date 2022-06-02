package back.controller.auth;

import back.repo.domain.Role;
import back.service.auth.AuthService;
import back.service.auth.AuthenticationException;
import back.service.auth.IAuthService;

public class AuthController implements IAuthController {

    private static final AuthController INSTANCE = new AuthController();
    private final IAuthService authService = AuthService.getInstance();

    @Override
    public void login(String id, String pass) throws AuthenticationException {
        authService.login(id, pass);
    }

    @Override
    public void logout() {
        authService.logout();
    }

    @Override
    public boolean hasAccess(Role role) {
        return authService.hasAccess(role);
    }

    public static AuthController getInstance() {
        return INSTANCE;
    }
}
