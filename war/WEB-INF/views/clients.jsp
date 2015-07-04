<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/header.jsp" %>
<%@ include file="/WEB-INF/views/includes.jsp" %>
<div class="container-fluid col-lg-9 col-lg-offset-2">
        <table class="table table-hover">
            <thead>
               <th>Login</th>
               <th>Фамилия</th>
               <th>Имя</th>
               <th>Город</th>
               <th>Улица</th>
               <th>Дом</th>
               <th>Корпус</th>
               <th>Телефон</th>
               <th>Почтовый индекс</th>
           </thead>
           <tbody>
              <c:forEach items="${clients}" var="client">
                  <tr>
                      <td>${client.login}</td>
                      <td>${client.lastName}</td>
                      <td>${client.firstName}</td>
                      <td>${client.city.name}</td>
                      <td>${client.street}</td>
                      <td>${client.house}</td>
                      <td>${client.wing}</td>
                      <td>${client.phone}</td>
                      <td>${client.postalCode}</td>
                  </tr>
               </c:forEach>
           </tbody>
         </table>
</div>
<%@ include file="/WEB-INF/views/footer.jsp" %>