<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>

<head>
   <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">

   <title>USER APP :: HOME</title>

   <link href="${pageContext.request.contextPath}/static/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
</head>

<body>

   <div class="container border p-5">
      <div class="row p-1">
         <div class="col border rounded text-center">
            <h1 class="text-danger"><strong>Team RED</strong></h1>
            <p><strong>CRUD Demo Applcation</strong></p>
            <p>Running On Servlets</p>
            <strong><a href="${pageContext.request.contextPath}/users" class="btn btn-danger"><strong>START</strong></a>
         </div>
      </div>

      <div class="row p-1">
         <div class="col border rounded text-center">
            <c:set var="now" value="<%=new java.util.Date()%>" />
            <p><strong>The time is NOW:</strong>
               <fmt:formatDate value="${now}" type="both" timeStyle="SHORT" dateStyle="SHORT" />
            </p>
         </div>
      </div>
   </div>

</body>

</html>