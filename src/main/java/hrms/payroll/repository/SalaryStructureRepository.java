package hrms.payroll.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hrms.payroll.entity.SalaryStructure;

@Repository
public interface SalaryStructureRepository extends JpaRepository<SalaryStructure, Long> {

}
