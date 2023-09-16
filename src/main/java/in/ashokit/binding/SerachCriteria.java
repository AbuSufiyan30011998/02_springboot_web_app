package in.ashokit.binding;

import java.time.LocalDate;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class SerachCriteria {

	private String planName;
	private String planStatus;
	private String geneder;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate startPlanDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate endPlanDate;

}
