package back.service.member;

import back.repo.domain.LibraryMember;
import back.service.BaseService;

public class MemberService extends BaseService {

    public void createMember(LibraryMember member) {
        //todo create validation for member`s fields
        dataAccess.saveNewMember(member);
    }
}
