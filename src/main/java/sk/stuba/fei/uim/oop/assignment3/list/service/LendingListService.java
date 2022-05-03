package sk.stuba.fei.uim.oop.assignment3.list.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.book.service.BookService;
import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;
import sk.stuba.fei.uim.oop.assignment3.list.data.LendingList;
import sk.stuba.fei.uim.oop.assignment3.list.data.LendingListRepository;
import sk.stuba.fei.uim.oop.assignment3.list.web.bodies.BookIdRequest;

import java.util.List;

@Service
public class LendingListService implements ILendingListService{

    @Autowired
    private LendingListRepository repository;

    @Autowired
    private BookService bookService;

    @Override
    public LendingList create() {
        return repository.save(new LendingList());
    }

    @Override
    public List<LendingList> getAll() {
        return repository.findAll();
    }

    @Override
    public LendingList getById(long id) throws NotFoundException {
        return repository.findLendingListById(id);
    }

    @Override
    public void delete(long id) throws NotFoundException {
        this.repository.delete(this.getById(id));
    }

    @Override
    public LendingList addBook(long listId, BookIdRequest bookId) throws NotFoundException {
        var book = bookService.getById(bookId.getId());
        var lendingList =  this.repository.findLendingListById(listId);
        lendingList.getBooks().add(book);
        return lendingList;
    }

    @Override
    public void removeBookFromList(long listId, BookIdRequest bookId) throws NotFoundException {
        var book = bookService.getById(bookId.getId());
        var lendingList =  this.repository.findLendingListById(listId);
        lendingList.getBooks().remove(book);
    }

    @Override
    public void rentList(long listId) {
        var lendingList =  this.repository.findLendingListById(listId);
        lendingList.setLended(true);
    }
}
