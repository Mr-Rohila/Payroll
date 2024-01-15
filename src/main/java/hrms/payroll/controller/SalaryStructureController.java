package hrms.payroll.controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import hrms.payroll.dto.GenericResponse;
import hrms.payroll.dto.SalaryStructureDto;
import hrms.payroll.exception.CSVErrorException;
import hrms.payroll.service.SalaryStructureService;
import hrms.payroll.utils.CSVHelper;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("salary/structure")
@RequiredArgsConstructor
public class SalaryStructureController {

	private final SalaryStructureService salaryStructureService;

	@GetMapping("create")
	public GenericResponse createStructure(@Valid @RequestBody SalaryStructureDto structureDto) throws Exception {

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
	public GenericResponse uploadCsv(@RequestParam MultipartFile file) throws IOException, CSVErrorException {

		if (CSVHelper.hasCSVFormat(file)) {
			String message = salaryStructureService.csvUpload(file.getInputStream());
			if (message.isBlank())
				return GenericResponse.builder().message("CSV Data Upload Successfully").build();
			else
				throw CSVErrorException.builder().message(message).build();
		}
		return GenericResponse.builder().message("Invalid CSV format").build();
	}

}