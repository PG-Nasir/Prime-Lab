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
    	String voucherNo=(String)request.getAttribute("voucherNo");
    	String paymentType=(String)request.getAttribute("paymentType");
		
    	
     	HashMap map=new HashMap();
     	map.put("orgName", orgranizationInfo.get(0).getOrganizationName());
    	map.put("orgAddress", orgranizationInfo.get(0).getOrganizationAddress());
    	map.put("orgContact", orgranizationInfo.get(0).getOrganizationContact());
    	
    	System.out.println("orgAddress"+orgranizationInfo.get(0).getOrganizationAddress());
        SpringRootConfig sp=new SpringRootConfig();
        
		String Sql="";
		if(paymentType.equals("1") || paymentType.equals("6")){
			Sql="select a.type,a.date,ISNULL((select Name from TbCostCenterInfo where costCenterId=a.costCenterId),'') as CostCenterName,a.voucherNo,(select username from Tblogin where id=a.UserId) as Username,(select narration from TbVoucherType where type=a.type)  as VoucherType,a.entryTime,a.description,a.amount,ISNULL(a.Note,'') as Note,ISNULL((select LedgerTitle from tbAccfledger where reference=a.d_l_id),'') as DebitLedger,STUFF((SELECT DISTINCT ','+(select ledgerTitle from tbAccfledger where reference =c.c_l_id) FROM tbAccftransection c where c.voucherNo=a.voucherNo and c.type=a.type and c.c_l_id!='0' FOR XML PATH(''), TYPE).value('.', 'nvarchar(max)'), 1, 1, '') as CreditLedger,(select dbo.number((select sum(amount) from tbAccftransection where type=a.type and voucherNo=a.voucherNo and d_l_id!='0'))) as Taka from tbAccftransection a where a.type='"+paymentType+"' and a.voucherNo='"+voucherNo+"' and a.d_l_id!='0'";
		}
		else if(paymentType.equals("3")){
			Sql="select a.type,a.date,ISNULL((select Name from TbCostCenterInfo where costCenterId=a.costCenterId),'') as CostCenterName,a.voucherNo,(select username from Tblogin where id=a.UserId) as Username,(select narration from TbVoucherType where type=a.type)  as VoucherType,a.entryTime,a.description,a.amount,ISNULL(a.Note,'') as Note,ISNULL((select LedgerTitle from tbAccfledger where reference=a.d_l_id),'') as DebitLedger,STUFF((SELECT DISTINCT ','+(select ledgerTitle from tbAccfledger where reference =c.c_l_id) FROM tbAccftransection c where c.voucherNo=a.voucherNo and c.type=a.type and c.c_l_id!='0' FOR XML PATH(''), TYPE).value('.', 'nvarchar(max)'), 1, 1, '') as CreditLedger,(select dbo.number((select sum(amount) from tbAccftransection where type=a.type and voucherNo=a.voucherNo and d_l_id!='0'))) as Taka from tbAccftransection a where a.type='"+paymentType+"' and a.voucherNo='"+voucherNo+"' and a.d_l_id!='0'";
		}
		
		else if(paymentType.equals("2")){
			Sql="select a.type,a.date,ISNULL((select Name from TbCostCenterInfo where costCenterId=a.costCenterId),'') as CostCenterName,a.voucherNo,(select username from Tblogin where id=a.UserId) as Username,(select narration from TbVoucherType where type=a.type)  as VoucherType,a.entryTime,a.description,ISNULL((select ledgerTitle from tbAccfledger where reference=d_l_id),'') as DebitLedger,STUFF((SELECT DISTINCT ','+(select ledgerTitle from tbAccfledger where reference =c.c_l_id) FROM tbAccftransection c where c.voucherNo=a.voucherNo and c.type=a.type and c.c_l_id!='0' FOR XML PATH(''), TYPE).value('.', 'nvarchar(max)'), 1, 1, '') as CreditLedger,STUFF((SELECT DISTINCT '#'+concat('Cheque No : ',c.chequeNumber,'>>','Cheque Date : ',c.chequeDate) FROM tbAccftransection c where c.voucherNo=a.voucherNo and c.type=a.type and c.c_l_id!='0' FOR XML PATH(''), TYPE).value('.', 'nvarchar(max)'), 1, 1, '') as ChequeNoDetails,STUFF((SELECT DISTINCT '#'+concat('',c.chequeNumber) FROM tbAccftransection c where c.voucherNo=a.voucherNo and c.type=a.type and c.c_l_id!='0' FOR XML PATH(''), TYPE).value('.', 'nvarchar(max)'), 1, 1, '') as ChequeFind,a.amount,(select dbo.number((select sum(amount) from tbAccftransection where type=a.type and voucherNo=a.voucherNo and d_l_id!='0'))) as Taka from tbAccftransection a where a.type='"+paymentType+"' and a.voucherNo='"+voucherNo+"' and a.d_l_id!='0'";
		}
		else if(paymentType.equals("4")){
			Sql="select a.type,a.date,ISNULL((select Name from TbCostCenterInfo where costCenterId=a.costCenterId),'') as CostCenterName,a.voucherNo,(select username from Tblogin where id=a.UserId) as Username,(select narration from TbVoucherType where type=a.type)  as VoucherType,a.entryTime,a.description,ISNULL((select ledgerTitle from tbAccfledger where reference=d_l_id),'') as DebitLedger,STUFF((SELECT DISTINCT ','+(select ledgerTitle from tbAccfledger where reference =c.c_l_id) FROM tbAccftransection c where c.voucherNo=a.voucherNo and c.type=a.type and c.c_l_id!='0' FOR XML PATH(''), TYPE).value('.', 'nvarchar(max)'), 1, 1, '') as CreditLedger,STUFF((SELECT DISTINCT '#'+concat('Cheque No : ',c.chequeNumber,'>>','Cheque Date : ',c.chequeDate) FROM tbAccftransection c where c.voucherNo=a.voucherNo and c.type=a.type and c.c_l_id!='0' FOR XML PATH(''), TYPE).value('.', 'nvarchar(max)'), 1, 1, '') as ChequeNoDetails,STUFF((SELECT DISTINCT '#'+concat('',c.chequeNumber) FROM tbAccftransection c where c.voucherNo=a.voucherNo and c.type=a.type and c.c_l_id!='0' FOR XML PATH(''), TYPE).value('.', 'nvarchar(max)'), 1, 1, '') as ChequeFind,a.amount,(select dbo.number((select sum(amount) from tbAccftransection where type=a.type and voucherNo=a.voucherNo and d_l_id!='0'))) as Taka from tbAccftransection a where a.type='"+paymentType+"' and a.voucherNo='"+voucherNo+"' and a.d_l_id!='0'";
		}
		else{
			Sql="select a.type,a.date,ISNULL((select Name from TbCostCenterInfo where costCenterId=a.costCenterId),'') as CostCenterName,a.voucherNo,(select username from Tblogin where id=a.UserId) as Username,(select narration from TbVoucherType where type=a.type)  as VoucherType,a.entryTime,a.description,a.amount,ISNULL(a.Note,'') as Note,ISNULL((select LedgerTitle from tbAccfledger where reference=a.d_l_id),'') as DebitLedger,ISNULL((select LedgerTitle from tbAccfledger where reference=a.c_l_id),'') as CreditLedger,(select dbo.number((select sum(amount) from tbAccftransection where type=a.type and voucherNo=a.voucherNo and d_l_id!='0'))) as Taka from tbAccftransection a where a.type='"+paymentType+"' and a.voucherNo='"+voucherNo+"'";
		}
		
      	System.out.println("sql "+Sql);
      	
      	
      	
		String jrxmlFile = null;
		if(paymentType.equals("1") || paymentType.equals("3") || paymentType.equals("6")){
			jrxmlFile =session.getServletContext().getRealPath("WEB-INF/jasper/Accounts/Cash_Payment_Receipt_Voucher.jrxml");
		}
		else if(paymentType.equals("2") || paymentType.equals("4") ){
			jrxmlFile =session.getServletContext().getRealPath("WEB-INF/jasper/Accounts/Bank_Payment_Receipt_Voucher.jrxml");
		}
		else if(paymentType.equals("5")){
			jrxmlFile =session.getServletContext().getRealPath("WEB-INF/jasper/Accounts/JournalVoucher.jrxml");
		}
		else if(paymentType.equals("12")){
			jrxmlFile =session.getServletContext().getRealPath("WEB-INF/jasper/Accounts/Pathology_Voucher.jrxml");
			
		}
		else if(paymentType.equals("13")){
			jrxmlFile =session.getServletContext().getRealPath("WEB-INF/jasper/Accounts/HospitalBillIndoor_Voucher.jrxml");
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