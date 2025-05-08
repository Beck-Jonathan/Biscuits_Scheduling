$(document).ready(function() {
    $("#dialog").dialog({
        modal: true,
        bgiframe: true,
        autoOpen: false,
        width: 500,
        height: 400,
    });
    $(".delButton").click(function (e) {
        e.preventDefault();
        var text = "";
        document.getElementById("dialog").innerHTML = text;
        var targetUrl = $(this).attr("href");
        var friend_name = this.parentElement.parentElement.children[0].textContent;
        friend_name=friend_name.trim()
        console.log(friend_name)
        var sent = this.parentElement.parentElement.children[2].textContent;
        sent=sent.trim()
        console.log(sent);
        var text = "Are you sure you want to delete this friend?<br/>";
        text +=friend_name+"<br/>";
        text+="You've been connected since:<br/>";
        text += sent;

        document.getElementById("dialog").innerHTML=text;
        console.log(targetUrl)
        $('#dialog').dialog('option', 'title', 'Delete Friend '+friend_name+' ???');
        $("#dialog").dialog({
            hide: {
                effect: "explode",
                duration: 300
            },
            show: {
                effect: "explode",
                duration: 300
            },
            buttons: {
                "Delete For Real": function () {
                    console.log("try");
                    $.ajax({
                        url: 'approveFriend',
                        data: 'mode=reject&friend_lineid=' + targetUrl,
                        type: 'get',
                        async: true,
                        success: function (response) {
                            if (response == 1) {
                                $(rowid).slideUp();
                            } else {
                                $(rowid).addClass("ui-state-error");
                            }
                        }
                    })
                    $(this).dialog("close");
                    var rowid = "#" + targetUrl + "row";
                    $(rowid).slideUp();
                },
                "Let It Stay": function () {
                    $(this).dialog("close");
                }
            }
        });
        $("#dialog").dialog("open");
    });
    $(".approveButton").click(function (e) {
        e.preventDefault();
        var text = "";
        document.getElementById("dialog").innerHTML = text;
        var targetUrl = $(this).attr("href");
        var friend_name = this.parentElement.parentElement.children[0].textContent;
        friend_name=friend_name.trim()
        console.log(friend_name)
        var sent = this.parentElement.parentElement.children[2].textContent;
        sent=sent.trim()
        console.log(sent);
        var text = "Are you sure you want to approve this friend?<br/>";
        text +=friend_name+"<br/>";
        text+="The Request was sent:<br/>";
        text += sent;

        document.getElementById("dialog").innerHTML=text;
        console.log(targetUrl)
        $('#dialog').dialog('option', 'title', 'Approve Friend '+friend_name+' ???');
        $("#dialog").dialog({
            hide: {
                effect: "explode",
                duration: 300
            },
            show: {
                effect: "explode",
                duration: 300
            },
            buttons: {
                "Approve For Real": function () {
                    console.log("try");
                    $.ajax({
                        url: 'approveFriend',
                        data: 'mode=approve&friend_lineid=' + targetUrl,
                        type: 'get',
                        async: true,
                        success: function (response) {
                            if (response == 1) {
                                $(rowid).slideUp();
                            } else {
                                $(rowid).addClass("ui-state-error");
                            }
                        }
                    })
                    $(this).dialog("close");
                    var rowid = "#" + targetUrl + "row";
                    $(rowid).slideUp();
                },
                "Hmm, let me think about it": function () {
                    $(this).dialog("close");
                }
            }
        });
        $("#dialog").dialog("open");
    });
})
