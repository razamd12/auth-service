package comauth.service.models;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@Data
@MappedSuperclass
public class BaseModel
{

    @Column(name="created_by")
    private Long createdBy;
    @CreationTimestamp
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date",nullable = false,updatable = false)
    private Date createdDate;
    @Column(name="modified_by")
    private Long modifiedBy;
    @UpdateTimestamp
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modified_date",nullable = false)
    private Date modifiedDate;
    @Column(name = "active")
    private Boolean active;

    @PrePersist
    protected void onCreate(){
        createdDate = new Date();
    }

    @PreUpdate
    protected void onUpdate(){
        modifiedDate = new Date();
    }
}
