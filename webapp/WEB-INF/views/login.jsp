<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Home Page</title>
</head>
<body bgcolor="#E8E8E8">



<%--<form action="<%=request.getContextPath()%>/chat" method="POST">--%>

<div style="background-image:url(${pageContext.request.contextPath}/images/texture.jpg); background-repeat:repeat; width: 100%; height: 150px">

    <img src="${pageContext.request.contextPath}/images/logo.png" width="228" height="150" style="margin: 0px 0px 0px 0px">

</div>

    <div style="width: 75%; margin-left: 25%">



        <table border="0">
            <c:if test="${wrongLoginOrPassword != null}">
                <tr>
                    <td><h6 style="color: #333333; text-align: center;"> ${wrongLoginOrPassword}</h6></td>
                </tr>
            </c:if>
            <tr>
                <td><h2 style="color: #4e4e4e; text-align: center; margin-top: 25px;">Log in:</h2></td>
            </tr>
        </table>

        <table border="0" style="margin-top: 10px">
            <form action="${pageContext.request.contextPath}/login" method="POST">
            <tr>
                <td> <b>Input login: </b></td>
                <td><input type="text" name="login" value="" size="10"/></td>
            </tr>

            <tr>
                <td> <b>Input password: </b></td>
                <td><input type="password" name="password" value="" size="10"/></td>
            </tr>

            <tr>
                <td> <input type="submit" value="OK"/></td></form>

                <td><form action="${pageContext.request.contextPath}/registration"  method="POST">
                    <input type="submit" value="Registration"/>
                    </form>
                </td>
            </tr>
        </table>
    </div>
</body>
</html>


