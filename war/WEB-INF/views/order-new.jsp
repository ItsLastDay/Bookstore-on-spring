<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/header.jsp" %>
<%@ include file="/WEB-INF/views/includes.jsp" %>
<div class="container-fluid col-lg-6     col-lg-offset-3">
    <form:form class="form" modelAttribute="order" method="POST">
        <label class="control-label" for="inputId">Id:</label>
        <form:input path="id" disabled="true" id="inputId"/>

        <br/>
        <strong>Клиент</strong>:
        <form:select path="client.id">
                <form:option value="">Клиент:</form:option>
                    <c:forEach items="${clients}" var="client">
                        <form:option value="${client.id}">${client.login}</form:option>
                    </c:forEach>
        </form:select>
        <br/>
        
        <label class="control-label" for="inputWhenMade">Дата:</label>
        <form:input path="whenMade" id="inputWhenMade"/> <form:errors path="whenMade" class="text-danger"/>
        <br/>
               
               
        <label class="control-label" for="inputDescription">Комментарии к заказу:</label>
        <form:textarea path="description" rows="8" cols="80" id="inputDescription"/>
        
        <c:set var="totalCost" value="${0}"/>
        <c:forEach items="${order.books}" var="book">
            <p>${book.key.title} - ${book.value} шт.</p>
            <c:set var="totalCost" value="${totalCost + book.key.price * book.value}"/>
        </c:forEach>
        <p><strong>Общая стоимость</strong>: ${totalCost} р.</p>
        <a href="<c:url value="/order/${orderId}/addBook"/>">
            <button type="button">Добвить\удалить книгу</button>
        </a>
        <input class="pull-right btn btn-success" type="submit" value="Оформить заказ"/>
        
    </form:form>
</div>
<%@ include file="/WEB-INF/views/footer.jsp" %>