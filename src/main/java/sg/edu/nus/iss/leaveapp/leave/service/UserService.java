package sg.edu.nus.iss.leaveapp.leave.service;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.leaveapp.leave.model.User;
import sg.edu.nus.iss.leaveapp.leave.repository.UserRepository;

@Service
public class UserService {
	private List<User> employee = new ArrayList<User>();
	@Autowired
	UserRepository userrepo;
	
	public List<User> getEmployee(){
		return userrepo.findAll();
	}
	public void AddEmployee(User newEmployee) {
		userrepo.save(newEmployee);
	}
	public void RemoveEmployee(User RemoveEmp) {
		User foundEmp = employee.stream().filter(p -> p.getUsername().equals(RemoveEmp.getUsername())).findAny().orElse(null);
		employee.remove(foundEmp);
	}
	public void UpdateEmployee(User UpdateEmp) {
		User foundEmp = userrepo.getUserByUsername(UpdateEmp.getUsername());
		
        foundEmp.setUsername(UpdateEmp.getUsername());
        foundEmp.setPassword(UpdateEmp.getPassword());
		foundEmp.setFullname(UpdateEmp.getFullname());
        foundEmp.setReportingStaffID(UpdateEmp.getReportingStaffID());
		foundEmp.setDesignation(UpdateEmp.getDesignation());
		foundEmp.setContactNum(UpdateEmp.getContactNum());
		foundEmp.setAddress(UpdateEmp.getAddress());
        foundEmp.setEmail(UpdateEmp.getEmail());
		
		userrepo.save(foundEmp);
	}
}
