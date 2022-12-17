package sg.edu.nus.iss.leaveapp.leave.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table
public class LeaveEntitlement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer annualLeave;
    private Integer medicalLeave;
    private Integer compensationLeave;
    private Long overtimeHours;

    public LeaveEntitlement(User user){
        if (user.getDesignation().equals("Manager")){
            this.annualLeave = 18;
            this.medicalLeave = 60;
            this.compensationLeave = 0;
            this.overtimeHours = (long) (0);

        }
        
        if (user.getDesignation().equals("Employee")){
            this.annualLeave = 14;
            this.medicalLeave = 60;
            this.compensationLeave = 0;
            this.overtimeHours = (long) (0);
        }
        this.user = user;

    }

    @OneToOne
    private User user;

}
