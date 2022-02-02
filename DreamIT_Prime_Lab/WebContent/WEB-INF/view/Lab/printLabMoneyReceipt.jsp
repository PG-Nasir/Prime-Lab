<%@ page contentType="application/pdf"%>

<%@ page trimDirectiveWhitespaces="true"%>


<%@ page import="net.sf.jasperreports.engine.design.JRDesignQuery"%>
<%@ page import="net.sf.jasperreports.engine.design.JasperDesign"%>
<%@ page import="net.sf.jasperreports.engine.xml.JRXmlLoader"%>

<%@page import="java.util.*"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="pg.OrganizationModel.OrganizationInfo"%>
<%@page import="java.util.HashMap"%>
<%@ page import="net.sf.jasperreports.engine.*"%>
<%@ page import="java.io.File"%>
<%@ page import="java.io.FileInputStream"%>
<%@ page import="java.io.FileNotFoundException"%>
<%@ page import="java.io.InputStream"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.DriverManager"%>
<%@ page import="java.sql.SQLException"%>
<%@ page import="pg.config.*"%>

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
		System.out.println("reportType "+reportType);
		

		jrxmlFile = session.getServletContext().getRealPath("WEB-INF/jasper/lab/PatientLabMoneyReceipte.jrxml");
		Sql="select a.testName,a.rate*a.qty as rate,a.type,a.RefundStatus,b.labId,b.fiscalyear,b.remark,b.RegNo,b.PatientName,b.Age,b.Month,b.day,b.Cabin,b.Sex,b.Mobile,b.DateTime,b.SampleCollect,b.ReportDelivery,(select username from tblogin where id=b.CreateBy) as CreateBy,(select username from tblogin where id=b.ModifyBy) as ModifyBy,b.RefferBy,(select Name from tbdoctorinfo where DoctorId=b.RefferBy) as RefferName,(select Degree from tbdoctorinfo where DoctorId=b.RefferBy) as Degree ,b.TotalCharge ,b.PercentDiscount,b.Discount,b.ManualDiscount,b.totalDiscount,b.TotalPayable as ActualPayable,(select ISNULL(sum(Cash),0) from TbLabPaymentHistory where LabId='"+labId+"' and FiscalYear='"+fiscalyear+"' and CMonth='"+cmonth+"'  and PaymentStatus='Paid')-(select ISNULL(sum(Cash),0) from TbLabPaymentHistory where LabId='"+labId+"' and FiscalYear='"+fiscalyear+"' and cmonth='"+cmonth+"'  and PaymentStatus='Refund') as TotalPaidAmount, (select dbo.number(((select ISNULL(sum(Cash),0) from TbLabPaymentHistory where LabId='"+labId+"' and FiscalYear='"+fiscalyear+"' and cmonth='"+cmonth+"' and PaymentStatus='Paid')-(select ISNULL(sum(Cash),0) from TbLabPaymentHistory where LabId='"+labId+"' and FiscalYear='"+fiscalyear+"' and cmonth='"+cmonth+"'  and PaymentStatus='Refund')))) as Taka,b.actualEntryTime from TbLabTestHistory a join TbLabPatient b on b.FiscalYear=a.FiscalYear and b.CMonth=a.CMonth and b.labId=a.labId where b.FiscalYear='"+fiscalyear+"' and b.cmonth='"+cmonth+"' and b.labId='"+labId+"'  and a.RefundStatus!='1'  and a.FiscalYear='"+fiscalyear+"' and a.cmonth='"+cmonth+"' and a.labId='"+labId+"' order by a.type,a.headId,a.testName";
			
		//Sql="select a.testName,a.rate*a.qty as rate,a.type,a.RefundStatus,b.labId,b.fiscalyear,b.RegNo,b.PatientName,b.Age,b.Month,b.day,b.Cabin,b.Sex,b.Mobile,b.DateTime,b.SampleCollect,b.ReportDelivery,(select username from tblogin where id=b.CreateBy) as CreateBy,(select username from tblogin where id=b.ModifyBy) as ModifyBy,b.RefferBy,(select Name from tbdoctorinfo where DoctorId=b.RefferBy) as RefferName,(select Degree from tbdoctorinfo where DoctorId=b.RefferBy) as Degree ,b.TotalCharge ,b.PercentDiscount,b.Discount,b.ManualDiscount,b.totalDiscount,b.TotalPayable as ActualPayable,(select ISNULL(sum(Cash),0) from TbLabPaymentHistory where LabId='2' and FiscalYear='2021' and CMonth='June'  and PaymentStatus='Paid')-(select ISNULL(sum(Cash),0) from TbLabPaymentHistory where LabId='2' and FiscalYear='2021' and cmonth='June'  and PaymentStatus='Refund') as TotalPaidAmount, (select dbo.number(((select ISNULL(sum(Cash),0) from TbLabPaymentHistory where LabId='2' and FiscalYear='2021' and cmonth='June' and PaymentStatus='Paid')-(select ISNULL(sum(Cash),0) from TbLabPaymentHistory where LabId='2' and FiscalYear='2021' and cmonth='June'  and PaymentStatus='Refund')))) as Taka,b.actualEntryTime from TbLabTestHistory a join TbLabPatient b on b.labId=a.labId and b.FiscalYear=a.FiscalYear and b.CMonth=a.CMonth where b.FiscalYear='2021' and b.cmonth='June' and b.labId='2' and a.RefundStatus!='1' and a.FiscalYear='2021' and b.cmonth='June' and a.labId='2'  order by a.type,a.headId,a.testName";
	
			
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