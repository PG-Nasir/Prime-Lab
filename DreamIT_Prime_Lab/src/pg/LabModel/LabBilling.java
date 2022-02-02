package pg.LabModel;

public class LabBilling {

	String userId;
	String counter;
	String testId;
	String patientname;
	String mobile;
	String age;
	String month;
	String day;
	String address;
	String referral_search;
	String referralId;
	String bedcabin;
	String referraldegree;
	String referralcomission;
	String referralcId;
	String referralcomissiondegree;
	String billType;
	String regno;
	String find;
	String fiscalyear;
	String cMonth;
	String sex;
	String percentdiscount;
	String manualdiscount;
	String labId;
	String testname;
	String headId;
	String qty;
	String rate;
	String period;
	String testType="";
	String referralcdegree;

	
	String discountAmount;
	String payable;
	
	String totalamount;
	String paid;
	String dues;
	String advance;
	String perdiscount_taka;
	String mdiscount_tata;
	String totalpayable;
	String refund;
	String deliverydatetime;
	
	String patientfiscalyear;
	String billdate;
	String startdate;
	String endate;
	String username;
	String totaldiscount;
	
	String paymentstatus;
	String billstatus;
	String amountreceived;
	String receivedtime;
	
	String deptitle;
	String groupname;
	String amount;
	String headName;
	String resultstatus;
	
	String RefferName;
	String RefferDegree;
	String LabBill;
	String PatientName;
	String PathologyRate;
	String PathologyNetAmount;
	String HormoneRate;
	String HormoneNetAmount;
	String EchoCardRate;
	String EchoCardNetAmount;
	String UltraSonoRate;
	String UltraSonoNetAmount;
	String EnDosRate;
	String EnDosNetAmount;
	String XrayRate;
	String XrayNetAmount;
	String ECGRate;
	String ECGNetAmount;
	String HistopathologyRate;
	String HistopathologyNetAmount;
	String BloodGroupRate;
	String BloodGroupNetAmount;
	String FNARate;
	String FNANetAmount;
	String PepsSemarRate;
	String PepsSemarNetAmount;
	String OthersRate;
	String OthersNetAmount;
	String DoctorDiscount;
	//String ManualDiscount;
	String PerticularCharge;
	String TotalCharge;
	String TotalPaid;
	String Due;
	String Date;
	String StartDate;
	String EndDate;
	String resultList="";
	String autoId="";
	String refferCId="";
	String remark="";
	String extraCommission="";

	public LabBilling() {
		
	}
	
	//Referral Id Wise Comission Statement
	public LabBilling(String RefferName,String RefferDegree,String LabBill,String PatientName,String PathologyRate,String PathologyNetAmount,String HormoneRate,String HormoneNetAmount,String EchoCardRate,String EchoCardNetAmount,String UltraSonoRate,String UltraSonoNetAmount,String EnDosRate,String EnDosNetAmount,String XrayRate,String XrayNetAmount,String ECGRate,String ECGNetAmount,String HistopathologyRate,String HistopathologyNetAmount,String BloodGroupRate,String BloodGroupNetAmount,String FNARate,String FNANetAmount,String PepsSemarRate,String PepsSemarNetAmount,String OthersRate,String OthersNetAmount,String ManualDiscount,String DoctorDiscount,String TotalCharge,String PerticularCharge,String TotalPaid,String Due,String Date,String StartDate,String EndDate) {
		this.RefferName=RefferName;
		this.RefferDegree=RefferDegree;
		this.LabBill=LabBill;
		this.PatientName=PatientName;
		this.PathologyRate=PathologyRate;
		this.PathologyNetAmount=PathologyNetAmount;
		this.HormoneRate=HormoneRate;
		this.HormoneNetAmount=HormoneNetAmount;
		this.EchoCardRate=EchoCardRate;
		this.EchoCardNetAmount=EchoCardNetAmount;
		this.UltraSonoRate=UltraSonoRate;
		this.UltraSonoNetAmount=UltraSonoNetAmount;
		this.EnDosRate=EnDosRate;
		this.EnDosNetAmount=EnDosNetAmount;
		this.XrayRate=XrayRate;
		this.XrayNetAmount=XrayNetAmount;
		this.ECGRate=ECGRate;
		this.ECGNetAmount=ECGNetAmount;
		this.HistopathologyRate=HistopathologyRate;
		this.HistopathologyNetAmount=HistopathologyNetAmount;
		this.BloodGroupRate=BloodGroupRate;
		this.BloodGroupNetAmount=BloodGroupNetAmount;
		this.FNARate=FNARate;
		this.FNANetAmount=FNANetAmount;
		this.PepsSemarRate=PepsSemarRate;
		this.PepsSemarNetAmount=PepsSemarNetAmount;
		this.OthersRate=OthersRate;
		this.OthersNetAmount=OthersNetAmount;
		this.manualdiscount=ManualDiscount;
		this.DoctorDiscount=DoctorDiscount;
		this.TotalCharge=TotalCharge;
		this.PerticularCharge=PerticularCharge;
		this.TotalPaid=TotalPaid;
		this.Due=Due;
		this.Date=Date;
		this.StartDate=StartDate;
		this.EndDate=EndDate;
	}
	
	public LabBilling(String LabId,String PatientName,String Mobile,String Date,String RefferDegree,String FiscalYear,String CMonth) {
		this.labId=LabId;
		this.patientname=PatientName;
		this.mobile=Mobile;
		this.billdate=Date;
		this.referralcdegree=RefferDegree;
		this.fiscalyear=FiscalYear;
		this.cMonth=CMonth;
	}
	
	public LabBilling(String LabId,String PatientName,String Mobile,String FiscalYear) {
		this.labId=LabId;
		this.patientname=PatientName;
		this.mobile=Mobile;
		this.fiscalyear=FiscalYear;
	}
	
	public LabBilling(String type,String TestId, String TestName,String Qty,String Rate,String Discount, String DiscountAmount,String Payable,String Counter,String s) {
		this.testType = type;
		this.testId = TestId;
		this.testname = TestName;
		this.qty = Qty;
		this.rate = Rate;
		this.discountAmount = DiscountAmount;
		this.payable=Payable;
		this.counter = Counter;
	}

	
	//Counter Search
	public LabBilling(String Type,String TestId, String TestName, String Qty, String Rate, String Discount, String DiscountAmount,
			String Payable, String BillType, String Counter, String RegNo, String PatientName, String Mobile,
			String Age, String Month, String Day, String Sex, String Cabin, String Address,
			String ReferralComission,String referralCDegree,String RefferCId,String Referral,String referralDegree, String ReferralId, String PercentDiscount, String ManualDiscount) {
		
		this.testType = Type;
		this.testId = TestId;
		this.testname = TestName;
		this.qty = Qty;
		this.rate = Rate;
		this.discountAmount = DiscountAmount;
		this.payable=Payable;
		this.counter = Counter;
		this.billType = BillType;
		
		this.regno = RegNo;
		this.patientname = PatientName;
		this.mobile = Mobile;
		this.age = Age;
		this.month = Month;
		this.day = Day;
		this.sex = Sex;
		this.bedcabin = Cabin;
		this.address = Address;
		this.referralcomission =ReferralComission;
		this.referral_search = Referral;
		this.percentdiscount = PercentDiscount;
		this.manualdiscount = ManualDiscount;
		
		this.referralcId=RefferCId;
		this.referralId=ReferralId;
		
		this.referralcdegree=referralCDegree;
		this.referraldegree=referralDegree;

	}

	
	//Lab Bill Investigation
	public LabBilling(String PatientName,String Mobile,String Age,String Month,String Day,String Sex,String RefferDcotor,String RefferDegree,String TestId,String TestName,String HeadId,String HeadName,String ResultStatus,String LabId,String FiscalYear) {
		
		this.patientname=PatientName;
		this.mobile=Mobile;
		this.age=Age;
		this.month=Month;
		this.day=Day;
		this.sex=Sex;
		this.referral_search=RefferDcotor;
		this.referraldegree=RefferDegree;
		this.testId=TestId;
		this.testname=TestName;
		this.headId=HeadId;
		this.headName=HeadName;
		if(ResultStatus.equals("0")) {
			this.resultstatus="Pending";
		}
		else {
			this.resultstatus="Done";
		}
	
		this.labId=LabId;
		this.fiscalyear=FiscalYear;

	}
	
	//Lab Id Search
	public LabBilling(String Paid,String Refund,String FiscalYear,String CMonth,String HeadId,String headname,String autoId,String type,String TestId, String TestName, String Qty, String Rate, String Discount, String DiscountAmount,
			String Payable,String ResultStatus, String BillType, String RegNo, String PatientName, String Mobile,
			String Age, String Month, String Day, String Sex, String Cabin, String Address,
			String ReferralComission,String referralCDegree,String RefferCId,String Referral,String referralDegree, String ReferralId,String ExtraCommision,String Remark,String TotalCharge,String TotalPayable, String PercentDiscount, String ManualDiscount,String DiscountInTaka,String BillDate,String LabId) {
		
		this.paid = Paid;
		this.refund = Refund;
		this.fiscalyear = FiscalYear;
		this.cMonth=CMonth;
		this.testType = type;
		this.headId = HeadId;
		this.headName = headname;
		this.remark = Remark;
		this.autoId = autoId;
		this.testId = TestId;
		this.testname = TestName;
		this.resultstatus = ResultStatus;
		this.qty = Qty;
		this.rate = Rate;
		this.discountAmount = DiscountAmount;
		this.payable=Payable;
		
		System.out.println("BillType "+BillType);
		this.billType = BillType;
		
		this.regno = RegNo;
		this.patientname = PatientName;
		this.mobile = Mobile;
		this.age = Age;
		this.month = Month;
		this.day = Day;
		this.sex = Sex;
		this.bedcabin = Cabin;
		this.address = Address;
		this.referralcomission = ReferralComission;
		this.referral_search = Referral;
		this.extraCommission=ExtraCommision;
		
		this.referralcId=RefferCId;
		this.referralId=ReferralId;
		
		this.TotalCharge = TotalCharge;
		this.totalpayable = TotalPayable;
		
		this.percentdiscount = PercentDiscount;
		this.manualdiscount = ManualDiscount;
		this.perdiscount_taka = DiscountInTaka;
		
		this.referralcdegree=referralCDegree;
		this.referraldegree=referralDegree;
		
		this.billdate=BillDate;
		this.labId=LabId;

	}
	
	//Lab Sale Statement
	public LabBilling(String LabId,String BillType,String PatientName,String BillDate,String TotalCharge,String TotalDiscount,String TotalPayable,String Paid,String Refund,String UserName,String StartDate,String EndDate) {
		this.labId = LabId;
		this.billType = BillType;
		this.patientname = PatientName;
		this.totalamount = TotalCharge;
		this.totaldiscount = TotalDiscount;
		this.totalpayable=TotalPayable;
		this.paid = Paid;
		this.refund = Refund;
		this.username = UserName;
		this.startdate = StartDate;
		this.endate = EndDate;
		this.billdate = BillDate;
	}
	
	//Lab Sale Cash Statement
	public LabBilling(String LabId,String PatientName,String BillType,String PaymentStatus,String BillStatus,String AmountReceived,String BillDate,String ReceivedTime,String StartDate,String EndDate,String UserName) {
		this.labId = LabId;
		this.billType = BillType;
		this.patientname = PatientName;
		this.paymentstatus = PaymentStatus;
		this.billstatus = BillStatus;
		this.amountreceived=AmountReceived;
		this.billdate = BillDate;
		this.receivedtime = ReceivedTime;
		this.username = UserName;
		this.startdate = StartDate;
		this.endate = EndDate;

	}
	
	//Lab Sale Due Statement
	public LabBilling(String LabId,String PatientName,String RefferName,String Degree,String TotalCharge,String Discount,String TotalPayable,String ActualPaid,String Refund,String Due,String BillDate,String Username,String StartDate,String EndDate) {
		this.labId = LabId;
		this.patientname = PatientName;
		this.referral_search = RefferName;
		this.referraldegree = Degree;
		this.totalamount=TotalCharge;
		this.totaldiscount = Discount;
		this.totalpayable = TotalPayable;
		this.paid = ActualPaid;
		this.refund = Refund;
		this.dues = Due;
		this.billdate = BillDate;
		this.username = Username;
		this.startdate = StartDate;
		this.endate = EndDate;

	}
	
	//Department Wise Lab Sale Due Statement
	public LabBilling(String DepTitle,String GroupName,String Rate,String Qty,String Amount,String Discount,String Total,String StartDate,String EndDate) {
		this.deptitle = DepTitle;
		this.groupname = GroupName;
		this.rate = Rate;
		this.qty = Qty;
		this.amount=Amount;
		this.totaldiscount = Discount;
		this.totalpayable = Total;
		this.startdate = StartDate;
		this.endate = EndDate;

	}

	

	public String getFind() {
		return find;
	}

	public void setFind(String find) {
		this.find = find;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getResultstatus() {
		return resultstatus;
	}

	public void setResultstatus(String resultstatus) {
		this.resultstatus = resultstatus;
	}


	public String getHeadName() {
		return headName;
	}

	public void setHeadName(String headName) {
		this.headName = headName;
	}

	public String getTotalPaid() {
		return TotalPaid;
	}

	public void setTotalPaid(String totalPaid) {
		TotalPaid = totalPaid;
	}

	public String getRefferName() {
		return RefferName;
	}

	public void setRefferName(String refferName) {
		RefferName = refferName;
	}

	public String getRefferDegree() {
		return RefferDegree;
	}

	public void setRefferDegree(String refferDegree) {
		RefferDegree = refferDegree;
	}

	public String getLabBill() {
		return LabBill;
	}

	public void setLabBill(String labBill) {
		LabBill = labBill;
	}

	public String getPatientName() {
		return PatientName;
	}

	public void setPatientName(String patientName) {
		PatientName = patientName;
	}

	public String getPathologyRate() {
		return PathologyRate;
	}

	public void setPathologyRate(String pathologyRate) {
		PathologyRate = pathologyRate;
	}

	public String getPathologyNetAmount() {
		return PathologyNetAmount;
	}

	public void setPathologyNetAmount(String pathologyNetAmount) {
		PathologyNetAmount = pathologyNetAmount;
	}

	public String getHormoneRate() {
		return HormoneRate;
	}

	public void setHormoneRate(String hormoneRate) {
		HormoneRate = hormoneRate;
	}

	public String getHormoneNetAmount() {
		return HormoneNetAmount;
	}

	public void setHormoneNetAmount(String hormoneNetAmount) {
		HormoneNetAmount = hormoneNetAmount;
	}

	public String getEchoCardRate() {
		return EchoCardRate;
	}

	public void setEchoCardRate(String echoCardRate) {
		EchoCardRate = echoCardRate;
	}

	public String getEchoCardNetAmount() {
		return EchoCardNetAmount;
	}

	public void setEchoCardNetAmount(String echoCardNetAmount) {
		EchoCardNetAmount = echoCardNetAmount;
	}

	public String getUltraSonoRate() {
		return UltraSonoRate;
	}

	public void setUltraSonoRate(String ultraSonoRate) {
		UltraSonoRate = ultraSonoRate;
	}

	public String getUltraSonoNetAmount() {
		return UltraSonoNetAmount;
	}

	public void setUltraSonoNetAmount(String ultraSonoNetAmount) {
		UltraSonoNetAmount = ultraSonoNetAmount;
	}

	public String getEnDosRate() {
		return EnDosRate;
	}

	public void setEnDosRate(String enDosRate) {
		EnDosRate = enDosRate;
	}

	public String getEnDosNetAmount() {
		return EnDosNetAmount;
	}

	public void setEnDosNetAmount(String enDosNetAmount) {
		EnDosNetAmount = enDosNetAmount;
	}

	public String getXrayRate() {
		return XrayRate;
	}

	public void setXrayRate(String xrayRate) {
		XrayRate = xrayRate;
	}

	public String getXrayNetAmount() {
		return XrayNetAmount;
	}

	public void setXrayNetAmount(String xrayNetAmount) {
		XrayNetAmount = xrayNetAmount;
	}

	public String getECGRate() {
		return ECGRate;
	}

	public void setECGRate(String eCGRate) {
		ECGRate = eCGRate;
	}

	public String getECGNetAmount() {
		return ECGNetAmount;
	}

	public void setECGNetAmount(String eCGNetAmount) {
		ECGNetAmount = eCGNetAmount;
	}

	public String getHistopathologyRate() {
		return HistopathologyRate;
	}

	public void setHistopathologyRate(String histopathologyRate) {
		HistopathologyRate = histopathologyRate;
	}

	public String getHistopathologyNetAmount() {
		return HistopathologyNetAmount;
	}

	public void setHistopathologyNetAmount(String histopathologyNetAmount) {
		HistopathologyNetAmount = histopathologyNetAmount;
	}

	public String getBloodGroupRate() {
		return BloodGroupRate;
	}

	public void setBloodGroupRate(String bloodGroupRate) {
		BloodGroupRate = bloodGroupRate;
	}

	public String getBloodGroupNetAmount() {
		return BloodGroupNetAmount;
	}

	public void setBloodGroupNetAmount(String bloodGroupNetAmount) {
		BloodGroupNetAmount = bloodGroupNetAmount;
	}

	public String getFNARate() {
		return FNARate;
	}

	public void setFNARate(String fNARate) {
		FNARate = fNARate;
	}

	public String getFNANetAmount() {
		return FNANetAmount;
	}

	public void setFNANetAmount(String fNANetAmount) {
		FNANetAmount = fNANetAmount;
	}

	public String getPepsSemarRate() {
		return PepsSemarRate;
	}

	public void setPepsSemarRate(String pepsSemarRate) {
		PepsSemarRate = pepsSemarRate;
	}

	public String getPepsSemarNetAmount() {
		return PepsSemarNetAmount;
	}

	public void setPepsSemarNetAmount(String pepsSemarNetAmount) {
		PepsSemarNetAmount = pepsSemarNetAmount;
	}

	public String getOthersRate() {
		return OthersRate;
	}

	public void setOthersRate(String othersRate) {
		OthersRate = othersRate;
	}

	public String getOthersNetAmount() {
		return OthersNetAmount;
	}

	public void setOthersNetAmount(String othersNetAmount) {
		OthersNetAmount = othersNetAmount;
	}

	public String getDoctorDiscount() {
		return DoctorDiscount;
	}

	public void setDoctorDiscount(String doctorDiscount) {
		DoctorDiscount = doctorDiscount;
	}



	public String getPerticularCharge() {
		return PerticularCharge;
	}

	public void setPerticularCharge(String perticularCharge) {
		PerticularCharge = perticularCharge;
	}

	public String getTotalCharge() {
		return TotalCharge;
	}

	public void setTotalCharge(String totalCharge) {
		TotalCharge = totalCharge;
	}

	public String getDue() {
		return Due;
	}

	public void setDue(String due) {
		Due = due;
	}

	public String getDate() {
		return Date;
	}

	public void setDate(String date) {
		Date = date;
	}

	public String getStartDate() {
		return StartDate;
	}

	public void setStartDate(String startDate) {
		StartDate = startDate;
	}

	public String getEndDate() {
		return EndDate;
	}

	public void setEndDate(String endDate) {
		EndDate = endDate;
	}

	public String getDeptitle() {
		return deptitle;
	}

	public void setDeptitle(String deptitle) {
		this.deptitle = deptitle;
	}

	public String getGroupname() {
		return groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getPaymentstatus() {
		return paymentstatus;
	}

	public void setPaymentstatus(String paymentstatus) {
		this.paymentstatus = paymentstatus;
	}

	public String getBillstatus() {
		return billstatus;
	}

	public void setBillstatus(String billstatus) {
		this.billstatus = billstatus;
	}

	public String getAmountreceived() {
		return amountreceived;
	}

	public void setAmountreceived(String amountreceived) {
		this.amountreceived = amountreceived;
	}

	public String getReceivedtime() {
		return receivedtime;
	}

	public void setReceivedtime(String receivedtime) {
		this.receivedtime = receivedtime;
	}

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getEndate() {
		return endate;
	}

	public void setEndate(String endate) {
		this.endate = endate;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getTotaldiscount() {
		return totaldiscount;
	}

	public void setTotaldiscount(String totaldiscount) {
		this.totaldiscount = totaldiscount;
	}

	public String getBilldate() {
		return billdate;
	}

	public void setBilldate(String billdate) {
		this.billdate = billdate;
	}

	public String getPatientfiscalyear() {
		return patientfiscalyear;
	}


	public void setPatientfiscalyear(String patientfiscalyear) {
		this.patientfiscalyear = patientfiscalyear;
	}


	public String getTotalamount() {
		return totalamount;
	}


	public void setTotalamount(String totalamount) {
		this.totalamount = totalamount;
	}


	public String getPaid() {
		return paid;
	}


	public void setPaid(String paid) {
		this.paid = paid;
	}


	public String getDues() {
		return dues;
	}


	public void setDues(String dues) {
		this.dues = dues;
	}


	public String getAdvance() {
		return advance;
	}


	public void setAdvance(String advance) {
		this.advance = advance;
	}


	public String getPerdiscount_taka() {
		return perdiscount_taka;
	}


	public void setPerdiscount_taka(String perdiscount_taka) {
		this.perdiscount_taka = perdiscount_taka;
	}


	public String getMdiscount_tata() {
		return mdiscount_tata;
	}


	public void setMdiscount_tata(String mdiscount_tata) {
		this.mdiscount_tata = mdiscount_tata;
	}


	public String getTotalpayable() {
		return totalpayable;
	}


	public void setTotalpayable(String totalpayable) {
		this.totalpayable = totalpayable;
	}


	public String getRefund() {
		return refund;
	}


	public void setRefund(String refund) {
		this.refund = refund;
	}


	public String getDeliverydatetime() {
		return deliverydatetime;
	}


	public void setDeliverydatetime(String deliverydatetime) {
		this.deliverydatetime = deliverydatetime;
	}


	public String getReferralId() {
		return referralId;
	}


	public void setReferralId(String referralId) {
		this.referralId = referralId;
	}


	public String getReferralcId() {
		return referralcId;
	}


	public void setReferralcId(String referralcId) {
		this.referralcId = referralcId;
	}


	public String getReferralcdegree() {
		return referralcdegree;
	}


	public void setReferralcdegree(String referralcdegree) {
		this.referralcdegree = referralcdegree;
	}


	public String getPayable() {
		return payable;
	}


	public void setPayable(String payable) {
		this.payable = payable;
	}


	public String getDiscountAmount() {
		return discountAmount;
	}


	public void setDiscountAmount(String discountAmount) {
		this.discountAmount = discountAmount;
	}


	public String getQty() {
		return qty;
	}




	public void setQty(String qty) {
		this.qty = qty;
	}




	public String getRate() {
		return rate;
	}




	public void setRate(String rate) {
		this.rate = rate;
	}




	public String getHeadId() {
		return headId;
	}




	public void setHeadId(String headId) {
		this.headId = headId;
	}




	public String getTestname() {
		return testname;
	}



	public void setTestname(String testname) {
		this.testname = testname;
	}



	public String getLabId() {
		return labId;
	}



	public void setLabId(String labId) {
		this.labId = labId;
	}



	public String getPercentdiscount() {
		return percentdiscount;
	}



	public void setPercentdiscount(String percentdiscount) {
		this.percentdiscount = percentdiscount;
	}



	public String getManualdiscount() {
		return manualdiscount;
	}



	public void setManualdiscount(String manualdiscount) {
		this.manualdiscount = manualdiscount;
	}



	public String getSex() {
		return sex;
	}



	public void setSex(String sex) {
		this.sex = sex;
	}



	public String getFiscalyear() {
		return fiscalyear;
	}



	public void setFiscalyear(String fiscalyear) {
		this.fiscalyear = fiscalyear;
	}



	public String getRegno() {
		return regno;
	}

	
	public void setRegno(String regno) {
		this.regno = regno;
	}


	public String getBillType() {
		return billType;
	}


	public void setBillType(String billType) {
		this.billType = billType;
	}



	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCounter() {
		return counter;
	}

	public void setCounter(String counter) {
		this.counter = counter;
	}

	public String getTestId() {
		return testId;
	}

	public void setTestId(String testId) {
		this.testId = testId;
	}

	public String getPatientname() {
		return patientname;
	}

	public void setPatientname(String patientname) {
		this.patientname = patientname;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getReferral_search() {
		return referral_search;
	}

	public void setReferral_search(String referral_search) {
		this.referral_search = referral_search;
	}

	public String getBedcabin() {
		return bedcabin;
	}

	public void setBedcabin(String bedcabin) {
		this.bedcabin = bedcabin;
	}

	public String getReferraldegree() {
		return referraldegree;
	}

	public void setReferraldegree(String referraldegree) {
		this.referraldegree = referraldegree;
	}

	public String getReferralcomission() {
		return referralcomission;
	}

	public void setReferralcomission(String referralcomission) {
		this.referralcomission = referralcomission;
	}

	public String getReferralcomissiondegree() {
		return referralcomissiondegree;
	}

	public void setReferralcomissiondegree(String referralcomissiondegree) {
		this.referralcomissiondegree = referralcomissiondegree;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getTestType() {
		return testType;
	}

	public void setTestType(String testType) {
		this.testType = testType;
	}

	public String getResultList() {
		return resultList;
	}

	public void setResultList(String resultList) {
		this.resultList = resultList;
	}

	public String getAutoId() {
		return autoId;
	}

	public void setAutoId(String autoId) {
		this.autoId = autoId;
	}

	public String getcMonth() {
		return cMonth;
	}

	public void setcMonth(String cMonth) {
		this.cMonth = cMonth;
	}

	public String getRefferCId() {
		return refferCId;
	}

	public void setRefferCId(String refferCId) {
		this.refferCId = refferCId;
	}

	public String getExtraCommission() {
		return extraCommission;
	}

	public void setExtraCommission(String extraCommission) {
		this.extraCommission = extraCommission;
	}


	
	
	
}
