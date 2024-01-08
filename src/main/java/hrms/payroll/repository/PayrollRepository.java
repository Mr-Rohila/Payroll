package hrms.payroll.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hrms.payroll.entity.Payroll;
import hrms.payroll.enums.MonthName;

@Repository
public interface PayrollRepository extends JpaRepository<Payroll, Long> {

	List<Payroll> findByMonthName(MonthName monthName);

	List<Payroll> findByEmployeeId(Long employeeId);

	List<Payroll> findByEmployeeIdAndMonthName(Long employeeId, MonthName monthName);
}
