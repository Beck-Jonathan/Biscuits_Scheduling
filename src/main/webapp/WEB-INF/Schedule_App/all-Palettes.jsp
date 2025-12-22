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
            <br>
            <c:if test="${Palettes.size() > 0}">



                    <c:forEach items="${Palettes}" var="palette">

                        <span id="${palette.palette_ID}_card" class="card paletteCard">


    <p>${fn:escapeXml(palette.lineNo)}</p>
                            <div class="row">
                               <div id="${palette.palette_ID}_1" class="color-tile sm col-2" style="background-color:${fn:escapeXml(palette.color1.code)} "> </div>
                                <div id="${palette.palette_ID}_2" class="color-tile sm col-2" style="background-color:${fn:escapeXml(palette.color2.code)} "> </div>
                                <div id="${palette.palette_ID}_3" class="color-tile sm col-2" style="background-color:${fn:escapeXml(palette.color3.code)} "> </div>
                                <div id="${palette.palette_ID}_4" class="color-tile sm col-2" style="background-color:${fn:escapeXml(palette.color4.code)} "> </div>
                            </div>
                            <div class="row">
                               <div id="${palette.palette_ID}_5" class="color-tile sm col-2" style="background-color:${fn:escapeXml(palette.color5.code)} "> </div>
                                <div id="${palette.palette_ID}_6" class="color-tile sm col-2" style="background-color:${fn:escapeXml(palette.color6.code)} "> </div>
                                <div id="${palette.palette_ID}_7" class="color-tile sm col-2" style="background-color:${fn:escapeXml(palette.color7.code)} "> </div>
                                <div id="${palette.palette_ID}_8" class="color-tile sm col-2" style="background-color:${fn:escapeXml(palette.color8.code)} "> </div>

                            </div>
                            <div class="row">
                               <div id="${palette.palette_ID}_9" class="color-tile sm col-2" style="background-color:${fn:escapeXml(palette.color9.code)} "> </div>
                                <div id="${palette.palette_ID}_10" class="color-tile sm col-2" style="background-color:${fn:escapeXml(palette.color10.code)} "> </div>
                                <div id="${palette.palette_ID}_11" class="color-tile sm col-2" style="background-color:${fn:escapeXml(palette.color11.code)} "> </div>
                                <div id="${palette.palette_ID}_12" class="color-tile sm col-2" style="background-color:${fn:escapeXml(palette.color12.code)} "> </div>

                            </div>
        <div class="row">
            <div class="col-2">
                    <a  class="delButton" href="${palette.palette_ID}">❌</a>
            </div>
            <div class="col-5"></div>
            <div class="col-2 ">
                    <a href = "editPalette?paletteid=${palette.palette_ID}&mode=edit"> ⚙️ </a>
            </div>
            <div class="col-2"><a href = "editPalette?paletteid=${palette.palette_ID}&mode=view"> 🔎 </a></div>
            <div class="col-1"></div>
        </div>
    </span>

                    </c:forEach>



            </c:if>
        </div>
    </div>
</div>


<div id="dialog" title="Confirmation Required">
    Are you sure about this?
</div>
<div id="colorPicker">
    <canvas id="slCanvas"></canvas>
    <input type="range" id="hue" min="0" max="360">
</div>
</main>
<%@include file="/WEB-INF/Schedule_App/schedule_bottom.jsp"%>

