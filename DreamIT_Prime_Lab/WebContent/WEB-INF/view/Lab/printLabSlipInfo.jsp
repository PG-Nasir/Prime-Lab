<%@ page contentType="application/pdf" %>

<%@ page trimDirectiveWhitespaces="true"%>


<%@ page import="net.sf.jasperreports.engine.design.JRDesignQuery" %>
<%@ page import="net.sf.jasperreports.engine.design.JasperDesign" %>
<%@ page import="net.sf.jasperreports.engine.xml.JRXmlLoader" %>

<%@page import="java.util.*"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="pg.OrganizationModel.OrganizationInfo"%>
<%@page import="java.util.HashMap"%>
<%@ page import="net.sf.jasperreports.engine.*" %>
<%@ page import="java.io.File" %>
<%@ page import="java.io.FileInputStream" %>
<%@ page import="java.io.FileNotFoundException" %>
<%@ page import="java.io.InputStream" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="pg.config.*" %>

<%

    try {
		
    	String fiscalyear=(String)request.getAttribute("fiscalyear");
    	String labId=(String)request.getAttribute("labId");
    	String cmonth=(String)request.getAttribute("cmonth");
    	String reportType=(String)request.getAttribute("reportType");

    	List<OrganizationInfo> orgranizationInfo = (List<OrganizationInfo>) session.getAttribute("organization_info");
    	
    	
    	HashMap map=new HashMap();
     	map.put("orgName", orgranizationInfo.get(0).getOrganizationName());
    	map.put("orgAddress", orgranizationInfo.get(0).getOrganizationAddress());
    	map.put("orgContact", orgranizationInfo.get(0).getOrganizationContact());
    	
    	
        SpringRootConfig sp=new SpringRootConfig();
        
        String jrxmlFile="";
        
		String Sql="";


			jrxmlFile = session.getServletContext().getRealPath("WEB-INF/jasper/lab/LabSlipDepartmentWise.jrxml");
			Sql="select a.headId,ISNULL((select GroupName from tblabtestgroup where headId=a.headId),'Tube') as GroupName,(select roomNO from tblabtestgroup where headId=a.headId)as roomNo,a.testName,a.rate*a.qty as rate,a.RefundStatus,b.labId,b.RegNo,b.PatientName,b.Age,b.Month,b.day,b.Cabin,b.Sex,b.Mobile,b.DateTime,b.SampleCollect,b.ReportDelivery,(select username from tblogin where id=b.CreateBy) as UserName,b.RefferBy,(select Name from tbdoctorinfo where DoctorId=b.RefferBy) as RefferName,(select Degree from tbdoctorinfo where DoctorId=b.RefferBy) as Degree ,b.TotalCharge ,b.PercentDiscount,b.Discount,b.ManualDiscount,b.totalDiscount,b.TotalPayable as ActualPayable,(select ISNULL(sum(Cash),0) from TbLabPaymentHistory where labId='"+labId+"' and FiscalYear='"+fiscalyear+"' and CMonth='"+cmonth+"' and PaymentStatus='Paid')-(select ISNULL(sum(Cash),0) from TbLabPaymentHistory where labId='"+labId+"' and FiscalYear='"+fiscalyear+"' and CMonth='"+cmonth+"' and PaymentStatus='Refund') as TotalPaidAmount from TbLabTestHistory a join TbLabPatient b on b.labId=a.labId where b.FiscalYear='"+fiscalyear+"'  and b.CMonth='"+cmonth+"' and b.labId='"+labId+"' and a.RefundStatus!='1' and a.FiscalYear='"+fiscalyear+"' and a.CMonth='"+cmonth+"' and a.labId='"+labId+"' and a.type!='2' order by a.type,a.headId,a.testName";
		
			System.out.println("sql "+Sql);
	        InputStream input = new FileInputStream(new File(jrxmlFile));

	        
	    	JasperDesign jd=JRXmlLoader.load(input);		
	    	JRDesignQuery jq=new JRDesignQuery();
			
			jq.setText(Sql);
			jd.setQuery(jq);
			
	        //Generating the report
	        JasperReport jr = JasperCompileManager.compileReport(jd);
	      
	        JasperPrint jp = JasperFillManager.fillReport(jr, map, sp.getDataSource().getConnection());

	        //Exporting the report as a PDF
	        JasperExportManager.exportReportToPdfStream(jp, response.getOutputStream());
			


    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (JRException e) {
        e.printStackTrace();
    }  catch (SQLException e) {
        e.printStackTrace();
    }


%>