package sk.stuba.fei.uim.oop.assignment3.author.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.author.data.Author;
import sk.stuba.fei.uim.oop.assignment3.author.data.AuthorRepository;
import sk.stuba.fei.uim.oop.assignment3.author.web.bodies.AuthorAddRequestBody;
import sk.stuba.fei.uim.oop.assignment3.author.web.bodies.AuthorUpdateRequestBody;
import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;

import java.util.List;

@Service
public class AuthorService implements IAuthorService{

    @Autowired
    private AuthorRepository repository;

    @Override
    public Author create(AuthorAddRequestBody request) {
        var newAuthor = new Author(request);
        repository.save(newAuthor);
        return newAuthor;
    }

    @Override
    public Author getById(long id) {
        return repository.findAuthorById(id);
    }

    @Override
    public List<Author> getAll() {
        return repository.findAll();
    }

    @Override
    public Author update(long id, AuthorUpdateRequestBody request) {
        var updatedAuthor = this.getById(id);
        updatedAuthor.setName(request.getName() != null ? request.getName() : updatedAuthor.getName());
        updatedAuthor.setSurname(request.getSurname() != null ? request.getSurname() : updatedAuthor.getSurname());
        return this.repository.save(updatedAuthor);
    }

    @Override
    public void delete(long id) throws NotFoundException {
        this.repository.delete(this.getById(id));
    }
}
