package back.dataaccess;

import back.repo.domain.Book;
import back.repo.domain.LibraryMember;

import java.util.HashMap;

public interface DataAccess {

    HashMap<String, Book> readBooksMap();

    HashMap<String, User> readUserMap();

    HashMap<String, LibraryMember> readMemberMap();

    void saveNewMember(LibraryMember member);
}
