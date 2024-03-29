package sk.stuba.fei.uim.oop.assignment3.book.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.author.service.IAuthorService;
import sk.stuba.fei.uim.oop.assignment3.book.data.Book;
import sk.stuba.fei.uim.oop.assignment3.book.data.BookRepository;
import sk.stuba.fei.uim.oop.assignment3.book.web.bodies.BookAddRequestBody;
import sk.stuba.fei.uim.oop.assignment3.book.web.bodies.BookUpdateRequestBody;
import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

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
    public Book getById(long id) {
        var res = this.repository.findBookById(id);
        return res;
    }

    @Override
    public Book create(BookAddRequestBody request) throws NotFoundException {
        var newBook = new Book(request, authorService.getById(request.getAuthor()));
        authorService.getById(request.getAuthor()).getBooks().add(newBook);
        return this.repository.save(newBook);
    }

    @Override
    public Book update(long id, BookUpdateRequestBody request) throws NotFoundException {
        var updatedBook = this.getById(id);
        if (!request.getAuthor().equals(updatedBook.getAuthor().getId()) && request.getAuthor() != null) {
            authorService.getById(updatedBook.getAuthor().getId()).getBooks().remove(updatedBook);
        }
        updatedBook.setName(request.getName() != null ? request.getName() : updatedBook.getName());
        updatedBook.setDescription(request.getDescription() != null ? request.getDescription() : updatedBook.getDescription());
        updatedBook.setAuthor(request.getAuthor() != null && request.getAuthor() != 0 ? authorService.getById(request.getAuthor()) : updatedBook.getAuthor());
        updatedBook.setPages(request.getPages() != null && request.getPages() != 0 ? request.getPages() : updatedBook.getPages());

        return this.repository.save(updatedBook);
    }

    @Override
    public void delete(long id) throws NotFoundException {
        var deletedBook = this.getById(id);
        var authors = authorService.getAll().stream().filter(author -> author.getBooks().contains(deletedBook)).collect(Collectors.toList());
        authors.forEach(author -> author.getBooks().remove(deletedBook));
        this.repository.delete(deletedBook);
    }

    @Override
    public long getAmount(long id) {
        return repository.findBookById(id).getAmount();
    }

    @Override
    public long addAmount(long id, long increment) {
        Book book = repository.findBookById(id);
        book.setAmount(book.getAmount() + increment);
        this.repository.save(book);
        return book.getAmount();
    }

    @Override
    public long getLendCount(long id) {
        return repository.findBookById(id).getLendCount();
    }
}
