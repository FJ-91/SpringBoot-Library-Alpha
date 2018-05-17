package fr.afpa.library.repository;

import fr.afpa.library.model.Borrowing;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowingRepository extends CrudRepository<Borrowing, Long> {

}
