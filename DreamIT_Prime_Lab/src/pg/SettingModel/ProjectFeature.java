package pg.SettingModel;

public class ProjectFeature {
	String userId="";
	String moduleId="";
	String menuId="";
	String subMenuId="";
	
	String moduleName="";
	String menuName="";
	String subMenuName="";
	
	public ProjectFeature() {
		
	}
	
	public ProjectFeature(String ModuleId,String ModuleName) {
		this.moduleId=ModuleId;
		this.moduleName=ModuleName;
	}
	
	public ProjectFeature(String MenuId,String MenuName,String ModuleId,String ModuleName) {
		this.menuId=MenuId;
		this.menuName=MenuName;
		this.moduleId=ModuleId;
		this.moduleName=ModuleName;
	}
	
	public String getModuleId() {
		return moduleId;
	}
	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getSubMenuId() {
		return subMenuId;
	}
	public void setSubMenuId(String subMenuId) {
		this.subMenuId = subMenuId;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getSubMenuName() {
		return subMenuName;
	}
	public void setSubMenuName(String subMenuName) {
		this.subMenuName = subMenuName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
}
