package comauth.service.rest.controller;

import comauth.service.dto.RoleDTO;
import comauth.service.dto.StatusDTO;
import comauth.service.models.RoleEntity;
import comauth.service.services.RoleService;
import comauth.service.services.UserService;
import comauth.service.transformer.RoleTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<StatusDTO> create(@RequestBody RoleDTO roleDTO) {
        try {

            if (roleDTO.getName() == null) {
                return new ResponseEntity<>(new StatusDTO(0, "Fill all required fields!"), HttpStatus.OK);
            }
            if (roleService.findByName(roleDTO.getName()) != null) {
                return new ResponseEntity<>(new StatusDTO(0, " Role Already exist!"), HttpStatus.OK);
            }
            RoleEntity roleEntity= RoleTransformer.getRoleEntity(roleDTO);
            roleEntity.setActive(true);

            roleService.create(roleEntity);
            return new ResponseEntity<>(new StatusDTO(1, "Role Added Successfully ",RoleTransformer.getRoleDTO(roleEntity)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new StatusDTO(0, "Exception occurred: " + e), HttpStatus.OK);
        }
    }

    @PostMapping(value = "/update")
    public ResponseEntity<StatusDTO> update(@RequestBody RoleDTO roleDTO) {
        try {
            RoleEntity roleEntity = null;
            if (roleDTO.getId()==null || roleDTO.getName() == null) {
                return new ResponseEntity<>(new StatusDTO(0, "Fill all required fields !"), HttpStatus.OK);
            }
            roleEntity=roleService.findOne(Long.parseLong(roleDTO.getId()));
            if (roleEntity== null){
                return new ResponseEntity<>(new StatusDTO(0, "Role not found!"), HttpStatus.NOT_FOUND);
            }
            if (!roleEntity.getName().equalsIgnoreCase(roleDTO.getName()) && roleService.findByName(roleDTO.getName()) != null) {
                return new ResponseEntity<>(new StatusDTO(0, " Role Already exist!"), HttpStatus.OK);
            }
            roleEntity=RoleTransformer.getRoleEntity(roleDTO);
            roleEntity.setActive(true);
            roleService.update(roleEntity);
        } catch (Exception e) {
            return new ResponseEntity<>(new StatusDTO(0, "Exception occurred!" + e), HttpStatus.OK);
        }
        return new ResponseEntity<>(new StatusDTO(1, "Role updated Successfully"), HttpStatus.OK);


    }

    @GetMapping(value = "delete/{id}")
    public ResponseEntity<StatusDTO> delete(@PathVariable(value = "id") Long id) {
        try {
           RoleEntity roleEntity = roleService.findOne(id);
              if (roleEntity == null) {
                    return new ResponseEntity<>(new StatusDTO(0, "Role not found!"), HttpStatus.NOT_FOUND);
                } else {
                    roleEntity.setActive(false);
                    roleService.update(roleEntity);

                }
       } catch (Exception e) {
            return new ResponseEntity<>(new StatusDTO(0, "Exception occurred!"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new StatusDTO(1, "Role deleted Successfully"), HttpStatus.OK);
    }

    @GetMapping(value = "view/{id}")
    public ResponseEntity<RoleDTO> getOne(@PathVariable(value = "id") Long id) {
        RoleEntity roleEntity;
        RoleDTO roleDTO = new RoleDTO();
        try {
            roleEntity = roleService.findOne(id);
            if(roleEntity==null){
                return new ResponseEntity("User not found", HttpStatus.NOT_FOUND);
            }else {
                roleDTO=RoleTransformer.getRoleDTO(roleEntity);
                return new ResponseEntity<>(roleDTO, HttpStatus.OK);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity("Exception occurred!", HttpStatus.NOT_FOUND);
        }

    }

   @GetMapping(value = "/getAll")
   public List<RoleDTO> getAll() {
       List<RoleEntity> roleEntities =roleService.findAll();
       return RoleTransformer.getRoleDTOList(roleEntities);
   }
}
