package com.denise.employmentsystem.controller;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.metamodel.IdentifiableType;
import javax.servlet.http.HttpServletRequest;
import javax.swing.plaf.basic.BasicOptionPaneUI.ButtonActionListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.denise.employmentsystem.model.Compensation;
import com.denise.employmentsystem.model.CompensationMonth;
import com.denise.employmentsystem.model.CompensationSearch;
import com.denise.employmentsystem.model.Employee;
import com.denise.employmentsystem.services.ICompensationService;
import com.denise.employmentsystem.services.IEmployeeService;

@org.springframework.stereotype.Controller
@RequestMapping
public class Controller {
	int results;
	
	@Autowired
	private IEmployeeService service;
	
	@Autowired
	private ICompensationService service2;
	
	
	@GetMapping("/employment")
	public String start() {
		return "index";
	}
	
	@GetMapping("/viewAddEmployee")
	public String view(Model model) {
		List<Employee> employees = service.view();
		model.addAttribute("employees", employees);
		return "viewAddEmployee";
	}
	
	
	@GetMapping("/viewEmployees")
	public List<Employee> viewEmployees() {
		return service.view();
	}
	
	//Compensation
	
	@GetMapping("/viewCompensations")
	public String viewCompensations(Model model) {
		List<Compensation> compensations=service2.viewAll();
		
		model.addAttribute("compensations", compensations);
		return "viewCompensations";
	}
	
	
	
	@GetMapping("/newCompensation/{idEmployee}")
	public String addCompensation(Model model, @PathVariable(name="idEmployee") int idEmployee ) {
		
		Compensation cp = new Compensation();
		cp.setIdEmployee(idEmployee);
		model.addAttribute("compensation", cp);
		
		return "formCompensation";
	}
	
	
	
	@PostMapping("/saveCompensation")
	public String saveCompensation(@Validated @ModelAttribute("compensation") Compensation compensation, Model model, RedirectAttributes att) {
		int tm =0; 
		String sal = "Salary";
		String sal1 = compensation.getType();
		if(sal1.equals(sal)) {
			List<Compensation> compensations = service2.buscarByTypeAndDateAndIdEmployee(compensation.getType(), compensation.getDate(), compensation.getIdEmployee());
			tm = compensations.size();
			System.out.println(sal1);
			System.out.println(sal);
		}
		System.out.println(sal1);
		System.out.println(sal);
		if(tm>=1) {
			att.addFlashAttribute("error", "This employee has a salary in that date");
		}else {
			att.addFlashAttribute("success", "Compensation saved successful");
			 service2.add(compensation);
		}
		
		return "redirect:/searchEmployee";
	}
	
	
	
	//viewCompensation
	@GetMapping("/viewCompensation/{idEmployee}")
	public String viewCompensation(@PathVariable(name="idEmployee") int idEmployee, Model model) {
		
		CompensationSearch cp = new CompensationSearch();
		//cp.setIdEmployee(idEmployee);
		model.addAttribute("compensation", new CompensationSearch());
		
		//model.addAttribute("idEmployee", idEmployee);
		
		
		return "formCompensationHistory";
	}
	//viewCompensation
	
	
	//viewCompensation
	
	@GetMapping("/searchByDate/{idEmployee}")
	public String searchByDate( @PathVariable(name="idEmployee") int idEmployee, @RequestParam String date1, 
			@RequestParam String date2 ,@ModelAttribute("compensation") CompensationSearch compensation, 
			Model model ) {
		
		String date1get = compensation.getDate1().substring(0, 7);
		String date2get = compensation.getDate2().substring(0, 7);
		List<Compensation> compensationes = service2.buscarByIdEmployeeAndDate(idEmployee, date1get, date2get);
		List<CompensationMonth> months = new ArrayList<>();
		String mes="";
		
		String yearn = compensation.getDate1().substring(0, 4);
		String yearm = compensation.getDate2().substring(0, 4);
		
		Boolean  moreThanYear = ( Integer.parseInt(yearn)  - Integer.parseInt(yearm)) >= 1;
		
		String last = "";
		for(Compensation c: compensationes) {
			String dateMonthYear;
			
			if(moreThanYear) {
				dateMonthYear = c.getDate() + " - " + c.getDate();
				
			}else {
				dateMonthYear = c.getDate();
			}
			
			
			if(months.size() == 0) {
				String month = c.getDate().substring(5, 7);
				String yearz = c.getDate().substring(0, 4);
				
				months.add(new CompensationMonth(dateMonthYear, c.getAmount(), month, Integer.parseInt(yearz) ));
				last = dateMonthYear;
				
			}else {
				if(dateMonthYear.equals(last)) {
					
					months.get(months.size() - 1).addAmount(c.getAmount());
					
				}else {
					String monthx = c.getDate().substring(5, 7);
					String yeary = c.getDate().substring(0, 4);
					
					months.add(new CompensationMonth(dateMonthYear, c.getAmount(), monthx, Integer.parseInt(yeary) ));
					last = dateMonthYear;
				}
			}
		}

		
		for(int i=0 ; i<months.size()-1; i++){
		     for(int j=months.size()-1; j>i; j--){
		    	CompensationMonth x = months.get(i);
				String monthComp = x.getMonth();
					
				CompensationMonth y = months.get(j);
				String monthCompAux = y.getMonth();
					
		       if(monthComp.equals(monthCompAux))
		       {
		    	   x.addAmount((y.getAmount()));
		    	   months.remove(j);
		       } 
		       
		     } 
		   } 
		
		model.addAttribute("compensationes", months);
		
		//model.addAttribute("idEmployee", idEmployee);
		
		
		return "formCompensationHistory";
	}
	//close Compensation
	
	
	@GetMapping("/viewDetails/{idEmployee}/{month}/{amount}")
	public String viewDetails( @PathVariable(name="idEmployee") int idEmployee,  @PathVariable(name="month")  String month, 
			@PathVariable(name="amount")  double amount, Model model ) {
		
		List<Compensation> compensationes = service2.buscarByIdEmployee(idEmployee);
		List<Compensation> compensations = new ArrayList<Compensation>();
		
		for(Compensation c: compensationes) {
			
			String months = c.getDate().substring(5, 7);
			String yearz = c.getDate().substring(0, 4);
			String date = yearz + "-" + months;
			
			Boolean  equalToMonth = date.equals(month);
			
			if(equalToMonth) {
				compensations.add(c);
			}
		}
		String monthh = month.substring(5,7);
		System.out.println(amount);
		CompensationMonth cm = new CompensationMonth(month, amount, monthh, idEmployee);
		model.addAttribute("amount", cm);
		model.addAttribute("compensations", compensations);
		
		return "viewCompensations";
	}
	
	
	
	@GetMapping("/editCompensation/{idCompensation}")
	public String editCompensation(@PathVariable (name="idCompensation") int idCompensation, 
			 Model model) {
		Optional<Compensation> cmp = service2.viewId(idCompensation);
		
		model.addAttribute("compensation", cmp);
		
		return "formCompensationEdit";
	}
	
	
	
	@PostMapping("/saveCompensationEdited/{idCompensation}")
	public String saveCompensationEdited(@PathVariable (name="idCompensation") int idCompensation, 
			@Validated @ModelAttribute("compensation") Compensation compensation, 
			Model model, RedirectAttributes att) {
		
			List<Compensation> compensationss = service2.searchByIdCompensationAndTypeAndAmountAndDescriptionAndDateAndIdEmployee(idCompensation, compensation.getType(), compensation.getAmount(), compensation.getDescription(), compensation.getDate(), compensation.getIdEmployee());
			
			
			if(compensationss.size()!=0) {
				att.addFlashAttribute("error", "Compensation exist");
				System.out.println("sientra a error");
			}else {
				att.addFlashAttribute("success", "Compensation saved successful");
				System.out.println("sientra a editar");

				Compensation cmps ;
				cmps = compensation;
				cmps.setIdCompesation(idCompensation);
				service2.add(cmps);
			}
		//}
		
		return "redirect:/viewAllCompensations";
	}
	
	@GetMapping("/viewAllCompensations")
	public String viewAllCompensations( Model model) {
		List<Compensation> compensations = service2.viewAll();
		model.addAttribute("compensations", compensations);
		return "viewAllCompensations";
	}
	
	
	
	@GetMapping("/new")
	public String add(Model model) {
		model.addAttribute("employee", new Employee());
		return "formAddEmployee";
	}
	
	
	
	
	@PostMapping("/save")
	public String save(@RequestParam String firstName,@RequestParam String middleName, @RequestParam String lastName, @RequestParam String birthDate, @Validated Employee employee, Model model, RedirectAttributes att) {
		List<Employee> employeess = service.view();
		List<Employee> employeesRep = new ArrayList<Employee>();
		System.out.println(employeess.size());
		
		String firstNameOne ="",middleNameOne ="",lastNameOne ="",birthDateOne ="";
		for(int i = 0; i<employeess.size();i++) {
			
			firstNameOne = employeess.get(i).getFirstName();
			middleNameOne = employeess.get(i).getMiddleName();
			lastNameOne = employeess.get(i).getLastName();
			birthDateOne = employeess.get(i).getBirthDate();
			
		
			
			Boolean  equalToFirst = firstNameOne.equals(firstName);
			Boolean  equalToMiddle = middleNameOne.equals(middleName);
			Boolean  equalToLast = lastNameOne.equals(lastName);
			Boolean  equalToBirth = birthDateOne.equals(birthDate);
			
			if(i == (employeess.size()-1)) {
				firstNameOne = employeess.get(i).getFirstName();
				
				equalToFirst = firstNameOne.equals(firstName);
			}
			if((equalToFirst) && (equalToMiddle) && (equalToLast) && (equalToBirth)) {
				employeesRep.add(employeess.get(i));
			}
		}
		if(employeesRep.size()!=0) {
			att.addFlashAttribute("error", "Employee exist");
		}else {
			att.addFlashAttribute("success", "Employee saved successful");

			service.add(employee);
		}
		
		model.addAttribute("employeest",new Employee());
		
		return "redirect:/searchEmployee";
	}
	
	@PostMapping("/saveEmployee/{idEmployee}")
	public String saved(@PathVariable (name="idEmployee") int idEmployee, @RequestParam String firstName,
			@RequestParam String middleName, @RequestParam String lastName, @RequestParam String birthDate, 
			@Validated @ModelAttribute("employee") Employee employee, Model model, RedirectAttributes att) {
		
		List<Employee> employeess = service.buscarByFirstNameAndMiddleNameAndLastNameAndBirthDate(firstName, middleName, lastName, birthDate);
		
		
		if(employeess.size()>=1) {
			att.addFlashAttribute("error", "Employee exist");
		}else {
			att.addFlashAttribute("success", "Employee saved successful");

			Employee cmps ;
			cmps = employee;
			cmps.setIdEmployee(idEmployee);
			service.add(cmps);
		}
	
		
		
		return "redirect:/searchEmployee";
	}
	
	@GetMapping("/edit/{idEmployee}")
	public String edit(@PathVariable int idEmployee, Model model) {
		Optional<Employee>employee=service.viewId(idEmployee);
		model.addAttribute("employee", employee);
		
		
		return "formEditEmployee";
	}
	
	@GetMapping("/delete/{idEmployee}")
	public String delete(Model model, @PathVariable int idEmployee) {
		service.delete(idEmployee);
		return "redirect:/viewAddEmployee";
	}
	@GetMapping("/deleted/{idCompensation}")
	public String deleted(Model model, @PathVariable int idCompensation) {
		service2.deleted(idCompensation);
		return "redirect:/viewAllCompensations";
	}
	
	
	@GetMapping("/searchEmployee")
	public String search(Model model) {
		model.addAttribute("employees",new Employee());
		
		return "searchEmployee";
	}
	
	
	
	@GetMapping("/searchByParam")
	public String buscar(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String position, Model model, @ModelAttribute("employees") Employee employees ) {
		if(firstName!="" && lastName!="" && position!="" ) {
			List<Employee> employeess = service.buscarByFirstNameAndLastNameAndPosition(firstName, lastName, position);

			List<Employee> employees1 = service.buscarFirstName(firstName);
			
			List<Employee> employees2 = service.buscarLastName(lastName);
			
			List<Employee> employees3 = service.buscarPosition(position);
			
			List<Employee> resultList2 = new ArrayList<Employee>(employeess);
			
			resultList2.addAll(employees1);
			resultList2.addAll(employees2);
			resultList2.addAll(employees3);
			
			
			resultList2 = resultList2.stream().distinct().collect(Collectors.toList());
			
			
			results=resultList2.size();
			model.addAttribute("employeest",resultList2);

			
		}else if(firstName!="" && lastName!="" && position=="") {
			List<Employee> employeess = service.buscarByFirstNameAndLastName(firstName, lastName);
			
			List<Employee> employees1 = service.buscarFirstName(firstName);
			
			List<Employee> employees2 = service.buscarLastName(lastName);
			
			List<Employee> resultList2 = new ArrayList<Employee>(employeess);
			
			resultList2.addAll(employees1);
			resultList2.addAll(employees2);
			
			resultList2 = resultList2.stream().distinct().collect(Collectors.toList());
			results=resultList2.size();
			
			model.addAttribute("employeest",resultList2);
		}else if(firstName!="" && position!="" && lastName=="") {
			List<Employee> employeess = service.buscarByFirstNameAndPosition(firstName, position);
			
			List<Employee> employees1 = service.buscarFirstName(firstName);
			
			List<Employee> employees2 = service.buscarPosition(position);
			
			List<Employee> resultList2 = new ArrayList<Employee>(employeess);
			
			resultList2.addAll(employees1);
			resultList2.addAll(employees2);
			
			resultList2 = resultList2.stream().distinct().collect(Collectors.toList());

			results=resultList2.size();
			model.addAttribute("employeest",resultList2);
		}else if(lastName!="" && position!="" && firstName=="") {
			List<Employee> employeess = service.buscarByLastNameAndPosition(lastName, position);
			
			List<Employee> employees1 = service.buscarLastName(lastName);
			
			List<Employee> employees2 = service.buscarPosition(position);
			
			List<Employee> resultList2 = new ArrayList<Employee>(employeess);
			
			resultList2.addAll(employees1);
			resultList2.addAll(employees2);
			
			resultList2 = resultList2.stream().distinct().collect(Collectors.toList());

			results=resultList2.size();
			model.addAttribute("employeest",resultList2);
		}else if(firstName!="" && position=="" && lastName=="") {
			List<Employee> employees1 = service.buscarFirstName(firstName);
			results=employees1.size();
			model.addAttribute("employeest",employees1);
		}else if(firstName=="" && position=="" && lastName!="") {
			List<Employee> employees2 = service.buscarLastName(lastName);
			results=employees2.size();
			model.addAttribute("employeest",employees2);
		}else if(firstName=="" && position!="" && lastName=="") {
			List<Employee> employees3 = service.buscarPosition(position);
			results=employees3.size();
			model.addAttribute("employeest",employees3);
		}else if(firstName=="" && position=="" && lastName=="") {
			results=0;
			
		}
		return "searchEmployee";
		
		
		
		
		
	}
	
	
	

	
}

