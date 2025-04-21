const calendarDates = document.querySelector('.calendar-dates');
const monthYear = document.getElementById('month-year');
const prevMonthBtn = document.getElementById('prev-month');
const nextMonthBtn = document.getElementById('next-month');

let currentDate = new Date();
let currentMonth = currentDate.getMonth();
let currentYear = currentDate.getFullYear();
let searchTerm = "";

function filter(){
    searchBox = document.getElementById("searchBox")
    searchTerm= searchBox.value;
    if(searchTerm.length>2 && searchTerm.length<100) {
        console.log(searchBox);
        renderCalendar(currentMonth, currentYear);
        callAjaxMonth(currentMonth + 1, searchTerm);
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


renderCalendar(currentMonth, currentYear);
callAjaxMonth(currentMonth+1,"");
prevMonthBtn.addEventListener('click', () => {
    currentMonth--;
    if (currentMonth < 0) {
        currentMonth = 11;
        currentYear--;
    }

    renderCalendar(currentMonth, currentYear);
    callAjaxMonth(currentMonth+1,searchTerm);


});

nextMonthBtn.addEventListener('click', () => {
    currentMonth++;
    if (currentMonth > 11) {
        currentMonth = 0;
        currentYear++;
    }
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

function callAjaxMonth(month,search){
var events = "";
    $.ajax   ({
        url: 'AJAXCALENDAR',
        data: "month="+month+"&year="+currentYear+"&search="+search,
        type: 'get',
        async: false,
        success: function(response)
        {
           events = response ;
        }
    });
    for (i=0;i<events.length;i++){
        if(events[i].events.length>0){
            for (j=0;j<events[i].events.length;j++){
                var backgroundColor="pink";
                var textColor="black";
                var thing=null;
                var id = "";
                if (j===0){
                    id = "day"+i+"r3e1";
                    thing =  document.getElementById(id)
                    thing.classList.add("box")
                    thing.classList.add("pink")
                    thing.setAttribute('href',"editEvent?eventid="+events[i].events[j].event_ID);
                    thing.addEventListener( "click", function() {
                    } );
                    thing.addEventListener( "dblclick", function() {
                        window.location=this.getAttribute("href");
                    } );
                    backgroundColor="";
                    textColor=""
                }
                else if (j===1){
                    id = "day"+i+"r3e2";
                    thing =  document.getElementById(id)
                    thing.classList.add("box")
                    thing.classList.add("red")
                    thing.setAttribute('href',"editEvent?eventid="+events[i].events[j].event_ID);
                    thing.addEventListener( "click", function() {
                    } );
                    thing.addEventListener( "dblclick", function() {
                        window.location=this.getAttribute("href");
                    } );
                    backgroundColor="red";
                    textColor="white"
                }
                else if (j===2){
                    id = "day"+i+"r3e3";
                    thing =  document.getElementById(id)
                    thing.classList.add("box")
                    thing.classList.add("orange")
                    thing.setAttribute('href',"editEvent?eventid="+events[i].events[j].event_ID);
                    thing.addEventListener( "click", function() {
                    } );
                    thing.addEventListener( "dblclick", function() {
                        window.location=this.getAttribute("href");
                    } );
                    backgroundColor="orange";
                    textColor="black"
                }
                else if (j===3){
                    id = "day"+i+"r3e4";
                    thing =  document.getElementById(id)
                    thing.classList.add("box")
                    thing.classList.add("yellow")
                    thing.setAttribute('href',"editEvent?eventid="+events[i].events[j].event_ID);
                    thing.addEventListener( "click", function() {
                    } );
                    thing.addEventListener( "dblclick", function() {
                        window.location=this.getAttribute("href");
                    } );
                    backgroundColor="yellow";
                    textColor="black"
                }
                else if (j===4){
                    id = "day"+i+"r4e1";
                    thing =  document.getElementById(id)
                    thing.classList.add("box")
                    thing.classList.add("lightgreen")
                    thing.setAttribute('href',"editEvent?eventid="+events[i].events[j].event_ID);
                    thing.addEventListener( "click", function() {
                    } );
                    thing.addEventListener( "dblclick", function() {
                        window.location=this.getAttribute("href");
                    } );                    backgroundColor="lightgreen";
                    textColor="black"
                }
                else if (j===5){
                    id = "day"+i+"r4e2";
                    thing =  document.getElementById(id)
                    thing.classList.add("box")
                    thing.classList.add("green")
                    thing.setAttribute('href',"editEvent?eventid="+events[i].events[j].event_ID);
                    thing.addEventListener( "click", function() {
                    } );
                    thing.addEventListener( "dblclick", function() {
                        window.location=this.getAttribute("href");
                    } );                    backgroundColor="green";
                    textColor="white"
                }
                else if (j===6){
                    id = "day"+i+"r4e3";
                    thing =  document.getElementById(id)
                    thing.classList.add("box")
                    thing.classList.add("blue")
                    thing.setAttribute('href',"editEvent?eventid="+events[i].events[j].event_ID);
                    thing.addEventListener( "click", function() {
                    } );
                    thing.addEventListener( "dblclick", function() {
                        window.location=this.getAttribute("href");
                    } );                    backgroundColor="blue";
                    textColor="white"
                }
                else if (j===7){
                    id = "day"+i+"r4e4";
                    thing =  document.getElementById(id)
                    thing.classList.add("box")
                    thing.classList.add("indigo")
                    thing.setAttribute('href',"editEvent?eventid="+events[i].events[j].event_ID);
                    thing.addEventListener( "click", function() {
                    } );
                    thing.addEventListener( "dblclick", function() {
                        window.location=this.getAttribute("href");
                    } );                    backgroundColor="indigo";
                    textColor="white"
                }
                else if (j===8){
                    id = "day"+i+"r5e1";
                    thing =  document.getElementById(id)
                    thing.classList.add("box")
                    thing.classList.add("violet")
                    thing.setAttribute('href',"editEvent?eventid="+events[i].events[j].event_ID);
                    thing.addEventListener( "click", function() {
                    } );
                    thing.addEventListener( "dblclick", function() {
                        window.location=this.getAttribute("href");
                    } );                    backgroundColor="violet";
                    textColor="white"
                }
                else if (j===9){
                    id = "day"+i+"r5e2";
                    thing =  document.getElementById(id)
                    thing.classList.add("box")
                    thing.classList.add("lightgray")
                    thing.setAttribute('href',"editEvent?eventid="+events[i].events[j].event_ID);
                    thing.addEventListener( "click", function() {
                    } );
                    thing.addEventListener( "dblclick", function() {
                        window.location=this.getAttribute("href");
                    } );                    backgroundColor="lightgray";
                    textColor="black"
                }
                else if (j===10){
                    id = "day"+i+"r5e3";
                    thing =  document.getElementById(id)
                    thing.classList.add("box")
                    thing.classList.add("darkgray")
                    thing.setAttribute('href',"editEvent?eventid="+events[i].events[j].event_ID);
                    thing.addEventListener( "click", function() {
                    } );
                    thing.addEventListener( "dblclick", function() {
                        window.location=this.getAttribute("href");
                    } );                    backgroundColor="darkgray";
                    textColor="white"
                }
                else if (j===11){
                    id = "day"+i+"r5e4";
                    thing =  document.getElementById(id)
                    thing.classList.add("box")
                    thing.classList.add("black")
                    thing.setAttribute('href',"editEvent?eventid="+events[i].events[j].event_ID);
                    thing.addEventListener( "click", function() {
                    } );
                    thing.addEventListener( "dblclick", function() {
                        window.location=this.getAttribute("href");
                    } );                    backgroundColor="black";
                    textColor="white"
                }

                    var time =events[i].events[j].date;
                    var date = new Date(time).toLocaleTimeString();


                    thing.innerHTML="&nbsp<span style='color:"+textColor+"; background-color:"+backgroundColor+"' class='hiddendetails double-border' ><h3>"+events[i].events[j].name
                        +"</h3></br>"+events[i].events[j].description+"</br>"+date+"</span>";

                }
            }
        }


}
