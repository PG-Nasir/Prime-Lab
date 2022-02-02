package pg.LabModel;

public class Test {

	String userId;
	String headId;
	String headName;
	String testName;
	String testCode;
	String rate;
	String unit;
	String normalRange;
	String discountAllow;
	String testId;
	String calculativeType;
	String subTestId;
	String doctorCommission;

	String subtestname;
	String particular;
	String particularId;
	String qty;
	String sorting;
	
	public Test() {
		
	}
	
	public Test(String TestName,String TestId,String Rate) {
		this.testId = TestId;
		this.testName = TestName;
		this.rate = Rate;
	}
	
	
	public Test(String SubTestId,String TestName,String SubTestName,String Unit,String NormalRange,String Sorting) {
		this.subTestId=SubTestId;
		this.particularId=SubTestId;
		
		this.testName=TestName;

		this.subtestname=SubTestName;
		this.particular=SubTestName;
		
		
		this.unit=Unit;
		this.rate=Unit;
		
		this.normalRange=NormalRange;
		this.qty=NormalRange;
		this.sorting=Sorting;
	}
	
	public Test(String TestId, String headName,String headId, String testName, String rate, String doctorComission, String unit,
			String normalRange,String discountAllow) {
		this.testId = TestId;
		this.headName = headName;
		this.testName = testName;
		this.headId = headId;
		this.rate = rate;
		this.doctorCommission = doctorComission;
		this.unit = unit;
		this.normalRange = normalRange;
		this.discountAllow = discountAllow;
	}
	

	
	public Test(String testId, String testName) {
		this.testId = testId;
		this.testName = testName;
	}

	public String getParticularId() {
		return particularId;
	}

	public void setParticularId(String particularId) {
		this.particularId = particularId;
	}

	public String getQty() {
		return qty;
	}

	public void setQty(String qty) {
		this.qty = qty;
	}

	public String getSubTestId() {
		return subTestId;
	}

	public void setSubTestId(String subTestId) {
		this.subTestId = subTestId;
	}

	public String getParticular() {
		return particular;
	}

	public void setParticular(String particular) {
		this.particular = particular;
	}

	public String getCalculativeType() {
		return calculativeType;
	}

	public void setCalculativeType(String calculativeType) {
		this.calculativeType = calculativeType;
	}

	public String getSubtestname() {
		return subtestname;
	}

	public void setSubtestname(String subtestname) {
		this.subtestname = subtestname;
	}

	public String getTestId() {
		return testId;
	}

	public void setTestId(String testId) {
		this.testId = testId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getDiscountAllow() {
		return discountAllow;
	}

	public void setDiscountAllow(String discountAllow) {
		this.discountAllow = discountAllow;
	}

	public String getTestCode() {
		return testCode;
	}

	public void setTestCode(String testcode) {
		this.testCode = testcode;
	}

	public String getHeadId() {
		return headId;
	}
	public void setHeadId(String headId) {
		this.headId = headId;
	}
	public String getHeadName() {
		return headName;
	}
	public void setHeadName(String headName) {
		this.headName = headName;
	}
	public String getTestName() {
		return testName;
	}
	public void setTestName(String testName) {
		this.testName = testName;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}

	
	
	public String getDoctorCommission() {
		return doctorCommission;
	}

	public void setDoctorCommission(String doctorCommission) {
		this.doctorCommission = doctorCommission;
	}

	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getNormalRange() {
		return normalRange;
	}
	public void setNormalRange(String normalRange) {
		this.normalRange = normalRange;
	}

	public String getSorting() {
		return sorting;
	}

	public void setSorting(String sorting) {
		this.sorting = sorting;
	}
	
	
}
