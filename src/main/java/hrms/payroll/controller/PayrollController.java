package hrms.payroll.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hrms.payroll.dto.GenericResponse;
import hrms.payroll.service.PayrollService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("payroll")
@RequiredArgsConstructor
public class PayrollController {

	private final PayrollService payrollService;

	@GetMapping("list")
	public GenericResponse payrollList() {
		return GenericResponse.builder().data(payrollService.payrollList()).message("Success").build();
	}

	@GetMapping("list/month/{monthName}")
	public GenericResponse payrollByMonth(@PathVariable String monthName) {
		return GenericResponse.builder().data(payrollService.payrollByMonth(monthName)).message("Success").build();
	}

	@GetMapping("employee/{employeeId}")
	public GenericResponse payrollByEmploye(@PathVariable Long employeeId) {
		return null;
	}

}
