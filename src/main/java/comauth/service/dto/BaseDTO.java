package comauth.service.dto;

import lombok.Data;

@Data
public class BaseDTO {
    private String createdBy;
    private String createdDate;
    private String modifiedBy;
    private String modifiedDate;
    private String active;
}
