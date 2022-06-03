package back.controller.member;

import back.repo.domain.LibraryMember;
import back.service.EntityExistException;

public interface IMemberController {

    void createNewMember(LibraryMember member, String password) throws EntityExistException;
}
