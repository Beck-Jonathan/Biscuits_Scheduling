<%--************
Create the JSP For Viuw/Edit from the Culvers table
 Created By Jonathan Beck 11/21/2025
**********--%>
<%@include file="/WEB-INF/Schedule_App/schedule_top.jsp"%>
<div class = "container">
    <form method="post" action="${appURL}/editCulvers" id = "editCulvers" >
        <!-- Culvers_ID -->
        <div class ="row" id = "row0">
            <h2>User_ID  :
                ${fn:escapeXml(culvers.user_ID)}</h2>
        </div>

        <!-- Name -->
        <div class ="row" id = "row2">
            <label for="inputculversName" class="form-label">Name</label>
            <div class="input-group input-group-lg">
                <input type="text" class="<c:if test="${not empty results.culversNameerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Name" <c:if test="${mode eq 'view'}"> disabled </c:if>  id="inputculversName" name="inputculversName" value="${fn:escapeXml(culvers.name)}">
                <c:if test="${not empty results.culversNameerror}">
                    <div class="invalid-feedback">${results.culversNameerror}</div>
                </c:if>
            </div>
        </div>
        <!-- WebAddress -->
        <div class ="row" id = "row3">
            <label for="inputculversWebAddress" class="form-label">WebAddress</label>
            <div class="input-group input-group-lg">
                <input type="text" class="<c:if test="${not empty results.culversWebAddresserror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="WebAddress" <c:if test="${mode eq 'view'}"> disabled </c:if>  id="inputculversWebAddress" name="inputculversWebAddress" value="${fn:escapeXml(culvers.webAddress)}">
                <c:if test="${not empty results.culversWebAddresserror}">
                    <div class="invalid-feedback">${results.culversWebAddresserror}</div>
                </c:if>
            </div>
        </div>
        <div class="align-items-center mt-0">
            <div class="d-grid"><button id="submitButton" class="btn btn-orange mb-0" type="submit">Edit Culvers </button></div>
            <c:if test="${not empty results.dbStatus}"
            ><p>${results.dbStatus}</p>
            </c:if>
        </div>
    </form>
</div>
<%@include file="/WEB-INF/Schedule_App/schedule_bottom.jsp"%>

