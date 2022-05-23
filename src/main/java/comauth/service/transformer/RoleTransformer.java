package comauth.service.transformer;


import comauth.service.dto.RoleDTO;
import comauth.service.models.RoleEntity;

import java.util.ArrayList;
import java.util.List;

public class RoleTransformer {
    public static List<RoleDTO> getRoleDTOList(List<RoleEntity> roles) {
        List<RoleDTO> roleBeans = new ArrayList<>();
        for (RoleEntity r : roles) {
            RoleDTO roleBean = new RoleDTO();
            if (r.getId() != null) {
                roleBean.setId(r.getId().toString());
            }
            if (r.getName() != null && !r.getName().equals("")) {
                roleBean.setName(r.getName());
            }
            if (r.getDescription() != null && !r.getDescription().equals("")) {
                roleBean.setDescription(r.getDescription());
            }
           
            if(r.getCreatedBy()!=null){
                roleBean.setCreatedBy(r.getCreatedBy().toString());
            }
            if(r.getModifiedBy()!=null){
                roleBean.setModifiedBy(r.getModifiedBy().toString());
            }
            if(r.getCreatedDate()!=null){
                roleBean.setCreatedDate(r.getCreatedDate().toString());
            }
            if(r.getModifiedDate()!=null){
                roleBean.setModifiedDate(r.getModifiedDate().toString());
            }
            if(r.getActive()!=null){
                roleBean.setActive(r.getActive().toString());
            }
            roleBeans.add(roleBean);
        }
        return roleBeans;
    }
    public static RoleEntity getRoleEntity(RoleDTO roleDTO){
        RoleEntity roleEntity=new RoleEntity();
        if(roleDTO.getId()!=null){
            roleEntity.setId(Long.parseLong(roleDTO.getId()));
        }
        if(roleDTO.getName()!=null){
            roleEntity.setName(roleDTO.getName());
        }
        if(roleDTO.getDescription()!=null){
            roleEntity.setDescription(roleDTO.getDescription());
        }
        if(roleDTO.getCreatedBy()!=null){
            roleEntity.setCreatedBy(Long.parseLong(roleDTO.getCreatedBy()));
        }
        if(roleDTO.getModifiedBy()!=null){
            roleEntity.setModifiedBy(Long.parseLong(roleDTO.getModifiedBy()));
        }

        if(roleDTO.getActive()!=null){
            roleEntity.setActive(Boolean.valueOf(roleDTO.getActive()));
        }
        return roleEntity;
    }
    public static RoleDTO getRoleDTO(RoleEntity roleEntity) {
        RoleDTO roleDTO = new RoleDTO();
        if (roleEntity != null){
            if (roleEntity.getId() != null) {
                roleDTO.setId(roleEntity.getId().toString());
            }
        if (roleEntity.getName() != null) {
            roleDTO.setName(roleEntity.getName());
        }
        if (roleEntity.getDescription() != null) {
            roleDTO.setDescription(roleEntity.getDescription());
        }
        if (roleEntity.getCreatedBy() != null) {
            roleDTO.setCreatedBy(roleEntity.getCreatedBy().toString());
        }
        if (roleEntity.getModifiedBy() != null) {
            roleDTO.setModifiedBy(roleEntity.getModifiedBy().toString());
        }
        if (roleEntity.getCreatedDate() != null) {
            roleDTO.setCreatedDate(roleEntity.getCreatedDate().toString());
        }
        if (roleEntity.getModifiedDate() != null) {
            roleDTO.setModifiedDate(roleEntity.getModifiedDate().toString());
        }
        if (roleEntity.getActive() != null) {
            roleDTO.setActive(roleEntity.getActive().toString());
        }
    }
        return roleDTO;
    }

    public static RoleDTO getDTO(RoleEntity roleEntity){
        RoleDTO roleDTO=new RoleDTO();
        if(roleEntity.getId()!=null){
            roleDTO.setId(roleEntity.getId().toString());
        }
        if(roleEntity.getName()!=null){
            roleDTO.setName(roleEntity.getName());
        }
        if(roleEntity.getDescription()!=null){
            roleDTO.setDescription(roleEntity.getDescription());
        }
        if(roleEntity.getCreatedBy()!=null){
            roleDTO.setCreatedBy(roleEntity.getCreatedBy().toString());
        }
        if(roleEntity.getModifiedBy()!=null){
            roleDTO.setModifiedBy(roleEntity.getModifiedBy().toString());
        }
        if(roleEntity.getCreatedDate()!=null){
            roleDTO.setCreatedDate(roleEntity.getCreatedDate().toString());
        }
        if(roleEntity.getModifiedDate()!=null){
            roleDTO.setModifiedDate(roleEntity.getModifiedDate().toString());
        }
        if(roleEntity.getActive()!=null){
            roleDTO.setActive(roleEntity.getActive().toString());
        }
        
        return roleDTO;
    }

}
