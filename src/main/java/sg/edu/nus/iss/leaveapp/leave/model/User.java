package sg.edu.nus.iss.leaveapp.leave.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Data;

import javax.validation.constraints.*;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name= "User")
public class User {
    @Id
    @NotBlank(message = "Staff ID or username cannot be null")
    @Column(name= "StaffID")
    private String username;

    @NotBlank(message = "The Full Name can't be null")
    @Column(name= "StaffFullName",columnDefinition = "nvarchar(150) not null")
    private String fullname;

    @NotBlank(message = "Password cannot be null")
    private String password;

    @NotBlank(message = "Job Designation must be declared")
    @Column(name= "StaffDesignation",columnDefinition = "nvarchar(20) not null")
    private String designation;

    @NotBlank(message = "Reporting Staff ID must be declared")
    @Column(name= "ReportingStaffID",columnDefinition = "nvarchar(4) not null")
    private String reportingStaffID;

    @NotBlank
    @Column(name= "StaffContactNum",columnDefinition = "nvarchar(8) not null")
    @Size()
    private String ContactNum;
	
    @NotBlank
    @Column(name= "StaffAddress",columnDefinition = "nvarchar(150) not null")
	private String Address;

    @NotBlank(message = "Email cannot be null")
    @Email
    @Column(name= "StaffEmail",columnDefinition = "nvarchar(50) not null")
    private String Email;

    public User(String username, String password, String fullname, String designation, String reportingStaffID, String ContactNum, String Address, String Email){
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.designation = designation;
        this.reportingStaffID = reportingStaffID;
        this.ContactNum = ContactNum;
        this.Address = Address;
        this.Email = Email;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    //
    private List<Role> roles;

    @OneToOne(mappedBy="user")
    private LeaveEntitlement staffleave;

    @OneToMany(mappedBy="user")
    private List<LeaveApplication> leaveApplication;
    
}
