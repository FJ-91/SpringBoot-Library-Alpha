package fr.afpa.library.repository;

import fr.afpa.library.model.Copy;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CopyRepository extends CrudRepository<Copy, Long> {
    public List<Copy> findByAvailableIsTrue();
}
