$(document).ready(function() {
// to clean the field, then set event listener for validating the input for Name
        var Name_input= document.getElementById("inputeventName");

        Name_input.addEventListener('keyup',function(){
                if (Name_input.value!=""&& Name_input.value.length>1 && Name_input.value.length<=100){
                    $(Name_input).addClass("ui-state-highlight");
                    $(Name_input).removeClass("ui-state-error");
                }
                else {
                    $(Name_input).removeClass("ui-state-highlight");
                    $(Name_input).addClass("ui-state-error");
                }
            }
        );

// to clean the field, then set event listener for validating the input for Location
        var Location_input= document.getElementById("inputeventLocation");
        
        Location_input.addEventListener('keyup',function(){
                if (Location_input.value!=""&& Location_input.value.length>1 && Location_input.value.length<=100){
                    $(Location_input).addClass("ui-state-highlight");
                    $(Location_input).removeClass("ui-state-error");
                }
                else {
                    $(Location_input).removeClass("ui-state-highlight");
                    $(Location_input).addClass("ui-state-error");
                }
            }
        );
// to clean the field, then set event listener for validating the input for Description
        var Description_input= document.getElementById("inputeventdescription");
        Description_input.value='';
        Description_input.addEventListener('keyup',function(){
                if (Description_input.value!=""&& Description_input.value.length>1 && Description_input.value.length<=256){
                    $(Description_input).addClass("ui-state-highlight");
                    $(Description_input).removeClass("ui-state-error");
                }
                else {
                    $(Description_input).removeClass("ui-state-highlight");
                    $(Description_input).addClass("ui-state-error");
                }
            }
        );
    $(document.getElementsByName("bool_recur")).click(function(){
        var recur= (document.querySelector('input[name="bool_recur"]:checked').value);
        if (recur=="yes") {
            $("#recur_row").slideDown().removeAttr("hidden");

        }

        else {$("#recur_row").slideUp();
            }


    }
)





})