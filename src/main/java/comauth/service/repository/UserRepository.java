package comauth.service.repository;

import comauth.service.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUserName(String userName);

    UserEntity findByPassword(String password);

    UserEntity findByUserNameAndPassword(String userName, String password);

    UserEntity findByActive(Boolean status);
    UserEntity findByMobileNo(String mobileNo);

    UserEntity findByEmail(String email);
}
