<%--************
Create the JSP  For Viewing All of The  Event table
 Created By Jonathan Beck 4/9/2025
**********--%>
<%@include file="/WEB-INF/Schedule_App/schedule_top.jsp"%>
<div class = "container">
    <div class="row">
        <div class="col-12">
            <h1>All Family Events</h1>
            <p>There ${Events.size() eq 1 ? "is" : "are"}&nbsp;${Events.size()} Event${Events.size() ne 1 ? "s" : ""}</p>
            Add Event   <a href="addEvent?day=${results.day}&month=${results.month}&year=${results.year}">Add</a>
            <c:if test="${Events.size() > 0}">
                Export Event   <a href="exportEvent?mode=export">Export</a>
                Write To SQL File Event   <a href="exportEvent?mode=SQL">SQL</a>
                <div class="search-container">
                    <form action="all-events">
                        <input type="text" placeholder="Search.." id="searchBox" name="search">
                        <button type="submit"><i class="fa fa-search"></i></button>
                    </form>
                </div>
                <div class="table-responsive"><table class="table table-bordered">
                    <thead>
                    <tr>
                        <th scope="col">Details</th>
                        <th scope="col">Name</th>
                        <th scope="col">Date</th>
                        <th scope="col">Location</th>
                        <th scope="col">description</th>
                        <th scope="col">Length</th>
                        <th scope="col">Decision</th>
                        <th scope="col">Paid</th>
                        <th scope="col">Delete</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${Events}" var="event">
                           <tr id="${event.event_ID}row">
                            <td><a href = "editEvent?eventid=${event.event_ID}">View Detail</a></td>
                            <td>${fn:escapeXml(event.name)}</td>
                            <td>${fn:escapeXml(event.date)}</td>
                            <td>${fn:escapeXml(event.location)}</td>
                            <td>${fn:escapeXml(event.description)}</td>
                            <td><c:choose>
                                    <c:when test = "${event.length < 1}">
                                        ${Math.round(event.length*60)} minutes
                                    </c:when>

                                    <c:when test = "${event.length >=1 && event.length <24}">
                                        ${event.length} hours
                                    </c:when>

                                    <c:when test = "${event.length >24}">
                                        ${event.length div 24} days
                                    </c:when>
                                </c:choose>
                            </td>
                            <td>${fn:escapeXml(event.decision)}</td>
                            <td>${fn:escapeXml(event.paid)}</td>
                            <td>
                                <div>
                                    <button class="delButton" href="${event.event_ID}" >Delete</button>
                                </div>
                                <div style="display: none;" id="event.event_IDStatus">

                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                </div>
            </c:if>
        </div>
    </div>
</div>
<div id="dialog" title="Confirmation Required">
    Are you sure about this?
</div>
</main>
<%@include file="/WEB-INF/Schedule_App/schedule_bottom.jsp"%>

