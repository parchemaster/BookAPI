package sk.stuba.fei.uim.oop.assignment3.list.web.bodies;

import lombok.Getter;
import sk.stuba.fei.uim.oop.assignment3.book.data.Book;
import sk.stuba.fei.uim.oop.assignment3.list.data.LendingList;

import java.util.List;

@Getter
public class LendingListResponse {

    private final long id;
    private final List<Book> books;
    private boolean lended;

    public LendingListResponse(LendingList lendingList) {
        this.id = lendingList.getId();
        this.books = lendingList.getBooks();
        this.lended = lendingList.getLended();
    }
}
