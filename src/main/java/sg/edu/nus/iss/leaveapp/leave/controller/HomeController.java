package sg.edu.nus.iss.leaveapp.leave.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.nus.iss.leaveapp.leave.model.DefaultLeaveEntitlement;
import sg.edu.nus.iss.leaveapp.leave.model.LeaveApplication;
import sg.edu.nus.iss.leaveapp.leave.model.Role;
import sg.edu.nus.iss.leaveapp.leave.model.User;
import sg.edu.nus.iss.leaveapp.leave.repository.DefaultLeaveEntitlementRepository;
import sg.edu.nus.iss.leaveapp.leave.repository.RoleRepository;
import sg.edu.nus.iss.leaveapp.leave.repository.UserRepository;
import sg.edu.nus.iss.leaveapp.leave.service.UserDetailsServiceImpl;

import org.springframework.security.core.userdetails.UserDetails;

@Controller
public class HomeController {

	@Autowired
	private UserRepository UserRepo;
	@Autowired
	private DefaultLeaveEntitlementRepository defaultLeaveEntitlementRepo;

	@GetMapping({"/","/login"})
    public String login() {
        return "login";
    }

    @GetMapping("/welcome")
	public String getWelcomePage(@AuthenticationPrincipal UserDetails userDetails,
	Model model) {
		String staffID = userDetails.getUsername();
        User user = UserRepo.getUserByUsername(staffID);
		model.addAttribute("fullName", user.getFullname());
		model.addAttribute("designation", user.getDesignation());
		return "welcomePage";
	}
    
	@GetMapping("/admin")
	public String getAdminPage() {
		return "adminPage";
	}
	
	@GetMapping("/emp")
	public String getEmployeePage(@AuthenticationPrincipal UserDetails userDetails,
	Model model) {
		String staffID = userDetails.getUsername();
        User user = UserRepo.getUserByUsername(staffID);
		String designation = user.getDesignation();
		DefaultLeaveEntitlement defaultLeave = defaultLeaveEntitlementRepo.findBydesignation(designation);
		model.addAttribute("medicalleave", defaultLeave.getMedicalLeave());
		model.addAttribute("annualleave", defaultLeave.getAnnualLeave());
		model.addAttribute("staffleave", user.getStaffleave());
		return "empPage";
	}

	@GetMapping("/mgr")
	public String getManagerPage() {
		return "mgrPage";
	}
	
    @GetMapping("/accessDenied")
	public String getAccessDeniedPage() {
		return "accessDeniedPage";
	}
}
