package hrms.payroll.rest;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import hrms.payroll.dto.GenericResponse;

@FeignClient(name = "EMPLOYEE-SERVICE")
@Service
public interface RestEmployeeService {
	@GetMapping("employee//{employeeId}")
	GenericResponse getEmployeById(@PathVariable Long employeeId);
}
