const form = document.getElementById('form');
const inputs = document.querySelectorAll('#form input');

//Get date


let today = new Date();
let day = today.getDate();
let month = today.getMonth();
let year = today.getFullYear();

let minyear = year-90;

  $(document).ready(function(){
    $('#birthDate').datepicker({
	format: 'yyyy-mm-dd',
	minDate: new Date(minyear,0,1),
	maxDate: new Date(year, month, day)
	});
  });



//

const expresions = {
	text: /^[a-zA-ZÀ-ÿ\s]{1,25}$/, 
	id: /^[0-9]+$/ ,
	nulo: /^/
}

const fields = {
	idEmployee: false,
	firstName: false,
	middleName: false,
	lastName: false,
	position: false
}
const valForm = (e) => {
	let btn = document.getElementById('save');
	switch(e.target.name){
		case "idEmployee":
			if(expresions.id.test(e.target.value)){
				document.getElementById('group__idEmployee').classList.remove('form__group-incorrect');
				document.getElementById('group__idEmployee').classList.add('form__group-correct');
				
				document.getElementById('error').classList.remove('form__input-error-active');
				document.getElementById('error').classList.add('form__input-error');
				fields[0] = true;
				btn.disabled = false;

			}else{
				document.getElementById('group__idEmployee').classList.add('form__group-incorrect');
				document.getElementById('group__idEmployee').classList.remove('form__group-correct');
				
				document.getElementById('error').classList.add('form__input-error-active');
				document.getElementById('error').classList.remove('form__input-error');
				fields[0] = false;
				btn.disabled = true;
			}
			
			
		break;
		
		case "firstName":
			
			if(expresions.text.test(e.target.value)){
				document.getElementById('group__firstName').classList.remove('form__group-incorrect');
				document.getElementById('group__firstName').classList.add('form__group-correct');
				
				document.getElementById('error2').classList.remove('form__input-error-active');
				document.getElementById('error2').classList.add('form__input-error');
				fields[1] = true;
			}else{
				document.getElementById('group__firstName').classList.add('form__group-incorrect');
				document.getElementById('group__firstName').classList.remove('form__group-correct');
				
				document.getElementById('error2').classList.add('form__input-error-active');
				document.getElementById('error2').classList.remove('form__input-error');
				fields[1] = false;
			}
		break;
		
		case "middleName":
			if(expresions.text.test(e.target.value)){
				
				document.getElementById('group__middleName').classList.remove('form__group-incorrect');
				document.getElementById('group__middleName').classList.add('form__group-correct');
				
				document.getElementById('error3').classList.remove('form__input-error-active');
				document.getElementById('error3').classList.add('form__input-error');
				fields[2] = true;
			}else{
				
				
				document.getElementById('group__middleName').classList.add('form__group-incorrect');
				document.getElementById('group__middleName').classList.remove('form__group-correct');
				
				document.getElementById('error3').classList.add('form__input-error-active');
				document.getElementById('error3').classList.remove('form__input-error');
				fields[2] = false;

			}
		break;
		
		case "lastName":
			if(expresions.text.test(e.target.value)){
				document.getElementById('group__lastName').classList.remove('form__group-incorrect');
				document.getElementById('group__lastName').classList.add('form__group-correct');
				
				document.getElementById('error4').classList.remove('form__input-error-active');
				document.getElementById('error4').classList.add('form__input-error');
				fields[3] = true;
			}else{
				document.getElementById('group__lastName').classList.add('form__group-incorrect');
				document.getElementById('group__lastName').classList.remove('form__group-correct');
				
				document.getElementById('error4').classList.add('form__input-error-active');
				document.getElementById('error4').classList.remove('form__input-error');
				fields[3] = false;

			}
		break;
		
		case "position":
			if(expresions.text.test(e.target.value)){
				document.getElementById('position').classList.remove('form__group-incorrect');
				document.getElementById('position').classList.add('form__group-correct');
				
				document.getElementById('error6').classList.remove('form__input-error-active');
				document.getElementById('error6').classList.add('form__input-error');
				fields[4] = true;
				
			}else{
				document.getElementById('position').classList.add('form__group-incorrect');
				document.getElementById('position').classList.remove('form__group-correct');
				
				document.getElementById('error6').classList.add('form__input-error-active');
				document.getElementById('error6').classList.remove('form__input-error');
				fields[4] = false;

			}
		break;
	}

	
	
}





inputs.forEach((input) => {
	input.addEventListener('keyup',valForm);
	input.addEventListener('blur',valForm);
});

form.addEventListener('submit', (e) =>{
	if(fields.idEmployee && fields.firstName && fields.middleName && fields.lastName && fields.position){
		form.reset();
	}else{
		//document.getElementById('save').disabled=true;
	}
	
	
});






