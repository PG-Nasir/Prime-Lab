package pg.LabModel;

public class SubTest {

	String subTestId;
	String subTestName;
	String testName;
	String unit;
	String testId;
	String parentTest;
	String calculate;
	String normalRange;
	String userId;
	String sorting;
	public SubTest() {
		
	}
	
	
	public SubTest(String subTestId, String subTestName,String calculate, String testId, String testName, String unit, String normalRange,String Sorting) {
		this.subTestId = subTestId;
		this.subTestName = subTestName;
		this.calculate=calculate;
		this.testId=testId;
		this.testName = testName;
		this.unit = unit;
		this.normalRange = normalRange;
		this.sorting = Sorting;
	}


	public String getSubTestId() {
		return subTestId;
	}


	public void setSubTestId(String subTestId) {
		this.subTestId = subTestId;
	}


	public String getSubTestName() {
		return subTestName;
	}


	public void setSubTestName(String subTestName) {
		this.subTestName = subTestName;
	}


	public String getTestName() {
		return testName;
	}


	public void setTestName(String testName) {
		this.testName = testName;
	}


	public String getUnit() {
		return unit;
	}


	public void setUnit(String unit) {
		this.unit = unit;
	}


	public String getTestId() {
		return testId;
	}


	public void setTestId(String testId) {
		this.testId = testId;
	}


	public String getParentTest() {
		return parentTest;
	}


	public void setParentTest(String parentTest) {
		this.parentTest = parentTest;
	}


	public String getCalculate() {
		return calculate;
	}


	public void setCalculate(String calculate) {
		this.calculate = calculate;
	}


	public String getNormalRange() {
		return normalRange;
	}


	public void setNormalRange(String normalRange) {
		this.normalRange = normalRange;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getSorting() {
		return sorting;
	}


	public void setSorting(String sorting) {
		this.sorting = sorting;
	}

	
}
