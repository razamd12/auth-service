package comauth.service.transformer;


import comauth.service.dto.UserDTO;
import comauth.service.models.RoleEntity;
import comauth.service.models.UserEntity;

import java.util.ArrayList;
import java.util.List;

public class UserTransformer {
    public static List<UserDTO> getUserDTOs(List<UserEntity> users) {
        List<UserDTO> userDTOS = new ArrayList<>();
        users.forEach(user -> {
            UserDTO userDTO = new UserDTO();
            if (user.getId() != null) {
                userDTO.setId(user.getId().toString());
            }
            if (user.getUserName() != null) {
                userDTO.setUserName(user.getUserName());
            }
            if (user.getEmail() != null) {
                userDTO.setEmail(user.getEmail());
            }
            if (user.getMobileNo() != null) {
                userDTO.setMobileNo(user.getMobileNo());
            }
            if (user.getRoleEntity() != null) {
                userDTO.setRoleDTO(RoleTransformer.getRoleDTO(user.getRoleEntity()));
            }
            if(user.getActive()!=null){
                userDTO.setActive(user.getActive().toString());
            }

            if(user.getCreatedBy()!=null){
                userDTO.setCreatedBy(user.getCreatedBy().toString());
            }
            if(user.getModifiedBy()!=null){
                userDTO.setModifiedBy(user.getModifiedBy().toString());
            }
            if(user.getCreatedDate()!=null){
                userDTO.setCreatedDate(user.getCreatedDate().toString());
            }
            if(user.getModifiedDate()!=null){
                userDTO.setModifiedDate(user.getModifiedDate().toString());
            }
            if(user.getActive()!=null){
                userDTO.setActive(user.getActive().toString());
            }
            userDTOS.add(userDTO);
        });
        return userDTOS;
    }

    public static UserDTO getUserDTO(UserEntity user) {
        UserDTO userDTO = new UserDTO();
        if (user.getId() != null) {
            userDTO.setId(user.getId().toString());
        }
        if (user.getUserName() != null) {
            userDTO.setUserName(user.getUserName());
        }
        if (user.getEmail() != null) {
            userDTO.setEmail(user.getEmail());
        }

        if(user.getPassword()!=null && !user.getPassword().equals("")){
            userDTO.setPassword(user.getPassword());
        }
        if (user.getMobileNo() != null) {
            userDTO.setMobileNo(user.getMobileNo());
        }
        if(user.getRoleEntity()!=null){
            userDTO.setRoleDTO(RoleTransformer.getRoleDTO(user.getRoleEntity()));
        }
		if(user.getActive()!=null){
            userDTO.setActive(user.getActive().toString());
        }
        if(user.getCreatedBy()!=null){
            userDTO.setCreatedBy(user.getCreatedBy().toString());
        }
        if(user.getModifiedBy()!=null){
            userDTO.setModifiedBy(user.getModifiedBy().toString());
        }
        if(user.getCreatedDate()!=null){
            userDTO.setCreatedDate(user.getCreatedDate().toString());
        }
        if(user.getModifiedDate()!=null){
            userDTO.setModifiedDate(user.getModifiedDate().toString());
        }
        if(user.getActive()!=null){
            userDTO.setActive(user.getActive().toString());
        }
        return userDTO;
    }

    public static UserEntity getUserForUpdate(UserEntity user, UserDTO userDTO) {
        if (userDTO.getId() != null) {
            user.setId(Long.parseLong(userDTO.getId()));
        }
        if (userDTO.getUserName() != null) {
            user.setUserName(userDTO.getUserName());
        }
        if (userDTO.getEmail() != null && !userDTO.getEmail().equals("") && !userDTO.getEmail().equals("null")) {
            user.setEmail(userDTO.getEmail());
        }

        if(userDTO.getPassword()!=null && !userDTO.getPassword().equals("")){
            user.setPassword(userDTO.getPassword());
        }
        if (userDTO.getMobileNo() != null && !userDTO.getMobileNo().equals("") && !userDTO.getMobileNo().equals("null")) {
            user.setMobileNo(userDTO.getMobileNo());
        }
        if(userDTO.getRoleDTO()!=null){
            RoleEntity roleEntity=new RoleEntity();
            roleEntity.setId(Long.parseLong(userDTO.getRoleDTO().getId()));
            user.setRoleEntity(roleEntity);
            user.setRoleEntity(RoleTransformer.getRoleEntity(userDTO.getRoleDTO()));
        }
        if(userDTO.getActive()!=null){
            user.setActive(Boolean.parseBoolean(userDTO.getActive()));
        }

        if(userDTO.getCreatedBy()!=null){
            user.setCreatedBy(Long.parseLong(userDTO.getCreatedBy()));
        }
        if(userDTO.getModifiedBy()!=null){
            user.setModifiedBy(Long.parseLong(userDTO.getModifiedBy()));
        }
        if(userDTO.getActive()!=null){
            user.setActive(Boolean.parseBoolean(userDTO.getActive()));
        }
        return user;
    }

    public static UserEntity getUserEntity(UserDTO userDTO) {
        UserEntity user = new UserEntity();
        if(userDTO.getId()!=null && !userDTO.getId().isEmpty()){
            user.setId(Long.valueOf(userDTO.getId()));
        }
        if (userDTO.getUserName() != null && !userDTO.getUserName().equals("")) {
            user.setUserName(userDTO.getUserName());
        }
        if (userDTO.getEmail() != null && !userDTO.getEmail().equals("")) {
            user.setEmail(userDTO.getEmail());
        }
        if(userDTO.getPassword()!=null && !userDTO.getPassword().equals("")){
            user.setPassword(userDTO.getPassword());
        }
        if (userDTO.getMobileNo() !=null && !userDTO.getMobileNo().equals("")) {
            user.setMobileNo(userDTO.getMobileNo());
        }
        if(userDTO.getRoleDTO()!=null){
            RoleEntity roleEntity=new RoleEntity();
            roleEntity.setId(Long.parseLong(userDTO.getRoleDTO().getId()));
            user.setRoleEntity(roleEntity);
            user.setRoleEntity(RoleTransformer.getRoleEntity(userDTO.getRoleDTO()));
        }
        if(userDTO.getActive()!=null){
            user.setActive(Boolean.parseBoolean(userDTO.getActive()));
        }

        if(userDTO.getCreatedBy()!=null){
            user.setCreatedBy(Long.parseLong(userDTO.getCreatedBy()));
        }
        if(userDTO.getModifiedBy()!=null){
            user.setModifiedBy(Long.parseLong(userDTO.getModifiedBy()));
        }
        if(userDTO.getActive()!=null){
            user.setActive(Boolean.parseBoolean(userDTO.getActive()));
        }
        return user;
    }
}
