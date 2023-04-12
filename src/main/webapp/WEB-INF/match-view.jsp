<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. -->
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!-- Formatting (dates) -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %>
<html>
<head>
  <title>View Match</title>
  <%--    Bootstrap--%>
  <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
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
      <h1 class="text-center"> ${oneMatch.location} </h1>
      <h3 class="text-danger"> <c:out value="${error}"/></h3>

      <%--  if match = true --%>
      <c:if test="${user.id == oneMatch.submittedBy.id}">
        <td>
          <h3> Hey, ${oneMatch.submittedBy.firstName} ${oneMatch.submittedBy.lastName} </h3>
        </td>
      </c:if>

      <!-- NOT a match -->
      <c:if test="${user.id != oneMatch.submittedBy.id}">
        <td>
          <h3> ${oneMatch.submittedBy.firstName} ${oneMatch.submittedBy.lastName} is playing at ${oneMatch.location}</h3>
          <h4> Here's ${oneMatch.submittedBy.lastName} info: </h4>
        </td>
      </c:if>
      <hr/>
      <div class="container">
        <p>
          Host:
          ${oneMatch.submittedBy.firstName}
          ${oneMatch.submittedBy.lastName}
        </p>
        <p>Email: ${oneMatch.submittedBy.email}</p>
        <p>Date: ${oneMatch.date}</p>
        <p>Description: ${oneMatch.description}</p>
        <p>Price: ${oneMatch.price}</p>

      </div>

      <hr/>
      <!-- user and submittedBy match -->
      <c:if test="${user.id == oneMatch.submittedBy.id}">
        <div class="flexControl justify-content-center align-content-end">
          <p>
            <a href="/match/${oneMatch.id}/update" class="btn btn-outline-primary">Edit</a>
            <a href="/match/${oneMatch.id}/delete" class="btn btn-outline-danger">Delete</a>
          </p>

        </div>
      </c:if>
    </div>
    </div>
  </div>
</div>
</body>
</html>