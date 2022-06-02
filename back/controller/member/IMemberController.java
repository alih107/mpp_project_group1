package back.controller.member;

import back.repo.domain.LibraryMember;
import back.service.member.MemberExistException;

public interface IMemberController {

    void createNewMember(LibraryMember member, String password) throws MemberExistException;
}
