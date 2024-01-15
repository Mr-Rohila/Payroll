package hrms.payroll.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import feign.FeignException;
import hrms.payroll.dto.GenericResponse;
import hrms.payroll.dto.SalaryStructureDto;
import hrms.payroll.entity.SalaryStructure;
import hrms.payroll.enums.PayrollStructureCSVHeader;
import hrms.payroll.exception.CSVErrorException;
import hrms.payroll.repository.SalaryStructureRepository;
import hrms.payroll.rest.RestEmployeeService;
import hrms.payroll.service.SalaryStructureService;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SalaryStructureServiceImpl implements SalaryStructureService {

	private final Validator validator;

	private final SalaryStructureRepository salaryStructureRepository;
	private final RestEmployeeService restEmployeeService;

	@SuppressWarnings("unchecked")
	@Override
	public SalaryStructureDto saveSalaryStructure(SalaryStructureDto structureDto) throws Exception {

		// validate employee
		GenericResponse employeeData = null;
		try {
			employeeData = restEmployeeService.getEmployeById(structureDto.getEmployeeId());
		} catch (Exception e) {
			throw new Exception("Employee not found with employee Id : " + structureDto.getEmployeeId()
					+ ", Error message : " + e.getLocalizedMessage());
		}
		Map<String, Object> employee = (Map<String, Object>) employeeData.getData();

		Map<String, Object> personalDetails = (Map<String, Object>) employee.get("personalDetails");

		String fullName = personalDetails.get("fullName").toString();

		if (!fullName.equalsIgnoreCase(structureDto.getEmployeeName())) {
			throw new Exception("Employee name invalid, The right name is " + fullName);
		}

		SalaryStructure salaryStructure = new SalaryStructure(structureDto);
		salaryStructureRepository.save(salaryStructure);
		return structureDto;

	}

	@Override
	public List<String> csvHeaders() {
		return Arrays.asList(PayrollStructureCSVHeader.values()).stream().map(PayrollStructureCSVHeader::value)
				.map(String::trim).toList();
	}

	@Override
	public String csvUpload(InputStream inputStream) throws IOException, CSVErrorException {
		StringBuilder errorTxt = new StringBuilder();

		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

		CSVFormat csvFormat = CSVFormat.DEFAULT.builder().setHeader(PayrollStructureCSVHeader.class)
				.setIgnoreHeaderCase(true).setTrim(true).build();
		CSVParser csvParser = csvFormat.parse(reader);

		Iterable<CSVRecord> iterable = csvParser.getRecords();

		long rowCount = 0;
		for (CSVRecord csvRecord : iterable) {
			rowCount++;

			if (rowCount == 1) {
				// validate Headers
				validateHeaders(csvRecord.toList());
				continue;
			}
			//

			try {
				SalaryStructureDto csvToSalaryStructureDto = csvToSalaryStructureDto(csvRecord);
				Errors errors = new BeanPropertyBindingResult(csvToSalaryStructureDto, "csvToSalaryStructureDto");
				ValidationUtils.invokeValidator(validator, csvToSalaryStructureDto, errors);
				if (errors.hasErrors()) {
					final long row = rowCount;
					// get all error and add to error file
					errors.getAllErrors().forEach((error) -> {
						String errorMessage = error.getDefaultMessage();
						errorTxt.append("\t Data Error, row = " + (row - 1) + " : " + errorMessage + " \n");
					});
				} else {
					saveSalaryStructure(csvToSalaryStructureDto);
				}
			} catch (Exception e) {
				e.printStackTrace();
				errorTxt.append("\t Data Error, row = " + (rowCount - 1) + " : " + e.getLocalizedMessage() + " \n");
			}
		}

		return errorTxt.toString();

	}

	private SalaryStructureDto csvToSalaryStructureDto(CSVRecord csvRecord) throws NumberFormatException {
		SalaryStructureDto dto = new SalaryStructureDto();

		dto.setEmployeeId(Long.valueOf(csvRecord.get(PayrollStructureCSVHeader.EMPLOYEE_ID)));

		dto.setEmployeeName(csvRecord.get(PayrollStructureCSVHeader.EMPLOYEE_NAME));

		dto.setCtc(Long.valueOf(csvRecord.get(PayrollStructureCSVHeader.CTC)));

		dto.setBasic(Long.valueOf(csvRecord.get(PayrollStructureCSVHeader.BASIC)));

		dto.setPf(Long.valueOf(csvRecord.get(PayrollStructureCSVHeader.PF)));

		dto.setHra(Long.valueOf(csvRecord.get(PayrollStructureCSVHeader.HRA)));

		dto.setMedical(Long.valueOf(csvRecord.get(PayrollStructureCSVHeader.MEDICAL)));

		dto.setTravelReimbursement(Long.valueOf(csvRecord.get(PayrollStructureCSVHeader.TRAVEL_REIMBURSEMENT)));

		dto.setOtherReimbursement(Long.valueOf(csvRecord.get(PayrollStructureCSVHeader.OTHER_REIMBURSEMENT)));

		return dto;
	}

	private void validateHeaders(List<String> actualHeaders) throws CSVErrorException {
		// Check if headers are present and compare them with expected headers
		List<String> expectedHeaders = csvHeaders();

		if (actualHeaders == null || !expectedHeaders.equals(actualHeaders))
			throw CSVErrorException.builder()
					.message("CSV file headers do not match the expected headers " + expectedHeaders.toString())
					.build();

	}

}
