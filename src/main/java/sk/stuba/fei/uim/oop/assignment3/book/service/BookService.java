package sk.stuba.fei.uim.oop.assignment3.book.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.author.service.IAuthorService;
import sk.stuba.fei.uim.oop.assignment3.book.web.bodies.BookAddRequestBody;
import sk.stuba.fei.uim.oop.assignment3.book.web.bodies.BookUpdateRequestBody;
import sk.stuba.fei.uim.oop.assignment3.book.data.Book;
import sk.stuba.fei.uim.oop.assignment3.book.data.BookRepository;
import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;


import java.util.List;

@Service
public class BookService implements IBookService{

    @Autowired
    private BookRepository repository;

    @Autowired
    private IAuthorService authorService;

    @Override
    public List<Book> getAll() {
        return repository.findAll();
    }

    @Override
    public Book getById(long id) throws NotFoundException {
        return this.repository.findBookById(id);
    }

    //TODO maybe I should replace @SneakyThrows to exception
//    @SneakyThrows
    @Override
    public Book create(BookAddRequestBody request) throws NotFoundException {
//        var authorsID = authorService.getAll().stream().map(Author::getId).collect(Collectors.toList());
//        if (authorsID.contains(request.getAuthorId())) {
//            var author = authorService.getById(request.getAuthorId());
//            var newBook = new Book(request, author);
//            if (authorsID.contains(request.getAuthorId())) authorService.getById(request.getAuthorId()).getBooks().add(newBook);
//            return this.repository.save(newBook);
//        }

//        var author = authorService.getById(request.getAuthorId());
        if (request.getAuthor() != null) {
            if (authorService.getById(request.getAuthor()) != null
                    && authorService.getById(request.getAuthor()).getId().equals(request.getAuthor())) {
                var newBook = new Book(request, authorService.getById(request.getAuthor()));
                authorService.getById(request.getAuthor()).getBooks().add(newBook);
                return this.repository.save(newBook);
            }
        }
        return null;
    }

    @Override
    public Book update(long id, BookUpdateRequestBody request) throws NotFoundException {
        var updatedBook = this.getById(id);
        updatedBook.setName(request.getName());
        updatedBook.setDescription(request.getDescription());
        updatedBook.setAuthor(authorService.getById(request.getAuthor()));
        updatedBook.setPages(request.getPages());
        return this.repository.save(updatedBook);
    }

    @Override
    public void delete(long id) throws NotFoundException {
        this.repository.delete(this.getById(id));
    }

    @Override
    public long getAmount(long id) throws NotFoundException {
        return repository.findBookById(id).getAmount();
    }

    @Override
    public long addAmount(long id, long increment) throws NotFoundException {
        Book book = repository.findBookById(id);
        book.setAmount(book.getAmount() + increment);
        this.repository.save(book);
        return book.getAmount();
    }

    @Override
    public long getLendCount(long id) throws NotFoundException {
        return repository.findBookById(id).getLendCount();
    }
}
