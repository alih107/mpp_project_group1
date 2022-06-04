package back.controller.member;

import back.repo.domain.LibraryMember;
import back.service.EntityExistException;
import back.service.auth.AuthenticationException;

import java.nio.file.AccessDeniedException;

public interface IMemberController {

    void createNewMember(LibraryMember member, String password) throws EntityExistException, AccessDeniedException, AuthenticationException;
}
