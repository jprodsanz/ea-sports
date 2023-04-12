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
            <h1 class="text-center">Edit Pick-Up Game</h1>
            <h2>Hello, <c:out value="${user.firstName} ${user.lastName}"></c:out></h2></p>

            <form:form action="/match/${updateMatch.id}/update" method="POST" modelAttribute="updateMatch">
                <input type="hidden" name="_method" value="PUT"/>

                <form:input type="hidden" path="submittedBy" value="${updateMatch.submittedBy.id}" class="form"/>
                <form:input type="hidden" path="id" value="${updateMatch.id}" class="form"/>

                    <!-- Location input -->
                    <div class="mb-3">
                        <form:label for="location" class="form-label" path="location">Location</form:label>
                        <form:errors path="location" class="text-danger"/>
                        <form:select style="width:250px;" type="text" class="form-control" id="location" aria-describedby="location" path="location">
                            <option selected="true" disabled="disabled"> Select location</option>
                            <option value="West Loop">West Loop</option>
                            <option value="South Loop">South Loop</option>
                            <option value="Logan Square">Logan Square</option>
                            <option value="Pilsen Yards">Pilsen Yards</option>
                        </form:select>
                    </div>
                    <!-- Date input -->
                    <div class="mb-3">
                        <form:label for="date" class="form-label" path="date">Date</form:label>
                        <form:input style="width:250px;" type="datetime-local" class="form-control" id="date" aria-describedby="date" path="date"/>
                        <form:errors path="date" class="text-danger"/>
                    </div>
                <!-- Description input -->
                    <div class="mb-3">
                        <form:label for="description" class="form-label" path="description">Description</form:label>
                        <form:textarea style="width:250px;" type="description" class="form-control" id="description" aria-describedby="description" path="description" rows="4" cols="50" />
                        <form:errors path="description" class="text-danger"/>
                    </div>
                <!-- Price input -->
                    <div class="mb-3">
                        <form:label for="price" class="form-label" path="price">Price</form:label>
                        <form:input style="width:250px;" type="number" class="form-control" id="price" aria-describedby="price" path="price"/>
                        <form:errors path="price" class="text-danger"/>
                    </div>
                <!-- Submit button -->
                <button type="submit"  value= "Update Match" class="btn btn-outline-success">Add</button>
            </form:form>
        </div>
    </div>
</div>
</body>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</html>

