<%--************
Create the JSP For Viuw/Edit from the Palette table
 Created By Jonathan Beck 12/17/2025
**********--%>
<%@include file="/WEB-INF/Schedule_App/schedule_top.jsp"%>
<div class = "container">
    <form method="post" action="${appURL}/editPalette" id = "editPalette" >
        <!-- Palette_ID -->
        <div class ="row" id = "row0">
            <h2>User_ID  :
                ${fn:escapeXml(palette.user_ID)}</h2>
        </div>

        <!-- LineNo -->
        <div class ="row" id = "row1">

            <div class="input-group input-group-lg">
                <input type="text" class="<c:if test="${not empty results.paletteLineNoerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="LineNo" <c:if test="${mode eq 'view'}"> disabled </c:if>  id="inputpaletteLineNo" name="inputpaletteLineNo" value="${fn:escapeXml(palette.lineNo)}">
                <c:if test="${not empty results.paletteLineNoerror}">
                    <div class="invalid-feedback">${results.paletteLineNoerror}</div>
                </c:if>
            </div>
        </div>

        <!-- Color1 -->
        <div class ="row" id = "row2">
            <div class="input-group input-group-lg">
                <input type="hidden" name="inputpaletteColor1" id="inputpaletteColor1" value="${palette.color1.code} ">
                <div class="color-tile" data-input="inputpaletteColor1" ></div>


                <!-- Color2 -->

                <input type="hidden" name="inputpaletteColor2" id="inputpaletteColor2" value="${palette.color2.code} ">
                <div class="color-tile" data-input="inputpaletteColor2"></div>

                <!-- Color3 -->

                <input type="hidden" name="inputpaletteColor3" id="inputpaletteColor3" value="${palette.color3.code} ">
                <div class="color-tile" data-input="inputpaletteColor3"  ></div>

                <!-- Color4 -->


                <input type="hidden" name="inputpaletteColor4" id="inputpaletteColor4" value="${palette.color4.code} ">
                <div class="color-tile" data-input="inputpaletteColor4" ></div>

            </div>
        </div>

        <!-- Color5 -->
        <div class ="row" id = "row3">
            <div class="input-group input-group-lg">
                <input type="hidden" name="inputpaletteColor5" id="inputpaletteColor5" value="${palette.color5.code} ">
                <div class="color-tile" data-input="inputpaletteColor5"  ></div>

                <!-- Color6 -->

                <input type="hidden" name="inputpaletteColor6" id="inputpaletteColor6" value="${palette.color6.code} ">
                <div class="color-tile" data-input="inputpaletteColor6" ></div>


                <!-- Color7 -->

                <input type="hidden" name="inputpaletteColor7" id="inputpaletteColor7" value="${palette.color7.code} ">
                <div class="color-tile" data-input="inputpaletteColor7" ></div>

                <!-- Color8 -->

                <input type="hidden" name="inputpaletteColor8" id="inputpaletteColor8" value="${palette.color8.code} ">
                <div class="color-tile" data-input="inputpaletteColor8"></div>
            </div>
        </div>

        <!-- Color9 -->
        <div class ="row" id = "row4">
            <div class="input-group input-group-lg">
                <input type="hidden" name="inputpaletteColor9" id="inputpaletteColor9" value="${palette.color9.code} ">
                <div class="color-tile" data-input="inputpaletteColor9" ></div>


                <!-- Color10 -->

                <input type="hidden" name="inputpaletteColor10" id="inputpaletteColor10" value="${palette.color10.code} ">
                <div class="color-tile" data-input="inputpaletteColor10"></div>


                <!-- Color11 -->

                <input type="hidden" name="inputpaletteColor11" id="inputpaletteColor11" value="${palette.color11.code} ">
                <div class="color-tile" data-input="inputpaletteColor11" ></div>

                <!-- Color12 -->

                <input type="hidden" name="inputpaletteColor12" id="inputpaletteColor12" value="${palette.color12.code} ">
                <div class="color-tile" data-input="inputpaletteColor12" ></div>
            </div>
        </div>
        <div id="palettePreview"></div>

        <button type="button" id="randomizeBtn" class="btn btn-secondary w-100">
            🎲 Randomize Palette
        </button>
        <div class="align-items-center mt-0">
            <div class="d-grid"><button id="submitButton" class="btn btn-orange mb-0" type="submit">Create Palette  </button></div>
            <c:if test="${not empty results.dbStatus}"
            ><p>${results.dbStatus}</p>
            </c:if>
        </div>
    </form>
</form>

    <div id="colorPicker">
        <canvas id="slCanvas"></canvas>
        <input type="range" id="hue" min="0" max="360">
    </div>
<%@include file="/WEB-INF/Schedule_App/schedule_bottom.jsp"%>

