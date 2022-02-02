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
    	String date=(String)request.getAttribute("date");
       	String transactionType=(String)request.getAttribute("transactionType");

       	
		
     	HashMap map=new HashMap();
     	map.put("orgName", orgranizationInfo.get(0).getOrganizationName());
    	map.put("orgAddress", orgranizationInfo.get(0).getOrganizationAddress());
    	map.put("orgContact", orgranizationInfo.get(0).getOrganizationContact());
    	
    	System.out.println("orgAddress"+orgranizationInfo.get(0).getOrganizationAddress());
        SpringRootConfig sp=new SpringRootConfig();
        
		String Sql="";
		if(transactionType.equals("0")){
			Sql="select * from funBalanceSheet('2020-10-01','"+date+"') order by reference,pheadId,headid";
		}
		else{
			Sql="select * from funBalanceSheet('2020-10-01','"+date+"') where OpeningBalance>0 or Credit>0 or Debit>0 order by reference,pheadId,headid";
		}
      	System.out.println("sql "+Sql);
      	
      	
      	
		String 	jrxmlFile ="";
		if(transactionType.equals("0")){
			jrxmlFile=session.getServletContext().getRealPath("WEB-INF/jasper/Accounts/BalanceSheetReport.jrxml");
		}
		else{
			jrxmlFile=session.getServletContext().getRealPath("WEB-INF/jasper/Accounts/BalanceSheetReport.jrxml");
			//jrxmlFile=session.getServletContext().getRealPath("WEB-INF/jasper/Accounts/transactionBalanceSheetReport.jrxml");
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