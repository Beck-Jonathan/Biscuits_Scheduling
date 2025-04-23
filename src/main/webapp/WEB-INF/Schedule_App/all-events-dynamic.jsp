<%@include file="/WEB-INF/Schedule_App/schedule_top.jsp"%>


<div id="calendar"></div>
<script src="//cdnjs.cloudflare.com/ajax/libs/moment.js/2.5.1/moment.min.js"></script>
<div class="row">
<div class="search-container col-md-3">
    <input type="text" id="searchBox" onkeyup="filter()" placeholder="Search for names..">
</div>
    <div class="col-md-5">

    </div>
    <div class="col-md-1">
        <button id="noculvers" onclick="noculvers()">no culvers</button>
    </div>
    <div class="col-md-1">
        <button id="culvers" onclick="culvers()">culvers</button>
    </div>
</div>

<div class="calendar" id="calendar">
    <div class="calendar-header">
        <button id="prev-month">‹</button>
        <div id="month-year"></div>
        <button id="next-month">›</button>
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
</body>




<%@include file="/WEB-INF/Schedule_App/schedule_bottom.jsp"%>