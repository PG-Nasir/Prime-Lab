package pg.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Repository;


import pg.OrganizationModel.OrganizationInfo;
import pg.SettingModel.DoctorCreate;
import pg.SettingModel.Employee;
import pg.SettingModel.MemberCreate;
import pg.SettingModel.ProjectFeature;
import pg.model.Menu;
import pg.model.MenuInfo;
import pg.model.Module;
import pg.model.ModuleInfo;
import pg.model.ModuleWiseMenu;
import pg.model.ModuleWiseMenuSubMenu;
import pg.model.Password;
import pg.model.RoleManagement;
import pg.model.SubMenuInfo;
import pg.model.Ware;
import pg.model.WareInfo;
import pg.share.FormId;
import pg.share.HibernateUtil;

@Repository
public class SettingDAOImpl implements SettingDAO {


	@Override
	public List<Module> getAllModuleName() {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		List<Module> query=new ArrayList<Module>();
		try{
			tx=session.getTransaction();
			tx.begin();


			List<?> list = session.createSQLQuery("select id,name from Tbmodule").list();

			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				Object[] element = (Object[]) iter.next();
				query.add(new Module(Integer.parseInt(element[0].toString()),element[1].toString(),0));
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
	public List<WareInfo> getAllWareName() {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		List<WareInfo> query=new ArrayList<WareInfo>();
		try{
			tx=session.getTransaction();
			tx.begin();


			List<?> list = session.createSQLQuery("select id,name from ware").list();

			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				Object[] element = (Object[]) iter.next();
				query.add(new WareInfo(Integer.parseInt(element[0].toString()),element[1].toString()));
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

	public boolean  addWare(Ware w) {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		List<Menu> query=new ArrayList<Menu>();
		try{
			tx=session.getTransaction();
			tx.begin();

			String sql="insert into ware ("
					+ "name,"
					+ "phone,"
					+ "email,"
					+ "vat,"
					+ "theme,"
					+ "user,"
					+ "entrytime"
					+ ") values ("
					+ "'"+w.getName()+"',"
					+ "'"+w.getPhone()+"',"
					+ "'"+w.getEmail()+"',"
					+ "'"+w.getVat()+"',"
					+ "'"+w.getTheme()+"',"
					+ "'"+w.getUser()+"',NOW()"
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



	public boolean  addModule(ModuleInfo v) {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		try{
			tx=session.getTransaction();
			tx.begin();

			String sql="insert into Tbmodule ("
					+ "name,"
					+ "ware,"
					+ "user,"
					+ "status,"
					+ "trash,"
					+ "entrytime"
					+ ") values ("
					+ "'"+v.getName()+"',"
					+ "'"+v.getWare()+"',"
					+ "'"+v.getUser()+"',"
					+ "'"+v.getActive()+"','0',"
					+ "NOW()"
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

	public boolean  addMenu(MenuInfo v) {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		try{
			tx=session.getTransaction();
			tx.begin();

			String sql="insert into Tbmenu ("
					+ "name,"
					+ "module,"
					+ "ware,"
					+ "user,"
					+ "trash,"
					+ "entrytime"
					+ ") values ("
					+ "'"+v.getName()+"',"
					+ "'"+v.getModule()+"',"
					+ "(select ware from module where id='"+v.getModule()+"'),"
					+ "'"+v.getUser()+"','0',"
					+ "NOW()"
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


	public boolean  addSubMenu(SubMenuInfo v) {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		try{
			tx=session.getTransaction();
			tx.begin();

			String sql="insert into TbSubMenu ("
					+ "name,"
					+ "links,"
					+ "root,"
					+ "module,"
					+ "ware,"
					+ "user,"
					+ "trash,"
					+ "entrytime"
					+ ") values ("
					+ "'"+v.getName()+"',"
					+ "'"+v.getLinks()+"',"
					+ "'"+v.getMenu()+"',"
					+ "'"+v.getModule()+"',"
					+ "(select ware from Tbmodule where id='"+v.getModule()+"'),"
					+ "'"+v.getUser()+"','0',"
					+ "NOW()"
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
	public List<ModuleWiseMenuSubMenu> getAllModuleWiseMenuSubMenuName(int user,String menulist) {

		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		List<ModuleWiseMenuSubMenu> query=new ArrayList<ModuleWiseMenuSubMenu>();
		try{
			tx=session.getTransaction();
			tx.begin();


			//String sql="select a.module as moduleid,a.id as menuId,b.id as submenuId,a.name,b.name as SubMenu from Tbmenu a join TbSubMenu b on a.id=b.root where b.module in("+menulist+")  group by b.module,a.name,b.name";
			String sql="select a.module as moduleid,a.id as menuId,b.id as submenuId,a.name,b.name as SubMenu from Tbmenu a join TbSubMenu b on a.id=b.root where a.module in("+menulist+")  order by a.module ";
			List<?> list = session.createSQLQuery(sql).list();

			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				Object[] element = (Object[]) iter.next();
				query.add(new ModuleWiseMenuSubMenu(Integer.parseInt(element[0].toString()),Integer.parseInt(element[1].toString()),Integer.parseInt(element[2].toString()), "",element[3].toString(),element[4].toString(),""));
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
	public List<ModuleWiseMenuSubMenu> getAllModuleWiseSubmenu() {

		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		List<ModuleWiseMenuSubMenu> query=new ArrayList<ModuleWiseMenuSubMenu>();
		try{
			tx=session.getTransaction();
			tx.begin();


			String sql="select module,root,id,(select name from Tbmodule where id=TbSubMenu.module) as ModuleName,(select name from Tbmenu where id=root) as head,name,links from TbSubMenu order by root,module";
			List<?> list = session.createSQLQuery(sql).list();

			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				Object[] element = (Object[]) iter.next();
				query.add(new ModuleWiseMenuSubMenu(Integer.parseInt(element[0].toString()),Integer.parseInt(element[1].toString()),Integer.parseInt(element[2].toString()), element[3].toString(),element[4].toString(),element[5].toString(),element[6].toString()));
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

	public boolean  addUser(Password v) {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		try{
			tx=session.getTransaction();
			tx.begin();

			String newUserId=getMaxId();

			String modulelist=v.getSelectedItemsModule();
			modulelist=modulelist.replace("[", "");
			modulelist=modulelist.replace("]", "");
			System.out.println("modulelist "+modulelist);
			String accesslit=v.getAccesslist();
			accesslit=accesslit.replace("[", "");
			accesslit=accesslit.replace("]", "");
			System.out.println("list "+accesslit);

			String warelist=v.getSelectedItemsWare();
			warelist=warelist.replace("[", "");
			warelist=warelist.replace("]", "");
			System.out.println("warelist "+warelist);

			String sql="insert into Tblogin ("
					+ "id,"
					+ "password,"
					+ "fullname,"
					+ "username,"
					+ "type,"
					+ "factoryId,"
					+ "departmentId,"
					+ "active,"
					+ "createby,"
					+ "entrytime"
					+ ") values ("
					+ "'"+newUserId+"',"
					+ "'"+v.getPassword()+"',"
					+ "'"+v.getFullName()+"',"
					+ "'"+v.getUser()+"',"
					+ "'"+v.getType()+"',"
					+ "'"+v.getFactoryId()+"',"
					+ "'"+v.getDepartmentId()+"',"
					+ "'"+v.getActive()+"',"
					+ "'"+v.getUserId()+"',"
					+ "CURRENT_TIMESTAMP"
					+ ")";

			session.createSQLQuery(sql).executeUpdate();


			StringTokenizer s=new StringTokenizer(accesslit,",");
			while(s.hasMoreElements()) {
				String a=s.nextToken().trim();
				StringTokenizer s2=new StringTokenizer(a,":");
				while(s2.hasMoreElements()) {
					String moduleId=s2.nextToken();
					String headId=s2.nextToken();
					String subId=s2.nextToken();
					String add=s2.nextToken();
					String edit=s2.nextToken();
					String delete=s2.nextToken();
					String sqlpass="insert into Tbuseraccess ("
							+ "userId,"
							+ "head,"
							+ "sub,"
							+ "module,"
							+ "entry,"
							+ "modifty,"
							+ "del,"
							+ "trash,"
							+ "entrytime"
							+ ") "
							+ "values ("
							+ "'"+newUserId+"',"
							+ "'"+headId+"',"
							+ "'"+subId+"',"
							+ "'"+moduleId+"',"
							+ "'"+add+"',"
							+ "'"+edit+"',"
							+ "'"+delete+"',"
							+ "'0',"
							+ "CURRENT_TIMESTAMP"
							+ ")";

					session.createSQLQuery(sqlpass).executeUpdate();

				}
			}

			StringTokenizer s2=new StringTokenizer(modulelist,",");
			while(s2.hasMoreElements()) {
				String moduletoken=s2.nextToken();

				StringTokenizer s3=new StringTokenizer(moduletoken,":");
				while(s3.hasMoreElements()) {
					String wareId=s3.nextToken();
					String moduleId=s3.nextToken();
					String sqlpass="insert into Tbuser_access_module ("

						+ "userId,"
						+ "module_id,"
						+ "trash,"
						+ "entrytime"
						+ ") "
						+ "values ("
						+ "'"+newUserId+"',"
						+ "'"+moduleId+"',"
						+ "'0',"
						+ "CURRENT_TIMESTAMP"
						+ ")";

					session.createSQLQuery(sqlpass).executeUpdate();
				}


			}

			StringTokenizer s3=new StringTokenizer(warelist,",");
			while(s3.hasMoreElements()) {
				String wareId=s3.nextToken();
				String sqlware="insert into tbuser_access_ware ("
						+ "user,"
						+ "ware,"
						+ "trash,"
						+ "entrytime"
						+ ") "
						+ "values ("
						+ "'"+newUserId+"',"
						+ "'"+wareId+"',"
						+ "'0',"
						+ "NOW()"
						+ ")";

				session.createSQLQuery(sqlware).executeUpdate();
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

	private String getMaxId() {
		String id="";

		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		try{
			tx=session.getTransaction();
			tx.begin();


			String sql="select (ISNULL(max(id),0)+1)as id from Tblogin ";
			List<?> list = session.createSQLQuery(sql).list();

			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				id = iter.next().toString();
				System.out.println("id "+id);

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
	public List<Menu> getAllModuleWiseMenu(int i) {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		List<Menu> query=new ArrayList<Menu>();
		try{
			tx=session.getTransaction();
			tx.begin();


			List<?> list = session.createSQLQuery("select id,name from Tbmenu where module='"+i+"'").list();

			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				Object[] element = (Object[]) iter.next();
				query.add(new Menu(Integer.parseInt(element[0].toString()),element[1].toString(),"",""));
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
	public List<ModuleWiseMenu> getAllModuleWiseMenu() {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		List<ModuleWiseMenu> query=new ArrayList<ModuleWiseMenu>();
		try{
			tx=session.getTransaction();
			tx.begin();


			List<?> list = session.createSQLQuery("select id,(select name from Tbmodule where id=Tbmenu.module) as Module,name from Tbmenu order by module").list();

			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				Object[] element = (Object[]) iter.next();
				query.add(new ModuleWiseMenu(Integer.parseInt(element[0].toString()),element[1].toString(),element[2].toString()));
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
	public List<OrganizationInfo> getOrganization() {
		// TODO Auto-generated method stub
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		List<OrganizationInfo> dataList=new ArrayList<OrganizationInfo>();

		try{
			tx=session.getTransaction();
			tx.begin();

			String sql="select organizationId, organizationName, organizationContact, organizationAddress from tbOrganizationInfo";
			List<?> list = session.createSQLQuery(sql).list();
			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				Object[] element = (Object[]) iter.next();

				dataList.add(new OrganizationInfo(element[0].toString(),element[1].toString(),element[2].toString(),element[3].toString()));
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
	public boolean editOrganization(OrganizationInfo v) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			tx.begin();

			String sql = "update tbOrganizationInfo set organizationName='"+v.getOrganizationName()+"', organizationContact='"+v.getOrganizationContact()+"', organizationAddress='"+v.getOrganizationAddress()+"' where organizationId='"+v.getOrganizationId()+"'";
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
	public boolean savenotice(String heading, String departs, String textbody, String filename,String userid) {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			tx.begin();
			String depts[]=departs.split(",");

			int maxnoticeno=getMaxNoticeNo();

			for (int i = 0; i < depts.length; i++) {
				String sql = "insert into tbnotice(noticeno, noticeheader,noticebody, filenames, accessabledepartments, entryby, entrytime) values('"+maxnoticeno+"','"+heading+"','"+textbody+"','"+filename+"','"+depts[i]+"','"+userid+"', CURRENT_TIMESTAMP)";
				session.createSQLQuery(sql).executeUpdate();
			}

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




	public int getMaxNoticeNo() {
		// TODO Auto-generated method stub
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		List<OrganizationInfo> dataList=new ArrayList<OrganizationInfo>();
		int maxno=0;
		try{
			tx=session.getTransaction();
			tx.begin();

			String sql="select isnull(max(noticeno),0)+1 as noticeno from tbnotice";
			List<?> list = session.createSQLQuery(sql).list();
			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				//Object[] element = (Object[]) iter.next();

				//dataList.add(new OrganizationInfo(element[0].toString(),element[1].toString(),element[2].toString(),element[3].toString()));
				maxno= Integer.parseInt(iter.next().toString());
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
		return maxno;
	}



	@Override
	public JSONArray getDepartmentWiseUserList(String departmentIds) {
		// TODO Auto-generated method stub
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		JSONArray array=new JSONArray();
		JSONObject object;
		try{
			tx=session.getTransaction();
			tx.begin();

			//String sql="select isnull(max(CuttingReqId),0)+1 from TbCuttingRequisitionDetails";
			String sql="select l.id,l.fullname,ei.EmployeeCode,ei.Name,ei.DepartmentId from TbEmployeeInfo ei\n" + 
					"join Tblogin l\n" + 
					"on ei.AutoId = l.employeeId\n" + 
					"where ei.DepartmentId in ("+departmentIds+")";

			List<?> list = session.createSQLQuery(sql).list();
			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				Object[] element = (Object[]) iter.next();
				object = new JSONObject();
				object.put("id", element[0].toString());
				object.put("fullName", element[1].toString());
				object.put("employeeCode", element[2].toString());
				object.put("name", element[3].toString());
				object.put("departmentId", element[4].toString());
				array.add(object);
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

		return array;

	}

	@Override
	public String notificationTargetAdd(JSONObject notification,JSONArray targetList) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			tx.begin();

			String sql="insert into tbNotification (subject,type,notificationContent,createdBy,createdTime,issueLinkId) values('"+notification.get("subject")+"','"+notification.get("type")+"','"+notification.get("notificationContent")+"','"+notification.get("createdBy")+"',CURRENT_TIMESTAMP,'"+notification.get("issueLinkedId")+"')";
			session.createSQLQuery(sql).executeUpdate();

			String notificationId = "0";
			sql = "select max(id) as maxid from tbNotification";
			List<?> list = session.createSQLQuery(sql).list();
			if(list.size()>0) {
				notificationId = list.get(0).toString();
			}

			for (int i=0; i< targetList.size();i++) {
				JSONObject tempObject = (JSONObject)targetList.get(i);
				sql = "insert into tbNotificationTargets (notificationId,targetUserId,targetSeen,isClear) values('"+notificationId+"','"+tempObject.get("id")+"','0','0');";
				session.createSQLQuery(sql).executeUpdate();
			}

			tx.commit();
			return "success";

		} catch (Exception ee) {
			if (tx != null) {
				tx.rollback();
				return "something wrong";
			}
			ee.printStackTrace();
		}
		finally {
			session.close();
		}
		return "something wrong";
	}
	
	
	@Override
	public String notificationSeen(String targetId) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			tx.begin();
			String sql="update tbNotificationTargets set targetSeen = '1' where targetUserId = '"+targetId+"'";
			session.createSQLQuery(sql).executeUpdate();
			tx.commit();
			return "success";

		} catch (Exception ee) {
			if (tx != null) {
				tx.rollback();
				return "something wrong";
			}
			ee.printStackTrace();
		}
		finally {
			session.close();
		}
		return "something wrong";
	}
	
	@Override
	public String updateNotificationToSeen(String notificationId,String targetId) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			tx.begin();
			String sql="update tbNotificationTargets set targetSeen = '1' where notificationId='"+notificationId+"' and targetUserId = '"+targetId+"'";
			session.createSQLQuery(sql).executeUpdate();
			tx.commit();
			return "success";

		} catch (Exception ee) {
			if (tx != null) {
				tx.rollback();
				return "something wrong";
			}
			ee.printStackTrace();
		}
		finally {
			session.close();
		}
		return "something wrong";
	}

	@Override
	public JSONArray getNotificationList(String targetId) {
		// TODO Auto-generated method stub
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		JSONArray array=new JSONArray();
		JSONObject object;
		try{
			tx=session.getTransaction();
			tx.begin();

			//String sql="select isnull(max(CuttingReqId),0)+1 from TbCuttingRequisitionDetails";
			String sql="select n.id,l.username,n.subject,n.type,n.notificationContent,n.issueLinkId,nt.targetUserId,nt.targetSeen,n.createdTime  \r\n" + 
					"from tbNotificationTargets nt\r\n" + 
					"join tbNotification n\r\n" + 
					"on nt.notificationId = n.id\r\n"
					+ "left join Tblogin l\r\n" + 
					"on n.createdBy = l.id\r\n" + 
					"where nt.targetUserId= '"+targetId+"' and nt.isClear='0'";

			List<?> list = session.createSQLQuery(sql).list();
			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				Object[] element = (Object[]) iter.next();
				object = new JSONObject();
				object.put("notificationId", element[0].toString());
				object.put("createdBy", element[1].toString());
				object.put("subject", element[2].toString());
				object.put("type", element[3].toString());
				object.put("content", element[4].toString());
				object.put("issueLinkId", element[5].toString());
				object.put("targetUserId", element[6].toString());
				object.put("targetSeen", element[7].toString());
				object.put("createdTime", element[8].toString());
				array.add(object);
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

		return array;

	}

	@Override
	public JSONArray getUserList() {
		// TODO Auto-generated method stub
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		JSONArray array=new JSONArray();
		JSONObject object;
		try{
			tx=session.getTransaction();
			tx.begin();

			//String sql="select isnull(max(CuttingReqId),0)+1 from TbCuttingRequisitionDetails";
			String sql="select id,fullname,type,factoryId,departmentId from Tblogin";

			List<?> list = session.createSQLQuery(sql).list();
			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				Object[] element = (Object[]) iter.next();
				object = new JSONObject();
				object.put("id", element[0].toString());
				object.put("fullname", element[1].toString());
				object.put("type", element[2].toString());
				object.put("factoryId", element[3].toString());
				object.put("departmentId", element[4].toString());
				array.add(object);
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

		return array;

	}


	@Override
	public String saveGroup(String group) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			tx.begin();

			JSONParser jsonParser = new JSONParser();
			System.out.println(group);
			JSONObject groupObject = (JSONObject)jsonParser.parse(group);;

			String sql="select groupId,groupName from tbGroups where memberId in("+groupObject.get("members")+")";
			List<?> list = session.createSQLQuery(sql).list();

			if(list.size()==0) {
				sql="select isnull(max(groupId),0)+1 as maxGroupId from tbGroups";
				list = session.createSQLQuery(sql).list();

				int groupId = (int)list.get(0);


				String[] idList = groupObject.get("members").toString().split(",");
				for (String id: idList) {
					sql = "insert into tbGroups (groupId,groupName,memberId,entryTime,entryBy) values ('"+groupId+"','"+groupObject.get("groupName")+"','"+id+"',CURRENT_TIMESTAMP,'"+groupObject.get("userId")+"');";
					session.createSQLQuery(sql).executeUpdate();
				}
			}else {
				return "Members Already have another group";
			}

			tx.commit();
			return "success";

		} catch (Exception ee) {
			if (tx != null) {
				tx.rollback();
				return "something wrong";
			}
			ee.printStackTrace();
		}
		finally {
			session.close();
		}
		return "something wrong";
	}

	@Override
	public String editGroup(String group) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			tx.begin();

			JSONParser jsonParser = new JSONParser();
			System.out.println(group);
			JSONObject groupObject = (JSONObject)jsonParser.parse(group);;

			String sql="select groupId,groupName from tbGroups where memberId in("+groupObject.get("members")+") and groupId != '"+groupObject.get("groupId")+"'";
			List<?> list = session.createSQLQuery(sql).list();

			if(list.size()==0) {
				sql = "delete from tbGroups where groupId = '"+groupObject.get("groupId")+"'";
				session.createSQLQuery(sql).executeUpdate();

				sql="select isnull(max(groupId),0)+1 as maxGroupId from tbGroups";
				list = session.createSQLQuery(sql).list();

				int groupId = (int)list.get(0);


				String[] idList = groupObject.get("members").toString().split(",");
				for (String id: idList) {
					sql = "insert into tbGroups (groupId,groupName,memberId,entryTime,entryBy) values ('"+groupId+"','"+groupObject.get("groupName")+"','"+id+"',CURRENT_TIMESTAMP,'"+groupObject.get("userId")+"');";
					session.createSQLQuery(sql).executeUpdate();
				}
			}else {
				return "Members Already have another group";
			}

			tx.commit();
			return "success";

		} catch (Exception ee) {
			if (tx != null) {
				tx.rollback();
				return "something wrong";
			}
			ee.printStackTrace();
		}
		finally {
			session.close();
		}
		return "something wrong";
	}

	@Override
	public JSONArray getGroupList() {
		// TODO Auto-generated method stub
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		JSONArray array=new JSONArray();
		JSONObject object;
		try{
			tx=session.getTransaction();
			tx.begin();

			//String sql="select isnull(max(CuttingReqId),0)+1 from TbCuttingRequisitionDetails";
			String sql="select groupId,groupName from tbGroups group by groupId,groupName";

			List<?> list = session.createSQLQuery(sql).list();
			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				Object[] element = (Object[]) iter.next();
				object = new JSONObject();
				object.put("groupId", element[0].toString());
				object.put("groupName", element[1].toString());
				array.add(object);
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
		return array;
	}

	@Override
	public JSONArray getGroupMembers(String groupId) {
		// TODO Auto-generated method stub
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		JSONArray array=new JSONArray();
		JSONObject object;
		try{
			tx=session.getTransaction();
			tx.begin();

			//String sql="select isnull(max(CuttingReqId),0)+1 from TbCuttingRequisitionDetails";
			String sql="select g.autoId,groupId,groupName,memberId,l.fullname\r\n" + 
					"from tbGroups g\r\n" + 
					"left join Tblogin l\r\n" + 
					"on g.memberId = l.id where groupId = '"+groupId+"'";

			List<?> list = session.createSQLQuery(sql).list();
			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				Object[] element = (Object[]) iter.next();
				object = new JSONObject();
				object.put("autoId", element[0].toString());
				object.put("groupId", element[1].toString());
				object.put("groupName", element[2].toString());
				object.put("memberId", element[3].toString());
				object.put("memberName", element[4].toString());
				array.add(object);
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
		return array;
	}





	@Override
	public JSONArray getFormPermitInvoiceList(String formId,String ownerId,String permittedUserId) {
		// TODO Auto-generated method stub
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		JSONArray array=new JSONArray();
		JSONObject object;
		try{
			tx=session.getTransaction();
			tx.begin();

			//String sql="select isnull(max(CuttingReqId),0)+1 from TbCuttingRequisitionDetails";
			String sql = "";

			if(formId.equals(String.valueOf(FormId.BUYER_CREATE.getId()))) {
				sql = "select b.id,(select name from TbSubMenu where id= '"+formId+"') as FromName,b.name as IdNo,isnull(ap.autoId,0) as isPermitted \r\n" + 
						"  from tbBuyer b\r\n" + 
						"left join tbFileAccessPermission ap\r\n" + 
						"on ap.ownerId = '"+ownerId+"' and b.id = ap.resourceId and ap.permittedUserId = '"+permittedUserId+"'\r\n" + 
						" where b.UserId = '"+ownerId+"'\r\n" + 
						" group by b.id,b.name,ap.autoId";
			}
			else if(formId.equals(String.valueOf(FormId.STYLE_CREATE.getId()))) {
				sql="select sc.styleId,(select name from TbSubMenu where id= '"+formId+"') as FromName,styleNo  as IdNo,isnull(ap.autoId,0) as isPermitted from TbStyleCreate sc\r\n" + 
						"left join tbFileAccessPermission ap\r\n" + 
						"on ap.ownerId = '"+ownerId+"' and sc.styleId = ap.resourceId and ap.permittedUserId = '"+permittedUserId+"'\r\n" + 
						" where UserId = '"+ownerId+"'";

			}else if(formId.equals(String.valueOf(FormId.COSTING_CREATE.getId()))) {
				sql="select cc.costingNo,(select name from TbSubMenu where id= '"+formId+"') as FromName,cc.costingNo  as IdNo,isnull(ap.autoId,0) as isPermitted \r\n" + 
						"  from TbCostingCreate cc\r\n" + 
						"left join tbFileAccessPermission ap\r\n" + 
						"on ap.ownerId = '"+ownerId+"' and cc.costingNo = ap.resourceId and ap.permittedUserId = '"+permittedUserId+"'\r\n" + 
						" where UserId = '"+ownerId+"'\r\n" + 
						" group by cc.costingNo,ap.autoId";
			}
			else if(formId.equals(String.valueOf(FormId.BUYER_PO.getId()))) {
				sql=" select boes.autoId,(select name from TbSubMenu where id= '"+formId+"') as FromName,boes.autoId  as IdNo,isnull(ap.autoId,0) as isPermitted \r\n" + 
						"  from TbBuyerOrderEstimateSummary boes\r\n" + 
						"left join tbFileAccessPermission ap\r\n" + 
						"on ap.ownerId = '"+ownerId+"' and boes.autoId = ap.resourceId and ap.permittedUserId = '"+permittedUserId+"'\r\n" + 
						" where UserId = '"+ownerId+"'\r\n" + 
						" group by boes.autoId,ap.autoId";
			}else if(formId.equals(String.valueOf(FormId.ACCESSORIES_INDENT.getId()))) {
				sql = " select ai.AINo,(select name from TbSubMenu where id= '"+formId+"') as FromName,ai.AINo  as IdNo,isnull(ap.autoId,0) as isPermitted \r\n" + 
						"  from tbAccessoriesIndent ai\r\n" + 
						"left join tbFileAccessPermission ap\r\n" + 
						"on ap.ownerId = '"+ownerId+"' and ai.AINo = ap.resourceId and ap.permittedUserId = '"+permittedUserId+"'\r\n" + 
						" where ai.IndentPostBy = '"+ownerId+"'\r\n" + 
						" group by ai.AINo,ap.autoId";
			}
			else if(formId.equals(String.valueOf(FormId.ZIPPER_INDENT.getId()))) {
				sql = " select zi.AINo,(select name from TbSubMenu where id= '"+formId+"') as FromName,zi.AINo  as IdNo,isnull(ap.autoId,0) as isPermitted \r\n" + 
						"  from tbZipperIndent zi\r\n" + 
						"left join tbFileAccessPermission ap\r\n" + 
						"on ap.ownerId = '"+ownerId+"' and zi.AINo = ap.resourceId and ap.permittedUserId = '"+permittedUserId+"'\r\n" + 
						" where zi.IndentPostBy = '"+ownerId+"'\r\n" + 
						" group by zi.AINo,ap.autoId";
			}
			else if(formId.equals(String.valueOf(FormId.FABRICS_INDENT.getId()))) {
				sql = " select fi.indentId,(select name from TbSubMenu where id= '"+formId+"') as FromName,fi.indentId  as IdNo,isnull(ap.autoId,0) as isPermitted \r\n" + 
						"  from tbFabricsIndent fi\r\n" + 
						"left join tbFileAccessPermission ap\r\n" + 
						"on ap.ownerId = '"+ownerId+"' and fi.indentId = ap.resourceId and ap.permittedUserId = '"+permittedUserId+"'\r\n" + 
						" where fi.entryby = '"+ownerId+"'\r\n" + 
						" group by fi.indentId,ap.autoId";
			}else if(formId.equals(String.valueOf(FormId.CARTON_INDENT.getId()))) {
				sql = " select ci.indentId,(select name from TbSubMenu where id= '"+formId+"') as FromName,ci.indentId  as IdNo,isnull(ap.autoId,0) as isPermitted \r\n" + 
						"  from tbAccessoriesIndentForCarton ci\r\n" + 
						"left join tbFileAccessPermission ap\r\n" + 
						"on ap.ownerId = '"+ownerId+"' and ci.indentId = ap.resourceId and ap.permittedUserId = '"+permittedUserId+"'\r\n" + 
						" where ci.IndentPostBy = '"+ownerId+"'\r\n" + 
						" group by ci.indentId,ap.autoId";
			}else if(formId.equals(String.valueOf(FormId.PURCHASE_ORDER.getId()))) {
				sql = " select pos.pono,(select name from TbSubMenu where id= '"+formId+"') as FromName,pos.pono as IdNo,isnull(ap.autoId,0) as isPermitted \r\n" + 
						"  from tbPurchaseOrderSummary pos\r\n" + 
						"left join tbFileAccessPermission ap\r\n" + 
						"on ap.ownerId = '"+ownerId+"' and pos.pono = ap.resourceId and ap.permittedUserId = '"+permittedUserId+"'\r\n" + 
						" where pos.entryBy = '"+ownerId+"'\r\n" + 
						" group by pos.pono,ap.autoId";
			}else if(formId.equals(String.valueOf(FormId.SAMPLE_REQUISITION.getId()))) {
				sql = " select sr.sampleReqId,(select name from TbSubMenu where id= '"+formId+"') as FromName,sr.sampleReqId as IdNo,isnull(ap.autoId,0) as isPermitted \n" + 
						"  from tbSampleRequisition sr\n" + 
						"left join tbFileAccessPermission ap\n" + 
						"on ap.ownerId = '"+ownerId+"' and sr.sampleReqId = ap.resourceId and ap.permittedUserId = '"+permittedUserId+"'\n" + 
						" where sr.UserId = '"+ownerId+"'\n" + 
						" group by sr.sampleReqId,ap.autoId";
			}


			List<?> list = session.createSQLQuery(sql).list();
			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				Object[] element = (Object[]) iter.next();
				object = new JSONObject();
				object.put("id", element[0].toString());
				object.put("formName", element[1].toString());
				object.put("fileNo", element[2].toString());
				object.put("permit", element[3].toString());
				array.add(object);
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
		return array;
	}

	@Override
	public JSONArray getFormPermitedUsers(String formId, String ownerId) {
		// TODO Auto-generated method stub
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		JSONArray array=new JSONArray();
		JSONObject object;
		try{
			tx=session.getTransaction();
			tx.begin();

			//String sql="select isnull(max(CuttingReqId),0)+1 from TbCuttingRequisitionDetails";
			String sql = "";

			sql="select permittedUserId,l.fullname from tbFileAccessPermission fap\r\n" + 
					" join Tblogin l\r\n" + 
					" on fap.permittedUserId = l.id\r\n" + 
					" where ownerId = '"+ownerId+"' and resourceType = '"+formId+"'\r\n" + 
					" group by permittedUserId,l.fullname";




			List<?> list = session.createSQLQuery(sql).list();
			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				Object[] element = (Object[]) iter.next();
				object = new JSONObject();
				object.put("id", element[0].toString());
				object.put("fullName", element[1].toString());
				array.add(object);
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
		return array;
	}

	@Override
	public String submitFileAccessPermit(String fileAccessPermit) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			tx.begin();

			JSONParser jsonParser = new JSONParser();
			System.out.println(fileAccessPermit);
			JSONObject permitObject = (JSONObject)jsonParser.parse(fileAccessPermit);

			JSONArray filePermitArray = (JSONArray)jsonParser.parse(permitObject.get("permittedFileList").toString());

			String sql="delete from tbFileAccessPermission where resourceType = '"+permitObject.get("resourceType")+"' and ownerId = '"+permitObject.get("ownerId")+"' and permittedUserId = '"+permitObject.get("permittedUserId")+"'";
			session.createSQLQuery(sql).executeUpdate();


			for(int i = 0; i<filePermitArray.size(); i++) {
				JSONObject tempObject = (JSONObject)filePermitArray.get(i);

				sql = "insert into tbFileAccessPermission (resourceType,resourceId,ownerId,permittedUserId,entryTime,entryBy) values('"+tempObject.get("resourceType")+"','"+tempObject.get("resourceId")+"','"+tempObject.get("ownerId")+"','"+tempObject.get("permittedUserId")+"',CURRENT_TIMESTAMP,'"+tempObject.get("ownerId")+"')";
				session.createSQLQuery(sql).executeUpdate();
			}	

			tx.commit();
			return "success";

		} catch (Exception ee) {
			if (tx != null) {
				tx.rollback();
				return "something wrong";
			}
			ee.printStackTrace();
		}
		finally {
			session.close();
		}
		return "something wrong";
	}


	@Override
	public JSONArray getMenus(String userId) {
		// TODO Auto-generated method stub
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		JSONArray array=new JSONArray();
		JSONObject object;
		try{
			tx=session.getTransaction();
			tx.begin();

			//String sql="select isnull(max(CuttingReqId),0)+1 from TbCuttingRequisitionDetails";
			String sql="select sm.id,sm.module,sm.root,sm.name \r\n" + 
					"from Tbuseraccess ua \r\n" + 
					"join TbSubMenu sm\r\n" + 
					"on ua.sub = sm.id\r\n" + 
					"where ua.userId = '"+userId+"' \r\n" + 
					"order by sm.module,sm.ordering";

			List<?> list = session.createSQLQuery(sql).list();
			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				Object[] element = (Object[]) iter.next();
				object = new JSONObject();
				object.put("id", element[0].toString());
				object.put("module", element[1].toString());
				object.put("root", element[2].toString());
				object.put("name", element[3].toString());
				array.add(object);
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
		return array;
	}


	@Override
	public List<RoleManagement> getSubmenu(String moduleId) {
		// TODO Auto-generated method stub
		String sql = "";
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		List<RoleManagement> dataList = new ArrayList<RoleManagement>();
		try {
			tx = session.getTransaction();
			tx.begin();
			sql = "select (a.id) as module, (a.name) as moduleNmae, (b.id) as head,c.id,c.name from TbModule a join TbMenu b on b.module=a.id join TbSubMenu c on c.root=b.id where a.id in ("+moduleId+") and b.module=a.id and c.root=b.id order by a.id";
			List<?> list = session.createSQLQuery(sql).list();
			for (Iterator<?> iter = list.iterator(); iter.hasNext();) {
				Object[] element = (Object[]) iter.next();
				dataList.add(new RoleManagement(element[0].toString(),element[1].toString(),Integer.parseInt(element[2].toString()),Integer.parseInt(element[3].toString()), element[4].toString()));
			}
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return dataList;
	}



	@Override
	public boolean saveRolePermission(RoleManagement v) {
		// TODO Auto-generated method stub
		String sql="";
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			tx.begin();

			String accesslit=v.getAccesslist();
			accesslit=accesslit.replace("[", "");
			accesslit=accesslit.replace("]", "");
			int x=0;
			sql="select roleName from tbRoleInfo where roleName='"+v.getRoleName()+"'";
			List<?> list1 = session.createSQLQuery(sql).list();
			if(list1.size()==0) {

				sql = "select (isnull(max(roleId),0)+1) as id from tbRoleInfo";
				List<?> list = session.createSQLQuery(sql).list();
				String maxRoleId = list.get(0).toString();

				sql="insert into tbRoleInfo (roleId, roleName, userid, entrytime) values ('"+maxRoleId+"', '"+v.getRoleName()+"', '"+v.getUserId()+"', CURRENT_TIMESTAMP) ";
				//			System.err.println("sql : "+sql);
				session.createSQLQuery(sql).executeUpdate();

				StringTokenizer s=new StringTokenizer(accesslit,",");
				while(s.hasMoreElements()) {
					String a=s.nextToken().trim();
					StringTokenizer s2=new StringTokenizer(a,":");
					while(s2.hasMoreElements()) {

						String moduleId=s2.nextToken();
						String headId=s2.nextToken();
						String subId=s2.nextToken();
						String add=s2.nextToken();
						String edit=s2.nextToken();
						String view=s2.nextToken();
						String delete=s2.nextToken();
						String refund=s2.nextToken();
						String discount=s2.nextToken();

						sql="insert into tbRolePermission (roleId, moduleid, head, sub, entry, edit, [view], clear,refund,discount, entryby) values ('"+maxRoleId+"','"+moduleId+"','"+headId+"','"+subId+"','"+add+"','"+edit+"','"+view+"','"+delete+"','"+refund+"','"+discount+"','"+v.getUserId()+"')";
						//					System.err.println("sql 2 : "+sql);
						session.createSQLQuery(sql).executeUpdate();

					}
				}
				x++;
			}
			tx.commit();
			if(x>0) {
				return true;
			}else {
				return false;
			}

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
	public List<RoleManagement> getAllRoleName() {
		// TODO Auto-generated method stub
		String sql = "";
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		List<RoleManagement> dataList = new ArrayList<RoleManagement>();
		try {
			tx = session.getTransaction();
			tx.begin();
			sql = "select roleId,roleName from tbRoleInfo";
			List<?> list = session.createSQLQuery(sql).list();
			for (Iterator<?> iter = list.iterator(); iter.hasNext();) {
				Object[] element = (Object[]) iter.next();
				dataList.add(new RoleManagement(element[0].toString(),element[1].toString()));
			}
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return dataList;
	}



	@Override
	public List<RoleManagement> getAllPermissions(String id) {
		// TODO Auto-generated method stub
		String sql = "";
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		List<RoleManagement> dataList = new ArrayList();
		try {
			tx = session.getTransaction();
			tx.begin();
			sql = "select moduleid,head,sub,clear,entry,edit,[view],refund,discount from tbRolePermission where roleId='"+id+"'";

			List<?> list = session.createSQLQuery(sql).list();
			for (Iterator<?> iter = list.iterator(); iter.hasNext();) {
				Object[] element = (Object[]) iter.next();
				dataList.add(new RoleManagement(element[0].toString(), Integer.parseInt(element[1].toString()),
						Integer.parseInt(element[2].toString()),element[3].toString(),element[4].toString(),
						element[5].toString(),element[6].toString(),element[7].toString(),element[8].toString()));
			}
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return dataList;
	}



	@Override
	public boolean editRolePermission(RoleManagement v) {
		// TODO Auto-generated method stub
		String sql="";
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			tx.begin();

			String accesslit=v.getAccesslist();
			accesslit=accesslit.replace("[", "");
			accesslit=accesslit.replace("]", "");
			int x=0;
			sql="select roleName from tbRoleInfo where roleName='"+v.getRoleName()+"' and roleId!='"+v.getRoleId()+"' ";
			List<?> list1 = session.createSQLQuery(sql).list();
			if(list1.size()==0) {

				sql="update tbRoleInfo set roleName='"+v.getRoleName()+"' where roleId='"+v.getRoleId()+"'";
				session.createSQLQuery(sql).executeUpdate();

				sql="delete from tbRolePermission where roleId='"+v.getRoleId()+"'";
				session.createSQLQuery(sql).executeUpdate();

				StringTokenizer s=new StringTokenizer(accesslit,",");
				while(s.hasMoreElements()) {
					String a=s.nextToken().trim();
					StringTokenizer s2=new StringTokenizer(a,":");
					while(s2.hasMoreElements()) {

						String moduleId=s2.nextToken();
						String headId=s2.nextToken();
						String subId=s2.nextToken();
						String add=s2.nextToken();
						String edit=s2.nextToken();
						String view=s2.nextToken();
						String delete=s2.nextToken();
						String refund=s2.nextToken();
						String discount=s2.nextToken();
						sql="insert into tbRolePermission (roleId, moduleid, head, sub, entry, edit, [view], clear,refund,discount, entryby) values ('"+v.getRoleId()+"','"+moduleId+"','"+headId+"','"+subId+"','"+add+"','"+edit+"','"+view+"','"+delete+"','"+refund+"','"+discount+"','"+v.getUserId()+"')";
						System.out.println("editsql "+sql);
						session.createSQLQuery(sql).executeUpdate();

					}
				}
				x++;
			}
			tx.commit();
			if(x>0) {
				return true;
			}else {
				return false;
			}

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
	public List<Employee> getEmployeeList() {
		// TODO Auto-generated method stub
		String sql="";
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		List<Employee> dataList=new ArrayList<Employee>();
		Employee employee = null;
		try{
			tx=session.getTransaction();
			tx.begin();

			int i=1;
			//String sql="select a.AutoId,a.EmployeeCode, a.Name, a.CardNo, a.DepartmentId, (select b.DepartmentName from TbDepartmentInfo b where b.DepartmentId=a.DepartmentId) as DepartmentName, a.DesginationId, (select c.DesignationName from TbDesignationInfo c where c.DesignationId=a.DesginationId) as Designation, a.LineId, a.Grade, isnull(CONVERT(VARCHAR(50),JoinDate),'') as JoinDate from TbEmployeeInfo a";
			sql="select a.AutoId,a.EmployeeCode, a.Name, a.CardNo, a.DepartmentId, isnull((select b.DepartmentName from TbDepartmentInfo b where b.DepartmentId=a.DepartmentId),'') as DepartmentName, a.DesginationId, isnull((select c.DesignationName from TbDesignationInfo c where c.DesignationId=a.DesginationId),'') as Designation, a.LineId, a.Grade, isnull(CONVERT(VARCHAR(50),JoinDate),'') as JoinDate,a.religion,a.gender, a.email, a.contact, a.nationality, a.nationalid, isnull(CONVERT(VARCHAR(50),a.birthdate),'') as birthdate,factoryId from TbEmployeeInfo a where trash=0";
			List<?> list = session.createSQLQuery(sql).list();
			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	

				Object[] element = (Object[]) iter.next();
				employee = new Employee(element[0].toString(), element[1].toString(), element[2].toString(), element[3].toString(),
						element[4].toString(), element[5].toString(), element[6].toString(), element[7].toString(), element[8].toString(),
						element[9].toString(),element[10].toString(),element[11].toString(),element[12].toString(),element[13].toString(),
						element[14].toString(),element[15].toString(),element[16].toString(),element[17].toString());
				employee.setFactoryId(element[18].toString());
				dataList.add(employee);
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
	public Employee getEmployeeInfoByEmployeeCode(String employeeCode) {
		// TODO Auto-generated method stub
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		Employee employeeInfo = null;
		try{
			tx=session.getTransaction();
			tx.begin();

			String sql="select AutoId,EmployeeCode,CardNo,Name,DepartmentId,DesginationId,LineId,Grade,isnull(CONVERT(VARCHAR(50),JoinDate),'') as JoinDate from TbEmployeeInfo where EmployeeCode= '"+employeeCode+"'";

			List<?> list = session.createSQLQuery(sql).list();
			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				Object[] element = (Object[]) iter.next();
				employeeInfo = new Employee(element[0].toString(), element[1].toString(), element[3].toString(), element[2].toString(), element[4].toString(), "", element[5].toString(), "", element[6].toString(), element[7].toString(), element[8].toString());

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
		return employeeInfo;
	}



	@Override
	public List<ProjectFeature> getModuleList() {
		// TODO Auto-generated method stub
		String sql="";
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		List<ProjectFeature> dataList=new ArrayList<ProjectFeature>();
		
		try{
			tx=session.getTransaction();
			tx.begin();

			int i=1;
			
			sql="select id,name from tbmodule order by name";
			List<?> list = session.createSQLQuery(sql).list();
			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	

				Object[] element = (Object[]) iter.next();
				dataList.add(new ProjectFeature(element[0].toString(), element[1].toString()));
					
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
	public boolean isMenuExist(ProjectFeature v) {
		// TODO Auto-generated method stub
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		try{
			tx=session.getTransaction();
			tx.begin();

			String sql="select * from TbMenu where id!='"+v.getMenuId()+"' and module='"+v.getModuleId()+"'  and name='"+v.getMenuName()+"'";

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
	public boolean saveMenu(ProjectFeature v) {
		// TODO Auto-generated method stub
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		try{
			tx=session.getTransaction();
			tx.begin();


			String sql="insert into TbMenu ("
					+ "name,"
					+ "module,"
					+ "trash,"
					+ "userid,"
					+ "entrytime"
					+ ") values ("
					+ "'"+v.getMenuName()+"',"
					+ "'"+v.getModuleId()+"',"
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
	public List<ProjectFeature> getMenuList() {
		// TODO Auto-generated method stub
		String sql="";
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		List<ProjectFeature> dataList=new ArrayList<ProjectFeature>();
		
		try{
			tx=session.getTransaction();
			tx.begin();
			
			int i=1;
			
			sql="select id,name,module,(select name from tbmodule where id=TbMenu.module) as ModuleName from TbMenu order by name";
			List<?> list = session.createSQLQuery(sql).list();
			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	

				Object[] element = (Object[]) iter.next();
				dataList.add(new ProjectFeature(element[0].toString(), element[1].toString(),element[2].toString(),element[3].toString()));
					
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
	public boolean saveDoctorDirect(DoctorCreate v) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		try {
			
			tx = session.getTransaction();
			tx.begin();

			String doctorId="";
			String sql="select isnull(max(DoctorId),0)+1 from TbDoctorInfo";
			List<?> list = session.createSQLQuery(sql).list();
			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				doctorId=iter.next().toString();
			}
			
			String doctorCode="DC-"+doctorId;
			sql = "insert into TbDoctorInfo (DoctorId,Name,DoctorCode,Address,Religion,Sex,Mobile,Type,Department,Degree,VisitFee, RoomNo,EntryTime, UserId) values ("
					+ "'" + doctorId+ "',"
					+ "'" + v.getDoctorName()+ "',"
					+ "'" + doctorCode+ "',"
					+ "'',"
					+ "'Other',"
					+ "'Male',"
					+ "'01',"
					+ "'0',"
					+ "'1',"
					+ "'" + v.getDegree()+ "',"
					+ "'"+v.getVisitFee()+"',"
					+ "'"+v.getRoomNo()+"',"
					+ "CURRENT_TIMESTAMP,'"+ v.getUserId() + "')";

			session.createSQLQuery(sql).executeUpdate();


			tx.commit();
			return true;

		} catch (Exception ee) {
			ee.printStackTrace();
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
	public List<DoctorCreate> getDoctorList() {
		// TODO Auto-generated method stub
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		List<DoctorCreate> datalist=new ArrayList<DoctorCreate>();

		try{
			tx=session.getTransaction();
			tx.begin();

			String sql="select DoctorId,Name,Degree from TbDoctorInfo order by Type,Name";
			List<?> list = session.createSQLQuery(sql).list();
			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				Object[] element = (Object[]) iter.next();

				datalist.add(new DoctorCreate(element[0].toString(),element[1].toString(),element[2].toString()));

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
	public List<DoctorCreate> getConsultantList() {
		// TODO Auto-generated method stub
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		List<DoctorCreate> datalist=new ArrayList<DoctorCreate>();

		try{
			tx=session.getTransaction();
			tx.begin();

			String sql="select id,Name,'' as degree from tbLabInchargeConsultantDegree";
			List<?> list = session.createSQLQuery(sql).list();
			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				Object[] element = (Object[]) iter.next();

				datalist.add(new DoctorCreate(element[0].toString(),element[1].toString(),element[2].toString()));

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
	public List<DoctorCreate> getLabGroupList() {
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		List<DoctorCreate> datalist=new ArrayList<DoctorCreate>();

		try{
			tx=session.getTransaction();
			tx.begin();

			String sql="select HeadId,GroupName,cast(DoctorComission as int) as DoctorComission,ParentId from TbLabTestGroup order by ParentId";
			List<?> list = session.createSQLQuery(sql).list();
			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				Object[] element = (Object[]) iter.next();

				datalist.add(new DoctorCreate(element[0].toString(),element[1].toString(),element[2].toString(),element[3].toString()));

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
	public String getMaxDoctorId() {
		// TODO Auto-generated method stub
		String id="";

		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		try{
			tx=session.getTransaction();
			tx.begin();


			String sql="select (ISNULL(max(DoctorId),0)+1)as DoctorId from TbDoctorInfo ";
			List<?> list = session.createSQLQuery(sql).list();

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
	public boolean isDoctorExist(DoctorCreate v) {
		// TODO Auto-generated method stub
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		try{
			tx=session.getTransaction();
			tx.begin();

			String sql="select Name from TbDoctorInfo where Name='"+v.getDoctorName()+"' and DoctorId!='"+v.getDoctorId()+"'";

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
	public boolean saveDoctor(DoctorCreate v) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			tx.begin();

			String doctorId="";
			String sql="select isnull(max(DoctorId),0)+1 from TbDoctorInfo";
			List<?> list = session.createSQLQuery(sql).list();
			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				doctorId=iter.next().toString();
			}
			
			String resultValue=v.getResultList().substring(v.getResultList().indexOf("[")+1, v.getResultList().indexOf("]"));
			
			sql = "insert into TbDoctorInfo (DoctorId,Name,DoctorCode,Address,Religion,Sex,Mobile,Type,Department,Degree,VisitFee, RoomNo,EntryTime, UserId) values ("
					+ "'" + doctorId+ "',"
					+ "'" + v.getDoctorName()+ "',"
					+ "'" + v.getDoctorCode()+ "',"
					+ "'" + v.getAddress()+ "',"
					+ "'" + v.getReligion()+ "',"
					+ "'" + v.getSex()+ "',"
					+ "'" + v.getMobile()+ "',"
					+ "'" + v.getDoctorCategory()+ "',"
					+ "'" + v.getDepartmentId()+ "',"
					+ "'" + v.getDegree()+ "',"
					+ "'"+v.getVisitFee()+"',"
					+ "'"+v.getRoomNo()+"',"
					+ "CURRENT_TIMESTAMP,'"+ v.getUserId() + "')";

			session.createSQLQuery(sql).executeUpdate();

			System.out.println("resultValue "+resultValue);

			StringTokenizer firsttoken=new StringTokenizer(resultValue,",");
			while(firsttoken.hasMoreElements()) {
				String tokenValue=firsttoken.nextToken();
				StringTokenizer token=new StringTokenizer(tokenValue,"*");
				while(token.hasMoreTokens()) {
					String groupId=token.nextToken();
					String groupName=token.nextToken();
					String doctorComission=token.nextToken();
					String parentId=token.nextToken();
					String checkRequest=token.nextToken();

					if(checkRequest.equals("1")) {
						String sqldoctorcom="insert into TbDoctorComissionSet (GroupParentId,TestGroupId,TestGroupName,DoctorId,DoctorComission,EntryTime,UserId) values ('"+parentId+"','"+groupId+"','"+groupName+"','"+doctorId+"','"+doctorComission+"',CURRENT_TIMESTAMP,'"+v.getUserId()+"')";
						System.out.println("sqldoctorcom "+sqldoctorcom);
						session.createSQLQuery(sqldoctorcom).executeUpdate();
					}
					
					System.out.println("checkRequest "+checkRequest);

				}
			}

			tx.commit();
			return true;

		} catch (Exception ee) {
			ee.printStackTrace();
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
	public boolean editDoctor(DoctorCreate v) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			tx.begin();
			
			
			
			String resultValue=v.getResultList().substring(v.getResultList().indexOf("[")+1, v.getResultList().indexOf("]"));

			String sql = "update TbDoctorInfo set "
					+ "Name='" + v.getDoctorName()+ "',"
					+ "DoctorCode='" + v.getDoctorCode()+ "',"
					+ "Address='" + v.getAddress()+ "',"
					+ "Religion='" + v.getReligion()+ "',"
					+ "Sex='" + v.getSex()+ "',"
					+ "Mobile='" + v.getMobile()+ "',"
					+ "Type='" + v.getDoctorCategory()+ "',"
					+ "Department='" + v.getDepartmentId()+ "',"
					+ "Degree='" + v.getDegree()+ "',"
					+ "VisitFee='"+v.getVisitFee()+"',"
					+ "RoomNo='"+v.getRoomNo()+"',"
					+ "EntryTime=CURRENT_TIMESTAMP,UserId='"+ v.getUserId() + "' where DoctorId='"+v.getDoctorId()+"'";

			session.createSQLQuery(sql).executeUpdate();
			
			sql="delete from TbDoctorComissionSet where DoctorId='"+v.getDoctorId()+"'";
			session.createSQLQuery(sql).executeUpdate();
			
			StringTokenizer firsttoken=new StringTokenizer(resultValue,",");
			while(firsttoken.hasMoreElements()) {
				String tokenValue=firsttoken.nextToken();
				StringTokenizer token=new StringTokenizer(tokenValue,"*");
				while(token.hasMoreTokens()) {
					String groupId=token.nextToken();
					String groupName=token.nextToken();
					String doctorComission=token.nextToken();
					String parentId=token.nextToken();
					String checkRequest=token.nextToken();

					if(checkRequest.equals("1")) {
						String sqldoctorcom="insert into TbDoctorComissionSet (GroupParentId,TestGroupId,TestGroupName,DoctorId,DoctorComission,EntryTime,UserId) values ('"+parentId+"','"+groupId+"','"+groupName+"','"+v.getDoctorId()+"','"+doctorComission+"',CURRENT_TIMESTAMP,'"+v.getUserId()+"')";
						System.out.println("sqldoctorcom "+sqldoctorcom);
						session.createSQLQuery(sqldoctorcom).executeUpdate();
					}

					System.out.println("checkRequest "+checkRequest);

				}
			}

			tx.commit();
			return true;

		} catch (Exception ee) {
			ee.printStackTrace();
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
	public List<DoctorCreate> getDoctorDetails(String doctorId) {
		// TODO Auto-generated method stub
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		List<DoctorCreate> datalist=new ArrayList<DoctorCreate>();

		try{
			tx=session.getTransaction();
			tx.begin();

			String sql="select DoctorId,Name,DoctorCode,Address,Religion,Sex,Mobile,Type,Department,Degree,ISNULL(VisitFee,0) as VisitFee,ISNULL(RoomNo,'') as RoomNo from TbDoctorInfo where DoctorId='"+doctorId+"'";
			List<?> list = session.createSQLQuery(sql).list();
			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				Object[] element = (Object[]) iter.next();

				datalist.add(new DoctorCreate(element[0].toString(),element[1].toString(),element[2].toString(),element[3].toString(),element[4].toString(),element[5].toString(),element[6].toString(),element[7].toString(),element[8].toString(),element[9].toString(),element[10].toString(),element[11].toString(),""));

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
	public List<DoctorCreate> getDoctorCommissionDetails(String doctorId) {
		// TODO Auto-generated method stub
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		List<DoctorCreate> datalist=new ArrayList<DoctorCreate>();

		try{
			tx=session.getTransaction();
			tx.begin();

			String sql="select a.HeadId,a.GroupName,ISNULL((select cast(DoctorComission as int) from TbDoctorComissionSet where TestGroupId=a.HeadId and DoctorId='"+doctorId+"'),0) as SetDoctorComission,'0' as DoctorComissionDeduction,ParentId from TbLabTestGroup a order by a.ParentId";
			List<?> list = session.createSQLQuery(sql).list();
			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				Object[] element = (Object[]) iter.next();

				datalist.add(new DoctorCreate(element[0].toString(),element[1].toString(),element[2].toString(),element[3].toString(),element[4].toString()));

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
	public boolean isMemberExist(MemberCreate v) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		try{
			tx=session.getTransaction();
			tx.begin();

			String sql="select memberName from TbMemberShip where memberName='"+v.getMemberName()+"' and memberId!='"+v.getMemberId()+"'";

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
	public boolean saveMember(MemberCreate v) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			tx.begin();
			
			
			String sql = "insert into TbMemberShip (memberName,contact,membershipType,occupation,userId,entrtyTime) values ("
					+ "'" + v.getMemberName()+ "',"
					+ "'" + v.getContact()+ "',"
					+ "'" + v.getMembershipType()+ "',"
					+ "'" + v.getOccupation()+ "',"
					+ "'" + v.getUserId()+ "',"
					+ "CURRENT_TIMESTAMP)";

			session.createSQLQuery(sql).executeUpdate();



			tx.commit();
			return true;

		} catch (Exception ee) {
			ee.printStackTrace();
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
	public List<MemberCreate> getMemberList() {
		// TODO Auto-generated method stub
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		List<MemberCreate> datalist=new ArrayList<MemberCreate>();

		try{
			tx=session.getTransaction();
			tx.begin();

			String sql="select memberId,memberName,contact,membershipType,occupation from TbMemberShip order by membershipType";
			List<?> list = session.createSQLQuery(sql).list();
			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				Object[] element = (Object[]) iter.next();

				datalist.add(new MemberCreate(element[0].toString(),element[1].toString(),element[2].toString(),element[3].toString(),element[4].toString()));

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
	public List<MemberCreate> getMemberDetails(String memberId) {
		// TODO Auto-generated method stub
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		List<MemberCreate> datalist=new ArrayList<MemberCreate>();

		try{
			tx=session.getTransaction();
			tx.begin();

			String sql="select memberId,memberName,contact,membershipType,occupation from TbMemberShip where memberId='"+memberId+"'";
			List<?> list = session.createSQLQuery(sql).list();
			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				Object[] element = (Object[]) iter.next();

				datalist.add(new MemberCreate(element[0].toString(),element[1].toString(),element[2].toString(),element[3].toString(),element[4].toString()));

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
	public boolean editMember(MemberCreate v) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			tx.begin();
			
			
			String sql = "update TbMemberShip set "
					+ "memberName='" + v.getMemberName()+ "',"
					+ "contact='" + v.getContact()+ "',"
					+ "membershipType='" + v.getMembershipType()+ "',"
					+ "occupation='" + v.getOccupation()+ "',"
					+ "userId='" + v.getUserId()+ "',"
					+ "entrtyTime=CURRENT_TIMESTAMP where memberId='"+v.getMemberId()+"'";

			session.createSQLQuery(sql).executeUpdate();



			tx.commit();
			return true;

		} catch (Exception ee) {
			ee.printStackTrace();
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
	public String getAccessStatus(String userId) {
		// TODO Auto-generated method stub
		String access="0";
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		List<DoctorCreate> datalist=new ArrayList<DoctorCreate>();

		try{
			tx=session.getTransaction();
			tx.begin();

			String sql="select a.edit,a.clear,a.refund,a.discount from  tbRolePermission a join tbUserRole b on b.roleId=a.roleid where b.userId='"+userId+"' and a.sub=(select id from TbSubMenu where links='lab_billing')";
			List<?> list = session.createSQLQuery(sql).list();
			for(Iterator<?> iter = list.iterator(); iter.hasNext();)
			{	
				Object[] element = (Object[]) iter.next();
				access=element[0].toString()+":"+element[1].toString()+":"+element[2].toString()+":"+element[3].toString();
			
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
		return access;
	}


}
