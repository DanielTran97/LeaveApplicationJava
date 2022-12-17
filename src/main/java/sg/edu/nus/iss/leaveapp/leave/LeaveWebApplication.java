package sg.edu.nus.iss.leaveapp.leave;

import java.util.Arrays;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;

// import java.rmi.ServerException;
// import java.io.Console;
// import java.time.Duration;
// import java.time.LocalDateTime;
// import java.time.OffsetDateTime;
// import java.util.ArrayList;
// import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
// import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
// import org.springframework.http.HttpHeaders;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.MediaType;
// import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import sg.edu.nus.iss.leaveapp.leave.model.DefaultLeaveEntitlement;
import sg.edu.nus.iss.leaveapp.leave.model.LeaveEntitlement;
import sg.edu.nus.iss.leaveapp.leave.model.LeaveType;
import sg.edu.nus.iss.leaveapp.leave.model.Role;
import sg.edu.nus.iss.leaveapp.leave.model.User;
import sg.edu.nus.iss.leaveapp.leave.repository.DefaultLeaveEntitlementRepository;
import sg.edu.nus.iss.leaveapp.leave.repository.LeaveEntitlementRepository;
import sg.edu.nus.iss.leaveapp.leave.repository.LeaveTypeRepository;
import sg.edu.nus.iss.leaveapp.leave.repository.RoleRepository;
import sg.edu.nus.iss.leaveapp.leave.repository.UserRepository;

// import reactor.core.publisher.Flux;
// import reactor.core.publisher.Mono;
// import sg.edu.nus.iss.demoapp.demo.model.Member;
// import sg.edu.nus.iss.demoapp.demo.model.Role;

@SpringBootApplication
@EnableJpaRepositories
public class LeaveWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(LeaveWebApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRun(UserRepository userRepo, RoleRepository roleRepo, LeaveEntitlementRepository leaveEntitlementRepo, DefaultLeaveEntitlementRepository defaultLeaveEntitlementRepo,
	LeaveTypeRepository leaveTypeRepo){
		return args -> {
			System.out.println("---- Create some roles");
			Role admin = roleRepo.save(new Role("Admin"));
			Role employee = roleRepo.save(new Role("Employee"));
			Role manager = roleRepo.save(new Role("Manager"));

			System.out.println("---- Create some users");
			// username, password, full name, designation, reportingstaffID, contactNum, address, email
			User ram = new User("2531", "John123", "John Tan Meng Keng", "Administrator", "2811", "81012348", "John's Address", "john97@gmail.com");
			User raven = new User("1835", "Tom123", "Tom Lee Shin","Employee", "2811", "78318999", "Tom's Address", "tom89@gmail.com");
			User run = new User("2811", "Sally123", "Sally Ang Jean Tee", "Manager", "2811", "78691111", "Sally's Address", "sally123@gmail.com");

			ram.setRoles(Arrays.asList(admin));
			raven.setRoles(Arrays.asList(employee));
			run.setRoles(Arrays.asList(manager, employee));

			userRepo.save(ram);
			userRepo.save(raven);
			userRepo.save(run);
//since leave entitlement contains the foreign key staff_ID, it can only be set when staff is saved in the database/repository with staff ID.
			LeaveEntitlement staffleave1 = new LeaveEntitlement(ram);
			LeaveEntitlement staffleave2 = new LeaveEntitlement(raven);
			LeaveEntitlement staffleave3 = new LeaveEntitlement(run);
			DefaultLeaveEntitlement manage = new DefaultLeaveEntitlement("Manager");
			DefaultLeaveEntitlement employ = new DefaultLeaveEntitlement("Employee");
			DefaultLeaveEntitlement admins = new DefaultLeaveEntitlement("Administrator");
			defaultLeaveEntitlementRepo.save(manage);
			defaultLeaveEntitlementRepo.save(employ);
			defaultLeaveEntitlementRepo.save(admins);
			leaveEntitlementRepo.save(staffleave1);
			leaveEntitlementRepo.save(staffleave2);
			leaveEntitlementRepo.save(staffleave3);

			LeaveType annualLeave = new LeaveType("annual_leave","1");
			LeaveType medicalLeave = new LeaveType("medical_leave","1");
			LeaveType compensationLeave = new LeaveType("compensation_leave","0.5");
			leaveTypeRepo.save(annualLeave);
			leaveTypeRepo.save(medicalLeave);
			leaveTypeRepo.save(compensationLeave);
		
		};
		
	}

}
