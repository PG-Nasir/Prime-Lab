<%@ page contentType="application/pdf" %>

<%@ page trimDirectiveWhitespaces="true"%>

<%@page import="java.util.StringTokenizer"%>
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

String labId = (String)request.getAttribute("labId"); 
String fiscalYear = (String)request.getAttribute("fiscalYear"); 
String cMonth = (String)request.getAttribute("cMonth"); 





	
    try {
    	JasperPrint jp=null;
    	HashMap map=new HashMap();
    	List<HashMap<String,String>> datalist=new ArrayList<HashMap<String,String>>();
    	Session dbsession=HibernateUtil.openSession();
		Transaction tx=null;
		
		String QuantityV="",ColourV="",AppearanceV="",SedimentV="",UserName="";
		String QuantityR="",ColourR="",AppearanceR="",SedimentR="";
		String SpGravityV="",pHV="",SugarV="",AlbuminV="",KetoneBodiesV="",BloddV="",BilirubinV="",UrobilinogenV="",NitriteV="",BilePigmentV="",BileSaltV="",ExsPhosphateV="";
		String SpGravityR="",pHR="",SugarR="",AlbuminR="",KetoneBodiesR="",BloddR="",BilirubinR="",UrobilinogenR="",NitriteR="",BilePigmentR="",BileSaltR="",ExsPhosphateR="";
		String HyalineCastV="",GranularCastV="",FattyV="",WBCV="",RBCCastV="";
		String HyalineCastR="",GranularCastR="",FattyR="",WBCR="",RBCCastR="";
		String RBCV="",PusCellsV="",EpithelialCellsV="",MucusV="",SpermatozoaV="",ParasitesV="",MicroOrgV="",YeastV="";
		String RBCR="",PusCellsR="",EpithelialCellsR="",MucusR="",SpermatozoaR="",ParasitesR="",MicroOrgR="",YeastR="";
		String CalciumOxV="",UricAcid="",Urate="",TriplePhosphate="",AmrPhosphate="";
		String CalciumOxR="",UricAcidR="",UrateR="",TriplePhosphateR="",AmrPhosphateR="";
		
        
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
		String testNameList="";
	
		datalist.clear();
		String Sql = "select RegNo,PatientName,Age,Month,Day,Sex,(select name+' ('+Degree+')' from TbDoctorInfo where DoctorId=TbLabPatient.RefferBy) as RefferDegree,convert(varchar,DateTime,105) as DateTime from TbLabPatient where FiscalYear='"+fiscalYear+"' and labId='"+labId+"'";
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
		map.put("OrderDate",Ordate);
		map.put("ReportingDate",new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
		map.put("Consultant",ConsultantName);
		map.put("Degree",Degree);
		map.put("Sample","Urine");
		map.put("username",UserName);
		

		
		datalist.add(map);
		result++;
		
		map.put("testNameList","");
		
		if(result!=0){
			
			map.put("inchargel1","");
			map.put("inchargel2","");
			map.put("inchargel3","");
			map.put("inchargel4","");
			map.put("inchargel5","");
			
	/* 		Sql = "select line1,line2,line3,line4,ISNULL(line5,'') as line5 from tbLabInchargeConsultantDegree where id = '"+inchargeId+"'";
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


			} */
			
			map.put("doctorl1","");
			map.put("doctorl2","");
			map.put("doctorl3","");
			map.put("doctorl4","");
			map.put("doctorl5","");
			
/* 			Sql = "select line1,line2,line3,line4,line5 from tbLabInchargeConsultantDegree where id = '"+doctor1+"'";
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

			} */

			map.put("doctorl_b1","");
			map.put("doctorl_b2","");
			map.put("doctorl_b3","");
			map.put("doctorl_b4","");
			map.put("doctorl_b5","");
			
/* 			Sql = "select line1,line2,line3,line4,line5 from tbLabInchargeConsultantDegree where id = '"+doctor2+"'";
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
				
			} */
			System.out.println("map "+map);
		}
		
		String jrxmlFile=session.getServletContext().getRealPath("WEB-INF/jasper/LabResultReport/TopBill.jrxml");
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