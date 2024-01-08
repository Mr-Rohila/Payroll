package hrms.payroll.service.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import hrms.payroll.dto.PayrollDto;
import hrms.payroll.entity.Payroll;
import hrms.payroll.repository.PayrollRepository;
import hrms.payroll.service.PayrollService;
import hrms.payroll.utils.MonthUtils;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PayrollServiceImpl implements PayrollService {

	private final PayrollRepository payrollRepository;

	@Override
	public List<PayrollDto> payrollList() {
		return payrollRepository.findAll().stream().map(PayrollDto::new).toList();
	}

	@Override
	public List<PayrollDto> payrollByMonth(String monthName) {

		return payrollRepository.findByMonthName(MonthUtils.convertMonthName(monthName)).stream().map(PayrollDto::new)
				.toList();
	}

	@Override
	public List<PayrollDto> payrollByEmployee(Long employeeId) {
		return payrollRepository.findByEmployeeId(employeeId).stream().map(PayrollDto::new).toList();
	}

	@Override
	public PayrollDto payrollByEmployeeAndMonth(Long employeeId, String monthName) {
		List<Payroll> findByEmployeeIdAndMonthName = payrollRepository.findByEmployeeIdAndMonthName(employeeId, null);

		if (!findByEmployeeIdAndMonthName.isEmpty()) {
			return new PayrollDto(findByEmployeeIdAndMonthName.get(0));
		}
		return null;
	}

	public void calculatePayroll(Long employeeId) {

	}

}
