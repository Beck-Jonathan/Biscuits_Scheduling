const calendarDates = document.querySelector('.calendar-dates');
const monthYear = document.getElementById('month-year');
const prevMonthBtn = document.getElementById('prev-month');
const nextMonthBtn = document.getElementById('next-month');

let currentDate = new Date();
let currentMonth = currentDate.getMonth();
let anchorMonth = currentDate.getMonth();
let anchorYear = currentDate.getFullYear();
let currentYear = currentDate.getFullYear();
let searchTerm = "";
let events = [];
let temp_events = []
let backup_events = [];
let culvers_events = []
let is_culvers = false;
let modified=false;
let combined_events = [];
let culvers_filtered=[];
let culvers_stored = [];

async function filter(){


    searchBox = document.getElementById("searchBox")
    searchTerm= searchBox.value;
    culvers_filtered = filterArray(culvers_events,searchTerm);

    var x =  await callAjaxMonth(currentMonth + 1, searchTerm);


    combined_events = combine_array(events,culvers_filtered);
    renderCalendar(currentMonth, currentYear);
    addEventsToBoxes();
}
function noculvers(){
    is_culvers=false;
    $("#culvers").slideDown();
    $("#noculvers").slideUp();
    culvers_filtered=[];

    renderCalendar(currentMonth, currentYear);
    addEventsToBoxes()

}
function culvers(){
    //var events = [];
    is_culvers=true;
    var month = currentMonth
    $("#datesToSlide").slideUp();
    $("#culvers").slideUp();
    $("#noculvers").slideDown();
    console.log(culvers_stored[currentMonth]);
    if (culvers_stored[currentMonth]==null) {
        $.ajax({
            url: 'AJAXCUULVERS',
            data: "month=" + month + "&year=" + currentYear,
            type: 'get',
            async: true,
            success: function (response) {
                culvers_events = response;
                culvers_filtered = response;
                culvers_stored[currentMonth]=culvers_events;
                $("#datesToSlide").slideDown();
                addEventsToBoxes()
            }


        });
    } else {

        culvers_events = culvers_stored[currentMonth];
        culvers_filtered = culvers_stored[currentMonth];
        $("#datesToSlide").slideDown();
        addEventsToBoxes()
    }

}


const months = [
    'January', 'February', 'March', 'April', 'May', 'June',
    'July', 'August', 'September', 'October', 'November', 'December'
];
function renderCalendar(month, year) {
    calendarDates.innerHTML = '';
    boxMonth = currentMonth+1;
    monthYear.innerHTML = "<a href='all-events?day="+"&month="+boxMonth+"&year="+currentYear+"'>"+months[month]+" "+year +"</a>";


    // Get the first day of the month
    const firstDay = new Date(year, month, 1).getDay();

    // Get the number of days in the month
    const daysInMonth = new Date(year, month + 1, 0).getDate();

    // Create blanks for days of the week before the first day
    for (let i = 0; i < firstDay; i++) {
        const blank = document.createElement('div');
        calendarDates.appendChild(blank);
    }
// Get today's date
    const today = new Date();
    // Populate the days
    for (let i = 1; i <= daysInMonth; i++) {
        const day = document.createElement('div');
        //day.textContent = i;
        day.setAttribute("id","day"+i);
        let thisday = new Date();
        thisday.setMonth(currentMonth);
        thisday.setFullYear(currentYear)
        thisday.setDate(i);
        let dayofweek = thisday.getDay();
        day.setAttribute("dayofweek",dayofweek)


        // Highlight today's date
        if (
            i === today.getDate() &&
            year === today.getFullYear() &&
            month === today.getMonth()
        ) {
            day.classList.add('current-date');
        }

        calendarDates.appendChild(day);
        addBox("day"+i,i)
    }


}

$("#noculvers").slideUp();
renderCalendar(currentMonth, currentYear);
callAjaxMonth(currentMonth+1,"");
prevMonthBtn.addEventListener('click', () => {
    is_culvers=false;
    culvers_filtered = [];

    currentMonth--;
    if (currentMonth < 0) {
        currentMonth = 11;
        currentYear--;
    }
    if(currentMonth-anchorMonth==1||currentMonth-anchorMonth==0||currentMonth-anchorMonth==-11) {
        $("#culvers").slideDown();
    }
    else {
        $("#culvers").slideUp();
    }
    $("#noculvers").slideUp();

    renderCalendar(currentMonth, currentYear);
    callAjaxMonth(currentMonth+1,searchTerm);



});

nextMonthBtn.addEventListener('click', () => {
    is_culvers=false;
    culvers_filtered = [];

    currentMonth++;
    if (currentMonth > 11) {
        currentMonth = 0;
        currentYear++;
    }
    if(currentMonth-anchorMonth==1||currentMonth-anchorMonth==0||currentMonth-anchorMonth==-11) {
        $("#culvers").slideDown();
    }
    else {
        $("#culvers").slideUp();
    }
    $("#noculvers").slideUp();
    let _events = "";

    renderCalendar(currentMonth, currentYear);
    callAjaxMonth(currentMonth+1,searchTerm);


});



function addBox(id, dayno){
var boxMonth=currentMonth+1;

    var calendarBox = document.getElementById(id);
    var html = "<!-- \n" +
        "  Bootstrap docs: https://getbootstrap.com/docs\n" +
        "-->\n" +
        "<div class=\"container calday\">\n" +
        "<!-- start row 1 -->\n" +
        "  <div class=\"row \">\n" +
        "   <div class=\"col\"> <a href='all-events?day="+dayno+"&month="+boxMonth+"&year="+currentYear+"'>\n" +
           dayno +
        "   </a></div>\n" +
        "   <div class=\"col\">\n" +
        "   \n" +
        "   </div>\n" +
        "   <div class=\"col\">\n" +
        "   <a href='addEvent?day="+dayno+"&month="+boxMonth+"&year="+currentYear+"'>+</a>\n" +
        "   </div>\n" +
        "</div>\n" +
        "<!-- end row 1 -->\n" +
        "<!-- start row 2 -->\n" +
        "  <div class=\"row xxyy\">\n" +
        "  <div class=\"col\">\n" +
        "   &nbsp\n" +
        "   </div>\n" +
        "   <div class=\"col\">\n" +
        "   \n" +
        "   </div>\n" +
        "   <div class=\"col\">\n" +
        "   \n" +
        "   </div>\n" +
        "</div>\n" +
        "<!-- end row 2 -->\n" +
        "<!-- start row 3 -->\n" +

        "  <div class=\"row xxyy\">\n" +
        "    <div id=day"+dayno+"r3e1 class=\"col \">\n" +
        "    &nbsp\n" +
        "     </div>\n" +
        "   \n" +
        "    <div id=day"+dayno+"r3e2 class=\"col \">\n" +
        "    &nbsp\n" +
        "   </div>\n" +
        "    <div id=day"+dayno+"r3e3 class=\"col \">\n" +
        "   \n" +
        "   </div>\n" +
        "    <div id=day"+dayno+"r3e4 class=\"col \">\n" +
        "   \n" +
        "   </div>\n" +
        "   </div>\n" +
        "   <!-- end row 3 for real -->\n" +
        "   <!-- start row 4 -->\n" +
        "  <div class=\"row xxyy\">\n" +
        "    <div id=day"+dayno+"r4e1 class=\"col \">\n" +
        "    &nbsp\n" +
        "     </div>\n" +
        "   \n" +
        "    <div id=day"+dayno+"r4e2 class=\"col \">\n" +
        "    &nbsp\n" +
        "   </div>\n" +
        "   <div id=day"+dayno+"r4e3 class=\"col \">\n" +
        "   \n" +
        "   </div>\n" +
        "    <div id=day"+dayno+"r4e4 class=\"col \">\n" +
        "   \n" +
        "   </div>\n" +
        "   </div>\n" +
        "   <!-- end row 4 for real -->\n" +
        "   <!-- start row 5 -->\n" +
        "  <div class=\"row xxyy\">\n" +
        "    <div id=day"+dayno+"r5e1 class=\"col \">\n" +
        "    &nbsp\n" +
        "     </div>\n" +
        "   \n" +
        "    <div id=day"+dayno+"r5e2 class=\"col \">\n" +
        "    &nbsp\n" +
        "   </div>\n" +
        "    <div id=day"+dayno+"r5e3 class=\"col \">\n" +
        "   \n" +
        "   </div>\n" +
        "    <div id=day"+dayno+"r5e4 class=\"col \">\n" +
        "   \n" +
        "   </div>\n" +
        "   </div>\n" +
        "   <!-- end row 5 for real -->\n" +
        "   <!-- start row 6 -->\n" +
        "   <div class=\"row\">\n" +
        "   <div class=\"col-9 \">  </div>\n" +
        "   <div class=\"col-1\"> </div>\n" +
        "   </div>\n" +
        "   \n" +
        "   </div>\n" +
        "   <!-- end row 6 -->"
    calendarBox.innerHTML = html;
}

function addEventsToBoxes(){
    combined_events = combine_array(events,culvers_filtered);


    for (i=0;i<combined_events.length;i++){
        if(combined_events[i].events.length>0){
            for (j=0;j<combined_events[i].events.length;j++) {
                if (j >11) {
                    break;
                }
                    var backgroundColor = "pink";
                    var textColor = "black";
                    var thing = null;
                    var id = "";
                    if (j === 0) {
                        id = "day" + i + "r3e1";
                        thing = document.getElementById(id)
                        thing.classList.add("box")
                        thing.classList.add("pink")
                        var parent = (thing.parentElement.parentElement.parentElement);
                        var parentdayofweek = parseInt(parent.getAttribute("dayofweek"));

                        if (parentdayofweek<4){
                            thing.classList.add("HoverRight")
                        }
                        else {
                            thing.classList.add("HoverLeft")
                        }
                        thing.setAttribute('href', "editEvent?eventid=" + combined_events[i].events[j].event_ID);
                        //thing.setAttribute('href', "eventid=" + combined_events[i].events[j].event_ID+"&viewmode=middle");
                        var href = thing.getAttribute("href");
                        thing.addEventListener("click", function () {
                        });
                        thing.addEventListener("dblclick", function () {
                            //$("#calendarmain").slideUp();
                            window.location = this.getAttribute("href");
                            //$.ajax({
                              //  url: 'editEvent',
                                //data: this.getAttribute("href"),
                                //type: 'get',
                                //async: true,
                                //success: function (response) {
                                 //   console.log(response);
                                  //  document.getElementById("calendarmain").innerHTML="";
                                   // document.getElementById("calendarmain").innerHTML=response;

                                   // $("#calendarmain").slideDown();


                                //}})
                        });
                        backgroundColor = "";
                        textColor = ""
                    } else if (j === 1) {
                        id = "day" + i + "r3e2";
                        thing = document.getElementById(id)
                        thing.classList.add("box")
                        thing.classList.add("red")
                        var parent = (thing.parentElement.parentElement.parentElement);
                        var parentdayofweek = parseInt(parent.getAttribute("dayofweek"));

                        if (parentdayofweek<4){
                            thing.classList.add("HoverRight")
                        }
                        else {
                            thing.classList.add("HoverLeft")
                        }
                        thing.setAttribute('href', "editEvent?eventid=" + combined_events[i].events[j].event_ID);
                        thing.addEventListener("click", function () {
                        });
                        thing.addEventListener("dblclick", function () {
                            window.location = this.getAttribute("href");
                        });
                        backgroundColor = "red";
                        textColor = "white"
                    } else if (j === 2) {
                        id = "day" + i + "r3e3";
                        thing = document.getElementById(id)
                        thing.classList.add("box")
                        thing.classList.add("orange")
                        var parent = (thing.parentElement.parentElement.parentElement);
                        var parentdayofweek = parseInt(parent.getAttribute("dayofweek"));

                        if (parentdayofweek<4){
                            thing.classList.add("HoverRight")
                        }
                        else {
                            thing.classList.add("HoverLeft")
                        }
                        thing.setAttribute('href', "editEvent?eventid=" + combined_events[i].events[j].event_ID);
                        thing.addEventListener("click", function () {
                        });
                        thing.addEventListener("dblclick", function () {
                            window.location = this.getAttribute("href");
                        });
                        backgroundColor = "orange";
                        textColor = "black"
                    } else if (j === 3) {
                        id = "day" + i + "r3e4";
                        thing = document.getElementById(id)
                        thing.classList.add("box")
                        thing.classList.add("yellow")
                        var parent = (thing.parentElement.parentElement.parentElement);
                        var parentdayofweek = parseInt(parent.getAttribute("dayofweek"));

                        if (parentdayofweek<4){
                            thing.classList.add("HoverRight")
                        }
                        else {
                            thing.classList.add("HoverLeft")
                        }
                        thing.setAttribute('href', "editEvent?eventid=" + combined_events[i].events[j].event_ID);
                        thing.addEventListener("click", function () {
                        });
                        thing.addEventListener("dblclick", function () {
                            window.location = this.getAttribute("href");
                        });
                        backgroundColor = "yellow";
                        textColor = "black"
                    } else if (j === 4) {
                        id = "day" + i + "r4e1";
                        thing = document.getElementById(id)
                        thing.classList.add("box")
                        thing.classList.add("lightgreen")
                        var parent = (thing.parentElement.parentElement.parentElement);
                        var parentdayofweek = parseInt(parent.getAttribute("dayofweek"));

                        if (parentdayofweek<4){
                            thing.classList.add("HoverRight")
                        }
                        else {
                            thing.classList.add("HoverLeft")
                        }
                        thing.setAttribute('href', "editEvent?eventid=" + combined_events[i].events[j].event_ID);
                        thing.addEventListener("click", function () {
                        });
                        thing.addEventListener("dblclick", function () {
                            window.location = this.getAttribute("href");
                        });
                        backgroundColor = "lightgreen";
                        textColor = "black"
                    } else if (j === 5) {
                        id = "day" + i + "r4e2";
                        thing = document.getElementById(id)
                        thing.classList.add("box")
                        thing.classList.add("green")
                        var parent = (thing.parentElement.parentElement.parentElement);
                        var parentdayofweek = parseInt(parent.getAttribute("dayofweek"));

                        if (parentdayofweek<4){
                            thing.classList.add("HoverRight")
                        }
                        else {
                            thing.classList.add("HoverLeft")
                        }
                        thing.setAttribute('href', "editEvent?eventid=" + combined_events[i].events[j].event_ID);
                        thing.addEventListener("click", function () {
                        });
                        thing.addEventListener("dblclick", function () {
                            window.location = this.getAttribute("href");
                        });
                        backgroundColor = "green";
                        textColor = "white"
                    } else if (j === 6) {
                        id = "day" + i + "r4e3";
                        thing = document.getElementById(id)
                        thing.classList.add("box")
                        thing.classList.add("blue")
                        var parent = (thing.parentElement.parentElement.parentElement);
                        var parentdayofweek = parseInt(parent.getAttribute("dayofweek"));

                        if (parentdayofweek<4){
                            thing.classList.add("HoverRight")
                        }
                        else {
                            thing.classList.add("HoverLeft")
                        }
                        thing.setAttribute('href', "editEvent?eventid=" + combined_events[i].events[j].event_ID);
                        thing.addEventListener("click", function () {
                        });
                        thing.addEventListener("dblclick", function () {
                            window.location = this.getAttribute("href");
                        });
                        backgroundColor = "blue";
                        textColor = "white"
                    } else if (j === 7) {
                        id = "day" + i + "r4e4";
                        thing = document.getElementById(id)
                        thing.classList.add("box")
                        thing.classList.add("indigo")
                        var parent = (thing.parentElement.parentElement.parentElement);
                        var parentdayofweek = parseInt(parent.getAttribute("dayofweek"));

                        if (parentdayofweek<4){
                            thing.classList.add("HoverRight")
                        }
                        else {
                            thing.classList.add("HoverLeft")
                        }
                        thing.setAttribute('href', "editEvent?eventid=" + combined_events[i].events[j].event_ID);
                        thing.addEventListener("click", function () {
                        });
                        thing.addEventListener("dblclick", function () {
                            window.location = this.getAttribute("href");
                        });
                        backgroundColor = "indigo";
                        textColor = "white"
                    } else if (j === 8) {
                        id = "day" + i + "r5e1";
                        thing = document.getElementById(id)
                        thing.classList.add("box")
                        thing.classList.add("violet")
                        var parent = (thing.parentElement.parentElement.parentElement);
                        var parentdayofweek = parseInt(parent.getAttribute("dayofweek"));

                        if (parentdayofweek<4){
                            thing.classList.add("HoverRight")
                        }
                        else {
                            thing.classList.add("HoverLeft")
                        }
                        thing.setAttribute('href', "editEvent?eventid=" + combined_events[i].events[j].event_ID);
                        thing.addEventListener("click", function () {
                        });
                        thing.addEventListener("dblclick", function () {
                            window.location = this.getAttribute("href");
                        });
                        backgroundColor = "violet";
                        textColor = "white"
                    } else if (j === 9) {
                        id = "day" + i + "r5e2";
                        thing = document.getElementById(id)
                        thing.classList.add("box")
                        thing.classList.add("lightgray")
                        var parent = (thing.parentElement.parentElement.parentElement);
                        var parentdayofweek = parseInt(parent.getAttribute("dayofweek"));

                        if (parentdayofweek<4){
                            thing.classList.add("HoverRight")
                        }
                        else {
                            thing.classList.add("HoverLeft")
                        }
                        thing.setAttribute('href', "editEvent?eventid=" + combined_events[i].events[j].event_ID);
                        thing.addEventListener("click", function () {
                        });
                        thing.addEventListener("dblclick", function () {
                            window.location = this.getAttribute("href");
                        });
                        backgroundColor = "lightgray";
                        textColor = "black"
                    } else if (j === 10) {
                        id = "day" + i + "r5e3";
                        thing = document.getElementById(id)
                        thing.classList.add("box")
                        thing.classList.add("darkgray")
                        var parent = (thing.parentElement.parentElement.parentElement);
                        var parentdayofweek = parseInt(parent.getAttribute("dayofweek"));

                        if (parentdayofweek<4){
                            thing.classList.add("HoverRight")
                        }
                        else {
                            thing.classList.add("HoverLeft")
                        }
                        thing.setAttribute('href', "editEvent?eventid=" + combined_events[i].events[j].event_ID);
                        thing.addEventListener("click", function () {
                        });
                        thing.addEventListener("dblclick", function () {
                            window.location = this.getAttribute("href");
                        });
                        backgroundColor = "darkgray";
                        textColor = "white"
                    } else if (j === 11) {
                        id = "day" + i + "r5e4";
                        thing = document.getElementById(id)
                        thing.classList.add("box")
                        thing.classList.add("black")
                        var parent = (thing.parentElement.parentElement.parentElement);
                        var parentdayofweek = parseInt(parent.getAttribute("dayofweek"));

                        if (parentdayofweek<4){
                            thing.classList.add("HoverRight")
                        }
                        else {
                            thing.classList.add("HoverLeft")
                        }
                        thing.setAttribute('href', "editEvent?eventid=" + combined_events[i].events[j].event_ID);
                        thing.addEventListener("click", function () {
                        });
                        thing.addEventListener("dblclick", function () {
                            window.location = this.getAttribute("href");
                        });
                        backgroundColor = "black";
                        textColor = "white"
                    }

                    var time = combined_events[i].events[j].date;
                    var date = new Date(time).toLocaleTimeString();


                    thing.innerHTML = "&nbsp<span style='color:" + textColor + "; background-color:" + backgroundColor + "' class='hiddendetails double-border' ><h3>" + combined_events[i].events[j].name
                        + "</h3></br>" + combined_events[i].events[j].description + "</br>" + date + "</br>" + combined_events[i].events[j].location+ "</span>";

                }
            }
        }


}

async function callAjaxMonth(month,search){
    $("#datesToSlide").slideUp();

    $.ajax   ({
        url: 'AJAXCALENDAR',
        data: "month="+month+"&year="+currentYear+"&search="+search,
        type: 'get',
        async: true,
        success: function(response) {
            $("#datesToSlide").slideDown();

            events = response;

            combine_array(events,culvers_filtered)

            addEventsToBoxes();



        }


    });



}
function combine_array(array1,array2){

    var resulting_array= JSON.parse(JSON.stringify(array1))

    if (array1.length==0 && array2.length>0){
        console.log("array 1 empty");
        return array2;
    }
    if (array1.length>0 && array2.length==0){
        console.log("array 2 empty");
        return array1;
    }

    if (array1.length<26||array2.length<26){

        console.log("one array is too short")
        //var result = []
        //return result;
    }
    var resulting_array = structuredClone(array1);
    for (i =1;i<array1.length&&i<array2.length;i++){

        for (j=0;j<array2[i].events.length;j++){

            resulting_array[i].events.push(array2[i].events[j])
        }
    }
    return resulting_array;
}
function filterArray(array,search){

    if (search===""){
        return array;
    }
    search=search.toLowerCase()

    var result = [];
    for(i=1;i<array.length;i++){
        result[i] = {events : []}

        for(j=0;j<array[i].events.length;j++){


            var name = array[i].events[j].name.toLowerCase();

            var desc = array[i].events[j].description.toLowerCase();

            if (name.search(search)>-1||desc.search(search)>-1){

                result[i].events.push(array[i].events[j])
            }
        }

    }

    return result;
}