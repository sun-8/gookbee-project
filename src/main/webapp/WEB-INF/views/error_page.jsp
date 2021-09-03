<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="false" import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>에러 페이지</title>
</head>
<body>
	<h4><c:out value="${exception.getMessage()}"></c:out></h4>
	<%-- Model의 Attribute속성으로 저장한 exception --%>
	
	<ul>
		<c:forEach items="${exception.getStackTrace()}" var="stack">
			<li>
				<c:out value="${stack}"></c:out>
			</li>
		</c:forEach>
	</ul>
</body>
</html>