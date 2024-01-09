package hrms.payroll.exception;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Builder
@Data
@EqualsAndHashCode(callSuper = true)
public class CSVErrorException extends Exception {

	private static final long serialVersionUID = 6812695205375697337L;

	private String message;
}
