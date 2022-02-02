package pg.services;

import java.util.List;

import pg.LabModel.ConsultantCreate;
import pg.LabModel.LabBilling;
import pg.LabModel.LabReportCreate;
import pg.LabModel.LabResult;
import pg.LabModel.MachineCreate;
import pg.LabModel.SubTest;
import pg.LabModel.Test;
import pg.LabModel.TestGroup;
import pg.LabModel.TestParticular;
import pg.LabModel.TestWiseParticularName;
import pg.ReceiptionModel.PatientRegistration;



public interface LabService {

	List<Test> getTestlist(String testGroupId);

	boolean addTest(Test v);

	List<Test> getMainTestList(String value);


	boolean addTestParticular(TestWiseParticularName v);
	boolean editTestParticular(TestWiseParticularName v);
	boolean isTestParticularExist(TestWiseParticularName v);
	List<TestWiseParticularName> TestWiseParticularList();

	//Lab Biiling
	boolean AddPatientInforWithTest(LabBilling v);

	boolean checkHasCounterPatient(String userId, String counter, String labid,String find);

	boolean AddTestForThisCounterPatient(LabBilling v);
	public String getMaxLabId();

	boolean CheckTestForThisCounterPatient(LabBilling v);

	List<LabBilling> getTestForThisCounterPatient(LabBilling v);

	List<LabBilling> counterWisePendingTestWithPatientInfo(String userId, String counter);

	boolean BillPost(LabBilling v);

	List<LabBilling> getlabpatientlist(String value);

	List<LabBilling> LabIdWiseTestAndPatientInfo(String labid,String fiscalyear,String cmonth);

	boolean EditPostedBill(LabBilling v);

	boolean CounterInfoDelete(LabBilling v);

	boolean DuePayment(LabBilling v);

	List<LabBilling> LabSaleStatementReport(String startdate, String enddate, String billtype);

	List<LabBilling> LabSalesCashStatementReport(String startdate, String enddate, String billtype);

	List<LabBilling> LabSalesDueStatementReport(String startdate, String enddate, String billtype);

	List<LabBilling> DepartmentWiseLabSalesStatementReport(String startdate, String enddate, String billtype);

	List<LabBilling> TestWiseInvestigationStatement(String startdate, String enddate,String testname,String testall);

	List<LabBilling> LabIdWiseReferraComissionStatement(String startdate, String enddate,String RefferalId);

	//Lab Result
	boolean HeamatologySaveEvent(LabResult v);
	List<LabResult> setHeamatolorySaveData(String testId, String labbill, String fiscalyear,String cMonth);

	boolean UrineSaveEvent(LabResult v);

	List<LabResult> setUrineSaveData(String testId, String labbill, String fiscalyear,String cMonth);
	
	boolean StoolSaveEvent(LabResult v);

	boolean MicrobiologySaveEvent(LabResult v);

	List<LabResult> BioSerHormoneTestData(String testId,String labbill,String headId,String fiscalyear);
	List<LabResult> setBioSerHormoneTestData(String testId,String labbill,String headId, String fiscalyear,String cMonth);

	boolean biochemestrySaveEvent(LabResult v);
	List<LabResult> BioSerHormoneTestData(String value);
	boolean SerologySaveEvent(LabResult v);

	
	List<LabResult> setHeamUrineStoolMicSaveData(String testId,String headid, String labbill, String fiscalyear);

	boolean HormoneSaveEvent(LabResult v);

	List<TestGroup> getTestGrouplist();

	boolean isTestExist(Test v);

	boolean editTest(Test v);

	List<Test> getParentTestlist();

	List<TestParticular> getTestParticularNamelist();

	//Lab Billing
	List<PatientRegistration> getRuningPatientList();
	List<PatientRegistration> getIndoorPatientInformation(String patientId);

	List<LabBilling> getLabBillList();

	boolean addSubTest(SubTest v);
	boolean editSubTest(SubTest v);
	boolean isSubTestExist(SubTest v);
	List<SubTest> TestWiseSubTestList();

	List<LabBilling> getLabBillWiseTestDetails(String labId, String fiscalYear,String Cmonth);

	boolean deleteLabTestData(String userId, String fiscalyear,String cmonth, String find, String counter,String testId, String type,String regNo,String labId);

	boolean isLabBillExist(LabBilling v);

	boolean refundTransaction(LabBilling v);


	public List<ConsultantCreate> getConsultantList();
	boolean isConsultantExist(ConsultantCreate v);
	public boolean saveConsultant(ConsultantCreate v);
	public boolean editConsultant(ConsultantCreate v);
	public String getMaxConsultantId();

	boolean DeleteSubTest(String subTestId);

	boolean ImmunologySaveEvent(LabResult v);

	List<LabResult> setStoolSaveData(String testId, String labbill, String fiscalyear,String cMonth);

	boolean hormoneConfirmatortySaveEvent(LabResult v);

	List<LabResult> getConfirmatorSaveData(String testcodelist, String labbill, String headId,String fiscalyear,String cMonth);

	boolean immunologyConfirmatortySaveEvent(LabResult v);

	List<LabResult> setMicrobiologSaveData(String testId, String labbill, String fiscalyear,String cMonth);

	List<MachineCreate> MachineList();

	boolean isMachineExist(MachineCreate v);

	boolean saveMachineInfo(MachineCreate v);

	boolean editMachineInfo(MachineCreate v);

	boolean isLabReportTitleExist(LabReportCreate v);

	boolean saveLabReportTitleInfo(LabReportCreate v);

	List<LabReportCreate> LabReportTitleList();

	boolean editLabReportTitleInfo(LabReportCreate v);

	List<TestGroup> getPathologyGroup();

	boolean deleteParticularItem(String particularId);

	boolean IsValidTest(LabBilling v);

	boolean billAutoSave(LabBilling v);
	







	
}
