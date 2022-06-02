package back.repo.dataaccess;

import back.repo.domain.Book;
import back.repo.domain.CheckoutRecord;
import back.repo.domain.LibraryMember;

import java.util.HashMap;
import java.util.UUID;

public interface DataAccess {

    HashMap<String, Book> readBooksMap();

    HashMap<String, User> readUserMap();

    HashMap<String, LibraryMember> readMemberMap();

    HashMap<UUID, CheckoutRecord> readCheckoutRecordMap();

    void saveNewMember(LibraryMember member);

    void saveBook(Book book);

    void saveNewUser(LibraryMember member, String password);

    void addNewCheckoutRecord(CheckoutRecord record);
}
