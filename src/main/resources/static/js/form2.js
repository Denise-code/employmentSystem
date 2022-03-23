


const form = document.getElementById('form');
const inputs = document.querySelectorAll('#form input');

//Get date

let today = new Date();
let day = today.getDate();
let month = today.getMonth();
let year = today.getFullYear();

let minyear = year-90;

  $(document).ready(function(){
    $('#date').datepicker({
	format: 'yyyy-mm',
	minDate: new Date(minyear,0),
	maxDate: new Date(year, month)
	});
  });


//

//Type of compensations
	function selecttype(){
		let typeCompensa = document.getElementById('typeCompensa');
		let compensa = typeCompensa.value;
		document.getElementById('error3').innerHTML= "Please, check the amount of your " + compensa ;
		return compensa;
	}
	
//

const expresions = {
	text: /^[a-zA-ZÀ-ÿ\s]{1,40}$/, 
	integer:/^[1-9]\d+$/ ,
	nulo: /^/,
	neg:  /^-?[0-9]\d+$/,
	menosCero: /^-?[1-9]\d+$/
}


let amountIni = document.getElementById('amount');
console.log(amountIni.value);

const valForm = (e) => {
	let btn = document.getElementById('save');
	switch(e.target.name){
		case "amount":
			
			switch(selecttype()){
				case "Salary":
					if(expresions.neg.test(e.target.value)){
						document.getElementById('group__amount').classList.remove('form__group-incorrect');
						document.getElementById('group__amount').classList.add('form__group-correct');
						
						document.getElementById('error3').classList.remove('form__input-error-active');
						document.getElementById('error3').classList.add('form__input-error');
						
					}else{
						document.getElementById('group__amount').classList.add('form__group-incorrect');
						document.getElementById('group__amount').classList.remove('form__group-correct');
						
						document.getElementById('error3').classList.add('form__input-error-active');
						document.getElementById('error3').classList.remove('form__input-error');
						
					}
				break;
				
				case "Bonus":
				
					
					if(expresions.integer.test(e.target.value)){
						document.getElementById('group__amount').classList.remove('form__group-incorrect');
						document.getElementById('group__amount').classList.add('form__group-correct');
						
						document.getElementById('error3').classList.remove('form__input-error-active');
						document.getElementById('error3').classList.add('form__input-error');
						
					}else{
						document.getElementById('group__amount').classList.add('form__group-incorrect');
						document.getElementById('group__amount').classList.remove('form__group-correct');
						
						document.getElementById('error3').classList.add('form__input-error-active');
						document.getElementById('error3').classList.remove('form__input-error');
						
					}
				break;
				
				case "Commission":
					if(expresions.integer.test(e.target.value)){
						document.getElementById('group__amount').classList.remove('form__group-incorrect');
						document.getElementById('group__amount').classList.add('form__group-correct');
						
						document.getElementById('error3').classList.remove('form__input-error-active');
						document.getElementById('error3').classList.add('form__input-error');
						
					}else{
						document.getElementById('group__amount').classList.add('form__group-incorrect');
						document.getElementById('group__amount').classList.remove('form__group-correct');
						
						document.getElementById('error3').classList.add('form__input-error-active');
						document.getElementById('error3').classList.remove('form__input-error');
						
					}
				break;
				case "Allowance":
					if(expresions.integer.test(e.target.value)){
						document.getElementById('group__amount').classList.remove('form__group-incorrect');
						document.getElementById('group__amount').classList.add('form__group-correct');
						
						document.getElementById('error3').classList.remove('form__input-error-active');
						document.getElementById('error3').classList.add('form__input-error');
						
					}else{
						document.getElementById('group__amount').classList.add('form__group-incorrect');
						document.getElementById('group__amount').classList.remove('form__group-correct');
						
						document.getElementById('error3').classList.add('form__input-error-active');
						document.getElementById('error3').classList.remove('form__input-error');
						
					}
				break;
				case "Adjustment":
					if(expresions.menosCero.test(e.target.value)){
						document.getElementById('group__amount').classList.remove('form__group-incorrect');
						document.getElementById('group__amount').classList.add('form__group-correct');
						
						document.getElementById('error3').classList.remove('form__input-error-active');
						document.getElementById('error3').classList.add('form__input-error');
						
					}else{
						document.getElementById('group__amount').classList.add('form__group-incorrect');
						document.getElementById('group__amount').classList.remove('form__group-correct');
						
						document.getElementById('error3').classList.add('form__input-error-active');
						document.getElementById('error3').classList.remove('form__input-error');
						
					}
				break;
				
			}
		
		
		
		
		break;
		
		case "description":
			if(expresions.text.test(e.target.value)){
				
				document.getElementById('group__description').classList.remove('form__group-incorrect');
				document.getElementById('group__description').classList.add('form__group-correct');
				
				document.getElementById('error4').classList.remove('form__input-error-active');
				document.getElementById('error4').classList.add('form__input-error');
				fields[2] = true;
			}else{
				
				
				document.getElementById('group__description').classList.add('form__group-incorrect');
				document.getElementById('group__description').classList.remove('form__group-correct');
				
				document.getElementById('error4').classList.add('form__input-error-active');
				document.getElementById('error4').classList.remove('form__input-error');
				fields[2] = false;

			}
		break;
		
		
		
		case "idEmployee":
			if(expresions.text.test(e.target.value)){
				document.getElementById('idEmployee').classList.remove('form__group-incorrect');
				document.getElementById('idEmployee').classList.add('form__group-correct');
				
				document.getElementById('error6').classList.remove('form__input-error-active');
				document.getElementById('error6').classList.add('form__input-error');
				fields[4] = true;
				
			}else{
				document.getElementById('idEmployee').classList.add('form__group-incorrect');
				document.getElementById('idEmployee').classList.remove('form__group-correct');
				
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









