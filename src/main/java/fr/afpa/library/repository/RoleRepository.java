package fr.afpa.library.repository;

import fr.afpa.library.model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

@Repository
@RestResource(exported = false)
public interface RoleRepository extends CrudRepository<Role, Integer> {

    public Role findRoleByName(String name);

}
