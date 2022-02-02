package pg.AccountsModel;

public class DailyStatement {

	String category="";
	String salesAmount="";
	String discountAmount="";
	String collectionAmount="";
	String dueAmount="";
	String type="";
	
	String ledgerId="";
	String ledgerTitle="";
	String amount="";
	String depId="";
	String transactionType="";
	
	public DailyStatement() {
		
	}
	
	

	public DailyStatement(String category,String Type, String salesAmount, String discountAmount, String collectionAmount,
			String dueAmount) {
		super();
		this.category = category;
		this.type=Type;
		this.salesAmount = salesAmount;
		this.discountAmount = discountAmount;
		this.collectionAmount = collectionAmount;
		this.dueAmount = dueAmount;
	}



	public DailyStatement(String LedgerId, String LedgerTitle, String Amount,String DepId,String TransactionType) {
		// TODO Auto-generated constructor stub
		this.ledgerId=LedgerId;
		this.ledgerTitle=LedgerTitle;
		this.amount=Amount;
		this.depId=DepId;
		this.transactionType=TransactionType;
	}



	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSalesAmount() {
		return salesAmount;
	}

	public void setSalesAmount(String salesAmount) {
		this.salesAmount = salesAmount;
	}

	public String getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(String discountAmount) {
		this.discountAmount = discountAmount;
	}

	public String getCollectionAmount() {
		return collectionAmount;
	}

	public void setCollectionAmount(String collectionAmount) {
		this.collectionAmount = collectionAmount;
	}

	public String getDueAmount() {
		return dueAmount;
	}

	public void setDueAmount(String dueAmount) {
		this.dueAmount = dueAmount;
	}



	public String getType() {
		return type;
	}



	public void setType(String type) {
		this.type = type;
	}



	public String getLedgerId() {
		return ledgerId;
	}



	public void setLedgerId(String ledgerId) {
		this.ledgerId = ledgerId;
	}



	public String getLedgerTitle() {
		return ledgerTitle;
	}



	public void setLedgerTitle(String ledgerTitle) {
		this.ledgerTitle = ledgerTitle;
	}



	public String getAmount() {
		return amount;
	}



	public void setAmount(String amount) {
		this.amount = amount;
	}



	public String getDepId() {
		return depId;
	}



	public void setDepId(String depId) {
		this.depId = depId;
	}



	public String getTransactionType() {
		return transactionType;
	}



	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	
	
	
}
