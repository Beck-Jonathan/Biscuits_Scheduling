$(document).ready(function() {

    let _text = ""

    console.log("hi")
    events = document.getElementsByClassName("event-link");

    console.log(events[1].innerHTML)
    for (i = 0; i < events.length; i++) {

        var data = events[i].innerHTML
        var date_start = data.indexOf('<p class=')+17
        var date_end = data.indexOf('<i class')
        var date = data.substring(date_start,date_end)
        date=date.trim()
        var time_start =data.indexOf( "</i>")+5;
        var time_end = data.indexOf("</p>");

        var time = data.substring(time_start,time_end);
        time=time.replace("\n","");
        time = time.trim();
        var times = [];
        times = time.split("-");
        var startevent = times[0];
        var endevent = times[1];
        if (endevent==null){
            endevent="";
        }
        startevent=startevent.trim();
        endevent=endevent.trim()
        var name_start = data.indexOf("<h3>")+5;
        var name_end = data.indexOf("</h3>")-5
        var name = data.substring(name_start,name_end)
        name=name.trim();
        _text = _text + name+ "\t "+date +" \t " +startevent+"\t"+endevent+"\n";

    }
    var __textbox = document.getElementById('textbox');
    __textbox.value=_text;
    var textFile = null,
        makeTextFile = function (text) {
            var data = new Blob([text], {type: 'text/plain'});

            // If we are replacing a previously generated file we need to
            // manually revoke the object URL to avoid memory leaks.
            if (textFile !== null) {
                window.URL.revokeObjectURL(textFile);
            }

            textFile = window.URL.createObjectURL(data);

            return textFile;
        };


    var create = document.getElementById('create'),
        textbox = document.getElementById('textbox');

    create.addEventListener('click', function () {
        var link = document.createElement('a');
        link.setAttribute('download', 'info.txt');
        link.href = makeTextFile(textbox.value);
        document.body.appendChild(link);

        // wait for the link to be added to the document
        window.requestAnimationFrame(function () {
            var event = new MouseEvent('click');
            link.dispatchEvent(event);
            document.body.removeChild(link);
        });})})




