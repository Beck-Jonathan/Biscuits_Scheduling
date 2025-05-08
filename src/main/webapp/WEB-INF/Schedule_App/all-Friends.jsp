<%--************
Create the JSP  For Viewing All of The  Friend_Line table
 Created By Jonathan Beck 5/7/2025
**********--%>
<%@include file="/WEB-INF/Schedule_App/schedule_top.jsp" %>
<div class="container">
    <div class="row">
        <div class="col-12">
            <h1>Friend Management</h1>

            Add Friend <a href="addFriend_Line">Add</a>
            <c:if test="${approved_Friends.size() > 0}">

                <h3>Your Friends</h3>
                <p>You have &nbsp;${approved_Friends.size()} Friend
                        ${approved_Friends.size() ne 1 ? "s" : ""}</p>
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
                        <c:forEach items="${approved_Friends}" var="friend_line">

                            <tr id="${friend_line.user2.user_ID}row">

                                <td>
                                    ${friend_line.user2.user_Name}
                                </td>

                                <td>${fn:escapeXml(friend_line.last_Updated)}</td>

                                <td>
                                        <button class="delButton" href="${friend_line.user2.user_ID}">Delete</button>
                                </td>
                                <td>
                                <button class="viewButton" href="${friend_line.user2.user_ID}">View Events</button>
                                </td>
                            </tr>

                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:if>
            <c:if test="${outgoing_friends.size() > 0}">
                <h3>Your outgoing requests</h3>
                <p>You have &nbsp;${outgoing_friends.size()} Friend
                        ${outgoing_friends.size() ne 1 ? "s" : ""}</p>
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
                        <c:forEach items="${outgoing_friends}" var="friend_line">

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

                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:if>
            <c:if test="${incoming_friends.size() > 0}">
                <h3>Your incoming requests</h3>
                <p>You have &nbsp;${incoming_friends.size()} Friend
                        ${incoming_friends.size() ne 1 ? "s" : ""}</p>
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
                        <c:forEach items="${incoming_friends}" var="friend_line">

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
                                <button class="approveButton" href="${friend_line.user2.user_ID}">Approve</button>
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
<%@include file="/WEB-INF/Schedule_App/schedule_bottom.jsp" %>

