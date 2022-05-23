package comauth.service.dto;

import lombok.Data;

@Data
public class UserDTO extends BaseDTO {

    private String id;
    private String userName;
    private String email;
    private String password;
    private String mobileNo;
    private RoleDTO roleDTO;
}
