var sameTest=0;
var tempDep=0;
var tempTest=0;
var resultlist=[];

function PrintBercodeLab(){
	
	var labbill=$("#labNo").val();
	var fiscalyear=$("#labfiscalyear").val();
	
	var url = `printBercodeLab/${labbill}@${fiscalyear}`;
	window.open(url, '_blank');
	
/*	var win=window.open(url,"", "width=200,height=100");
	setTimeout(() => {
		win.close();
	}, 1000);
	*/
}


function setLabBillData(labId,fiscalYear){
	
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
			
	        $("#labbill_table").empty();
	        drawLabBillTable(data.result);

		}
		
		
	});
}


function drawLabBillTable(data) {
    var rows = [];
    
    for (var i = 0; i < data.length; i++) {
        rows.push(drawRowLabBillTable(data[i],i+1));
    }

    $("#labbill_table").append(rows);
}

function drawRowLabBillTable(rowData,c) {

		var row = $("<tr class='itemrow' data-id='"+c+"'/>")
		
		row.append($("<td> <input type='hidden' value='"+1+"' id='autoId-"+rowData.autoId+"' /> " + c+ "</td>"));
		row.append($("<td>" + rowData.headName+ "</td>"));
		row.append($("<td>" + rowData.testname+ "</td>"));
		row.append($("<td><input class='form-check-input approve' type='checkbox' id='test-"+rowData.c+"' value='"+rowData.testId+"'></td>"));
		
		$("#percentdiscount").val(rowData.percentdiscount);
		$("#manualdiscount").val(rowData.TotalMDiscounTaka);
		
		$("#totalamount").val(rowData.TotalAmount);
		$("#perdiscount_taka").val(rowData.TotalPrDiscounTaka);
		$("#mdiscount_tata").val(rowData.TotalMDiscounTaka);
		$("#totalpayable").val(rowData.TotalPayableTaka);
		
		$("#patientname").val(rowData.patientname);
		$("#mobile").val(rowData.mobile);
		$("#age").val(rowData.age);
		$("#month").val(rowData.month);
		$("#day").val(rowData.day);
		$("#sex").val(rowData.sex);

		$("#labfiscalyear").val(rowData.fiscalyear);
		

		$("#labNo").val(rowData.labId);
		
		
		return row;
}

document.getElementById("checkAll").addEventListener("click", function () {
    if ($(this).prop('checked')) {
        $(".approve").prop('checked', true);
    } else {
        $(".approve").prop('checked', false);
    }

});

