package pg.ChequeWriter;

public class ChequeWriter {
	String bankId="";
	String bankName="";
	String payTo="";
	String date="";
	String amount="";
	String userId="";
	String printCategory="";
	String writerId="";
	String userName="";
	String reportName="";
	
	public ChequeWriter() {
		
	}
	
	public ChequeWriter(String BankId,String BankName) {
		this.bankId=BankId;
		this.bankName=BankName;
	}
	
	public ChequeWriter(String WriterId,String BankId,String PayTo,String Amount,String Date,String Type) {
		this.writerId=WriterId;
		this.bankId=BankId;
		this.payTo=PayTo;
		this.amount=Amount;
		this.date=Date;
		this.printCategory=Type;
	}
	
	public ChequeWriter(String WriterId,String BankName,String PayTo,String Amount,String Date,String Type,String UserName) {
		this.writerId=WriterId;
		this.bankName=BankName;
		this.payTo=PayTo;
		this.amount=Amount;
		this.date=Date;
		this.printCategory=Type;
		this.userName=UserName;
	}
	
	public String getBankId() {
		return bankId;
	}
	public void setBankId(String bankId) {
		this.bankId = bankId;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getPayTo() {
		return payTo;
	}
	public void setPayTo(String payTo) {
		this.payTo = payTo;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPrintCategory() {
		return printCategory;
	}

	public void setPrintCategory(String printCategory) {
		this.printCategory = printCategory;
	}

	public String getWriterId() {
		return writerId;
	}

	public void setWriterId(String writerId) {
		this.writerId = writerId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}
	
	
	
}
