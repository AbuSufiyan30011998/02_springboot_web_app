package in.ashokit.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "citizen_plan_info")
@Data

public class CitizenPlan {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String email;
	private Long phnNo;
	private Long ssn;
	private String geneder;
	private String planName;
	private String planStatus;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate startPlanDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate endPlanDate;

	public CitizenPlan() {
		super();
	}

	public CitizenPlan(String name, String email, Long phnNo, Long ssn, String geneder, String planName,
			String planStatus, LocalDate startPlanDate, LocalDate endPlanDate) {
		super();
		this.name = name;
		this.email = email;
		this.phnNo = phnNo;
		this.ssn = ssn;
		this.geneder = geneder;
		this.planName = planName;
		this.planStatus = planStatus;
		this.startPlanDate = startPlanDate;
		this.endPlanDate = endPlanDate;
	}
	

}
