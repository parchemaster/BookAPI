package sk.stuba.fei.uim.oop.assignment3.book.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sk.stuba.fei.uim.oop.assignment3.author.data.Author;
import sk.stuba.fei.uim.oop.assignment3.book.web.bodies.BookAddRequestBody;
import sk.stuba.fei.uim.oop.assignment3.list.data.LendingList;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String name;
    String description;
    @ManyToOne
    Author author;
    Long pages;
    Long amount;
    Long lendCount;
    @ManyToOne
    LendingList lendingList;




public Book(BookAddRequestBody requestBody, Author author) {
        this.name = requestBody.getName();
        this.description = requestBody.getDescription();
        this.author = author;
        this.pages = requestBody.getPages();
        this.amount = requestBody.getAmount();
        this.lendCount = requestBody.getLendCount();
    }
}
