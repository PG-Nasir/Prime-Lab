
function remarkListView(){
	var fromDate = $("#fromDate").val();
	var toDate = $("#toDate").val();
	if(fromDate!='' && toDate!=''){
		var url = `remarkListPreview/${fromDate}/${toDate}`;
		window.open(url, '_blank');
	}
	else{
		alert("Provide both date");
	}
}

function extraCommissionListView (){
	var fromDate = $("#fromDate").val();
	var toDate = $("#toDate").val();
	if(fromDate!='' && toDate!=''){
		var url = `extraCommissionListPreview/${fromDate}/${toDate}`;
		window.open(url, '_blank');
	}
	else{
		alert("Provide both date");
	}
}


function departmentWiseInvestigationStatementPreview(){
	var fromDate = $("#fromDate").val();
	var toDate = $("#toDate").val();
	var patientType = $("#patientType").val();	
	var headId = $("#headId").val();	
	
	if(fromDate!='' && toDate!=''){
		var url = `departmentWiseInvestigationStatementPreview/${fromDate}/${toDate}/${patientType}/${headId}`;
		window.open(url, '_blank');
	}
	else{
		alert("Provide both date");
	}
}

function testWiseInvestigationStatementPreview(){
	var fromDate = $("#fromDate").val();
	var toDate = $("#toDate").val();
	var testId = $("#testId").val();	
	
	if(fromDate!='' && toDate!=''){
		var url = `testWiseInvestigationStatementPreview/${fromDate}/${toDate}/${testId}`;
		window.open(url, '_blank');
	}
	else{
		alert("Provide both date");
	}
}

function departmentWiseSaleStatementPreview(){
	var fromDate = $("#fromDate").val();
	var toDate = $("#toDate").val();
	var patientType = $("#patientType").val();	
	
	if(fromDate!='' && toDate!=''){
		var url = `departmentWiseSaleStatementPreview/${fromDate}/${toDate}/${patientType}`;
		window.open(url, '_blank');
	}
	else{
		alert("Provide both date");
	}
}

function LabIdWiseReferralCommissionStatementPreview(){
	var fromDate = $("#fromDate").val();
	var toDate = $("#toDate").val();
	var doctorId = $("#doctorId").val();	
	
	if(fromDate!='' && toDate!=''){
		var url = `lab_id_wise_referral_comission_statement/${fromDate}/${toDate}/${doctorId}`;
		window.open(url, '_blank');
	}
	else{
		alert("Provide both date");
	}
	
}


function AllReferralCommissionStatementPreview(){
	var fromDate = $("#fromDate").val();
	var toDate = $("#toDate").val();
	var doctorId = $("#doctorId").val();	
	
	if(fromDate!='' && toDate!=''){
		var url = `allReferralCommissionStatement/${fromDate}/${toDate}/${doctorId}`;
		window.open(url, '_blank');
	}
	else{
		alert("Provide both date");
	}
	
}

function labSaleStatementPreview(){
	
	var fromDate = $("#fromDate").val();
	var toDate = $("#toDate").val();
	var patientType = $("#patientType").val();
	var reportType = $("#reportType").val();	
	
	if(fromDate!='' && toDate!=''){
		var url = `labSaleStatement/${fromDate}/${toDate}/${patientType}/${reportType}`;
		window.open(url, '_blank');
	}
	else{
		alert("Provide both date");
	}
	

}


function labSaleCashStatementPreview(){
	
	var fromDate = $("#fromDate").val();
	var toDate = $("#toDate").val();
	var patientType = $("#patientType").val();	
	
	
	if(fromDate!='' && toDate!=''){
		var url = `labSaleCashStatement/${fromDate}/${toDate}/${patientType}`;
		window.open(url, '_blank');
	}
	else{
		alert("Provide both date");
	}
	

}



function labSaleDueStatementPreview(){
	
	var fromDate = $("#fromDate").val();
	var toDate = $("#toDate").val();
	var patientType = $("#patientType").val();	
	
	if(fromDate!='' && toDate!=''){
		var url = `labSaleDueStatement/${fromDate}/${toDate}/${patientType}`;
		window.open(url, '_blank');
	}
	else{
		alert("Provide both date");
	}
	

}


var today = new Date();
document.getElementById("fromDate").value = today.getFullYear() + '-' + ('0' + (today.getMonth() + 1)).slice(-2) + '-' + ('0' + today.getDate()).slice(-2);
document.getElementById("toDate").value = today.getFullYear() + '-' + ('0' + (today.getMonth() + 1)).slice(-2) + '-' + ('0' + today.getDate()).slice(-2);

