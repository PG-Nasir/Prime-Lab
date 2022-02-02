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
     	map.put("orgName", orgranizationInfo.get(0).getOrganizationName());
    	map.put("orgAddress", orgranizationInfo.get(0).getOrganizationAddress());
    	map.put("orgContact", orgranizationInfo.get(0).getOrganizationContact());
    	
     /* 	map.put("orgName", orgranizationInfo.get(0).getOrganizationName());
    	map.put("orgAddress", orgranizationInfo.get(0).getOrganizationAddress());
    	map.put("orgContact", orgranizationInfo.get(0).getOrganizationContact());
    	 */
    	//System.out.println("orgAddress"+orgranizationInfo.get(0).getOrganizationAddress());
        SpringRootConfig sp=new SpringRootConfig();
        
		//String Sql="select a.headTitle,ISNULL((select sum(openingBalance) from tbAccfledger where reference=b.reference),0) as OpeningBalance,ISNULL((select sum(amount) from tbAccftransection where approve='1' and date between '"+fromDate+"' and '"+toDate+"' and c_l_id=b.reference ),0) as Amount1,ISNULL((select sum(amount) from tbAccftransection where approve='1' and date between '"+fromDate+"' and '"+toDate+"' and d_l_id=b.reference),0) as Amount2,'Revenue'  as Type,'"+fromDate+"' as StartDate,'"+toDate+"' as EndDate  from tbAccfhead a left join tbAccfledger b on a.headid=b.pheadId where a.pheadId='4'"+
				//" UNION ALL select a.headTitle,ISNULL((select sum(openingBalance) from tbAccfledger where reference=b.reference),0) as OpeningBalance,ISNULL((select sum(amount) from tbAccftransection where approve='1' and date between '"+fromDate+"' and '"+toDate+"' and c_l_id=b.reference ),0) as Amount1,ISNULL((select sum(amount) from tbAccftransection where approve='1' and date between '"+fromDate+"' and '"+toDate+"' and d_l_id=b.reference),0) as Amount2,'Expense'  as Type,'"+fromDate+"' as StartDate,'"+toDate+"' as EndDate from tbAccfhead a left join tbAccfledger b on a.headid=b.pheadId where a.pheadId='3' ";
      //	System.out.println("sql "+Sql);
      	
      	
      	String Sql="select a.headTitle,ISNULL((select sum(openingBalance) from tbAccfledger where reference like a.reference+'%'),0) as OpeningBalance,ISNULL((select sum(amount) from tbAccftransection where approve='1' and date between '"+fromDate+"' and '"+toDate+"' and c_l_id like a.reference+'%' ),0) as Amount1,ISNULL((select sum(amount) from tbAccftransection where approve='1' and date between '"+fromDate+"' and '"+toDate+"' and d_l_id like a.reference+'%'),0) as Amount2,'Revenue'  as Type,'"+fromDate+"' as StartDate,'"+toDate+"' as EndDate  from tbAccfhead a where a.pheadId='4' group by a.headTitle,a.reference UNION ALL select a.headTitle,ISNULL((select sum(openingBalance) from tbAccfledger where reference like a.reference+'%'),0) as OpeningBalance,ISNULL((select sum(amount) from tbAccftransection where approve='1' and date between '"+fromDate+"' and '"+toDate+"' and c_l_id like a.reference+'%' ),0) as Amount1,ISNULL((select sum(amount) from tbAccftransection where approve='1' and date between '"+fromDate+"' and '"+toDate+"' and d_l_id like a.reference+'%'),0) as Amount2,'Expense'  as Type,'"+toDate+"' as StartDate,'"+fromDate+"' as EndDate from tbAccfhead a where a.pheadId='3'  group by a.headTitle,a.reference";
      	System.out.println(Sql);
      	
		String 	jrxmlFile =session.getServletContext().getRealPath("WEB-INF/jasper/Accounts/PALReport.jrxml");
		
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