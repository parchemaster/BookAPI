package sk.stuba.fei.uim.oop.assignment3.book.data;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
//    @NotNull
    String name;
    String description;
    @ManyToOne
    Author author;
    @JsonBackReference
    public Author getAuthor() {
        return author;
    }
    Long pages;
    Long amount;
    Long lendCount;
//    @JsonBackReference
    @ManyToOne
    LendingList lendingList;
    @JsonBackReference
    public LendingList getLendingList() {
        return lendingList;
    }




public Book(BookAddRequestBody requestBody, Author author) {
        this.name = requestBody.getName();
        this.description = requestBody.getDescription();
        this.author = author;
//        this.lendingList = null;
        this.pages = requestBody.getPages();
        this.amount = requestBody.getAmount();
        this.lendCount = requestBody.getLendCount();
    }
}
