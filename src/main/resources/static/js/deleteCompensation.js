function deletedd(idCompensation) {
	swal({
		  title: "Are you sure?",
		  text: "If you delete this, you don´t see the compensation anymore",
		  icon: "warning",
		  buttons: true,
		  dangerMode: true,
		})
		.then((OK) => {
		  if (OK) {
			  $.ajax({
				 url:"/deleted/"+idCompensation,
				 success: function(res) {
					console.log(res);
				},			
			  });
		    swal("Poof! The compensation has been deleted!", {
		      icon: "success",
		    }).then((ok)=>{
		    	if(ok){
		    		location.href="/viewAllCompensations";
		    	}
		    });
		  } 
		});
}