<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<a href="<c:url value="/book/${book.id}"/>" style="color:inherit;">
                        <div class="container-fluid"  style="margin-top:20px;">
                        <hr/>
                        <h4><p class="text-center">${book.title}</p></h4>
                        <p class="text-center">
                            <c:forEach items="${book.authors}" var="author">
                                ${author.firstName} ${author.lastName};
                            </c:forEach>
                        </p>
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
                    </a>