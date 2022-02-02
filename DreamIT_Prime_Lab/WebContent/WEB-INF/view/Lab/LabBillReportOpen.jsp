<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



 <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css">
<div class="container" id="print">

<%
	String BillNo = (String)request.getAttribute("BillNo"); 
	String PatientName = (String)request.getAttribute("PatientName"); 
	String Sex = (String)request.getAttribute("Sex"); 
	String Mobile = (String)request.getAttribute("Mobile"); 
	String Age = (String)request.getAttribute("Age"); 
	String Month = (String)request.getAttribute("Month"); 
	String Day = (String)request.getAttribute("Day"); 
	String Reffer = (String)request.getAttribute("Reffer"); 
	String BillDate = (String)request.getAttribute("BillDate"); 
	String BedCabin = (String)request.getAttribute("BedCabin"); 
	
	String TotalAmount = (String)request.getAttribute("TotalAmount"); 
	String TotalPrDiscounTaka = (String)request.getAttribute("TotalPrDiscounTaka"); 
	String TotalMDiscounTaka = (String)request.getAttribute("TotalMDiscounTaka"); 
	String TotalDiscounTaka = (String)request.getAttribute("TotalDiscounTaka"); 
	
	String TotalPayableTaka = (String)request.getAttribute("TotalPayableTaka"); 
	String Paid = (String)request.getAttribute("Paid"); 
	String Due = (String)request.getAttribute("Due"); 
	

%>

	<script type="text/javascript">     
			function PrintDiv() {
				
				var divToPrint = document.getElementById('print');
				var popupWin = window.open('', '_blank', 'width=900,height=900');
				popupWin.document.open();
				popupWin.document.write('<html><head><link href="https://www.cursorbd.com/obsnew/css/bootstrap.min.css" rel="stylesheet"></head><body onload="window.print()">' + divToPrint.innerHTML + '</html>');
				popupWin.document.close();
				
				
			}
			
		</script>

	  <div class="container invoice" style="width: 528px;height: 787.2px;">
          <div class="card">
           <div class="card-header text-center">
           
	        <div  class="row">
		         <div style="width:30px;" class="col-lg-1 col-sm-1">
		 			  <img style="width:80px;" src="assets/img/cursor.png" alt="logo" class="float-left">
		        </div>
		         <div class="col-lg-11 col-sm-11">
		             <h5>SURGISCOPE HOSPITAL LTD. (Unit-2)</h5>
           			      445/466, Katalgonj, Chawkbazar <br>
           				  031 652201, 031 652203, 2555101-5
		        </div>
	        </div>

           </div>

           <div class="text-center mt-4">
              <span class="text-center border p-2">Mony Receipt</span>
            </div>  

        <div class="card-body">
         <div class="row mb-4">
          <div class="col-sm-6">
           <h6 class="mb-3">Bill: <strong><%=BillNo %></strong></h6>
           <h6 class="">Name: <strong><%=PatientName %></strong></h6>
           <h6 class="">Sex: <strong><%=Sex %></strong></h6>
           <h6 class="">Mobile: <strong><%=Mobile %></strong></h6>
           <h6 class="">Refd By: <strong><%=Reffer %></strong></h6>
           

          </div>

        <div class="col-sm-6">
         <h6 class="">Date: <strong><%=BillDate %></strong></h6>
         <h6 class="">Age: <strong><%=Age %>Y</strong> <strong>M:<%=Month %></strong> <strong>D:<%=Day %></strong></h6>
         <h6 class="">Cabin: <strong><%=BedCabin %></strong></h6>
        
        
        </div>



        </div>

        <div class="table-responsive-sm">
        <table class="table table-striped">
        <thead>
        <tr>
        <th class="center">No</th>
        <th>Test Name</th>
        <th class="right">Amount (Tk)</th>
        </tr>

        </thead>

        <tbody>

	    <c:forEach items="${labinformation.result}" var="item" varStatus="count">	
        <tr>
        <td class="center">${item.sl}</td>
        <td class="left strong">${item.testname}</td>
        <td class="right">${item.rate}</td>
        </tr>
       	</c:forEach>
      
        </tbody>
        </table>
        </div>
        
        <div class="row">
          <div class="col-lg-4 col-sm-5">

        </div>

        <div class="col-sm-6 ml-auto">
         <table class="table table-clear" style="margin-left:50px;font-size: 14px;">
        <tbody>

        <tr>
         <td class="left">
         <strong>Total</strong>
         </td>
         <td class="right"><strong><%=TotalAmount%></strong></td>
        </tr>

        <tr>
         <td class="left">
         <strong>Discount %20.0</strong>
         </td>
         <td class="right"><strong><%=TotalPrDiscounTaka%></strong></td>
        </tr>

        <tr>
         <td class="left">
          <strong>Sp. Discount</strong>
         </td>
         <td class="right"><strong><%=TotalMDiscounTaka%></strong></td>
         

        </tr>
        
        <tr>
         <td class="left">
         <strong>Net Payable</strong>
         </td>
         <td class="right">
         <strong><%=TotalPayableTaka%></strong>
         </td>
        </tr>

        <tr>
         <td class="left">
         <strong>Total Paid</strong>
         </td>
         <td class="right">
         <strong><%=Paid%></strong>
         </td>
        </tr>


        <tr>
         <td class="left">
         <strong>Due</strong>
         </td>
         <td class="right">
         <strong><%=Due%></strong>
         </td>
        </tr>

        </tbody>
        </table>

        </div>

        </div>

        </div>
        </div>
        </div>
	<script src="https://www.cursorbd.com/obsnew/js/jquery.min.js"></script>


</div>

<jsp:include page="../include/footer.jsp" />