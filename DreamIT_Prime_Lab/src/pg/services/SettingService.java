package pg.services;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import pg.OrganizationModel.OrganizationInfo;
import pg.SettingModel.DoctorCreate;
import pg.SettingModel.Employee;
import pg.SettingModel.MemberCreate;
import pg.SettingModel.ProjectFeature;
import pg.assetModel.AssetInformation;
import pg.model.Menu;
import pg.model.MenuInfo;
import pg.model.Module;
import pg.model.ModuleInfo;
import pg.model.ModuleWiseMenu;
import pg.model.ModuleWiseMenuSubMenu;
import pg.model.Password;
import pg.model.SubMenuInfo;
import pg.model.Ware;
import pg.model.WareInfo;
import pg.model.RoleManagement;

public interface SettingService{
	public boolean addWare(Ware ware) ;
	
	public List<ModuleWiseMenuSubMenu> getAllModuleWiseMenuSubMenuName(int i,String menulist);
	public List<ModuleWiseMenuSubMenu> getAllModuleWiseSubmenu();
	public List<Module> getAllModuleName();
	public List<Menu> getAllModuleWiseMenu(int i);
	public List<ModuleWiseMenu> getAllModuleWiseMenu();

	
	public List<WareInfo> getAllWareName();
	
	public boolean addUser(Password str) ;
	public boolean addModule(ModuleInfo m) ;
	public boolean addMenu(MenuInfo m) ;
	public boolean addSubMenu(SubMenuInfo m) ;
	
	public List<OrganizationInfo> getOrganization();
	public boolean editOrganization(OrganizationInfo v);
	
	
	public int getMaxNoticeNo();
	public boolean savenotice(String heading, String departs, String textbody, String filename,String userid);

	
	public JSONArray getDepartmentWiseUserList(String departmentIds);

	public String notificationTargetAdd(JSONObject notificationObject,JSONArray targetList);
	public String notificationSeen(String targetId);
	public String updateNotificationToSeen(String notificationId,String targetId);
	
	public JSONArray getUserList();
	public String saveGroup(String group);
	public String editGroup(String group);
	public JSONArray getGroupList();
	public JSONArray getGroupMembers(String groupId);
	public JSONArray getNotificationList(String targetId);
	
	public JSONArray getFormPermitInvoiceList(String formId,String ownerId,String permittedUserId);
	public JSONArray getFormPermitedUsers(String formId,String ownerId);
	public String submitFileAccessPermit(String fileAccessPermit);

	public JSONArray getMenus(String userId);
	public List<RoleManagement> getSubmenu(String moduleId);
	public boolean saveRolePermission(RoleManagement v);
	public List<RoleManagement> getAllRoleName();
	public List<RoleManagement> getAllPermissions(String id);
	public boolean editRolePermission(RoleManagement v);

	public List<Employee> getEmployeeList();

	public Employee getEmployeeInfoByEmployeeCode(String employeeCode);

	public List<ProjectFeature> getModuleList();

	public boolean isMenuExist(ProjectFeature v);

	public boolean saveMenu(ProjectFeature v);

	public List<ProjectFeature> getMenuList();

	public boolean saveDoctorDirect(DoctorCreate v);

	public List<DoctorCreate> getDoctorList();

	public List<DoctorCreate> getConsultantList();

	public List<DoctorCreate> getLabGroupList();

	public String getMaxDoctorId();

	public boolean isDoctorExist(DoctorCreate v);

	public boolean saveDoctor(DoctorCreate v);

	public boolean editDoctor(DoctorCreate v);

	public List<DoctorCreate> getDoctorDetails(String doctorId);

	public List<DoctorCreate> getDoctorCommissionDetails(String doctorId);

	public boolean isMemberExist(MemberCreate v);

	public boolean saveMember(MemberCreate v);

	public List<MemberCreate> getMemberList();

	public List<MemberCreate> getMemberDetails(String memberId);

	public boolean editMember(MemberCreate v);

	public String getAccessStatus(String userId);


	
	
}