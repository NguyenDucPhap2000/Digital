<%-- 
    Document   : Detail
    Created on : May 15, 2021, 5:31:55 PM
    Author     : nguye
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Detail Page</title>
        <link href="css/Detail.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="container">
            <jsp:include page="Header.jsp"/>
            <div class="main">
                <div class="left">
                    <div class="title">
                        ${one.title}
                    </div>
                    <div class="image">
                        <img src="${imagePath}/${one.image}" alt=""/>
                    </div>
                    <h2>${error}</h2>
                    <div class="description">
                        ${one.content}
                    </div>
                    <div class="information">
                        <div class="icon1"></div>
                        <div class="icon2"></div>
                        By ${one.author} | ${one.getDateConvert()}
                    </div>
                </div>
                <jsp:include page="Right.jsp"/>
            </div>
            <jsp:include page="Footer.jsp"/>
        </div>
    </body>
</html>