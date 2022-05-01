package sk.stuba.fei.uim.oop.assignment3.book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import sk.stuba.fei.uim.oop.assignment3.book.bodies.BookAddRequestBody;
import sk.stuba.fei.uim.oop.assignment3.book.bodies.BookResponse;
import sk.stuba.fei.uim.oop.assignment3.book.data.Book;
import sk.stuba.fei.uim.oop.assignment3.book.service.BookService;
import sk.stuba.fei.uim.oop.assignment3.book.service.IBookService;

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
    public Book body(@RequestBody BookAddRequestBody body) {
        return service.create(body);
    }
}
