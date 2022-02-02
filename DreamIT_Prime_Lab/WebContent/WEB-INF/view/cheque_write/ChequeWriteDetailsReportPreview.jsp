<%@page import="net.sf.jasperreports.engine.export.JRXlsExporter"%>
<%@ page contentType="application/pdf" %>

<%@ page trimDirectiveWhitespaces="true"%>


<%@ page import="net.sf.jasperreports.engine.design.JRDesignQuery" %>
<%@ page import="net.sf.jasperreports.engine.design.JasperDesign" %>
<%@ page import="net.sf.jasperreports.engine.xml.JRXmlLoader" %>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.ResultSet"%>
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
<%@ page import="java.util.HashMap" %>
<%@ page import="pg.config.*" %>

<%

	String writerId=(String) request.getAttribute("writerId");
	
	
    try {
    	
    	SpringRootConfig sp=new SpringRootConfig();    
    	List<HashMap<String,String>> datalist=new ArrayList<HashMap<String,String>>();    	
    	Session dbsession=HibernateUtil.openSession();
		Transaction tx=null;
        
	
		String ReportName="";
        SpringRootConfig sp1=new SpringRootConfig();
    	Session dbsession1=HibernateUtil.openSession();
        
		String Sql1="select bankId,ReportName from TbBankName a where a.bankId=(select bankId from TbChequeWriter a where a.WriterId='"+writerId+"')";
		
		List<?> list = dbsession.createSQLQuery(Sql1).list();
		
		for(Iterator<?> iter = list.iterator(); iter.hasNext();)
		{
			Object[] element = (Object[]) iter.next();
			
			ReportName=element[1].toString();
			break;			
			/* map.put("BuyerName",element[0].toString());
			map.put("BuyerTelePhone",element[1].toString());
			map.put("BuyerMobile",element[2].toString());
			map.put("BuyerFax",element[3].toString());
			map.put("BuyerEmail",element[4].toString());
			map.put("BuyerAddress",element[5].toString());
			map.put("NotifyAddress",element[6].toString()); */
		
		}
		
		
		
		String Sql="";
		Sql="select (select dbo.number((a.Amount))) as Taka,WriterId,(select BankName from TbBankName where BankId=a.BankId) as BankName,a.PayTo,a.Amount,(select convert(varchar,a.Date,105)) as Date,Type,a.Entrytime,a.UserId,(select fullname from  Tblogin where id=a.userId) as Username from TbChequeWriter a where a.WriterId='"+writerId+"'";
		String jrxmlFile=session.getServletContext().getRealPath(ReportName);;
		System.out.println(Sql);	
        InputStream input = new FileInputStream(new File(jrxmlFile));
    	JasperDesign jd=JRXmlLoader.load(input);
		JRDesignQuery jq=new JRDesignQuery();
		
		jq.setText(Sql);
		jd.setQuery(jq);
		
        //Generating the report
        JasperReport jr = JasperCompileManager.compileReport(jd);
      
        JasperPrint jp = JasperFillManager.fillReport(jr, null, sp.getDataSource().getConnection());

        //Exporting the report as a PDF
        JasperExportManager.exportReportToPdfStream(jp, response.getOutputStream());
		
		
		
        //Generating the report
      
		
		

    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (JRException e) {
        e.printStackTrace();
    } 
    


%>