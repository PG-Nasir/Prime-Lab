package pg.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import pg.SettingModel.Employee;
import pg.assetModel.AssetInformation;
import pg.assetModel.VendorInformation;
import pg.share.HibernateUtil;

@Repository
public class AssetDAOImpl implements AssetDAO{

	@Override
	public boolean isAssetExist(AssetInformation v) {
		// TODO Auto-generated method stub
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		try{
			tx=session.getTransaction();
			tx.begin();

			String sql="select AssetName from TbAssetInformation where AssetName='"+v.getAssetName()+"'";

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
	public boolean saveAsset(AssetInformation v) {
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			tx.begin();
			String sql = "insert into TbAssetInformation (AssetName, CategoryId, Location, Brand,Model, SerialNo, Status, Condition, Note,"
					+ " Vendor, PoNumber,PoDate, PurchaseValue, Qty, MarketValue, ScrapValue, DepreciationMethod, EstimateLife,UserId,Entrytime,date) values"
					+ " ('" + v.getAssetName()+ "','" + v.getAssetTypeId()+ "','"+v.getLocation()+"',"
					+ "'"+v.getBrand()+"','"+v.getModel()+"','"+v.getSerialNo()+"','"+v.getStatus()+"',"
					+ "'"+v.getCondition()+"','"+v.getNote()+"','"+v.getVendorId() + "',"
					+ "'"+v.getPoNumber()+"','"+v.getPurchaseDate()+"','"+v.getPurchaseValue()+"',"
					+ "'"+v.getQty()+"','"+v.getMarketValue()+"','"+v.getScrapValue()+"',"
					+ "'"+v.getDepreciationMethod()+"','"+v.getEstimateLife()+"','"+v.getUserId()+"',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP)";
			//			System.err.println("sql : "+sql);
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
	public List<VendorInformation> getVendorList() {
		// TODO Auto-generated method stub
		String sql="";
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		List<VendorInformation> dataList=new ArrayList<VendorInformation>();
		
		try{
			tx=session.getTransaction();
			tx.begin();

			int i=1;
			//String sql="select a.AutoId,a.EmployeeCode, a.Name, a.CardNo, a.DepartmentId, (select b.DepartmentName from TbDepartmentInfo b where b.DepartmentId=a.DepartmentId) as DepartmentName, a.DesginationId, (select c.DesignationName from TbDesignationInfo c where c.DesignationId=a.DesginationId) as Designation, a.LineId, a.Grade, isnull(CONVERT(VARCHAR(50),JoinDate),'') as JoinDate from TbEmployeeInfo a";
			sql="select VendorId,VendorName from tbVendorInformation order by VendorName";
			List<?> list = session.createSQLQuery(sql).list();
			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	

				Object[] element = (Object[]) iter.next();
				dataList.add(new VendorInformation(element[0].toString(), element[1].toString()));

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
	public List<AssetInformation> getAssetList() {
		// TODO Auto-generated method stub
		String sql="";
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		List<AssetInformation> dataList=new ArrayList<AssetInformation>();
		
		try{
			tx=session.getTransaction();
			tx.begin();

			int i=1;
			//String sql="select a.AutoId,a.EmployeeCode, a.Name, a.CardNo, a.DepartmentId, (select b.DepartmentName from TbDepartmentInfo b where b.DepartmentId=a.DepartmentId) as DepartmentName, a.DesginationId, (select c.DesignationName from TbDesignationInfo c where c.DesignationId=a.DesginationId) as Designation, a.LineId, a.Grade, isnull(CONVERT(VARCHAR(50),JoinDate),'') as JoinDate from TbEmployeeInfo a";
			sql="select AssetId,AssetName,(select CategoryName from TbCategory where CategoryId=TbAssetInformation.CategoryId) as Category  from TbAssetInformation order by CategoryId,AssetName";
			List<?> list = session.createSQLQuery(sql).list();
			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	

				Object[] element = (Object[]) iter.next();
				dataList.add(new AssetInformation(element[0].toString(), element[1].toString(),element[2].toString()));

				i++;
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
		return dataList;
	}

}
