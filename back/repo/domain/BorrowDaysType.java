package back.repo.domain;

public enum BorrowDaysType {

    SEVEN(7),
    TWENTY_ONE(21);

    public int days;

    BorrowDaysType(int days) {
        this.days = days;
    }
}
