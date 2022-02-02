
window.onload = () => {
	TestWiseSubTestList();
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
	
  var subTestName = $("#subTestName").val();
  var calculate = $("#calculate").val();
  var parentTest = $("#testId").val();
  var unit = $("#unit").val();
  var normalRange = $("#normalRange").val();
  var sorting = $("#sorting").val()==''?"0":$("#sorting").val();
  var userId = $("#userId").val();
	  
	if(subTestName !=""){  
    $.ajax({
      type: 'POST',
      dataType: 'json',
      url: './addSubTest',
      data: {
    	  subTestId:"0",
    	  subTestName: subTestName,
    	  calculate:calculate,
    	  parentTest:parentTest,
    	  unit:unit,
    	  normalRange:normalRange,
    	  userId:userId,
    	  sorting:sorting

      },
      success: function (data) {
        if (data.result == "Something Wrong") {
          dangerAlert("Something went wrong");
        } else if (data.result == "duplicate") {
        	alert("Duplicate SubTest..This SubTest Name Allreary Exist");
          dangerAlert("Duplicate SubTest..This Test Name Allreary Exist")
        } else {
          	alert("SubTest Save Successfully");
            successAlert("SubTest Save Successfully");

          $("#dataList").empty();
          TestWiseSubTestList();
        }
      }
    });
  } else {
    warningAlert("Empty SubTest Name ... Please Enter SubTest Name");
  }
}

function editAction() {
	
	  var subTestId = $("#subTestId").val();
	  var subTestName = $("#subTestName").val();
	  var calculate = $("#calculate").val();
	  var parentTest = $("#testId").val();
	  var unit = $("#unit").val();
	  var normalRange = $("#normalRange").val();
	  var userId = $("#userId").val();
	  var sorting = $("#sorting").val()==''?"0":$("#sorting").val();
		  
		if(subTestName !=""){  
	    $.ajax({
	      type: 'POST',
	      dataType: 'json',
	      url: './editSubTest',
	      data: {
	    	  
	    	  subTestId:subTestId,
	    	  subTestName: subTestName,
	    	  calculate:calculate,
	    	  parentTest:parentTest,
	    	  unit:unit,
	    	  normalRange:normalRange,
	    	  sorting:sorting,
	    	  userId:userId

	      },
	      success: function (data) {
	        if (data.result == "Something Wrong") {
	          dangerAlert("Something went wrong");
	        } else if (data.result == "duplicate") {
	        	alert("Duplicate SubTest..This SubTest Name Allreary Exist");
	          dangerAlert("Duplicate SubTest..This Test Name Allreary Exist")
	        } else {
	          	alert("SubTest update Successfully");
	            successAlert("SubTest update Successfully");

	          $("#dataList").empty();
	          TestWiseSubTestList();
	        }
	      }
	    });
	  } else {
	    warningAlert("Empty SubTest Name ... Please Enter SubTest Name");
	  }
	}

function TestWiseSubTestList(){
	$.ajax({
	      type: 'POST',
	      dataType: 'json',
	      url: './TestWiseSubTestList',
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
	row.append($("<td >" + rowData.subtestname+ "</td>"));
	row.append($("<td >" + rowData.unit+ "</td>"));
	row.append($("<td >" + rowData.sorting+ "</td>"));
	row.append($("<td ><i class='fa fa-edit' onclick=setData('"+encodeURIComponent(rowData.subTestId)+"','"+encodeURIComponent(rowData.subtestname)+"','"+encodeURIComponent(rowData.calculate)+"','"+encodeURIComponent(rowData.testId)+"','"+encodeURIComponent(rowData.testName)+"','"+encodeURIComponent(rowData.unit)+"','"+encodeURIComponent(rowData.normalRange)+"','"+encodeURIComponent(rowData.sorting)+"')> </i></td>"));
	row.append($("<td ><i class='fa fa-trash' onclick=DeleteSubTest('"+encodeURIComponent(rowData.subTestId)+"')> </i></td>"));
	
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

function setData(subtestId,subtestname,calculate,testId,testName,unit,normalRange,sorting){
	
	subTestId=decodeURIComponent(subtestId);
	console.log("subTestId : "+subTestId)
	var subtestname=decodeURIComponent(subtestname);
	var calculate=decodeURIComponent(calculate);
	var testId=decodeURIComponent(testId);
	var unit=decodeURIComponent(unit);
	var normalRange=decodeURIComponent(normalRange);
	
	$("#subTestId").val(subTestId);
	$("#subTestName").val(subtestname);
	$("#calculate").val(calculate);
	$("#testId").val(testId).change();
	$("#unit").val(unit);
	$("#normalRange").val(normalRange);
	$("#sorting").val(sorting);
	
	
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