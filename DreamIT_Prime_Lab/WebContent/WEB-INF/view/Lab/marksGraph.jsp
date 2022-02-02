<%@ page contentType="application/pdf" %>

<%@ page trimDirectiveWhitespaces="true"%>


<%@ page import="net.sf.jasperreports.engine.design.JRDesignQuery" %>
<%@ page import="net.sf.jasperreports.engine.design.JasperDesign" %>
<%@ page import="net.sf.jasperreports.engine.xml.JRXmlLoader" %>

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

        SpringRootConfig sp=new SpringRootConfig();
        
		String Sql="select a.testName,a.rate*a.qty as rate,a.RefundStatus,b.labId,b.RegNo,b.PatientName,b.Age,b.Month,b.day,b.Cabin,b.Sex,b.Mobile,b.DateTime,b.SampleCollect,b.ReportDelivery,(select username from tblogin where user_id=b.CreateBy) as UserName,b.RefferBy,(select Name from tbdoctorinfo where DoctorCode=b.RefferBy) as RefferName,(select Degree from tbdoctorinfo where DoctorCode=b.RefferBy) as Degree ,b.TotalCharge ,b.PercentDiscount,b.Discount,b.ManualDiscount,b.totalDiscount,b.TotalPayable as ActualPayable,(select ISNULL(sum(Cash),0) from TbLabPaymentHistory where BillNo='8008' and FiscalYear='2020' and PaymentStatus='Paid')-(select ISNULL(sum(Cash),0) from TbLabPaymentHistory where BillNo='8008' and FiscalYear='2020' and PaymentStatus='Refund') as TotalPaidAmount from TbLabTestHistory a join TbLabPatient b on b.labId=a.labId where b.FiscalYear='2020' and b.labId='8008' and a.RefundStatus!='1' and a.labId='8008' and a.FiscalYear='2020' order by a.type,a.testGroupId,a.testName";
       	String jrxmlFile = session.getServletContext().getRealPath("WEB-INF/jasper/PatientLabMoneyReceipte.jrxml");
        InputStream input = new FileInputStream(new File(jrxmlFile));

        
    	JasperDesign jd=JRXmlLoader.load(input);
		JRDesignQuery jq=new JRDesignQuery();
		
		jq.setText(Sql);
		jd.setQuery(jq);
		
        //Generating the report
        JasperReport jr = JasperCompileManager.compileReport(jd);
      
        JasperPrint jp = JasperFillManager.fillReport(jr, null, sp.getDataSource().getConnection());

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