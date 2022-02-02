package pg.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

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
import pg.share.HibernateUtil;

@Repository
public class AccountDAOImpl implements AccountDAO{

	@Override
	public List<AccountsCreate> getAccountHead() {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		List<AccountsCreate> query=new ArrayList<AccountsCreate>();
		try{
			tx=session.getTransaction();
			tx.begin();


			List<?> list = session.createSQLQuery("select headid,headTitle from tbAccfhead where HeadId!='1' order by HeadId").list();

			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				Object[] element = (Object[]) iter.next();
				query.add(new AccountsCreate(element[0].toString(),element[1].toString()));
			}

			tx.commit();
		}
		catch(Exception e){

			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}

		finally {
			session.close();
		}

		return query;
	}

	@Override
	public boolean isHeadExist(AccountsCreate v) {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		try{
			tx=session.getTransaction();
			tx.begin();

			String sql="select headTitle from tbAccfhead where headTitle='"+v.getSubCategoryName()+"' and headid!='"+v.getHeadId()+"'";

			List<?> list = session.createSQLQuery(sql).list();
			if(list.size()>0)
				return true;
			tx.commit();
		}
		catch(Exception e){
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}
		finally {
			session.close();
		}
		return false;
	}

	@Override
	public boolean addHead(AccountsCreate v) {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		try{
			tx=session.getTransaction();
			tx.begin();

			System.out.println("v "+v.getParentId());
			String headId="0";
			String pHeadId=v.getParentId();
			String reference="";
			String pHeadType="";

			String sql="select (ISNULL(max(headid),0)+1)as headid from tbAccfhead";
			List<?> list = session.createSQLQuery(sql).list();

			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				headId = iter.next().toString();
				break;

			}

			sql="select Reference from tbAccfhead where headId='"+pHeadId+"'";
			List<?> list1 = session.createSQLQuery(sql).list();

			for(Iterator<?> iter = list1.iterator(); iter.hasNext();)
			{	
				reference = iter.next().toString();
				break;

			}

			reference=reference+"-"+headId;

			sql="select type from tbAccfhead where headId='"+pHeadId+"'";
			List<?> list2 = session.createSQLQuery(sql).list();

			for(Iterator<?> iter = list2.iterator(); iter.hasNext();)
			{	
				pHeadType = iter.next().toString();
				break;

			}


			sql="insert into tbAccfhead ("
					+ "headid,"
					+ "headTitle,"
					+ "pheadId,"
					+ "reference,"
					+ "UnitId,"
					+ "DepId,"
					+ "type,"
					+ "remark,"
					+ "isFixed,"
					+ "entryTime,"
					+ "UserId"
					+ ") values ("
					+ "'"+headId+"',"
					+ "'"+v.getSubCategoryName()+"',"
					+ "'"+pHeadId+"',"
					+ "'"+reference+"',"
					+ "'"+v.getUnitId()+"',"
					+ "'"+v.getDepId()+"',"
					+ "'"+pHeadType+"',"
					+ "'"+v.getRemark()+"',"
					+ "'0',"
					+ "CURRENT_TIMESTAMP,"
					+ "'"+v.getUserId()+"')";

			session.createSQLQuery(sql).executeUpdate();


			tx.commit();

			return true;
		}
		catch(Exception e){

			if (tx != null) {
				tx.rollback();
				return false;
			}
			e.printStackTrace();
		}

		finally {
			session.close();
		}

		return false;
	}



	private String getHeadReference(Session session,int headId) {
		String id="";

		try{

			String sql="select Reference from tbAccfhead where headId='"+headId+"'";
			List<?> list = session.createSQLQuery(sql).list();

			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				id = iter.next().toString();

			}

		}
		catch(Exception e){

			e.printStackTrace();
		}

		return id;
	}

	private String getHeadType(Session session,int headId) {
		String id="";

		try{

			String sql="select type from tbAccfhead where headId='"+headId+"'";
			List<?> list = session.createSQLQuery(sql).list();

			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				id = iter.next().toString();

			}

		}
		catch(Exception e){

			e.printStackTrace();
		}
		return id;
	}

	@Override
	public List<AccountsCreate> getHeadlist() {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		List<AccountsCreate> query=new ArrayList<AccountsCreate>();
		try{
			tx=session.getTransaction();
			tx.begin();



			List<?> list = session.createSQLQuery("select a.headId,a.headTitle,a.pheadId,ISNULL((select b.headTitle from tbAccfhead b where b.headId=a.pheadId and b.headId!='1'),'') as ParentHead,ISNULL(a.remark,'') from tbAccfhead a where a.pheadId!='1' and a.pheadId!='0'").list();

			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				Object[] element = (Object[]) iter.next();
				query.add(new AccountsCreate(element[0].toString(),element[1].toString(),element[2].toString(),element[3].toString(),element[4].toString()));
			}

			tx.commit();
		}
		catch(Exception e){

			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}

		finally {
			session.close();
		}

		return query;
	}

	@Override
	public boolean editHead(AccountsCreate v) {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		try{
			tx=session.getTransaction();
			tx.begin();

			String headId=v.getHeadId();
			String pHeadId=v.getParentId();
			String reference="";
			String pHeadType="";



			String sql="select Reference from tbAccfhead where headId='"+pHeadId+"'";
			List<?> list1 = session.createSQLQuery(sql).list();

			for(Iterator<?> iter = list1.iterator(); iter.hasNext();)
			{	
				reference = iter.next().toString();
				break;

			}

			sql="select type from tbAccfhead where headId='"+pHeadId+"'";
			List<?> list2 = session.createSQLQuery(sql).list();

			for(Iterator<?> iter = list2.iterator(); iter.hasNext();)
			{	
				pHeadType = iter.next().toString();
				break;

			}

			reference=reference+"-"+headId;

			sql="update tbAccfhead set "

					+ "headTitle='"+v.getSubCategoryName()+"',"
					+ "pheadId='"+pHeadId+"',"
					+ "reference='"+reference+"',"
					+ "UnitId='"+v.getUnitId()+"',"
					+ "DepId='"+v.getDepId()+"',"
					+ "type='"+pHeadType+"',"
					+ "remark='"+v.getRemark()+"',"
					+ "entryTime=CURRENT_TIMESTAMP,"
					+ "UserId='"+v.getUserId()+"' where headId='"+headId+"'";

			session.createSQLQuery(sql).executeUpdate();


			tx.commit();

			return true;
		}
		catch(Exception e){

			if (tx != null) {
				tx.rollback();
				return false;
			}
			e.printStackTrace();
		}

		finally {
			session.close();
		}

		return false;
	}

	@Override
	public boolean isLedgerExist(LedgerCreate v) {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		try{
			tx=session.getTransaction();
			tx.begin();

			String sql="select ledgerTitle from tbAccfledger where ledgerTitle='"+v.getLedgerName()+"' and ledgerId!='"+v.getLedgerId()+"'";

			List<?> list = session.createSQLQuery(sql).list();
			if(list.size()>0)
				return true;
			tx.commit();
		}
		catch(Exception e){
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}
		finally {
			session.close();
		}
		return false;
	}

	@Override
	public boolean addLedger(LedgerCreate v) {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		try{
			tx=session.getTransaction();
			tx.begin();

			String LedgerId="0";
			String pHeadId=v.getHeadId();
			String reference="";
			String pHeadType="";

			String sql="select (ISNULL(max(ledgerId),0)+1)as ledgerId from tbAccfledger";
			List<?> list = session.createSQLQuery(sql).list();

			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				LedgerId = iter.next().toString();
				break;

			}

			sql="select Reference from tbAccfhead where headId='"+pHeadId+"'";
			List<?> list1 = session.createSQLQuery(sql).list();

			for(Iterator<?> iter = list1.iterator(); iter.hasNext();)
			{	
				reference = iter.next().toString();
				break;

			}

			reference=reference+"-"+LedgerId;

			sql="select type from tbAccfhead where headId='"+pHeadId+"'";
			List<?> list2 = session.createSQLQuery(sql).list();

			for(Iterator<?> iter = list2.iterator(); iter.hasNext();)
			{	
				pHeadType = iter.next().toString();
				break;

			}


			sql="insert into tbAccfledger ("
					+ "ledgerid,"
					+ "ledgerTitle,"
					+ "pheadId,"
					+ "reference,"
					+ "openingBalance,"
					+ "UnitId,"
					+ "DepId,"
					+ "type,"
					+ "remark,"
					+ "isFixed,"
					+ "entryTime,"
					+ "UserId"
					+ ") values ("
					+ "'"+LedgerId+"',"
					+ "'"+v.getLedgerName()+"',"
					+ "'"+pHeadId+"',"
					+ "'"+reference+"',"
					+ "'"+v.getOpeningBalance()+"',"
					+ "'"+v.getUnitId()+"',"
					+ "'"+v.getDepId()+"',"
					+ "'"+pHeadType+"',"
					+ "'"+v.getRemark()+"',"
					+ "'0',"
					+ "CURRENT_TIMESTAMP,"
					+ "'"+v.getUserId()+"')";

			session.createSQLQuery(sql).executeUpdate();


			tx.commit();

			return true;
		}
		catch(Exception e){

			if (tx != null) {
				tx.rollback();
				return false;
			}
			e.printStackTrace();
		}

		finally {
			session.close();
		}

		return false;
	}

	@Override
	public List<LedgerCreate> getLedgerlist() {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		List<LedgerCreate> query=new ArrayList<LedgerCreate>();
		try{
			tx=session.getTransaction();
			tx.begin();


			List<?> list = session.createSQLQuery("select ledgerId,ledgerTitle,pheadId,(select HeadTitle from tbAccfHead where HeadId=tbAccfledger.pheadId) as HeadName,ISNULL(OpeningBalance,'0'),reference from tbAccfledger order by pheadId").list();

			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				Object[] element = (Object[]) iter.next();
				query.add(new LedgerCreate(element[0].toString(),element[1].toString(),element[2].toString(),element[3].toString(),element[4].toString(),element[5].toString()));
			}

			tx.commit();
		}
		catch(Exception e){

			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}

		finally {
			session.close();
		}

		return query;
	}

	@Override
	public boolean editLedger(LedgerCreate v) {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		try{
			tx=session.getTransaction();
			tx.begin();

			String ledgerId=v.getLedgerId();
			String pHeadId=v.getHeadId();
			String reference="";
			String pHeadType="";



			String sql="select Reference from tbAccfhead where headId='"+pHeadId+"'";
			List<?> list1 = session.createSQLQuery(sql).list();

			for(Iterator<?> iter = list1.iterator(); iter.hasNext();)
			{	
				reference = iter.next().toString();
				break;

			}

			reference=reference+"-"+ledgerId;
			sql="select type from tbAccfhead where headId='"+pHeadId+"'";
			List<?> list2 = session.createSQLQuery(sql).list();

			for(Iterator<?> iter = list2.iterator(); iter.hasNext();)
			{	
				pHeadType = iter.next().toString();
				break;

			}

			sql="update tbAccfledger set "

					+ "ledgerTitle='"+v.getLedgerName()+"',"
					+ "pheadId='"+pHeadId+"',"
					+ "reference='"+reference+"',"
					+ "UnitId='"+v.getUnitId()+"',"
					+ "DepId='"+v.getDepId()+"',"
					+ "type='"+pHeadType+"',"
					+ "remark='"+v.getRemark()+"',"
					+ "OpeningBalance='"+v.getOpeningBalance()+"',"
					+ "entryTime=CURRENT_TIMESTAMP,"
					+ "UserId='"+v.getUserId()+"' where ledgerId='"+ledgerId+"'";

			session.createSQLQuery(sql).executeUpdate();


			tx.commit();

			return true;
		}
		catch(Exception e){

			if (tx != null) {
				tx.rollback();
				return false;
			}
			e.printStackTrace();
		}

		finally {
			session.close();
		}

		return false;
	}

	@Override
	public boolean isChequeExist(ChequeBookCreate v) {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		try{
			tx=session.getTransaction();
			tx.begin();

			String sql="select BankLedgerId from TbChequeBookInfo where BankLedgerId='"+v.getLedgerId()+"' and ChequeBookNo='"+v.getChequeBookNo()+"'";


			List<?> list = session.createSQLQuery(sql).list();
			if(list.size()>0)
				return true;
			tx.commit();
		}
		catch(Exception e){
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}
		finally {
			session.close();
		}
		return false;
	}

	@Override
	public boolean addChequeBookSerialRange(ChequeBookCreate v) {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		try{
			tx=session.getTransaction();
			tx.begin();

			System.out.println("totalCheque "+v.getTotalChequeNo());

			int totalCheque=Integer.parseInt(v.getToChequeNo());
			for(int a=Integer.parseInt(v.getFromChequeNo());a<=totalCheque;a++) {
				String sql="insert into TbChequeBookInfo ("
						+ "BankLedgerId,"
						+ "BankOfPrefix,"
						+ "ChequeBookNo,"
						+ "FromChequeNo,"
						+ "ToChequeNo,"
						+ "ChequeNo,"
						+ "entryTime,"
						+ "UserId"
						+ ") values ("
						+ "'"+v.getLedgerId()+"',"
						+ "'"+v.getBookNoPrefix()+"',"
						+ "'"+v.getChequeBookNo()+"',"
						+ "'"+v.getFromChequeNo()+"',"
						+ "'"+v.getToChequeNo()+"',"
						+ "'"+(a)+"',"
						+ "CURRENT_TIMESTAMP,"
						+ "'"+v.getUserId()+"')";

				session.createSQLQuery(sql).executeUpdate();
			}

			tx.commit();

			return true;
		}
		catch(Exception e){

			if (tx != null) {
				tx.rollback();
				return false;
			}
			e.printStackTrace();
		}

		finally {
			session.close();
		}

		return false;
	}

	@Override
	public List<ChequeBookCreate> getChequeBookSerialList() {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		List<ChequeBookCreate> query=new ArrayList<ChequeBookCreate>();
		try{
			tx=session.getTransaction();
			tx.begin();


			List<?> list = session.createSQLQuery("select a.BankLedgerId,(select LedgerTitle from tbAccfledger where ledgerId=a.BankLedgerId) as LedgerTitle,a.BankOfPrefix,a.ChequeBookNo,a.FromChequeNo,a.ToChequeNo from TbChequeBookInfo a group by a.BankLedgerId,a.BankOfPrefix,a.ChequeBookNo,a.FromChequeNo,a.ToChequeNo").list();

			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				Object[] element = (Object[]) iter.next();
				query.add(new ChequeBookCreate(element[0].toString(),element[1].toString(),element[2].toString(),element[3].toString(),element[4].toString(),element[5].toString()));
			}

			tx.commit();
		}
		catch(Exception e){

			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}

		finally {
			session.close();
		}

		return query;
	}

	@Override
	public List<ChequeBookCreate> getChequeBookDetails(String ledgerId, String chequeBookNo) {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		List<ChequeBookCreate> query=new ArrayList<ChequeBookCreate>();
		try{
			tx=session.getTransaction();
			tx.begin();


			List<?> list = session.createSQLQuery("select a.BankLedgerId,a.BankOfPrefix,a.ChequeBookNo,a.FromChequeNo,a.ToChequeNo from TbChequeBookInfo a where a.BankLedgerId='"+ledgerId+"' and a.ChequeBookNo='"+chequeBookNo+"' group by a.BankLedgerId,a.BankOfPrefix,a.ChequeBookNo,a.FromChequeNo,a.ToChequeNo").list();

			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				Object[] element = (Object[]) iter.next();
				query.add(new ChequeBookCreate(element[0].toString(),element[1].toString(),element[2].toString(),element[3].toString(),element[4].toString()));
			}

			tx.commit();
		}
		catch(Exception e){

			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}

		finally {
			session.close();
		}

		return query;
	}

	@Override
	public boolean editChequeBookSerialRange(ChequeBookCreate v) {
		// TODO Auto-generated method stub
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		try{
			tx=session.getTransaction();
			tx.begin();

			System.out.println("totalCheque "+v.getTotalChequeNo());

			int totalCheque=Integer.parseInt(v.getToChequeNo());

			String deleteSql="delete from TbChequeBookInfo where ChequeBookNo='"+v.getHiddenchequeBookNo()+"' and BankLedgerId='"+v.getHiddenLedgerId()+"'";
			session.createSQLQuery(deleteSql).executeUpdate();

			//int a=

			for(int a=Integer.parseInt(v.getFromChequeNo());a<=totalCheque;a++) {

				System.out.println(" a "+a);
				String sql="insert into TbChequeBookInfo ("
						+ "BankLedgerId,"
						+ "BankOfPrefix,"
						+ "ChequeBookNo,"
						+ "FromChequeNo,"
						+ "ToChequeNo,"
						+ "ChequeNo,"
						+ "entryTime,"
						+ "UserId"
						+ ") values ("
						+ "'"+v.getLedgerId()+"',"
						+ "'"+v.getBookNoPrefix()+"',"
						+ "'"+v.getChequeBookNo()+"',"
						+ "'"+v.getFromChequeNo()+"',"
						+ "'"+v.getToChequeNo()+"',"
						+ "'"+a+"',"
						+ "CURRENT_TIMESTAMP,"
						+ "'"+v.getUserId()+"')";

				session.createSQLQuery(sql).executeUpdate();
			}


			tx.commit();

			return true;
		}
		catch(Exception e){

			if (tx != null) {
				tx.rollback();
				return false;
			}
			e.printStackTrace();
		}

		finally {
			session.close();
		}

		return false;
	}

	@Override
	public List<CostCenterCreate> getCostCenterlist() {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		List<CostCenterCreate> query=new ArrayList<CostCenterCreate>();
		try{
			tx=session.getTransaction();
			tx.begin();


			List<?> list = session.createSQLQuery("select costCenterId,Name from TbCostCenterInfo ").list();

			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				Object[] element = (Object[]) iter.next();
				query.add(new CostCenterCreate(element[0].toString(),element[1].toString()));
			}

			tx.commit();
		}
		catch(Exception e){

			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}

		finally {
			session.close();
		}

		return query;
	}

	@Override
	public boolean saveVoucher(Voucher v) {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		try{
			tx=session.getTransaction();
			tx.begin();

			String TransId="0";
			String d_l_id="0",c_l_id="0";

			System.out.println("credit "+v.getLedgerId());
			String sql="select reference from tbAccfledger where ledgerId='"+v.getLedgerId()+"'";
			List<?> listcredit = session.createSQLQuery(sql).list();

			for(Iterator<?> iter = listcredit.iterator(); iter.hasNext();)
			{	
				if(v.getVoucherType().equals("Payment") || v.getVoucherType().equals("Contra")) {
					c_l_id = iter.next().toString();
				}
				else if(v.getVoucherType().equals("Receipt")) {
					d_l_id = iter.next().toString();
				}
				break;
			}

			String resultValue=v.getResultList().substring(v.getResultList().indexOf("[")+1, v.getResultList().indexOf("]"));
			System.out.println("resultValue "+resultValue);
			StringTokenizer sizeToken=new StringTokenizer(resultValue,",");
			String fristToken="";
			while(sizeToken.hasMoreTokens()) {
				fristToken=sizeToken.nextToken();
				StringTokenizer resultToken=new StringTokenizer(fristToken,"*");
				while(resultToken.hasMoreTokens()) {
					String passLedgerId=resultToken.nextToken();
					String accAmount=resultToken.nextToken();
					String accDescription=resultToken.nextToken();
					sql="select (ISNULL(max(transectionid),0)+1)as transectionid from tbAccftransection";
					List<?> list = session.createSQLQuery(sql).list();

					for(Iterator<?> iter = list.iterator(); iter.hasNext();)
					{	
						TransId = iter.next().toString();
						break;

					}


					sql="select reference from tbAccfledger where ledgerId='"+passLedgerId+"'";
					List<?> listdebit = session.createSQLQuery(sql).list();

					for(Iterator<?> iter = listdebit.iterator(); iter.hasNext();)
					{	
						if(v.getVoucherType().equals("Payment") || v.getVoucherType().equals("Contra")) {
							d_l_id = iter.next().toString();						
						}
						else if(v.getVoucherType().equals("Receipt")) {
							c_l_id = iter.next().toString();
						}


						break;

					}

					if(!accAmount.equals("0")) {

						System.out.println("accType"+v.getAccountType());

						if(v.getAccountType().equals("1") || v.getAccountType().equals("3")) {
							sql="insert into tbAccftransection ("
									+ "transectionid,"
									+ "voucherNo,"
									+ "type,"
									+ "d_l_id,"
									+ "c_l_id,"
									+ "amount,"
									+ "description,"
									+ "standby,"
									+ "costCenterId,"
									+ "approve,"
									+ "date,"
									+ "entryTime,"
									+ "UserId"
									+ ") values ("
									+ "'"+TransId+"',"
									+ "'"+v.getVoucherNo()+"',"
									+ "'"+v.getPaymentType()+"',"
									+ "'"+d_l_id+"',"
									+ "'"+c_l_id+"',"
									+ "'"+accAmount+"',"
									+ "'"+accDescription+"',"
									+ "'"+v.getStandBy()+"',"
									+ "'"+v.getCostCenterId()+"',"
									+ "'0',"
									+ "'"+v.getDate()+"',"
									+ "CURRENT_TIMESTAMP,"
									+ "'"+v.getUserId()+"')";
						}
						else if(v.getAccountType().equals("2") || v.getAccountType().equals("4") || v.getAccountType().equals("6")) {
							sql="insert into tbAccftransection ("
									+ "transectionid,"
									+ "voucherNo,"
									+ "type,"
									+ "d_l_id,"
									+ "c_l_id,"
									+ "amount,"
									+ "description,"
									+ "standby,"
									+ "costCenterId,"
									+ "chequeNumber,"
									+ "chequeDate,"
									+ "approve,"
									+ "date,"
									+ "entryTime,"
									+ "UserId"
									+ ") values ("
									+ "'"+TransId+"',"
									+ "'"+v.getVoucherNo()+"',"
									+ "'"+v.getPaymentType()+"',"
									+ "'"+d_l_id+"',"
									+ "'"+c_l_id+"',"
									+ "'"+accAmount+"',"
									+ "'"+accDescription+"',"
									+ "'"+v.getStandBy()+"',"
									+ "'"+v.getCostCenterId()+"',"
									+ "'"+v.getChequeNumber()+"',"
									+ "'"+v.getChequeDate()+"',"
									+ "'0',"
									+ "'"+v.getDate()+"',"
									+ "CURRENT_TIMESTAMP,"
									+ "'"+v.getUserId()+"')";
						}

						session.createSQLQuery(sql).executeUpdate();
					}

				}
			}


			tx.commit();

			return true;
		}
		catch(Exception e){

			if (tx != null) {
				tx.rollback();
				return false;
			}
			e.printStackTrace();
		}

		finally {
			session.close();
		}

		return false;
	}

	@Override
	public List<LedgerCreate> getCashLedgerlist() {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		List<LedgerCreate> query=new ArrayList<LedgerCreate>();
		try{
			tx=session.getTransaction();
			tx.begin();


			List<?> list = session.createSQLQuery("select ledgerId,ledgerTitle from tbAccfledger where pheadId='14'").list();

			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				Object[] element = (Object[]) iter.next();
				query.add(new LedgerCreate(element[0].toString(),element[1].toString()));
			}

			tx.commit();
		}
		catch(Exception e){

			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}

		finally {
			session.close();
		}

		return query;
	}

	@Override
	public List<LedgerCreate> getBankLedgerlist() {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		List<LedgerCreate> query=new ArrayList<LedgerCreate>();
		try{
			tx=session.getTransaction();
			tx.begin();


			List<?> list = session.createSQLQuery("select ledgerId,ledgerTitle from tbAccfledger where pheadId='15'").list();

			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				Object[] element = (Object[]) iter.next();
				query.add(new LedgerCreate(element[0].toString(),element[1].toString()));
			}

			tx.commit();
		}
		catch(Exception e){

			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}

		finally {
			session.close();
		}

		return query;
	}

	@Override
	public List<Voucher> getPaymentVoucherList(String voucherNoSearch, String fromDate, String toDate,
			String paymentType) {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		List<Voucher> query=new ArrayList<Voucher>();
		try{
			tx=session.getTransaction();
			tx.begin();



			String sql="";
			if(voucherNoSearch.equals("") && fromDate.equals("") && toDate.equals("")) {
				sql="select a.voucherNo,a.type,(select narration from TbVoucherType where type=a.type) as PaymentType,(select convert(varchar,a.Date, 120)) as Date,sum(a.Amount) as Amount,a.approve from tbAccftransection a where a.type='"+paymentType+"'  group by a.type,a.voucherNo,a.Date,a.approve";
			}
			else if(!voucherNoSearch.equals("") && fromDate.equals("") && toDate.equals("")) {
				sql="select a.voucherNo,a.type,(select narration from TbVoucherType where type=a.type) as PaymentType,(select convert(varchar,a.Date, 120)) as Date,sum(a.Amount) as Amount,a.approve from tbAccftransection a where a.type='"+paymentType+"' and a.voucherNo='"+voucherNoSearch+"' group by a.type,a.voucherNo,a.Date,a.approve";
			}
			else if(!voucherNoSearch.equals("") && !fromDate.equals("") && !toDate.equals("")) {
				sql="select a.voucherNo,a.type,(select narration from TbVoucherType where type=a.type) as PaymentType,(select convert(varchar,a.Date, 120)) as Date,sum(a.Amount) as Amount,a.approve from tbAccftransection a where a.type='"+paymentType+"' and a.voucherNo='"+voucherNoSearch+"' and a.Date between '"+fromDate+"' and '"+toDate+"' group by a.type,a.voucherNo,a.Date,a.approve";
			}
			else if(voucherNoSearch.equals("") && !fromDate.equals("") && !toDate.equals("")) {
				sql="select a.voucherNo,a.type,(select narration from TbVoucherType where type=a.type) as PaymentType,(select convert(varchar,a.Date, 120)) as Date,sum(a.Amount) as Amount,a.approve from tbAccftransection a where a.type='"+paymentType+"' and a.Date between '"+fromDate+"' and '"+toDate+"' group by a.type,a.voucherNo,a.Date,a.approve";
			}

			System.out.println(sql);

			List<?> list = session.createSQLQuery(sql).list();

			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				Object[] element = (Object[]) iter.next();
				String status=element[5].toString().equals("0")?"Pending":"Approve";
				query.add(new Voucher(element[0].toString(),element[1].toString(),element[2].toString(),element[3].toString(),element[4].toString(),status,""));
			}

			tx.commit();
		}
		catch(Exception e){

			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}

		finally {
			session.close();
		}

		return query;
	}

	@Override
	public String getMaxVoucherNo(String PaymentType) {

		String LedgerId="";
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		try{
			tx=session.getTransaction();
			tx.begin();

			String sql="select (ISNULL(max(voucherNo),0)+1)as voucherNo from tbAccftransection where type='"+PaymentType+"'";
			List<?> list = session.createSQLQuery(sql).list();

			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				LedgerId = iter.next().toString();
				break;

			}

			tx.commit();
		}
		catch(Exception e){

			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}

		finally {
			session.close();
		}

		return LedgerId;

	}

	@Override
	public boolean saveJournalVoucher(Voucher v) {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		try{
			tx=session.getTransaction();
			tx.begin();

			String TransId="0";
			String d_l_id="0",c_l_id="0";


			String resultValue=v.getResultList().substring(v.getResultList().indexOf("[")+1, v.getResultList().indexOf("]"));
			System.out.println("resultValue "+resultValue);
			StringTokenizer sizeToken=new StringTokenizer(resultValue,",");
			String fristToken="";
			while(sizeToken.hasMoreTokens()) {
				fristToken=sizeToken.nextToken();
				StringTokenizer resultToken=new StringTokenizer(fristToken,"*");
				while(resultToken.hasMoreTokens()) {
					String passLedgerId=resultToken.nextToken();					
					String accDebit=resultToken.nextToken();
					String accCredit=resultToken.nextToken();
					String accDescription=resultToken.nextToken();

					System.out.println("accDebit "+accDebit);
					System.out.println("accCredit "+accCredit);
					double AccAmount=0;
					String sql="select (ISNULL(max(transectionid),0)+1)as transectionid from tbAccftransection";
					List<?> list = session.createSQLQuery(sql).list();

					for(Iterator<?> iter = list.iterator(); iter.hasNext();)
					{	
						TransId = iter.next().toString();
						break;
					}


					if(!v.getValid().equals("0") && (!accDebit.equals("0") || !accCredit.equals("0"))) {

						if(Double.parseDouble(accDebit)>0) {
							sql="select reference from tbAccfledger where ledgerId='"+passLedgerId+"'";
							List<?> listcredit = session.createSQLQuery(sql).list();

							for(Iterator<?> iter = listcredit.iterator(); iter.hasNext();)
							{	
								d_l_id = iter.next().toString();
								c_l_id="0";
								break;
							}

							AccAmount=Double.parseDouble(accDebit);
						}
						else if(Double.parseDouble(accCredit)>0) {
							sql="select reference from tbAccfledger where ledgerId='"+passLedgerId+"'";
							List<?> listcredit = session.createSQLQuery(sql).list();

							for(Iterator<?> iter = listcredit.iterator(); iter.hasNext();)
							{	
								c_l_id = iter.next().toString();
								d_l_id="0";
								break;
							}

							AccAmount=Double.parseDouble(accCredit);
						}

						sql="insert into tbAccftransection ("
								+ "transectionid,"
								+ "voucherNo,"
								+ "type,"
								+ "d_l_id,"
								+ "c_l_id,"
								+ "amount,"
								+ "description,"
								+ "standby,"
								+ "costCenterId,"
								+ "approve,"
								+ "date,"
								+ "entryTime,"
								+ "UserId"
								+ ") values ("
								+ "'"+TransId+"',"
								+ "'"+v.getVoucherNo()+"',"
								+ "'5',"
								+ "'"+d_l_id+"',"
								+ "'"+c_l_id+"',"
								+ "'"+AccAmount+"',"
								+ "'"+accDescription+"',"
								+ "'"+v.getStandBy()+"',"
								+ "'"+v.getCostCenterId()+"',"
								+ "'0',"
								+ "'"+v.getDate()+"',"
								+ "CURRENT_TIMESTAMP,"
								+ "'"+v.getUserId()+"')";

						session.createSQLQuery(sql).executeUpdate();
					}

				}
			}


			tx.commit();

			return true;
		}
		catch(Exception e){

			if (tx != null) {
				tx.rollback();
				return false;
			}
			e.printStackTrace();
		}

		finally {
			session.close();
		}

		return false;
	}

	@Override
	public List<Voucher> getPaymentVoucherListForApprove(String voucherNoSearch, String fromDate,
			String toDate, String voucherType, String approveType) {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		List<Voucher> query=new ArrayList<Voucher>();
		try{
			tx=session.getTransaction();
			tx.begin();
			
			
			
			String sql="";
			if(voucherNoSearch.equals("") && fromDate.equals("") && toDate.equals("") && !voucherType.equals("0")) {
				sql="select a.voucherNo,a.type,(select narration from TbVoucherType where type=a.type) as PaymentType,(select convert(varchar,a.Date, 120)) as Date,sum(a.Amount) as Amount,a.approve,ISNULL((select username from Tblogin where id=a.ApproveUserId),'') as ApproveUser,ISNULL(a.ApproveTime,'') as ApproveTime from tbAccftransection a where a.type='"+voucherType+"' and a.approve='"+approveType+"' group by a.type,a.voucherNo,a.Date,a.approve,a.ApproveTime,a.ApproveUserId";
			}
			else if(!voucherNoSearch.equals("") && fromDate.equals("") && toDate.equals("") && !voucherType.equals("0")) {
				sql="select a.voucherNo,a.type,(select narration from TbVoucherType where type=a.type) as PaymentType,(select convert(varchar,a.Date, 120)) as Date,sum(a.Amount) as Amount,a.approve,ISNULL((select username from Tblogin where id=a.ApproveUserId),'') as ApproveUser,ISNULL(a.ApproveTime,'') as ApproveTime from tbAccftransection a where a.type='"+voucherType+"' and a.voucherNo='"+voucherNoSearch+"' and a.approve='"+approveType+"' group by a.type,a.voucherNo,a.Date,a.approve,a.ApproveTime,a.ApproveUserId";
			}
			else if(!voucherNoSearch.equals("") && !fromDate.equals("") && !toDate.equals("") && !voucherType.equals("0")) {
				sql="select a.voucherNo,a.type,(select narration from TbVoucherType where type=a.type) as PaymentType,(select convert(varchar,a.Date, 120)) as Date,sum(a.Amount) as Amount,a.approve,ISNULL((select username from Tblogin where id=a.ApproveUserId),'') as ApproveUser,ISNULL(a.ApproveTime,'') as ApproveTime from tbAccftransection a where a.type='"+voucherType+"' and a.voucherNo='"+voucherNoSearch+"' and a.Date between '"+fromDate+"' and '"+toDate+"' and a.approve='"+approveType+"'  group by a.type,a.voucherNo,a.Date,a.approve,a.ApproveTime,a.ApproveUserId";
			}
			else if(voucherNoSearch.equals("") && !fromDate.equals("") && !toDate.equals("") && !voucherType.equals("0")) {
				sql="select a.voucherNo,a.type,(select narration from TbVoucherType where type=a.type) as PaymentType,(select convert(varchar,a.Date, 120)) as Date,sum(a.Amount) as Amount,a.approve,ISNULL((select username from Tblogin where id=a.ApproveUserId),'') as ApproveUser,ISNULL(a.ApproveTime,'') as ApproveTime from tbAccftransection a where a.type='"+voucherType+"' and a.Date between '"+fromDate+"' and '"+toDate+"' and a.approve='"+approveType+"' group by a.type,a.voucherNo,a.Date,a.approve,a.ApproveTime,a.ApproveUserId";
			}
			else{
				sql="select a.voucherNo,a.type,(select narration from TbVoucherType where type=a.type) as PaymentType,(select convert(varchar,a.Date, 120)) as Date,sum(a.Amount) as Amount,a.approve,ISNULL((select username from Tblogin where id=a.ApproveUserId),'') as ApproveUser,ISNULL(a.ApproveTime,'') as ApproveTime from tbAccftransection a where  a.Date between '"+fromDate+"' and '"+toDate+"' and a.approve='"+approveType+"' group by a.type,a.voucherNo,a.Date,a.approve,a.ApproveTime,a.ApproveUserId";
			}
			
			System.out.println(sql);

			List<?> list = session.createSQLQuery(sql).list();

			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				Object[] element = (Object[]) iter.next();
				String status=element[5].toString().equals("0")?"Pending":"Approve";
				query.add(new Voucher(element[0].toString(),element[1].toString(),element[2].toString(),element[3].toString(),element[4].toString(),status,element[6].toString(),element[7].toString()));
			}

			tx.commit();
		}
		catch(Exception e){

			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}

		finally {
			session.close();
		}

		return query;
	}

	@Override
	public boolean approveVoucher(Voucher v) {
		// TODO Auto-generated method stub
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		try{
			tx=session.getTransaction();
			tx.begin();


			String resultValue=v.getResultList().substring(v.getResultList().indexOf("[")+1, v.getResultList().indexOf("]"));
			System.out.println("resultValue "+resultValue);
			StringTokenizer sizeToken=new StringTokenizer(resultValue,",");
			String fristToken="";
			while(sizeToken.hasMoreTokens()) {
				fristToken=sizeToken.nextToken();
				StringTokenizer resultToken=new StringTokenizer(fristToken,"*");
				while(resultToken.hasMoreTokens()) {
					String approve=resultToken.nextToken();					
					String vouchertype=resultToken.nextToken();
					String voucherno=resultToken.nextToken();

					if(approve.equals("1")) {
						String approvesql="update tbAccftransection set approve='"+approve+"',ApproveUserId='"+v.getUserId()+"',ApproveTime=CURRENT_TIMESTAMP where voucherNo='"+voucherno+"' and type='"+vouchertype+"'";
						session.createSQLQuery(approvesql).executeUpdate();

					}


				}
			}


			tx.commit();

			return true;
		}
		catch(Exception e){

			if (tx != null) {
				tx.rollback();
				return false;
			}
			e.printStackTrace();
		}

		finally {
			session.close();
		}

		return false;
	}

	@Override
	public List<LedgerCreate> getCashBankLedgerList() {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		List<LedgerCreate> query=new ArrayList<LedgerCreate>();
		try{
			tx=session.getTransaction();
			tx.begin();


			List<?> list = session.createSQLQuery("select ledgerId,ledgerTitle from tbAccfledger where pheadId in(14,15) order by pheadId").list();

			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				Object[] element = (Object[]) iter.next();
				query.add(new LedgerCreate(element[0].toString(),element[1].toString()));
			}

			tx.commit();
		}
		catch(Exception e){

			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}

		finally {
			session.close();
		}

		return query;
	}

	@Override
	public List<Voucher> getChequeList(String fromDate, String toDate, String vocuherType,
			String chequeNumber) {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		List<Voucher> query=new ArrayList<Voucher>();
		try{
			tx=session.getTransaction();
			tx.begin();



			String sql="";
			if(chequeNumber.equals("") && fromDate.equals("") && toDate.equals("")) {
				sql="select a.voucherNo,a.type,(select narration from TbVoucherType where type=a.type) as PaymentType,(select convert(varchar,a.Date, 120)) as Date,sum(a.Amount) as Amount,(select convert(varchar,a.chequeDate, 120)) as chequeDate,a.chequeNumber from tbAccftransection a where a.type='"+vocuherType+"' and a.chequePass='0'  group by a.type,a.voucherNo,a.Date,a.chequeDate,a.chequeNumber";
			}
			else if(!chequeNumber.equals("") && fromDate.equals("") && toDate.equals("")) {
				sql="select a.voucherNo,a.type,(select narration from TbVoucherType where type=a.type) as PaymentType,(select convert(varchar,a.Date, 120)) as Date,sum(a.Amount) as Amount,(select convert(varchar,a.chequeDate, 120)) as chequeDate,a.chequeNumber from tbAccftransection a where a.type='"+vocuherType+"' and a.chequeNumber='"+chequeNumber+"' and a.chequePass='0'  group by a.type,a.voucherNo,a.Date,a.chequeDate,a.chequeNumber";
			}
			else if(!chequeNumber.equals("") && !fromDate.equals("") && !toDate.equals("")) {
				sql="select a.voucherNo,a.type,(select narration from TbVoucherType where type=a.type) as PaymentType,(select convert(varchar,a.Date, 120)) as Date,sum(a.Amount) as Amount,(select convert(varchar,a.chequeDate, 120)) as chequeDate,a.chequeNumber from tbAccftransection a where a.type='"+vocuherType+"' and a.chequeNumber='"+chequeNumber+"' and a.Date between '"+fromDate+"' and '"+toDate+"'  and a.chequePass='0' group by a.type,a.voucherNo,a.Date,a.chequeDate,a.chequeNumber";
			}
			else if(chequeNumber.equals("") && !fromDate.equals("") && !toDate.equals("")) {
				sql="select a.voucherNo,a.type,(select narration from TbVoucherType where type=a.type) as PaymentType,(select convert(varchar,a.Date, 120)) as Date,sum(a.Amount) as Amount,(select convert(varchar,a.chequeDate, 120)) as chequeDate,a.chequeNumber from tbAccftransection a where a.type='"+vocuherType+"' and a.Date between '"+fromDate+"' and '"+toDate+"' and a.chequePass='0'  group by a.type,a.voucherNo,a.Date,a.chequeDate,a.chequeNumber";
			}

			System.out.println(sql);

			List<?> list = session.createSQLQuery(sql).list();

			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				Object[] element = (Object[]) iter.next();

				query.add(new Voucher(element[0].toString(),element[1].toString(),element[2].toString(),element[3].toString(),element[4].toString(),element[5].toString(),element[6].toString(),"","","",""));
			}

			tx.commit();
		}
		catch(Exception e){

			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}

		finally {
			session.close();
		}

		return query;
	}

	@Override
	public boolean checquePass(Voucher v) {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		try{
			tx=session.getTransaction();
			tx.begin();


			String resultValue=v.getResultList().substring(v.getResultList().indexOf("[")+1, v.getResultList().indexOf("]"));
			System.out.println("resultValue "+resultValue);
			StringTokenizer sizeToken=new StringTokenizer(resultValue,",");
			String fristToken="";
			while(sizeToken.hasMoreTokens()) {
				fristToken=sizeToken.nextToken();
				StringTokenizer resultToken=new StringTokenizer(fristToken,"*");
				while(resultToken.hasMoreTokens()) {
					String pass=resultToken.nextToken();					
					String vouchertype=resultToken.nextToken();
					String voucherno=resultToken.nextToken();

					if(pass.equals("1")) {
						String approvesql="update tbAccftransection set chequePass='"+pass+"' where voucherNo='"+voucherno+"' and type='"+vouchertype+"'";
						session.createSQLQuery(approvesql).executeUpdate();

					}


				}
			}


			tx.commit();

			return true;
		}
		catch(Exception e){

			if (tx != null) {
				tx.rollback();
				return false;
			}
			e.printStackTrace();
		}

		finally {
			session.close();
		}

		return false;
	}

	@Override
	public List<Voucher> getChequeBookReport(String vocuherType, String chequeBookNo, String ledgerId) {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		List<Voucher> query=new ArrayList<Voucher>();
		try{
			tx=session.getTransaction();
			tx.begin();



			String sql="";
			sql="select a.ChequeNo,ISNULL(b.voucherNo,0) as Voucher,ISNULL((select c.Narration from TbVoucherType c where c.Type=b.type),'') as Type,ISNULL(sum(b.amount),0) as Amount,ISNULL(convert(varchar,b.chequeDate, 120),'') as chequeDate,ISNULL(cast(b.chequePass as varchar(15)) ,'No Used') as chequePass from TbChequeBookInfo a left join tbAccftransection b on a.ChequeNo=b.chequeNumber where a.BankLedgerId='"+ledgerId+"' and a.ChequeBookNo='"+chequeBookNo+"'  group by a.ChequeNo,b.voucherNo,b.type,b.chequeDate,b.chequePass";

			System.out.println(sql);

			List<?> list = session.createSQLQuery(sql).list();

			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				Object[] element = (Object[]) iter.next();
				String voucher=element[1].toString().equals("0")?"":element[1].toString();

				String status=element[5].toString().equals("0")?"Pending":element[5].toString().equals("1")?"Approve":"No Used Yet";
				query.add(new Voucher(element[0].toString(),voucher,element[2].toString(),element[3].toString(),element[4].toString(),status,"","","",""));
			}

			tx.commit();
		}
		catch(Exception e){

			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}

		finally {
			session.close();
		}

		return query;
	}

	@Override
	public List<ChartOfAccount> getAllHeadlist() {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		List<ChartOfAccount> query=new ArrayList<ChartOfAccount>();
		try{
			tx=session.getTransaction();
			tx.begin();



			String sql="";
			sql="select headid,headTitle,pheadId from tbAccfhead where pheadId!='0' order by pheadId,headId";

			System.out.println(sql);

			List<?> list = session.createSQLQuery(sql).list();

			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				Object[] element = (Object[]) iter.next();
				query.add(new ChartOfAccount(element[0].toString(),element[1].toString(),element[2].toString()));
			}

			tx.commit();
		}
		catch(Exception e){

			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}

		finally {
			session.close();
		}

		return query;
	}

	@Override
	public List<AccountsSummary> getAccountSummary(String fromDate,String toDate,String headId) {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		List<AccountsSummary> query=new ArrayList<AccountsSummary>();
		try{
			tx=session.getTransaction();
			tx.begin();


			int count=0;

			String sql="";
			sql="select a.headTitle,ISNULL((select sum(b.amount) from tbAccftransection b where b.date between '"+fromDate+"' and '"+toDate+"' and b.approve='1' and b.d_l_id like ''+a.reference+'%'),0) as Debit,ISNULL((select sum(b.amount) from tbAccftransection b where b.date between '"+fromDate+"' and '"+toDate+"' and b.approve='1' and b.c_l_id like ''+a.reference+'%'),0) as Credit,a.Type from tbAccfhead a  where a.pheadId='"+headId+"'";

			System.out.println(sql);

			List<?> list = session.createSQLQuery(sql).list();

			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				Object[] element = (Object[]) iter.next();



				double debit=Double.parseDouble(element[1].toString());
				double credit=Double.parseDouble(element[2].toString());

				if(element[3].toString().equals("1")) {

					double balance=debit-credit;
					if(balance<0){
						credit=balance*(-1);
						debit=0;
					}
					else {
						debit=balance;
						credit=0;
					}
				}

				else if(element[3].toString().equals("2")) {

					double balance=credit-debit;
					if(balance<0){
						debit=balance*-1;
						credit=0;
					}
					else {
						credit=balance;
						debit=0;
					}
				}

				query.add(new AccountsSummary(element[0].toString(),Double.toString(debit),Double.toString(credit)));
				count++;
			}

			if(count==0) {
				sql="select a.headTitle,ISNULL((select sum(b.amount) from tbAccftransection b where b.date between '"+fromDate+"' and '"+toDate+"' and b.approve='1' and b.d_l_id like ''+a.reference+'%'),0) as Debit,ISNULL((select sum(b.amount) from tbAccftransection b where b.date between '"+fromDate+"' and '"+toDate+"' and b.approve='1' and b.c_l_id like ''+a.reference+'%'),0) as Credit,a.Type from tbAccfhead a  where a.headId='"+headId+"'";

				System.out.println(sql);

				List<?> list1 = session.createSQLQuery(sql).list();

				for(Iterator<?> iter = list1.iterator(); iter.hasNext();)
				{	
					Object[] element = (Object[]) iter.next();



					double debit=Double.parseDouble(element[1].toString());
					double credit=Double.parseDouble(element[2].toString());

					if(element[3].toString().equals("1")) {

						double balance=debit-credit;
						if(balance<0){
							credit=balance*(-1);
							debit=0;
						}
						else {
							debit=balance;
							credit=0;
						}
					}

					else if(element[3].toString().equals("2")) {

						double balance=credit-debit;
						if(balance<0){
							debit=balance*-1;
							credit=0;
						}
						else {
							credit=balance;
							debit=0;
						}
					}

					query.add(new AccountsSummary(element[0].toString(),Double.toString(debit),Double.toString(credit)));
				}
			}

			tx.commit();
		}
		catch(Exception e){

			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}

		finally {
			session.close();
		}

		return query;
	}

	@Override
	public boolean TransferTransaction(String transactionOnDate, String transferFrom, String transferTo,String userId,String Amount) {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;

		try{
			tx=session.getTransaction();
			tx.begin();


			String sql="";
			String d_l_id="",c_l_id="",TransId="";
			sql="select reference from tbAccfledger where ledgerId='"+transferFrom+"'";
			System.out.println(sql);

			List<?> list = session.createSQLQuery(sql).list();

			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				d_l_id=iter.next().toString();
				break;
			}

			sql="select reference from tbAccfledger where ledgerId='"+transferTo+"'";
			System.out.println(sql);

			List<?> list1 = session.createSQLQuery(sql).list();

			for(Iterator<?> iter = list1.iterator(); iter.hasNext();)
			{	
				c_l_id=iter.next().toString();
				break;
			}



			sql="select (ISNULL(max(transectionid),0)+1)as transectionid from tbAccftransection";
			List<?> list3 = session.createSQLQuery(sql).list();

			for(Iterator<?> iter = list3.iterator(); iter.hasNext();)
			{	
				TransId = iter.next().toString();
				break;

			}

			if(Double.parseDouble(Amount)>0) {
				sql="insert into tbAccftransection ("
						+ "transectionid,"
						+ "voucherNo,"
						+ "type,"
						+ "d_l_id,"
						+ "c_l_id,"
						+ "amount,"
						+ "description,"
						+ "approve,"
						+ "transfer,"
						+ "costCenterId,"
						+ "date,"
						+ "entryTime,"
						+ "UserId) values ('"+TransId+"',"
						+ "'0',"
						+ "'15',"
						+ "'"+d_l_id+"',"
						+ "'"+c_l_id+"',"
						+ "'"+Amount+"',"
						+ "'',"
						+ "'1',"
						+ "'0',"
						+ "'0',"
						+ "CURRENT_TIMESTAMP,"
						+ "CURRENT_TIMESTAMP,"
						+ "'"+userId+"')";

				session.createSQLQuery(sql).executeUpdate();

				sql="update tbAccftransection set transfer='1' where c_l_id='"+d_l_id+"' and Date='"+transactionOnDate+"'";
				session.createSQLQuery(sql).executeUpdate();


				tx.commit();
				return true;

			}


		}
		catch(Exception e){
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}

		}

		finally {
			session.close();
		}

		return false;
	}

	@Override
	public String getTransactionAmount(String transactionOnDate, String transferFrom) {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		String Amount="0";
		try{
			tx=session.getTransaction();
			tx.begin();

			String sql="";
			sql="select ISNULL(sum(Amount),0) as Amount from tbAccftransection where c_l_id=(select reference from tbAccfledger where ledgerId='"+transferFrom+"') and date='"+transactionOnDate+"'";
			System.out.println(sql);

			List<?> list = session.createSQLQuery(sql).list();

			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				Amount=iter.next().toString();
				break;
			}

			tx.commit();


		}
		catch(Exception e){
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}

		}

		finally {
			session.close();
		}

		return Amount;
	}

	@Override
	public List<TrialBalance> getTrialBalanceHistory(String fromDate,String toDate,String transactionType) {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		List<TrialBalance> query=new ArrayList<TrialBalance>();
		try{
			tx=session.getTransaction();
			tx.begin();
			
			String sql="";
			sql="select a.headTitle,a.reference,ISNULL((select sum(amount) from tbAccftransection where approve='1' and d_l_id like ''+(a.reference)+'%' and date<'"+fromDate+"'),0) as BeforeDebit,ISNULL((select sum(amount) from tbAccftransection where approve='1' and c_l_id like ''+(a.reference)+'%' and date<'"+fromDate+"'),0) as BeforeCredit,ISNULL((select sum(amount) from tbAccftransection where approve='1' and d_l_id like ''+(a.reference)+'%' and date between '"+fromDate+"' and '"+toDate+"'),0) as CurrentDebit,ISNULL((select sum(amount) from tbAccftransection where approve='1' and c_l_id like ''+(a.reference)+'%' and date between '"+fromDate+"' and '"+toDate+"'),0) as CurrentCredit,'"+fromDate+"' as StartDate,'"+toDate+"' as EndDate from tbAccfhead a where a.pheadId!='1' and a.pheadId!='0' and (round((len(a.reference) - len(replace(a.reference,'-',''))) / LEN('-'),1))<2 order by a.headid,a.reference";

			System.out.println(sql);

			List<?> list = session.createSQLQuery(sql).list();

			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				Object[] element = (Object[]) iter.next();
				
				if(transactionType.equals("0")) {
					query.add(new TrialBalance(element[0].toString(),element[1].toString(),element[2].toString(),element[3].toString(),element[4].toString(),element[5].toString(),element[6].toString(),element[7].toString()));
				}
				else {
					if(Double.parseDouble(element[2].toString())>0 || Double.parseDouble(element[3].toString())>0 || Double.parseDouble(element[4].toString())>0 || Double.parseDouble(element[5].toString())>0) {
						query.add(new TrialBalance(element[0].toString(),element[1].toString(),element[2].toString(),element[3].toString(),element[4].toString(),element[5].toString(),element[6].toString(),element[7].toString()));
					}
				}

				
			}

			tx.commit();
		}
		catch(Exception e){

			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}

		finally {
			session.close();
		}

		return query;
	}

	@Override
	public List<TrialBalance> geHeadWisetTrialBalanceHistory(String fromDate, String toDate, String title,String transactionType) {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		List<TrialBalance> query=new ArrayList<TrialBalance>();
		try{
			tx=session.getTransaction();
			tx.begin();
			
			String sql="";
			sql="select a.headTitle,a.reference,ISNULL((select sum(amount) from tbAccftransection where approve='1' and d_l_id like ''+(a.reference)+'%' and date<'"+fromDate+"'),0) as BeforeDebit,ISNULL((select sum(amount) from tbAccftransection where approve='1' and c_l_id like ''+(a.reference)+'%' and date<'"+fromDate+"'),0) as BeforeCredit,ISNULL((select sum(amount) from tbAccftransection where approve='1' and d_l_id like ''+(a.reference)+'%' and date between '"+fromDate+"' and '"+toDate+"'),0) as CurrentDebit,ISNULL((select sum(amount) from tbAccftransection where approve='1' and c_l_id like ''+(a.reference)+'%' and date between '"+fromDate+"' and '"+toDate+"'),0) as CurrentCredit,'"+fromDate+"' as StartDate,'"+toDate+"' as EndDate from tbAccfhead a where a.pheadId!='1' and a.pheadId!='0' and a.pheadId=(select headid from tbAccfhead where headTitle='"+title+"') order by a.headid,a.reference";

			System.out.println(sql);

			List<?> list = session.createSQLQuery(sql).list();

			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				Object[] element = (Object[]) iter.next();
				if(transactionType.equals("0")) {
					query.add(new TrialBalance(element[0].toString(),element[1].toString(),element[2].toString(),element[3].toString(),element[4].toString(),element[5].toString(),element[6].toString(),element[7].toString()));
				}
				else{
					if(Double.parseDouble(element[2].toString())>0 || Double.parseDouble(element[3].toString())>0 || Double.parseDouble(element[4].toString())>0 || Double.parseDouble(element[5].toString())>0) {
						query.add(new TrialBalance(element[0].toString(),element[1].toString(),element[2].toString(),element[3].toString(),element[4].toString(),element[5].toString(),element[6].toString(),element[7].toString()));
					}
				}
				
			}

			tx.commit();
		}
		catch(Exception e){

			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}

		finally {
			session.close();
		}

		return query;
	}

	@Override
	public List<TrialBalance> geHeadAndLedgerWiseTrialBalanceHistory(String fromDate, String toDate, String headName) {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		List<TrialBalance> query=new ArrayList<TrialBalance>();
		try{
			tx=session.getTransaction();
			tx.begin();
			
			String sql="";
			sql="select a.ledgerTitle,a.reference,ISNULL((select sum(amount) from tbAccftransection where approve='1' and d_l_id like ''+(a.reference)+'%' and date<'"+fromDate+"'),0) as BeforeDebit,ISNULL((select sum(amount) from tbAccftransection where approve='1' and c_l_id like ''+(a.reference)+'%' and date<'"+fromDate+"'),0) as BeforeCredit,ISNULL((select sum(amount) from tbAccftransection where approve='1' and d_l_id like ''+(a.reference)+'%' and date between '"+fromDate+"' and '"+toDate+"'),0) as CurrentDebit,ISNULL((select sum(amount) from tbAccftransection where approve='1' and c_l_id like ''+(a.reference)+'%' and date between '"+fromDate+"' and '"+toDate+"'),0) as CurrentCredit,'"+fromDate+"' as StartDate,'"+toDate+"' as EndDate from tbAccfledger a where a.pheadId=(select headid from tbAccfhead where headTitle='"+headName+"') order by a.ledgerId,a.reference";

			System.out.println(sql);

			List<?> list = session.createSQLQuery(sql).list();

			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				Object[] element = (Object[]) iter.next();
				query.add(new TrialBalance(element[0].toString(),element[1].toString(),element[2].toString(),element[3].toString(),element[4].toString(),element[5].toString(),element[6].toString(),element[7].toString()));
			}

			tx.commit();
		}
		catch(Exception e){

			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}

		finally {
			session.close();
		}

		return query;
	}

	@Override
	public List<AccountsDetails> getProfitAndLoss(String fromDate, String toDate) {
		// TODO Auto-generated method stub
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		List<AccountsDetails> query=new ArrayList<AccountsDetails>();
		try{
			tx=session.getTransaction();
			tx.begin();
			
			String sql="";
			sql="select a.headTitle,ISNULL((select sum(openingBalance) from tbAccfledger where reference like a.reference+'%'),0) as OpeningBalance,ISNULL((select sum(amount) from tbAccftransection where approve='1' and date between '"+fromDate+"' and '"+toDate+"' and c_l_id like a.reference+'%' ),0) as Amount1,ISNULL((select sum(amount) from tbAccftransection where approve='1' and date between '"+fromDate+"' and '"+toDate+"' and d_l_id like a.reference+'%'),0) as Amount2,'Revenue'  as Type,'"+fromDate+"' as StartDate,'"+toDate+"' as EndDate  from tbAccfhead a where a.pheadId='4' group by a.headTitle,a.reference UNION ALL select a.headTitle,ISNULL((select sum(openingBalance) from tbAccfledger where reference like a.reference+'%'),0) as OpeningBalance,ISNULL((select sum(amount) from tbAccftransection where approve='1' and date between '"+fromDate+"' and '"+toDate+"' and c_l_id like a.reference+'%' ),0) as Amount1,ISNULL((select sum(amount) from tbAccftransection where approve='1' and date between '"+fromDate+"' and '"+toDate+"' and d_l_id like a.reference+'%'),0) as Amount2,'Expense'  as Type,'"+fromDate+"' as StartDate,'"+toDate+"' as EndDate from tbAccfhead a where a.pheadId='3'  group by a.headTitle,a.reference";

			System.out.println(sql);

			List<?> list = session.createSQLQuery(sql).list();

			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				Object[] element = (Object[]) iter.next();
				String type=element[4].toString();
				double amount=0;
				double ob=Double.parseDouble(element[1].toString());
				double am1=Double.parseDouble(element[2].toString());
				double am2=Double.parseDouble(element[3].toString());
				if(element[4].toString().equals("Revenue")) {
					amount=(ob+am1)-am2;
				}
				else {
					amount=(ob+am2)-am1;
				}
				query.add(new AccountsDetails(element[0].toString(),Double.toString(amount),type));
			}

			tx.commit();
		}
		catch(Exception e){

			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}

		finally {
			session.close();
		}

		return query;
	}

	@Override
	public List<AccountsDetails> geHeadWisetPALHistory(String fromDate, String toDate, String headName,String headType) {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		List<AccountsDetails> query=new ArrayList<AccountsDetails>();
		try{
			tx=session.getTransaction();
			tx.begin();
			
			String sql="";
			sql="select a.headTitle,ISNULL((select sum(openingBalance) from tbAccfledger where reference=b.reference),0) as OpeningBalance,ISNULL((select sum(amount) from tbAccftransection where approve='1' and date between '"+fromDate+"' and '"+toDate+"' and c_l_id=b.reference ),0) as credit,ISNULL((select sum(amount) from tbAccftransection where approve='1' and date between '"+fromDate+"' and '"+toDate+"' and d_l_id=b.reference),0) as debit,'"+headType+"' as headType  from tbAccfhead a left join tbAccfledger b on a.headid=b.pheadId where a.pheadid=(select headId from tbAccfhead where headTitle='"+headName+"')";

			System.out.println(sql);

			List<?> list = session.createSQLQuery(sql).list();

			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				Object[] element = (Object[]) iter.next();
				query.add(new AccountsDetails(element[0].toString(),element[1].toString(),element[2].toString(),element[3].toString(),element[4].toString()));
			}

			tx.commit();
		}
		catch(Exception e){

			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}

		finally {
			session.close();
		}

		return query;
	}

	@Override
	public List<AccountsDetails> geHeadWiseLedgerPALHistory(String fromDate, String toDate, String headName,
			String headType) {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		List<AccountsDetails> query=new ArrayList<AccountsDetails>();
		try{
			tx=session.getTransaction();
			tx.begin();
			
			String sql="";
			sql="select a.ledgerTitle,a.LedgerId,ISNULL((select sum(openingBalance) from tbAccfledger where reference=a.reference),0) as OpeningBalance,ISNULL((select sum(amount) from tbAccftransection where approve='1' and date between '"+fromDate+"' and '"+toDate+"' and c_l_id=a.reference ),0) as credit,ISNULL((select sum(amount) from tbAccftransection where approve='1' and date between '"+fromDate+"' and '"+toDate+"' and d_l_id=a.reference),0) as debit,'"+headType+"' as headType  from  tbAccfledger a where a.pheadId=(select headId from tbAccfhead where headTitle='"+headName+"')";
			System.out.println(sql);
			List<?> list = session.createSQLQuery(sql).list();

			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				Object[] element = (Object[]) iter.next();
				query.add(new AccountsDetails(element[0].toString(),element[1].toString(),element[2].toString(),element[3].toString(),element[4].toString(),element[5].toString()));
			}

			tx.commit();
		}
		catch(Exception e){

			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}

		finally {
			session.close();
		}

		return query;
	}

	@Override
	public List<AccountsDetails> getBalanceSheetData(String fromDate, String toDate,String transactionType) {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		List<AccountsDetails> query=new ArrayList<AccountsDetails>();
		try{
			tx=session.getTransaction();
			tx.begin();
			
			String sql="";
			sql="select headTitle,sum(OpeningBalance) as OpeningBalance,sum(Credit) as Credit,sum(Debit) as Debit,GroupType as HeadType from funBalanceSheet('2020-10-01','"+fromDate+"')  group by Sactor,headTitle,GroupType order by GroupType";
			System.out.println(sql);
			List<?> list = session.createSQLQuery(sql).list();

			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				Object[] element = (Object[]) iter.next();
				
				double amount=0;
				double ob=Double.parseDouble(element[1].toString());
				double am1=Double.parseDouble(element[2].toString());
				double am2=Double.parseDouble(element[3].toString());
				if(element[4].toString().equals("Liability")) {
					amount=(ob+am1)-am2;
				}
				else {
					amount=(ob+am2)-am1;
				}
		
				if(transactionType.equals("0")) {
					query.add(new AccountsDetails(element[0].toString(),Double.toString(amount),element[4].toString()));
				}
				else {
					if(ob>0 || am1>0 || am2>0) {
						query.add(new AccountsDetails(element[0].toString(),Double.toString(amount),element[4].toString()));
					}

				
				}
				
			}

			tx.commit();
		}
		catch(Exception e){

			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}

		finally {
			session.close();
		}

		return query;
	}

	@Override
	public List<AccountsDetails> geHeadWisetBalHistory(String fromDate, String headName,
			String headType) {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		List<AccountsDetails> query=new ArrayList<AccountsDetails>();
		try{
			tx=session.getTransaction();
			tx.begin();
			
			String sql="";
			sql="select a.headTitle,ISNULL((select sum(openingBalance) from tbAccfledger where reference=b.reference),0) as OpeningBalance,ISNULL((select sum(amount) from tbAccftransection where approve='1' and date <='"+fromDate+"' and c_l_id=b.reference ),0) as credit,ISNULL((select sum(amount) from tbAccftransection where approve='1' and date <='"+fromDate+"' and d_l_id=b.reference),0) as debit,'"+headType+"' as headType  from tbAccfhead a left join tbAccfledger b on a.headid=b.pheadId where a.pheadid=(select headId from tbAccfhead where headTitle='"+headName+"')";
			
			System.out.println(sql);

			List<?> list = session.createSQLQuery(sql).list();

			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				Object[] element = (Object[]) iter.next();
				query.add(new AccountsDetails(element[0].toString(),element[1].toString(),element[2].toString(),element[3].toString(),element[4].toString()));
			}

			tx.commit();
		}
		catch(Exception e){

			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}

		finally {
			session.close();
		}

		return query;
	}

	@Override
	public List<AccountsDetails> geHeadWiseLedgerBalHistory(String fromDate, String headName, String headType) {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		List<AccountsDetails> query=new ArrayList<AccountsDetails>();
		try{
			tx=session.getTransaction();
			tx.begin();
			
			String sql="";
			sql="select a.ledgerTitle,a.LedgerId,ISNULL((select sum(openingBalance) from tbAccfledger where reference=a.reference),0) as OpeningBalance,ISNULL((select sum(amount) from tbAccftransection where approve='1' and date<='"+fromDate+"' and c_l_id=a.reference ),0) as credit,ISNULL((select sum(amount) from tbAccftransection where approve='1' and date<='"+fromDate+"' and d_l_id=a.reference),0) as debit,'"+headType+"' as headType  from  tbAccfledger a where a.pheadId=(select headId from tbAccfhead where headTitle='"+headName+"')";
			System.out.println(sql);
			List<?> list = session.createSQLQuery(sql).list();

			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				Object[] element = (Object[]) iter.next();
				query.add(new AccountsDetails(element[0].toString(),element[1].toString(),element[2].toString(),element[3].toString(),element[4].toString(),element[5].toString()));
			}

			tx.commit();
		}
		catch(Exception e){

			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}

		finally {
			session.close();
		}

		return query;
	}

	@Override
	public List<Voucher> getAllBookReport(String fromDate, String toDate, String voucherType) {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		List<Voucher> query=new ArrayList<Voucher>();
		try{
			tx=session.getTransaction();
			tx.begin();
			
			String sql="";
			if(voucherType.equals("0")) {
				sql="select a.type,a.voucherNo,(select Narration from TbVoucherType where type=a.type) as Narration,ISNULL((select ledgerTitle from tbAccfledger where reference=a.d_l_id),'') as DebitLedger,ISNULL((select LedgerTitle from tbAccfledger where reference=a.c_l_id),'') as CreditLedger,amount,(select name from TbCostCenterInfo where costCenterId=a.costCenterId) as CostCenterName,(select convert(varchar,a.Date, 120)) as Date,a.UserId from tbAccftransection a where  a.approve='1' and a.date between '"+fromDate+"' and '"+toDate+"' order by a.type,a.voucherNo";
			}
			else {
				sql="select a.type,a.voucherNo,(select Narration from TbVoucherType where type=a.type) as Narration,ISNULL((select ledgerTitle from tbAccfledger where reference=a.d_l_id),'') as DebitLedger,ISNULL((select LedgerTitle from tbAccfledger where reference=a.c_l_id),'') as CreditLedger,amount,(select name from TbCostCenterInfo where costCenterId=a.costCenterId) as CostCenterName,(select convert(varchar,a.Date, 120)) as Date,a.UserId from tbAccftransection a where a.type='"+voucherType+"' and  a.approve='1' and a.date between '"+fromDate+"' and '"+toDate+"' order by a.type,a.voucherNo";
			}
			
			System.out.println(sql);
			List<?> list = session.createSQLQuery(sql).list();

			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				Object[] element = (Object[]) iter.next();
				query.add(new Voucher(element[0].toString(),element[1].toString(),element[2].toString(),element[3].toString(),element[4].toString(),element[5].toString(),element[6].toString(),element[7].toString(),element[8].toString()));
			}

			tx.commit();
		}
		catch(Exception e){

			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}

		finally {
			session.close();
		}

		return query;
	}

	@Override
	public List<Voucher> getCashBankBooReport(String fromDate, String toDate, String voucherType) {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		List<Voucher> query=new ArrayList<Voucher>();
		try{
			tx=session.getTransaction();
			tx.begin();
			
			System.out.println("voucherType "+voucherType);
			
			String sql="";
			if(voucherType.equals("Cash Book")) {
				sql="select a.type,a.voucherNo,(select convert(varchar,a.Date, 120)) as Date,a.amount,(select ledgerTitle from tbAccfledger where reference=a.d_l_id)as DebitLedger,(select ledgerTitle from tbAccfledger where reference=a.c_l_id)as CreditLedger,'Cash Book' as BookType,'Debit' as TrasactionType,a.UserId,a.description  from tbAccftransection a where a.d_l_id like ''+(select reference from tbAccfhead where headTitle='Cash')+'%' and a.approve='1' and a.date between '"+fromDate+"' and '"+toDate+"' UNION ALL select a.type,a.voucherNo,(select convert(varchar,a.Date, 120)) as Date,a.amount,(select ledgerTitle from tbAccfledger where reference=a.d_l_id)as DebitLedger,(select ledgerTitle from tbAccfledger where reference=a.c_l_id)as CreditLedger,'Cash Book' as BookType,'Credit' as TrasactionType,a.UserId,a.description from tbAccftransection a where a.c_l_id like ''+(select reference from tbAccfhead where headTitle='Cash')+'%'  and a.approve='1' and a.date between '"+fromDate+"' and '"+toDate+"'";
			}
			else {
				sql="select a.type,a.voucherNo,(select convert(varchar,a.Date, 120)) as Date,a.amount,(select ledgerTitle from tbAccfledger where reference=a.d_l_id)as DebitLedger,(select ledgerTitle from tbAccfledger where reference=a.c_l_id)as CreditLedger,'Bank Book' as BookType,'Debit' as TrasactionType,a.UserId,a.description  from tbAccftransection a where a.d_l_id like ''+(select reference from tbAccfhead where headTitle='Bank')+'%' and a.approve='1' and a.date between '"+fromDate+"' and '"+toDate+"' UNION ALL select a.type,a.voucherNo,(select convert(varchar,a.Date, 120)) as Date,a.amount,(select ledgerTitle from tbAccfledger where reference=a.d_l_id)as DebitLedger,(select ledgerTitle from tbAccfledger where reference=a.c_l_id)as CreditLedger,'Bank Book' as BookType,'Credit' as TrasactionType,a.UserId,a.description from tbAccftransection a where a.c_l_id like ''+(select reference from tbAccfhead where headTitle='Bank')+'%'  and a.approve='1' and a.date between '"+fromDate+"' and '"+toDate+"'";
			}
			
			System.out.println(sql);
			List<?> list = session.createSQLQuery(sql).list();

			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				Object[] element = (Object[]) iter.next();
				query.add(new Voucher(element[0].toString(),element[1].toString(),element[2].toString(),element[3].toString(),element[4].toString(),element[5].toString(),element[6].toString(),element[7].toString(),element[8].toString(),element[9].toString(),"",""));
			}

			tx.commit();
		}
		catch(Exception e){

			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}

		finally {
			session.close();
		}

		return query;
	}

	@Override
	public boolean saveAllTypesVoucher(Voucher v) {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		try{
			tx=session.getTransaction();
			tx.begin();

			String TransId="0";
			String d_l_id="0",c_l_id="0";
			String sql="";
			
			
			//Debit-------
			String debitResultValue=v.getDebitresultList().substring(v.getDebitresultList().indexOf("[")+1, v.getDebitresultList().indexOf("]"));
			System.out.println("debitResultValue "+debitResultValue);
			StringTokenizer sizeToken=new StringTokenizer(debitResultValue,",");
			String fristToken="";
			while(sizeToken.hasMoreTokens()) {
				fristToken=sizeToken.nextToken();
				StringTokenizer resultToken=new StringTokenizer(fristToken,"*");
				while(resultToken.hasMoreTokens()) {
					String debitLedgerId=resultToken.nextToken();
					String accAmount=resultToken.nextToken();
					String accDescription=resultToken.nextToken();
					String accChequeNo=resultToken.nextToken();
					String accChequeDate=resultToken.nextToken();
					
					System.out.println("debitLedgerId "+debitLedgerId);
					System.out.println("accChequeDate "+accChequeDate);
					
					sql="select (ISNULL(max(transectionid),0)+1)as transectionid from tbAccftransection";
					List<?> list = session.createSQLQuery(sql).list();

					for(Iterator<?> iter = list.iterator(); iter.hasNext();)
					{	
						TransId = iter.next().toString();
						break;
					}
					
					sql="select reference from tbAccfledger where ledgerId='"+debitLedgerId+"'";
					List<?> listreference = session.createSQLQuery(sql).list();

					for(Iterator<?> iter = listreference.iterator(); iter.hasNext();)
					{	
						d_l_id=iter.next().toString();
						break;
					}

					if(!accAmount.equals("0")) {
						if(accChequeDate.equals("0")) {
							sql="insert into tbAccftransection ("
									+ "transectionid,"
									+ "voucherNo,"
									+ "type,"
									+ "d_l_id,"
									+ "c_l_id,"
									+ "amount,"
									+ "description,"
									+ "standby,"
									+ "costCenterId,"
									+ "approve,"
									+ "date,"
									+ "entryTime,"
									+ "UserId"
									+ ") values ("
									+ "'"+TransId+"',"
									+ "'"+v.getVoucherNo()+"',"
									+ "'"+v.getPaymentType()+"',"
									+ "'"+d_l_id+"',"
									+ "'0',"
									+ "'"+accAmount+"',"
									+ "'"+accDescription+"',"
									+ "'"+v.getStandBy()+"',"
									+ "'"+v.getCostCenterId()+"',"
									+ "'0',"
									+ "'"+v.getDate()+"',"
									+ "CURRENT_TIMESTAMP,"
									+ "'"+v.getUserId()+"')";
						}
						else {
							sql="insert into tbAccftransection ("
									+ "transectionid,"
									+ "voucherNo,"
									+ "type,"
									+ "d_l_id,"
									+ "c_l_id,"
									+ "amount,"
									+ "description,"
									+ "standby,"
									+ "costCenterId,"
									+ "chequeNumber,"
									+ "chequeDate,"
									+ "approve,"
									+ "date,"
									+ "entryTime,"
									+ "UserId"
									+ ") values ("
									+ "'"+TransId+"',"
									+ "'"+v.getVoucherNo()+"',"
									+ "'"+v.getPaymentType()+"',"
									+ "'"+d_l_id+"',"
									+ "'0',"
									+ "'"+accAmount+"',"
									+ "'"+accDescription+"',"
									+ "'"+v.getStandBy()+"',"
									+ "'"+v.getCostCenterId()+"',"
									+ "'"+accChequeNo+"',"
									+ "'"+accChequeDate+"',"
									+ "'0',"
									+ "'"+v.getDate()+"',"
									+ "CURRENT_TIMESTAMP,"
									+ "'"+v.getUserId()+"')";
						}

						session.createSQLQuery(sql).executeUpdate();
					}

				}
			}

			//Credit-------
			String creditResultValue=v.getCreditresultList().substring(v.getCreditresultList().indexOf("[")+1, v.getCreditresultList().indexOf("]"));
			System.out.println("creditResultValue "+creditResultValue);
			StringTokenizer csizeToken=new StringTokenizer(creditResultValue,",");
			String cfristToken="";
			while(csizeToken.hasMoreTokens()) {
				cfristToken=csizeToken.nextToken();
				StringTokenizer resultToken=new StringTokenizer(cfristToken,"*");
				while(resultToken.hasMoreTokens()) {
					String creditLedgerId=resultToken.nextToken();
					String accAmount=resultToken.nextToken();
					String accDescription=resultToken.nextToken();
					String accChequeNo=resultToken.nextToken();
					String accChequeDate=resultToken.nextToken();
					
					System.out.println("creditLedgerId "+creditLedgerId);
					System.out.println("accChequeDate "+accChequeDate);
					
					sql="select (ISNULL(max(transectionid),0)+1)as transectionid from tbAccftransection";
					List<?> list = session.createSQLQuery(sql).list();

					for(Iterator<?> iter = list.iterator(); iter.hasNext();)
					{	
						TransId = iter.next().toString();
						break;
					}
					
					sql="select reference from tbAccfledger where ledgerId='"+creditLedgerId+"'";
					List<?> listreference = session.createSQLQuery(sql).list();

					for(Iterator<?> iter = listreference.iterator(); iter.hasNext();)
					{	
						c_l_id=iter.next().toString();
						break;
					}

					if(!accAmount.equals("0")) {
						if(accChequeDate.equals("0")) {
							sql="insert into tbAccftransection ("
									+ "transectionid,"
									+ "voucherNo,"
									+ "type,"
									+ "d_l_id,"
									+ "c_l_id,"
									+ "amount,"
									+ "description,"
									+ "standby,"
									+ "costCenterId,"
									+ "approve,"
									+ "date,"
									+ "entryTime,"
									+ "UserId"
									+ ") values ("
									+ "'"+TransId+"',"
									+ "'"+v.getVoucherNo()+"',"
									+ "'"+v.getPaymentType()+"',"
									+ "'0',"
									+ "'"+c_l_id+"',"
									+ "'"+accAmount+"',"
									+ "'"+accDescription+"',"
									+ "'"+v.getStandBy()+"',"
									+ "'"+v.getCostCenterId()+"',"
									+ "'0',"
									+ "'"+v.getDate()+"',"
									+ "CURRENT_TIMESTAMP,"
									+ "'"+v.getUserId()+"')";
						}
						else {
							sql="insert into tbAccftransection ("
									+ "transectionid,"
									+ "voucherNo,"
									+ "type,"
									+ "d_l_id,"
									+ "c_l_id,"
									+ "amount,"
									+ "description,"
									+ "standby,"
									+ "costCenterId,"
									+ "chequeNumber,"
									+ "chequeDate,"
									+ "approve,"
									+ "date,"
									+ "entryTime,"
									+ "UserId"
									+ ") values ("
									+ "'"+TransId+"',"
									+ "'"+v.getVoucherNo()+"',"
									+ "'"+v.getPaymentType()+"',"
									+ "'0',"
									+ "'"+c_l_id+"',"
									+ "'"+accAmount+"',"
									+ "'"+accDescription+"',"
									+ "'"+v.getStandBy()+"',"
									+ "'"+v.getCostCenterId()+"',"
									+ "'"+accChequeNo+"',"
									+ "'"+accChequeDate+"',"
									+ "'0',"
									+ "'"+v.getDate()+"',"
									+ "CURRENT_TIMESTAMP,"
									+ "'"+v.getUserId()+"')";
						}

						session.createSQLQuery(sql).executeUpdate();
					}

				}
			}
			tx.commit();

			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
				return false;
			}
			
		}

		finally {
			session.close();
		}

		return false;
	}

	@Override
	public List<DailyStatement> StatementViewList(String fromDate, String toDate, String statementType) {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		List<DailyStatement> query=new ArrayList<DailyStatement>();
		try{
			tx=session.getTransaction();
			tx.begin();
			
			String sql="";
			sql="select * from funDailyStatement('"+fromDate+"','"+toDate+"','"+statementType+"')";
			
			System.out.println(sql);
			List<?> list = session.createSQLQuery(sql).list();

			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				Object[] element = (Object[]) iter.next();
				query.add(new DailyStatement(element[0].toString(),element[1].toString(),element[2].toString(),element[3].toString(),element[4].toString(),element[5].toString()));
			}

			tx.commit();
		}
		catch(Exception e){

			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}

		finally {
			session.close();
		}

		return query;
	}

	@Override
	public List<DailyStatement> statementIncomeDetails(String fromDate, String toDate, String headName) {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		List<DailyStatement> query=new ArrayList<DailyStatement>();
		try{
			tx=session.getTransaction();
			tx.begin();
			
			String sql="",depId="",transactionType="";
			if(headName.equals("IPD Income")) {
				depId="1";
				transactionType="1";
			}
			
			else if(headName.equals("OPD Income")) {
				depId="2";
				transactionType="1";
			}
			
			else if(headName.equals("Lab Income")) {
				depId="3";
				transactionType="1";
			}
			
			sql="select (select ledgerId from tbAccfledger where reference=c_l_id) as LedgerId,(select ledgerTitle from tbAccfledger where reference=c_l_id) as LedgerName,sum(amount) as Amount,DepId,TransactionType from tbAccftransection where depId='"+depId+"' and transactionType='"+transactionType+"' and date between '"+fromDate+"' and '"+toDate+"' group by c_l_id,depId,transactionType";
			
			System.out.println(sql);
			List<?> list = session.createSQLQuery(sql).list();

			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				Object[] element = (Object[]) iter.next();
				query.add(new DailyStatement(element[0].toString(),element[1].toString(),element[2].toString(),element[3].toString(),element[4].toString()));
			}

			tx.commit();
		}
		catch(Exception e){

			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}

		finally {
			session.close();
		}

		return query;
	}

	@Override
	public List<DailyStatement> statementDiscountDetails(String fromDate, String toDate, String headName) {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		List<DailyStatement> query=new ArrayList<DailyStatement>();
		try{
			tx=session.getTransaction();
			tx.begin();
			
			String sql="",depId="",transactionType="";
			if(headName.equals("IPD Income")) {
				depId="1";
				transactionType="2";
			}
			
			else if(headName.equals("OPD Income")) {
				depId="2";
				transactionType="2";
			}
			
			else if(headName.equals("Lab Income")) {
				depId="3";
				transactionType="2";
			}
			
			sql="select (select ledgerId from tbAccfledger where reference=c_l_id) as LedgerId,(select ledgerTitle from tbAccfledger where reference=c_l_id) as LedgerName,sum(amount) as Amount,DepId,TransactionType from tbAccftransection where depId='"+depId+"' and transactionType='"+transactionType+"' and date between '"+fromDate+"' and '"+toDate+"' group by c_l_id,depId,transactionType";
			
			System.out.println(sql);
			List<?> list = session.createSQLQuery(sql).list();

			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				Object[] element = (Object[]) iter.next();
				query.add(new DailyStatement(element[0].toString(),element[1].toString(),element[2].toString(),element[3].toString(),element[4].toString()));
			}

			tx.commit();
		}
		catch(Exception e){

			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}

		finally {
			session.close();
		}
		return query;
	}

	@Override
	public List<DailyStatement> statementCashDetails(String fromDate, String toDate, String headName) {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		List<DailyStatement> query=new ArrayList<DailyStatement>();
		try{
			tx=session.getTransaction();
			tx.begin();
			
			String sql="",depId="",transactionType="";
			if(headName.equals("IPD Income")) {
				depId="1";
				transactionType="3";
			}
			
			else if(headName.equals("OPD Income")) {
				depId="2";
				transactionType="3";
			}
			
			else if(headName.equals("Lab Income")) {
				depId="3";
				transactionType="3";
			}
			
			sql="select (select ledgerId from tbAccfledger where reference=c_l_id) as LedgerId,(select ledgerTitle from tbAccfledger where reference=c_l_id) as LedgerName,sum(amount) as Amount,DepId,Transactiontype from tbAccftransection where depId='"+depId+"' and transactionType='"+transactionType+"' and date between '"+fromDate+"' and '"+toDate+"' group by c_l_id,depId,transactionType";
			
			System.out.println(sql);
			List<?> list = session.createSQLQuery(sql).list();

			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				Object[] element = (Object[]) iter.next();
				query.add(new DailyStatement(element[0].toString(),element[1].toString(),element[2].toString(),element[3].toString(),element[4].toString()));
			}

			tx.commit();
		}
		catch(Exception e){

			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}

		finally {
			session.close();
		}
		return query;
	}

	@Override
	public String getTypeWiseVoucherNo(String voucherType) {
		// TODO Auto-generated method stub
		String VoucherNo="";
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		List<DailyStatement> query=new ArrayList<DailyStatement>();
		try{
			tx=session.getTransaction();
			tx.begin();
			
			String sql="",depId="",transactionType="";
			
			
			sql="select (ISNULL(max(voucherNo),0)+1)as voucherNo from tbAccftransection where type='"+voucherType+"'";
			
			System.out.println(sql);
			List<?> list = session.createSQLQuery(sql).list();

			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				VoucherNo=iter.next().toString();
			}

			tx.commit();
		}
		catch(Exception e){

			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}

		finally {
			session.close();
		}
		return VoucherNo;
	}

	@Override
	public List<DailyStatement> statementExpenseDetails(String fromDate, String toDate, String headName) {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		List<DailyStatement> query=new ArrayList<DailyStatement>();
		try{
			tx=session.getTransaction();
			tx.begin();
			
			String sql="";
			
			
			
			sql="select (select ledgerId from tbAccfledger where reference=d_l_id) as LedgerId,(select ledgerTitle from tbAccfledger where reference=d_l_id) as LedgerName,amount,ISNULL(DepId,'') as DepId,ISNULL(Transactiontype,'') as Transactiontype from tbAccftransection where type='1' and d_l_id!='0' and date between '"+fromDate+"' and '"+toDate+"'";
			
			System.out.println(sql);
			List<?> list = session.createSQLQuery(sql).list();

			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				Object[] element = (Object[]) iter.next();
				query.add(new DailyStatement(element[0].toString(),element[1].toString(),element[2].toString(),element[3].toString(),element[4].toString()));
			}

			tx.commit();
		}
		catch(Exception e){

			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}

		finally {
			session.close();
		}

		return query;
	}

	@Override
	public List<BankReconcilation> getBankReconcilationItemList() {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		List<BankReconcilation> query=new ArrayList<BankReconcilation>();
		try{
			tx=session.getTransaction();
			tx.begin();
			
			String sql="";

			sql="select particularId,particularName from TbBankReconcilationItemInfo order by particularName";
			
			System.out.println(sql);
			List<?> list = session.createSQLQuery(sql).list();

			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				Object[] element = (Object[]) iter.next();
				query.add(new BankReconcilation(element[0].toString(),element[1].toString()));
			}

			tx.commit();
		}
		catch(Exception e){

			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}

		finally {
			session.close();
		}

		return query;
	}

	@Override
	public String saveBankReconcilationMonthlyTransaction(BankReconcilation v) {
		String msg="";
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		List<BankReconcilation> query=new ArrayList<BankReconcilation>();
		try{
			tx=session.getTransaction();
			tx.begin();
			
			String sql="";
			
			String fiscalYear=new SimpleDateFormat("yyyy").format(new Date());
			
			String Month=new SimpleDateFormat("MMMM").format(new Date());
			System.out.println("Month "+Month);
			
			int save=0;
			String ResultValue=v.getResultList().substring(v.getResultList().indexOf("[")+1, v.getResultList().indexOf("]"));
			StringTokenizer resultToken=new StringTokenizer(ResultValue,",");
			while(resultToken.hasMoreTokens()) {
				String secondValue=resultToken.nextToken();
				StringTokenizer finalToken=new StringTokenizer(secondValue,"*");
				while(finalToken.hasMoreElements()) {
					String itemId=finalToken.nextToken();
					String accAmount=finalToken.nextToken();
					String description=finalToken.nextToken();
					
					sql="insert into TbBankReconcilationEntryInfo (ItemId,Amount,Description,Date,EntryTime,Month,FiscalYear,UserId) values ('"+itemId+"','"+accAmount+"','"+description+"',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'"+Month+"','"+fiscalYear+"','"+v.getUserId()+"')";
					session.createSQLQuery(sql).executeUpdate();
					save++;
				}
			}

			if(save!=0) {
				msg="Bank Reconcilation Entry Successfully";
			}
		

			tx.commit();
		}
		catch(Exception e){
			msg=e.getMessage();
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}

		finally {
			session.close();
		}

		return msg;
	}

	@Override
	public List<BankReconcilation> getBankReconcilationList() {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		List<BankReconcilation> query=new ArrayList<BankReconcilation>();
		try{
			tx=session.getTransaction();
			tx.begin();
			
			String sql="";
			
			sql="select  MONTH,FiscalYear from TbBankReconcilationEntryInfo FiscalYear group by MONTH,FiscalYear";
			
			System.out.println(sql);
			List<?> list = session.createSQLQuery(sql).list();

			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				Object[] element = (Object[]) iter.next();
				query.add(new BankReconcilation(element[0].toString(),element[1].toString(),""));
			}

			tx.commit();
		}
		catch(Exception e){

			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}

		finally {
			session.close();
		}

		return query;
	}

	@Override
	public boolean isHeadHasTransaction(String headId) {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		List<Voucher> query=new ArrayList<Voucher>();
		try{
			tx=session.getTransaction();
			tx.begin();
			
			
			String sql="";
			int headFlag=0;
			int ledgerFlag=0;
			sql="select headId from tbAccfhead where pheadId='"+headId+"'";	
			System.out.println(sql);
			List<?> list = session.createSQLQuery(sql).list();

			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				headFlag=1;
				break;		
			}
			
			sql="select ledgerId from tbAccfledger where reference like '"+headId+"%'";	
			System.out.println(sql);
			List<?> list1 = session.createSQLQuery(sql).list();

			for(Iterator<?> iter = list1.iterator(); iter.hasNext();)
			{	
				ledgerFlag=1;
				break;		
			}
			tx.commit();
			
			if(headFlag==1 || ledgerFlag==1) {
				return true;
			}
			else {
				return false;
			}

		}
		catch(Exception e){

			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}

		finally {
			session.close();
		}
		return false;
	}
	
	@Override
	public boolean deleteHead(String headId) {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		List<Voucher> query=new ArrayList<Voucher>();
		try{
			tx=session.getTransaction();
			tx.begin();

			String sql="";
			sql="delete  from tbAccfhead where headId='"+headId+"'";	
			session.createSQLQuery(sql).executeUpdate();
			tx.commit();
			return true;
		}
		catch(Exception e){

			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}

		finally {
			session.close();
		}
		return false;
	}


}
