<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/header.jsp" %>
<%@ include file="/WEB-INF/views/includes.jsp" %>
<div class="container-fluid col-lg-5     col-lg-offset-3">
    <form:form method="POST" class="form" modelAttribute="authorSearcher">
        <form:select path="authorId">
        <form:option value="">Автор:</form:option>
          <c:forEach items="${authors}" var="author">
           <form:option value="${author.id}">${author.firstName} ${author.lastName}</form:option>
          </c:forEach>
        </form:select>
        <form:errors path="authorId"/>
        <br/>
        Удалить автора? (альтернатива - добавить):
        <form:checkbox path="delete"/>
        <br/>
        <input class="pull-right btn btn-success" type="submit" value="Готово"/>
    </form:form>
</div>
<%@ include file="/WEB-INF/views/footer.jsp" %>