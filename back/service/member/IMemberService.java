package back.service.member;

import back.repo.dataaccess.EntityNotFoundException;
import back.repo.domain.LibraryMember;
import back.service.EntityExistException;

public interface IMemberService {

    void createMember(LibraryMember member, String password) throws EntityExistException;

    LibraryMember findById(String memberId) throws EntityNotFoundException;
}
