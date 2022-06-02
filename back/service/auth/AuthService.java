package back.service.auth;

import back.dataaccess.DataAccess;
import back.dataaccess.DataAccessFacade;
import back.dataaccess.User;
import back.repo.domain.Role;

import java.util.HashMap;

public class AuthService {

    public static Role currentAuth = null;

    public static void login(String id, String passwd) throws AuthenticationException {
        DataAccess da = new DataAccessFacade();
        HashMap<String, User> map = da.readUserMap();
        if (!map.containsKey(id)) {
            throw new AuthenticationException("ID " + id + " not found");
        }
        String passwordFound = map.get(id).getPassword();
        if (!passwordFound.equals(passwd)) {
            throw new AuthenticationException("Password incorrect");
        }
        currentAuth = map.get(id).getAuthorization();
    }

    public void logout() {
        currentAuth = null;
    }


}
