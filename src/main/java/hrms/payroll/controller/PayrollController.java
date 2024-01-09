package hrms.payroll.controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import hrms.payroll.dto.GenericResponse;
import hrms.payroll.exception.CSVErrorException;
import hrms.payroll.service.PayrollService;
import hrms.payroll.utils.CSVHelper;
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

	@GetMapping("csv/header")
	public ResponseEntity<byte[]> payrollCsvHeader() {
		byte[] csvHeaders = payrollService.payrollCsvHeader().stream().map(String::trim)
				.collect(Collectors.joining(",")).getBytes(StandardCharsets.UTF_8);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		headers.setContentDispositionFormData("attachment", "Payroll_CSV_Template.csv");

		return ResponseEntity.ok().headers(headers).body(csvHeaders);
	}

	@PostMapping("csv/upload")
	public GenericResponse csvUpload(@RequestParam MultipartFile file)
			throws hrms.payroll.exception.CSVErrorException, IOException {

		if (CSVHelper.hasCSVFormat(file)) {
			String message = payrollService.csvUpload(file.getInputStream());
			if (message.isBlank())
				return GenericResponse.builder().message("CSV Data Upload Successfully").build();
			else
				throw CSVErrorException.builder().message(message).build();

		}
		return GenericResponse.builder().message("Invalid CSV Format").build();
	}

}
