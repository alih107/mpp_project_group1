package back.controller.member;

import back.repo.domain.LibraryMember;
import back.service.member.IMemberService;
import back.service.EntityExistException;
import back.service.member.MemberService;

public class MemberController implements IMemberController {

    private static final MemberController INSTANCE = new MemberController();
    private final IMemberService memberService;

    public MemberController() {
        memberService = MemberService.getInstance();
    }

    @Override
    public void createNewMember(LibraryMember member, String password) throws EntityExistException {
        memberService.createMember(member, password);
    }

    public static MemberController getInstance() {
        return INSTANCE;
    }
}
