<%--************
Create the JSP  For Viewing All of The  Culvers table
 Created By Jonathan Beck 11/21/2025
**********--%>
<%@include file="/WEB-INF/Schedule_App/schedule_top.jsp"%>
<div class = "container">
    <div class="row">
        <div class="col-12">
            <h1>All schedule_app Culverss</h1>
            <p>There ${Culverss.size() eq 1 ? "is" : "are"}&nbsp;${Culverss.size()} Culvers${Culverss.size() ne 1 ? "s" : ""}</p>
            Add Culvers   <a href="addCulvers">Add</a>
            <c:if test="${Culverss.size() > 0}">
                <div class="search-container">
                    <form action="all-Culvers">
                        <input type="text" placeholder="Search.." id="searchBox" name="search">
                        <label for="inputculversUser_ID" class="form-label">User_ID</label>
                        <div class="input-group input-group-lg">
                            <select  class="<c:if test="${not empty results.culversUser_IDerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="User_ID" id="inputculversUser_ID" name="inputculversUser_ID" value="${fn:escapeXml(results.User_ID)}">
                                <c:forEach items="${Users}" var="User">
                                    <option value="${User.user_ID}">${User.name}   </option>
                                </c:forEach>
                            </select>
                            <c:if test="${not empty results.culversUser_IDerror}">
                                <div class="invalid-feedback">${results.culversUser_IDerror}</div>
                            </c:if>
                        </div>
                        <button type="submit"><i class="fa fa-search">search</i></button>
                    </form>
                </div>
                Export Culvers   <a href="exportCulvers?mode=export">Add</a>
                Write To SQL File Culvers   <a href="exportCulvers?mode=SQL">Add</a>
                <div class="table-responsive"><table class="table table-bordered">
                    <thead>
                    <tr>
                        <th scope="col"> Details </th>

                        <th scope="col">Name</th>
                        <th scope="col">WebAddress</th>
                        <th scope="col">Is_Active</th>
                        <th scope="col">Edit</th>

                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${Culverss}" var="culvers">
                        <tr id="${culvers.culvers_ID}row">
                            <td><a href = "editCulvers?culversid=${culvers.culvers_ID}&mode=view"> Details </a></td>

                            <td>${fn:escapeXml(culvers.name)}</td>
                            <td>${fn:escapeXml(culvers.webAddress)}</td>
                            <td><input type="checkbox"
                                       class="isActiveCheckbox"
                                       id="${culvers.culvers_ID}_isActive"
                                ${culvers.is_Active ? "checked" : ""}></td>
                            <td><a href = "editCulvers?culversid=${culvers.culvers_ID}&mode=edit" > Edit </a></td>


                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                </div>
            </c:if>
        </div>
    </div>
</div>
<%--For displaying Previous link except for the 1st page --%>
<c:if test="${currentPage != 1}">
    <form action="all-Culverss" method="get">
        <input type="hidden" name="user_id" value="${user_id}">
        <input type="hidden" name="page" value="${currentPage-1}">
        <br/><br/>
        <input type="submit" value="Previous Page" />
    </form>
</c:if>
<%--For displaying Page numbers.
The when condition does not display a link for the current page--%><form action="all-Culverss" method="get" ><input type="hidden" name="user_id" value="${user_id}">
    Select a page :
    <select name="page" onchange="this.form.submit()">
        <c:forEach var="i" begin="1" end="${noOfPages}">
            <option value=${i}  ${currentPage == i ? ' selected' : ''} >${i}</option>
        </c:forEach>
    </select>
    <br/><br/>
    <input type="submit" value="Submit" />
</form>
<%--For displaying Next link --%>
<c:if test="${currentPage lt noOfPages}">
    <form action="all-Transactions" method="get">
        <input type="hidden" name="user_id" value="${user_id}">
        <input type="hidden" name="page" value="${currentPage+1}">
        <br/><br/>
        <input type="submit" value="Next Page" />
    </form>
</c:if>

</main>
<%@include file="/WEB-INF/Schedule_App/schedule_bottom.jsp"%>

