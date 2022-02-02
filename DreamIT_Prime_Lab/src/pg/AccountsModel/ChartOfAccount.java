package pg.AccountsModel;

public class ChartOfAccount {

	String headId="";
	String pHeadId="";
	String headTitle="";
	
	public ChartOfAccount() {
		
	}
	
	public ChartOfAccount(String headId, String headTitle, String pHeadId) {
		this.headId=headId;
		this.headTitle=headTitle;
		this.pHeadId=pHeadId;
	}

	public String getHeadId() {
		return headId;
	}

	public void setHeadId(String headId) {
		this.headId = headId;
	}



	public String getpHeadId() {
		return pHeadId;
	}

	public void setpHeadId(String pHeadId) {
		this.pHeadId = pHeadId;
	}

	public String getHeadTitle() {
		return headTitle;
	}

	public void setHeadTitle(String headTitle) {
		this.headTitle = headTitle;
	}


	

}
