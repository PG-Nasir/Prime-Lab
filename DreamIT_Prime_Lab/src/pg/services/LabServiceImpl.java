package pg.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import pg.dao.LabDAO;
import pg.dao.PasswordDAO;

@Service
public class LabServiceImpl implements LabService{

	
	@Autowired
	private LabDAO labDAO;
	
	
	@Override
	public List<Test> getTestlist(String testGroupId) {
		// TODO Auto-generated method stub
		return labDAO.getTestlist(testGroupId);
	}


	@Override
	public boolean addTest(Test v) {
		// TODO Auto-generated method stub
		return labDAO.addTest(v);
	}


	@Override
	public List<Test> getMainTestList(String value) {
		// TODO Auto-generated method stub
		return labDAO.getMainTestList(value);
	}

	@Override
	public boolean addTestParticular(TestWiseParticularName v) {
		// TODO Auto-generated method stub
		return labDAO.addTestParticular(v);
	}


	@Override
	public List<TestWiseParticularName> TestWiseParticularList() {
		// TODO Auto-generated method stub
		return labDAO.TestWiseParticularList();
	}
@Override
	public boolean editTestParticular(TestWiseParticularName v) {
		// TODO Auto-generated method stub
		return labDAO.editTestParticular(v);
	}
@Override
	public boolean isTestParticularExist(TestWiseParticularName v) {
		// TODO Auto-generated method stub
		return labDAO.isTestParticularExist(v);
	}


	@Override
	public boolean AddPatientInforWithTest(LabBilling v) {
		// TODO Auto-generated method stub
		return labDAO.AddPatientInforWithTest(v);
	}


	@Override
	public boolean checkHasCounterPatient(String userId, String counter, String labid,String find) {
		// TODO Auto-generated method stub
		return labDAO.checkHasCounterPatient(userId, counter, labid,find);
	}


	@Override
	public boolean AddTestForThisCounterPatient(LabBilling v) {
		// TODO Auto-generated method stub
		return labDAO.AddTestForThisCounterPatient(v);
	}


	@Override
	public String getMaxLabId() {
		// TODO Auto-generated method stub
		return labDAO.getMaxLabId();
	}


	@Override
	public boolean CheckTestForThisCounterPatient(LabBilling v) {
		// TODO Auto-generated method stub
		return labDAO.CheckTestForThisCounterPatient(v);
	}


	@Override
	public List<LabBilling> getTestForThisCounterPatient(LabBilling v) {
		// TODO Auto-generated method stub
		return labDAO.getTestForThisCounterPatient(v);
	}


	@Override
	public List<LabBilling> counterWisePendingTestWithPatientInfo(String userId, String counter) {
		// TODO Auto-generated method stub
		return labDAO.counterWisePendingTestWithPatientInfo(userId, counter);
	}


	@Override
	public boolean BillPost(LabBilling v) {
		// TODO Auto-generated method stub
		return labDAO.BillPost(v);
	}


	@Override
	public List<LabBilling> getlabpatientlist(String value) {
		// TODO Auto-generated method stub
		return labDAO.getlabpatientlist(value);
	}


	@Override
	public List<LabBilling> LabIdWiseTestAndPatientInfo(String labid,String fiscalyear,String cmonth) {
		// TODO Auto-generated method stub
		return labDAO.LabIdWiseTestAndPatientInfo(labid,fiscalyear,cmonth);
	}


	@Override
	public boolean EditPostedBill(LabBilling v) {
		// TODO Auto-generated method stub
		return labDAO.EditPostedBill(v);
	}


	@Override
	public boolean CounterInfoDelete(LabBilling v) {
		// TODO Auto-generated method stub
		return labDAO.CounterInfoDelete(v);
	}


	@Override
	public boolean DuePayment(LabBilling v) {
		// TODO Auto-generated method stub
		return labDAO.DuePayment(v);
	}


	@Override
	public List<LabBilling> LabSaleStatementReport(String startdate, String enddate, String billtype) {
		// TODO Auto-generated method stub
		return labDAO.LabSaleStatementReport(startdate, enddate, billtype);
	}
	
	
	@Override
	public List<LabBilling> LabSalesCashStatementReport(String startdate, String enddate, String billtype) {
		// TODO Auto-generated method stub
		return labDAO.LabSalesCashStatementReport(startdate, enddate, billtype);
	}


	@Override
	public List<LabBilling> LabSalesDueStatementReport(String startdate, String enddate, String billtype) {
		// TODO Auto-generated method stub
		return labDAO.LabSalesDueStatementReport(startdate, enddate, billtype);
	}


	@Override
	public List<LabBilling> DepartmentWiseLabSalesStatementReport(String startdate, String enddate, String billtype) {
		// TODO Auto-generated method stub
		return labDAO.DepartmentWiseLabSalesStatementReport(startdate, enddate, billtype);
	}


	@Override
	public List<LabBilling> TestWiseInvestigationStatement(String startdate, String enddate,String testname,String testall) {
		// TODO Auto-generated method stub
		return labDAO.TestWiseInvestigationStatement(startdate, enddate, testname,testall);
	}


	@Override
	public List<LabBilling> LabIdWiseReferraComissionStatement(String startdate, String enddate, String RefferalId) {
		// TODO Auto-generated method stub
		return labDAO.LabIdWiseReferraComissionStatement(startdate, enddate, RefferalId);
	}


	@Override
	public boolean HeamatologySaveEvent(LabResult v) {
		// TODO Auto-generated method stub
		return labDAO.HeamatologySaveEvent(v);
	}


	@Override
	public boolean UrineSaveEvent(LabResult v) {
		// TODO Auto-generated method stub
		return labDAO.UrineSaveEvent(v);
	}

	
	
	@Override
	public boolean StoolSaveEvent(LabResult v) {
		// TODO Auto-generated method stub
		return labDAO.StoolSaveEvent(v);
	}


	@Override
	public boolean MicrobiologySaveEvent(LabResult v) {
		// TODO Auto-generated method stub
		return labDAO.MicrobiologySaveEvent(v);
	}


	@Override
	public List<LabResult> BioSerHormoneTestData(String testId,String labbill,String headId,String fiscalyear) {
		// TODO Auto-generated method stub
		return labDAO.BioSerHormoneTestData(testId,labbill,headId,fiscalyear);
	}


	@Override
	public boolean biochemestrySaveEvent(LabResult v) {
		// TODO Auto-generated method stub
		return labDAO.biochemestrySaveEvent(v);
	}


	@Override
	public boolean SerologySaveEvent(LabResult v) {
		// TODO Auto-generated method stub
		return labDAO.SerologySaveEvent(v);
	}

	
	@Override
	public List<LabResult> setHeamUrineStoolMicSaveData(String testId,String headid,String labbill, String fiscalyear) {
		// TODO Auto-generated method stub
		return labDAO.setHeamUrineStoolMicSaveData(testId,headid,labbill, fiscalyear);
	}


	@Override
	public boolean HormoneSaveEvent(LabResult v) {
		// TODO Auto-generated method stub
		return labDAO.HormoneSaveEvent(v);
	}


	@Override
	public List<TestGroup> getTestGrouplist() {
		// TODO Auto-generated method stub
		return labDAO.getTestGrouplist();
	}


	@Override
	public boolean isTestExist(Test v) {
		// TODO Auto-generated method stub
		return labDAO.isTestExist(v);
	}


	@Override
	public boolean editTest(Test v) {
		// TODO Auto-generated method stub
		return labDAO.editTest(v);
	}


	@Override
	public List<Test> getParentTestlist() {
		// TODO Auto-generated method stub
		return labDAO.getParentTestlist();
	}


	@Override
	public List<TestParticular> getTestParticularNamelist() {
		// TODO Auto-generated method stub
		return labDAO.getTestParticularNamelist();
	}


	@Override
	public List<PatientRegistration> getRuningPatientList() {
		// TODO Auto-generated method stub
		return labDAO.getRuningPatientList();
	}


	@Override
	public List<PatientRegistration> getIndoorPatientInformation(String patientId) {
		// TODO Auto-generated method stub
		return labDAO.getIndoorPatientInformation(patientId);
	}


	@Override
	public List<LabBilling> getLabBillList() {
		// TODO Auto-generated method stub
		return labDAO.getLabBillList();
	}


	@Override
	public boolean addSubTest(SubTest v) {
		// TODO Auto-generated method stub
		return labDAO.addSubTest(v);
	}


	@Override
	public boolean editSubTest(SubTest v) {
		// TODO Auto-generated method stub
		return labDAO.editSubTest(v);
	}


	@Override
	public boolean isSubTestExist(SubTest v) {
		// TODO Auto-generated method stub
		return labDAO.isSubTestExist(v);
	}


	@Override
	public List<SubTest> TestWiseSubTestList() {
		// TODO Auto-generated method stub
		return labDAO.TestWiseSubTestList();
	}


	@Override
	public List<LabBilling> getLabBillWiseTestDetails(String labId, String fiscalYear,String Cmonth) {
		// TODO Auto-generated method stub
		return labDAO.getLabBillWiseTestDetails(labId, fiscalYear,Cmonth);
	}


	@Override
	public List<LabResult> setHeamatolorySaveData(String testId, String labbill, String fiscalyear,String cMonth) {
		// TODO Auto-generated method stub
		return labDAO.setHeamatolorySaveData(testId, labbill, fiscalyear,cMonth);
	}


	@Override
	public List<LabResult> setUrineSaveData(String testId, String labbill, String fiscalyear,String cMonth) {
		// TODO Auto-generated method stub
		return labDAO.setUrineSaveData(testId, labbill, fiscalyear,cMonth);
	}


	@Override
	public List<LabResult> BioSerHormoneTestData(String value) {
		// TODO Auto-generated method stub
		return labDAO.BioSerHormoneTestData(value);
	}


	@Override
	public List<LabResult> setBioSerHormoneTestData(String testId, String labbill, String headId, String fiscalyear,String cMonth) {
		// TODO Auto-generated method stub
		return labDAO.setBioSerHormoneTestData(testId, labbill, headId, fiscalyear,cMonth);
	}


	@Override
	public boolean deleteLabTestData(String userId, String fiscalyear,String cmonth, String find, String counter, String testId,
			String type, String regNo,String labId) {
		// TODO Auto-generated method stub
		return labDAO.deleteLabTestData(userId, fiscalyear,cmonth, find, counter, testId, type, regNo,labId);
	}


	@Override
	public boolean isLabBillExist(LabBilling v) {
		// TODO Auto-generated method stub
		return labDAO.isLabBillExist(v);
	}


	@Override
	public boolean refundTransaction(LabBilling v) {
		// TODO Auto-generated method stub
		return labDAO.refundTransaction(v);
	}


	@Override
	public List<ConsultantCreate> getConsultantList() {
		// TODO Auto-generated method stub
		return labDAO.getConsultantList();
	}


	@Override
	public boolean isConsultantExist(ConsultantCreate v) {
		// TODO Auto-generated method stub
		return labDAO.isConsultantExist(v);
	}


	@Override
	public boolean saveConsultant(ConsultantCreate v) {
		// TODO Auto-generated method stub
		return labDAO.saveConsultant(v);
	}


	@Override
	public boolean editConsultant(ConsultantCreate v) {
		// TODO Auto-generated method stub
		return labDAO.editConsultant(v);
	}


	@Override
	public String getMaxConsultantId() {
		// TODO Auto-generated method stub
		return labDAO.getMaxConsultantId();
	}


	@Override
	public boolean DeleteSubTest(String subTestId) {
		// TODO Auto-generated method stub
		return labDAO.DeleteSubTest(subTestId);
	}


	@Override
	public boolean ImmunologySaveEvent(LabResult v) {
		// TODO Auto-generated method stub
		return labDAO.ImmunologySaveEvent(v);
	}


	@Override
	public List<LabResult> setStoolSaveData(String testId, String labbill, String fiscalyear,String cMonth) {
		// TODO Auto-generated method stub
		return labDAO.setStoolSaveData(testId, labbill, fiscalyear,cMonth);
	}


	@Override
	public boolean hormoneConfirmatortySaveEvent(LabResult v) {
		// TODO Auto-generated method stub
		return labDAO.hormoneConfirmatortySaveEvent(v);
	}


	@Override
	public List<LabResult> getConfirmatorSaveData(String testcodelist, String labbill, String headId,String fiscalyear,String cMonth) {
		// TODO Auto-generated method stub
		return labDAO.getConfirmatorSaveData(testcodelist, labbill, headId,fiscalyear,cMonth);
	}


	@Override
	public boolean immunologyConfirmatortySaveEvent(LabResult v) {
		// TODO Auto-generated method stub
		return labDAO.immunologyConfirmatortySaveEvent(v);
	}


	@Override
	public List<LabResult> setMicrobiologSaveData(String testId, String labbill, String fiscalyear,String cMonth) {
		// TODO Auto-generated method stub
		return labDAO.setMicrobiologSaveData(testId, labbill, fiscalyear,cMonth);
	}


	@Override
	public List<MachineCreate> MachineList() {
		// TODO Auto-generated method stub
		return labDAO.MachineList();
	}


	@Override
	public boolean isMachineExist(MachineCreate v) {
		// TODO Auto-generated method stub
		return labDAO.isMachineExist(v);
	}

	@Override
	public boolean saveMachineInfo(MachineCreate v) {
		// TODO Auto-generated method stub
		return labDAO.saveMachineInfo(v);
	}
	
	@Override
	public boolean editMachineInfo(MachineCreate v) {
		// TODO Auto-generated method stub
		return labDAO.editMachineInfo(v);
	}


	@Override
	public boolean isLabReportTitleExist(LabReportCreate v) {
		// TODO Auto-generated method stub
		return labDAO.isLabReportTitleExist(v);
	}


	@Override
	public boolean saveLabReportTitleInfo(LabReportCreate v) {
		// TODO Auto-generated method stub
		return labDAO.saveLabReportTitleInfo(v);
	}


	@Override
	public List<LabReportCreate> LabReportTitleList() {
		// TODO Auto-generated method stub
		return labDAO.LabReportTitleList();
	}


	@Override
	public boolean editLabReportTitleInfo(LabReportCreate v) {
		// TODO Auto-generated method stub
		return labDAO.editLabReportTitleInfo(v);
	}


	@Override
	public List<TestGroup> getPathologyGroup() {
		// TODO Auto-generated method stub
		return labDAO.getPathologyGroup();
	}


	@Override
	public boolean deleteParticularItem(String particularId) {
		// TODO Auto-generated method stub
		return labDAO.deleteParticularItem(particularId);
	}


	@Override
	public boolean IsValidTest(LabBilling v) {
		// TODO Auto-generated method stub
		return labDAO.IsValidTest(v);
	}


	@Override
	public boolean billAutoSave(LabBilling v) {
		// TODO Auto-generated method stub
		return labDAO.billAutoSave(v);
	}
	
}
