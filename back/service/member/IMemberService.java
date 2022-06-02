package back.service.member;

import back.repo.dataaccess.EntityNotFoundException;
import back.repo.domain.LibraryMember;

public interface IMemberService {

    void createMember(LibraryMember member, String password) throws MemberExistException;

    LibraryMember findById(String memberId) throws EntityNotFoundException;
}
