package pg.AccountsModel;

public class AccountsCreate {
		
	String headId="";
	String parentId="";
	String headName="";
	String subCategoryName="";
	String remark="";
	String userId="";
	String unitId="";
	String depId="";
	
	
	public AccountsCreate() {
		
	}

	

	public AccountsCreate(String parentId, String headName) {
		this.parentId = parentId;
		this.headName = headName;
	}



	public AccountsCreate(String HeadId, String subCategoryName, String PheadId, String ParentHead,String remark) {
		// TODO Auto-generated constructor stub
		this.headId=HeadId;
		this.subCategoryName = subCategoryName;
		this.parentId = PheadId;
		this.headName=ParentHead;
		this.remark=remark;
	}






	public String getHeadName() {
		return headName;
	}
	public void setHeadName(String headName) {
		this.headName = headName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public String getDepId() {
		return depId;
	}

	public void setDepId(String depId) {
		this.depId = depId;
	}

	public String getSubCategoryName() {
		return subCategoryName;
	}

	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}


	public String getHeadId() {
		return headId;
	}


	public void setHeadId(String headId) {
		this.headId = headId;
	}


	public String getParentId() {
		return parentId;
	}


	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
	
	
}
