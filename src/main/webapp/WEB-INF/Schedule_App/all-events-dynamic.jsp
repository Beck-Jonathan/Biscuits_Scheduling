<%@include file="/WEB-INF/Schedule_App/schedule_top.jsp"%>



<script src="//cdnjs.cloudflare.com/ajax/libs/moment.js/2.5.1/moment.min.js"></script>
<div id="calendarmain">
<div class="row">
<div class="search-container col-3">
    <input type="text" id="searchBox" onkeyup="filter()" placeholder="Search for names..">
</div>
    <div class="col-3">
        <select onchange=rainbow() id="selectPalette">
            <option>Choose a Palette</option>
        </select>
    </div>
    <div class="col-1">
        <button id="noPokemon" onclick="noPokemon()">no Pokemon</button>
    </div>
    <div class="col-1">
        <button id="Pokemon" onclick="Pokemon()">Pokemon</button>
    </div>
    <div class="col-1">
        <button id="noculvers" onclick="noculvers()">no culvers</button>
    </div>
    <div class="col-1">
        <button id="culvers" onclick="culvers()">culvers</button>
    </div>
</div>

<div class="calendar" >
    <div class="calendar-header row">
        <div class="col-4"></div>
        <div class="col-1"><button id="prev-month">‹</button></div>

        <div class="col-1" id="month-year"></div>

        <div class="col-1"><button id="next-month">›</button></div>
        <div class="col-4"></div>
    </div>
    <div class="calendar-body">
        <div class="calendar-weekdays">

                <div>Sun</div>
                <div>Mon</div>
                <div>Tue</div>
                <div>Wed</div>
                <div>Thu</div>
                <div>Fri</div>
                <div>Sat</div>


        </div>
        <div class="calendar-dates" id="datesToSlide">
            <!-- Dates will be populated here -->
        </div>
    </div>
</div>
</div>
</body>




<%@include file="/WEB-INF/Schedule_App/schedule_bottom.jsp"%>