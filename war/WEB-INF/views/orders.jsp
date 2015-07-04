<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/header.jsp" %>
<%@ include file="/WEB-INF/views/includes.jsp" %>
<div class="container-fluid col-lg-5 col-lg-offset-3">
    <h3><p class="text-center text-success">${message}</p></h3>
    <br/>
    <c:forEach items="${orders}" var="order">
        <%@ include file="/WEB-INF/views/orderlist.jsp" %>
    </c:forEach>
</div>
<%@ include file="/WEB-INF/views/footer.jsp" %>