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
		
    	String doctorId=(String)request.getAttribute("doctorId");

    	List<OrganizationInfo> orgranizationInfo = (List<OrganizationInfo>) session.getAttribute("organization_info");
    	
    	
    	HashMap map=new HashMap();
     	map.put("orgName", orgranizationInfo.get(0).getOrganizationName());
    	map.put("orgAddress", orgranizationInfo.get(0).getOrganizationAddress());
    	map.put("orgContact", orgranizationInfo.get(0).getOrganizationContact());
    	
    	
        SpringRootConfig sp=new SpringRootConfig();
        
		String Sql="select Name,Degree,Address,(select organizationName from tbOrganizationInfo where organizationId='1') as organizationName,(select organizationAddress from tbOrganizationInfo where organizationId='1') as organizationAddress from TbDoctorInfo where DoctorId='"+doctorId+"'";
      	System.out.println("sql "+Sql);
      	
		String jrxmlFile = session.getServletContext().getRealPath("WEB-INF/jasper/lab/EnvelopePrint.jrxml");
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