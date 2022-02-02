var ledgerlist=null;
var i=1;


function PreviewDailyStatement(){

	var fromDate = $("#fromDate").val();
	var toDate = $("#toDate").val();
	var statementType = $("#statementType").val();
	var url = `PreviewDailyStatement/${fromDate}@${toDate}@${statementType}`;
	window.open(url, '_blank');

	
}
function StatementViewList(){

	var fromDate = $("#fromDate").val();
	var toDate = $("#toDate").val();
	var statementType = $("#statementType").val();

	if(fromDate!='' && toDate!=''){
		$.ajax({
			type: 'POST',
			dataType: 'json',
			data: {
				fromDate: fromDate,
				toDate: toDate,
				statementType:statementType
			},
			url: './StatementViewList/',
			success: function (data) {



				//alert(data);
				$('#statement_details_list').empty();
				patchDailyStatementData(data.result);
				/*			if(data=='Voucher Create Successfully'){
					alert(data);
					refreshAction();
				}
				else{
					alert(data);
				}*/
			}
		});
	}
	else{
		aler("Provide from date and to date");
	}

}

function patchDailyStatementData(data){


	var rows;
	var j=0;
	var tempHead;
	var totalIncome=0;
	var totalExpense=0;

	var totalSalesAmt=0;
	var totalDiscountAmt=0;
	var totalCollectionAmt=0;
	var totalDueAmt=0;

	var totalExSalesAmt=0;
	var totalExDiscountAmt=0;
	var totalExCollectionAmt=0;
	var totalExDueAmt=0;

	for (var i = 0; i < data.length; i++) {

		if(data[i].type=='Income'){

			totalSalesAmt=totalSalesAmt+parseFloat(data[i].salesAmount);
			totalDiscountAmt=totalDiscountAmt+parseFloat(data[i].discountAmount);
			totalCollectionAmt=totalCollectionAmt+parseFloat(data[i].collectionAmount);
			totalDueAmt=totalDueAmt+parseFloat(data[i].dueAmount);

			if(j==0){

				rows=rows+"<tr style='background:yellow;font-weight:bold;font-size:18px;'>";
				tempHead=data[i].type;
				rows=rows+"<td></td><td>"+tempHead+"</td> <td></td> <td></td> <td></td> <td></td> <td></td></tr>";
				rows=rows+"<tr><td>"+(i+1)+"</td> <td>"+data[i].category+"</td> <td class='text-right'>"+parseFloat(data[i].salesAmount).toFixed(2)+"</td> <td class='text-right'>"+parseFloat(data[i].discountAmount).toFixed(2)+"</td> <td class='text-right'>"+parseFloat(data[i].collectionAmount).toFixed(2)+"</td> <td class='text-right'>"+parseFloat(data[i].dueAmount).toFixed(2)+"</td> <td ><i class='fa fa-search' data-toggle='modal' data-target='#incomeDetailsList' onclick=DetailsIncomeStatement('"+encodeURIComponent(data[i].category)+"','"+encodeURIComponent(data[i].dueAmount)+"')> </i></td></tr>";
				j++;
			}
			else{
				rows=rows+"<tr><td>"+(i+1)+"</td> <td>"+data[i].category+"</td> <td class='text-right'>"+parseFloat(data[i].salesAmount).toFixed(2)+"</td> <td class='text-right'>"+parseFloat(data[i].discountAmount).toFixed(2)+"</td> <td class='text-right'>"+parseFloat(data[i].collectionAmount).toFixed(2)+"</td> <td class='text-right'>"+parseFloat(data[i].dueAmount).toFixed(2)+"</td> <td ><i class='fa fa-search' data-toggle='modal' data-target='#incomeDetailsList' onclick=DetailsIncomeStatement('"+encodeURIComponent(data[i].category)+"','"+encodeURIComponent(data[i].dueAmount)+"')> </i></td></tr>";
				j++;
			}


		}
		else{
			if(data[i].type!=tempHead){
				rows=rows+"<tr style='background:#800000;color:#FFFFFF;font-weight:bold;font-size:18px;'><td></td><td>Sub Total</td> <td class='text-right'>"+parseFloat(totalSalesAmt).toFixed(2)+"</td> <td class='text-right'>"+parseFloat(totalDiscountAmt).toFixed(2)+"</td> <td class='text-right'>"+parseFloat(totalCollectionAmt).toFixed(2)+"</td> <td class='text-right'>"+parseFloat(totalDueAmt).toFixed(2)+"</td> </tr>";
				j=0;
			}


			totalExSalesAmt=totalExSalesAmt+parseFloat(data[i].salesAmount);
			totalExDiscountAmt=totalExDiscountAmt+parseFloat(data[i].discountAmount);
			totalExCollectionAmt=totalExCollectionAmt+parseFloat(data[i].collectionAmount);
			totalExDueAmt=totalExDueAmt+parseFloat(data[i].dueAmount);

			if(j==0){

				rows=rows+"<tr style='background:yellow;font-weight:bold;font-size:18px;'>";
				tempHead=data[i].type;
				rows=rows+"<td></td><td>"+tempHead+"</td> <td></td> <td></td> <td></td> <td></td> <td></td></tr>";
				rows=rows+"<tr><td>"+(i+1)+"</td> <td>"+data[i].category+"</td> <td class='text-right'>"+parseFloat(data[i].salesAmount).toFixed(2)+"</td> <td class='text-right'>"+parseFloat(data[i].discountAmount).toFixed(2)+"</td> <td class='text-right'>"+parseFloat(data[i].collectionAmount).toFixed(2)+"</td> <td class='text-right'>"+parseFloat(data[i].dueAmount).toFixed(2)+"</td> <td ><i class='fa fa-search' data-toggle='modal' data-target='#expenseDetailsList' onclick=DetailsExpenseStatement('"+encodeURIComponent(data[i].category)+"')> </i></td></tr>";
				j++;
			}
			else{
				rows=rows+"<tr><td>"+(i+1)+"</td> <td>"+data[i].category+"</td> <td class='text-right'>"+parseFloat(data[i].salesAmount).toFixed(2)+"</td> <td class='text-right'>"+parseFloat(data[i].discountAmount).toFixed(2)+"</td> <td class='text-right'>"+parseFloat(data[i].collectionAmount).toFixed(2)+"</td> <td class='text-right'>"+parseFloat(data[i].dueAmount).toFixed(2)+"</td> <td ><i class='fa fa-search' > </i></td></tr>";
				j++;
			}
		}

	}

	rows=rows+"<tr style='background:#800000;color:#FFFFFF;font-weight:bold;font-size:18px;'><td></td><td>Sub Total</td> <td class='text-right'>"+parseFloat(totalExSalesAmt).toFixed(2)+"</td> <td class='text-right'>"+parseFloat(totalExDiscountAmt).toFixed(2)+"</td> <td class='text-right'>"+parseFloat(totalExCollectionAmt).toFixed(2)+"</td> <td class='text-right'>"+parseFloat(totalExDueAmt).toFixed(2)+"</td> </tr>";
	//rows=rows+"<tr style='background:#F5F5F5;color:#000000;font-weight:bold;font-size:18px;'><td></td><td>Total</td> <td class='text-right'>"+parseFloat(totalExSalesAmt).toFixed(2)+"</td> <td class='text-right'>"+parseFloat(totalExDiscountAmt).toFixed(2)+"</td> <td class='text-right'>"+parseFloat(totalExCollectionAmt).toFixed(2)+"</td> <td class='text-right'>"+parseFloat(totalExDueAmt).toFixed(2)+"</td> </tr>";
	$("#statement_details_list").append(rows);


}

function DetailsExpenseStatement(category){
	var headName = decodeURIComponent(category);
	//alert("headName "+headName);


	var fromDate = $("#fromDate").val();
	var toDate = $("#toDate").val();

	if(fromDate!='' && toDate!=''){
		$.ajax({

			type:'POST',
			dataType:'json',
			url:'./statementExpenseDetails',
			data:{
				fromDate:fromDate,
				toDate:toDate,
				headName:headName
			},
			success:function(data)
			{
				$('#stExpenseDetailsList').empty();
				patchDailyExpenseStatementDetailsData(data.result_expense);
				
			}
		});

	}
	else{
		alert("Provide both date and Ledger");
	}
}


function patchDailyExpenseStatementDetailsData(data){
	var amount=0;
	var rows;
	for (var i = 0; i < data.length; i++) {
		amount=amount+parseFloat(data[i].amount);
		rows=rows+"<tr>" +
		"<td style='width:80px'>"+(i+1)+"</td> " +
		"<td style='width:280px'>"+data[i].ledgerTitle+"</td> " +
		"<td style='width:120px' class='text-right'>"+parseFloat(data[i].amount).toFixed(2)+"</td> " +
		"<td style='width:120px' ><i class='fa fa-search'  onclick=DetailsLedger('"+encodeURIComponent(data[i].ledgerId)+"','"+encodeURIComponent(data[i].depId)+"','"+encodeURIComponent(data[i].transactionType)+"')> </i></td></tr>";
	
	}
	
	rows=rows+"<tr style='background:yellow;color:#000000'>" +
	"<td style='width:80px'></td> " +
	"<td style='width:280px'>Total</td> " +
	"<td style='width:120px' class='text-right'>"+parseFloat(amount).toFixed(2)+"</td> " +
	"<td style='width:120px' ></td></tr>";

	
	$("#stExpenseDetailsList").append(rows);

}

function DetailsIncomeStatement(category,dueamount){
	var headName = decodeURIComponent(category);
	var dueAmount = decodeURIComponent(dueamount);
	//alert("headName "+headName);

	$("#groupSyn").empty();
	var title="<a class='my-node' data-id='"+headName+"' >"+headName+" >> </a>";

	$("#groupSyn").append(title);

	var fromDate = $("#fromDate").val();
	var toDate = $("#toDate").val();

	if(fromDate!='' && toDate!=''){
		$.ajax({

			type:'POST',
			dataType:'json',
			url:'./statementIncomeDetails',
			data:{
				fromDate:fromDate,
				toDate:toDate,
				headName:headName
			},
			success:function(data)
			{
				$('#DueAmount').empty();
				$('#stIncomeDetailsList').empty();
				patchDailyIncomeStatementDetailsData(data.result_sale);

				$('#stDiscountDetailsList').empty();
				patchDailyDiscountStatementDetailsData(data.result_discount);

				$('#stCashDetailsList').empty();
				patchDailyCashStatementDetailsData(data.result_cash);
				
				if(data.result_sale.length>0 || data.result_discount.length>0 || data.result_cash.length>0){
					$('#DueAmount').html(parseFloat(dueAmount).toFixed(2));
				}
			}
		});

	}
	else{
		alert("Provide both date and Ledger");
	}
}

function patchDailyCashStatementDetailsData(data){
	var amount=0;
	var rows;
	for (var i = 0; i < data.length; i++) {
		amount=amount+parseFloat(data[i].amount);
		rows=rows+"<tr>" +
		"<td style='width:80px'>"+(i+1)+"</td> " +
		"<td style='width:280px'>"+data[i].ledgerTitle+"</td> " +
		"<td style='width:120px' class='text-right'>"+parseFloat(data[i].amount).toFixed(2)+"</td> " +
		"<td style='width:120px' ><i class='fa fa-search'  onclick=DetailsLedger('"+encodeURIComponent(data[i].ledgerId)+"','"+encodeURIComponent(data[i].depId)+"','"+encodeURIComponent(data[i].transactionType)+"')> </i></td></tr>";
	
	}
	
	rows=rows+"<tr style='background:yellow;color:#000000'>" +
	"<td style='width:80px'></td> " +
	"<td style='width:280px'>Total</td> " +
	"<td style='width:120px' class='text-right'>"+parseFloat(amount).toFixed(2)+"</td> " +
	"<td style='width:120px' ></td></tr>";

	
	$("#stCashDetailsList").append(rows);

}

function patchDailyDiscountStatementDetailsData(data){
	var amount=0;
	var rows;
	for (var i = 0; i < data.length; i++) {
		amount=amount+parseFloat(data[i].amount);
		rows=rows+"<tr>" +
		"<td style='width:80px'>"+(i+1)+"</td> " +
		"<td style='width:280px'>"+data[i].ledgerTitle+"</td> " +
		"<td style='width:120px' class='text-right'>"+parseFloat(data[i].amount).toFixed(2)+"</td> " +
		"<td style='width:120px' ><i class='fa fa-search'  onclick=DetailsLedger('"+encodeURIComponent(data[i].ledgerId)+"','"+encodeURIComponent(data[i].depId)+"','"+encodeURIComponent(data[i].transactionType)+"')> </i></td></tr>";
	}
	
	rows=rows+"<tr style='background:yellow;color:#000000'>" +
	"<td style='width:80px'></td> " +
	"<td style='width:280px'>Total</td> " +
	"<td style='width:120px' class='text-right'>"+parseFloat(amount).toFixed(2)+"</td> " +
	"<td style='width:120px' ></td></tr>";
	
	$("#stDiscountDetailsList").append(rows);

}

function patchDailyIncomeStatementDetailsData(data){
	var amount=0;
	var rows;
	for (var i = 0; i < data.length; i++) {
		amount=amount+parseFloat(data[i].amount);
		rows=rows+"<tr>" +
		"<td style='width:80px'>"+(i+1)+"</td> " +
		"<td style='width:280px'>"+data[i].ledgerTitle+"</td> " +
		"<td style='width:120px' class='text-right'>"+parseFloat(data[i].amount).toFixed(2)+"</td> " +
		"<td style='width:120px' ><i class='fa fa-search'  onclick=DetailsLedger('"+encodeURIComponent(data[i].ledgerId)+"','"+encodeURIComponent(data[i].depId)+"','"+encodeURIComponent(data[i].transactionType)+"')> </i></td></tr>";
	}
	
	rows=rows+"<tr style='background:yellow;color:#000000'>" +
	"<td style='width:80px'></td> " +
	"<td style='width:280px'>Total</td> " +
	"<td style='width:120px' class='text-right'>"+parseFloat(amount).toFixed(2)+"</td> " +
	"<td style='width:120px' ></td></tr>";
	
	$("#stIncomeDetailsList").append(rows);

}


function DetailsLedger(ledgerId,depId,transactionType){
	var LedgerId = decodeURIComponent(ledgerId);
	var DepId = decodeURIComponent(depId);
	var TransactionType = decodeURIComponent(transactionType);
	var fromDate = $("#fromDate").val();
	var toDate = $("#toDate").val();
	
	console.log("LedgerId "+LedgerId);
	console.log("DepId "+DepId);
	console.log("TransactionType "+TransactionType);
	console.log("fromDate "+fromDate);
	console.log("toDate "+toDate);

	
	if(fromDate!='' && toDate!='' && LedgerId!=''){
		var url = `PreviewLedgerReportByLedgerid/${DepId}@${TransactionType}@${fromDate}@${toDate}@${LedgerId}`;
		window.open(url, '_blank');
		
	}
	else{
		alert("Provide both date and Ledger");
	}
	
	
}

function addRow(){

	$('#payment_voucher').append("<tr class='itemrow' data-id='"+i+"'>" +
			"<td>"+i+"</td>" +
			"<td><select id='ledger-"+i+"'  class='selectpicker ledger-"+i+" employee-width tableSelect  col-md-12 px-0' data-live-search='true'  data-style='btn-light btn-sm border-light-gray' onchange='EditAmount("+i+")' >" + ledgerlist + "</select></td>" +
			"<td style='width:200px;'><input id='accDescription-"+i+"' style='width:200px;' type='text'  class='form-control-sm'  value=''/></td>" +
			"<td style='width:170px;'><input readonly id='accAmount-"+i+"' style='width:170px;' type='number'  onkeyup='setTotalQty()' class='form-control-sm accAmount-"+i+"'  value=''/></td></tr>");
	i++;

	$('.tableSelect').selectpicker('refresh');
}

function EditAmount(number){
	var ledgerValue=$('#ledger-'+number).val();

	console.log("ledgerValue "+ledgerValue);
	if(ledgerValue!='0'){
		$('.accAmount-'+number).prop('readonly', false);

	}
	else{
		$('.accAmount-'+number).prop('readonly', true);
		$('#accAmount-'+number).val(0);
	}

	setTotalQty();
}

function setTotalQty(){
	value=0;
	$('.itemrow').each(function () {
		var id = $(this).attr("data-id");
		var accAmount=parseFloat($('.accAmount-'+id).val()==''?"0":$('.accAmount-'+id).val());

		value=value+accAmount;

	});

	$('#amount').val(value);

}

function getOptions(dataList) {
	let options = "";
	var length = dataList.length;

	options += "<option value='0'>Select Account</option>"
		for (var i = 0; i < length; i++) {
			var item = dataList[i];
			console.log("ledgerlist "+item.ledgerName);
			options += "<option  value='" + item.ledgerId + "'>" + item.ledgerName + "</option>"
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

			for(var a=0;a<6;a++){
				$('#payment_voucher').append("<tr class='itemrow' data-id='"+i+"'>" +
						"<td>"+i+"</td>" +
						"<td><select id='ledger-"+i+"'  class='selectpicker ledger-"+i+" employee-width tableSelect  col-md-12 px-0' data-live-search='true'  data-style='btn-light btn-sm border-light-gray' onchange='EditAmount("+i+")' >" + ledgerlist + "</select></td>" +
						"<td style='width:200px;'><input id='accDescription-"+i+"' style='width:200px;' type='text'  class='form-control-sm'  value=''/></td>" +
						"<td style='width:170px;'><input readonly id='accAmount-"+i+"' style='width:170px;' type='number'  onkeyup='setTotalQty()' class='form-control-sm accAmount-"+i+"'  value=''/></td></tr>");
				i++;

				$('.tableSelect').selectpicker('refresh');
			}

			//getOptions(data.ledgerresult);
		}
	});




}

window.onload = loadLedgerList();

function saveAction(){
	var userId=$("#userId").val();
	var voucherNo=$("#voucherNo").val();
	var accountType = $("#accountType").val();
	var voucherType = $("#voucherType").val();
	var ledgerId =0;

	var paymentType = $("#type").val();

	if(voucherType=='Payment' && accountType=='1'){
		paymentType="1";
	}
	else if(voucherType=='Receipt' && accountType=='1'){
		paymentType="3";
	}
	else if(voucherType=='Payment' && accountType=='2'){
		paymentType="2";
	}
	else if(voucherType=='Receipt' && accountType=='2'){
		paymentType="4";
	}

	if(accountType=='1' || accountType=='3'){
		ledgerId=$("#cashLedgerId").val();

	}
	else{
		ledgerId=$("#bankLedgerId").val();
	}

	var date = $("#date").val();
	var amount = $("#amount").val();
	var chequeNumber = $("#chequeNumber").val();
	var chequeDate = $("#chequeDate").val();
	var costCenterId = $("#costCenterId").val();
	var standBy = $("#standBy").is(':checked') ? '1' : '0';


	console.log("costCenterId "+costCenterId);
	if(voucherNo!=''){
		if(ledgerId!='0'){
			if(date!=''){
				if(amount!=''){
					if(costCenterId!='0'){
						var i = 0;
						var value = 0;
						var resultList = [];
						$('.itemrow').each(function () {

							var id = $(this).attr("data-id");

							var debitLedgerId=$('#ledger-'+id).val();
							var accAmount=$('#accAmount-'+id).val()==''?"0":$('#accAmount-'+id).val();
							var description=$('#accDescription-'+id).val()==''?".":$('#accDescription-'+id).val();

							console.log("debitLedgerId "+debitLedgerId);
							console.log("accAmount "+accAmount);
							console.log("description "+description);
							resultList[i] = debitLedgerId + "*" + accAmount + "*" + description;
							i++;
						});

						resultList = "[" + resultList + "]"

						if (confirm("Are you sure to Submit?")) {
							$.ajax({
								type: 'POST',
								dataType: 'json',
								data: {
									voucherNo: voucherNo,
									ledgerId: ledgerId,
									voucherType:voucherType,
									date: date,
									costCenterId: costCenterId,
									paymentType:paymentType,
									userId:userId,
									resultList:resultList,
									standBy:standBy,
									accountType:accountType,
									chequeNumber:chequeNumber,
									chequeDate:chequeDate
								},
								url: './saveVoucher/',
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
						alert("Provide Cost Center");
					}
				}
				else{
					alert("Provide Amount");
				}
			}
			else{
				alert("Provide Date");
			}
		}
		else{
			alert("Provide Cash Ledger or Bank Ledger");
		}
	}
	else{
		alert("Provide Voucher No");
	}

}
function refreshAction() {
	location.reload();
}
function SetAccountType(){

	var voucherType = $("#voucherType").val();
	var accountType=$("#accountType").val();

	console.log("voucherType "+voucherType);
	console.log("accountType "+accountType);

	var paymentType="0";

	if(voucherType=='Payment' && accountType=='1'){
		paymentType="1";
	}
	else if(voucherType=='Receipt' && accountType=='1'){
		paymentType="3";
	}
	else if(voucherType=='Payment' && accountType=='2'){
		paymentType="2";
	}
	else if(voucherType=='Receipt' && accountType=='2'){
		paymentType="4";
	}

	console.log("paymentType "+paymentType);

	if(accountType=='1'){
		$('#chequeNumberDiv').hide();
		$('#chequeDateDiv').hide();
		$('#bankListDiv').hide();
		$('#cashListDiv').show();
	}
	else{
		$('#chequeNumberDiv').show();
		$('#chequeDateDiv').show();
		$('#bankListDiv').show();
		$('#cashListDiv').hide();
	}

	$.ajax({
		type: 'POST',
		dataType: 'json',
		data: {
			paymentType: paymentType
		},
		url: './getMaxVoucher/',
		success: function (data) {
			$("#voucherNo").val(data);

		}
	});


}

function PaymentVoucherList(){
	var voucherNoSearch = $("#voucherNoSearch").val();
	var fromDate = $("#fromDate").val();
	var toDate = $("#toDate").val();
	var paymentType = $("#paymentType").val();
	var voucherType = $("#voucherType").val();

	if(voucherType=='Payment' && paymentType=='1'){
		paymentType="1";
	}
	else if(voucherType=='Receipt' && paymentType=='1'){
		paymentType="3";
	}
	else if(voucherType=='Payment' && paymentType=='2'){
		paymentType="2";
	}
	else if(voucherType=='Receipt' && paymentType=='2'){
		paymentType="4";
	}
	else if(voucherType=='Contra'){
		paymentType="6";
	}

	if(fromDate!='' && toDate!=''){
		$.ajax({
			type: 'POST',
			dataType: 'json',
			data: {
				voucherNoSearch: voucherNoSearch,
				fromDate: fromDate,
				toDate: toDate,
				paymentType: paymentType
			},
			url: './getPaymentVoucherList/',
			success: function (data) {
				$("#payment_voucher_list").empty();
				patchdata(data.paymentVoucherList);
			}
		});
	}
	else{
		alert("Provide Both Date");
	}

}

function patchdata(data){
	var rows = [];

	for (var i = 0; i < data.length; i++) {
		rows.push(drawRow(data[i],i+1));

	}

	$("#payment_voucher_list").append(rows);
}

function drawRow(rowData,c) {

	var row = $("<tr />")
	row.append($("<td>" + c + "</td>"));
	row.append($("<td >" + rowData.voucherNo+ "</td>"));
	row.append($("<td >" + rowData.paymentType+ "</td>"));
	row.append($("<td >" + rowData.date+ "</td>"));
	row.append($("<td >" + parseFloat(rowData.amount).toFixed(2)+ "</td>"));
	row.append($("<td >" + rowData.status+ "</td>"));
	row.append($("<td ><i class='fa fa-edit' onclick=setData()> </i></td>"));
	row.append($("<td ><i class='fa fa-print' onclick=VoucherPrint("+rowData.voucherNo+","+rowData.accountType+")> </i></td>"));

	return row;
}


function VoucherPrint(voucherNo,paymentType){

	console.log("voucherNo "+voucherNo);
	console.log("paymentType "+paymentType);
	var url = `JournalVoucherPrint/${voucherNo}@${paymentType}`;
	window.open(url, '_blank');

}

function PreviewBooReport(){

	var fromDate = $("#fromDate").val();
	var toDate = $("#toDate").val();
	var bookType = $("#bookType").val();

	console.log("bookType "+bookType);

	if(fromDate!='' && toDate!=''){
		var url = `PreviewBooReport/${fromDate}@${toDate}@${bookType}`;
		window.open(url, '_blank');

	}
	else{
		alert("Provide both date");
	}

}

function viewCashBankBooReport(){
	var fromDate = $("#fromDate").val();
	var toDate = $("#toDate").val();
	var voucherType = $("#bookType").val();

	$.ajax({
		type: 'POST',
		dataType: 'json',
		data: {
			fromDate: fromDate,
			toDate: toDate,
			voucherType: voucherType,
		},
		url: './viewCashBankBooReport/',
		success: function (data) {
			$("#cash_bank_book_report_list").empty();
			patchCashBankBookdata(data.result);
		}
	});
}

function patchCashBankBookdata(data){
	var rows = [];


	for (var i = 0; i < data.length; i++) {
		rows.push(drawCashBankBookDataRow(data[i],i+1));
	}

	$("#cash_bank_book_report_list").append(rows);

}

function drawCashBankBookDataRow(rowData,c) {

	var row = $("<tr />")
	row.append($("<td>" + c + "</td>"));
	row.append($("<td >" + rowData.voucherNo+ "</td>"));
	if(rowData.transactionType=='Debit'){
		row.append($("<td >" + rowData.creditLedger+ "</td>"));
	}
	else{
		row.append($("<td >" + rowData.debitLegder+ "</td>"));
	}
	row.append($("<td >" + parseFloat(rowData.amount).toFixed(2)+ "</td>"));
	row.append($("<td >" + rowData.transactionType+ "</td>"));
	row.append($("<td >" + rowData.date+ "</td>"));

	return row;
}

function PreviewAllBookReport(){

	var fromDate = $("#fromDate").val();
	var toDate = $("#toDate").val();
	var voucherType = $("#voucherType").val();

	console.log("voucherType "+voucherType);

	if(fromDate!='' && toDate!=''){
		var url = `PreviewAllBooReport/${fromDate}@${toDate}@${voucherType}`;
		window.open(url, '_blank');

	}
	else{
		alert("Provide both date");
	}


}

function ViewAllBookReport(){
	var fromDate = $("#fromDate").val();
	var toDate = $("#toDate").val();
	var voucherType = $("#voucherType").val();
	$.ajax({
		type: 'POST',
		dataType: 'json',
		data: {
			fromDate: fromDate,
			toDate: toDate,
			voucherType: voucherType,
		},
		url: './viewAllBookReport/',
		success: function (data) {
			$("#daybook_list").empty();
			patchBookdata(data.result);
		}
	});
}

function patchBookdata(data){
	var rows = [];


	for (var i = 0; i < data.length; i++) {
		rows.push(drawBookDataRow(data[i],i+1));
	}

	$("#daybook_list").append(rows);

}

function drawBookDataRow(rowData,c) {

	var row = $("<tr />")
	row.append($("<td>" + c + "</td>"));
	row.append($("<td >" + rowData.voucherType+ "</td>"));
	row.append($("<td >" + rowData.voucherNo+ "</td>"));
	row.append($("<td >" + rowData.debitLegder+ "</td>"));
	row.append($("<td >" + rowData.creditLedger+ "</td>"));
	row.append($("<td >" + parseFloat(rowData.amount).toFixed(2)+ "</td>"));


	return row;
}



function TransactionAmount(){
	var transferFrom = $("#transferFrom").val();
	var transactionOnDate = $("#transactionOnDate").val();

	$.ajax({
		type: 'POST',
		dataType: 'json',
		data: {
			transactionOnDate: transactionOnDate,
			transferFrom: transferFrom,
		},
		url: './getTransactionAmount/',
		success: function (data) {
			$('#amount').val(parseFloat(data).toFixed(2));
			$('#transactionAmount').val(parseFloat(data).toFixed(2));
		}
	});
}

function TransactionTransfer(){
	var transactionOnDate = $("#transactionOnDate").val();
	var transferFrom = $("#transferFrom").val();
	var transferTo = $("#transferTo").val();
	var amount = parseFloat($("#amount").val()==''?"0":$("#amount").val());
	var transactionAmount = parseFloat($("#transactionAmount").val()==''?"0":$("#transactionAmount").val());
	var userId = $("#userId").val();

	if(transactionOnDate!=null){
		if(transferFrom!=0){
			if(transferTo!=0){

				if(transferFrom==transferTo){
					alert("Same Ledger Transaction Can't Be Possible");
				}
				else{
					if(amount<=transactionAmount){
						$.ajax({
							type: 'POST',
							dataType: 'json',
							data: {
								transactionOnDate: transactionOnDate,
								transferFrom: transferFrom,
								transferTo: transferTo,
								userId:userId,
								amount:amount
							},
							url: './TransferTransaction/',
							success: function (data) {
								alert(data);
							}
						});
					}
					else{
						alert("Provide Less or equal amount for transaction of from ledger");
					}


				}


			}
			else{
				alert("Provide Ledger For Transfer To");
			}
		}
		else{
			alert("Provide Ledger For Transfer From");
		}
	}
	else{
		alert("Provide Transaction Date");
	}

}

var today = new Date();



document.getElementById("fromDate").value = today.getFullYear() + '-' + ('0' + (today.getMonth() + 1)).slice(-2) + '-' + ('0' + today.getDate()).slice(-2);
document.getElementById("toDate").value = today.getFullYear() + '-' + ('0' + (today.getMonth() + 1)).slice(-2) + '-' + ('0' + today.getDate()).slice(-2);

document.getElementById("date").value = today.getFullYear() + '-' + ('0' + (today.getMonth() + 1)).slice(-2) + '-' + ('0' + today.getDate()).slice(-2);

