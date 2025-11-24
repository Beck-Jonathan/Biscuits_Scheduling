function callActivation(id,mode) {
    console.log("try");


    console.log(id)
    var isActive="";

    console.log(mode);
    $.ajax({
        url: 'deleteculvers',
        data: "Culversid=" + id+"&mode="+mode ,
        type: 'post',
        async: true,
        success: function (response) {
            console.log(response)
            if (response==1) {
                console.log("good");
            }
                //button.parentElement.addClass("");}
            else {
                console.log("bad")
                //button.parentElement.addClass("");
            }
        }})

    //var rowid ="#"+ targetUrl+"_row";
    //$(rowid).slideUp();
}

var activeButtons = document.getElementsByClassName("isActiveCheckbox")
for (i=0;i<activeButtons.length;i++){
    console.log(i);
    let id=activeButtons[i].id;
    id=id.substring(0,id.indexOf("_"))
    isActive = activeButtons[i].checked;
    let mode = isActive ? 0:1
    activeButtons[i].addEventListener("click", function(){
        callActivation(id,mode);
    })

}