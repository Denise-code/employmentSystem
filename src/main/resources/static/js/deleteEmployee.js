function deleted(idEmployee) {
	console.log(idEmployee);
	swal({
		  title: "Are you sure?",
		  text: "If you delete this employee, you don´t see the information anymore!",
		  icon: "warning",
		  buttons: true,
		  dangerMode: true,
		})
		.then((OK) => {
		  if (OK) {
			  $.ajax({
				 url:"/delete/"+idEmployee,
				 success: function(res) {
					console.log(res);
				},			
			  });
		    swal("Poof! The employee has been deleted!", {
		      icon: "success",
		    }).then((ok)=>{
		    	if(ok){
		    		location.href="/viewAddEmployee";
		    	}
		    });
		  } 
		});
}












