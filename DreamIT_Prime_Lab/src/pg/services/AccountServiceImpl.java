package pg.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import pg.dao.AccountDAO;

@Service
public class AccountServiceImpl implements AccountService{

	@Autowired
	AccountDAO accountDAO;

	@Override
	public List<AccountsCreate> getAccountHead() {
		// TODO Auto-generated method stub
		return accountDAO.getAccountHead();
	}

	@Override
	public boolean isHeadExist(AccountsCreate v) {
		// TODO Auto-generated method stub
		return accountDAO.isHeadExist(v);
	}

	@Override
	public boolean addHead(AccountsCreate v) {
		// TODO Auto-generated method stub
		return accountDAO.addHead(v);
	}

	@Override
	public List<AccountsCreate> getHeadlist() {
		// TODO Auto-generated method stub
		return accountDAO.getHeadlist();
	}

	@Override
	public boolean editHead(AccountsCreate v) {
		// TODO Auto-generated method stub
		return accountDAO.editHead(v);
	}

	@Override
	public boolean isLedgerExist(LedgerCreate v) {
		// TODO Auto-generated method stub
		return accountDAO.isLedgerExist(v);
	}

	@Override
	public boolean addLedger(LedgerCreate v) {
		// TODO Auto-generated method stub
		return accountDAO.addLedger(v);
	}

	@Override
	public List<LedgerCreate> getLedgerlist() {
		// TODO Auto-generated method stub
		return accountDAO.getLedgerlist();
	}

	@Override
	public boolean editLedger(LedgerCreate v) {
		// TODO Auto-generated method stub
		return accountDAO.editLedger(v);
	}

	@Override
	public boolean isChequeExist(ChequeBookCreate v) {
		// TODO Auto-generated method stub
		return accountDAO.isChequeExist(v);
	}

	@Override
	public boolean addChequeBookSerialRange(ChequeBookCreate v) {
		// TODO Auto-generated method stub
		return accountDAO.addChequeBookSerialRange(v);
	}

	@Override
	public List<ChequeBookCreate> getChequeBookSerialList() {
		// TODO Auto-generated method stub
		return accountDAO.getChequeBookSerialList();
	}

	@Override
	public List<ChequeBookCreate> getChequeBookDetails(String ledgerId, String chequeBookNo) {
		// TODO Auto-generated method stub
		return accountDAO.getChequeBookDetails(ledgerId, chequeBookNo);
	}

	@Override
	public boolean editChequeBookSerialRange(ChequeBookCreate v) {
		// TODO Auto-generated method stub
		return accountDAO.editChequeBookSerialRange(v);
	}

	@Override
	public List<CostCenterCreate> getCostCenterlist() {
		// TODO Auto-generated method stub
		return accountDAO.getCostCenterlist();
	}

	@Override
	public boolean saveVoucher(Voucher v) {
		// TODO Auto-generated method stub
		return accountDAO.saveVoucher(v);
	}

	@Override
	public List<LedgerCreate> getCashLedgerlist() {
		// TODO Auto-generated method stub
		return accountDAO.getCashLedgerlist();
	}

	@Override
	public List<LedgerCreate> getBankLedgerlist() {
		// TODO Auto-generated method stub
		return accountDAO.getBankLedgerlist();
	}

	@Override
	public List<Voucher> getPaymentVoucherList(String voucherNoSearch, String fromDate, String toDate,
			String paymentType) {
		// TODO Auto-generated method stub
		return accountDAO.getPaymentVoucherList(voucherNoSearch, fromDate, toDate, paymentType);
	}

	@Override
	public String getMaxVoucherNo(String PaymentType) {
		// TODO Auto-generated method stub
		return accountDAO.getMaxVoucherNo(PaymentType);
	}

	@Override
	public boolean saveJournalVoucher(Voucher v) {
		// TODO Auto-generated method stub
		return accountDAO.saveJournalVoucher(v);
	}

	@Override
	public List<Voucher> getPaymentVoucherListForApprove(String voucherNoSearch, String fromDate,
			String toDate, String voucherType, String approveType) {
		// TODO Auto-generated method stub
		return accountDAO.getPaymentVoucherListForApprove(voucherNoSearch, fromDate, toDate, voucherType, approveType);
	}

	@Override
	public boolean approveVoucher(Voucher v) {
		// TODO Auto-generated method stub
		return accountDAO.approveVoucher(v);
	}

	@Override
	public List<LedgerCreate> getCashBankLedgerList() {
		// TODO Auto-generated method stub
		return accountDAO.getCashBankLedgerList();
	}

	@Override
	public List<Voucher> getChequeList(String fromDate, String toDate, String vocuherType,
			String chequeNumber) {
		// TODO Auto-generated method stub
		return accountDAO.getChequeList(fromDate, toDate, vocuherType, chequeNumber);
	}

	@Override
	public boolean checquePass(Voucher v) {
		// TODO Auto-generated method stub
		return accountDAO.checquePass(v);
	}

	@Override
	public List<Voucher> getChequeBookReport(String vocuherType, String chequeBookNo, String ledgerId) {
		// TODO Auto-generated method stub
		return accountDAO.getChequeBookReport(vocuherType, chequeBookNo, ledgerId);
	}

	@Override
	public List<ChartOfAccount> getAllHeadlist() {
		// TODO Auto-generated method stub
		return accountDAO.getAllHeadlist();
	}

	@Override
	public List<AccountsSummary> getAccountSummary(String fromDate,String toDate,String headId) {
		// TODO Auto-generated method stub
		return accountDAO.getAccountSummary(fromDate,toDate,headId);
	}

	@Override
	public boolean TransferTransaction(String transactionOnDate, String transferFrom, String transferTo,String userId,String Amount) {
		// TODO Auto-generated method stub
		return accountDAO.TransferTransaction(transactionOnDate, transferFrom, transferTo,userId,Amount);
	}

	@Override
	public String getTransactionAmount(String transactionOnDate, String transferFrom) {
		// TODO Auto-generated method stub
		return accountDAO.getTransactionAmount(transactionOnDate, transferFrom);
	}

	@Override
	public List<TrialBalance> getTrialBalanceHistory(String fromDate, String toDate,String transactionType) {
		// TODO Auto-generated method stub
		return accountDAO.getTrialBalanceHistory(fromDate, toDate,transactionType);
	}

	@Override
	public List<TrialBalance> geHeadWisetTrialBalanceHistory(String fromDate, String toDate, String title,String transactionType) {
		// TODO Auto-generated method stub
		return accountDAO.geHeadWisetTrialBalanceHistory(fromDate, toDate, title,transactionType);
	}

	@Override
	public List<TrialBalance> geHeadAndLedgerWiseTrialBalanceHistory(String fromDate, String toDate, String headName) {
		// TODO Auto-generated method stub
		return accountDAO.geHeadAndLedgerWiseTrialBalanceHistory(fromDate, toDate, headName);
	}

	@Override
	public List<AccountsDetails> getProfitAndLoss(String fromDate, String toDate) {
		// TODO Auto-generated method stub
		return accountDAO.getProfitAndLoss(fromDate, toDate);
	}

	@Override
	public List<AccountsDetails> geHeadWisetPALHistory(String fromDate, String toDate, String headName, String headType) {
		// TODO Auto-generated method stub
		return accountDAO.geHeadWisetPALHistory(fromDate, toDate, headName, headType);
	}

	@Override
	public List<AccountsDetails> geHeadWiseLedgerPALHistory(String fromDate, String toDate, String headName,
			String headType) {
		// TODO Auto-generated method stub
		return accountDAO.geHeadWiseLedgerPALHistory(fromDate, toDate, headName, headType);
	}

	@Override
	public List<AccountsDetails> getBalanceSheetData(String fromDate, String toDate,String transactionType) {
		// TODO Auto-generated method stub
		return accountDAO.getBalanceSheetData(fromDate, toDate,transactionType);
	}

	@Override
	public List<AccountsDetails> geHeadWisetBalHistory(String fromDate, String headName,
			String headType) {
		// TODO Auto-generated method stub
		return accountDAO.geHeadWisetBalHistory(fromDate, headName, headType);
	}

	@Override
	public List<AccountsDetails> geHeadWiseLedgerBalHistory(String fromDate, String headName, String headType) {
		// TODO Auto-generated method stub
		return accountDAO.geHeadWiseLedgerBalHistory(fromDate, headName, headType);
	}

	@Override
	public List<Voucher> getAllBookReport(String fromDate, String toDate, String voucherType) {
		// TODO Auto-generated method stub
		return accountDAO.getAllBookReport(fromDate, toDate, voucherType);
	}

	@Override
	public List<Voucher> getCashBankBooReport(String fromDate, String toDate, String voucherType) {
		// TODO Auto-generated method stub
		return accountDAO.getCashBankBooReport(fromDate, toDate, voucherType);
	}

	@Override
	public boolean saveAllTypesVoucher(Voucher v) {
		// TODO Auto-generated method stub
		return accountDAO.saveAllTypesVoucher(v);
	}

	@Override
	public List<DailyStatement> StatementViewList(String fromDate, String toDate, String statementType) {
		// TODO Auto-generated method stub
		return accountDAO.StatementViewList(fromDate, toDate, statementType);
	}

	@Override
	public List<DailyStatement> statementIncomeDetails(String fromDate, String toDate, String headName) {
		// TODO Auto-generated method stub
		return accountDAO.statementIncomeDetails(fromDate, toDate, headName);
	}

	@Override
	public List<DailyStatement> statementDiscountDetails(String fromDate, String toDate, String headName) {
		// TODO Auto-generated method stub
		return accountDAO.statementDiscountDetails(fromDate, toDate, headName);
	}

	@Override
	public List<DailyStatement> statementCashDetails(String fromDate, String toDate, String headName) {
		// TODO Auto-generated method stub
		return accountDAO.statementCashDetails(fromDate, toDate, headName);
	}

	@Override
	public String getTypeWiseVoucherNo(String voucherType) {
		// TODO Auto-generated method stub
		return accountDAO.getTypeWiseVoucherNo(voucherType);
	}

	@Override
	public List<DailyStatement> statementExpenseDetails(String fromDate, String toDate, String headName) {
		// TODO Auto-generated method stub
		return accountDAO.statementExpenseDetails(fromDate, toDate, headName);
	}

	@Override
	public List<BankReconcilation> getBankReconcilationItemList() {
		// TODO Auto-generated method stub
		return accountDAO.getBankReconcilationItemList();
	}

	@Override
	public String saveBankReconcilationMonthlyTransaction(BankReconcilation v) {
		// TODO Auto-generated method stub
		return accountDAO.saveBankReconcilationMonthlyTransaction(v);
	}

	@Override
	public List<BankReconcilation> getBankReconcilationList() {
		// TODO Auto-generated method stub
		return accountDAO.getBankReconcilationList();
	}

	@Override
	public boolean isHeadHasTransaction(String headId) {
		// TODO Auto-generated method stub
		return accountDAO.isHeadHasTransaction(headId);
	}

	@Override
	public boolean deleteHead(String headId) {
		// TODO Auto-generated method stub
		return accountDAO.deleteHead(headId);
	}

}
