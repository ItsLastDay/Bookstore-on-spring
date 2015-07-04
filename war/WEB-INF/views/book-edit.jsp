<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/header.jsp" %>
<%@ include file="/WEB-INF/views/includes.jsp" %>
<div class="container-fluid col-lg-8     col-lg-offset-3">
    <c:choose>
        <c:when test="${not empty book}">
            <form:form method="POST" class="form" modelAttribute="book">
            <div class="container-fluid col-lg-6">
                    <label class="control-label" for="inputTitle">Название книги:</label>
                    <form:input path="title" id="inputTitle"/> <form:errors path="title" class="text-danger"/>
                    <br/>
                    
                    <c:forEach items="${bookAuthors}" var="author">
                        <a href="<c:url value="/author/${author.id}"/>">${author.firstName} ${author.lastName}</a> 
                        <br/>
                    </c:forEach>
                    <br/>
                    <a href="<c:url value="/book/${bookId}/addAuthor"/>">
                        <button type="button">Добвить\удалить автора</button>
                    </a>
                    <br/>
                    
                    <label class="control-label pull-left" for="inputDescription">Описание книги:</label>
                    <form:textarea path="description" rows="6" cols="50" id="inputDescription"/>
                    <br/>
                    <c:if test="${not empty error_message}">
                        <p class="text-danger">${error_message}</p>
                        <br/>
                    </c:if>
                    
                    <input class="pull-right btn btn-success" type="submit" value="Сохранить изменения"/>
                    <a href="<c:url value="/book/${bookId}/delete"/>">
                        <p class="text-right">
                            <button type="button" class="pull-left btn btn-danger">Удалить книгу</button>
                        </p>
                    </a>
                    
            </div>
            <div class="container-fluid col-lg-6" style="background-color:#aaa;">
                <label class="control-label" for="inputPrice">Цена:</label>
                <form:input path="price" id="inputPrice"/><form:errors path="price" class="text-danger"/>
                <br/>
                <p>Жанр:
                    <c:forEach items="${bookGenres}" var="genre">
                        ${genre.name};
                    </c:forEach> 
                    <a href="<c:url value="/book/${bookId}/addGenre"/>">
                        <button type="button">Добвить\удалить жанр</button>
                    </a>
                </p>
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
        </c:when>
        <c:otherwise>
            <p class="text-center text-danger">Запрошенная книга не существует</p>
        </c:otherwise>
    </c:choose>
</div>
<%@ include file="/WEB-INF/views/footer.jsp" %>