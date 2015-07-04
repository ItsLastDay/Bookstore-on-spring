<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/header.jsp" %>
<%@ include file="/WEB-INF/views/includes.jsp" %>
<div class="container-fluid col-lg-6     col-lg-offset-3">
    <form:form class="form" modelAttribute="author" method="POST">
        <label class="control-label" for="inputFirstName">Имя:</label>
        <form:input path="firstName" id="inputFirstName"/> <form:errors path="firstName" class="text-danger"/>
        <br/>
        
        <label class="control-label" for="inputLastName">Фамилия:</label>
        <form:input path="lastName" id="inputLastName"/> <form:errors path="lastName" class="text-danger"/>
        <br/>
               
        <label class="control-label" for="inputBiography">Биография:</label>
        <form:textarea path="biography" rows="8" cols="80" id="inputBiography"/>
        
        <input class="pull-right btn btn-success" type="submit" value="Добавить автора"/>
        
    </form:form>
</div>
<%@ include file="/WEB-INF/views/footer.jsp" %>