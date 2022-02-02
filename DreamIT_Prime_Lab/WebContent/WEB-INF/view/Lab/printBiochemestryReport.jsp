<%@ page contentType="application/pdf" %>

<%@ page trimDirectiveWhitespaces="true"%>


<%@ page import="net.sf.jasperreports.engine.design.JRDesignQuery" %>
<%@ page import="net.sf.jasperreports.engine.design.JasperDesign" %>
<%@ page import="net.sf.jasperreports.engine.xml.JRXmlLoader" %>
<%@page import="net.sf.jasperreports.engine.JRExporterParameter"%>
<%@page import="net.sf.jasperreports.engine.data.JRBeanCollectionDataSource"%>
<%@page import="net.sf.jasperreports.engine.JasperCompileManager"%>

<%@ page import="net.sf.jasperreports.engine.*" %>
	

<%@page import="java.util.StringTokenizer"%>
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

	

	String headId = (String)request.getAttribute("headId"); 
	String fiscalYear = (String)request.getAttribute("fiscalYear"); 
	String cMonth = (String)request.getAttribute("cMonth"); 
	String labId = (String)request.getAttribute("labbill"); 
	String testIdlist = (String)request.getAttribute("testIdlist"); 
 	
	String note = (String)request.getAttribute("note");  
	String inchargeId = (String)request.getAttribute("inchargeId"); 
	String doctor1 = (String)request.getAttribute("doctor1"); 
	String doctor2 = (String)request.getAttribute("doctor2"); 
	
	String machineId = (String)request.getAttribute("machineId"); 
	String titleId = (String)request.getAttribute("titleId"); 
	
	System.out.println("machineId "+machineId);
	System.out.println("titleId "+titleId);
	
    try {
    	JasperPrint jp=null;
    	HashMap map=null;
    	List<HashMap<String,String>> datalist=new ArrayList<HashMap<String,String>>();
    	Session dbsession=HibernateUtil.openSession();
		Transaction tx=null;
		
		int result=0;
		String Name="";
		String Age="";
		String Month="";
		String Day="";
		String Reg="";
		String Cabin="";
		String Sex="";
		String Degree="";
		String ConsultantName="";
		String Ordate="";
		String UserName="";
		String normalRange="1";
		String testNameList="";
		String tempTestName="";
		datalist.clear();
		String Sql = "select RegNo,PatientName,Age,Month,Day,Sex,(select name+'. '+Degree+'' from TbDoctorInfo where DoctorId=TbLabPatient.RefferBy) as RefferDegree,convert(varchar,DateTime,105) as DateTime from TbLabPatient where FiscalYear='"+fiscalYear+"' and labId='"+labId+"' and cMonth='"+cMonth+"'";
		System.out.println(Sql);;
		List<?> list1 = dbsession.createSQLQuery(Sql).list();
		for(Iterator<?> iter = list1.iterator(); iter.hasNext();)
		{
			Object[] element = (Object[]) iter.next();

			Reg=element[0].toString();
			Name=element[1].toString();
			Age=element[2].toString();
			Month=element[3].toString();
			Day=element[4].toString();
			Sex=element[5].toString();
			ConsultantName=element[6].toString();
			Ordate=element[7].toString();
			break;
		}
		
		
		System.out.println("testIdlist "+testIdlist);
		
		testIdlist=testIdlist.substring(1,testIdlist.length()-1);
		
		int check=0;
		Sql="select RId,TestName,Unit,NormalRange,Result,CalculateValue,Flag,ParentTestName,Sorting from funPathologyReportResult('"+testIdlist+"','"+fiscalYear+"','"+labId+"','"+cMonth+"','"+headId+"') order by Sorting";
		
		List<?> tlist = dbsession.createSQLQuery(Sql).list();
		
		for(Iterator<?> iter = tlist.iterator(); iter.hasNext();)
		{
			Object[] element = (Object[]) iter.next();
			map=new HashMap();
			
			
			if(!element[5].toString().equals(" 00")){
				normalRange=element[5].toString();
			}
			
			if(check==0){
				tempTestName=element[7].toString();
				testNameList=testNameList+element[7].toString()+",";
			}
			
			if(tempTestName.equals(element[7].toString())){
				check++;
			}
			else{
				tempTestName=element[7].toString();
				testNameList=testNameList+element[7].toString()+",";
			}
			
			
			map.put("LabNo",fiscalYear+"00"+labId);
			map.put("PatientName",Name);
			Age=!Age.equals("")?Age+"Y":"";
			Month=!Month.equals("")?Month+"M":"";
			Day=!Day.equals("")?Day+"D":"";
			map.put("Age",Age+" "+Month+" "+Day);
			map.put("RegNo",Reg);
			map.put("CabinNo",Cabin);
			map.put("Gender",Sex);
			map.put("Sample","");
			map.put("OrderDate",Ordate);
			map.put("ReportingDate",new SimpleDateFormat("dd-MM-yyyy").format(new Date()));	
			map.put("Consultant",ConsultantName);
			map.put("Degree",Degree);
			
			
			
			map.put("TestName",element[1].toString());
			System.out.println("Unit "+element[2].toString());
			System.out.println("NormalRange "+element[3].toString());
			map.put("Unit",element[2].toString());
			map.put("NormalRange",element[3].toString());
			map.put("Result",element[4].toString());
			map.put("CalculateValue",element[5].toString());
			map.put("Flag",element[6].toString());
			

			
			
		///	map.put("MainTestName",element[7].toString());
			map.put("TestList","");
			map.put("Note",note);
			map.put("Sample","Blood");
			map.put("username",UserName);
			
			datalist.add(map);
			result++;
		}
		

		
		if(testNameList.length()>0){
			testNameList=testNameList.substring(0, testNameList.length()-1);
		}
	
		
		System.out.println("testNameList "+testNameList);
		map.put("testNameList",testNameList);
	
		if(!machineId.equals("0")){
			Sql = "select MachineName from TbMachineInfo where MachineId='"+machineId+"'";
			System.out.println(Sql);;
			List<?> list2 = dbsession.createSQLQuery(Sql).list();
			for(Iterator<?> iter = list2.iterator(); iter.hasNext();)
			{
			
				String machineame=iter.next().toString();
				map.put("machineName",machineame);
				
			}
		}

		if(!titleId.equals("0")){
			Sql = "select TitleName from TbLabReportTitleInfo where TitleId='"+titleId+"'";
			System.out.println(Sql);;
			List<?> list2 = dbsession.createSQLQuery(Sql).list();
			for(Iterator<?> iter = list2.iterator(); iter.hasNext();)
			{

				String titleName=iter.next().toString();
				map.put("title",titleName);
				
			}
		}
		
		if(result!=0){
			Sql = "select line1,line2,line3,line4,ISNULL(line5,'') as line5 from tbLabInchargeConsultantDegree where id = '"+inchargeId+"'";
			System.out.println(Sql);;
			List<?> list2 = dbsession.createSQLQuery(Sql).list();
			for(Iterator<?> iter = list2.iterator(); iter.hasNext();)
			{
				Object[] element = (Object[]) iter.next();

				
				map.put("inchargel1",element[0].toString());
				map.put("inchargel2",element[1].toString());
				map.put("inchargel3",element[2].toString());
				map.put("inchargel4",element[3].toString());
				map.put("inchargel5",element[4].toString());


			}
			Sql = "select line1,line2,line3,line4,line5 from tbLabInchargeConsultantDegree where id = '"+doctor1+"'";
			System.out.println(Sql);;
			List<?> list3 = dbsession.createSQLQuery(Sql).list();
			for(Iterator<?> iter = list3.iterator(); iter.hasNext();)
			{
				Object[] element = (Object[]) iter.next();
				map.put("doctorl1",element[0].toString());
				map.put("doctorl2",element[1].toString());
				map.put("doctorl3",element[2].toString());
				map.put("doctorl4",element[3].toString());
				map.put("doctorl5",element[4].toString());

			}

			Sql = "select line1,line2,line3,line4,line5 from tbLabInchargeConsultantDegree where id = '"+doctor2+"'";
			System.out.println(Sql);;
			List<?> list4 = dbsession.createSQLQuery(Sql).list();
			for(Iterator<?> iter = list4.iterator(); iter.hasNext();)
			{
				Object[] element = (Object[]) iter.next();
				map.put("doctorl_b1",element[0].toString());
				map.put("doctorl_b2",element[1].toString());
				map.put("doctorl_b3",element[2].toString());
				map.put("doctorl_b4",element[3].toString());
				map.put("doctorl_b5",element[4].toString());
				
			}
			System.out.println("map "+map);
		}
		
		String jrxmlFile="";
		if(normalRange.equals("1")){
			jrxmlFile=session.getServletContext().getRealPath("WEB-INF/jasper/LabResultReport/BioChemistry2Col.jrxml");
		}
		else{
			jrxmlFile=session.getServletContext().getRealPath("WEB-INF/jasper/LabResultReport/BioChemistry3ColWithOutGroup.jrxml");
		}
		
		System.out.println("jrxmlFile "+jrxmlFile);
		InputStream input = new FileInputStream(new File(jrxmlFile));
	 	JasperDesign jd=JRXmlLoader.load(input);
	 	
		JasperReport com=JasperCompileManager.compileReport(jd);
		jp = JasperFillManager.fillReport(com, map, new JRBeanCollectionDataSource(datalist));
	
		
	    JasperExportManager.exportReportToPdfStream(jp, response.getOutputStream());
	    datalist.clear();

    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (JRException e) {
        e.printStackTrace();
    } 
    


%>