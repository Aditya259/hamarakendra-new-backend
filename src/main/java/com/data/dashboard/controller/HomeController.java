package com.data.dashboard.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.data.dashboard.model.BookedServices;
import com.data.dashboard.model.BookingServicesDetails;
import com.data.dashboard.model.EmployeeDetails;
import com.data.dashboard.model.FinalFeeStructure;
import com.data.dashboard.model.Login;
import com.data.dashboard.repository.BookedServicesRepo;
import com.data.dashboard.repository.BookingServiceDetailsRepo;
import com.data.dashboard.repository.EmployeeDetailsRepo;
import com.data.dashboard.repository.FinalFeeStructureRepo;
import com.data.dashboard.repository.LoginRepository;
import com.data.dashboard.utils.Base64Utils;

@Controller
public class HomeController {

	@Autowired
	LoginRepository loginRepository;

	@Autowired
	BookedServicesRepo bookedServicesRepo;

	@Autowired
	EmployeeDetailsRepo employeeDetailsRepo;

	@Autowired
	Base64Utils base64Utils;

	@Autowired
	BookingServiceDetailsRepo bookingServiceDetailsRepo;

	@Autowired
	FinalFeeStructureRepo finalFeeStructureRepo;

	@GetMapping("/")
	public String home() {
		return "index";
	}

	@PostMapping("login")
	public String adminLogin(@RequestParam("email") String email, @RequestParam("password") String password,
			ModelMap modelMap) throws Exception {
		Login loginUser = loginRepository.findByUserNamePassword(email, password);
		List<EmployeeDetails> employeeDetails = employeeDetailsRepo.findAll();
		List<BookedServices> bookedServices = bookedServicesRepo.findSortedDesc();
		int noOfServices;
		int noOfEmployee;
		noOfServices = bookedServices.size();
		noOfEmployee = employeeDetails.size();
		List<BookedServices> listOfbookedServicesDash;
		if (loginUser != null) {
			if (email.equals(loginUser.getUserName()) && password.equals(loginUser.getPassword()) &&  loginUser.getStatus().equals("active")) {
				loginUser.setLastLoggedIn(getCurrentDate().toString());
				loginUser.setToken(base64Utils.encode(loginUser.getUserName()));
				Login savedData = loginRepository.save(loginUser);
				modelMap.put("msg", "Success");
				modelMap.put("username", savedData.getUserName());
				modelMap.put("firstName", savedData.getFirstName());
				modelMap.put("lastName", savedData.getLastName());
				modelMap.put("role", savedData.getRole());
				modelMap.put("authToken", new String(savedData.getToken()));
				modelMap.put("noOfEmployee", noOfEmployee);
				modelMap.put("lastLoginDate", savedData.getLastLoggedIn());
				modelMap.put("noOfServices", noOfServices);
				modelMap.addAttribute("listOfBookedServices", bookedServices);
				if (savedData.getRole().equalsIgnoreCase("employee")) {
					listOfbookedServicesDash = bookedServicesRepo.findBymappedEmp(savedData.getUserName());
				} else {
					listOfbookedServicesDash = bookedServicesRepo.findSortedDesc();
				}
				modelMap.addAttribute("listOfbookedServicesDash", listOfbookedServicesDash);
				return "dashboard";
			}
		}
		modelMap.put("msg", "Invalid Credentials");
		return "index";
	}

	@GetMapping("/dashboard")
	public String dashboard(@RequestParam("hamaraKendra") String auth, ModelMap modelMap) {
		List<BookedServices> listOfbookedServicesDash;
		List<BookedServices> listOfbookedServicesDashLimit;
		String decode = base64Utils.decode(auth.getBytes());
		Login savedData = loginRepository.findByUserName(decode);
		if (savedData != null) {
			int noOfServices;
			int noOfEmployee;
			List<EmployeeDetails> employeeDetails = employeeDetailsRepo.findAll();
			List<BookedServices> bookedServices = bookedServicesRepo.findSortedDesc();
			noOfServices = bookedServices.size();
			noOfEmployee = employeeDetails.size();
			if (savedData.getRole().equalsIgnoreCase("employee")) {
				listOfbookedServicesDash = bookedServicesRepo.findBymappedEmp(savedData.getUserName());
				listOfbookedServicesDashLimit = bookedServicesRepo.findBymappedEmpLimit(savedData.getUserName());
			} else {
				listOfbookedServicesDash = bookedServicesRepo.findSortedDesc();
				listOfbookedServicesDashLimit = bookedServicesRepo.findSortedDesclimit5();
			}
			System.err.println(listOfbookedServicesDashLimit);
			modelMap.addAttribute("listOfbookedServicesDash", listOfbookedServicesDash);
			modelMap.addAttribute("listOfbookedServicesDashLimit", listOfbookedServicesDashLimit);
			modelMap.put("username", savedData.getUserName());
			modelMap.put("firstName", savedData.getFirstName());
			modelMap.put("lastName", savedData.getLastName());
			modelMap.put("role", savedData.getRole());
			modelMap.put("authToken", new String(savedData.getToken()));
			modelMap.put("noOfEmployee", noOfEmployee);
			modelMap.put("lastLoginDate", savedData.getLastLoggedIn());
			modelMap.put("noOfServices", noOfServices);
			return "dashboard";
		}
		modelMap.put("msg", "Something Went Wrong..");
		return "index";

	}

	@GetMapping("/loadListOfServices")
	public String loadListOfServices(@RequestParam("hamaraKendra") String auth, ModelMap modelMap) {
		List<BookedServices> listOfbookedServicesDash;
		String decode = base64Utils.decode(auth.getBytes());
		Login savedData = loginRepository.findByUserName(decode);
		if (savedData != null) {
			int noOfServices;
			int noOfEmployee;
			List<EmployeeDetails> employeeDetails = employeeDetailsRepo.findAll();
			List<BookedServices> bookedServices = bookedServicesRepo.findSortedDesc();
			noOfServices = bookedServices.size();
			noOfEmployee = employeeDetails.size();
			if (savedData.getRole().equalsIgnoreCase("employee")) {
				listOfbookedServicesDash = bookedServicesRepo.findBymappedEmp(savedData.getUserName());
			} else {
				listOfbookedServicesDash = bookedServicesRepo.findSortedDesc();
			}
			modelMap.addAttribute("listOfbookedServicesDash", listOfbookedServicesDash);
			modelMap.put("username", savedData.getUserName());
			modelMap.put("firstName", savedData.getFirstName());
			modelMap.put("lastName", savedData.getLastName());
			modelMap.put("role", savedData.getRole());
			modelMap.put("authToken", new String(savedData.getToken()));
			modelMap.put("noOfEmployee", noOfEmployee);
			modelMap.put("lastLoginDate", savedData.getLastLoggedIn());
			modelMap.put("noOfServices", noOfServices);
			modelMap.addAttribute("listOfBookedServices", listOfbookedServicesDash);
			return "listOfServices";
		}
		modelMap.put("msg", "Something Went Wrong..");
		return "index";
	}

	@GetMapping("employeeServiceMapping")
	public String employeeServiceMapping(@RequestParam("hamaraKendra") String auth, ModelMap modelMap) {
		List<BookedServices> listOfbookedServicesDash;
		String decode = base64Utils.decode(auth.getBytes());
		Login savedData = loginRepository.findByUserName(decode);
		if (savedData != null) {
			int noOfServices;
			int noOfEmployee;
			List<EmployeeDetails> employeeDetails = employeeDetailsRepo.findAll();
			List<BookedServices> bookedServices = bookedServicesRepo.findSortedDesc();
			noOfServices = bookedServices.size();
			noOfEmployee = employeeDetails.size();
			if (savedData.getRole().equalsIgnoreCase("employee")) {
				listOfbookedServicesDash = bookedServicesRepo.findBymappedEmp(savedData.getUserName());
			} else {
				listOfbookedServicesDash = bookedServicesRepo.findSortedDesc();
			}
			modelMap.addAttribute("listOfbookedServicesDash", listOfbookedServicesDash);
			modelMap.put("username", savedData.getUserName());
			modelMap.put("firstName", savedData.getFirstName());
			modelMap.put("lastName", savedData.getLastName());
			modelMap.put("role", savedData.getRole());
			modelMap.put("authToken", new String(savedData.getToken()));
			modelMap.put("noOfEmployee", noOfEmployee);
			modelMap.put("lastLoginDate", savedData.getLastLoggedIn());
			modelMap.put("noOfServices", noOfServices);
			modelMap.addAttribute("listOfBookedServices", listOfbookedServicesDash);
			modelMap.addAttribute("listOfBookedServices", bookedServices);
			modelMap.addAttribute("loadListOfEmployees", employeeDetails);
			modelMap.put("msg", "Success");
			return "employeeServiceMapping";
		}
		modelMap.put("msg", "Something Went Wrong..");
		return "index";
	}

	@GetMapping("/loadListOfEmployees")
	public String loadListOfEmployees(@RequestParam("hamaraKendra") String auth, ModelMap modelMap) {
		List<BookedServices> listOfbookedServicesDash;
		String decode = base64Utils.decode(auth.getBytes());
		Login savedData = loginRepository.findByUserName(decode);
		if (savedData != null) {
			int noOfServices;
			int noOfEmployee;
			List<EmployeeDetails> employeeDetails = employeeDetailsRepo.findAll();
			List<BookedServices> bookedServices = bookedServicesRepo.findSortedDesc();
			List<EmployeeDetails> loadListOfEmployees = employeeDetailsRepo.findAll();
			noOfServices = bookedServices.size();
			noOfEmployee = employeeDetails.size();
			if (savedData.getRole().equalsIgnoreCase("employee")) {
				listOfbookedServicesDash = bookedServicesRepo.findBymappedEmp(savedData.getUserName());
			} else {
				listOfbookedServicesDash = bookedServicesRepo.findSortedDesc();
			}
			modelMap.addAttribute("listOfbookedServicesDash", listOfbookedServicesDash);
			modelMap.put("username", savedData.getUserName());
			modelMap.put("firstName", savedData.getFirstName());
			modelMap.put("lastName", savedData.getLastName());
			modelMap.put("role", savedData.getRole());
			modelMap.put("authToken", new String(savedData.getToken()));
			modelMap.put("noOfEmployee", noOfEmployee);
			modelMap.put("lastLoginDate", savedData.getLastLoggedIn());
			modelMap.put("noOfServices", noOfServices);
			modelMap.addAttribute("listOfBookedServices", loadListOfEmployees);
			modelMap.put("msg", "Success");
			return "loadListOfEmployees";
		}
		modelMap.put("msg", "Something Went Wrong..");
		return "index";
	}

	@GetMapping("/ServiceBookingWithCustDetails")
	public String ServiceBookingWithCustDetails(@RequestParam("hamaraKendra") String auth, ModelMap modelMap) {
		List<BookedServices> listOfbookedServicesDash;
		String decode = base64Utils.decode(auth.getBytes());
		Login savedData = loginRepository.findByUserName(decode);
		if (savedData != null) {
			int noOfServices;
			int noOfEmployee;
			List<EmployeeDetails> employeeDetails = employeeDetailsRepo.findAll();
			List<BookedServices> bookedServices = bookedServicesRepo.findSortedDesc();
			List<EmployeeDetails> loadListOfEmployees = employeeDetailsRepo.findAll();
			noOfServices = bookedServices.size();
			noOfEmployee = employeeDetails.size();
			if (savedData.getRole().equalsIgnoreCase("employee")) {
				listOfbookedServicesDash = bookedServicesRepo.findBymappedEmp(savedData.getUserName());
			} else {
				listOfbookedServicesDash = bookedServicesRepo.findSortedDesc();
			}
			List<BookingServicesDetails> bookingServicesDetails = bookingServiceDetailsRepo.findAll();

			modelMap.addAttribute("listOfbookedServicesDash", listOfbookedServicesDash);
			modelMap.put("username", savedData.getUserName());
			modelMap.put("firstName", savedData.getFirstName());
			modelMap.put("lastName", savedData.getLastName());
			modelMap.put("role", savedData.getRole());
			modelMap.put("authToken", new String(savedData.getToken()));
			modelMap.put("noOfEmployee", noOfEmployee);
			modelMap.put("lastLoginDate", savedData.getLastLoggedIn());
			modelMap.put("noOfServices", noOfServices);
			modelMap.addAttribute("listOfBookedServices", loadListOfEmployees);
			modelMap.addAttribute("bookingServicesDetails", bookingServicesDetails);

			modelMap.put("msg", "Success");
			return "ServiceBookingWithCustDetails";
		}
		modelMap.put("msg", "Something Went Wrong..");
		return "index";
	}

	@GetMapping("logout1")
	public String logout(@RequestParam("hamaraKendra") String auth, ModelMap modelMap) {
		String decode = base64Utils.decode(auth.getBytes());
		Login savedData = loginRepository.findByUserName(decode);
		if (savedData != null) {
			savedData.setToken("".getBytes());
			savedData.setFirstName(savedData.getFirstName());
			savedData.setId(savedData.getId());
			savedData.setLastLoggedIn(savedData.getLastLoggedIn());
			savedData.setLastName(savedData.getLastName());
			savedData.setPassword(savedData.getPassword());
			savedData.setRole(savedData.getRole());
			savedData.setUserName(savedData.getUserName());
			loginRepository.save(savedData);
		}
		return "index";
	}

	@GetMapping("editServiceStatus")
	public String editServiceStatus(@RequestParam("hamaraKendra") String auth, @RequestParam("id") String id,
			ModelMap modelMap) {
		List<BookedServices> listOfbookedServicesDash;
		String decode = base64Utils.decode(auth.getBytes());
		Login savedData = loginRepository.findByUserName(decode);
		if (savedData != null) {
			int noOfServices;
			int noOfEmployee;
			List<EmployeeDetails> employeeDetails = employeeDetailsRepo.findAll();
			List<BookedServices> bookedServices = bookedServicesRepo.findSortedDesc();
			List<EmployeeDetails> loadListOfEmployees = employeeDetailsRepo.findAll();
			Optional<BookedServices> bookedServicesbyid = bookedServicesRepo.findById(Integer.parseInt(id));
			noOfServices = bookedServices.size();
			noOfEmployee = employeeDetails.size();
			if (savedData.getRole().equalsIgnoreCase("employee")) {
				listOfbookedServicesDash = bookedServicesRepo.findBymappedEmp(savedData.getUserName());
			} else {
				listOfbookedServicesDash = bookedServicesRepo.findSortedDesc();
			}
			modelMap.addAttribute("listOfbookedServicesDash", listOfbookedServicesDash);
			modelMap.put("username", savedData.getUserName());
			modelMap.put("firstName", savedData.getFirstName());
			modelMap.put("lastName", savedData.getLastName());
			modelMap.put("role", savedData.getRole());
			modelMap.put("authToken", new String(savedData.getToken()));
			modelMap.put("noOfEmployee", noOfEmployee);
			modelMap.put("lastLoginDate", savedData.getLastLoggedIn());
			modelMap.put("noOfServices", noOfServices);
			modelMap.addAttribute("listOfBookedServices", loadListOfEmployees);
			modelMap.put("msg", "Success");
			modelMap.put("bookedService", bookedServicesbyid.get());
			return "editbookedServices";
		}
		modelMap.put("msg", "Something Went Wrong..");
		return "index";
	}

	@PostMapping("updateBookingData")
	public String updateBookingData(@RequestParam("status") String status, @RequestParam("name") String name,
			@RequestParam("mobileNo") String mobileNo, @RequestParam("emailId") String emailId,
			@RequestParam("address1") String address1, @RequestParam("address2") String address2,
			@RequestParam("address3") String address3, @RequestParam("date") String date,
			@RequestParam("selectedService") String selectedService, @RequestParam("id") String id,
			@RequestParam("hamaraKendra") String auth, ModelMap modelMap) throws Exception {
		List<BookedServices> listOfbookedServicesDash;
		String decode = base64Utils.decode(auth.getBytes());
		Login savedData = loginRepository.findByUserName(decode);
		if (savedData != null) {
			int noOfServices;
			int noOfEmployee;
			List<EmployeeDetails> employeeDetails = employeeDetailsRepo.findAll();
			List<BookedServices> bookedServices = bookedServicesRepo.findSortedDesc();
			List<EmployeeDetails> loadListOfEmployees = employeeDetailsRepo.findAll();
			noOfServices = bookedServices.size();
			noOfEmployee = employeeDetails.size();
			if (savedData.getRole().equalsIgnoreCase("employee")) {
				listOfbookedServicesDash = bookedServicesRepo.findBymappedEmp(savedData.getUserName());
			} else {
				listOfbookedServicesDash = bookedServicesRepo.findSortedDesc();
			}
			//
			Optional<BookedServices> bookedServicesbyid = bookedServicesRepo.findById(Integer.parseInt(id));
			if (bookedServicesbyid.get() != null) {
				BookedServices bookedService = new BookedServices();
				bookedService.setAddress1(address1);
				bookedService.setAddress2(address2);
				bookedService.setAddress3(address3);
				bookedService.setDate(date);
				bookedService.setEmailId(emailId);
				bookedService.setMobileNo(mobileNo);
				bookedService.setName(name);
				bookedService.setSelectedService(selectedService);
				bookedService.setStatus(status);
				bookedService.setUrlName(bookedServicesbyid.get().getUrlName());
				bookedService.setId(bookedServicesbyid.get().getId());
				bookedService.setMappedEmp(bookedServicesbyid.get().getMappedEmp());
				bookedService.setTimeSlot(bookedServicesbyid.get().getTimeSlot());
				bookedServicesRepo.save(bookedService);
				//
				modelMap.addAttribute("listOfbookedServicesDash", listOfbookedServicesDash);

				modelMap.put("heading", "Successfully Updated ");
				modelMap.put("desc", "Data Saved Successfully");
				modelMap.put("url", "loadListOfServices");
				modelMap.put("username", savedData.getUserName());
				modelMap.put("firstName", savedData.getFirstName());
				modelMap.put("lastName", savedData.getLastName());
				modelMap.put("role", savedData.getRole());
				modelMap.put("authToken", new String(savedData.getToken()));
				modelMap.put("noOfEmployee", noOfEmployee);
				modelMap.put("lastLoginDate", savedData.getLastLoggedIn());
				modelMap.put("noOfServices", noOfServices);
				modelMap.put("noOfServices", noOfServices);
				modelMap.put("noOfEmployee", noOfEmployee);
				return "popup";
			}
			modelMap.put("username", savedData.getUserName());
			modelMap.put("firstName", savedData.getFirstName());
			modelMap.put("lastName", savedData.getLastName());
			modelMap.put("role", savedData.getRole());
			modelMap.put("authToken", new String(savedData.getToken()));
			modelMap.put("noOfEmployee", noOfEmployee);
			modelMap.put("lastLoginDate", savedData.getLastLoggedIn());
			modelMap.put("noOfServices", noOfServices);
			modelMap.addAttribute("listOfBookedServices", loadListOfEmployees);
			modelMap.put("msg", "Error");
			modelMap.put("heading", "Something Went Wrong ");
			modelMap.put("desc", "Please try after some time!");
			modelMap.put("url", "loadListOfServices");
			modelMap.put("noOfServices", noOfServices);
			modelMap.put("noOfEmployee", noOfEmployee);
			return "popup";
		}
		modelMap.put("msg", "Something Went Wrong..");
		return "index";
	}

	@GetMapping("viewServiceStatus")
	public String viewServiceStatus(@RequestParam("id") String id, @RequestParam("hamaraKendra") String auth,
			ModelMap modelMap) {
		List<BookedServices> listOfbookedServicesDash;
		String decode = base64Utils.decode(auth.getBytes());
		Login savedData = loginRepository.findByUserName(decode);
		if (savedData != null) {
			int noOfServices;
			int noOfEmployee;
			List<EmployeeDetails> employeeDetails = employeeDetailsRepo.findAll();
			List<BookedServices> bookedServices = bookedServicesRepo.findSortedDesc();
			noOfServices = bookedServices.size();
			noOfEmployee = employeeDetails.size();
			if (savedData.getRole().equalsIgnoreCase("employee")) {
				listOfbookedServicesDash = bookedServicesRepo.findBymappedEmp(savedData.getUserName());
			} else {
				listOfbookedServicesDash = bookedServicesRepo.findSortedDesc();
			}
			Optional<BookedServices> bookedServicesById = bookedServicesRepo.findById(Integer.parseInt(id));

			modelMap.addAttribute("listOfbookedServicesDash", listOfbookedServicesDash);
			modelMap.put("username", savedData.getUserName());
			modelMap.put("firstName", savedData.getFirstName());
			modelMap.put("lastName", savedData.getLastName());
			modelMap.put("role", savedData.getRole());
			modelMap.put("authToken", new String(savedData.getToken()));
			modelMap.put("noOfEmployee", noOfEmployee);
			modelMap.put("lastLoginDate", savedData.getLastLoggedIn());
			modelMap.put("noOfServices", noOfServices);
			modelMap.addAttribute("listOfBookedServices", listOfbookedServicesDash);
			modelMap.put("msg", "Success");
			modelMap.put("bookedService", bookedServicesById.get());
			return "viewServiceStatus";
		}
		modelMap.put("msg", "Something Went Wrong..");
		return "index";
	}

	@GetMapping("editEmployeeDetails")
	public String editEmployeeDetails(@RequestParam("id") String id, @RequestParam("hamaraKendra") String auth,
			ModelMap modelMap) {
		List<BookedServices> listOfbookedServicesDash;
		String decode = base64Utils.decode(auth.getBytes());
		Login savedData = loginRepository.findByUserName(decode);
		if (savedData != null) {
			int noOfServices;
			int noOfEmployee;
			List<EmployeeDetails> employeeDetails = employeeDetailsRepo.findAll();
			List<BookedServices> bookedServices = bookedServicesRepo.findSortedDesc();
			noOfServices = bookedServices.size();
			noOfEmployee = employeeDetails.size();
			if (savedData.getRole().equalsIgnoreCase("employee")) {
				listOfbookedServicesDash = bookedServicesRepo.findBymappedEmp(savedData.getUserName());
			} else {
				listOfbookedServicesDash = bookedServicesRepo.findSortedDesc();
			}
			EmployeeDetails bookedServicesByemployeeId = employeeDetailsRepo.findByemployeeId(id);
			modelMap.addAttribute("listOfbookedServicesDash", listOfbookedServicesDash);
			modelMap.put("username", savedData.getUserName());
			modelMap.put("firstName", savedData.getFirstName());
			modelMap.put("lastName", savedData.getLastName());
			modelMap.put("role", savedData.getRole());
			modelMap.put("authToken", new String(savedData.getToken()));
			modelMap.put("noOfEmployee", noOfEmployee);
			modelMap.put("lastLoginDate", savedData.getLastLoggedIn());
			modelMap.put("noOfServices", noOfServices);
			modelMap.addAttribute("listOfBookedServices", listOfbookedServicesDash);
			modelMap.put("msg", "Success");
			modelMap.put("bookedService", bookedServicesByemployeeId);

			return "editEmployeeDetails";
		}
		modelMap.put("msg", "Something Went Wrong..");
		return "index";
	}

	@PostMapping("updateEmployeeData")
	public String updateEmployeeData(@RequestParam("id") String id, @RequestParam("name") String name,
			@RequestParam("mobileNo") String mobileNo, @RequestParam("emailId") String emailId,
			@RequestParam("address1") String address1, @RequestParam("city") String city,
			@RequestParam("state") String state, @RequestParam("country") String country,
			@RequestParam("username") String username, @RequestParam("password") String password,
			@RequestParam("status") String status, @RequestParam("hamaraKendra") String auth, ModelMap modelMap)
			throws Exception {

		//
		List<BookedServices> listOfbookedServicesDash;
		String decode = base64Utils.decode(auth.getBytes());
		Login savedData = loginRepository.findByUserName(decode);
		if (savedData != null) {
			int noOfServices;
			int noOfEmployee;
			List<EmployeeDetails> employeeDetails = employeeDetailsRepo.findAll();
			List<BookedServices> bookedServices = bookedServicesRepo.findSortedDesc();
			List<EmployeeDetails> loadListOfEmployees = employeeDetailsRepo.findAll();
			noOfServices = bookedServices.size();
			noOfEmployee = employeeDetails.size();
			if (savedData.getRole().equalsIgnoreCase("employee")) {
				listOfbookedServicesDash = bookedServicesRepo.findBymappedEmp(savedData.getUserName());
			} else {
				listOfbookedServicesDash = bookedServicesRepo.findSortedDesc();
			}
			//
			Optional<EmployeeDetails> employeeDetailsById = employeeDetailsRepo.findById(Integer.parseInt(id));
			if (employeeDetailsById.get() != null) {
				EmployeeDetails empDetails = new EmployeeDetails();
				empDetails.setEmployeeStatus(status);
				empDetails.setEmployeeAddress(address1);
				empDetails.setEmployeeCity(city);
				empDetails.setEmployeeCountry(country);
				empDetails.setEmployeeEmailAddress(emailId);
				empDetails.setEmployeeId(employeeDetailsById.get().getEmployeeId());
				empDetails.setEmployeeMobileNo(mobileNo);
				empDetails.setEmployeeName(name);
				empDetails.setEmployeePassword(password);
				empDetails.setEmployeeState(state);
				empDetails.setEmployeeUserName(username);
				empDetails.setId(employeeDetailsById.get().getId());
				EmployeeDetails savedEmpDetails = employeeDetailsRepo.save(empDetails);
				if (savedEmpDetails != null) {
					Login loginUser = loginRepository.findByUserName(savedEmpDetails.getEmployeeUserName());
					if (loginUser != null) {
						loginUser.setFirstName(loginUser.getFirstName());
						loginUser.setId(loginUser.getId());
						loginUser.setLastLoggedIn(loginUser.getLastLoggedIn());
						loginUser.setLastName(loginUser.getLastName());
						loginUser.setRole(loginUser.getRole());
						loginUser.setToken(loginUser.getToken());
						loginUser.setUserName(loginUser.getUserName());
						loginUser.setPassword(savedEmpDetails.getEmployeePassword());
						loginUser.setStatus(savedEmpDetails.getBookingStatus());
						loginRepository.save(loginUser);
					}
					//
					modelMap.addAttribute("listOfbookedServicesDash", listOfbookedServicesDash);
					modelMap.put("bookedService", savedEmpDetails);
					modelMap.put("heading", "Successfully Updated ");
					modelMap.put("desc", "Data Saved Successfully");
					modelMap.put("url", "loadListOfEmployees");
					modelMap.put("username", savedData.getUserName());
					modelMap.put("firstName", savedData.getFirstName());
					modelMap.put("lastName", savedData.getLastName());
					modelMap.put("role", savedData.getRole());
					modelMap.put("authToken", new String(savedData.getToken()));
					modelMap.put("noOfEmployee", noOfEmployee);
					modelMap.put("lastLoginDate", savedData.getLastLoggedIn());
					modelMap.put("noOfServices", noOfServices);
					return "popup";
				}
				modelMap.put("username", savedData.getUserName());
				modelMap.put("firstName", savedData.getFirstName());
				modelMap.put("lastName", savedData.getLastName());
				modelMap.put("role", savedData.getRole());
				modelMap.put("authToken", new String(savedData.getToken()));
				modelMap.put("noOfEmployee", noOfEmployee);
				modelMap.put("lastLoginDate", savedData.getLastLoggedIn());
				modelMap.put("noOfServices", noOfServices);
				modelMap.addAttribute("listOfBookedServices", loadListOfEmployees);
				modelMap.put("msg", "Error");
				modelMap.put("heading", "Something Went Wrong ");
				modelMap.put("desc", "Please try after some time!");
				modelMap.put("url", "loadListOfEmployees");
				modelMap.put("noOfServices", noOfServices);
				modelMap.put("noOfEmployee", noOfEmployee);
				return "popup";
			}
			modelMap.put("username", savedData.getUserName());
			modelMap.put("firstName", savedData.getFirstName());
			modelMap.put("lastName", savedData.getLastName());
			modelMap.put("role", savedData.getRole());
			modelMap.put("authToken", new String(savedData.getToken()));
			modelMap.put("noOfEmployee", noOfEmployee);
			modelMap.put("lastLoginDate", savedData.getLastLoggedIn());
			modelMap.put("noOfServices", noOfServices);
			modelMap.addAttribute("listOfBookedServices", loadListOfEmployees);
			modelMap.put("msg", "Error");
			modelMap.put("heading", "Something Went Wrong ");
			modelMap.put("desc", "Please try after some time!");
			modelMap.put("url", "loadListOfEmployees");
			modelMap.put("noOfServices", noOfServices);
			modelMap.put("noOfEmployee", noOfEmployee);
			return "popup";
		}
		modelMap.put("msg", "Something Went Wrong..");
		return "index";
	}

	@GetMapping("viewlistofemployee")
	public String viewlistofemployee(@RequestParam("id") String id, @RequestParam("hamaraKendra") String auth,
			ModelMap modelMap) {

		List<BookedServices> listOfbookedServicesDash;
		String decode = base64Utils.decode(auth.getBytes());
		Login savedData = loginRepository.findByUserName(decode);
		if (savedData != null) {
			int noOfServices;
			int noOfEmployee;
			List<EmployeeDetails> employeeDetails = employeeDetailsRepo.findAll();
			List<BookedServices> bookedServices = bookedServicesRepo.findSortedDesc();
			noOfServices = bookedServices.size();
			noOfEmployee = employeeDetails.size();
			if (savedData.getRole().equalsIgnoreCase("employee")) {
				listOfbookedServicesDash = bookedServicesRepo.findBymappedEmp(savedData.getUserName());
			} else {
				listOfbookedServicesDash = bookedServicesRepo.findSortedDesc();
			}
			EmployeeDetails bookedServicesById = employeeDetailsRepo.findByemployeeId(id);

			modelMap.addAttribute("listOfbookedServicesDash", listOfbookedServicesDash);
			modelMap.put("username", savedData.getUserName());
			modelMap.put("firstName", savedData.getFirstName());
			modelMap.put("lastName", savedData.getLastName());
			modelMap.put("role", savedData.getRole());
			modelMap.put("authToken", new String(savedData.getToken()));
			modelMap.put("noOfEmployee", noOfEmployee);
			modelMap.put("lastLoginDate", savedData.getLastLoggedIn());
			modelMap.put("noOfServices", noOfServices);
			modelMap.addAttribute("listOfBookedServices", listOfbookedServicesDash);
			modelMap.put("msg", "Success");
			modelMap.put("bookedService", bookedServicesById);
			return "viewlistofemployee";
		}
		modelMap.put("msg", "Something Went Wrong..");
		return "index";
	}

	@GetMapping("createEmployee")
	public String createEmployee(@RequestParam("hamaraKendra") String auth, ModelMap modelMap) {

		List<BookedServices> listOfbookedServicesDash;
		String decode = base64Utils.decode(auth.getBytes());
		Login savedData = loginRepository.findByUserName(decode);
		if (savedData != null) {
			int noOfServices;
			int noOfEmployee;
			List<EmployeeDetails> employeeDetails = employeeDetailsRepo.findAll();
			List<BookedServices> bookedServices = bookedServicesRepo.findSortedDesc();
			noOfServices = bookedServices.size();
			noOfEmployee = employeeDetails.size();
			if (savedData.getRole().equalsIgnoreCase("employee")) {
				listOfbookedServicesDash = bookedServicesRepo.findBymappedEmp(savedData.getUserName());
			} else {
				listOfbookedServicesDash = bookedServicesRepo.findSortedDesc();
			}

			modelMap.addAttribute("listOfbookedServicesDash", listOfbookedServicesDash);
			modelMap.put("username", savedData.getUserName());
			modelMap.put("firstName", savedData.getFirstName());
			modelMap.put("lastName", savedData.getLastName());
			modelMap.put("role", savedData.getRole());
			modelMap.put("authToken", new String(savedData.getToken()));
			modelMap.put("noOfEmployee", noOfEmployee);
			modelMap.put("lastLoginDate", savedData.getLastLoggedIn());
			modelMap.put("noOfServices", noOfServices);
			modelMap.addAttribute("listOfBookedServices", listOfbookedServicesDash);
			modelMap.put("msg", "Success");
			return "createEmployee";
		}
		modelMap.put("msg", "Something Went Wrong..");
		return "index";
	}

	@PostMapping("createEmployeeData")
	public String createEmployeeData(@RequestParam("id") String id, @RequestParam("name") String name,
			@RequestParam("mobileNo") String mobileNo, @RequestParam("emailId") String emailId,
			@RequestParam("address1") String address1, @RequestParam("city") String city,
			@RequestParam("state") String state, @RequestParam("country") String country,
			@RequestParam("username") String username, @RequestParam("password") String password,
			@RequestParam("status") String status, @RequestParam("hamaraKendra") String auth, ModelMap modelMap)
			throws Exception {

		//
		List<BookedServices> listOfbookedServicesDash;
		String decode = base64Utils.decode(auth.getBytes());
		Login savedData = loginRepository.findByUserName(decode);
		if (savedData != null) {
			int noOfServices;
			int noOfEmployee;
			List<EmployeeDetails> employeeDetails = employeeDetailsRepo.findAll();
			List<BookedServices> bookedServices = bookedServicesRepo.findSortedDesc();
			noOfServices = bookedServices.size();
			noOfEmployee = employeeDetails.size();
			if (savedData.getRole().equalsIgnoreCase("employee")) {
				listOfbookedServicesDash = bookedServicesRepo.findBymappedEmp(savedData.getUserName());
			} else {
				listOfbookedServicesDash = bookedServicesRepo.findSortedDesc();
			}

			EmployeeDetails empDetails = new EmployeeDetails();
			empDetails.setBookingStatus(status);
			empDetails.setEmployeeAddress(address1);
			empDetails.setEmployeeCity(city);
			empDetails.setEmployeeCountry(country);
			empDetails.setEmployeeEmailAddress(emailId);
			empDetails.setEmployeeId(getRandomNumberString());
			empDetails.setEmployeeMobileNo(mobileNo);
			empDetails.setEmployeeName(name);
			empDetails.setEmployeePassword(password);
			empDetails.setEmployeeState(state);
			empDetails.setEmployeeUserName(username);
			EmployeeDetails savedEmpDetails = employeeDetailsRepo.save(empDetails);
			if (savedEmpDetails != null) {
				Login loginUserCheckExist = loginRepository.findByUserName(username);
				if (loginUserCheckExist != null) {
					modelMap.put("message", "Error");
					modelMap.put("username", savedData.getUserName());
					modelMap.put("firstName", savedData.getFirstName());
					modelMap.put("lastName", savedData.getLastName());
					modelMap.put("role", savedData.getRole());
					modelMap.put("authToken", new String(savedData.getToken()));
					modelMap.put("noOfEmployee", noOfEmployee);
					modelMap.put("lastLoginDate", savedData.getLastLoggedIn());
					modelMap.put("heading", "Employee userName already exist");
					modelMap.put("desc", "Employee userName already exist, Please try with another username");
					modelMap.put("url", "loadListOfEmployees");
					modelMap.put("noOfServices", noOfServices);
					modelMap.put("noOfEmployee", noOfEmployee);
					return "popup";
				} else {

					Login loginUser = new Login();
					loginUser.setFirstName(name);
					loginUser.setLastLoggedIn(getCurrentDate().toString());
					loginUser.setLastName("");
					loginUser.setRole("employee");
					loginUser.setUserName(username);
					loginUser.setPassword(password);
					loginUser.setStatus(status);
					loginRepository.save(loginUser);
				}
			}
			modelMap.put("message", "Success");
			modelMap.put("username", savedData.getUserName());
			modelMap.put("firstName", savedData.getFirstName());
			modelMap.put("lastName", savedData.getLastName());
			modelMap.put("role", savedData.getRole());
			modelMap.put("authToken", new String(savedData.getToken()));
			modelMap.put("noOfEmployee", noOfEmployee);
			modelMap.put("lastLoginDate", savedData.getLastLoggedIn());
			modelMap.put("heading", "Successfully Updated ");
			modelMap.put("desc", "Data Saved Successfully");
			modelMap.put("url", "loadListOfEmployees");
			modelMap.put("noOfServices", noOfServices);
			modelMap.put("noOfEmployee", noOfEmployee);
			return "popup";
		}
		modelMap.put("msg", "Something Went Wrong..");
		return "index";
	}

	@PostMapping("serviceBookingData")
	public String serviceBookingData(@RequestParam("status") String status, @RequestParam("name") String name,
			@RequestParam("mobileNo") String mobileNo, @RequestParam("emailId") String emailId,
			@RequestParam("address1") String address1, @RequestParam("address2") String address2,
			@RequestParam("address3") String address3, @RequestParam("date") String date,
			@RequestParam("selectedService") String selectedService, @RequestParam("id") String id,
			@RequestParam("hamaraKendra") String auth, @RequestParam("userName") String userName, ModelMap modelMap)
			throws Exception {
		List<BookedServices> listOfbookedServicesDash;
		String decode = base64Utils.decode(auth.getBytes());
		Login savedData = loginRepository.findByUserName(decode);
		if (savedData != null) {
			int noOfServices;
			int noOfEmployee;
			List<EmployeeDetails> employeeDetails = employeeDetailsRepo.findAll();
			List<BookedServices> bookedServices = bookedServicesRepo.findSortedDesc();
			List<EmployeeDetails> loadListOfEmployees = employeeDetailsRepo.findAll();
			noOfServices = bookedServices.size();
			noOfEmployee = employeeDetails.size();
			if (savedData.getRole().equalsIgnoreCase("employee")) {
				listOfbookedServicesDash = bookedServicesRepo.findBymappedEmp(savedData.getUserName());
			} else {
				listOfbookedServicesDash = bookedServicesRepo.findSortedDesc();
			}
			//

			BookedServices bookedService = new BookedServices();
			bookedService.setAddress1(address1);
			bookedService.setAddress2(address2);
			bookedService.setAddress3(address3);
			bookedService.setDate(date);
			bookedService.setEmailId(emailId);
			bookedService.setMobileNo(mobileNo);
			bookedService.setName(name);
			bookedService.setSelectedService(selectedService);
			bookedService.setStatus(status);
			// bookedService.setUrlName(urlName);
			bookedService.setMappedEmp(userName);
			//bookedService.setTimeSlot(decode);
			BookedServices bookedServiceSaved = bookedServicesRepo.save(bookedService);
			//
			modelMap.addAttribute("listOfbookedServicesDash", listOfbookedServicesDash);
			if (bookedServiceSaved != null) {
				modelMap.put("heading", "Successfully Updated ");
				modelMap.put("desc", "Data Saved Successfully");
				modelMap.put("url", "loadListOfServices");
				modelMap.put("username", savedData.getUserName());
				modelMap.put("firstName", savedData.getFirstName());
				modelMap.put("lastName", savedData.getLastName());
				modelMap.put("role", savedData.getRole());
				modelMap.put("authToken", new String(savedData.getToken()));
				modelMap.put("noOfEmployee", noOfEmployee);
				modelMap.put("lastLoginDate", savedData.getLastLoggedIn());
				modelMap.put("noOfServices", noOfServices);
				modelMap.put("noOfServices", noOfServices);
				modelMap.put("noOfEmployee", noOfEmployee);
				return "popup";
			}
			// end
			modelMap.put("username", savedData.getUserName());
			modelMap.put("firstName", savedData.getFirstName());
			modelMap.put("lastName", savedData.getLastName());
			modelMap.put("role", savedData.getRole());
			modelMap.put("authToken", new String(savedData.getToken()));
			modelMap.put("noOfEmployee", noOfEmployee);
			modelMap.put("lastLoginDate", savedData.getLastLoggedIn());
			modelMap.put("noOfServices", noOfServices);
			modelMap.addAttribute("listOfBookedServices", loadListOfEmployees);
			modelMap.put("msg", "Error");
			modelMap.put("heading", "Something Went Wrong ");
			modelMap.put("desc", "Please try after some time!");
			modelMap.put("url", "loadListOfServices");
			modelMap.put("noOfServices", noOfServices);
			modelMap.put("noOfEmployee", noOfEmployee);
			return "popup";
		}
		modelMap.put("msg", "Something Went Wrong..");
		return "index";
	}

	@GetMapping("/mapEmployee")
	@ResponseBody
	public HashMap<String, Object> mapEmployee(@RequestHeader("id") String id,
			@RequestHeader("empName") String empName) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		Optional<BookedServices> bookedService = bookedServicesRepo.findById(Integer.parseInt(id));
		if (bookedService.get() != null) {
			BookedServices bookedServices = new BookedServices();
			bookedServices.setAddress1(bookedService.get().getAddress1());
			bookedServices.setAddress2(bookedService.get().getAddress2());
			bookedServices.setAddress3(bookedService.get().getAddress3());
			bookedServices.setDate(bookedService.get().getDate());
			bookedServices.setEmailId(bookedService.get().getEmailId());
			bookedServices.setMobileNo(bookedService.get().getMobileNo());
			bookedServices.setName(bookedService.get().getName());
			bookedServices.setSelectedService(bookedService.get().getSelectedService());
			bookedServices.setStatus(bookedService.get().getStatus());
			bookedServices.setUrlName(bookedService.get().getUrlName());
			bookedServices.setId(bookedService.get().getId());
			bookedServices.setMappedEmp(empName);
			bookedServices.setTimeSlot(bookedService.get().getTimeSlot());
			bookedServicesRepo.save(bookedServices);
			map.put("msg", "Success");
			return map;
		}
		map.put("msg", "Error");
		return map;
	}

	@GetMapping("updateServicePricing")
	public String updateServicePricing(@RequestParam("hamaraKendra") String auth, ModelMap modelMap) {
		List<BookedServices> listOfbookedServicesDash;
		String decode = base64Utils.decode(auth.getBytes());
		Login savedData = loginRepository.findByUserName(decode);
		if (savedData != null) {
			int noOfServices;
			int noOfEmployee;
			List<EmployeeDetails> employeeDetails = employeeDetailsRepo.findAll();
			List<BookedServices> bookedServices = bookedServicesRepo.findSortedDesc();
			noOfServices = bookedServices.size();
			noOfEmployee = employeeDetails.size();
			if (savedData.getRole().equalsIgnoreCase("employee")) {
				listOfbookedServicesDash = bookedServicesRepo.findBymappedEmp(savedData.getUserName());
			} else {
				listOfbookedServicesDash = bookedServicesRepo.findSortedDesc();
			}
			List<FinalFeeStructure> finalFeeStructure = finalFeeStructureRepo.findAll();

			modelMap.addAttribute("listOfbookedServicesDash", listOfbookedServicesDash);
			modelMap.put("username", savedData.getUserName());
			modelMap.put("firstName", savedData.getFirstName());
			modelMap.put("lastName", savedData.getLastName());
			modelMap.put("role", savedData.getRole());
			modelMap.put("authToken", new String(savedData.getToken()));
			modelMap.put("noOfEmployee", noOfEmployee);
			modelMap.put("lastLoginDate", savedData.getLastLoggedIn());
			modelMap.put("noOfServices", noOfServices);
			modelMap.addAttribute("listOfBookedServices", listOfbookedServicesDash);
			modelMap.put("msg", "Success");
			modelMap.put("finalFeeStructure", finalFeeStructure);
			return "updateServicePricing";
		}
		modelMap.put("msg", "Something Went Wrong..");
		return "index";
	}

	@GetMapping("editFeeStructure")
	public String editFeeStructure(@RequestParam("id") String id, @RequestParam("hamaraKendra") String auth,
			ModelMap modelMap) throws Exception {
		List<BookedServices> listOfbookedServicesDash;
		String decode = base64Utils.decode(auth.getBytes());
		Login savedData = loginRepository.findByUserName(decode);
		if (savedData != null) {
			int noOfServices;
			int noOfEmployee;
			List<EmployeeDetails> employeeDetails = employeeDetailsRepo.findAll();
			List<BookedServices> bookedServices = bookedServicesRepo.findSortedDesc();
			List<EmployeeDetails> loadListOfEmployees = employeeDetailsRepo.findAll();
			noOfServices = bookedServices.size();
			noOfEmployee = employeeDetails.size();
			if (savedData.getRole().equalsIgnoreCase("employee")) {
				listOfbookedServicesDash = bookedServicesRepo.findBymappedEmp(savedData.getUserName());
			} else {
				listOfbookedServicesDash = bookedServicesRepo.findSortedDesc();
			}
			Optional<FinalFeeStructure> finalFeeStructure = finalFeeStructureRepo.findById(Integer.parseInt(id));

			modelMap.addAttribute("listOfbookedServicesDash", listOfbookedServicesDash);
			modelMap.put("username", savedData.getUserName());
			modelMap.put("firstName", savedData.getFirstName());
			modelMap.put("lastName", savedData.getLastName());
			modelMap.put("role", savedData.getRole());
			modelMap.put("authToken", new String(savedData.getToken()));
			modelMap.put("noOfEmployee", noOfEmployee);
			modelMap.put("lastLoginDate", savedData.getLastLoggedIn());
			modelMap.put("noOfServices", noOfServices);
			modelMap.addAttribute("listOfBookedServices", loadListOfEmployees);
			modelMap.put("msg", "Success");

			modelMap.put("finalFeeStructure", finalFeeStructure.get());
			return "editFinalFeeStructure";
		}
		modelMap.put("msg", "Something Went Wrong..");
		return "index";
	}

	@PostMapping("updatefinalFeeStructureData")
	public String updatefinalFeeStructureData(@RequestParam("id") String id, @RequestParam("govFee") String govFee,
			@RequestParam("serviceCharge") String serviceCharge, @RequestParam("totalFee") String totalFee,
			@RequestParam("hamaraKendra") String auth, ModelMap modelMap) throws Exception {
		List<BookedServices> listOfbookedServicesDash;
		String decode = base64Utils.decode(auth.getBytes());
		Login savedData = loginRepository.findByUserName(decode);
		if (savedData != null) {
			int noOfServices;
			int noOfEmployee;
			List<EmployeeDetails> employeeDetails = employeeDetailsRepo.findAll();
			List<BookedServices> bookedServices = bookedServicesRepo.findSortedDesc();
			List<EmployeeDetails> loadListOfEmployees = employeeDetailsRepo.findAll();
			noOfServices = bookedServices.size();
			noOfEmployee = employeeDetails.size();
			if (savedData.getRole().equalsIgnoreCase("employee")) {
				listOfbookedServicesDash = bookedServicesRepo.findBymappedEmp(savedData.getUserName());
			} else {
				listOfbookedServicesDash = bookedServicesRepo.findSortedDesc();
			}
			List<FinalFeeStructure> finalFeeStructureAll = finalFeeStructureRepo.findAll();

			Optional<FinalFeeStructure> finalFeeStructure = finalFeeStructureRepo.findById(Integer.parseInt(id));
			if (finalFeeStructure.get() != null) {
				FinalFeeStructure finalFeeStructureObj = new FinalFeeStructure();
				if (govFee != null && !govFee.isEmpty()) {
					finalFeeStructureObj.setGovFee(govFee);
				} else {
					finalFeeStructureObj.setGovFee(finalFeeStructure.get().getGovFee());
				}
				if (serviceCharge != null && !serviceCharge.isEmpty()) {
					finalFeeStructureObj.setServiceCharges(serviceCharge);
				} else {
					finalFeeStructureObj.setServiceCharges(finalFeeStructure.get().getServiceCharges());
				}
				if (totalFee != null && !totalFee.isEmpty()) {
					finalFeeStructureObj.setTotal(totalFee);
				} else {
					finalFeeStructureObj.setTotal(finalFeeStructure.get().getTotal());
				}
				finalFeeStructureObj.setSeriveName(finalFeeStructure.get().getSeriveName());
				finalFeeStructureObj.setUrlName(finalFeeStructure.get().getUrlName());
				finalFeeStructureObj.setId(finalFeeStructure.get().getId());
				finalFeeStructureRepo.save(finalFeeStructureObj);
			}
		
			modelMap.addAttribute("listOfbookedServicesDash", listOfbookedServicesDash);
			modelMap.put("username", savedData.getUserName());
			modelMap.put("firstName", savedData.getFirstName());
			modelMap.put("lastName", savedData.getLastName());
			modelMap.put("role", savedData.getRole());
			modelMap.put("authToken", new String(savedData.getToken()));
			modelMap.put("noOfEmployee", noOfEmployee);
			modelMap.put("lastLoginDate", savedData.getLastLoggedIn());
			modelMap.put("noOfServices", noOfServices);
			modelMap.addAttribute("listOfBookedServices", loadListOfEmployees);
			modelMap.put("msg", "Success");

			modelMap.put("finalFeeStructure", finalFeeStructureAll);
			return "updateServicePricing";
		}
		modelMap.put("msg", "Something Went Wrong..");
		return "index";
	}

	@GetMapping("/loadPendingListOfServices")
	public String loadPendingListOfServices(@RequestParam("hamaraKendra") String auth, ModelMap modelMap) {
		List<BookedServices> listOfbookedServicesDash;
		List<BookedServices> listOfPendingBookedServices;
		String decode = base64Utils.decode(auth.getBytes());
		Login savedData = loginRepository.findByUserName(decode);
		if (savedData != null) {
			int noOfServices;
			int noOfEmployee;
			List<EmployeeDetails> employeeDetails = employeeDetailsRepo.findAll();
			List<BookedServices> bookedServices = bookedServicesRepo.findSortedDesc();
			noOfServices = bookedServices.size();
			noOfEmployee = employeeDetails.size();
			if (savedData.getRole().equalsIgnoreCase("employee")) {
				listOfbookedServicesDash = bookedServicesRepo.findBymappedEmp(savedData.getUserName());
				listOfPendingBookedServices = bookedServicesRepo.getPendingStatusByEmp(savedData.getUserName());
			} else {
				listOfbookedServicesDash = bookedServicesRepo.findSortedDesc();
				listOfPendingBookedServices = bookedServicesRepo.getPendingStatus();
			}
			
			
			modelMap.addAttribute("listOfbookedServicesDash", listOfbookedServicesDash);
			modelMap.put("username", savedData.getUserName());
			modelMap.put("firstName", savedData.getFirstName());
			modelMap.put("lastName", savedData.getLastName());
			modelMap.put("role", savedData.getRole());
			modelMap.put("authToken", new String(savedData.getToken()));
			modelMap.put("noOfEmployee", noOfEmployee);
			modelMap.put("lastLoginDate", savedData.getLastLoggedIn());
			modelMap.put("noOfServices", noOfServices);
			modelMap.addAttribute("listOfBookedServices", listOfbookedServicesDash);
			modelMap.addAttribute("listOfPendingBookedServices", listOfPendingBookedServices);
			return "loadPendingListOfServices";
		}
		modelMap.put("msg", "Something Went Wrong..");
		return "index";
	}
	
	@GetMapping("/loadCompletedListOfServices")
	public String loadCompletedListOfServices(@RequestParam("hamaraKendra") String auth, ModelMap modelMap) {
		List<BookedServices> listOfbookedServicesDash;
		List<BookedServices> listOfPendingBookedServices;
		String decode = base64Utils.decode(auth.getBytes());
		Login savedData = loginRepository.findByUserName(decode);
		if (savedData != null) {
			int noOfServices;
			int noOfEmployee;
			List<EmployeeDetails> employeeDetails = employeeDetailsRepo.findAll();
			List<BookedServices> bookedServices = bookedServicesRepo.findSortedDesc();
			noOfServices = bookedServices.size();
			noOfEmployee = employeeDetails.size();
			if (savedData.getRole().equalsIgnoreCase("employee")) {
				listOfbookedServicesDash = bookedServicesRepo.findBymappedEmp(savedData.getUserName());
				listOfPendingBookedServices = bookedServicesRepo.getCompleteStatusByEmp(savedData.getUserName());

			} else {
				listOfbookedServicesDash = bookedServicesRepo.findSortedDesc();
				listOfPendingBookedServices = bookedServicesRepo.getCompleteStatus();
			}
			
			modelMap.addAttribute("listOfbookedServicesDash", listOfbookedServicesDash);
			modelMap.put("username", savedData.getUserName());
			modelMap.put("firstName", savedData.getFirstName());
			modelMap.put("lastName", savedData.getLastName());
			modelMap.put("role", savedData.getRole());
			modelMap.put("authToken", new String(savedData.getToken()));
			modelMap.put("noOfEmployee", noOfEmployee);
			modelMap.put("lastLoginDate", savedData.getLastLoggedIn());
			modelMap.put("noOfServices", noOfServices);
			modelMap.addAttribute("listOfBookedServices", listOfbookedServicesDash);
			modelMap.addAttribute("listOfPendingBookedServices", listOfPendingBookedServices);
			return "loadCompleteListOfServices";
		}
		modelMap.put("msg", "Something Went Wrong..");
		return "index";
	}
	
	public Date getCurrentDate() {
		Date date = new Date();
		return date;
	}

	public static String getRandomNumberString() {
		Random rnd = new Random();
		int number = rnd.nextInt(999999);
		return String.format("%06d", number);
	}

}
