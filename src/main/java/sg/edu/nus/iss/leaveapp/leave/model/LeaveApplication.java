package sg.edu.nus.iss.leaveapp.leave.model;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name= "LeaveApplication")
public class LeaveApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "leaveType cannot be blank.")
    private String leaveType;
    @NotNull(message = "Start Date cannot be blank.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    @NotNull(message = "End Date cannot be blank.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;
    @NotBlank(message = "Reason cannot be blank.")
    private String reason;
    private String dissemination;
    private Long contactNumber;

    @Column(name = "status", columnDefinition = "ENUM('APPROVED', 'REJECTED', 'PENDING', 'CANCELLED', 'DELETED')")
	@Enumerated (EnumType.STRING)
	private LeaveEventEnum status;
	
	private Date dateOfApplication;
	
	private Date dateOfStatus;

    public LeaveApplication(Long staffID, String leaveType, Date startDate, Date endDate, String reason, String dissemination, Long contactNumber){
        this.leaveType = leaveType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.reason = reason;
        this.dissemination = dissemination;
        this.contactNumber = contactNumber;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    
    private User user;



    
}
