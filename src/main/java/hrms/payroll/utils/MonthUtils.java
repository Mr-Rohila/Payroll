package hrms.payroll.utils;

import hrms.payroll.enums.MonthName;

public class MonthUtils {

	public static MonthName getCurrentMonthName(int minusMonth) {
		return convertMonthName(java.time.LocalDate.now().getMonthValue() - minusMonth);
	}

	public static MonthName convertMonthName(int monthValue) {
		switch (monthValue) {
		case 1:
			return MonthName.JANUARY;
		case 2:
			return MonthName.FEBRUARY;
		case 3:
			return MonthName.MARCH;
		case 4:
			return MonthName.APRIL;
		case 5:
			return MonthName.MAY;
		case 6:
			return MonthName.JUNE;
		case 7:
			return MonthName.JULY;
		case 8:
			return MonthName.AUGUST;
		case 9:
			return MonthName.SEPTEMBER;
		case 10:
			return MonthName.OCTOBER;
		case 11:
			return MonthName.NOVEMBER;
		case 12:
			return MonthName.DECEMBER;
		default:
			throw new IllegalArgumentException("Invalid month value " + monthValue);
		}

	}

	public static MonthName convertMonthName(String monthName) {

		switch (monthName.toUpperCase()) {
		case "JANUARY":
			return MonthName.JANUARY;
		case "FEBRUARY":
			return MonthName.FEBRUARY;
		case "MARCH":
			return MonthName.MARCH;
		case "APRIL":
			return MonthName.APRIL;
		case "MAY":
			return MonthName.MAY;
		case "JUNE":
			return MonthName.JUNE;
		case "JULY":
			return MonthName.JULY;
		case "AUGUST":
			return MonthName.AUGUST;
		case "SEPTEMBER":
			return MonthName.SEPTEMBER;
		case "OCTOBER":
			return MonthName.OCTOBER;
		case "NOVEMBER":
			return MonthName.NOVEMBER;
		case "DECEMBER":
			return MonthName.DECEMBER;
		default:
			throw new IllegalArgumentException("Invalid month name " + monthName);
		}

	}

}
