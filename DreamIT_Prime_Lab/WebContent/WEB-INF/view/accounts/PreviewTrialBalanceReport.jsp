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
    	String transactionType=(String)request.getAttribute("transactionType");
		
    	
     	HashMap map=new HashMap();
     	map.put("orgName", orgranizationInfo.get(0).getOrganizationName());
    	map.put("orgAddress", orgranizationInfo.get(0).getOrganizationAddress());
    	map.put("orgContact", orgranizationInfo.get(0).getOrganizationContact());
    	
    	System.out.println("orgAddress"+orgranizationInfo.get(0).getOrganizationAddress());
        SpringRootConfig sp=new SpringRootConfig();
        
		String Sql="select '"+transactionType+"' as transactionType,a.headTitle,a.reference,ISNULL((select sum(amount) from tbAccftransection where approve='1' and d_l_id like ''+(a.reference)+'%' and date<'"+fromDate+"'),0) as BeforeDebit,ISNULL((select sum(amount) from tbAccftransection where approve='1' and c_l_id like ''+(a.reference)+'%' and date<'"+fromDate+"'),0) as BeforeCredit,ISNULL((select sum(amount) from tbAccftransection where approve='1' and d_l_id like ''+(a.reference)+'%' and date between '"+fromDate+"' and '"+toDate+"'),0) as CurrentDebit,ISNULL((select sum(amount) from tbAccftransection where approve='1' and c_l_id like ''+(a.reference)+'%' and date between '"+fromDate+"' and '"+toDate+"'),0) as CurrentCredit,'"+fromDate+"' as StartDate,'"+toDate+"' as EndDate from tbAccfhead a where a.pheadId!='1' and a.pheadId!='0' and (round((len(a.reference) - len(replace(a.reference,'-',''))) / LEN('-'),1))<2 order by a.headid,a.reference";
      	System.out.println("sql "+Sql);
      	
      	
      	
		String 	jrxmlFile ="";
		if(transactionType.equals("0")){
			jrxmlFile=session.getServletContext().getRealPath("WEB-INF/jasper/Accounts/TrailBalanceSheet.jrxml");
		}
		else{
			jrxmlFile=session.getServletContext().getRealPath("WEB-INF/jasper/Accounts/trasactionTrailBalanceSheet.jrxml");
		}
		
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