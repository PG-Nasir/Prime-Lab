package pg.LabModel;

public class TestGroup {
	String groupId="";
	String groupName="";
	
	
	public TestGroup() {
		
	}

	
	public TestGroup(String groupId, String groupName) {

		this.groupId = groupId;
		this.groupName = groupName;
	}



	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}


	public String getGroupName() {
		return groupName;
	}


	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	
	
}
