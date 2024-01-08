package hrms.payroll.entity;

import hrms.payroll.enums.MonthName;
import hrms.payroll.enums.PayrollType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Payroll {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long employeeId;

	private Long employeeName;

	@Enumerated(EnumType.STRING)
	private MonthName monthName;

	@Enumerated(EnumType.STRING)
	private PayrollType type;
}
