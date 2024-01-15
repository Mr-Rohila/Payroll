package hrms.payroll.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

import hrms.payroll.dto.PayrollDto;
import hrms.payroll.entity.Payroll;
import hrms.payroll.enums.PayrollSalaryCSVHeader;
import hrms.payroll.exception.CSVErrorException;
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

	@Override
	public List<String> payrollCsvHeader() {
		return Arrays.asList(PayrollSalaryCSVHeader.values()).stream().map(PayrollSalaryCSVHeader::value)
				.map(String::trim).toList();
	}

	@Override
	public String csvUpload(InputStream inputStream) throws CSVErrorException {

		StringBuilder errorTxt = new StringBuilder();

		try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {

			CSVFormat csvFormat = CSVFormat.DEFAULT.builder().setHeader(PayrollSalaryCSVHeader.class)
					.setIgnoreHeaderCase(true).setTrim(true).build();
			CSVParser csvParser = csvFormat.parse(bufferedReader);

			Iterable<CSVRecord> iterable = csvParser.getRecords();

			long rowCont = 0;
			for (CSVRecord csvRecord : iterable) {
				rowCont++;

				if (rowCont == 1) {
					// validate header
					validatePayrollCSVHeader(csvRecord.toList());
					continue;
				}

				PayrollDto payrollDto = csvToPayrollDto(csvRecord);

			}

		} catch (IOException e) {
			e.printStackTrace();
			errorTxt.append("\ncan not read CSV file : reason => " + e.getLocalizedMessage());
		}
		return errorTxt.toString();
	}

	private PayrollDto csvToPayrollDto(CSVRecord csvRecord) {
		PayrollDto dto = new PayrollDto();

		return dto;
	}

	private void validatePayrollCSVHeader(List<String> csvHeader) throws CSVErrorException {
		List<String> actualHeader = payrollCsvHeader();
		if (csvHeader == null || (csvHeader.size() != PayrollSalaryCSVHeader.values().length)
				|| !csvHeader.equals(actualHeader)) {
			throw CSVErrorException.builder()
					.message("CSV file headers do not match the expected headers " + actualHeader).build();
		}
	}
}
