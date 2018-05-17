package fr.afpa.library.service;

import fr.afpa.library.model.Membership;
import fr.afpa.library.repository.MembershipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MembershipServiceImpl implements MembershipService {

    @Autowired
    MembershipRepository membershipRepository;

    @Override
    public void save(Membership membership) {
        membershipRepository.save(membership);
    }

    @Override
    public void delete(Long id) {
        membershipRepository.delete(id);
    }

    @Override
    public Membership findOne(Long id) {
        return membershipRepository.findOne(id);
    }

    @Override
    public List<Membership> findAll() {
        return (List<Membership>) membershipRepository.findAll();
    }

    @Override
    public Membership findMembershipByName(String name) {
        return membershipRepository.findMembershipByName(name);
    }
}
