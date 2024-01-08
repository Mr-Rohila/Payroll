package hrms.payroll.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class SalaryStructure {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long employeeId;

	private String employeeName;

	private Long ctc;

	private Long hra;

	private Long pf;

	private Long medical;

	private Long travelReimbursement;

	private Long otherReimbursement;
}