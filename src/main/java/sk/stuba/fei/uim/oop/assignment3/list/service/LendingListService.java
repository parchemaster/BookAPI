package sk.stuba.fei.uim.oop.assignment3.list.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.book.service.BookService;
import sk.stuba.fei.uim.oop.assignment3.exception.IllegalOperationException;
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
    public LendingList getById(long id) {
        return repository.findLendingListById(id);
    }

    @Override
    public void delete(long id) throws NotFoundException {
        getById(id).getBooks().forEach(book -> book.setLendCount(book.getLendCount() - 1));
        this.repository.delete(this.getById(id));
    }

    @Override
    public LendingList addBookToList(long listId, BookIdRequest bookId) throws NotFoundException, IllegalOperationException {
        var lendingList =  getById(listId);
        var book = bookService.getById(bookId.getId());
        if (lendingList.getLended() || lendingList.getBooks().contains(book)) {
            throw new IllegalOperationException();
        }
        lendingList.getBooks().add(book);
        var res = repository.save(lendingList);
        getById(listId).setBooks(lendingList.getBooks());
        return res;
    }

    @Override
    public void removeBookFromList(long listId, BookIdRequest bookId) throws NotFoundException {
        var book = bookService.getById(bookId.getId());
        var lendingList =  this.repository.findLendingListById(listId);
        lendingList.getBooks().remove(book);
    }

    @Override
    public void rentList(long listId) throws IllegalOperationException {
        var lendingList =  this.repository.findLendingListById(listId);
        if (lendingList.getLended()) {
            throw new IllegalOperationException();
        }
        lendingList.getBooks().forEach(book -> book.setLendCount(book.getLendCount() + 1));
        lendingList.setLended(true);
    }
}
