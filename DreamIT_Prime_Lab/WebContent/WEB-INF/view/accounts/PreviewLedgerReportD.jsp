<%@ page contentType="application/pdf" %>

<%@ page trimDirectiveWhitespaces="true"%>

<%@page import="java.util.HashMap"%>
<%@ page import="net.sf.jasperreports.engine.design.JRDesignQuery" %>
<%@ page import="net.sf.jasperreports.engine.design.JasperDesign" %>
<%@ page import="net.sf.jasperreports.engine.xml.JRXmlLoader" %>
<%@page import="org.hibernate.Session"%>
<%@page import="org.hibernate.Transaction"%>
<%@page import="pg.share.HibernateUtil"%>
<%@page import="java.util.Iterator"%>
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
    	String LedgerId=(String)request.getAttribute("LedgerId");
		
    	
     	HashMap map=new HashMap();
     	map.put("orgName", orgranizationInfo.get(0).getOrganizationName());
    	map.put("orgAddress", orgranizationInfo.get(0).getOrganizationAddress());
    	map.put("orgContact", orgranizationInfo.get(0).getOrganizationContact());
    	
    	System.out.println("orgAddress"+orgranizationInfo.get(0).getOrganizationAddress());
        SpringRootConfig sp=new SpringRootConfig();
        
        
    	Session dbsession=HibernateUtil.openSession();
		Transaction tx=null;
		
		String reference="";
		String SqlRef = "select reference from tbAccfledger where ledgerId='"+LedgerId+"'";
		System.out.println(SqlRef);;
		List<?> list2 = dbsession.createSQLQuery(SqlRef).list();
		for(Iterator<?> iter = list2.iterator(); iter.hasNext();)
		{
			reference=iter.next().toString();

		}
        
		String Sql="select '"+fromDate+"' as StartDate,'"+toDate+"' as EndDate,tbAccftransection.voucherNo,ISNULL(tbAccftransection.RegNo,'') as RegNo,tbAccftransection.date,((select ISNULL((select tbAccfledger.openingBalance from tbAccfledger where reference='"+reference+"' and date <'"+toDate+"'),0))+(select (select ISNULL(sum(tbAccftransection.amount),0)) from tbAccftransection where tbAccftransection.approve='1' and  tbAccftransection.d_l_id='"+reference+"' and date <'"+toDate+"' ))-(select (select ISNULL(sum(tbAccftransection.amount),0)) from tbAccftransection where tbAccftransection.approve='1' and  tbAccftransection.c_l_id='"+reference+"' and date <'"+toDate+"')as OB,(select ledgerTitle from tbAccfledger where reference='"+reference+"')as ledger,(select ledgerTitle from tbAccfledger where reference=tbAccftransection.d_l_id ) debitledger,(select ledgerTitle from tbAccfledger where reference=tbAccftransection.c_l_id)as creditLedger ,(select ledgerTitle from tbAccfledger where reference=tbAccftransection.c_l_id) as perticular,tbAccftransection.amount,tbAccftransection.description,(select username from tblogin where id=tbAccftransection.UserId)as username from tbAccftransection where approve='1' and  (tbAccftransection.d_l_id='"+reference+"' or  tbAccftransection.c_l_id='"+reference+"') and date between '"+fromDate+"' and '"+toDate+"'";
      	System.out.println("sql "+Sql);
      	
      	
      	
		String 	jrxmlFile =session.getServletContext().getRealPath("WEB-INF/jasper/Accounts/LedgerReport.jrxml");
		
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