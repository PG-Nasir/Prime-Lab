package pg.AccountsModel;

public class Voucher {
	String voucherNo="";
	String ledgerId="";
	String date="";
	String costCenterId="";
	String userId="";
	String resultList="";
	String standBy="";
	String type="";
	String accountType="";
	String chequeNumber="";
	String chequeDate="";
	String paymentType="";
	String amount="";
	String status="";
	String voucherType="";
	String valid="";
	String approveBy="";
	String approveAt="";
	
	String debitLegder="";
	String creditLedger="";
	String costCenterName="";
	String userName="";
	String bookType="";
	String transactionType="";
	String debitresultList="";
	String creditresultList="";
	
	public Voucher() {
		
	}
	public Voucher(String voucherNo, String type, String PaymentType, String Date, String Amount,String Status,String s) {

		this.voucherNo = voucherNo;
		
		this.accountType = type;
		this.paymentType=PaymentType;
		this.date = Date;
		this.amount = Amount;
		this.status = Status;


	}
	
	public Voucher(String voucherNo, String type, String PaymentType, String Date, String Amount,String Status,String approveBy,String approveAt) {

		this.voucherNo = voucherNo;
		
		this.accountType = type;
		this.paymentType=PaymentType;
		this.date = Date;
		this.amount = Amount;
		this.status = Status;
		this.approveBy=approveBy;
		this.approveAt=approveAt;

	}
	
	
	
	public Voucher(String voucherNo, String ledgerId, String date, String costCenterId, String userId,
			String resultList) {

		this.voucherNo = voucherNo;
		this.ledgerId = ledgerId;
		this.date = date;
		this.costCenterId = costCenterId;
		this.userId = userId;
		this.resultList = resultList;
	}
	
	public Voucher(String voucherNo, String type, String paymentType, String Date, String Amount,
			String chequeDate,String chequeNumber,String s1,String s2,String s3,String s4) {

		this.voucherNo = voucherNo;
		this.accountType = type;
		this.paymentType = paymentType;
		this.date = Date;
		this.amount = Amount;
		this.chequeDate = chequeDate;
		this.chequeNumber = chequeNumber;
	}
	
	
	public Voucher(String chequeNo, String voucherNo, String type, String amount, String chequeDate, String chequePass,
			String string6, String string7, String string8, String string9) {
		this.voucherNo=voucherNo;
		this.chequeNumber=chequeNo;
		this.accountType=type;
		this.amount=amount;
		this.chequeDate=chequeDate;
		this.status=chequePass;
	}
	public Voucher(String Type, String VoucherNo, String VoucherType, String DebitLedger, String CreditLedger, String Amount,
			String CostCenterName, String Date, String UserName) {
		this.type=Type;
		this.voucherNo=VoucherNo;
		this.voucherType=VoucherType;
		this.debitLegder=DebitLedger;
		this.creditLedger=CreditLedger;
		this.amount=Amount;
		this.costCenterName=CostCenterName;
		this.date=Date;
		this.userName=UserName;
	}
	public Voucher(String Type, String VoucherNo, String Date, String Amount, String DebitLedger, String CreditLedger,
			String BookType, String TransactionType, String UserId, String Description, String string11, String string12) {
		this.type=Type;
		this.voucherNo=VoucherNo;
		this.date=Date;
		this.amount=Amount;
		this.debitLegder=DebitLedger;
		this.creditLedger=CreditLedger;
		this.bookType=BookType;
		this.transactionType=TransactionType;
		
	}
	public String getVoucherNo() {
		return voucherNo;
	}
	public void setVoucherNo(String voucherNo) {
		this.voucherNo = voucherNo;
	}

	
	public String getLedgerId() {
		return ledgerId;
	}


	public void setLedgerId(String ledgerId) {
		this.ledgerId = ledgerId;
	}


	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getCostCenterId() {
		return costCenterId;
	}
	public void setCostCenterId(String costCenterId) {
		this.costCenterId = costCenterId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getResultList() {
		return resultList;
	}
	public void setResultList(String resultList) {
		this.resultList = resultList;
	}

	public String getStandBy() {
		return standBy;
	}

	public void setStandBy(String standBy) {
		this.standBy = standBy;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getChequeNumber() {
		return chequeNumber;
	}

	public void setChequeNumber(String chequeNumber) {
		this.chequeNumber = chequeNumber;
	}

	public String getChequeDate() {
		return chequeDate;
	}

	public void setChequeDate(String chequeDate) {
		this.chequeDate = chequeDate;
	}


	public String getPaymentType() {
		return paymentType;
	}


	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}


	public String getAmount() {
		return amount;
	}


	public void setAmount(String amount) {
		this.amount = amount;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getVoucherType() {
		return voucherType;
	}


	public void setVoucherType(String voucherType) {
		this.voucherType = voucherType;
	}


	public String getValid() {
		return valid;
	}


	public void setValid(String valid) {
		this.valid = valid;
	}
	public String getApproveBy() {
		return approveBy;
	}
	public void setApproveBy(String approveBy) {
		this.approveBy = approveBy;
	}
	public String getApproveAt() {
		return approveAt;
	}
	public void setApproveAt(String approveAt) {
		this.approveAt = approveAt;
	}
	public String getDebitLegder() {
		return debitLegder;
	}
	public void setDebitLegder(String debitLegder) {
		this.debitLegder = debitLegder;
	}
	public String getCreditLedger() {
		return creditLedger;
	}
	public void setCreditLedger(String creditLedger) {
		this.creditLedger = creditLedger;
	}
	public String getCostCenterName() {
		return costCenterName;
	}
	public void setCostCenterName(String costCenterName) {
		this.costCenterName = costCenterName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getBookType() {
		return bookType;
	}
	public void setBookType(String bookType) {
		this.bookType = bookType;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public String getDebitresultList() {
		return debitresultList;
	}
	public void setDebitresultList(String debitresultList) {
		this.debitresultList = debitresultList;
	}
	public String getCreditresultList() {
		return creditresultList;
	}
	public void setCreditresultList(String creditresultList) {
		this.creditresultList = creditresultList;
	}
	
	
}
