package jp.co.sample.repository;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.sample.domain.Employee;


@Repository
public class EmployeeRepository {
	
	/**
	 * @param 
	 * @param  
	 * @param  
	 * @return 
	 * 
	 * 
	 */


	@Autowired
	
	private NamedParameterJdbcTemplate template;
	
	private static final RowMapper<Employee> EMPLOYEE_ROW_MAPPER = new BeanPropertyRowMapper<>(Employee.class);
	
	public List<Employee> findAll(){
		String findASql ="SELECT id,name,image,gender,hire_date,mail_address,zip_code,address,telephone,salary,characteristics,dependents_count FROM employees ORDER BY hire_date DESC";
		List<Employee> employeeList
		= template.query(findASql, EMPLOYEE_ROW_MAPPER); 
		return employeeList;
//		if (employeeList.size() == 0) {
//		return Collections.EMPTY_LIST; }
//		return (List<Employee>) employeeList.get(0);
	}
	
	public Employee load(Integer id) {
		String loadSql = "SELECT id,name,image,gender,hire_date,mail_address,"
				+ " zip_code,address,telephone,salary,characteristics,dependents_count "
				+ " FROM employees WHERE id=:id";
//		SqlParameterSource param = new BeanPropertySqlParameterSource(employee);
		SqlParameterSource param = new MapSqlParameterSource().addValue("id",id);
		Employee employee = template.queryForObject(loadSql, param, EMPLOYEE_ROW_MAPPER);
		
		return employee;
	}
	
	public void update(Employee employee) {
		String updateSql = "UPDATE employees SET name=:name, image=:image, gender=:gender, hire_date=:hireDate"
				+ ", zip_code=:zipCode, address=:address, telephone=:telephone, salary=:salary"
				+ ", characteristics=:characteristics, dependents_count=:dependentsCount"
				+ " WHERE id=:id ";
				
		SqlParameterSource param = new BeanPropertySqlParameterSource(employee);
		template.update(updateSql, param);
	}
}
