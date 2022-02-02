var id;

function btnPrintEvent(){
	alert("This report will be comming soon....");
	/*var url = `printDoctorList`;
	window.open(url, '_blank');*/
}

function saveAction() {

	
	var memberName = $("#memberName").val();
	var occupation = $("#occupation").val();
	var contact = $("#contact").val();
	var membershipType = $("#membershipType").val();
	var userId = $("#userId").val();


	if (memberName != '' & contact != '') {



		$.ajax({
			type: 'POST',
			dataType: 'json',
			url: './saveMember',
			data: {
				doctorId:"0",
				memberName: memberName,
				occupation: occupation,
				contact: contact,
				membershipType:membershipType,
				userId:userId,

			},
			success: function (data) {
				if (data.result == "Something Wrong") {
					dangerAlert("Something went wrong");
				} else if (data.result == "duplicate") {
					alert("Duplicate Member..This Member Name Allreary Exist");
					dangerAlert("Duplicate Member..This Member Name Allreary Exist")
				} else {
					alert("Member Save Successfully");
					successAlert("Member Save Successfully");

					$("#dataList").empty();
					patchdata(data.result);
				}
			}
		});
	} else {
		alert("Empty Information ... Please Enter Information");
	}
}


function editAction() {
	var memberId = $("#memberId").val();
	var memberName = $("#memberName").val();
	var occupation = $("#occupation").val();
	var contact = $("#contact").val();
	var membershipType = $("#membershipType").val();
	var userId = $("#userId").val();

	

	if (memberName != '') {
		
		
		$.ajax({
			type: 'POST',
			dataType: 'json',
			url: './editMember',
			data: {
				memberId:memberId,
				memberName: memberName,
				occupation: occupation,
				contact: contact,
				membershipType:membershipType,
				userId:userId,
			},
			success: function (data) {
				if (data.result == "Something Wrong") {
					dangerAlert("Something went wrong");
				} else if (data.result == "duplicate") {
					dangerAlert("Duplicate Information..This Name Allreary Exist")
				} else {
					successAlert("Information Edit Successfully");
					$("#dataList").empty();
					patchdata(data.result);
				}
			}
		});
	} else {
		warningAlert("Empty Information... Please Enter Information");
	}
}


function refreshAction() {
	location.reload();
}



function patchdata(data){
	var rows = [];

	for (var i = 0; i < data.length; i++) {
		rows.push(drawRow(data[i],i+1));

	}

	$("#dataList").append(rows);
}

function drawRow(rowData,c) {

	var row = $("<tr />")
	row.append($("<td>" + c + "</td>"));
	row.append($("<td >" + rowData.memberName+ "</td>"));
	row.append($("<td >" + rowData.membershipType+ "</td>"));
	row.append($("<td ><i class='fa fa-edit' onclick=setData('"+rowData.memberId+"')> </i></td>"));

	return row;
}

function setData(memberId) {


	$.ajax({
		type: 'GET',
		dataType: 'json',
		url: './getMemberDetails/'+memberId,
		data: {
			memberId:memberId
		},
		success: function (data) {
			var item=data.result;
			$("#memberName").val(item[0].memberName);
			$("#occupation").val(item[0].occupation);
			$("#membershipType").val(item[0].membershipType);
			$("#memberId").val(item[0].memberId);
			$("#contact").val(item[0].contact);
		}
	});


	document.getElementById("btnSave").disabled = true;
	document.getElementById("btnEdit").disabled = false;

}

function setDoctorTable(dataList,doctorCommisionList){

	for (var i = 0; i < dataList.length; i++) {
		var item = dataList[i];
		$('#doctorId').val(item.doctorId);
		$('#doctorName').val(item.doctorName);
		$('#doctorCode').val(item.doctorCode);
		$('#degree').val(item.degree);
		$('#doctorCategory').val(item.doctorCategory);
		$('#departmentId').val(item.departmentId);
		$('#address').val(item.address);
		$('#religion').val(item.religion);
		$('#sex').val(item.sex);
		$('#mobile').val(item.mobile);
		$('#visitFee').val(parseFloat(item.visitFee).toFixed(2));
		$('#roomNo').val(item.roomNo);
	}

	var tableValue="";
	$('#doctorCommissionTable').empty();
	for (var i = 0; i < doctorCommisionList.length; i++) {
		var rowData = doctorCommisionList[i];
		
		tableValue=tableValue+"<tr class='lab-group-row row-height-30' data-id='"+rowData.groupId+"'>"+
		"<td width='240px;'>"+rowData.groupName+"<input type='hidden' id='groupName"+rowData.groupId+"' class='form-control-sm' value="+rowData.groupName+" /></td>"+
		"<td width='80px;'><input type='text' id='doctorComission"+rowData.groupId+"' class='form-control-sm' value="+rowData.doctorComission+" /><input type='hidden' id='parentId"+rowData.groupId+"' class='form-control-sm' value="+rowData.parentId+" /></td>"+
		"<td width='80px;'><input type='text' id='doctorComissionDeduction"+rowData.groupId+"' class='form-control-sm' value="+rowData.doctorComissionDeduction+" /><input type='hidden' id='parentId"+rowData.groupId+"' class='form-control-sm' value="+rowData.parentId+" /></td>";
		if(rowData.doctorComissionDeduction!='0'){
			console.log("checked");
			tableValue=tableValue+"<td width='60px;'><input class='check' type='checkbox' id='req-"+rowData.groupId+"' checked /></td></tr>";
		}
		else{
			tableValue=tableValue+"<td width='60px;'><input class='check'  type='checkbox' id='req-"+rowData.groupId+"' /></td></tr>";
		}
		
	}
	
	$('#doctorCommissionTable').append(tableValue);
	

}

function successAlert(message) {
	var element = $(".alert");
	element.hide();
	element = $(".alert-success");
	document.getElementById("successAlert").innerHTML = "<strong>Success!</strong> " + message + "...";
	element.show();
	setTimeout(() => {
		element.toggle('fade');
	}, 2500);
}

function warningAlert(message) {
	var element = $(".alert");
	element.hide();
	element = $(".alert-warning");
	document.getElementById("warningAlert").innerHTML = "<strong>Warning!</strong> "+message+"..";
	element.show();
	setTimeout(() => {
		element.toggle('fade');
	}, 2500);
}

function dangerAlert(message) {
	var element = $(".alert");
	element.hide();
	element = $(".alert-danger");
	document.getElementById("dangerAlert").innerHTML = "<strong>Duplicate!</strong> "+message+"..";
	element.show();
	setTimeout(() => {
		element.toggle('fade');
	}, 2500);
}

function setCheck(){
	var checkvalue = $("#allCheck").is(':checked') ? 'checked' : 'unchecked';

	if (checkvalue=='checked') {
		$(".check").prop('checked', true);
	} else {
		$(".check").prop('checked', false);
	}
}

$(document).ready(function () {
	$("input:text").focus(function () { $(this).select(); });
});

$(document).ready(function () {
	$("#search").on("keyup", function () {
		var value = $(this).val().toLowerCase();
		$("#dataList tr").filter(function () {
			$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
		});
	});
});


document.querySelector('#doctorName').addEventListener('keypress', function (e) {
    if (e.key === 'Enter') {
    	document.getElementById('degree').focus();
    }
});

document.querySelector('#degree').addEventListener('keypress', function (e) {
    if (e.key === 'Enter') {
    	document.getElementById('doctorCategory').focus();
    }
});



$(document).on('keypress', 'select', function (e) {
    if (e.which == 13) {
        e.preventDefault();
        // Get all focusable elements on the page
        var $canfocus = $(':focusable');
        var index = $canfocus.index(document.activeElement) + 1;
        if (index >= $canfocus.length) index = 0;
        $canfocus.eq(index).focus();
    }
});

document.querySelector('#address').addEventListener('keypress', function (e) {
    if (e.key === 'Enter') {
    	document.getElementById('religion').focus();
    }
});

document.querySelector('#mobile').addEventListener('keypress', function (e) {
    if (e.key === 'Enter') {
    	document.getElementById('visitFee').focus();
    }
});

document.querySelector('#visitFee').addEventListener('keypress', function (e) {
    if (e.key === 'Enter') {
    	document.getElementById('roomNo').focus();
    }
});