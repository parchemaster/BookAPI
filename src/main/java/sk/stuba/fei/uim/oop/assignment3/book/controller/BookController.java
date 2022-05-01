package sk.stuba.fei.uim.oop.assignment3.book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import sk.stuba.fei.uim.oop.assignment3.book.bodies.BookAddRequestBody;
import sk.stuba.fei.uim.oop.assignment3.book.bodies.BookResponse;
import sk.stuba.fei.uim.oop.assignment3.book.bodies.BookUpdateRequestBody;
import sk.stuba.fei.uim.oop.assignment3.book.data.Book;
import sk.stuba.fei.uim.oop.assignment3.book.service.BookService;
import sk.stuba.fei.uim.oop.assignment3.book.service.IBookService;
import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Book> getAllBooks() {
        return service.getAll();
    }

    @PostMapping("/add")
    public Book addBook(@RequestBody BookAddRequestBody body) {
        return service.create(body);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Book getBook(@PathVariable("id") Long bookId) throws NotFoundException {
        return service.getById(bookId);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Book updateBook(@PathVariable("id") Long bookId, @RequestBody BookUpdateRequestBody body) throws NotFoundException {
        return service.update(bookId, body);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteProduct(@PathVariable("id") Long bookId) throws NotFoundException {
        this.service.delete(bookId);
    }
}
