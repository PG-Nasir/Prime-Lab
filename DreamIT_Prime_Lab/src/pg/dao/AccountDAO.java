package pg.dao;

import java.util.List;

import pg.AccountsModel.AccountsCreate;
import pg.AccountsModel.AccountsSummary;
import pg.AccountsModel.BankReconcilation;
import pg.AccountsModel.ChartOfAccount;
import pg.AccountsModel.ChequeBookCreate;
import pg.AccountsModel.CostCenterCreate;
import pg.AccountsModel.DailyStatement;
import pg.AccountsModel.LedgerCreate;
import pg.AccountsModel.AccountsDetails;
import pg.AccountsModel.TrialBalance;
import pg.AccountsModel.Voucher;

import pg.LabModel.Test;


public interface AccountDAO {
	List<AccountsCreate> getAccountHead();
	boolean isHeadExist(AccountsCreate v);
	boolean addHead(AccountsCreate v);
	List<AccountsCreate> getHeadlist();
	
	boolean editHead(AccountsCreate v);
	
	boolean isLedgerExist(LedgerCreate v);
	boolean addLedger(LedgerCreate v);
	List<LedgerCreate> getLedgerlist();
	boolean editLedger(LedgerCreate v);
	
	//Cheque Book Generate
	boolean isChequeExist(ChequeBookCreate v);
	boolean addChequeBookSerialRange(ChequeBookCreate v);
	List<ChequeBookCreate> getChequeBookSerialList();
	List<ChequeBookCreate> getChequeBookDetails(String ledgerId, String chequeBookNo);
	boolean editChequeBookSerialRange(ChequeBookCreate v);
	
	//Cost Center
	List<CostCenterCreate> getCostCenterlist();
	boolean saveVoucher(Voucher v);
	List<LedgerCreate> getCashLedgerlist();
	List<LedgerCreate> getBankLedgerlist();
	
	List<Voucher> getPaymentVoucherList(String voucherNoSearch, String fromDate, String toDate,
			String paymentType);
	String getMaxVoucherNo(String PaymentType);
	boolean saveJournalVoucher(Voucher v);
	
	List<Voucher> getPaymentVoucherListForApprove(String voucherNoSearch, String fromDate,
			String toDate, String voucherType, String approveType);
	boolean approveVoucher(Voucher v);
	List<LedgerCreate> getCashBankLedgerList();
	
	List<Voucher> getChequeList(String fromDate, String toDate,String vocuherType,
			String chequeNumber);
	
	boolean checquePass(Voucher v);
	List<Voucher> getChequeBookReport(String vocuherType, String chequeBookNo, String ledgerId);
	
	List<ChartOfAccount> getAllHeadlist();
	List<AccountsSummary> getAccountSummary(String fromDate,String toDate,String headId);
	
	boolean TransferTransaction(String transactionOnDate, String transferFrom, String transferTo,String userId,String amount);
	String getTransactionAmount(String transactionOnDate, String transferFrom);
	
	List<TrialBalance> getTrialBalanceHistory(String fromDate,String toDate,String transactionType);
	List<TrialBalance> geHeadWisetTrialBalanceHistory(String fromDate, String toDate, String title,String trasactionType);
	List<TrialBalance> geHeadAndLedgerWiseTrialBalanceHistory(String fromDate, String toDate, String headName);
	
	List<AccountsDetails> getProfitAndLoss(String fromDate, String toDate);
	List<AccountsDetails> geHeadWisetPALHistory(String fromDate, String toDate, String headName,String headType);
	List<AccountsDetails> geHeadWiseLedgerPALHistory(String fromDate, String toDate, String headName, String headType);
	
	List<AccountsDetails> getBalanceSheetData(String fromDate, String toDate,String transactionType);
	List<AccountsDetails> geHeadWisetBalHistory(String fromDate,String headName, String headType);
	List<AccountsDetails> geHeadWiseLedgerBalHistory(String fromDate, String headName, String headType);
	
	List<Voucher> getAllBookReport(String fromDate, String toDate, String voucherType);
	
	List<Voucher> getCashBankBooReport(String fromDate, String toDate, String voucherType);
	
	boolean saveAllTypesVoucher(Voucher v);
	
	List<DailyStatement> StatementViewList(String fromDate, String toDate, String statementType);
	List<DailyStatement> statementIncomeDetails(String fromDate, String toDate, String headName);
	List<DailyStatement> statementDiscountDetails(String fromDate, String toDate, String headName);
	List<DailyStatement> statementCashDetails(String fromDate, String toDate, String headName);
	
	String getTypeWiseVoucherNo(String voucherType);
	
	List<DailyStatement> statementExpenseDetails(String fromDate, String toDate, String headName);
	
	List<BankReconcilation> getBankReconcilationItemList();
	String saveBankReconcilationMonthlyTransaction(BankReconcilation v);
	List<BankReconcilation> getBankReconcilationList();
	boolean isHeadHasTransaction(String headId);
	boolean deleteHead(String headId);

}
