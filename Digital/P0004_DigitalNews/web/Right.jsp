<%-- 
    Document   : Right
    Created on : May 15, 2021, 4:57:53 PM
    Author     : nguye
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/Right.css" rel="stylesheet" type="text/css"/>
        <title>JSP Page</title>
    </head>
    <body>
        <div class="right">
            <div class="title">
                Digital News
            </div>
            <div class="shortdes">
                ${top1.description}
            </div>
            <div class="title">
                Search
            </div>
            <div class="search">
                <form action="SearchControl?index=1" method="POST">
                    <input class="txtInput" type="text" name="txtSearch">
                    <input class="txtSubmit" type="submit" name="btnGo" value="Go">
                </form>
            </div>
            <div class="title">
                Last Articles
            </div>
            <c:forEach items="${top5}" var="x">
                <div class="list">
                    <a href="DetailControl?id=${x.id}">${x.title}</a>
                </div>
            </c:forEach>
        </div>
    </body>
</html>
