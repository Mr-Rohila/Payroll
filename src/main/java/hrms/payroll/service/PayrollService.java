package hrms.payroll.service;

import java.io.InputStream;
import java.util.List;

import org.springframework.stereotype.Service;

import hrms.payroll.dto.PayrollDto;

@Service
public interface PayrollService {

	List<PayrollDto> payrollList();

	List<PayrollDto> payrollByMonth(String monthName);

	List<PayrollDto> payrollByEmployee(Long employeeId);

	PayrollDto payrollByEmployeeAndMonth(Long employeeId, String monthName);

	List<String> payrollCsvHeader();

	String csvUpload(InputStream inputStream);
}
