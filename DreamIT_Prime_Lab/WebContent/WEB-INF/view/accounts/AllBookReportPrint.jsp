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
    	String voucherType=(String)request.getAttribute("voucherType");
		
    	
     	HashMap map=new HashMap();

     	map.put("orgName", orgranizationInfo.get(0).getOrganizationName());
    	map.put("orgAddress", orgranizationInfo.get(0).getOrganizationAddress());
    	map.put("orgContact", orgranizationInfo.get(0).getOrganizationContact());
    	
    	System.out.println("orgAddress"+orgranizationInfo.get(0).getOrganizationAddress());
        SpringRootConfig sp=new SpringRootConfig();
        
		String Sql=null;
		
		if(voucherType.equals("0")){
			Sql="select a.type,a.date,a.voucherNo,(select Narration from TbVoucherType where type=a.type) as Narration,ISNULL((select ledgerTitle from tbAccfledger where reference=a.d_l_id),'') as DebitLedger,ISNULL((select LedgerTitle from tbAccfledger where reference=a.c_l_id),'') as CreditLedger,amount,(select name from TbCostCenterInfo where costCenterId=a.costCenterId) as CostCenterName,'"+fromDate+"' as FromDate,'"+toDate+"' as ToDate from tbAccftransection a where a.approve='1' and a.date between '"+fromDate+"' and '"+toDate+"' order by a.type,a.voucherNo";
		}
		else{
			Sql="select a.type,a.date,a.voucherNo,(select Narration from TbVoucherType where type=a.type) as Narration,ISNULL((select ledgerTitle from tbAccfledger where reference=a.d_l_id),'') as DebitLedger,ISNULL((select LedgerTitle from tbAccfledger where reference=a.c_l_id),'') as CreditLedger,amount,(select name from TbCostCenterInfo where costCenterId=a.costCenterId) as CostCenterName,'"+fromDate+"' as FromDate,'"+toDate+"' as ToDate from tbAccftransection a where a.type='"+voucherType+"'  and a.approve='1' and a.date between '"+fromDate+"' and '"+toDate+"'";
		}
	
		
      	System.out.println("sql "+Sql);

      	
		String jrxmlFile =session.getServletContext().getRealPath("WEB-INF/jasper/Accounts/DayBookReport.jrxml");
	
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