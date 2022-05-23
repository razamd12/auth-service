package comauth.service.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class StatusDTO {
    private Integer code;
    private String message;
    private Object entity;
    private ArrayList<String> errorList;
    private List response = new ArrayList();

    public StatusDTO(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public StatusDTO(Integer code, Object entity) {
        this.code = code;
        this.entity = entity;
    }

    public StatusDTO(Integer code, String message, ArrayList<String> errorList) {
        this.code = code;
        this.message = message;
        this.errorList = errorList;
    }

    public StatusDTO(Integer code, String message, Object entity) {
        this.code = code;
        this.message = message;
        this.entity = entity;
    }

    public StatusDTO(Integer code, ArrayList<String> errorList) {
        this.code = code;
        this.errorList = errorList;
    }
}
