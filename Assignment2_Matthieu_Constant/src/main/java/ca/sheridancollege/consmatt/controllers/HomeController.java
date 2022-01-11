package ca.sheridancollege.consmatt.controllers;

import java.security.Principal;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ca.sheridancollege.consmatt.beans.Employee;
import ca.sheridancollege.consmatt.beans.User;
import ca.sheridancollege.consmatt.repositories.EmployeeRepository;
import ca.sheridancollege.consmatt.repositories.UserRepository;





@Controller //Controller annotation used to define this class as a controller. 
public class HomeController {
	
	static ArrayList<Employee> employees = new ArrayList<Employee>();  //Creating ArrayList for Employee Class (Bean) (Part of Database part) 
	
	@Autowired
	private EmployeeRepository employeeRepo; //Creating Object for EmployeeRepository Class (used for the Database)
	
	@Autowired
	private UserRepository userRepo; //Creating Object for UserRepository class (used for Security)
	
	@GetMapping("/")                          //Annotation used to register this method as a mapping
	public String goRoot() {
		return "root.html";                  //Will return you to the root page which is names "root.html" in the templates folder, no directory required as it is expected to be in this folder
	}
	
	
	@GetMapping("/employees") //used to open employees database page, includes security. Also pulls data from database
	public String loadEmployeesPage(Authentication authentication, Model model,  HttpServletRequest req, Principal principal) { 
		String name = authentication.getName();  
		
		ArrayList<String> roles = new ArrayList<String>();
		ArrayList<Employee> employee = new ArrayList<Employee>();
		for (GrantedAuthority ga : authentication.getAuthorities()) {                                
			roles.add(ga.getAuthority());
		}
		model.addAttribute("myEmployees", employeeRepo.getEmployees());	
	    model.addAttribute("myEmployee", employeeRepo.getEmployeeTable(name));
	    System.out.println(principal.getName());
		return "employees.html";
		}
		

	
	@GetMapping("/addEmployee")
	public String loadAddEmployeePage(Model model) {
		model.addAttribute("employee", new Employee());  //Mapping for the add employee page.
		return "addEmployee.html";
	}
	
	@PostMapping("/addEmployee")
	public String addEmployee(@ModelAttribute Employee employee, Model model ) {
		System.out.println(employee);
		model.addAttribute("employee", new Employee());                  
		employees.add(employee);
		employeeRepo.addEmployee(employee);                                     //PostMapping for add employee page, this method is used when you submit the creation of an employee
		userRepo.addUser(employee.getName(), employee.getEmployeeId());
		User user = userRepo.findUserAccount(employee.getName());
		userRepo.addRole(user.getUserId(),2);
		return"redirect:/addEmployee";
	}
	
	@GetMapping("/edit/{id}")    //This method is used to open the edit employee page
	public String loadEdit(@PathVariable int id, Model model) {
		Employee d = employeeRepo.getEmployeeById(id); //The Employee that is selected
		model.addAttribute("employee", d);
		return "editemployee.html";
	}
	
	@PostMapping("/editEmployee")  //This PostMapping will then update the database when you subit your information
	public String editEmployee(@ModelAttribute Employee employee, Model model) {
		employeeRepo.editEmployee(employee);
		model.addAttribute("myEmployees", employeeRepo.getEmployees());
		return "employees.html";
	}
	
	@GetMapping("/delete/{id}") //This method deleted the specific employee
	public String deleteEmployee(@PathVariable int id, Model model) {
		employeeRepo.deleteEmployee(id);
		model.addAttribute("myEmployees", employeeRepo.getEmployees());
		return "employees.html";
	}
	
	
	
}
