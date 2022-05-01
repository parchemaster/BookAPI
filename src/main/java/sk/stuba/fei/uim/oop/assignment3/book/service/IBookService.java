package sk.stuba.fei.uim.oop.assignment3.book.service;


import sk.stuba.fei.uim.oop.assignment3.book.bodies.BookAddRequestBody;
import sk.stuba.fei.uim.oop.assignment3.book.bodies.BookUpdateRequestBody;
import sk.stuba.fei.uim.oop.assignment3.book.data.Book;
import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;

import java.util.List;

public interface IBookService {
    List<Book> getAll();

    Book getById(long id) throws NotFoundException;

    Book create(BookAddRequestBody request);

    Book update(long id, BookUpdateRequestBody request) throws NotFoundException;

    void delete(long id) throws NotFoundException;
}
