
var clickValue=0;
var tempType='';

function viewBalanceReport(){
	var fromDate = $("#fromDate").val();
	var toDate = $("#toDate").val();
	var transactionType = $("#transactionType").val();
	
	
	$("#groupSyn").empty();
	var title="<a class='my-node' data-id='"+clickValue+"' onclick=viewBalanceReport()>Balance Sheet >> </a>";
	
	$("#groupSyn").append(title);
	
	if(fromDate!='' && toDate!=''){
		$.ajax({

			type:'POST',
			dataType:'json',
			url:'./viewBalanceReport',
			data:{
				fromDate:fromDate,
				toDate:toDate,
				transactionType:transactionType
			},
			success:function(data)
			{
				$('#balance_list').empty();
				patchBalanceSheetData(data.result);
			}
		});
		
	}
	else{
		alert("Provide both date");
	}
}

function patchBalanceSheetData(data){
	var rows;
	var tempHead;
	var j=0;
	var totalAssetAmt=0;
	var totalLiabilityAmt=0;
	var profitLoss=0;
	
	for (var i = 0; i < data.length; i++) {
		
		if(data[i].headType=='Revenue' || data[i].headType=='Liability'){
			totalLiabilityAmt=totalLiabilityAmt+parseFloat(data[i].plAmount);
		}
		else{
			totalAssetAmt=totalAssetAmt+parseFloat(data[i].plAmount);
		}
		
		if(data[i].headType=='Asset'){
			
			
			if(j==0){

				rows=rows+"<tr style='background:yellow;font-weight:bold;font-size:18px;'>";
				tempHead=data[i].headType;
				rows=rows+"<td></td><td>"+tempHead+"</td> <td></td> <td></td></tr>";
				rows=rows+"<tr><td>"+(i+1)+"</td> <td>"+data[i].headTitle+"</td>  <td class='text-right'>"+parseFloat(data[i].plAmount).toFixed(2)+"</td><td ><i class='fa fa-search' data-toggle='modal' data-target='#balList' onclick=DetailsBaL('"+encodeURIComponent(data[i].headTitle)+"','"+encodeURIComponent(data[i].headType)+"')> </i></td></tr>";
				j++;
			}
			else{
				rows=rows+"<tr><td>"+(i+1)+"</td> <td>"+data[i].headTitle+"</td> <td class='text-right'>"+parseFloat(data[i].plAmount).toFixed(2)+"</td><td ><i class='fa fa-search' data-toggle='modal' data-target='#balList' onclick=DetailsBaL('"+encodeURIComponent(data[i].headTitle)+"','"+encodeURIComponent(data[i].headType)+"')> </i></td></tr>";
				j++;
			}
		}
		else{
			if(data[i].headType!=tempHead){
				rows=rows+"<tr style='background:yellow;font-weight:bold;font-size:18px;'><td></td><td></td> <td class='text-right'>"+parseFloat(totalAssetAmt).toFixed(2)+"</td> <td></td></tr>";
				j=0;
			}
			
			if(j==0){

				rows=rows+"<tr style='background:yellow;font-weight:bold;font-size:18px;'>";
				tempHead=data[i].headType;
				rows=rows+"<td></td><td>"+tempHead+"</td> <td></td> <td></td></tr>";
				rows=rows+"<tr><td>"+(i+1)+"</td> <td>"+data[i].headTitle+"</td>  <td class='text-right'>"+parseFloat(data[i].plAmount).toFixed(2)+"</td><td ><i class='fa fa-search' data-toggle='modal' data-target='#balList' onclick=DetailsBaL('"+encodeURIComponent(data[i].headTitle)+"','"+encodeURIComponent(data[i].headType)+"')> </i></td></tr>";
				j++;
			}
			else{
				rows=rows+"<tr><td>"+(i+1)+"</td> <td>"+data[i].headTitle+"</td> <td class='text-right'>"+parseFloat(data[i].plAmount).toFixed(2)+"</td><td ><i class='fa fa-search' data-toggle='modal' data-target='#balList' onclick=DetailsBaL('"+encodeURIComponent(data[i].headTitle)+"','"+encodeURIComponent(data[i].headType)+"')> </i></td></tr>";
				j++;
			}
		}
	}
	
	rows=rows+"<tr style='background:yellow;font-weight:bold;font-size:18px;'><td></td><td></td> <td class='text-right'>"+parseFloat(totalLiabilityAmt).toFixed(2)+"</td> <td></td></tr>";
	
	$("#balance_list").append(rows);
	
	
/*	for (var i = 0; i < data.length; i++) {
		
		if(data[i].headType=='Revenue' || data[i].headType=='Liability'){
			totalLiabilityAmt=totalLiabilityAmt+parseFloat(data[i].plAmount);
		}
		else{
			totalAssetAmt=totalAssetAmt+parseFloat(data[i].plAmount);
		}
		
		if(j==0){
			rows=rows+"<tr style='background:yellow;font-weight:bold;font-size:18px;'>";
			tempHead=data[i].headType;
			rows=rows+"<td></td><td>"+tempHead+"</td> <td></td> <td></td></tr>";
			rows=rows+"<tr><td>"+(i+1)+"</td> <td>"+data[i].headTitle+"</td>  <td class='text-right'>"+parseFloat(data[i].plAmount).toFixed(2)+"</td><td ><i class='fa fa-search' data-toggle='modal' data-target='#balList' onclick=DetailsBaL('"+encodeURIComponent(data[i].headTitle)+"','"+encodeURIComponent(data[i].headType)+"')> </i></td></tr>";
			j++;
		}
		else{
			rows=rows+"<tr><td>"+(i+1)+"</td> <td>"+data[i].headTitle+"</td> <td class='text-right'>"+parseFloat(data[i].plAmount).toFixed(2)+"</td><td ><i class='fa fa-search' data-toggle='modal' data-target='#balList' onclick=DetailsBaL('"+encodeURIComponent(data[i].headTitle)+"','"+encodeURIComponent(data[i].headType)+"')> </i></td></tr>";
			j++;
		}
		
		if(data[i].headType!=tempHead){
			rows=rows+"<tr style='background:yellow;font-weight:bold;font-size:18px;'><td></td><td></td> <td class='text-right'>"+totalAssetAmt+"</td> <td></td></tr>";
			j=0;
		}
		
	}
	rows=rows+"<tr style='background:yellow;font-weight:bold;font-size:18px;'><td></td><td></td> <td class='text-right'>"+totalLiabilityAmt+"</td> <td></td></tr>";
	$("#balance_list").append(rows);*/
	
}

function DetailsBaL(headTitle,headType){
	
	
	var headName = decodeURIComponent(headTitle);
	var headType = decodeURIComponent(headType);

	var fromDate = $("#fromDate").val();
	
	$.ajax({

		type:'POST',
		dataType:'json',
		url:'./ViewHeadWiseBalanceSheet',
		data:{
			fromDate:fromDate,
			headName:headName,
			headType:headType
		},
		success:function(data)
		{
			//<i class='fa fa-search' onclick=DetailsTrial('"+encodeURIComponent(rowData.headTitle)+"')> </i>
			$('#balDetailsList').empty();
			$('#balLedgerDetailsList').empty();
			console.log("data size "+data.result.length);
			if(data.result.length>0){
				clickValue++;
				var title="<a class='my-node' data-id='"+clickValue+"' onclick=DetailsBaL('"+encodeURIComponent(headName)+"')>"+headName+" >> </a>";
				$("#groupSyn").append(title);
				patchBalDetailsdata(data.result);
			}
			else{
				//DetailsTrialLedger(encodeURIComponent(headName));
			}
			
			patchBalLedgerDetailsdata(data.ledger_result);
			
		}
	});

	

}

function patchBalDetailsdata(data){
	var rows = [];
	

	for (var i = 0; i < data.length; i++) {
		rows.push(drawBalDetailsRow(data[i],i+1));
	}

	$("#balDetailsList").append(rows);

}

function drawBalDetailsRow(rowData,c) {
	
	var row = $("<tr />")
	row.append($("<td>" + c + "</td>"));
	row.append($("<td >" + rowData.headTitle+ "</td>"));
	row.append($("<td class='text-right'>" + parseFloat(rowData.ob).toFixed(2)+ "</td>"));
	row.append($("<td class='text-right'>" + parseFloat(rowData.debit).toFixed(2)+ "</td>"));
	row.append($("<td class='text-right'>" + parseFloat(rowData.credit).toFixed(2)+ "</td>"));
	row.append($("<td class='text-right'>" + parseFloat(rowData.closing).toFixed(2)+ "</td>"));
	row.append($("<td ><i class='fa fa-search' data-toggle='modal' data-target='#palList' onclick=DetailsPAL('"+encodeURIComponent(rowData.headTitle)+"','"+encodeURIComponent(rowData.headType)+"')> </i></td>"));

	return row;
}


function patchBalLedgerDetailsdata(data){
	var rows = [];
	
	var tOb=0;
	var tDebit=0;
	var tCredit=0;
	var tClose=0;
	for (var i = 0; i < data.length; i++) {
		tOb=tOb+parseFloat(data[i].ob);
		tDebit=tDebit+parseFloat(data[i].debit);
		tCredit=tCredit+parseFloat(data[i].credit);
		tClose=tClose+parseFloat(data[i].closing);
		rows.push(drawBalLedgerDetailsRow(data[i],i+1));
	}

	$("#balLedgerDetailsList").append(rows);
	$("#tOb").val(parseFloat(tOb).toFixed(2));
	$("#tDebit").val(parseFloat(tDebit).toFixed(2));
	$("#tCredit").val(parseFloat(tCredit).toFixed(2));
	$("#tClose").val(parseFloat(tClose).toFixed(2));
	

}

function drawBalLedgerDetailsRow(rowData,c) {
	
	var row = $("<tr />")
	row.append($("<td style='width:40px;'>" + c + "</td>"));
	row.append($("<td style='width:350px;'>" + rowData.headTitle+ "</td>"));
	row.append($("<td class='text-right' style='width:120px;'>" + parseFloat(rowData.ob).toFixed(2)+ "</td>"));
	row.append($("<td class='text-right' style='width:120px;'>" + parseFloat(rowData.debit).toFixed(2)+ "</td>"));
	row.append($("<td class='text-right' style='width:120px;'>" + parseFloat(rowData.credit).toFixed(2)+ "</td>"));
	row.append($("<td class='text-right' style='width:120px;'>" + parseFloat(rowData.closing).toFixed(2)+ "</td>"));
	row.append($("<td ><i class='fa fa-search' data-toggle='modal' data-target='#palList' onclick=DetailsLedgerFromBal('"+encodeURIComponent(rowData.ledgerId)+"')> </i></td>"));

	return row;
}


function ViewProfitAndLossReport(){
	var fromDate = $("#fromDate").val();
	var toDate = $("#toDate").val();
	
	$("#groupSyn").empty();
	var title="<a class='my-node' data-id='"+clickValue+"' onclick=ViewProfitAndLossReport()>Profit And Loss >> </a>";
	
	$("#groupSyn").append(title);
	
	if(fromDate!='' && toDate!=''){
		$.ajax({

			type:'POST',
			dataType:'json',
			url:'./ViewProfitAndLoss',
			data:{
				fromDate:fromDate,
				toDate:toDate
			},
			success:function(data)
			{
				$('#profitandloss_list').empty();
				patchIncomeStatementDetailsData(data.result);
			}
		});
		
	}
	else{
		alert("Provide both date and Ledger");
	}
}


function patchIncomeStatementDetailsData(data){
	var rows;
	var tempHead;
	var j=0;
	var totalRevenueAmt=0;
	var totalExpenseAmt=0;
	var profitLoss=0;
	for (var i = 0; i < data.length; i++) {
		
		if(data[i].headType=='Revenue'){
			totalRevenueAmt=totalRevenueAmt+parseFloat(data[i].plAmount);
		}
		else{
			totalExpenseAmt=totalExpenseAmt+parseFloat(data[i].plAmount);
		}
		
		if(j==0){
			rows=rows+"<tr style='background:yellow;font-weight:bold;font-size:18px;'>";
			tempHead=data[i].headType;
			rows=rows+"<td></td><td>"+tempHead+"</td> <td></td> <td></td></tr>";
			rows=rows+"<tr><td>"+(i+1)+"</td> <td>"+data[i].headTitle+"</td> <td></td> <td class='text-right'>"+parseFloat(data[i].plAmount).toFixed(2)+"</td><td ><i class='fa fa-search' data-toggle='modal' data-target='#palList' onclick=DetailsPAL('"+encodeURIComponent(data[i].headTitle)+"','"+encodeURIComponent(data[i].headType)+"')> </i></td></tr>";
			j++;
		}
		else{
			rows=rows+"<tr><td>"+(i+1)+"</td> <td>"+data[i].headTitle+"</td> <td></td> <td class='text-right'>"+parseFloat(data[i].plAmount).toFixed(2)+"</td><td ><i class='fa fa-search' data-toggle='modal' data-target='#palList' onclick=DetailsPAL('"+encodeURIComponent(data[i].headTitle)+"','"+encodeURIComponent(data[i].headType)+"')> </i></td></tr>";
			j++;
		}
		
		if(data[i].headType!=tempHead){
			j=0;
		}
		
	}
	$("#profitandloss_list").append(rows);
	
	profitLoss=totalRevenueAmt-totalExpenseAmt;
	
	$("#netProfit").val(parseFloat(profitLoss).toFixed(2));
}


function patchProfitAndLossData(data){
	var rows;
	var tempHead;
	var j=0;
	var totalRevenueAmt=0;
	var totalExpenseAmt=0;
	var profitLoss=0;
	for (var i = 0; i < data.length; i++) {
		
		if(data[i].headType=='Revenue'){
			totalRevenueAmt=totalRevenueAmt+parseFloat(data[i].plAmount);
		}
		else{
			totalExpenseAmt=totalExpenseAmt+parseFloat(data[i].plAmount);
		}
		
		if(j==0){
			rows=rows+"<tr style='background:yellow;font-weight:bold;font-size:18px;'>";
			tempHead=data[i].headType;
			rows=rows+"<td></td><td>"+tempHead+"</td> <td></td> <td></td></tr>";
			rows=rows+"<tr><td>"+(i+1)+"</td> <td>"+data[i].headTitle+"</td> <td></td> <td class='text-right'>"+parseFloat(data[i].plAmount).toFixed(2)+"</td><td ><i class='fa fa-search' data-toggle='modal' data-target='#palList' onclick=DetailsPAL('"+encodeURIComponent(data[i].headTitle)+"','"+encodeURIComponent(data[i].headType)+"')> </i></td></tr>";
			j++;
		}
		else{
			rows=rows+"<tr><td>"+(i+1)+"</td> <td>"+data[i].headTitle+"</td> <td></td> <td class='text-right'>"+parseFloat(data[i].plAmount).toFixed(2)+"</td><td ><i class='fa fa-search' data-toggle='modal' data-target='#palList' onclick=DetailsPAL('"+encodeURIComponent(data[i].headTitle)+"','"+encodeURIComponent(data[i].headType)+"')> </i></td></tr>";
			j++;
		}
		
		if(data[i].headType!=tempHead){
			j=0;
		}
		
	}
	$("#profitandloss_list").append(rows);
	
	profitLoss=totalRevenueAmt-totalExpenseAmt;
	
	$("#netProfit").val(parseFloat(profitLoss).toFixed(2));
}


function DetailsPAL(headTitle,headType){
	
		
		var headName = decodeURIComponent(headTitle);
		var headType = decodeURIComponent(headType);

		var fromDate = $("#fromDate").val();
		var toDate = $("#toDate").val();
		
		$.ajax({

			type:'POST',
			dataType:'json',
			url:'./ViewHeadWiseProfitAndLoss',
			data:{
				fromDate:fromDate,
				toDate:toDate,
				headName:headName,
				headType:headType
			},
			success:function(data)
			{
				//<i class='fa fa-search' onclick=DetailsTrial('"+encodeURIComponent(rowData.headTitle)+"')> </i>
				$('#palDetailsList').empty();
				$('#palLedgerDetailsList').empty();
				console.log("data size "+data.result.length);
				if(data.result.length>0){
					clickValue++;
					var title="<a class='my-node' data-id='"+clickValue+"' onclick=DetailsPAL('"+encodeURIComponent(headName)+"')>"+headName+" >> </a>";
					$("#groupSyn").append(title);
					patchPalDetailsdata(data.result);
				}
				else{
					//DetailsTrialLedger(encodeURIComponent(headName));
				}
				
				patchPalLedgerDetailsdata(data.ledger_result);
				
			}
		});

		

	}



function patchPalLedgerDetailsdata(data){
	var rows = [];
	
	var tOb=0;
	var tDebit=0;
	var tCredit=0;
	var tClose=0;
	for (var i = 0; i < data.length; i++) {
		tOb=tOb+parseFloat(data[i].ob);
		tDebit=tDebit+parseFloat(data[i].debit);
		tCredit=tCredit+parseFloat(data[i].credit);
		tClose=tClose+parseFloat(data[i].closing);
		rows.push(drawPalLedgerDetailsRow(data[i],i+1));
	}

	$("#palLedgerDetailsList").append(rows);
	$("#tOb").val(parseFloat(tOb).toFixed(2));
	$("#tDebit").val(parseFloat(tDebit).toFixed(2));
	$("#tCredit").val(parseFloat(tCredit).toFixed(2));
	$("#tClose").val(parseFloat(tClose).toFixed(2));
	

}

function drawPalLedgerDetailsRow(rowData,c) {
	
	var row = $("<tr />")
	row.append($("<td style='width:40px;'>" + c + "</td>"));
	row.append($("<td style='width:350px;'>" + rowData.headTitle+ "</td>"));
	row.append($("<td class='text-right' style='width:120px;'>" + parseFloat(rowData.ob).toFixed(2)+ "</td>"));
	row.append($("<td class='text-right' style='width:120px;'>" + parseFloat(rowData.debit).toFixed(2)+ "</td>"));
	row.append($("<td class='text-right' style='width:120px;'>" + parseFloat(rowData.credit).toFixed(2)+ "</td>"));
	row.append($("<td class='text-right' style='width:120px;'>" + parseFloat(rowData.closing).toFixed(2)+ "</td>"));
	row.append($("<td ><i class='fa fa-search' data-toggle='modal' data-target='#palList' onclick=DetailsLedger('"+encodeURIComponent(rowData.ledgerId)+"')> </i></td>"));

	return row;
}


function patchPalDetailsdata(data){
	var rows = [];
	

	for (var i = 0; i < data.length; i++) {
		rows.push(drawPalDetailsRow(data[i],i+1));
	}

	$("#palDetailsList").append(rows);

}

function drawPalDetailsRow(rowData,c) {
	
	var row = $("<tr />")
	row.append($("<td>" + c + "</td>"));
	row.append($("<td >" + rowData.headTitle+ "</td>"));
	row.append($("<td class='text-right'>" + parseFloat(rowData.ob).toFixed(2)+ "</td>"));
	row.append($("<td class='text-right'>" + parseFloat(rowData.debit).toFixed(2)+ "</td>"));
	row.append($("<td class='text-right'>" + parseFloat(rowData.credit).toFixed(2)+ "</td>"));
	row.append($("<td class='text-right'>" + parseFloat(rowData.closing).toFixed(2)+ "</td>"));
	row.append($("<td ><i class='fa fa-search' data-toggle='modal' data-target='#palList' onclick=DetailsPAL('"+encodeURIComponent(rowData.headTitle)+"','"+encodeURIComponent(rowData.headType)+"')> </i></td>"));

	return row;
}


function PreviewCashFlowStatementReport(){
	
	var fromDate = $("#fromDate").val();
	var toDate = $("#toDate").val();

	
	if(fromDate!='' && toDate!=''){
		var url = `PreviewCashFlowReport/${fromDate}@${toDate}`;
		window.open(url, '_blank');
		
	}
	else{
		alert("Provide both date");
	}
	
}

function PreviewReport(){
	
	var fromDate = $("#fromDate").val();
	var toDate = $("#toDate").val();
	var reference = $("#reference").val();
	
	console.log("reference "+reference);
	
	if(fromDate!='' && toDate!='' && reference!=''){
		var url = `PreviewLedgerReport/${fromDate}@${toDate}@${reference}`;
		window.open(url, '_blank');
		
	}
	else{
		alert("Provide both date and Ledger");
	}
	
}

function PreviewPaymentReceiptReport(){
	var fromDate = $("#fromDate").val();
	var toDate = $("#toDate").val();

	
	if(fromDate!='' && toDate!=''){
		var url = `PreviewPaymentReceiptReport/${fromDate}@${toDate}`;
		window.open(url, '_blank');
		
	}
	else{
		alert("Provide both date and Ledger");
	}
}


function ViewTrialBalanceReport(){
	var fromDate = $("#fromDate").val();
	var toDate = $("#toDate").val();
	var transactionType = $("#transactionType").val();
	
	$("#groupSyn").empty();
	var title="<a class='my-node' data-id='"+clickValue+"' onclick=ViewTrialBalanceReport()>Trial Balance >> </a>";
	
	$("#groupSyn").append(title);
	
	if(fromDate!='' && toDate!=''){
		$.ajax({

			type:'POST',
			dataType:'json',
			url:'./ViewTrialBalance',
			data:{
				fromDate:fromDate,
				toDate:toDate,
				transactionType:transactionType
			},
			success:function(data)
			{
				$('#tiralbalance_list').empty();
				patchdata(data.result);
			}
		});
		
	}
	else{
		alert("Provide both date and Ledger");
	}
}


function patchdata(data){
	var rows = [];
	
	var beforeDebit=0;
	var beforeCredit=0;
	var transactionDebit=0;
	var transactionCredit=0;
	
	var currentDebit=0;
	var currentCredit=0;

	for (var i = 0; i < data.length; i++) {
		rows.push(drawRow(data[i],i+1));
		beforeDebit=beforeDebit+parseFloat(data[i].beforeDebit);
		beforeCredit=beforeCredit+parseFloat(data[i].beforeCredit);
		transactionDebit=transactionDebit+parseFloat(data[i].currentDebit);
		transactionCredit=transactionCredit+parseFloat(data[i].currentCredit);
		
		currentDebit=currentDebit+parseFloat(data[i].currentDebit);
		currentCredit=currentCredit+parseFloat(data[i].currentCredit);
	}
	
	$("#beforeDebit").val(beforeDebit);
	$("#beforeCredit").val(beforeCredit);
	
	$("#transactionDebit").val(transactionDebit);
	$("#transactionCredit").val(transactionCredit);
	$("#balanceDebit").val(currentDebit);
	$("#balanceCredit").val(currentCredit);
	
	$("#tiralbalance_list").append(rows);

}

function drawRow(rowData,c) {
	
	var row = $("<tr />")
	row.append($("<td>" + c + "</td>"));
	row.append($("<td >" + rowData.headTitle+ "</td>"));
	row.append($("<td class='text-right'>" + parseFloat(rowData.beforeDebit).toFixed(2)+ "</td>"));
	row.append($("<td class='text-right'>" + parseFloat(rowData.beforeCredit).toFixed(2)+ "</td>"));
	row.append($("<td class='text-right'>" + parseFloat(rowData.currentDebit).toFixed(2)+ "</td>"));
	row.append($("<td class='text-right'>" + parseFloat(rowData.currentCredit).toFixed(2)+ "</td>"));
	row.append($("<td class='text-right'>" + parseFloat(rowData.currentDebit).toFixed(2)+ "</td>"));
	row.append($("<td class='text-right'>" + parseFloat(rowData.currentCredit).toFixed(2)+ "</td>"));
	row.append($("<td ><i class='fa fa-search' onclick=DetailsTrial('"+encodeURIComponent(rowData.headTitle)+"')> </i></td>"));

	return row;
}




function DetailsTrial(headTitle){
	var transactionType = $("#transactionType").val();
	var headName = decodeURIComponent(headTitle);

	var fromDate = $("#fromDate").val();
	var toDate = $("#toDate").val();
	
	$.ajax({

		type:'POST',
		dataType:'json',
		url:'./ViewHeadWiseTrialBalance',
		data:{
			fromDate:fromDate,
			toDate:toDate,
			headName:headName,
			transactionType:transactionType
		},
		success:function(data)
		{
			//<i class='fa fa-search' onclick=DetailsTrial('"+encodeURIComponent(rowData.headTitle)+"')> </i>
			$('#tiralbalance_list').empty();
			if(data.result.length>0){
				clickValue++;
				var title="<a class='my-node' data-id='"+clickValue+"' onclick=DetailsTrial('"+encodeURIComponent(headName)+"')>"+headName+" >> </a>";
				$("#groupSyn").append(title);
				patchdata(data.result);
			}
			else{
				DetailsTrialLedger(encodeURIComponent(headName));
			}
			
		}
	});

	

}

function DetailsTrialLedger(headTitle){
	var headName = decodeURIComponent(headTitle);

	
	var fromDate = $("#fromDate").val();
	var toDate = $("#toDate").val();

	
	$.ajax({

		type:'POST',
		dataType:'json',
		url:'./ViewHeadAndLedgerWiseTrialBalance',
		data:{
			fromDate:fromDate,
			toDate:toDate,
			headName:headName
		},
		success:function(data)
		{
			$('#tiralbalance_list').empty();
			if(data.result.length>0){
				clickValue++;
				var title="<a class='my-node' data-id='"+clickValue+"' onclick=DetailsTrial('"+encodeURIComponent(headName)+"')>"+headName+" >> </a>";
				$("#groupSyn").append(title);
			}	
			patchdata(data.result);
			
		}
	});
}

function DetailsLedgerFromBal(ledgerId){
	var LedgerId = decodeURIComponent(ledgerId);

	var fromDate = $("#fromDate").val();
	
	if(fromDate!='' && LedgerId!=''){
		var url = `PreviewLedgerReportBal/${fromDate}@${LedgerId}`;
		window.open(url, '_blank');
		
	}
	else{
		alert("Provide both date and Ledger");
	}
	
	
}

function DetailsLedger(ledgerId){
	var LedgerId = decodeURIComponent(ledgerId);

	var fromDate = $("#fromDate").val();
	var toDate = $("#toDate").val();

	
	if(fromDate!='' && toDate!='' && LedgerId!=''){
		var url = `PreviewLedgerReportD/${fromDate}@${toDate}@${LedgerId}`;
		window.open(url, '_blank');
		
	}
	else{
		alert("Provide both date and Ledger");
	}
	
	
}

function PreviewTrialBalanceReport(){
	var fromDate = $("#fromDate").val();
	var toDate = $("#toDate").val();
	var transactionType = $("#transactionType").val();
	
	if(fromDate!='' && toDate!=''){
		var url = `PreviewTrialBalanceReport/${fromDate}@${toDate}@${transactionType}`;
		window.open(url, '_blank');
		
	}
	else{
		alert("Provide both date and Ledger");
	}
}

function PreviewPALReport(){
	var fromDate = $("#fromDate").val();
	var toDate = $("#toDate").val();

	if(fromDate!='' && toDate!=''){
		var url = `PreviewPALReport/${fromDate}@${toDate}`;
		window.open(url, '_blank');	
	}
	else{
		alert("Provide both date and Ledger");
	}
}



function PreviewBalanceReport(){
	var date = $("#fromDate").val();
	var transactionType = $("#transactionType").val();
	
	if(date!=''){
		var url = `PreviewBalanceReport/${date}@${transactionType}`;
		window.open(url, '_blank');	
	}
	else{
		alert("Provide date");
	}
}

var today = new Date();
document.getElementById("fromDate").value = today.getFullYear() + '-' + ('0' + (today.getMonth() + 1)).slice(-2) + '-' + ('0' + today.getDate()).slice(-2);
document.getElementById("toDate").value = today.getFullYear() + '-' + ('0' + (today.getMonth() + 1)).slice(-2) + '-' + ('0' + today.getDate()).slice(-2);

document.getElementById("date").value = today.getFullYear() + '-' + ('0' + (today.getMonth() + 1)).slice(-2) + '-' + ('0' + today.getDate()).slice(-2);

