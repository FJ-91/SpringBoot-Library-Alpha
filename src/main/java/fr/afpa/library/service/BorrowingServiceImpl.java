package fr.afpa.library.service;

import fr.afpa.library.model.Borrowing;
import fr.afpa.library.repository.BorrowingRepository;
import fr.afpa.library.exception.BorrowingNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BorrowingServiceImpl implements BorrowingService {

    @Autowired
    private BorrowingRepository borrowingRepository;

    /**
     * If a user tries to play with a random id in the URL, the exceptions are throwns
     * and he gets redirected to the error page with a custom message
     * @param id
     * @throws BorrowingNotFoundException
     * @throws DataAccessException
     * @throws NumberFormatException
     */
    @Override
    public void delete(Long id) throws BorrowingNotFoundException,
            DataAccessException, NumberFormatException {
        if(!exists(id))
            throw new BorrowingNotFoundException("There is no borrowing with this id: "+id);
        borrowingRepository.delete(id);
    }

    @Override
    public Borrowing findOne(Long id) throws BorrowingNotFoundException,
            DataAccessException, NumberFormatException {
        if(borrowingRepository.findOne(id) == null)
            throw new BorrowingNotFoundException("There is no borrowing with this id: "+id);

        return borrowingRepository.findOne(id);
    }

    @Override
    public void save(Borrowing borrowing) {
        borrowingRepository.save(borrowing);
    }

    @Override
    public List<Borrowing> findAll() {
        return (List<Borrowing>) borrowingRepository.findAll();
    }

    @Override
    public boolean exists(Long id) {
        return borrowingRepository.exists(id);
    }
}
