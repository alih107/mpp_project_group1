package back.service.auth;

import back.dataaccess.User;
import back.repo.domain.Role;
import back.service.BaseService;

import java.util.HashMap;
import java.util.List;

public class AuthService extends BaseService implements IAuthService {

    private static final AuthService INSTANCE = new AuthService();

    public static List<Role> currentAuth = null;

    private AuthService() {
    }

    public void login(String id, String passwd) throws AuthenticationException {
        HashMap<String, User> map = dataAccess.readUserMap();
        if (!map.containsKey(id)) {
            throw new AuthenticationException("ID " + id + " not found");
        }
        String passwordFound = map.get(id).getPassword();
        if (!passwordFound.equals(passwd)) {
            throw new AuthenticationException("Password incorrect");
        }
        currentAuth = map.get(id).getAuthorizations();
    }

    public void logout() {
        currentAuth = null;
    }

    public static AuthService getInstance() {
        return INSTANCE;
    }
}
