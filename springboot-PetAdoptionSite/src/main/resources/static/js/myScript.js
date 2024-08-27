//!!!For New Reg page!!!//
    /*definition of elements in the form*/
	var email = document.getElementById("email");
	var password = document.getElementById("password");
	var rePassword = document.getElementById("rePassword");
	var lastName = document.getElementById("lastName");
	var firstName = document.getElementById("firstName");
    
    var pwrules_length = document.getElementById("pwrules_length");
    var pwrules_upper = document.getElementById("pwrules_upper");
    var pwrules_lower = document.getElementById("pwrules_lower");
    var pwrules_no = document.getElementById("pwrules_no");
    var pwrules_special = document.getElementById("pwrules_special");
	
	/*the var list of elements of invalid-feedback */
	var invalid_feedback = document.getElementsByClassName("invalid-feedback");
	
	/*the form in html*/
	const form = document.querySelector("form");
	const button = document.querySelector("button");

    /*show the password rules when */
    password.onfocus = function(){
        document.getElementById("pw_rules").style.display = "block";
    } 
    password.onblur = function(){
        document.getElementById("pw_rules").style.display = "none";
    }    
	/*validate passwrod rules respectively and change the class according to style settings*/
    password.onkeyup = function(){
    
        if(password.value.length >=8 && password.value.length <=16){
            pwrules_length.classList.remove("pw_invalid");
            pwrules_length.classList.add("pw_valid");
        }else{
            pwrules_length.classList.remove("pw_valid");
            pwrules_length.classList.add("pw_invalid");
        }
        var upper = /[A-Z]/g;
        if(password.value.match(upper)){
            pwrules_upper.classList.remove("pw_invalid");
            pwrules_upper.classList.add("pw_valid");
        }else{
            pwrules_upper.classList.remove("pw_valid");
            pwrules_upper.classList.add("pw_invalid");
        }
        var lower = /[a-z]/g;
        if(password.value.match(lower)){
            pwrules_lower.classList.remove("pw_invalid");
            pwrules_lower.classList.add("pw_valid");
        }else{
            pwrules_lower.classList.remove("pw_valid");
            pwrules_lower.classList.add("pw_invalid");
        }
        var no =/[0-9]/g;
        if(password.value.match(no)){
            pwrules_no.classList.remove("pw_invalid");
            pwrules_no.classList.add("pw_valid");
        }else{
            pwrules_no.classList.remove("pw_valid");
            pwrules_no.classList.add("pw_invalid");
        }
        var special = /[_\#\$\!\%\&\*]/g;
        if(password.value.match(special)){
            pwrules_special.classList.remove("pw_invalid");
            pwrules_special.classList.add("pw_valid");
        }else{
            pwrules_special.classList.remove("pw_valid");
            pwrules_special.classList.add("pw_invalid");
        }
      
    }
	//Add an event listening to the form's submit event
	form.addEventListener("submit", function(event){
		let result = true;
		
		//Check if email field has input
		if(email.value == ""){
			invalid_feedback[0].style.display = "block";
			result = false;
			
		}else{
			invalid_feedback[0].style.display = "none";
		}
		//Check if email field has input
		if(email.value.match(/[@]/g)){
			if(email.value.length <=8 && email.value.length <=50){
				invalid_feedback[1].style.display = "none";
			}
		}else{
			invalid_feedback[1].style.display = "block";
			result = false;
		}
		//Check if password has input
		if(password.value == ""){
			invalid_feedback[2].style.display = "block";
			result = false;
		}else{
			invalid_feedback[2].style.display = "none";
		}
		//Check if password pattern is correct
		const regexp = /(?=.*\d)(?=.*[a-zA-Z])(?=.*[A-Z])(?=.*[_\#\$\!\%\&\*])(?=.*[a-zA-Z])/g;
		if(password.value.match(regexp)){
			invalid_feedback[3].style.display = "none";
		}else{
			invalid_feedback[3].style.display = "block";
			result = false;
		}
		//Check if re-input password has input
		if(rePassword.value == ""){
			invalid_feedback[4].style.display = "block";
			result = false;
		}else{
			invalid_feedback[4].style.display = "none";
		}
		//Check if re-input password equals to the password
		if(password.value != rePassword.value){
			invalid_feedback[5].style.display = "block";
			result = false;
		}else{
			invalid_feedback[5].style.display = "none";
		}
		//Check if last name field has input
		if(lastName.value ==""){
			invalid_feedback[6].style.display = "block";
			result = false;
		}else{
			invalid_feedback[6].style.display = "none";
			}
			
		if(result != true){
			event.preventDefault();
		}
		
	});
	
	
	
	
