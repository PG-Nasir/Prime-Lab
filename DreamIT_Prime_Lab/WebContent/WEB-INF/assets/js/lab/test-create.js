function btnTestView(){
	 var findGroupId = $("#findGroupId").val();
	  $.ajax({
	        type: 'GET',
	        dataType: 'json',
	        url: './findGroupId/'+findGroupId,
	        success: function (data) {
	        	
	            $("#dataList").empty();
	  			patchdata(data.result);
	        }
	  });
}

function btnTestPrint(){
	 var findGroupId = $("#findGroupId").val();
	 
		var url = `printTestList/${findGroupId}`;
		window.open(url, '_blank');
	 
}


function saveAction() {
	
  var testName = $("#testName").val();
  var discountAllow = $("#discountAllow").val();
  var headId = $("#groupId").val();
  var rate = $("#rate").val()==''?"0":$("#rate").val();
  var doctorCommission = $("#doctorCommission").val()==''?"0":$("#doctorCommission").val();
  console.log("doctorCommission "+doctorCommission);
  var unit = $("#unit").val();
  var normalRange = $("#normalRange").val();
  var userId = $("#userId").val();
  var testCode='';
  
  if (testName != '' & rate != '' & headId!='0' ) {
	  
	  
    $.ajax({
      type: 'POST',
      dataType: 'json',
      url: './saveTest',
      data: {
    	  testId:"0",
    	  testName: testName,
    	  testCode:testCode,
    	  discountAllow:discountAllow,
    	  headId: headId,
    	  rate:rate,
    	  doctorCommission:doctorCommission,
    	  unit:unit,
    	  normalRange:normalRange,
    	  userId:userId

      },
      success: function (data) {
        if (data.result == "Something Wrong") {
          dangerAlert("Something went wrong");
        } else if (data.result == "duplicate") {
        	alert("Duplicate Test..This Test Name Allreary Exist");
          dangerAlert("Duplicate Test..This Test Name Allreary Exist")
        } else {
          	alert("Test Save Successfully");
            successAlert("Test Save Successfully");

          $("#dataList").empty();
			patchdata(data.result);
        }
      }
    });
  } else {
    warningAlert("Empty Test Info ... Please Enter Test Infor");
  }
}


function editAction() {
	  var testId = $("#testId").val();
	  var testName = $("#testName").val();
	  var discountAllow = $("#discountAllow").val();
	  var headId = $("#groupId").val();
	  var rate = $("#rate").val()==''?"0":$("#rate").val();
	  var doctorCommission = $("#doctorCommission").val()==''?"0":$("#doctorCommission").val();
	  console.log("doctorCommission "+doctorCommission);
	  var unit = $("#unit").val();
	  var normalRange = $("#normalRange").val();
	  var userId = $("#userId").val();
	  var testCode='';

	if (testName != '' & rate != '0') {
    $.ajax({
      type: 'POST',
      dataType: 'json',
      url: './editTest',
      data: {
    	  testId:testId,
    	  testName: testName,
    	  testCode:testCode,
    	  discountAllow:discountAllow,
    	  headId: headId,
    	  rate:rate,
    	  doctorCommission:doctorCommission,
    	  unit:unit,
    	  normalRange:normalRange,
    	  userId:userId
      },
      success: function (data) {
        if (data.result == "Something Wrong") {
          dangerAlert("Something went wrong");
        } else if (data.result == "duplicate") {
          dangerAlert("Duplicate Test..This Test Name Allreary Exist")
        } else {
          successAlert("Test Edit Successfully");
          $("#dataList").empty();
		  patchdata(data.result);
        }
      }
    });
  } else {
    warningAlert("Empty Test Inforomation... Please Enter Test Inforomation" );
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
	row.append($("<td >" + rowData.headName+ "</td>"));
	row.append($("<td >" + rowData.testName+ "</td>"));
	row.append($("<td >" + rowData.rate+ "</td>"));
	row.append($("<td ><input type='hidden' id='headId"+rowData.testId+"' value='"+rowData.headId+"'/>"+
				"<input type='hidden' id='testName"+rowData.testId+"' value='"+rowData.testName+"'/>"+
				"<input type='hidden' id='rate"+rowData.testId+"' value='"+rowData.rate+"'/>"+
				"<input type='hidden' id='normalRange"+rowData.testId+"' value='"+rowData.normalRange+"'/>"+
				"<input type='hidden' id='unit"+rowData.testId+"' value='"+rowData.unit+"'/>"+
				"<input type='hidden' id='doctorCommission"+rowData.testId+"' value='"+rowData.doctorCommission+"'/>"+
				"<input type='hidden' id='discountAllow"+rowData.testId+"' value='"+rowData.discountAllow+"'/>"+
				"<i class='fa fa-edit' onclick=setData('"+rowData.testId+"')> </i></td>"));
	
	return row;
}

function setData(testId) {

	  document.getElementById("testId").value = testId;
	  document.getElementById("testName").value = document.getElementById("testName" + testId).value;
	  document.getElementById("discountAllow").value = document.getElementById("discountAllow" + testId).value;
	//  document.getElementById("groupId").value = document.getElementById("headId" + testId).value;
	  
	  
	  $('#groupId').val($('#headId'+testId).val()).change();
	  $('#groupId').selectpicker('refresh');
	  //document.getElementById("rate").value = document.getElementById("rate" + testId).value;
	  
	  $('#rate').val(parseFloat($('#rate'+testId).val()).toFixed(2));
	  $('#doctorCommission').val(parseFloat($('#doctorCommission'+testId).val()).toFixed(2));
	  
	  //document.getElementById("doctorCommission").value = parseFloat((document.getElementById("doctorCommission" + testId).value).toFixed(2));
	  document.getElementById("unit").value = document.getElementById("unit" + testId).value;
	  document.getElementById("normalRange").value = document.getElementById("normalRange" + testId).value;
	  
	  
	  document.getElementById("btnSave").disabled = true;
	  document.getElementById("btnEdit").disabled = false;

	}

function setMamangeDetails(dataList){
	
	 for (var i = 0; i < dataList.length; i++) {
		  var item = dataList[i];
		  $('#corporateId').val(item.corporateId);
		  $('#corporateName').val(item.corporateName);
		  $('#nationality').val(item.nationality);
		  $('#nationalId').val(item.nationalId);
		  $('#address').val(item.address);
		  $('#religion').val(item.religion);
		  $('#sex').val(item.sex);
		  $('#mobile').val(item.mobile);
		  $('#email').val(item.email);
		  $('#telephone').val(item.telephone);
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

