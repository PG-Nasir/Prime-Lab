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
		
		String QuantityV="",ColourV="",AppearanceV="",SedimentV="",UserName="";
		String QuantityR="",ColourR="",AppearanceR="",SedimentR="";
		String SpGravityV="",pHV="",SugarV="",AlbuminV="",KetoneBodiesV="",BloddV="",BilirubinV="",UrobilinogenV="",NitriteV="",BilePigmentV="",BileSaltV="",ExsPhosphateV="";
		String SpGravityR="",pHR="",SugarR="",AlbuminR="",KetoneBodiesR="",BloddR="",BilirubinR="",UrobilinogenR="",NitriteR="",BilePigmentR="",BileSaltR="",ExsPhosphateR="";
		String HyalineCastV="",GranularCastV="",FattyV="",WBCV="",RBCCastV="";
		String HyalineCastR="",GranularCastR="",FattyR="",WBCR="",RBCCastR="";
		String RBCV="",PusCellsV="",EpithelialCellsV="",TrichomonasV="",SpermatozoaV="",ParasitesV="",MicroOrgV="",YeastV="";
		String RBCR="",PusCellsR="",EpithelialCellsR="",TrichomonasR="",SpermatozoaR="",ParasitesR="",MicroOrgR="",YeastR="";
		String CalciumOxV="",UricAcid="",Urate="",TriplePhosphate="",AmrPhosphate="";
		String CalciumOxR="",UricAcidR="",UrateR="",TriplePhosphateR="",AmrPhosphateR="",FungusR="",FungusV="";
		
        
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
		String Sql = "select RegNo,PatientName,Age,Month,Day,Sex,(select name+' ('+Degree+')' from TbDoctorInfo where DoctorId=TbLabPatient.RefferBy) as RefferDegree,convert(varchar,DateTime,105) as DateTime,(select TestName from tbTestName where TestId='"+testId+"') as TestName from TbLabPatient where FiscalYear='"+fiscalYear+"' and cMonth='"+cMonth+"' and labId='"+labId+"'";
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
			TestName=element[8].toString();
			break;
		}
		
		Sql="select tblabreportvalue.value,isnull(tblabreporthead.Ranges,0),tblabreporthead.Name,tblabreporthead.Catagory,(select name from tblogin where id=tblabreportvalue.createBy) as FullName,'' as DegreeName,(select username from tblogin where id=tblabreportvalue.createBy) as username from tblabreporthead join tblabreportvalue on tblabreportvalue.rId=tblabreporthead.Id where tblabreportvalue.FiscalYear='"+fiscalYear+"' and tblabreportvalue.CMonth='"+cMonth+"' and tblabreportvalue.LabId='"+labId+"' and tblabreportvalue.HeadId='"+headId+"' and testId='"+testId+"' order by Catagory,rId asc";
		
		List<?> list = dbsession.createSQLQuery(Sql).list();
		
		for(Iterator<?> iter = list.iterator(); iter.hasNext();)
		{
			Object[] element = (Object[]) iter.next();
			
			UserName=element[6].toString();
			
			if(element[2].toString().equals("Quantity")){
				QuantityV=element[0].toString();
				QuantityR=element[1].toString();
			}
			if(element[2].toString().equals("Colour")){
				ColourV=element[0].toString();
				ColourR=element[1].toString();
			}
			if(element[2].toString().equals("Appearance")){
				AppearanceV=element[0].toString();
				AppearanceR=element[1].toString();
			}
			if(element[2].toString().equals("Sediment")){
				SedimentV=element[0].toString();
				SedimentR=element[1].toString();
			}
			
			if(element[2].toString().equals("Sp.Gravity")){
				SpGravityV=element[0].toString();
				SpGravityR=element[1].toString();
			}				
			if(element[2].toString().equals("PH")){
				pHV=element[0].toString();
				pHR=element[1].toString();
			}
			if(element[2].toString().equals("Suger")){
				SugarV=element[0].toString();
				SugarR=element[1].toString();
			}
			if(element[2].toString().equals("Albumin")){
				AlbuminV=element[0].toString();
				AlbuminR=element[1].toString();
			}
			if(element[2].toString().equals("Ketone Bodies")){
				KetoneBodiesV=element[0].toString();
				KetoneBodiesR=element[1].toString();
			}
			if(element[2].toString().equals("Blood")){
				BloddV=element[0].toString();
				BloddR=element[1].toString();
			}
			if(element[2].toString().equals("Bilirubin")){
				BilirubinV=element[0].toString();
				BilirubinR=element[1].toString();
			}
			if(element[2].toString().equals("Urobilinogen")){
				UrobilinogenV=element[0].toString();
				UrobilinogenR=element[1].toString();
			}
			if(element[2].toString().equals("Nitrites")){
				NitriteV=element[0].toString();
				NitriteR=element[1].toString();
			}
			if(element[2].toString().equals("Bile Pigment")){
				BilePigmentV=element[0].toString();
				BilePigmentR=element[1].toString();
			}
			if(element[2].toString().equals("Bile Salt")){
				BileSaltV=element[0].toString();
				BileSaltR=element[1].toString();
			}
			if(element[2].toString().equals("Ex Phosphate")){
				ExsPhosphateV=element[0].toString();
				ExsPhosphateR=element[1].toString();
			}
			if(element[2].toString().equals("Hyaline Cast")){
				HyalineCastV=element[0].toString();
				HyalineCastR=element[1].toString();
			}
			if(element[2].toString().equals("Grancular Cells")){
				GranularCastV=element[0].toString();
				GranularCastR=element[1].toString();
			}
			if(element[2].toString().equals("Fatty")){
				FattyV=element[0].toString();
				FattyR=element[1].toString();
			}
			if(element[2].toString().equals("WBC")){
				WBCV=element[0].toString();
				WBCR=element[1].toString();
			}
			if(element[2].toString().equals("RBC Cast")){
				RBCCastV=element[0].toString();
				RBCCastR=element[1].toString();
			}
			if(element[2].toString().equals("R.B.C")){
				RBCV=element[0].toString();
				RBCR=element[1].toString();
			}
			if(element[2].toString().equals("Pus Cells")){
				PusCellsV=element[0].toString();
				PusCellsR=element[1].toString();
			}
			if(element[2].toString().equals("Epithelial Cells")){
				EpithelialCellsV=element[0].toString();
				EpithelialCellsR=element[1].toString();
			}
			if(element[2].toString().equals("Trichomonas Vaginalis")){
				TrichomonasV=element[0].toString();
				TrichomonasR=element[1].toString();
			}
			if(element[2].toString().equals("Supermatozoa")){
				SpermatozoaV=element[0].toString();
				SpermatozoaR=element[1].toString();
			}
			if(element[2].toString().equals("Fungus")){
				FungusV=element[0].toString();
				FungusR=element[1].toString();
			}
			if(element[2].toString().equals("Parasites")){
				ParasitesV=element[0].toString();
				ParasitesR=element[1].toString();
			}
			if(element[2].toString().equals("Micro Oraganism")){
				MicroOrgV=element[0].toString();
				MicroOrgR=element[1].toString();
			}
			if(element[2].toString().equals("Yeast")){
				YeastV=element[0].toString();
				YeastR=element[1].toString();
			}
			if(element[2].toString().equals("Calciumn Oxalate")){
				CalciumOxV=element[0].toString();
				CalciumOxR=element[1].toString();
			}
			if(element[2].toString().equals("Uric Acid Crystals")){
				UricAcid=element[0].toString();
				UricAcidR=element[1].toString();
			}
			if(element[2].toString().equals("Urate")){
				Urate=element[0].toString();
				UrateR=element[1].toString();
			}
			if(element[2].toString().equals("Triple Phosphate")){
				TriplePhosphate=element[0].toString();
				TriplePhosphateR=element[1].toString();
			}
			if(element[2].toString().equals("Amorph. Phosphate")){
				AmrPhosphate=element[0].toString();
				AmrPhosphateR=element[1].toString();
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
		map.put("Sample","Urine");
		map.put("username",UserName);
		
		
		map.put("QuantityV",QuantityV);
		map.put("QuantityR",QuantityR);
		map.put("ColourV",ColourV);
		map.put("ColourR",ColourR);
		map.put("AppearanceV",AppearanceV);
		map.put("AppearanceR",AppearanceR);
		map.put("SedimentV",SedimentV);
		map.put("SedimentR",SedimentR);
		map.put("FungusV",FungusV);
		map.put("FungusR",FungusR);
		
		
		map.put("SpGravityV",SpGravityV);
		map.put("SpGravityR",SpGravityR);
		map.put("hPV",pHV);
		map.put("hPR",pHR);
		map.put("SugarV",SugarV);
		map.put("SugarR",SugarR);
		map.put("AlbuminV",AlbuminV);
		map.put("AlbuminR",AlbuminR);
		map.put("AcetoneV",KetoneBodiesV);
		map.put("AcetoneR",KetoneBodiesR);
		map.put("BloodV",BloddV);
		map.put("BloodR",BloddR);
		map.put("BilirubinV",BilirubinV);
		map.put("BilirubinR",BilirubinR);
		map.put("UrobiliogenV",UrobilinogenV);
		map.put("UrobilinogenR",UrobilinogenR);
		map.put("NitritesV",NitriteV);
		map.put("NitritesR",NitriteR);
		map.put("BilePigmentV",BilePigmentV);
		map.put("BilePigmentR",BilePigmentR);
		map.put("BileSaltV",BileSaltV);
		map.put("BileSaltR",BileSaltR);
		map.put("ExPhosphateV",ExsPhosphateV);
		map.put("ExsPhosphateR",ExsPhosphateR);
		
		map.put("HyalineV",HyalineCastV);
		map.put("HyalineR",HyalineCastR);
		map.put("GranularV",GranularCastV);
		map.put("GranularR",GranularCastR);
		map.put("FattyV",FattyV);
		map.put("FattyR",FattyR);
		map.put("WBCV",FattyV);
		map.put("WBCR",WBCR);
		map.put("RBCCastV",RBCCastV);
		map.put("RBCCastR",RBCCastR);
		
		map.put("RBCV",RBCV);
		map.put("RBCR",RBCR);
		map.put("PusCellV",PusCellsV);
		map.put("PusCellR",PusCellsR);
		map.put("EpithelialCellsV",EpithelialCellsV);
		map.put("EpithelialCellsR",EpithelialCellsR);
		map.put("TrichomonasV",TrichomonasV);
		map.put("TrichomonasR",TrichomonasV);
		map.put("SpermatozoaV",SpermatozoaV);
		map.put("SpermatozoaR",SpermatozoaR);
		map.put("ParasitesV",ParasitesV);
		map.put("ParasitesR",ParasitesR);
		map.put("MicroV",MicroOrgV);
		map.put("MicroOrgR",MicroOrgR);
		map.put("YeastV",YeastV);
		map.put("YeastR",YeastR);
		
		map.put("CalOxalateV",CalciumOxV);
		map.put("CalOxalateR",CalciumOxR);
		map.put("UricAcidV",UricAcid);
		map.put("UricAcidR",UricAcidR);
		map.put("UrateV",Urate);
		map.put("UrateR",UrateR);
		map.put("TripPhosV",TriplePhosphate);
		map.put("TripPhosR",TriplePhosphateR);
		map.put("AmPhosV",AmrPhosphate);
		map.put("AmPhosR",AmrPhosphateR);
		
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
		
		String jrxmlFile=session.getServletContext().getRealPath("WEB-INF/jasper/LabResultReport/Urine3Col.jrxml");
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