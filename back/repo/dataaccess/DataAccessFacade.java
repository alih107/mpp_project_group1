package back.repo.dataaccess;

import back.repo.domain.Book;
import back.repo.domain.CheckoutRecord;
import back.repo.domain.LibraryMember;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class DataAccessFacade implements DataAccess {

    private static final DataAccessFacade INSTANCE = new DataAccessFacade();

    enum StorageType {
        BOOKS,
        MEMBERS,
        USERS,
        CHECKOUT_RECORDS
    }

    public static final String OUTPUT_DIR = System.getProperty("user.dir")
                                            + "/back/repo/storage";
    public static final String DATE_PATTERN = "MM/dd/yyyy";

    public static DataAccessFacade getInstance() {
        return INSTANCE;
    }

    //implement: other save operations
    public void saveNewMember(LibraryMember member) {
        HashMap<String, LibraryMember> mems = readMemberMap();
        String memberId = member.getMemberId();
        mems.put(memberId, member);
        saveToStorage(StorageType.MEMBERS, mems);
    }

    @Override
    public void saveBook(Book book) {
        HashMap<String, Book> bookMap = readBooksMap();
        bookMap.put(book.getIsbn(), book);
        saveToStorage(StorageType.BOOKS, bookMap);
    }

    @Override
    public void saveNewUser(LibraryMember member, String password) {
        HashMap<String, User> userMap = readUserMap();
        String id = member.getMemberId();
        userMap.put(id, new User(id, password, member.getRoles()));
        saveToStorage(StorageType.USERS, userMap);
    }

    @Override
    public void addNewCheckoutRecord(CheckoutRecord record) {
        HashMap<UUID, CheckoutRecord> recordMap = readCheckoutRecordMap();
        recordMap.put(record.getRecordID(), record);
        saveToStorage(StorageType.CHECKOUT_RECORDS, recordMap);
    }

    @SuppressWarnings("unchecked")
    public HashMap<String, Book> readBooksMap() {
        //Returns a Map with name/value pairs being
        //   isbn -> Book
        HashMap<String, Book> bookHashMap = (HashMap<String, Book>) readFromStorage(StorageType.BOOKS);
        return bookHashMap == null ? new HashMap<>() : bookHashMap;
    }

    @SuppressWarnings("unchecked")
    public HashMap<String, LibraryMember> readMemberMap() {
        //Returns a Map with name/value pairs being
        //   memberId -> LibraryMember
        HashMap<String, LibraryMember> memberHashMap = (HashMap<String, LibraryMember>) readFromStorage(StorageType.MEMBERS);
        return memberHashMap == null ? new HashMap<>() : memberHashMap;
    }

    @Override
    @SuppressWarnings("unchecked")
    public HashMap<UUID, CheckoutRecord> readCheckoutRecordMap() {
        HashMap<UUID, CheckoutRecord> recordMap = (HashMap<UUID, CheckoutRecord>) readFromStorage(StorageType.CHECKOUT_RECORDS);
        return recordMap == null ? new HashMap<>() : recordMap;
    }

    @SuppressWarnings("unchecked")
    public HashMap<String, User> readUserMap() {
        //Returns a Map with name/value pairs being
        //   userId -> User
        HashMap<String, User> userHashMap = (HashMap<String, User>) readFromStorage(StorageType.USERS);
        return userHashMap == null ? new HashMap<>() : userHashMap;
    }

    /////load methods - these place test data into the storage area
    ///// - used just once at startup

    static void loadBookMap(List<Book> bookList) {
        HashMap<String, Book> books = new HashMap<>();
        bookList.forEach(book -> books.put(book.getIsbn(), book));
        saveToStorage(StorageType.BOOKS, books);
    }

    static void loadUserMap(List<User> userList) {
        HashMap<String, User> users = new HashMap<>();
        userList.forEach(user -> users.put(user.getId(), user));
        saveToStorage(StorageType.USERS, users);
    }

    static void loadMemberMap(List<LibraryMember> memberList) {
        HashMap<String, LibraryMember> members = new HashMap<>();
        memberList.forEach(member -> members.put(member.getMemberId(), member));
        saveToStorage(StorageType.MEMBERS, members);
    }

    static void saveToStorage(StorageType type, Object ob) {
        ObjectOutputStream out = null;
        try {
            Path path = FileSystems.getDefault().getPath(OUTPUT_DIR, type.toString());
            out = new ObjectOutputStream(Files.newOutputStream(path));
            out.writeObject(ob);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (Exception e) {}
            }
        }
    }

    static Object readFromStorage(StorageType type) {
        ObjectInputStream in = null;
        Object retVal = null;
        try {
            Path path = FileSystems.getDefault().getPath(OUTPUT_DIR, type.toString());
            in = new ObjectInputStream(Files.newInputStream(path));
            retVal = in.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Exception e) {}
            }
        }
        return retVal;
    }

    final static class Pair<S, T> implements Serializable {

        S first;
        T second;

        Pair(S s, T t) {
            first = s;
            second = t;
        }

        @Override
        @SuppressWarnings("unchecked")
        public boolean equals(Object ob) {
            if (ob == null) { return false; }
            if (this == ob) { return true; }
            if (ob.getClass() != getClass()) { return false; }

            Pair<S, T> p = (Pair<S, T>) ob;
            return p.first.equals(first) && p.second.equals(second);
        }

        @Override
        public int hashCode() {
            return first.hashCode() + 5 * second.hashCode();
        }

        @Override
        public String toString() {
            return "(" + first.toString() + ", " + second.toString() + ")";
        }

        private static final long serialVersionUID = 5399827794066637059L;
    }
}
