
function saveAction() {
	
  var headId = $("#headId").val();
  var ledgerName = $("#ledgerName").val();
  var openingBalance = $("#openingBalance").val()==''?"0":$("#openingBalance").val();
  var remark = $("#remark").val();
  var userId = $("#userId").val();
  var unitId="0";
  var depId="0";
  

  
  if (headId != '0' & ledgerName != '') {
	  
	  
    $.ajax({
      type: 'POST',
      dataType: 'json',
      url: './saveLedger',
      data: {
    	  ledgerId:"0",
    	  headId:headId,
    	  ledgerName: ledgerName,
    	  openingBalance:openingBalance,
    	  remark:remark,
    	  userId:userId,
    	  unitId:unitId,
    	  depId:depId

      },
      success: function (data) {
        if (data.result == "Something Wrong") {
        	alert("Something went wrong");
        } else if (data.result == "duplicate") {
        	alert("Duplicate Ledger..This Ledger Name Allreary Exist");
        	alert("Duplicate Ledger..This Ledger Name Allreary Exist")
        } else {
        	
          $("#dataList").empty();
			patchdata(data.result);
        }
      }
    });
  } else {
	  alert("Empty Ledger Info ... Please Enter Ledger Information");
  }
}


function editAction() {
	  var ledgerId = $("#ledgerId").val();
	  var headId = $("#headId").val();
	  var ledgerName = $("#ledgerName").val();
	  var openingBalance = $("#openingBalance").val()==''?"0":$("#openingBalance").val();
	  var remark = $("#remark").val();
	  var userId = $("#userId").val();
	  var unitId="0";
	  var depId="0";

	  if (headId != '0' & ledgerName != '') {
    $.ajax({
      type: 'POST',
      dataType: 'json',
      url: './editLedger',
      data: {
    	  ledgerId:ledgerId,
    	  headId:headId,
    	  ledgerName: ledgerName,
    	  openingBalance:openingBalance,
    	  remark:remark,
    	  userId:userId,
    	  unitId:unitId,
    	  depId:depId
      },
      success: function (data) {
        if (data.result == "Something Wrong") {
          alert("Something went wrong");
        } else if (data.result == "duplicate") {
        	alert("Duplicate Ledger..This Ledger Name Allreary Exist")
        } else {
        	alert("Ledger Edit Successfully");
          $("#dataList").empty();
		  patchdata(data.result);
        }
      }
    });
  } else {
	  alert("Empty Ledger Inforomation... Please Enter Ledger Inforomation" );
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
	row.append($("<td >" + rowData.ledgerName+ "</td>"));
	row.append($("<td ><input type='hidden' id='headId"+rowData.ledgerId+"' value='"+rowData.headId+"'/>"+
					  "<input type='hidden' id='ledgerId"+rowData.ledgerId+"' value='"+rowData.ledgerId+"'/>"+
					  "<input type='hidden' id='ledgerName"+rowData.ledgerId+"' value='"+rowData.ledgerName+"'/>"+
					  "<input type='hidden' id='openingBalance"+rowData.ledgerId+"' value='"+rowData.openingBalance+"'/>"+
					  "<input type='hidden' id='remark"+rowData.ledgerId+"' value='"+rowData.remark+"'/>"+
					  "<i class='fa fa-edit' onclick=setData('"+rowData.ledgerId+"')> </i></td>"));
	
	return row;
}

function setData(ledgerId) {

	  console.log("ledgerId "+ledgerId);	
	  document.getElementById("ledgerId").value = ledgerId;
	  document.getElementById("headId").value = document.getElementById("headId" + ledgerId).value;
	  document.getElementById("ledgerName").value =document.getElementById("ledgerName" + ledgerId).value;
	  document.getElementById("openingBalance").value = parseFloat(document.getElementById("openingBalance" + ledgerId).value).toFixed(2);
	  document.getElementById("remark").value = document.getElementById("remark" + ledgerId).value;
	  
	  
	  $('#headId').val($('#headId'+ledgerId).val()).change();
	  $('#headId').selectpicker('refresh');
	  
	  
	  document.getElementById("btnSave").disabled = true;
	  document.getElementById("btnEdit").disabled = false;

	}

function setAccountDetails(dataList){
	
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

