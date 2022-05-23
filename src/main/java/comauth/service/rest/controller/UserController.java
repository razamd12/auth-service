package comauth.service.rest.controller;

import comauth.service.dto.StatusDTO;
import comauth.service.dto.UserDTO;
import comauth.service.models.RoleEntity;
import comauth.service.models.UserEntity;
import comauth.service.services.RoleService;
import comauth.service.services.UserService;
import comauth.service.transformer.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @PostMapping(value = "/create")
    public ResponseEntity<StatusDTO> createUser(@RequestBody UserDTO userDTO) {
        try {

            boolean error = false;
            String errorMsg = "";
            if(userDTO.getRoleDTO()!=null){
                RoleEntity roleEntity=roleService.findOne(Long.parseLong(userDTO.getRoleDTO().getId()));
                if(roleEntity==null){
                    return new ResponseEntity<>(new StatusDTO(0, "Role Not found"), HttpStatus.NOT_FOUND);
                }
            }
            UserEntity oldUser = userService.findByUserName(userDTO.getUserName());
            if (oldUser != null) {
                if (oldUser.getActive()) {
                    error = true;
                    errorMsg = userDTO.getUserName() + " userName already exists! ";
                }
            }
            oldUser = userService.findByEmail(userDTO.getEmail());
            if (oldUser != null) {
                error = true;
                errorMsg += userDTO.getEmail() + " emailId already exists! ";
            }
            oldUser = userService.findByMobileNo(userDTO.getMobileNo().toUpperCase());
            if (oldUser != null) {
                error = true;
                errorMsg += userDTO.getMobileNo() + " mobile# already exists! ";
            }
            if (error) {
                return new ResponseEntity<>(new StatusDTO(0, errorMsg), HttpStatus.OK);
            }
            if(!(userDTO.getEmail().contains("@"))){
                return new ResponseEntity<>(new StatusDTO(1, "Incorrect Email"), HttpStatus.OK);
            }

            UserEntity user = UserTransformer.getUserEntity(userDTO);
            userService.create(user);
            return new ResponseEntity<>(new StatusDTO(1, "User Added Successfully ",UserTransformer.getUserDTO(user)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new StatusDTO(0, "Exception occurred! "+e), HttpStatus.OK);
        }
    }

    @PostMapping(value = "/update")
    public ResponseEntity<StatusDTO> updateUser(@RequestBody UserDTO userDTO) {
        try {
            boolean error = false;
            String errorMsg = "";
            UserEntity oldUser;
            UserEntity user;
            user=userService.findById(Long.parseLong(userDTO.getId()));
            if (user== null) {
                return new ResponseEntity<>(new StatusDTO(0, "User not found!"), HttpStatus.NOT_FOUND);
            } else {
                if(userDTO.getRoleDTO()!=null){
                    RoleEntity roleEntity=roleService.findOne(Long.parseLong(userDTO.getRoleDTO().getId()));
                    if(roleEntity==null){
                        return new ResponseEntity<>(new StatusDTO(0, "Role Not found"), HttpStatus.NOT_FOUND);
                    }
                }
                if (user.getUserName().equals(userDTO.getUserName()) && !(user.getUserName().equalsIgnoreCase(userDTO.getUserName()))) {
                    oldUser = userService.findByUserName(userDTO.getUserName());
                    if (oldUser != null) {
                        error = true;
                        errorMsg += userDTO.getUserName() + " userName already exists! ";
                    }
                }
                if (user.getEmail().equals(userDTO.getEmail()) && !(user.getEmail().equalsIgnoreCase(userDTO.getEmail()))) {
                    oldUser = userService.findByEmail(userDTO.getEmail());
                    if (oldUser != null) {
                        error = true;
                        errorMsg += userDTO.getEmail() + " emailId already exists! ";
                    }
                }
                if (user.getMobileNo().equals(userDTO.getMobileNo()) && !(user.getMobileNo().equalsIgnoreCase(userDTO.getMobileNo()))) {
                    oldUser = userService.findByMobileNo(userDTO.getMobileNo());
                    if (oldUser != null) {
                        error = true;
                        errorMsg += userDTO.getMobileNo() + " mobile# already exists! ";
                    }
                }

                if (error) {
                    return new ResponseEntity<>(new StatusDTO(0, errorMsg), HttpStatus.OK);
                }
                user = UserTransformer.getUserForUpdate(user, userDTO);
                user.setActive(true);
                userService.update(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new StatusDTO(0, "Exception occurred! "+e), HttpStatus.OK);
        }
        return new ResponseEntity<>(new StatusDTO(1, "User updated Successfully"), HttpStatus.OK);
    }


    @GetMapping(value = "/delete/{id}")
    public ResponseEntity<StatusDTO> deleteUser(@PathVariable Long id) {
        try {
            UserEntity user = userService.findById(id);

            if (user == null) {
                return new ResponseEntity<StatusDTO>(new StatusDTO(1, "UserEntity not found!"), HttpStatus.NOT_FOUND);
            } else {
                if(!user.getUserName().equalsIgnoreCase("superadmin")) {
                    userService.delete(user);
                }else{
                    return new ResponseEntity<>(new StatusDTO(0, "You can not Delete Super Admin!"), HttpStatus.OK);
                }
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new StatusDTO(0, "Exception occurred!\n" + e.getMessage()), HttpStatus.OK);

        }
        return new ResponseEntity<>(new StatusDTO(1, "User deleted Successfully"), HttpStatus.OK);
    }

    @GetMapping(value = "/getAll")
    public List<UserDTO> getAll() {
        List<UserEntity> userEntities =userService.findAll();
        return UserTransformer.getUserDTOs(userEntities);
    }

    @GetMapping(value = "/view/{id}")
    public ResponseEntity<UserDTO> viewById(@PathVariable Long id) throws IOException {
        UserEntity user;
        UserDTO userDTO = null;
        try {
            user = userService.findById(id);
            if (user != null) {
                userDTO = UserTransformer.getUserDTO(user);
                return new ResponseEntity<>(userDTO, HttpStatus.OK);
            }
            else{
                return new ResponseEntity("User not found", HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity("Exception occurred!", HttpStatus.NOT_FOUND);
        }

    }
}

