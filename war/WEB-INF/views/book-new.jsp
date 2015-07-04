<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/header.jsp" %>
<%@ include file="/WEB-INF/views/includes.jsp" %>
<div class="container-fluid col-lg-8     col-lg-offset-3">
            <form:form method="POST" class="form" modelAttribute="book">
            <div class="container-fluid col-lg-6">
                    <label class="control-label" for="inputTitle">Название книги:</label>
                    <form:input path="title" id="inputTitle"/> <form:errors path="title" class="text-danger"/>
                    <br/>
                                        
                    <label class="control-label" for="inputDescription">Описание книги:</label>
                    <form:textarea path="description" rows="6" cols="50" id="inputDescription"/>
                    <br/>
                    <c:if test="${not empty error_message}">
                        <p class="text-danger">${error_message}</p>
                        <br/>
                    </c:if>
                    
                    <input class="pull-right btn btn-success" type="submit" value="Добавить книгу"/>
                    
            </div>
            <div class="container-fluid col-lg-6" style="background-color:#aaa;">
                <label class="control-label" for="inputPrice">Цена:</label>
                <form:input path="price" id="inputPrice"/><form:errors path="price" class="text-danger"/>
                <br/>
                
                <label class="control-label" for="inputPage">Количество страниц:</label>
                <form:input path="numPages" id="inputPage"/><form:errors path="numPages" class="text-danger"/>
                <br/>
                
                <form:select path="publisher.id">
                <form:option value="">Издательство:</form:option>
                    <c:forEach items="${publishers}" var="apublisher">
                        <form:option value="${apublisher.id}">${apublisher.name}</form:option>
                    </c:forEach>
                </form:select>
                <form:errors path="publisher" class="text-danger"/>
                <form:select path="bookface.type">
                <form:option value="">Тип обложки:</form:option>
                    <c:forEach items="${bookfaces}" var="abookface">
                        <form:option value="${abookface.type}">${abookface.type}</form:option>
                    </c:forEach>
                </form:select>
                <form:errors path="bookface.type" class="text-danger"/>
                <br/>
                <label class="control-label" for="inputYear">Год издания:</label>
                <form:input path="year" id="inputYear"/><form:errors path="year" class="text-danger"/>
                <br/>
                
                <label class="control-label" for="inputQuantity">Количество на складе:</label>
                <form:input path="quantity" id="inputQuantity"/><form:errors path="quantity" class="text-danger"/>
                <br/>
            </div>
            </form:form>
</div>
<%@ include file="/WEB-INF/views/footer.jsp" %>