$(document).ready(function() {
    let submitbutton = document.getElementById("submitButton")
    submitbutton.disabled=true;

// to clean the field, then set event listener for validating the input for User_One
    var User_One_input = document.getElementById("inputfriend_lineUser_One");
    User_One_input.value = '';
    User_One_input.addEventListener('keyup', function () {
            if (User_One_input.value != "" &&isEmail(User_One_input.value)) {
                $(User_One_input).addClass("ui-state-highlight");
                $(User_One_input).removeClass("ui-state-error");
                submitbutton.disabled=false;
            } else {
                $(User_One_input).removeClass("ui-state-highlight");
                $(User_One_input).addClass("ui-state-error");
                submitbutton.disabled=true;

            }
        }
    );
    function isEmail(email) {
        var regex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
        return regex.test(email);
    }
})