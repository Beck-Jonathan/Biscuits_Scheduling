<%--************
Create the JSP  For Viewing All of The  Suggestion table
 Created By Jonathan Beck 5/1/2025
**********--%>
<%@include file="/WEB-INF/Schedule_App/schedule_top.jsp"%>
<div class = "container">
    <div class="row">
        <div class="col-12">
            <h1>All schedule_app Suggestions</h1>
            <p>There ${Suggestions.size() eq 1 ? "is" : "are"}&nbsp;${Suggestions.size()} Suggestion${Suggestions.size() ne 1 ? "s" : ""}</p>
            Add Suggestion   <a href="addSuggestion">Add</a>
            <c:if test="${Suggestions.size() > 0}">
                <div class="search-container">
                    <form action="all-Suggestions">
                        <input type="text" placeholder="Search.." id="searchBox" name="search">
                        <select name ="App" id="inputsuggestionApplication_Name">
                            <option value="" >All</option>
                            <option value="Budgeting" >Budgeting</option>
                            <option value="Homepage">Homepage</option>
                            <option value="Scheduling" selected>Scheduling</option>
                            <option value="Derby">CRRD Site</option>
                            <option value="Other">Other (Please Explain)</option>
                        </select>
                        <button type="submit"><i class="fa fa-search">search & filter</i></button>

                    </form>
                </div>

                <div class="table-responsive"><table class="table table-bordered">
                    <thead>
                    <tr>
                        <th scope="col"> Details </th>
                        <th scope="col">User</th>
                        <th scope="col">Application_Name</th>
                        <th scope="col">content</th>
                        <th scope="col">Delete</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${Suggestions}" var="suggestion">
                        <tr id="${suggestion.suggestion_ID}row">
                            <td>
                                <button class="detailButton" href="${suggestion.suggestion_ID}" >Details</button>
                            </td>
                            <td>${fn:escapeXml(suggestion.user.user_Name)}</td>
                            <td>${fn:escapeXml(suggestion.application_Name)}</td>
                            <td>${fn:escapeXml(suggestion.content)}</td>
                            <td>
                                <div>
                                <button class="delButton" href="${suggestion.suggestion_ID}" >Delete</button>
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

