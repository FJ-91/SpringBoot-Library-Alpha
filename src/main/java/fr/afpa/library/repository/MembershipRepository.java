package fr.afpa.library.repository;


import fr.afpa.library.model.Membership;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

@Repository
@RestResource(exported = false)
public interface MembershipRepository extends CrudRepository<Membership, Long> {

    public Membership findMembershipByName(String name);

}
