package back.service.member;

import back.repo.dataaccess.User;
import back.repo.domain.LibraryMember;
import back.service.BaseService;

import java.util.HashMap;

public class MemberService extends BaseService implements IMemberService {

    private static final MemberService INSTANCE = new MemberService();

    @Override
    public void createMember(LibraryMember member, String password) throws MemberExistException {

        HashMap<String, LibraryMember> memberMap = dataAccess.readMemberMap();
        HashMap<String, User> userMap = dataAccess.readUserMap();

        if (memberMap.containsKey(member.getMemberId()) || userMap.containsKey(member.getMemberId())) {
            throw new MemberExistException(String.format("Member with this memberId: %s already exist!", member.getMemberId()));
        }


        dataAccess.saveNewMember(member);
        dataAccess.saveNewUser(member, password);
    }

    public static MemberService getInstance() {
        return INSTANCE;
    }
}
