package sk.stuba.fei.uim.oop.assignment3.book.web.bodies;

import lombok.Getter;

@Getter
public class BookUpdateRequestBody {
    String name;
    String description;
    Long authorId;
    Long pages;
    Long amount;
    Long lendCount;
}
