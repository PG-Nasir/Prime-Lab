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

String headId = (String)request.getAttribute("headId"); 
String fiscalYear = (String)request.getAttribute("fiscalYear"); 
String cMonth = (String)request.getAttribute("cMonth"); 
String labId = (String)request.getAttribute("labbill"); 
String testId = (String)request.getAttribute("testId"); 

String inchargeId = (String)request.getAttribute("inchargeId"); 
String doctor1 = (String)request.getAttribute("doctor1"); 
String doctor2 = (String)request.getAttribute("doctor2"); 

String machineId = (String)request.getAttribute("machineId"); 
String titleId = (String)request.getAttribute("titleId"); 
	
    try {
    	JasperPrint jp=null;
    	HashMap map=new HashMap();
    	List<HashMap<String,String>> datalist=new ArrayList<HashMap<String,String>>();
    	Session dbsession=HibernateUtil.openSession();
		Transaction tx=null;
		
		String UserName="",ConsistenctyV="",colourV="",OdourV="",MucusV="",BloodV="",HelminthsV="",ConsultantNameV="",DegreeV="",UserNameV="";
		String ReactionV="",RedcuingSubstanceV="",OccultBloodTestV="";
		String ProtozoaV="",CystsOfEHV="",CystsOfEColtV="",CystsOfEGiardiaV="",OvaOfALV="",OvaOfADV="",OvaOfTTV="",LarvaOfSSV="",MacrophageV="";
		String StrachV="",VegetiableCellsV="",PusCellV="",EpithelialV="",FatDropleteV="",FungiV="",WBCV="",RBCV="",YeastV="",MuscleV="",CharcotV="";
		
        
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
		String TestName="";
		
		datalist.clear();
		String Sql = "select RegNo,PatientName,Age,Month,Day,Sex,(select name+'. '+Degree+'' from TbDoctorInfo where DoctorId=TbLabPatient.RefferBy) as RefferDegree,convert(varchar,DateTime,105) as DateTime from TbLabPatient where FiscalYear='"+fiscalYear+"' and cMonth='"+cMonth+"' and labId='"+labId+"'";
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
		
		Sql="select tblabreportvalue.value,tblabreporthead.Name,tblabreporthead.Catagory,(select name from tblogin where id=tblabreportvalue.createBy) as FullName,'' as DegreeName,(select username from tblogin where id=tblabreportvalue.createBy) as username,(select testName from TbTestName where TestId=tblabreportvalue.TestId) as TestName  from tblabreporthead join tblabreportvalue on tblabreportvalue.rId=tblabreporthead.Id where tblabreportvalue.FiscalYear='"+fiscalYear+"' and tblabreportvalue.CMonth='"+cMonth+"' and tblabreportvalue.LabId='"+labId+"' and tblabreportvalue.HeadId='"+headId+"' and testId='"+testId+"'";
		
		List<?> list = dbsession.createSQLQuery(Sql).list();
		
		for(Iterator<?> iter = list.iterator(); iter.hasNext();)
		{
			Object[] element = (Object[]) iter.next();
			
			UserName=element[5].toString();
			TestName=element[6].toString();
			
			if(element[1].toString().equals("Consistency")){
				ConsistenctyV=element[0].toString();
			}
			else if(element[1].toString().equals("Colour")){
				colourV=element[0].toString();
			}
			else if(element[1].toString().equals("Odour")){
				OdourV=element[0].toString();
			}
			else if(element[1].toString().equals("Mucus")){
				MucusV=element[0].toString();
			}
			else if(element[1].toString().equals("Blood")){
				BloodV=element[0].toString();
			}
			else if(element[1].toString().equals("Helminths")){
				HelminthsV=element[0].toString();
			}
			else if(element[1].toString().equals("Reaction")){
				ReactionV=element[0].toString();
			}
			else if(element[1].toString().equals("Reducing Substance")){
				RedcuingSubstanceV=element[0].toString();
			}
			else if(element[1].toString().equals("Occult Blood Test")){
				OccultBloodTestV=element[0].toString();
			}
			else if(element[1].toString().equals("Protozoa")){
				ProtozoaV=element[0].toString();
			}
			else if(element[1].toString().equals("Cysts Of E.H")){
				CystsOfEHV=element[0].toString();
			}
			else if(element[1].toString().equals("Cysts Of E.Colt")){
				CystsOfEColtV=element[0].toString();
			}
			else if(element[1].toString().equals("Cysts Of Giardia")){
				CystsOfEGiardiaV=element[0].toString();
			}
			else if(element[1].toString().equals("Ova Of (Round Worm) A.L")){
				OvaOfALV=element[0].toString();
			}
			else if(element[1].toString().equals("Ova Of (Hook Work) A.D")){
				OvaOfADV=element[0].toString();
			}
			else if(element[1].toString().equals("Ova Of (Whip Worm) T.T")){
				OvaOfTTV=element[0].toString();
			}
			else if(element[1].toString().equals("Larva Of S.S")){
				LarvaOfSSV=element[0].toString();
			}
			else if(element[1].toString().equals("Macrophage")){
				MacrophageV=element[0].toString();
			}
			else if(element[1].toString().equals("Stract")){
				StrachV=element[0].toString();
			}
			else if(element[1].toString().equals("Vagetitable Cells")){
				VegetiableCellsV=element[0].toString();
			}
			else if(element[1].toString().equals("Pus Cells")){
				PusCellV=element[0].toString();
			}
			else if(element[1].toString().equals("Epithelial")){
				EpithelialV=element[0].toString();
			}
			else if(element[1].toString().equals("Fat Droplets")){
				FatDropleteV=element[0].toString();
			}
			else if(element[1].toString().equals("Fungi")){
				FungiV=element[0].toString();
			}
			else if(element[1].toString().equals("WBC")){
				WBCV=element[0].toString();
			}
			else if(element[1].toString().equals("RBC")){
				RBCV=element[0].toString();
			}
			else if(element[1].toString().equals("Yeast")){
				YeastV=element[0].toString();
			}
			else if(element[1].toString().equals("Muscle Fibers")){
				MuscleV=element[0].toString();
			}
			else if(element[1].toString().equals("Charcot-Leyden Crystals")){
				CharcotV=element[0].toString();
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
		map.put("OrderDate",Ordate);
		map.put("ReportingDate",new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
		map.put("Consultant",ConsultantName);
		map.put("Degree",Degree);
		
		
		
		map.put("TestList",TestName);
		map.put("Sample","Stool");
		map.put("username",UserName);
		
		


		map.put("ConsistencyV",ConsistenctyV);
		System.out.println("ColorV "+colourV);
		map.put("ColorV",colourV);
		map.put("OdourV",OdourV);
		map.put("MucusV",MucusV);
		map.put("BloodV",BloodV);
		map.put("HelminthsV",HelminthsV);
		
		
		map.put("ReactionV",ReactionV);
		map.put("OccultV",OccultBloodTestV);
		map.put("ReducingV",RedcuingSubstanceV);
		
		
		map.put("ProtozoaV",ProtozoaV);
		
		System.out.println("CystsOfEHV "+CystsOfEHV);
		System.out.println("CystsOfEColtV "+CystsOfEColtV);
		map.put("CystsOfEHV",CystsOfEHV);
		map.put("CystsOfEColtV",CystsOfEColtV);
		map.put("CystsOfGiardiaV",CystsOfEGiardiaV);
		map.put("OvaOfALV",OvaOfALV);
		map.put("OvaOfADV",OvaOfADV);
		map.put("OvaOfTTV",OvaOfTTV);
		map.put("LarvaOfSSV",LarvaOfSSV);
		map.put("MacrophageV",MacrophageV);
		
		
		map.put("StrachV",StrachV);
		map.put("VegitablleV",VegetiableCellsV);
		map.put("PusCellV",PusCellV);
		map.put("EpithelialCellsV",EpithelialV);
		map.put("FatDropletsV",FatDropleteV);
		map.put("FungiV",FungiV);
		map.put("WBCV",WBCV);
		map.put("RBCV",RBCV);
		map.put("YeastV",YeastV);
		map.put("MuscleFiberV",MuscleV);
		map.put("CharcotV",CharcotV);
		
		map.put("Top", true);
		map.put("testName", true);
		map.put("result", true);
		map.put("Footer",true);
		
		
		datalist.add(map);
		result++;
		
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
		
		String jrxmlFile=session.getServletContext().getRealPath("WEB-INF/jasper/LabResultReport/Stool4Col.jrxml");
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