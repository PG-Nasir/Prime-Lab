package pg.dao;

import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

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
import pg.share.HibernateUtil;


@Repository
public class LabDAOImpl implements LabDAO{

	DecimalFormat df = new DecimalFormat("#.00");

	@Override
	public List<Test> getTestlist(String testGroupId) {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		List<Test> query=new ArrayList<Test>();
		try{
			tx=session.getTransaction();
			tx.begin();

			String sql="";

			if(testGroupId.equals("0")) {
				sql="select TestId,(select GroupName from TbLabTestGroup where HeadId=a.HeadId) as HeadName,a.HeadId as groupId,a.TestName,a.Rate,a.Discount as DoctorComission,a.Unit,a.NormalRange,DiscountAllow from TbTestName a order by a.headId,a.testName";
			}
			else {
				sql="select TestId,(select GroupName from TbLabTestGroup where HeadId=a.HeadId) as HeadName,a.HeadId as groupId,a.TestName,a.Rate,a.Discount as DoctorComission,a.Unit,a.NormalRange,DiscountAllow from TbTestName a where a.HeadId='"+testGroupId+"' order by a.headId,a.testName";
			}

			List<?> list = session.createSQLQuery(sql).list();

			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				Object[] element = (Object[]) iter.next();
				query.add(new Test(element[0].toString(),element[1].toString(),element[2].toString(),element[3].toString(),Double.toString(Double.parseDouble(element[4].toString())),element[5].toString(),element[6].toString(),element[7].toString(),element[8].toString()));
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
	public boolean addTest(Test v) {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		try{
			tx=session.getTransaction();
			tx.begin();


			String sql="insert into TbTestName ("
					+ "HeadId,"
					+ "TestName,"
					+ "TestCode,"
					+ "Rate,"
					+ "Discount,"
					+ "Unit,"
					+ "NormalRange,"
					+ "DiscountAllow,"
					+ "HeadShow,"
					+ "createBy,"
					+ "entrytime"
					+ ") values ("
					+ "'"+v.getHeadId()+"',"
					+ "'"+v.getTestName()+"',"
					+ "'"+v.getTestCode()+"',"
					+ "'"+v.getRate()+"',"
					+ "'"+v.getDoctorCommission()+"',"
					+ "'"+v.getUnit()+"',"
					+ "'"+v.getNormalRange()+"',"
					+ "'"+v.getDiscountAllow()+"',"
					+ "'0',"
					+ "'"+v.getUserId()+"',"
					+ "CURRENT_TIMESTAMP"
					+ ")";

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
	public List<Test> getMainTestList(String value) {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		List<Test> query=new ArrayList<Test>();
		try{
			tx=session.getTransaction();
			tx.begin();


			List<?> list = session.createSQLQuery("select TestName,TestId,rate from TbTestName where TestName like '%"+value+"%' order by TestName").list();

			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				Object[] element = (Object[]) iter.next();
				query.add(new Test(element[0].toString(),element[1].toString(),element[2].toString()));
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
	public boolean addSubTest(SubTest v) {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		try{
			tx=session.getTransaction();
			tx.begin();


			String sql="insert into TbSubTestName ("
					+ "TestId,"
					+ "SubTestName,"
					+ "CalculateType,"
					+ "Unit,"
					+ "NormalRange,"
					+ "Sorting,"
					+ "createBy,"
					+ "entrytime"
					+ ") values ("
					+ "'"+v.getParentTest()+"',"
					+ "'"+v.getSubTestName()+"',"
					+ "'"+v.getCalculate()+"',"
					+ "'"+v.getUnit()+"',"
					+ "'"+v.getNormalRange()+"',"
					+ "'"+v.getSorting()+"',"
					+ "'"+v.getUserId()+"',"
					+ "CURRENT_TIMESTAMP"
					+ ")";

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
	public List<Test> TestWiseSubTestList(String value) {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		List<Test> query=new ArrayList<Test>();
		try{
			tx=session.getTransaction();
			tx.begin();


			List<?> list = session.createSQLQuery("select SubTestId,(select TestName from TbTestName where TestId=a.TestId) as TestName,a.SubTestName,a.Unit,a.NormalRange,a.Sorting from TbSubTestName a where a.TestId='"+value+"' order by a.testId,a.subtestname").list();

			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				Object[] element = (Object[]) iter.next();
				query.add(new Test(element[0].toString(),element[1].toString(),element[2].toString(),element[3].toString(),element[4].toString(),element[5].toString()));
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
	public boolean isSubTestExist(SubTest v) {
		// TODO Auto-generated method stub
		boolean flag=false;
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		try{
			tx=session.getTransaction();
			tx.begin();

			String sql="select SubTestName from TbSubTestName where TestId='"+v.getParentTest()+"' and SubTestId!='"+v.getSubTestId()+"' and SubTestName='"+v.getSubTestName()+"'";

			List<?> list = session.createSQLQuery(sql).list();
			if(list.size()>0)
				return flag=true;
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
		return flag;
	}


	@Override
	public boolean editSubTest(SubTest v) {
		// TODO Auto-generated method stub
		boolean flag=false;
		String sql="";
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		int a=0;
		try{
			tx=session.getTransaction();
			tx.begin();

			sql="update TbSubTestName set TestId='"+v.getParentTest()+"', SubTestName='"+v.getSubTestName()+"', CalculateType='"+v.getCalculate()+"', Unit='"+v.getUnit()+"',NormalRange='"+v.getNormalRange()+"',sorting='"+v.getSorting()+"', entryTime=CURRENT_TIMESTAMP, createBy='"+v.getUserId()+"' where SubTestId='"+v.getSubTestId()+"' ";
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
	public List<SubTest> TestWiseSubTestList() {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		List<SubTest> query=new ArrayList<SubTest>();
		try{
			tx=session.getTransaction();
			tx.begin();


			List<?> list = session.createSQLQuery("select SubTestId,a.SubTestName,a.CalculateType,a.TestId,(select TestName from TbTestName where TestId=a.TestId) as TestName,a.Unit,a.NormalRange,a.Sorting from TbSubTestName a order by a.testId,a.subtestId").list();

			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				Object[] element = (Object[]) iter.next();
				query.add(new SubTest(element[0].toString(),element[1].toString(),element[2].toString(),element[3].toString(),element[4].toString(),element[5].toString(),element[6].toString(),element[7].toString()));
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
	public boolean isTestParticularExist(TestWiseParticularName v) {
		// TODO Auto-generated method stub
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		try{
			tx=session.getTransaction();
			tx.begin();

			String sql="select * from TbTestPerticularName where TestId='"+v.getTestId()+"' and ParticularRefId='"+v.getParticularRefId()+"' and ParticularId!='"+v.getParticularId()+"'";

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
	public boolean addTestParticular(TestWiseParticularName v) {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		String sql="";
		boolean flag=false;
		try{
			tx=session.getTransaction();
			tx.begin();

			sql="insert into TbTestPerticularName (TestId, ParticularRefId, Qty, Rate, entryTime, createBy) values ('"+v.getTestId()+"', '"+v.getParticularRefId()+"',  '"+v.getQty()+"','"+v.getRate()+"', CURRENT_TIMESTAMP, '"+v.getUserId()+"')";
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
	public boolean editTestParticular(TestWiseParticularName v) {
		boolean flag=false;
		String sql="";
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		try{
			tx=session.getTransaction();
			tx.begin();

			sql="update TbTestPerticularName set TestId='"+v.getTestId()+"', ParticularRefId='"+v.getParticularRefId()+"', Qty='"+v.getQty()+"', Rate='"+v.getRate()+"', entryTime=CURRENT_TIMESTAMP, createBy='"+v.getUserId()+"' where ParticularId='"+v.getParticularId()+"' and TestId='"+v.getTestId()+"'";
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
	public List<TestWiseParticularName> TestWiseParticularList() {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		List<TestWiseParticularName> query=new ArrayList<TestWiseParticularName>();
		try{
			tx=session.getTransaction();
			tx.begin();


			List<?> list = session.createSQLQuery("select TestId,isnull((select TestName from TbTestName where TestId=a.TestId),'') as TestName,ParticularId,a.ParticularRefId,(select t.ParticularName from TbTestParticular t where t.ParticularId=a.ParticularRefId) as particularName,a.Rate,a.Qty from TbTestPerticularName a order by a.testId,a.ParticularRefId").list();

			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				Object[] element = (Object[]) iter.next();
				query.add(new TestWiseParticularName(element[0].toString(),element[1].toString(),element[2].toString(),element[3].toString(),element[4].toString(),element[5].toString(),element[6].toString()));
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
	public boolean AddPatientInforWithTest(LabBilling v) {

		//String labId=getMaxLabId();


		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		try{
			tx=session.getTransaction();
			tx.begin();

			if(v.getFind().equals("1")) {
				return true;
			}
			else {
				int check=0;
				String sql="select RegNo from TbLabCounterWisePatientInfomation where CreateBy='"+v.getUserId()+"' and Counter='"+v.getCounter()+"' and labId IS NULL";
				List<?> list = session.createSQLQuery(sql).list();

				for(Iterator<?> iter = list.iterator(); iter.hasNext();)
				{	
					check=1;	
					break;
				}

				String referralId="",cReferralId="";
				sql="select DoctorId from TbDoctorInfo where Name='"+v.getReferral_search()+"'";
				List<?> list1 = session.createSQLQuery(sql).list();

				for(Iterator<?> iter = list1.iterator(); iter.hasNext();)
				{	
					referralId=iter.next().toString();	
					break;
				}

				sql="select DoctorId from TbDoctorInfo where Name='"+v.getReferralcomission()+"'";
				List<?> list2 = session.createSQLQuery(sql).list();

				for(Iterator<?> iter = list2.iterator(); iter.hasNext();)
				{	
					cReferralId=iter.next().toString();	
					break;
				}


				if(check==0) {
					sql="insert into TbLabCounterWisePatientInfomation ("
							+ "Counter,"
							+ "BillType,"
							+ "RegNo,"
							+ "PatientName,"
							+ "PatientFiscalYear,"
							+ "Period,"
							+ "Mobile,"
							+ "Age,"
							+ "Month,"
							+ "day,"
							+ "Sex,"
							+ "Cabin,"
							+ "address,"
							+ "RfPersionId,"
							+ "RefferBy,"
							+ "PercentDiscount,"
							+ "ManualDiscount,"
							+ "createBy,"
							+ "entrytime"
							+ ") values ("
							+ "'"+v.getCounter()+"',"
							+ "'"+v.getBillType()+"',"
							+ "'"+v.getRegno()+"',"
							+ "'"+v.getPatientname()+"',"
							+ "'"+v.getFiscalyear()+"',"
							+ "'"+v.getPeriod()+"',"
							+ "'"+v.getMobile()+"',"
							+ "'"+v.getAge()+"',"
							+ "'"+v.getMonth()+"',"
							+ "'"+v.getDay()+"',"
							+ "'"+v.getSex()+"',"
							+ "'"+v.getBedcabin()+"',"
							+ "'"+v.getAddress()+"',"
							+ "'"+cReferralId+"',"
							+ "'"+referralId+"',"
							+ "'"+v.getPercentdiscount()+"',"
							+ "'"+v.getManualdiscount()+"',"
							+ "'"+v.getUserId()+"',"
							+ "CURRENT_TIMESTAMP"
							+ ")";

					session.createSQLQuery(sql).executeUpdate();


					tx.commit();

					return true;
				}
			}





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
	public boolean checkHasCounterPatient(String userId, String counter,String labId,String find) {

		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		try{
			tx=session.getTransaction();
			tx.begin();

			String sql="";

			if(find.equals("0")) {
				sql="select * from TbLabCounterWisePatientInfomation where CreateBy='"+userId+"' and Counter='"+counter+"' and labId IS NULL";
				List<?> list = session.createSQLQuery(sql).list();

				for(Iterator<?> iter = list.iterator(); iter.hasNext();)
				{	
					return true;	
				}
			}
			else {
				return true;
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


		return false;
	}

	@Override
	public String getMaxLabId() {
		String id="";

		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		try{
			tx=session.getTransaction();
			tx.begin();

			String currentMonth="";
			String sql="SELECT  cast(DATENAME(M,'"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"') as varchar) as MName";
			System.out.println(sql);

			List<?> list = session.createSQLQuery(sql).list();

			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				currentMonth =iter.next().toString();
				break;

			}

			String fiscalYear=new SimpleDateFormat("yyyy").format(new Date());

			sql="select (ISNULL(max(labId),0)+1)as labId from TbLabPatient where FiscalYear='"+fiscalYear+"' and CMonth='"+currentMonth+"'";
			list = session.createSQLQuery(sql).list();

			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				id = iter.next().toString();

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

		return id;
	}

	@Override
	public boolean AddTestForThisCounterPatient(LabBilling v) {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		try{
			tx=session.getTransaction();
			tx.begin();

			String CMonth=getCurrentMonth();

			double manualdiscount=0;
			String testId="";
			String sql="select TestId from TbTestName where TestName='"+v.getTestId()+"'";
			List<?> list9 = session.createSQLQuery(sql).list();

			for(Iterator<?> iter = list9.iterator(); iter.hasNext();)
			{	
				testId = iter.next().toString();
			}

			String discountAllow=getDiscountAllow(testId);

			double cmddiscount=getCmdDiscount(testId,v.getReferralcomission());

			String headId=getHeadId(testId);
			String rate=getRate(testId);
			String deductionRate=getDeductionRate(testId,v.getReferralcomission());
			String testName=v.getTestId();

			if(v.getFind().equals("0")) {
				sql="insert into TbLabTestHistory ("
						+ "regNo,"
						+ "testId,"
						+ "testName,"
						+ "headId,"
						+ "qty,"
						+ "rate,"
						+ "deductionRate,"
						+ "discount,"
						+ "ManualDiscount,"
						+ "CmdDiscount,"
						+ "discountAllow,"
						+ "ResultStatus,"
						+ "RefundStatus,"
						+ "type,"
						+ "counter,"
						+ "date,"
						+ "FiscalYear,"
						+ "CMonth,"
						+ "createBy,"
						+ "entrytime"
						+ ") values ("
						+ "'"+v.getRegno()+"',"
						+ "'"+testId+"',"
						+ "'"+testName+"',"
						+ "'"+headId+"',"
						+ "'1',"
						+ "'"+rate+"',"
						+ "'"+deductionRate+"',"
						+ "'"+v.getPercentdiscount()+"',"
						+ "'"+manualdiscount+"',"
						+ "'"+cmddiscount+"',"
						+ "'"+discountAllow+"',"
						+ "'0',"
						+ "'0',"
						+ "'1',"
						+ "'"+v.getCounter()+"',"
						+ "CURRENT_TIMESTAMP,"
						+ "'"+v.getFiscalyear()+"',"
						+ "'"+CMonth+"',"
						+ "'"+v.getUserId()+"',"
						+ "CURRENT_TIMESTAMP"
						+ ")";

				session.createSQLQuery(sql).executeUpdate();
			}
			else {
				sql="insert into TbLabTestHistory ("
						+ "regNo,"
						+ "labId,"
						+ "testId,"
						+ "testName,"
						+ "headId,"
						+ "qty,"
						+ "rate,"
						+ "deductionRate,"
						+ "discount,"
						+ "ManualDiscount,"
						+ "CmdDiscount,"
						+ "discountAllow,"
						+ "ResultStatus,"
						+ "RefundStatus,"
						+ "type,"
						+ "counter,"
						+ "date,"
						+ "FiscalYear,"
						+ "CMonth,"
						+ "createBy,"
						+ "entrytime"
						+ ") values ("
						+ "'"+v.getRegno()+"',"
						+ "'"+v.getLabId()+"',"
						+ "'"+testId+"',"
						+ "'"+testName+"',"
						+ "'"+headId+"',"
						+ "'1',"
						+ "'"+rate+"',"
						+ "'"+deductionRate+"',"
						+ "'"+v.getPercentdiscount()+"',"
						+ "'"+manualdiscount+"',"
						+ "'"+cmddiscount+"',"
						+ "'"+discountAllow+"',"
						+ "'0',"
						+ "'0',"
						+ "'1',"
						+ "'"+v.getCounter()+"',"
						+ "CURRENT_TIMESTAMP,"
						+ "'"+v.getFiscalyear()+"',"
						+ "'"+CMonth+"',"
						+ "'"+v.getUserId()+"',"
						+ "CURRENT_TIMESTAMP"
						+ ")";

				session.createSQLQuery(sql).executeUpdate();
			}


			ArrayList TId=new ArrayList();
			ArrayList NameOfPericular=new ArrayList();
			ArrayList NameOfRate=new ArrayList();
			ArrayList<Integer> NameOfQty=new ArrayList<Integer>();

			int count=0;
			sql="select ParticularId,(select particularName from TbTestParticular where ParticularId=ParticularRefId) as ParticularName,Qty,Rate from tbtestperticularname where TestId='"+testId+"'";
			List<?> list = session.createSQLQuery(sql).list();

			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				Object[] element = (Object[]) iter.next();


				TId.add(element[0].toString());
				NameOfPericular.add(element[1].toString());
				NameOfRate.add(element[3].toString());
				NameOfQty.add(Integer.parseInt(element[2].toString()));
				count++;

			}

			System.out.println("count "+count);

			for(int a=0;a<count;a++){

				int check=0;
				if(v.getFind().equals("0")) {
					sql="select tblabtesthistory.TestId from tblabtesthistory where tblabtesthistory.FiscalYear='"+v.getFiscalyear()+"' and CMonth='"+v.getcMonth()+"' and tblabtesthistory.TestName='"+NameOfPericular.get(a).toString()+"' and tblabtesthistory.type='2' and tblabtesthistory.labId IS NULL and tblabtesthistory.counter='"+v.getCounter()+"' and tblabtesthistory.createBy='"+v.getUserId()+"'";
				}
				else {
					sql="select tblabtesthistory.TestId from tblabtesthistory where tblabtesthistory.FiscalYear='"+v.getFiscalyear()+"' and CMonth='"+v.getcMonth()+"' and tblabtesthistory.TestName='"+NameOfPericular.get(a).toString()+"' and tblabtesthistory.type='2' and tblabtesthistory.labId='"+v.getLabId()+"' ";
				}

				List<?> list1 = session.createSQLQuery(sql).list();
				for(Iterator<?> iter = list1.iterator(); iter.hasNext();)
				{	
					check=1;
					break;

				}

				if(check==0) {
					String query="";
					if(v.getFind().equals("0")) {
						query ="insert into tblabtesthistory (regNo,testId,testName,qty,rate,discount,discountAllow,ResultStatus,RefundStatus,type,counter,date,entryTime,createBy,FiscalYear,CMonth) values ('"+v.getRegno()+"','"+TId.get(a).toString()+"','"+NameOfPericular.get(a).toString()+"','"+NameOfQty.get(a).toString()+"','"+df.format(Double.parseDouble(NameOfRate.get(a).toString()))+"','0','0','NOT DONE','0','2','"+v.getCounter()+"',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'"+v.getUserId()+"','"+v.getFiscalyear()+"','"+CMonth+"')";
						System.out.println(query);
						session.createSQLQuery(query).executeUpdate();

						String Udquery ="insert into tbUdlabtesthistory (regNo,testId,testName,qty,rate,discount,discountAllow,ResultStatus,RefundStatus,type,counter,date,entryTime,createBy,flag,FiscalYear,CMonth) values ('"+v.getRegno()+"','"+TId.get(a).toString()+"','"+NameOfPericular.get(a).toString()+"','"+NameOfQty.get(a).toString()+"','"+df.format(Double.parseDouble(NameOfRate.get(a).toString()))+"','0','0','NOT DONE','0','2','"+v.getCounter()+"',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'"+v.getUserId()+"','NEW','"+v.getFiscalyear()+"','"+CMonth+"')";
						System.out.println(Udquery);
						session.createSQLQuery(Udquery).executeUpdate();
					}
					else {
						query ="insert into tblabtesthistory (regNo,labId,testId,testName,qty,rate,discount,discountAllow,ResultStatus,RefundStatus,type,counter,date,entryTime,createBy,FiscalYear,CMonth) values ('"+v.getRegno()+"','"+v.getLabId()+"','"+TId.get(a).toString()+"','"+NameOfPericular.get(a).toString()+"','"+NameOfQty.get(a).toString()+"','"+df.format(Double.parseDouble(NameOfRate.get(a).toString()))+"','0','0','NOT DONE','0','2','"+v.getCounter()+"',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'"+v.getUserId()+"','"+v.getFiscalyear()+"','"+CMonth+"')";
						System.out.println(query);
						session.createSQLQuery(query).executeUpdate();

						String Udquery ="insert into tbUdlabtesthistory (regNo,labId,testId,testName,qty,rate,discount,discountAllow,ResultStatus,RefundStatus,type,counter,date,entryTime,createBy,flag,FiscalYear,CMonth) values ('"+v.getRegno()+"','"+v.getLabId()+"','"+TId.get(a).toString()+"','"+NameOfPericular.get(a).toString()+"','"+NameOfQty.get(a).toString()+"','"+df.format(Double.parseDouble(NameOfRate.get(a).toString()))+"','0','0','NOT DONE','0','2','"+v.getCounter()+"',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'"+v.getUserId()+"','NEW','"+v.getFiscalyear()+"','"+CMonth+"')";
						System.out.println(Udquery);
						session.createSQLQuery(Udquery).executeUpdate();
					}

				}	

			}

			TId.clear();
			NameOfPericular.clear();
			NameOfRate.clear();


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


	private boolean checkInvoiceDoublePerticular(String sql) {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		try{
			tx=session.getTransaction();
			tx.begin();

			System.out.println(sql);

			List<?> list = session.createSQLQuery(sql).list();

			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				return true;

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
		return false;
	}

	private String getDeductionRate(String TestId,String CReferralId) {
		double rate=0;
		double deductionRate=0;
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		try{
			tx=session.getTransaction();
			tx.begin();

			double cmdDiscount=0;
			String sql="";
			int check=0;

			sql="select Rate from tbtestname where TestId='"+TestId+"'";
			System.out.println(sql);

			List<?> list = session.createSQLQuery(sql).list();

			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				rate =Double.parseDouble(iter.next().toString());
				break;

			}


			sql="select DoctorComission,DoctorComissionDeduction from TbDoctorComissionSet where DoctorId='"+CReferralId+"' and TestGroupId=(select HeadId from TbTestName where TestId='"+TestId+"')";
			System.out.println(sql);
			double deductionPercent=0;

			List<?> list1 = session.createSQLQuery(sql).list();

			for(Iterator<?> iter = list1.iterator(); iter.hasNext();)
			{	Object[] element = (Object[]) iter.next();
			double DoctorComission =Double.parseDouble(element[0].toString());
			double DoctorComissionDeduction =Double.parseDouble(element[1].toString());
			deductionPercent=DoctorComission-DoctorComissionDeduction;
			check=1;
			break;

			}

			if(check==1) {
				deductionRate=rate-((rate*deductionPercent)/100);
			}
			else {
				deductionRate=rate;
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

		return Double.toString(deductionRate);
	}

	private String getRate(String testId) {
		String rate="";
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		try{
			tx=session.getTransaction();
			tx.begin();


			String sql="select Rate from tbtestname where TestId='"+testId+"'";
			System.out.println(sql);

			List<?> list = session.createSQLQuery(sql).list();

			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				rate =iter.next().toString();
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

		return rate;
	}

	private String getTestName(String testId) {
		// TODO Auto-generated method stub
		String testName="";
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		try{
			tx=session.getTransaction();
			tx.begin();


			String sql="select testName from tbtestname where TestId='"+testId+"'";
			System.out.println(sql);

			List<?> list = session.createSQLQuery(sql).list();

			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				testName =iter.next().toString();
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

		return testName;
	}


	private String getHeadId(String TestId) {
		String headId="";
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		try{
			tx=session.getTransaction();
			tx.begin();


			String sql="select HeadId from tbtestname where TestId='"+TestId+"'";
			System.out.println(sql);

			List<?> list = session.createSQLQuery(sql).list();

			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				headId =iter.next().toString();
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

		return headId;
	}

	private String getDiscountAllow(String TestId) {
		String DiscountAllow="0";
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		try{
			tx=session.getTransaction();
			tx.begin();


			String sql="select ISNULL(DiscountAllow,0) as DiscountAllow from tbtestname where TestId='"+TestId+"'";
			System.out.println(sql);

			List<?> list = session.createSQLQuery(sql).list();

			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				DiscountAllow =iter.next().toString();
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

		return DiscountAllow;
	}

	private double getCmdDiscount(String TestId,String CReferralId) {
		double cmdDiscount=0;
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		try{
			tx=session.getTransaction();
			tx.begin();

			String sql="";
			int check=0;
			sql="select DoctorComission,DoctorComissionDeduction from TbDoctorComissionSet where DoctorId='"+CReferralId+"' and TestGroupId=(select HeadId from TbTestName where TestId='"+TestId+"')";
			System.out.println(sql);

			List<?> list1 = session.createSQLQuery(sql).list();

			for(Iterator<?> iter = list1.iterator(); iter.hasNext();)
			{	Object[] element = (Object[]) iter.next();
			cmdDiscount =Double.parseDouble(element[0].toString());
			check=1;
			break;

			}

			if(check!=1) {
				sql="select ISNULL(Discount,0) as Discount from tbtestname where TestId='"+TestId+"'";
				System.out.println(sql);

				List<?> list = session.createSQLQuery(sql).list();

				for(Iterator<?> iter = list.iterator(); iter.hasNext();)
				{	
					cmdDiscount =Double.parseDouble(iter.next().toString());
					break;

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

		return cmdDiscount;
	}

	@Override
	public boolean CheckTestForThisCounterPatient(LabBilling v) {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		try{
			tx=session.getTransaction();
			tx.begin();

			String testId="";
			String sql="select TestId from TbTestName where TestName='"+v.getTestId()+"'";
			List<?> list9 = session.createSQLQuery(sql).list();

			for(Iterator<?> iter = list9.iterator(); iter.hasNext();)
			{	
				testId = iter.next().toString();
			}

			if(v.getFind().equals("0")) {
				sql="select testId from TbLabTestHistory where CreateBy='"+v.getUserId()+"' and FiscalYear='"+v.getFiscalyear()+"' and CMonth='"+v.getFiscalyear()+"' and Counter='"+v.getCounter()+"' and labId IS NULL and TestId='"+testId+"'";
			}
			else {
				sql="select testId from TbLabTestHistory where  FiscalYear='"+v.getFiscalyear()+"' and CMonth='"+v.getFiscalyear()+"' and labId='"+v.getLabId()+"' and TestId='"+testId+"'";
			}


			List<?> list = session.createSQLQuery(sql).list();

			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				return true;	
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


		return false;
	}


	public boolean CheckForThisCounterPatient(LabBilling v) {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		try{
			tx=session.getTransaction();
			tx.begin();


			String sql="select labId from TbLabTestHistory where CreateBy='"+v.getUserId()+"' and Counter='"+v.getCounter()+"' and labId IS NULL ";
			List<?> list = session.createSQLQuery(sql).list();

			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				return true;	
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


		return false;
	}

	@Override
	public List<LabBilling> getTestForThisCounterPatient(LabBilling v) {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		List<LabBilling> query=new ArrayList<LabBilling>();
		try{
			tx=session.getTransaction();
			tx.begin();

			String sql="";
			if(v.getFind().equals("0")) {
				sql="select a.type,a.TestId,a.TestName,a.Qty,a.Rate,a.Discount,(a.Rate*a.Discount/100) as DiscountAmount,a.Rate-(a.Rate*a.Discount/100) as PayableAmount,a.counter from tblabtesthistory a where a.labId IS NULL and a.counter='"+v.getCounter()+"' and a.FiscalYear='"+v.getFiscalyear()+"' and a.createBy='"+v.getUserId()+"' order by a.type,a.autoId desc";
			}
			else {
				sql="select a.type,a.TestId,a.TestName,a.Qty,a.Rate,a.Discount,(a.Rate*a.Discount/100) as DiscountAmount,a.Rate-(a.Rate*a.Discount/100) as PayableAmount,a.counter from tblabtesthistory a where a.labId='"+v.getLabId()+"' and a.FiscalYear='"+v.getFiscalyear()+"'  order by a.type,a.autoId desc";
			}

			System.out.println(sql);
			List<?> list = session.createSQLQuery(sql).list();

			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				Object[] element = (Object[]) iter.next();
				query.add(new LabBilling(element[0].toString(),element[1].toString(),element[2].toString(),element[3].toString(),element[4].toString(),element[5].toString(),element[6].toString(),element[7].toString(),element[8].toString(),""));
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
	public List<LabBilling> counterWisePendingTestWithPatientInfo(String userId, String counter) {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		List<LabBilling> query=new ArrayList<LabBilling>();
		try{
			tx=session.getTransaction();
			tx.begin();


			String sql="select b.type,b.testId,b.TestName,b.Qty,b.Rate,b.Discount,(b.Rate*b.Discount/100) as DiscountAmount,b.Rate-(b.Rate*b.Discount/100) as PayableAmount,a.BillType,a.counter,a.RegNo,a.PatientName,a.Mobile,a.Age,a.Month,a.Day,a.Sex,a.Cabin,a.address,ISNULL((select Name from TbDoctorInfo where DoctorId=a.RefferBy ),'') as DoctorName1,ISNULL((select Degree from TbDoctorInfo where DoctorId=a.RefferBy ),'') as Degree1,a.RfPersionId,ISNULL((select Name from TbDoctorInfo where DoctorId=a.RefferBy ),'') as DoctorName2,ISNULL((select Degree from TbDoctorInfo where DoctorId=a.RefferBy ),'') as Degree2,a.RefferBy,a.PercentDiscount,a.ManualDiscount from TbLabCounterWisePatientInfomation a join TbLabTestHistory b on a.Counter=b.counter and a.CreateBy=b.createBy  where  b.labId IS NULL and a.Counter='"+counter+"' and a.CreateBy='"+userId+"' order by b.type,b.autoId desc";
			System.out.println(sql);
			List<?> list = session.createSQLQuery(sql).list();

			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				Object[] element = (Object[]) iter.next();
				query.add(new LabBilling(element[0].toString(),element[1].toString(),element[2].toString(),element[3].toString(),element[4].toString(),element[5].toString(),element[6].toString(),element[7].toString(),element[8].toString(),element[9].toString(),element[10].toString(),element[11].toString(),element[12].toString(),element[13].toString(),element[14].toString(),element[15].toString(),element[16].toString(),element[17].toString(),element[18].toString(),element[19].toString(),element[20].toString(),element[21].toString(),element[22].toString(),element[23].toString(),element[24].toString(),element[25].toString(),element[26].toString()));
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
	public boolean BillPost(LabBilling v) {
		String labId=getMaxLabId();

		if(CheckForThisCounterPatient(v)) {


			Session session=HibernateUtil.openSession();
			Transaction tx=null;
			try{
				tx=session.getTransaction();
				tx.begin();

				double totalDiscount=Double.parseDouble(v.getPerdiscount_taka())+Double.parseDouble(v.getMdiscount_tata());

				double cmddiscount=0;

				String discountAllow="";

				int Period=0;
				String PatientFiscalYear=v.getPatientfiscalyear();

				if(v.getBillType().equals("1")) {
					Period=Integer.parseInt(v.getPeriod());
				}

				String sql="select Type from TbDoctorInfo where Name='"+v.getReferralcomission()+"'";
				System.out.println(sql);
				String commissonAllow="";
				List<?> list9 = session.createSQLQuery(sql).list();

				for(Iterator<?> iter = list9.iterator(); iter.hasNext();)
				{	
					commissonAllow=iter.next().toString();
				}

				commissonAllow=commissonAllow.equals("Referral")?"1":"0";

				String referralId="0",cReferralId="0",extraCommission="0";
				sql="select DoctorId from TbDoctorInfo where Name='"+v.getReferral_search()+"'";
				List<?> list1 = session.createSQLQuery(sql).list();

				for(Iterator<?> iter = list1.iterator(); iter.hasNext();)
				{	
					referralId=iter.next().toString();	
					System.out.println("blank "+referralId);
					break;
				}

				sql="select DoctorId from TbDoctorInfo where Name='"+v.getReferralcomission()+"'";
				List<?> list2 = session.createSQLQuery(sql).list();

				for(Iterator<?> iter = list2.iterator(); iter.hasNext();)
				{	
					cReferralId=iter.next().toString();	
					System.out.println("cblank "+cReferralId);
					break;
				}

				sql="select DoctorId from TbDoctorInfo where Name='"+v.getExtraCommission()+"'";
				List<?> list3 = session.createSQLQuery(sql).list();

				for(Iterator<?> iter = list3.iterator(); iter.hasNext();)
				{	
					extraCommission=iter.next().toString();	
					break;
				}



				String CMonth=getCurrentMonth();
				String deliverDateTime="";

				String currentTime=new SimpleDateFormat("HH").format(new Date());

				String currentDate=new SimpleDateFormat("yyyy-MM-dd").format(new Date());

				int hourNumber=Integer.parseInt(currentTime);
				System.out.println("hourNumber "+hourNumber);

				if(hourNumber>=13) {
					Date Sdate=new SimpleDateFormat("yyyy-MM-dd").parse(currentDate);

					Calendar cal=Calendar.getInstance();
					cal.setTime(Sdate);
					cal.add(Calendar.DAY_OF_YEAR, 1);
					Date plusDate=cal.getTime();

					String nextDate=new SimpleDateFormat("yyyy-MM-dd").format(plusDate)+" 8 PM";	

					deliverDateTime=nextDate;
				}
				else {
					deliverDateTime=v.getDeliverydatetime();
				}

				System.out.println("deliverDateTime "+deliverDateTime);

				sql="insert into TbLabPatient ("
						+ "labId,"
						+ "BillType,"
						+ "RegNo,"
						+ "PatientName,"
						+ "Mobile,"
						+ "Age,"
						+ "Month,"
						+ "day,"
						+ "Sex,"
						+ "Cabin,"
						+ "address,"
						+ "RfPersionId,"
						+ "RefferBy,extraCommission,"
						+ "Mode,"
						+ "DateTime,"
						+ "CommissionAllow,"
						+ "PercentDiscount,"
						+ "Discount,"
						+ "ManualDiscount,"
						+ "totalDiscount,"
						+ "TotalCharge,"
						+ "TotalPayable,"
						+ "Paid,"
						+ "vat,"
						+ "CO,"
						+ "SampleCollect,"
						+ "ReportDelivery,"
						+ "DeliverStatus,"
						+ "remark,"
						+ "lasPaid,"
						+ "entryTime,"
						+ "ActualEntryTime,"
						+ "CreateBy,"
						+ "Period,"
						+ "PatientFiscalYear,"
						+ "FiscalYear,"
						+ "CMonth"
						+ ") values ("
						+ "'"+labId+"',"
						+ "'"+v.getBillType()+"',"
						+ "'"+v.getRegno()+"',"
						+ "'"+v.getPatientname()+"',"
						+ "'"+v.getMobile()+"',"
						+ "'"+v.getAge()+"',"
						+ "'"+v.getMonth()+"',"
						+ "'"+v.getDay()+"',"
						+ "'"+v.getSex()+"',"
						+ "'"+v.getBedcabin()+"',"
						+ "'"+v.getAddress()+"',"
						+ "'"+cReferralId+"',"
						+ "'"+referralId+"',"
						+ "'"+extraCommission+"',"
						+ "'0',"
						+ "CURRENT_TIMESTAMP,"
						+ "'"+commissonAllow+"',"
						+ "'"+v.getPercentdiscount()+"',"
						+ "'"+v.getPerdiscount_taka()+"',"
						+ "'"+v.getMdiscount_tata()+"',"
						+ "'"+totalDiscount+"',"
						+ "'"+v.getTotalamount()+"',"
						+ "'"+v.getTotalpayable()+"',"
						+ "'"+v.getPaid()+"',"
						+ "'0',"
						+ "'',"
						+ "'',"
						+ "'"+deliverDateTime+"',"
						+ "'0',"
						+ "'"+v.getRemark()+"',"
						+ "'"+v.getPaid()+"',"
						+ "CURRENT_TIMESTAMP,"
						+ "CURRENT_TIMESTAMP,"
						+ "'"+v.getUserId()+"',"
						+ "'"+Period+"',"
						+ "'"+v.getPatientfiscalyear()+"',"
						+ "'"+v.getFiscalyear()+"',"
						+ "'"+CMonth+"'"
						+ ")";

				System.out.println(sql);
				session.createSQLQuery(sql).executeUpdate();


				String updatelabhistory="update TbLabTestHistory set labId='"+labId+"',discount='"+v.getPercentdiscount()+"' where labId IS NULL and createBy='"+v.getUserId()+"' and counter='"+v.getCounter()+"'";
				session.createSQLQuery(updatelabhistory).executeUpdate();

				String deletecounter="delete from TbLabCounterWisePatientInfomation where labId IS NULL and createBy='"+v.getUserId()+"' and counter='"+v.getCounter()+"'";
				session.createSQLQuery(deletecounter).executeUpdate();

				String paysql="insert into TbLabPaymentHistory (LabId,Cash,Card,BillType,PaymentStatus,date,EntryTime,createBy,FiscalYear,CMonth,PaymentType) values ('"+labId+"','"+v.getPaid()+"','0','"+v.getBillType()+"','Paid',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'"+v.getUserId()+"','"+v.getFiscalyear()+"','"+CMonth+"','New Collection') ";
				session.createSQLQuery(paysql).executeUpdate();

				double TotalPaidCash=0;
				String PaidLastDate="";
				sql="select cash,convert(varchar,date, 120) as LastDate from TbLabPaymentHistory where LabId='"+labId+"' and FiscalYear='"+v.getFiscalyear()+"' and CMonth='"+CMonth+"' order by autoId asc";
				List<?> list4 = session.createSQLQuery(sql).list();

				System.out.println("size "+list4.size());

				for(Iterator<?> iter = list4.iterator(); iter.hasNext();)
				{	Object[] element = (Object[]) iter.next();

				TotalPaidCash=TotalPaidCash+Double.parseDouble(element[0].toString());
				PaidLastDate=element[1].toString();
				}

				System.out.println("TotalPaidCash "+TotalPaidCash);
				if(TotalPaidCash==Double.parseDouble(v.getTotalpayable()) && !PaidLastDate.equals("")) {
					String paidSql="update TbLabPatient set PaidDate='"+PaidLastDate+"' where LabId='"+labId+"' and FiscalYear='"+v.getFiscalyear()+"' and CMonth='"+CMonth+"'";
					session.createSQLQuery(paidSql).executeUpdate();
				}

				//Outdoor bill hit to accounts
				if(v.getBillType().equals("2")) {
					String d_l_id="";
					String c_l_id="";

					d_l_id="2-50-26";
					c_l_id="4-51-27";

					//Sales..................
					String TransId="";
					sql="select (ISNULL(max(transectionid),0)+1)as transectionid from tbAccftransection";
					List<?> list = session.createSQLQuery(sql).list();

					for(Iterator<?> iter = list.iterator(); iter.hasNext();)
					{	
						TransId = iter.next().toString();
						break;
					}

					String accsql="insert into tbAccftransection (transectionid,voucherNo,type,d_l_id,c_l_id,amount,description,groupId,standby,approve,costCenterId,status,date,entryTime,UserId) values ('"+TransId+"','"+v.getLabId()+"','12','"+d_l_id+"','"+c_l_id+"','"+v.getTotalamount()+"','Outdoor Lab Sale','0','0','0','0','Income',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'"+v.getUserId()+"') ";
					session.createSQLQuery(accsql).executeUpdate();
					//Discount.................
					if(totalDiscount>0) {

						d_l_id="3-52-28";
						c_l_id="2-50-26";

						sql="select (ISNULL(max(transectionid),0)+1)as transectionid from tbAccftransection";
						List<?> listdis = session.createSQLQuery(sql).list();

						for(Iterator<?> iter = listdis.iterator(); iter.hasNext();)
						{	
							TransId = iter.next().toString();
							break;
						}

						accsql="insert into tbAccftransection (transectionid,voucherNo,type,d_l_id,c_l_id,amount,description,groupId,standby,approve,costCenterId,status,date,entryTime,UserId) values ('"+TransId+"','"+v.getLabId()+"','13','"+d_l_id+"','"+c_l_id+"','"+totalDiscount+"','Outdoor Lab Discount','0','0','0','0','Expense',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'"+v.getUserId()+"') ";
						session.createSQLQuery(accsql).executeUpdate();
					}
					//Cash................................
					if(Double.parseDouble(v.getPaid())>0) {
						d_l_id="2-6-14-7";
						c_l_id="2-50-26";

						sql="select (ISNULL(max(transectionid),0)+1)as transectionid from tbAccftransection";
						List<?> listdis = session.createSQLQuery(sql).list();

						for(Iterator<?> iter = listdis.iterator(); iter.hasNext();)
						{	
							TransId = iter.next().toString();
							break;
						}

						accsql="insert into tbAccftransection (transectionid,voucherNo,type,d_l_id,c_l_id,amount,description,groupId,standby,approve,costCenterId,date,entryTime,UserId) values ('"+TransId+"','"+v.getLabId()+"','14','"+d_l_id+"','"+c_l_id+"','"+v.getPaid()+"','Outdoor Cash Collecetion','0','0','0','0',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'"+v.getUserId()+"') ";
						session.createSQLQuery(accsql).executeUpdate();
					}
				}


				tx.commit();
				v.setLabId(labId);

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
		}



		return false;
	}

	public String getCurrentMonth(){

		String Month="";
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		try{
			tx=session.getTransaction();
			tx.begin();


			String sql="SELECT  cast(DATENAME(M,'"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"') as varchar) as MName";
			System.out.println(sql);

			List<?> list = session.createSQLQuery(sql).list();

			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				Month =iter.next().toString();
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

		return Month;

	}

	private int getCurrentPeriod(String RegNo,String FiscalYear) {
		int period=0;
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		try{
			tx=session.getTransaction();
			tx.begin();


			String sql="select  period from tbpatientinfo where RegNo='"+RegNo+"' and FiscalYear='"+FiscalYear+"' order by period desc";
			System.out.println(sql);

			List<?> list = session.createSQLQuery(sql).list();

			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				period = Integer.parseInt(iter.next().toString());
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

		return period;
	}


	@Override
	public List<LabBilling> getlabpatientlist(String value) {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		List<LabBilling> query=new ArrayList<LabBilling>();
		try{
			tx=session.getTransaction();
			tx.begin();


			String sql="select labId,PatientName,Mobile,FiscalYear from TbLabPatient where (labId like '%"+value+"%' ) or (PatientName like '%"+value+"%') or (Mobile like '%"+value+"%')";
			System.out.println(sql);

			List<?> list = session.createSQLQuery(sql).list();

			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				Object[] element = (Object[]) iter.next();
				query.add(new LabBilling(element[0].toString(),element[1].toString(),element[2].toString(),element[3].toString()));
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
	public List<LabBilling> LabIdWiseTestAndPatientInfo(String labid,String fiscalyear,String cmonth) {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		List<LabBilling> query=new ArrayList<LabBilling>();
		try{
			tx=session.getTransaction();
			tx.begin();


			String sql="select (select ISNULL(sum(Cash),0) as Cash from TbLabPaymentHistory where LabId=b.LabId and FiscalYear=b.FiscalYear and cmonth='"+cmonth+"' and PaymentStatus='Paid') as Paid,(select ISNULL(sum(Cash),0) as Cash from TbLabPaymentHistory where LabId=b.LabId and FiscalYear=b.FiscalYear and cmonth='"+cmonth+"' and PaymentStatus='Refund') as Refund,b.FiscalYear,b.CMonth,ISNULL(b.headId,''),ISNULL((select GroupName from TbLabTestGroup where headId=b.headId),'') as HeadName,b.autoId,b.type,b.testId,b.testName,b.Qty,b.Rate,b.Discount,(b.Rate*b.Discount/100) as DiscountAmount,b.Rate-(b.Rate*b.Discount/100) as PayableAmount,b.ResultStatus,a.BillType,a.RegNo,a.PatientName,a.Mobile,a.Age,a.Month,a.Day,a.Sex,a.Cabin,a.address,ISNULL((select Name from TbDoctorInfo where DoctorId=a.RfPersionId ),'') as DoctorName1,ISNULL((select Degree from TbDoctorInfo where DoctorId=a.RfPersionId ),'') as Degree1,a.RfPersionId,ISNULL((select Name from TbDoctorInfo where DoctorId=a.RefferBy ),'') as DoctorName2,ISNULL((select Degree from TbDoctorInfo where DoctorId=a.RefferBy ),'') as Degree2,a.RefferBy,ISNULL((select Name from TbDoctorInfo where DoctorId=a.extraCommission ),'') as extraCommission,a.remark,a.TotalCharge,a.TotalPayable,a.PercentDiscount,a.ManualDiscount,a.Discount as DiscountInTaka,(select convert(varchar,a.DateTime, 103)) as BillDate,a.labId from TbLabPatient a join TbLabTestHistory b on  a.FiscalYear=b.FiscalYear and a.CMonth=b.Cmonth and a.labId=b.labId where  b.fiscalyear='"+fiscalyear+"' and b.labId='"+labid+"' and b.cmonth='"+cmonth+"' and b.refundstatus='0' order by b.type";
			System.out.println(sql);
			List<?> list = session.createSQLQuery(sql).list();

			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				Object[] element = (Object[]) iter.next();
				query.add(new LabBilling(element[0].toString(),element[1].toString(),element[2].toString(),element[3].toString(),element[4].toString(),element[5].toString(),element[6].toString(),element[7].toString(),element[8].toString(),element[9].toString(),element[10].toString(),element[11].toString(),element[12].toString(),element[13].toString(),element[14].toString(),element[15].toString(),element[16].toString(),element[17].toString(),element[18].toString(),element[19].toString(),element[20].toString(),element[21].toString(),element[22].toString(),element[23].toString(),element[24].toString(),element[25].toString(),element[26].toString(),element[27].toString(),element[28].toString(),element[29].toString(),element[30].toString(),element[31].toString(),element[32].toString(),element[33].toString(),element[34].toString(),element[35].toString(),element[36].toString(),element[37].toString(),element[38].toString(),element[39].toString(),element[40].toString()));
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
	public boolean EditPostedBill(LabBilling v) {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		try{
			tx=session.getTransaction();
			tx.begin();
			
			System.out.println("manu "+v.getManualdiscount());
			System.out.println("perce "+v.getPercentdiscount());
			System.out.println("percetk "+v.getPerdiscount_taka());
			System.out.println("paya "+v.getTotalpayable());

			double manualdiscount=Double.parseDouble(v.getManualdiscount());
			double percentdiscount=Double.parseDouble(v.getPercentdiscount());
			double perdiscount_taka=Double.parseDouble(v.getPerdiscount_taka());

			double totalDiscount=perdiscount_taka+manualdiscount;


			double totalpayable=Double.parseDouble(v.getTotalpayable());

			double cmddiscount=0;

			String discountAllow="";

			String Period="0";
			String PatientFiscalYear="";

			String referralId="0",cReferralId="0",extraCommission="0";
			String sql="select DoctorId from TbDoctorInfo where Name='"+v.getReferral_search()+"'";
			List<?> list1 = session.createSQLQuery(sql).list();

			for(Iterator<?> iter = list1.iterator(); iter.hasNext();)
			{	
				referralId=iter.next().toString();	
				break;
			}

			sql="select DoctorId from TbDoctorInfo where Name='"+v.getReferralcomission()+"'";
			List<?> list2 = session.createSQLQuery(sql).list();

			for(Iterator<?> iter = list2.iterator(); iter.hasNext();)
			{	
				cReferralId=iter.next().toString();	
				break;
			}

			sql="select DoctorId from TbDoctorInfo where Name='"+v.getExtraCommission()+"'";
			List<?> list3 = session.createSQLQuery(sql).list();

			for(Iterator<?> iter = list3.iterator(); iter.hasNext();)
			{	
				extraCommission=iter.next().toString();	
				break;
			}

			sql="update TbLabPatient set "
					+ "RegNo='"+v.getRegno()+"',"
					+ "PatientName='"+v.getPatientname()+"',"
					+ "Mobile='"+v.getMobile()+"',"
					+ "Age='"+v.getAge()+"',"
					+ "Month='"+v.getMonth()+"',"
					+ "day='"+v.getDay()+"',"
					+ "Sex='"+v.getSex()+"',"
					+ "Cabin='"+v.getBedcabin()+"',"
					+ "address='"+v.getAddress()+"',"
					+ "RfPersionId='"+cReferralId+"',"
					+ "RefferBy='"+referralId+"',"
					+ "extraCommission='"+extraCommission+"',"
					+ "ManualDiscount='"+manualdiscount+"',"
					+ "PercentDiscount='"+percentdiscount+"',"
					+ "Discount='"+perdiscount_taka+"',"
					+ "totalDiscount='"+totalDiscount+"',"
					+ "TotalPayable='"+totalpayable+"',"
					+ "remark='"+v.getRemark()+"',"
					+ "ReportDelivery='"+v.getDeliverydatetime()+"',"
					+ "entryTime=CURRENT_TIMESTAMP,"
					+ "FiscalYear='"+v.getFiscalyear()+"',"
					+ "ModifyBy='"+v.getUserId()+"',"
					+ "Period='"+Period+"',"
					+ "PatientFiscalYear='"+PatientFiscalYear+"' where  FiscalYear='"+v.getFiscalyear()+"' and labId='"+v.getLabId()+"' and Cmonth='"+v.getcMonth()+"'";

			session.createSQLQuery(sql).executeUpdate();


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
	public boolean CounterInfoDelete(LabBilling v) {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		try{
			tx=session.getTransaction();
			tx.begin();


			String sql="delete from TbLabCounterWisePatientInfomation where labId IS NULL and CreateBy='"+v.getUserId()+"' and Counter='"+v.getCounter()+"'";
			session.createSQLQuery(sql).executeUpdate();

			String sqlhistory="delete from TbLabTestHistory where labId IS NULL and CreateBy='"+v.getUserId()+"' and Counter='"+v.getCounter()+"'";
			session.createSQLQuery(sqlhistory).executeUpdate();


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
	public boolean DuePayment(LabBilling v) {

		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		try{
			tx=session.getTransaction();
			tx.begin();

			System.out.println("cMonth"+v.getcMonth());

			double dues=Double.parseDouble(v.getDues());

			if(dues<=0) {
				String sql="update TbLabPatient set PaidDate=CURRENT_TIMESTAMP where FiscalYear='"+v.getFiscalyear()+"' and LabId='"+v.getLabId()+"' and CMonth='"+v.getcMonth()+"'";
				System.out.println(sql);
				session.createSQLQuery(sql).executeUpdate();
			}


			String sql="update TbLabPatient set Paid=Paid+'"+v.getPaid()+"' where FiscalYear='"+v.getFiscalyear()+"' and LabId='"+v.getLabId()+"' and CMonth='"+v.getcMonth()+"'";
			System.out.println(sql);
			session.createSQLQuery(sql).executeUpdate();


			String paysql="insert into TbLabPaymentHistory (LabId,Cash,Card,BillType,PaymentStatus,date,EntryTime,createBy,FiscalYear,CMonth,PaymentType) values ('"+v.getLabId()+"','"+v.getPaid()+"','0','"+v.getBillType()+"','Paid',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'"+v.getUserId()+"','"+v.getFiscalyear()+"','"+v.getcMonth()+"','Due Collection') ";
			session.createSQLQuery(paysql).executeUpdate();

			double TotalPaidCash=0;
			String PaidLastDate="";
			sql="select cash,convert(varchar,date, 120) as LastDate from TbLabPaymentHistory where LabId='"+v.getLabId()+"' and FiscalYear='"+v.getFiscalyear()+"' and CMonth='"+v.getcMonth()+"' order by autoId asc";
			List<?> list3 = session.createSQLQuery(sql).list();

			System.out.println("list3.size "+list3.size());
			for(Iterator<?> iter = list3.iterator(); iter.hasNext();)
			{	Object[] element = (Object[]) iter.next();

			TotalPaidCash=TotalPaidCash+Double.parseDouble(element[0].toString());
			System.out.println("TotalPaidCash "+TotalPaidCash);
			PaidLastDate=element[1].toString();

			}

			System.out.println("PaidLastDate "+PaidLastDate);

			if(TotalPaidCash==Double.parseDouble(v.getTotalpayable()) && !PaidLastDate.equals("")) {
				String paidSql="update TbLabPatient set PaidDate='"+PaidLastDate+"' where LabId='"+v.getLabId()+"' and FiscalYear='"+v.getFiscalyear()+"' and CMonth='"+v.getcMonth()+"'";
				session.createSQLQuery(paidSql).executeUpdate();
			}


			//Cash................................
			if(Double.parseDouble(v.getPaid())>0) {
				String d_l_id="2-6-14-7";
				String c_l_id="2-50-26";
				String TransId="";

				sql="select (ISNULL(max(transectionid),0)+1)as transectionid from tbAccftransection";
				List<?> listdis = session.createSQLQuery(sql).list();

				for(Iterator<?> iter = listdis.iterator(); iter.hasNext();)
				{	
					TransId = iter.next().toString();
				}

				String accsql="insert into tbAccftransection (transectionid,voucherNo,type,d_l_id,c_l_id,amount,description,groupId,standby,approve,costCenterId,date,entryTime,UserId) values ('"+TransId+"','"+v.getLabId()+"','14','"+d_l_id+"','"+c_l_id+"','"+v.getPaid()+"','Outdoor Cash Collecetion','0','0','0','0',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'"+v.getUserId()+"') ";
				session.createSQLQuery(accsql).executeUpdate();
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
	public List<LabBilling> LabSaleStatementReport(String startdate, String enddate, String billtype) {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		List<LabBilling> query=new ArrayList<LabBilling>();
		try{
			tx=session.getTransaction();
			tx.begin();


			Date sdate=new SimpleDateFormat("MM/dd/yyyy").parse(startdate);
			String sDate=new SimpleDateFormat("yyyy-MM-dd").format(sdate);

			Date edate=new SimpleDateFormat("MM/dd/yyyy").parse(enddate);
			String eDate=new SimpleDateFormat("yyyy-MM-dd").format(edate);

			System.out.println("sDate "+sDate);
			System.out.println("eDate "+eDate);

			String sql="";
			sql= "select labId,BillType,PatientName,(select convert(varchar,DateTime, 103)) as DateTime,TotalCharge,totalDiscount,TotalPayable,(select ISNULL(sum(cash),0) from TbLabPaymentHistory where LabId=TbLabPatient.labId and FiscalYear=TbLabPatient.FiscalYear and PaymentStatus='Paid' and date between '"+sDate+"' and '"+eDate+"') as ActualPaid,(select ISNULL(sum(cash),0) from TbLabPaymentHistory where LabId=TbLabPatient.labId and FiscalYear=TbLabPatient.FiscalYear and PaymentStatus='Refund' and date between '"+sDate+"' and '"+eDate+"') as Refund,(select username from tblogin where id=TbLabPatient.CreateBy) as username,(select convert(varchar,'"+sDate+"', 103)) as StartDate,(select convert(varchar,'"+eDate+"', 103)) as EndDate from TbLabPatient where  BillType='"+billtype+"' and DateTime  between '"+sDate+"' and '"+eDate+"' order by CreateBy,labId asc";

			System.out.println(sql);

			List<?> list = session.createSQLQuery(sql).list();

			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				Object[] element = (Object[]) iter.next();
				query.add(new LabBilling(element[0].toString(),element[1].toString(),element[2].toString(),element[3].toString(),element[4].toString(),element[5].toString(),element[6].toString(),element[7].toString(),element[8].toString(),element[9].toString(),element[10].toString(),element[11].toString()));
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
	public List<LabBilling> LabSalesCashStatementReport(String startdate, String enddate, String billtype) {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		List<LabBilling> query=new ArrayList<LabBilling>();
		try{
			tx=session.getTransaction();
			tx.begin();


			Date sdate=new SimpleDateFormat("MM/dd/yyyy").parse(startdate);
			String StartDate=new SimpleDateFormat("yyyy-MM-dd").format(sdate);

			String sDate=StartDate+" 23:59:00.000";			
			Date edate=new SimpleDateFormat("MM/dd/yyyy").parse(enddate);
			String eDate=new SimpleDateFormat("yyyy-MM-dd").format(edate)+" 23:59:00.000";

			System.out.println("sDate "+sDate);
			System.out.println("eDate "+eDate);

			String sql="";




			sql="select BillNo,PatientName,PatientType,PaymentStatus,BillStatus,AmountReceived,(select convert(varchar,DateOfBill, 103)) as DateOfBill,ReceivedTime,StartDate,EndDate,UserName from  TbLabSaleCashStatement('"+sDate+"','"+eDate+"','"+StartDate+"') where PatientType='"+billtype+"' order by UserName,DateOfBill,BillStatus";

			System.out.println(sql);


			List<?> list = session.createSQLQuery(sql).list();

			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				Object[] element = (Object[]) iter.next();
				query.add(new LabBilling(element[0].toString(),element[1].toString(),element[2].toString(),element[3].toString(),element[4].toString(),element[5].toString(),element[6].toString(),element[7].toString(),element[8].toString(),element[9].toString(),element[10].toString()));
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
	public List<LabBilling> LabSalesDueStatementReport(String startdate, String enddate, String billtype) {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		List<LabBilling> query=new ArrayList<LabBilling>();
		try{
			tx=session.getTransaction();
			tx.begin();


			Date sdate=new SimpleDateFormat("MM/dd/yyyy").parse(startdate);
			String sDate=new SimpleDateFormat("yyyy-MM-dd").format(sdate);

			Date edate=new SimpleDateFormat("MM/dd/yyyy").parse(enddate);
			String eDate=new SimpleDateFormat("yyyy-MM-dd").format(edate);



			String sql="";

			sql="select LabId,PatientName,isnull(RefferName,'') as RefferName,isnull(Degree,'') as Degree,TotalCharge,Discount,TotalPayable,ActualPaid,Refund,Due,(select convert(varchar,DateTime, 103)) as DateOfBill,Username,StartDate,EndDate from TbLabSaleDueStatement('"+sDate+"','"+eDate+"','"+billtype+"','All') order by DateTime,UserName";

			System.out.println(sql);


			List<?> list = session.createSQLQuery(sql).list();

			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				Object[] element = (Object[]) iter.next();
				query.add(new LabBilling(element[0].toString(),element[1].toString(),element[2].toString(),element[3].toString(),element[4].toString(),element[5].toString(),element[6].toString(),element[7].toString(),element[8].toString(),element[9].toString(),element[10].toString(),element[11].toString(),element[12].toString(),element[13].toString()));
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
	public List<LabBilling> DepartmentWiseLabSalesStatementReport(String startdate, String enddate, String billtype) {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		List<LabBilling> query=new ArrayList<LabBilling>();
		try{
			tx=session.getTransaction();
			tx.begin();


			Date sdate=new SimpleDateFormat("MM/dd/yyyy").parse(startdate);
			String sDate=new SimpleDateFormat("yyyy-MM-dd").format(sdate);

			Date edate=new SimpleDateFormat("MM/dd/yyyy").parse(enddate);
			String eDate=new SimpleDateFormat("yyyy-MM-dd").format(edate);



			String sql="";

			String type=billtype.equals("1")?"Indoor":"Outdoor";

			sql= "select '"+type+"' as DepTitle,(select Name from tblabtestgroup where HeadId=a.headId) as GroupName,avg(a.rate) as Rate,sum(a.qty) as Qty,sum(a.qty*a.rate) as Amount,sum(a.discount) as Discount,sum((a.qty*a.rate)-a.discount) as Total,'"+sDate+"' as StartDate, '"+eDate+"' as EndDate  from tblabtesthistory a  join TbLabPatient b  on b.labId=a.labId and b.FiscalYear=a.FiscalYear where b.DateTime between '"+sDate+"' and '"+eDate+"' and b.BillType='"+billtype+"' and a.RefundStatus=0 and a.labId IS NOT NULL group by a.headId order by a.headId desc";

			System.out.println(sql);


			List<?> list = session.createSQLQuery(sql).list();

			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				Object[] element = (Object[]) iter.next();
				query.add(new LabBilling(element[0].toString(),element[1].toString(),element[2].toString(),element[3].toString(),element[4].toString(),element[5].toString(),element[6].toString(),element[7].toString(),element[8].toString()));
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
	public List<LabBilling> TestWiseInvestigationStatement(String startdate, String enddate,String testname,String testall) {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		List<LabBilling> query=new ArrayList<LabBilling>();
		try{
			tx=session.getTransaction();
			tx.begin();


			Date sdate=new SimpleDateFormat("MM/dd/yyyy").parse(startdate);
			String sDate=new SimpleDateFormat("yyyy-MM-dd").format(sdate);

			Date edate=new SimpleDateFormat("MM/dd/yyyy").parse(enddate);
			String eDate=new SimpleDateFormat("yyyy-MM-dd").format(edate);



			String sql="";


			if(testall.equals("Yes") && testname.equals("")){
				sql="select *,'All Test Investigation Statement' as ReportTitle,(select PatientName from TbLabPatient where labId=tblabtesthistory.labId and FiscalYear=tblabtesthistory.FiscalYear) as PatientName,(select SampleCollect from TbLabPatient where labId=tblabtesthistory.labId and FiscalYear=tblabtesthistory.FiscalYear) as SampleCollect,'"+sDate+"' as StartDate,'"+eDate+"' as enddate,1 as Qty,(select (select GroupName from tblabtestgroup where SN=tbTestName.TestHeadId)  from tbTestName where TestName=tblabtesthistory.testName) as GroupHead  from tblabtesthistory where type=1 and  RefundStatus='0' and date between '"+sDate+"' and '"+eDate+"' and labId IS NOT NULL order by GroupHead,testName";
			}
			else if(testall.equals("No") && !testname.equals("")){
				sql="select *,'Test Wise Investigation Statement' as ReportTitle,(select PatientName from TbLabPatient where labId=tblabtesthistory.labId and FiscalYear=tblabtesthistory.FiscalYear) as PatientName,(select SampleCollect from TbLabPatient where labId=tblabtesthistory.labId and FiscalYear=tblabtesthistory.FiscalYear) as SampleCollect,'"+sDate+"' as StartDate,'"+eDate+"' as enddate,1 as Qty,(select (select GroupName from tblabtestgroup where SN=tbTestName.TestHeadId)  from tbTestName where TestName=tblabtesthistory.testName) as GroupHead  from tblabtesthistory where  date between '"+sDate+"' and '"+eDate+"' and testName='"+testname+"' and RefundStatus='0' and labId IS NOT NULL order by GroupHead,testName";

			}
			System.out.println(sql);


			List<?> list = session.createSQLQuery(sql).list();

			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				Object[] element = (Object[]) iter.next();
				query.add(new LabBilling(element[0].toString(),element[1].toString(),element[2].toString(),element[3].toString(),element[4].toString(),element[5].toString(),element[6].toString(),element[7].toString(),element[8].toString()));
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
	public List<LabBilling> LabIdWiseReferraComissionStatement(String startdate, String enddate,String RefferalId) {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		List<LabBilling> query=new ArrayList<LabBilling>();
		try{
			tx=session.getTransaction();
			tx.begin();


			Date sdate=new SimpleDateFormat("MM/dd/yyyy").parse(startdate);
			String sDate=new SimpleDateFormat("yyyy-MM-dd").format(sdate);

			Date edate=new SimpleDateFormat("MM/dd/yyyy").parse(enddate);
			String eDate=new SimpleDateFormat("yyyy-MM-dd").format(edate);



			String sql="";

			sql="select RefferName,RefferDegree,LabBill,PatientName,PathologyRate,PathologyNetAmount,HormoneRate,HormoneNetAmount,EchoCardRate,EchoCardNetAmount,UltraSonoRate,UltraSonoNetAmount,EnDosRate,EnDosNetAmount,XrayRate,XrayNetAmount,ECGRate,ECGNetAmount,HistopathologyRate,HistopathologyNetAmount,BloodGroupRate,BloodGroupNetAmount,FNARate,FNANetAmount,PepsSemarRate,PepsSemarNetAmount,OthersRate,OthersNetAmount,ManualDiscount,DoctorDiscount,TotalCharge,PerticularCharge,TotalPaid,Due,Date,StartDate,EndDate from TbRefferWiseComissionStatement('"+sDate+"','"+eDate+"','"+RefferalId+"')";


			System.out.println(sql);


			List<?> list = session.createSQLQuery(sql).list();

			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				Object[] element = (Object[]) iter.next();
				query.add(new LabBilling(element[0].toString(),element[1].toString(),element[2].toString(),element[3].toString(),element[4].toString(),element[5].toString(),element[6].toString(),element[7].toString(),element[8].toString(),element[9].toString(),element[10].toString(),element[11].toString(),element[12].toString(),element[13].toString(),element[14].toString(),element[15].toString(),element[16].toString(),element[17].toString(),element[18].toString(),element[19].toString(),element[20].toString(),element[21].toString(),element[22].toString(),element[23].toString(),element[24].toString(),element[25].toString(),element[26].toString(),element[27].toString(),element[28].toString(),element[29].toString(),element[30].toString(),element[31].toString(),element[32].toString(),element[33].toString(),element[34].toString(),element[35].toString(),element[36].toString()));
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
	public boolean HeamatologySaveEvent(LabResult v) {

		int submit=0;
		//ESR
		if(!checkLabReport(1,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {
			if(!v.getEsr().toString().isEmpty() && insertdata(1,v.getEsr(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(1,v.getEsr(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		//LYM Count
		if(!checkLabReport(3,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {
			if(!v.getLymphocytes().toString().isEmpty() && insertdata(3,v.getLymphocytes(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v) ) {

				submit++;
			}

		}
		else {
			updatedata(3,v.getLymphocytes(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}



		//MON Count
		if(!checkLabReport(4,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {
			if(!v.getMonocytes().toString().isEmpty() && insertdata(4,v.getMonocytes(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v) ) {	
				submit++;
			}	
		}
		else {
			updatedata(4,v.getMonocytes(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		//MON Count
		if(!checkLabReport(5,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {

			if(!v.getEosinophils().toString().isEmpty() && insertdata(5,v.getEosinophils(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v) ) {	
				submit++;
			}
		}
		else {
			updatedata(5,v.getEosinophils(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		//Bas Count
		if(!checkLabReport(6,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {

			if(!v.getBasophils().toString().isEmpty() && insertdata(6,v.getBasophils(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v) ) {	
				submit++;
			}
		}
		else {
			updatedata(6,v.getBasophils(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}


		//Neu Count
		if(!checkLabReport(24,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {

			if(!v.getNeutrophlis().toString().isEmpty() && insertdata(24,v.getNeutrophlis(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v) ) {	
				submit++;
			}
		}
		else {
			updatedata(24,v.getNeutrophlis(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		//Other
		if(!checkLabReport(26,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {
			if(!v.getOther().toString().isEmpty() && insertdata(26,v.getOther(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v) ) {	
				submit++;
			}
		}
		else {
			updatedata(26,v.getOther(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		//MP
		if(!checkLabReport(25,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {
			if(!v.getMp().toString().isEmpty() && insertdata(25,v.getMp(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v) ) {	
				submit++;
			}
		}
		else {
			updatedata(25,v.getMp(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		//MPC
		if(!checkLabReport(18,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {

			if(!v.getMpc().toString().isEmpty() && insertdata(18,v.getMpc(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v) ) {	
				submit++;
			}

		}
		else {
			updatedata(18,v.getMpc(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		//RBC
		if(!checkLabReport(7,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {

			if(!v.getPlt().toString().isEmpty() && insertdata(7,v.getPlt(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v) ) {	
				submit++;
			}

		}
		else {
			updatedata(7,v.getPlt(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		//HGB
		if(!checkLabReport(8,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {

			if(!v.getHgb().toString().isEmpty() && insertdata(8,v.getHgb(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v) ) {	
				submit++;
			}

		}
		else {
			updatedata(8,v.getHgb(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		//PCV
		if(!checkLabReport(9,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {

			if(!v.getPcv().toString().isEmpty() && insertdata(9,v.getPcv(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v) ) {	
				submit++;
			}

		}
		else {
			updatedata(9,v.getPcv(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		//MCV
		if(!checkLabReport(10,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {
			if(!v.getMcv().toString().isEmpty() && insertdata(10,v.getMcv(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v) ) {	
				submit++;
			}

		}
		else if(updatedata(10,v.getMcv(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)){
			submit++;
		}

		//MCH
		if(!checkLabReport(11,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {

			if(!v.getMch().toString().isEmpty() && insertdata(11,v.getMch(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v) ) {	
				submit++;
			}

		}
		else {
			updatedata(11,v.getMch(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}



		//MCHC
		if(!checkLabReport(12,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {

			if(!v.getMchc().toString().isEmpty() && insertdata(12,v.getMchc(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v) ) {	
				submit++;
			}

		}
		else {
			updatedata(12,v.getMchc(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}


		//PCT
		if(!checkLabReport(14,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {
			if(!v.getPct().toString().isEmpty() && insertdata(14,v.getPct(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v) ) {	
				submit++;
			}
		}
		else {
			updatedata(14,v.getPct(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}


		//MPV
		if(!checkLabReport(15,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {
			if(!v.getMpv().toString().isEmpty() && insertdata(15,v.getMpv(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v) ) {	
				submit++;
			}
		}
		else {
			updatedata(15,v.getMpv(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		//pDW
		if(!checkLabReport(16,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {

			if(!v.getPdw().toString().isEmpty() && insertdata(16,v.getPdw(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v) ) {	
				submit++;
			}

		}
		else {
			updatedata(16,v.getPdw(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}


		//CT
		if(!checkLabReport(20,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {
			if(!v.getCtmin().toString().isEmpty() && insertdata(20,v.getCtmin(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v) ) {	
				submit++;
			}
		}
		else {
			updatedata(20,v.getCtmin(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}



		//BT
		if(!checkLabReport(19,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {
			if(!v.getMin().toString().isEmpty() && insertdata(19,v.getMin(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v) ) {	
				submit++;
			}
		}
		else {
			updatedata(19,v.getMin(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}


		/*		
		//EC
		if(!checkLabReport(22,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getFiscalyear())) {
			if(!v.getEc().toString().isEmpty() && insertdata(22,v.getEc(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v) ) {	
				submit++;
			}

		}
		else {
			updatedata(22,v.getEc(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}*/

		//RBC
		if(!checkLabReport(23,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {

			if(!v.getRbc().toString().isEmpty() && insertdata(23,v.getRbc(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v) ) {	
				submit++;
			}

		}
		else {
			updatedata(23,v.getRbc(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}


		if(!checkLabReport(30,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {

			if(!v.getTotalCount().toString().isEmpty() && insertdata(30,v.getTotalCount(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v) ) {	
				submit++;
			}

		}
		else {
			updatedata(30,v.getTotalCount(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		if(!checkLabReport(260,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {

			if(!v.getCec().toString().isEmpty() && insertdata(260,v.getCec(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v) ) {	
				submit++;
			}

		}
		else {
			updatedata(260,v.getCec(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}


		if(submit!=0) {
			Session session=HibernateUtil.openSession();
			Transaction tx=null;
			try{
				tx=session.getTransaction();
				tx.begin();

				String sql="update TbLabTestHistory set ResultStatus='1' where LabId='"+v.getLabbill()+"' and  testId='"+v.getTestid()+"'";

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
		}

		return false;

	}

	private boolean insertdata(int rId,String value,String TestId,String HeadId,String LabId,String UserId,String FiscalYear,int sort,LabResult v){
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		try{
			tx=session.getTransaction();
			tx.begin();

			String sql="insert into TbLabReportValue ("
					+ "rId,"
					+ "value,"
					+ "testId,"
					+ "HeadId,"
					+ "LabId,"
					+ "CalculateValue,"
					+ "Flag,"
					+ "CheckedById,"
					+ "LabInchargeId,"
					+ "Doctor1Id,"
					+ "Doctor2Id,"
					+ "machineId,"
					+ "titleId,"
					+ "Sorting,"
					+ "date,"
					+ "entryTime,"
					+ "createBy,"
					+ "FiscalYear,Cmonth) values "
					+ "('"+rId+"',"
					+ "'"+value+"',"
					+ "'"+TestId+"',"
					+ "'"+HeadId+"',"
					+ "'"+LabId+"',"
					+ "'',"
					+ "'',"
					+ "'',"
					+ "'"+v.getInchargeId()+"',"
					+ "'"+v.getDoctor1()+"',"
					+ "'"+v.getDoctor2()+"',"
					+ "'"+v.getMachineId()+"',"
					+ "'"+v.getTitleId()+"',"
					+ "'"+sort+"',"
					+ "CURRENT_TIMESTAMP,"
					+ "CURRENT_TIMESTAMP,"
					+ "'"+UserId+"',"
					+ "'"+v.getLabfiscalyear()+"','"+v.getCmonth()+"')";

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

	private boolean updatedata(int rId,String value,String TestId,String HeadId,String LabId,String UserId,String FiscalYear,int sort,LabResult v){
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		try{
			tx=session.getTransaction();
			tx.begin();

			String sql="update TbLabReportValue set value='"+value+"',testId='"+TestId+"',HeadId='"+HeadId+"',LabId='"+LabId+"',date=CURRENT_TIMESTAMP,entryTime=CURRENT_TIMESTAMP,createBy='"+UserId+"',FiscalYear='"+v.getLabfiscalyear()+"',Cmonth='"+v.getCmonth()+"',Sorting='"+sort+"',LabInchargeId='"+v.getInchargeId()+"',Doctor1Id='"+v.getDoctor1()+"',Doctor2Id='"+v.getDoctor2()+"',machineId='"+v.getMachineId()+"',titleId='"+v.getTitleId()+"' where fiscalyear='"+v.getLabfiscalyear()+"' and cmonth='"+v.getCmonth()+"' and HeadId='"+HeadId+"' and LabId='"+LabId+"' and TestId='"+TestId+"' and rId='"+rId+"'";

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
	private boolean checkLabReport(int rId,String labId,String TestId,String HeadId,String FiscalYear,String Cmonth) {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		String sql="";
		try {
			tx=session.getTransaction();
			tx.begin();
			sql="select rId from tblabreportvalue  where FiscalYear='"+FiscalYear+"' and Cmonth='"+Cmonth+"' and rId='"+rId+"' and LabId='"+labId+"' and TestId='"+TestId+"' and HeadId='"+HeadId+"'";
			System.out.println(sql);

			List<?> list = session.createSQLQuery(sql).list();

			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
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
	public boolean UrineSaveEvent(LabResult v) {

		int submit=0;

		//Quantity
		if(!checkLabReport(38,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {
			if(!v.getQuantity().toString().isEmpty() && insertdata(38,v.getQuantity(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(38,v.getQuantity(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		//Color
		if(!checkLabReport(39,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {
			if(!v.getColor().toString().isEmpty() && insertdata(39,v.getColor(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(39,v.getColor(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		//Appearance
		if(!checkLabReport(40,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {
			if(!v.getAppearance().toString().isEmpty() && insertdata(40,v.getAppearance(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(40,v.getAppearance(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}


		//Sediment
		if(!checkLabReport(41,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {
			if(!v.getSediment().toString().isEmpty() && insertdata(41,v.getSediment(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(41,v.getSediment(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		//Gravity
		if(!checkLabReport(42,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {
			if(!v.getGravity().toString().isEmpty() && insertdata(42,v.getGravity(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(42,v.getGravity(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		//PH
		if(!checkLabReport(43,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {
			if(!v.getPh().toString().isEmpty() && insertdata(43,v.getPh(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(43,v.getPh(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		//Sugar
		if(!checkLabReport(65,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {
			if(!v.getSugar().toString().isEmpty() && insertdata(65,v.getSugar(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(65,v.getSugar(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		//Albumin
		if(!checkLabReport(44,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {
			if(!v.getAlbumin().toString().isEmpty() && insertdata(44,v.getAlbumin(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(44,v.getAlbumin(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		//Ketonebodies
		if(!checkLabReport(47,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {
			if(!v.getKetonebodies().toString().isEmpty() && insertdata(47,v.getKetonebodies(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(47,v.getKetonebodies(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}


		//Blood
		if(!checkLabReport(52,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {
			if(!v.getBlood().toString().isEmpty() && insertdata(52,v.getBlood(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(52,v.getBlood(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		//Bilirubin
		if(!checkLabReport(49,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {
			if(!v.getBilirubin().toString().isEmpty() && insertdata(49,v.getBilirubin(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(49,v.getBilirubin(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		//Uribiliogen
		if(!checkLabReport(48,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {
			if(!v.getUribiliogen().toString().isEmpty() && insertdata(48,v.getUribiliogen(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(48,v.getUribiliogen(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		//Nitrites
		if(!checkLabReport(50,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {
			if(!v.getNitrites().toString().isEmpty() && insertdata(50,v.getNitrites(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(50,v.getNitrites(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		//Bilepigment
		if(!checkLabReport(46,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {
			if(!v.getBilepigment().toString().isEmpty() && insertdata(46,v.getBilepigment(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(46,v.getBilepigment(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		//Bilesalth
		if(!checkLabReport(45,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {
			if(!v.getBilesalth().toString().isEmpty() && insertdata(45,v.getBilesalth(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(45,v.getBilesalth(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		//Phosphate
		if(!checkLabReport(66,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {
			if(!v.getPhosphate().toString().isEmpty() && insertdata(66,v.getPhosphate(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(66,v.getPhosphate(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		//Hyalinecash
		if(!checkLabReport(73,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {
			if(!v.getHyalinecash().toString().isEmpty() && insertdata(73,v.getHyalinecash(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(73,v.getHyalinecash(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		//Fatty
		if(!checkLabReport(75,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {
			if(!v.getFatty().toString().isEmpty() && insertdata(75,v.getFatty(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(75,v.getFatty(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		//Wbccasts
		if(!checkLabReport(76,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {
			if(!v.getWbccasts().toString().isEmpty() && insertdata(76,v.getWbccasts(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(76,v.getWbccasts(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		//Rbccasts
		if(!checkLabReport(77,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {
			if(!v.getRbccasts().toString().isEmpty() && insertdata(77,v.getRbccasts(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(77,v.getRbccasts(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		//Rbc
		if(!checkLabReport(56,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {
			if(!v.getRbc().toString().isEmpty() && insertdata(56,v.getRbc(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(56,v.getRbc(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		//Puscells
		if(!checkLabReport(54,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {
			if(!v.getPuscells().toString().isEmpty() && insertdata(54,v.getPuscells(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(54,v.getPuscells(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		//Epithelialcells
		if(!checkLabReport(55,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {
			if(!v.getEpithelialcells().toString().isEmpty() && insertdata(55,v.getEpithelialcells(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(55,v.getEpithelialcells(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}


		if(!checkLabReport(74,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {
			if(!v.getGranularcells().toString().isEmpty() && insertdata(74,v.getGranularcells(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(74,v.getGranularcells(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		//Mucus
		if(!checkLabReport(67,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {
			if(!v.getTrichomonasVaginalis().toString().isEmpty() && insertdata(67,v.getTrichomonasVaginalis(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(67,v.getTrichomonasVaginalis(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		if(!checkLabReport(63,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {
			if(!v.getFungus().toString().isEmpty() && insertdata(63,v.getFungus(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(63,v.getFungus(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		//Supermatozoa
		if(!checkLabReport(68,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {
			if(!v.getSupermatozoa().toString().isEmpty() && insertdata(68,v.getSupermatozoa(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(68,v.getSupermatozoa(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}


		//Microorganism
		if(!checkLabReport(70,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {
			if(!v.getMicroorganism().toString().isEmpty() && insertdata(70,v.getMicroorganism(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(70,v.getMicroorganism(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		//Calciumoxalate
		if(!checkLabReport(57,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {
			if(!v.getCalciumoxalate().toString().isEmpty() && insertdata(57,v.getCalciumoxalate(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(57,v.getCalciumoxalate(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		//Calciumoxalate
		if(!checkLabReport(61,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {
			if(!v.getUricacidcrystals().toString().isEmpty() && insertdata(61,v.getUricacidcrystals(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(61,v.getUricacidcrystals(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		//Urate
		if(!checkLabReport(72,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {
			if(!v.getUrate().toString().isEmpty() && insertdata(72,v.getUrate(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(72,v.getUrate(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		//Triplephosphate
		if(!checkLabReport(59,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {
			if(!v.getTriplephosphate().toString().isEmpty() && insertdata(59,v.getTriplephosphate(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(59,v.getTriplephosphate(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		//Amorphphosphate
		if(!checkLabReport(58,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {
			if(!v.getAmorphphosphate().toString().isEmpty() && insertdata(58,v.getAmorphphosphate(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(58,v.getAmorphphosphate(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		if(submit!=0) {
			Session session=HibernateUtil.openSession();
			Transaction tx=null;
			try{
				tx=session.getTransaction();
				tx.begin();

				String sql="update TbLabTestHistory set ResultStatus='1' where LabId='"+v.getLabbill()+"' and  testId='"+v.getTestid()+"'";

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
		}
		return false;

	}

	@Override
	public boolean StoolSaveEvent(LabResult v) {
		int submit=0;

		//Quantity
		if(!checkLabReport(85,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {
			if(!v.getConsistency().toString().isEmpty() && insertdata(85,v.getConsistency(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(85,v.getConsistency(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		if(!checkLabReport(86,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {
			if(!v.getStoolColor().toString().isEmpty() && insertdata(86,v.getStoolColor(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(86,v.getStoolColor(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		if(!checkLabReport(87,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {
			if(!v.getOdour().toString().isEmpty() && insertdata(87,v.getOdour(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(87,v.getOdour(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		if(!checkLabReport(88,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {
			if(!v.getStoolMucus().toString().isEmpty() && insertdata(88,v.getStoolMucus(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(88,v.getStoolMucus(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		if(!checkLabReport(89,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {
			if(!v.getStoolBlood().toString().isEmpty() && insertdata(89,v.getStoolBlood(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(89,v.getStoolBlood(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		if(!checkLabReport(90,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {
			if(!v.getHelminths().toString().isEmpty() && insertdata(90,v.getHelminths(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(90,v.getHelminths(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		if(!checkLabReport(91,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {
			if(!v.getReaction().toString().isEmpty() && insertdata(91,v.getReaction(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(91,v.getReaction(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		if(!checkLabReport(92,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {
			if(!v.getReducingSubstance().toString().isEmpty() && insertdata(92,v.getReducingSubstance(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(92,v.getReducingSubstance(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		if(!checkLabReport(93,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {
			if(!v.getOccultBloodTest().toString().isEmpty() && insertdata(93,v.getOccultBloodTest(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(93,v.getOccultBloodTest(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		if(!checkLabReport(94,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {
			if(!v.getProtozoa().toString().isEmpty() && insertdata(94,v.getProtozoa(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(94,v.getProtozoa(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		if(!checkLabReport(95,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {
			if(!v.getCystseh().toString().isEmpty() && insertdata(95,v.getCystseh(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(95,v.getCystseh(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		if(!checkLabReport(96,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {
			if(!v.getCystsColt().toString().isEmpty() && insertdata(96,v.getCystsColt(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(96,v.getCystsColt(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		if(!checkLabReport(97,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {
			if(!v.getCystsGiardia().toString().isEmpty() && insertdata(97,v.getCystsGiardia(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(97,v.getCystsGiardia(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		if(!checkLabReport(98,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {
			if(!v.getOvaoFroundWorm().toString().isEmpty() && insertdata(98,v.getOvaoFroundWorm(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(98,v.getOvaoFroundWorm(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		if(!checkLabReport(99,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {
			if(!v.getOvaOfHookWorm().toString().isEmpty() && insertdata(99,v.getOvaOfHookWorm(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(99,v.getOvaOfHookWorm(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		if(!checkLabReport(100,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {
			if(!v.getOvaOfWhipWorm().toString().isEmpty() && insertdata(100,v.getOvaOfWhipWorm(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(100,v.getOvaOfWhipWorm(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		if(!checkLabReport(112,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {
			if(!v.getLarvaOfSs().toString().isEmpty() && insertdata(112,v.getLarvaOfSs(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(112,v.getLarvaOfSs(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		if(!checkLabReport(113,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {
			if(!v.getMacrophage().toString().isEmpty() && insertdata(113,v.getMacrophage(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(113,v.getMacrophage(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		if(!checkLabReport(101,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {
			if(!v.getStrach().toString().isEmpty() && insertdata(101,v.getStrach(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(101,v.getStrach(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		if(!checkLabReport(102,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {
			if(!v.getVagetiableCells().toString().isEmpty() && insertdata(102,v.getVagetiableCells(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(102,v.getVagetiableCells(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		if(!checkLabReport(103,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {
			if(!v.getStoolPuscells().toString().isEmpty() && insertdata(103,v.getStoolPuscells(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(103,v.getStoolPuscells(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		if(!checkLabReport(104,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {
			if(!v.getEithelial().toString().isEmpty() && insertdata(104,v.getEithelial(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(104,v.getEithelial(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		if(!checkLabReport(105,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {
			if(!v.getFatDroplets().toString().isEmpty() && insertdata(105,v.getFatDroplets(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(105,v.getFatDroplets(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		if(!checkLabReport(106,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {
			if(!v.getFungi().toString().isEmpty() && insertdata(106,v.getFungi(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(106,v.getFungi(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
		}

		if(!checkLabReport(107,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {
			if(!v.getStoolWbc().toString().isEmpty() && insertdata(107,v.getStoolWbc(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(107,v.getStoolWbc(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		if(!checkLabReport(108,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {
			if(!v.getStoolRbc().toString().isEmpty() && insertdata(108,v.getStoolRbc(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(108,v.getStoolRbc(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		/*		if(!checkLabReport(109,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getFiscalyear())) {
			if(!v.getYeast().toString().isEmpty() && insertdata(109,v.getYeast(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(109,v.getYeast(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}*/


		if(!checkLabReport(110,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {
			if(!v.getMuscleFibers().toString().isEmpty() && insertdata(110,v.getMuscleFibers(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(110,v.getMuscleFibers(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}
		if(!checkLabReport(114,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {
			if(!v.getCharotLeyden().toString().isEmpty() && insertdata(114,v.getCharotLeyden(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(114,v.getCharotLeyden(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}



		if(submit!=0) {
			Session session=HibernateUtil.openSession();
			Transaction tx=null;
			try{
				tx=session.getTransaction();
				tx.begin();

				String sql="update TbLabTestHistory set ResultStatus='1' where LabId='"+v.getLabbill()+"' and  testId='"+v.getTestid()+"'";

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
		}
		return false;
	}

	@Override
	public boolean MicrobiologySaveEvent(LabResult v) {

		int submit=0;


		if(!checkLabReport(300,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {
			System.out.println("amoxy In"+v.getAmoxycillinClavulanAcid());
			System.out.println("userId In"+v.getUserId());
			System.out.println("fiscal In"+v.getFiscalyear());
			if(!v.getAmoxycillinClavulanAcid().toString().isEmpty() && insertdata(300,v.getAmoxycillinClavulanAcid(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			System.out.println("amoxy out");
			updatedata(300,v.getAmoxycillinClavulanAcid(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		if(!checkLabReport(301,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {
			if(!v.getGentamycin().toString().isEmpty() && insertdata(301,v.getGentamycin(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(301,v.getGentamycin(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		if(!checkLabReport(302,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {
			if(!v.getAzithromycin().toString().isEmpty() && insertdata(302,v.getAzithromycin(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(302,v.getAzithromycin(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		if(!checkLabReport(303,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {
			if(!v.getCiprofloxacin().toString().isEmpty() && insertdata(303,v.getCiprofloxacin(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(303,v.getCiprofloxacin(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		if(!checkLabReport(304,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {
			if(!v.getMeropenem().toString().isEmpty() && insertdata(304,v.getMeropenem(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(304,v.getMeropenem(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		if(!checkLabReport(305,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {
			if(!v.getCeftriaxone().toString().isEmpty() && insertdata(305,v.getCeftriaxone(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(305,v.getCeftriaxone(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		if(!checkLabReport(306,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {
			if(!v.getAmikacin().toString().isEmpty() && insertdata(306,v.getAmikacin(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(306,v.getAmikacin(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		if(!checkLabReport(307,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {
			if(!v.getPiperacillinTazobactam().toString().isEmpty() && insertdata(307,v.getPiperacillinTazobactam(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(307,v.getPiperacillinTazobactam(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		if(!checkLabReport(308,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {
			if(!v.getTigecycline().toString().isEmpty() && insertdata(308,v.getTigecycline(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(308,v.getTigecycline(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		if(!checkLabReport(309,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {
			if(!v.getErtapenem().toString().isEmpty() && insertdata(309,v.getErtapenem(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(309,v.getErtapenem(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		if(!checkLabReport(310,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {
			if(!v.getLevofloxacin().toString().isEmpty() && insertdata(310,v.getLevofloxacin(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(310,v.getLevofloxacin(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		if(!checkLabReport(311,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {
			if(!v.getImipenem().toString().isEmpty() && insertdata(311,v.getImipenem(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(311,v.getImipenem(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		if(!checkLabReport(312,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {
			if(!v.getMoxafloxacin().toString().isEmpty() && insertdata(312,v.getMoxafloxacin(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(312,v.getMoxafloxacin(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		if(!checkLabReport(313,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {
			if(!v.getNalidixicAcid().toString().isEmpty() && insertdata(313,v.getNalidixicAcid(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(313,v.getNalidixicAcid(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		if(!checkLabReport(314,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {
			if(!v.getCotrimexazole().toString().isEmpty() && insertdata(314,v.getCotrimexazole(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(314,v.getCotrimexazole(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		if(!checkLabReport(315,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {
			if(!v.getCefuroxime().toString().isEmpty() && insertdata(315,v.getCefuroxime(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(315,v.getCefuroxime(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		if(!checkLabReport(316,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {
			if(!v.getCeftazidime().toString().isEmpty() && insertdata(316,v.getCeftazidime(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(316,v.getCeftazidime(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		if(!checkLabReport(317,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {
			if(!v.getNitrofurantoin().toString().isEmpty() && insertdata(317,v.getNitrofurantoin(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(317,v.getNitrofurantoin(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		if(!checkLabReport(318,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {
			if(!v.getVancomycin().toString().isEmpty() && insertdata(318,v.getVancomycin(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(318,v.getVancomycin(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		if(!checkLabReport(319,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {
			if(!v.getCefixime().toString().isEmpty() && insertdata(319,v.getCefixime(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(319,v.getCefixime(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		//Organism_a
		if(!checkLabReport(244,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {
			if(!v.getOrganism_a().toString().isEmpty() && insertdata(244,v.getOrganism_a(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(244,v.getOrganism_a(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}


		//Organism_b
		if(!checkLabReport(245,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {
			if(!v.getOrganism_b().toString().isEmpty() && insertdata(245,v.getOrganism_b(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(245,v.getOrganism_b(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		if(!checkLabReport(268,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {
			if(!v.getMicro_nongrowth().toString().isEmpty() && insertdata(268,v.getMicro_nongrowth(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(268,v.getMicro_nongrowth(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}


		/*//amoxycillin_a
		if(!checkLabReport(271,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getFiscalyear())) {
			if(!v.getAmoxycillin_a().toString().isEmpty() && insertdata(271,v.getAmoxycillin_a(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(271,v.getAmoxycillin_a(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		//cefepime
		if(!checkLabReport(272,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getFiscalyear())) {
			if(!v.getCefepime().toString().isEmpty() && insertdata(272,v.getCefepime(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(272,v.getCefepime(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		//chloramphenicol
		if(!checkLabReport(273,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getFiscalyear())) {
			if(!v.getChloramphenicol().toString().isEmpty() && insertdata(273,v.getChloramphenicol(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(273,v.getChloramphenicol(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		//ceftrixon
		if(!checkLabReport(274,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getFiscalyear())) {
			if(!v.getCeftrixon().toString().isEmpty() && insertdata(274,v.getCeftrixon(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(274,v.getCeftrixon(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		//streptomycin
		if(!checkLabReport(275,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getFiscalyear())) {
			if(!v.getStreptomycin().toString().isEmpty() && insertdata(275,v.getStreptomycin(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(275,v.getStreptomycin(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		//nitrofurantoin
		if(!checkLabReport(276,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getFiscalyear())) {
			if(!v.getNitrofurantoin().toString().isEmpty() && insertdata(276,v.getNitrofurantoin(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(276,v.getNitrofurantoin(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		//gentamycin
		if(!checkLabReport(277,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getFiscalyear())) {
			if(!v.getGentamycin().toString().isEmpty() && insertdata(277,v.getGentamycin(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(277,v.getGentamycin(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		//cepradine
		if(!checkLabReport(278,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getFiscalyear())) {
			if(!v.getCepradine().toString().isEmpty() && insertdata(278,v.getCepradine(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(278,v.getCepradine(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		//doxycycline
		if(!checkLabReport(279,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getFiscalyear())) {
			if(!v.getDoxycycline().toString().isEmpty() && insertdata(279,v.getDoxycycline(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(279,v.getDoxycycline(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		//netilimyin
		if(!checkLabReport(280,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getFiscalyear())) {
			if(!v.getNetilimyin().toString().isEmpty() && insertdata(280,v.getNetilimyin(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(280,v.getNetilimyin(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		//azithromycin
		if(!checkLabReport(281,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getFiscalyear())) {
			if(!v.getAzithromycin().toString().isEmpty() && insertdata(281,v.getAzithromycin(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(281,v.getAzithromycin(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		//penicillin
		if(!checkLabReport(282,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getFiscalyear())) {
			if(!v.getPenicillin().toString().isEmpty() && insertdata(282,v.getPenicillin(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(282,v.getPenicillin(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		//meropenem
		if(!checkLabReport(283,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getFiscalyear())) {
			if(!v.getMeropenem().toString().isEmpty() && insertdata(283,v.getMeropenem(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(283,v.getMeropenem(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		//coTrimoxazole
		if(!checkLabReport(284,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getFiscalyear())) {
			if(!v.getCoTrimoxazole().toString().isEmpty() && insertdata(284,v.getCoTrimoxazole(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(284,v.getCoTrimoxazole(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		//cefixime
		if(!checkLabReport(285,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getFiscalyear())) {
			if(!v.getCefixime().toString().isEmpty() && insertdata(285,v.getCefixime(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(285,v.getCefixime(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		//ceftazidime
		if(!checkLabReport(286,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getFiscalyear())) {
			if(!v.getCeftazidime().toString().isEmpty() && insertdata(286,v.getCeftazidime(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(286,v.getCeftazidime(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		//nalidiximeAcid
		if(!checkLabReport(287,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getFiscalyear())) {
			if(!v.getNalidiximeAcid().toString().isEmpty() && insertdata(287,v.getNalidiximeAcid(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(287,v.getNalidiximeAcid(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		//nalidiximeAcid
		if(!checkLabReport(288,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getFiscalyear())) {
			if(!v.getErythromycin().toString().isEmpty() && insertdata(288,v.getErythromycin(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(288,v.getErythromycin(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		//ceftaxime
		if(!checkLabReport(289,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getFiscalyear())) {
			if(!v.getCeftaxime().toString().isEmpty() && insertdata(289,v.getCeftaxime(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(289,v.getCeftaxime(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		//cephalexin
		if(!checkLabReport(290,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getFiscalyear())) {
			if(!v.getCephalexin().toString().isEmpty() && insertdata(290,v.getCephalexin(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(290,v.getCephalexin(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		//cephalexin
		if(!checkLabReport(291,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getFiscalyear())) {
			if(!v.getCiprofloxacine().toString().isEmpty() && insertdata(291,v.getCiprofloxacine(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(291,v.getCiprofloxacine(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		//amicacin
		if(!checkLabReport(292,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getFiscalyear())) {
			if(!v.getAmicacin().toString().isEmpty() && insertdata(292,v.getAmicacin(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(292,v.getAmicacin(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		//amicacin
		if(!checkLabReport(293,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getFiscalyear())) {
			if(!v.getLevofloxacin().toString().isEmpty() && insertdata(293,v.getLevofloxacin(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(293,v.getLevofloxacin(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}


		//Organism_a
		if(!checkLabReport(244,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getFiscalyear())) {
			if(!v.getOrganism_a().toString().isEmpty() && insertdata(244,v.getOrganism_a(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(244,v.getOrganism_a(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}


		//Organism_b
		if(!checkLabReport(245,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getFiscalyear())) {
			if(!v.getOrganism_b().toString().isEmpty() && insertdata(245,v.getOrganism_b(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(245,v.getOrganism_b(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		if(!checkLabReport(268,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getFiscalyear())) {
			if(!v.getMicro_nongrowth().toString().isEmpty() && insertdata(268,v.getMicro_nongrowth(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(268,v.getMicro_nongrowth(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}*/



		if(submit!=0) {
			return true;
		}
		else {
			return false;
		}
	}



	@Override
	public List<LabResult> BioSerHormoneTestData(String testId, String headId,String labbill, String fiscalyear) {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		List<LabResult> query=new ArrayList<LabResult>();
		try{
			tx=session.getTransaction();
			tx.begin();

			String LabInchargeId="0",Doctor1Id="0",Doctor2Id="0",machineId="0",titleId="0";
			List<?> list1 = session.createSQLQuery("select LabInchargeId,Doctor1Id,Doctor2Id,machineId,titleId from tblabreportvalue where FiscalYear='"+fiscalyear+"' and LabId='"+labbill+"' and testId='"+testId+"' and HeadId='"+headId+"'").list();
			for(Iterator<?> iter = list1.iterator(); iter.hasNext();)
			{	
				Object[] element = (Object[]) iter.next();
				LabInchargeId=element[0].toString();
				Doctor1Id=element[1].toString();
				Doctor2Id=element[2].toString();
				machineId=element[3].toString();
				titleId=element[4].toString();
				break;
			}

			System.out.println("Hi");
			if(checkTestHasGroupTest(testId)) {
				System.out.println("testId "+testId);

				String sql="select a.SubTestId,a.SubTestName,a.Unit,a.NormalRange,isnull((select value from tblabreportvalue where FiscalYear='"+fiscalyear+"' and LabId='"+labbill+"' and testId='"+testId+"' and HeadId='"+headId+"'  and rId=a.SubTestId),'') as Result,(select TestName from TbTestName where TestId=a.TestId) as MainTestName,isnull((select sorting from tblabreportvalue where FiscalYear='"+fiscalyear+"' and LabId='"+labbill+"' and testId='"+testId+"' and HeadId='"+headId+"'  and rId=a.SubTestId),'') as ResultSorting,a.Sorting from TbSubTestName a where a.TestId='"+testId+"' order by a.Sorting";
				System.out.println(sql);
				List<?> list = session.createSQLQuery(sql).list();

				for(Iterator<?> iter = list.iterator(); iter.hasNext();)
				{	
					Object[] element = (Object[]) iter.next();

					String saveSorting=element[6].toString();
					String settingSorting=element[7].toString();
					String sorting="";

					if(saveSorting.equals("0") || saveSorting.equals("")) {
						sorting=settingSorting;
					}
					else {
						sorting=saveSorting;
					}
					query.add(new LabResult(element[0].toString(),element[1].toString(),element[2].toString(),element[3].toString(),element[4].toString(),element[5].toString(),sorting,testId,LabInchargeId,Doctor1Id,Doctor2Id,machineId,titleId));
				}
			}
			else{
				String sql="select a.TestId,a.TestName,a.Unit,a.NormalRange,isnull((select value from tblabreportvalue where FiscalYear='"+fiscalyear+"' and LabId='"+labbill+"' and testId='"+testId+"' and HeadId='"+headId+"'  and rId=a.TestId),'') as Result,(select TestName from TbTestName where TestId=a.TestId) as MainTestName,isnull((select Sorting from tblabreportvalue where FiscalYear='"+fiscalyear+"' and LabId='"+labbill+"' and testId='"+testId+"' and HeadId='"+headId+"'  and rId=a.TestId),'') as Sorting from TbTestName a where a.TestId='"+testId+"'";
				System.out.println(sql);
				List<?> list = session.createSQLQuery(sql).list();

				for(Iterator<?> iter = list.iterator(); iter.hasNext();)
				{	
					Object[] element = (Object[]) iter.next();

					query.add(new LabResult(element[0].toString(),element[1].toString(),element[2].toString(),element[3].toString(),element[4].toString(),element[5].toString(),element[6].toString(),testId,LabInchargeId,Doctor1Id,Doctor2Id,machineId,titleId));
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
	public List<LabResult> setBioSerHormoneTestData(String testIdlist,String labbill,String headId, String fiscalyear,String cMonth) {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		List<LabResult> query=new ArrayList<LabResult>();
		try{
			tx=session.getTransaction();
			tx.begin();


			String resultValue=testIdlist.substring(testIdlist.indexOf("[")+1, testIdlist.indexOf("]"));

			StringTokenizer token=new StringTokenizer(resultValue,",");
			while(token.hasMoreTokens()) {
				String testId=token.nextToken();

				String LabInchargeId="0",Doctor1Id="0",Doctor2Id="0",machineId="0",titleId="0";
				List<?> list1 = session.createSQLQuery("select LabInchargeId,Doctor1Id,Doctor2Id,machineId,titleId from tblabreportvalue where FiscalYear='"+fiscalyear+"' and cMonth='"+cMonth+"' and LabId='"+labbill+"' and testId='"+testId+"' and HeadId='"+headId+"'").list();
				for(Iterator<?> iter = list1.iterator(); iter.hasNext();)
				{	
					Object[] element = (Object[]) iter.next();
					LabInchargeId=element[0].toString();
					Doctor1Id=element[1].toString();
					Doctor2Id=element[2].toString();
					machineId=element[3].toString();
					titleId=element[4].toString();
					break;
				}

				if(checkTestHasGroupTest(testId)) {


					List<?> list = session.createSQLQuery("select a.SubTestId,a.SubTestName,a.Unit,a.NormalRange,isnull((select value from tblabreportvalue where FiscalYear='"+fiscalyear+"' and cMonth='"+cMonth+"' and LabId='"+labbill+"' and testId='"+testId+"' and HeadId='"+headId+"'  and rId=a.SubTestId),'') as Result,(select TestName from TbTestName where TestId=a.TestId) as MainTestName,isnull((select sorting from tblabreportvalue where FiscalYear='"+fiscalyear+"' and cMonth='"+cMonth+"' and LabId='"+labbill+"' and testId='"+testId+"' and HeadId='"+headId+"'  and rId=a.SubTestId),'') as ResultSorting,a.Sorting from TbSubTestName a where a.TestId='"+testId+"' order by a.Sorting").list();

					for(Iterator<?> iter = list.iterator(); iter.hasNext();)
					{	
						Object[] element = (Object[]) iter.next();
						String saveSorting=element[6].toString();
						String settingSorting=element[7].toString();
						String sorting="";

						if(saveSorting.equals("0") || saveSorting.equals("")) {
							sorting=settingSorting;
						}
						else {
							sorting=saveSorting;
						}
						query.add(new LabResult(element[0].toString(),element[1].toString(),element[2].toString(),element[3].toString(),element[4].toString(),element[5].toString(),sorting,testId,LabInchargeId,Doctor1Id,Doctor2Id,machineId,titleId));
					}
				}
				else{
					List<?> list = session.createSQLQuery("select a.TestId,a.TestName,a.Unit,a.NormalRange,isnull((select value from tblabreportvalue where FiscalYear='"+fiscalyear+"' and cMonth='"+cMonth+"' and LabId='"+labbill+"' and testId='"+testId+"' and HeadId='"+headId+"'  and rId=a.TestId),'') as Result,(select TestName from TbTestName where TestId=a.TestId) as MainTestName,isnull((select Sorting from tblabreportvalue where FiscalYear='"+fiscalyear+"' and cMonth='"+cMonth+"' and LabId='"+labbill+"' and testId='"+testId+"' and HeadId='"+headId+"'  and rId=a.TestId),'') as Sorting from TbTestName a where a.TestId='"+testId+"'").list();

					for(Iterator<?> iter = list.iterator(); iter.hasNext();)
					{	
						Object[] element = (Object[]) iter.next();

						query.add(new LabResult(element[0].toString(),element[1].toString(),element[2].toString(),element[3].toString(),element[4].toString(),element[5].toString(),element[6].toString(),testId,LabInchargeId,Doctor1Id,Doctor2Id,machineId,titleId));
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


	private boolean checkTestHasGroupTest(String testId) {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		String sql="";
		try {
			tx=session.getTransaction();
			tx.begin();
			sql="select TestId from TbSubTestName where TestId='"+testId+"'";
			System.out.println(sql);

			List<?> list = session.createSQLQuery(sql).list();

			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				return true;
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
	public boolean biochemestrySaveEvent(LabResult v) {
		String result=v.getResult().toString();

		result=result.substring(1,result.length()-1);

		StringTokenizer resulttoken=new StringTokenizer(result,",");
		String eachToken="";
		String eachResult="";
		String eachTestId="";
		String eachMaintTestId="";
		int eachSort=0;
		int submit=0;
		int update=0;
		while(resulttoken.hasMoreTokens()) {
			eachToken=resulttoken.nextToken().toString().trim();

			System.out.println("eachToken "+eachToken);

			StringTokenizer sorttoken=new StringTokenizer(eachToken,"@");
			while(sorttoken.hasMoreTokens()) {
				eachMaintTestId=sorttoken.nextToken().toString().trim();

				System.out.println("eachMaintTestId "+eachMaintTestId);
				eachTestId=sorttoken.nextToken().toString().trim();
				eachResult=sorttoken.nextToken().toString().trim();
				eachResult=eachResult.replace("~", "");
				eachSort=Integer.parseInt(sorttoken.nextToken().toString().trim());

				if(!checkLabReport(Integer.parseInt(eachTestId),v.getLabbill(),eachMaintTestId,v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {
					if(!eachResult.equals("") && insertdata(Integer.parseInt(eachTestId),eachResult,eachMaintTestId,v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),eachSort,v)) {
						submit++;
					}
				}
				else {
					updatedata(Integer.parseInt(eachTestId),eachResult,eachMaintTestId,v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),eachSort,v);
					submit++;
				}

				if(submit!=0) {
					Session session=HibernateUtil.openSession();
					Transaction tx=null;
					try{
						tx=session.getTransaction();
						tx.begin();

						String sql="update TbLabTestHistory set ResultStatus='1' where LabId='"+v.getLabbill()+"' and  testId='"+eachMaintTestId+"'";

						session.createSQLQuery(sql).executeUpdate();
						tx.commit();
						update++;

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
				}
			}
		}


		if(update!='0') {
			return true;
		}

		return false;

	}

	@Override
	public boolean SerologySaveEvent(LabResult v) {
		String result=v.getResult().toString();

		result=result.substring(1,result.length()-1);

		System.out.println("result "+result);

		StringTokenizer resulttoken=new StringTokenizer(result,",");
		String eachToken="";
		String eachResult="";
		String eachTestId="";
		String eachMaintTestId="";
		int eachSort=0;
		int submit=0;
		int update=0;
		while(resulttoken.hasMoreTokens()) {
			eachToken=resulttoken.nextToken().toString().trim();

			System.out.println("eachToken "+eachToken);

			StringTokenizer sorttoken=new StringTokenizer(eachToken,"@");
			while(sorttoken.hasMoreTokens()) {
				eachMaintTestId=sorttoken.nextToken().toString().trim();

				System.out.println("eachMaintTestId "+eachMaintTestId);
				eachTestId=sorttoken.nextToken().toString().trim();
				eachResult=sorttoken.nextToken().toString().trim();
				eachResult=eachResult.replace("~", "");
				eachSort=Integer.parseInt(sorttoken.nextToken().toString().trim());

				if(!checkLabReport(Integer.parseInt(eachTestId),v.getLabbill(),eachMaintTestId,v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {
					if(!eachResult.equals("") && insertdata(Integer.parseInt(eachTestId),eachResult,eachMaintTestId,v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),eachSort,v)) {
						submit++;
					}
				}
				else {
					updatedata(Integer.parseInt(eachTestId),eachResult,eachMaintTestId,v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),eachSort,v);
					submit++;
				}

				if(submit!=0) {
					Session session=HibernateUtil.openSession();
					Transaction tx=null;
					try{
						tx=session.getTransaction();
						tx.begin();

						String sql="update TbLabTestHistory set ResultStatus='1' where FiscalYear='"+v.getFiscalyear()+"' and LabId='"+v.getLabbill()+"' and  testId='"+eachMaintTestId+"'";

						session.createSQLQuery(sql).executeUpdate();
						tx.commit();
						update++;

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
				}
			}
		}


		if(update!='0') {
			return true;
		}

		return false;
	}

	@Override
	public List<LabResult> setHeamUrineStoolMicSaveData(String testId,String headid, String labbill, String fiscalyear) {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		List<LabResult> query=new ArrayList<LabResult>();
		try{
			tx=session.getTransaction();
			tx.begin();

			List<?> list = session.createSQLQuery("select value,rid,LabInchargeId,Doctor1Id,Doctor2Id,machineId,titleId from tblabreportvalue where FiscalYear='"+fiscalyear+"' and HeadId='"+headid+"' and LabId='"+labbill+"' and testId='"+testId+"'"
					+ "").list();

			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				Object[] element = (Object[]) iter.next();



				query.add(new LabResult(element[0].toString(),element[1].toString(),element[2].toString(),element[3].toString(),element[4].toString(),element[5].toString(),element[6].toString()));
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
	public boolean HormoneSaveEvent(LabResult v) {
		String result=v.getResult().toString();

		result=result.substring(1,result.length()-1);

		StringTokenizer resulttoken=new StringTokenizer(result,",");
		String eachToken="";
		String eachResult="";
		String eachTestId="";
		String eachMaintTestId="";
		int eachSort=0;
		int submit=0;
		int update=0;
		while(resulttoken.hasMoreTokens()) {
			eachToken=resulttoken.nextToken().toString().trim();

			System.out.println("eachToken "+eachToken);

			StringTokenizer sorttoken=new StringTokenizer(eachToken,"@");
			while(sorttoken.hasMoreTokens()) {
				eachMaintTestId=sorttoken.nextToken().toString().trim();

				System.out.println("eachMaintTestId "+eachMaintTestId);
				eachTestId=sorttoken.nextToken().toString().trim();
				eachResult=sorttoken.nextToken().toString().trim();
				eachResult=eachResult.replace("~", "");
				eachSort=Integer.parseInt(sorttoken.nextToken().toString().trim());

				if(!checkLabReport(Integer.parseInt(eachTestId),v.getLabbill(),eachMaintTestId,v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {
					if(!eachResult.equals("") && insertdata(Integer.parseInt(eachTestId),eachResult,eachMaintTestId,v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),eachSort,v)) {
						submit++;
					}
				}
				else {
					updatedata(Integer.parseInt(eachTestId),eachResult,eachMaintTestId,v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),eachSort,v);
					submit++;
				}

				if(submit!=0) {
					Session session=HibernateUtil.openSession();
					Transaction tx=null;
					try{
						tx=session.getTransaction();
						tx.begin();

						String sql="update TbLabTestHistory set ResultStatus='1' where LabId='"+v.getLabbill()+"' and  testId='"+eachMaintTestId+"'";

						session.createSQLQuery(sql).executeUpdate();
						tx.commit();
						update++;

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
				}
			}
		}


		if(update!='0') {
			return true;
		}

		return false;
	}

	@Override
	public List<TestGroup> getTestGrouplist() {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		List<TestGroup> query=new ArrayList<TestGroup>();
		try{
			tx=session.getTransaction();
			tx.begin();


			List<?> list = session.createSQLQuery("select HeadId,GroupName from TbLabTestGroup").list();

			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				Object[] element = (Object[]) iter.next();
				query.add(new TestGroup(element[0].toString(),element[1].toString()));
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
	public boolean isTestExist(Test v) {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		try{
			tx=session.getTransaction();
			tx.begin();

			String sql="select TestName from TbTestName where TestName='"+v.getTestName()+"' and TestId!='"+v.getTestId()+"'";

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
	public boolean editTest(Test v) {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		try{
			tx=session.getTransaction();
			tx.begin();


			String sql="update TbTestName  set "
					+ "HeadId='"+v.getHeadId()+"',"
					+ "TestName='"+v.getTestName()+"',"
					+ "Rate='"+v.getRate()+"',"
					+ "Discount='"+v.getDoctorCommission()+"',"
					+ "Unit='"+v.getUnit()+"',"
					+ "NormalRange='"+v.getNormalRange()+"',"
					+ "DiscountAllow='"+v.getDiscountAllow()+"',"
					+ "createBy='"+v.getUserId()+"',"
					+ "entrytime=CURRENT_TIMESTAMP"
					+ " where testId='"+v.getTestId()+"'";

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
	public List<Test> getParentTestlist() {

		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		List<Test> query=new ArrayList<Test>();
		try{
			tx=session.getTransaction();
			tx.begin();


			List<?> list = session.createSQLQuery("select TestId,TestName from TbTestName order by TestName").list();

			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				Object[] element = (Object[]) iter.next();
				query.add(new Test(element[0].toString(),element[1].toString()));
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
	public List<TestParticular> getTestParticularNamelist() {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		List<TestParticular> query=new ArrayList<TestParticular>();
		try{
			tx=session.getTransaction();
			tx.begin();


			List<?> list = session.createSQLQuery("select ParticularId,ParticularName from TbTestParticular order by ParticularName").list();

			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				Object[] element = (Object[]) iter.next();
				query.add(new TestParticular(element[0].toString(),element[1].toString()));
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
	public List<PatientRegistration> getRuningPatientList() {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		List<PatientRegistration> query=new ArrayList<PatientRegistration>();
		try{
			tx=session.getTransaction();
			tx.begin();


			List<?> list = session.createSQLQuery("select tbpatientinfo.autoId,tbpatientinfo.RegNo,tbpatientinfo.PatientName,convert(varchar,tbpatientinfo.admissionDate,23) as admissionDate,tbpatientinfo.admissionTime,TbPatientInfo.period,TbPatientInfo.FiscalYear,(select seatName from tbSeatCreate where SeatId=tbpatientinfo.SeatId) as SeatName from tbpatientinfo where deschargeDate IS NULL  group by tbpatientinfo.autoId,tbpatientinfo.RegNo,tbpatientinfo.PatientName,tbpatientinfo.admissionDate,tbpatientinfo.admissionTime,TbPatientInfo.period,TbPatientInfo.FiscalYear,tbpatientinfo.SeatId order by FiscalYear desc").list();

			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				Object[] element = (Object[]) iter.next();
				query.add(new PatientRegistration(element[0].toString(),element[1].toString(),element[2].toString(),element[3].toString(),element[4].toString(),element[5].toString(),element[6].toString(),element[7].toString()));
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
	public List<PatientRegistration> getIndoorPatientInformation(String patientId) {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		List<PatientRegistration> query=new ArrayList<PatientRegistration>();
		try{
			tx=session.getTransaction();
			tx.begin();


			List<?> list = session.createSQLQuery("select RegNo,PatientName,Age,Month,Day,Sex,RefferBy,ISNULL((select Degree from TbDoctorInfo where DoctorId=RefferBy),'') as RefferDegree,Consultant,ISNULL((select Degree from TbDoctorInfo where DoctorId=Consultant),'') as ConsultantDegree,Address,(select seatName from tbSeatCreate where SeatId=tbpatientinfo.SeatId) as SeatName,MobileNo,FiscalYear,period from TbPatientInfo where autoId='"+patientId+"'").list();

			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				Object[] element = (Object[]) iter.next();
				query.add(new PatientRegistration(element[0].toString(),element[1].toString(),element[2].toString(),element[3].toString(),element[4].toString(),element[5].toString(),element[6].toString(),element[7].toString(),element[8].toString(),element[9].toString(),element[10].toString(),element[11].toString(),element[12].toString(),element[13].toString(),element[14].toString()));
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
	public List<LabBilling> getLabBillList() {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		List<LabBilling> query=new ArrayList<LabBilling>();
		try{
			tx=session.getTransaction();
			tx.begin();

			String sql="";
			//sql="select labId,PatientName,Mobile,(select convert(varchar,DateTime, 120)) as DateTime,ISNULL((select name+' ('+degree+')' from TbDoctorInfo where DoctorId=RefferBy),'') as RefferDegee,FiscalYear,CMonth from TbLabPatient order by LabId desc";
			sql="select labId,PatientName,Mobile,(select convert(varchar,DateTime, 120)) as DateTime,ISNULL((select name+' ('+degree+')' from TbDoctorInfo where DoctorId=RefferBy),'') as RefferDegee,FiscalYear,CMonth from TbLabPatient order by DateTime desc, CMonth, labid desc";

			List<?> list = session.createSQLQuery(sql).list();

			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				Object[] element = (Object[]) iter.next();
				query.add(new LabBilling(element[0].toString(),element[1].toString(),element[2].toString(),element[3].toString(),element[4].toString(),element[5].toString(),element[6].toString()));
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
	public List<LabBilling> getLabBillWiseTestDetails(String labId, String fiscalYear,String Cmonth) {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		List<LabBilling> query=new ArrayList<LabBilling>();
		try{
			tx=session.getTransaction();
			tx.begin();


			List<?> list = session.createSQLQuery(" select a.PatientName,a.Mobile,a.Age,a.Month,a.Day,a.Sex,ISNULL((select name from TbDoctorInfo where DoctorId=a.RefferBy),'') as DoctorName,ISNULL((select Degree from TbDoctorInfo where DoctorId=a.RefferBy),'') as RefferDegree,b.testId,b.testName,b.headId,(select GroupName from TbLabTestGroup where headId=b.headId) as GroupName,b.ResultStatus,b.LabId,b.FiscalYear from TbLabPatient a join TbLabTestHistory b on a.labId=b.labId and a.FiscalYear=b.FiscalYear and a.CMonth=b.CMonth where a.labId='"+labId+"' and a.cmonth='"+Cmonth+"' and a.FiscalYear='"+fiscalYear+"' and b.type='1'").list();

			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				Object[] element = (Object[]) iter.next();
				query.add(new LabBilling(element[0].toString(),element[1].toString(),element[2].toString(),element[3].toString(),element[4].toString(),element[5].toString(),element[6].toString(),element[7].toString(),element[8].toString(),element[9].toString(),element[10].toString(),element[11].toString(),element[12].toString(),element[13].toString(),element[14].toString()));
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
	public List<LabResult> setHeamatolorySaveData(String testId, String labbill, String fiscalyear,String cMonth) {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		List<LabResult> query=new ArrayList<LabResult>();
		try{
			tx=session.getTransaction();
			tx.begin();

			List<?> list = session.createSQLQuery("select value,rid,LabInchargeId,Doctor1Id,Doctor2Id,machineId,titleId  from tblabreportvalue where FiscalYear='"+fiscalyear+"' and cMonth='"+cMonth+"' and HeadId='1' and LabId='"+labbill+"' and testId='"+testId+"'"
					+ "").list();

			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				Object[] element = (Object[]) iter.next();



				query.add(new LabResult(element[0].toString(),element[1].toString(),element[2].toString(),element[3].toString(),element[4].toString(),element[5].toString(),element[6].toString()));
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
	public List<LabResult> setUrineSaveData(String testId, String labbill, String fiscalyear,String cMonth) {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		List<LabResult> query=new ArrayList<LabResult>();
		try{
			tx=session.getTransaction();
			tx.begin();

			List<?> list = session.createSQLQuery("select value,rid,LabInchargeId,Doctor1Id,Doctor2Id,machineId,titleId from tblabreportvalue where FiscalYear='"+fiscalyear+"' and cMonth='"+cMonth+"' and HeadId='5' and LabId='"+labbill+"' and testId='"+testId+"'"
					+ "").list();

			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				Object[] element = (Object[]) iter.next();



				query.add(new LabResult(element[0].toString(),element[1].toString(),element[2].toString(),element[3].toString(),element[4].toString(),element[5].toString(),element[6].toString()));
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
	public List<LabResult> BioSerHormoneTestData(String testIdlist) {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		List<LabResult> query=new ArrayList<LabResult>();
		try{
			tx=session.getTransaction();
			tx.begin();


			String resultValue=testIdlist.substring(testIdlist.indexOf("[")+1, testIdlist.indexOf("]"));
			System.out.println("resultValue "+resultValue);
			StringTokenizer token=new StringTokenizer(resultValue,",");
			while(token.hasMoreTokens()) {

				String testId=token.nextToken();
				if(checkTestHasGroupTest(testId)) {
					List<?> list = session.createSQLQuery("select a.SubTestId,a.SubTestName,a.Unit,a.NormalRange,(select TestName from TbTestName where TestId=a.TestId) as MainTestName from TbSubTestName a where a.TestId='"+testId+"'").list();

					for(Iterator<?> iter = list.iterator(); iter.hasNext();)
					{	
						Object[] element = (Object[]) iter.next();
						query.add(new LabResult(element[0].toString(),element[1].toString(),element[2].toString(),element[3].toString(),element[4].toString(),testId));
					}
				}
				else{
					List<?> list = session.createSQLQuery("select a.TestId,a.TestName,a.Unit,a.NormalRange,(select TestName from TbTestName where TestId=a.TestId) as MainTestName from TbTestName a where a.TestId='"+testId+"'").list();

					for(Iterator<?> iter = list.iterator(); iter.hasNext();)
					{	
						Object[] element = (Object[]) iter.next();
						query.add(new LabResult(element[0].toString(),element[1].toString(),element[2].toString(),element[3].toString(),element[4].toString(),testId));
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
	public boolean deleteLabTestData(String userId, String fiscalyear,String cmonth, String find, String counter,String testId,String type,String regNo,String labId) {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		try{
			tx=session.getTransaction();
			tx.begin();
			String sql="";


			if(find.equals("0")) {
				ArrayList DSN=new ArrayList();
				ArrayList DNameOfPericular=new ArrayList();
				ArrayList DNameOfRate=new ArrayList();
				int Dcount=0;

				List<?> list = session.createSQLQuery("select ParticularId,(select ParticularName from TbTestParticular where ParticularId=ParticularRefId) as ParticularName,Rate from TbTestPerticularName where TestId='"+testId+"'").list();

				for(Iterator<?> iter = list.iterator(); iter.hasNext();)
				{	
					Object[] element = (Object[]) iter.next();
					DSN.add(element[0].toString());
					DNameOfPericular.add(element[1].toString());
					DNameOfRate.add(element[2].toString());
					Dcount++;
				}

				for(int a=0;a<Dcount;a++){

					int check=0;
					sql="select tblabtesthistory.TestName from tblabtesthistory where tblabtesthistory.FiscalYear='"+fiscalyear+"' and tblabtesthistory.CMonth='"+cmonth+"' and tblabtesthistory.TestName='"+DNameOfPericular.get(a).toString()+"' and  tblabtesthistory.createBy='"+userId+"' and counter='"+counter+"' and tblabtesthistory.labId IS NULL";
					List<?> list1 = session.createSQLQuery(sql).list();
					for(Iterator<?> iter = list1.iterator(); iter.hasNext();)
					{	
						check=1;
						break;

					}

					if(check==1){
						String deleteQ="delete from tblabtesthistory where tblabtesthistory.FiscalYear='"+fiscalyear+"' and tblabtesthistory.CMonth='"+cmonth+"' and  tblabtesthistory.TestName='"+DNameOfPericular.get(a).toString()+"' and tblabtesthistory.type=2 and  tblabtesthistory.createBy='"+userId+"' and counter='"+counter+"' and tblabtesthistory.labId IS NULL";
						session.createSQLQuery(deleteQ).executeUpdate();
					}

				}

				String deleteQ="delete from tblabtesthistory where tblabtesthistory.FiscalYear='"+fiscalyear+"' and tblabtesthistory.CMonth='"+cmonth+"' and tblabtesthistory.TestId='"+testId+"' and tblabtesthistory.type=1 and  tblabtesthistory.createBy='"+userId+"' and counter='"+counter+"' and tblabtesthistory.labId IS NULL";
				System.out.println(deleteQ);
				session.createSQLQuery(deleteQ).executeUpdate();
				String deleteQT="delete from tblabtesthistory where tblabtesthistory.FiscalYear='"+fiscalyear+"' and tblabtesthistory.CMonth='"+cmonth+"' and  tblabtesthistory.type=2 and  tblabtesthistory.createBy='"+userId+"' and counter='"+counter+"' and tblabtesthistory.labId IS NULL";
				System.out.println(deleteQT);
				session.createSQLQuery(deleteQT).executeUpdate();
				DSN.clear();
				DNameOfPericular.clear();
				DNameOfRate.clear();


				ArrayList TestIdList=new ArrayList();
				ArrayList TestTypeList=new ArrayList();
				sql="select TestId,Type from tblabtesthistory where tblabtesthistory.FiscalYear='"+fiscalyear+"' and tblabtesthistory.CMonth='"+cmonth+"' and  tblabtesthistory.createBy='"+userId+"' and counter='"+counter+"' and tblabtesthistory.type='1' and tblabtesthistory.labId IS NULL order by type asc";
				List<?> list1 = session.createSQLQuery(sql).list();
				for(Iterator<?> iter = list1.iterator(); iter.hasNext();)
				{	
					Object[] element = (Object[]) iter.next();
					TestIdList.add(element[0].toString());
					TestTypeList.add(element[1].toString());

				}

				for(int a=0;a<TestIdList.size();a++) {
					ArrayList particularId=new ArrayList();
					ArrayList NameOfPericular=new ArrayList();
					ArrayList NameOfRate=new ArrayList();
					ArrayList NameOfQty=new ArrayList();
					int count=0;

					sql="select ParticularId,(select ParticularName from TbTestParticular where ParticularId=ParticularRefId) as ParticularName,Rate,qty from TbTestPerticularName where TestId='"+TestIdList.get(a).toString()+"'";
					List<?> list2 = session.createSQLQuery(sql).list();
					for(Iterator<?> iter = list2.iterator(); iter.hasNext();)
					{	
						Object[] element = (Object[]) iter.next();
						particularId.add(element[0].toString());
						NameOfPericular.add(element[1].toString());
						NameOfRate.add(element[2].toString());
						NameOfQty.add(element[3].toString());
						count++;
					}

					for(int b=0;b<count;b++){

						int check=0;
						sql="select tblabtesthistory.TestName from tblabtesthistory where tblabtesthistory.FiscalYear='"+fiscalyear+"' and tblabtesthistory.CMonth='"+cmonth+"'  and tblabtesthistory.TestName='"+NameOfPericular.get(b).toString()+"' and  tblabtesthistory.createBy='"+userId+"' and counter='"+counter+"' and tblabtesthistory.labId IS NULL";
						List<?> list3 = session.createSQLQuery(sql).list();
						for(Iterator<?> iter = list3.iterator(); iter.hasNext();)
						{	
							check=1;
							break;
						}

						if(check==0){
							String query ="insert into tblabtesthistory (regNo,TestId,testName,qty,rate,discount,ResultStatus,RefundStatus,type,counter,date,entryTime,createBy,FiscalYear,Cmonth) values ('"+regNo+"','"+particularId.get(b).toString()+"','"+NameOfPericular.get(b).toString()+"','"+NameOfQty.get(b).toString()+"','"+df.format(Double.parseDouble(NameOfRate.get(b).toString()))+"','0','NOT DONE','0','2','"+counter+"',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'"+userId+"','"+fiscalyear+"','"+cmonth+"')";
							System.out.println(query);
							session.createSQLQuery(query).executeUpdate();

							String Udquery ="insert into tbUdlabtesthistory (regNo,TestId,testName,qty,rate,discount,ResultStatus,RefundStatus,type,counter,date,entryTime,createBy,FiscalYear,Cmonth) values ('"+regNo+"','"+particularId.get(b).toString()+"','"+NameOfPericular.get(b).toString()+"','"+NameOfQty.get(b).toString()+"','"+df.format(Double.parseDouble(NameOfRate.get(b).toString()))+"','0','NOT DONE','0','2','"+counter+"',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'"+userId+"','"+fiscalyear+"','"+cmonth+"')";
							System.out.println(Udquery);
							session.createSQLQuery(Udquery).executeUpdate();
						}
					}									
					particularId.clear();
					NameOfPericular.clear();
					NameOfRate.clear();
					NameOfQty.clear();
				}
			}
			else if(find.equals("1")) {

				ArrayList DSN=new ArrayList();
				ArrayList DNameOfPericular=new ArrayList();
				ArrayList DNameOfRate=new ArrayList();
				/*	int Dcount=0;

				List<?> list = session.createSQLQuery("select ParticularId,(select ParticularName from TbTestParticular where ParticularId=ParticularRefId) as ParticularName,Rate from TbTestPerticularName where TestId='"+testId+"'").list();

				for(Iterator<?> iter = list.iterator(); iter.hasNext();)
				{	
					Object[] element = (Object[]) iter.next();
					DSN.add(element[0].toString());
					DNameOfPericular.add(element[1].toString());
					DNameOfRate.add(element[2].toString());
					Dcount++;
				}

				for(int a=0;a<Dcount;a++){

					int check=0;
					sql="select tblabtesthistory.TestName from tblabtesthistory where tblabtesthistory.FiscalYear='"+fiscalyear+"' and tblabtesthistory.CMonth='"+cmonth+"' and tblabtesthistory.TestName='"+DNameOfPericular.get(a).toString()+"' and tblabtesthistory.labId='"+labId+"'";
					List<?> list1 = session.createSQLQuery(sql).list();
					for(Iterator<?> iter = list1.iterator(); iter.hasNext();)
					{	
						check=1;
						break;

					}

					if(check==1){
						String deleteQ="delete from tblabtesthistory where tblabtesthistory.FiscalYear='"+fiscalyear+"'  and  tblabtesthistory.TestName='"+DNameOfPericular.get(a).toString()+"' and tblabtesthistory.type=2 and tblabtesthistory.labId='"+labId+"'";
						session.createSQLQuery(deleteQ).executeUpdate();
					}

				}*/

				String deleteQ="delete from tblabtesthistory where tblabtesthistory.FiscalYear='"+fiscalyear+"' and tblabtesthistory.Cmonth='"+cmonth+"' and tblabtesthistory.TestId='"+testId+"' and tblabtesthistory.type=1  and tblabtesthistory.labId='"+labId+"'";
				System.out.println(deleteQ);
				session.createSQLQuery(deleteQ).executeUpdate();
				String deleteQT="delete from tblabtesthistory where tblabtesthistory.FiscalYear='"+fiscalyear+"' and tblabtesthistory.Cmonth='"+cmonth+"'  and  tblabtesthistory.type=2 and tblabtesthistory.labId='"+labId+"'";
				System.out.println(deleteQT);
				session.createSQLQuery(deleteQT).executeUpdate();
				DSN.clear();
				DNameOfPericular.clear();
				DNameOfRate.clear();


				ArrayList TestIdList=new ArrayList();
				ArrayList TestTypeList=new ArrayList();
				sql="select TestId,Type from tblabtesthistory where tblabtesthistory.FiscalYear='"+fiscalyear+"' and tblabtesthistory.Cmonth='"+cmonth+"'  and tblabtesthistory.labId='"+labId+"' and tblabtesthistory.type='1' order by type asc";
				List<?> list1 = session.createSQLQuery(sql).list();
				for(Iterator<?> iter = list1.iterator(); iter.hasNext();)
				{	
					Object[] element = (Object[]) iter.next();
					TestIdList.add(element[0].toString());
					TestTypeList.add(element[1].toString());

				}

				for(int a=0;a<TestIdList.size();a++) {
					ArrayList particularId=new ArrayList();
					ArrayList NameOfPericular=new ArrayList();
					ArrayList NameOfRate=new ArrayList();
					ArrayList NameOfQty=new ArrayList();
					int count=0;

					sql="select ParticularId,(select ParticularName from TbTestParticular where ParticularId=ParticularRefId) as ParticularName,Rate,qty from TbTestPerticularName where TestId='"+TestIdList.get(a).toString()+"'";
					List<?> list2 = session.createSQLQuery(sql).list();
					for(Iterator<?> iter = list2.iterator(); iter.hasNext();)
					{	
						Object[] element = (Object[]) iter.next();
						particularId.add(element[0].toString());
						NameOfPericular.add(element[1].toString());
						NameOfRate.add(element[2].toString());
						NameOfQty.add(element[3].toString());
						count++;
					}

					for(int b=0;b<count;b++){

						int check=0;
						sql="select tblabtesthistory.TestName from tblabtesthistory where tblabtesthistory.FiscalYear='"+fiscalyear+"'  and tblabtesthistory.cmonth='"+cmonth+"' and tblabtesthistory.TestName='"+NameOfPericular.get(b).toString()+"' and tblabtesthistory.labId='"+labId+"'";
						List<?> list3 = session.createSQLQuery(sql).list();
						for(Iterator<?> iter = list3.iterator(); iter.hasNext();)
						{	
							check=1;
							break;
						}

						if(check==0){
							String query ="insert into tblabtesthistory (labId,regNo,TestId,testName,qty,rate,discount,ResultStatus,RefundStatus,type,counter,date,entryTime,createBy,FiscalYear,cmonth) values ('"+labId+"','"+regNo+"','"+particularId.get(b).toString()+"','"+NameOfPericular.get(b).toString()+"','"+NameOfQty.get(b).toString()+"','"+df.format(Double.parseDouble(NameOfRate.get(b).toString()))+"','0','NOT DONE','0','2','"+counter+"',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'"+userId+"','"+fiscalyear+"','"+cmonth+"')";
							System.out.println(query);
							session.createSQLQuery(query).executeUpdate();

							String Udquery ="insert into tbUdlabtesthistory (labId,regNo,TestId,testName,qty,rate,discount,ResultStatus,RefundStatus,type,counter,date,entryTime,createBy,FiscalYear,cmonth) values ('"+labId+"','"+regNo+"','"+particularId.get(b).toString()+"','"+NameOfPericular.get(b).toString()+"','"+NameOfQty.get(b).toString()+"','"+df.format(Double.parseDouble(NameOfRate.get(b).toString()))+"','0','NOT DONE','0','2','"+counter+"',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'"+userId+"','"+fiscalyear+"','"+cmonth+"')";
							System.out.println(Udquery);
							session.createSQLQuery(Udquery).executeUpdate();
						}
					}									
					particularId.clear();
					NameOfPericular.clear();
					NameOfRate.clear();
					NameOfQty.clear();
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
	public boolean isLabBillExist(LabBilling v) {
		boolean flag=false;
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		try{
			tx=session.getTransaction();
			tx.begin();

			String sql="select labId,FiscalYear from TbLabPatient where labId='"+v.getLabId()+"' and FiscalYear='"+v.getFiscalyear()+"'";

			List<?> list = session.createSQLQuery(sql).list();
			if(list.size()>0)
				return flag=true;
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
		return flag;
	}

	@Override
	public boolean refundTransaction(LabBilling v) {
		// TODO Auto-generated method stub
		boolean flag=false;
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		try{
			tx=session.getTransaction();
			tx.begin();

			int exist=0;
			String sql="select labId,FiscalYear as fis from TbLabPatient where  FiscalYear='"+v.getFiscalyear()+"' and CMonth='"+v.getcMonth()+"' and labId='"+v.getLabId()+"'";

			List<?> list = session.createSQLQuery(sql).list();
			if(list.size()>0)
				exist=1;

			System.out.println("exist "+exist);
			System.out.println("Result "+v.getResultList());


			if(exist==1) {
				double RefundTestRate=0,RefundTestAmount=0,RefundAmount=0;
				int refund=0;
				String resultValue=v.getResultList().substring(v.getResultList().indexOf("[")+1, v.getResultList().indexOf("]"));
				StringTokenizer resultToken=new StringTokenizer(resultValue,",");
				while(resultToken.hasMoreTokens()) {
					String firstToken=resultToken.nextToken();
					StringTokenizer valueToken=new StringTokenizer(firstToken,"*");
					while(valueToken.hasMoreTokens()) {
						String autoId=valueToken.nextToken();
						String qty=valueToken.nextToken();
						String rate=valueToken.nextToken();
						String payable=valueToken.nextToken();
						String refundstatus=valueToken.nextToken();

						if(refundstatus.equals("1")) {
							String Udquery ="update TbLabTestHistory set RefundStatus='1' where labId='"+v.getLabId()+"' and FiscalYear='"+v.getFiscalyear()+"' and autoId='"+autoId+"'";
							System.out.println(Udquery);
							session.createSQLQuery(Udquery).executeUpdate();

							double discount=0;
							sql="select discount from tblabtesthistory where autoId='"+autoId+"'";
							List<?> list2 = session.createSQLQuery(sql).list();
							System.out.println("list2 "+list2.size());
							for(Iterator<?> iter = list2.iterator(); iter.hasNext();)
							{		
								discount=Double.parseDouble(iter.next().toString());
								break;
							}

							double trate=(Double.parseDouble(qty)*Double.parseDouble(rate));
							double discountAmt=trate*discount/100;

							RefundTestRate=RefundTestRate+(Double.parseDouble(qty)*Double.parseDouble(rate)-discountAmt);


							refund++;
						}	

					}
				}


				if(refund!=0){

					double MainTestAmount=0;
					sql="SELECT ISNULL(sum(qty*rate),0) as MainTestAmount FROM tblabtesthistory WHERE labId='"+v.getLabId()+"' AND FiscalYear='"+v.getFiscalyear()+"' and type=1 and RefundStatus='0' and discountAllow='1'";
					List<?> list2 = session.createSQLQuery(sql).list();
					System.out.println("list2 "+list2.size());
					for(Iterator<?> iter = list2.iterator(); iter.hasNext();)
					{		
						MainTestAmount=MainTestAmount+Double.parseDouble(iter.next().toString());
					}

					System.out.println("MainTestAmount "+MainTestAmount);
					System.out.println("Percentdiscount "+v.getPercentdiscount());
					System.out.println("Manaualdiscount "+v.getManualdiscount());
					System.out.println("Advance "+v.getAdvance());
					System.out.println("Refund "+v.getRefund());
					System.out.println("RefundTestRate "+RefundTestRate);




					double TotalCharge=Double.parseDouble(v.getTotalCharge())-RefundTestRate;
					double PerCentDis=MainTestAmount*Double.parseDouble(v.getPercentdiscount())/100;
					double TotalDiscount=PerCentDis+Double.parseDouble(v.getManualdiscount());
					double TotalPayble=(TotalCharge-TotalDiscount)<0?0:(TotalCharge-TotalDiscount);
					double Paid=Double.parseDouble(v.getAdvance());
					double RefundAmt=Double.parseDouble(v.getRefund());
					double tPaid=Paid-RefundAmt;
					double Due=TotalPayble-tPaid;
					double PerCent=0;
					double Manual=0;
					double BeforeManualDiscount=Double.parseDouble(v.getManualdiscount().toString().isEmpty()?"0":v.getManualdiscount().toString());
					double BeforeTotalCharge=Double.parseDouble(v.getTotalCharge().toString().isEmpty()?"0":v.getTotalCharge().toString());

					//double RefundAmt=Double.parseDouble(txtRefund.getText().trim().toString());

					System.out.println("Paid "+Paid);
					System.out.println("TotalPayble "+TotalPayble);
					System.out.println("Due "+Due);
					if(Paid>=TotalPayble){
						RefundAmount=RefundTestRate;
					}
					else if(TotalPayble==0){
						RefundAmount=tPaid;
					}
					else if(Due>RefundTestRate){
						RefundAmount=0;
					}
					if(TotalCharge==0){
						PerCent=0;
						Manual=0;
						TotalDiscount=0;
					}
					else{
						Manual=Double.parseDouble(v.getManualdiscount().toString().isEmpty()?"0":v.getManualdiscount().toString());
						PerCent=Double.parseDouble(v.getPercentdiscount().toString().isEmpty()?"0":v.getPercentdiscount().toString());
					}


					String UpdateMainBill="update TbLabPatient set PercentDiscount='"+PerCent+"',Discount='"+PerCentDis+"',ManualDiscount='"+Manual+"',totalDiscount='"+TotalDiscount+"',TotalCharge='"+TotalCharge+"',TotalPayable='"+TotalPayble+"' ,Paid='"+Paid+"',entryTime=CURRENT_TIMESTAMP,CreateBy='"+v.getUserId()+"' where FiscalYear='"+v.getFiscalyear()+"' and labId='"+v.getLabId()+"'";
					System.out.println(UpdateMainBill);
					session.createSQLQuery(UpdateMainBill).executeUpdate();

					System.out.println("RefundAmount "+RefundAmount);

					if(RefundAmount>0) {
						String paysql="insert into TbLabPaymentHistory (LabId,Cash,Card,BillType,PaymentStatus,date,EntryTime,createBy,FiscalYear,CMonth,PaymentType) values ('"+v.getLabId()+"','"+RefundAmount+"','0','"+v.getBillType()+"','Refund',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'"+v.getUserId()+"','"+v.getFiscalyear()+"','"+v.getcMonth()+"','New Refund') ";
						session.createSQLQuery(paysql).executeUpdate();
					}


					/*
					StringTokenizer resultToken1=new StringTokenizer(resultValue,",");
					while(resultToken1.hasMoreTokens()) {
						String firstToken=resultToken1.nextToken();
						StringTokenizer valueToken=new StringTokenizer(firstToken,"*");
						while(valueToken.hasMoreTokens()) {
							String autoId=valueToken.nextToken();
							String qty=valueToken.nextToken();
							String rate=valueToken.nextToken();
							String payable=valueToken.nextToken();
							String refundstatus=valueToken.nextToken();

							if(refundstatus.equals("1")) {
								String Udquery ="update TbLabTestHistory set RefundStatus='1' where labId='"+v.getLabId()+"' and FiscalYear='"+v.getFiscalyear()+"' and autoId='"+autoId+"'";
								System.out.println(Udquery);
								session.createSQLQuery(Udquery).executeUpdate();
								refund++;
							}	

						}
					}*/

					flag=true;
				}
			}


			tx.commit();

			return flag;
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
		return flag;
	}




	@Override
	public List<ConsultantCreate> getConsultantList() {
		// TODO Auto-generated method stub
		String sql="";
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		List<ConsultantCreate> datalist=new ArrayList<ConsultantCreate>();
		try{
			tx=session.getTransaction();
			tx.begin();

			sql="select id,name, designation, isnull(line1,'') as line1, isnull(line2,'') as line2, isnull(line3,'') as line3, isnull(line4,'') as line4, isnull(line5,'') as line5 from tbLabInchargeConsultantDegree";
			List<?> list = session.createSQLQuery(sql).list();
			for(Iterator<?> iter = list.iterator(); iter.hasNext();){	
				Object[] element = (Object[]) iter.next();
				datalist.add(new ConsultantCreate(element[0].toString(),element[1].toString(),element[2].toString(),element[3].toString(),element[4].toString(),element[5].toString(),element[6].toString(),element[7].toString()));
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
		return datalist;
	}

	@Override
	public boolean isConsultantExist(ConsultantCreate v) {
		// TODO Auto-generated method stub
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		try{
			tx=session.getTransaction();
			tx.begin();

			String sql="select name from tbLabInchargeConsultantDegree where id!='"+v.getId()+"' and name='"+v.getConsultantName()+"'";
			List<?> list = session.createSQLQuery(sql).list();
			if(list.size()>0) {
				return true;
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
		return false;
	}

	@Override
	public boolean saveConsultant(ConsultantCreate v) {
		// TODO Auto-generated method stub
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		try{
			tx=session.getTransaction();
			tx.begin();

			String sql="insert into tbLabInchargeConsultantDegree (id, Name, designation, line1, line2, line3, line4, line5,entryTime, entryBy) values ('"+v.getId()+"','"+v.getConsultantName()+"','"+v.getDesignation()+"','"+v.getLine1()+"','"+v.getLine2()+"','"+v.getLine3()+"','"+v.getLine4()+"','"+v.getLine5()+"', CURRENT_TIMESTAMP, '"+v.getUserId()+"')";
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
	public boolean editConsultant(ConsultantCreate v) {
		// TODO Auto-generated method stub
		Session session=HibernateUtil.openSession();
		Transaction tx=null;

		try{
			tx=session.getTransaction();
			tx.begin();

			String sql="Update tbLabInchargeConsultantDegree set id='"+v.getId()+"', Name='"+v.getConsultantName()+"', designation='"+v.getDesignation()+"',line1='"+v.getLine1()+"', line2='"+v.getLine2()+"', line3='"+v.getLine3()+"', line4='"+v.getLine4()+"', line5='"+v.getLine5()+"' where id='"+v.getId()+"'";
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
	public String getMaxConsultantId() {
		// TODO Auto-generated method stub
		String id="";
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		try{
			tx=session.getTransaction();
			tx.begin();

			String sql="select (ISNULL(max(id),0)+1)as id from tbLabInchargeConsultantDegree ";
			List<?> list = session.createSQLQuery(sql).list();

			for(Iterator<?> iter = list.iterator(); iter.hasNext();) {	
				id = iter.next().toString();
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
		return id;
	}

	@Override
	public boolean DeleteSubTest(String subTestId) {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;

		try{
			tx=session.getTransaction();
			tx.begin();

			String sql="delete from TbSubTestName where SubTestId='"+subTestId+"'";
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
	public boolean ImmunologySaveEvent(LabResult v) {
		String result=v.getResult().toString();

		result=result.substring(1,result.length()-1);

		StringTokenizer resulttoken=new StringTokenizer(result,",");
		String eachToken="";
		String eachResult="";
		String eachTestId="";
		String eachMaintTestId="";
		int eachSort=0;
		int submit=0;
		int update=0;
		while(resulttoken.hasMoreTokens()) {
			eachToken=resulttoken.nextToken().toString().trim();

			System.out.println("eachToken "+eachToken);

			StringTokenizer sorttoken=new StringTokenizer(eachToken,"@");
			while(sorttoken.hasMoreTokens()) {
				eachMaintTestId=sorttoken.nextToken().toString().trim();

				System.out.println("eachMaintTestId "+eachMaintTestId);
				eachTestId=sorttoken.nextToken().toString().trim();
				eachResult=sorttoken.nextToken().toString().trim();
				eachResult=eachResult.replace("~", "");
				eachSort=Integer.parseInt(sorttoken.nextToken().toString().trim());

				if(!checkLabReport(Integer.parseInt(eachTestId),v.getLabbill(),eachMaintTestId,v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {
					if(!eachResult.equals("") && insertdata(Integer.parseInt(eachTestId),eachResult,eachMaintTestId,v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),eachSort,v)) {
						submit++;
					}
				}
				else {
					updatedata(Integer.parseInt(eachTestId),eachResult,eachMaintTestId,v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),eachSort,v);
					submit++;
				}

				if(submit!=0) {
					Session session=HibernateUtil.openSession();
					Transaction tx=null;
					try{
						tx=session.getTransaction();
						tx.begin();

						String sql="update TbLabTestHistory set ResultStatus='1' where LabId='"+v.getLabbill()+"' and  testId='"+eachMaintTestId+"'";

						session.createSQLQuery(sql).executeUpdate();
						tx.commit();
						update++;

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
				}
			}
		}


		if(update!='0') {
			return true;
		}

		return false;
	}

	@Override
	public List<LabResult> setStoolSaveData(String testId, String labbill, String fiscalyear,String cMonth) {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		List<LabResult> query=new ArrayList<LabResult>();
		try{
			tx=session.getTransaction();
			tx.begin();

			List<?> list = session.createSQLQuery("select value,rid,LabInchargeId,Doctor1Id,Doctor2Id,machineId,titleId  from tblabreportvalue where FiscalYear='"+fiscalyear+"' and cMonth='"+cMonth+"' and HeadId='8' and LabId='"+labbill+"' and testId='"+testId+"'"
					+ "").list();

			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				Object[] element = (Object[]) iter.next();



				query.add(new LabResult(element[0].toString(),element[1].toString(),element[2].toString(),element[3].toString(),element[4].toString(),element[5].toString(),element[6].toString()));
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
	public boolean hormoneConfirmatortySaveEvent(LabResult v) {
		int submit=0;
		//Cut Of Value
		if(!checkLabReport(261,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {

			if(!v.getHorCutValue().toString().isEmpty() && insertdata(261,v.getHorCutValue(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(261,v.getHorCutValue(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		if(!checkLabReport(262,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {
			if(!v.getHorPatientSampleCount().toString().isEmpty() && insertdata(262,v.getHorPatientSampleCount(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(262,v.getHorPatientSampleCount(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		if(!checkLabReport(263,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {
			if(!v.getHorImpression().toString().isEmpty() && insertdata(263,v.getHorImpression(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(263,v.getHorImpression(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		if(submit!=0) {
			Session session=HibernateUtil.openSession();
			Transaction tx=null;
			try{
				tx=session.getTransaction();
				tx.begin();

				String sql="update TbLabTestHistory set ResultStatus='1' where LabId='"+v.getLabbill()+"' and  testId='"+v.getTestid()+"'";

				session.createSQLQuery(sql).executeUpdate();
				tx.commit();

				return true;
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
		}

		return false;
	}

	@Override
	public List<LabResult> getConfirmatorSaveData(String testIdlist, String labbill, String headId,String fiscalyear,String cMonth) {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		List<LabResult> query=new ArrayList<LabResult>();
		try{
			tx=session.getTransaction();
			tx.begin();

			String TestId=testIdlist=testIdlist.substring(1,testIdlist.length()-1);

			List<?> list = session.createSQLQuery("select value,rid,LabInchargeId,Doctor1Id,Doctor2Id,machineId,titleId  from tblabreportvalue where FiscalYear='"+fiscalyear+"' and cMonth='"+cMonth+"' and HeadId='"+headId+"' and LabId='"+labbill+"' and testId='"+TestId+"'"
					+ "").list();

			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				Object[] element = (Object[]) iter.next();


				query.add(new LabResult(element[0].toString(),element[1].toString(),element[2].toString(),element[3].toString(),element[4].toString(),element[5].toString(),element[6].toString()));
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

		return query;
	}

	@Override
	public boolean immunologyConfirmatortySaveEvent(LabResult v) {
		int submit=0;
		//Cut Of Value
		if(!checkLabReport(264,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {

			if(!v.getImmCutValue().toString().isEmpty() && insertdata(264,v.getImmCutValue(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(264,v.getImmCutValue(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		if(!checkLabReport(265,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {
			if(!v.getImmPatientSampleCount().toString().isEmpty() && insertdata(265,v.getImmPatientSampleCount(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(265,v.getImmPatientSampleCount(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		if(!checkLabReport(266,v.getLabbill(),v.getTestid(),v.getHeadid(),v.getLabfiscalyear(),v.getCmonth())) {
			if(!v.getImmImpression().toString().isEmpty() && insertdata(266,v.getImmImpression(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v)) {
				submit++;
			}
		}
		else {
			updatedata(266,v.getImmImpression(),v.getTestid(),v.getHeadid(),v.getLabbill(),v.getUserId(),v.getFiscalyear(),0,v);
			submit++;
		}

		if(submit!=0) {
			Session session=HibernateUtil.openSession();
			Transaction tx=null;
			try{
				tx=session.getTransaction();
				tx.begin();

				String sql="update TbLabTestHistory set ResultStatus='1' where LabId='"+v.getLabbill()+"' and  testId='"+v.getTestid()+"'";

				session.createSQLQuery(sql).executeUpdate();
				tx.commit();

				return true;
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
		}

		return false;
	}

	@Override
	public List<LabResult> setMicrobiologSaveData(String testId, String labbill, String fiscalyear,String cMonth) {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		List<LabResult> query=new ArrayList<LabResult>();
		try{
			tx=session.getTransaction();
			tx.begin();

			List<?> list = session.createSQLQuery("select value,rid,LabInchargeId,Doctor1Id,Doctor2Id,machineId,titleId  from tblabreportvalue where FiscalYear='"+fiscalyear+"' and cMonth='"+cMonth+"' and HeadId='6' and LabId='"+labbill+"' and testId='"+testId+"'"
					+ "").list();

			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				Object[] element = (Object[]) iter.next();



				query.add(new LabResult(element[0].toString(),element[1].toString(),element[2].toString(),element[3].toString(),element[4].toString(),element[5].toString(),element[6].toString()));
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
	public List<MachineCreate> MachineList() {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		List<MachineCreate> query=new ArrayList<MachineCreate>();
		try{
			tx=session.getTransaction();
			tx.begin();

			List<?> list = session.createSQLQuery("select a.MachineId,a.MachineName,a.GroupId,(select GroupName from TbLabTestGroup where HeadId=a.GroupId) as GroupName from TbMachineInfo a order by a.GroupId").list();

			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				Object[] element = (Object[]) iter.next();

				query.add(new MachineCreate(element[0].toString(),element[1].toString(),element[2].toString(),element[3].toString()));
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
	public boolean isMachineExist(MachineCreate v) {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		try{
			tx=session.getTransaction();
			tx.begin();

			String sql="select MachineName from TbMachineInfo where GroupId='"+v.getGroupId()+"' and MachineId!='"+v.getMachineId()+"' and MachineName='"+v.getMachineName()+"'";
			List<?> list = session.createSQLQuery(sql).list();
			if(list.size()>0) {
				return true;
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
		return false;
	}

	@Override
	public boolean saveMachineInfo(MachineCreate v) {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		try{
			tx=session.getTransaction();
			tx.begin();

			String sql="insert into TbMachineInfo (MachineName, GroupId, UserId, Entrytime) values ('"+v.getMachineName()+"','"+v.getGroupId()+"','"+v.getUserId()+"',CURRENT_TIMESTAMP)";
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
	public boolean editMachineInfo(MachineCreate v) {
		// TODO Auto-generated method stub
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		try{
			tx=session.getTransaction();
			tx.begin();

			String sql="update TbMachineInfo set MachineName='"+v.getMachineName()+"',GroupId='"+v.getGroupId()+"',UserId='"+v.getUserId()+"',Entrytime=CURRENT_TIMESTAMP where MachineId='"+v.getMachineId()+"'";
			session.createSQLQuery(sql).executeUpdate();
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
	public boolean isLabReportTitleExist(LabReportCreate v) {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		try{
			tx=session.getTransaction();
			tx.begin();

			String sql="select TitleName from TbLabReportTitleInfo where GroupId='"+v.getGroupId()+"' and TitleId!='"+v.getTitleId()+"' and TitleName='"+v.getTitleName()+"'";
			List<?> list = session.createSQLQuery(sql).list();
			if(list.size()>0) {
				return true;
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
		return false;
	}

	@Override
	public boolean saveLabReportTitleInfo(LabReportCreate v) {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		try{
			tx=session.getTransaction();
			tx.begin();

			String sql="insert into TbLabReportTitleInfo (TitleName, GroupId, UserId, Entrytime) values ('"+v.getTitleName()+"','"+v.getGroupId()+"','"+v.getUserId()+"',CURRENT_TIMESTAMP)";
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
	public List<LabReportCreate> LabReportTitleList() {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		List<LabReportCreate> query=new ArrayList<LabReportCreate>();
		try{
			tx=session.getTransaction();
			tx.begin();

			List<?> list = session.createSQLQuery("select a.TitleId,a.TitleName,a.GroupId,(select GroupName from TbLabTestGroup where HeadId=a.GroupId) as GroupName from TbLabReportTitleInfo a order by a.GroupId").list();

			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				Object[] element = (Object[]) iter.next();

				query.add(new LabReportCreate(element[0].toString(),element[1].toString(),element[2].toString(),element[3].toString()));
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
	public boolean editLabReportTitleInfo(LabReportCreate v) {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		try{
			tx=session.getTransaction();
			tx.begin();

			String sql="update TbLabReportTitleInfo set TitleName='"+v.getTitleName()+"',GroupId='"+v.getGroupId()+"',UserId='"+v.getUserId()+"',Entrytime=CURRENT_TIMESTAMP where titleId='"+v.getTitleId()+"'";
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
	public List<TestGroup> getPathologyGroup() {
		// TODO Auto-generated method stub
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		List<TestGroup> query=new ArrayList<TestGroup>();
		try{
			tx=session.getTransaction();
			tx.begin();

			List<?> list = session.createSQLQuery("select HeadId,GroupName from TbLabTestGroup order by GroupName").list();

			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				Object[] element = (Object[]) iter.next();

				query.add(new TestGroup(element[0].toString(),element[1].toString()));
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
	public boolean deleteParticularItem(String particularId) {
		// TODO Auto-generated method stub
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		try{
			tx=session.getTransaction();
			tx.begin();

			String sql="delete from  TbTestPerticularName where particularId='"+particularId+"'";
			session.createSQLQuery(sql).executeUpdate();
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
	public boolean IsValidTest(LabBilling v) {
		// TODO Auto-generated method stub

		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		try{
			tx=session.getTransaction();
			tx.begin();

			List<?> list = session.createSQLQuery("select Rate from TbTestName where TestName='"+v.getTestId()+"'").list();

			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
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
	public boolean billAutoSave(LabBilling v) {
		// TODO Auto-generated method stub
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		try{
			tx=session.getTransaction();
			tx.begin();

			System.out.println("perDiscountTk"+v.getPerdiscount_taka());
			System.out.println("mDiscount"+v.getManualdiscount());

			double totalDiscount=Double.parseDouble(v.getPerdiscount_taka())+Double.parseDouble(v.getManualdiscount());


			String sql="update TbLabPatient set "

					+ "PercentDiscount='"+v.getPercentdiscount()+"',"
					+ "Discount='"+v.getPerdiscount_taka()+"',"
					+ "ManualDiscount='"+v.getManualdiscount()+"',"
					+ "totalDiscount='"+totalDiscount+"',"
					+ "TotalCharge='"+v.getTotalamount()+"',"
					+ "TotalPayable='"+v.getTotalpayable()+"' where fiscalyear='"+v.getFiscalyear()+"' and labId='"+v.getLabId()+"' and cmonth='"+v.getcMonth()+"'";

			System.out.println(sql);
			session.createSQLQuery(sql).executeUpdate();
			tx.commit();
			return true;

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

}
