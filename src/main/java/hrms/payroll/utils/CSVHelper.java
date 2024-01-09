package hrms.payroll.utils;

import org.springframework.web.multipart.MultipartFile;

public class CSVHelper {
	private static final String TYPE = "text/csv";

	public static boolean hasCSVFormat(MultipartFile file) {
		return TYPE.equals(file.getContentType());
	}
}
