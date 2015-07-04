<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<%@ include file="/WEB-INF/views/includes.jsp" %>
<html>  
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Книжный магазин</title>
	<link rel="stylesheet" href="<c:url value="/css/bootstrap.css"/>" type="text/css">
</head>
  
<body>
<div class="container-fluid">
	<div class="row">
		<div class="col-lg-10">
		    <a href="<c:url value="/home"/>" style="color:inherit;">
			<h1><p class="text-center" style="font-size:64px;">Лучший книжный магазин</p></h1>
			<p class="text-center">занимаемся печатной продукцией с 1564 года</p>
			</a>
		</div>
		<div class="col-lg-2">
			<a href="<c:url value="/book/new"/>"><p class="text-right" style="margin-top:20px;">Добавить книгу</p></a>
			<a href="<c:url value="/order/all"/>">
			     <p class="text-right">Все заказы</p>
			</a>
			<a href="<c:url value="/clients/all"/>">
                 <p class="text-right">Все клиенты</p>
            </a>
			<a href="<c:url value="/author/new"/>">
                 <p class="text-right">Добавить автора</p>
            </a>
            <a href="<c:url value="/order/new"/>">
                 <p class="text-right">Оформить заказ</p>
            </a>
		</div>
	</div>
	<div class="row" style="margin-bottom:30px; margin-top:30px">