package sk.stuba.fei.uim.oop.assignment3.author.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.stuba.fei.uim.oop.assignment3.author.service.AuthorService;
import sk.stuba.fei.uim.oop.assignment3.author.web.bodies.AuthorAddRequestBody;
import sk.stuba.fei.uim.oop.assignment3.author.web.bodies.AuthorResponse;
import sk.stuba.fei.uim.oop.assignment3.author.web.bodies.AuthorUpdateRequestBody;
import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    private AuthorService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<sk.stuba.fei.uim.oop.assignment3.author.web.bodies.AuthorResponse> getAllAuthors() {
        return this.service.getAll().stream().map(sk.stuba.fei.uim.oop.assignment3.author.web.bodies.AuthorResponse::new).collect(Collectors.toList());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<sk.stuba.fei.uim.oop.assignment3.author.web.bodies.AuthorResponse> addAuthor(@RequestBody AuthorAddRequestBody body) {
        return new ResponseEntity<>(new sk.stuba.fei.uim.oop.assignment3.author.web.bodies.AuthorResponse(service.create(body)), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public sk.stuba.fei.uim.oop.assignment3.author.web.bodies.AuthorResponse getAuthor(@PathVariable("id") Long authorId) throws NotFoundException {
        return new sk.stuba.fei.uim.oop.assignment3.author.web.bodies.AuthorResponse(service.getById(authorId));
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public AuthorResponse updateBook(@PathVariable("id") Long authorId, @RequestBody AuthorUpdateRequestBody body) throws NotFoundException {
        return new AuthorResponse(service.update(authorId, body));
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") Long authorId) throws NotFoundException {
        this.service.delete(authorId);
    }
}
