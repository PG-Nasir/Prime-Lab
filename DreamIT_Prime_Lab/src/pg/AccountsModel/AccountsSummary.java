package pg.AccountsModel;

public class AccountsSummary {
	String headName="";
	String debit="";
	String credit="";
	
	public AccountsSummary() {
		
	}
	
	

	public AccountsSummary(String HeadTitle, String Debit, String Credit) {
		this.headName=HeadTitle;
		this.debit=Debit;
		this.credit=Credit;
	}



	public String getHeadName() {
		return headName;
	}

	public void setHeadName(String headName) {
		this.headName = headName;
	}



	public String getDebit() {
		return debit;
	}



	public void setDebit(String debit) {
		this.debit = debit;
	}



	public String getCredit() {
		return credit;
	}



	public void setCredit(String credit) {
		this.credit = credit;
	}


}
