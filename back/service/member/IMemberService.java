package back.service.member;

import back.repo.domain.LibraryMember;

public interface IMemberService {

    void createMember(LibraryMember member, String password) throws MemberExistException;
}
