<%--************
Create the JSP  For adding to The  Culvers table
 Created By Jonathan Beck 11/21/2025
**********--%>
<%@include file="/WEB-INF/Schedule_App/schedule_top.jsp"%>
<div class = "container">
    <form method="post" action="${appURL}/addCulvers" id = "addCulvers" >

        <!-- Name -->
        <div class ="row" id = "row1">
            <label for="inputculversName" class="form-label">Name</label>
            <div class="input-group input-group-lg">
                <input type="text" class="<c:if test="${not empty results.culversNameerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Name" id="inputculversName" name="inputculversName" value="${fn:escapeXml(results.Name)}">
                <c:if test="${not empty results.culversNameerror}">
                    <div class="invalid-feedback">${results.culversNameerror}</div>
                </c:if>
            </div>
        </div>
        <!-- WebAddress -->
        <div class ="row" id = "row2">
            <label for="inputculversWebAddress" class="form-label">WebAddress</label>
            <div class="input-group input-group-lg">
                <input type="text" class="<c:if test="${not empty results.culversWebAddresserror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="WebAddress" id="inputculversWebAddress" name="inputculversWebAddress" value="${fn:escapeXml(results.WebAddress)}">
                <c:if test="${not empty results.culversWebAddresserror}">
                    <div class="invalid-feedback">${results.culversWebAddresserror}</div>
                </c:if>
            </div>
        </div>
        <div class="align-items-center mt-0">
            <div class="d-grid"><button id="submitButton" class="btn btn-orange mb-0" type="submit">Create Culvers  </button></div>
            <c:if test="${not empty results.dbStatus}"
            ><p>${results.dbStatus}</p>
            </c:if>
        </div>
    </form>
</div>
<%@include file="/WEB-INF/Schedule_App/schedule_bottom.jsp"%>

