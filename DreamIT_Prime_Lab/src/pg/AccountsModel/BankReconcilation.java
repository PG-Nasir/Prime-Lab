package pg.AccountsModel;

public class BankReconcilation {

	String userId;
	String particularId;
	String particularName;
	String resultList;
	String date;
	String monthName;
	String fiscalYear;
	public BankReconcilation() {
		
	}

	public BankReconcilation(String ParticularId, String ParticularName) {
		// TODO Auto-generated constructor stub
		this.particularId=ParticularId;
		this.particularName=ParticularName;
	}

	public BankReconcilation(String MONTH, String FiscalYear, String string3) {
		this.monthName=MONTH;
		this.fiscalYear=FiscalYear;
	}

	public String getParticularId() {
		return particularId;
	}

	public void setParticularId(String particularId) {
		this.particularId = particularId;
	}

	public String getParticularName() {
		return particularName;
	}

	public void setParticularName(String particularName) {
		this.particularName = particularName;
	}

	public String getResultList() {
		return resultList;
	}

	public void setResultList(String resultList) {
		this.resultList = resultList;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getMonthName() {
		return monthName;
	}

	public void setMonthName(String monthName) {
		this.monthName = monthName;
	}

	public String getFiscalYear() {
		return fiscalYear;
	}

	public void setFiscalYear(String fiscalYear) {
		this.fiscalYear = fiscalYear;
	}
	
	
	
}
