package pg.AccountsModel;

public class TrialBalance {
	String headTitle="";
	String reference="";
	String beforeDebit="";
	String beforeCredit="";
	String currentDebit="";
	String currentCredit="";
	String startDate="";
	String endDate="";
	
	
	
	public TrialBalance(String headTitle, String reference, String beforeDebit, String beforeCredit,
			String currentDebit, String currentCredit, String startDate, String endDate) {
		super();
		this.headTitle = headTitle;
		this.reference = reference;
		this.beforeDebit = beforeDebit;
		this.beforeCredit = beforeCredit;
		this.currentDebit = currentDebit;
		this.currentCredit = currentCredit;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	
	public String getHeadTitle() {
		return headTitle;
	}
	public void setHeadTitle(String headTitle) {
		this.headTitle = headTitle;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public String getBeforeDebit() {
		return beforeDebit;
	}
	public void setBeforeDebit(String beforeDebit) {
		this.beforeDebit = beforeDebit;
	}
	public String getBeforeCredit() {
		return beforeCredit;
	}
	public void setBeforeCredit(String beforeCredit) {
		this.beforeCredit = beforeCredit;
	}
	public String getCurrentDebit() {
		return currentDebit;
	}
	public void setCurrentDebit(String currentDebit) {
		this.currentDebit = currentDebit;
	}
	public String getCurrentCredit() {
		return currentCredit;
	}
	public void setCurrentCredit(String currentCredit) {
		this.currentCredit = currentCredit;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	
	
}
