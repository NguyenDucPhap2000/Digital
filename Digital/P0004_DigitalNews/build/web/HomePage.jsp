<%-- 
    Document   : HomePage
    Created on : May 10, 2021, 9:20:17 PM
    Author     : nguye
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <%--<jsp:useBean id="t" class="DAO.DigitalDAO" scope="request"></jsp:useBean>--%>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
        <link href="css/Home.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="container">
            <jsp:include page="Header.jsp"/>
            <div class="main">
                <div class="left">
                    <div class="title">
                        ${top1.title}
                    </div>
                    <div class="image">
                        <img src="${imagePath}/${top1.image}" alt=""/>
                    </div>
                    <div class="description">
                        ${top1.content}
                    </div>
                    <div class="information">
                        <div class="icon1"></div>
                        <div class="icon2"></div>
                        ${top1.author} | ${top1.getDateConvert()}
                    </div>
                </div>
                <jsp:include page="Right.jsp"/>
            </div>
            <jsp:include page="Footer.jsp"/>
        </div>
    </body>
</html>
