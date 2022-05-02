package sk.stuba.fei.uim.oop.assignment3.author.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.stuba.fei.uim.oop.assignment3.author.data.Author;
import sk.stuba.fei.uim.oop.assignment3.author.service.AuthorService;
import sk.stuba.fei.uim.oop.assignment3.author.web.bodies.AuthorAddRequestBody;
import sk.stuba.fei.uim.oop.assignment3.author.web.bodies.AuthorResponse;
import sk.stuba.fei.uim.oop.assignment3.book.data.Book;
import sk.stuba.fei.uim.oop.assignment3.book.web.bodies.BookAddRequestBody;
import sk.stuba.fei.uim.oop.assignment3.book.web.bodies.BookResponse;
import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    private AuthorService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AuthorResponse> getAllAuthors() {
        return this.service.getAll().stream().map(AuthorResponse::new).collect(Collectors.toList());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthorResponse> addAuthor(@RequestBody AuthorAddRequestBody body) {
        return new ResponseEntity<>(new AuthorResponse(service.create(body)), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public AuthorResponse getAuthor(@PathVariable("id") Long authorId) throws NotFoundException {
        return new AuthorResponse(service.getById(authorId));
    }
}
