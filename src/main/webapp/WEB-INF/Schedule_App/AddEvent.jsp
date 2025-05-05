<%--************
Create the JSP  For adding to The  Event table
 Created By Jonathan Beck 4/9/2025
**********--%>
<%@include file="/WEB-INF/Schedule_App/schedule_top.jsp"%>
<div class = "container">
    <form method="post" action="${appURL}/addEvent" id = "addEvent" >

        <!-- Name -->
        <div class ="row" id = "row1">
            <label for="inputeventName" class="form-label">Name</label>
            <div class="input-group input-group-lg">
                <input type="text" class="<c:if test="${not empty results.eventNameerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Name" id="inputeventName" name="inputeventName" value="${fn:escapeXml(results.Name)}">
                <c:if test="${not empty results.eventNameerror}">
                    <div class="invalid-feedback">${results.eventNameerror}</div>
                </c:if>
            </div>
        </div>
        <!-- Date -->
        <div class ="row" id = "row2">
            <label for="inputeventDate" class="form-label">Date</label>
            <div class="input-group input-group-lg">
                <input type="datetime-local" class="<c:if test="${not empty results.eventDateerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Date" id="inputeventDate" name="inputeventDate" value="${fn:escapeXml(results.Date)}">
                <c:if test="${not empty results.eventDateerror}">
                    <div class="invalid-feedback">${results.eventDateerror}</div>
                </c:if>
            </div>
        </div>
        <!-- Location -->
        <div class ="row" id = "row3">
            <label for="inputeventLocation" class="form-label">Location</label>
            <div class="input-group input-group-lg">
                <input type="text" class="<c:if test="${not empty results.eventLocationerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Location" id="inputeventLocation" name="inputeventLocation" value="${fn:escapeXml(results.Location)}">
                <c:if test="${not empty results.eventLocationerror}">
                    <div class="invalid-feedback">${results.eventLocationerror}</div>
                </c:if>
            </div>
        </div>
        <!-- description -->
        <div class ="row" id = "row4">
            <label for="inputeventdescription" class="form-label">description</label>
            <div class="input-group input-group-lg">
                <input type="text" class="<c:if test="${not empty results.eventdescriptionerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="description" id="inputeventdescription" name="inputeventdescription" value="${fn:escapeXml(results.description)}">
                <c:if test="${not empty results.eventdescriptionerror}">
                    <div class="invalid-feedback">${results.eventdescriptionerror}</div>
                </c:if>
            </div>
        </div>
        <!-- Length -->
        <div class ="row" id = "row5">
            <label for="inputeventLength" class="form-label">Length</label>
            <div class="input-group input-group-lg">
                <input type="number" step='0.1' class="<c:if test="${not empty results.eventLengtherror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Length" id="inputeventLength" name="inputeventLength" value="${fn:escapeXml(results.Length)}">
                <c:if test="${not empty results.eventLengtherror}">
                    <div class="invalid-feedback">${results.eventLengtherror}</div>
                </c:if>
                <select class="<c:if test="${not empty results.eventDescisionerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1"  id="inputeventUnit" name="inputeventUnit">
                    <option value="Minutes">Minutes</option>
                    <option value="Hours" selected>Hours</option>
                    <option value="Days">Days</option>
                </select>
            </div>
        </div>
        <!-- Descision -->
        <div class ="row" id = "row6">
            <label for="inputeventDescision" class="form-label">Descision</label>
            <div class="input-group input-group-lg">
                <select class="<c:if test="${not empty results.eventDescisionerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1"  id="inputeventDescision" name="inputeventDescision">
                <option value="Going">Yes</option>
                <option value="Skipping" selected>No</option>
                <option value="Maybe">Maybe</option>
                </select>
            </div>
        </div>
        <!-- Paid -->
        <div class ="row" id = "row7">
            <label for="inputeventPaid" class="form-label">Paid</label>
            <div class="input-group input-group-lg">
                <select  class="<c:if test="${not empty results.eventPaiderror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1"  id="inputeventPaid" name="inputeventPaid" >
                    <option value="Yes">Yes</option>
                    <option value="No" selected>No</option>
                    <option value="N/A">N/A</option>
                </select>
            </div>
        </div>

        <!-- recurrences -->

        <div class ="row" id = "row8">
            <label for="inputEventRecur" class="form-label">Repeat </label>
            <div class="input-group input-group-lg row">
                <div class="col-md-1">
                    <input type="radio" id="yes" name="bool_recur" value="yes" >
                    <label for="yes">Yes</label><br>
                </div>
                <div class="col-md-1">
                <input type="radio" id="no" checked name="bool_recur" value="no">
                <label for="no">No</label><br>
                </div>
            </div>
            <div class="input-group input-group-lg row" id="recur_row" style="display: none">
                <input type="number" class="<c:if test="${not empty results.eventLengtherror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Length" id="inputEventRecur" name="inputEventRecur" value="${fn:escapeXml(results.Length)}">

                <select class="<c:if test="${not empty results.eventDescisionerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1"  id="inputEventPeriod" name="inputEventPeriod">
                    <option value="day">Days</option>
                    <option value="week" >Weeks</option>
                    <option value="month" selected>Months</option>
                    <option value="year">Years</option>
                </select>
            </div>
        </div>
        <!-- submit -->
        <div class="align-items-center mt-0">
            <div class="d-grid"><button class="btn btn-orange mb-0" type="submit">Create Event  </button></div>
            <c:if test="${not empty results.dbStatus}"
            ><p>${results.dbStatus}</p>
            </c:if>
        </div>
    </form>
</div>
<%@include file="/WEB-INF/Schedule_App/schedule_bottom.jsp"%>

