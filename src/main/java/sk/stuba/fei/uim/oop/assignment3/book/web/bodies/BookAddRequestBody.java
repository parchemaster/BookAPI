package sk.stuba.fei.uim.oop.assignment3.book.web.bodies;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import net.bytebuddy.asm.Advice;

@Getter
@Setter

public class BookAddRequestBody {
    private String name;
    private String description;
    private Long author;
    private Long pages;
    private Long amount;
    private Long lendCount;
}