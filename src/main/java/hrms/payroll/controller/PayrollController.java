package hrms.payroll.controller;

import org.springframework.web.bind.annotation.RestController;

import hrms.payroll.service.PayrollService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PayrollController {

	private final PayrollService payrollService;
}
