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
@Table(name= "Role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name= "roleId")
    private Long id;
    private String roleName;

    public Role(String roleName){
        this.roleName = roleName;
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
