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
	
	
	HashMap map=new HashMap();
 	map.put("orgName", orgranizationInfo.get(0).getOrganizationName());
	map.put("orgAddress", orgranizationInfo.get(0).getOrganizationAddress());
	map.put("orgContact", orgranizationInfo.get(0).getOrganizationContact());
	
	
	
    try {
    	
    	SpringRootConfig sp=new SpringRootConfig();
    	
    
    	List<HashMap<String,String>> datalist=new ArrayList<HashMap<String,String>>();
    	
    	Session dbsession=HibernateUtil.openSession();
		Transaction tx=null;
        
		
		String Sql="select *,(select organizationLogo from tbOrganizationInfo where organizationId='1' ) as OrgLogo from TbLabSaleDueStatement('"+fromDate+"','"+toDate+"','"+patientType+"','All') order by DateTime,UserName;";
	    	System.out.println("Query "+Sql);
	    	
	        
			String jrxmlFile = session.getServletContext().getRealPath("WEB-INF/jasper/lab/CurrentDueStatement.jrxml");
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