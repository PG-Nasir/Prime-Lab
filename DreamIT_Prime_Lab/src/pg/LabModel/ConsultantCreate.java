package pg.LabModel;

public class ConsultantCreate {

	String id;
	String consultantName;
	String designation;
	String line1;
	String line2;
	String line3;
	String line4;
	String line5;
	String userId;
	
	public ConsultantCreate() {
		
	}

	public ConsultantCreate(String id, String consultantName, String designation, String line1, String line2,
			String line3, String line4, String line5) {
		this.id = id;
		this.consultantName = consultantName;
		this.designation = designation;
		this.line1 = line1;
		this.line2 = line2;
		this.line3 = line3;
		this.line4 = line4;
		this.line5 = line5;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getConsultantName() {
		return consultantName;
	}

	public void setConsultantName(String consultantName) {
		this.consultantName = consultantName;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getLine1() {
		return line1;
	}

	public void setLine1(String line1) {
		this.line1 = line1;
	}

	public String getLine2() {
		return line2;
	}

	public void setLine2(String line2) {
		this.line2 = line2;
	}

	public String getLine3() {
		return line3;
	}

	public void setLine3(String line3) {
		this.line3 = line3;
	}

	public String getLine4() {
		return line4;
	}

	public void setLine4(String line4) {
		this.line4 = line4;
	}

	public String getLine5() {
		return line5;
	}

	public void setLine5(String line5) {
		this.line5 = line5;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}
