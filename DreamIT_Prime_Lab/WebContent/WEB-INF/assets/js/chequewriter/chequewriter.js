function printSaveCheque(writerId){
	 let url = `printChequeWriteDetailsReport/${writerId}`;
	  window.open(url, '_blank');
}

function bankSaveAction(){
	var bankName=$('#newBankName').val();
	var reportName=$('#reportName').val();
	var userId=$('#userId').val();
	if(bankName!=''){
		$.ajax({
			type: 'POST',
			dataType: 'json',
			url: './addNewBank',
			data: {
				bankName:bankName,
				reportName:reportName,
				userId:userId
			},
			success: function(data){
			    let item = data.result;
			    if(data.result=='Something Has Wrong'){
			    	alert("Something Has Wrong");
			    }
			    else{
			    	alert("Bank Create Successfully");
			    	$('.modal').modal('hide');
			    	location.reload();
			    	//$('#bankList').empty();
			    	//setBankTableData(data.result);
			    }
			}
		});	
	}
	else{
		alert("Provide Bank Name");
	}
	

}


function setBankTableData(data){
	for (var i = 0; i < data.length; i++) {
		$('#bankList').append(`<tr data-id=${data[i].writerId} ">

				<td style='width:300px;' class="row-index text-left" >${data[i].bankName}</td>
				

		</tr>`);
	}
}


function viewData(writerId){
	$.ajax({
		type: 'GET',
		dataType: 'json',
		url: './getChequeWriterDetails/'+writerId,
		data: {
			writerId:writerId
		},
		success: function(data){
		    let item = data.result;

	        $("#bankId").val(item[0].bankId);
	        $("#payTo").val(item[0].payTo);
	        $("#amount").val(parseFloat(item[0].amount).toFixed(2));
	        $("#chequeWriteDate").val(item[0].chequeWriteDate);
	        $("#printCategory").val(item[0].printCategory);

		}
	});	
}


function refreshAction(){
    $("#bankId").val(0);
    $("#payTo").val('');
    $("#amount").val('');
    $("#chequeWriteDate").val();
    $("#printCategory").val(0);
}


function saveAction(){
	var bankId=$('#bankId').val();
	var payTo=$('#payTo').val();
	var amount=$('#amount').val();
	var chequeWriteDate=$('#chequeWriteDate').val();
	var userId=$('#userId').val();
	var printCategory=$('#printCategory').val();
	
	if(bankId!='0'){
		if(payTo!=''){
			if(amount!=''){
				
				$.ajax({
					type: 'POST',
					dataType: 'json',
					url: './saveChequeWriter',
					data: {
						menuId:"0",
						bankId:bankId,
						payTo:payTo,
						amount:amount,
						date:chequeWriteDate,
						printCategory:printCategory,
						userId:userId
					},
					success: function(data){
							
						if(data=='Something Wrong'){
							alert("Due to error cheque write doesn't possible");
						}
						else{
							printSaveCheque(data.writerId);
							$('#chequeList').empty();
							setTableData(data.result);
						}
			
					}
				});	
			}
			else{
				alert("Provide Amount ");
			}
		}
		else{
			alert("Provide Pay To");
		}
	}
	else{
		alert("Provide Bank Name");
	}
	
}




function setTableData(data){
	for (var i = 0; i < data.length; i++) {
		$('#chequeList').append(`<tr data-id=${data[i].writerId} ">

				<td class="row-index text-left"> ${i+1} </td>
				<td class="row-index text-left" >${data[i].bankName}</td>
				<td class="row-index text-left" >${data[i].payTo}</td>
				<td class="row-index text-left" >${data[i].userName}</td>
			
				<td class="row-index text-center"> </button>  <button id='' onclick='printSaveCheque(${data[i].writerId})' class='' ><i class='fa fa-edit'></i></button>  <button id='' onclick='' class='' ><i class='fa fa-trash'></i></button> </td>

		</tr>`);
	}
}



var today = new Date();
document.getElementById("chequeWriteDate").value = today.getFullYear() + '-' + ('0' + (today.getMonth() + 1)).slice(-2) + '-' + ('0' + today.getDate()).slice(-2);

