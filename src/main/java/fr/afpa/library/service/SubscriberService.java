package fr.afpa.library.service;

import fr.afpa.library.model.Subscriber;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface SubscriberService {

    public void save(Subscriber subscriber);

    public void delete(Long id);

    public Subscriber findOne(Long id);

    public List<Subscriber> findAll();

    public Subscriber findByEmail(String email);

    public boolean exists(Long id);

}