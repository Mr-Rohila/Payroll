package hrms.payroll.controller;

import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hrms.payroll.dto.GenericResponse;
import hrms.payroll.dto.SalaryStructureDto;
import hrms.payroll.service.SalaryStructureService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("salary/structure")
@RequiredArgsConstructor
public class SalaryStructureController {

	private final SalaryStructureService salaryStructureService;

	@GetMapping("create")
	public GenericResponse createStructure(@Valid @RequestBody SalaryStructureDto structureDto) {

		return GenericResponse.builder().message("Created Successfully")
				.data(salaryStructureService.saveSalaryStructure(structureDto)).build();
	}

	@GetMapping("csv/header")
	public ResponseEntity<byte[]> csvHeaders(HttpServletResponse response) {

		byte[] csvBytes = salaryStructureService.csvHeaders().stream().collect(Collectors.joining(","))
				.getBytes(StandardCharsets.UTF_8);

		// Set headers for the response
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		headers.setContentDispositionFormData("attachment", "Salary-Structure_CSV_Template.csv");

		// Return the ResponseEntity with the CSV content and headers
		return ResponseEntity.ok().headers(headers).body(csvBytes);
	}

	@PostMapping("csv/upload")
	public GenericResponse uploadCsv() {
		return null;
	}

}