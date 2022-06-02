package back.repo.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

final public class CheckoutRecord implements Serializable {

    private static final long serialVersionUID = -891229800414574881L;

    private final UUID recordID;
    private LibraryMember member;
    private BookCopy bookCopy;
    private LocalDate checkoutDate;
    private LocalDate dueDate;

    CheckoutRecord(LibraryMember member, BookCopy bookCopy, LocalDate checkoutDate, LocalDate dueDate) {
        this.recordID = UUID.randomUUID();
        this.member = member;
        this.bookCopy = bookCopy;
        this.checkoutDate = checkoutDate;
        this.dueDate = dueDate;
    }

    public static CheckoutRecord createCheckoutRecord(LibraryMember member, BookCopy bookCopy, LocalDate checkoutDate, LocalDate dueDate) {
        return new CheckoutRecord(member, bookCopy, checkoutDate, dueDate);
    }

    public UUID getRecordID() {
        return recordID;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public LibraryMember getMember() {
        return member;
    }

    public void setMember(LibraryMember member) {
        this.member = member;
    }

    public BookCopy getBookCopy() {
        return bookCopy;
    }

    public void setBookCopy(BookCopy bookCopy) {
        this.bookCopy = bookCopy;
    }

    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(LocalDate checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
}
