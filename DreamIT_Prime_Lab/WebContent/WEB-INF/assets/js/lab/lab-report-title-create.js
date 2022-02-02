

window.onload = () => {
	LabReportTitleList();
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
	
  var titleName = $("#titleName").val();
  var groupId = $("#groupId").val();
  var userId = $("#userId").val();

	  
	if(titleName !=""){  
    $.ajax({
      type: 'POST',
      dataType: 'json',
      url: './addLabReportTitle',
      data: {
    	  titleId:"0",
    	  titleName: titleName,
    	  groupId:groupId,
    	  userId:userId
      },
      success: function (data) {
        if (data.result == "Something Wrong") {
          dangerAlert("Something went wrong");
        } else if (data.result == "duplicate") {
        	alert("Duplicate Title..This Title Allreary Exist");
          dangerAlert("Duplicate Title..This Title Allreary Exist")
        } else {
          	alert("Title Save Successfully");
            successAlert("Title Save Successfully");
            txtClear();
          $("#dataList").empty();
          LabReportTitleList();
        }
      }
    });
  } else {
    warningAlert("Empty Title Name ... Please Enter Title Name");
  }
}

function editAction() {
	
	  var titleId=$("#titleId").val();
	  var titleName = $("#titleName").val();
	  var groupId = $("#groupId").val();
	  var userId = $("#userId").val();
		  
		if(titleName !=""){  
	    $.ajax({
	      type: 'POST',
	      dataType: 'json',
	      url: './editLabReportTitle',
	      data: {
	    	  
	    	  titleId:titleId,
	    	  titleName: titleName,
	    	  groupId:groupId,
	    	  userId:userId

	      },
	      success: function (data) {
	        if (data.result == "Something Wrong") {
	          dangerAlert("Something went wrong");
	        } else if (data.result == "duplicate") {
	        	alert("Duplicate Title Name..This Title Name Allreary Exist");
	          dangerAlert("Duplicate Title Name..This Title Name Allreary Exist")
	        } else {
	          	alert("Title Name update Successfully");
	            successAlert("Title Name update Successfully");
	          txtClear();
	          $("#dataList").empty();
	          LabReportTitleList();
	        }
	      }
	    });
	  } else {
	    warningAlert("Empty Title Name ... Please Enter Title Name");
	  }
	}

function txtClear(){
	$('#groupId').val(0);
	$('#groupId').selectpicker('refresh');
	$('#titleName').val('');
}

function LabReportTitleList(){
	$.ajax({
	      type: 'POST',
	      dataType: 'json',
	      url: './LabReportTitleList',
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
	row.append($("<td >" + rowData.groupName+ "</td>"));
	row.append($("<td >" + rowData.titleName+ "</td>"));
	row.append($("<td ><i class='fa fa-edit' onclick=setData('"+encodeURIComponent(rowData.titleId)+"','"+encodeURIComponent(rowData.titleName)+"','"+encodeURIComponent(rowData.groupId)+"')> </i></td>"));
	
	
	return row;
}



function setData(titleId,titleName,groupId){
	
	var tId=decodeURIComponent(titleId);	
	var tName=decodeURIComponent(titleName);

	$("#groupId").val(groupId);
	$('#groupId').selectpicker('refresh');
	
	$("#titleId").val(tId);
	$("#titleName").val(tName);
	
	
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