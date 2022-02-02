package pg.controller;

import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpSession;

import org.json.JSONArray;
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
import pg.AccountsModel.AccountsDetails;
import pg.AccountsModel.ChartOfAccount;
import pg.AccountsModel.ChequeBookCreate;
import pg.AccountsModel.CostCenterCreate;
import pg.AccountsModel.DailyStatement;
import pg.AccountsModel.LedgerCreate;
import pg.AccountsModel.TrialBalance;
import pg.AccountsModel.Voucher;
import pg.services.AccountService;
import pg.services.SettingService;

@Controller
public class AccountsController {
	@Autowired
	private AccountService accountService;
	
	
	@Autowired
	private SettingService settingService;
	
	@RequestMapping(value = "account_create",method=RequestMethod.GET)
	public ModelAndView account_create(ModelMap map,HttpSession session) {
		
		String userId=(String)session.getAttribute("userId");
		System.out.println("userId "+userId);
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
		List<AccountsCreate> headlist=accountService.getHeadlist();
		List<AccountsCreate> acclist=accountService.getAccountHead();
		ModelAndView view = new ModelAndView("accounts/account_create");
		view.addObject("acclist", acclist);
		view.addObject("headlist", headlist);
		map.addAttribute("edit",edit);
		map.addAttribute("delete",delete);
		return view; //JSP - /WEB-INF/view/index.jsp
		
	}
	
	
	@RequestMapping(value = "/saveHead",method=RequestMethod.POST)
	public @ResponseBody JSONObject saveHead(AccountsCreate v) {
		JSONObject objmain = new JSONObject();
		if(!accountService.isHeadExist(v)) {
			
			if(accountService.addHead(v)) {
				
				objmain.put("result", "Sucess");
				List<AccountsCreate> headlist=accountService.getHeadlist();
				objmain.put("result", headlist);

			}else {
				objmain.put("result", "Something Wrong");
			}	
		}else {
			objmain.put("result", "duplicate");
		}

		return objmain;
	}

	@RequestMapping(value = "/editHead",method=RequestMethod.POST)
	public @ResponseBody JSONObject editHead(AccountsCreate v) {
		JSONObject objmain = new JSONObject();
		if(!accountService.isHeadExist(v)) {
			
			if(accountService.editHead(v)) {
				
				objmain.put("result", "Sucess");
				List<AccountsCreate> headlist=accountService.getHeadlist();
				objmain.put("result", headlist);

			}else {
				objmain.put("result", "Something Wrong");
			}	
		}else {
			objmain.put("result", "duplicate");
		}

		return objmain;
	}
	
	@RequestMapping(value = "/deleteHead",method=RequestMethod.POST)
	public @ResponseBody JSONObject deleteHead(String headId) {
		JSONObject objmain = new JSONObject();
		if(!accountService.isHeadHasTransaction(headId)) {
			
			if(accountService.deleteHead(headId)) {
				
				objmain.put("result", "Sucess");
				List<AccountsCreate> headlist=accountService.getHeadlist();
				objmain.put("result", headlist);

			}else {
				objmain.put("result", "Something Wrong");
			}	
		}else {
			objmain.put("result", "Head has many transaction plase contact to system administrator");
		}

		return objmain;
	}
	
	@RequestMapping(value = "acc_ledger_create",method=RequestMethod.GET)
	public ModelAndView acc_ledger_create(ModelMap map) {
		
		List<LedgerCreate> ledgerlist=accountService.getLedgerlist();
		List<AccountsCreate> acclist=accountService.getAccountHead();
		ModelAndView view = new ModelAndView("accounts/ledger_create");
		view.addObject("acclist", acclist);
		view.addObject("ledgerlist", ledgerlist);
		return view; //JSP - /WEB-INF/view/index.jsp
		
	}
	
	@RequestMapping(value = "/saveLedger",method=RequestMethod.POST)
	public @ResponseBody JSONObject saveLedger(LedgerCreate v) {
		JSONObject objmain = new JSONObject();
		if(!accountService.isLedgerExist(v)) {
			
			if(accountService.addLedger(v)) {
				
		
				List<LedgerCreate> headlist=accountService.getLedgerlist();
				objmain.put("result", headlist);

			}else {
				objmain.put("result", "Something Wrong");
			}	
		}else {
			objmain.put("result", "duplicate");
		}

		return objmain;
	}
	
	@RequestMapping(value = "/editLedger",method=RequestMethod.POST)
	public @ResponseBody JSONObject editLedger(LedgerCreate v) {
		JSONObject objmain = new JSONObject();
		if(!accountService.isLedgerExist(v)) {
			
			if(accountService.editLedger(v)) {
				
			
				List<LedgerCreate> ledgerlist=accountService.getLedgerlist();
				objmain.put("result", ledgerlist);

			}else {
				objmain.put("result", "Something Wrong");
			}	
		}else {
			objmain.put("result", "duplicate");
		}

		return objmain;
	}
	
	
	@RequestMapping(value = "all_payment_voucher_entry",method=RequestMethod.GET)
	public ModelAndView all_payment_voucher_entry(ModelMap map) {
		List<LedgerCreate> cashledgerlist=accountService.getCashLedgerlist();
		List<CostCenterCreate> costCenterlist=accountService.getCostCenterlist();
		String maxVoucherNo=accountService.getMaxVoucherNo("1");
		System.out.println("maxVoucherNo "+maxVoucherNo);
		ModelAndView view = new ModelAndView("accounts/acc_all_payment_voucher_entry");
		
		view.addObject("cashledgerlist", cashledgerlist);
		view.addObject("costCenterlist", costCenterlist);
		map.addAttribute("maxVoucherNo",maxVoucherNo);
		
		return view; //JSP - /WEB-INF/view/index.jsp
		
	}
	
	@RequestMapping(value = "/getLedgerList",method=RequestMethod.GET)
	public @ResponseBody JSONObject getLedgerList(ChequeBookCreate v) {
		
		
		JSONObject objmain = new JSONObject();
		
		List<LedgerCreate> ledgerlist=accountService.getLedgerlist();
		objmain.put("ledgerresult", ledgerlist);

		return objmain;
	}
	
	@RequestMapping(value = "typeWiseVoucherNo/{voucherType}",method=RequestMethod.POST)
	public @ResponseBody String typeWiseVoucherNo(@PathVariable ("voucherType") String voucherType) {
		
		String maxVoucherNo=accountService.getTypeWiseVoucherNo(voucherType);
		return maxVoucherNo;
	}
	
	
	@RequestMapping(value = "/saveAllTypesVoucher",method=RequestMethod.POST)
	public @ResponseBody String saveAllTypesVoucher(Voucher v) {
		
		String msg="Create Occue while create payment voucher";
		
		boolean flag=accountService.saveAllTypesVoucher(v);
		
		if(flag) {
			msg="Voucher Create Successfully";
		}

		return msg;
	}
	
	@RequestMapping(value = "/getPaymentVoucherListForApprove",method=RequestMethod.POST)
	public @ResponseBody JSONObject getPaymentVoucherListForApprove(String voucherNoSearch,String fromDate,String toDate,String voucherType,String approveType) {
		
		JSONObject objmain = new JSONObject();
		List<Voucher> paymentVoucherListApprove=accountService.getPaymentVoucherListForApprove(voucherNoSearch,fromDate,toDate,voucherType,approveType);
		objmain.put("paymentVoucherList", paymentVoucherListApprove);

		return objmain;
	}
	
	@RequestMapping(value = "/JournalVoucherPrint/{idList}",method=RequestMethod.GET)
	public @ResponseBody ModelAndView JournalVoucherPrint(ModelMap map,@PathVariable ("idList") String idList) {
		
		
		ModelAndView view=new ModelAndView("accounts/VoucherPrint");
		
		String id[] = idList.split("@");
		map.addAttribute("voucherNo", id[0]);
		map.addAttribute("paymentType", id[1]);
		
		return view;
	}
	
	@RequestMapping(value = "acc_voucher_approve_list",method=RequestMethod.GET)
	public ModelAndView acc_voucher_approve_list(ModelMap map) {
		

		ModelAndView view = new ModelAndView("accounts/acc_voucher_approve_list");
		return view; //JSP - /WEB-INF/view/index.jsp
		
	}
	
	@RequestMapping(value = "/ApproveVoucher",method=RequestMethod.POST)
	public @ResponseBody String ApproveVoucher(Voucher v) {
		
		String msg="Create Occue while create voucher approve";
		
		boolean flag=accountService.approveVoucher(v);
		
		if(flag) {
			msg="Voucher Approve Successfully";
		}

		return msg;
	}
	
	@RequestMapping(value = "acc_report_by_ledger",method=RequestMethod.GET)
	public ModelAndView acc_report_by_ledger(ModelMap map) {
		
		List<LedgerCreate> ledgerlist=accountService.getLedgerlist();
		ModelAndView view = new ModelAndView("accounts/acc_report_by_ledger");
		
		view.addObject("ledgerlist", ledgerlist);
		return view; //JSP - /WEB-INF/view/index.jsp
		
	}
	
	@RequestMapping(value = "/PreviewLedgerReport/{idList}",method=RequestMethod.GET)
	public @ResponseBody ModelAndView PreviewLedgerReport(ModelMap map,@PathVariable ("idList") String idList) {
		
		
		ModelAndView view=new ModelAndView("accounts/PreviewLedgerReport");
		
		String id[] = idList.split("@");
		map.addAttribute("fromDate", id[0]);
		map.addAttribute("toDate", id[1]);
		map.addAttribute("reference", id[2]);
		
		return view;
	}
	
	@RequestMapping(value = "/PreviewLedgerReportD/{idList}",method=RequestMethod.GET)
	public @ResponseBody ModelAndView PreviewLedgerReportD(ModelMap map,@PathVariable ("idList") String idList) {
		
		
		ModelAndView view=new ModelAndView("accounts/PreviewLedgerReportD");
		
		String id[] = idList.split("@");
		map.addAttribute("fromDate", id[0]);
		map.addAttribute("toDate", id[1]);
		map.addAttribute("LedgerId", id[2]);
		
		return view;
	}
	
	@RequestMapping(value = "/PreviewLedgerReportBal/{idList}",method=RequestMethod.GET)
	public @ResponseBody ModelAndView PreviewLedgerReportBal(ModelMap map,@PathVariable ("idList") String idList) {
		
		
		ModelAndView view=new ModelAndView("accounts/PreviewLedgerReportBal");
		
		String id[] = idList.split("@");
		map.addAttribute("fromDate", id[0]);
		map.addAttribute("LedgerId", id[1]);
		
		return view;
	}
	
	@RequestMapping(value = "acc_profit_and_loss",method=RequestMethod.GET)
	public ModelAndView acc_profit_and_loss(ModelMap map) {
		
		ModelAndView view = new ModelAndView("accounts/acc_profit_and_loss");
		return view; //JSP - /WEB-INF/view/index.jsp
		
	}
	
	@RequestMapping(value = "/PreviewPALReport/{idList}",method=RequestMethod.GET)
	public @ResponseBody ModelAndView PreviewPALReport(ModelMap map,@PathVariable ("idList") String idList) {
		
		
		ModelAndView view=new ModelAndView("accounts/PreviewPALReport");
		
		String id[] = idList.split("@");
		map.addAttribute("fromDate", id[0]);
		map.addAttribute("toDate", id[1]);
		
		return view;
	}
	
	@RequestMapping(value = "/ViewProfitAndLoss",method=RequestMethod.POST)
	public @ResponseBody JSONObject ViewProfitAndLoss(String fromDate,String toDate) {
		
		JSONObject objmain = new JSONObject();
		List<AccountsDetails> profitlist=accountService.getProfitAndLoss(fromDate,toDate);
		objmain.put("result", profitlist);
		return objmain;
	}
	
	@RequestMapping(value = "/ViewHeadWiseProfitAndLoss",method=RequestMethod.POST)
	public @ResponseBody JSONObject ViewHeadWiseProfitAndLoss(String fromDate,String toDate,String headName,String headType) {
		
		JSONObject objmain = new JSONObject();
		List<AccountsDetails> pallist=accountService.geHeadWisetPALHistory(fromDate,toDate,headName,headType);
		
		List<AccountsDetails> palLedgerlist=accountService.geHeadWiseLedgerPALHistory(fromDate,toDate,headName,headType);
		
		objmain.put("result", pallist);
		objmain.put("ledger_result", palLedgerlist);
		return objmain;
	}
	
	
	@RequestMapping(value = "acc_received_payment_statement",method=RequestMethod.GET)
	public ModelAndView acc_received_payment_statement(ModelMap map) {

		ModelAndView view = new ModelAndView("accounts/acc_received_payment_statement");
		return view; //JSP - /WEB-INF/view/index.jsp
		
	}
	
	@RequestMapping(value = "/PreviewPaymentReceiptReport/{idList}",method=RequestMethod.GET)
	public @ResponseBody ModelAndView PreviewPaymentReceiptReport(ModelMap map,@PathVariable ("idList") String idList) {
		
		
		ModelAndView view=new ModelAndView("accounts/PreviewPaymentReceiptReport");
		
		String id[] = idList.split("@");
		map.addAttribute("fromDate", id[0]);
		map.addAttribute("toDate", id[1]);
		
		return view;
	}
	
	@RequestMapping(value = "acc_balance_sheet",method=RequestMethod.GET)
	public ModelAndView acc_balance_sheet(ModelMap map) {
		
		ModelAndView view = new ModelAndView("accounts/acc_balance_sheet");
		return view; //JSP - /WEB-INF/view/index.jsp
		
	}
	
	@RequestMapping(value = "/viewBalanceReport",method=RequestMethod.POST)
	public @ResponseBody JSONObject viewBalanceReport(String fromDate,String toDate,String transactionType ) {
		

		JSONObject objmain = new JSONObject();
		List<AccountsDetails> balancesheetlist=accountService.getBalanceSheetData(fromDate,toDate,transactionType);
		objmain.put("result", balancesheetlist);
		return objmain;
	}
	
	@RequestMapping(value = "/ViewHeadWiseBalanceSheet",method=RequestMethod.POST)
	public @ResponseBody JSONObject ViewHeadWiseBalanceSheet(String fromDate,String toDate,String headName,String headType) {
		
		JSONObject objmain = new JSONObject();
		List<AccountsDetails> ballist=accountService.geHeadWisetBalHistory(fromDate,headName,headType);
		
		List<AccountsDetails> balLedgerlist=accountService.geHeadWiseLedgerBalHistory(fromDate,headName,headType);
		
		objmain.put("result", ballist);
		objmain.put("ledger_result", balLedgerlist);
		return objmain;
	}
	
	@RequestMapping(value = "/PreviewBalanceReport/{idList}",method=RequestMethod.GET)
	public @ResponseBody ModelAndView PreviewBalanceReport(ModelMap map,@PathVariable ("idList") String idList) {
		
		
		ModelAndView view=new ModelAndView("accounts/PreviewBalanceReport");
		
		String id[] = idList.split("@");
		map.addAttribute("date", id[0]);
		map.addAttribute("transactionType", id[1]);
		
		return view;
	}
	
	@RequestMapping(value = "acc_trial_balance",method=RequestMethod.GET)
	public ModelAndView acc_trial_balance(ModelMap map) {
		
		ModelAndView view = new ModelAndView("accounts/acc_trial_balance");
		return view; //JSP - /WEB-INF/view/index.jsp
		
	}
	

	
	@RequestMapping(value = "/ViewTrialBalance",method=RequestMethod.POST)
	public @ResponseBody JSONObject ViewTrialBalanceReport(String fromDate,String toDate,String transactionType) {
		
		JSONObject objmain = new JSONObject();
		List<TrialBalance> triallist=accountService.getTrialBalanceHistory(fromDate,toDate,transactionType);
		objmain.put("result", triallist);
		return objmain;
	}
	
	@RequestMapping(value = "/ViewHeadWiseTrialBalance",method=RequestMethod.POST)
	public @ResponseBody JSONObject ViewHeadWiseTrialBalance(String fromDate,String toDate,String headName,String transactionType) {
		
		JSONObject objmain = new JSONObject();
		List<TrialBalance> triallist=accountService.geHeadWisetTrialBalanceHistory(fromDate,toDate,headName,transactionType);
		objmain.put("result", triallist);
		return objmain;
	}
	
	@RequestMapping(value = "/ViewHeadAndLedgerWiseTrialBalance",method=RequestMethod.POST)
	public @ResponseBody JSONObject ViewHeadAndLedgerWiseTrialBalance(String fromDate,String toDate,String headName) {
		
		JSONObject objmain = new JSONObject();
		List<TrialBalance> triallist=accountService.geHeadAndLedgerWiseTrialBalanceHistory(fromDate,toDate,headName);
		objmain.put("result", triallist);
		return objmain;
	}
	
	@RequestMapping(value = "/PreviewTrialBalanceReport/{idList}",method=RequestMethod.GET)
	public @ResponseBody ModelAndView PreviewTrialBalanceReport(ModelMap map,@PathVariable ("idList") String idList) {
		
		
		ModelAndView view=new ModelAndView("accounts/PreviewTrialBalanceReport");
		
		String id[] = idList.split("@");
		map.addAttribute("fromDate", id[0]);
		map.addAttribute("toDate", id[1]);
		map.addAttribute("transactionType", id[2]);
		return view;
	}
	
	
	@RequestMapping(value = "acc_daily_statement",method=RequestMethod.GET)
	public ModelAndView acc_daily_statement(ModelMap map) {
		List<LedgerCreate> cashledgerlist=accountService.getCashLedgerlist();
		ModelAndView view = new ModelAndView("accounts/acc_daily_statement");
		
		view.addObject("cashledgerlist", cashledgerlist);
		
		return view; //JSP - /WEB-INF/view/index.jsp
		
	}
	
	@RequestMapping(value = "StatementViewList",method=RequestMethod.POST)
	public @ResponseBody JSONObject StatementViewList(String fromDate,String toDate,String statementType) {
		
		System.out.println("Call");
		JSONObject ob=new JSONObject();
		JSONArray arr=new JSONArray();
		List<DailyStatement> dailStatementlist=accountService.StatementViewList(fromDate,toDate,statementType);

		ob.put("result", dailStatementlist);
		
		return ob;
	}
	
	@RequestMapping(value = "statementIncomeDetails",method=RequestMethod.POST)
	public @ResponseBody JSONObject statementIncomeDetails(String fromDate,String toDate,String headName) {
		
		System.out.println("HI");
		JSONObject ob=new JSONObject();
		JSONArray arr=new JSONArray();
		List<DailyStatement> dailStatementlist=accountService.statementIncomeDetails(fromDate,toDate,headName);
		List<DailyStatement> dailStatementDiscountlist=accountService.statementDiscountDetails(fromDate,toDate,headName);
		List<DailyStatement> dailStatementCashlist=accountService.statementCashDetails(fromDate,toDate,headName);
		
		ob.put("result_sale", dailStatementlist);
		ob.put("result_discount", dailStatementDiscountlist);
		ob.put("result_cash", dailStatementCashlist);
		return ob;
	}
	
	@RequestMapping(value = "statementExpenseDetails",method=RequestMethod.POST)
	public @ResponseBody JSONObject statementExpenseDetails(String fromDate,String toDate,String headName) {
		
		
		JSONObject ob=new JSONObject();
		JSONArray arr=new JSONArray();
		List<DailyStatement> dailStatementlist=accountService.statementExpenseDetails(fromDate,toDate,headName);
		
		ob.put("result_expense", dailStatementlist);
		return ob;
	}
	
	@RequestMapping(value = "/PreviewDailyStatement/{idList}",method=RequestMethod.GET)
	public @ResponseBody ModelAndView PreviewDailyStatement(ModelMap map,@PathVariable ("idList") String idList) {
		
		
		ModelAndView view=new ModelAndView("accounts/PreviewDailyStatement");
		
		String id[] = idList.split("@");
		map.addAttribute("fromDate", id[0]);
		map.addAttribute("toDate", id[1]);
		map.addAttribute("statementType", id[2]);
		
		return view;
	}
	
	@RequestMapping(value = "acc_day_book",method=RequestMethod.GET)
	public ModelAndView acc_day_book(ModelMap map) {
		

		ModelAndView view = new ModelAndView("accounts/acc_day_book");

		return view; //JSP - /WEB-INF/view/index.jsp
		
	}
	
	@RequestMapping(value = "/viewAllBookReport",method=RequestMethod.POST)
	public @ResponseBody JSONObject viewAllBookReport(String fromDate,String toDate,String voucherType) {
		
		JSONObject objmain = new JSONObject();
		List<Voucher> allBookReport=accountService.getAllBookReport(fromDate,toDate,voucherType);
		objmain.put("result", allBookReport);

		return objmain;
	}
	
	@RequestMapping(value = "/PreviewAllBooReport/{idList}",method=RequestMethod.GET)
	public @ResponseBody ModelAndView PreviewAllBooReport(ModelMap map,@PathVariable ("idList") String idList) {
		
		
		ModelAndView view=new ModelAndView("accounts/AllBookReportPrint");
		
		String id[] = idList.split("@");
		map.addAttribute("fromDate", id[0]);
		map.addAttribute("toDate", id[1]);
		map.addAttribute("voucherType", id[2]);
		
		return view;
	}
	
	@RequestMapping(value = "acc_bank_book_report",method=RequestMethod.GET)
	public ModelAndView acc_bank_book_report(ModelMap map) {
		

		ModelAndView view = new ModelAndView("accounts/acc_book_report");
		map.addAttribute("bookType", "Bank Book");
		
		
		return view; //JSP - /WEB-INF/view/index.jsp
		
	}
	
	
	@RequestMapping(value = "/viewCashBankBooReport",method=RequestMethod.POST)
	public @ResponseBody JSONObject viewCashBankBooReport(String fromDate,String toDate,String voucherType) {
		
		JSONObject objmain = new JSONObject();
		List<Voucher> allBookReport=accountService.getCashBankBooReport(fromDate,toDate,voucherType);
		objmain.put("result", allBookReport);

		return objmain;
	}
	
	@RequestMapping(value = "/PreviewBooReport/{idList}",method=RequestMethod.GET)
	public @ResponseBody ModelAndView PreviewBooReport(ModelMap map,@PathVariable ("idList") String idList) {
		
		
		ModelAndView view=new ModelAndView("accounts/BookReportPrint");
		
		String id[] = idList.split("@");
		map.addAttribute("fromDate", id[0]);
		map.addAttribute("toDate", id[1]);
		map.addAttribute("bookType", id[2]);
		
		return view;
	}
	
	@RequestMapping(value = "acc_cash_book_report",method=RequestMethod.GET)
	public ModelAndView acc_cash_book_report(ModelMap map) {
		

		ModelAndView view = new ModelAndView("accounts/acc_book_report");
		map.addAttribute("bookType", "Cash Book");
		
		
		return view; //JSP - /WEB-INF/view/index.jsp
		
	}

	@RequestMapping(value = "chartOfAccountData",method=RequestMethod.GET)
	public @ResponseBody JSONObject chartOfAccountData(ModelMap map) {

		JSONObject ob=new JSONObject();
		JSONArray arr=new JSONArray();
		List<ChartOfAccount> headlist=accountService.getAllHeadlist();

		ob.put("result", headlist);
		
		System.out.println("ob "+ob);
		

		return ob;
	}
	
}
