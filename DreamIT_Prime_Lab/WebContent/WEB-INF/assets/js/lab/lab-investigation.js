var sameTest=0;
var tempDep=0;
var tempTest=0;
var Cmonth="";
var d = new Date();
var month = new Array();
month[0] = "January";
month[1] = "February";
month[2] = "March";
month[3] = "April";
month[4] = "May";
month[5] = "June";
month[6] = "July";
month[7] = "August";
month[8] = "September";
month[9] = "October";
month[10] = "November";
month[11] = "December";
var month = month[d.getMonth()];

$("#sMonth").val(month);

var year = d.getFullYear();
$("#sFiscalYear").val(year);

$( "#sLabId" ).keyup(function(e) {
	
	 if(e.key==='ArrowDown' || e.key==='ArrowUp') {
		console.log("labId "+labId);
		var labId=$("#sLabId").val();
		var fiscalyear=$("#sFiscalYear").val();
		var cmonth=$("#sMonth").val();
		setLabBillData(labId,fiscalyear,cmonth);
	 }
	 
});


function printEvent(){
	var department=$("#department").val();
	var inchargeId=$("#inchargeId").val();
	var doctor1=$("#doctor1").val();
	var doctor2=$("#doctor2").val();
	
	
	
	if(department=='1'){
		heamatologyPrintEvent();
	}
	if(department=='2'){
		biochemistryPrintEvent();
	}
	if(department=='3'){
		serologyPrintEvent();
	}
	if(department=='4'){
		hormonePrintEvent();
	}
	if(department=='5'){
		urinePrintEvent();
	}
	if(department=='6'){
		microPrintEvent();
	}
	if(department=='8'){
		stoolPrintEvent();
	}
	if(department=='9'){
		immunologyPrintEvent();
	}

}

function printTopEvent(){
	var labbill=$("#labbill").val();
	var fiscalyear=$("#labfiscalyear").val();
	var cmonth=$("#cmonth").val();
	var headid=$("#department").val();
	
	var inchargeId=$("#inchargeId").val();
	var doctor1=$("#doctor1").val();
	var doctor2=$("#doctor2").val();
	var machineId=$("#machineId").val();
	var titleId=$("#titleId").val();

	var testIdlist=[];
	var i=0;


	$(".checkdepartment").each(function(){

		if($(this).is(":checked")){
			var testId=$(this).val();
			
			testIdlist[i++]=[testId];
		}
		

	});
	
	testIdlist="("+testIdlist+")";
	
	if(labbill!=''){
		if(fiscalyear!=''){
			if(testid!=''){

				var url = `printTopReport/${labbill}@${fiscalyear}@${cmonth}@${headid}@${testIdlist}@${inchargeId}@${doctor1}@${doctor2}@${machineId}@${titleId}`;
				window.open(url, '_blank');
			}
			else{
				alert("Provide Fiscal Year");
			}
		}
		else{
			alert("Provide Fiscal Year");
		}
	}
	else{
		alert("Provide Lab Bill");
	}
}

function urinePrintEvent(){
	
	var labbill=$("#labbill").val();
	var fiscalyear=$("#labfiscalyear").val();
	var cMonth=$("#cmonth").val();
	var headid=$("#department").val();
	var testid=$("#testid").val();
	var inchargeId=$("#inchargeId").val();
	var doctor1=$("#doctor1").val();
	var doctor2=$("#doctor2").val();
	
	var machineId=$("#machineId").val();
	var titleId=$("#titleId").val();


	var url = `printUrineReport/${labbill}@${fiscalyear}@${cMonth}@${headid}@${testid}@${inchargeId}@${doctor1}@${doctor2}@${machineId}@${titleId}`;
	window.open(url, '_blank');
}

function microPrintEvent(){
	var labbill=$("#labbill").val();
	var fiscalyear=$("#labfiscalyear").val();
	var cMonth=$("#cmonth").val();
	var headid=$("#department").val();
	var testid=$("#testid").val();
	var inchargeId=$("#inchargeId").val();
	var doctor1=$("#doctor1").val();
	var doctor2=$("#doctor2").val();
	
	var micreport=$("#microreportcategory").val();
	
	var machineId=$("#machineId").val();
	var titleId=$("#titleId").val();

	var url = `printMicrobiologyReport/${labbill}@${fiscalyear}${cMonth}@${headid}@${testid}@${inchargeId}@${doctor1}@${doctor2}@${micreport}@${machineId}@${titleId}`;
	window.open(url, '_blank');
}

function stoolPrintEvent(){
	
	var labbill=$("#labbill").val();
	var fiscalyear=$("#labfiscalyear").val();
	var cMonth=$("#cmonth").val();
	var headid=$("#department").val();
	var testid=$("#testid").val();

	

	var inchargeId=$("#inchargeId").val();
	var doctor1=$("#doctor1").val();
	var doctor2=$("#doctor2").val();
	var machineId=$("#machineId").val();
	var titleId=$("#titleId").val();

	var url = `printStoolReport/${labbill}@${fiscalyear}@${cMonth}@${headid}@${testid}@${inchargeId}@${doctor1}@${doctor2}@${machineId}@${titleId}`;
	window.open(url, '_blank');
}



function heamatologyPrintEvent(){
	
	var labbill=$("#labbill").val();
	var fiscalyear=$("#labfiscalyear").val();
	var cMonth=$("#cmonth").val();
	var headid=$("#department").val();
	var testid=$("#testid").val();
	var inchargeId=$("#inchargeId").val();
	var doctor1=$("#doctor1").val();
	var doctor2=$("#doctor2").val();
	var machineId=$("#machineId").val();
	var titleId=$("#titleId").val();
	
	var url = `printHeamatologyReport/${labbill}@${fiscalyear}@${cMonth}@${headid}@${testid}@${inchargeId}@${doctor1}@${doctor2}@${machineId}@${titleId}`;
	window.open(url, '_blank');

}


function biochemistryPrintEvent(){
	
	var labbill=$("#labbill").val();
	var fiscalYear=$("#labfiscalyear").val();
	var cMonth=$("#cmonth").val();
	var headId=$("#department").val();
	var testid=$("#testid").val();
	var inchargeId=$("#inchargeId").val();
	var doctor1=$("#doctor1").val();
	var doctor2=$("#doctor2").val();
	var note=$("#bio-note").val();
	var machineId=$("#machineId").val();
	var titleId=$("#titleId").val();

	var testIdlist=[];
	var i=0;


	$(".checkdepartment").each(function(){

		if($(this).is(":checked")){
			var testId=$(this).val();
			
			testIdlist[i++]=[testId];
		}
		

	});
	
	testIdlist="["+testIdlist+"]";
	
	
	
	$.ajax({
		
		type:'POST',
		dataType:'json',
		url:'./investiationReportParam',
        data: {
        	labbill:labbill,
        	fiscalYear:fiscalYear,
        	cMonth:cMonth,
        	headId:headId,
        	testIdlist:testIdlist,
        	inchargeId:inchargeId,
        	doctor1:doctor1,
        	doctor2:doctor2,
        	note:note,
        	machineId:machineId,
        	titleId:titleId
        		
        },
		success: function (data) {
			if(data=='Success'){
				var url = 'printBiochemistryReport';
				window.open(url, '_blank');
			}
			else{
				alert(data);
			}

		
		}
		
		
	});


}

function serologyPrintEvent(){
	
	
	var labbill=$("#labbill").val();
	var fiscalYear=$("#labfiscalyear").val();
	var cMonth=$("#cmonth").val();
	var headId=$("#department").val();
	var testid=$("#testid").val();
	var inchargeId=$("#inchargeId").val();
	var doctor1=$("#doctor1").val();
	var doctor2=$("#doctor2").val();
	var note=$("#ser-note").val();
	var machineId=$("#machineId").val();
	var titleId=$("#titleId").val();
	

	var testIdlist=[];
	var i=0;


	$(".checkdepartment").each(function(){

		if($(this).is(":checked")){
			var testId=$(this).val();
			
			testIdlist[i++]=[testId];
		}
		

	});
	
	testIdlist="["+testIdlist+"]";
	
	
	$.ajax({
		
		type:'POST',
		dataType:'json',
		url:'./investiationReportParam',
        data: {
        	labbill:labbill,
        	fiscalYear:fiscalYear,
        	cMonth:cMonth,
        	headId:headId,
        	testIdlist:testIdlist,
        	inchargeId:inchargeId,
        	doctor1:doctor1,
        	doctor2:doctor2,
        	note:note,
        	machineId:machineId,
        	titleId:titleId
        		
        },
		success: function (data) {
			if(data=='Success'){
				var url = 'printSerologyReport';
				window.open(url, '_blank');
			}
			else{
				alert(data);
			}

		
		}
		
		
	});



}

function hormonePrintEvent(){
	
	
	var labbill=$("#labbill").val();
	var fiscalYear=$("#labfiscalyear").val();
	var cMonth=$("#cmonth").val();
	var headId=$("#department").val();
	var testid=$("#testid").val();
	var inchargeId=$("#inchargeId").val();
	var doctor1=$("#doctor1").val();
	var doctor2=$("#doctor2").val();
	var note=$("#hor-note").val();
	
	var conTestId=$("#testid").val();
	
	console.log("note "+note);
	
	var horReportCategory=$("#hormoneReportType").val();
	
	var machineId=$("#machineId").val();
	var titleId=$("#titleId").val();
	

	var testIdlist=[];
	var i=0;


	$(".checkdepartment").each(function(){

		if($(this).is(":checked")){
			var testId=$(this).val();
			
			testIdlist[i++]=[testId];
		}
		

	});
	
	testIdlist="["+testIdlist+"]";
	
	
	$.ajax({
		
		type:'POST',
		dataType:'json',
		url:'./investiationReportParam',
        data: {
        	labbill:labbill,
        	fiscalYear:fiscalYear,
        	cMonth:cMonth,
        	headId:headId,
        	testIdlist:testIdlist,
        	inchargeId:inchargeId,
        	doctor1:doctor1,
        	doctor2:doctor2,
        	note:note,
        	horReportCategory:horReportCategory,
        	machineId:machineId,
        	titleId:titleId
        	
        		
        },
		success: function (data) {
			if(data=='Success'){
				var url = 'printHormoneReport';
				window.open(url, '_blank');
			}
			else{
				alert(data);
			}

		
		}
		
		
	});

	

}


function immunologyPrintEvent(){
	

	var labbill=$("#labbill").val();
	var fiscalYear=$("#labfiscalyear").val();
	var cMonth=$("#cmonth").val();
	var headId=$("#department").val();
	var testid=$("#testid").val();
	var inchargeId=$("#inchargeId").val();
	var doctor1=$("#doctor1").val();
	var doctor2=$("#doctor2").val();
	var note=$("#img-note").val();
	var machineId=$("#machineId").val();
	var titleId=$("#titleId").val();
	
	var conTestId=$("#testid").val();
	
	console.log("note "+note);

	var immReportCategory=$("#immReportType").val();
	

	var testIdlist=[];
	var i=0;


	$(".checkdepartment").each(function(){

		if($(this).is(":checked")){
			var testId=$(this).val();
			
			testIdlist[i++]=[testId];
		}
		

	});
	
	testIdlist="["+testIdlist+"]";
	
	
	$.ajax({
		
		type:'POST',
		dataType:'json',
		url:'./investiationReportParam',
        data: {
        	labbill:labbill,
        	fiscalYear:fiscalYear,
        	cMonth:cMonth,
        	headId:headId,
        	testIdlist:testIdlist,
        	inchargeId:inchargeId,
        	doctor1:doctor1,
        	doctor2:doctor2,
        	note:note,
        	immReportCategory:immReportCategory,
        	machineId:machineId,
        	titleId:titleId
        	
        		
        },
		success: function (data) {
			if(data=='Success'){
				var url = 'printImmunologyReport';
				window.open(url, '_blank');
			}
			else{
				alert(data);
			}

		
		}
		
		
	});
	


}
function refreshEvent(){
	var department=$("#department").val();
	
	if(department=='1'){
		heamatologyRefreshEvent();
	}
	if(department=='4'){
		hormoneConRefreshEvent();
	}
	if(department=='9'){
		immunologyRefreshEvent();
	}
	if(department=='6'){
		microRefreshEvent();
	}
}

function microRefreshEvent(){
	$("#amoxycillinClavulanAcid").val('');
	$("#gentamycin").val('');
	$("#azithromycin").val('');
	$("#ciprofloxacin").val('');
	$("#meropenem").val('');
	$("#ceftriaxone").val('');
	$("#amikacin").val('');
	$("#piperacillinTazobactam").val('');
	$("#tigecycline").val('');
	$("#ertapenem").val('');
	
	$("#levofloxacin").val('');
	$("#imipenem").val('');
	$("#moxafloxacin").val('');
	$("#nalidixicAcid").val('');
	$("#cotrimexazole").val('');
	$("#cefuroxime").val('');
	$("#cotrimexazole").val('');
	$("#ceftazidime").val('');
	$("#nitrofurantoin").val('');
	$("#vancomycin").val('');
	$("#cefixime").val('');
	
}

function immunologyRefreshEvent(){
	$("#imm-cut-value").val('');
	$("#imm-patient-sample-count").val('');
	$("#imm-impression").val('');
}
function hormoneConRefreshEvent(){
	$("#hor-cut-value").val('');
	$("#hor-patient-sample-count").val('');
	$("#hor-impression").val('');
}
function heamatologyRefreshEvent(){
	$("#hgb").val('');
	$("#plt").val('');
	$("#esr").val('');
	$("#mpv").val('');
	$("#totalCount").val('');
	$("#pdw").val('');
	$("#neutrophlis").val('');
	$("#pct").val('');
	$("#mp").val('');
	$("#lymphocytes").val('');
	$("#rbc").val('');
	$("#mpc").val('');
	$("#monocytes").val('');
	$("#pcv").val('');
	$("#eosinophils").val('');
	$("#basophils").val('');
	$("#mch").val('');
	$("#mchc").val('');
	$("#mcv").val('');
	$("#rdw").val('');
	$("#ec").val('');
	$("#min").val('');
	$("#ctmin").val('');
	$("#mp").val('');
	$("#mpc").val('');
	$("#cec").val('');
	$("#other").val('');
}

function resultsave(){

	var department=$("#department").val();
	
	if(department=='1'){
		heamatologySaveEvent();
	}
	else if(department=='2'){
		biochemestrySaveEvent();
	}
	else if(department=='3'){
		serologySaveEvent();
	}
	else if(department=='4'){
		hormoneSaveEvent();
	}
	else if(department=='5'){
		urineSaveEvent();
	}
	
	else if(department=='8'){
		stoolSaveEvent();
	}
	else if(department=='6'){
		microbiologySaveEvent();
	}
	else if(department=='9'){
		immunologySaveEvent();
	}

}

function immunologySaveEvent(){
	var userId = $("#user_hidden").val();
	var labbill=$("#labbill").val();
	var labfiscalyear=$("#labfiscalyear").val();
	var headid=$("#department").val();
	var testid=$("#testid").val();
	var fiscalyear=$("#fiscalyear").val();
	var cmonth=$("#cmonth").val();
	var inchargeId=$("#inchargeId").val();
	var doctor1=$("#doctor1").val();
	var doctor2=$("#doctor2").val();
	
	var machineId=$("#machineId").val();
	var titleId=$("#titleId").val();
	
	
	var immReportType=$("#immReportType").val();
	
	if(immReportType=='0'){
		var resultlist=[];
		var i=0;

		$(".tr_list").each(function(){
			var testId=$(this).attr("data-id");
			var mainTestId=$(".testId-"+testId).val()==''?"~":$(".testId-"+testId).val();
			var resultvalue=$(".result_value-"+testId).val()==''?"~":$(".result_value-"+testId).val();
			var sortingvalue=$(".sort_value-"+testId).val()==''?"0":$(".sort_value-"+testId).val();
			var value=mainTestId+"@"+testId+"@"+resultvalue+"@"+sortingvalue;
			resultlist[i++]=[value];

		});
		
		
		var result="["+resultlist+"]";

		
		$.ajax({
			
			type:'POST',
			dataType:'json',
			url:'./immunologySaveEvent',
	        data: {
	        	userId:userId,
	        	labbill:labbill,
	        	labfiscalyear:labfiscalyear,
	        	fiscalyear:fiscalyear,
	        	Cmonth:cmonth,
	        	testid:testid,
	        	headid:headid,
	        	result:result,
	        	inchargeId:inchargeId,
	        	doctor1:doctor1,
	        	doctor2:doctor2,
	        	machineId:machineId,
	        	titleId:titleId
	        		
	        },
			success: function (data) {
				
				alert(data);
				$('#immunologytable').empty();
			}
			
			
		});
	}
	else{
		
		
		var immCutValue=$("#imm-cut-value").val();
		var immPatientSampleCount=$("#imm-patient-sample-count").val();
		var immImpression=$("#imm-impression").val();
		
		$.ajax({
			
			type:'POST',
			dataType:'json',
			url:'./immunologyConfirmatortySaveEvent',
	        data: {
	        	userId:userId,
	        	labbill:labbill,
	        	labfiscalyear:labfiscalyear,
	        	fiscalyear:fiscalyear,
	        	Cmonth:cmonth,
	        	testid:testid,
	        	headid:headid,
	        	immCutValue:immCutValue,
	        	immPatientSampleCount:immPatientSampleCount,
	        	immImpression:immImpression,
	        	inchargeId:inchargeId,
	        	doctor1:doctor1,
	        	doctor2:doctor2,
	        	machineId:machineId,
	        	titleId:titleId
	        		
	        },
			success: function (data) {
				
				alert(data);
			}
			
			
		});
	}

	
}
function hormoneSaveEvent(){
	var userId = $("#user_hidden").val();
	var labbill=$("#labbill").val();
	var labfiscalyear=$("#labfiscalyear").val();
	var headid=$("#department").val();
	var testid=$("#testid").val();
	var fiscalyear=$("#fiscalyear").val();
	var cmonth=$("#cmonth").val();
	var inchargeId=$("#inchargeId").val();
	var doctor1=$("#doctor1").val();
	var doctor2=$("#doctor2").val();
	
	var machineId=$("#machineId").val();
	var titleId=$("#titleId").val();
	
	var hormoneReportType=$("#hormoneReportType").val();
	
	console.log("hormoneReportType "+hormoneReportType);
	
	if(hormoneReportType=='0'){
		var resultlist=[];
		var i=0;

		$(".tr_list").each(function(){
			var testId=$(this).attr("data-id");
			var mainTestId=$(".testId-"+testId).val()==''?"~":$(".testId-"+testId).val();
			var resultvalue=$(".result_value-"+testId).val()==''?"~":$(".result_value-"+testId).val();
			var sortingvalue=$(".sort_value-"+testId).val()==''?"0":$(".sort_value-"+testId).val();
			var value=mainTestId+"@"+testId+"@"+resultvalue+"@"+sortingvalue;
			resultlist[i++]=[value];

		});
		
		
		var result="["+resultlist+"]";

		
		$.ajax({
			
			type:'POST',
			dataType:'json',
			url:'./hormoneSaveEvent',
	        data: {
	        	userId:userId,
	        	labbill:labbill,
	        	labfiscalyear:labfiscalyear,
	        	fiscalyear:fiscalyear,
	        	Cmonth:cmonth,
	        	testid:testid,
	        	headid:headid,
	        	result:result,
	        	inchargeId:inchargeId,
	        	doctor1:doctor1,
	        	doctor2:doctor2,
	        	machineId:machineId,
	        	titleId:titleId
	        		
	        },
			success: function (data) {
				
				alert(data);
				$('#hormonetable').empty();
			}
			
			
		});
	}
	else{
		
		var horCutValue=$("#hor-cut-value").val();
		var horPatientSampleCount=$("#hor-patient-sample-count").val();
		var horImpression=$("#hor-impression").val();
		
		$.ajax({
			
			type:'POST',
			dataType:'json',
			url:'./hormoneConfirmatortySaveEvent',
	        data: {
	        	userId:userId,
	        	labbill:labbill,
	        	labfiscalyear:labfiscalyear,
	        	fiscalyear:fiscalyear,
	        	Cmonth:cmonth,
	        	testid:testid,
	        	headid:headid,
	        	horCutValue:horCutValue,
	        	horPatientSampleCount:horPatientSampleCount,
	        	horImpression:horImpression,
	        	inchargeId:inchargeId,
	        	doctor1:doctor1,
	        	doctor2:doctor2,
	        	machineId:machineId,
	        	titleId:titleId
	        		
	        },
			success: function (data) {
				
				alert(data);
				
			/*	if(data.result.length>0){
					setHormoneSaveData(data.result);
				}*/
				
				
			}
			
			
		});
	}

	
}

function serologySaveEvent(){
	
	var userId = $("#user_hidden").val();
	var labbill=$("#labbill").val();
	var labfiscalyear=$("#labfiscalyear").val();
	var headid=$("#department").val();
	var fiscalyear=$("#fiscalyear").val();
	var cmonth=$("#cmonth").val();
	var inchargeId=$("#inchargeId").val();
	var doctor1=$("#doctor1").val();
	var doctor2=$("#doctor2").val();
	var machineId=$("#machineId").val();
	var titleId=$("#titleId").val();
	
	var resultlist=[];
	var i=0;

	$(".tr_list").each(function(){
		var testId=$(this).attr("data-id");
		var mainTestId=$(".testId-"+testId).val()==''?"~":$(".testId-"+testId).val();
		var resultvalue=$(".result_value-"+testId).val()==''?"~":$(".result_value-"+testId).val();
		var sortingvalue=$(".sort_value-"+testId).val()==''?"0":$(".sort_value-"+testId).val();
		var value=mainTestId+"@"+testId+"@"+resultvalue+"@"+sortingvalue;
		resultlist[i++]=[value];

	});
	
	
	
	
	var result="["+resultlist+"]";

	
	$.ajax({
		
		type:'POST',
		dataType:'json',
		url:'./serologySaveEvent',
        data: {
        	userId:userId,
        	labbill:labbill,
        	labfiscalyear:labfiscalyear,
        	fiscalyear:fiscalyear,
        	Cmonth:cmonth,
        	headid:headid,
        	result:result,
        	inchargeId:inchargeId,
        	doctor1:doctor1,
        	doctor2:doctor2,
        	machineId:machineId,
        	titleId:titleId
        		
        },
		success: function (data) {
			
			alert(data);
			$('#serologytable').empty();
		}
		
		
	});
	
	
	
}

function biochemestrySaveEvent(){
	
	
	
	var userId = $("#user_hidden").val();
	var labbill=$("#labbill").val();
	var labfiscalyear=$("#labfiscalyear").val();
	var headid=$("#department").val();
	
	var fiscalyear=$("#fiscalyear").val();
	var cmonth=$("#cmonth").val();
	console.log(" ccc month "+cmonth)
	
	var inchargeId=$("#inchargeId").val();
	var doctor1=$("#doctor1").val();
	var doctor2=$("#doctor2").val();
	var machineId=$("#machineId").val();
	var titleId=$("#titleId").val();

	
	var resultlist=[];
	var i=0;

	$(".tr_list").each(function(){
		var testId=$(this).attr("data-id");
		var mainTestId=$(".testId-"+testId).val()==''?"~":$(".testId-"+testId).val();
		var resultvalue=$(".result_value-"+testId).val()==''?"~":$(".result_value-"+testId).val();
		var sortingvalue=$(".sort_value-"+testId).val()==''?"0":$(".sort_value-"+testId).val();
		var value=mainTestId+"@"+testId+"@"+resultvalue+"@"+sortingvalue;
		resultlist[i++]=[value];

	});
	
	
	var result="["+resultlist+"]";

	
	$.ajax({
		
		type:'POST',
		dataType:'json',
		url:'./biochemestrySaveEvent',
        data: {
        	userId:userId,
        	labbill:labbill,
        	labfiscalyear:labfiscalyear,
        	fiscalyear:fiscalyear,
        	Cmonth:cmonth,
        	headid:headid,
        	result:result,
        	inchargeId:inchargeId,
        	doctor1:doctor1,
        	doctor2:doctor2,
        	machineId:machineId,
        	titleId:titleId
        		
        },
		success: function (data) {
			
			alert(data);
			//$('#biologytable').empty();
		}
		
		
	});
	
	
	
}


function microbiologySaveEvent(){
	var userId = $("#user_hidden").val();
	var labbill=$("#labbill").val();
	var labfiscalyear=$("#labfiscalyear").val();
	var headid=$("#department").val();
	var testid=$("#testid").val();
	var fiscalyear=$("#fiscalyear").val();
	var cmonth=$("#cmonth").val();
	var inchargeId=$("#inchargeId").val();
	var doctor1=$("#doctor1").val();
	var doctor2=$("#doctor2").val();
	
	var amoxycillinClavulanAcid=$('#amoxycillinClavulanAcid').val();
	var gentamycin=$('#gentamycin').val();
	var azithromycin=$('#azithromycin').val();
	
	var ciprofloxacin=$('#ciprofloxacin').val();
	var meropenem=$('#meropenem').val();
	var amikacin=$('#amikacin').val();
	var piperacillinTazobactam=$('#piperacillinTazobactam').val();
	var tigecycline=$('#tigecycline').val();
	var ertapenem=$('#ertapenem').val();
	var ceftriaxone=$('#ceftriaxone').val();
	
	var levofloxacin=$('#levofloxacin').val();
	var imipenem=$('#imipenem').val();
	var moxafloxacin=$('#moxafloxacin').val();
	var nalidixicAcid=$('#nalidixicAcid').val();
	var cotrimexazole=$('#cotrimexazole').val();
	var cefuroxime=$('#cefuroxime').val();
	var ceftazidime=$('#ceftazidime').val();
	var nitrofurantoin=$('#nitrofurantoin').val();
	var vancomycin=$('#vancomycin').val();
	var cefixime=$('#cefixime').val();
	
	
	
/*	var imipenem=$("#imipenem").val();
	var amoxycillin_a=$("#amoxycillin_a").val();
	var cefepime=$("#cefepime").val();
	var chloramphenicol=$("#chloramphenicol").val();
	var ceftrixon=$("#ceftrixon").val();
	var streptomycin=$("#streptomycin").val();
	var nitrofurantoin=$("#nitrofurantoin").val();
	var gentamycin=$("#gentamycin").val();
	var cepradine=$("#cepradine").val();
	var doxycycline=$("#doxycycline").val();
	var netilimyin=$("#netilimyin").val();
	var azithromycin=$("#azithromycin").val();
	
	
	var penicillin=$("#penicillin").val();
	var meropenem=$("#meropenem").val();
	var coTrimoxazole=$("#coTrimoxazole").val();
	var cefixime=$("#cefixime").val();
	var ceftazidime=$("#ceftazidime").val();
	
	var ceftazidime=$("#ceftazidime").val();
	var nalidiximeAcid=$("#nalidiximeAcid").val();
	var erythromycin=$("#erythromycin").val();
	var ceftaxime=$("#ceftaxime").val();
	var cephalexin=$("#cephalexin").val();
	var ciprofloxacine=$("#ciprofloxacine").val();
	var amicacin=$("#amicacin").val();
	var levofloxacin=$("#levofloxacin").val();
	*/
	var organism_a=$("#organism_a").val();
	var organism_b=$("#organism_b").val();
	var micro_nongrowth=$("#micro-nongrowth").val();
	var machineId=$("#machineId").val();
	var titleId=$("#titleId").val();

	$.ajax({
		
		type:'POST',
		dataType:'json',
		url:'./microbiologySaveEvent',
        data: {
        	userId:userId,
        	labbill:labbill,
        	labfiscalyear:labfiscalyear,
        	fiscalyear:fiscalyear,
        	Cmonth:cmonth,
        	testid:testid,
        	headid:headid,
        	
        	amoxycillinClavulanAcid:amoxycillinClavulanAcid,
        	gentamycin:gentamycin,
        	azithromycin:azithromycin,
        	ciprofloxacin:ciprofloxacin,
        	ceftriaxone:ceftriaxone,
        	meropenem:meropenem,
        	amikacin:amikacin,
        	piperacillinTazobactam:piperacillinTazobactam,
        	tigecycline:tigecycline,
        	ertapenem:ertapenem,
        	levofloxacin:levofloxacin,
        	imipenem:imipenem,
        	moxafloxacin:moxafloxacin,
        	nalidixicAcid:nalidixicAcid,
        	cotrimexazole:cotrimexazole,
        	cefuroxime:cefuroxime,
        	ceftazidime:ceftazidime,
        	nitrofurantoin:nitrofurantoin,
        	vancomycin:vancomycin,
        	cefixime:cefixime,
        	organism_a:organism_a,
        	organism_b:organism_b,
        	micro_nongrowth:micro_nongrowth,
        	inchargeId:inchargeId,
        	doctor1:doctor1,
        	doctor2:doctor2,
        	machineId:machineId,
        	titleId:titleId
        		
        		
        },
		success: function (data) {
			
			alert(data);
			
		}
		
		
	});
	
/*	$.ajax({
		
		type:'POST',
		dataType:'json',
		url:'./microbiologySaveEvent',
        data: {
        	userId:userId,
        	labbill:labbill,
        	labfiscalyear:labfiscalyear,
        	fiscalyear:fiscalyear,
        	testid:testid,
        	headid:headid,
        	
        	imipenem:imipenem,
        	amoxycillin_a:amoxycillin_a,
        	cefepime:cefepime,
        	chloramphenicol:chloramphenicol,
        	ceftrixon:ceftrixon,	
        	streptomycin:streptomycin,
        	nitrofurantoin:nitrofurantoin,      	
        	gentamycin:gentamycin,     	
        	cepradine:cepradine,
        	doxycycline:doxycycline,
        	netilimyin:netilimyin,
        	azithromycin:azithromycin,
        	penicillin:penicillin,
        	meropenem:meropenem,  	
        	coTrimoxazole:coTrimoxazole,
        	cefixime:cefixime,  	
        	ceftazidime:ceftazidime,
        	nalidiximeAcid:nalidiximeAcid,
        	erythromycin:erythromycin,
        	ceftaxime:ceftaxime,	
        	cephalexin:cephalexin,
        	ciprofloxacine:ciprofloxacine,
        	amicacin:amicacin,
        	levofloxacin:levofloxacin,
        	organism_a:organism_a,
        	organism_b:organism_b,
        	cotrimoxazole_a:cotrimoxazole_a,
        	cotrimoxazole_b:cotrimoxazole_b,
        	micro_nongrowth:micro_nongrowth,
        	inchargeId:inchargeId,
        	doctor1:doctor1,
        	doctor2:doctor2,
        	machineId:machineId,
        	titleId:titleId
        		
        		
        },
		success: function (data) {
			
			alert(data);
			
		}
		
		
	});*/
}

function stoolSaveEvent(){
	
	var inchargeId=$("#inchargeId").val();
	var doctor1=$("#doctor1").val();
	var doctor2=$("#doctor2").val();
	
	var userId = $("#user_hidden").val();
	var labbill=$("#labbill").val();
	var labfiscalyear=$("#labfiscalyear").val();
	var headid=$("#department").val();
	var testid=$("#testid").val();
	var fiscalyear=$("#fiscalyear").val();
	var cmonth=$("#cmonth").val();
	var sample=$("#sample").val();
	var consistency=$("#consistency").val();
	var stoolColor=$("#stoolColor").val();
	var odour=$("#odour").val();
	var stoolMucus=$("#stoolMucus").val();
	var stoolBlood=$("#stoolBlood").val();
	var helminths=$("#helminths").val();
	var reaction=$("#reaction").val();
	var reducingSubstance=$("#reducingSubstance").val();
	var occultBloodTest=$("#occultBloodTest").val();	
	var protozoa=$("#protozoa").val();
	var cystseh=$("#cystseh").val();
	
	
	var cystsColt=$("#cystsColt").val();
	var cystsGiardia=$("#cystsGiardia").val();
	var ovaoFroundWorm=$("#ovaoFroundWorm").val();
	var ovaOfHookWorm=$("#ovaOfHookWorm").val();
	var ovaOfWhipWorm=$("#ovaOfWhipWorm").val();
	var larvaOfSs=$("#larvaOfSs").val();
	
	var macrophage=$("#macrophage").val();
	var strach=$("#strach").val();
	var vagetiableCells=$("#vagetiableCells").val();
	var stoolPuscells=$("#stoolPuscells").val();
	var eithelial=$("#eithelial").val();
	var fatDroplets=$("#fatDroplets").val();
	var fungi=$("#fungi").val();
	var stoolWbc=$("#stoolWbc").val();
	var stoolRbc=$("#stoolRbc").val();
	var muscleFibers=$("#muscleFibers").val();
	var charotLeyden=$("#charotLeyden").val();
	var machineId=$("#machineId").val();
	var titleId=$("#titleId").val();
	
	$.ajax({
		
		type:'POST',
		dataType:'json',
		url:'./stoolSaveEvent',
        data: {
        	userId:userId,
        	labbill:labbill,
        	labfiscalyear:labfiscalyear,
        	fiscalyear:fiscalyear,
        	Cmonth:cmonth,
        	testid:testid,
        	headid:headid,
        	
        	consistency:consistency,
        	stoolColor:stoolColor,
        	odour:odour,
        	stoolMucus:stoolMucus,
        	stoolBlood:stoolBlood,
        	helminths:helminths,
        	reaction:reaction,
        	reducingSubstance:reducingSubstance,
        	occultBloodTest:occultBloodTest,
        	protozoa:protozoa,
        	cystseh:cystseh,
        	cystsColt:cystsColt,
        	cystsGiardia:cystsGiardia,
        	ovaoFroundWorm:ovaoFroundWorm,
        	ovaOfHookWorm:ovaOfHookWorm,
        	ovaOfWhipWorm:ovaOfWhipWorm,
        	larvaOfSs:larvaOfSs,
        	macrophage:macrophage,
        	strach:strach,
        	vagetiableCells:vagetiableCells,
        	stoolPuscells:stoolPuscells,
        	eithelial:eithelial,
        	fatDroplets:fatDroplets,
        	fungi:fungi,
        	stoolWbc:stoolWbc,
        	stoolRbc:stoolRbc,
        	muscleFibers:muscleFibers,
        	charotLeyden:charotLeyden,
        	inchargeId:inchargeId,
        	doctor1:doctor1,
        	doctor2:doctor2,
        	machineId:machineId,
        	titleId:titleId
        		
        },
		success: function (data) {
			
			alert(data);
			
		}
		
		
	});
}


function urineSaveEvent(){
	
	var inchargeId=$("#inchargeId").val();
	var doctor1=$("#doctor1").val();
	var doctor2=$("#doctor2").val();
	
	console.log("inchargeId "+inchargeId);
	console.log("doctor1 "+doctor1);	
	console.log("doctor2 "+doctor2);	
	
	var userId = $("#user_hidden").val();
	var labbill=$("#labbill").val();
	var labfiscalyear=$("#labfiscalyear").val();
	var headid=$("#department").val();
	var testid=$("#testid").val();
	var fiscalyear=$("#fiscalyear").val();
	var cmonth=$("#cmonth").val();
	var quantity=$("#quantity").val();
	var color=$("#color").val();
	var appearance=$("#appearance").val();
	var sediment=$("#sediment").val();
	var hyalinecash=$("#hyalinecash").val();
	var granularcells=$("#granularcells").val();
	var fatty=$("#fatty").val();
	var wbccasts=$("#wbccasts").val();
	var rbccasts=$("#rbccasts").val();
	var gravity=$("#gravity").val();
	var ph=$("#ph").val();
	var sugar=$("#sugar").val();
	
	
	var albumin=$("#albumin").val();
	var ketonebodies=$("#ketonebodies").val();
	var blood=$("#urineBlood").val();
	var bilirubin=$("#bilirubin").val();
	var uribiliogen=$("#uribiliogen").val();
	var nitrites=$("#nitrites").val();
	var bilepigment=$("#bilepigment").val();
	var bilesalth=$("#bilesalth").val();
	var phosphate=$("#phosphate").val();
	var rbc=$("#urinRbc").val();
	var puscells=$("#puscells").val();
	var trichomonasVaginalis=$("#trichomonasVaginalis").val();
	

	var epithelialcells=$("#epithelialcells").val();
	var mucus=$("#mucus").val();
	var supermatozoa=$("#supermatozoa").val();
	var parasites=$("#parasites").val();
	
	var microorganism=$("#microorganism").val();
	var fungus=$("#fungus").val();
	var calciumoxalate=$("#calciumoxalate").val();
	var uricacidcrystals=$("#uricacidcrystals").val();
	var triplephosphate=$("#triplephosphate").val();
	var amorphphosphate=$("#amorphphosphate").val();
	var urate=$("#urate").val();
	var machineId=$("#machineId").val();
	var titleId=$("#titleId").val();
	
	$.ajax({
		
		type:'POST',
		dataType:'json',
		url:'./urineSaveEvent',
        data: {
        	userId:userId,
        	labbill:labbill,
        	labfiscalyear:labfiscalyear,
        	fiscalyear:fiscalyear,
        	Cmonth:cmonth,
        	testid:testid,
        	headid:headid,
        	
        	quantity:quantity,
        	color:color,
        	appearance:appearance,
        	sediment:sediment,
        	hyalinecash:hyalinecash,
        	granularcells:granularcells,
        	fatty:fatty,
        	wbccasts:wbccasts,
        	rbccasts:rbccasts,
        	gravity:gravity,
        	ph:ph,
        	sugar:sugar,
        	albumin:albumin,
        	ketonebodies:ketonebodies,
        	blood:blood,
        	bilirubin:bilirubin,
        	uribiliogen:uribiliogen,
        	nitrites:nitrites,
        	bilepigment:bilepigment,
        	bilesalth:bilesalth,
        	phosphate:phosphate,
        	rbc:rbc,
        	puscells:puscells,
        	epithelialcells:epithelialcells,
        	mucus:mucus,
        	supermatozoa:supermatozoa,
        	parasites:parasites,
        	microorganism:microorganism,
        	trichomonasVaginalis:trichomonasVaginalis,
        	fungus:fungus,
        	calciumoxalate:calciumoxalate,
        	uricacidcrystals:uricacidcrystals,
        	triplephosphate:triplephosphate,
        	amorphphosphate:amorphphosphate,
        	urate:urate,
        	fungus:fungus,
        	inchargeId:inchargeId,
        	doctor1:doctor1,
        	doctor2:doctor2,
        	machineId:machineId,
        	titleId:titleId

        },
		success: function (data) {
			
			alert(data);
			
		}
		
		
	});
}
function heamatologySaveEvent(){
	
	var cmonth=$("#cmonth").val();
	
	var inchargeId=$("#inchargeId").val();
	var doctor1=$("#doctor1").val();
	var doctor2=$("#doctor2").val();
	var userId = $("#user_hidden").val();
	var labbill=$("#labbill").val();
	var labfiscalyear=$("#labfiscalyear").val();
	var headid=$("#department").val();
	var testid=$("#testid").val();
	var fiscalyear=$("#fiscalyear").val();
	
	
	var hgb=$("#hgb").val();
	var plt=$("#plt").val();
	var sample=$("#sample").val();
	var esr=$("#esr").val();
	var mpv=$("#mpv").val();
	var min=$("#min").val();
	
	

	var mpv=$("#mpv").val();
	var totalCount=$("#totalCount").val();
	var pdw=$("#pdw").val();
	var ctmin=$("#ctmin").val();

	var neutrophlis=$("#neutrophlis").val();
	var pct=$("#pct").val();
	var mp=$("#mp").val();
	var lymphocytes=$("#lymphocytes").val();
	var rbc=$("#rbc").val();
	var mpc=$("#mpc").val();
	var monocytes=$("#monocytes").val();
	var pcv=$("#pcv").val();
	var eosinophils=$("#eosinophils").val();
	var basophils=$("#basophils").val();
	var mch=$("#mch").val();
	var other=$("#other").val();
	var mchc=$("#mchc").val();
	
	var mcv=$("#mcv").val();
	var rdw=$("#rdw").val();
	var cec=$("#cec").val();
	//var ec=$("#ec").val();
	
	var machineId=$("#machineId").val();
	var titleId=$("#titleId").val();


	$.ajax({
		
		type:'POST',
		dataType:'json',
		url:'./heamatologySaveEvent',
        data: {
        	userId:userId,
        	labbill:labbill,
        	labfiscalyear:labfiscalyear,
        	Cmonth:cmonth,
        	fiscalyear:fiscalyear,
        	testid:testid,
        	headid:headid,
        	hgb:hgb,
        	plt:plt,
        	sample:sample,
        	esr:esr,
        	mpv:mpv,
        	totalCount:totalCount,
        	pdw:pdw,
        	ctmin:ctmin,
        	neutrophlis:neutrophlis,
        	pct:pct,
        	mp:mp,
        	lymphocytes:lymphocytes,
        	rbc:rbc,
        	mpc:mpc,
        	monocytes:monocytes,
        	pcv:pcv,
        	eosinophils:eosinophils,
        	basophils:basophils,
        	mch:mch,
        	other:other,
        	mchc:mchc,
        	mcv:mcv,
        	rdw:rdw,
        	min:min,
        	cec:cec,
        	inchargeId:inchargeId,
        	doctor1:doctor1,
        	doctor2:doctor2,
        	machineId:machineId,
        	titleId:titleId
        },
		success: function (data) {
			
			alert(data);
			
		}
		
		
	});
}

function labpatient_search(v){
	$(v).addClass('ac_loading');		
	var id=$(v).val();
	
	
	$(v).autocomplete({
		source: function( request, response ) {
		
			$.ajax({
				
				type:'GET',
				url:'./getlabpatientlist/'+id,
				success:function(data)
				{
					response(data);
		
				}
				
				
			});
			
			
			$(v).removeClass('ac_loading');
			
		},
		select: function (event, ui) {  
			  var regno = ui.item.label;
			  setDetails(regno.substring(regno.lastIndexOf('*')+1,regno.length));
	  },
		
	});
	
	
	$(v).autocomplete( "option", "appendTo", ".eventInsForm_labpatient" );
	
}


// Lab Result Report

//Heamatology
function setHeamatolorySaveData(data){
    for (var i = 0; i < data.length; i++) {
    	dsetHeamatolorySaveDataEachText(data[i]);
    }

}

function setUrineSaveData(data){
    for (var i = 0; i < data.length; i++) {
    	dsetUrineSaveDataEachText(data[i]);
    }

}

function setStoolSaveData(data){
    for (var i = 0; i < data.length; i++) {
    	dsetStoolSaveDataEachText(data[i]);
    }

}

function setHormoneSaveData(data){
    for (var i = 0; i < data.length; i++) {
    	dsetHormoneSaveDataEachText(data[i]);
    }

}

function setImmunologySaveDataEachText(data){
    for (var i = 0; i < data.length; i++) {
    	dsetImmunologySaveDataEachText(data[i]);
    }
}

function setMicrobiologySaveDataEachText(data){
    for (var i = 0; i < data.length; i++) {
    	dsetMicrobiologySaveDataEachText(data[i]);
    }
}

function dsetMicrobiologySaveDataEachText(rowData){
	

	let idListMicroNew = ["amoxycillinClavulanAcid","gentamycin","azithromycin","ciprofloxacin","meropenem","ceftriaxone","amikacin","piperacillinTazobactam","tigecycline","ertapenem",
		"levofloxacin","imipenem","moxafloxacin","nalidixicAcid","cotrimexazole","cefuroxime","ceftazidime","nitrofurantoin","vancomycin","cefixime"];
	idListMicroNew.forEach((id,index)=>{
	  console.log("execute",id);
	  $('#'+id).keyup(function(event){
	    console.log("execute",event.keyCode);
	    if(event.keyCode ===13){
	      event.preventDefault();
	      $("#"+idListMicroNew[index+1]).focus();
	    }
	    
	  });
	})
	
	
	if(rowData.RId=='300'){
		$('#amoxycillinClavulanAcid').val(rowData.Result);
	}
	if(rowData.RId=='301'){
		$('#gentamycin').val(rowData.Result);
	}
	if(rowData.RId=='302'){
		$('#azithromycin').val(rowData.Result);
	}
	if(rowData.RId=='303'){
		$('#ciprofloxacin').val(rowData.Result);
	}
	if(rowData.RId=='304'){
		$('#meropenem').val(rowData.Result);
	}
	if(rowData.RId=='305'){
		$('#ceftriaxone').val(rowData.Result);
	}
	if(rowData.RId=='306'){
		$('#amikacin').val(rowData.Result);
	}
	if(rowData.RId=='307'){
		$('#piperacillinTazobactam').val(rowData.Result);
	}
	if(rowData.RId=='308'){
		$('#tigecycline').val(rowData.Result);
	}
	if(rowData.RId=='309'){
		$('#ertapenem').val(rowData.Result);
	}
	if(rowData.RId=='310'){
		$('#levofloxacin').val(rowData.Result);
	}
	if(rowData.RId=='311'){
		$('#imipenem').val(rowData.Result);
	}
	if(rowData.RId=='312'){
		$('#moxafloxacin').val(rowData.Result);
	}
	if(rowData.RId=='313'){
		$('#nalidixicAcid').val(rowData.Result);
	}
	if(rowData.RId=='314'){
		$('#cotrimexazole').val(rowData.Result);
	}
	if(rowData.RId=='315'){
		$('#cefuroxime').val(rowData.Result);
	}
	if(rowData.RId=='316'){
		$('#ceftazidime').val(rowData.Result);
	}
	if(rowData.RId=='317'){
		$('#nitrofurantoin').val(rowData.Result);
	}
	if(rowData.RId=='318'){
		$('#vancomycin').val(rowData.Result);
	}
	if(rowData.RId=='319'){
		$('#cefixime').val(rowData.Result);
	}
	
	

/*	if(rowData.RId=='270'){
		$('#imipenem').val(rowData.Result);
	}
	if(rowData.RId=='271'){
		$('#amoxycillin_a').val(rowData.Result);
	}
	if(rowData.RId=='272'){
		$('#cefepime').val(rowData.Result);
	}
	if(rowData.RId=='273'){
		$('#chloramphenicol').val(rowData.Result);
	}
	if(rowData.RId=='274'){
		$('#ceftrixon').val(rowData.Result);
	}
	if(rowData.RId=='275'){
		$('#streptomycin').val(rowData.Result);
	}
	if(rowData.RId=='276'){
		$('#nitrofurantoin').val(rowData.Result);
	}
	if(rowData.RId=='277'){
		$('#gentamycin').val(rowData.Result);
	}
	if(rowData.RId=='278'){
		$('#cepradine').val(rowData.Result);
	}
	if(rowData.RId=='279'){
		$('#doxycycline').val(rowData.Result);
	}
	if(rowData.RId=='280'){
		$('#netilimyin').val(rowData.Result);
	}
	if(rowData.RId=='281'){
		$('#azithromycin').val(rowData.Result);
	}
	if(rowData.RId=='282'){
		$('#penicillin').val(rowData.Result);
	}
	if(rowData.RId=='283'){
		$('#meropenem').val(rowData.Result);
	}
	if(rowData.RId=='284'){
		$('#coTrimoxazole').val(rowData.Result);
	}
	if(rowData.RId=='285'){
		$('#cefixime').val(rowData.Result);
	}
	if(rowData.RId=='286'){
		$('#ceftazidime').val(rowData.Result);
	}
	if(rowData.RId=='287'){
		$('#nalidiximeAcid').val(rowData.Result);
	}
	if(rowData.RId=='288'){
		$('#erythromycin').val(rowData.Result);
	}
	if(rowData.RId=='289'){
		$('#ceftaxime').val(rowData.Result);
	}
	if(rowData.RId=='290'){
		$('#cephalexin').val(rowData.Result);
	}
	if(rowData.RId=='291'){
		$('#ciprofloxacine').val(rowData.Result);
	}
	if(rowData.RId=='292'){
		$('#amicacin').val(rowData.Result);
	}
	if(rowData.RId=='293'){
		$('#levofloxacin').val(rowData.Result);
	}*/
	
	
	if(rowData.RId=='244'){
		$('#organism_a').val(rowData.Result);
	}
	if(rowData.RId=='245'){
		$('#organism_b').val(rowData.Result);
	}
	
	if(rowData.RId=='267'){
		$('#micro-nongrowth').val(rowData.Result);
	}
	
	
	$('#inchargeId').val(rowData.inchargeId);
	$('#doctor1').val(rowData.doctor1);
	$('#doctor2').val(rowData.doctor2);
	
    $('#inchargeId').selectpicker('refresh');
    $('#doctor1').selectpicker('refresh');
    $('#doctor2').selectpicker('refresh');
    
	$('#machineId').val(rowData.machineId);
	$('#titleId').val(rowData.titleId);
	
    $('#machineId').selectpicker('refresh');
    $('#titleId').selectpicker('refresh');
    
}

function dsetImmunologySaveDataEachText(rowData){
	//alert("data "+rowData.Result);
	if(rowData.RId=='264'){
		$('#imm-cut-value').val(rowData.Result);
	}
	if(rowData.RId=='265'){
		$('#imm-patient-sample-count').val(rowData.Result);
	}
	if(rowData.RId=='266'){
		$('#imm-impression').val(rowData.Result);
	}
	
	$('#inchargeId').val(rowData.inchargeId);
	$('#doctor1').val(rowData.doctor1);
	$('#doctor2').val(rowData.doctor2);
	
    $('#inchargeId').selectpicker('refresh');
    $('#doctor1').selectpicker('refresh');
    $('#doctor2').selectpicker('refresh');
    
	$('#machineId').val(rowData.machineId);
	$('#titleId').val(rowData.titleId);
	
    $('#machineId').selectpicker('refresh');
    $('#titleId').selectpicker('refresh');
}

function dsetHormoneSaveDataEachText(rowData){
	//alert("data "+rowData.Result);
	if(rowData.RId=='261'){
		$('#hor-cut-value').val(rowData.Result);
	}
	if(rowData.RId=='262'){
		$('#hor-patient-sample-count').val(rowData.Result);
	}
	if(rowData.RId=='263'){
		$('#hor-impression').val(rowData.Result);
	}
	
	$('#inchargeId').val(rowData.inchargeId);
	$('#doctor1').val(rowData.doctor1);
	$('#doctor2').val(rowData.doctor2);
	
    $('#inchargeId').selectpicker('refresh');
    $('#doctor1').selectpicker('refresh');
    $('#doctor2').selectpicker('refresh');
    
	$('#machineId').val(rowData.machineId);
	$('#titleId').val(rowData.titleId);
	
    $('#machineId').selectpicker('refresh');
    $('#titleId').selectpicker('refresh');
}
function dsetStoolSaveDataEachText(rowData){
	//alert("data "+rowData.Result);
	if(rowData.RId=='85'){
		$('#consistency').val(rowData.Result);
	}
	if(rowData.RId=='86'){
		$('#stoolColor').val(rowData.Result);
	}
	if(rowData.RId=='87'){
		$('#odour').val(rowData.Result);
	}
	if(rowData.RId=='88'){
		$('#stoolMucus').val(rowData.Result);
	}
	if(rowData.RId=='89'){
		$('#stoolBlood').val(rowData.Result);
	}
	if(rowData.RId=='90'){
		$('#helminths').val(rowData.Result);
	}
	if(rowData.RId=='91'){
		$('#reaction').val(rowData.Result);
	}
	if(rowData.RId=='92'){
		$('#reducingSubstance').val(rowData.Result);
	}
	if(rowData.RId=='93'){
		$('#occultBloodTest').val(rowData.Result);
	}
	if(rowData.RId=='94'){
		$('#protozoa').val(rowData.Result);
	}
	if(rowData.RId=='95'){
		$('#cystseh').val(rowData.Result);
	}
	if(rowData.RId=='96'){
		$('#cystsColt').val(rowData.Result);
	}
	if(rowData.RId=='97'){
		$('#cystsGiardia').val(rowData.Result);
	}
	if(rowData.RId=='98'){
		$('#ovaoFroundWorm').val(rowData.Result);
	}
	if(rowData.RId=='99'){
		$('#ovaOfHookWorm').val(rowData.Result);
	}
	if(rowData.RId=='100'){
		$('#ovaOfWhipWorm').val(rowData.Result);
	}
	if(rowData.RId=='101'){
		$('#strach').val(rowData.Result);
	}
	if(rowData.RId=='102'){
		$('#vagetiableCells').val(rowData.Result);
	}
	if(rowData.RId=='103'){
		$('#stoolPuscells').val(rowData.Result);
	}
	if(rowData.RId=='104'){
		$('#eithelial').val(rowData.Result);
	}
	if(rowData.RId=='105'){
		$('#fatDroplets').val(rowData.Result);
	}
	if(rowData.RId=='106'){
		$('#fungi').val(rowData.Result);
	}
	if(rowData.RId=='107'){
		$('#stoolWbc').val(rowData.Result);
	}
	if(rowData.RId=='108'){
		$('#stoolRbc').val(rowData.Result);
	}
	if(rowData.RId=='110'){
		$('#muscleFibers').val(rowData.Result);
	}
	if(rowData.RId=='112'){
		$('#larvaOfSs').val(rowData.Result);
	}
	if(rowData.RId=='113'){
		$('#macrophage').val(rowData.Result);
	}
	if(rowData.RId=='114'){
		$('#charotLeyden').val(rowData.Result);
	}
	
	$('#inchargeId').val(rowData.inchargeId);
	$('#doctor1').val(rowData.doctor1);
	$('#doctor2').val(rowData.doctor2);
	
    $('#inchargeId').selectpicker('refresh');
    $('#doctor1').selectpicker('refresh');
    $('#doctor2').selectpicker('refresh');
    
	$('#machineId').val(rowData.machineId);
	$('#titleId').val(rowData.titleId);
	
    $('#machineId').selectpicker('refresh');
    $('#titleId').selectpicker('refresh');
}

function dsetUrineSaveDataEachText(rowData){
	//alert("data "+rowData.Result);
	if(rowData.RId=='38'){
		$('#quantity').val(rowData.Result);
	}
	else if(rowData.RId=='39'){
		$('#color').val(rowData.Result);
	}
	else if(rowData.RId=='40'){
		$('#appearance').val(rowData.Result);
	}
	else if(rowData.RId=='41'){
		$('#sediment').val(rowData.Result);
	}
	else if(rowData.RId=='42'){
		$('#gravity').val(rowData.Result);
	}
	else if(rowData.RId=='43'){
		$('#ph').val(rowData.Result);
	}
	else if(rowData.RId=='65'){
		$('#sugar').val(rowData.Result);
	}
	else if(rowData.RId=='44'){
		$('#albumin').val(rowData.Result);
	}
	else if(rowData.RId=='47'){
		$('#ketonebodies').val(rowData.Result);
	}
	else if(rowData.RId=='52'){
		$('#blood').val(rowData.Result);
	}
	else if(rowData.RId=='49'){
		$('#bilirubin').val(rowData.Result);
	}
	else if(rowData.RId=='48'){
		$('#uribiliogen').val(rowData.Result);
	}
	else if(rowData.RId=='50'){
		$('#nitrites').val(rowData.Result);
	}
	else if(rowData.RId=='46'){
		$('#bilepigment').val(rowData.Result);
	}
	else if(rowData.RId=='45'){
		$('#bilesalth').val(rowData.Result);
	}
	else if(rowData.RId=='66'){
		$('#phosphate').val(rowData.Result);
	}
	else if(rowData.RId=='73'){
		$('#hyalinecash').val(rowData.Result);
	}
	else if(rowData.RId=='75'){
		$('#fatty').val(rowData.Result);
	}
	else if(rowData.RId=='76'){
		$('#wbccasts').val(rowData.Result);
	}
	else if(rowData.RId=='77'){
		$('#rbccasts').val(rowData.Result);
	}
	else if(rowData.RId=='56'){

	
		$('#urinRbc').val(rowData.Result);
	}
	else if(rowData.RId=='55'){
		$('#epithelialcells').val(rowData.Result);
	}
	else if(rowData.RId=='67'){
		$('#mucus').val(rowData.Result);
	}
	else if(rowData.RId=='68'){
		$('#supermatozoa').val(rowData.Result);
	}
	else if(rowData.RId=='54'){

		$('#puscells').val(rowData.Result);
	}
	else if(rowData.RId=='69'){

		$('#parasites').val(rowData.Result);
	}
	else if(rowData.RId=='70'){
		$('#microorganism').val(rowData.Result);
	}
	else if(rowData.RId=='57'){
		$('#calciumoxalate').val(rowData.Result);
	}
	else if(rowData.RId=='61'){
		$('#uricacidcrystals').val(rowData.Result);
	}
	else if(rowData.RId=='72'){
		$('#urate').val(rowData.Result);
	}
	else if(rowData.RId=='59'){
		$('#triplephosphate').val(rowData.Result);
	}
	else if(rowData.RId=='58'){
		$('#amorphphosphate').val(rowData.Result);
	}
	
	$('#inchargeId').val(rowData.inchargeId);
	$('#doctor1').val(rowData.doctor1);
	$('#doctor2').val(rowData.doctor2);
	
    $('#inchargeId').selectpicker('refresh');
    $('#doctor1').selectpicker('refresh');
    $('#doctor2').selectpicker('refresh');
    
	$('#machineId').val(rowData.machineId);
	$('#titleId').val(rowData.titleId);
	
    $('#machineId').selectpicker('refresh');
    $('#titleId').selectpicker('refresh');
}
function dsetHeamatolorySaveDataEachText(rowData){
	//alert("data "+rowData.Result);
	if(rowData.RId=='1'){
		$('#esr').val(rowData.Result);
	}
	else if(rowData.RId=='3'){
		$('#lymphocytes').val(rowData.Result);
	}
	else if(rowData.RId=='4'){
		$('#monocytes').val(rowData.Result);
	}
	else if(rowData.RId=='5'){
		$('#eosinophils').val(rowData.Result);
	}
	else if(rowData.RId=='6'){
		$('#basophils').val(rowData.Result);
	}
	else if(rowData.RId=='23'){
		$('#rbc').val(rowData.Result);
	}
	else if(rowData.RId=='24'){
		$('#neutrophlis').val(rowData.Result);
	}
	else if(rowData.RId=='7'){
		$('#plt').val(rowData.Result);
	}
	else if(rowData.RId=='8'){
		$('#hgb').val(rowData.Result);
	}
	else if(rowData.RId=='9'){
		$('#pcv').val(rowData.Result);
	}
	else if(rowData.RId=='10'){
		$('#mcv').val(rowData.Result);
	}
	else if(rowData.RId=='11'){
		$('#mch').val(rowData.Result);
	}
	else if(rowData.RId=='12'){
		$('#mchc').val(rowData.Result);
	}
	else if(rowData.RId=='13'){
		$('#rdw').val(rowData.Result);
	}
	else if(rowData.RId=='14'){
		$('#pct').val(rowData.Result);
	}
	else if(rowData.RId=='15'){
		$('#mpv').val(rowData.Result);
	}
	else if(rowData.RId=='16'){
		$('#pdw').val(rowData.Result);
	}
	else if(rowData.RId=='17'){
		$('#plt').val(rowData.Result);
	}
	else if(rowData.RId=='22'){
		$('#ec').val(rowData.Result);
	}
	else if(rowData.RId=='30'){
		$('#totalCount').val(rowData.Result);
	}
	else if(rowData.RId=='25'){
		$('#mp').val(rowData.Result);
	}
	else if(rowData.RId=='18'){
		$('#mpc').val(rowData.Result);
	}
	else if(rowData.RId=='26'){
		$('#other').val(rowData.Result);
	}
	else if(rowData.RId=='19'){
		$('#min').val(rowData.Result);
	}
	else if(rowData.RId=='20'){
		$('#ctmin').val(rowData.Result);
	}
	else if(rowData.RId=='20'){
		$('#ctmin').val(rowData.Result);
	}
	else if(rowData.RId=='18'){
		$('#mpc').val(rowData.Result);
	}
	else if(rowData.RId=='25'){
		$('#mp').val(rowData.Result);
	}
	else if(rowData.RId=='260'){
		$('#cec').val(rowData.Result);
	}
	
	
	$('#inchargeId').val(rowData.inchargeId);
	$('#doctor1').val(rowData.doctor1);
	$('#doctor2').val(rowData.doctor2);
	
    $('#inchargeId').selectpicker('refresh');
    $('#doctor1').selectpicker('refresh');
    $('#doctor2').selectpicker('refresh');
    
	$('#machineId').val(rowData.machineId);
	$('#titleId').val(rowData.titleId);
	
    $('#machineId').selectpicker('refresh');
    $('#titleId').selectpicker('refresh');
}

$(document).on("click",".checkdepartment",function(){
	
	var cMonth=$("#cmonth").val();
	var resultlist=[];
	var mid=$(this).attr("data-id");
	
	
	if($(this).is(":checked")){
		
		var testId=$(this).val();
		
	

		var testName=$('#'+mid+'-testname').val();
		
		$('#testName').val(testName);
	
		var labbill=$('#labbill').val();
		var fiscalyear=$('#labfiscalyear').val();
		
		console.log("mid "+mid)
		
		if(mid=='1'){
			
			tempDep=mid;
			
			heamatologyRefreshEvent();
			$('#mytabs a[href="#hematology_tab"]').tab('show');
			
			
			$.ajax({
				
				type:'GET',
				dataType:'json',
		        data: {
		        	testId:testId,
		        	labbill:labbill,
		        	fiscalyear:fiscalyear,
		        	cMonth:cMonth
		        
		        },
				url:'./setHeamatologySaveData',
				success: function (data) {
					
					setHeamatolorySaveData(data.result);
					
				}
				
				
			});
		}
		else if(mid=='2'){
	     	$("#biologytable").empty();
			$('#mytabs a[href="#biochemestry_tab"]').tab('show');
		
			
			m=0;

			$(".checkdepartment").each(function(){

				if($(this).is(":checked")){
					var headId=$(this).attr("data-id");
					
					if(mid==headId){
						var testId1=$(this).val();
						
						resultlist[m++]=[testId1];
					}

				}

			});
			
			
			var testcodelist="["+resultlist+"]";
			
		
			$.ajax({
				
				type:'POST',
				dataType:'json',
		        data: {
		        	testcodelist:testcodelist,
		        	labbill:labbill,
		        	headId:mid,
		        	fiscalyear:fiscalyear,
		        	cMonth:cMonth

		        },
		        url:'./setBioSerHormoneTestData',
				success: function (data) {
					resultsize=data.result.length;
					console.log("resultsize "+resultsize);
					if(resultsize=='0'){
						
						console.log("resultsizechec "+resultsize);
						$.ajax({
							
							type:'GET',
							dataType:'json',
							url:'./BioSerHormoneTestData/'+result,
							success: function (data) {
								
						      	$("#biologytable").empty();
								drawBiologyTable(data.result,0);
								$("#biologytable").val(labbill);
							}
							
							
						});
					}
					else{
				      	$("#biologytable").empty();
						drawBiologyTable(data.result,1);
						$("#biologytable").val(labbill);
					}
		
				}
				
				
			});
			


			
		}
		else if(mid=='3'){
			 $("#serologytable").empty();
			$('#mytabs a[href="#serology_tab"]').tab('show');
			
			
			m=0;

			$(".checkdepartment").each(function(){

				if($(this).is(":checked")){
					var headId=$(this).attr("data-id");
					if(mid==headId){
						var testId1=$(this).val();
						
						resultlist[m++]=[testId1];
					}
				}

			});
			
			var testcodelist="["+resultlist+"]";
			
			$.ajax({
				
				type:'POST',
				dataType:'json',
		        data: {
		        	testcodelist:testcodelist,
		        	labbill:labbill,
		        	headId:mid,
		        	fiscalyear:fiscalyear,
		        	cMonth:cMonth

		        },
		        url:'./setBioSerHormoneTestData',
				success: function (data) {
					resultsize=data.result.length;
					console.log("resultsize "+resultsize);
					if(resultsize=='0'){
						
						console.log("resultsizechec "+resultsize);
						$.ajax({
							
							type:'GET',
							dataType:'json',
							url:'./BioSerHormoneTestData/'+testcodelist,
							success: function (data) {
								
						      	$("#serologytable").empty();
						      	drawSerologyTable(data.result,0);
								$("#serologytable").val(labbill);
							}
							
							
						});
					}
					else{
				      	$("#serologytable").empty();
				      	drawSerologyTable(data.result,1);
						$("#serologytable").val(labbill);
					}
		
				}
				
				
			});
			

		}
		else if(mid=='4'){
			hormoneConRefreshEvent();
			 $("#hormonetable").empty();
			$('#mytabs a[href="#hormone_tab"]').tab('show');
			
			
			m=0;

			$(".checkdepartment").each(function(){

				if($(this).is(":checked")){
					var headId=$(this).attr("data-id");
					if(mid==headId){
						var testId1=$(this).val();
						
						resultlist[m++]=[testId1];
					}
				}

			});
			
			
			
			var testcodelist="["+resultlist+"]";
			$.ajax({
				
				type:'POST',
				dataType:'json',
		        data: {
		        	testcodelist:testcodelist,
		        	labbill:labbill,
		        	headId:mid,
		        	fiscalyear:fiscalyear,
		        	cMonth:cMonth

		        },
		        url:'./setBioSerHormoneTestData',
				success: function (data) {
					resultsize=data.result.length;
					console.log("resultsize "+resultsize);
					if(resultsize=='0'){
						
						console.log("resultsizechec "+resultsize);
						$.ajax({
							
							type:'GET',
							dataType:'json',
							url:'./BioSerHormoneTestData/'+testcodelist,
							success: function (data) {
								
						      	$("#hormonetable").empty();
						      	drawHormoneTable(data.result,0);
								$("#hormonetable").val(labbill);
							}
							
							
						});
					}
					else{
				      	$("#hormonetable").empty();
				      	drawHormoneTable(data.result,1);
						$("#hormonetable").val(labbill);
						
						setHormoneSaveData(data.resultcon);
					}
		
				}
				
				
			});
			
		}
		else if(mid=='5'){
			$('#mytabs a[href="#urine_tab"]').tab('show');
			
			$.ajax({
				
				type:'GET',
				dataType:'json',
		        data: {
		        	testId:testId,
		        	labbill:labbill,
		        	fiscalyear:fiscalyear,
		        	cMonth:cMonth

		        },
				url:'./setUrineSaveData',
				success: function (data) {
					
					setUrineSaveData(data.result);
					
				}
				
				
			});
		}
		else if(mid=='8'){
			$('#mytabs a[href="#stool_tab"]').tab('show');
			
			$.ajax({
				
				type:'GET',
				dataType:'json',
		        data: {
		        	testId:testId,
		        	labbill:labbill,
		        	fiscalyear:fiscalyear,
		        	cMonth:cMonth

		        },
				url:'./setStoolSaveData',
				success: function (data) {
					
					setStoolSaveData(data.result);
					
				}
				
				
			});
		}
		else if(mid=='9'){
			
			immunologyRefreshEvent();
			 $("#immunologytable").empty();
			$('#mytabs a[href="#immunology_tab"]').tab('show');
				
				
				m=0;

				$(".checkdepartment").each(function(){

					if($(this).is(":checked")){
						var headId=$(this).attr("data-id");
						if(mid==headId){
							var testId1=$(this).val();
							
							resultlist[m++]=[testId1];
						}
					}

				});
				
				
				
				var testcodelist="["+resultlist+"]";
				$.ajax({
					
					type:'POST',
					dataType:'json',
			        data: {
			        	testcodelist:testcodelist,
			        	labbill:labbill,
			        	headId:mid,
			        	fiscalyear:fiscalyear,
			        	cMonth:cMonth

			        },
			        url:'./setBioSerHormoneTestData',
					success: function (data) {
						resultsize=data.result.length;
						console.log("resultsize "+resultsize);
						if(resultsize=='0'){
							
							console.log("resultsizechec "+resultsize);
							$.ajax({
								
								type:'GET',
								dataType:'json',
								url:'./BioSerHormoneTestData/'+testcodelist,
								success: function (data) {
									
							      	$("#immunologytable").empty();
							      	drawImmunologyTable(data.result,0);
									$("#immunologytable").val(labbill);
								}
								
								
							});
						}
						else{
					      	$("#immunologytable").empty();
					      	drawImmunologyTable(data.result,1);
							$("#immunologytable").val(labbill);
							
							console.log("resultcon "+data.resultcon.length);
							setImmunologySaveDataEachText(data.resultcon);
						}
			
					}
					
					
				});
				
		}
		else if(mid=='6'){
			$('#mytabs a[href="#microbiology_tab"]').tab('show');
			
			$.ajax({
				
				type:'GET',
				dataType:'json',
		        data: {
		        	testId:testId,
		        	labbill:labbill,
		        	fiscalyear:fiscalyear,
		        	cMonth:cMonth

		        },
				url:'./setMicrobiologySaveData',
				success: function (data) {
					
					setMicrobiologySaveDataEachText(data.result);
					
				}
				
				
			});
			
			
		}
		else if(mid=='28'){
			$('#mytabs a[href="#echochardio_tab"]').tab('show');
		}
		
		$("#department").val(mid);
		
		
		$("#testid").val(testId);
	}
	else{
		
	
		if(mid=='1'){
			$('#mytabs a[href="#hematology_tab"]').tab('hide');
		}
		else if(mid=='2'){
			$("#biologytable").empty();
			//$('#mytabs a[href="#biochemestry_tab"]').tab('hide');
			/*sameTest=0;
			temtDip=0;*/
		}
		else if(mid=='3'){
			$('#mytabs a[href="#serology_tab"]').tab('hide');
		}
		else if(mid=='4'){
			 $("#hormonetable").empty();
			
			///$('#mytabs a[href="#hormone_tab"]').tab('hide');
			
			
			
		}
		else if(mid=='5'){
			$('#mytabs a[href="#urine_tab"]').tab('hide');
		}
		else if(mid=='8'){
			$('#mytabs a[href="#stool_tab"]').tab('hide');
		}
		else if(mid=='6'){
			$('#mytabs a[href="#microbiology_tab"]').tab('hide');
		}
		else if(mid=='28'){
			$('#mytabs a[href="#echochardio_tab"]').tab('hide');
		}
		

	}


})


function drawImmunologyTable(data,temp) {
    var rows = [];
    
    for (var i = 0; i < data.length; i++) {
        rows.push(drawImmunologyTableRaw(data[i],i+1,temp));
    }

    $("#immunologytable").append(rows);
}


function drawImmunologyTableRaw(rowData,c,temp) {
	
	
	console.log("temp "+temp);
	console.log("testId "+rowData.MainTestId);
	
	
	var row = $("<tr class='tr_list' data-id='"+rowData.TestId+"' />")
	
	row.append($("<td>" + rowData.TestId+ "</td>"));
	row.append($("<td >" + rowData.TestName+ "</td>"));
	if(temp=='0'){
		row.append($("<td><input type='hidden' style='background:#8FBC8F;' class='form-control testId-"+rowData.TestId+"' value='"+rowData.MainTestId+"'/><input id='' type='text' class='form-control result_value-"+rowData.TestId+"'></td>"));
	}
	else if(temp=='1'){
		row.append($("<td><input type='hidden' style='background:#8FBC8F;' class='form-control testId-"+rowData.TestId+"' value='"+rowData.MainTestId+"'/><input id='' type='text' class='form-control result_value-"+rowData.TestId+"' value='"+rowData.Result+"'></td>"));
	}
	row.append($("<td></td>"));
	row.append($("<td class='biotest'>" + rowData.NormalRange + "</td>"));
	row.append($("<td>" + rowData.Unit + "</td>"));
	
	if(temp=='0'){
		row.append($("<td style='width:60px;' ><input style='width:60px;background:#8FBC8F;' type='text' class='form-control sort_value-"+rowData.TestId+"''></td>"));
	}
	else if(temp=='1'){
		row.append($("<td style='width:60px;' ><input style='width:60px;background:#8FBC8F;' type='text' class='form-control sort_value-"+rowData.TestId+"' value ='"+rowData.Sorting+"' ></td>"));
	}
	
	row.append($("<td>" + rowData.MainTestName + "</td>"));
	
	if(rowData.inchargeId!=null || rowData.inchargeId!=''){
		$('#inchargeId').val(rowData.inchargeId);
	    $('#inchargeId').selectpicker('refresh');
	}
	
	if(rowData.doctor1!=null || rowData.doctor1!=''){
		$('#doctor1').val(rowData.doctor1);
		$('#doctor1').selectpicker('refresh');
	}
	
	if(rowData.doctor2!=null || rowData.doctor2!=''){
		$('#doctor2').val(rowData.doctor2);
	    $('#doctor2').selectpicker('refresh');
	}
	

	
    if(rowData.machineId!=null || rowData.machineId!=''){
    	$('#machineId').val(rowData.machineId);
        $('#machineId').selectpicker('refresh');
    }
    
    if(rowData.titleId!=null || rowData.titleId!=''){
		$('#titleId').val(rowData.titleId);
	    $('#titleId').selectpicker('refresh');
    }
	return row;
}

function drawHormoneTable(data,temp) {
    var rows = [];
    
    for (var i = 0; i < data.length; i++) {
        rows.push(drawHormoneTableRaw(data[i],i+1,temp));
    }

    $("#hormonetable").append(rows);
}


function drawHormoneTableRaw(rowData,c,temp) {
	
	
	console.log("temp "+temp);
	console.log("testId "+rowData.MainTestId);
	
	
	var row = $("<tr class='tr_list' data-id='"+rowData.TestId+"' />")
	
	row.append($("<td>" + rowData.TestId+ "</td>"));
	row.append($("<td >" + rowData.TestName+ "</td>"));
	if(temp=='0'){
		row.append($("<td><input type='hidden' style='background:#8FBC8F;' class='form-control testId-"+rowData.TestId+"' value='"+rowData.MainTestId+"'/><input id='' type='text' class='form-control result_value-"+rowData.TestId+"'></td>"));
	}
	else if(temp=='1'){
		row.append($("<td><input type='hidden' style='background:#8FBC8F;' class='form-control testId-"+rowData.TestId+"' value='"+rowData.MainTestId+"'/><input id='' type='text' class='form-control result_value-"+rowData.TestId+"' value='"+rowData.Result+"'></td>"));
	}
	row.append($("<td></td>"));
	row.append($("<td class='biotest'>" + rowData.NormalRange + "</td>"));
	row.append($("<td>" + rowData.Unit + "</td>"));
	
	if(temp=='0'){
		row.append($("<td style='width:60px;' ><input style='width:60px;background:#8FBC8F;' type='text' class='form-control sort_value-"+rowData.TestId+"''></td>"));
	}
	else if(temp=='1'){
		row.append($("<td style='width:60px;' ><input style='width:60px;background:#8FBC8F;' type='text' class='form-control sort_value-"+rowData.TestId+"' value ='"+rowData.Sorting+"' ></td>"));
	}
	
	row.append($("<td>" + rowData.MainTestName + "</td>"));
	
	if(rowData.inchargeId!=null || rowData.inchargeId!=''){
		$('#inchargeId').val(rowData.inchargeId);
	    $('#inchargeId').selectpicker('refresh');
	}
	
	if(rowData.doctor1!=null || rowData.doctor1!=''){
		$('#doctor1').val(rowData.doctor1);
		$('#doctor1').selectpicker('refresh');
	}
	
	if(rowData.doctor2!=null || rowData.doctor2!=''){
		$('#doctor2').val(rowData.doctor2);
	    $('#doctor2').selectpicker('refresh');
	}
	

	
    if(rowData.machineId!=null || rowData.machineId!=''){
    	$('#machineId').val(rowData.machineId);
        $('#machineId').selectpicker('refresh');
    }
    
    if(rowData.titleId!=null || rowData.titleId!=''){
		$('#titleId').val(rowData.titleId);
	    $('#titleId').selectpicker('refresh');
    }
	
	return row;
}

function drawSerologyTable(data,temp) {
    var rows = [];
    
    for (var i = 0; i < data.length; i++) {
        rows.push(drawSerologyTableRaw(data[i],i+1,temp));
    }

    $("#serologytable").append(rows);
}


function drawSerologyTableRaw(rowData,c,temp) {
	var row = $("<tr class='tr_list' data-id='"+rowData.TestId+"' />")
	
	row.append($("<td>" + rowData.TestId+ "</td>"));
	row.append($("<td >" + rowData.TestName+ "</td>"));
	if(temp=='0'){
		row.append($("<td><input type='hidden' style='background:#8FBC8F;' class='form-control testId-"+rowData.TestId+"' value='"+rowData.MainTestId+"'/><input id='' type='text' class='form-control result_value-"+rowData.TestId+"'></td>"));
	}
	else if(temp=='1'){
		row.append($("<td><input type='hidden' style='background:#8FBC8F;' class='form-control testId-"+rowData.TestId+"' value='"+rowData.MainTestId+"'/><input id='' type='text' class='form-control result_value-"+rowData.TestId+"' value ='"+rowData.Result+"'></td>"));
	}
	
	row.append($("<td></td>"));
	row.append($("<td class='biotest'>" + rowData.NormalRange + "</td>"));
	row.append($("<td>" + rowData.Unit + "</td>"));
	if(temp=='0'){
		row.append($("<td style='width:60px;' ><input style='width:60px;background:#8FBC8F;' type='text' class='form-control sort_value-"+rowData.TestId+"''></td>"));
	}
	else if(temp=='1'){
		row.append($("<td style='width:60px;' ><input style='width:60px;background:#8FBC8F;' type='text' class='form-control sort_value-"+rowData.TestId+"' value ='"+rowData.Sorting+"' ></td>"));
	}

	row.append($("<td>" + rowData.MainTestName + "</td>"));
	
	if(rowData.inchargeId!=null || rowData.inchargeId!=''){
		$('#inchargeId').val(rowData.inchargeId);
	    $('#inchargeId').selectpicker('refresh');
	}
	
	if(rowData.doctor1!=null || rowData.doctor1!=''){
		$('#doctor1').val(rowData.doctor1);
		$('#doctor1').selectpicker('refresh');
	}
	
	if(rowData.doctor2!=null || rowData.doctor2!=''){
		$('#doctor2').val(rowData.doctor2);
	    $('#doctor2').selectpicker('refresh');
	}
	

	console.log("machineId "+rowData.machineId);
	console.log("titleId "+rowData.titleId);
	
	$('#machineId').val(rowData.machineId);
    $('#machineId').selectpicker('refresh');
    
	$('#titleId').val(rowData.titleId);
    $('#titleId').selectpicker('refresh');
    
	return row;
}

function drawBiologyTable(data,temp) {
    var rows = [];
    
    for (var i = 0; i < data.length; i++) {
        rows.push(drawBiologyTableRaw(data[i],i+1,temp));
    }

    $("#biologytable").append(rows);
}


function drawBiologyTableRaw(rowData,c,temp) {
	var row = $("<tr class='tr_list' data-id='"+rowData.TestId+"' />")
	
		row.append($("<td>" + rowData.TestId+ "</td>"));
		row.append($("<td >" + rowData.TestName+ "</td>"));
		
		console.log("temp "+temp);
		console.log("testId "+rowData.MainTestId);
		
		if(temp=='0'){
			row.append($("<td><input type='hidden' class='form-control testId-"+rowData.TestId+"' value='"+rowData.MainTestId+"'/><input id='' type='text' class='form-control result_value-"+rowData.TestId+"'></td>"));
		}
		else if(temp=='1'){
			row.append($("<td><input type='hidden' style='background:#8FBC8F;' class='form-control testId-"+rowData.TestId+"' value='"+rowData.MainTestId+"'/><input id='' type='text' class='form-control result_value-"+rowData.TestId+"' value='"+rowData.Result+"'></td>"));
		}
	
		row.append($("<td></td>"));
		row.append($("<td></td>"));
		row.append($("<td class='biotest'>" + rowData.NormalRange + "</td>"));
		row.append($("<td>" + rowData.Unit + "</td>"));
		if(temp=='0'){
			row.append($("<td style='width:60px;'><input  type='text' style='width:60px;background:#8FBC8F;' class='form-control sort_value-"+rowData.TestId+"''></td>"));
		}
		else if(temp=='1'){
			row.append($("<td style='width:60px;'><input  type='text' style='width:60px;background:#8FBC8F;' class='form-control sort_value-"+rowData.TestId+"' value='"+rowData.Sorting+"''></td>"));
		}
		
		row.append($("<td>" + rowData.MainTestName + "</td>"));
		
		
		console.log("rowData.inchargeId "+rowData.inchargeId);
		console.log("rowData.doctor1 "+rowData.doctor1);
		console.log("rowData.doctor1 "+rowData.doctor1);
		
		if(rowData.inchargeId!=null || rowData.inchargeId!=''){
			$('#inchargeId').val(rowData.inchargeId);
		    $('#inchargeId').selectpicker('refresh');
		}
		
		if(rowData.doctor1!=null || rowData.doctor1!=''){
			$('#doctor1').val(rowData.doctor1);
			$('#doctor1').selectpicker('refresh');
		}
		
		if(rowData.doctor2!=null || rowData.doctor2!=''){
			$('#doctor2').val(rowData.doctor2);
		    $('#doctor2').selectpicker('refresh');
		}
		

		
	    if(rowData.machineId!=null || rowData.machineId!=''){
	    	$('#machineId').val(rowData.machineId);
	        $('#machineId').selectpicker('refresh');
	    }
	    
	    if(rowData.titleId!=null || rowData.titleId!=''){
			$('#titleId').val(rowData.titleId);
		    $('#titleId').selectpicker('refresh');
	    }
		

		return row;

	
}


function setLabBillData(labId,fiscalYear,Cmonth){
	
	console.log(" bill mnth "+Cmonth)
	//Cmonth=Cmonth;
	$('#cmonth').val(Cmonth)
	console.log(" a month "+Cmonth)
	
	sameTest=0;
	tempTest=0;
	$('#labBillList').modal('hide');

	$.ajax({
		
		type:'POST',
		dataType:'json',
		url:'./LabBillWiseTestDetails/',
		data:{
			labId:labId,
			fiscalYear:fiscalYear,
			Cmonth:Cmonth
			
		},
		success: function (data) {
			
			console.log("data"+data.result);
	        $("#labbill_invetable").empty();
	        $('#sLabId').val(labId);
	      	drawLabBillTable(data.result);

		}
		
		
	});
}


function drawLabBillTable(data) {
    var rows = [];
    
    for (var i = 0; i < data.length; i++) {
        rows.push(drawRowLabBillTable(data[i],i+1));
    }

    $("#labbill_invetable").append(rows);
}

function drawRowLabBillTable(rowData,c) {

	var row = $("<tr class='testrow' dataid='"+c+"' />")
	
	row.append($("<td>" + c+ "</td>"));
	row.append($("<td>" + rowData.testname+ "</td>"));
	row.append($("<td>" + rowData.headName + "</td>"));
	row.append($("<td><input type='hidden' id='"+rowData.headId+"-testname' value='"+rowData.testname+"' class='selecttestname' /><input type='hidden' value='"+rowData.testId+"' class='selecttest testId-"+c+"' /><input class='checkdepartment' type='checkbox' data-id='"+rowData.headId+"' id='checkdepartment' value='"+ rowData.testId+"'></td>"));
	row.append($("<td>" + rowData.resultstatus + "</td>"));


		$("#patientname").val(rowData.patientname);
		$("#mobile").val(rowData.mobile);
		$("#age").val(rowData.age);
		$("#month").val(rowData.month);
		$("#day").val(rowData.day);
		$("#sex").val(rowData.sex);
		$("#address").val(rowData.address);
		$("#referral_search").val(rowData.referral_search);
		$("#referraldegree").val(rowData.referraldegree);

		$("#labbill").val(rowData.labId);
		$("#labfiscalyear").val(rowData.fiscalyear);

	
	
	return row;
}

//Barcode.............


function LabNoBlank(){
	$("#labfind").val('');
	
}

function findLabNo(){

	//findLabNo
	var value = $("#labfind").val();
	
	var labId="";
	var fiscalYear ="";
	var tempValue=value.slice(0,1);
	
	
	if(tempValue=='0'){
		 fiscalYear=value.slice(1,5);
		 labId=value.slice(7);	
	}
	else if(tempValue!='0'){
		//var value1 = $("#labfind").val();
		
		fiscalYear=value.slice(0,4);
		labId=value.slice(6);
	}
	
	
	
	sameTest=0;
	tempTest=0;
	$('#labBillList').modal('hide');

	$.ajax({
		
		type:'POST',
		dataType:'json',
		url:'./LabBillWiseTestDetails/',
		data:{
			labId:labId,
			fiscalYear:fiscalYear
		},
		success: function (data) {
			
			console.log("data"+data.result);
	        $("#labbill_invetable").empty();
	      	drawLabBillTable(data.result);

		}
		
		
	});
}

let inputStart, inputStop;

$("#labfind")[0].onpaste = e => e.preventDefault();
// handle a key value being entered by either keyboard or scanner
let lastInput;

let checkValidity = () => {
    if ($("#labfind").val().length < 4) {
        alert("Scan Barcode Only Or Please Uncheck The Barcode Permission.");
        $("#labfind").val('');
    }else{
    	findLabNo();
    }
    timeout = false
}

let timeout = false;
$("#labfind").keypress(function (e) {
    if (performance.now() - lastInput > 1000) {
        $("#labfind").val('')
    }
    lastInput = performance.now();
    if (!timeout) {
        timeout = setTimeout(checkValidity, 200)

    }
});


function setTotalDC(){
	var neutrophlis=parseFloat($('#neutrophlis').val()==''?"0":$('#neutrophlis').val());
	var lymphocytes=parseFloat($('#lymphocytes').val()==''?"0":$('#lymphocytes').val());
	var monocytes=parseFloat($('#monocytes').val()==''?"0":$('#monocytes').val());
	var eosinophils=parseFloat($('#eosinophils').val()==''?"0":$('#eosinophils').val());
	var basophils=parseFloat($('#basophils').val()==''?"0":$('#basophils').val());
	var other=parseFloat($('#other').val()==''?"0":$('#other').val());
	
	var total=neutrophlis+lymphocytes+monocytes+eosinophils+basophils+other;
	console.log("total "+total);
	$('#totaldc').val(total);
}

/*----Key Action--Heamatology*/
document.querySelector('#hgb').addEventListener('keypress', function (e) {
    if (e.key === 'Enter') {
    	document.getElementById('esr').focus();
    }
});


document.querySelector('#esr').addEventListener('keypress', function (e) {
    if (e.key === 'Enter') {
    	document.getElementById('totalCount').focus();
    }
});

document.querySelector('#totalCount').addEventListener('keypress', function (e) {
    if (e.key === 'Enter') {
    	document.getElementById('neutrophlis').focus();
    }
});


document.querySelector('#neutrophlis').addEventListener('keypress', function (e) {
    if (e.key === 'Enter') {
    	document.getElementById('lymphocytes').focus();
    }
});

document.querySelector('#lymphocytes').addEventListener('keypress', function (e) {
    if (e.key === 'Enter') {
    	document.getElementById('monocytes').focus();
    }
});


document.querySelector('#monocytes').addEventListener('keypress', function (e) {
    if (e.key === 'Enter') {
    	document.getElementById('eosinophils').focus();
    }
});

document.querySelector('#eosinophils').addEventListener('keypress', function (e) {
    if (e.key === 'Enter') {
    	document.getElementById('basophils').focus();
    }
});

document.querySelector('#basophils').addEventListener('keypress', function (e) {
    if (e.key === 'Enter') {
    	document.getElementById('other').focus();
    }
});

document.querySelector('#other').addEventListener('keypress', function (e) {
    if (e.key === 'Enter') {
    	document.getElementById('plt').focus();
    }
});

document.querySelector('#plt').addEventListener('keypress', function (e) {
    if (e.key === 'Enter') {
    	document.getElementById('mpv').focus();
    }
});

document.querySelector('#mpv').addEventListener('keypress', function (e) {
    if (e.key === 'Enter') {
    	document.getElementById('pdw').focus();
    }
});

document.querySelector('#pdw').addEventListener('keypress', function (e) {
    if (e.key === 'Enter') {
    	document.getElementById('pct').focus();
    }
});

document.querySelector('#pct').addEventListener('keypress', function (e) {
    if (e.key === 'Enter') {
    	document.getElementById('rbc').focus();
    }
});

document.querySelector('#rbc').addEventListener('keypress', function (e) {
    if (e.key === 'Enter') {
    	document.getElementById('pcv').focus();
    }
});

document.querySelector('#pcv').addEventListener('keypress', function (e) {
    if (e.key === 'Enter') {
    	document.getElementById('mcv').focus();
    }
});

document.querySelector('#mcv').addEventListener('keypress', function (e) {
    if (e.key === 'Enter') {
    	document.getElementById('mch').focus();
    }
});

document.querySelector('#mch').addEventListener('keypress', function (e) {
    if (e.key === 'Enter') {
    	document.getElementById('mchc').focus();
    }
});

document.querySelector('#mchc').addEventListener('keypress', function (e) {
    if (e.key === 'Enter') {
    	document.getElementById('cec').focus();
    }
});

document.querySelector('#cec').addEventListener('keypress', function (e) {
    if (e.key === 'Enter') {
    	document.getElementById('min').focus();
    }
});

document.querySelector('#min').addEventListener('keypress', function (e) {
    if (e.key === 'Enter') {
    	document.getElementById('ctmin').focus();
    }
});


document.querySelector('#ctmin').addEventListener('keypress', function (e) {
    if (e.key === 'Enter') {
    	document.getElementById('mp').focus();
    }
});

document.querySelector('#mp').addEventListener('keypress', function (e) {
    if (e.key === 'Enter') {
    	document.getElementById('mpc').focus();
    }
});


/*----Key Action--Urine*/
$("#quantity").keypress(function (e) {
	  if (e.key === 'Enter') {
	    	e.preventDefault();
	    	document.getElementById('color').focus();
	    }
});
$("#color").keypress(function (e) {
	  if (e.key === 'Enter') {
	    	e.preventDefault();
	    	document.getElementById('appearance').focus();
	    }
});


document.querySelector('#appearance').addEventListener('keypress', function (e) {
    if (e.key === 'Enter') {
    	document.getElementById('sediment').focus();
    }
});
document.querySelector('#sediment').addEventListener('keypress', function (e) {
    if (e.key === 'Enter') {
    	document.getElementById('hyalinecash').focus();
    }
});
document.querySelector('#hyalinecash').addEventListener('keypress', function (e) {
    if (e.key === 'Enter') {
    	document.getElementById('granularcells').focus();
    }
});
document.querySelector('#granularcells').addEventListener('keypress', function (e) {
    if (e.key === 'Enter') {
    	document.getElementById('fatty').focus();
    }
});

document.querySelector('#fatty').addEventListener('keypress', function (e) {
    if (e.key === 'Enter') {
    	document.getElementById('wbccasts').focus();
    }
});
document.querySelector('#wbccasts').addEventListener('keypress', function (e) {
    if (e.key === 'Enter') {
    	document.getElementById('rbccasts').focus();
    }
});
document.querySelector('#rbccasts').addEventListener('keypress', function (e) {
    if (e.key === 'Enter') {
    	document.getElementById('gravity').focus();
    }
});
document.querySelector('#gravity').addEventListener('keypress', function (e) {
    if (e.key === 'Enter') {
    	document.getElementById('ph').focus();
    }
});
document.querySelector('#ph').addEventListener('keypress', function (e) {
    if (e.key === 'Enter') {
    	document.getElementById('sugar').focus();
    }
});
document.querySelector('#sugar').addEventListener('keypress', function (e) {
    if (e.key === 'Enter') {
    	document.getElementById('albumin').focus();
    }
});
document.querySelector('#albumin').addEventListener('keypress', function (e) {
    if (e.key === 'Enter') {
    	document.getElementById('ketonebodies').focus();
    }
});
document.querySelector('#ketonebodies').addEventListener('keypress', function (e) {
    if (e.key === 'Enter') {
    	document.getElementById('urineBlood').focus();
    }
});
document.querySelector('#urineBlood').addEventListener('keypress', function (e) {
    if (e.key === 'Enter') {
    	document.getElementById('bilirubin').focus();
    }
});
document.querySelector('#bilirubin').addEventListener('keypress', function (e) {
    if (e.key === 'Enter') {
    	document.getElementById('uribiliogen').focus();
    }
});
document.querySelector('#uribiliogen').addEventListener('keypress', function (e) {
    if (e.key === 'Enter') {
    	document.getElementById('nitrites').focus();
    }
});
document.querySelector('#nitrites').addEventListener('keypress', function (e) {
    if (e.key === 'Enter') {
    	document.getElementById('bilepigment').focus();
    }
});
document.querySelector('#bilepigment').addEventListener('keypress', function (e) {
    if (e.key === 'Enter') {
    	document.getElementById('bilesalth').focus();
    }
});
document.querySelector('#bilesalth').addEventListener('keypress', function (e) {
    if (e.key === 'Enter') {
    	document.getElementById('phosphate').focus();
    }
});
document.querySelector('#phosphate').addEventListener('keypress', function (e) {
    if (e.key === 'Enter') {
    	document.getElementById('urinRbc').focus();
    }
});
document.querySelector('#urinRbc').addEventListener('keypress', function (e) {
    if (e.key === 'Enter') {
    	document.getElementById('puscells').focus();
    }
});
document.querySelector('#puscells').addEventListener('keypress', function (e) {
    if (e.key === 'Enter') {
    	document.getElementById('epithelialcells').focus();
    }
});
document.querySelector('#epithelialcells').addEventListener('keypress', function (e) {
    if (e.key === 'Enter') {
    	document.getElementById('trichomonasVaginalis').focus();
    }
});
document.querySelector('#trichomonasVaginalis').addEventListener('keypress', function (e) {
    if (e.key === 'Enter') {
    	document.getElementById('supermatozoa').focus();
    }
});
document.querySelector('#supermatozoa').addEventListener('keypress', function (e) {
    if (e.key === 'Enter') {
    	document.getElementById('microorganism').focus();
    }
});
document.querySelector('#microorganism').addEventListener('keypress', function (e) {
    if (e.key === 'Enter') {
    	document.getElementById('fungus').focus();
    }
});
document.querySelector('#fungus').addEventListener('keypress', function (e) {
    if (e.key === 'Enter') {
    	document.getElementById('calciumoxalate').focus();
    }
});
document.querySelector('#calciumoxalate').addEventListener('keypress', function (e) {
    if (e.key === 'Enter') {
    	document.getElementById('uricacidcrystals').focus();
    }
});
document.querySelector('#uricacidcrystals').addEventListener('keypress', function (e) {
    if (e.key === 'Enter') {
    	document.getElementById('triplephosphate').focus();
    }
});
document.querySelector('#triplephosphate').addEventListener('keypress', function (e) {
    if (e.key === 'Enter') {
    	document.getElementById('amorphphosphate').focus();
    }
});
document.querySelector('#amorphphosphate').addEventListener('keypress', function (e) {
    if (e.key === 'Enter') {
    	document.getElementById('urate').focus();
    }
});


let idList = ["consistency","stoolColor","odour","stoolMucus","stoolBlood","helminths","reaction","reducingSubstance","occultbBoodTest","protozoa","cystseh",
	"cystsColt","cystsGiardia","ovaoFroundWorm","ovaOfHookWorm","ovaOfWhipWorm","larvaOfSs","macrophage","strach","vagetiableCells","stoolPuscells","eithelial",
	"fatDroplets","fungi","stoolWbc","stoolRbc","muscleFibers","charotLeyden"];
idList.forEach((id,index)=>{
  console.log("execute",id);
  $('#'+id).keyup(function(event){
    console.log("execute",event.keyCode);
    if(event.keyCode ===13){
      event.preventDefault();
      $("#"+idList[index+1]).focus();
    }
    
  });
})


let idListMicro = ["imipenem","amoxycillin_a","cefepime","chloramphenicol","ceftrixon","streptomycin","nitrofurantoin","gentamycin","cepradine","doxycycline","netilimyin","azithromycin",
	"penicillin","meropenem","coTrimoxazole","cefixime","cefixime","ceftazidime","nalidiximeAcid","erythromycin","ceftaxime","cephalexin","ciprofloxacine","amicacin","levofloxacin"];
idListMicro.forEach((id,index)=>{
  console.log("execute",id);
  $('#'+id).keyup(function(event){
    console.log("execute",event.keyCode);
    if(event.keyCode ===13){
      event.preventDefault();
      $("#"+idListMicro[index+1]).focus();
    }
    
  });
})

/*let idListMicroNew = ["amikacin_a","amikacin_b","amoxycillin_a","amoxycillin_b","amoxyclave_a","amoxyclave_b","azithromycin_a","azithromycin_b","aztreonam_a","aztreonam_b","cefaclot_a",
	"cefaclot_b","cefapime_a","cefapime_b","cefixime_a","cefixime_b","cefotaxime_a","cefotaxime_b","cefoxitin_a","cefoxitin_b","ceftazidime_a","ceftazidime_b",
	"ceftriaxone_a","ceftriaxone_b","cefuroxime_a","cefuroxime_b","cephradine_a","cephradine_b","chloramphenicol_a","chloramphenicol_b","cloxacillin_a","cloxacillin_b",
	"ciprofloxacin_a","ciprofloxacin_b","colistin_a","colistin_b","cotrimoxazole_a","cotrimoxazole_b","doxycycline_a","doxycycline_b","erythromycin_a","erythromycin_b",
	"gentamincin_a","gentamincin_b","imipenem_a","imipenem_b","levofloxacin_a","levofloxacin_b","linezolid_a","linezolid_b","mecillinam_a","mecillinam_b","meropenem_a",
	"meropenem_b","nalidixic_acid_a","nalidixic_acid_b","neomycin_a","neomycin_b","netilmicin_a","netilmicin_b","nitrofurantoiin_a","nitrofurantoiin_b","penicillin_a",
	"penicillin_b","piperacillin_a","piperacillin_b","tazobactum_a","tazobactum_b","polymyxin_a","polymyxin_b","tetracycline_a","tetracycline_b","vancomcycin_a",
	"vancomcycin_b","organism_a","organism_b","micro-cut-value","micro-patient-sample","micro-count-impression","micro-puscells","micro-ephithelles","micro-result"];
idListMicroNew.forEach((id,index)=>{
  console.log("execute",id);
  $('#'+id).keyup(function(event){
    console.log("execute",event.keyCode);
    if(event.keyCode ===13){
      event.preventDefault();
      $("#"+idListMicroNew[index+1]).focus();
    }
    
  });
})*/


let idListMicroNew = ["amoxycillinClavulanAcid","gentamycin","azithromycin","ciprofloxacin","meropenem","ceftriaxone","amikacin","piperacillinTazobactam","tigecycline","ertapenem",
	"levofloxacin","imipenem","moxafloxacin","nalidixicAcid","cotrimexazole","cefuroxime","ceftazidime","nitrofurantoin","vancomycin","cefixime"];
idListMicroNew.forEach((id,index)=>{
  console.log("execute",id);
  $('#'+id).keyup(function(event){
    console.log("execute",event.keyCode);
    if(event.keyCode ===13){
      event.preventDefault();
      $("#"+idListMicroNew[index+1]).focus();
    }
    
  });
})

$(document).ready(function () {
	$("input:text").focus(function () { $(this).select(); });
});

$(document).ready(function () {
	$("#search").on("keyup", function () {
		var value = $(this).val().toLowerCase();
		$("#labbill_table tr").filter(function () {
			$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
		});
	});
});