<%@ page contentType="application/pdf" %>

<%@ page trimDirectiveWhitespaces="true"%>

<%@page import="java.util.HashMap"%>
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
<%@page import="pg.OrganizationModel.OrganizationInfo"%>
<%@page import="java.util.List"%>
<%

    try {
    	List<OrganizationInfo> orgranizationInfo = (List<OrganizationInfo>) session.getAttribute("organization_info");
    	String fromDate=(String)request.getAttribute("fromDate");
    	String toDate=(String)request.getAttribute("toDate");
		
		
    	
     	HashMap map=new HashMap();
     	map.put("StartDate", fromDate);
    	map.put("EndDate", toDate);
     	map.put("orgName", orgranizationInfo.get(0).getOrganizationName());
    	map.put("orgAddress", orgranizationInfo.get(0).getOrganizationAddress());
    	map.put("orgContact", orgranizationInfo.get(0).getOrganizationContact());
    	
    	System.out.println("orgAddress"+orgranizationInfo.get(0).getOrganizationAddress());
        SpringRootConfig sp=new SpringRootConfig();
        
		String Sql="select '"+fromDate+"' as FromDate,'"+toDate+"' as ToDate,a.ledgerTitle,ISNULL(a.openingBalance,'0') as openingBalance,a.reference,ISNULL((select ISNULL(sum(amount),0) from tbAccftransection where d_l_id=a.reference and date<'"+fromDate+"' and approve='1'),0)-ISNULL((select ISNULL(sum(amount),0) from tbAccftransection where c_l_id=a.reference and date<'"+fromDate+"' and approve='1'),0) as TransactionAmount,'1' as Type from tbAccfledger a where a.reference like '2-6%' UNION ALL select '"+fromDate+"' as FromDate,'"+toDate+"' as ToDate,a.ledgerTitle,ISNULL(a.openingBalance,'0') as openingBalance,a.reference,ISNULL((select ISNULL(sum(amount),0) from tbAccftransection where d_l_id=a.reference and date between '"+fromDate+"' and '"+toDate+"' and approve='1'),0) as TransactionAmount,'2' as Type from tbAccfledger a where a.reference like '2-6%'  UNION ALL  select '"+fromDate+"' as FromDate,'"+toDate+"' as ToDate,a.ledgerTitle,ISNULL(a.openingBalance,'0') as openingBalance,a.reference,ISNULL((select ISNULL(sum(amount),0) from tbAccftransection where c_l_id=a.reference and date between '"+fromDate+"' and '"+toDate+"' and approve='1'),0) as TransactionAmount,'3' as Type from tbAccfledger a where a.reference like '2-6%' ";
      	System.out.println("sql "+Sql);
      	
      	
      	
		String 	jrxmlFile =session.getServletContext().getRealPath("WEB-INF/jasper/Accounts/PaymentReceiptStatement.jrxml");
		
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