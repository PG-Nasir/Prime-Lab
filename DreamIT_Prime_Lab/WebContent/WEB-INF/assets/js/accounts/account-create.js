
var childItem="";
var size=0;
var myResult;
var headId=0;

function chartOfAccount(){


	
	$.ajax({

		type:'GET',
		dataType:'json',
		url:'./chartOfAccountData',
		success:function(data)
		{
			// var size=JSON.stringify(data.result);
			
			myResult=data.result;
			console.log("datalenght"+data.result.length);
			
			var chartvalue="";
			chartvalue+="<ul class='nested'>"

				for(var i=0;i<4;i++){
					chartvalue+="<li onclick='AccountSumarySingleClick(event,"+myResult[i].headId+")' ondblclick='AccountSumary(event,"+data.result[i].headId+")'; style='cursor:pointer;'><span class='caret'>"+data.result[i].headTitle+"</span>"
					childItem="";
					chartvalue+=childAdd(data.result[i].headId)+"</li>";
				}

			chartvalue+="</ul>";
			$('#chartAcc').append(chartvalue);


			var toggler = document.getElementsByClassName("caret");
			var i;

			for (i = 0; i < toggler.length; i++) {
				toggler[i].addEventListener("click", function() {
					this.parentElement.querySelector(".nested").classList
					.toggle("active");
					this.classList.toggle("caret-down");
				});
			}
	
		}

		
	});


}

function childAdd(pId){


	console.log("tresultlenght"+myResult.length);
	
	childItem="";
	//size=data.result.length;


	var inittext="<ul class='nested'>";


	var add=0;
	for(var i=0;i<myResult.length;i++){
		console.log("pHeadId"+myResult[i].pHeadId);
		if(myResult[i].pHeadId==pId){
			if(add==0){
				childItem+=inittext;
			}
			var hasChild=checkHasChild(myResult[i].headId);
			if(hasChild){

				childItem+="<li onclick='AccountSumarySingleClick(event,"+myResult[i].headId+")' ondblclick='AccountSumary(event,"+myResult[i].headId+")' style='cursor:pointer;'><span class='caret'>"+myResult[i].headTitle+"</span>";

				childItem+=childAdd(myResult[i].headId);
				//childItem+="</ul>"
					childItem+="</li>";

			}
			else{
				childItem+="<li onclick='AccountSumarySingleClick(event,"+myResult[i].headId+")' ondblclick='AccountSumary(event,"+myResult[i].headId+")' style='cursor:pointer;'>"+myResult[i].headTitle+"</li>";
			}


			add++;
		}
	}

	if(add!=0){
		childItem+="</ul>";
	}

	return childItem;
}

function checkHasChild(pId){
	var size=myResult.length;

	for(var i=0;i<size;i++){
		if(myResult[i].pHeadId==pId){
			return true;
			break;
		}
	}

	return false;
}

function saveAction() {
	
  var parentId = $("#parentId").val();
  var subCategoryName = $("#subCategoryName").val();
  var remark = $("#remark").val();
  var userId = $("#userId").val();
  var unitId="0";
  var depId="0";

  console.log("parentId "+parentId);
  
  if (parentId != '0' & subCategoryName != '') {
	  
	  
    $.ajax({
      type: 'POST',
      dataType: 'json',
      url: './saveHead',
      data: {
    	  headId:"0",
    	  parentId:parentId,
    	  subCategoryName: subCategoryName,
    	  remark:remark,
    	  userId:userId,
    	  unitId:unitId,
    	  depId:depId

      },
      success: function (data) {
        if (data.result == "Something Wrong") {
        	alert("Something went wrong");
        } else if (data.result == "duplicate") {
        	alert("Duplicate Head..This Head Name Allreary Exist");
          dangerAlert("Duplicate Head..This Head Name Allreary Exist")
        } else {
        
        	alert("Head Create Successfully");
          $("#dataList").empty();
			patchdata(data.result);
        }
      }
    });
  } else {
	  alert("Empty Head Info ... Please Enter Head Information");
  }
}


function editAction() {
	
	var editPermission = $("#editPermission").val();
	
	console.log("editPermission "+editPermission);
	
	if(editPermission=='1'){
		  var headId = $("#headId").val();
		  var parentId = $("#parentId").val();
		  var subCategoryName = $("#subCategoryName").val();
		  var remark = $("#remark").val();
		  var userId = $("#userId").val();
		  var unitId="0";
		  var depId="0";

		  if (parentId != '0' & subCategoryName != '') {
	    $.ajax({
	      type: 'POST',
	      dataType: 'json',
	      url: './editHead',
	      data: {
	    	  headId:headId,
	    	  parentId:parentId,
	    	  subCategoryName: subCategoryName,
	    	  remark:remark,
	    	  userId:userId,
	    	  unitId:unitId,
	    	  depId:depId
	      },
	      success: function (data) {
	        if (data.result == "Something Wrong") {
	        	alert("Something went wrong");
	        } else if (data.result == "duplicate") {
	        	alert("Duplicate Head..This Head Name Allreary Exist")
	        } else {
	          alert("Head Edit Successfully");
	          $("#dataList").empty();
			  patchdata(data.result);
	        }
	      }
	    });
	  } else {
		  alert("Empty Head Inforomation... Please Enter Head Inforomation" );
	  }
	}
	else{
		alert("Provide Edit Permission");
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
	row.append($("<td >" + rowData.subCategoryName+ "</td>"));
	row.append($("<td ><input type='hidden' id='headId"+rowData.headId+"' value='"+rowData.headId+"'/>"+
					  "<input type='hidden' id='parentId"+rowData.headId+"' value='"+rowData.parentId+"'/>"+
					  "<input type='hidden' id='subCategoryName"+rowData.headId+"' value='"+rowData.subCategoryName+"'/>"+
					  "<input type='hidden' id='remark"+rowData.headId+"' value='"+rowData.remark+"'/>"+
					  "<i class='fa fa-edit' onclick=setData('"+rowData.headId+"')> </i></td>"));
	row.append($("<td ><i class='fa fa-trash' onclick=DeleteHead('"+rowData.headId+"')> </i></td>"));
	
	return row;
}

function DeleteHead(headId){
	var deletePermissio = $("#deletePermissio").val();
	if(deletePermissio=='1'){
	    $.ajax({
		      type: 'POST',
		      dataType: 'json',
		      url: './deleteHead',
		      data: {
		    	  headId:headId
		      },
		      success: function (data) {
		        if (data.result == "Something Wrong") {
		        	alert("Something went wrong");
		        } else if (data.result == "Head has many transaction plase contact to system administrator") {
		        	alert("Head has many transaction plase contact to system administrator")
		        } else {
		          alert("Head Delete Successfully");
		          $("#dataList").empty();
				  patchdata(data.result);
		        }
		      }
		    });
	}
	else{
		alert("You have no delete permission");
	}
	
}

function setData(headId) {

	  console.log("headId "+headId);	
	  document.getElementById("headId").value = headId;
	  document.getElementById("parentId").value = document.getElementById("parentId" + headId).value;
	  document.getElementById("subCategoryName").value = document.getElementById("subCategoryName" + headId).value;
	  document.getElementById("remark").value = document.getElementById("remark" + headId).value;
	  
	  
	  $('#parentId').val($('#parentId'+headId).val()).change();
	  $('#parentId').selectpicker('refresh');
	  
	  
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

