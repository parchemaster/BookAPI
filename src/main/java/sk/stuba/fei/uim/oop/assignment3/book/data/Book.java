package sk.stuba.fei.uim.oop.assignment3.book.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sk.stuba.fei.uim.oop.assignment3.book.web.bodies.BookAddRequestBody;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
    Long authorId;
    Long pages;
    Long amount;
    Long lendCount;


    public Book(BookAddRequestBody requestBody) {
        this.name = requestBody.getName();
        this.description = requestBody.getDescription();
        this.authorId = requestBody.getAuthorId();
        this.pages = requestBody.getPages();
        this.amount = requestBody.getAmount();
        this.lendCount = requestBody.getLendCount();
    }
}
