<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/header.jsp" %>
<%@ include file="/WEB-INF/views/includes.jsp" %>
<div class="container-fluid col-lg-5     col-lg-offset-3">
    <form:form method="POST" class="form" modelAttribute="genreSearcher">
        <form:select path="genreId">
        <form:option value="">Жанр:</form:option>
          <c:forEach items="${genres}" var="genre">
           <form:option value="${genre.id}">${genre.name}</form:option>
          </c:forEach>
        </form:select>
        <form:errors path="genreId"/>
        <br/>
        Удалить жанр? (альтернатива - добавить):
        <form:checkbox path="delete"/>
        <br/>
        <input class="pull-right btn btn-success" type="submit" value="Готово"/>
    </form:form>
</div>
<%@ include file="/WEB-INF/views/footer.jsp" %>