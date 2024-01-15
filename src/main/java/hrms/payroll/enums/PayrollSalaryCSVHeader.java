package hrms.payroll.enums;

public enum PayrollSalaryCSVHeader {

	SNO("Sno"), EMPLOYEE_NAME("Employee Name"), EMPLOYEE_ID("Employee Id");

	private final String value;

	PayrollSalaryCSVHeader(final String value) {
		this.value = value;
	}

	public String value() {
		return value;
	}
}
