package sk.stuba.fei.uim.oop.assignment3.list.data;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sk.stuba.fei.uim.oop.assignment3.book.data.Book;
import sk.stuba.fei.uim.oop.assignment3.book.web.bodies.BookResponse;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Getter
@Setter
public class LendingList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @OneToMany
    List<Book> books;
//    @JsonManagedReference
//    public List<Book> getBooks(){
//        return books;
//    }
    Boolean lended;

    public LendingList() {
        this.books = new ArrayList<>();
        this.lended = false;
    }
}
