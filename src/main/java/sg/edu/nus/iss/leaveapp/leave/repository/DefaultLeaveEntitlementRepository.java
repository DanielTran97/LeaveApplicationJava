package sg.edu.nus.iss.leaveapp.leave.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.leaveapp.leave.model.DefaultLeaveEntitlement;
import sg.edu.nus.iss.leaveapp.leave.model.LeaveApplication;

@EnableJpaRepositories
@Repository
public interface DefaultLeaveEntitlementRepository extends JpaRepository<DefaultLeaveEntitlement, Long> {
    public DefaultLeaveEntitlement findBydesignation(String designation);
}