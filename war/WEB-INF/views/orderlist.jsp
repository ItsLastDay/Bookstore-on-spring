<%@ page contentType="text/html;charset=UTF-8" language="java" %>
                        <div class="container-fluid"  style="margin-top:20px;">
                        <hr/>
                        <p><strong>Клиент</strong>: ${order.client.login}</p>
                        <p><strong>Заказ сделан</strong>: ${order.whenMade}</p>
                        <p><strong>Описание</strong>: ${order.description}</p>
                        <p><strong>Статус</strong>: ${order.status.name}</p>
                        <p><strong>Книги</strong>:</p>
                        <c:set var="totalCost" value="${0}"/>
                        <c:forEach items="${order.books}" var="book">
                            <p>${book.key.title} - ${book.value} шт.</p>
                            <c:set var="totalCost" value="${totalCost + book.key.price * book.value}"/>
                        </c:forEach>
                        <p><strong>Общая стоимость</strong>: ${totalCost} р.</p>
                        </div>