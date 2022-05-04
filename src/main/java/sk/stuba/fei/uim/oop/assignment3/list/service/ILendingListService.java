package sk.stuba.fei.uim.oop.assignment3.list.service;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.stuba.fei.uim.oop.assignment3.author.data.Author;
import sk.stuba.fei.uim.oop.assignment3.book.data.Book;
import sk.stuba.fei.uim.oop.assignment3.exception.IllegalOperationException;
import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;
import sk.stuba.fei.uim.oop.assignment3.list.data.LendingList;
import sk.stuba.fei.uim.oop.assignment3.list.web.bodies.BookIdRequest;

import java.util.List;

public interface ILendingListService {
    LendingList create();

    List<LendingList> getAll();

    LendingList getById(long id) throws NotFoundException;

    void delete(long id) throws NotFoundException;

    LendingList addBookToList(long listId, BookIdRequest bookId) throws NotFoundException, IllegalOperationException;

    void removeBookFromList(long listId, BookIdRequest bookId) throws NotFoundException;

    void rentList(long listId) throws NotFoundException, IllegalOperationException;
}
