window.onload = () => {
	addLocationWiseAssetDataRow();
}


function saveAction(){
	var assetName=$('#assetName').val();
	var assetTypeId=$('#assetType').val();
	var location=$('#location').val();
	var brand=$('#brand').val();
	var model=$('#model').val();
	var serialNo=$('#serialNo').val();
	var status=$('#status').val();
	var condition=$('#condition').val();
	var note=$('#note').val();
	var vendorId=$('#vendor').val();
	var poNumber=$('#poNumber').val();
	var purchaseDate=$('#purchaseDate').val();
	var purchaseValue=$('#purchaseValue').val()==''?"0":$('#purchaseValue').val();
	var qty=$('#qty').val()==''?"0":$('#qty').val();
	var marketValue=$('#marketValue').val()==''?"0":$('#marketValue').val();
	var scrapValue=$('#scrapValue').val()==''?"0":$('#scrapValue').val();
	var depreciationMethod=$('#depreciationMethod').val();
	var estimateLife=$('#estimateLife').val();
	
	if(assetName!=''){
		if(assetTypeId!='0'){
			if(location!=''){
						if(serialNo!=''){
							if(status!=''){
								if(condition!=''){
									if(vendorId!='0'){
										if(poNumber!=''){
											if(purchaseDate!=''){
												if(purchaseValue!=''){
													if(qty!=''){
														if(depreciationMethod!='0'){
															if(estimateLife!='0'){
																$.ajax({
																	type: 'POST',
																	dataType: 'json',
																	url: './saveAssetInformation',
																	data: {
																		assetId:"0",
																		assetName:assetName,
																		assetTypeId:assetTypeId,
																		location:location,
																		brand:brand,
																		model:model,
																		serialNo:serialNo,
																		status:status,
																		condition:condition,
																		note:note,
																		vendorId:vendorId,
																		poNumber:poNumber,
																		purchaseDate:purchaseDate,
																		purchaseValue:purchaseValue,
																		qty:qty,
																		marketValue:marketValue,
																		scrapValue:scrapValue,
																		depreciationMethod:depreciationMethod,
																		estimateLife:estimateLife
																	},
																	success: function(data){
																		
																		
																		
																		if(data=='duplicate'){
																			alert("Doplicate Asset Name can't be save");
																		}
																		else if(data=='Something Wrong'){
																			alert("Due to error asset create doesn't possible");
																		}
																		else{
																			$('#assetList').empty();
																			setTableData(data.result);
																		}
																	}
																});						
															}
															else{
																alert("Provide Asset Estimate Life");
															}
														}
														else{
															alert("Provide Asset Depreciation Method");
														}
													}
													else{
														alert("Provide Unit Qty");
													}
												}
												else{
													alert("Provide Purchase Value");
												}
											}
											else{
												alert("Provide Purchase Date");
											}
										}
										else{
											alert("Provide Purchase Order No");
										}
									}
									else{
										alert("Provide Vendor");
									}
								}
								else{
									alert("Provide condition");
								}
							}
							else{
								alert("Provide status");
							}
						}
						else{
							alert("Provide Serial No");
						}
			}
			else{
				alert("Provide Location");
			}
		}
		else{
			alert("Provide Asset Type Id");
		}
	}
	else{
		alert("Provide Asset Name");
	}
	

}


function setTableData(data){
	for (var i = 0; i < data.length; i++) {
		$('#assetList').append(`<tr data-id=${data[i].assetId} ">

				<td class="row-index text-left"> ${i+1} </td>

				<td class="row-index text-left" >${data[i].categoryName}</td>
				<td class="row-index text-left" >${data[i].assetName}</td>
			
				<td class="row-index text-center"> <button id='' onclick='printAssetDetails(${data[i].assetId})' class='' ><i class='fa fa-print'></i></button>  <button id='' onclick='testDelete()' class='' ><i class='fa fa-edit'></i></button>  <button id='' onclick='testDelete()' class='' ><i class='fa fa-trash'></i></button> </td>


		</tr>`);
	}

}


function addLocationWiseAssetDataRow(){
	for (var i = 0; i < 15; i++) {
		$('#locationWiseAssetList').append(`<tr data-id=${i} ">

				<td class="row-index text-left">${i+1}</td>
				<td class="row-index text-left" ></td>
				<td class="row-index text-left" ><input id='assetName-"+${i}+"' style='width:250px;' type='text'  class='form-control-sm assetName-"+i+"'  value=''/></td>
				<td class="row-index text-left" ><input id='brand-"+${i}+"' style='width:80px;' type='text'  class='form-control-sm brand-"+i+"'  value=''/></td>
				<td class="row-index text-left" ><input id='model-"+${i}+"' style='width:80px;' type='text'  class='form-control-sm model-"+i+"'  value=''/></td>
				<td class="row-index text-left" ><input id='serial-"+${i}+"' style='width:80px;' type='text'  class='form-control-sm serial-"+i+"'  value=''/></td>
				<td class="row-index text-left" ><input id='status-"+${i}+"' style='width:80px;' type='text'  class='form-control-sm status-"+i+"'  value=''/></td>
				<td class="row-index text-left" ><input id='conditon-"+${i}+"' style='width:80px;' type='text'  class='form-control-sm conditon-"+i+"'  value=''/></td>
				<td class="row-index text-left" ><input id='note-"+${i}+"' style='width:80px;' type='text'  class='form-control-sm note-"+i+"'  value=''/></td>
				<td class="row-index text-left" ><input id='vendor-"+${i}+"' style='width:80px;' type='text'  class='form-control-sm vendor-"+i+"'  value=''/></td>
				<td class="row-index text-left" ><input id='purchaseNo-"+${i}+"' style='width:80px;' type='text'  class='form-control-sm purchaseNo-"+i+"'  value=''/></td>
				<td class="row-index text-left" ><input id='purchaseDate-"+${i}+"' style='width:80px;' type='text'  class='form-control-sm purchaseDate-"+i+"'  value=''/></td>
				<td class="row-index text-left" ><input id='purchaseValue-"+${i}+"' style='width:80px;' type='text'  class='form-control-sm purchaseValue-"+i+"'  value=''/></td>
				<td class="row-index text-left" ><input id='purchaseQty-"+${i}+"' style='width:80px;' type='text'  class='form-control-sm purchaseQty-"+i+"'  value=''/></td>
				<td class="row-index text-left" ><input id='marketValue-"+${i}+"' style='width:80px;' type='text'  class='form-control-sm marketValue-"+i+"'  value=''/></td>
				<td class="row-index text-left" ><input id='scrapValue-"+${i}+"' style='width:80px;' type='text'  class='form-control-sm scrapValue-"+i+"'  value=''/></td>
				<td class="row-index text-left" ><input id='depreciationMethod-"+${i}+"' style='width:80px;' type='text'  class='form-control-sm depreciationMethod-"+i+"'  value=''/></td>
				<td class="row-index text-left" ><input id='estimateLife-"+${i}+"' style='width:80px;' type='text'  class='form-control-sm estimateLife-"+i+"'  value=''/></td>
				
		</tr>`);
	}
}

let idListMicroNew = ["assetName","assetTypeId","location","brand","model","serialNo","status","Condition","note","vendorId","poNumber",
	"purchaseDate","purchaseValue","qty","marketValue","scrapValue","DepreciationMethod","estimateLife"];
idListMicroNew.forEach((id,index)=>{
  console.log("execute",id);
  $('#'+id).keyup(function(event){
    console.log("execute",event.keyCode);
    if(event.keyCode ===13){
      event.preventDefault();
      $("#"+idListMicroNew[index+1]).focus();
    }
    
  });
})


function printAssetDetails(assetId){
	 let url = `printAssetDetailsReport/${assetId}`;
	  window.open(url, '_blank');
}