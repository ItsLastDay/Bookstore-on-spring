<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/header.jsp" %>
<%@ include file="/WEB-INF/views/includes.jsp" %>
<div class="container-fluid col-lg-5 col-lg-offset-3">
    Поиск по авторам:
    <ul class="nav navbar-nav">
        <li><a href="<c:url value="/authors/all"/>">Все авторы</a></li>
        <li><a href="<c:url value="/authors/А"/>">А</a></li>
        <li><a href="<c:url value="/authors/Б"/>">Б</a></li>
        <li><a href="<c:url value="/authors/В"/>">В</a></li>
        <li><a href="<c:url value="/authors/Г"/>">Г</a></li>
        <li><a href="<c:url value="/authors/Д"/>">Д</a></li>
        <li><a href="<c:url value="/authors/Е"/>">Е</a></li>
        <li><a href="<c:url value="/authors/Ж"/>">Ж</a></li>
        <li><a href="<c:url value="/authors/З"/>">З</a></li>
        <li><a href="<c:url value="/authors/И"/>">И</a></li>
        <li><a href="<c:url value="/authors/Й"/>">Й</a></li>
        <li><a href="<c:url value="/authors/К"/>">К</a></li>
        <li><a href="<c:url value="/authors/Л"/>">Л</a></li>
        <li><a href="<c:url value="/authors/М"/>">М</a></li>
        <li><a href="<c:url value="/authors/Н"/>">Н</a></li>
        <li><a href="<c:url value="/authors/О"/>">О</a></li>
        <li><a href="<c:url value="/authors/П"/>">П</a></li>
        <li><a href="<c:url value="/authors/Р"/>">Р</a></li>
        <li><a href="<c:url value="/authors/С"/>">С</a></li>
        <li><a href="<c:url value="/authors/Т"/>">Т</a></li>
        <li><a href="<c:url value="/authors/У"/>">У</a></li>
        <li><a href="<c:url value="/authors/Ф"/>">Ф</a></li>
        <li><a href="<c:url value="/authors/Х"/>">Х</a></li>
        <li><a href="<c:url value="/authors/Ц"/>">Ц</a></li>
        <li><a href="<c:url value="/authors/Ч"/>">Ч</a></li>
        <li><a href="<c:url value="/authors/Ш"/>">Ш</a></li>
        <li><a href="<c:url value="/authors/Щ"/>">Щ</a></li>
        <li><a href="<c:url value="/authors/Ы"/>">Ы</a></li>
        <li><a href="<c:url value="/authors/Э"/>">Э</a></li>
        <li><a href="<c:url value="/authors/Ю"/>">Ю</a></li>
        <li><a href="<c:url value="/authors/Я"/>">Я</a></li>
    </ul>
    <br/>
    <br/>
    <c:if test="${not empty letter}">
        <h4 class="text-center">Список авторов с фамилией на букву ${letter}:</h4>
    </c:if>
    <c:choose>
    <c:when test="${not empty authors}">
        <table class="table table-hover">
            <thead>
               <th>Фамилия</th>
               <th>Имя</th>
           </thead>
           <tbody>
              <c:forEach items="${authors}" var="author">
                  <tr>
                      <td><a href="<c:url value="/author/${author.id}"/>">${author.lastName}</a></td>
                      <td><a href="<c:url value="/author/${author.id}"/>">${author.firstName}</a></td>
                  </tr>
               </c:forEach>
           </tbody>
         </table>
     </c:when>
     <c:otherwise>
        <p class="text-center">Извините, на данный момент в магазине не представлены такие авторы</p>
     </c:otherwise>
    </c:choose>
</div>
<%@ include file="/WEB-INF/views/footer.jsp" %>