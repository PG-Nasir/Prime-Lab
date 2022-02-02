package pg.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import pg.ChequeWriter.ChequeWriter;
import pg.assetModel.VendorInformation;
import pg.share.HibernateUtil;

@Repository
public class ChequeWriterDAOImpl implements ChequeWriterDAO{

	@Override
	public List<ChequeWriter> getBankList() {
		// TODO Auto-generated method stub
		String sql="";
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		List<ChequeWriter> dataList=new ArrayList<ChequeWriter>();
		
		try{
			tx=session.getTransaction();
			tx.begin();

			int i=1;
			
			sql="select BankId,BankName from TbBankName order by BankName";
			List<?> list = session.createSQLQuery(sql).list();
			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	

				Object[] element = (Object[]) iter.next();
				dataList.add(new ChequeWriter(element[0].toString(), element[1].toString()));

				i++;
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
		return dataList;
	}

	@Override
	public boolean saveChequeWriter(ChequeWriter v) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			tx.begin();
			String sql = "insert into TbChequeWriter (BankId, PayTo, Amount, Date,Type, Entrytime, UserId) values"
					+ " ('" + v.getBankId()+ "','" + v.getPayTo()+ "','"+v.getAmount()+"',"
					+ "'"+v.getDate()+"','"+v.getPrintCategory()+"',CURRENT_TIMESTAMP,'"+v.getUserId()+"')";
			session.createSQLQuery(sql).executeUpdate();
			tx.commit();
			return true;

		} catch (Exception ee) {
			if (tx != null) {
				tx.rollback();
				return false;
			}
			ee.printStackTrace();
		}
		finally {
			session.close();
		}
		return false;
	}

	@Override
	public List<ChequeWriter> getChequeList() {
		// TODO Auto-generated method stub
		String sql="";
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		List<ChequeWriter> dataList=new ArrayList<ChequeWriter>();
		
		try{
			tx=session.getTransaction();
			tx.begin();

			int i=1;
			
			sql="select WriterId,(select BankName from TbBankName where BankId=a.BankId) as BankName,a.PayTo,a.Amount,(select convert(varchar,a.Date,103)) as date,Type,(select fullname from  Tblogin where id=a.userId) as Username from TbChequeWriter a order by a.WriterId";
			List<?> list = session.createSQLQuery(sql).list();
			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				Object[] element = (Object[]) iter.next();
				dataList.add(new ChequeWriter(element[0].toString(), element[1].toString(),element[2].toString(),element[3].toString(),element[4].toString(),element[5].toString(),element[6].toString()));
				i++;
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
		return dataList;
	}

	@Override
	public List<ChequeWriter> getChequeWriterDetails(String writerId) {
		// TODO Auto-generated method stub
		String sql="";
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		List<ChequeWriter> dataList=new ArrayList<ChequeWriter>();
		
		try{
			tx=session.getTransaction();
			tx.begin();

			int i=1;
			
			sql="select WriterId,BankId,PayTo,Amount,(select convert(varchar,a.Date,103)) as date,Type from TbChequeWriter a where a.WriterId='"+writerId+"'";
			List<?> list = session.createSQLQuery(sql).list();
			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				Object[] element = (Object[]) iter.next();
				dataList.add(new ChequeWriter(element[0].toString(), element[1].toString(),element[2].toString(),element[3].toString(),element[4].toString(),element[5].toString()));
				i++;
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
		return dataList;
	}

	@Override
	public boolean addNewBank(ChequeWriter v) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			tx.begin();
			String sql = "insert into TbBankName (BankName,ReportName,Entrytime, UserId) values"
					+ " ('" + v.getBankName()+ "','" + v.getReportName()+ "',CURRENT_TIMESTAMP,'"+v.getUserId()+"')";
			session.createSQLQuery(sql).executeUpdate();
			tx.commit();
			return true;

		} catch (Exception ee) {
			if (tx != null) {
				tx.rollback();
				return false;
			}
			ee.printStackTrace();
		}
		finally {
			session.close();
		}
		return false;
	}

}
