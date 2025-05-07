<%--************
Create the JSP  For adding to The  Friend_Line table
 Created By Jonathan Beck 5/7/2025
**********--%>
<%@include file="/WEB-INF/Schedule_App/schedule_top.jsp"%>
<div class = "container">
    <form method="post" action="${appURL}/addFriend_Line" id = "addFriend_Line" >
        <!-- User_One -->
        <div class ="row" id = "row0">
            <label for="inputfriend_lineUser_One" class="form-label">Enter your friend's email</label>
            <div class="input-group input-group-lg">
                <input type="text" class="<c:if test="${not empty results.friend_lineUser_Oneerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="email" id="inputfriend_lineUser_One" name="inputfriend_lineUser_One" value="${fn:escapeXml(results.User_One)}">
                <c:if test="${not empty results.friend_lineUser_Oneerror}">
                    <div class="invalid-feedback">${results.friend_lineUser_Oneerror}</div>
                </c:if>

            </div>
        </div>

        <div class="align-items-center mt-0">
            <div class="d-grid"><button class="btn btn-orange mb-0" type="submit">Create Friend_Line  </button></div>
            <c:if test="${not empty results.dbStatus}"
            ><p>${results.dbStatus}</p>
            </c:if>
        </div>
    </form>
</div>
<%@include file="/WEB-INF/Schedule_App/schedule_bottom.jsp"%>

