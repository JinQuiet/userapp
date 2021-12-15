<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>

<head>
   <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">

   <title>USER APP :: USERS</title>

   <link href="${pageContext.request.contextPath}/static/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
</head>

<body>
   <div class="container border p-5">
      <div class="row  p-1">
         <div class="col border rounded text-center">

            <table class="table table-striped table-sm caption-top">
               <caption><strong>List of users</strong></caption>
               <thead class="table-dark">
                  <tr>
                     <th scope="col">#</th>
                     <th scope="col">ID</th>
                     <th scope="col">NAME</th>
                     <th scope="col">AGE</th>
                     <th scope="col">E-MAIL</th>
                     <th scope="col">EDIT</th>
                     <th scope="col">DELETE</th>
                  </tr>
               </thead>
               <tbody>
                  <c:forEach items="${users}" var="user" varStatus="loop">
                     <tr>
                        <th scope="row">
                           ${loop.index+1}
                        </th>
                        <td>
                           <c:out value="${user.userId}"></c:out>
                        </td>
                        <td>
                           <c:out value="${user.userName}"></c:out>
                        </td>
                        <td>
                           <c:out value="${user.password}"></c:out>
                        </td>
                        <td>
                           <c:out value="${user.userAge}"></c:out>
                        </td>
                        <td>
                           <a href="${pageContext.request.contextPath}/users/${user.userId}" class="btn btn-info">EDIT</a>
                        </td>
                        <td>
                           <a href="${pageContext.request.contextPath}/users/${user.userId}" class="btn btn-danger">DELETE</a>
                        </td>
                     </tr>
                  </c:forEach>
               </tbody>
            </table>
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