package sk.stuba.fei.uim.oop.assignment3.book.data;

import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

//@RepositoryRestResource

public interface BookRepository extends JpaRepository<Book, Long> {
    //extend PagingAndSortingRepository<Book, Long>
    List<Book> findAll();

    Book findBookById(Long id);
}
