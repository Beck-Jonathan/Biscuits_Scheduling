$(document).ready(function() {
        let submitbutton = document.getElementById("submitButton")
        submitbutton.disabled=true;
        let total_errors=0;

        let Name_error=0;
        let WebAddress_error=0;
// to clean the field, then set event listener for validating the input for User_ID

// to clean the field, then set event listener for validating the input for Name
        var Name_input= document.getElementById("inputculversName");
        Name_input.addEventListener('blur',function(){
                Name_input.value = Name_input.value.trim();
                if (Name_input.value!=""&& Name_input.value.length>1 && Name_input.value.length<=255){
                    $(Name_input).addClass("ui-state-highlight");
                    $(Name_input).removeClass("ui-state-error");
                    Name_error=0;}
                else {
                    $(Name_input).removeClass("ui-state-highlight");
                    $(Name_input).addClass("ui-state-error");
                    Name_error=1;}
                total_errors =  Name_error+ WebAddress_error;
                if (total_errors ==0){
                    submitbutton.disabled=false;
                } else {
                    submitbutton.disabled=true;
                }
            }
        );
// to clean the field, then set event listener for validating the input for WebAddress
        var WebAddress_input= document.getElementById("inputculversWebAddress");
        WebAddress_input.addEventListener('blur',function(){
                WebAddress_input.value = WebAddress_input.value.trim();
                if (WebAddress_input.value!=""&& WebAddress_input.value.length>1 && WebAddress_input.value.length<=255){
                    $(WebAddress_input).addClass("ui-state-highlight");
                    $(WebAddress_input).removeClass("ui-state-error");
                    WebAddress_error=0;}
                else {
                    $(WebAddress_input).removeClass("ui-state-highlight");
                    $(WebAddress_input).addClass("ui-state-error");
                    WebAddress_error=1;}
                total_errors =  Name_error+ WebAddress_error;
                if (total_errors ==0){
                    submitbutton.disabled=false;
                } else {
                    submitbutton.disabled=true;
                }
            }
        );

    }
)

