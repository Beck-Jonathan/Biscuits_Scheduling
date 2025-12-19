<%--************
Create the JSP  For Viewing All of The  Palette table
 Created By Jonathan Beck 12/17/2025
**********--%>
<%@include file="/WEB-INF/Schedule_App/schedule_top.jsp"%>
<div class = "container">
    <div class="row">
        <div class="col-12">
            <h1>All schedule_app Palettes</h1>

            Add Palette   <a href="addPalette">Add</a>
            <c:if test="${Palettes.size() > 0}">


                <div class="table-responsive"><table class="table table-bordered">
                    <thead>
                    <tr>
                        <th scope="col"> Details </th>

                        <th scope="col">LineNo</th>
                        <th scope="col"></th>
                        <th scope="col"></th>
                        <th scope="col"></th>
                        <th scope="col"></th>
                        <th scope="col"></th>
                        <th scope="col"></th>
                        <th scope="col"></th>
                        <th scope="col"></th>
                        <th scope="col"></th>
                        <th scope="col"></th>
                        <th scope="col"></th>
                        <th scope="col"></th>
                        <th scope="col">Edit</th>
                        <th scope="col">Delete</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${Palettes}" var="palette">
                        <tr id="${palette.palette_ID}row">
                            <td><a href = "editPalette?paletteid=${palette.palette_ID}&mode=view"> Details </a></td>

                            <td>${fn:escapeXml(palette.lineNo)}</td>
                            <td><div class="color-tile sm " style="background-color:${fn:escapeXml(palette.color1.code)} "> </div> </td>
                            <td><div class="color-tile sm " style="background-color:${fn:escapeXml(palette.color2.code)} "> </div> </td>
                            <td><div class="color-tile sm " style="background-color:${fn:escapeXml(palette.color3.code)} "> </div> </td>
                            <td><div class="color-tile sm " style="background-color:${fn:escapeXml(palette.color4.code)} "> </div> </td>
                            <td><div class="color-tile sm " style="background-color:${fn:escapeXml(palette.color5.code)} "> </div> </td>
                            <td><div class="color-tile sm " style="background-color:${fn:escapeXml(palette.color6.code)} "> </div> </td>
                            <td><div class="color-tile sm " style="background-color:${fn:escapeXml(palette.color7.code)} "> </div> </td>
                            <td><div class="color-tile sm " style="background-color:${fn:escapeXml(palette.color8.code)} "> </div> </td>
                            <td><div class="color-tile sm " style="background-color:${fn:escapeXml(palette.color9.code)} "> </div> </td>
                            <td><div class="color-tile sm " style="background-color:${fn:escapeXml(palette.color10.code)} "> </div> </td>
                            <td><div class="color-tile sm " style="background-color:${fn:escapeXml(palette.color11.code)} "> </div> </td>
                            <td><div class="color-tile sm " style="background-color:${fn:escapeXml(palette.color12.code)} "> </div> </td>

                            <td><a href = "editPalette?paletteid=${palette.palette_ID}&mode=edit" > Edit </a></td>

                            <td>
                                <div>
                                    <button class="delButton" href="${palette.palette_ID}" >Delete</button> </div>
                                <div style="display: none;" id="palette.palette_IDStatus"></div>
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

