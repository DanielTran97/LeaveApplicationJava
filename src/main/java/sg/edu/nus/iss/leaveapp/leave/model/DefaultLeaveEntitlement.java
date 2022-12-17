package sg.edu.nus.iss.leaveapp.leave.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table
public class DefaultLeaveEntitlement {
    @Id
    private String designation;

    private Integer annualLeave;
    private Integer medicalLeave;
    private Integer compensationLeave;

    public DefaultLeaveEntitlement (String designation){
        this.designation = designation;
        if (designation.equals("Manager")){
            this.annualLeave = 18;
            this.medicalLeave = 60;
            this.compensationLeave = 0;
        }
        if (designation.equals("Employee")){
            this.annualLeave = 14;
            this.medicalLeave = 60;
            this.compensationLeave = 0;
        }
    }


    /*/
    private Integer annualLeave;
    private Integer medicalLeave;
    
    public Role(String roleName){
        this.roleName = roleName;
        if (roleName.equals("Manager")){
            this.annualLeave = 18;
            this.medicalLeave = 60;
        }
        if (roleName.equals("Employee")){
            this.annualLeave = 14;
            this.medicalLeave = 60;
        }
    }
    /*/
    
}