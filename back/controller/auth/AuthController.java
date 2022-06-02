package back.controller.auth;

import back.service.auth.AuthService;
import back.service.auth.AuthenticationException;
import back.service.auth.IAuthService;

public class AuthController implements IAuthController {

    private final IAuthService authService = AuthService.getInstance();

    @Override
    public void login(String id, String pass) throws AuthenticationException {
        authService.login(id, pass);
    }

    @Override
    public void logout() {
        authService.logout();
    }
}
