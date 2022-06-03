package back.service.auth;

import back.repo.dataaccess.User;
import back.repo.domain.Role;
import back.service.BaseService;

import java.util.HashMap;

public class AuthService extends BaseService implements IAuthService {

    private static final AuthService INSTANCE = new AuthService();

    private static User currentAuth = null;

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
        currentAuth = map.get(id);
    }

    public void logout() {
        currentAuth = null;
    }

    public static AuthService getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean hasAccess(Role role) throws AuthenticationException {
        checkSession();
        return hasAccess(currentAuth, role);
    }

    @Override
    public boolean hasAccess(String memberId, Role role) throws AuthenticationException {
        checkSession();
        HashMap<String, User> userMap = dataAccess.readUserMap();
        return hasAccess(userMap.get(memberId), role);
    }

    private boolean hasAccess(User user, Role role) {
        if (user == null) { return false; }
        Role exist = user.getAuthorizations().stream().filter(t -> t.equals(role)).findAny().orElse(null);
        return exist != null;
    }

    @Override
    public String getAuthorizedMemberId() throws AuthenticationException {
        checkSession();
        return currentAuth.getId();
    }

    private void checkSession() throws AuthenticationException {
        if (currentAuth == null) {
            throw new AuthenticationException("No active sessions found!");
        }
    }
}
