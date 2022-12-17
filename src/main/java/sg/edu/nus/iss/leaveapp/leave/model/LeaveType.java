package sg.edu.nus.iss.leaveapp.leave.model;
import java.util.List;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class LeaveType {
	@Id
	private String name;
	
	private String granularity;
	
}