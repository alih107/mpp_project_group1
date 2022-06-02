package back.repo.dataaccess;

import back.repo.domain.Address;
import back.repo.domain.Author;
import back.repo.domain.Book;
import back.repo.domain.BorrowDaysType;
import back.repo.domain.LibraryMember;
import back.repo.domain.Role;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * This class loads data into the data repository and also
 * sets up the storage units that are used in the application.
 * The main method in this class must be run once (and only
 * once) before the rest of the application can work properly.
 * It will create three serialized objects in the dataaccess.storage
 * folder.
 */
public class TestData {

    public static void main(String[] args) {
        TestData td = new TestData();
        td.bookData();
        td.libraryMemberData();
        td.userData();
        DataAccess da = new DataAccessFacade();
        System.out.println(da.readBooksMap());
        System.out.println(da.readUserMap());
    }

    ///create books
    public void bookData() {
        allBooks.get(0).addCopy();
        allBooks.get(0).addCopy();
        allBooks.get(1).addCopy();
        allBooks.get(3).addCopy();
        allBooks.get(2).addCopy();
        allBooks.get(2).addCopy();
        DataAccessFacade.loadBookMap(allBooks);
    }

    public void userData() {
        DataAccessFacade.loadUserMap(allUsers);
    }

    //create library members
    public void libraryMemberData() {
        LibraryMember libraryMember = new LibraryMember("1001", Collections.singletonList(Role.LIBRARIAN), "Rogers", "641-223-2211", addresses.get(4), "Andy");
        members.add(libraryMember);
        libraryMember = new LibraryMember("1002", Collections.singletonList(Role.LIBRARIAN), "Stevens", "702-998-2414", addresses.get(5), "Drew");
        members.add(libraryMember);

        libraryMember = new LibraryMember("1003", Arrays.asList(Role.ADMIN, Role.LIBRARIAN), "Eagleton", "451-234-8811", addresses.get(6), "Sarah");
        members.add(libraryMember);

        libraryMember = new LibraryMember("1004", Collections.singletonList(Role.LIBRARIAN), "Montalbahn", "641-472-2871", addresses.get(7), "Ricardo");
        members.add(libraryMember);

        DataAccessFacade.loadMemberMap(members);
    }

    ///////////// DATA //////////////
    List<LibraryMember> members = new ArrayList<>();
    @SuppressWarnings("serial")

    List<Address> addresses = new ArrayList<>() {
        {
            add(new Address("101 S. Main", "Fairfield", "IA", "52556"));
            add(new Address("51 S. George", "Georgetown", "MI", "65434"));
            add(new Address("23 Headley Ave", "Seville", "Georgia", "41234"));
            add(new Address("1 N. Baton", "Baton Rouge", "LA", "33556"));
            add(new Address("5001 Venice Dr.", "Los Angeles", "CA", "93736"));
            add(new Address("1435 Channing Ave", "Palo Alto", "CA", "94301"));
            add(new Address("42 Dogwood Dr.", "Fairfield", "IA", "52556"));
            add(new Address("501 Central", "Mountain View", "CA", "94707"));
        }
    };
    @SuppressWarnings("serial")
    public List<Author> allAuthors = new ArrayList<>() {
        {
            add(new Author("Joe", "Thomas", "641-445-2123", addresses.get(0), "A happy man is he."));
            add(new Author("Sandra", "Thomas", "641-445-2123", addresses.get(0), "A happy wife is she."));
            add(new Author("Nirmal", "Pugh", "641-919-3223", addresses.get(1), "Thinker of thoughts."));
            add(new Author("Andrew", "Cleveland", "976-445-2232", addresses.get(2), "Author of childrens' books."));
            add(new Author("Sarah", "Connor", "123-422-2663", addresses.get(3), "Known for her clever style."));
        }
    };

    @SuppressWarnings("serial")
    List<Book> allBooks = new ArrayList<>() {
        {
            add(new Book("23-11451", "The Big Fish", BorrowDaysType.TWENTY_ONE, Arrays.asList(allAuthors.get(0), allAuthors.get(1))));
            add(new Book("28-12331", "Antartica", BorrowDaysType.SEVEN, Collections.singletonList(allAuthors.get(2))));
            add(new Book("99-22223", "Thinking Java", BorrowDaysType.TWENTY_ONE, Collections.singletonList(allAuthors.get(3))));
            add(new Book("48-56882", "Jimmy's First Day of School", BorrowDaysType.SEVEN, Collections.singletonList(allAuthors.get(4))));
        }
    };

    @SuppressWarnings("serial")
    List<User> allUsers = new ArrayList<>() {
        {
            add(new User("1001", "xyz", Collections.singletonList(Role.LIBRARIAN)));
            add(new User("1002", "abc", Collections.singletonList(Role.ADMIN)));
            add(new User("1003", "111", Arrays.asList(Role.ADMIN, Role.LIBRARIAN)));
        }
    };
}
