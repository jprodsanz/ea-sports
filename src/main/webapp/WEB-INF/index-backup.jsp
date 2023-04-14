<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. -->
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!-- Formatting (dates) -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Atleti Sports </title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
</head>
<body>
<div class="container pt-4 mb-4">
    <div class="row">
        <div class="col-12 d-flex align-items-baseline justify-content-evenly">
            <p class="h1">Welcome to EA Sports </p>

            <nav class="d-flex justify-content-around pb-2">

                <!-- Upcoming Game -->
                <a href="/match/create" class="btn btn-outline-primary">Add Match</a>
                <!-- Dashboard -->
                <a href="/" class="btn btn-outline-primary">Dashboard</a>
                <!-- Logout btn -->
                <a href="/user/logout" class="btn btn-outline-danger">Logout</a>
            </nav>
        </div>
    </div>
</div>

<div class="container">
    <div class="row">
        <h3>Hello, <c:out value="${user.firstName} ${user.lastName}"></c:out></h3>
        <div class="col-12">
            <h2 class="text-center">Pick-Up Games</h2>
            <ul class="list-group">
                <c:forEach var="match" items="${allMatches}">
                    <li class="list-group-item d-flex justify-content-evenly">
                        <c:out value="${match.name}"/>
                        <span class="badge bg-primary rounded-pill">
                            <c:out value="${match.location}"/>
                        </span>
                    </li>
                    <ul class="list-group">
                        <li>Players Going</li>
                        <c:forEach var="player" items="${match.players}">
                            <li>
                                <c:out value="${player.firstName}"/>
                                <c:out value="${player.lastName}"/>
                            </li>
                        </c:forEach>
                    </ul>
                </c:forEach>
            </ul>
        </div>
    </div>
</div>
</body>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</html>

