<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: mmarkowski
  Date: 19.06.2022
  Time: 23:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
    <body>
<%@ include file="/header.jsp" %>
<c:forEach items="${users}" var="user">
    <tr>
        <td>${user}</td>
    </tr>
</c:forEach>
<%@ include file="/footer.jsp" %>
</body>
</html>

