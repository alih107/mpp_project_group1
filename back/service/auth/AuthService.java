package back.service.auth;

import back.repo.dataaccess.EntityNotFoundException;
import back.repo.dataaccess.User;
import back.repo.domain.LibraryMember;
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

    @Override
    public boolean hasAccess(Role role) {
        Role exist = currentAuth.stream().filter(t -> t.equals(role)).findAny().orElse(null);
        return exist != null;
    }

    @Override
    public LibraryMember findById(String memberId) throws EntityNotFoundException {
        HashMap<String, LibraryMember> memberMap = dataAccess.readMemberMap();
        if (!memberMap.containsKey(memberId)) {
            throw new EntityNotFoundException(String.format("A member with this id: %s not found!", memberId));
        }
        return memberMap.get(memberId);
    }
}
