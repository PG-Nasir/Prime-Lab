package pg.ReceiptionModel;

public class PatientRegistration {
	String userId;
	String seatId;
	String admissiond_t;
	String seatrate;
	String patientmode;
	String patientname;
	String fathername;
	String husbandname;
	String religion;
	String nationality;
	String nationalId;
	String address;
	String mobile;
	String email;
	String telephone;
	String consultantId;
	String refferId;
	String admissionfee;
	String remark;
	String admissiontype;
	String regNo;
	String age;
	String month;
	String day;
	String sex;
	String contactperson;
	String modereferral;
	String amount;
	String patientfiscalyear;
	String fiscalyear;
	String seatname;
	String relation;
	String period;
	String previousseat;
	String patientId="";
	String refferDegree;
	String consultantDegree;
	
	
	public PatientRegistration() {
		
	}
	
	public PatientRegistration(String AutoId,String RegNo,String AdmissionType,String patientmode,String SeatName,String SeatRate,String SeatId,String PatientName,String FatherName,String HusbandName,String Address,String Nationality,String NationalId,String Age,String Month,String Day,String Sex,String Mobile,String ContactPerson,String Relation, String Telephone,String ConsultantName,String ConsultantId,String ReferralName,String ReferralId,String Remark,String ReferralMode,String Rate,String Amount,String patientfiscalyear,String Period) {
		this.patientId=AutoId;
		this.regNo=RegNo;
		System.out.println("AdmissionType "+AdmissionType);
		System.out.println("patientmode "+patientmode);
		this.admissiontype=AdmissionType;
		this.patientmode=patientmode;
		this.seatname=SeatName;

		this.seatrate=SeatRate;
		this.patientname=PatientName;
		this.fathername=FatherName;
		this.husbandname=HusbandName;
		this.address=Address;
		this.nationality=Nationality;
		this.age=Age;
		this.month=Month;
		this.day=Day;
		this.sex=Sex;
		this.mobile=Mobile;
		this.contactperson=ContactPerson;
		this.relation=Relation;
		this.telephone=Telephone;

		this.consultantId=ConsultantId;
		this.refferId=ReferralId;
		this.remark=Remark;
		this.modereferral=ReferralMode;
		this.amount=Amount;
		this.patientfiscalyear=patientfiscalyear;
		this.period=Period;
	}
	
	public PatientRegistration(String Regno,String patientfiscalyear,String PatientName,String SeatName) {
		this.regNo=Regno;
		this.patientfiscalyear=patientfiscalyear;
		this.patientname=PatientName;
		this.seatname=SeatName;
	}
	

	

	public PatientRegistration(String AutoId, String Regno, String PatientName, String AdmissionDate, String AdmissionTime,
			String SeatName) {
		this.patientId=AutoId;
		this.regNo=Regno;
		this.patientname=PatientName;
		this.seatname=SeatName;
		
		this.admissiond_t=AdmissionDate+"/"+AdmissionTime;
	}


	public PatientRegistration(String AutoId, String Regno, String PatientName, String AdmissionDate, String AdmissionTime,
			String Perioid,String patientfiscalyear,String SeatName) {
		this.patientId=AutoId;
		this.regNo=Regno;
		this.patientname=PatientName;
		this.seatname=SeatName;
		this.period=Perioid;
		this.patientfiscalyear=patientfiscalyear;
		
		this.admissiond_t=AdmissionDate+"/"+AdmissionTime;
	}
	
	public PatientRegistration(String RegNo,String PatientName,String Age,String Month,String Day,String Sex,String RefferId,String RefferDegree,String ConsultantId,String ConsultantDegree,String Address,String SeatName,String MobileNo,String patientfiscalyear,String Period) {

		this.regNo=RegNo;
		this.patientname=PatientName;
		this.age=Age;
		this.month=Month;
		this.day=Day;
		this.sex=Sex;
		this.refferId=RefferId;
		this.refferDegree=RefferDegree;
		this.consultantId=ConsultantId;
		this.consultantDegree=ConsultantDegree;
		this.address=Address;
		this.seatname=SeatName;
		this.mobile=MobileNo;
		this.patientfiscalyear=patientfiscalyear;
		this.period=Period;
	}

	public String getPreviousseat() {
		return previousseat;
	}

	public void setPreviousseat(String previousseat) {
		this.previousseat = previousseat;
	}

	public String getSeatname() {
		return seatname;
	}



	public void setSeatname(String seatname) {
		this.seatname = seatname;
	}



	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	
	public String getModereferral() {
		return modereferral;
	}
	public void setModereferral(String modereferral) {
		this.modereferral = modereferral;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getContactperson() {
		return contactperson;
	}
	public void setContactperson(String contactperson) {
		this.contactperson = contactperson;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		System.out.println("day "+day);
		this.day = day;
	}
	public String getRegNo() {
		return regNo;
	}
	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAdmissiond_t() {
		return admissiond_t;
	}
	public void setAdmissiond_t(String admissiond_t) {
		this.admissiond_t = admissiond_t;
	}
	public String getSeatrate() {
		return seatrate;
	}
	public void setSeatrate(String seatrate) {
		this.seatrate = seatrate;
	}

	public String getPatientname() {
		return patientname;
	}
	public void setPatientname(String patientname) {
		this.patientname = patientname;
	}
	public String getFathername() {
		return fathername;
	}
	public void setFathername(String fathername) {
		this.fathername = fathername;
	}
	public String getHusbandname() {
		return husbandname;
	}
	public void setHusbandname(String husbandname) {
		this.husbandname = husbandname;
	}
	public String getReligion() {
		return religion;
	}
	public void setReligion(String religion) {
		this.religion = religion;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getNationalId() {
		return nationalId;
	}
	public void setNationalId(String nationalId) {
		this.nationalId = nationalId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getAdmissionfee() {
		return admissionfee;
	}
	public void setAdmissionfee(String admissionfee) {
		this.admissionfee = admissionfee;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getAdmissiontype() {
		return admissiontype;
	}
	public void setAdmissiontype(String admissiontype) {
		this.admissiontype = admissiontype;
	}

	public String getConsultantId() {
		return consultantId;
	}

	public void setConsultantId(String consultantId) {
		this.consultantId = consultantId;
	}



	public String getRefferId() {
		return refferId;
	}

	public void setRefferId(String refferId) {
		this.refferId = refferId;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getSeatId() {
		return seatId;
	}

	public void setSeatId(String seatId) {
		this.seatId = seatId;
	}


	public String getPatientmode() {
		return patientmode;
	}

	public void setPatientmode(String patientmode) {
		this.patientmode = patientmode;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getRefferDegree() {
		return refferDegree;
	}

	public void setRefferDegree(String refferDegree) {
		this.refferDegree = refferDegree;
	}

	public String getConsultantDegree() {
		return consultantDegree;
	}

	public void setConsultantDegree(String consultantDegree) {
		this.consultantDegree = consultantDegree;
	}

	public String getPatientfiscalyear() {
		return patientfiscalyear;
	}

	public void setPatientfiscalyear(String patientfiscalyear) {
		this.patientfiscalyear = patientfiscalyear;
	}

	public String getFiscalyear() {
		return fiscalyear;
	}

	public void setFiscalyear(String fiscalyear) {
		this.fiscalyear = fiscalyear;
	}

	
}
