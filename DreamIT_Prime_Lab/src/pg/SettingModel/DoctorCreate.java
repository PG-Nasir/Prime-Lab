package pg.SettingModel;

public class DoctorCreate {

	String doctorId="";
	String doctorName="";
	String doctorCode="";
	String degree="";
	String doctorCategory="";
	String departmentId="";
	String address="";
	String religion="";
	String sex="";
	String mobile="";
	String userId="";
	String seatActive="";
	String groupId="";
	String groupName="";
	String doctorComission="";
	String doctorComissionDeduction="";
	String parentId="";
	String resultList="";
	
	String visitFee;
	String roomNo;
	
	public DoctorCreate() {
		
	}
	

	public DoctorCreate(String DcotorId, String DcotorName) {
		this.doctorId = DcotorId;
		this.doctorName = DcotorName;
	}
	
	public DoctorCreate(String Sn, String GroupName,String DoctorComission, String DoctorComissionDeduction,String ParentId) {
		this.groupId = Sn;
		this.groupName = GroupName;
		this.doctorComission = DoctorComission;
		this.doctorComissionDeduction = DoctorComissionDeduction;
		this.parentId = ParentId;
	}
	
	public DoctorCreate(String Sn, String GroupName,String DoctorComission, String ParentId) {
		this.groupId = Sn;
		this.groupName = GroupName;
		this.doctorComission = DoctorComission;
		this.parentId = ParentId;
	}

	public DoctorCreate(String DcotorId, String DcotorName, String Degree) {
		this.doctorId = DcotorId;
		this.doctorName = DcotorName;
		this.degree = Degree;
	}

	
	public DoctorCreate(String doctorId, String doctorName, String doctorCode, String degree, String doctorCategory,
			String departmentId, String address, String religion, String sex, String mobile, String userId,
			String seatActive) {
	
		this.doctorId = doctorId;
		this.doctorName = doctorName;
		this.doctorCode = doctorCode;
		this.degree = degree;
		this.doctorCategory = doctorCategory;
		this.departmentId = departmentId;
		this.address = address;
		this.religion = religion;
		this.sex = sex;
		this.mobile = mobile;
		this.userId = userId;
		this.seatActive = seatActive;
	}
	public DoctorCreate(String DoctorId, String Name, String DoctorCode, String Address, String Religion, String Sex,
			String Mobile, String Type, String Department, String Degree,String VisitFee,String RoomNo,String s) {
		this.doctorId=DoctorId;
		this.doctorName=Name;
		this.doctorCode=DoctorCode;
		this.address=Address;
		this.religion=Religion;
		this.sex=Sex;
		this.mobile=Mobile;
		this.doctorCategory=Type;
		this.departmentId=Department;
		this.degree=Degree;
		this.visitFee=VisitFee;
		this.roomNo=RoomNo;
	}

	public String getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public String getDoctorCode() {
		return doctorCode;
	}
	public void setDoctorCode(String doctorCode) {
		this.doctorCode = doctorCode;
	}
	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}
	public String getDoctorCategory() {
		return doctorCategory;
	}
	public void setDoctorCategory(String doctorCategory) {
		this.doctorCategory = doctorCategory;
	}
	public String getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getReligion() {
		return religion;
	}
	public void setReligion(String religion) {
		this.religion = religion;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getSeatActive() {
		return seatActive;
	}
	public void setSeatActive(String seatActive) {
		this.seatActive = seatActive;
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

	public String getDoctorComission() {
		return doctorComission;
	}

	public void setDoctorComission(String doctorComission) {
		this.doctorComission = doctorComission;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getResultList() {
		return resultList;
	}

	public void setResultList(String resultList) {
		this.resultList = resultList;
	}


	public String getVisitFee() {
		return visitFee;
	}


	public void setVisitFee(String visitFee) {
		this.visitFee = visitFee;
	}


	public String getRoomNo() {
		return roomNo;
	}


	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}


	public String getDoctorComissionDeduction() {
		return doctorComissionDeduction;
	}


	public void setDoctorComissionDeduction(String doctorComissionDeduction) {
		this.doctorComissionDeduction = doctorComissionDeduction;
	}
	
	
}
