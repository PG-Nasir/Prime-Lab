

function PaymentVoucherList(){
	var voucherNoSearch = $("#voucherNoSearch").val();
	var fromDate = $("#fromDate").val();
	var toDate = $("#toDate").val();
	var voucherType = $("#voucherType").val();
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
			  patchdata(data.paymentVoucherList);
		}
	});
}

function patchdata(data){
	var rows = [];
	
	for (var i = 0; i < data.length; i++) {
		rows.push(drawRow(data[i],i+1));
		
	}

	$("#payment_voucher_list").append(rows);
}

function drawRow(rowData,c) {
	
	var row = $("<tr class='itemrow' data-id='"+c+"' />")
	row.append($("<td>" + c + "</td>"));
	row.append($("<td >" + rowData.voucherNo+ "</td>"));
	row.append($("<td >" + rowData.paymentType+ "</td>"));
	row.append($("<td >" + rowData.date+ "</td>"));
	row.append($("<td ><input type='checkbox' class='approve' id='approve-"+c+"'  /><input type='hidden' id='voucherType-"+c+"' value='"+rowData.accountType+"'  /><input type='hidden' id='voucherNo-"+c+"' value='"+rowData.voucherNo+"'  /></td>"));
	
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

function approveVoucher(){
	
	var userId=$('#userId').val();
	var i = 0;
	var value = 0;
	var resultList = [];
	$('.itemrow').each(function () {

		var id = $(this).attr("data-id");
		
		console.log("id"+id);
		
		var approve=$("#approve-"+id).is(':checked') ? '1' : '0';
		var vouchertype=$('#voucherType-'+id).val();
		var voucherno=$('#voucherNo-'+id).val();
		

		console.log("approve"+approve);
		console.log("vouchertype"+vouchertype);
		console.log("voucherno"+voucherno);
	
		resultList[i] = approve + "*" + vouchertype +"*"+voucherno;
		i++;
	});
	
	resultList = "[" + resultList + "]";
	
	if (confirm("Are you sure to Submit?")) {
		
		$.ajax({
			type: 'POST',
			dataType: 'json',
			data: {
				userId:userId,
				resultList:resultList
			},
			url: './ApproveVoucher/',
			success: function (data) {
			
				if(data=='Voucher Approve Successfully'){
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



var today = new Date();
document.getElementById("fromDate").value = today.getFullYear() + '-' + ('0' + (today.getMonth() + 1)).slice(-2) + '-' + ('0' + today.getDate()).slice(-2);
document.getElementById("toDate").value = today.getFullYear() + '-' + ('0' + (today.getMonth() + 1)).slice(-2) + '-' + ('0' + today.getDate()).slice(-2);


document.getElementById("allcheck").addEventListener("click", function () {
    if ($(this).prop('checked')) {
        $(".approve").prop('checked', true);
    } else {
        $(".approve").prop('checked', false);
    }

});

