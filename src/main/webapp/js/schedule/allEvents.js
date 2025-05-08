$(document).ready(function() {
    $("#CurrentEvents").slideUp();
    $("#FutureEvents").slideUp();
    $("#PastEvents").slideUp();

    document.getElementById("CurrentHeader").addEventListener("click",function(){$("#CurrentEvents").slideToggle()});
    document.getElementById("FutureHeader").addEventListener("click",function(){$("#FutureEvents").slideToggle()});
    document.getElementById("PastHeader").addEventListener("click",function(){$("#PastEvents").slideToggle()});
    $("#dialog").dialog({
        modal: true,
        bgiframe: true,
        autoOpen: false,
        width: 500,
        height: 400,
    });
    $(".delButton").click(function(e) {
        e.preventDefault();
        var headers = document.getElementsByClassName('table-responsive')[0].childNodes[0].childNodes[1].childNodes[1].childNodes;
        var parentrow = this.parentElement.parentElement.parentElement.children;
        var rowid ="#"+ targetUrl+"row";
        var text = "";
        for (i=1;i<headers.length-2;i=i+2){
            text +=headers[i].textContent+": "+parentrow[(i-1)/2].innerHTML+"</br>";
        }
        document.getElementById("dialog").innerHTML=text;
        var targetUrl = $(this).attr("href");
        $('#dialog').dialog('option', 'title', 'Delete '+parentrow[1].innerHTML+"???");
        $("#dialog").dialog({
            hide: {
                effect: "explode",
                duration: 300
            },
            show: {
                effect: "explode",
                duration: 300
            },
            buttons : {
                "Delete For Real" : function() {
                    console.log("try");
                    $.ajax({
                        url: 'deleteEvent',
                        data: "eventid=" + targetUrl+"&AJAX=true" ,
                        type: 'post',
                        async: true,
                        success: function (response) {
                            if (response==1){
                                $(rowid).slideUp();}
                            else {
                                $(rowid).addClass("ui-state-error");
                            }
                        }})
                    $(this).dialog("close");
                    var rowid ="#"+ targetUrl+"row";
                    $(rowid).slideUp();
                },
                "Let It Stay" : function() {
                    $(this).dialog("close");
                }
            }
        });
        $("#dialog").dialog("open"); });

    function deleteEvent(_id) {
        document.getElement.ID(_id + "Status").innerHTML = "<h5>&#9202</h5>"
    }
})
