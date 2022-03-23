	let tabla = document.getElementById('table');
	let filas = tabla.getElementsByTagName('tbody')[0];
	let firstName = document.getElementById('firstName');
	let lastName = document.getElementById('lastName');
	let position = document.getElementById('position');

	console.log(firstName.value);
	console.log(lastName.value);
	console.log(position.value);
	
	let num = filas.children.length;
	if(firstName.value === "" && lastName.value === "" && position.value === ""){
		
	}else{
		if(num == 0 ){
			swal({
			  icon: "warning",
			  text: "0 results found",
			  value: true,
			  visible: true,
			  className: "",
			  closeModal: true,
			})
		}
	}
