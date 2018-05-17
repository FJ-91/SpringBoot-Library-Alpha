package fr.afpa.library.service;

import fr.afpa.library.model.Subscriber;
import fr.afpa.library.repository.SubscriberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriberServiceImpl implements SubscriberService {

    @Autowired
    private SubscriberRepository subscriberRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(Subscriber subscriber) {
        subscriber.setRole(roleService.findRoleByName("SUBSCRIBER"));
        subscriber.setPassword(bCryptPasswordEncoder.encode(subscriber.getPassword()));
        subscriberRepository.save(subscriber);
    }

    @Override
    public void delete(Long id) {
        subscriberRepository.delete(id);
    }

    @Override
    public Subscriber findOne(Long id) {
        return subscriberRepository.findOne(id);
    }

    @Override
    public List<Subscriber> findAll() {
        return (List<Subscriber>) subscriberRepository.findAll();
    }

    @Override
    public Subscriber findByEmail(String email) {
        return subscriberRepository.findByEmail(email);
    }

    @Override
    public boolean exists(Long id) {
        return subscriberRepository.exists(id);
    }
}
