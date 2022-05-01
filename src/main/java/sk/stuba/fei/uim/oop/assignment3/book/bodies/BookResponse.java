package sk.stuba.fei.uim.oop.assignment3.book.bodies;

import lombok.Getter;
import sk.stuba.fei.uim.oop.assignment3.book.data.Book;

@Getter
public class BookResponse {

    private final long id;
    private final String name;

    public BookResponse(Book book) {
        this.id = book.getId();
        this.name = book.getName();
    }
}
