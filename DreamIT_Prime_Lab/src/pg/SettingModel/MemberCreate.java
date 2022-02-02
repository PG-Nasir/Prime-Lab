package pg.SettingModel;

public class MemberCreate {
	String memberId="";
	String memberName="";
	String occupation="";
	String contact="";
	String membershipType="";
	String userId="";
	
	public MemberCreate() {
		
	}
	
	
	public MemberCreate(String MemberId,String MemberName,String Contact,String MembershipType,String Occupation) {
		this.memberId=MemberId;
		this.memberName=MemberName;
		this.occupation=Occupation;
		this.contact=Contact;
		this.membershipType=MembershipType;
	}
	
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getMembershipType() {
		return membershipType;
	}
	public void setMembershipType(String membershipType) {
		this.membershipType = membershipType;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
}
