var gcounter=1;
$("#btncounter1").css("background-color", "red");
$("#btncounter2").css("background-color", "green");
$("#btncounter3").css("background-color", "green");
$("#btncounter4").css("background-color", "green");
$("#btncounter5").css("background-color", "green");
$("#btncounter6").css("background-color", "green");

let doctorlist=null;
let testlist=null;


var d = new Date();
var month = new Array();
month[0] = "January";
month[1] = "February";
month[2] = "March";
month[3] = "April";
month[4] = "May";
month[5] = "June";
month[6] = "July";
month[7] = "August";
month[8] = "September";
month[9] = "October";
month[10] = "November";
month[11] = "December";
var month = month[d.getMonth()];

$("#sMonth").val(month);

var year = d.getFullYear();
$("#sFiscalYear").val(year);

window.onload = () => {
	loadDoctorList();
	loadTestList();
	var modueMenu=$('#modueMenu').val();
	checkMenuId(modueMenu);
};

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


function loadTestList(){
    $.ajax({
        type: 'GET',
        dataType: 'json',
        url: './getTestList',
        success: function (data) {
        	testlist = data.mainTestlist.map(rowData => rowData.testName);
		  	 $("#testId").autocomplete({
	                source: testlist
	            });
        }
    });
}

function loadDoctorList(){
    $.ajax({
        type: 'GET',
        dataType: 'json',
        url: './getDoctorList',
        success: function (data) {
        	doctorlist = data.doctorList.map(rowData => rowData.doctorName);
        	
		  	 $("#referral").autocomplete({
	                source: doctorlist
	            });
		  	 
		  	 $("#cReferral").autocomplete({
	                source: doctorlist
	            });
		  	 
		  	 $("#extraCommission").autocomplete({
	                source: doctorlist
	            });
        }
    });
}

function LabBillPrint(){
	
	var labId=$("#sLabId").val();
	var fiscalyear=$("#sFiscalYear").val();
	var cmonth=$("#sMonth").val();
	var reportType=$("#reporType").val();
	
	if(reportType=='All'){
		var url = `printLabInfo/${fiscalyear}@${labId}@${cmonth}@${reportType}`;
		window.open(url, '_blank');
		
		var url = `printLabSlipInfo/${fiscalyear}@${labId}@${cmonth}@${reportType}`;
		window.open(url, '_blank');
	}
	else if(reportType=='Money Receipt'){
		var url = `printLabInfo/${fiscalyear}@${labId}@${cmonth}@${reportType}`;
		window.open(url, '_blank');
	}
	else if(reportType=='Lab Slip'){
		var url = `printLabSlipInfo/${fiscalyear}@${labId}@${cmonth}@${reportType}`;
		window.open(url, '_blank');
	}
	
}

function labPrint(labId,fiscalyear,cmonth){
	
	
	var reportType=$("#reporType").val();
	
	if(reportType=='All'){
		var url = `printLabInfo/${fiscalyear}@${labId}@${cmonth}@${reportType}`;
		window.open(url, '_blank');
		
		var url = `printLabSlipInfo/${fiscalyear}@${labId}@${cmonth}@${reportType}`;
		window.open(url, '_blank');
	}
	else if(reportType=='Money Receipt'){
		var url = `printLabInfo/${fiscalyear}@${labId}@${cmonth}@${reportType}`;
		window.open(url, '_blank');
	}
	else if(reportType=='Lab Slip'){
		var url = `printLabSlipInfo/${fiscalyear}@${labId}@${cmonth}@${reportType}`;
		window.open(url, '_blank');
	}
	
}

function addCRerralDoctor(){
	var userId = $("#user_hidden").val();
	var referralId = $("#cReferral").val();
	var degree = $("#referralcomissiondegree").val();
	
	if(referralId==''){
		alert("Provide Commission Referral Doctor");
	}
	else{

		$.ajax({
			
			type:'POST',
			dataType:'json',
			url:'./addDoctorDirect',
	        data: {
	        	userId:userId,
	        	doctorName:referralId,
	        	degree:degree,
	        	visitFee:"0",
	        	roomNo:""
	        	
	        },
			success: function (data) {
				
				if(data.doctorList=='Something has wrong!'){
					alert(data);
				}
				else{
				  	doctorlist = data.doctorList.map(rowData => rowData.doctorName);
		        	
				  	 $("#referral").autocomplete({
			                source: doctorlist
			            });
				  	 
				  	 $("#cReferral").autocomplete({
			                source: doctorlist
			            });
				}
			
			}
			
			
		});
	}
}
function addRerralDoctor(){
	var userId = $("#user_hidden").val();
	var referralId = $("#referral").val();
	var degree = $("#referraldegree").val();
	
	if(referralId==''){
		alert("Provide Referral Doctor");
	}
	else{

		$.ajax({
			
			type:'POST',
			dataType:'json',
			url:'./addDoctorDirect',
	        data: {
	        	userId:userId,
	        	doctorName:referralId,
	        	degree:degree,
	        	visitFee:"0",
	        	roomNo:""
	        	
	        },
			success: function (data) {
				
				if(data.doctorList=='Something has wrong!'){
					alert(data);
				}
				else{
				  	doctorlist = data.doctorList.map(rowData => rowData.doctorName);
		        	
				  	 $("#referral").autocomplete({
			                source: doctorlist
			            });
				  	 
				  	 $("#cReferral").autocomplete({
			                source: doctorlist
			            });
				}
			
			}
			
			
		});
	}
}
function printTop() {

	var labId=$("#sLabId").val();
	var fiscalYear=$("#sFiscalYear").val();
	var cMonth=$("#sMonth").val();
	
	var i = 0;
	var value = 0;
	var resultList = [];

	$('.itemrow').each(function () {

		var id = $(this).attr("data-id");

		var c=$("#autoId-"+ id).val();
		console.log("autoId+"+c);
		
		var autoId = parseFloat(($("#autoId-"+ id).val() == '' ? "0" : $("#autoId-"+id).val()));
		var refundstatus=$("#topstatus-"+id).is(':checked') ? '1' : '0';
		
		console.log("refundstatus "+refundstatus);
		
		if(refundstatus=='1') {
			resultList[i] =autoId;
			i++;
		}

		
	});
	resultList = "(" + resultList + ")";

	var url = `printTopReportFromBilling/${labId}@${fiscalYear}@${cMonth}`;
	window.open(url, '_blank');
	
}

function CounterInfoDelete(){
	var userId = $("#user_hidden").val();
	var counter = gcounter;
	

	if(gcounter==''){
		alert("Information Incomplete");
	}
	else{
		
		
		$.ajax({
			
			type:'POST',
			dataType:'json',
			url:'./CounterInfoDelete',
	        data: {
	        	userId:userId,
	        	counter:counter
	        	
	        },
			success: function (data) {
				
				alert(data);
				allTextClear();
			}
			
			
		});
	}
}

function EditPostedBill(){
	
	var editStatus = $("#editStatus").val();
	
	if(editStatus=='1'){
		var userId = $("#user_hidden").val();
		var billType=$("#billType").val();
		var labId=$("#labbill").val();
		var cMonth=$("#labcMonth").val();
		
		var manualdiscount = $("#manualdiscount").val()==''?"0":$("#manualdiscount").val();
		var percentdiscount = $("#percentdiscount").val()==''?"0":$("#percentdiscount").val();
		var totalpayable = $("#totalpayable").val()==''?"0":$("#totalpayable").val();
		var perdiscount_taka = $("#perdiscount_taka").val()==''?"0":$("#perdiscount_taka").val();
		
		var patientname = $("#patientname").val();
		var regno = $("#runningpatient").val();
		var patientfiscalyear="";
		
		if(billType=='1'){
			patientfiscalyear = $("#patientFiscalYear").val();
			regno=$("#regNo").val();
			period=$("#period").val();
			
		}

		var mobile = $("#mobile").val();
		var age = $("#age").val();
		var month = $("#month").val();
		var day = $("#day").val();
		var sex = $("#sex").val();
		var address = $("#address").val();
		var bedcabin = $("#bedcabin").val();
		var remark = $("#remark").val();

		var referral_search = $("#referral").val();
		var referraldegree = $("#referraldegree").val();
		

		var referralcomission = $("#cReferral").val();
		var referralcomissiondegree = $("#referralcomissiondegree").val();
		
		var fiscalyear = $("#fiscalyear").val();
		
		var deliverydatetime = $("#deliverydatetime").val()==''?"0":$("#deliverydatetime").val();
		var extraCommission=$('#extraCommission').val()==''?"0":$('#extraCommission').val();
		
		
		if(labId=='' || patientname=='' || mobile=='' || sex=='' || sex=='0'){
			alert("Incformation Incomplete");
		}
		else{
			
			
			$.ajax({
				
				type:'POST',
				dataType:'json',
				url:'./EditPostedBill',
		        data: {
		        	userId:userId,
		        	billType:billType,
		        	patientname:patientname,
		        	regno:regno,
		        	mobile:mobile,
		        	age:age,
		        	month:month,
		        	day:day,
		        	sex:sex,
		        	bedcabin:bedcabin,
		        	referral_search:referral_search,
		        	referraldegree:referraldegree,
		        	referralcomission:referralcomission,
		        	referralcomissiondegree:referralcomissiondegree,
		        	extraCommission:extraCommission,
		        	manualdiscount:manualdiscount,
		        	percentdiscount:percentdiscount,
		        	totalpayable:totalpayable,
		        	perdiscount_taka:perdiscount_taka,
		        	address:address,
		        	remark:remark,
		        	labId:labId,
		        	cMonth:cMonth,
		        	deliverydatetime:deliverydatetime,
		        	fiscalyear:fiscalyear,
		        	patientfiscalyear:patientfiscalyear
		        	
		        },
				success: function (data) {
					
					alert(data);

				}
				
				
			});
		}
	}
	else{
		alert("You Have No Edit Permission");
	}
	

}





function setDuesValue(v){
	var paid=parseFloat($(v).val());
	var totalpayable = parseFloat($("#totalpayable").val()==''?"0":$("#totalpayable").val());
	var advance = parseFloat($("#advance").val()==''?"0":$("#advance").val());
	var refund = parseFloat($("#refund").val()==''?"0":$("#refund").val());
	var dues=totalpayable-(advance+paid-refund);
	
	if(dues>=0){
		$("#dues").val(dues);
	}
	else{
		$("#dues").val('0');
	}
}

function counterWisePendingTestWithPatientInfo(v){
	
	allTextClear();
	
	var btncounter=$(v).val();
	
	gcounter=btncounter;
	
	$("#btncounter1").css("background-color", "green");
	$("#btncounter2").css("background-color", "green");
	$("#btncounter3").css("background-color", "green");
	$("#btncounter4").css("background-color", "green");
	$("#btncounter5").css("background-color", "green");
	$("#btncounter6").css("background-color", "green");

	
	$(v).css("background-color", "red");
	
	var userId = $("#user_hidden").val();
	
	
	$.ajax({
		
		type:'POST',
		dataType:'json',
		url:'./counterWisePendingTestWithPatientInfo',
        data: {
        	userId:userId,
        	counter:gcounter
        	
        },
		success: function (data) {
			
	      	$("#labbill_table").empty();
			drawLabBillTable(data.result);
			//componentEnable(true);
			//$("#maintestlabbill").val('');
			
		}
		
		
	});

}



/*$(document).on('keypress',function(e) {
    if(e.which == 13) {
        alert('You pressed enter!');
    }
});*/

function counterValue(v){
	
	var btncounter=$(v).val();
	
	gcounter=btncounter;
	
	$("#btncounter1").css("background-color", "green");
	$("#btncounter2").css("background-color", "green");
	$("#btncounter3").css("background-color", "green");
	$("#btncounter4").css("background-color", "green");
	$("#btncounter5").css("background-color", "green");
	$("#btncounter6").css("background-color", "green");

	
	$(v).css("background-color", "red");


}


function billtype(v){
	var billType=$(v).val();
	
	
	
	if(billType=='2'){
		$("#runningpatient").prop( "readonly", true );
		$("#runningpatient").val('');
		componentEnable(false);
	}
	else{
		$("#runningpatient").prop( "readonly", false );
		componentEnable(true);
	}

}

function componentEnable(v){
	
	$("#patientname").prop( "disabled", v );
	$("#bedcabin").prop( "disabled", v );
	$("#referral_search").prop( "disabled", v );
	$("#age").prop( "disabled", v );
	$("#month").prop( "disabled", v );
	$("#day").prop( "disabled", v );
	$("#mobile").prop( "disabled", v );
	$("#address").prop( "disabled", v );
	$("#bedcabin").prop( "disabled", v );
	
	$("#percentdiscount").prop( "disabled", v );
	$("#manualdiscount").prop( "disabled", v );
	$("#paid").prop( "disabled", v );
	
}

function btnclearevent(){
	location.reload();
	allTextClear();
	
}

function allTextClear(){
	$('#referral').val(0);
	$('#cReferral').val(0);
    $('#cReferral').selectpicker('refresh');
	$('#referral').selectpicker('refresh');
	$("#billType").val('2');
	$("#runningpatient").val('');
	$("#patientname").val('');
	$("#mobile").val('');
	$("#maintest").val('');
	$("#labbill").val('');
	$("#age").val('');
	$("#month").val('');
	$("#day").val('');
	$("#sex").val('Male');
	$("#address").val('');
	$("#bedcabin").val('');
	$("#remark").val('');
	$("#advance").val('');

	$("#referraldegree").val('');

	$("#referralcomissiondegree").val('');
	$("#labbill_table").empty();
	$("#totalamount").val('');
	$("#percentdiscount").val('');
	$("#manualdiscount").val('');
	$("#paid").val('');
	$("#dues").val('');
	$("#perdiscount_taka").val('');
	$("#mdiscount_tata").val('');
	$("#totalpayable").val('');
	$("#dues").val('');
	$("#refund").val('');
	$("#deliverydate").val('');
	$("#deliverytime").val('');

}

function BillPost(){
	var userId = $("#user_hidden").val();
	var billType=$("#billType").val();
	
	
	var labId=$("#labbill").val();
	var find=$("#find").val();
	
	

	
	if(find=='1'){
		var dues=$("#dues").val();
		var paid=parseFloat($("#paid").val()=='0'?"0":$("#paid").val());
		var advance=parseFloat($("#advance").val()=='0'?"0":$("#advance").val());
		var refund=parseFloat($("#refund").val()=='0'?"0":$("#refund").val());
		var totalpayable=parseFloat($("#totalpayable").val()=='0'?"0":$("#totalpayable").val());
		var fiscalyear=$("#labfiscalyear").val();
		var cMonth=$("#labcMonth").val();
		
		console.log("cMonth "+cMonth);
		console.log("fiscalyear "+fiscalyear);
		
		var tpaid=(paid+advance)-refund;
		
		if(tpaid<=totalpayable){
			$.ajax({
				
				type:'POST',
				dataType:'json',
				url:'./DuePayment',
		        data: {
		        	userId:userId,
		        	labId:labId,
		        	paid:paid,
		        	dues:dues,
		        	advance:advance,
		        	fiscalyear:fiscalyear,
		        	totalpayable:totalpayable,
		        	cMonth:cMonth,
		        	billType:billType
		        },
				success: function (data) {
					
					//
					
					if(data=='Due Payment Successfully'){
						alert(data);
						labPrint(labId,fiscalyear,cMonth);
						allTextClear();
					}

				}
				
			});
		}
		else{
			alert("Due Payment Can't Be More Than Payable Amount");
		}
		

	}
	else if(find=='0'){

		var cMonth=$("#sMonth").val();
		
		var counter = gcounter;
		
		var testId = $("#testId").val();
		
		var testname="";

		   
		var patientname = $("#patientname").val();
		var regno = "";
		var period = "";
		
		var patientfiscalyear="";
		
		if(billType=='1'){
			patientfiscalyear = $("#patientFiscalYear").val();
			regno=$("#regNo").val();
			period=$("#period").val();
		}
		
		console.log("period "+period);
		
		var mobile = $("#mobile").val();
		var age = $("#age").val();
		var month = $("#month").val();
		var day = $("#day").val();
		var sex = $("#sex").val();
		console.log("sex "+sex);
		var address = $("#address").val();
		var bedcabin = $("#bedcabin").val();

		
		var referral_search = $("#referral").val();
		var referraldegree = $("#referraldegree").val();

		var referralcomission = $("#cReferral").val();
		var referralcomissiondegree = $("#referralcomissiondegree").val();
		var extraCommission=$('#extraCommission').val()==''?"0":$('#extraCommission').val();
		var fiscalyear = $("#fiscalyear").val();
		var remark = $("#remark").val();
		
		var percentdiscount = $("#percentdiscount").val()==''?"0":$("#percentdiscount").val();
		var manualdiscount = $("#manualdiscount").val()==''?"0":$("#manualdiscount").val();
		

		
		var totalamount = $("#totalamount").val()==''?"0":$("#totalamount").val();
		var paid = $("#paid").val()==''?"0":$("#paid").val();
		var dues = $("#dues").val()==''?"0":$("#dues").val();
		var advance = $("#advance").val()==''?"0":$("#advance").val();
		var perdiscount_taka = $("#perdiscount_taka").val()==''?"0":$("#perdiscount_taka").val();
		var mdiscount_tata = $("#mdiscount_tata").val()==''?"0":$("#mdiscount_tata").val();
		var totalpayable = $("#totalpayable").val()==''?"0":$("#totalpayable").val();
		var refund = $("#refund").val()==''?"0":$("#refund").val();
		var deliverydatetime = $("#deliverydatetime").val()==''?"0":$("#deliverydatetime").val();
		
		var dtime = $("#dtime").val()==''?"0":$("#dtime").val();

		deliverydatetime=deliverydatetime+' '+dtime;
		
		if(counter!='0'){
			if(patientname!=''){
				if(mobile!=''){
					if(referral_search!=''){
							
						if(referralcomission!=''){
							if(sex!='' || sex!='0'){
								$.ajax({
									
									type:'POST',
									dataType:'json',
									url:'./BillPost',
							        data: {
							        	userId:userId,
							        	labId:labId,
							        	billType:billType,
							        	testId:testId,
							        	testname:testname,
							        	counter:counter,
							        	patientname:patientname,
							        	patientfiscalyear:patientfiscalyear,
							        	period:period,
							        	regno:regno,
							        	mobile:mobile,
							        	age:age,
							        	month:month,
							        	day:day,
							        	sex:sex,
							        	bedcabin:bedcabin,
							        	referral_search:referral_search,
							        	referraldegree:referraldegree,
							        	referralcomission:referralcomission,
							        	referralcomissiondegree:referralcomissiondegree,
							        	extraCommission:extraCommission,
							        	fiscalyear:fiscalyear,
							        	percentdiscount:percentdiscount,
							        	manualdiscount:manualdiscount,
							        	address:address,
							        	totalamount:totalamount,
							        	paid:paid,
							        	dues:dues,
							        	advance:advance,
							        	perdiscount_taka:perdiscount_taka,
							        	mdiscount_tata:mdiscount_tata,
							        	totalpayable:totalpayable,
							        	refund:refund,
							        	deliverydatetime:deliverydatetime,
							        	remark:remark
							        	
							        },
									success: function (data) {
										
										
										if(data!='Create occured while bill posting'){
											
											alert("Bill Post Successfully");
											console.log("data "+data);
											console.log("fiscalyear "+fiscalyear);
											
				
											labPrint(data,fiscalyear,cMonth);
											allTextClear();
											location.reload();
										}
										
									}
									
									
								});	
							}
							else{
								alert("Provide Patient Gender");
							}
						}
						else{
							alert("Provide referral commission name");
						}
					
					}
					else{
						alert("please provide referral name");
					}
				}
				else{
					alert("please provide mobile");
				}
			}
			else{
				alert("please provide patient name");
			}
		}
		else{
			alert("At first provide any counter");
		}
		

	}
	
}
function AddPatientInforWithTest(){
	var userId = $("#user_hidden").val();
	var billType=$("#billType").val();
	var find=$("#find").val();
	console.log("find "+find);
	var counter = gcounter;
	
	var period=$("#period").val();
	
	
	var labId ="";
	if(find=='0'){
		labId=$("#labId").val();
	}
	else{
		labId=$("#labbill").val();
	}
	
	
	var testId = $("#testId").val();
	var testname = $("#testId").text();
	   
	var patientname = $("#patientname").val();
	var regno = "";
	
	var patientfiscalyear="";
	var cMonth="";
	
	if(find=='1'){
		cMonth=$("#labcMonth").val();
	}
	else{
		cMonth=$("#sMonth").val();
	}

	
	if(billType=='1'){
		patientfiscalyear = $("#patientFiscalYear").val();
		regno = $("#regNo").val();
	}
	
	var mobile = $("#mobile").val();
	var age = $("#age").val();
	var month = $("#month").val();
	var day = $("#day").val();
	var sex = $("#sex").val();
	console.log("Psex "+sex);
	var address = $("#address").val();
	var bedcabin = $("#bedcabin").val();
	
	var referral_search = $("#referral").val();
	
	console.log("referral_search "+referral_search);
	
	var referraldegree = $("#referraldegree").val();

	var referralcomission = $("#cReferral").val();
	var referralcomissiondegree = $("#referralcomissiondegree").val();
	var fiscalyear ="";
	
	
	if(find=='1'){
		fiscalyear = $("#labfiscalyear").val();
	}
	else{
		fiscalyear = $("#fiscalyear").val();
	}
	
	var percentdiscount = $("#percentdiscount").val()==''?"0":$("#percentdiscount").val();
	var manualdiscount = $("#manualdiscount").val()==''?"0":$("#manualdiscount").val();
	
	
	
	if(counter!='0'){
		if(testId!=''){
			if(patientname!='0'){
				if(mobile!=''){
					
					if(sex!='' || sex!='0'){
						
						if(find=='1'){
							if(confirm("Are you sure to add new test?")){
								$.ajax({
									
									type:'POST',
									dataType:'json',
									url:'./AddPatientInforWithTest',
							        data: {
							        	userId:userId,
							        	labId:labId,
							        	billType:billType,
							        	find:find,
							        	testId:testId,
							        	testname:testname,
							        	counter:counter,
							        	patientname:patientname,
							        	patientfiscalyear:patientfiscalyear,
							        	period:period,
							        	regno:regno,
							        	mobile:mobile,
							        	age:age,
							        	month:month,
							        	day:day,
							        	sex:sex,
							        	bedcabin:bedcabin,
							        	referral_search:referral_search,
							        	referraldegree:referraldegree,
							        	referralcomission:referralcomission,
							        	referralcomissiondegree:referralcomissiondegree,
							        	fiscalyear:fiscalyear,
							        	percentdiscount:percentdiscount,
							        	manualdiscount:manualdiscount,
							        	address:address,
							        	cMonth:cMonth
							        	
							        },
									success: function (data) {
										
										console.log("data "+data.result);
										if(data.result=='Doplicate Test Never Allow'){
											alert('Doplicate Test Never Allow');
										}
										else if(data.result=='Invalid Test Never Allow') {
											alert('Invalid Test Never Allow');
										}
										else{
											$("#testId").val('');
									      	$("#labbill_table").empty();
									    	$("#labbill_invetable").empty();
											drawLabBillTable(data.result);
											//componentEnable(true);
											$("#maintestlabbill").val('');
											
											if(find=='1'){
												billAutoSave();
											}
										}
			
									}
							
								});
							}
						}
						else{
							$.ajax({
								
								type:'POST',
								dataType:'json',
								url:'./AddPatientInforWithTest',
						        data: {
						        	userId:userId,
						        	labId:labId,
						        	billType:billType,
						        	find:find,
						        	testId:testId,
						        	testname:testname,
						        	counter:counter,
						        	patientname:patientname,
						        	patientfiscalyear:patientfiscalyear,
						        	period:period,
						        	regno:regno,
						        	mobile:mobile,
						        	age:age,
						        	month:month,
						        	day:day,
						        	sex:sex,
						        	bedcabin:bedcabin,
						        	referral_search:referral_search,
						        	referraldegree:referraldegree,
						        	referralcomission:referralcomission,
						        	referralcomissiondegree:referralcomissiondegree,
						        	fiscalyear:fiscalyear,
						        	percentdiscount:percentdiscount,
						        	manualdiscount:manualdiscount,
						        	address:address,
						        	cMonth:cMonth
						        	
						        },
								success: function (data) {
									
									console.log("data "+data.result);
									if(data.result=='Doplicate Test Never Allow'){
										alert('Doplicate Test Never Allow');
									}
									else if(data.result=='Invalid Test Never Allow') {
										alert('Invalid Test Never Allow');
									}
									else{
										$("#testId").val('');
								      	$("#labbill_table").empty();
								    	$("#labbill_invetable").empty();
										drawLabBillTable(data.result);
										//componentEnable(true);
										$("#maintestlabbill").val('');
										
										if(find=='1'){
											billAutoSave();
										}
									}
		
								}
						
							});
						}
					
					}
					else{
						alert("Provide Patient Gender");
					}
		
				}
				else{
					alert("please provide mobile");
				}
			}
			else{
				alert("please provide patient name");
			}
		}
		else{
			alert("please provide test name");
		}
	}
	else{
		alert("At frist select any counter");
	}

}



function setLabBillData(labid,fiscalyear,cmonth){
	
	console.log("cmonth "+cmonth);

	allTextClear();
	$('#labBillList').modal('hide');
	$.ajax({
		
		type:'GET',
		dataType:'json',
		url:'./LabIdWiseTestAndPatientInfo',
		data:{
			labid:labid,
			fiscalyear:fiscalyear,
			cmonth:cmonth
		},
		success: function (data) {
			
			if(data.result!='No entry'){
				$("#labbill_table").empty();
				drawLabBillTable(data.result);
				$("#labbill").val(labid);
				$('#labId').val(labid);
				$('#sLabId').val(labid);
			}
			
	   /*   	$("#labbill_table").empty();
			drawLabBillTable(data.result);
			$("#labbill").val(labid);*/

		}

	});
}

function drawLabBillTable(data) {
    var rows = [];
    var actualTestAmount=0;
    for (var i = 0; i < data.length; i++) {
        rows.push(drawRowLabBillTable(data[i],i+1));
        if(data[i].type=='1'){
        	 actualTestAmount=actualTestAmount+parseFloat(data[i].qty)*parseFloat(data[i].rate);
        }
       
    }
    
    $('#actualTestAmount').val(parseFloat(actualTestAmount).toFixed(2));
    
    var percentDiscount= parseFloat($('#percentdiscount').val()==''?"0":$('#percentdiscount').val());
    
    var perDiscount=actualTestAmount*percentDiscount/100;
    
    var manualdiscount= parseFloat($('#manualdiscount').val()==''?"0":$('#manualdiscount').val());
    
    var totalDiscount=perDiscount+manualdiscount;
    
    var grandTotal=parseFloat($('#totalamount').val()==''?"0":$('#totalamount').val());
    
    var payable=grandTotal-totalDiscount;
    
    console.log("actualTestAmount "+actualTestAmount);
    console.log("grandTotal "+grandTotal);
    console.log("manualdiscount "+manualdiscount);
    console.log("totalDiscount "+totalDiscount);
    
    $("#totalpayable").val(parseFloat(payable).toFixed(2));
    
    var advancePaid=parseFloat($('#advance').val()==''?"0":$('#advance').val());
    var paid=parseFloat($('#paid').val()==''?"0":$('#paid').val());
    var refund=parseFloat($('#refund').val()==''?"0":$('#refund').val());
    
    var due=payable-(advancePaid+paid-refund);
    
    $("#dues").val(parseFloat(due).toFixed(2));

    $("#labbill_table").append(rows);
}

function drawRowLabBillTable(rowData,c) {
		
		var row = $("<tr class='itemrow' data-id='"+c+"'/>")
		
		row.append($("<td> <input type='hidden' value='"+rowData.autoId+"' id='autoId-"+c+"' /> " + c+ "</td>"));
		row.append($("<td>" + rowData.testname+ "</td>"));
		row.append($("<td> <input type='hidden' value='"+parseFloat(rowData.qty).toFixed(0)+"' class='qty-'"+c+" id='qty-"+c+"' /> " + parseFloat(rowData.qty).toFixed(0) + "</td>"));
		row.append($("<td> <input type='hidden' value='"+parseFloat(rowData.rate).toFixed(2)+"' id='rate-"+c+"' /> " + parseFloat(rowData.rate).toFixed(2) + "</td>"));
		row.append($("<td>" + parseFloat(rowData.discountAmount).toFixed(2) + "</td>"));
		row.append($("<td> <input type='hidden' value='"+ parseFloat(rowData.payable).toFixed(2)+"' id='payable-"+c+"' />" + parseFloat(rowData.payable).toFixed(2) + "</td>"));
		row.append($("<td><button id='' onclick='testDelete("+rowData.testId+","+rowData.type+")' class='' ><i class='fa fa-trash'></i></button></td>"));
		row.append($("<td><input class='form-check-input' type='checkbox' id='refundstatus-"+c+"' value=''></td>"));
		row.append($("<td><input class='form-check-input' type='checkbox' id='topstatus-"+c+"' value=''></td>"));
		
		
		console.log("perDis"+rowData.percentdiscount);
		console.log("manDis"+rowData.TotalMDiscounTaka);
		
		$("#percentdiscount").val(parseFloat(rowData.percentdiscount==null?"0":rowData.percentdiscount).toFixed(2));
		$("#manualdiscount").val(parseFloat(rowData.TotalMDiscounTaka).toFixed(2));
		
	
		
/*		if(rowData.percentdiscount!=null || rowData.percentdiscount!='0' || rowData.percentdiscount!='0.0000' || rowData.percentdiscount!='NaN'){
			$("#percentdiscount").val(parseFloat(rowData.percentdiscount).toFixed(2));
		}
		
		if(rowData.TotalMDiscounTaka!=null || rowData.TotalMDiscounTaka!='0' || rowData.TotalMDiscounTaka!='0.0000' || rowData.TotalMDiscounTaka!='NaN'){
			$("#manualdiscount").val(parseFloat(rowData.TotalMDiscounTaka).toFixed(2));
		}*/
		
		
		
		
		$("#totalamount").val(parseFloat(rowData.TotalAmount).toFixed(2));
		$("#perdiscount_taka").val(parseFloat(rowData.TotalPrDiscounTaka).toFixed(2));
		$("#mdiscount_tata").val(parseFloat(rowData.TotalMDiscounTaka).toFixed(2));
		$("#totalpayable").val(parseFloat(rowData.TotalPayableTaka).toFixed(2));
		
		if(rowData.countersearch=='1'){
			$("#patientname").val(rowData.patientname);
			$("#mobile").val(rowData.mobile);
			$("#age").val(rowData.age);
			$("#month").val(rowData.month);
			$("#day").val(rowData.day);
			if(rowData.sex!=''){
				$("#sex").val(rowData.sex);
			}
			
			$("#address").val(rowData.address);
			$("#bedcabin").val(rowData.bedcabin);
			$("#cReferral").val(rowData.referralcomission);
		    //$('#cReferral').selectpicker('refresh');
			
		 //   $('#referral').selectpicker('refresh');
			$("#referral").val(rowData.referral_search);
		
			  
			$("#referraldegree").val(rowData.referraldegree);
			$("#referralcomissiondegree").val(rowData.referralcdegree);
			$("#extraCommission").val(rowData.extraCommission);
			$("#remark").val(rowData.remark)
			
			$("#advance").val(parseFloat(rowData.paid==null?"0":rowData.paid).toFixed(2));
			$("#find").val(rowData.find);
			$("#refund").val(rowData.refund);
			$("#labfiscalyear").val(rowData.fiscalyear);
			$("#labcMonth").val(rowData.cMonth);
			
			var totalPayable=parseFloat($('#totalpayable').val());
			var advancePaid=parseFloat($('#advance').val());
			
			console.log("totalPayable "+totalPayable);
			console.log("advancePaid "+advancePaid);
			
			var due=totalPayable-advancePaid;
			
			$('#dues').val(parseFloat(due).toFixed(2));
		}
		
		console.log("billtype "+rowData.billtype);
		$("#billType").val(rowData.billtype);
		
		if(rowData.billtype=='1'){
			componentEnable(true);
		}
		else{
			componentEnable(false);
		}
		
		return row;
}


function testDelete(testId,type){
	var userId = $("#user_hidden").val();
	var fiscalyear=$("#fiscalyear").val();
	var find=$("#find").val();
	var regNo=$("#regNo").val();
	var labId=$("#labbill").val()==''?"0":$("#labbill").val();
	var counter = (find=='0'?gcounter:"1");

	
	

	if(find=='1'){
		var cmonth=$("#labcMonth").val();
		var deleteStatus = $("#deleteStatus").val();
		if(deleteStatus=='1'){
			if(confirm("Are you sure to delete?")){
				$.ajax({
					
					type:'POST',
					dataType:'json',
					url:'./deleteLabTestData/',
					data:{
						userId:userId,
						fiscalyear:fiscalyear,
						find:find,
						counter:counter,
						testId:testId,
						type:type,
						regNo:regNo,
						cmonth:cmonth,
						labId:labId
					},
					success: function (data) {
						$("#labcMonth").val(cmonth);	
				    	$("#labbill_table").empty();
				    	
				    	console.log("resultdelete "+data.result);
						drawLabBillTable(data.result);
						
						if(labId!='0'){
							billAutoSave();
						}
					}	
				});
				
			}
		}
		else{
			alert("You have no delete permission");
		}
	}
	else{
		if(confirm("Are you sure to delete?")){
			
			var cmonth=$("#sMonth").val();
			
			$.ajax({
				
				type:'POST',
				dataType:'json',
				url:'./deleteLabTestData/',
				data:{
					userId:userId,
					fiscalyear:fiscalyear,
					find:find,
					counter:counter,
					testId:testId,
					type:type,
					regNo:regNo,
					cmonth:cmonth,
					labId:labId
				},
				success: function (data) {
					$("#labcMonth").val(cmonth);	
			    	$("#labbill_table").empty();
			    	
			    	console.log("resultdelete "+data.result);
					drawLabBillTable(data.result);
					
					if(labId!='0'){
						billAutoSave();
					}
				}	
			});
			
		}
	}
	

	

}


function billAutoSave(){
	var userId = $("#user_hidden").val();
	var billType=$("#billType").val();
	
	
	var labId=$("#labbill").val();
	var fiscalYear=$("#labfiscalyear").val();
	var cMonth=$("#labcMonth").val();
	
	console.log("labId "+labId);
	console.log("fiscalYear "+fiscalYear);
	console.log("cMonth "+cMonth);
	
	var totalamount=$("#totalamount").val();
	var percentdiscount=$("#percentdiscount").val();
	var manualdiscount=$("#manualdiscount").val();
	var perdiscount_taka=$("#perdiscount_taka").val();
	var totalpayable=$("#totalpayable").val();
	
	$.ajax({
		
		type:'POST',
		dataType:'json',
		url:'./billAutoSave/',
		data:{
			userId:userId,
			labId:labId,
			fiscalyear:fiscalYear,
			cMonth:cMonth,
			totalamount:totalamount,
			percentdiscount:percentdiscount,
			manualdiscount:manualdiscount,
			perdiscount_taka:perdiscount_taka,
			totalpayable:totalpayable,
			counter:1,
			
		},
		success: function (data) {
			if(data!='Create occured while bill posting'){
				alert("Bill Modify Successfully");
			}
			else{
				alert(data);
			}
			
		}	
	});
	
	
}

function setData(patientId){
	$.ajax({
		
		type:'GET',
		dataType:'json',
		url:'./getIndoorPatientInformation/'+patientId,
		success: function (data) {
			
			setPatientDetailsInformation(data.result);
		}	
	});
}

function setPatientDetailsInformation(dataList){
	
	
	$('#indoorPaitentList').modal('hide');
	 for (var i = 0; i < dataList.length; i++) {
		  var item = dataList[i];
		  
		  $('#billType').val(1);
		  
		  componentEnable(true);
		  
		  $('#regNo').val(item.regNo);
		  
		  $('#referralcomissiondegree').val(item.consultantDegree);
		  $('#cReferral').val(item.refferId);
		  $('#referraldegree').val(item.refferDegree);
		  $('#referral').val(item.consultantId);
		  
		  $('#referral').val(item.refferId).change();
		  $('#referral').selectpicker('refresh');
		  
		  $('#cReferral').val(item.consultantId).change();
		  $('#cReferral').selectpicker('refresh');
		  
		  $('#bedcabin').val(item.seatname);
		  $('#address').val(item.address);
		  $('#sex').val(item.sex);
		  $('#day').val(item.day);
		  $('#month').val(item.month);
		  $('#age').val(item.age);
		  
		  
		  console.log("patientFiscalYear "+item.patientfiscalyear);
		  
		  $('#mobile').val(item.mobile);
		  $('#patientFiscalYear').val(item.patientfiscalyear);
		  $('#period').val(item.period);
		  $('#patientname').val(item.patientname);

	 }
}


function setPayable(){
	
	
	var discountStatus = $("#discountStatus").val();
	
	if(discountStatus=='1'){
		var totalamount = parseFloat($("#totalamount").val()==''?"0":$("#totalamount").val());
		var actualTestAmount = parseFloat($("#actualTestAmount").val()==''?"0":$("#actualTestAmount").val());
		
		var percentdiscount = parseFloat($("#percentdiscount").val()==''?"0":$("#percentdiscount").val());
		var manualdiscount = parseFloat($("#manualdiscount").val()==''?"0":$("#manualdiscount").val());
		

		
		
		var perdiscount=(actualTestAmount*percentdiscount)/100;
		
		$("#perdiscount_taka").val(perdiscount);
		$("#mdiscount_tata").val(manualdiscount);
		
		var totaldiscount=perdiscount+manualdiscount;
		var totalpayable=totalamount-totaldiscount;
		
		
		$("#totalpayable").val(totalpayable);
		
		var paid=parseFloat($('#paid').val()==''?"0":$('#paid').val());
		var totalpayable = parseFloat($("#totalpayable").val()==''?"0":$("#totalpayable").val());
		var advance = parseFloat($("#advance").val()==''?"0":$("#advance").val());
		var refund = parseFloat($("#refund").val()==''?"0":$("#refund").val());
		var dues=totalpayable-(advance+paid-refund);
		
		console.log("paid "+paid);
		console.log("totalpayable "+totalpayable);
		console.log("advance "+advance);
		console.log("refund "+refund);
		console.log("dues "+dues);
		
		if(dues>=0){
			$("#dues").val(dues);
		}
		else{
			$("#dues").val('0');
		}
	}
	else{
		$("#percentdiscount").val('0');
		$("#manualdiscount").val('0');
		$("#perdiscount_taka").val('0');
		$("#mdiscount_tata").val('0');
		
		
		var totalamount = parseFloat($("#totalamount").val()==''?"0":$("#totalamount").val());
		var actualTestAmount = parseFloat($("#actualTestAmount").val()==''?"0":$("#actualTestAmount").val());
		
		var percentdiscount = parseFloat($("#percentdiscount").val()==''?"0":$("#percentdiscount").val());
		var manualdiscount = parseFloat($("#manualdiscount").val()==''?"0":$("#manualdiscount").val());
		

		
		
		var perdiscount=(actualTestAmount*percentdiscount)/100;
		
		$("#perdiscount_taka").val(perdiscount);
		$("#mdiscount_tata").val(manualdiscount);
		
		var totaldiscount=perdiscount+manualdiscount;
		var totalpayable=totalamount-totaldiscount;
		
		
		$("#totalpayable").val(totalpayable);
		
		alert("You have no discount permission");
	}
	

	

}

function btnRefundEvent(){
	var userId = $("#user_hidden").val();
	var labId=$("#labbill").val();
	var find=$("#find").val();

	var fiscalyear=$("#labfiscalyear").val();
	var cMonth=$("#labcMonth").val();
	
	var TotalCharge = $("#totalamount").val()==''?"0":$("#totalamount").val();
	var totalpayable=$("#totalpayable").val()==''?"0":$("#totalpayable").val();;
	var percentdiscount=$("#percentdiscount").val()==''?"0":$("#percentdiscount").val();;
	var manualdiscount=$("#manualdiscount").val()==''?"0":$("#manualdiscount").val();;
	var advance=$("#advance").val()==''?"0":$("#advance").val();;
	var refund=$("#refund").val()==''?"0":$("#refund").val();;
	var billType='2';
	
	var i = 0;
	var value = 0;
	var resultList = [];
	
	var refundStatus = $("#refundStatus").val();
	
	if(refundStatus=='1'){
		$('.itemrow').each(function () {

			var id = $(this).attr("data-id");

			var c=$("#autoId-"+ id).val();
			console.log("autoId+"+c);
			
			var autoId = parseFloat(($("#autoId-"+ id).val() == '' ? "0" : $("#autoId-"+id).val()));
			var qty = parseFloat(($("#qty-"+ id).val() == '' ? "0" : $("#qty-"+id).val()));
			var rate = parseFloat(($("#rate-"+ id).val() == '' ? "0" : $("#rate-"+id).val()));
			var payable = parseFloat(($("#payable-"+ id).val() == '' ? "0" : $("#payable-"+id).val()));
			var refundstatus=$("#refundstatus-"+id).is(':checked') ? '1' : '0';
			
			resultList[i] = autoId + "*" + qty + "*" + rate + "*"+payable+"*"+ refundstatus;
			i++;
		});
		resultList = "[" + resultList + "]"
		
		
		$.ajax({
			
			type:'POST',
			dataType:'json',
			url:'./refundTransaction/',
			data:{
				userId:userId,
				labId:labId,
				fiscalyear:fiscalyear,
				cMonth:cMonth,
				resultList:resultList,
				TotalCharge:TotalCharge,
				totalpayable:totalpayable,
				percentdiscount:percentdiscount,
				manualdiscount:manualdiscount,
				advance:advance,
				refund:refund,
				billType:billType
				
			},
			success: function (data) {
					
			
				if(data.resultsucess=='Refund Success'){
					alert(data.resultsucess);
			    	$("#labbill_table").empty();
					drawLabBillTable(data.result);
				}
				else{
					alert(data.result);
				}

			}	
		});
	}
	else{
		alert("You have no refund permission");
	}


	
}
//Barcode.............


function LabNoBlank(){
	$("#labfind").val('');
	
}

function findLabNo(){
	allTextClear();
	//findLabNo
var value = $("#labfind").val();
	
	
	var labId="";
	var fiscalYear ="";
	var tempValue=value.slice(0,1);
	
	
	if(tempValue=='0'){
		 fiscalYear=value.slice(1,5);
		 labId=value.slice(7);	
	}
	else if(tempValue!='0'){
		//var value1 = $("#labfind").val();
		
		fiscalYear=value.slice(0,4);
		labId=value.slice(6);
	}
	
	

	
	$.ajax({
		
		type:'GET',
		dataType:'json',
		url:'./LabIdWiseTestAndPatientInfo',
		data:{
			labid:labId,
			fiscalyear:fiscalYear
		},
		success: function (data) {
			
	      	$("#labbill_table").empty();
			drawLabBillTable(data.result);
			$("#labbill").val(labId);
			document.getElementById("labfind").select();
			$("#labfind").val('');
			//labPrint(labId,fiscalYear);

		}

	});
}


$(document).ready(function () {
	$("input:text").focus(function () { $(this).select(); });
});

$(document).ready(function () {
	$("#labsearch").on("keyup", function () {
		var value = $(this).val().toLowerCase();
		$("#labbilllist_table tr").filter(function () {
			$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
		});
	});
});


let inputStart, inputStop;

$("#labfind")[0].onpaste = e => e.preventDefault();
// handle a key value being entered by either keyboard or scanner
let lastInput;

let checkValidity = () => {
    if ($("#labfind").val().length < 4) {
        alert("Scan Barcode Only Or Please Uncheck The Barcode Permission.");
        $("#labfind").val('');
    }else{
    	findLabNo();
    }
    timeout = false
}

let timeout = false;
$("#labfind").keypress(function (e) {
    if (performance.now() - lastInput > 1000) {
        $("#labfind").val('')
    }
    lastInput = performance.now();
    if (!timeout) {
        timeout = setTimeout(checkValidity, 200)

    }
});


/*let idListMicroNew = ["patientname","address","age","month","day","sex"];
idListMicroNew.forEach((id,index)=>{
  console.log("execute",id);
  $('#'+id).keyup(function(event){
    console.log("execute",event.keyCode);
    if(event.keyCode ===13){
      event.preventDefault();
      $("#"+idListMicroNew[index+1]).focus();
    }
    
  });
})*/



$(document).on('keypress', 'select', function (e) {
    if (e.which == 13) {
        e.preventDefault();
        // Get all focusable elements on the page
        var $canfocus = $(':focusable');
        var index = $canfocus.index(document.activeElement) + 1;
        if (index >= $canfocus.length) index = 0;
        $canfocus.eq(index).focus();
    }
});


document.querySelector('#patientname').addEventListener('keypress', function (e) {
    if (e.key === 'Enter') {
    	document.getElementById('age').focus();
    	e.preventDefault(); 
    }
});


document.querySelector('#address').addEventListener('keypress', function (e) {
    if (e.key === 'Enter') {
    	document.getElementById('age').focus();
    	e.preventDefault(); 
    }
});

document.querySelector('#age').addEventListener('keypress', function (e) {
    if (e.key === 'Enter') {
    	document.getElementById('month').focus();
    	e.preventDefault(); 
    }
});

document.querySelector('#month').addEventListener('keypress', function (e) {
    if (e.key === 'Enter') {
    	document.getElementById('day').focus();
    	e.preventDefault(); 
    }
});

document.querySelector('#day').addEventListener('keypress', function (e) {
    if (e.key === 'Enter') {
    	document.getElementById('sex').focus();
    	e.preventDefault(); 
    }
});


document.querySelector('#mobile').addEventListener('keypress', function (e) {
    if (e.key === 'Enter') {
    	document.getElementById('referral').focus();
    	e.preventDefault(); 
    }
});

document.querySelector('#percentdiscount').addEventListener('keypress', function (e) {
    if (e.key === 'Enter') {
    	document.getElementById('manualdiscount').focus();
    	 e.preventDefault(); 
    }
});

document.querySelector('#manualdiscount').addEventListener('keypress', function (e) {
    if (e.key === 'Enter') {
    	document.getElementById('paid').focus();
    	e.preventDefault(); 
    }
});

document.querySelector('#paid').addEventListener('keypress', function (e) {
    if (e.key === 'Enter') {
    	BillPost();
    }
});

document.querySelector('#referral').addEventListener('keypress', function (e) {
    if (e.key === 'Enter') {
    	document.getElementById('cReferral').focus();
    	e.preventDefault(); 
    }
});

document.querySelector('#cReferral').addEventListener('keypress', function (e) {
    if (e.key === 'Enter') {
    	document.getElementById('testId').focus();
    }
});


document.querySelector('#testId').addEventListener('keypress', function (e) {
	
	
    if (e.key === 'Enter') {
    	AddPatientInforWithTest();
    }
});

/*document.querySelector('#sLabId').addEventListener('keypress', function (e) {
	
	console.log("key "+e.key);
    if (e.key === 'Enter') {
    	AddPatientInforWithTest();
    }
});*/


$( "#sLabId" ).keyup(function(e) {
	
	 if(e.key==='ArrowDown' || e.key==='ArrowUp') {
		console.log("labId "+labId);
		var labId=$("#sLabId").val();
		var fiscalyear=$("#sFiscalYear").val();
		var cmonth=$("#sMonth").val();
		setLabBillData(labId,fiscalyear,cmonth);
	 }
	 
});


$("#testId" ).keydown(function(e) {
	
	 if(e.key==='Tab') {
		 document.getElementById('percentdiscount').focus();
		 e.preventDefault(); 
	 }
	 
});





/*$("#testId").on('keydown', 'input.detectTab', function(e) { 
	  var keyCode = e.keyCode || e.which; 
	  console.log("keyCode "+keyCode);
	  if (keyCode == 9) { 
	    e.preventDefault(); 
	    // call custom function here
	    alert("Tap");
	  } 
	});
	*/

/*	document.querySelector('#testId').addEventListener('keypress', function (e) {
		var keyCode = e.keyCode || e.which; 
		
	});*/
	

/*$('#referral').on('changed.bs.select', function (e, clickedIndex, isSelected, previousValue) {
 	$("#cReferral").focus();
});

$('#cReferral').on('changed.bs.select', function (e, clickedIndex, isSelected, previousValue) {
 	$("#testId").focus();
});*/

/*$('#testId').on('changed.bs.select', function (e, clickedIndex, isSelected, previousValue) {
	var keyCode = e.keyCode || e.which; 
	console.log("isSelected "+isSelected);
	console.log("previousValue "+previousValue);
	//AddPatientInforWithTest();
});*/

/*$("#testId").on('keydown', 'select', function(e) { 
	  var keyCode = e.keyCode || e.which; 

	  if (keyCode == 9) { 
	    e.preventDefault(); 
	    // call custom function here
	  } 
	});
*/

var today = new Date();
document.getElementById("deliverydatetime").value = today.getFullYear() + '-' + ('0' + (today.getMonth() + 1)).slice(-2) + '-' + ('0' + today.getDate()).slice(-2);

