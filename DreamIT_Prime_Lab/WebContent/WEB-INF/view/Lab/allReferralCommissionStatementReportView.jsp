<%@page import="pg.OrganizationModel.OrganizationInfo"%>
<%@ page contentType="application/pdf" %>

<%@ page trimDirectiveWhitespaces="true"%>


<%@ page import="net.sf.jasperreports.engine.design.JRDesignQuery" %>
<%@ page import="net.sf.jasperreports.engine.design.JasperDesign" %>
<%@ page import="net.sf.jasperreports.engine.xml.JRXmlLoader" %>
<%@page import="net.sf.jasperreports.engine.JRExporterParameter"%>
<%@page import="net.sf.jasperreports.engine.data.JRBeanCollectionDataSource"%>
<%@page import="net.sf.jasperreports.engine.JasperCompileManager"%>

<%@ page import="net.sf.jasperreports.engine.*" %>

<%@page import="java.util.*"%>
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

	
	
List<OrganizationInfo> orgranizationInfo = (List<OrganizationInfo>) session.getAttribute("organization_info");
	
	
	
	String fromDate=(String) request.getAttribute("fromDate");
	String toDate=(String) request.getAttribute("toDate");
	String doctorId=(String) request.getAttribute("doctorId");
	
	
	HashMap map=new HashMap();
 	map.put("orgName", orgranizationInfo.get(0).getOrganizationName());
	map.put("orgAddress", orgranizationInfo.get(0).getOrganizationAddress());
	map.put("orgContact", orgranizationInfo.get(0).getOrganizationContact());
	
	
	
    try {
    	
    	SpringRootConfig sp=new SpringRootConfig();
    	
    
    	List<HashMap<String,String>> datalist=new ArrayList<HashMap<String,String>>();
    	
    	Session dbsession=HibernateUtil.openSession();
		Transaction tx=null;
        
		
		String sql="";
		if(!doctorId.equals("0")){
			sql="select (select organizationLogo from tbOrganizationInfo where organizationId='1' ) as OrgLogo,RefferName,MobileNo,Address,sum(PathologyRate) as PathologyRate, sum(PathologyNetAmount) as PathologyNetAmount, sum(PathologyCMDDiscount) as PathologyCMDDiscount,sum(HormoneRate) as HormoneRate,sum(HormoneNetAmount) as HormoneNetAmount,avg(HormoneCMDDiscount)as HormoneCMDDiscount,sum(EchoCardRate) as EchoCardRate,sum(EchoCardNetAmount) as EchoCardNetAmount,avg(EchoCardCMDDiscount) as EchoCardCMDDiscount ,sum(UltraSonoRate) as UltraSonoRate,sum(UltraSonoNetAmount) as UltraSonoNetAmount,avg(UltraSonoCMDDiscount) as UltraSonoCMDDiscount,sum(EnDosRate) as EnDosRate,sum(EnDosNetAmount) as EnDosNetAmount,avg(EnDosCMDDiscount) as EnDosCMDDiscount,sum(XrayRate) as XrayRate,sum(XrayNetAmount) as XrayNetAmount,avg(XrayCMDDiscount) as XrayCMDDiscount,sum(ECGRate) as ECGRate,sum(ECGNetAmount) as ECGNetAmount,avg(ECGCMDDiscount) as ECGCMDDiscount,sum(FNARate) as FNARate,sum(FNANetAmount) as FNANetAmount,avg(FNACMDDiscount) as FNACMDDiscount,sum(HistopathologyRate) as HistopathologyRate,sum(HistopathologyNetAmount) as HistopathologyNetAmount,avg(HistopathologyCMDDiscount)as HistopathologyCMDDiscount,sum(BloodGroupRate) as BloodGroupRate,sum(BloodGroupNetAmount) as BloodGroupNetAmount,avg(BloodGroupCMDDiscount)as BloodGroupCMDDiscount,sum(PepsSemarRate) as PepsSemarRate,sum(PepsSemarNetAmount) as PepsSemarNetAmount,avg(PepsSemarCMDDiscount) as PepsSemarCMDDiscount,sum(OthersRate) as OthersRate,sum(OthersNetAmount) as OthersNetAmount,avg(OthersCMDDiscount) as OthersCMDDiscount,sum(TotalCharge) as TotalCharge,sum(DoctorDiscount) as DoctorDiscount,sum(ManualDiscount) as ManualDiscount,sum(TotalPaid) as TotalPaid,sum(Due) as Due,'"+fromDate+"' as StartDate,'"+toDate+"' as EndDate from TbAllRefferWiseComissionStatement('"+fromDate+"','"+toDate+"') where RefferName=(select Name from TbDoctorInfo where DoctorId='"+doctorId+"') group by RefferName,MobileNo,Address";			
		}
		else{
			sql="select (select organizationLogo from tbOrganizationInfo where organizationId='1' ) as OrgLogo,RefferName,MobileNo,Address,sum(PathologyRate) as PathologyRate, sum(PathologyNetAmount) as PathologyNetAmount, sum(PathologyCMDDiscount) as PathologyCMDDiscount,sum(HormoneRate) as HormoneRate,sum(HormoneNetAmount) as HormoneNetAmount,avg(HormoneCMDDiscount)as HormoneCMDDiscount,sum(EchoCardRate) as EchoCardRate,sum(EchoCardNetAmount) as EchoCardNetAmount,avg(EchoCardCMDDiscount) as EchoCardCMDDiscount ,sum(UltraSonoRate) as UltraSonoRate,sum(UltraSonoNetAmount) as UltraSonoNetAmount,avg(UltraSonoCMDDiscount) as UltraSonoCMDDiscount,sum(EnDosRate) as EnDosRate,sum(EnDosNetAmount) as EnDosNetAmount,avg(EnDosCMDDiscount) as EnDosCMDDiscount,sum(XrayRate) as XrayRate,sum(XrayNetAmount) as XrayNetAmount,avg(XrayCMDDiscount) as XrayCMDDiscount,sum(ECGRate) as ECGRate,sum(ECGNetAmount) as ECGNetAmount,avg(ECGCMDDiscount) as ECGCMDDiscount,sum(FNARate) as FNARate,sum(FNANetAmount) as FNANetAmount,avg(FNACMDDiscount) as FNACMDDiscount,sum(HistopathologyRate) as HistopathologyRate,sum(HistopathologyNetAmount) as HistopathologyNetAmount,avg(HistopathologyCMDDiscount)as HistopathologyCMDDiscount,sum(BloodGroupRate) as BloodGroupRate,sum(BloodGroupNetAmount) as BloodGroupNetAmount,avg(BloodGroupCMDDiscount)as BloodGroupCMDDiscount,sum(PepsSemarRate) as PepsSemarRate,sum(PepsSemarNetAmount) as PepsSemarNetAmount,avg(PepsSemarCMDDiscount) as PepsSemarCMDDiscount,sum(OthersRate) as OthersRate,sum(OthersNetAmount) as OthersNetAmount,avg(OthersCMDDiscount) as OthersCMDDiscount,sum(TotalCharge) as TotalCharge,sum(DoctorDiscount) as DoctorDiscount,sum(ManualDiscount) as ManualDiscount,sum(TotalPaid) as TotalPaid,sum(Due) as Due,'"+fromDate+"' as StartDate,'"+toDate+"' as EndDate from TbAllRefferWiseComissionStatement('"+fromDate+"','"+toDate+"') group by RefferName,MobileNo,Address order by RefferName";
		}
		

		System.out.println(sql);
	        
			String jrxmlFile = session.getServletContext().getRealPath("WEB-INF/jasper/lab/AllReferralComissionreport.jrxml");
	        InputStream input = new FileInputStream(new File(jrxmlFile));

	        
	    	JasperDesign jd=JRXmlLoader.load(input);
			JRDesignQuery jq=new JRDesignQuery();
			
			jq.setText(sql);
			jd.setQuery(jq);
			
	        //Generating the report
	        JasperReport jr = JasperCompileManager.compileReport(jd);
	      
	        JasperPrint jp = JasperFillManager.fillReport(jr, map, sp.getDataSource().getConnection());

	        //Exporting the report as a PDF
	        JasperExportManager.exportReportToPdfStream(jp, response.getOutputStream());
		

		
		
		
        //Generating the report
      
		
		

    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (JRException e) {
        e.printStackTrace();
    } 
    


%>