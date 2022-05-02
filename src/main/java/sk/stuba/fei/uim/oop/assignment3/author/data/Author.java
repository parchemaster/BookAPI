package sk.stuba.fei.uim.oop.assignment3.author.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sk.stuba.fei.uim.oop.assignment3.author.web.bodies.AuthorAddRequestBody;
import sk.stuba.fei.uim.oop.assignment3.book.data.Book;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String name;
    String surname;
    @OneToMany(orphanRemoval = true)
    List<Book> books;


    public Author(AuthorAddRequestBody requestBody) {
        this.name = requestBody.getName();
        this.surname = requestBody.getSurname();
        this.books = new ArrayList<>();
    }
}
