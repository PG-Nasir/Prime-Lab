package pg.LabModel;

public class LabReportCreate {
	String groupId;
	String titleId;
	String titleName;
	String groupName;
	String userId;
	
	public LabReportCreate() {
		
	}
	
	public LabReportCreate(String TitleId, String TitleName, String GroupId, String GroupName) {
		this.titleId=TitleId;
		this.titleName=TitleName;
		this.groupId=GroupId;
		this.groupName=GroupName;
	}
	
	
	
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getTitleId() {
		return titleId;
	}
	public void setTitleId(String titleId) {
		this.titleId = titleId;
	}
	public String getTitleName() {
		return titleName;
	}
	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
}
