<%--************
Create the JSP  For Viewing All of The  Friend_Line table
 Created By Jonathan Beck 5/7/2025
**********--%>
<%@include file="/WEB-INF/Schedule_App/schedule_top.jsp" %>
<div class="container">
    <div class="row">
        <div class="col-12">
            <h1>Friend Management</h1>
            <p>You have &nbsp;${Friend_Lines.size()} Friend
                ${Friend_Lines.size() ne 1 ? "s" : ""}</p>
            Add Friend <a href="addFriend_Line">Add</a>
            <c:if test="${Friend_Lines.size() > 0}">

                <h3>Your Friends</h3>
                <div class="table-responsive">
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th scope="col">User_Two</th>
                            <th scope="col">Friends Since</th>
                            <th scope="col">Delete</th>
                            <th scope="col">View Events</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${Friend_Lines}" var="friend_line">
                            <c:if test="${fn:substring(friend_line.status,0,8) eq 'Accepted'}">
                            <tr id="${friend_line.user2.user_ID}row">

                                <td>
                                    ${friend_line.user2.user_Name}
                                </td>

                                <td>${fn:escapeXml(friend_line.last_Updated)}</td>

                                <td>
                                        <button class="delButton" href="${friend_line.user2.user_ID}">Delete</button>
                                </td>
                                <td>
                                <button class="delButton" href="${friend_line.user2.user_ID}">View Events</button>
                                </td>
                            </tr>
                            </c:if>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <h3>Your outgoing requests</h3>
                <div class="table-responsive">
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th scope="col">User Name</th>
                            <th scope="col">Status</th>
                            <th scope="col">Sent</th>

                            <th scope="col">Delete</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${Friend_Lines}" var="friend_line">
                            <c:if test="${friend_line.status eq 'pending - sent'}">
                            <tr id="${friend_line.user2.user_ID}row">

                                <td>
                                        ${friend_line.user2.user_Name}
                                </td>
                                <td>${fn:escapeXml(friend_line.status)}</td>
                                <td>${fn:escapeXml(friend_line.last_Updated)}</td>

                                <td>
                                    <button class="delButton" href="${friend_line.user2.user_ID}">Delete</button>
                                </td>
                            </tr>
                        </c:if>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <h3>Your incoming requests</h3>
                <div class="table-responsive">
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th scope="col">User Name</th>
                            <th scope="col">Status</th>
                            <th scope="col">Sent</th>

                            <th scope="col">Decline</th>
                            <th scope="col">Approve</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${Friend_Lines}" var="friend_line">
                            <c:if test="${friend_line.status eq 'pending - received'}">
                            <tr id="${friend_line.user2.user_ID}row">

                                <td>
                                        ${friend_line.user2.user_Name}
                                </td>
                                <td>${fn:escapeXml(friend_line.status)}</td>
                                <td>${fn:escapeXml(friend_line.last_Updated)}</td>

                                <td>
                                    <button class="delButton" href="${friend_line.user2.user_ID}">Delete</button>
                            </td>
                                <td>
                                <button class="delButton" href="${friend_line.user2.user_ID}">Approve</button>
                                </td>
                            </tr>
                            </c:if>
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
<%@include file="/WEB-INF/Schedule_App/schedule_bottom.jsp" %>

