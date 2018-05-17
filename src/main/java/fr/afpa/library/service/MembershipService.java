package fr.afpa.library.service;

import fr.afpa.library.model.Membership;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MembershipService {

    public void save(Membership membership);

    public void delete(Long id);

    public Membership findOne(Long id);

    public List<Membership> findAll();

    public Membership findMembershipByName(String name);

}
