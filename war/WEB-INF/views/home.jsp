<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/header.jsp" %>
<%@ include file="/WEB-INF/views/includes.jsp" %>
<div class="container-fluid col-lg-5 col-lg-offset-3">
    <h3><p class="text-center text-success">${message}</p></h3>
    <br/>
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
    
	<form:form method="POST" class="form" modelAttribute="search">
    	<label class="control-label" for="inputTitle">Название книги:</label>
    	<form:input path="title" id="inputTitle"/>
    	<br/>
    	Фильтры: 
    	<br/>
    	<label class="control-label" for="inputYear">Год издания:</label>
    	<form:input path="year" id="inputYear"/> <form:errors class="text-danger" path="year"/>
    	<br/>
    	
      	<form:select path="genreId">
        <form:option value="">Жанр:</form:option>
       	  <c:forEach items="${genres}" var="genre">
       	   <form:option value="${genre.id}">${genre.name}</form:option>
      	  </c:forEach>
        </form:select>
        
        <form:select path="publisherId">
        <form:option value="">Издательство:</form:option>
       	  <c:forEach items="${publishers}" var="publisher">
       	   <form:option value="${publisher.id}">${publisher.name}</form:option>
      	  </c:forEach>
        </form:select>
        
        <form:select path="bookface">
        <form:option value="">Тип обложки:</form:option>
       	  <c:forEach items="${bookfaces}" var="bookface">
       	   <form:option value="${bookface.type}">${bookface.type}</form:option>
      	  </c:forEach>
        </form:select>
    	<br/>
    	
    	<label class="control-label" for="inputOrder">Сортировать по:</label>
    	<form:select path="order" id="inputOrder">
    		<form:option value="new">году издания</form:option>
    		<form:option value="price">цене</form:option>
    	</form:select>
    	<br/>
    	
    	
    	<label class="control-label" for="inputPages">Книг на странице</label>
    	<form:select path="numberOfBooks" id="inputPages">
    	    <form:option value="2">2</form:option>
    		<form:option value="10">10</form:option>
    		<form:option value="15">15</form:option>
    		<form:option value="30">30</form:option>
    	</form:select>
    	<br/>
    	
    	<c:choose>
    	   <c:when test="${search.numPages > 0}">
    	   
    	       <label class="control-label" for="Pages">Страница:</label>
    	       <form:select path="page" id="inputPage">
    	       <c:forEach var="i" begin="0" end="${search.numPages - 1}">
    		      <form:option value="${i}">${i + 1}</form:option>
    	       </c:forEach>
    	       </form:select>
    	       <br/>
    	   </c:when>
    	</c:choose>
    	Есть в наличии:
    	<form:checkbox path="presented"/>
    	<br/>
    	<input class="pull-right" type="submit" value="Поиск"/>
	</form:form>
</div>
<div class="container-fluid col-lg-5 col-lg-offset-3">
  <c:choose>
    <c:when test="${not empty books}">
    <c:forEach items="${books}" var="book">
        <%@ include file="/WEB-INF/views/booklist.jsp" %>
    </c:forEach>
    </c:when>
    <c:otherwise>
    <p class="text-center text-danger" style="margin-top:20px;">Нет книг, подходящих под заданные ограничения!</p>
    </c:otherwise>
  </c:choose>
</div>
<%@ include file="/WEB-INF/views/footer.jsp" %>