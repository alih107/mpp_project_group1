package back.service.book;

public class CheckoutSearchFilter {

    private String memberId;
    private String isbn;
    private CheckoutSearchType searchType;

    public static CheckoutSearchFilter createByMemberFilter(String memberId){
        CheckoutSearchFilter filter = new CheckoutSearchFilter();
        filter.setMemberId(memberId);
        filter.setSearchType(CheckoutSearchType.BY_MEMBER);

        return filter;
    }

    public static CheckoutSearchFilter createByIsbn(String isbn){
        CheckoutSearchFilter filter = new CheckoutSearchFilter();
        filter.setIsbn(isbn);
        filter.setSearchType(CheckoutSearchType.BY_ISBN);

        return filter;
    }

    private void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    private void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    private void setSearchType(CheckoutSearchType searchType) {
        this.searchType = searchType;
    }

    String getMemberId() {
        return memberId;
    }

    String getIsbn() {
        return isbn;
    }

    CheckoutSearchType getSearchType() {
        return searchType;
    }

    enum CheckoutSearchType{
        BY_MEMBER,
        BY_ISBN
    }
}
