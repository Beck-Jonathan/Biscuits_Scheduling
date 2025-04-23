
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="UTF-8">
    <title>${pageTitle}</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.css" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="css/schedule/site.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/nprogress@0.2.0/nprogress.css" rel="stylesheet"/>
    <link rel="stylesheet" href="${appURL}/css/loading.css">
    <link rel="stylesheet" href="${appURL}/css/calendar.css">
    <link href="css/schedule/jquery-ui.css" rel="stylesheet">




<body onload="">


<header id="rollerHeader" onload="formatHeader">
    <div class="row ">
        <div class="col-md-1 " id="topleftblack" ></div>
        <div class="col-md-1 " id="Headerr1c1" >


        </div>
        <div class="col-sm-2 " id="Headerr1c2" >
            <a href="schedule_home"> <button type="button" value="Home" id="Home" ></button> </a>
        </div>

        <div class="col-sm-2 " id="Headerr1c3" >
            <c:if test="${empty User_C}">
                <a href="schedulewithus">   <button type="button" value="SignUp" id="SignUp" ></button></a>
            </c:if>
            <c:if test="${not empty User_C}">

                <a href="schedule-dash">   <button type="button" value="User Dashboard" id="user-dash" ></button></a>
                ${User_C.email}

            </c:if>
        </div>


        <div class="col-sm-2 " id="Headerr1c5" >


        </div>
        <div class="col-sm-2 " id="Headerr1c6" >
            <%@include file="/WEB-INF/Schedule_App/UserSignIn.jsp"%>

        </div>

        <div class="col-sm-2 .d-none.d-xxl-block" id="toprightblack"></div>

    </div>
    <div class="row">

    </div>


</header>
<main>
    <c:if test="${not empty User_C}">
        <%@include file="/WEB-INF/Schedule_App/user_dash_buttons.jsp"%>
    </c:if>
    <div class ="row">

        <div class = "col-md-1 ps-0 " id="left" alt="left Hand Border" ></div>
        <div class = "col-md-10 pe-0 ps-0" id="mainForm">
            <input type="hidden" id="currentpage" name="currentpage" value=${requestScope['javax.servlet.forward.request_uri']}/>