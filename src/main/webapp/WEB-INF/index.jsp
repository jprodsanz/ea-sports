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
            <p class="h1">Welcome to Atleti Sports </p>

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
        <div class="col-12">
            <h2 class="text-center">Available Pick-Up games</h2>
            <h3>Hello, <c:out value="${user.firstName} ${user.lastName}"></c:out></h3></p>
            <table class="table table-striped table-bordered text-center">
                <thead class="table-dark">
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">Location</th>
                    <th scope="col">Date / Time</th>
                    <th scope="col">Actions</th>

                </tr>
                </thead>
                <tbody>
                <c:forEach var="match" items="${allMatches}">
                    <tr>
                        <td><c:out value="${match.id}"/></td>
                        <td><c:out value="${match.location}"/></td>
                        <td>
                            <c:out value="${match.date}"/>
                        </td>
                        <td>
                            <a href="/match/${match.id}/view" class="btn btn-outline-dark">View</a>
                            <a href="#" class="btn btn-outline-success">RSVP</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
<div class="container">
    <div class="row">
        <div class="col-12">
            <h2 class="text-center">Active Players</h2>
            <ul class="list-group">
                <c:forEach var="user" items="${allUsers}">
                    <li class="list-group-item d-flex justify-content-between align-items-center">
                        <c:out value="${user.firstName}"/>
                        <c:out value="${user.lastName}"/>
                        <span class="badge bg-success rounded-pill">Active</span>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
</div>
</body>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</html>

