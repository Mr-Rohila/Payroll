package hrms.payroll.entity;

import hrms.payroll.dto.SalaryStructureDto;
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

	private boolean status;

	public SalaryStructure() {
	}

	public SalaryStructure(SalaryStructureDto dto) {
		this.employeeId = dto.getEmployeeId();

		this.employeeName = dto.getEmployeeName();

		this.ctc = dto.getCtc();

		this.hra = dto.getHra();

		this.pf = dto.getPf();

		this.medical = dto.getMedical();

		this.travelReimbursement = dto.getTravelReimbursement();

		this.otherReimbursement = dto.getOtherReimbursement();

		this.status = true;
	}
}