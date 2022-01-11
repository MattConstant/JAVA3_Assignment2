package ca.sheridancollege.consmatt.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ca.sheridancollege.consmatt.beans.Employee;


@Repository
public class EmployeeRepository {

	@Autowired
	private NamedParameterJdbcTemplate jdbc;
	
	public void addEmployee(Employee employee) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
			
			//This query creates a SQL statement that will add the data into a database
			String query = "INSERT INTO employee (name, employeeId, sunday, monday, tuesday, wednesday, thursday, friday, saturday, wage, totalHours, pay) VALUES (:name, :employeeId, :sunday, :monday, :tuesday, :wednesday, :thursday, :friday, :saturday, :wage, :totalHours, :pay)";
			parameters.addValue("name", employee.getName());
			parameters.addValue("employeeId", employee.getEmployeeId());
			parameters.addValue("sunday", employee.getSunday());
			parameters.addValue("monday", employee.getMonday());
			parameters.addValue("tuesday", employee.getTuesday());
			parameters.addValue("wednesday", employee.getWednesday());       //This is the method to add an employee to the database, this will forward the parameters to the SQL database
			parameters.addValue("thursday", employee.getThursday());
			parameters.addValue("friday", employee.getFriday());
			parameters.addValue("saturday", employee.getSaturday());
			parameters.addValue("wage", employee.getWage());
			parameters.addValue("totalHours", employee.getTotalHours());
			parameters.addValue("pay", employee.getPay());
			jdbc.update(query, parameters); //this updates the query string which is the SQL statement and the parameters into the SQL database
		}
	
	public ArrayList<Employee> getEmployees() {		//This will return the information to the database
		ArrayList<Employee> employees = new ArrayList<Employee>();
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "SELECT * FROM employee";  //Another query to be sent to the SQL database
		List<Map<String, Object>> rows = jdbc.queryForList(query, parameters);  
		for (Map<String, Object> row : rows) {       //depending on how many lines you have in the database, this will pull up all data
			Employee d = new Employee();
			d.setId((Integer)row.get("id"));
			d.setName((String)row.get("name"));
			d.setEmployeeId((String)row.get("employeeId"));
			d.setSunday((Double)row.get("sunday"));
			d.setMonday((Double)row.get("monday"));
			d.setTuesday((Double)row.get("tuesday"));
			d.setWednesday((Double)row.get("wednesday"));
			d.setThursday((Double)row.get("thursday"));
			d.setFriday((Double)row.get("friday"));
			d.setSaturday((Double)row.get("saturday"));
			d.setPay((Double)row.get("saturday"));
			d.setWage((Double)row.get("wage"));
			d.setTotalHours((Double)row.get("totalHours"));
			d.setPay((Double)row.get("pay"));
			employees.add(d);  //adds all data to an employee object and this will be used when opening the view employees page
		}
		return employees;   //returns employees to where ever it is called, in this case, the view employees page
	}
	
	public Employee getEmployeeById(int id) { //This allows you to get a single employee based on their ID's
		ArrayList<Employee> employees = new ArrayList<Employee>();
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query ="SELECT * FROM employee WHERE id=:id";
		parameters.addValue("id", id);
		List<Map<String, Object>> rows = jdbc.queryForList(query, parameters);
		
		for(Map<String, Object> row:rows) {
			Employee d = new Employee();
			d.setId((Integer)row.get("id"));
			d.setName((String)row.get("name"));
			d.setEmployeeId((String)row.get("employeeId"));
			d.setSunday((Double)row.get("sunday"));
			d.setMonday((Double)row.get("monday"));
			d.setTuesday((Double)row.get("tuesday"));
			d.setWednesday((Double)row.get("wednesday"));
			d.setThursday((Double)row.get("thursday"));
			d.setFriday((Double)row.get("friday"));
			d.setSaturday((Double)row.get("saturday"));
			d.setWage((Double)row.get("wage"));
			d.setTotalHours((Double)row.get("totalHours"));
			d.setPay((Double)row.get("pay"));
			employees.add(d);
		}
		if(employees.size() == 1) {
			return employees.get(0);
		}else {
			return null;
		}
	}
	
	public void editEmployee(Employee employee) {    //Same as the addEmployee method
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "UPDATE employee SET name=:name, sunday=:sunday, monday=:monday, tuesday=:tuesday, wednesday=:wednesday, thursday=:thursday, friday=:friday,  saturday=:saturday, wage=:wage, totalHours=:totalHours, pay=:pay WHERE id=:id";
		parameters.addValue("name", employee.getName());
		parameters.addValue("sunday", employee.getSunday());
		parameters.addValue("monday", employee.getMonday());
		parameters.addValue("tuesday", employee.getTuesday());
		parameters.addValue("wednesday", employee.getWednesday());
		parameters.addValue("thursday", employee.getThursday());
		parameters.addValue("friday", employee.getFriday());
		parameters.addValue("saturday", employee.getSaturday());
		parameters.addValue("wage", employee.getWage());
		parameters.addValue("totalHours", employee.getTotalHours());
		parameters.addValue("pay", employee.getPay());
		parameters.addValue("id", employee.getId());
		jdbc.update(query,  parameters);
	}
	
	public void deleteEmployee(int id) {
		MapSqlParameterSource parameters = new MapSqlParameterSource(); //Simply passes a delete query to remove the selected employee 
		String query = "DELETE FROM employee WHERE id=:id";
		parameters.addValue("id", id);
		jdbc.update(query, parameters);
	}
	
	public Employee getEmployeeTable(String name)            //This was some willy nilly bullshit that worked for my assignment and I don't remember how 
	{
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query ="SELECT * FROM employee WHERE name=:name";
		ArrayList<Employee> employees = new ArrayList<Employee>();
		parameters.addValue("name", name);
		List<Map<String, Object>> rows = jdbc.queryForList(query, parameters);
		
		for(Map<String, Object> row: rows) 
		{
			Employee c = new Employee();
			c.setId((Integer)row.get("id"));
			c.setName((String)row.get("name"));
			c.setEmployeeId((String)row.get("employeeId"));
			c.setSunday((Double)row.get("sunday"));
			c.setMonday((Double)row.get("monday"));
			c.setTuesday((Double)row.get("tuesday"));
			c.setWednesday((Double)row.get("wednesday"));
			c.setThursday((Double)row.get("thursday"));
			c.setFriday((Double)row.get("friday"));
			c.setSaturday((Double)row.get("saturday"));
			c.setWage((Double)row.get("wage"));
			c.setTotalHours((Double)row.get("totalHours"));
			c.setPay((Double)row.get("pay"));
			employees.add(c);
		}
		if(employees.size() == 1) {
			return employees.get(0);
		}else {
			return null;
		}
	}
	

	
	
	
}
