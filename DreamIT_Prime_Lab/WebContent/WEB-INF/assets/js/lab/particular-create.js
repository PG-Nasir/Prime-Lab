window.onload = () => {
	TestWiseParticularList();
	var modueMenu=$('#modueMenu').val();
	checkMenuId(modueMenu);
}

function checkMenuId(modueMenu){

	var module=modueMenu.slice(0,8);
	console.log("module "+module);
	
	var togglerModule = document.getElementsByClassName(module);
	
	togglerModule[0].parentElement.querySelector(".nested").classList.toggle("active");
	togglerModule[0].classList.toggle("caret-down");
	
	
	$(".caret1").css({"background-color": "yellow", "font-size": "40px;","color":"#17a2b8",
	"font-family":"fontawesome",
	"content": "\f146","display": "inline-block"});
	console.log("modueMenu "+modueMenu);
	var toggler = document.getElementsByClassName(modueMenu);
	
	console.log("length "+toggler.length);
	
	var i=0;

	for (i = 0; i < toggler.length; i++) {
		
		toggler[i].parentElement.querySelector(".nested").classList
		.toggle("active");
		toggler[i].classList.toggle("caret-down");

	}

}

function saveAction() {

	var testId = $("#testId").val();
	var particularRefId = $("#particularRefId").val();
	var rate = $("#rate").val();
	var qty = $("#qty").val();
	var userId = $("#userId").val();

	if(testId !="0" && particularId !="0"){
		$.ajax({
			type: 'POST',
			dataType: 'json',
			url: './addTestParticular',
			data: {
				particularId:"0",
				testId: testId,
				particularRefId:particularRefId,
				rate:rate,
				qty:qty,
				userId:userId

			},
			success: function (data) {
				if (data.result == "Something Wrong") {
					dangerAlert("Something went wrong");
				} else if (data.result == "duplicate") {
					alert("Duplicate Test Particular..This Test Particular Name Allreary Exist");
					dangerAlert("Duplicate Test Particular..This Test Particular Name Allreary Exist")
				} else {
					alert("Test Particular Save Successfully");
					successAlert("Test Particular Save Successfully");

					$("#dataList").empty();
					TestWiseParticularList();
				}
			}
		});
	} else {
		warningAlert("Empty Test Particular Name or Particular ... Please Enter...");
	}
}

function editAction() {

	var particularId = $("#particularId").val();
	var testId = $("#testId").val();
	var particularRefId = $("#particularRefId").val();
	var rate = $("#rate").val();
	var qty = $("#qty").val();
	var userId = $("#userId").val();

	if(testId !="0" && particularRefId !="0"){
		$.ajax({
			type: 'POST',
			dataType: 'json',
			url: './editTestParticular',
			data: {

				testId: testId,
				particularId:particularId,
				particularRefId:particularRefId,
				rate:rate,
				qty:qty,
				userId:userId

			},
			success: function (data) {
				if (data.result == "Something Wrong") {
					dangerAlert("Something went wrong");
				} else if (data.result == "duplicate") {
					alert("Duplicate Test Particular..This Test Particular Name Allreary Exist");
					dangerAlert("Duplicate est Particular..This Test Particular Allreary Exist")
				} else {
					alert("Test Particular update Successfully");
					successAlert("Test Particular update Successfully");

					$("#dataList").empty();
					TestWiseParticularList();
				}
			}
		});
	} else {
		warningAlert("Empty Test Particular Name ... Please Enter Test Particular Name");
	}
}

function TestWiseParticularList(){
	$.ajax({
		type: 'POST',
		dataType: 'json',
		url: './TestWiseParticularList',
		data: {

		},
		success: function (data) {
			patchdata(data.result);
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
	row.append($("<td>" + c + "</td>"));
	row.append($("<td >" + rowData.testName+ "</td>"));
	row.append($("<td >" + rowData.particularMainName+ "</td>"));
	row.append($("<td >" + rowData.qty+ "</td>"));
	row.append($("<td >" + parseFloat(rowData.rate).toFixed(2)+ "</td>"));
	row.append($("<td ><i class='fa fa-edit' onclick=setData('"+encodeURIComponent(rowData.particularId)+"','"+encodeURIComponent(rowData.particularRefId)+"','"+encodeURIComponent(rowData.testId)+"','"+encodeURIComponent(rowData.testName)+"','"+encodeURIComponent(rowData.rate)+"','"+encodeURIComponent(rowData.qty)+"')> </i></td>"));
	row.append($("<td ><i class='fa fa-trash' onclick=deleteTestParticular('"+encodeURIComponent(rowData.particularId)+"')> </i></td>"));

	return row;
}

function setData(pId,particularRefId,testId,testName,rate,qty){

	particularId=decodeURIComponent(pId);
	var particularRefId=decodeURIComponent(particularRefId);
	var testId=decodeURIComponent(testId);
	var testName=decodeURIComponent(testName);
	var rate=parseFloat(decodeURIComponent(rate));
	var qty=decodeURIComponent(qty);

	$("#particularId").val(particularId);
	$("#testId").val(testId).change();
	$("#particularRefId").val(particularRefId).change();
	$("#rate").val(rate.toFixed(2));
	$("#qty").val(qty);


	document.getElementById("btnSave").disabled = true;
	document.getElementById("btnEdit").disabled = false;

}


function deleteTestParticular(particularId){
	
	if(confirm("Are you sure to delete?")){
		$.ajax({
			type: 'POST',
			dataType: 'json',
			url: './deleteParticularItem',
			data: {
				particularId:particularId
			},
			success: function (data) {
				if(data.result=='Particular Delete is not successfully'){
					alert('Particular Delete is not successfully');
				}
				else{
					$("#dataList").empty();
					patchdata(data.result);
				}
				
			}
		});
	}

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

function refreshAction() {
	location.reload();
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