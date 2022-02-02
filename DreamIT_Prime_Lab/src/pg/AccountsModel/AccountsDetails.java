package pg.AccountsModel;

public class AccountsDetails {
	String headTitle;
	String plAmount;
	String headType;
	String debit;
	String ob;
	String credit;
	String closing;
	String ledgerId;
	
	public AccountsDetails(String headTitle, String taka,String type) {
		super();
		this.headTitle = headTitle;
		this.plAmount = taka;
		this.headType=type;
	}
	
	public AccountsDetails(String HeadTitle, String OpenigBalance, String Credit, String Debit, String HeadType) {
		this.headTitle=HeadTitle;
		this.ob=OpenigBalance;
		this.credit=Credit;
		this.debit=Debit;
		this.headType=HeadType;
		
		if(headType.equals("Revenue") || headType.equals("Liability")) {
			double cl=(Double.parseDouble(ob)+Double.parseDouble(credit))-Double.parseDouble(debit);
			this.closing=Double.toString(cl);
		}
		else {
			double cl=(Double.parseDouble(ob)+Double.parseDouble(debit))-Double.parseDouble(credit);
			this.closing=Double.toString(cl);
		}
	}
	
	public AccountsDetails(String HeadTitle,String LedgerId, String OpenigBalance, String Credit, String Debit, String HeadType) {
		this.headTitle=HeadTitle;
		this.ledgerId=LedgerId;
		this.ob=OpenigBalance;
		this.credit=Credit;
		this.debit=Debit;
		this.headType=HeadType;
		
		if(headType.equals("Revenue")) {
			double cl=(Double.parseDouble(ob)+Double.parseDouble(credit))-Double.parseDouble(debit);
			this.closing=Double.toString(cl);
		}
		else {
			double cl=(Double.parseDouble(ob)+Double.parseDouble(debit))-Double.parseDouble(credit);
			this.closing=Double.toString(cl);
		}
	}

	public String getHeadTitle() {
		return headTitle;
	}
	public void setHeadTitle(String headTitle) {
		this.headTitle = headTitle;
	}
	public String getPlAmount() {
		return plAmount;
	}
	public void setPlAmount(String plAmount) {
		this.plAmount = plAmount;
	}

	public String getHeadType() {
		return headType;
	}

	public void setHeadType(String headType) {
		this.headType = headType;
	}

	public String getDebit() {
		return debit;
	}

	public void setDebit(String debit) {
		this.debit = debit;
	}

	public String getOb() {
		return ob;
	}

	public void setOb(String ob) {
		this.ob = ob;
	}

	public String getCredit() {
		return credit;
	}

	public void setCredit(String credit) {
		this.credit = credit;
	}

	public String getClosing() {
		return closing;
	}

	public void setClosing(String closing) {
		this.closing = closing;
	}

	public String getLedgerId() {
		return ledgerId;
	}

	public void setLedgerId(String ledgerId) {
		this.ledgerId = ledgerId;
	}

	
}
