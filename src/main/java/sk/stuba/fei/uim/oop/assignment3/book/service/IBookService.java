package sk.stuba.fei.uim.oop.assignment3.book.service;


import sk.stuba.fei.uim.oop.assignment3.book.web.bodies.BookAddRequestBody;
import sk.stuba.fei.uim.oop.assignment3.book.web.bodies.BookUpdateRequestBody;
import sk.stuba.fei.uim.oop.assignment3.book.data.Book;
import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;

import java.util.List;

public interface IBookService {
    List<Book> getAll();

    Book getById(long id) throws NotFoundException;

    Book create(BookAddRequestBody request) throws NotFoundException;

    Book update(long id, BookUpdateRequestBody request) throws NotFoundException;

    void delete(long id) throws NotFoundException;

    long getAmount(long id) throws NotFoundException;

    long addAmount(long id, long increment) throws NotFoundException;

    long getLendCount(long id) throws NotFoundException;
}
