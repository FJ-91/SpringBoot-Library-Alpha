package fr.afpa.library.repository;

import fr.afpa.library.model.Subscriber;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RestResource(exported = false)
public interface SubscriberRepository extends CrudRepository<Subscriber, Long> {

    String ELLIGIBLE_SUBS = "SELECT s from Subscriber s " +
            "where s.totalDelays < s.membership.maxTotalDelays";

    public Subscriber findByEmail(String email);

    @Query(ELLIGIBLE_SUBS)
    public List<Subscriber> findElligibleSubs();

}
