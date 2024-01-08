package hrms.payroll.service.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import hrms.payroll.dto.SalaryStructureDto;
import hrms.payroll.service.SalaryStructureService;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SalaryStructureServiceImpl implements SalaryStructureService {
	@Override
	public SalaryStructureDto saveSalaryStructure(SalaryStructureDto structureDto) {
		return null;
	}

	@Override
	public List<String> csvHeaders() {
		return List.of("Sno", "Employee Id", "Employee Name");
	}

}
