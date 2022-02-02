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
    	String bookType=(String)request.getAttribute("bookType");
		
    	
     	HashMap map=new HashMap();
       	map.put("fromDate", fromDate);
    	map.put("toDate",toDate);
     	map.put("orgName", orgranizationInfo.get(0).getOrganizationName());
    	map.put("orgAddress", orgranizationInfo.get(0).getOrganizationAddress());
    	map.put("orgContact", orgranizationInfo.get(0).getOrganizationContact());
    	
    	System.out.println("orgAddress"+orgranizationInfo.get(0).getOrganizationAddress());
        SpringRootConfig sp=new SpringRootConfig();
        
		String Sql=null;
		if(bookType.equals("Cash Book")){
			Sql="select '"+fromDate+"' as fromDate,'"+toDate+"' as toDate,a.d_l_id,a.c_l_id,a.type,a.voucherNo,a.date,a.amount,(select ledgerTitle from tbAccfledger where reference=a.d_l_id)as DebitLedger,(select ledgerTitle from tbAccfledger where reference=a.c_l_id)as CreditLedger,'Cash Book' as BookType,'Debit' as TrasactionType  from tbAccftransection a where a.d_l_id like ''+(select reference from tbAccfhead where headTitle='Cash')+'%' and a.approve='1' and a.date between '"+fromDate+"' and '"+toDate+"'"+ 
				"UNION ALL select '"+fromDate+"' as fromDate,'"+toDate+"' as toDate,a.d_l_id,a.c_l_id,a.type,a.voucherNo,a.date,a.amount,(select ledgerTitle from tbAccfledger where reference=a.d_l_id)as DebitLedger,(select ledgerTitle from tbAccfledger where reference=a.c_l_id)as CreditLedger,'Cash Book' as BookType,'Credit' as TrasactionType from tbAccftransection a where a.c_l_id like ''+(select reference from tbAccfhead where headTitle='Cash')+'%'  and a.approve='1' and a.date between '"+fromDate+"' and '"+toDate+"'";
	
		}
		else{
			Sql="select '"+fromDate+"' as fromDate,'"+toDate+"' as toDate,a.d_l_id,a.c_l_id,a.type,a.voucherNo,a.date,a.amount,(select ledgerTitle from tbAccfledger where reference=a.d_l_id)as DebitLedger,(select ledgerTitle from tbAccfledger where reference=a.c_l_id)as CreditLedger,'Bank Book' as BookType,'Debit' as TrasactionType  from tbAccftransection a where a.d_l_id like ''+(select reference from tbAccfhead where headTitle='Bank')+'%'  and a.approve='1' and a.date between '"+fromDate+"' and '"+toDate+"'"+ 
					"UNION ALL select '"+fromDate+"' as fromDate,'"+toDate+"' as toDate,a.d_l_id,a.c_l_id,a.type,a.voucherNo,a.date,a.amount,(select ledgerTitle from tbAccfledger where reference=a.d_l_id)as DebitLedger,(select ledgerTitle from tbAccfledger where reference=a.c_l_id)as CreditLedger,'Bank Book' as BookType,'Credit' as TrasactionType from tbAccftransection a where a.c_l_id like ''+(select reference from tbAccfhead where headTitle='Bank')+'%'  and a.approve='1' and a.date between '"+fromDate+"' and '"+toDate+"'";
		
		}
		
      	System.out.println("sql "+Sql);
      	
      	
      	
		String jrxmlFile =session.getServletContext().getRealPath("WEB-INF/jasper/Accounts/AccountsBookReport.jrxml");
	
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