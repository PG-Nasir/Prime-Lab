package pg.AccountsModel;

public class ChequeBookCreate {
	String ChequeId="";
	String userId="";
	String ledgerId="";
	String ledgerTitle="";
	String bookNoPrefix="";
	String chequeBookNo="";
	String fromChequeNo="";
	String toChequeNo="";
	String totalChequeNo="";
	String hiddenchequeBookNo="";
	String hiddenLedgerId="";
	
	public ChequeBookCreate(){
		
	}
	
	
	public ChequeBookCreate(String ledgerId,String bookNoPrefix, String chequeBookNo,
			String fromChequeNo, String toChequeNo) {
		this.ledgerId = ledgerId;
		this.bookNoPrefix = bookNoPrefix;
		this.chequeBookNo = chequeBookNo;
		this.fromChequeNo = fromChequeNo;
		this.toChequeNo = toChequeNo;
	}
	
	public ChequeBookCreate(String ledgerId,String LedgerTitle,String bookNoPrefix, String chequeBookNo,
			String fromChequeNo, String toChequeNo) {
		this.ledgerId = ledgerId;
		this.ledgerTitle = LedgerTitle;
		this.bookNoPrefix = bookNoPrefix;
		this.chequeBookNo = chequeBookNo;
		this.fromChequeNo = fromChequeNo;
		this.toChequeNo = toChequeNo;
	}
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getLedgerId() {
		return ledgerId;
	}
	public void setLedgerId(String ledgerId) {
		this.ledgerId = ledgerId;
	}
	public String getBookNoPrefix() {
		return bookNoPrefix;
	}
	public void setBookNoPrefix(String bookNoPrefix) {
		this.bookNoPrefix = bookNoPrefix;
	}
	public String getChequeBookNo() {
		return chequeBookNo;
	}
	public void setChequeBookNo(String chequeBookNo) {
		this.chequeBookNo = chequeBookNo;
	}
	public String getFromChequeNo() {
		return fromChequeNo;
	}
	public void setFromChequeNo(String fromChequeNo) {
		this.fromChequeNo = fromChequeNo;
	}
	public String getTotalChequeNo() {
		return totalChequeNo;
	}
	public void setTotalChequeNo(String totalChequeNo) {
		this.totalChequeNo = totalChequeNo;
	}

	public String getChequeId() {
		return ChequeId;
	}

	public void setChequeId(String chequeId) {
		ChequeId = chequeId;
	}


	public String getLedgerTitle() {
		return ledgerTitle;
	}


	public void setLedgerTitle(String ledgerTitle) {
		this.ledgerTitle = ledgerTitle;
	}


	public String getToChequeNo() {
		return toChequeNo;
	}


	public void setToChequeNo(String toChequeNo) {
		this.toChequeNo = toChequeNo;
	}


	public String getHiddenchequeBookNo() {
		return hiddenchequeBookNo;
	}


	public void setHiddenchequeBookNo(String hiddenchequeBookNo) {
		this.hiddenchequeBookNo = hiddenchequeBookNo;
	}


	public String getHiddenLedgerId() {
		return hiddenLedgerId;
	}


	public void setHiddenLedgerId(String hiddenLedgerId) {
		this.hiddenLedgerId = hiddenLedgerId;
	}
	
	
}
