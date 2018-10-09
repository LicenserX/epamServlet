<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="pageVisits" value="${cookie['pageVisits'].value}"/>

<c:choose>
    <c:when test="${empty pageVisits}">
        <%
            Cookie pageVisitsCookie = new Cookie("pageVisits", "1");
            response.addCookie(pageVisitsCookie);
        %>
        <c:set var="pageVisits" value="1"/>
    </c:when>
    <c:otherwise>
        <%
            Cookie[] cookies = request.getCookies();
            Cookie pageVisitsCookie = null;
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("pageVisits")) {
                    pageVisitsCookie = cookie;
                    break;
                }
            }
            if (pageVisitsCookie != null) {
                pageVisitsCookie.setValue(Integer.valueOf(pageVisitsCookie.getValue()) + 1 + "");
                response.addCookie(pageVisitsCookie);
            }
        %>
        <c:set var="pageVisits" value="${cookie['pageVisits'].value}"/>
    </c:otherwise>
</c:choose>

<html>
<head>
    <title>Values API</title>
    <script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="js/crud.js"></script>
  </head>
<body>
<div>
    <h2>Info</h2>
    <p>
        Server time when responded:
        <strong> <fmt:formatDate type="both" value="${serverDate}"/> </strong><br>
        Server is running for ${not empty runtimeMs ? runtimeMs : -1} ms<br>
        <c:choose>
            <c:when test="${pageVisits > 0}">
                You visited this page ${pageVisits} times
            </c:when>
            <c:otherwise>
                You visit this page first time. Welcome!
            </c:otherwise>
        </c:choose>
    </p>
</div>
<div>
    <h2>ADD ENTITY</h2>
    <label for="name">Name</label>
    <input id="name"/>
    <input type="button" id="addValue" value="add"/>
    <div id="createStatus"></div>
</div>
<div>
    <h2>UPDATE ENTITY</h2>
    <label for="id">Id</label>
    <input id="id"/>
    <label for="value">Name</label>
    <input id="value"/>
    <input type="button" id="putValue" value="update"/>
    <div id="putStatus"></div>
</div>
<div>
    <h2>DELETE ENTITY</h2>
    <label for="idValue">Name</label>
    <input id="idValue"/>
    <input type="button" id="deleteValue" value="Delete"/>
    <div id="deleteStatus"></div>
</div>
<div>
    <h2>GET ALL ENTITIES</h2>
    <input type="button" id="getButton" value="Get all"/>
    <div id="getStatus"></div>
    <h3>Results</h3>
    <ul id="results"></ul>
</div>
</body>
</html>