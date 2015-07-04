<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/header.jsp" %>
<%@ include file="/WEB-INF/views/includes.jsp" %>
<div class="container-fluid col-lg-6     col-lg-offset-3">
    <c:choose>
        <c:when test="${not empty author}">
                <h2>${author.firstName} ${author.lastName}</h2>
                <br/>
                <c:choose>
                    <c:when test="${not empty author.biography}">
                        ${author.biography}
                    </c:when>
                    <c:otherwise>
                        Биография не указана.
                    </c:otherwise>
                </c:choose>
                <a href="<c:url value="/author/${author.id}/books"/>">
                    <p class="text-right">
                        <button type="button" class="btn btn-default">Книги автора</button>
                    </p>
                </a>
        </c:when>
        <c:when test="${not empty allBooks}">
                <h4>Все книги автора:</h4>
                <c:forEach items="${allBooks}" var="book">
                    <%@ include file="/WEB-INF/views/booklist.jsp" %>
                </c:forEach>
        </c:when>
        <c:otherwise>
            <p class="text-center text-danger">Запрошенный автор отсутствует</p>
        </c:otherwise>
    </c:choose>
</div>
<%@ include file="/WEB-INF/views/footer.jsp" %>