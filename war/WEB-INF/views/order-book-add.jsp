<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/header.jsp" %>
<%@ include file="/WEB-INF/views/includes.jsp" %>
<div class="container-fluid col-lg-5     col-lg-offset-3">
    <form:form method="POST" class="form" modelAttribute="bookSearcher">
        <form:select path="bookId">
        <form:option value="">Книга:</form:option>
          <c:forEach items="${books}" var="book">
           <form:option value="${book.id}">${book.title}</form:option>
          </c:forEach>
        </form:select>
        <form:errors path="bookId"/>
        <br/>
        
        <label class="control-label" for="inputQuantity">Количество:</label>
        <form:input path="quantity" id="inputQuantity"/><form:errors path="quantity" class="text-danger"/>
        <br/>
        
        Удалить книгу? (альтернатива - добавить):
        <form:checkbox path="delete"/>
        <br/>
        <input class="pull-right btn btn-success" type="submit" value="Готово"/>
    </form:form>
</div>
<%@ include file="/WEB-INF/views/footer.jsp" %>