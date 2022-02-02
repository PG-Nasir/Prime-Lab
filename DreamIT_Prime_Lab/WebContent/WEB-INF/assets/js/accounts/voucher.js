var ledgerlist=null;
var i=1;
var j=1;

var col3=3;
var col4=4;
$('#costCenterId').val('1');
$('#costCenterId').selectpicker('refresh');

$('#debit_table th:nth-child('+col3+'),#debit_table td:nth-child('+col3+')').hide();
$('#debit_table th:nth-child('+col4+'),#debit_table td:nth-child('+col4+')').hide();

$('#credit_table th:nth-child('+col3+'),#credit_table td:nth-child('+col3+')').hide();
$('#credit_table th:nth-child('+col4+'),#credit_table td:nth-child('+col4+')').hide();

function TypeWiseVoucherNo(){
	var voucherType = $("#voucherType").val();
	$.ajax({
		type: 'POST',
		dataType: 'json',
		url: './typeWiseVoucherNo/'+voucherType,
		success: function (data) {
			//alert(data);
			
			$('#voucherNo').val(data);
	/*		if(data=='Voucher Create Successfully'){
				alert(data);
				refreshAction();
			}
			else{
				alert(data);
			}*/
		}
	});
}

$( "#voucherNoSearch" ).keyup(function(e) {
	
	var voucherType=$('#voucherTypeS').val();
	var voucherNoSearch=$('#voucherNoSearch').val();
	
	
	 if(e.key==='ArrowDown' || e.key==='ArrowUp') {
		setAccountsVoucherData(voucherType,voucherNoSearch);
	 }
	 
});

function setAccountsVoucherData(voucherType,voucherNoSearch){
	alert("Voucher No "+voucherNoSearch);
	alert("Voucher Type "+voucherType);
	
	
}

function saveAction(){
	var userId=$("#userId").val();
	var voucherNo=$("#voucherNo").val();
	var paymentType = $("#voucherType").val();
	var date = $("#date").val();
	var costCenterId = $("#costCenterId").val();
	var standBy = $("#standBy").is(':checked') ? '1' : '0';
	console.log("paymentType "+paymentType);
	
	var totalDebit = $("#txtTotalDebit").val();
	var totalCredit = $("#txtTotalCredit").val();
	
	if(voucherNo!=''){
			if(date!=''){
					if(costCenterId!='0'){
						if(totalDebit==totalCredit){
							var i = 0;
							var value = 0;
							var debitresultList = [];
							var creditresultList = [];
							$('.debititemrow').each(function () {

								var id = $(this).attr("data-id");
								
								console.log("id "+id);

								var debitLedgerId=$('#debitLedger-'+id).val();
								var accAmount=$('#accDebitAmount-'+id).val()==''?"0":$('#accDebitAmount-'+id).val();
								var accChequeNo=$('#accDebitChequeNo-'+id).val()==''?"0":$('#accDebitChequeNo-'+id).val();
								var accChequeDate=$('#accDebitChequeDate-'+id).val()==''?"0":$('#accDebitChequeDate-'+id).val();
								var description=$('#accDebitDescription-'+id).val()==''?".":$('#accDebitDescription-'+id).val();

								console.log("debitLedgerId "+debitLedgerId);
								console.log("accAmount "+accAmount);
								console.log("description "+description);
								debitresultList[i] = debitLedgerId + "*" + accAmount + "*" + description+"*"+accChequeNo+"*"+accChequeDate;
								i++;
							});
							
							debitresultList = "[" + debitresultList + "]";
							
							$('.credititemrow').each(function () {

								var id = $(this).attr("data-id");

								var creditLedgerId=$('#creditLedger-'+id).val();
								var accAmount=$('#accCreditAmount-'+id).val()==''?"0":$('#accCreditAmount-'+id).val();
								var accChequeNo=$('#accCreditChequeNo-'+id).val()==''?"0":$('#accCreditChequeNo-'+id).val();
								var accChequeDate=$('#accCreditChequeDate-'+id).val()==''?"0":$('#accCreditChequeDate-'+id).val();
								var description=$('#accCreditDescription-'+id).val()==''?".":$('#accCreditDescription-'+id).val();

								console.log("debitLedgerId "+creditLedgerId);
								console.log("accAmount "+accAmount);
								console.log("description "+description);
								creditresultList[i] = creditLedgerId + "*" + accAmount + "*" + description+"*"+accChequeNo+"*"+accChequeDate;
								i++;
							});
							
							creditresultList = "[" + creditresultList + "]";
							
							if (confirm("Are you sure to Submit?")) {
								$.ajax({
									type: 'POST',
									dataType: 'json',
									data: {
										voucherNo: voucherNo,
										date: date,
										costCenterId: costCenterId,
										paymentType:paymentType,
										userId:userId,
										debitresultList:debitresultList,
										creditresultList:creditresultList,
										standBy:standBy,
										
									},
									url: './saveAllTypesVoucher/',
									success: function (data) {
										if(data=='Voucher Create Successfully'){
											alert(data);
											refreshAction();
										}
										else{
											alert(data);
										}
									}
								});
							}
						}
						else{
							alert("Provide Equal Debit and Credit Amount");
						}
						

					}
					else{
						alert("Provide Cost Center");
					}
			}
			else{
				alert("Provide Date");
			}
	}
	else{
		alert("Provide Voucher No");
	}
	
}

function addDebitRow(){
	
	
	
	$('#debit_trlist').append("<tr class='debititemrow' data-id='"+i+"'>" +
			"<td>"+i+"</td>" +
			"<td style='width:280px'><select id='debitLedger-"+i+"'  class='selectpicker debitLedger-"+i+" employee-width tableSelect  col-md-12 px-0' data-live-search='true'  data-style='btn-light btn-sm border-light-gray' onchange='DebitEditAmount("+i+")' >" + ledgerlist + "</select></td>" +
			"<td style='width:170px;'><input readonly id='accDebitChequeNo-"+i+"' style='width:170px;' type='text'  class='form-control-sm accDebitChequeNo-"+i+"'  value=''/></td>" +
			"<td style='width:170px;'><input readonly id='accDebitChequeDate-"+i+"' style='width:170px;' type='date'  class='form-control-sm accDebitChequeDate-"+i+"'  value=''/></td>" +
			"<td style='width:170px;'><input readonly id='accDebitAmount-"+i+"' style='width:170px;' type='text'  onkeyup='setTotalDebitQty()' class='form-control-sm accDebitAmount-"+i+"'  value=''/></td>" +
			"<td style='width:200px;'><input id='accDebitDescription-"+i+"' style='width:200px;' type='text'  class='form-control-sm accDebitDescription-"+i+"'  value=''/></td>" +
			"<td><i class='fa fa-plus' onclick=addDebitRow()> </i></td>" +
			"<td><i class='fa fa-minus' onclick=deleteDebitRow()> </i></td>" +
			"<td><i class='fa fa-edit' onclick=editDebitRow()> </i></td>" +
					"</tr>");
		i++;
		$('#debit_table th:nth-child('+col3+'),#debit_table td:nth-child('+col3+')').hide();
		$('#debit_table th:nth-child('+col4+'),#debit_table td:nth-child('+col4+')').hide();
	$('.tableSelect').selectpicker('refresh');
}

function addCreditRow(){
	
	var totalCreditAmount=0;
	$('.credititemrow').each(function () {
		var id = $(this).attr("data-id");
		totalCreditAmount=totalCreditAmount+parseFloat($('#accCreditAmount-'+id).val()==''?"0":$('#accCreditAmount-'+id).val());			
	});
	

	var totalDebitAmount=parseFloat($('#txtTotalDebit').val());
	
	var nextCredit=totalDebitAmount-totalCreditAmount;
	
	
	$('#credit_trlist').append("<tr class='credititemrow' data-id='"+j+"'>" +
			"<td>"+j+"</td>" +
			"<td style='width:280px'><select id='creditLedger-"+j+"'  class='selectpicker creditLedger-"+j+" employee-width tableSelect  col-md-12 px-0' data-live-search='true'  data-style='btn-light btn-sm border-light-gray' onchange='CreditEditAmount("+j+")' >" + ledgerlist + "</select></td>" +
			"<td style='width:170px;'><input readonly id='accCreditChequeNo-"+j+"' style='width:170px;' type='text'  class='form-control-sm accCreditChequeNo-"+j+"'  value=''/></td>" +
			"<td style='width:170px;'><input readonly id='accCreditChequeDate-"+j+"' style='width:170px;' type='date'  class='form-control-sm accCreditChequeDate-"+j+"'  value=''/></td>" +
			"<td style='width:170px;'><input readonly id='accCreditAmount-"+j+"' style='width:170px;' type='text'  onkeyup='setTotalCreditQty()' class='form-control-sm accCreditAmount-"+j+"'  value=''/></td>" +
			"<td style='width:200px;'><input id='accCreditDescription-"+j+"' style='width:200px;' type='text'  class='form-control-sm accCreditDescription-"+j+"'  value=''/></td>" +
			"<td><i class='fa fa-plus' onclick=addCreditRow()> </i></td>" +
			"<td><i class='fa fa-minus' onclick=deleteCreditRow()> </i></td>" +
			"<td><i class='fa fa-edit' onclick=editCreditRow()> </i></td>" +
					"</tr>");
		$('#accCreditAmount-'+j).val(parseFloat(nextCredit).toFixed(2));
	j++;
		$('#credit_table th:nth-child('+col3+'),#credit_table td:nth-child('+col3+')').hide();
		$('#credit_table th:nth-child('+col4+'),#credit_table td:nth-child('+col4+')').hide();
		
		setTotalCreditQty();
		
	$('.tableSelect').selectpicker('refresh');
	
	
}


function getOptions(dataList) {
	let options = "";
	var length = dataList.length;

	options += "<option value='0'>Select Account</option>"
	for (var i = 0; i < length; i++) {
		var item = dataList[i];
		console.log("ledgerlist "+item.ledgerName);
		options += "<option  data-head='"+item.headName+"' value='" + item.ledgerId + "'>" + item.ledgerName + "</option>"
	}
	return options;
};


function loadLedgerList() {

	  $.ajax({
	    type: 'GET',
	    dataType: 'json',
	    url: './getLedgerList',
	    success: function (data) {
	    	console.log("data "+data.ledgerresult);
	    	
	    	ledgerlist = getOptions(data.ledgerresult);
	    	
	  	  for(var a=0;a<3;a++){
				$('#debit_trlist').append("<tr class='debititemrow' data-id='"+i+"'>" +
						"<td>"+i+"</td>" +
						"<td style='width:280px'><select id='debitLedger-"+i+"'  class='selectpicker debitLedger-"+i+" employee-width tableSelect  col-md-12 px-0' data-live-search='true'  data-style='btn-light btn-sm border-light-gray' onchange='DebitEditAmount("+i+")' >" + ledgerlist + "</select></td>" +
						"<td style='width:170px;'><input readonly id='accDebitChequeNo-"+i+"' style='width:170px;' type='text'  class='form-control-sm accDebitChequeNo-"+i+"'  value=''/></td>" +
						"<td style='width:170px;'><input readonly id='accDebitChequeDate-"+i+"' style='width:170px;' type='date'  class='form-control-sm accDebitChequeDate-"+i+"'  value=''/></td>" +
						"<td style='width:170px;'><input readonly id='accDebitAmount-"+i+"' style='width:170px;' type='text'  onkeyup='setTotalDebitQty()' class='form-control-sm accDebitAmount-"+i+"'  value=''/></td>" +
						"<td style='width:200px;'><input readonly id='accDebitDescription-"+i+"' style='width:200px;' type='text'  class='form-control-sm accDebitDescription-"+i+"'   value=''/></td>" +
						"<td><i class='fa fa-plus' onclick=addDebitRow()> </i></td>" +
						"<td><i class='fa fa-minus' onclick=deleteDebitRow()> </i></td>" +
						"<td><i class='fa fa-edit' onclick=editDebitRow()> </i></td>" +
								"</tr>");
					i++;
					$('#debit_table th:nth-child('+col3+'),#debit_table td:nth-child('+col3+')').hide();
					$('#debit_table th:nth-child('+col4+'),#debit_table td:nth-child('+col4+')').hide();
				$('.tableSelect').selectpicker('refresh');
		  }
	  	  
	  	  for(var a=0;a<1;a++){
				$('#credit_trlist').append("<tr class='credititemrow' data-id='"+j+"'>" +
						"<td>"+j+"</td>" +
						"<td style='width:280px'><select id='creditLedger-"+j+"'  class='selectpicker creditLedger-"+j+" employee-width tableSelect  col-md-12 px-0' data-live-search='true'  data-style='btn-light btn-sm border-light-gray' onchange='CreditEditAmount("+j+")' >" + ledgerlist + "</select></td>" +
						"<td style='width:170px;'><input readonly id='accCreditChequeNo-"+j+"' style='width:170px;' type='text'  class='form-control-sm accCreditChequeNo-"+j+"'  value=''/></td>" +
						"<td style='width:170px;'><input readonly id='accCreditChequeDate-"+j+"' style='width:170px;' type='date'  class='form-control-sm accCreditChequeDate-"+j+"'  value=''/></td>" +
						"<td style='width:170px;'><input readonly id='accCreditAmount-"+j+"' style='width:170px;' type='text'  onkeyup='setTotalCreditQty()' class='form-control-sm accCreditAmount-"+j+"'  value=''/></td>" +
						"<td style='width:200px;'><input id='accCreditDescription-"+j+"' style='width:200px;' type='text'  class='form-control-sm accCreditDescription-"+j+"'  value=''/></td>" +
						"<td><i class='fa fa-plus' onclick=addCreditRow()> </i></td>" +
						"<td><i class='fa fa-minus' onclick=deleteCreditRow()> </i></td>" +
						"<td><i class='fa fa-edit' onclick=editCreditRow()> </i></td>" +
								"</tr>");
					j++;
					$('#credit_table th:nth-child('+col3+'),#credit_table td:nth-child('+col3+')').hide();
					$('#credit_table th:nth-child('+col4+'),#credit_table td:nth-child('+col4+')').hide();
				$('.tableSelect').selectpicker('refresh');
		  }
	    	
	    	//getOptions(data.ledgerresult);
	    }
	  });
	  
}

window.onload = () => {
	loadLedgerList();
	
}



function setTotalDebitQty(){
	value=0;
	$('.debititemrow').each(function () {
		var id = $(this).attr("data-id");
		var accAmount=parseFloat($('.accDebitAmount-'+id).val()==''?"0":$('.accDebitAmount-'+id).val());
		var ledgerId=$('#debitLedger-'+id).val();
		console.log("ledgerId "+ledgerId);
		if(ledgerId!='0'){
			value=value+accAmount;
		}
	
	});
	
	$('#txtTotalDebit').val(value);
	var col2=2;
	$('#accCreditAmount-1').val(parseFloat(value).toFixed(2));

}



function DebitEditAmount(number){
	var ledgerValue=$('#debitLedger-'+number).val();
	var col3=3;
	var col4=4;
	
	console.log("number "+number);
	
	var headName=$('#debitLedger-'+number).find('option:selected').attr('data-head');
	if(headName=='Bank'){
		$('.accDebitChequeNo-'+number).prop('readonly', false);
		$('.accDebitChequeDate-'+number).prop('readonly', false);

		$('#debit_table th:nth-child('+col3+'),#debit_table td:nth-child('+col3+')').show();
		$('#debit_table th:nth-child('+col4+'),#debit_table td:nth-child('+col4+')').show();
        
	}
	else{
		
		$('#debit_table th:nth-child('+col3+'),#debit_table td:nth-child('+col3+')').hide();
		$('#debit_table th:nth-child('+col4+'),#debit_table td:nth-child('+col4+')').hide();
	    
		$('.accDebitChequeNo-'+number).prop('readonly', true);
		$('.accDebitChequeDate-'+number).prop('readonly', true);
	}
	
	if(ledgerValue!='0'){
		$('.accDebitAmount-'+number).prop('readonly', false);
		
	}
	else{
		$('.accDebitAmount-'+number).prop('readonly', true);
		$('#accDebitAmount-'+number).val(0);
	}
	
	setTotalDebitQty();
}

function setTotalCreditQty(){
	value=0;
	$('.credititemrow').each(function () {
		var id = $(this).attr("data-id");
		var accAmount=parseFloat($('.accCreditAmount-'+id).val()==''?"0":$('.accCreditAmount-'+id).val());
		var ledgerId=$('#creditLedger-'+id).val();
		console.log("ledgerId "+ledgerId);
		if(ledgerId!='0'){
			value=value+accAmount;
		}
	
	});
	
	$('#txtTotalCredit').val(value);

}

function CreditEditAmount(number){
	var ledgerValue=$('#creditLedger-'+number).val();
	
	var headName=$('#creditLedger-'+number).find('option:selected').attr('data-head');
	
	console.log("headName "+headName);
	if(headName=='Bank'){
		$('.accCreditChequeNo-'+number).prop('readonly', false);
		$('.accCreditChequeDate-'+number).prop('readonly', false);
		
		$('#credit_table th:nth-child('+col3+'),#credit_table td:nth-child('+col3+')').show();
		$('#credit_table th:nth-child('+col4+'),#credit_table td:nth-child('+col4+')').show();
	}
	else{
		
		$('#credit_table th:nth-child('+col3+'),#credit_table td:nth-child('+col3+')').hide();
		$('#credit_table th:nth-child('+col4+'),#credit_table td:nth-child('+col4+')').hide();
		
		$('.accCreditChequeNo-'+number).prop('readonly', true);
		$('.accCreditChequeDate-'+number).prop('readonly', true);
	}
	
	if(ledgerValue!='0'){
		$('.accCreditAmount-'+number).prop('readonly', false);
		
	}
	else{
		$('.accCreditAmount-'+number).prop('readonly', true);
		$('#accCreditAmount-'+number).val(0);
	}
	
	setTotalCreditQty();
}


function refreshAction() {
	location.reload();
}


function PaymentVoucherList(){
	
	
	var voucherNoSearch = $("#voucherNoSearch").val();
	var fromDate = $("#fromDate").val();
	var toDate = $("#toDate").val();
	var voucherType = $("#voucherTypeS").val();
	var approveType = $("#approveType").val();
	

	$.ajax({
		type: 'POST',
		dataType: 'json',
		data: {
			voucherNoSearch: voucherNoSearch,
			fromDate: fromDate,
			toDate: toDate,
			voucherType: voucherType,
			approveType:approveType
		},
		url: './getPaymentVoucherListForApprove/',
		success: function (data) {
		      $("#payment_voucher_list").empty();
		      patchVoucherdata(data.paymentVoucherList);
		}
	});
}


function patchVoucherdata(data){
	var rows = [];
	
	for (var i = 0; i < data.length; i++) {
		rows.push(drawVoucherDataRow(data[i],i+1));
		
	}

	$("#payment_voucher_list").append(rows);
}

function drawVoucherDataRow(rowData,c) {
	
	var row = $("<tr class='itemrow' data-id='"+c+"' />")
	row.append($("<td>" + c + "</td>"));
	row.append($("<td >" + rowData.voucherNo+ "</td>"));
	row.append($("<td >" + rowData.paymentType+ "</td>"));
	row.append($("<td >" + rowData.date+ "</td>"));
	console.log("approveBy "+rowData.approveBy);
	console.log("approveBy "+rowData.approveAt);
	row.append($("<td >"+rowData.approveBy+"</td>"));
	row.append($("<td >"+rowData.approveAt+"</td>"));
	row.append($("<td ><i class='fa fa-print' onclick=VoucherPrint("+rowData.voucherNo+","+rowData.accountType+")> </i></td>"));
	
	return row;
}


function VoucherPrint(voucherNo,paymentType){
	
	console.log("voucherNo "+voucherNo);
	console.log("paymentType "+paymentType);
	var url = `JournalVoucherPrint/${voucherNo}@${paymentType}`;
	window.open(url, '_blank');
	
}



var today = new Date();

document.getElementById("date").value = today.getFullYear() + '-' + ('0' + (today.getMonth() + 1)).slice(-2) + '-' + ('0' + today.getDate()).slice(-2);
document.getElementById("fromDate").value = today.getFullYear() + '-' + ('0' + (today.getMonth() + 1)).slice(-2) + '-' + ('0' + today.getDate()).slice(-2);
document.getElementById("toDate").value = today.getFullYear() + '-' + ('0' + (today.getMonth() + 1)).slice(-2) + '-' + ('0' + today.getDate()).slice(-2);

