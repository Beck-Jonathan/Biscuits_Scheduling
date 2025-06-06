<%@include file="/WEB-INF/Schedule_App/schedule_top.jsp"%>

<div class="table-responsive col-12">

    <table class="table table-bordered">




        <c:if test="${empty User}">
            <!--<tr><td>Join Us</td><td><a href="joinus"> View </a> </td></tr> -->
        </c:if>


        <thead>
        <row  id = "row7">
            <form method="post" action="${appURL}/upload_events" id = "event" enctype="multipart/form-data" >
                <label for="upload_events" class="form-label">Logo</label>
                <div class="input-group input-group-lg">
                    <input type="file" size="50" accept=".txt" class="<c:if test="${not empty results.teamLogoerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Transactions" id="upload_events" name="upload_events" value="${fn:escapeXml(results.Logo)}">
                    <c:if test="${not empty results.teamLogoerror}">
                        <div class="invalid-feedback">${results.teamLogoerror}</div>
                    </c:if>
                </div>
                <div class="align-items-center mt-0">
                    <div class="d-grid"><button class="btn btn-orange mb-0" type="submit">Import Events</button></div>
                    <c:if test="${not empty results.dbStatus}"
                    ><p>${results.dbStatus}</p>
                    </c:if>
                </div>
            </form>
        </row>
        </thead>
        <tr>upload</tr>
        <tbody>

        </tbody>
    </table>

</div>

<div class="jumbotron jumbotron-fluid">
    <div class="container">
        <h2 class="display-4 text-center">Welcome to Biscuit's Scheduling</h2>
        <p class="lead text-center">Let's Organize</p>
        <div class="row">
            <div class="col col-md-4">

            </div>
            <div class="col col-md-4">

            </div>
            <div class="col col-md-4">

            </div>
        </div>
    </div>
</div>


<%@include file="/WEB-INF/Schedule_App/schedule_bottom.jsp"%>
