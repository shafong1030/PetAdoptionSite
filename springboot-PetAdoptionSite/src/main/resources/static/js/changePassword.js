
    /*definition of elements in the form*/
	var oldPassword = document.getElementById("oldPassword");
	var newPassword = document.getElementById("newPassword");
	var newPassword2 = document.getElementById("newPassword2");

    
    var pwrules_length = document.getElementById("pwrules_length");
    var pwrules_upper = document.getElementById("pwrules_upper");
    var pwrules_lower = document.getElementById("pwrules_lower");
    var pwrules_no = document.getElementById("pwrules_no");
    var pwrules_special = document.getElementById("pwrules_special");
	
	/*the var list of elements of invalid-feedback */
	var invalid_feedback = document.getElementsByClassName("invalid-feedback");
	
	/*the form in html*/
	const form = document.querySelector("form");

    /*show the password rules when */
    newPassword.onfocus = function(){
        document.getElementById("pw_rules").style.display = "block";
    } 
    newPassword.onblur = function(){
        document.getElementById("pw_rules").style.display = "none";
    }    
	/*validate passwrod rules respectively and change the class according to style settings*/
    newPassword.onkeyup = function(){
    
        if(newPassword.value.length >=8 && newPassword.value.length <=16){
            pwrules_length.classList.remove("pw_invalid");
            pwrules_length.classList.add("pw_valid");
        }else{
            pwrules_length.classList.remove("pw_valid");
            pwrules_length.classList.add("pw_invalid");
        }
        var upper = /[A-Z]/g;
        if(newPassword.value.match(upper)){
            pwrules_upper.classList.remove("pw_invalid");
            pwrules_upper.classList.add("pw_valid");
        }else{
            pwrules_upper.classList.remove("pw_valid");
            pwrules_upper.classList.add("pw_invalid");
        }
        var lower = /[a-z]/g;
        if(newPassword.value.match(lower)){
            pwrules_lower.classList.remove("pw_invalid");
            pwrules_lower.classList.add("pw_valid");
        }else{
            pwrules_lower.classList.remove("pw_valid");
            pwrules_lower.classList.add("pw_invalid");
        }
        var no =/[0-9]/g;
        if(newPassword.value.match(no)){
            pwrules_no.classList.remove("pw_invalid");
            pwrules_no.classList.add("pw_valid");
        }else{
            pwrules_no.classList.remove("pw_valid");
            pwrules_no.classList.add("pw_invalid");
        }
        var special = /[_\#\$\!\%\&\*]/g;
        if(newPassword.value.match(special)){
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
		
		
		//Check if old password has input
		if(oldPassword.value == ""){
			invalid_feedback[0].style.display = "block";
			result = false;
		}else{
			invalid_feedback[0].style.display = "none";
		}
		//Check if new password has input
		if(newPassword.value == ""){
			invalid_feedback[1].style.display = "block";
			result = false;
		}else{
			invalid_feedback[1].style.display = "none";
				}
		//Check if new password pattern is correct
		const regexp = /(?=.*\d)(?=.*[a-zA-Z])(?=.*[A-Z])(?=.*[_\#\$\!\%\&\*])(?=.*[a-zA-Z])/g;
		if(newPassword.value.match(regexp)){
			invalid_feedback[2].style.display = "none";
		}else{
			invalid_feedback[2].style.display = "block";
			result = false;
		}
		//Check if re-input new password has input
		if(newPassword2.value == ""){
			invalid_feedback[3].style.display = "block";
			result = false;
		}else{
			invalid_feedback[3].style.display = "none";
		}
		//Check if re-input new password equals to the new password
		if(newPassword2.value != newPassword.value){
			invalid_feedback[4].style.display = "block";
			result = false;
		}else{
			invalid_feedback[4].style.display = "none";
		}
			
		if(result != true){
			event.preventDefault();
		}
		
	});
	
