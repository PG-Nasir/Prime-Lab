package pg.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import pg.OrganizationModel.OrganizationInfo;
import pg.SettingModel.DoctorCreate;
import pg.SettingModel.Employee;
import pg.SettingModel.MemberCreate;
import pg.SettingModel.ProjectFeature;
import pg.assetModel.AssetInformation;
import pg.model.RoleManagement;
import pg.services.PasswordService;
import pg.services.SettingService;


@Controller
public class SettingController {

	@Autowired
	private SettingService settingService;
	
	//Employee

/*	@RequestMapping(value = "/employee_create",method=RequestMethod.GET)
	public ModelAndView employee_create(ModelMap map,HttpSession session) {

		String userId=(String)session.getAttribute("userId");
		String userName=(String)session.getAttribute("userName");

		List<FactoryModel> factoryList = registerService.getAllFactories();
		List<Line> lineList = registerService.getLineList();
		List<Designation> designationList = registerService.getDesignationList();
		//List<Department> departmentList = registerService.getDepartmentList();
		ModelAndView view = new ModelAndView("register/employee_create");

		view.addObject("factorylist",factoryList);
		//view.addObject("department",departmentList);
		view.addObject("designation",designationList);
		view.addObject("line",lineList);
		map.addAttribute("userId",userId);
		map.addAttribute("userName",userName);

		return view; //JSP - /WEB-INF/view/index.jsp
	}*/

	@RequestMapping(value = "/allEmployee", method = RequestMethod.GET)
	public @ResponseBody JSONObject allEmployee() {
		
		System.out.println("All Employee Code");

		List<Employee> employeeList = settingService.getEmployeeList();

		JSONObject mainobj = new JSONObject();
		JSONArray mainarray = new JSONArray();

		for (int i = 0; i < employeeList.size(); i++) {

			JSONObject obj = new JSONObject();

			System.out.println("Employee Code"+employeeList.get(i).getEmployeeCode());
			obj.put("EmployeeName", employeeList.get(i).getEmployeeName());
			obj.put("Department", employeeList.get(i).getDepartment());
			obj.put("Designation", employeeList.get(i).getDesignation());
			obj.put("factoryId", employeeList.get(i).getFactoryId());
			obj.put("DepartmentId", employeeList.get(i).getDepartmentId());
			obj.put("DesignationId", employeeList.get(i).getDesignationId());

			obj.put("CardNo", employeeList.get(i).getCardNo());
			obj.put("Line", employeeList.get(i).getLine());
			obj.put("Grade", employeeList.get(i).getGrade());
			obj.put("EmployeeCode", employeeList.get(i).getEmployeeCode());
			obj.put("JoinDate", employeeList.get(i).getJoinDate());
			
			obj.put("religion", employeeList.get(i).getReligion());
			obj.put("gender", employeeList.get(i).getGender());
			obj.put("email", employeeList.get(i).getEmail());
			obj.put("contact", employeeList.get(i).getContact());
			obj.put("nationality", employeeList.get(i).getNationality());
			obj.put("nationaliid", employeeList.get(i).getNationalId());
			obj.put("birthdate", employeeList.get(i).getBirthDate());

			mainarray.add(obj);
		}
		mainobj.put("result", mainarray);
		return mainobj;
	}

/*	@RequestMapping(value = "/saveEmployee",method=RequestMethod.POST)
	public @ResponseBody JSONObject saveEmployee(Employee v) {

		JSONObject objmain = new JSONObject();
		if(!registerService.isEmployeeExist(v)) {
			if(registerService.saveEmployee(v)) {

				JSONArray mainarray = new JSONArray();

				List<Employee> List= registerService.getEmployeeList();

				for(int a=0;a<List.size();a++) {

					JSONObject obj = new JSONObject();
					obj.put("EmployeeCode", List.get(a).getEmployeeCode());
					obj.put("EmployeeName", List.get(a).getEmployeeName());
					obj.put("CardNo", List.get(a).getCardNo());
					obj.put("Department", List.get(a).getDepartment());
					obj.put("Designation", List.get(a).getDesignation());
					obj.put("Line", List.get(a).getLine());
					obj.put("Grade", List.get(a).getGrade());
					obj.put("JoinDate", List.get(a).getJoinDate());

					mainarray.add(obj);
				}


				objmain.put("result", mainarray);

			}else {
				objmain.put("result", "Something Wrong");
			}	
		}else {
			objmain.put("result", "duplicate");
		}

		return objmain;
	}*/

/*	@RequestMapping(value = "/editEmployee",method=RequestMethod.POST)
	public @ResponseBody JSONObject editEmployee(Employee v) {

		JSONObject objmain = new JSONObject();
		if(registerService.isEmployeeExist(v)) {
			if(registerService.editEmployee(v)) {

				JSONArray mainarray = new JSONArray();

				List<Employee> List= registerService.getEmployeeList();

				for(int a=0;a<List.size();a++) {

					JSONObject obj = new JSONObject();
					obj.put("EmployeeCode", List.get(a).getEmployeeCode());
					obj.put("EmployeeName", List.get(a).getEmployeeName());
					obj.put("CardNo", List.get(a).getCardNo());
					obj.put("Department", List.get(a).getDepartment());
					obj.put("Designation", List.get(a).getDesignation());
					obj.put("Line", List.get(a).getLine());
					obj.put("Grade", List.get(a).getGrade());
					obj.put("JoinDate", List.get(a).getJoinDate());

					mainarray.add(obj);
				}


				objmain.put("result", mainarray);

			}else {
				objmain.put("result", "Something Wrong");
			}	
		}else {
			objmain.put("result", "duplicate");
		}

		return objmain;
	}*/
	
	@RequestMapping(value = "/getEmployeeInfoByEmployeeCode",method = RequestMethod.GET)
	public @ResponseBody JSONObject getEmployeeInfo(String employeeCode) {
		JSONObject obj = new JSONObject();
		Employee employee = settingService.getEmployeeInfoByEmployeeCode(employeeCode);
		obj.put("employeeInfo", employee);
		
		return obj;
	}
	

	// Role Management
	@RequestMapping(value={"/role_management"})
	public ModelAndView roleManagement(ModelMap map,HttpSession session) {
		ModelAndView view = new ModelAndView("setting/role-management");
		map.addAttribute("roleList","");
		map.addAttribute("resourceList","");
		view.addObject("allModule",settingService.getAllModuleName());
		return view;
	}

	
	@RequestMapping(value = "/getSubmenu/{moduleId}", method = RequestMethod.POST)
	public @ResponseBody List<RoleManagement> getSubmenu(@PathVariable ("moduleId") String moduleId) {

		List<RoleManagement> getSubmenu = settingService.getSubmenu(moduleId);

		return getSubmenu;
	}
	
	@RequestMapping(value = "/saveRolePermission", method = RequestMethod.POST)
	public @ResponseBody boolean saveRolePermission(RoleManagement v) {
		boolean saveRolePermission = settingService.saveRolePermission(v);
		return saveRolePermission;
	}
	
	@RequestMapping(value = "/getAllRoleName", method = RequestMethod.POST)
	public @ResponseBody List<RoleManagement> getAllRoleName() {

		List<RoleManagement> getAllRoleName = settingService.getAllRoleName();

		return getAllRoleName;
	}
	
	@RequestMapping(value = "/getAllPermissions/{id}", method = RequestMethod.GET)
	public @ResponseBody JSONObject getAllPermissions(@PathVariable ("id") String id) {
		JSONObject obj = new JSONObject();
		List<RoleManagement> getAllPermissions = settingService.getAllPermissions(id);
		obj.put("permissionList", getAllPermissions);
		return obj;
	}
	
	@RequestMapping(value = "/editRolePermission", method = RequestMethod.POST)
	public @ResponseBody boolean editRolePermission(RoleManagement v) {
		boolean editRolePermission = settingService.editRolePermission(v);
		return editRolePermission;
	}
	
/*	@RequestMapping(value = "/deleteEmployee/{empcode}",method=RequestMethod.POST)
	public @ResponseBody boolean deleteEmployee(@PathVariable ("empcode") String empcode) {
		boolean deleteEmployee = registerService.deleteEmployee(empcode);
		return deleteEmployee;
		
	}*/
	
	@RequestMapping(value = "/menu_add",method=RequestMethod.GET)
	public ModelAndView employee_create(ModelMap map,HttpSession session) {

		String userId=(String)session.getAttribute("userId");
		String userName=(String)session.getAttribute("userName");
		List<ProjectFeature> List= settingService.getModuleList();
		List<ProjectFeature> menuList= settingService.getMenuList();
		
		ModelAndView view = new ModelAndView("setting/menu_add");


		view.addObject("ModuleList", List);
		view.addObject("menuList", menuList);
		
		map.addAttribute("userId",userId);
		map.addAttribute("userName",userName);

		return view; //JSP - /WEB-INF/view/index.jsp
	}
	
	@RequestMapping(value = "/saveMenuInformation",method=RequestMethod.POST)
	public @ResponseBody JSONObject saveMenuInformation(ProjectFeature v)  {

		JSONObject objmain = new JSONObject();
		
		if(!settingService.isMenuExist(v)) {
			if(settingService.saveMenu(v)) {

				JSONArray mainarray = new JSONArray();

				List<ProjectFeature> List= settingService.getMenuList();
				objmain.put("result", List);

			}else {
				objmain.put("result", "Something Wrong");
			}	
		}else {
			objmain.put("result", "duplicate");
		}

		
		
		return objmain;
	}
	
	
	//Doctor Create
	@RequestMapping(value = "doctor_create",method=RequestMethod.GET)
	public ModelAndView doctor_create(ModelMap map,HttpSession session) {
		
		String userId=(String)session.getAttribute("userId");
		String userName=(String)session.getAttribute("userName");
		
		List<DoctorCreate> labGroupList= settingService.getLabGroupList();
		List<DoctorCreate> List= settingService.getDoctorList();
		String doctorId=settingService.getMaxDoctorId();
		ModelAndView view = new ModelAndView("setting/doctorcreate");
		view.addObject("doctorList", List);
		view.addObject("labGroupList", labGroupList);
		map.addAttribute("doctorId", "DC-"+doctorId);
		
		map.addAttribute("userId",userId);
		map.addAttribute("userName",userName);
		
		return view; //JSP - /WEB-INF/view/index.jsp
		
	}
	
	
	@RequestMapping(value = "/saveDoctor",method=RequestMethod.POST)
	public @ResponseBody JSONObject saveDoctor(DoctorCreate v) {
		JSONObject objmain = new JSONObject();
		if(!settingService.isDoctorExist(v)) {
		
			if(settingService.saveDoctor(v)) {

				List<DoctorCreate> List= settingService.getDoctorList();
				objmain.put("result", List);

			}else {
				objmain.put("result", "Something Wrong");
			}	
		}else {
			objmain.put("result", "duplicate");
		}

		return objmain;
	}
	
	@RequestMapping(value = "/editDoctor",method=RequestMethod.POST)
	public @ResponseBody JSONObject editDoctor(DoctorCreate v) {
		JSONObject objmain = new JSONObject();
		if(!settingService.isDoctorExist(v)) {
		
			if(settingService.editDoctor(v)) {

				List<DoctorCreate> List= settingService.getDoctorList();
				objmain.put("result", List);

			}else {
				objmain.put("result", "Something Wrong");
			}	
		}else {
			objmain.put("result", "duplicate");
		}

		return objmain;
	}
	
	@RequestMapping(value = "/getDoctorDetails/{doctorId}",method=RequestMethod.GET)
	public @ResponseBody JSONObject getDoctorDetails(@PathVariable ("doctorId") String doctorId) {
		
		System.out.println("doctorId "+doctorId);
		JSONObject objmain = new JSONObject();

		List<DoctorCreate> List= settingService.getDoctorDetails(doctorId);
		for(int a=0;a<List.size();a++) {
			System.out.println("doctorId "+List.get(a).getDoctorId());
			System.out.println("doctorName "+List.get(a).getDoctorName());
		}
		objmain.put("result", List);
		
		List<DoctorCreate> List1= settingService.getDoctorCommissionDetails(doctorId);

		objmain.put("resultDoctorCommission", List1);
		
		return objmain;
	}
	
	
	@RequestMapping(value = "organization_create",method=RequestMethod.GET)
	public ModelAndView organization_create(ModelMap map,HttpSession session) {

		String userId=(String)session.getAttribute("userId");
		String userName=(String)session.getAttribute("userName");
		ModelAndView view = new ModelAndView("setting/organization-create");
		map.addAttribute("userId",userId);
		map.addAttribute("userName",userName);

		return view; //JSP - /WEB-INF/view/index.jsp

	}
	@RequestMapping(value = "/getOrganizationName", method = RequestMethod.POST)
	public @ResponseBody List<OrganizationInfo> getOrganizationName(OrganizationInfo v) {

		List<OrganizationInfo> OrganizationCreate = settingService.getOrganization();
		return OrganizationCreate;

	}
	@RequestMapping(value = "/saveOrganizationName", method = RequestMethod.POST)
	public @ResponseBody String saveOrganizationName(OrganizationInfo v) {

		boolean saveOrganizationName = settingService.editOrganization(v);
		return "success";

	}
	
	@RequestMapping(value = "printDoctorList",method=RequestMethod.GET)
	public @ResponseBody ModelAndView printTestList() {
		
		ModelAndView view=new ModelAndView("setting/printDoctorList");

		return view;
	}
	
	//Doctor Create
	@RequestMapping(value = "membershipcreate",method=RequestMethod.GET)
	public ModelAndView membershipcreate(ModelMap map,HttpSession session) {
		
		String userId=(String)session.getAttribute("userId");
		String userName=(String)session.getAttribute("userName");
		

		List<MemberCreate> memberLList= settingService.getMemberList();
		ModelAndView view = new ModelAndView("setting/membershipcreate");
		view.addObject("memberLList", memberLList);
		
		map.addAttribute("userId",userId);
		map.addAttribute("userName",userName);
		
		return view; //JSP - /WEB-INF/view/index.jsp
		
	}
	
	@RequestMapping(value = "/saveMember",method=RequestMethod.POST)
	public @ResponseBody JSONObject saveMember(MemberCreate v) {
		JSONObject objmain = new JSONObject();
		if(!settingService.isMemberExist(v)) {
		
			if(settingService.saveMember(v)) {

				List<MemberCreate> List= settingService.getMemberList();
				objmain.put("result", List);

			}else {
				objmain.put("result", "Something Wrong");
			}	
		}else {
			objmain.put("result", "duplicate");
		}

		return objmain;
	}
	
	@RequestMapping(value = "/getMemberDetails/{memberId}",method=RequestMethod.GET)
	public @ResponseBody JSONObject getMemberDetails(@PathVariable ("memberId") String memberId) {
		

		JSONObject objmain = new JSONObject();

		List<MemberCreate> List= settingService.getMemberDetails(memberId);
		objmain.put("result", List);
		
		
		return objmain;
	}
	
	@RequestMapping(value = "/editMember",method=RequestMethod.POST)
	public @ResponseBody JSONObject editMember(MemberCreate v) {
		JSONObject objmain = new JSONObject();
		if(!settingService.isMemberExist(v)) {
		
			if(settingService.editMember(v)) {

				List<MemberCreate> List= settingService.getMemberList();
				objmain.put("result", List);

			}else {
				objmain.put("result", "Something Wrong");
			}	
		}else {
			objmain.put("result", "duplicate");
		}

		return objmain;
	}
}
