package hrms.payroll.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.stereotype.Service;

import hrms.payroll.dto.SalaryStructureDto;
import hrms.payroll.exception.CSVErrorException;

@Service
public interface SalaryStructureService {

	public SalaryStructureDto saveSalaryStructure(SalaryStructureDto structureDto) throws Exception;

	List<String> csvHeaders();

	String csvUpload(InputStream inputStream) throws IOException, CSVErrorException;
}
