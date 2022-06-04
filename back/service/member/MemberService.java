package back.service.member;

import back.repo.dataaccess.EntityNotFoundException;
import back.repo.dataaccess.User;
import back.repo.domain.LibraryMember;
import back.repo.domain.Role;
import back.service.BaseService;
import back.service.EntityExistException;
import back.service.auth.AuthService;
import back.service.auth.AuthenticationException;
import back.service.auth.IAuthService;

import java.nio.file.AccessDeniedException;
import java.util.HashMap;

public class MemberService extends BaseService implements IMemberService {

    private static final MemberService INSTANCE = new MemberService();
    private final IAuthService authService = AuthService.getInstance();

    @Override
    public void createMember(LibraryMember member, String password) throws EntityExistException, AuthenticationException, AccessDeniedException {

        if (!authService.hasAccess(Role.ADMIN)) {
            throw new AccessDeniedException("You have no access to this functionality!");
        }

        HashMap<String, LibraryMember> memberMap = dataAccess.readMemberMap();
        HashMap<String, User> userMap = dataAccess.readUserMap();

        if (memberMap.containsKey(member.getMemberId()) || userMap.containsKey(member.getMemberId())) {
            throw new EntityExistException(String.format("Member with this memberId: %s is already exist!", member.getMemberId()));
        }

        dataAccess.saveNewMember(member);
        dataAccess.saveNewUser(member, password);
    }

    public static MemberService getInstance() {
        return INSTANCE;
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
