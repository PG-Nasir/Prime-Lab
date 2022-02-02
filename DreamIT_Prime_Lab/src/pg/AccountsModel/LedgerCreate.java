package pg.AccountsModel;

public class LedgerCreate {
	String headId="";
	String headName="";
	String ledgerId="";
	String ledgerName="";
	String remark="";
	String userId="";
	String unitId="";
	String depId="";
	String openingBalance="";
	String reference="";
	public LedgerCreate() {
		
	}
	
	
	
	public LedgerCreate(String headId, String headName, String ledgerId, String ledgerName, String remark,
			String userId, String unitId, String depId) {

		this.headId = headId;
		this.headName = headName;
		this.ledgerId = ledgerId;
		this.ledgerName = ledgerName;
		this.remark = remark;
		this.userId = userId;
		this.unitId = unitId;
		this.depId = depId;
	}



	public LedgerCreate(String LedgerId, String LedgerTitle, String pHeadId, String HeadName, String OpeningBalance,String reference) {
		this.ledgerId=LedgerId;
		this.ledgerName=LedgerTitle;
		this.headId=pHeadId;
		this.headName=HeadName;
		this.openingBalance=OpeningBalance;
		this.reference=reference;
	}



	public LedgerCreate(String LedgerId, String LedgerTitle) {
		this.ledgerId=LedgerId;
		this.ledgerName=LedgerTitle;
	}



	public String getHeadId() {
		return headId;
	}
	public void setHeadId(String headId) {
		this.headId = headId;
	}
	public String getHeadName() {
		return headName;
	}
	public void setHeadName(String headName) {
		this.headName = headName;
	}
	public String getLedgerId() {
		return ledgerId;
	}
	public void setLedgerId(String ledgerId) {
		this.ledgerId = ledgerId;
	}
	public String getLedgerName() {
		return ledgerName;
	}
	public void setLedgerName(String ledgerName) {
		this.ledgerName = ledgerName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUnitId() {
		return unitId;
	}
	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}
	public String getDepId() {
		return depId;
	}
	public void setDepId(String depId) {
		this.depId = depId;
	}



	public String getOpeningBalance() {
		return openingBalance;
	}



	public void setOpeningBalance(String openingBalance) {
		this.openingBalance = openingBalance;
	}



	public String getReference() {
		return reference;
	}



	public void setReference(String reference) {
		this.reference = reference;
	}
	
	
	
}
