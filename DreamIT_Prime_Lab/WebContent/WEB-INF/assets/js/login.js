window.onload = () => {
	setPaymentMessage();
	
}


function setPaymentMessage(){
	
	var today = new Date();
	var dd = today.getDate();

	var mm = today.getMonth()+1; 
	var yyyy = today.getFullYear();
	if(dd<10) 
	{
	    dd='0'+dd;
	} 

	if(mm<10) 
	{
	    mm='0'+mm;
	}
	
	today = dd+'-'+mm+'-'+yyyy;
	console.log(today);
	
	
/*	if(dd=='01' || dd=='02' || dd=='03' || dd=='04' || dd=='05'){
		$('#softwarePaymentStatus').val('Paid');
		$('#paymentText').text("Please contact with software developer team,due to monthly payment your software will be stop after 05-01-2022");
	}
	else{
		$('#softwarePaymentStatus').val('Due');
		$('#paymentText').text("Please contact with software developer team,due to payment we stop our system");
		
		//Paid Complete
		$('#softwarePaymentStatus').val('Paid');
		$('#paymentText').text("");
	}*/
	
	
	//Paid Complete
	$('#softwarePaymentStatus').val('Paid');
	$('#paymentText').text("");
	
/*	//When need system stop;
	$('#softwarePaymentStatus').val('Due');
	$('#paymentText').text("Please contact with software developer team,due to payment we stop our system");
	*/
	//
	
}