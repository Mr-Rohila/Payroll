package hrms.payroll.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hrms.payroll.entity.SalaryStructure;

@Repository
public interface SalaryStructureRepository extends JpaRepository<SalaryStructure, Long> {

	List<SalaryStructure> findByEmployeeId(Long employeeId);

	List<SalaryStructure> findByEmployeeIdAndCtc(Long employeeId, Long ctc);
}
