package sk.stuba.fei.uim.oop.assignment3.author.service;

import sk.stuba.fei.uim.oop.assignment3.author.data.Author;
import sk.stuba.fei.uim.oop.assignment3.author.web.bodies.AuthorAddRequestBody;
import sk.stuba.fei.uim.oop.assignment3.author.web.bodies.AuthorUpdateRequestBody;
import sk.stuba.fei.uim.oop.assignment3.book.data.Book;
import sk.stuba.fei.uim.oop.assignment3.book.web.bodies.BookAddRequestBody;
import sk.stuba.fei.uim.oop.assignment3.book.web.bodies.BookUpdateRequestBody;
import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;

import java.util.List;

public interface IAuthorService {
    Author create(AuthorAddRequestBody request);

    Author getById(long id) throws NotFoundException;
//
    List<Author> getAll();
//
    Author update(long id, AuthorUpdateRequestBody request) throws NotFoundException;
//
    void delete(long id) throws NotFoundException;
}
