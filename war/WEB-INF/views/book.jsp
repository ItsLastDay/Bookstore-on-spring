<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/header.jsp" %>
<%@ include file="/WEB-INF/views/includes.jsp" %>
<div class="container-fluid col-lg-8     col-lg-offset-3">
    <c:choose>
        <c:when test="${not empty book}">
            <div class="container-fluid col-lg-6">
                <h2>${book.title}</h2>
                <c:forEach items="${book.authors}" var="author">
                    <a href="<c:url value="/author/${author.id}"/>">${author.firstName} ${author.lastName}</a> 
                    <br/>
                </c:forEach>
                <br/>
                <c:choose>
                    <c:when test="${not empty book.description}">
                        ${book.description}
                    </c:when>
                    <c:otherwise>
                        У книги ещё нет описания.
                    </c:otherwise>
                </c:choose>
                <a href="<c:url value="/book/${book.id}/repub"/>">
                    <p class="text-right">
                        <button type="button" class="btn btn-default">Все издания этой книги</button>
                    </p>
                </a>
                <a href="<c:url value="/book/${book.id}/edit"/>">
                    <p class="text-right">
                        <button type="button" class="btn btn-default">Редактировать</button>
                    </p>
                </a>
            </div>
            <div class="container-fluid col-lg-6" style="background-color:#aaa;">
                <p>Цена: ${book.price} р.</p>
                <p>Жанр:
                    <c:forEach items="${book.genres}" var="genre">
                        ${genre.name};
                    </c:forEach> 
                </p>
                <p>Количество страниц: ${book.numPages}</p>
                <p>Издательство: ${book.publisher.name}</p>
                <p>Тип обложки: ${book.bookface.type}</p>
                <p>Год издания: ${book.year}
                <c:if test="${book.quantity > 0}">
                    <p class="text-success">Есть в наличии</p>
                </c:if>
            </div>
            <c:if test="${not empty alsoBooks}">
                <div class="container-fluid">
                <br/>
                <br/>
                <h4>Вместе с этой книгой берут:</h4>
                <c:forEach items="${alsoBooks}" var="book">
                    <%@ include file="/WEB-INF/views/booklist.jsp" %>
                </c:forEach>
                </div>
            </c:if>
        </c:when>
        <c:when test="${not empty repubBooks}">
                <h4>Все издания книги:</h4>
                <c:forEach items="${repubBooks}" var="book">
                    <%@ include file="/WEB-INF/views/booklist.jsp" %>
                </c:forEach>
        </c:when>
        <c:otherwise>
            <p class="text-center text-danger">Запрошенная книга не существует</p>
        </c:otherwise>
    </c:choose>
</div>
<%@ include file="/WEB-INF/views/footer.jsp" %>