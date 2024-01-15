package hrms.payroll.enums;

public enum PayrollStructureCSVHeader {

	SNO("Sno"), EMPLOYEE_NAME("Employee Name"), EMPLOYEE_ID("Employee Id"), CTC("CTC"), BASIC("Basic"), PF("PF"),
	HRA("HRA"), MEDICAL("Medical"), TRAVEL_REIMBURSEMENT("Travel Reimbursement"),
	OTHER_REIMBURSEMENT("Other Reimbursement");

	private final String value;

	PayrollStructureCSVHeader(final String value) {
		this.value = value;
	}

	public String value() {
		return value;
	}
}
