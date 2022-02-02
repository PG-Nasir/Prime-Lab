package pg.LabModel;

public class MachineCreate {
	String groupId;
	String machineId;
	String machineName;
	String groupName;
	String userId;
	
	public MachineCreate() {
		
	}

	public MachineCreate(String MachineId, String MachineName, String GroupId,String GroupName) {
		this.machineId=MachineId;
		this.machineName=MachineName;
		this.groupId=GroupId;
		this.groupName=GroupName;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getMachineId() {
		return machineId;
	}

	public void setMachineId(String machineId) {
		this.machineId = machineId;
	}

	public String getMachineName() {
		return machineName;
	}

	public void setMachineName(String machineName) {
		this.machineName = machineName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	
}
