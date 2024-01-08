package hrms.payroll.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SalaryStructureDto {

	@NotNull(message = "Invalid employee Id")
	private Long employeId;

	@NotBlank(message = "Employee name can not be empty")
	private String employeeName;

	@NotNull(message = "CTC can not be empty")
	private Long ctc;

	private Long pf;

	private Long hra;

	private Long medical;

	private Long travelReimbursement;

	private Long otherReimbursement;
}
