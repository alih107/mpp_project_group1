package back.service.member;

import back.repo.dataaccess.EntityNotFoundException;
import back.repo.dataaccess.User;
import back.repo.domain.LibraryMember;
import back.service.BaseService;
import back.service.EntityExistException;

import java.util.HashMap;

public class MemberService extends BaseService implements IMemberService {

    private static final MemberService INSTANCE = new MemberService();

    @Override
    public void createMember(LibraryMember member, String password) throws EntityExistException {

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
