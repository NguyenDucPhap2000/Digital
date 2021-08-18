<%-- 
    Document   : HomePage
    Created on : May 10, 2021, 9:20:17 PM
    Author     : nguye
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Page</title>
        <link href="css/Search.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="container">
            <jsp:include page="Header.jsp"/>
            <div class="main">
                <div class="left">
                    <c:forEach items="${list}" var="o">
                        <div class="title">
                            <a href="DetailControl?id=${o.id}">
                                ${o.title}  
                            </a>
                        </div>
                        <div class="content">
                            <div class="imageSearch">
                                <img src="${imagePath}/${o.image}" alt=""/>
                            </div>
                            <div class="descriptionSearch">
                                ${o.description}
                            </div>
                        </div>
                    </c:forEach>
                    <div class="numberOfPage">
                        <c:choose>
                            <c:when test="${index>endpage||index<=0}">
                                <h1>Not Found</h1>
                            </c:when>
                            <c:otherwise>
                                <c:forEach begin="1" end="${endpage}" var="i">
                                    <a class="${index == i?"active":""}" href="SearchControl?index=${i}&txtSearch=${save}">
                                        ${i}</a>  
                                    </c:forEach>   
                                </c:otherwise>
                            </c:choose>
                    </div>
                </div>
                <jsp:include page="Right.jsp"/>
            </div>
            <jsp:include page="Footer.jsp"/>
        </div>
    </body>
</html>
