package sk.stuba.fei.uim.oop.assignment3.book.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import sk.stuba.fei.uim.oop.assignment3.book.service.IBookService;
import sk.stuba.fei.uim.oop.assignment3.book.web.bodies.Amount;
import sk.stuba.fei.uim.oop.assignment3.book.web.bodies.BookAddRequestBody;
import sk.stuba.fei.uim.oop.assignment3.book.web.bodies.BookResponse;
import sk.stuba.fei.uim.oop.assignment3.book.web.bodies.BookUpdateRequestBody;
import sk.stuba.fei.uim.oop.assignment3.book.service.BookService;
import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private IBookService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BookResponse> getAllBooks() {
        return this.service.getAll().stream().map(BookResponse::new).collect(Collectors.toList());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookResponse> addBook(@RequestBody BookAddRequestBody body) throws NotFoundException {
        return new ResponseEntity<>(new BookResponse(service.create(body)), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public BookResponse getBook(@PathVariable("id") Long bookId) throws NotFoundException {
        return new BookResponse(service.getById(bookId));
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public BookResponse updateBook(@PathVariable("id") Long bookId, @RequestBody BookUpdateRequestBody body) throws NotFoundException {
        return new BookResponse(service.update(bookId, body));
    }

    @DeleteMapping(value = "/{id}")
    public void deleteBook(@PathVariable("id") Long bookId) throws NotFoundException {
        this.service.delete(bookId);
    }

    @GetMapping(value = "/{id}/amount", produces = MediaType.APPLICATION_JSON_VALUE)
    public Amount getAmount(@PathVariable("id") Long bookId) throws NotFoundException {
        return new Amount(this.service.getAmount(bookId));
    }

    @PostMapping(value = "/{id}/amount", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Amount addAmount(@PathVariable("id") Long bookId, @RequestBody Amount body) throws NotFoundException {
        return new Amount(this.service.addAmount(bookId, body.getAmount()));
    }

    @GetMapping(value = "/{id}/lendCount", produces = MediaType.APPLICATION_JSON_VALUE)
    public Long getLendCount(@PathVariable("id") Long bookId) throws NotFoundException {
        return this.service.getLendCount(bookId);
    }


}
