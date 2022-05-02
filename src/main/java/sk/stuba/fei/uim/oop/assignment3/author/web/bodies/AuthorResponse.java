package sk.stuba.fei.uim.oop.assignment3.author.web.bodies;

import lombok.Getter;
import sk.stuba.fei.uim.oop.assignment3.author.data.Author;

import java.util.List;

@Getter
public class AuthorResponse {

    private final long id;
    private final String name;
    private final String surname;
    private final List<Long> books;

    public AuthorResponse(Author author) {
        this.id = author.getId();
        this.name = author.getName();
        this.surname = author.getSurname();
        this.books = author.getBooks();
    }
}
