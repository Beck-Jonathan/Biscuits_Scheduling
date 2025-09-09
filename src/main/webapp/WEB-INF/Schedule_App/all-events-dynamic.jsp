<%@include file="/WEB-INF/Schedule_App/schedule_top.jsp"%>



<script src="//cdnjs.cloudflare.com/ajax/libs/moment.js/2.5.1/moment.min.js"></script>
<div id="calendarmain">
<div class="row">
<div class="search-container col-2">
</div>
    <div class="col-2">

    </div>
    <div class="col-2">

    </div>
    <div class="col-2">

    </div>
    <div class="col-2">

    </div>
</div>

<div class="calendar" >
    <div class="calendar-header row">
        <div class="col-3">
            <input type="text" id="searchBox" onkeyup="filter()" placeholder="Search for names..">
                <br/>

            <select onchange=rainbow() id="selectPalette">
                <option>Choose a Palette</option>
            </select>
        </div>
        <div class="col-1"><button id="prev-month">‹</button></div>

        <div class="col-3" id="month-year">
            <label for="month">Month: </label>
            <input type="text" id="month" name="month" class="monthPicker" />
        </div>

        <div class="col-1"><button id="next-month">›</button></div>
        <div class="col-2 sliderSelect">
            <div class="container">

            <div class="row">
                <div class="col-5">
                    Pokemon
                </div>
                <div class="col-7">
                    <label class="switch">
                        <input  id="Pokemon" type="checkbox" class="EventSlider" >
                        <span class="slider round EventSlider"></span>
                    </label>

                </div>
            </div>
                <div class="row">
                    <div class="col-5">
                        Lunch
                    </div>
                    <div class="col-7">
                        <label class="switch">
                            <input id= "Lunch" type="checkbox" class="EventSlider" >
                            <span class="slider round EventSlider"></span>
                        </label>

                    </div>
                </div>
                <div class="row">
                    <div class="col-5">
                        Culvers
                    </div>
                    <div class="col-7">
                        <label class="switch">
                            <input id="culvers" type="checkbox" class="EventSlider">
                            <span class="slider round EventSlider"></span>
                        </label>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-2 sliderSelect">
            <div class="container">

                <div class="row">
                    <div class="col-5">
                        Yes
                    </div>
                    <div class="col-7">
                        <label class="switch">
                            <input  id="dec_Yes" type="checkbox" class="DecisionSlider" checked>
                            <span class="slider round DecisionSlider"></span>
                        </label>

                    </div>
                </div>
                <div class="row">
                    <div class="col-5">
                        No
                    </div>
                    <div class="col-7">
                        <label class="switch">
                            <input id= "dec_No" type="checkbox" class="DecisionSlider" checked >
                            <span class="slider round DecisionSlider"></span>
                        </label>

                    </div>
                </div>
                <div class="row">
                    <div class="col-5">
                        Maybe
                    </div>
                    <div class="col-7">
                        <label class="switch">
                            <input id="dec_Maybe" type="checkbox" class="DecisionSlider" checked>
                            <span class="slider round DecisionSlider"></span>
                        </label>
                    </div>
                </div>
            </div>
        </div>
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