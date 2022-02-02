<%@ page contentType="application/pdf" %>

<%@ page trimDirectiveWhitespaces="true"%>


<%@ page import="net.sf.jasperreports.engine.design.JRDesignQuery" %>
<%@ page import="net.sf.jasperreports.engine.design.JasperDesign" %>
<%@ page import="net.sf.jasperreports.engine.xml.JRXmlLoader" %>
<%@page import="net.sf.jasperreports.engine.JRExporterParameter"%>
<%@page import="net.sf.jasperreports.engine.data.JRBeanCollectionDataSource"%>
<%@page import="net.sf.jasperreports.engine.JasperCompileManager"%>

<%@ page import="net.sf.jasperreports.engine.*" %>

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.util.HashMap"%>
<%@ page import="pg.config.*" %>
<%@page import="org.hibernate.Session"%>
<%@page import="org.hibernate.Transaction"%>
<%@page import="pg.share.HibernateUtil"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>

<%@ page import="java.io.File" %>
<%@ page import="java.io.FileInputStream" %>
<%@ page import="java.io.FileNotFoundException" %>
<%@ page import="java.io.InputStream" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.SQLException" %>



<%

	
	String fiscalYear = (String)request.getAttribute("fiscalYear"); 
	String labId = (String)request.getAttribute("labbill"); 
	String testId = (String)request.getAttribute("testId"); 
	String headId = (String)request.getAttribute("headId"); 
	String Name = (String)request.getAttribute("Name"); 
	String Age = (String)request.getAttribute("Age"); 
	String Month = (String)request.getAttribute("Month"); 
	String Day = (String)request.getAttribute("Day"); 
	String Reg = (String)request.getAttribute("Reg"); 
	String Cabin = (String)request.getAttribute("Cabin"); 
	String Sex = (String)request.getAttribute("Sex"); 
	String Degree = (String)request.getAttribute("Degree"); 
	System.out.println(" dr degree "+Degree);
	String ConsultantName = (String)request.getAttribute("ConsultantName"); 
	String ordate = (String)request.getAttribute("ordate"); 
	String TestName = (String)request.getAttribute("TestName"); 
	
    try {
    	JasperPrint jp=null;
    	HashMap map=new HashMap();
    	List<HashMap<String,String>> datalist=new ArrayList<HashMap<String,String>>();
    	Session dbsession=HibernateUtil.openSession();
		Transaction tx=null;
		
		String UserName="",OrganismA="",OrganismB="";
		String AmikacinA="",AmoxycillinA="",AmoxyClaveA="",AzithromkyciA="",AztreonamA="",CefaclorA="",CefeprimeA="",CefiximeA="",CefotazimeA="",CefoxitinA="",CeftaidimeA="",CeftraixoneA="",CefuroximeA="";
		String AmikacinB="",AmoxycillinB="",AmoxyClaveB="",AzithromkyciB="",AztreonamB="",CefaclorB="",CefeprimeB="",CefiximeB="",CefotazimeB="",CefoxitinB="",CeftaidimeB="",CeftraixoneB="",CefuroximeB="";
		String CephradineA="",ChloramphenicolA="",CloxacillinA="",CiprofloxacinA="",ColistinA="",CoTrimoxazoleA="",DoxkycyclineA="",ErythromycinA="",GentamicinA="",ImipenemA="",LevofloxacinA="",LinezolidA="",MecillinamA="";
		String CephradineB="",ChloramphenicolB="",CloxacillinB="",CiprofloxacinB="",ColistinB="",CoTrimoxazoleB="",DoxkycyclineB="",ErythromycinB="",GentamicinB="",ImipenemB="",LevofloxacinB="",LinezolidB="",MecillinamB="";
		String MeropenemA="",NalidixicA="",NeomycinA="",NetilmicinA="",NitrofurantoinA="",PenicillinA="",PiperacililnA="",TazobactumA="",PolymyxinA="",TetracyclineA="",VancomycinA="";
		String MeropenemB="",NalidixicB="",NeomycinB="",NetilmicinB="",NitrofurantoinB="",PenicillinB="",PiperacililnB="",TazobactumB="",PolymyxinB="",TetracyclineB="",VancomycinB="";
		String Puscells="",EpithelesCells="",Result="";
		
		
        
		String Sql="select tblabreportvalue.rId,tblabreportvalue.value,isnull(tblabreporthead.Ranges,''),tblabreporthead.Name,tblabreporthead.Catagory,(select name from tblogin where id=tblabreportvalue.createBy) as FullName,(select Degree from tblogin where id=tblabreportvalue.createBy) as DegreeName,(select username from tblogin where id=tblabreportvalue.createBy) as username from tblabreporthead join tblabreportvalue on tblabreportvalue.rId=tblabreporthead.Id where tblabreportvalue.FiscalYear='"+fiscalYear+"' and tblabreportvalue.LabId='"+labId+"' and tblabreportvalue.HeadId='"+headId+"' and testId='"+testId+"' order by Catagory,rId asc";
		
		List<?> list = dbsession.createSQLQuery(Sql).list();
		
		for(Iterator<?> iter = list.iterator(); iter.hasNext();)
		{
			Object[] element = (Object[]) iter.next();
			
			

			UserName=element[7].toString();
			
			if(Integer.parseInt(element[0].toString())==170){
				AmikacinA=element[1].toString();
			}
			else if(Integer.parseInt(element[0].toString())==171){
				AmoxycillinA=element[1].toString();
			}
			else if(Integer.parseInt(element[0].toString())==172){
				AmoxyClaveA=element[1].toString();
			}
			else if(Integer.parseInt(element[0].toString())==173){
				AzithromkyciA=element[1].toString();
			}
			else if(Integer.parseInt(element[0].toString())==174){
				AztreonamA=element[1].toString();
			}
			else if(Integer.parseInt(element[0].toString())==175){
				CefaclorA=element[1].toString();
			}
			else if(Integer.parseInt(element[0].toString())==176){
				CefeprimeA=element[1].toString();
			}
			else if(Integer.parseInt(element[0].toString())==177){
				CefiximeA=element[1].toString();
			}
			else if(Integer.parseInt(element[0].toString())==178){
				CefotazimeA=element[1].toString();
			}
			else if(Integer.parseInt(element[0].toString())==179){
				CefoxitinA=element[1].toString();
			}
			else if(Integer.parseInt(element[0].toString())==180){
				CeftaidimeA=element[1].toString();
			}
			else if(Integer.parseInt(element[0].toString())==181){
				CeftraixoneA=element[1].toString();
			}
			else if(Integer.parseInt(element[0].toString())==182){
				CefuroximeA=element[1].toString();
			}

			else if(Integer.parseInt(element[0].toString())==183){
				CephradineA=element[1].toString();
			}

			else if(Integer.parseInt(element[0].toString())==184){
				ChloramphenicolA=element[1].toString();
			}
			else if(Integer.parseInt(element[0].toString())==185){
				CloxacillinA=element[1].toString();
			}
			else if(Integer.parseInt(element[0].toString())==186){
				CiprofloxacinA=element[1].toString();
			}
			else if(Integer.parseInt(element[0].toString())==187){
				ColistinA=element[1].toString();
			}
			else if(Integer.parseInt(element[0].toString())==188){
				CoTrimoxazoleA=element[1].toString();
			}
			else if(Integer.parseInt(element[0].toString())==189){
				DoxkycyclineA=element[1].toString();
			}
			else if(Integer.parseInt(element[0].toString())==190){
				ErythromycinA=element[1].toString();
			}
			else if(Integer.parseInt(element[0].toString())==191){
				GentamicinA=element[1].toString();
			}
			else if(Integer.parseInt(element[0].toString())==192){
				ImipenemA=element[1].toString();
			}
			else if(Integer.parseInt(element[0].toString())==193){
				LevofloxacinA=element[1].toString();
			}
			else if(Integer.parseInt(element[0].toString())==194){
				LinezolidA=element[1].toString();
			}
			else if(Integer.parseInt(element[0].toString())==195){
				MecillinamA=element[1].toString();
			}
			else if(Integer.parseInt(element[0].toString())==196){
				MeropenemA=element[1].toString();
			}
			else if(Integer.parseInt(element[0].toString())==197){
				NalidixicA=element[1].toString();
			}
			else if(Integer.parseInt(element[0].toString())==198){
				NeomycinA=element[1].toString();
			}
			else if(Integer.parseInt(element[0].toString())==199){
				NetilmicinA=element[1].toString();
			}
			else if(Integer.parseInt(element[0].toString())==200){
				NitrofurantoinA=element[1].toString();
			}
			else if(Integer.parseInt(element[0].toString())==201){
				PenicillinA=element[1].toString();
			}
			else if(Integer.parseInt(element[0].toString())==202){
				PiperacililnA=element[1].toString();
			}
			else if(Integer.parseInt(element[0].toString())==203){
				TazobactumA=element[1].toString();
			}
			else if(Integer.parseInt(element[0].toString())==204){
				PolymyxinA=element[1].toString();
			}
			else if(Integer.parseInt(element[0].toString())==205){
				TetracyclineA=element[1].toString();
			}
			else if(Integer.parseInt(element[0].toString())==206){
				VancomycinA=element[1].toString();
			}
			//B---
			else if(Integer.parseInt(element[0].toString())==207){
				AmikacinB=element[1].toString();
			}
			else if(Integer.parseInt(element[0].toString())==208){
				AmoxycillinB=element[1].toString();
			}
			else if(Integer.parseInt(element[0].toString())==209){

				AmoxyClaveB=element[1].toString();
			}
			else if(Integer.parseInt(element[0].toString())==210){
				AzithromkyciB=element[1].toString();
			}
			else if(Integer.parseInt(element[0].toString())==211){
				AztreonamB=element[1].toString();
			}
			else if(Integer.parseInt(element[0].toString())==212){
				CefaclorB=element[1].toString();
			}
			else if(Integer.parseInt(element[0].toString())==213){
				CefeprimeB=element[1].toString();
			}
			else if(Integer.parseInt(element[0].toString())==214){
				CefiximeB=element[1].toString();
			}
			else if(Integer.parseInt(element[0].toString())==215){
				CefotazimeB=element[1].toString();
			}
			else if(Integer.parseInt(element[0].toString())==216){
				CefoxitinB=element[1].toString();
			}
			else if(Integer.parseInt(element[0].toString())==217){
				CeftaidimeB=element[1].toString();
			}
			else if(Integer.parseInt(element[0].toString())==218){
				CeftraixoneB=element[1].toString();
			}
			else if(Integer.parseInt(element[0].toString())==219){
				CefuroximeB=element[1].toString();
			}
			else if(Integer.parseInt(element[0].toString())==220){
				CephradineB=element[1].toString();
			}
			else if(Integer.parseInt(element[0].toString())==221){
				ChloramphenicolB=element[1].toString();
			}
			else if(Integer.parseInt(element[0].toString())==222){
				CloxacillinB=element[1].toString();
			}
			else if(Integer.parseInt(element[0].toString())==223){
				CiprofloxacinB=element[1].toString();
			}
			else if(Integer.parseInt(element[0].toString())==224){

				ColistinB=element[1].toString();
			}
			else if(Integer.parseInt(element[0].toString())==225){

				CoTrimoxazoleB=element[1].toString();
			}
			else if(Integer.parseInt(element[0].toString())==226){
				DoxkycyclineB=element[1].toString();
			}
			else if(Integer.parseInt(element[0].toString())==227){
				ErythromycinB=element[1].toString();
			}
			else if(Integer.parseInt(element[0].toString())==228){
				GentamicinB=element[1].toString();
			}
			else if(Integer.parseInt(element[0].toString())==229){
				ImipenemB=element[1].toString();
			}
			else if(Integer.parseInt(element[0].toString())==230){
				LevofloxacinB=element[1].toString();
			}
			else if(Integer.parseInt(element[0].toString())==231){
				LinezolidB=element[1].toString();
			}
			else if(Integer.parseInt(element[0].toString())==232){
				MecillinamB=element[1].toString();
			}
			else if(Integer.parseInt(element[0].toString())==233){
				MeropenemB=element[1].toString();
			}
			else if(Integer.parseInt(element[0].toString())==234){
				NalidixicB=element[1].toString();
			}
			else if(Integer.parseInt(element[0].toString())==235){
				NeomycinB=element[1].toString();
			}
			else if(Integer.parseInt(element[0].toString())==236){
				NetilmicinB=element[1].toString();
			}
			else if(Integer.parseInt(element[0].toString())==237){
				NitrofurantoinB=element[1].toString();
			}
			else if(Integer.parseInt(element[0].toString())==238){
				PenicillinB=element[1].toString();
			}
			else if(Integer.parseInt(element[0].toString())==239){
				PiperacililnB=element[1].toString();
			}
			else if(Integer.parseInt(element[0].toString())==240){
				TazobactumB=element[1].toString();
			}
			else if(Integer.parseInt(element[0].toString())==241){
				PolymyxinB=element[1].toString();
			}
			else if(Integer.parseInt(element[0].toString())==242){
				TetracyclineB=element[1].toString();
			}
			else if(Integer.parseInt(element[0].toString())==243){
				VancomycinB=element[1].toString();
			}
			else if(Integer.parseInt(element[0].toString())==244){
				OrganismA=element[1].toString();
			}
			else if(Integer.parseInt(element[0].toString())==245){
				OrganismB=element[1].toString();
			}

			else if(Integer.parseInt(element[0].toString())==251){
				Puscells=element[1].toString();
			}
			else if(Integer.parseInt(element[0].toString())==252){
				EpithelesCells=element[1].toString();
			}
			else if(Integer.parseInt(element[0].toString())==253){
				Result=element[1].toString();
			}
			

		}	
		
		map.put("Top", true);
		map.put("testName", true);
		map.put("result", true);
		map.put("Footer", true);
		map.put("LabNo",fiscalYear+"00"+labId);
		map.put("PatientName",Name);
		Age=!Age.equals("")?Age+"Y":"";
		Month=!Month.equals("")?Month+"M":"";
		Day=!Day.equals("")?Day+"D":"";
		map.put("Age",Age+" "+Month+" "+Day);
		map.put("Gender",Sex);
		
		map.put("RegNo",Reg);
		map.put("CabinNo",Cabin);
		System.out.println("Consultant "+ConsultantName);
		System.out.println("Degree "+Degree);
		map.put("OrderDate",new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
		map.put("ReportingDate",new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
		map.put("Consultant",ConsultantName);
		map.put("Degree",Degree);
		

		map.put("AmikacinA", AmikacinA);
		map.put("AmikacinB", AmikacinB);
		map.put("AmoxycillinA", AmoxycillinA);
		map.put("AmoxycillinB", AmoxycillinB);
		map.put("AmoxyClaveA", AmoxyClaveA);
		map.put("AmoxyClaveB", AmoxyClaveB);
		map.put("AzithromkyciA", AzithromkyciA);
		map.put("AzithromkyciB", AzithromkyciB);
		map.put("AztreonamA", AztreonamA);
		map.put("AztreonamB", AztreonamB);
		map.put("CefaclorA", CefaclorA);
		map.put("CefaclorB", CefaclorB);
		map.put("CefeprimeA", CefeprimeA);
		map.put("CefeprimeB", CefeprimeB);
		map.put("CefiximeA", CefiximeA);
		map.put("CefiximeB", CefiximeB);
		map.put("CefotazimeA", CefotazimeA);
		map.put("CefotazimeB", CefotazimeB);
		map.put("CefoxitinA", CefoxitinA);
		map.put("CefoxitinB", CefoxitinB);
		map.put("CeftaidimeA", CeftaidimeA);
		map.put("CeftaidimeB", CeftaidimeB);
		map.put("CeftraixoneA", CeftraixoneA);
		map.put("CeftraixoneB", CeftraixoneB);
		map.put("CefuroximeA", CefuroximeA);
		map.put("CefuroximeB", CefuroximeB);

		map.put("CephradineA", CephradineA);
		map.put("CephradineB", CephradineB);
		map.put("ChloramphenicolA", ChloramphenicolA);
		map.put("ChloramphenicolB", ChloramphenicolB);
		map.put("CloxacillinA", CloxacillinA);
		map.put("CloxacillinB", CloxacillinB);
		map.put("CiprofloxacinA", CiprofloxacinA);
		map.put("CiprofloxacinB", CiprofloxacinB);
		map.put("ColistinA", ColistinA);
		map.put("ColistinB", ColistinB);
		map.put("CoTrimoxazoleA", CoTrimoxazoleA);
		map.put("CoTrimoxazoleB", CoTrimoxazoleB);

		map.put("DoxkycyclineA", DoxkycyclineA);
		map.put("DoxkycyclineB", DoxkycyclineB);
		map.put("ErythromycinA", ErythromycinA);
		map.put("ErythromycinB", ErythromycinB);
		map.put("GentamicinA", GentamicinA);
		map.put("GentamicinB", GentamicinB);
		map.put("ImipenemA", ImipenemA);
		map.put("ImipenemB", ImipenemB);
		map.put("LevofloxacinA", LevofloxacinA);
		map.put("LevofloxacinB", LevofloxacinB);
		map.put("LinezolidA", LinezolidA);
		map.put("LinezolidB", LinezolidB);
		map.put("MecillinamA", MecillinamA);
		map.put("MecillinamB", MecillinamB);

		map.put("MeropenemA", MeropenemA);
		map.put("MeropenemB", MeropenemB);

		map.put("NalidixicA", NalidixicA);
		map.put("NalidixicB", NalidixicB);

		map.put("NeomycinA", NeomycinA);
		map.put("NeomycinB", NeomycinB);

		map.put("NetilmicinA", NetilmicinA);
		map.put("NetilmicinB", NetilmicinB);

		map.put("NitrofurantoinA", NitrofurantoinA);
		map.put("NitrofurantoinB", NitrofurantoinB);
		map.put("PenicillinA", PenicillinA);
		map.put("PenicillinB", PenicillinB);
		map.put("PiperacililnA", PiperacililnA);
		map.put("PiperacililnB", PiperacililnB);
		map.put("TazobactumA", TazobactumA);
		map.put("TazobactumB", TazobactumB);
		map.put("PolymyxinA", PolymyxinA);
		map.put("PolymyxinB", PolymyxinB);
		map.put("TetracyclineA", TetracyclineA);
		map.put("TetracyclineB", TetracyclineB);
		map.put("VancomycinA", VancomycinA);
		map.put("VancomycinB", VancomycinB);
		map.put("OrganismA", OrganismA);
		map.put("OrganismB", OrganismB);

		map.put("Puscells",Puscells);
		map.put("Epithelles",EpithelesCells);
		map.put("result1",Result);
		
		map.put("TestList",TestName);
		map.put("Sample","Urine");
		map.put("username",UserName);
		
		
		
		datalist.add(map);
		
		String jrxmlFile=session.getServletContext().getRealPath("WEB-INF/jasper/LabResultReport/MicrobiologyCulturaGrowth.jrxml");
		InputStream input = new FileInputStream(new File(jrxmlFile));
	 	JasperDesign jd=JRXmlLoader.load(input);
	 	
		JasperReport com=JasperCompileManager.compileReport(jd);
		jp = JasperFillManager.fillReport(com, map, new JRBeanCollectionDataSource(datalist));
	
		
	    JasperExportManager.exportReportToPdfStream(jp, response.getOutputStream());
		

    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (JRException e) {
        e.printStackTrace();
    } 
    

%>