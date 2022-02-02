package pg.LabModel;

public class TestWiseParticularName {

	String testId;
	String testName;
	String particularId;
	String particularRefId;
	String particularMainName;
	String rate;
	String qty;
	String userId;
	
	public TestWiseParticularName() {
		
	}

	public TestWiseParticularName(String testId, String testName, String particularId, String particularRefId,String particularMainName,
			String rate, String qty) {
		this.testId = testId;
		this.testName = testName;
		this.particularId = particularId;
		this.particularRefId = particularRefId;
		this.particularMainName=particularMainName;
		this.rate = rate;
		this.qty = qty;
	}

	public String getTestId() {
		return testId;
	}

	public void setTestId(String testId) {
		this.testId = testId;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public String getParticularId() {
		return particularId;
	}

	public void setParticularId(String particularId) {
		this.particularId = particularId;
	}


	public String getParticularRefId() {
		return particularRefId;
	}

	public void setParticularRefId(String particularRefId) {
		this.particularRefId = particularRefId;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getQty() {
		return qty;
	}

	public void setQty(String qty) {
		this.qty = qty;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getParticularMainName() {
		return particularMainName;
	}

	public void setParticularMainName(String particularMainName) {
		this.particularMainName = particularMainName;
	}
}
