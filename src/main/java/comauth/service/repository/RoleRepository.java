package comauth.service.repository;

import comauth.service.models.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
@Component
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    List<RoleEntity> findAll();

    RoleEntity findByName(String name);

}
