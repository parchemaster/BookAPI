package sk.stuba.fei.uim.oop.assignment3.list.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.book.data.Book;
import sk.stuba.fei.uim.oop.assignment3.book.service.BookService;
import sk.stuba.fei.uim.oop.assignment3.book.web.bodies.BookAddRequestBody;
import sk.stuba.fei.uim.oop.assignment3.book.web.bodies.BookResponse;
import sk.stuba.fei.uim.oop.assignment3.book.web.bodies.BookUpdateRequestBody;
import sk.stuba.fei.uim.oop.assignment3.exception.IllegalOperationException;
import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;
import sk.stuba.fei.uim.oop.assignment3.list.data.LendingList;
import sk.stuba.fei.uim.oop.assignment3.list.data.LendingListRepository;
import sk.stuba.fei.uim.oop.assignment3.list.web.bodies.BookIdRequest;

import java.util.List;
import java.util.stream.Collectors;

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
        var list = getById(id);
        for (var book : list.getBooks()) {
            book.setLendCount(book.getLendCount() - 1);
        }
        this.repository.delete(this.getById(id));
    }

    public LendingList addBookToList(long listId, BookIdRequest bookId) throws NotFoundException, IllegalOperationException {
//        var list = this.getById(listId);
//        if (list.getLended()) {
//            throw new IllegalOperationException();
//        }
//        for (var book : list.getBooks()) {
//            if (book.getId().equals(bookId.getId())) {
//                return null;
//            }
//        }
        var book = bookService.getById(bookId.getId());
        var lendingList =  getById(listId);

//        lendingList.getBooks().add(book);
//        lendingList.getBooks().add(bookService.getRepository().save(book));
//        lendingList.setBooks(lendingList.getBooks());
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
    public void rentList(long listId) throws NotFoundException {
        var lendingList =  this.repository.findLendingListById(listId);
        for (var book : lendingList.getBooks()) {
            book.setLendCount(book.getLendCount() + 1);
//            bookService.getRepository().save(book);
//            book.setLendCount(bookService.increaseLendCount(book.getId()));
        }
        lendingList.setLended(true);
    }
}
