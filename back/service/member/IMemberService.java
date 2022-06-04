package back.service.member;

import back.repo.dataaccess.EntityNotFoundException;
import back.repo.domain.LibraryMember;
import back.service.EntityExistException;
import back.service.auth.AuthenticationException;

import java.nio.file.AccessDeniedException;

public interface IMemberService {

    void createMember(LibraryMember member, String password) throws EntityExistException, AuthenticationException, AccessDeniedException;

    LibraryMember findById(String memberId) throws EntityNotFoundException;
}
