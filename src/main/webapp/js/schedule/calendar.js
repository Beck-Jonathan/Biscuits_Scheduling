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
let is_pokemon =false;
let modified=false;
let combined_events = [];
let culvers_filtered=[];
let culvers_stored = [];
let pokemon_events=[];
let pokemon_filtered = [];
let pokemon_stored = [];
let currentPalette= 0;
let locatonids = ['r3e1','r3e2','r3e3','r3e4','r4e1','r4e2','r4e3','r4e4','r5e1','r5e2','r5e3','r5e4']


let palettes =[];
let palette0 =['#F94939','#FE9925','#FFFB14','#93FF05','#1DF500','#00E64D','#00D6A8','#0098C7','#003AB8','#1600A8','#590099','#8A0085','A']
let palette1 = ['#30CC14','#8CED31','#DAF467','#F9EB9F','#1430CC','#318CED','#67DAF4','#9FF9EB','#CC1430','#ED318C','#F467DA','#EB9FF9','B']
let palette2 = ['#70CC14','#CEED31','#F4DC67','#F9CC9F','#1470CC','#31CEED','#67F4DC','#9FF9CC','#CC1470','#ED31CE','#DC67F4','#CC9FF9','C']
let palette3 = ['#1417CC','#3173ED','#67C7F4','#9FF9F7','#CC1417','#ED3173','#F467C7','#F79FF9','#1ACC14','#76ED31','#C9F467','#F9F69F','D']
let palette4 =['#CC2414','#9D1545','#71145E','#391047','#14CC24','#479D15','#607114','#473810','#2414CC','#15479D','#146071','#104738','E']
let PaletteSelect = document.getElementById("selectPalette");
palettes[0]=palette0;
palettes[1]=palette1;
palettes[2]=palette2;
palettes[3]=palette3;
palettes[4]=palette4;
const months = [
    'January', 'February', 'March', 'April', 'May', 'June',
    'July', 'August', 'September', 'October', 'November', 'December'
];


for(var i = 0; i < palettes.length; i++) {
    var opt = palettes[i][12];
    var el = document.createElement("option");
    el.textContent = opt;
    el.value = i;
    PaletteSelect.appendChild(el);
}
const root = document.querySelector(":root"); //grabbing the root element
for (i=0;i<palettes[0].length;i++) {
    root.style.setProperty("--pseudo-backgroundcolor" + i, palettes[0][i]);
    if (colorIsDarkAdvanced(palettes[0][i])){
        root.style.setProperty("--pseudo-textcolor" + i, '#FFFFFF');
    }
    else {
        root.style.setProperty("--pseudo-textcolor" + i, '#000000');
    }

}

async function filter(){
    console.log(currentMonth);
    console.log((currentYear))

    searchBox = document.getElementById("searchBox")
    searchTerm= searchBox.value;
    culvers_filtered = filterArray(culvers_events,searchTerm);
    pokemon_filtered = filterArray(pokemon_events,searchTerm)

    var x =  await callAjaxMonth(currentMonth + 1, searchTerm);


    combined_events = combine_array(events,culvers_filtered);
    combined_events = combine_array(combined_events,pokemon_filtered)
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
function noPokemon(){
    is_pokemon=false;
    $("#Pokemon").slideDown();
    $("#noPokemon").slideUp();
    pokemon_filtered=[];

    renderCalendar(currentMonth, currentYear);
    addEventsToBoxes()
}
var myDate = new Date();
var mymydate = ('0'+ (parseInt(myDate.getMonth())+1)).slice(-2)+ '-'+myDate.getFullYear()    ;
$("#month").val(months[currentMonth]+" "+currentYear);
var dateNow = new Date();
//console.log("mymydate:"+mymydate);
$(".monthPicker").datepicker({
    valueDefault : dateNow,
    dateFormat: 'MM yy',
    changeMonth: true,
    changeYear: true,
    showButtonPanel: true,

    onClose: function(dateText, inst) {

        currentMonth = parseInt($("#ui-datepicker-div .ui-datepicker-month :selected").val());

        currentYear = $("#ui-datepicker-div .ui-datepicker-year :selected").val();
        console.log(currentMonth);
        console.log(currentYear)
        $(this).val($.datepicker.formatDate('MM yy', new Date(currentYear, currentMonth,1)));
        prevMonthBtn.disabled=true;
        nextMonthBtn.disabled=true;
        is_culvers=false;
        culvers_filtered = [];


        if(currentMonth-anchorMonth==1||currentMonth-anchorMonth==0||currentMonth-anchorMonth==-11) {
            $("#culvers").slideDown();
        }
        else {
            $("#culvers").slideUp();
        }
        $("#noculvers").slideUp();

        renderCalendar(currentMonth, currentYear);
        callAjaxMonth(currentMonth+1,searchTerm);
    }
});

$(".monthPicker").focus(function () {
    $(".ui-datepicker-calendar").hide();
    $("#ui-datepicker-div").position({
        my: "center top",
        at: "center bottom",
        of: $(this)
    });
});

function rainbow(){
    var currentPalNo = PaletteSelect.value
    console.log(currentPalNo)
    currentPalette=currentPalNo;

    for (i=0;i<palettes[currentPalette].length;i++) {
        root.style.setProperty("--pseudo-backgroundcolor" + i, palettes[currentPalette][i]);
        if (colorIsDarkAdvanced(palettes[currentPalette][i])){
            root.style.setProperty("--pseudo-textcolor" + i, '#FFFFFF');
        }
        else {
            root.style.setProperty("--pseudo-textcolor" + i, '#000000');
        }

    }
}
function Pokemon(){
    //var events = [];
    is_pokemon=true;

    $("#datesToSlide").slideUp();
    $("#Pokemon").slideUp();
    $("#noPokemon").slideDown();
    var month = currentMonth;

    //if (culvers_stored[currentMonth]==null) {
        $.ajax({
            url: 'AJAXPOKEMON',
            data: "month=" + month + "&year=" + currentYear,
            type: 'get',
            async: true,
            success: function (response) {
                pokemon_events = response;
                pokemon_filtered = response;
                for (i=0;i<pokemon_events.length;i++){
                    for (j=0;j<pokemon_events[i].events.length;j++){
                        pokemon_events[i].events[j].source="Pokemon"
                        pokemon_events[i].events[j].source="Pokemon"
                    }
                }
                console.log(pokemon_events)
                pokemon_stored[currentMonth]=pokemon_events;
                $("#datesToSlide").slideDown();
                addEventsToBoxes()
            }


        });
    //} else {

//culvers_events = culvers_stored[currentMonth];
      //  culvers_filtered = culvers_stored[currentMonth];
     //   $("#datesToSlide").slideDown();
       // addEventsToBoxes()
   // }

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
                for (i=0;i<culvers_events.length;i++){
                    for (j=0;j<culvers_events[i].events.length;j++){
                        culvers_events[i].events[j].source="Culvers"
                        culvers_events[i].events[j].source="Culvers"
                    }
                }

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



function renderCalendar(month, year) {
    calendarDates.innerHTML = '';
    boxMonth = currentMonth+1;
    //monthYear.innerHTML = "<a href='all-events?day="+"&month="+boxMonth+"&year="+currentYear+"'>"+months[month]+" "+year +"</a>";


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
$("#noPokemon").slideUp();
renderCalendar(currentMonth, currentYear);
callAjaxMonth(currentMonth+1,"");
prevMonthBtn.addEventListener('click', () => {
    prevMonthBtn.disabled=true;
    nextMonthBtn.disabled=true;

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
    $("#month").val(months[currentMonth]+" "+currentYear);


});

nextMonthBtn.addEventListener('click', () => {
    prevMonthBtn.disabled=true;
    nextMonthBtn.disabled=true;

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

    $("#month").val(months[currentMonth]+" "+currentYear);


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
        "    <div id=day"+dayno+"r3e1 class=\"col-2 \">\n" +
        "    &nbsp\n" +
        "     </div>\n" +
        "   \n" +
        "    <div id=day"+dayno+"r3e2 class=\"col-2 \">\n" +
        "    &nbsp\n" +
        "   </div>\n" +
        "    <div id=day"+dayno+"r3e3 class=\"col-2 \">\n" +
        "   \n" +
        "   </div>\n" +
        "    <div id=day"+dayno+"r3e4 class=\"col-2 \">\n" +
        "   \n" +
        "   </div>\n" +
        "   </div>\n" +
        "   <!-- end row 3 for real -->\n" +
        "   <!-- start row 4 -->\n" +
        "  <div class=\"row xxyy\">\n" +
        "    <div id=day"+dayno+"r4e1 class=\"col-2 \">\n" +
        "    &nbsp\n" +
        "     </div>\n" +
        "   \n" +
        "    <div id=day"+dayno+"r4e2 class=\"col-2 \">\n" +
        "    &nbsp\n" +
        "   </div>\n" +
        "   <div id=day"+dayno+"r4e3 class=\"col-2 \">\n" +
        "   \n" +
        "   </div>\n" +
        "    <div id=day"+dayno+"r4e4 class=\"col-2 \">\n" +
        "   \n" +
        "   </div>\n" +
        "   </div>\n" +
        "   <!-- end row 4 for real -->\n" +
        "   <!-- start row 5 -->\n" +
        "  <div class=\"row xxyy\">\n" +
        "    <div id=day"+dayno+"r5e1 class=\"col-2 \">\n" +
        "    &nbsp\n" +
        "     </div>\n" +
        "   \n" +
        "    <div id=day"+dayno+"r5e2 class=\"col-2 \">\n" +
        "    &nbsp\n" +
        "   </div>\n" +
        "    <div id=day"+dayno+"r5e3 class=\"col-2 \">\n" +
        "   \n" +
        "   </div>\n" +
        "    <div id=day"+dayno+"r5e4 class=\"col-2 \">\n" +
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
    combined_events = combine_array(combined_events,pokemon_filtered)


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

                        id = "day" + i + locatonids[j]
                        thing = document.getElementById(id)
                        thing.classList.add("box")
                        thing.classList.add("box"+j)

                        var parent = (thing.parentElement.parentElement.parentElement);
                        var parentdayofweek = parseInt(parent.getAttribute("dayofweek"));

                        if (parentdayofweek<4){
                            thing.classList.add("HoverRight")
                        }
                        else {
                            thing.classList.add("HoverLeft")
                        }

                        thing.setAttribute("source",combined_events[i].events[j].source)
                        thing.setAttribute('href', "editEvent?eventid=" + combined_events[i].events[j].event_ID);
                        thing.setAttribute('loop',j);
                        //thing.setAttribute('href', "eventid=" + combined_events[i].events[j].event_ID+"&viewmode=middle");
                        var href = thing.getAttribute("href");
                        thing.addEventListener("mouseover",function(){
                            var colorstring= "--pseudo-backgroundcolor"+this.getAttribute('loop')

                            var root = document.querySelector(':root');
                            var startcolor= root.style.getPropertyValue(colorstring);
                            var darkercolor = LightenDarkenColor(startcolor, -50);
                            var lightercolor = LightenDarkenColor(startcolor, 30);


                            root.style.setProperty("--pseudo-hovercolor", startcolor);
                            root.style.setProperty("--pseudo-hovercolorlighter", lightercolor);
                            root.style.setProperty("--pseudo-hovercolordarker", darkercolor);


                        })

                        thing.addEventListener("click", function () {
                        });
                        thing.addEventListener("dblclick", function () {
                            //$("#calendarmain").slideUp();
                            var source = this.getAttribute("source");
                            if (source==="Personal") {
                                window.location = this.getAttribute("href");
                            }
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
                        backgroundColor = palettes[currentPalette][j];
                if (colorIsDarkAdvanced(palettes[currentPalette][j])){
                    root.style.setProperty("--pseudo-textcolor" + currentPalette, '#FFFFFF');
                }
                else {
                    root.style.setProperty("--pseudo-textcolor" + currentPalette, '#000000');
                }


                    var time = combined_events[i].events[j].date;
                    var date = new Date(time).toLocaleTimeString();


                    thing.innerHTML = "&nbsp<span  ' class='hiddendetails hiddendetails"+j+" double-border' ><h3>" + combined_events[i].events[j].name
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
            for (i=0;i<events.length;i++){
                for (j=0;j<events[i].events.length;j++){
                    events[i].events[j].source="Personal"
                }
            }
            console.log(events);

            combine_array(events,culvers_filtered)

            addEventsToBoxes();
            prevMonthBtn.disabled=false;
            nextMonthBtn.disabled=false;

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
function LightenDarkenColor(col, amt) {

    var usePound = false;

    if (col[0] == "#") {
        col = col.slice(1);
        usePound = true;
    }

    var num = parseInt(col,16);

    var r = (num >> 16) + amt;

    if (r > 255) r = 255;
    else if  (r < 0) r = 0;

    var b = ((num >> 8) & 0x00FF) + amt;

    if (b > 255) b = 255;
    else if  (b < 0) b = 0;

    var g = (num & 0x0000FF) + amt;

    if (g > 255) g = 255;
    else if (g < 0) g = 0;

    return (usePound?"#":"") + (g | (b << 8) | (r << 16)).toString(16);

}

function colorIsDarkAdvanced(bgColor) {
    let color = (bgColor.charAt(0) === '#') ? bgColor.substring(1, 7) : bgColor;
    let r = parseInt(color.substring(0, 2), 16); // hexToR
    let g = parseInt(color.substring(2, 4), 16); // hexToG
    let b = parseInt(color.substring(4, 6), 16); // hexToB
    let uicolors = [r / 255, g / 255, b / 255];
    let c = uicolors.map((col) => {
        if (col <= 0.03928) {
            return col / 12.92;
        }
        return Math.pow((col + 0.055) / 1.055, 2.4);
    });
    let L = (0.2126 * c[0]) + (0.7152 * c[1]) + (0.0722 * c[2]);
    return L <= 0.179;
}

