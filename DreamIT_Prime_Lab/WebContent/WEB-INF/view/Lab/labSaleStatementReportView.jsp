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

	
	String supplierid=(String) request.getAttribute("supplierid");
	String fromDate=(String) request.getAttribute("fromDate");
	String toDate=(String) request.getAttribute("toDate");
	String patientType=(String) request.getAttribute("patientType");
	String reportType=(String) request.getAttribute("reportType");
	
	HashMap map=new HashMap();
 	map.put("orgName", orgranizationInfo.get(0).getOrganizationName());
	map.put("orgAddress", orgranizationInfo.get(0).getOrganizationAddress());
	map.put("orgContact", orgranizationInfo.get(0).getOrganizationContact());
	
	
	
    try {
    	
    	SpringRootConfig sp=new SpringRootConfig();
    	
    
    	List<HashMap<String,String>> datalist=new ArrayList<HashMap<String,String>>();
    	
    	Session dbsession=HibernateUtil.openSession();
		Transaction tx=null;
		String Sql="";
		String jrxmlFile="";
		
		System.out.println("reportType "+reportType);
		if(reportType.equals("1")){
			Sql="select a.labId,a.PatientName,(select name+' '+Degree from TbDoctorInfo where DoctorId=a.refferby) as doctor,a.BillType,a.DateTime,a.TotalCharge,a.totalDiscount,a.TotalPayable,(select ISNULL(sum(cash),0) from TbLabPaymentHistory where LabId=a.labId and FiscalYear=a.FiscalYear and CMonth=a.CMonth and PaymentStatus='Paid'  and date between '"+fromDate+"' and '"+toDate+"') as ActualPaid,(select ISNULL(sum(cash),0) from TbLabPaymentHistory where  LabId=a.labId and FiscalYear=a.FiscalYear and CMonth=a.CMonth and PaymentStatus='Refund' and date between '"+fromDate+"' and '"+toDate+"') as Refund,(Select [dbo].[GetRefundAmountByFromAndToDate]('"+fromDate+"','"+toDate+"')) as otherRefound,(select username from tblogin where id=a.CreateBy) as username,(select ISNULL(sum(cash),0) from TbLabPaymentHistory where LabId not in(select labId from TbLabPatient where DateTime between '"+fromDate+"' and '"+toDate+"') and PaymentStatus='Paid' and PaymentType='Due Collection' and Date between '"+fromDate+"' and '"+toDate+"')as totalDueRecive,'"+fromDate+"' as StartDate,'"+toDate+"' as EndDate from TbLabPatient a where  a.billType='2' and a.DateTime  between '"+fromDate+"' and '"+toDate+"' order by a.labId, a.CreateBy asc"; 
			jrxmlFile = session.getServletContext().getRealPath("WEB-INF/jasper/lab/LabSaleStatement.jrxml");		
		}
		else{
			Sql="select DateTime,sum(TotalCharge) as TotalCharge,sum(totalDiscount) as totalDiscount,sum(TotalPayable) as TotalPayable,(Select [dbo].[GetPaidAmount](DateTime)) as Paid,(Select [dbo].[GetRefundAmount](DateTime)) as Refund,(select sum(cash) from TbLabPaymentHistory where date=TbLabPatient.DateTime and PaymentStatus='Paid') as NewCollection,(select ISNULL(sum(cash),0) from TbLabPaymentHistory where PaymentType='Due Collection' and Date between '"+fromDate+"' and '"+toDate+"' )as totalDueRecive, sum(TotalPayable-Paid) as NewDue,'"+fromDate+"' as StartDate,'"+toDate+"' as EndDate from TbLabPatient where DateTime between '"+fromDate+"' and '"+toDate+"' group by DateTime order by DateTime";
			jrxmlFile = session.getServletContext().getRealPath("WEB-INF/jasper/lab/LabSaleStatementSummery.jrxml");
		}


		System.out.println("Query "+Sql);
	    	
	        
			
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
		

		
		
		
        //Generating the report
      
		
		

    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (JRException e) {
        e.printStackTrace();
    } 
    


%>