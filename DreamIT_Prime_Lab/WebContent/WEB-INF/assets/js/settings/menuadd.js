function saveAction(){
	var moduleId=$('#moduleId').val();
	var menuName=$('#menuName').val();
	var userId=$('#userId').val();
	
	if(moduleId!='0'){
		if(moduleId!=''){
			$.ajax({
				type: 'POST',
				dataType: 'json',
				url: './saveMenuInformation',
				data: {
					menuId:"0",
					moduleId:moduleId,
					menuName:menuName,
					userId:userId
				},
				success: function(data){
						
					if(data=='duplicate'){
						alert("Doplicate Menu Name can't be save");
					}
					else if(data=='Something Wrong'){
						alert("Due to error asset create doesn't possible");
					}
					else{
						$('#menuList').empty();
						setTableData(data.result);
					}
				}
			});	
		}
		else{
			alert("Provide Menu Name");
		}
	}
	else{
		alert("Provide Module Name");
	}
	
}

function setTableData(data){
	for (var i = 0; i < data.length; i++) {
		$('#menuList').append(`<tr data-id=${data[i].menuId} ">

				<td class="row-index text-left"> ${i+1} </td>
				<td class="row-index text-left" >${data[i].moduleName}</td>
				<td class="row-index text-left" >${data[i].menuName}</td>
			
				<td class="row-index text-center"> </button>  <button id='' onclick='' class='' ><i class='fa fa-edit'></i></button>  <button id='' onclick='' class='' ><i class='fa fa-trash'></i></button> </td>

		</tr>`);
	}
}