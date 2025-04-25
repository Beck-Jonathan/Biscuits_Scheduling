<%--************
Create the JSP For Viuw/Edit from the Event table
 Created By Jonathan Beck 4/10/2025
**********--%>
<c:if test="${empty viewmode}">

<%@include file="/WEB-INF/Schedule_App/schedule_top.jsp"%>
</c:if>
<div class = "container">
    <form method="post" action="${appURL}/editEvent" id = "editEvent" >
        <!-- Event_ID -->
        <div class ="row" id = "row0">
            <h2>Name  :
                ${fn:escapeXml(event.name)}</h2>
        </div>
        <!-- Name -->
        <div class ="row" id = "row1">
            <label for="inputeventName" class="form-label">Name</label>
            <div class="input-group input-group-lg">
                <input type="text" class="<c:if test="${not empty results.eventNameerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Name" <c:if test="${mode eq 'view'}"> disabled </c:if>  id="inputeventName" name="inputeventName" value="${fn:escapeXml(event.name)}">
                <c:if test="${not empty results.eventNameerror}">
                    <div class="invalid-feedback">${results.eventNameerror}</div>
                </c:if>
            </div>
        </div>
        <!-- Date_Time -->
        <div class ="row" id = "row2">
            <label for="inputeventDate_Time" class="form-label">Date_Time</label>
            <div class="input-group input-group-lg">
                <input type="datetime-local" class="<c:if test="${not empty results.eventDate_Timeerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Date_Time" <c:if test="${mode eq 'view'}"> disabled </c:if>  id="inputeventDate_Time" name="inputeventDate_Time" value="${fn:escapeXml(event.date)}">
                <c:if test="${not empty results.eventDate_Timeerror}">
                    <div class="invalid-feedback">${results.eventDate_Timeerror}</div>
                </c:if>
            </div>
        </div>
        <!-- Location -->
        <div class ="row" id = "row3">
            <label for="inputeventLocation" class="form-label">Location</label>
            <div class="input-group input-group-lg">
                <input type="text" class="<c:if test="${not empty results.eventLocationerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Location" <c:if test="${mode eq 'view'}"> disabled </c:if>  id="inputeventLocation" name="inputeventLocation" value="${fn:escapeXml(event.location)}">
                <c:if test="${not empty results.eventLocationerror}">
                    <div class="invalid-feedback">${results.eventLocationerror}</div>
                </c:if>
            </div>
        </div>
        <!-- Description -->
        <div class ="row" id = "row4">
            <label for="inputeventDescription" class="form-label">Description</label>
            <div class="input-group input-group-lg">
                <input type="text" class="<c:if test="${not empty results.eventDescriptionerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Description" <c:if test="${mode eq 'view'}"> disabled </c:if>  id="inputeventDescription" name="inputeventDescription" value="${fn:escapeXml(event.description)}">
                <c:if test="${not empty results.eventDescriptionerror}">
                    <div class="invalid-feedback">${results.eventDescriptionerror}</div>
                </c:if>
            </div>
        </div>
        <!-- Length -->
        <div class ="row" id = "row5">
            <label for="inputeventLength" class="form-label">Length</label>
            <div class="input-group input-group-lg">
                <input type="number" class="<c:if test="${not empty results.eventLengtherror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Length" id="inputeventLength" name="inputeventLength"
                       value="<c:choose>
                                    <c:when test = "${event.length < 1}">
                                        ${Math.round(event.length*60)}
                                    </c:when>

                                    <c:when test = "${event.length >=1 && event.length <=24}">
                                        ${event.length}
                                    </c:when>

                                    <c:when test = "${event.length >24}">
                                        ${event.length div 24}
                                    </c:when>
                                </c:choose>"


                >
                <c:if test="${not empty results.eventLengtherror}">
                    <div class="invalid-feedback">${results.eventLengtherror}</div>
                </c:if>
                <select class="<c:if test="${not empty results.eventDescisionerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1"  id="inputeventUnit" name="inputeventUnit">
                    <option value="Minutes" <c:if test="${event.length < 1}">selected</c:if>>Minutes</option>
                    <option value="Hours" <c:if test="${event.length>=1 && event.length<=24}">selected</c:if>>Hours</option>
                    <option value="Days" <c:if test="${event.length >24}">selected</c:if>>Days</option>
                </select>
            </div>
        </div>

        <!-- Descision -->
        <div class ="row" id = "row6">
            <label for="inputeventDecision" class="form-label">Decision</label>
            <div class="input-group input-group-lg">
                <select class="<c:if test="${not empty results.eventDescisionerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1"  id="inputeventDecision" name="inputeventDecision">
                    <option value="Going" <c:if test="${event.decision eq 'Yes'}">selected</c:if> >Yes</option>
                    <option value="Skipping" <c:if test="${event.decision eq 'Skipping'}">selected</c:if>>No</option>
                    <option value="Maybe" <c:if test="${event.decision eq 'Maybe'}">selected</c:if>>Maybe</option>
                </select>
            </div>
        </div>
        <!-- Paid -->
        <div class ="row" id = "row7">
            <label for="inputeventPaid" class="form-label">Paid</label>
            <div class="input-group input-group-lg">
                <select  class="<c:if test="${not empty results.eventPaiderror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1"  id="inputeventPaid" name="inputeventPaid" >
                    <option value="Yes" <c:if test="${event.paid eq 'Yes'}">selected</c:if>>Yes</option>
                    <option value="No" <c:if test="${event.paid eq 'No'}">selected</c:if> >No</option>
                    <option value="N/A" <c:if test="${event.paid eq 'N/A'}">selected</c:if>>N/A</option>
                </select>
            </div>
        </div>
        <div class="align-items-center mt-0">
            <div class="d-grid"><button class="btn btn-orange mb-0" type="submit">Edit Event </button></div>
            <c:if test="${not empty results.dbStatus}"
            ><p>${results.dbStatus}</p>
            </c:if>
        </div>
    </form>
</div>
<c:if test="${empty viewmode}">
<%@include file="/WEB-INF/Schedule_App/schedule_bottom.jsp"%>
</c:if>
