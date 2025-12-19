</div> <!-- end row-->
<div class = "col-md-1 ps-0 " id="right" alt="Right Hand Border" ></div>

</div> <!-- end row-->




<footer>
    <div class="row">
        <div class="hidden-sm col-md-2" id="bottomleftblack" ></div>
        <div class="col-md-2 col-sm-2" id="howtoplay"></div>
        <div class="col-md-4 col-sm-8" id="footerimage"></div>
        <div class="col-md-2 col-sm-2" id="privacypolicy"><p><a href="howtouse"> How To Use</a></p></div>
        <div class="hidden-sm col-md-2  " id="bottomrightblack"></div>

    </div>
</footer>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="js/jquery.validate.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.13.2/jquery-ui.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.js"></script>

<script src="js/schedule/site.js"></script>

<c:if test="${pageTitle eq 'All Events - Calendar'}">
    <script src="js/schedule/calendar.js"></script>
</c:if>


<c:if test="${pageTitle eq 'Join Us!'}">
    <script src="js/schedule/SignUp.js"></script>
</c:if>

<c:if test="${pageTitle eq 'Add Event' || pageTitle eq 'Edit Event'}">
    <script src="js/schedule/addEvent.js"></script>
</c:if>
<c:if test="${pageTitle eq 'All Events - List'}">
    <script src="js/schedule/allEvents.js"></script>
</c:if>

<c:if test="${pageTitle eq 'Add Suggestion'}">
    <script src="js/schedule/addSuggestion.js"></script>
</c:if>

<c:if test="${pageTitle eq 'All Suggestions'}">
    <script src="js/schedule/allSuggestion.js"></script>
</c:if>

<c:if test="${pageTitle eq 'Manage Friends'}">
    <script src="js/schedule/allFriends.js"></script>
</c:if>

<c:if test="${pageTitle eq 'Add a Friend'}">
    <script src="js/schedule/addFriend.js"></script>
</c:if>
<c:if test="${pageTitle eq 'All Culvers'}">
    <script src="js/schedule/allCulvers.js"></script>
</c:if>
<c:if test="${pageTitle eq 'Add Culvers'|| pageTitle eq 'Edit Culvers'}">
    <script src="js/schedule/addCulvers.js"></script>
</c:if>

<c:if test="${pageTitle eq 'All Palettes'}">
    <script src="js/schedule/allPalettes.js"></script>
</c:if>

<c:if test="${pageTitle eq 'Add Palette'|| pageTitle eq 'Edit Palette'}">
    <script src="js/schedule/addPalette.js"></script>
</c:if>


</body>


</html>

