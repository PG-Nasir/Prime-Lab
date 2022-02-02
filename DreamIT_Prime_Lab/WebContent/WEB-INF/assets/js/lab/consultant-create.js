var id;

window.onload = () => {
	
	allConsultantName();
	
	$.ajax({
		type: 'POST',
		dataType: 'json',
		url: './getMaxConsultantId',
		data: {
			
		},
		success: function (data) {
			$('#id').val(data);
		}
	});
	
}




function saveAction() {

	var id = $("#id").val();
	var consultantName = $("#consultantName").val();
	var designation = $("#designation").val();
	var line1 = $("#line1").val();
	var line2 = $("#line2").val();
	var line3 = $("#line3").val();
	var line4 = $("#line4").val();
	var line5 = $("#line5").val();
	var userId = $("#userId").val();


	if (consultantName !='') {
		$.ajax({
			type: 'POST',
			dataType: 'json',
			url: './saveConsultant',
			data: {
				
				id:id,
				consultantName: consultantName,
				designation: designation,
				line1: line1,
				line2:line2,
				line3: line3,
				line4:line4,
				line5:line5,
				userId:userId,
				
			},
			success: function (data) {
				console.log("data "+data);
				if (data == "Something Wrong") {
					dangerAlert("Something went wrong");
				} else if (data == "duplicate") {
					dangerAlert("Duplicate Consultant..This Consultant Consultant Allreary Exist")
				} else {
					///successAlert("Consultant Save Successfully");
					//refreshAction();
					alert("Cosultant Name Create Successfully!!");
					location.reload();
				}
			}
		});
	} else {
		warningAlert("Empty Consultant ... Please Enter Consultant");
	}
}


function editAction() {
	
	var id = $("#id").val();
	var consultantName = $("#consultantName").val();
	var designation = $("#designation").val();
	var line1 = $("#line1").val();
	var line2 = $("#line2").val();
	var line3 = $("#line3").val();
	var line4 = $("#line4").val();
	var line5 = $("#line5").val();
	var userId = $("#userId").val();

	if (consultantName !='') {
		$.ajax({
			type: 'POST',
			dataType: 'json',
			url: './editConsultant',
			data: {
				
				id:id,
				consultantName: consultantName,
				designation: designation,
				line1: line1,
				line2:line2,
				line3: line3,
				line4:line4,
				line5:line5,
				userId:userId,
				
			},
			success: function (data) {
				if (data == "Something Wrong") {
					dangerAlert("Something went wrong");
				} else if (data == "duplicate") {
					dangerAlert("Duplicate Seat..This Consultant Name Allreary Exist")
				} else {
					alert("Cosultant Name Update Successfully!!");
					location.reload();
				}
			}
		});
	} else {
		warningAlert("Empty Consultant... Please Enter Consultant");
	}
}


function refreshAction() {
	location.reload();
}

function allConsultantName(){
	$.ajax({
		type: 'POST',
		dataType: 'json',
		url: './allConsultantName',
		data: {
			
		},
		success: function (data) {
			$("#dataList").empty();
			patchdata(data);
		}
	});
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
	row.append($("<td id='id-"+rowData.id+"'>" + rowData.id+ "</td>"));
	row.append($("<td id='name-"+rowData.consultantName+"'>" + rowData.consultantName+ "</td>"));
	row.append($("<td class='text-center'><i class='fa fa-edit' onclick=setData('"+encodeURIComponent(rowData.id)+"','"+encodeURIComponent(rowData.consultantName)+"','"+encodeURIComponent(rowData.designation)+"','"+encodeURIComponent(rowData.line1)+"','"+encodeURIComponent(rowData.line2)+"','"+encodeURIComponent(rowData.line3)+"','"+encodeURIComponent(rowData.line4)+"','"+encodeURIComponent(rowData.line5)+"')> </i></td>"));

	return row;
}

function setData(id,consultantName,designation,line1,line2,line3,line4,line5) {

	var id=decodeURIComponent(id);
	var consultantName=decodeURIComponent(consultantName);
	var designation=decodeURIComponent(designation);
	var line1=decodeURIComponent(line1);
	var line2=decodeURIComponent(line2);
	var line3=decodeURIComponent(line3);
	var line4=decodeURIComponent(line4);
	var line5=decodeURIComponent(line5);
	
	$("#id").val(id);
	$("#consultantName").val(consultantName);
	$("#designation").val(designation);
	$("#line1").val(line1);
	$("#line2").val(line2);
	$("#line3").val(line3);
	$("#line4").val(line4);
	$("#line5").val(line5);
	
	document.getElementById("btnSave").disabled = true;
	document.getElementById("btnEdit").disabled = false;

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

