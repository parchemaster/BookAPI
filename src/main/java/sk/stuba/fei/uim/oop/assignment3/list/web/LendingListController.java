package sk.stuba.fei.uim.oop.assignment3.list.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.stuba.fei.uim.oop.assignment3.author.web.bodies.AuthorAddRequestBody;
import sk.stuba.fei.uim.oop.assignment3.author.web.bodies.AuthorResponse;
import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;
import sk.stuba.fei.uim.oop.assignment3.list.service.ILendingListService;
import sk.stuba.fei.uim.oop.assignment3.list.web.bodies.BookIdRequest;
import sk.stuba.fei.uim.oop.assignment3.list.web.bodies.LendingListResponse;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/list")
public class LendingListController {


    @Autowired
    private ILendingListService service;

//    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping()
    public ResponseEntity<LendingListResponse> createList() {
        return new ResponseEntity<>(new LendingListResponse(service.create()), HttpStatus.CREATED);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<LendingListResponse> getAllAuthors() {
        return this.service.getAll().stream().map(LendingListResponse::new).collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public LendingListResponse getList(@PathVariable("id") Long id) throws NotFoundException {
        return new LendingListResponse(service.getById(id));
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") Long id) throws NotFoundException {
        this.service.delete(id);
    }

    @PostMapping(value = "/{id}/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public LendingListResponse addBookToList(@PathVariable("id") Long lendingListID, @RequestBody BookIdRequest body) throws NotFoundException {
        try {
            var a = new LendingListResponse(service.addBook(lendingListID, body));
            return a;
        }
        catch (Exception e) {
            System.out.println("there");
        }
        return null;
    }

    @DeleteMapping(value = "/{id}/remove")
    public void removeBookFromList(@PathVariable("id") Long listId, @RequestBody BookIdRequest body) throws NotFoundException {
        service.removeBookFromList(listId, body);
    }
}
