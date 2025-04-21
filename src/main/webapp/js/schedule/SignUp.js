$(document).ready(function() {

// to clearn the field, then set event listener for validating the input for User_Name
    var User_Name_input= document.getElementById("inputuserUser_Name1");
    User_Name_input.value='';
    User_Name_input.addEventListener('keyup',function(){
            if (User_Name_input.value!=""&& User_Name_input.value.length>1 && User_Name_input.value.length<=100){
                $(User_Name_input).addClass("ui-state-highlight");
                $(User_Name_input).removeClass("ui-state-error");
            }
        else {
                $(User_Name_input).removeClass("ui-state-highlight");
                $(User_Name_input).addClass("ui-state-error");
            }
        }
    );
// to clearn the field, then set event listener for validating the input for User_PW
    var User_PW_input= document.getElementById("inputuserUser_PW1");
    User_PW_input.value='';
    User_PW_input.addEventListener('keyup',function(){
            if (User_PW_input.value!=""&& User_PW_input.value.length>1 && User_PW_input.value.length<=100){
                $(User_PW_input).addClass("ui-state-highlight");
                $(User_PW_input).removeClass("ui-state-error");
            }
        else {
                $(User_PW_input).removeClass("ui-state-highlight");
                $(User_PW_input).addClass("ui-state-error");
            }
        }
    );
    // to clearn the field, then set event listener for validating the input for User_PW
    var User_PW_input2= document.getElementById("inputuserUser_PW2");
    User_PW_input2.value='';
    User_PW_input2.addEventListener('keyup',function(){
            if (User_PW_input2.value!=""&& User_PW_input2.value.length>1 && User_PW_input2.value.length<=100){
                $(User_PW_input2).addClass("ui-state-highlight");
                $(User_PW_input2).removeClass("ui-state-error");
            }
            else {
                $(User_PW_input2).removeClass("ui-state-highlight");
                $(User_PW_input2).addClass("ui-state-error");
            }
        }
    );
// to clearn the field, then set event listener for validating the input for Email
    var Email_input= document.getElementById("inputuserEmail");
    Email_input.value='';
    Email_input.addEventListener('keyup',function(){
            if (Email_input.value!=""&& Email_input.value.length>1 && Email_input.value.length<=100){
                $(Email_input).addClass("ui-state-highlight");
                $(Email_input).removeClass("ui-state-error");
            }
        else {
                $(Email_input).removeClass("ui-state-highlight");
                $(Email_input).addClass("ui-state-error");
            }
        }
    );})



