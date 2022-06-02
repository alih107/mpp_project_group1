package back.controller.member;

import back.repo.domain.LibraryMember;
import back.service.member.IMemberService;
import back.service.member.MemberExistException;
import back.service.member.MemberService;

public class MemberController implements IMemberController {

    private final IMemberService memberService;

    public MemberController() {
        memberService = MemberService.getInstance();
    }

    @Override
    public void createNewMember(LibraryMember member, String password) throws MemberExistException {
        memberService.createMember(member, password);
    }
}
