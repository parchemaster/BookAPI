package sk.stuba.fei.uim.oop.assignment3.book.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.book.bodies.BookAddRequestBody;
import sk.stuba.fei.uim.oop.assignment3.book.bodies.BookUpdateRequestBody;
import sk.stuba.fei.uim.oop.assignment3.book.data.Book;
import sk.stuba.fei.uim.oop.assignment3.book.data.BookRepository;
import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;


import java.util.List;

@Service
public class BookService implements IBookService{

    @Autowired
    private BookRepository repository;

    @Override
    public List<Book> getAll() {
        return repository.findAll();
    }

    @Override
    public Book getById(long id) throws NotFoundException {
        return this.repository.findBookById(id);
    }

    @Override
    public Book create(BookAddRequestBody request) {
        return this.repository.save(new Book(request));
    }

    @Override
    public Book update(long id, BookUpdateRequestBody request) throws NotFoundException {
        var updatedBook = this.getById(id);
        updatedBook.setName(request.getName());
        return this.repository.save(updatedBook);
    }

    @Override
    public void delete(long id) throws NotFoundException {

    }
}
