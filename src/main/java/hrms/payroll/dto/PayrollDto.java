package hrms.payroll.dto;

import hrms.payroll.entity.Payroll;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PayrollDto {

	@NotNull(message = "Employee Id can not be blank")
	private Long employeId;

	@NotBlank(message = "Employee name can not be blank")
	private String employeeName;

	@NotNull(message = "CTC can not be blank")
	private Long ctc;

	public PayrollDto() {
	}

	public PayrollDto(Payroll payroll) {
	}

}
