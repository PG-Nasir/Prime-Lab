package pg.services;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import pg.OrganizationModel.OrganizationInfo;
import pg.SettingModel.DoctorCreate;
import pg.SettingModel.Employee;
import pg.SettingModel.MemberCreate;
import pg.SettingModel.ProjectFeature;
import pg.dao.SettingDAO;
import pg.dao.SettingDAOImpl;


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

@Service
public class SettingServiceImpl implements SettingService{

	@Autowired
	private SettingDAOImpl settingDAO=new SettingDAOImpl();
	
	@Transactional
	public List<Module> getAllModuleName() {
		// TODO Auto-generated method stub
		return settingDAO.getAllModuleName();
	}
	
	
	@Transactional
	public List<Menu> getAllModuleWiseMenu(int i) {
		// TODO Auto-generated method stub
		return settingDAO.getAllModuleWiseMenu(i);
	}
	
	@Transactional
	public List<ModuleWiseMenu> getAllModuleWiseMenu() {
		// TODO Auto-generated method stub
		return settingDAO.getAllModuleWiseMenu();
	}


	@Autowired
	private SettingDAO settDAO;

	@Transactional
	public boolean  addWare(Ware ware) {
		return settDAO.addWare(ware);
	}


	@Override
	public List<ModuleWiseMenuSubMenu> getAllModuleWiseMenuSubMenuName(int i, String menulist) {
		// TODO Auto-generated method stub
		return settDAO.getAllModuleWiseMenuSubMenuName(i, menulist);
	}

	@Override
	public List<ModuleWiseMenuSubMenu> getAllModuleWiseSubmenu() {
		// TODO Auto-generated method stub
		return settDAO.getAllModuleWiseSubmenu();
	}
	
	@Transactional
	public boolean  addUser(Password pass) {
		return settDAO.addUser(pass);
	}

	@Transactional
	public boolean  addModule(ModuleInfo m) {
		return settDAO.addModule(m);
	}

	@Transactional
	public boolean  addMenu(MenuInfo m) {
		return settDAO.addMenu(m);
	}
	
	@Transactional
	public boolean  addSubMenu(SubMenuInfo m) {
		return settDAO.addSubMenu(m);
	}


	@Override
	public List<WareInfo> getAllWareName() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<OrganizationInfo> getOrganization() {
		// TODO Auto-generated method stub
		return settDAO.getOrganization();
	}


	@Override
	public boolean editOrganization(OrganizationInfo v) {
		// TODO Auto-generated method stub
		return settDAO.editOrganization(v);
	}


	@Override
	public boolean savenotice(String heading, String departs, String textbody, String filename,String userid) {
		// TODO Auto-generated method stub
		return settDAO.savenotice(heading, departs, textbody, filename, userid);
	}


	public int getMaxNoticeNo() {
		// TODO Auto-generated method stub
		return settDAO.getMaxNoticeNo();
	}



	@Override
	public JSONArray getDepartmentWiseUserList(String departmentIds) {
		// TODO Auto-generated method stub
		return settDAO.getDepartmentWiseUserList(departmentIds);
	}
	
	@Override
	public String notificationTargetAdd(JSONObject notificationObject, JSONArray targetList) {
		// TODO Auto-generated method stub
		return settDAO.notificationTargetAdd(notificationObject, targetList);
	}
	
	@Override
	public String notificationSeen(String targetId) {
		// TODO Auto-generated method stub
		return settDAO.notificationSeen(targetId);
	}
	
	@Override
	public String updateNotificationToSeen(String notificationId,String targetId) {
		// TODO Auto-generated method stub
		return settDAO.updateNotificationToSeen(notificationId, targetId);
	}

	@Override
	public JSONArray getNotificationList(String targetId) {
		// TODO Auto-generated method stub
		return settDAO.getNotificationList(targetId);
	}


	@Override
	public JSONArray getUserList() {
		// TODO Auto-generated method stub
		return settDAO.getUserList();
	}

	@Override
	public String saveGroup(String group) {
		// TODO Auto-generated method stub
		return settDAO.saveGroup(group);
	}

	@Override
	public String editGroup(String group) {
		// TODO Auto-generated method stub
		return settDAO.editGroup(group);
	}

	@Override
	public JSONArray getGroupList() {
		// TODO Auto-generated method stub
		return settDAO.getGroupList();
	}


	@Override
	public JSONArray getGroupMembers(String groupId) {
		// TODO Auto-generated method stub
		return settDAO.getGroupMembers(groupId);
	}


	@Override
	public JSONArray getMenus(String userId) {
		// TODO Auto-generated method stub
		return settDAO.getMenus(userId);
	}


	@Override
	public JSONArray getFormPermitInvoiceList(String formId, String ownerId, String permittedUserId) {
		// TODO Auto-generated method stub
		return settDAO.getFormPermitInvoiceList(formId, ownerId, permittedUserId);
	}


	@Override
	public String submitFileAccessPermit(String fileAccessPermit) {
		// TODO Auto-generated method stub
		return settDAO.submitFileAccessPermit(fileAccessPermit);
	}


	@Override
	public JSONArray getFormPermitedUsers(String formId, String ownerId) {
		// TODO Auto-generated method stub
		return settDAO.getFormPermitedUsers(formId, ownerId);
	}
	

	@Override
	public List<RoleManagement> getSubmenu(String moduleId) {
		// TODO Auto-generated method stub
		return settDAO.getSubmenu(moduleId);
	}


	@Override
	public boolean saveRolePermission(RoleManagement v) {
		// TODO Auto-generated method stub
		return settDAO.saveRolePermission(v);
	}


	@Override
	public List<RoleManagement> getAllRoleName() {
		// TODO Auto-generated method stub
		return settDAO.getAllRoleName();
	}


	@Override
	public List<RoleManagement> getAllPermissions(String id) {
		// TODO Auto-generated method stub
		return settDAO.getAllPermissions(id);
	}


	@Override
	public boolean editRolePermission(RoleManagement v) {
		// TODO Auto-generated method stub
		return settDAO.editRolePermission(v);
	}


	@Override
	public List<Employee> getEmployeeList() {
		// TODO Auto-generated method stub
		return settDAO.getEmployeeList();
	}


	@Override
	public Employee getEmployeeInfoByEmployeeCode(String employeeCode) {
		// TODO Auto-generated method stub
		return settDAO.getEmployeeInfoByEmployeeCode(employeeCode);
	}


	@Override
	public List<ProjectFeature> getModuleList() {
		// TODO Auto-generated method stub
		return settDAO.getModuleList();
	}


	@Override
	public boolean isMenuExist(ProjectFeature v) {
		// TODO Auto-generated method stub
		return settDAO.isMenuExist(v);
	}


	@Override
	public boolean saveMenu(ProjectFeature v) {
		// TODO Auto-generated method stub
		return settDAO.saveMenu(v);
	}


	@Override
	public List<ProjectFeature> getMenuList() {
		// TODO Auto-generated method stub
		return settDAO.getMenuList();
	}


	@Override
	public boolean saveDoctorDirect(DoctorCreate v) {
		// TODO Auto-generated method stub
		return settDAO.saveDoctorDirect(v);
	}


	@Override
	public List<DoctorCreate> getDoctorList() {
		// TODO Auto-generated method stub
		return settDAO.getDoctorList();
	}


	@Override
	public List<DoctorCreate> getConsultantList() {
		// TODO Auto-generated method stub
		return settDAO.getConsultantList();
	}


	@Override
	public List<DoctorCreate> getLabGroupList() {
		// TODO Auto-generated method stub
		return settDAO.getLabGroupList();
	}


	@Override
	public String getMaxDoctorId() {
		// TODO Auto-generated method stub
		return settDAO.getMaxDoctorId();
	}


	@Override
	public boolean isDoctorExist(DoctorCreate v) {
		// TODO Auto-generated method stub
		return settDAO.isDoctorExist(v);
	}


	@Override
	public boolean saveDoctor(DoctorCreate v) {
		// TODO Auto-generated method stub
		return settDAO.saveDoctor(v);
	}


	@Override
	public boolean editDoctor(DoctorCreate v) {
		// TODO Auto-generated method stub
		return settDAO.editDoctor(v);
	}


	@Override
	public List<DoctorCreate> getDoctorDetails(String doctorId) {
		// TODO Auto-generated method stub
		return settDAO.getDoctorDetails(doctorId);
	}


	@Override
	public List<DoctorCreate> getDoctorCommissionDetails(String doctorId) {
		// TODO Auto-generated method stub
		return settDAO.getDoctorCommissionDetails(doctorId);
	}


	@Override
	public boolean isMemberExist(MemberCreate v) {
		// TODO Auto-generated method stub
		return settDAO.isMemberExist(v);
	}


	@Override
	public boolean saveMember(MemberCreate v) {
		// TODO Auto-generated method stub
		return settDAO.saveMember(v);
	}


	@Override
	public List<MemberCreate> getMemberList() {
		// TODO Auto-generated method stub
		return settDAO.getMemberList();
	}


	@Override
	public List<MemberCreate> getMemberDetails(String memberId) {
		// TODO Auto-generated method stub
		return settDAO.getMemberDetails(memberId);
	}


	@Override
	public boolean editMember(MemberCreate v) {
		// TODO Auto-generated method stub
		return settDAO.editMember(v);
	}


	@Override
	public String getAccessStatus(String userId) {
		// TODO Auto-generated method stub
		return settDAO.getAccessStatus(userId);
	}
	
	
}