package hrms.payroll.service;

import java.util.List;

import org.springframework.stereotype.Service;

import hrms.payroll.dto.SalaryStructureDto;

@Service
public interface SalaryStructureService {

	public SalaryStructureDto saveSalaryStructure(SalaryStructureDto structureDto);

	List<String> csvHeaders();
}
