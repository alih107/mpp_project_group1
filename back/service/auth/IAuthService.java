package back.service.auth;

public interface IAuthService {

    void login(String id, String pass) throws AuthenticationException;

    void logout();
}
