package sg.edu.nus.iss.leaveapp.leave.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import sg.edu.nus.iss.leaveapp.leave.model.User;

@Component
public class UserFormatValidator implements Validator {
    
	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.isAssignableFrom(clazz);
    }
    @Override
    public void validate(Object obj, Errors error) {
        User username = (User) obj;
        User fullname = (User) obj;
        User password = (User) obj;
        User designation = (User) obj;
        User reportingStaffID = (User) obj;
        User ContactNum = (User) obj;
        User Address = (User) obj;
        User Email = (User) obj;
        
        String UserNameCheck = username.getUsername();
        String FullNameCheck = fullname.getFullname();
        String passwordCheck = password.getPassword();
        String designationCheck = designation.getDesignation();
        String RepStaffIdCheck = reportingStaffID.getReportingStaffID();
        String ContactCheck = ContactNum.getContactNum();
        String AddressCheck = Address.getAddress();
        String EmailCheck = Email.getEmail();

        if(!UserNameCheck.isBlank() || 
            !FullNameCheck.isBlank() ||
            !passwordCheck.isBlank() ||
            !designationCheck.isBlank() ||
            !RepStaffIdCheck.isBlank() ||
            !ContactCheck.isBlank() ||
            !AddressCheck.isBlank() ||
            !EmailCheck.isBlank()){
            System.out.println("username: " + UserNameCheck);
            System.out.println("fullname: " + FullNameCheck);
            System.out.println("password: " + passwordCheck);
            System.out.println("designation: " + designationCheck);
            System.out.println("reportingStaffID: " + RepStaffIdCheck);
            System.out.println("ContactNum: " + ContactCheck);
            System.out.println("Address: " + AddressCheck);
            System.out.println("Email: " + EmailCheck);
            if(UserNameCheck.isBlank()){
                error.rejectValue("username", "Username cannot be empty");
            }
            if(!FullNameCheck.isBlank()){
                error.rejectValue("fullname", "Name cannot be empty");
            }
            if(!passwordCheck.isBlank()){
                error.rejectValue("password", "Please indicate a password for your account");
            }
            if(!designationCheck.isBlank()){
                error.rejectValue("designation", "Please indicate your company's designation");
            }
            if(!RepStaffIdCheck.isBlank()){
                error.rejectValue("reportingStaffID", "Please indicate your superior's ID");
            }
            if(!ContactCheck.isBlank()){
                error.rejectValue("ContactNum", "Contact number cannot be empty");
            }
            if(!AddressCheck.isBlank()){
                error.rejectValue("Address", "Address cannot be empty");
            }
            if(!EmailCheck.isBlank()){
                error.rejectValue("Email", "Please indicate your company's email");
            }
        }
    }
}