package fr.afpa.library.service;

import fr.afpa.library.model.Role;
import fr.afpa.library.model.Subscriber;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface RoleService {

    public void save(Role role);

    public void delete(int id);

    public Role findOne(int id);

    public List<Role> findAll();

    public Role findRoleByName(String name);

}
