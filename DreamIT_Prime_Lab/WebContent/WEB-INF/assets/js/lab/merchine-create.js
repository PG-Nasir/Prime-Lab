

window.onload = () => {
	MachineList();
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
	
  var machineName = $("#machineName").val();
  var groupId = $("#groupId").val();
  var userId = $("#userId").val();

	  
	if(machineName !=""){  
    $.ajax({
      type: 'POST',
      dataType: 'json',
      url: './addMachine',
      data: {
    	  machineId:"0",
    	  machineName: machineName,
    	  groupId:groupId,
    	  userId:userId
      },
      success: function (data) {
        if (data.result == "Something Wrong") {
          dangerAlert("Something went wrong");
        } else if (data.result == "duplicate") {
        	alert("Duplicate Machine..This Machine Name Allreary Exist");
          dangerAlert("Duplicate Machine..This Machine Name Allreary Exist")
        } else {
          	alert("Machine Save Successfully");
            successAlert("Machine Save Successfully");
            txtClear();
          $("#dataList").empty();
          MachineList();
        }
      }
    });
  } else {
    warningAlert("Empty Machine Name ... Please Enter Machine Name");
  }
}

function editAction() {
	
	  var machineId=$("#machineId").val();
	  var machineName = $("#machineName").val();
	  var groupId = $("#groupId").val();
	  var userId = $("#userId").val();
		  
		if(machineName !=""){  
	    $.ajax({
	      type: 'POST',
	      dataType: 'json',
	      url: './editMachine',
	      data: {
	    	  
	    	  machineId:machineId,
	    	  machineName: machineName,
	    	  groupId:groupId,
	    	  userId:userId

	      },
	      success: function (data) {
	        if (data.result == "Something Wrong") {
	          dangerAlert("Something went wrong");
	        } else if (data.result == "duplicate") {
	        	alert("Duplicate Machine Name..This Machine Name Allreary Exist");
	          dangerAlert("Duplicate Machine Name..This Machine Name Allreary Exist")
	        } else {
	          	alert("Machine Name update Successfully");
	            successAlert("Machine Name update Successfully");
	          txtClear();
	          $("#dataList").empty();
	          MachineList();
	        }
	      }
	    });
	  } else {
	    warningAlert("Empty Machine Name ... Please Enter Machine Name");
	  }
	}

function txtClear(){
	$('#groupId').val(0);
	$('#groupId').selectpicker('refresh');
	$('#machineName').val('');
}

function MachineList(){
	$.ajax({
	      type: 'POST',
	      dataType: 'json',
	      url: './MachineList',
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
	row.append($("<td >" + rowData.machineName+ "</td>"));
	row.append($("<td ><i class='fa fa-edit' onclick=setData('"+encodeURIComponent(rowData.machineId)+"','"+encodeURIComponent(rowData.machineName)+"','"+encodeURIComponent(rowData.groupId)+"')> </i></td>"));
	
	
	return row;
}

function DeleteSubTest(subTestId){
	
	if(confirm("Are you sure to delete?")){
		$.ajax({
		      type: 'GET',
		      dataType: 'json',
		      url: './DeleteSubTest/'+subTestId,
		      success: function (data) {
		    	  if(data=='Delete can not due to error'){
		    		  alert(data);
		    	  }
		    	  else{
		    		  alert("Sub Test Delete Successfully");
		    		  $("#dataList").empty();
		    		  patchdata(data.result);
		    	  }
		    	 
		      }
		    });
	}
	

}

function setData(marchineId,machineName,groupId){
	
	var macId=decodeURIComponent(marchineId);	
	var macName=decodeURIComponent(machineName);

	$("#groupId").val(groupId);
	$('#groupId').selectpicker('refresh');
	
	$("#machineId").val(macId);
	$("#machineName").val(macName);
	
	
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