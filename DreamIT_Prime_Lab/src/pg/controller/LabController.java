package pg.controller;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import pg.AccountsModel.AccountsCreate;
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
import pg.SettingModel.DoctorCreate;
import pg.services.AccountService;
import pg.services.LabService;
import pg.services.SettingService;


@Controller
public class LabController {
	
	

	String LabId="";
	
	String labbill="",fiscalyear="",cMonth="",testid="",headid="";
	
	DecimalFormat df = new DecimalFormat("#.00");
	
	@Autowired
	private LabService labService;
	
	@Autowired
	private SettingService settingService;
	
	String fiscalYear="",headId="",testIdlist="",note="",inchargeId="",doctor1="",doctor2="",machineId="",titleId="",horReportCategory="",immReportCategory="";
	

	
	
	//Test Create
	@RequestMapping(value = "test_create",method=RequestMethod.GET)
	public ModelAndView test_create(ModelMap map,HttpSession session) {
		
		String userId=(String)session.getAttribute("userId");
		String userName=(String)session.getAttribute("userName");
		
		String fiscalYear=new SimpleDateFormat("yyyy").format(new Date());
		//String doctorId=settingService.getMaxDoctorId();

		List<TestGroup> grouplist=labService.getTestGrouplist();
		//List<Test> testlist=labService.getTestlist();
		ModelAndView view = new ModelAndView("Lab/test_create");
		view.addObject("grouplist", grouplist);
		//view.addObject("testlist", testlist);
		
		map.addAttribute("fiscalYear", fiscalYear);
		
		map.addAttribute("userId",userId);
		map.addAttribute("userName",userName);
		map.addAttribute("linkName","test_create");
		
		
		return view; //JSP - /WEB-INF/view/index.jsp
		
	}
	
	@RequestMapping(value = "/saveTest",method=RequestMethod.POST)
	public @ResponseBody JSONObject saveTest(Test v) {
		JSONObject objmain = new JSONObject();
		if(!labService.isTestExist(v)) {
			
			if(labService.addTest(v)) {
				
				//List<Test> testlist=labService.getTestlist();
				//objmain.put("result", testlist);

			}else {
				objmain.put("result", "Something Wrong");
			}	
		}else {
			objmain.put("result", "duplicate");
		}

		return objmain;
	}

	
	@RequestMapping(value = "/editTest",method=RequestMethod.POST)
	public @ResponseBody JSONObject editTest(Test v) {
		JSONObject objmain = new JSONObject();
		if(!labService.isTestExist(v)) {
			
			if(labService.editTest(v)) {
				
				//List<Test> testlist=labService.getTestlist();
				//objmain.put("result", testlist);

			}else {
				objmain.put("result", "Something Wrong");
			}	
		}else {
			objmain.put("result", "duplicate");
		}

		return objmain;
	}
	
	@RequestMapping(value = "/findGroupId/{findGroupId}",method=RequestMethod.GET)
	public @ResponseBody JSONObject editTest(@PathVariable ("findGroupId") String findGroupId) {
		JSONObject objmain = new JSONObject();
		List<Test> testlist=labService.getTestlist(findGroupId);
		objmain.put("result", testlist);

		return objmain;
	}
	
	@RequestMapping(value = "/printTestList/{idList}",method=RequestMethod.GET)
	public @ResponseBody ModelAndView printTestList(ModelMap map,@PathVariable ("idList") String idList) {
		
		System.out.println("stoll");
		ModelAndView view=new ModelAndView("Lab/testListView");
		
		String id[] = idList.split("@");
		map.addAttribute("findGroupId", id[0]);

		return view;
	}
	
	@RequestMapping(value = "sub_test_create",method=RequestMethod.GET)
	public ModelAndView sub_test_create(ModelMap map,HttpSession session) {
		
		String userId=(String)session.getAttribute("userId");
		String userName=(String)session.getAttribute("userName");
		
		System.out.println("call");
		List<Test> mainTestlist=labService.getParentTestlist();
		ModelAndView view = new ModelAndView("Lab/sub_test_create");
		view.addObject("mainTestlist", mainTestlist);
		
		map.addAttribute("userId",userId);
		map.addAttribute("userName",userName);
		map.addAttribute("linkName","sub_test_create");

		
		return view; //JSP - /WEB-INF/view/index.jsp
		
	}
	
	
	
	@RequestMapping(value = "/addSubTest",method=RequestMethod.POST)
	public @ResponseBody JSONObject addSubTest(SubTest v) {

		JSONObject objmain = new JSONObject();
		if(!labService.isSubTestExist(v)) {
			
			if(labService.addSubTest(v)) {

				List<SubTest> subTestlist=labService.TestWiseSubTestList();
				objmain.put("result", subTestlist);

			}else {
				objmain.put("result", "Something Wrong");
			}
		}else {
			objmain.put("result", "duplicate");
		}

		return objmain;

	}

	@RequestMapping(value = "/editSubTest",method=RequestMethod.POST)
	public @ResponseBody JSONObject editSubTest(SubTest v) {

		JSONObject objmain = new JSONObject();
		if(!labService.isSubTestExist(v)) {

			if(labService.editSubTest(v)) {

				List<SubTest> subTestlist=labService.TestWiseSubTestList();
				objmain.put("result", subTestlist);

			}else {
				objmain.put("result", "Something Wrong");
			}	
		}else {
			objmain.put("result", "duplicate");
		}

		return objmain;
	}
	
	
	@RequestMapping(value = "/DeleteSubTest/{subTestId}",method=RequestMethod.GET)
	public @ResponseBody JSONObject DeleteSubTest(@PathVariable ("subTestId") String subTestId) {


		JSONObject objmain = new JSONObject();
		JSONArray mainarray = new JSONArray();

		
		boolean flag=labService.DeleteSubTest(subTestId);
		
		if(flag) {
			List<SubTest> subtTestList=labService.TestWiseSubTestList();

			for(int a=0;a<subtTestList.size();a++) {


				JSONObject obj = new JSONObject();

				obj.put("subTestId", subtTestList.get(a).getSubTestId());
				obj.put("subtestname", subtTestList.get(a).getSubTestName());
				obj.put("calculate", subtTestList.get(a).getCalculate());
				obj.put("testId", subtTestList.get(a).getTestId());
				obj.put("testName", subtTestList.get(a).getTestName());
				obj.put("unit", subtTestList.get(a).getUnit());
				obj.put("normalRange", subtTestList.get(a).getNormalRange());

				mainarray.add(obj);

			}
			objmain.put("result", mainarray);
		}
		else {
			objmain.put("result", "Delete can not due to error");
		}
		


		return objmain;

	}

	@RequestMapping(value = "/TestWiseSubTestList",method=RequestMethod.POST)
	public @ResponseBody JSONObject TestWiseSubTestList() {


		JSONObject objmain = new JSONObject();
		JSONArray mainarray = new JSONArray();


		List<SubTest> subtTestList=labService.TestWiseSubTestList();

		for(int a=0;a<subtTestList.size();a++) {


			JSONObject obj = new JSONObject();

			obj.put("subTestId", subtTestList.get(a).getSubTestId());
			obj.put("subtestname", subtTestList.get(a).getSubTestName());
			obj.put("calculate", subtTestList.get(a).getCalculate());
			obj.put("testId", subtTestList.get(a).getTestId());
			obj.put("testName", subtTestList.get(a).getTestName());
			obj.put("unit", subtTestList.get(a).getUnit());
			obj.put("normalRange", subtTestList.get(a).getNormalRange());
			obj.put("sorting", subtTestList.get(a).getSorting());

			mainarray.add(obj);

		}
		objmain.put("result", mainarray);

		return objmain;

	}
	
	@RequestMapping(value = "test_particular_create",method=RequestMethod.GET)
	public ModelAndView test_particular_create(ModelMap map,HttpSession session) {
		
		String userId=(String)session.getAttribute("userId");
		String userName=(String)session.getAttribute("userName");
		
		List<Test> mainTestlist=labService.getParentTestlist();
		List<TestParticular> paticularNameList=labService.getTestParticularNamelist();
		ModelAndView view = new ModelAndView("Lab/test_particular_create");
		view.addObject("mainTestlist", mainTestlist);
		view.addObject("paticularNameList", paticularNameList);
		
		map.addAttribute("userId",userId);
		map.addAttribute("userName",userName);
		map.addAttribute("linkName","test_particular_create");

		
		return view; //JSP - /WEB-INF/view/index.jsp
		
	}
	
	@RequestMapping(value = "/addTestParticular",method=RequestMethod.POST)
	public @ResponseBody JSONObject addTestParticular(TestWiseParticularName v) {

		JSONObject objmain = new JSONObject();
		if(!labService.isTestParticularExist(v)) {

			if(labService.addTestParticular(v)) {

				List<SubTest> testParticularlist=labService.TestWiseSubTestList();
				objmain.put("result", testParticularlist);

			}else {
				objmain.put("result", "Something Wrong");
			}	
		}else {
			objmain.put("result", "duplicate");
		}

		return objmain;
	}

	@RequestMapping(value = "/editTestParticular",method=RequestMethod.POST)
	public @ResponseBody JSONObject editTestParticular(TestWiseParticularName v) {

		JSONObject objmain = new JSONObject();
		if(!labService.isTestParticularExist(v)) {

			if(labService.editTestParticular(v)) {

				List<SubTest> testParticularlist=labService.TestWiseSubTestList();
				objmain.put("result", testParticularlist);

			}else {
				objmain.put("result", "Something Wrong");
			}	
		}else {
			objmain.put("result", "duplicate");
		}

		return objmain;
	}

	@RequestMapping(value = "/deleteParticularItem",method=RequestMethod.POST)
	public @ResponseBody JSONObject deleteParticularItem(String particularId) {

		System.out.println("particularId "+particularId);

		JSONObject objmain = new JSONObject();
		JSONArray mainarray = new JSONArray();

		boolean flag=labService.deleteParticularItem(particularId);
		if(flag) {
			List<TestWiseParticularName> testWiseParticularList=labService.TestWiseParticularList();

			objmain.put("result", testWiseParticularList);
		}
		else {
			objmain.put("result", "Particular Delete is not successfully");
		}



		return objmain;

	}
	
	@RequestMapping(value = "/TestWiseParticularList",method=RequestMethod.POST)
	public @ResponseBody JSONObject TestWiseParticularList() {


		JSONObject objmain = new JSONObject();
		JSONArray mainarray = new JSONArray();


		List<TestWiseParticularName> testWiseParticularList=labService.TestWiseParticularList();




		objmain.put("result", testWiseParticularList);

		System.out.println(objmain.toString());

		return objmain;

	}
	
	@RequestMapping(value = "lab_billing",method=RequestMethod.GET)
	public ModelAndView lab_billing(ModelMap map,HttpSession session) {
		

		String userId=(String)session.getAttribute("userId");
		String userName=(String)session.getAttribute("userName");
		
		String checkStatus=settingService.getAccessStatus(userId);
		
		String edit="0",delete="0",refund="0",discount="0";
		StringTokenizer token=new StringTokenizer(checkStatus, ":");
		while(token.hasMoreTokens()) {
			edit=token.nextToken();
			delete=token.nextToken();
			refund=token.nextToken();
			discount=token.nextToken();
			break;
		}

		
		String labId=labService.getMaxLabId();
		
		List<DoctorCreate> doctorlist=settingService.getDoctorList();
		//List<Test> mainTestlist=labService.getParentTestlist();
		//List<PatientRegistration> indoorRuningPatientList=labService.getRuningPatientList();
		List<LabBilling> billlist=labService.getLabBillList();
		ModelAndView view = new ModelAndView("Lab/lab_billing");
		view.addObject("doctorlist", doctorlist);
		//view.addObject("mainTestlist", mainTestlist);
		//view.addObject("indoorRuningPatientList", indoorRuningPatientList);
		view.addObject("billlist", billlist);
	
		
		String fiscalYear=new SimpleDateFormat("yyyy").format(new Date());
		map.addAttribute("fiscalYear", fiscalYear);
		map.addAttribute("labId", labId);
		
		map.addAttribute("userId",userId);
		map.addAttribute("userName",userName);
		map.addAttribute("linkName","lab_billing");
		map.addAttribute("edit",edit);
		map.addAttribute("delete",delete);
		map.addAttribute("refund",refund);
		map.addAttribute("discount",discount);
		
		
		return view; //JSP - /WEB-INF/view/index.jsp
		
	}
	
	
	@RequestMapping(value = "/getTestList",method=RequestMethod.GET)
	public @ResponseBody JSONObject getTestList() {
		
		JSONObject objmain = new JSONObject();
		List<Test> mainTestlist=labService.getParentTestlist();
		objmain.put("mainTestlist", mainTestlist);
		return objmain;
	}
	
	@RequestMapping(value = "/getDoctorList",method=RequestMethod.GET)
	public @ResponseBody JSONObject getDoctorList(LabBilling v) {
		
		JSONObject objmain = new JSONObject();
		
		
		List<DoctorCreate> doctorlist=settingService.getDoctorList();
		objmain.put("doctorList", doctorlist);
		return objmain;
	}
	
	@RequestMapping(value = "/printLabInfo/{idList}")
	public @ResponseBody ModelAndView printLabInfo(ModelMap map,@PathVariable ("idList") String idList) {
		
		ModelAndView view=new ModelAndView("Lab/printLabMoneyReceipt");
		String id[] = idList.split("@");
		map.addAttribute("fiscalyear", id[0]);
		map.addAttribute("labId", id[1]);
		map.addAttribute("cmonth", id[2]);
		map.addAttribute("reportType", id[3]);
		return view;
	}
	
	@RequestMapping(value = "/printLabSlipInfo/{idList}")
	public @ResponseBody ModelAndView printLabSlipInfo(ModelMap map,@PathVariable ("idList") String idList) {
		
		ModelAndView view=new ModelAndView("Lab/printLabSlipInfo");
		String id[] = idList.split("@");
		map.addAttribute("fiscalyear", id[0]);
		map.addAttribute("labId", id[1]);
		map.addAttribute("cmonth", id[2]);
		map.addAttribute("reportType", id[3]);
		return view;
	}
	
	@RequestMapping(value = "getIndoorPatientInformation/{patientId}",method=RequestMethod.GET)
	public @ResponseBody JSONObject getIndoorPatientInformation(@PathVariable ("patientId") String patientId) {
		JSONObject objmain = new JSONObject();
		List<PatientRegistration> patientinfo=labService.getIndoorPatientInformation(patientId);
		objmain.put("result", patientinfo);
		
		return objmain;
	}
	
	
	
	//Lab Bi
	
	@RequestMapping(value = "/addDoctorDirect",method=RequestMethod.POST)
	public @ResponseBody JSONObject addDoctor(DoctorCreate v) {
		JSONObject objmain = new JSONObject();
		
		boolean flag=settingService.saveDoctorDirect(v);
		
		if(flag) {
			List<DoctorCreate> doctorList=settingService.getDoctorList();
			objmain.put("doctorList", doctorList);
		}
		else{
			objmain.put("doctorList", "Something has wrong!");
		}
		
		
		
		
		
		return objmain;
	}
	
	
	@RequestMapping(value = "/AddPatientInforWithTest",method=RequestMethod.POST)
	public @ResponseBody JSONObject AddPatientInforWithTest(LabBilling v) {

		
		JSONObject objmain = new JSONObject();
		JSONArray mainarray = new JSONArray();
	
		String msg="Occur Error While Add Test Particular";
		
		boolean validTest=labService.IsValidTest(v);
		
		if(validTest) {
			
			
			boolean flag=labService.AddPatientInforWithTest(v);
			
			System.out.println("flag "+flag);
			
		
			boolean checkCounterPatient=labService.checkHasCounterPatient(v.getUserId(),v.getCounter(),v.getLabId(),v.getFind());
		
			
			boolean doplicatetest=labService.CheckTestForThisCounterPatient(v);
			
			int dop=0;
			if(checkCounterPatient && doplicatetest) {
				System.out.println("Doplicate Test Never Allow");
				
				dop=1;
			}
		
			
			if(dop==0) {
				
				if(v.getFind().equals("0")) {
					boolean testaddflag=labService.AddTestForThisCounterPatient(v);
					
					if(checkCounterPatient && testaddflag){
					
						double TotalAmount=0;
						double TotalPrDiscounTaka=0;
						double TotalMDiscounTaka=0;
						double TotalPayableTaka=0;
						double TotalActualAmount=0;
						double percentDiscount=0;
						List<LabBilling> lablist=labService.getTestForThisCounterPatient(v);
						
						for(int a=0;a<lablist.size();a++) {
							if(lablist.get(a).getTestType().equals("1")) {
								TotalActualAmount=TotalActualAmount+(Double.parseDouble(lablist.get(a).getRate())*Double.parseDouble(lablist.get(a).getQty()));
							}
							TotalAmount=TotalAmount+(Double.parseDouble(lablist.get(a).getRate())*Double.parseDouble(lablist.get(a).getQty()));
						
							percentDiscount=Double.parseDouble(lablist.get(a).getPercentdiscount()==null?"0":lablist.get(a).getPercentdiscount());
							TotalMDiscounTaka=Double.parseDouble(lablist.get(a).getManualdiscount()==null?"0":lablist.get(a).getManualdiscount());
						}	
						
						TotalPrDiscounTaka=TotalActualAmount*percentDiscount/100;
						
						for(int a=0;a<lablist.size();a++) {


							JSONObject obj = new JSONObject();
							obj.put("countersearch", "0");
							obj.put("type", lablist.get(a).getTestType());
							obj.put("testId", lablist.get(a).getTestId());
							obj.put("testname", lablist.get(a).getTestname());
							obj.put("rate", lablist.get(a).getRate());
							obj.put("percentdiscount", lablist.get(a).getPercentdiscount());
							obj.put("discountAmount", lablist.get(a).getDiscountAmount());
							obj.put("payable", lablist.get(a).getPayable());
							obj.put("counter", lablist.get(a).getCounter());
							obj.put("labId", lablist.get(a).getLabId());
							obj.put("qty", lablist.get(a).getQty());
							obj.put("billtype", v.getBillType());

							obj.put("TotalAmount", TotalAmount);
							obj.put("TotalPrDiscounTaka", TotalPrDiscounTaka);
							obj.put("TotalMDiscounTaka", TotalMDiscounTaka);
							
							obj.put("referralId", lablist.get(a).getReferralId());
							obj.put("referralcId", lablist.get(a).getReferralcId());
							
							obj.put("referralcdegree", lablist.get(a).getReferralcdegree());
							obj.put("referraldegree", lablist.get(a).getReferraldegree());
							
							TotalPayableTaka=TotalActualAmount-(TotalPrDiscounTaka+TotalMDiscounTaka);
							obj.put("TotalPayableTaka",TotalPayableTaka);
							mainarray.add(obj);

						}		
						
						objmain.put("result", mainarray);

						System.out.println(objmain.toString());

						return objmain;
					}
				}
				else{
					
					
					boolean testaddflag=labService.AddTestForThisCounterPatient(v);
					if(checkCounterPatient && testaddflag){
						List<LabBilling> lablist=labService.LabIdWiseTestAndPatientInfo(v.getLabId(),v.getFiscalyear(),v.getcMonth());
						
						double TotalAmount=0;
						double TotalPrDiscounTaka=0;
						double TotalMDiscounTaka=0;
						double TotalPayableTaka=0;
						double TotalActualAmount=0;
						double percentDiscount=0;
						for(int a=0;a<lablist.size();a++) {
							if(lablist.get(a).getTestType().equals("1")) {
								TotalActualAmount=TotalActualAmount+(Double.parseDouble(lablist.get(a).getRate())*Double.parseDouble(lablist.get(a).getQty()));
							}
							TotalAmount=TotalAmount+(Double.parseDouble(lablist.get(a).getRate())*Double.parseDouble(lablist.get(a).getQty()));
							
							percentDiscount=Double.parseDouble(lablist.get(a).getPercentdiscount()==null?"0":lablist.get(a).getPercentdiscount());
							TotalMDiscounTaka=Double.parseDouble(lablist.get(a).getManualdiscount()==null?"0":lablist.get(a).getManualdiscount());
						}
						
						
						TotalPrDiscounTaka=TotalActualAmount*percentDiscount/100;
						
						for(int a=0;a<lablist.size();a++) {


							JSONObject obj = new JSONObject();

							obj.put("countersearch", "1");
							obj.put("type", lablist.get(a).getTestType());
							obj.put("testId", lablist.get(a).getTestId());
							obj.put("testname", lablist.get(a).getTestname());
							obj.put("headId", lablist.get(a).getHeadId());
							obj.put("headname", lablist.get(a).getHeadName());
							obj.put("resultstatus", lablist.get(a).getResultstatus());
							obj.put("rate", lablist.get(a).getRate());
							obj.put("percentdiscount", lablist.get(a).getPercentdiscount());
							obj.put("discountAmount", lablist.get(a).getDiscountAmount());
							obj.put("payable", lablist.get(a).getPayable());
							obj.put("counter", lablist.get(a).getCounter()==null?"":lablist.get(a).getCounter());
							obj.put("labId", lablist.get(a).getLabId());
							obj.put("fiscalYear", lablist.get(a).getFiscalyear());
							obj.put("cMonth", lablist.get(a).getcMonth());
							obj.put("qty", lablist.get(a).getQty());
							
							
							
							obj.put("regno", lablist.get(a).getRegno());
							obj.put("patientname", lablist.get(a).getPatientname());
							obj.put("mobile", lablist.get(a).getMobile());
							obj.put("age", lablist.get(a).getAge());
							obj.put("month", lablist.get(a).getMonth());
							obj.put("day", lablist.get(a).getDay());
							obj.put("sex", lablist.get(a).getSex());
							obj.put("address", lablist.get(a).getAddress());
							obj.put("bedcabin", lablist.get(a).getBedcabin());
							obj.put("referralcomission", lablist.get(a).getReferralcomission());
							obj.put("referral_search", lablist.get(a).getReferral_search());
							obj.put("referralcdegree", lablist.get(a).getReferralcdegree());
							obj.put("referraldegree", lablist.get(a).getReferraldegree());
							
							obj.put("referralId", lablist.get(a).getReferralId());
							obj.put("referralcId", lablist.get(a).getReferralcId());
							

							obj.put("TotalAmount", TotalAmount);
							obj.put("TotalPrDiscounTaka", TotalPrDiscounTaka);
							obj.put("TotalMDiscounTaka", TotalMDiscounTaka);
							
							TotalPayableTaka=TotalActualAmount-(TotalPrDiscounTaka+TotalMDiscounTaka);
							obj.put("TotalPayableTaka",TotalPayableTaka);
							
							obj.put("paid",lablist.get(a).getPaid());
							obj.put("refund",lablist.get(a).getRefund());
							obj.put("find","1");
							obj.put("fiscalyear",lablist.get(a).getFiscalyear());
							
							mainarray.add(obj);

						}		
						
						objmain.put("result", mainarray);

						System.out.println(objmain.toString());

						return objmain;	
					}
					
					
		
				}
				

			}
			else {
				objmain.put("result", "Doplicate Test Never Allow");
			}
		}
		else {
			objmain.put("result", "Invalid Test Never Allow");
		}
		



		return objmain;
	}
	
	@RequestMapping(value = "/counterWisePendingTestWithPatientInfo",method=RequestMethod.POST)
	public @ResponseBody JSONObject counterWisePendingTestWithPatientInfo(String userId,String counter) {
		JSONObject objmain = new JSONObject();
		JSONArray mainarray = new JSONArray();
		
		List<LabBilling> lablist=labService.counterWisePendingTestWithPatientInfo(userId,counter);
		
		double TotalAmount=0;
		double TotalPrDiscounTaka=0;
		double TotalMDiscounTaka=0;
		double TotalPayableTaka=0;
		
		for(int a=0;a<lablist.size();a++) {

			TotalAmount=TotalAmount+(Double.parseDouble(lablist.get(a).getRate())*Double.parseDouble(lablist.get(a).getQty()));
			TotalPrDiscounTaka=TotalPrDiscounTaka+Double.parseDouble(lablist.get(a).getDiscountAmount());
			TotalMDiscounTaka=Double.parseDouble(lablist.get(a).getManualdiscount()==null?"0":lablist.get(a).getManualdiscount());
		}
		
		for(int a=0;a<lablist.size();a++) {


			JSONObject obj = new JSONObject();

			obj.put("countersearch", "1");
			obj.put("type", lablist.get(a).getTestType());
			obj.put("testId", lablist.get(a).getTestId());
			obj.put("testname", lablist.get(a).getTestname());
			obj.put("rate", lablist.get(a).getRate());
			obj.put("percentdiscount", lablist.get(a).getPercentdiscount());
			obj.put("discountAmount", lablist.get(a).getDiscountAmount());
			obj.put("payable", lablist.get(a).getPayable());
			obj.put("counter", lablist.get(a).getCounter());
			obj.put("labId", lablist.get(a).getLabId());
			obj.put("qty", lablist.get(a).getQty());
			obj.put("find", "0");
			
			obj.put("billtype", lablist.get(a).getBillType());
			obj.put("regno", lablist.get(a).getRegno());
			obj.put("patientname", lablist.get(a).getPatientname());
			obj.put("mobile", lablist.get(a).getMobile());
			obj.put("age", lablist.get(a).getAge());
			obj.put("month", lablist.get(a).getMonth());
			obj.put("day", lablist.get(a).getDay());
			obj.put("sex", lablist.get(a).getSex());
			obj.put("address", lablist.get(a).getAddress());
			obj.put("bedcabin", lablist.get(a).getBedcabin());
			obj.put("referralcomission", lablist.get(a).getReferralcomission());
			obj.put("referral_search", lablist.get(a).getReferral_search());
			obj.put("referralcdegree", lablist.get(a).getReferralcdegree());
			obj.put("referraldegree", lablist.get(a).getReferraldegree());
			
			obj.put("referralId", lablist.get(a).getReferralId());
			obj.put("referralcId", lablist.get(a).getReferralcId());

			obj.put("TotalAmount", TotalAmount);
			obj.put("TotalPrDiscounTaka", TotalPrDiscounTaka);
			obj.put("TotalMDiscounTaka", TotalMDiscounTaka);
			
			TotalPayableTaka=TotalAmount-(TotalPrDiscounTaka+TotalMDiscounTaka);
			obj.put("TotalPayableTaka",TotalPayableTaka);
			
			mainarray.add(obj);
			
		}		
		
		objmain.put("result", mainarray);

		System.out.println(objmain.toString());

		return objmain;
	
	}
	
	@RequestMapping(value = "/deleteLabTestData",method=RequestMethod.POST)
	public @ResponseBody JSONObject deleteLabTestData(String userId,String fiscalyear,String find,String counter,String testId,String type,String regNo,String cmonth,String labId) {
		JSONObject objmain = new JSONObject();
		JSONArray mainarray = new JSONArray();
		boolean flag=labService.deleteLabTestData(userId,fiscalyear,cmonth,find,counter,testId,type,regNo,labId);
		
		System.out.println("deleteflag "+flag);
		if(flag){
			
			if(labId.equals("0")) {
				List<LabBilling> lablist=labService.counterWisePendingTestWithPatientInfo(userId,counter);
				
				double TotalAmount=0;
				double TotalPrDiscounTaka=0;
				double TotalMDiscounTaka=0;
				double TotalPayableTaka=0;
				
				for(int a=0;a<lablist.size();a++) {

					TotalAmount=TotalAmount+(Double.parseDouble(lablist.get(a).getRate())*Double.parseDouble(lablist.get(a).getQty()));
					TotalPrDiscounTaka=TotalPrDiscounTaka+Double.parseDouble(lablist.get(a).getDiscountAmount());
					TotalMDiscounTaka=Double.parseDouble(lablist.get(a).getManualdiscount()==null?"0":lablist.get(a).getManualdiscount());
				}
				
				for(int a=0;a<lablist.size();a++) {


					JSONObject obj = new JSONObject();

					obj.put("countersearch", "1");
					obj.put("type", lablist.get(a).getTestType());
					obj.put("testId", lablist.get(a).getTestId());
					obj.put("testname", lablist.get(a).getTestname());
					obj.put("rate", lablist.get(a).getRate());
					obj.put("percentdiscount", lablist.get(a).getPercentdiscount());
					obj.put("discountAmount", lablist.get(a).getDiscountAmount());
					obj.put("payable", lablist.get(a).getPayable());
					obj.put("counter", lablist.get(a).getCounter());
					obj.put("labId", lablist.get(a).getLabId());
					obj.put("qty", lablist.get(a).getQty());
					obj.put("find", "0");
					
					obj.put("billtype", lablist.get(a).getBillType());
					obj.put("regno", lablist.get(a).getRegno());
					obj.put("patientname", lablist.get(a).getPatientname());
					obj.put("mobile", lablist.get(a).getMobile());
					obj.put("age", lablist.get(a).getAge());
					obj.put("month", lablist.get(a).getMonth());
					obj.put("day", lablist.get(a).getDay());
					obj.put("sex", lablist.get(a).getSex());
					obj.put("address", lablist.get(a).getAddress());
					obj.put("bedcabin", lablist.get(a).getBedcabin());
					obj.put("referralcomission", lablist.get(a).getReferralcomission());
					obj.put("referral_search", lablist.get(a).getReferral_search());
					obj.put("referralcdegree", lablist.get(a).getReferralcdegree());
					obj.put("referraldegree", lablist.get(a).getReferraldegree());
					
					obj.put("referralId", lablist.get(a).getReferralId());
					obj.put("referralcId", lablist.get(a).getReferralcId());
					

					obj.put("TotalAmount", TotalAmount);
					obj.put("TotalPrDiscounTaka", TotalPrDiscounTaka);
					obj.put("TotalMDiscounTaka", TotalMDiscounTaka);
					
					TotalPayableTaka=TotalAmount-(TotalPrDiscounTaka+TotalMDiscounTaka);
					obj.put("TotalPayableTaka",TotalPayableTaka);
					
					mainarray.add(obj);

				}		
				
				objmain.put("result", mainarray);
				
				return objmain;
			}
			else {
				List<LabBilling> lablist=labService.LabIdWiseTestAndPatientInfo(labId,fiscalyear,cmonth);
				
				double TotalAmount=0;
				double TotalPrDiscounTaka=0;
				double TotalMDiscounTaka=0;
				double TotalPayableTaka=0;
				
				for(int a=0;a<lablist.size();a++) {

					TotalAmount=TotalAmount+(Double.parseDouble(lablist.get(a).getRate())*Double.parseDouble(lablist.get(a).getQty()));
					TotalPrDiscounTaka=TotalPrDiscounTaka+Double.parseDouble(lablist.get(a).getDiscountAmount());
					TotalMDiscounTaka=Double.parseDouble(lablist.get(a).getManualdiscount()==null?"0":lablist.get(a).getManualdiscount());
				}
				
				for(int a=0;a<lablist.size();a++) {


					JSONObject obj = new JSONObject();

					obj.put("countersearch", "1");
					obj.put("type", lablist.get(a).getTestType());
					obj.put("testId", lablist.get(a).getTestId());
					obj.put("testname", lablist.get(a).getTestname());
					obj.put("headId", lablist.get(a).getHeadId());
					obj.put("headname", lablist.get(a).getHeadName());
					obj.put("resultstatus", lablist.get(a).getResultstatus());
					obj.put("rate", lablist.get(a).getRate());
					obj.put("percentdiscount", lablist.get(a).getPercentdiscount());
					obj.put("discountAmount", lablist.get(a).getDiscountAmount());
					obj.put("payable", lablist.get(a).getPayable());
					obj.put("counter", lablist.get(a).getCounter()==null?"":lablist.get(a).getCounter());
					obj.put("labId", lablist.get(a).getLabId());
					obj.put("fiscalYear", lablist.get(a).getFiscalyear());
					obj.put("cMonth", lablist.get(a).getcMonth());
					obj.put("qty", lablist.get(a).getQty());
					
					
					
					obj.put("regno", lablist.get(a).getRegno());
					obj.put("patientname", lablist.get(a).getPatientname());
					obj.put("mobile", lablist.get(a).getMobile());
					obj.put("age", lablist.get(a).getAge());
					obj.put("month", lablist.get(a).getMonth());
					obj.put("day", lablist.get(a).getDay());
					obj.put("sex", lablist.get(a).getSex());
					obj.put("address", lablist.get(a).getAddress());
					obj.put("bedcabin", lablist.get(a).getBedcabin());
					obj.put("referralcomission", lablist.get(a).getReferralcomission());
					obj.put("referral_search", lablist.get(a).getReferral_search());
					obj.put("referralcdegree", lablist.get(a).getReferralcdegree());
					obj.put("referraldegree", lablist.get(a).getReferraldegree());
					
					obj.put("referralId", lablist.get(a).getReferralId());
					obj.put("referralcId", lablist.get(a).getReferralcId());
					

					obj.put("TotalAmount", TotalAmount);
					obj.put("TotalPrDiscounTaka", TotalPrDiscounTaka);
					obj.put("TotalMDiscounTaka", TotalMDiscounTaka);
					
					TotalPayableTaka=TotalAmount-(TotalPrDiscounTaka+TotalMDiscounTaka);
					obj.put("TotalPayableTaka",TotalPayableTaka);
					
					obj.put("paid",lablist.get(a).getPaid());
					obj.put("refund",lablist.get(a).getRefund());
					obj.put("find","1");
					obj.put("fiscalyear",lablist.get(a).getFiscalyear());
					
					mainarray.add(obj);

				}		
				
				objmain.put("result", mainarray);

				System.out.println(objmain.toString());

				return objmain;				
			}

		}
		else {
			objmain.put("result", "Something Wrong!!");
		}
		
		return objmain;
		
	}
	
	@RequestMapping(value = "/BillPost",method=RequestMethod.POST)
	public @ResponseBody String BillPost(LabBilling v) {
		
		System.out.println("v");
		String msg="Create occured while bill posting";
		
		boolean flag=labService.BillPost(v);
		if(flag) {
			msg=v.getLabId();
			//msg="Bill Post Successfully";
		}
		
		return msg;
	}
	
	@RequestMapping(value = "/billAutoSave",method=RequestMethod.POST)
	public @ResponseBody String billAutoSave(LabBilling v) {
		
		System.out.println("v");
		String msg="Create occured while bill posting";
		
		boolean flag=labService.billAutoSave(v);
		if(flag) {
			msg=v.getLabId();
			//msg="Bill Post Successfully";
		}
		
		return msg;
	}
	
	
	@RequestMapping(value = {"/getlabpatientlist/{value}"},method=RequestMethod.GET)
	public @ResponseBody JSONArray getlabpatientlist(@PathVariable ("value") String value) {
		
		System.out.println("value "+value);
		
		JSONArray mainarray = new JSONArray();

		List<LabBilling> patientlist=labService.getlabpatientlist(value);
		for(int a=0;a<patientlist.size();a++) {
			mainarray.add(patientlist.get(a).getPatientname()+"*"+patientlist.get(a).getMobile()+"*"+patientlist.get(a).getFiscalyear()+"*"+patientlist.get(a).getLabId());
		}

		//System.out.println(mainarray.toString());
		return mainarray;
	}
	
	
	@RequestMapping(value = "/refundTransaction",method=RequestMethod.POST)
	public @ResponseBody JSONObject refundTransaction(LabBilling v) {
		JSONObject objmain = new JSONObject();
		
		System.out.println("isLabBillExist "+labService.isLabBillExist(v));
		if(labService.isLabBillExist(v)) {
			
			if(labService.refundTransaction(v)) {
				
			
				List<LabBilling> lablist=labService.LabIdWiseTestAndPatientInfo(v.getLabId(),v.getFiscalyear(),v.getcMonth());
				objmain.put("resultsucess", "Refund Success");
				objmain.put("result", lablist);

			}else {
				objmain.put("result", "Something Wrong");
			}	
		}else {
			objmain.put("result", "Invalid Lab Bill Id");
		}

		return objmain;
	}

	
	@RequestMapping(value = "/LabIdWiseTestAndPatientInfo",method=RequestMethod.GET)
	public @ResponseBody JSONObject LabIdWiseTestAndPatientInfo(String dateValue,String labid,String fiscalyear,String cmonth) {
		JSONObject objmain = new JSONObject();
		JSONArray mainarray = new JSONArray();
		
		List<LabBilling> lablist=labService.LabIdWiseTestAndPatientInfo(labid,fiscalyear,cmonth);
		
		
		for(int a=0;a<lablist.size();a++) {


			JSONObject obj = new JSONObject();

			obj.put("countersearch", "1");
			obj.put("type", lablist.get(a).getTestType());
			obj.put("testId", lablist.get(a).getTestId());
			obj.put("testname", lablist.get(a).getTestname());
			obj.put("headId", lablist.get(a).getHeadId());
			obj.put("headname", lablist.get(a).getHeadName());
			obj.put("resultstatus", lablist.get(a).getResultstatus());
			obj.put("rate", lablist.get(a).getRate());
			obj.put("percentdiscount", lablist.get(a).getPercentdiscount());
			obj.put("discountAmount", lablist.get(a).getDiscountAmount());
			obj.put("discountAmount", lablist.get(a).getDiscountAmount());
			obj.put("payable", lablist.get(a).getPayable());
			obj.put("counter", lablist.get(a).getCounter());
			obj.put("labId", lablist.get(a).getLabId());
			obj.put("qty", lablist.get(a).getQty());
			obj.put("billtype", lablist.get(a).getBillType());
			obj.put("referralId", lablist.get(a).getReferralId());
			obj.put("referralcId", lablist.get(a).getReferralcId());
			obj.put("extraCommission", lablist.get(a).getExtraCommission());
			obj.put("autoId", lablist.get(a).getAutoId());
			obj.put("remark", lablist.get(a).getRemark());
			
			obj.put("regno", lablist.get(a).getRegno());
			obj.put("patientname", lablist.get(a).getPatientname());
			obj.put("mobile", lablist.get(a).getMobile());
			obj.put("age", lablist.get(a).getAge());
			obj.put("month", lablist.get(a).getMonth());
			obj.put("day", lablist.get(a).getDay());
			obj.put("sex", lablist.get(a).getSex());
			obj.put("address", lablist.get(a).getAddress());
			obj.put("bedcabin", lablist.get(a).getBedcabin());
			obj.put("referralcomission", lablist.get(a).getReferralcomission());
			obj.put("referral_search", lablist.get(a).getReferral_search());
			obj.put("referralcdegree", lablist.get(a).getReferralcdegree());
			obj.put("referraldegree", lablist.get(a).getReferraldegree());
			

			obj.put("TotalAmount", lablist.get(a).getTotalCharge());
			obj.put("TotalPrDiscounTaka", lablist.get(a).getPerdiscount_taka());
			obj.put("TotalMDiscounTaka", lablist.get(a).getManualdiscount());
			
			
			obj.put("TotalPayableTaka",lablist.get(a).getTotalpayable());
			
			obj.put("paid",lablist.get(a).getPaid());
			obj.put("refund",lablist.get(a).getRefund());
			obj.put("find","1");
			obj.put("fiscalyear",lablist.get(a).getFiscalyear());
			obj.put("cMonth",lablist.get(a).getcMonth());
			mainarray.add(obj);

		}		
		
		if(lablist.size()>0) {
			objmain.put("result", mainarray);
		}
		else {
			objmain.put("result", "No entry");
		}

		System.out.println(objmain.toString());

		return objmain;
	
	}
	
	
	@RequestMapping(value = "/EditPostedBill",method=RequestMethod.POST)
	public @ResponseBody String EditPostedBill(LabBilling v) {
		
		System.out.println("value "+v.getPatientname());
		String msg="Create occured while edit bill posting";
		
		boolean flag=labService.EditPostedBill(v);
		if(flag) {
			msg="Bill Edit Successfully";
		}
		
		return msg;
	}
	
	@RequestMapping(value = "/CounterInfoDelete",method=RequestMethod.POST)
	public @ResponseBody String CounterInfoDelete(LabBilling v) {
		
		
		String msg="Create occured while delete counter pending bill";
		
		boolean flag=labService.CounterInfoDelete(v);
		if(flag) {
			msg="Pending Bill Clear Successfully";
		}
		
		return msg;
	}

	
	@RequestMapping(value = "/DuePayment",method=RequestMethod.POST)
	public @ResponseBody String DuePayment(LabBilling v) {
		
		String msg="Create occured while due payment";
		
		boolean flag=labService.DuePayment(v);
		if(flag) {
			msg="Due Payment Successfully";
		}
		
		return msg;
	}
	

	@RequestMapping(value = "investigation_result",method=RequestMethod.GET)
	public ModelAndView investigation_result(ModelMap map,HttpSession session) {
		
		
		String userId=(String)session.getAttribute("userId");
		String userName=(String)session.getAttribute("userName");
		
		String labId=labService.getMaxLabId();
		List<MachineCreate> machineList=labService.MachineList();
		List<LabReportCreate> reportTitleList=labService.LabReportTitleList();
		List<LabBilling> billlist=labService.getLabBillList();
		List<DoctorCreate> consultantlist=settingService.getConsultantList();
		ModelAndView view = new ModelAndView("Lab/investigation_result");
		view.addObject("billlist", billlist);
		view.addObject("consultantlist", consultantlist);

		String fiscalYear=new SimpleDateFormat("yyyy").format(new Date());
		map.addAttribute("fiscalYear", fiscalYear);
		map.put("machineList", machineList);
		map.put("reportTitleList", reportTitleList);
		
		map.addAttribute("userId",userId);
		map.addAttribute("userName",userName);
		map.addAttribute("linkName","investigation_result");
		map.addAttribute("labId",labId);
		
		return view; //JSP - /WEB-INF/view/index.jsp
		
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = {"/LabBillWiseTestDetails/"},method=RequestMethod.POST)
	public @ResponseBody JSONObject  LabBillWiseTestDetails(String labId,String fiscalYear,String Cmonth) throws JSONException {


		JSONObject objmain = new JSONObject();

		List<LabBilling> lablist=labService.getLabBillWiseTestDetails(labId,fiscalYear,Cmonth);

		System.out.println("lablistSize "+lablist.size());
		
		objmain.put("result", lablist);


		return objmain;


	}
	
/*	@SuppressWarnings("unchecked")
	@RequestMapping(value = {"/getTestList"},method=RequestMethod.GET)
	public @ResponseBody JSONObject  getTestList() throws JSONException {


		
		JSONObject objmain = new JSONObject();
		JSONArray mainarray = new JSONArray();


		List<Test> lablist=labService.getTestlist();


		for(int a=0;a<lablist.size();a++) {


			JSONObject obj = new JSONObject();

			obj.put("headId", lablist.get(a).getHeadId());
			obj.put("headName", lablist.get(a).getHeadName());
			obj.put("testName", lablist.get(a).getTestName());
			obj.put("rate", lablist.get(a).getRate());
			obj.put("doctorComission", lablist.get(a).getDoctorCommission());
			obj.put("unit", lablist.get(a).getUnit());
			obj.put("normalRange", lablist.get(a).getNormalRange());

			mainarray.add(obj);

		}


		objmain.put("result", mainarray);

		System.out.println(objmain.toString());

		return objmain;


	}*/

	
	@RequestMapping(value = {"/getMainTestList/{value}"},method=RequestMethod.GET)
	public @ResponseBody JSONArray getMainTestList(@PathVariable ("value") String value) {
		
		System.out.println("ha");
		JSONArray mainarray = new JSONArray();

		List<Test> list=labService.getMainTestList(value);
		for(int a=0;a<list.size();a++) {
			mainarray.add(list.get(a).getTestName()+"*"+list.get(a).getTestId()+"*"+list.get(a).getRate());
		}

		System.out.println(mainarray.toString());
		return mainarray;
	}
	

	


	
	
	@RequestMapping(value = "/labsalestatementreport",method=RequestMethod.POST)
	public @ResponseBody JSONObject labsalestatementreport(String startdate,String enddate,String billtype) {
		
		
		JSONObject objmain = new JSONObject();
		JSONArray mainarray = new JSONArray();
		
		List<LabBilling> lablist=labService.LabSaleStatementReport(startdate,enddate,billtype);
		
		double grandTotalCharge=0;
		double grandTotalDiscount=0;
		double grandTotalPayable=0;
		double grandTotalPaid=0;
		double grandTotalRefund=0;
		double grandTotalDue=0;
		
		for(int a=0;a<lablist.size();a++) {
			grandTotalCharge=grandTotalCharge+Double.parseDouble(lablist.get(a).getTotalamount());
			grandTotalDiscount=grandTotalDiscount+Double.parseDouble(lablist.get(a).getTotaldiscount());
			grandTotalPayable=grandTotalPayable+Double.parseDouble(lablist.get(a).getTotalpayable());
			grandTotalPaid=grandTotalPaid+Double.parseDouble(lablist.get(a).getPaid());
			grandTotalRefund=grandTotalRefund+Double.parseDouble(lablist.get(a).getRefund());
			grandTotalDue=grandTotalDue+(Double.parseDouble(lablist.get(a).getTotalpayable())-Double.parseDouble(lablist.get(a).getPaid()));
		}
		
		for(int a=0;a<lablist.size();a++) {


			JSONObject obj = new JSONObject();
			
			obj.put("LabId", lablist.get(a).getLabId());
			obj.put("BillType", lablist.get(a).getBillType());
			obj.put("PatientName", lablist.get(a).getPatientname());
			obj.put("TotalCharge", df.format(Double.parseDouble(lablist.get(a).getTotalamount())));
			obj.put("TotalDiscount", df.format(Double.parseDouble(lablist.get(a).getTotaldiscount())));
			obj.put("TotalPayable", df.format(Double.parseDouble(lablist.get(a).getTotalpayable())));
			obj.put("Paid", df.format(Double.parseDouble(lablist.get(a).getPaid())));
			obj.put("Refund", df.format(Double.parseDouble(lablist.get(a).getRefund())));
			
			double due=Double.parseDouble(lablist.get(a).getTotalpayable())-Double.parseDouble(lablist.get(a).getPaid());
			
			obj.put("Due", df.format(due));
			obj.put("BillDate", lablist.get(a).getBilldate());
			obj.put("Username", lablist.get(a).getUsername());
			obj.put("StartDate", lablist.get(a).getStartdate());
			obj.put("EndDate", lablist.get(a).getEndate());
			
			obj.put("grandTotalCharge", df.format(grandTotalCharge));
			obj.put("grandTotalDiscount", df.format(grandTotalDiscount));
			obj.put("grandTotalPayable", df.format(grandTotalPayable));
			obj.put("grandTotalPaid", df.format(grandTotalPaid));
			obj.put("grandTotalRefund", df.format(grandTotalRefund));
			obj.put("grandTotalDue", df.format(grandTotalDue));
			
			mainarray.add(obj);
		}
		
		objmain.put("result", mainarray);
		System.out.println(objmain.toString());
		
		
		return objmain;
	}
	
	@RequestMapping(value = "/labsalescashstatementreport",method=RequestMethod.POST)
	public @ResponseBody JSONObject labsalescashstatementreport(String startdate,String enddate,String billtype) {
		

		
		JSONObject objmain = new JSONObject();
		JSONArray mainarray = new JSONArray();
		
		List<LabBilling> lablist=labService.LabSalesCashStatementReport(startdate,enddate,billtype);
		
		System.out.println("lablist "+lablist.size());
		
		double TotalAmount=0;
		for(int a=0;a<lablist.size();a++) {


			JSONObject obj = new JSONObject();
			
			obj.put("LabId", lablist.get(a).getLabId());
			obj.put("BillType", lablist.get(a).getBillType());
			obj.put("PatientName", lablist.get(a).getPatientname());
			obj.put("PaymentStatus", lablist.get(a).getPaymentstatus());
			obj.put("BillStatus", lablist.get(a).getBillstatus());
			
			System.out.println("Amountreceived "+lablist.get(a).getAmountreceived());
			
			TotalAmount=TotalAmount+Double.parseDouble(lablist.get(a).getAmountreceived());
			obj.put("AmountReceived", df.format(Double.parseDouble(lablist.get(a).getAmountreceived())));
			obj.put("TotalAmount", TotalAmount);
			
			obj.put("BillDate", lablist.get(a).getBilldate());
			obj.put("ReceivedTime", lablist.get(a).getReceivedtime());
			obj.put("StartDate", lablist.get(a).getStartdate());
			obj.put("EndDate", lablist.get(a).getEndate());
			obj.put("Username", lablist.get(a).getUsername());
			
			mainarray.add(obj);
		}
		
		objmain.put("result", mainarray);
		System.out.println(objmain.toString());
		
		
		return objmain;
	}
	
	@RequestMapping(value = "/labsalesduestatementreport",method=RequestMethod.POST)
	public @ResponseBody JSONObject labsalesduestatementreport(String startdate,String enddate,String billtype) {
		

		
		JSONObject objmain = new JSONObject();
		JSONArray mainarray = new JSONArray();
		
		List<LabBilling> lablist=labService.LabSalesDueStatementReport(startdate,enddate,billtype);
		
		System.out.println("lablist "+lablist.size());
		
		double TotalAmount=0;
		for(int a=0;a<lablist.size();a++) {


			JSONObject obj = new JSONObject();
			
			obj.put("LabId", lablist.get(a).getLabId());
			obj.put("PatientName", lablist.get(a).getPatientname());
			obj.put("ReferralName", lablist.get(a).getReferral_search());
			obj.put("Degree", lablist.get(a).getReferraldegree());
			obj.put("TotalCharge", df.format(Double.parseDouble(lablist.get(a).getTotalamount())));
			obj.put("Discount", df.format(Double.parseDouble(lablist.get(a).getTotaldiscount())));
			obj.put("TotalPayable", df.format(Double.parseDouble(lablist.get(a).getTotalpayable())));
			obj.put("Paid", df.format(Double.parseDouble(lablist.get(a).getPaid())));
			obj.put("Refund", df.format(Double.parseDouble(lablist.get(a).getRefund())));
			obj.put("Due", df.format(Double.parseDouble(lablist.get(a).getDues())));
			obj.put("BillDate", lablist.get(a).getBilldate());
			obj.put("Username", lablist.get(a).getUsername());
			obj.put("StartDate", lablist.get(a).getStartdate());
			obj.put("EndDate", lablist.get(a).getEndate());
			
			mainarray.add(obj);
		}
		
		objmain.put("result", mainarray);
		System.out.println(objmain.toString());
		
		
		return objmain;
	}
	


	@RequestMapping(value = "/departmentwiselabsalestatement",method=RequestMethod.POST)
	public @ResponseBody JSONObject departmentwiselabsalestatement(String startdate,String enddate,String billtype) {
		
		
		
		JSONObject objmain = new JSONObject();
		JSONArray mainarray = new JSONArray();
		
		List<LabBilling> lablist=labService.DepartmentWiseLabSalesStatementReport(startdate,enddate,billtype);
		
		System.out.println("lablist "+lablist.size());
		
		double TotalAmount=0;
		for(int a=0;a<lablist.size();a++) {


			JSONObject obj = new JSONObject();
			

			obj.put("Department", lablist.get(a).getGroupname());
			obj.put("Rate", lablist.get(a).getRate());
			obj.put("Qty", lablist.get(a).getQty());
			obj.put("Amount", df.format(Double.parseDouble(lablist.get(a).getAmount())));
			obj.put("Discount", df.format(Double.parseDouble(lablist.get(a).getTotaldiscount())));
			obj.put("Payable", df.format(Double.parseDouble(lablist.get(a).getTotalpayable())));

			
			mainarray.add(obj);
		}
		
		objmain.put("result", mainarray);
		System.out.println(objmain.toString());
		
		
		return objmain;
	}
	
	@RequestMapping(value = "/testwiseinvestigationstatement",method=RequestMethod.POST)
	public @ResponseBody JSONObject testwiseinvestigationstatement(String startdate,String enddate,String testname,String testall) {
		
		
		System.out.println("testwiseinvestigationstatement");
		
		JSONObject objmain = new JSONObject();
		JSONArray mainarray = new JSONArray();
		
		List<LabBilling> lablist=labService.TestWiseInvestigationStatement(startdate,enddate,testname,testall);
		
		System.out.println("lablist "+lablist.size());
		
		double TotalAmount=0;
		for(int a=0;a<lablist.size();a++) {


			JSONObject obj = new JSONObject();
			

			obj.put("Department", lablist.get(a).getGroupname());
			obj.put("Rate", lablist.get(a).getRate());
			obj.put("Qty", lablist.get(a).getQty());
			obj.put("Amount", df.format(Double.parseDouble(lablist.get(a).getAmount())));
			obj.put("Discount", df.format(Double.parseDouble(lablist.get(a).getTotaldiscount())));
			obj.put("Payable", df.format(Double.parseDouble(lablist.get(a).getTotalpayable())));

			
			mainarray.add(obj);
		}
		
		objmain.put("result", mainarray);
		System.out.println(objmain.toString());
		
		
		return objmain;
	}
	
	@RequestMapping(value = "/labidwisereferracomissionstatement",method=RequestMethod.POST)
	public @ResponseBody JSONObject labidwisereferracomissionstatement(String startdate,String enddate,String RefferalId) {
		
		
		System.out.println("labidwisereferracomissionstatement");
		
		JSONObject objmain = new JSONObject();
		JSONArray mainarray = new JSONArray();
		
		List<LabBilling> lablist=labService.LabIdWiseReferraComissionStatement(startdate,enddate,RefferalId);
		
		System.out.println("lablist "+lablist.size());
		
		double TotalAmount=0;
		for(int a=0;a<lablist.size();a++) {


			JSONObject obj = new JSONObject();
			

			obj.put("RefferName", lablist.get(a).getRefferName());
			obj.put("RefferDegree", lablist.get(a).getRefferDegree());
			obj.put("LabBill", lablist.get(a).getLabBill());
			obj.put("PatientName", lablist.get(a).getPatientName());
			obj.put("PathologyRate", df.format(Double.parseDouble(lablist.get(a).getPathologyRate())));
			obj.put("PathologyNetAmount", df.format(Double.parseDouble(lablist.get(a).getPathologyNetAmount())));
			obj.put("HormoneRate", df.format(Double.parseDouble(lablist.get(a).getHormoneRate())));
			obj.put("HormoneNetAmount", df.format(Double.parseDouble(lablist.get(a).getHormoneNetAmount())));
			obj.put("EchoCardRate", df.format(Double.parseDouble(lablist.get(a).getEchoCardRate())));
			obj.put("EchoCardNetAmount", df.format(Double.parseDouble(lablist.get(a).getEchoCardNetAmount())));
			obj.put("UltraSonoRate", df.format(Double.parseDouble(lablist.get(a).getUltraSonoRate())));
			obj.put("UltraSonoNetAmount", df.format(Double.parseDouble(lablist.get(a).getUltraSonoNetAmount())));
			obj.put("EnDosRate", df.format(Double.parseDouble(lablist.get(a).getEnDosRate())));
			obj.put("EnDosNetAmount", df.format(Double.parseDouble(lablist.get(a).getEnDosNetAmount())));
			obj.put("XrayRate", df.format(Double.parseDouble(lablist.get(a).getXrayRate())));
			obj.put("XrayNetAmount", df.format(Double.parseDouble(lablist.get(a).getXrayNetAmount())));
			obj.put("ECGRate", df.format(Double.parseDouble(lablist.get(a).getECGRate())));
			obj.put("ECGNetAmount", df.format(Double.parseDouble(lablist.get(a).getECGNetAmount())));
			obj.put("HistopathologyRate", df.format(Double.parseDouble(lablist.get(a).getHistopathologyRate())));
			obj.put("HistopathologyNetAmount", df.format(Double.parseDouble(lablist.get(a).getHistopathologyRate())));
			obj.put("BloodGroupRate", df.format(Double.parseDouble(lablist.get(a).getBloodGroupRate())));
			obj.put("BloodGroupNetAmount", df.format(Double.parseDouble(lablist.get(a).getBloodGroupNetAmount())));
			obj.put("FNARate", df.format(Double.parseDouble(lablist.get(a).getFNARate())));
			obj.put("FNANetAmount", df.format(Double.parseDouble(lablist.get(a).getFNANetAmount())));
			obj.put("PepsSemarRate", df.format(Double.parseDouble(lablist.get(a).getPepsSemarRate())));
			obj.put("PepsSemarNetAmount", df.format(Double.parseDouble(lablist.get(a).getPepsSemarNetAmount())));
			obj.put("OthersRate", df.format(Double.parseDouble(lablist.get(a).getOthersRate())));
			obj.put("OthersNetAmount", df.format(Double.parseDouble(lablist.get(a).getOthersNetAmount())));
			obj.put("ManualDiscount", df.format(Double.parseDouble(lablist.get(a).getManualdiscount())));
			obj.put("DoctorDiscount", df.format(Double.parseDouble(lablist.get(a).getDoctorDiscount())));
			
			obj.put("DoctorManualDiscount", df.format(Double.parseDouble(lablist.get(a).getDoctorDiscount())+Double.parseDouble(lablist.get(a).getManualdiscount())));
			
			
			obj.put("TotalPaid", df.format(Double.parseDouble(lablist.get(a).getTotalPaid())));
			
			obj.put("TotalCharge", df.format(Double.parseDouble(lablist.get(a).getTotalCharge())));
			obj.put("PerticularCharge", df.format(Double.parseDouble(lablist.get(a).getPerticularCharge())));
			obj.put("Due", df.format(Double.parseDouble(lablist.get(a).getDue())));
			
			double BillTotal=Double.parseDouble(lablist.get(a).getPathologyNetAmount())+Double.parseDouble(lablist.get(a).getHormoneNetAmount())+Double.parseDouble(lablist.get(a).getEchoCardNetAmount())+Double.parseDouble(lablist.get(a).getUltraSonoNetAmount())+Double.parseDouble(lablist.get(a).getEnDosNetAmount())+Double.parseDouble(lablist.get(a).getXrayNetAmount())+Double.parseDouble(lablist.get(a).getECGNetAmount())+Double.parseDouble(lablist.get(a).getFNANetAmount())+Double.parseDouble(lablist.get(a).getHistopathologyNetAmount())+Double.parseDouble(lablist.get(a).getPepsSemarNetAmount())+Double.parseDouble(lablist.get(a).getBloodGroupNetAmount())+Double.parseDouble(lablist.get(a).getOthersNetAmount());
			
			obj.put("BillTotal", df.format(BillTotal));
			
			obj.put("Date",lablist.get(a).getDate());
			obj.put("StartDate",lablist.get(a).getStartDate());
			obj.put("EndDate",lablist.get(a).getEndDate());
			
			mainarray.add(obj);
		}
		
		objmain.put("result", mainarray);
		System.out.println(objmain.toString());
		
		
		return objmain;
	}
	

	@RequestMapping(value = "/heamatologySaveEvent",method=RequestMethod.POST)
	public @ResponseBody String heamatologySaveEvent(LabResult v) {
		
		String msg="Create occured while Heamatology Result Posting";
		
		boolean flag=labService.HeamatologySaveEvent(v);
		
		System.out.println("flag "+flag);
		
		if(flag) {
			msg="Heamatology Result Save Successfully";
		}
		
		return msg;
	}
	
	@RequestMapping(value = "/setHeamatologySaveData",method=RequestMethod.GET)
	public @ResponseBody JSONObject setHeamatolorySaveData(String testId,String labbill,String fiscalyear,String cMonth) {
	
		String msg="Sucess";
		
		JSONObject objmain = new JSONObject();
		JSONArray mainarray = new JSONArray();
		
		List<LabResult> lablist=labService.setHeamatolorySaveData(testId,labbill,fiscalyear,cMonth);
		
		for(int a=0;a<lablist.size();a++) {

			JSONObject obj = new JSONObject();
			obj.put("Result", lablist.get(a).getResult());
			obj.put("RId", lablist.get(a).getRid());
			obj.put("inchargeId", lablist.get(a).getInchargeId());
			obj.put("doctor1", lablist.get(a).getDoctor1());
			obj.put("doctor2", lablist.get(a).getDoctor2());
			obj.put("machineId", lablist.get(a).getMachineId());
			obj.put("titleId", lablist.get(a).getTitleId());
			mainarray.add(obj);
		}
		
		objmain.put("result", mainarray);
		System.out.println(objmain.toString());
		
		
		return objmain;
	}
	
	@RequestMapping(value = "/urineSaveEvent",method=RequestMethod.POST)
	public @ResponseBody String urineSaveEvent(LabResult v) {
		
		System.out.println("urineSaveEvent ");
		
		String msg="Create occured while Urine Result Posting";
		
		boolean flag=labService.UrineSaveEvent(v);
		
		System.out.println("flag "+flag);
		
		if(flag) {
			msg="Urine Result Save Successfully";
		}
		
		return msg;
	}
	
	@RequestMapping(value = "/stoolSaveEvent",method=RequestMethod.POST)
	public @ResponseBody String stoolSaveEvent(LabResult v) {
		
		System.out.println("stoolSaveEvent ");
		
		String msg="Create occured while Stool Result Posting";
		
		boolean flag=labService.StoolSaveEvent(v);
		
		System.out.println("flag "+flag);
		
		if(flag) {
			msg="Stool Result Save Successfully";
		}
		
		return msg;
	}
	
	@RequestMapping(value = "/microbiologySaveEvent",method=RequestMethod.POST)
	public @ResponseBody String microbiologySaveEvent(LabResult v) {
		
		System.out.println("microbiologySaveEvent ");
		
		String msg="Create occured while Microbiology Result Posting";
		
		boolean flag=labService.MicrobiologySaveEvent(v);
		
		System.out.println("flag "+flag);
		
		if(flag) {
			msg="Microbiology Result Save Successfully";
		}
		
		return msg;
	}
	
	@RequestMapping(value = "/BioSerHormoneTestData/{value}",method=RequestMethod.GET)
	public @ResponseBody JSONObject BioChemestryTestData(@PathVariable ("value") String value) {
	
		String msg="Sucess";
		
		JSONObject objmain = new JSONObject();
		JSONArray mainarray = new JSONArray();
		
		List<LabResult> lablist=labService.BioSerHormoneTestData(value);
		
		for(int a=0;a<lablist.size();a++) {

			JSONObject obj = new JSONObject();
			obj.put("TestId", lablist.get(a).getTestId());
			obj.put("MainTestId", lablist.get(a).getMainTestId());
			obj.put("TestName", lablist.get(a).getTestName());
			obj.put("Unit", lablist.get(a).getUnit());
			obj.put("NormalRange", lablist.get(a).getNormalRange());
			obj.put("MainTestName", lablist.get(a).getMainTestName());
			obj.put("inchargeId", lablist.get(a).getInchargeId());
			obj.put("doctor1", lablist.get(a).getDoctor1());
			obj.put("doctor2", lablist.get(a).getDoctor2());
			obj.put("machineId", lablist.get(a).getMachineId());
			obj.put("titleId", lablist.get(a).getTitleId());
			mainarray.add(obj);
		}
		
		objmain.put("result", mainarray);
		System.out.println(objmain.toString());
		
		
		return objmain;
	}
	
	@RequestMapping(value = "/biochemestrySaveEvent",method=RequestMethod.POST)
	public @ResponseBody String biochemestrySaveEvent(LabResult v) {
		
		System.out.println("biochemestrySaveEvent ");
		
		String msg="Create occured while BioChemestry Result Posting";
		
		boolean flag=labService.biochemestrySaveEvent(v);
		
		System.out.println("flag "+flag);
		
		if(flag) {
			msg="BioChemestry Result Save Successfully";
		}
		
		return msg;
	}
	
	@RequestMapping(value = "/setBioSerHormoneTestData",method=RequestMethod.POST)
	public @ResponseBody JSONObject setBioSerHormoneTestData(String testcodelist,String labbill,String headId,String fiscalyear,String cMonth) {
	
		System.out.println("headId "+headId);
		String msg="Sucess";
		
		JSONObject objmain = new JSONObject();
		JSONArray mainarray = new JSONArray();
		
		JSONArray conmainarray = new JSONArray();
		
		List<LabResult> lablist=labService.setBioSerHormoneTestData(testcodelist,labbill,headId,fiscalyear,cMonth);
		
		for(int a=0;a<lablist.size();a++) {

			JSONObject obj = new JSONObject();
			obj.put("MainTestId", lablist.get(a).getMainTestId());
			obj.put("TestId", lablist.get(a).getTestId());
			obj.put("TestName", lablist.get(a).getTestName());
			obj.put("Unit", lablist.get(a).getUnit());
			obj.put("Result", lablist.get(a).getResult());
			obj.put("Sorting", lablist.get(a).getSorting());
			obj.put("NormalRange", lablist.get(a).getNormalRange());
			obj.put("MainTestName", lablist.get(a).getMainTestName());
			
			obj.put("inchargeId", lablist.get(a).getInchargeId());
			obj.put("doctor1", lablist.get(a).getDoctor1());
			obj.put("doctor2", lablist.get(a).getDoctor2());
			obj.put("machineId", lablist.get(a).getMachineId());
			obj.put("titleId", lablist.get(a).getTitleId());
			
			mainarray.add(obj);
		}
		
		objmain.put("result", mainarray);
		
		//Confirmatory
		List<LabResult> conlist=labService.getConfirmatorSaveData(testcodelist,labbill,headId,fiscalyear,cMonth);
		

		for(int a=0;a<conlist.size();a++) {

			JSONObject obj = new JSONObject();
			obj.put("Result", conlist.get(a).getResult());
			obj.put("RId", conlist.get(a).getRid());
			obj.put("inchargeId", conlist.get(a).getInchargeId());
			obj.put("doctor1", conlist.get(a).getDoctor1());
			obj.put("doctor2", conlist.get(a).getDoctor2());
			obj.put("machineId", lablist.get(a).getMachineId());
			obj.put("titleId", lablist.get(a).getTitleId());
			conmainarray.add(obj);
		}
		
		objmain.put("resultcon", conmainarray);

		
		
		return objmain;
	}
	
	@RequestMapping(value = "/hormoneConfirmatortySaveEvent",method=RequestMethod.POST)
	public @ResponseBody String hormoneConfirmatortySaveEvent(LabResult v) {
		
		String msg="Create occured while Hormone Result Posting";
		
		boolean flag=labService.hormoneConfirmatortySaveEvent(v);
		
		System.out.println("flag "+flag);
		
		if(flag) {
			msg="Hormone Result Save Successfully";
		}
		
		return msg;
	}
	
	@RequestMapping(value = "/immunologyConfirmatortySaveEvent",method=RequestMethod.POST)
	public @ResponseBody String immunologyConfirmatortySaveEvent(LabResult v) {
		
		String msg="Create occured while Immunology Result Posting";
		
		boolean flag=labService.immunologyConfirmatortySaveEvent(v);
		
		System.out.println("flag "+flag);
		
		if(flag) {
			msg="Immunology Result Save Successfully";
		}
		
		return msg;
	}
	
	@RequestMapping(value = "/investiationReportParam",method=RequestMethod.POST)
	public @ResponseBody String investiationReportParam(String labbill,String fiscalYear,String cMonth,String headId,String testIdlist,String inchargeId,String doctor1,String doctor2,String note,String horReportCategory,String immReportCategory,String machineId,String titleId) {
		
		String msg="Due to error report preview can't possible";
	
		this.labbill=labbill;
		this.fiscalYear=fiscalYear;
		this.cMonth=cMonth;
		this.headId=headId;
		this.testIdlist=testIdlist;
		this.inchargeId=inchargeId;
		this.doctor1=doctor1;
		this.doctor2=doctor2;
		this.note=note;
		this.horReportCategory=horReportCategory;
		this.immReportCategory=immReportCategory;
		
		this.machineId=machineId;
		this.titleId=titleId;
		
		if(!labbill.equals("0") || !labbill.equals("")) {
			msg="Success";
		}
		
		
		return msg;
	}
	
	
	@RequestMapping(value = "/printBiochemistryReport",method=RequestMethod.GET)
	public @ResponseBody ModelAndView printBiochemistryReport(ModelMap map) {
		
		ModelAndView view=new ModelAndView("Lab/printBiochemestryReport");
		
		
		map.addAttribute("labbill", labbill);
		map.addAttribute("fiscalYear", fiscalYear);
		map.addAttribute("cMonth", cMonth);
		map.addAttribute("headId",headId);
		map.addAttribute("testIdlist",testIdlist);
		map.addAttribute("note", note);
		map.addAttribute("inchargeId", inchargeId);
		map.addAttribute("doctor1", doctor1);
		map.addAttribute("doctor2", doctor2);
		
		map.addAttribute("machineId", machineId);
		map.addAttribute("titleId", titleId);
		return view;
	}
	
	@RequestMapping(value = "/serologySaveEvent",method=RequestMethod.POST)
	public @ResponseBody String serologySaveEvent(LabResult v) {
		
		System.out.println("serologySaveEvent ");
		
		String msg="Create occured while Serology Result Posting";
		
		boolean flag=labService.SerologySaveEvent(v);
		
		System.out.println("flag "+flag);
		
		if(flag) {
			msg="Serology Result Save Successfully";
		}
		
		return msg;
	}
	

	
	@RequestMapping(value = "/printSerologyReport",method=RequestMethod.GET)
	public @ResponseBody ModelAndView printSerologyReport(ModelMap map) {
		
		ModelAndView view=new ModelAndView("Lab/printSerologyReportView");
		
		map.addAttribute("labbill", labbill);
		map.addAttribute("fiscalYear", fiscalYear);
		map.addAttribute("cMonth", cMonth);
		map.addAttribute("headId",headId);
		map.addAttribute("testIdlist",testIdlist);
		map.addAttribute("note", note);
		map.addAttribute("inchargeId", inchargeId);
		map.addAttribute("doctor1", doctor1);
		map.addAttribute("doctor2", doctor2);
		map.addAttribute("machineId", machineId);
		map.addAttribute("titleId", titleId);

		return view;
	}
	
	@RequestMapping(value = "/printHeamatologyReport/{idList}",method=RequestMethod.GET)
	public @ResponseBody ModelAndView heamatprintHeamatologyReportologyPrintEvent(ModelMap map,@PathVariable ("idList") String idList) {
		
		ModelAndView view=new ModelAndView("Lab/heamatologyreportview");
		
		String id[] = idList.split("@");
		map.addAttribute("labbill", id[0]);
		map.addAttribute("fiscalYear", id[1]);
		map.addAttribute("cMonth", id[2]);
		map.addAttribute("headid", id[3]);
		map.addAttribute("testId", id[4]);
		map.addAttribute("inchargeId", id[5]);
		map.addAttribute("doctor1", id[6]);
		map.addAttribute("doctor2", id[7]);
		map.addAttribute("machineId", id[8]);
		map.addAttribute("titleId", id[9]);

		return view;
	}
	
	
	@RequestMapping(value = "/hormoneSaveEvent",method=RequestMethod.POST)
	public @ResponseBody String hormoneSaveEvent(LabResult v) {
		
		
		
		String msg="Create occured while Hormone Result Posting";
		
		boolean flag=labService.HormoneSaveEvent(v);
		
		System.out.println("flag "+flag);
		
		if(flag) {
			msg="Hormone Result Save Successfully";
		}
		
		return msg;
	}
	
	
	@RequestMapping(value = "/printHormoneReport",method=RequestMethod.GET)
	public @ResponseBody ModelAndView printHormoneReport(ModelMap map) {
		
		String link="";
		if(horReportCategory.equals("0")) {
			link="Lab/printHormoneReportView";
		}
		else if(horReportCategory.equals("1")) {
			link="Lab/printHormoneReportViewV2";
		}
		
		System.out.println("link "+link);
		
		ModelAndView view=new ModelAndView(link);
		
		map.addAttribute("labbill", labbill);
		map.addAttribute("fiscalYear", fiscalYear);
		map.addAttribute("cMonth", cMonth);
		map.addAttribute("headId",headId);
		map.addAttribute("testIdlist",testIdlist);
		map.addAttribute("note", note);
		map.addAttribute("inchargeId", inchargeId);
		map.addAttribute("doctor1", doctor1);
		map.addAttribute("doctor2", doctor2);
		map.addAttribute("machineId", machineId);
		map.addAttribute("titleId", titleId);

		return view;
	}
	
	
	@RequestMapping(value = "/immunologySaveEvent",method=RequestMethod.POST)
	public @ResponseBody String immunologySaveEvent(LabResult v) {
		
		
		
		String msg="Create occured while Immunology Result Posting";
		
		boolean flag=labService.ImmunologySaveEvent(v);
		
		System.out.println("flag "+flag);
		
		if(flag) {
			msg="Immunology Result Save Successfully";
		}
		
		return msg;
	}
	
	@RequestMapping(value = "/printImmunologyReport",method=RequestMethod.GET)
	public @ResponseBody ModelAndView printImmunologyReport(ModelMap map) {
		
		String link="";
		if(immReportCategory.equals("0")) {
			link="Lab/printImmunologyReportView";
		}
		else if(immReportCategory.equals("1")) {
			link="Lab/printImmunologyReportViewV2";
		}
		
		
		ModelAndView view=new ModelAndView(link);
		
		map.addAttribute("labbill", labbill);
		map.addAttribute("fiscalYear", fiscalYear);
		map.addAttribute("cMonth", cMonth);
		map.addAttribute("headId",headId);
		map.addAttribute("testIdlist",testIdlist);
		map.addAttribute("note", note);
		map.addAttribute("inchargeId", inchargeId);
		map.addAttribute("doctor1", doctor1);
		map.addAttribute("doctor2", doctor2);
		map.addAttribute("machineId", machineId);
		map.addAttribute("titleId", titleId);

		return view;
	}
	
	@RequestMapping(value = "/setUrineSaveData",method=RequestMethod.GET)
	public @ResponseBody JSONObject setUrineSaveData(String testId,String labbill,String fiscalyear,String cMonth) {
	
		String msg="Sucess";
		
		JSONObject objmain = new JSONObject();
		JSONArray mainarray = new JSONArray();
		
		List<LabResult> lablist=labService.setUrineSaveData(testId,labbill,fiscalyear,cMonth);
		
		for(int a=0;a<lablist.size();a++) {

			JSONObject obj = new JSONObject();
			obj.put("Result", lablist.get(a).getResult());
			obj.put("RId", lablist.get(a).getRid());
			obj.put("inchargeId", lablist.get(a).getInchargeId());
			obj.put("doctor1", lablist.get(a).getDoctor1());
			obj.put("doctor2", lablist.get(a).getDoctor2());
			obj.put("machineId", lablist.get(a).getMachineId());
			obj.put("titleId", lablist.get(a).getTitleId());
			mainarray.add(obj);
		}
		
		objmain.put("result", mainarray);
		System.out.println(objmain.toString());
		
		
		return objmain;
	}
	
	@RequestMapping(value = "/setStoolSaveData",method=RequestMethod.GET)
	public @ResponseBody JSONObject setStoolSaveData(String testId,String labbill,String fiscalyear,String cMonth) {
	
		String msg="Sucess";
		
		JSONObject objmain = new JSONObject();
		JSONArray mainarray = new JSONArray();
		
		List<LabResult> lablist=labService.setStoolSaveData(testId,labbill,fiscalyear,cMonth);
		
		for(int a=0;a<lablist.size();a++) {

			JSONObject obj = new JSONObject();
			obj.put("Result", lablist.get(a).getResult());
			obj.put("RId", lablist.get(a).getRid());
			obj.put("inchargeId", lablist.get(a).getInchargeId());
			obj.put("doctor1", lablist.get(a).getDoctor1());
			obj.put("doctor2", lablist.get(a).getDoctor2());
			obj.put("machineId", lablist.get(a).getMachineId());
			obj.put("titleId", lablist.get(a).getTitleId());
			mainarray.add(obj);
		}
		
		objmain.put("result", mainarray);
		System.out.println(objmain.toString());
		
		
		return objmain;
	}
	
	@RequestMapping(value = "/setMicrobiologySaveData",method=RequestMethod.GET)
	public @ResponseBody JSONObject setMicrobiologySaveData(String testId,String labbill,String fiscalyear,String cMonth) {
	
		String msg="Sucess";
		
		JSONObject objmain = new JSONObject();
		JSONArray mainarray = new JSONArray();
		
		List<LabResult> lablist=labService.setMicrobiologSaveData(testId,labbill,fiscalyear,cMonth);
		
		for(int a=0;a<lablist.size();a++) {

			JSONObject obj = new JSONObject();
			obj.put("Result", lablist.get(a).getResult());
			obj.put("RId", lablist.get(a).getRid());
			obj.put("inchargeId", lablist.get(a).getInchargeId());
			obj.put("doctor1", lablist.get(a).getDoctor1());
			obj.put("doctor2", lablist.get(a).getDoctor2());
			obj.put("machineId", lablist.get(a).getMachineId());
			obj.put("titleId", lablist.get(a).getTitleId());
			mainarray.add(obj);
		}
		
		objmain.put("result", mainarray);
		System.out.println(objmain.toString());
		
		
		return objmain;
	}
	
	@RequestMapping(value = "/printTopReport/{idList}",method=RequestMethod.GET)
	public @ResponseBody ModelAndView printTopReport(ModelMap map,@PathVariable ("idList") String idList) {
		
		System.out.println("Top"); 
		ModelAndView view=new ModelAndView("Lab/printTopReport");
		
		String id[] = idList.split("@");
		map.addAttribute("labbill", id[0]);
		map.addAttribute("fiscalYear", id[1]);
		map.addAttribute("cMonth", id[2]);
		map.addAttribute("headId", id[3]);
		map.addAttribute("testIdlist", id[4]);
		map.addAttribute("inchargeId", id[5]);
		map.addAttribute("doctor1", id[6]);
		map.addAttribute("doctor2", id[7]);
		map.addAttribute("machineId", id[8]);
		map.addAttribute("titleId", id[9]);

		return view;
	}

	
	@RequestMapping(value = "/printTopReportFromBilling/{idList}",method=RequestMethod.GET)
	public @ResponseBody ModelAndView printTopReportFromBilling(ModelMap map,@PathVariable ("idList") String idList) {
		
		System.out.println("Top1"); 
		ModelAndView view=new ModelAndView("Lab/printTopReportFromBilling");
		
		String id[] = idList.split("@");
		map.addAttribute("labId", id[0]);
		map.addAttribute("fiscalYear", id[1]); 
		map.addAttribute("cMonth", id[2]);


		return view;
	}
	
	@RequestMapping(value = "/printUrineReport/{idList}",method=RequestMethod.GET)
	public @ResponseBody ModelAndView printUrineReport(ModelMap map,@PathVariable ("idList") String idList) {
		
		ModelAndView view=new ModelAndView("Lab/urinereportview");
		
		String id[] = idList.split("@");
		map.addAttribute("labbill", id[0]);
		map.addAttribute("fiscalYear", id[1]);
		map.addAttribute("cMonth", id[2]);
		map.addAttribute("headId", id[3]);
		map.addAttribute("testId", id[4]);
		map.addAttribute("inchargeId", id[5]);
		map.addAttribute("doctor1", id[6]);
		map.addAttribute("doctor2", id[7]);
		map.addAttribute("machineId", id[8]);
		map.addAttribute("titleId", id[9]);

		return view;
	}
	
	@RequestMapping(value = "/printMicrobiologyReport/{idList}",method=RequestMethod.GET)
	public @ResponseBody ModelAndView printMicrobiologyReport(ModelMap map,@PathVariable ("idList") String idList) {
		
		
		
		String id[] = idList.split("@");
		map.addAttribute("labbill", id[0]);
		map.addAttribute("fiscalYear", id[1]);
		map.addAttribute("cMonth", id[2]);
		map.addAttribute("headId", id[3]);
		map.addAttribute("testId", id[4]);
		map.addAttribute("inchargeId", id[5]);
		map.addAttribute("doctor1", id[6]);
		map.addAttribute("doctor2", id[7]);
		map.addAttribute("micreport", id[8]);
		map.addAttribute("machineId", id[9]);
		map.addAttribute("titleId", id[10]);
		
		String reportType=id[7];
		String link="";
		if(reportType.equals("0")) {
			 link="Lab/microreportviewgrowth";
		}
		else if(reportType.equals("1")) {
			 link="Lab/microreportviewnongrowth";
		}
		
		ModelAndView view=new ModelAndView(link);

		return view;
	}
	
	@RequestMapping(value = "/printStoolReport/{idList}",method=RequestMethod.GET)
	public @ResponseBody ModelAndView printStoolReport(ModelMap map,@PathVariable ("idList") String idList) {
		
		System.out.println("stoll");
		ModelAndView view=new ModelAndView("Lab/stoolreportview");
		
		String id[] = idList.split("@");
		map.addAttribute("labbill", id[0]);
		map.addAttribute("fiscalYear", id[1]);
		map.addAttribute("cMonth", id[2]);
		map.addAttribute("headId", id[3]);
		map.addAttribute("testId", id[4]);
		map.addAttribute("inchargeId", id[5]);
		map.addAttribute("doctor1", id[6]);
		map.addAttribute("doctor2", id[7]);
		map.addAttribute("machineId", id[8]);
		map.addAttribute("titleId", id[9]);
		

		return view;
	}
	
	
	@RequestMapping(value = "/printBercodeLab/{idList}",method=RequestMethod.GET)
	public @ResponseBody ModelAndView printBercodeLab(ModelMap map,@PathVariable ("idList") String idList) {
		
		System.out.println("stoll");
		ModelAndView view=new ModelAndView("Lab/bercodereportview");
		
		String id[] = idList.split("@");
		map.addAttribute("labbill", id[0]);
		map.addAttribute("fiscalYear", id[1]);
		

		return view;
	}
	
	@RequestMapping(value = "lab_sale_statement",method=RequestMethod.GET)
	public ModelAndView lab_sale_statement(ModelMap map,HttpSession session) {
		
		ModelAndView view = new ModelAndView("Lab/lab-sale-statement");
		
		
		map.addAttribute("linkName","lab_sale_statement");
		
		
		return view; //JSP - /WEB-INF/view/index.jsp
	}
	
	@RequestMapping(value = "/labSaleStatement/{fromDate}/{toDate}/{patientType}/{reportType}",method=RequestMethod.GET)
	public @ResponseBody ModelAndView labSaleStatement(ModelMap map,@PathVariable ("fromDate") String fromDate,@PathVariable ("toDate") String toDate,@PathVariable ("patientType") String patientType,@PathVariable ("reportType") String reportType) {
		ModelAndView view=new ModelAndView("Lab/labSaleStatementReportView");
		
		map.addAttribute("fromDate", fromDate);
		map.addAttribute("toDate", toDate);
		map.addAttribute("patientType", patientType);
		map.addAttribute("reportType", reportType);
		
		return view;
	}
	
	@RequestMapping(value = "lab_sales_cash_statement",method=RequestMethod.GET)
	public ModelAndView lab_sales_cash_statement(ModelMap map) {
		ModelAndView view = new ModelAndView("Lab/lab-sale-cash-statement");
		
		map.addAttribute("linkName","lab_sales_cash_statement");
		
		
		return view; //JSP - /WEB-INF/view/index.jsp
	}
	
	@RequestMapping(value = "/labSaleCashStatement/{fromDate}/{toDate}/{patientType}",method=RequestMethod.GET)
	public @ResponseBody ModelAndView labSaleCashStatement(ModelMap map,@PathVariable ("fromDate") String fromDate,@PathVariable ("toDate") String toDate,@PathVariable ("patientType") String patientType) {
		ModelAndView view=new ModelAndView("Lab/labSaleCashStatementReportView");
		
		map.addAttribute("fromDate", fromDate);
		map.addAttribute("toDate", toDate);
		map.addAttribute("patientType", patientType);
		
		return view;
	}
	
	@RequestMapping(value = "lab_sales_due_statement",method=RequestMethod.GET)
	public ModelAndView lab_sales_due_statement(ModelMap map) {
		ModelAndView view = new ModelAndView("Lab/lab-sale-due-statement");
		
		map.addAttribute("linkName","lab_sales_due_statement");
		
		
		return view; //JSP - /WEB-INF/view/index.jsp
	}
	
	@RequestMapping(value = "/labSaleDueStatement/{fromDate}/{toDate}/{patientType}",method=RequestMethod.GET)
	public @ResponseBody ModelAndView labSaleDueStatement(ModelMap map,@PathVariable ("fromDate") String fromDate,@PathVariable ("toDate") String toDate,@PathVariable ("patientType") String patientType) {
		ModelAndView view=new ModelAndView("Lab/labSaleDueStatementReportView");
		
		map.addAttribute("fromDate", fromDate);
		map.addAttribute("toDate", toDate);
		map.addAttribute("patientType", patientType);
		
		return view;
	}
	
	@RequestMapping(value = "lab_due_received_statement",method=RequestMethod.GET)
	public ModelAndView lab_due_received_statement(ModelMap map) {
		ModelAndView view = new ModelAndView("Lab/lab_due_received_statement");
		
		map.addAttribute("linkName","lab_due_received_statement");
		
		
		return view; //JSP - /WEB-INF/view/index.jsp
	}
	
	@RequestMapping(value = "/dueReceivedStatementPreview/{fromDate}/{toDate}/{patientType}",method=RequestMethod.GET)
	public @ResponseBody ModelAndView dueReceivedStatementPreview(ModelMap map,@PathVariable ("fromDate") String fromDate,@PathVariable ("toDate") String toDate,@PathVariable ("patientType") String patientType) {
		ModelAndView view=new ModelAndView("Lab/dueReceivedStatementPreview");
		
		map.addAttribute("fromDate", fromDate);
		map.addAttribute("toDate", toDate);
		map.addAttribute("patientType", patientType);
		
		return view;
	}
	
	@RequestMapping(value = "all_referral_comission_statement",method=RequestMethod.GET)
	public ModelAndView all_referral_comission_statement(ModelMap map) {
		
		List<DoctorCreate> doctorlist=settingService.getDoctorList();
		ModelAndView view = new ModelAndView("Lab/all_referral_comission_statement");
		view.addObject("doctorlist", doctorlist);
		
		map.addAttribute("linkName","all_referral_comission_statement");

		
		return view; //JSP - /WEB-INF/view/index.jsp
	}
	
	@RequestMapping(value = "/allReferralCommissionStatement/{fromDate}/{toDate}/{doctorId}",method=RequestMethod.GET)
	public @ResponseBody ModelAndView allReferralCommissionStatement(ModelMap map,@PathVariable ("fromDate") String fromDate,@PathVariable ("toDate") String toDate,@PathVariable ("doctorId") String doctorId) {
		ModelAndView view=new ModelAndView("Lab/allReferralCommissionStatementReportView");
		
		map.addAttribute("fromDate", fromDate);
		map.addAttribute("toDate", toDate);
		map.addAttribute("doctorId", doctorId);
		
		return view;
	}
	
	@RequestMapping(value = "lab_id_wise_referral_comission_statement",method=RequestMethod.GET)
	public ModelAndView lab_id_wise_referral_comission_statement(ModelMap map) {
		
		List<DoctorCreate> doctorlist=settingService.getDoctorList();
		ModelAndView view = new ModelAndView("Lab/lab_id_wise_referral_comission_statement");
		view.addObject("doctorlist", doctorlist);
		
		map.addAttribute("linkName","lab_id_wise_referral_comission_statement");

		
		return view; //JSP - /WEB-INF/view/index.jsp
	}
	
	@RequestMapping(value = "/lab_id_wise_referral_comission_statement/{fromDate}/{toDate}/{doctorId}",method=RequestMethod.GET)
	public @ResponseBody ModelAndView lab_id_wise_referral_comission_statement(ModelMap map,@PathVariable ("fromDate") String fromDate,@PathVariable ("toDate") String toDate,@PathVariable ("doctorId") String doctorId) {
		ModelAndView view=new ModelAndView("Lab/LabIdReferralCommissionStatementReportView");
		
		map.addAttribute("fromDate", fromDate);
		map.addAttribute("toDate", toDate);
		map.addAttribute("doctorId", doctorId);
		
		return view;
	}
	
	@RequestMapping(value = "consultant_create",method=RequestMethod.GET)
	public ModelAndView consultant_create(ModelMap map,HttpSession session) {
		
		String userId=(String)session.getAttribute("userId");
		String userName=(String)session.getAttribute("userName");
		
		String id=labService.getMaxConsultantId();
		ModelAndView view = new ModelAndView("Lab/consultant-create");
		map.addAttribute("consultantId",id);
		
		map.addAttribute("userId",userId);
		map.addAttribute("userName",userName);
		map.addAttribute("linkName","consultant_create");
		

		return view; //JSP - /WEB-INF/view/index.jsp
	}
	
	@RequestMapping(value = "/allConsultantName", method = RequestMethod.POST)
	public @ResponseBody List<ConsultantCreate> departmentDetails(ConsultantCreate v) {
		
		List<ConsultantCreate> consultantCreate = labService.getConsultantList();
		return consultantCreate;

	}
	
	@RequestMapping(value = "/getMaxConsultantId", method = RequestMethod.POST)
	public @ResponseBody String getMaxConsultantId() {

		String colsultantId=labService.getMaxConsultantId();
		
		
		return colsultantId; 
	}
	
	@RequestMapping(value = "/saveConsultant", method = RequestMethod.POST)
	public @ResponseBody String saveConsultant(ConsultantCreate v) {

		if(!labService.isConsultantExist(v)) {
			boolean saveConsultant = labService.saveConsultant(v);
			return "success";
		}else {
			return "duplicate";
		}
	}

	@RequestMapping(value = "/editConsultant", method = RequestMethod.POST)
	public @ResponseBody String editConsultant(ConsultantCreate v) {

		if(!labService.isConsultantExist(v)) {
			boolean editConsultant = labService.editConsultant(v);
			return "success";
		}else {
			return "duplicate";
		}

	}
	
	//Bercode Genarate
	@RequestMapping(value = "bercode_print",method=RequestMethod.GET)
	public ModelAndView bercode_print(ModelMap map,HttpSession session) {
		

		String userId=(String)session.getAttribute("userId");
		String userName=(String)session.getAttribute("userName");
		String labId=labService.getMaxLabId();
		
		List<DoctorCreate> doctorlist=settingService.getDoctorList();
		List<Test> mainTestlist=labService.getParentTestlist();
		List<PatientRegistration> indoorRuningPatientList=labService.getRuningPatientList();
		List<LabBilling> billlist=labService.getLabBillList();
		ModelAndView view = new ModelAndView("Lab/bercode_print");
		view.addObject("doctorlist", doctorlist);
		view.addObject("mainTestlist", mainTestlist);
		view.addObject("indoorRuningPatientList", indoorRuningPatientList);
		view.addObject("billlist", billlist);
		
		String fiscalYear=new SimpleDateFormat("yyyy").format(new Date());
		map.addAttribute("fiscalYear", fiscalYear);
		map.addAttribute("labId", labId);
		
		map.addAttribute("userId",userId);
		map.addAttribute("userName",userName);
		
		map.addAttribute("linkName","bercode_print");
		
		
		return view; //JSP - /WEB-INF/view/index.jsp
		
	}
	
	
	@RequestMapping(value = "machine_create",method=RequestMethod.GET)
	public ModelAndView machine_create(ModelMap map,HttpSession session) {
		
		String userId=(String)session.getAttribute("userId");
		String userName=(String)session.getAttribute("userName");

		List<TestGroup> grouplist=labService.getTestGrouplist();
		ModelAndView view = new ModelAndView("Lab/machine_create");
		view.addObject("grouplist", grouplist);

		map.addAttribute("userId",userId);
		map.addAttribute("userName",userName);
		
		map.addAttribute("linkName","machine_create");
		
		
		return view; //JSP - /WEB-INF/view/index.jsp
		
	}
	
	//Machine List
	
	@RequestMapping(value = "/MachineList",method=RequestMethod.POST)
	public @ResponseBody JSONObject MachineList() {

		
		JSONObject objmain = new JSONObject();
		JSONArray mainarray = new JSONArray();
		
		List<MachineCreate> machineList=labService.MachineList();
		objmain.put("result", machineList);

		return objmain;

	}
	
	@RequestMapping(value = "/addMachine", method = RequestMethod.POST)
	public @ResponseBody String addMachine(MachineCreate v) {

		if(!labService.isMachineExist(v)) {
			boolean saveConsultant = labService.saveMachineInfo(v);
			if(saveConsultant) {
				return "Success";
			}
			else {
				return "Information Not save due to error";
			}
			
		}else {
			return "duplicate";
		}
	}
	
	@RequestMapping(value = "/editMachine", method = RequestMethod.POST)
	public @ResponseBody String editMachine(MachineCreate v) {

		if(!labService.isMachineExist(v)) {
			boolean saveConsultant = labService.editMachineInfo(v);
			if(saveConsultant) {
				return "Success";
			}
			else {
				return "Information Not save due to error";
			}
			
		}else {
			return "duplicate";
		}
	}
	
	@RequestMapping(value = "report_title_create",method=RequestMethod.GET)
	public ModelAndView report_title_create(ModelMap map,HttpSession session) {
		
		String userId=(String)session.getAttribute("userId");
		String userName=(String)session.getAttribute("userName");
		
		List<TestGroup> grouplist=labService.getTestGrouplist();
		ModelAndView view = new ModelAndView("Lab/lab_report_title_create");
		view.addObject("grouplist", grouplist);
		
		map.addAttribute("userId",userId);
		map.addAttribute("userName",userName);
		
		map.addAttribute("linkName","report_title_create");
		


		return view; //JSP - /WEB-INF/view/index.jsp
		
	}
	
	@RequestMapping(value = "/addLabReportTitle", method = RequestMethod.POST)
	public @ResponseBody String addLabReportTitle(LabReportCreate v) {

		if(!labService.isLabReportTitleExist(v)) {
			boolean saveConsultant = labService.saveLabReportTitleInfo(v);
			if(saveConsultant) {
				return "Success";
			}
			else {
				return "Information Not save due to error";
			}
			
		}else {
			return "duplicate";
		}
	}
	
	@RequestMapping(value = "/LabReportTitleList",method=RequestMethod.POST)
	public @ResponseBody JSONObject LabReportTitleList() {

		
		JSONObject objmain = new JSONObject();
		JSONArray mainarray = new JSONArray();
		
		List<LabReportCreate> machineList=labService.LabReportTitleList();
		objmain.put("result", machineList);

		return objmain;

	}
	
	@RequestMapping(value = "/editLabReportTitle", method = RequestMethod.POST)
	public @ResponseBody String editLabReportTitle(LabReportCreate v) {

		if(!labService.isLabReportTitleExist(v)) {
			boolean saveConsultant = labService.editLabReportTitleInfo(v);
			if(saveConsultant) {
				return "Success";
			}
			else {
				return "Information Not save due to error";
			}
			
		}else {
			return "duplicate";
		}
	}
	
	@RequestMapping(value = "department_wise_lab_sale_statement",method=RequestMethod.GET)
	public ModelAndView department_wise_lab_sale_statement(ModelMap map) {
		
		List<DoctorCreate> doctorlist=settingService.getDoctorList();
		ModelAndView view = new ModelAndView("Lab/department_wise_lab_sale_statement");
		view.addObject("doctorlist", doctorlist);
		
		map.addAttribute("linkName","department_wise_lab_sale_statement");

		
		return view; //JSP - /WEB-INF/view/index.jsp
	}
	
	@RequestMapping(value = "/departmentWiseSaleStatementPreview/{fromDate}/{toDate}/{patientType}",method=RequestMethod.GET)
	public @ResponseBody ModelAndView departmentWiseSaleStatementPreview(ModelMap map,@PathVariable ("fromDate") String fromDate,@PathVariable ("toDate") String toDate,@PathVariable ("patientType") String patientType) {
		
		System.out.println("fromDate "+fromDate);
		System.out.println("toDate "+toDate);
		System.out.println("patientType "+patientType);
		ModelAndView view=new ModelAndView("Lab/departmentwiselabsalestatementReportView");
		
		map.addAttribute("fromDate", fromDate);
		map.addAttribute("toDate", toDate);
		map.addAttribute("patientType", patientType);
		
		return view;
	}
	
	@RequestMapping(value = "test_wise_investigation_statement",method=RequestMethod.GET)
	public ModelAndView test_wise_investigation_statement(ModelMap map) {
		
		List<DoctorCreate> doctorlist=settingService.getDoctorList();
		List<Test> mainTestlist=labService.getParentTestlist();
		ModelAndView view = new ModelAndView("Lab/test_wise_investigation_statement");
		view.addObject("doctorlist", doctorlist);
		
		map.addAttribute("linkName","test_wise_investigation_statement");

		
		map.addAttribute("mainTestlist",mainTestlist);
		
		return view; //JSP - /WEB-INF/view/index.jsp
	}
	
	@RequestMapping(value = "/testWiseInvestigationStatementPreview/{fromDate}/{toDate}/{testId}",method=RequestMethod.GET)
	public @ResponseBody ModelAndView testWiseInvestigationStatementPreview(ModelMap map,@PathVariable ("fromDate") String fromDate,@PathVariable ("toDate") String toDate,@PathVariable ("testId") String testId) {
		
		System.out.println("fromDate "+fromDate);
		System.out.println("toDate "+toDate);

		ModelAndView view=new ModelAndView("Lab/testwiselabsalestatementReportView");
		
		map.addAttribute("fromDate", fromDate);
		map.addAttribute("toDate", toDate);
		map.addAttribute("testId", testId);
		
		return view;
	}
	
	@RequestMapping(value = "department_wise_investigation_statement",method=RequestMethod.GET)
	public ModelAndView department_wise_investigation_statement(ModelMap map) {
		
		List<TestGroup> grouplist=labService.getPathologyGroup();
		ModelAndView view = new ModelAndView("Lab/department_wise_investigation_statement");
		view.addObject("grouplist", grouplist);
		
		map.addAttribute("linkName","department_wise_investigation_statement");

		
		return view; //JSP - /WEB-INF/view/index.jsp
	}
	
	@RequestMapping(value = "/departmentWiseInvestigationStatementPreview/{fromDate}/{toDate}/{patientType}/{headId}",method=RequestMethod.GET)
	public @ResponseBody ModelAndView departmentWiseInvestigationStatementPreview(ModelMap map,@PathVariable ("fromDate") String fromDate,@PathVariable ("toDate") String toDate,@PathVariable ("patientType") String patientType,@PathVariable ("headId") String headId) {
		
		System.out.println("fromDate "+fromDate);
		System.out.println("toDate "+toDate);
		System.out.println("patientType "+patientType);
		ModelAndView view=new ModelAndView("Lab/departmentWiseInvestigationStatementPreview");
		
		map.addAttribute("fromDate", fromDate);
		map.addAttribute("toDate", toDate);
		map.addAttribute("patientType", patientType);
		map.addAttribute("headId", headId);
		
		return view;
	}
	
	@RequestMapping(value = "envelope_print",method=RequestMethod.GET)
	public ModelAndView envelope_print(ModelMap map) {
		
		List<DoctorCreate> doctorlist=settingService.getDoctorList();
		ModelAndView view = new ModelAndView("Lab/envelope_print");
		view.addObject("doctorlist", doctorlist);
		
		map.addAttribute("linkName","envelope_print");

		
		return view; //JSP - /WEB-INF/view/index.jsp
	}
	
	@RequestMapping(value = "/PrintEnvelope/{doctorId}",method=RequestMethod.GET)
	public @ResponseBody ModelAndView PrintEnvelope(ModelMap map,@PathVariable ("doctorId") String doctorId) {
		
	
		ModelAndView view=new ModelAndView("Lab/EnvelopePrintPreview");
		map.addAttribute("doctorId", doctorId);
		
		return view;
	}
	
	@RequestMapping(value = "remark_list_lab_billing",method=RequestMethod.GET)
	public ModelAndView remark_list_lab_billing(ModelMap map) {
		
	
		ModelAndView view = new ModelAndView("Lab/remark_list_lab_billing");
		
		return view; //JSP - /WEB-INF/view/index.jsp
	}
	
	@RequestMapping(value = "/remarkListPreview/{fromDate}/{toDate}",method=RequestMethod.GET)
	public @ResponseBody ModelAndView remarkListPreview(ModelMap map,@PathVariable ("fromDate") String fromDate,@PathVariable ("toDate") String toDate) {
		

		ModelAndView view=new ModelAndView("Lab/remarkListPreview");
		
		map.addAttribute("fromDate", fromDate);
		map.addAttribute("toDate", toDate);

		
		return view;
	}
	
	@RequestMapping(value = "extra_commission_list",method=RequestMethod.GET)
	public ModelAndView extra_commission_list(ModelMap map) {
		
	
		ModelAndView view = new ModelAndView("Lab/extra_commission_list");
		
		return view; //JSP - /WEB-INF/view/index.jsp
	}
	
	@RequestMapping(value = "/extraCommissionListPreview/{fromDate}/{toDate}",method=RequestMethod.GET)
	public @ResponseBody ModelAndView extraCommissionListPreview(ModelMap map,@PathVariable ("fromDate") String fromDate,@PathVariable ("toDate") String toDate) {
		

		ModelAndView view=new ModelAndView("Lab/extraCommissionListPreview");
		
		map.addAttribute("fromDate", fromDate);
		map.addAttribute("toDate", toDate);

		
		return view;
	}
}
