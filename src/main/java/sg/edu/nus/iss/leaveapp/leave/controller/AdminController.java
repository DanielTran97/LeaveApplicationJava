package sg.edu.nus.iss.leaveapp.leave.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Binding;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import sg.edu.nus.iss.leaveapp.leave.model.LeaveApplication;
import sg.edu.nus.iss.leaveapp.leave.model.LeaveEventEnum;
import sg.edu.nus.iss.leaveapp.leave.model.User;
import sg.edu.nus.iss.leaveapp.leave.repository.LeaveApplicationRepository;
import sg.edu.nus.iss.leaveapp.leave.repository.UserRepository;
import sg.edu.nus.iss.leaveapp.leave.service.UserDetailsServiceImpl;
import sg.edu.nus.iss.leaveapp.leave.service.UserService;
import sg.edu.nus.iss.leaveapp.leave.validator.UserFormatValidator;

import org.springframework.security.core.userdetails.UserDetails;

@Controller
@RequestMapping("adm")
public class AdminController {

	private List<User> StaffList = new ArrayList<User>();
	@Autowired
	private UserFormatValidator userFormatValidator;

	@InitBinder
  	private void initUserBinder(WebDataBinder binder) {
    	binder.addValidators(userFormatValidator);
  	}	

	@Autowired
	private UserRepository UserRepo;
	
	@Autowired
	private UserService userService;

	@Value("${User.list.header")
	private String headerMsg;
	
	@GetMapping("/managestaff")
	public String manageStaff(Model model) {
        StaffList = userService.getEmployee();
        model.addAttribute("user", StaffList);
		model.addAttribute("listofStaff", headerMsg);
		return "managestaff";
	}
    @GetMapping("/managestaffleave")
	public String manageStaffLeave() {
		return "managestaffleave";
	}

    @GetMapping("/managecalendar")
	public String manageCalendar() {
		return "managecalendar";
	}

    @GetMapping("/manageleavetypes")
	public String manageLeaveTypes() {
		return "manageleavetypes";
	}

    @GetMapping("/managehierarchy")
	public String getHierarchy() {
		return "managehierarchy";
	}

	@GetMapping(value= "/testRetrieveAllEmployee", produces = "application/json")
	public @ResponseBody List<User> getAllStaff() {
		StaffList = userService.getEmployee();
		return StaffList;
	}
	
	@PostMapping(value= "/update")
	public String updateUser(@ModelAttribute(value= "s") User s,Model model) {	
		model.addAttribute("emp", s);
		return "updatestaff";		
	}

    @PostMapping("/updatestaff")
	public String updateStaff(@ModelAttribute (value = "User") User s, Model model) {
		userService.UpdateEmployee(s);;
		return "redirect:/adm/managestaff";	
	}
    
    @GetMapping("/add")
	public String addstaff(Model model) {
		User user = new User();
		model.addAttribute("User", user);
		return "addstaff";
	}

	@PostMapping("/add")
	public String saveStaff(@Valid @ModelAttribute(value= "User") User s, BindingResult bindingResult, Model model){
		if(bindingResult.hasErrors()){
			return "addstaff";
		}
		userService.AddEmployee(s);
		model.addAttribute("User", s);
		return "redirect:/adm/managestaff";		
	}

    @PostMapping("/deleteStaff")
	public String deleteStaffRecord(@ModelAttribute(value= "User") User s) {
		userService.RemoveEmployee(s);
		return "redirect:/adm/managestaff";
	}	
}
