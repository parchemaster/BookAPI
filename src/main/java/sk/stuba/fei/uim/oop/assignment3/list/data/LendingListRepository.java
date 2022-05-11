package sk.stuba.fei.uim.oop.assignment3.list.data;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.stuba.fei.uim.oop.assignment3.author.data.Author;
import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;

import java.util.List;

public interface LendingListRepository extends JpaRepository<LendingList, Long> {
    List<LendingList> findAll();

    LendingList findLendingListById(Long id);


}
