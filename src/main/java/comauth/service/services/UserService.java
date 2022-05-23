package comauth.service.services;

import comauth.service.models.UserEntity;
import comauth.service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService{

    @Autowired
    UserRepository userRepository;

    public UserEntity findById(Long id) {
        Optional<UserEntity> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        }
        return null;
    }

    public UserEntity findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    public UserEntity findByPassword(String password) {
        return userRepository.findByPassword(password);
    }

    public UserEntity findByUserNameAndPassword(String userName, String password) {
        return userRepository.findByUserNameAndPassword(userName, password);
    }
    public UserEntity findByMobileNo(String mobileNo) {
        return userRepository.findByMobileNo(mobileNo);
    }

    public UserEntity findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public UserEntity findByStatus(Boolean status) {
        return userRepository.findByActive(status);
    }

    public List<UserEntity> findAll() {
        List<UserEntity> userList = (List<UserEntity>) userRepository.findAll();
        return userList;
    }
    public UserEntity create(UserEntity user) {
        UserEntity savedUser = userRepository.save(user);
        System.out.println("------"+user.getUserName());
        return savedUser;
    }

    public UserEntity delete(UserEntity user) {
        if (user.getId() != null) {

            user.setActive(false);
            userRepository.save(user);
            return user;
        }
        return null;
    }

    public UserEntity update(UserEntity user) {
        if (user.getId() != null) {
            UserEntity persisted = findById(user.getId());
            if (persisted == null) {
                return null;
            }
            UserEntity updated = userRepository.save(user);
            return updated;
        }
        return null;
    }

}
