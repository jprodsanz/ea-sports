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
    <title>User Registration</title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
</head>
<body>
<div class="container pt-4 mb-4">
    <div class="row">
        <div class="col-12 ">
            <div class="container">
                <h3 class="text-danger"> <c:out value="${error}"/></h3>
                <form:form action="/user/register" method="POST" modelAttribute="user">
                    <div class="container ">
                        <!-- First Name input -->
                        <div class="mb-3">
                            <form:label for="firstName" class="form-label" path="firstName">First Name:</form:label>
                            <form:input style="width:250px;" type="text" class="form-control" id="firstName" aria-describedby="firstName" path="firstName"/>
                            <form:errors path="firstName" class="text-danger"/>
                        </div>
                        <!-- Last Name input -->
                        <div class="mb-3">
                            <form:label for="lastName" class="form-label" path="lastName">Last Name:</form:label>
                            <form:input style="width:250px;" type="text" class="form-control" id="lastName" aria-describedby="lastName" path="lastName"/>
                            <form:errors path="lastName" class="text-danger"/>
                        </div>
                        <!-- Email  input -->
                        <div class="mb-3">
                            <form:label for="email" class="form-label" path="email">Email:</form:label>
                            <form:input style="width:250px;" type="email" class="form-control" id="email" aria-describedby="email" path="email"/>
                            <form:errors path="email" class="text-danger"/>
                        </div>
                        <!-- Field Position input -->
                        <div class="mb-3">
                            <form:label for="fieldPosition" class="form-label" path="fieldPosition">Field Position:</form:label>
                            <form:errors path="fieldPosition" class="text-danger"/>
                            <form:select style="width:250px;" type="text" class="form-control" id="fieldPosition" aria-describedby="fieldPosition" path="fieldPosition">
                                <option selected="true" disabled="disabled"> Select position</option>
                                <option value="Goalkeeper">Goalie</option>
                                <option value="Defender">Defender</option>
                                <option value="Midfielder">Midfielder</option>
                                <option value="Forward">Forward</option>
                            </form:select>

                        </div>
                        <!-- Age input -->
                        <div class="mb-3">
                            <form:label for="age" class="form-label" path="age">Age:</form:label>
                            <form:input style="width:250px;" type="number" class="form-control" id="age" aria-describedby="age" path="age"/>
                            <form:errors path="age" class="text-danger"/>
                        </div>

                        <!-- Password input -->
                        <div class="mb-3">
                            <form:label for="password" class="form-label" path="password">Password:</form:label>
                            <form:input style="width:250px;" type="password" class="form-control" id="password" aria-describedby="password" path="password"/>
                            <form:errors path="password" class="text-danger"/>
                        </div>
                        <!-- confirmPass input -->
                        <div class="mb-3">
                            <form:label for="confirmPass" class="form-label" path="confirmPass"> Confirm Password:</form:label>
                            <form:input style="width:250px;" type="password" class="form-control" id="confirmPass" aria-describedby="confirmPass" path="confirmPass"/>
                            <form:errors path="confirmPass" class="text-danger"/>
                        </div>
                        <!-- Submit button -->
                        <button type="submit" class="btn btn-outline-primary">Submit</button>
                    </div>
                </form:form>
                <div>
                    <small class="text-muted">
                        Already have an account? <a class="ml-2" href="/user/login">Login</a>
                    </small>
                </div>
            </div>
            </div>
        </div>
    </div>
</div>
</body>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</html>

