<%@include file="/WEB-INF/Schedule_App/schedule_top.jsp"%>



<div class="jumbotron jumbotron-fluid">
  <div class="container">
    <h2 class="display-4 text-center">Welcome to Biscuit's Scheduling</h2>
    <p class="lead text-center">Let's organize your life.</p>
    <div class="row">
      <div class="col col-md-4">
        <c:if test="${not empty results.AddedCount}">
          <div>${results.AddedCount}</div>
        </c:if>
      </div>
      <div class="col col-md-4">

      </div>
      <div class="col col-md-4">

      </div>
    </div>
  </div>
</div>


<%@include file="/WEB-INF/Schedule_App/schedule_bottom.jsp"%>
