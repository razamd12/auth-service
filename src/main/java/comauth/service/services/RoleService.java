package comauth.service.services;

import comauth.service.models.RoleEntity;
import comauth.service.repository.RoleRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class RoleService {

    @Autowired
    private RoleRepository a;

    public RoleEntity findOne(Long id) {
        Optional<RoleEntity> optionalRole = a.findById(id);
        if (optionalRole.isPresent()) {
            return optionalRole.get();
        }
        return null;
    }

    public RoleEntity findByName(String name) {
        return a.findByName(name);
    }

    public List<RoleEntity> findAll() {
        return a.findAll();
    }

    public RoleEntity create(RoleEntity role) {
        if (role.getId() != null) {
            return null;
        }
        System.out.println("---"+role.getName());
        RoleEntity saved = a.save(role);
        return saved;
    }

    public RoleEntity delete(RoleEntity role) {
        if (role.getId() != null) {
            RoleEntity persisted = findOne(role.getId());
            if (persisted == null) {
                return null;
            }
            role.setActive(false);
            a.save(role);
            return persisted;
        }
        return null;
    }

    public RoleEntity update(RoleEntity role) {
        if (role.getId() != null) {
            RoleEntity persisted = findOne(role.getId());
            if (persisted == null) {
                return null;
            }
            RoleEntity updatedBranch = a.save(role);
            return updatedBranch;
        }
        return null;
    }



}
