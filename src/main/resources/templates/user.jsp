<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
      <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

         <!DOCTYPE html>
         <html>

         <head>
            <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">

            <title>USER APP :: USER</title>

            <link href="${pageContext.request.contextPath}/static/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
         </head>

         <body>

            <div class="container border p-5">
               <div class="row  p-1">

                  <form method="POST" 
                     action="${pageContext.request.contextPath}/users/<c:out value="${user.userId}"/>">

                     <c:choose>
                        <c:when test="${actionMode == 'POST'}">
                           <input type="hidden" name="actualHttpMethod" value="POST">   
                        </c:when>
                        <c:when test="${actionMode == 'PUT'}">
                           <input type="hidden" name="actualHttpMethod" value="PUT">      
                        </c:when>                     
                     </c:choose>

                     <div class="row border m-1 p-1 ">

                        <div class="form-group row p-1">
                           <label class="col-sm-2 col-form-label">User Data</label>
                           <div class="col-sm-6">
                              <hr/>
                           </div>
                        </div>


                        <input  id="userId" name="userId" value="<c:out value="${user.userId}"/>" type="hidden"/>

                        <div class="form-group row p-1">
                           <label class="col-sm-2 col-form-label">Name</label>
                           <div class="col-sm-6">
                              <input id="userName" name="userName" type="text" class="form-control" value="<c:out value="${user.userName}"/>"/>
                           </div>
                        </div>

                        <div class="form-group row p-1">
                           <label class="col-sm-2 col-form-label">Password</label>
                           <div class="col-sm-6">
                              <input id="password" name="password" type="text" class="form-control" value="<c:out value="${user.password}"/>"/>
                           </div>
                        </div>

                        <div class="form-group row p-1">
                           <label class="col-sm-2 col-form-label">Age</label>
                           <div class="col-sm-6">
                              <input id="userAge" name="userAge" type="text" class="form-control" value="<c:out value="${user.userAge}"/>"/>
                           </div>
                        </div>

                        <div class="form-group row p-1">
                           <label class="col-sm-2 col-form-label">E-Mail</label>
                           <div class="col-sm-6">
                              <input id="eMail" name="eMail" type="text" class="form-control" value="email@example.com"/>
                           </div>
                        </div>
                     </div>

                     <div class="row border m-1 p-1">
                        <label class="col-sm-2 col-form-label"></label>
                        <div class="col-sm-2">

                           <c:if test="${actionMode=='POST'}">
                              <button type="submit" class="btn btn-success">CREATE</button>                              
                           </c:if>                           

                           <c:if test="${actionMode=='PUT'}">
                              <button type="submit" class="btn btn-success">APPLY</button>                              
                           </c:if>                           

                        </div>
                     </div>                     

                  </form>

                  <div class="row border m-1 p-1">
                     <div class="col-sm-2">
                     </div>
                     <div class="col-sm-2">

                        <c:choose>
                           <c:when test="${actionMode == 'POST'}">

                           </c:when>

                           <c:when test="${actionMode == 'PUT'}">
                              <form method="POST" 
                                 action="${pageContext.request.contextPath}/users/<c:out value="${user.userId}"/>">
                                 <input type="hidden" name="actualHttpMethod" value="DELETE">
                                 <input  id="userId" name="userId" value="<c:out value="${user.userId}"/>" type="hidden"/>                                 
                                 <button type="submit" class="btn btn-danger">DELETE</button>
                              </form>
                           </c:when>                     

                        </c:choose>

                     </div>
                     <div class="col-sm-2">
                        <a href="${pageContext.request.contextPath}/users" class="btn btn-info">CANCEL</a>
                     </div>
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